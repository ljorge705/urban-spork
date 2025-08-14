package io.sentry.android.core;

import android.os.Debug;
import android.os.Process;
import android.os.SystemClock;
import io.sentry.CpuCollectionData;
import io.sentry.DateUtils;
import io.sentry.ILogger;
import io.sentry.ISentryExecutorService;
import io.sentry.MemoryCollectionData;
import io.sentry.PerformanceCollectionData;
import io.sentry.SentryLevel;
import io.sentry.android.core.internal.util.SentryFrameMetricsCollector;
import io.sentry.profilemeasurements.ProfileMeasurement;
import io.sentry.profilemeasurements.ProfileMeasurementValue;
import io.sentry.util.Objects;
import java.io.File;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class AndroidProfiler {
    private static final int BUFFER_SIZE_BYTES = 3000000;
    private static final int PROFILING_TIMEOUT_MILLIS = 30000;
    private final BuildInfoProvider buildInfoProvider;
    private final ISentryExecutorService executorService;
    private final SentryFrameMetricsCollector frameMetricsCollector;
    private String frameMetricsCollectorId;
    private final int intervalUs;
    private final ILogger logger;
    private final File traceFilesDir;
    private long profileStartNanos = 0;
    private Future<?> scheduledFinish = null;
    private File traceFile = null;
    private final ArrayDeque<ProfileMeasurementValue> screenFrameRateMeasurements = new ArrayDeque<>();
    private final ArrayDeque<ProfileMeasurementValue> slowFrameRenderMeasurements = new ArrayDeque<>();
    private final ArrayDeque<ProfileMeasurementValue> frozenFrameRenderMeasurements = new ArrayDeque<>();
    private final Map<String, ProfileMeasurement> measurementsMap = new HashMap();
    private boolean isRunning = false;

    public static class ProfileStartData {
        public final long startCpuMillis;
        public final long startNanos;
        public final Date startTimestamp;

        public ProfileStartData(long j, long j2, Date date) {
            this.startNanos = j;
            this.startCpuMillis = j2;
            this.startTimestamp = date;
        }
    }

    public static class ProfileEndData {
        public final boolean didTimeout;
        public final long endCpuMillis;
        public final long endNanos;
        public final Map<String, ProfileMeasurement> measurementsMap;
        public final File traceFile;

        public ProfileEndData(long j, long j2, boolean z, File file, Map<String, ProfileMeasurement> map) {
            this.endNanos = j;
            this.traceFile = file;
            this.endCpuMillis = j2;
            this.measurementsMap = map;
            this.didTimeout = z;
        }
    }

    public AndroidProfiler(String str, int i, SentryFrameMetricsCollector sentryFrameMetricsCollector, ISentryExecutorService iSentryExecutorService, ILogger iLogger, BuildInfoProvider buildInfoProvider) {
        this.traceFilesDir = new File((String) Objects.requireNonNull(str, "TracesFilesDirPath is required"));
        this.intervalUs = i;
        this.logger = (ILogger) Objects.requireNonNull(iLogger, "Logger is required");
        this.executorService = (ISentryExecutorService) Objects.requireNonNull(iSentryExecutorService, "ExecutorService is required.");
        this.frameMetricsCollector = (SentryFrameMetricsCollector) Objects.requireNonNull(sentryFrameMetricsCollector, "SentryFrameMetricsCollector is required");
        this.buildInfoProvider = (BuildInfoProvider) Objects.requireNonNull(buildInfoProvider, "The BuildInfoProvider is required.");
    }

    public synchronized ProfileStartData start() {
        if (this.intervalUs == 0) {
            this.logger.log(SentryLevel.WARNING, "Disabling profiling because intervaUs is set to %d", Integer.valueOf(this.intervalUs));
            return null;
        }
        if (this.isRunning) {
            this.logger.log(SentryLevel.WARNING, "Profiling has already started...", new Object[0]);
            return null;
        }
        if (this.buildInfoProvider.getSdkInfoVersion() < 21) {
            return null;
        }
        this.traceFile = new File(this.traceFilesDir, UUID.randomUUID() + ".trace");
        this.measurementsMap.clear();
        this.screenFrameRateMeasurements.clear();
        this.slowFrameRenderMeasurements.clear();
        this.frozenFrameRenderMeasurements.clear();
        this.frameMetricsCollectorId = this.frameMetricsCollector.startCollection(new SentryFrameMetricsCollector.FrameMetricsCollectorListener() { // from class: io.sentry.android.core.AndroidProfiler.1
            float lastRefreshRate = 0.0f;

            @Override // io.sentry.android.core.internal.util.SentryFrameMetricsCollector.FrameMetricsCollectorListener
            public void onFrameMetricCollected(long j, long j2, long j3, long j4, boolean z, boolean z2, float f) {
                long jNanoTime = ((j2 - System.nanoTime()) + SystemClock.elapsedRealtimeNanos()) - AndroidProfiler.this.profileStartNanos;
                if (jNanoTime < 0) {
                    return;
                }
                if (z2) {
                    AndroidProfiler.this.frozenFrameRenderMeasurements.addLast(new ProfileMeasurementValue(Long.valueOf(jNanoTime), Long.valueOf(j3)));
                } else if (z) {
                    AndroidProfiler.this.slowFrameRenderMeasurements.addLast(new ProfileMeasurementValue(Long.valueOf(jNanoTime), Long.valueOf(j3)));
                }
                if (f != this.lastRefreshRate) {
                    this.lastRefreshRate = f;
                    AndroidProfiler.this.screenFrameRateMeasurements.addLast(new ProfileMeasurementValue(Long.valueOf(jNanoTime), Float.valueOf(f)));
                }
            }
        });
        try {
            this.scheduledFinish = this.executorService.schedule(new Runnable() { // from class: io.sentry.android.core.AndroidProfiler$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m6011lambda$start$0$iosentryandroidcoreAndroidProfiler();
                }
            }, 30000L);
        } catch (RejectedExecutionException e) {
            this.logger.log(SentryLevel.ERROR, "Failed to call the executor. Profiling will not be automatically finished. Did you call Sentry.close()?", e);
        }
        this.profileStartNanos = SystemClock.elapsedRealtimeNanos();
        Date currentDateTime = DateUtils.getCurrentDateTime();
        long elapsedCpuTime = Process.getElapsedCpuTime();
        try {
            Debug.startMethodTracingSampling(this.traceFile.getPath(), BUFFER_SIZE_BYTES, this.intervalUs);
            this.isRunning = true;
            return new ProfileStartData(this.profileStartNanos, elapsedCpuTime, currentDateTime);
        } catch (Throwable th) {
            endAndCollect(false, null);
            this.logger.log(SentryLevel.ERROR, "Unable to start a profile: ", th);
            this.isRunning = false;
            return null;
        }
    }

    /* renamed from: lambda$start$0$io-sentry-android-core-AndroidProfiler, reason: not valid java name */
    /* synthetic */ void m6011lambda$start$0$iosentryandroidcoreAndroidProfiler() {
        endAndCollect(true, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0044 A[Catch: all -> 0x00b7, TRY_LEAVE, TryCatch #2 {, blocks: (B:3:0x0001, B:5:0x0007, B:8:0x0014, B:14:0x0023, B:19:0x0031, B:21:0x0044, B:24:0x0051, B:26:0x0059, B:27:0x0069, B:29:0x0071, B:30:0x0081, B:32:0x0089, B:33:0x0099, B:35:0x00a0, B:36:0x00a6, B:40:0x00b4, B:41:0x00b6, B:17:0x0027, B:13:0x0020), top: B:49:0x0001, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0051 A[Catch: all -> 0x00b7, TRY_ENTER, TryCatch #2 {, blocks: (B:3:0x0001, B:5:0x0007, B:8:0x0014, B:14:0x0023, B:19:0x0031, B:21:0x0044, B:24:0x0051, B:26:0x0059, B:27:0x0069, B:29:0x0071, B:30:0x0081, B:32:0x0089, B:33:0x0099, B:35:0x00a0, B:36:0x00a6, B:40:0x00b4, B:41:0x00b6, B:17:0x0027, B:13:0x0020), top: B:49:0x0001, inners: #0, #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized io.sentry.android.core.AndroidProfiler.ProfileEndData endAndCollect(boolean r13, java.util.List<io.sentry.PerformanceCollectionData> r14) {
        /*
            r12 = this;
            monitor-enter(r12)
            boolean r0 = r12.isRunning     // Catch: java.lang.Throwable -> Lb7
            r1 = 0
            r2 = 0
            if (r0 != 0) goto L14
            io.sentry.ILogger r13 = r12.logger     // Catch: java.lang.Throwable -> Lb7
            io.sentry.SentryLevel r14 = io.sentry.SentryLevel.WARNING     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r0 = "Profiler not running"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> Lb7
            r13.log(r14, r0, r2)     // Catch: java.lang.Throwable -> Lb7
            monitor-exit(r12)
            return r1
        L14:
            io.sentry.android.core.BuildInfoProvider r0 = r12.buildInfoProvider     // Catch: java.lang.Throwable -> Lb7
            int r0 = r0.getSdkInfoVersion()     // Catch: java.lang.Throwable -> Lb7
            r3 = 21
            if (r0 >= r3) goto L20
            monitor-exit(r12)
            return r1
        L20:
            android.os.Debug.stopMethodTracing()     // Catch: java.lang.Throwable -> L26
        L23:
            r12.isRunning = r2     // Catch: java.lang.Throwable -> Lb7
            goto L31
        L26:
            r0 = move-exception
            io.sentry.ILogger r3 = r12.logger     // Catch: java.lang.Throwable -> Lb3
            io.sentry.SentryLevel r4 = io.sentry.SentryLevel.ERROR     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r5 = "Error while stopping profiling: "
            r3.log(r4, r5, r0)     // Catch: java.lang.Throwable -> Lb3
            goto L23
        L31:
            io.sentry.android.core.internal.util.SentryFrameMetricsCollector r0 = r12.frameMetricsCollector     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r3 = r12.frameMetricsCollectorId     // Catch: java.lang.Throwable -> Lb7
            r0.stopCollection(r3)     // Catch: java.lang.Throwable -> Lb7
            long r5 = android.os.SystemClock.elapsedRealtimeNanos()     // Catch: java.lang.Throwable -> Lb7
            long r7 = android.os.Process.getElapsedCpuTime()     // Catch: java.lang.Throwable -> Lb7
            java.io.File r0 = r12.traceFile     // Catch: java.lang.Throwable -> Lb7
            if (r0 != 0) goto L51
            io.sentry.ILogger r13 = r12.logger     // Catch: java.lang.Throwable -> Lb7
            io.sentry.SentryLevel r14 = io.sentry.SentryLevel.ERROR     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r0 = "Trace file does not exists"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> Lb7
            r13.log(r14, r0, r2)     // Catch: java.lang.Throwable -> Lb7
            monitor-exit(r12)
            return r1
        L51:
            java.util.ArrayDeque<io.sentry.profilemeasurements.ProfileMeasurementValue> r0 = r12.slowFrameRenderMeasurements     // Catch: java.lang.Throwable -> Lb7
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> Lb7
            if (r0 != 0) goto L69
            java.util.Map<java.lang.String, io.sentry.profilemeasurements.ProfileMeasurement> r0 = r12.measurementsMap     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r2 = "slow_frame_renders"
            io.sentry.profilemeasurements.ProfileMeasurement r3 = new io.sentry.profilemeasurements.ProfileMeasurement     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r4 = "nanosecond"
            java.util.ArrayDeque<io.sentry.profilemeasurements.ProfileMeasurementValue> r9 = r12.slowFrameRenderMeasurements     // Catch: java.lang.Throwable -> Lb7
            r3.<init>(r4, r9)     // Catch: java.lang.Throwable -> Lb7
            r0.put(r2, r3)     // Catch: java.lang.Throwable -> Lb7
        L69:
            java.util.ArrayDeque<io.sentry.profilemeasurements.ProfileMeasurementValue> r0 = r12.frozenFrameRenderMeasurements     // Catch: java.lang.Throwable -> Lb7
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> Lb7
            if (r0 != 0) goto L81
            java.util.Map<java.lang.String, io.sentry.profilemeasurements.ProfileMeasurement> r0 = r12.measurementsMap     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r2 = "frozen_frame_renders"
            io.sentry.profilemeasurements.ProfileMeasurement r3 = new io.sentry.profilemeasurements.ProfileMeasurement     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r4 = "nanosecond"
            java.util.ArrayDeque<io.sentry.profilemeasurements.ProfileMeasurementValue> r9 = r12.frozenFrameRenderMeasurements     // Catch: java.lang.Throwable -> Lb7
            r3.<init>(r4, r9)     // Catch: java.lang.Throwable -> Lb7
            r0.put(r2, r3)     // Catch: java.lang.Throwable -> Lb7
        L81:
            java.util.ArrayDeque<io.sentry.profilemeasurements.ProfileMeasurementValue> r0 = r12.screenFrameRateMeasurements     // Catch: java.lang.Throwable -> Lb7
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> Lb7
            if (r0 != 0) goto L99
            java.util.Map<java.lang.String, io.sentry.profilemeasurements.ProfileMeasurement> r0 = r12.measurementsMap     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r2 = "screen_frame_rates"
            io.sentry.profilemeasurements.ProfileMeasurement r3 = new io.sentry.profilemeasurements.ProfileMeasurement     // Catch: java.lang.Throwable -> Lb7
            java.lang.String r4 = "hz"
            java.util.ArrayDeque<io.sentry.profilemeasurements.ProfileMeasurementValue> r9 = r12.screenFrameRateMeasurements     // Catch: java.lang.Throwable -> Lb7
            r3.<init>(r4, r9)     // Catch: java.lang.Throwable -> Lb7
            r0.put(r2, r3)     // Catch: java.lang.Throwable -> Lb7
        L99:
            r12.putPerformanceCollectionDataInMeasurements(r14)     // Catch: java.lang.Throwable -> Lb7
            java.util.concurrent.Future<?> r14 = r12.scheduledFinish     // Catch: java.lang.Throwable -> Lb7
            if (r14 == 0) goto La6
            r0 = 1
            r14.cancel(r0)     // Catch: java.lang.Throwable -> Lb7
            r12.scheduledFinish = r1     // Catch: java.lang.Throwable -> Lb7
        La6:
            io.sentry.android.core.AndroidProfiler$ProfileEndData r14 = new io.sentry.android.core.AndroidProfiler$ProfileEndData     // Catch: java.lang.Throwable -> Lb7
            java.io.File r10 = r12.traceFile     // Catch: java.lang.Throwable -> Lb7
            java.util.Map<java.lang.String, io.sentry.profilemeasurements.ProfileMeasurement> r11 = r12.measurementsMap     // Catch: java.lang.Throwable -> Lb7
            r4 = r14
            r9 = r13
            r4.<init>(r5, r7, r9, r10, r11)     // Catch: java.lang.Throwable -> Lb7
            monitor-exit(r12)
            return r14
        Lb3:
            r13 = move-exception
            r12.isRunning = r2     // Catch: java.lang.Throwable -> Lb7
            throw r13     // Catch: java.lang.Throwable -> Lb7
        Lb7:
            r13 = move-exception
            monitor-exit(r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.AndroidProfiler.endAndCollect(boolean, java.util.List):io.sentry.android.core.AndroidProfiler$ProfileEndData");
    }

    public synchronized void close() {
        Future<?> future = this.scheduledFinish;
        if (future != null) {
            future.cancel(true);
            this.scheduledFinish = null;
        }
        if (this.isRunning) {
            endAndCollect(true, null);
        }
    }

    private void putPerformanceCollectionDataInMeasurements(List<PerformanceCollectionData> list) {
        if (this.buildInfoProvider.getSdkInfoVersion() < 21) {
            return;
        }
        long jElapsedRealtimeNanos = (SystemClock.elapsedRealtimeNanos() - this.profileStartNanos) - TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
        if (list != null) {
            ArrayDeque arrayDeque = new ArrayDeque(list.size());
            ArrayDeque arrayDeque2 = new ArrayDeque(list.size());
            ArrayDeque arrayDeque3 = new ArrayDeque(list.size());
            synchronized (list) {
                for (PerformanceCollectionData performanceCollectionData : list) {
                    CpuCollectionData cpuData = performanceCollectionData.getCpuData();
                    MemoryCollectionData memoryData = performanceCollectionData.getMemoryData();
                    if (cpuData != null) {
                        arrayDeque3.add(new ProfileMeasurementValue(Long.valueOf(TimeUnit.MILLISECONDS.toNanos(cpuData.getTimestampMillis()) + jElapsedRealtimeNanos), Double.valueOf(cpuData.getCpuUsagePercentage())));
                    }
                    if (memoryData != null && memoryData.getUsedHeapMemory() > -1) {
                        arrayDeque.add(new ProfileMeasurementValue(Long.valueOf(TimeUnit.MILLISECONDS.toNanos(memoryData.getTimestampMillis()) + jElapsedRealtimeNanos), Long.valueOf(memoryData.getUsedHeapMemory())));
                    }
                    if (memoryData != null && memoryData.getUsedNativeMemory() > -1) {
                        arrayDeque2.add(new ProfileMeasurementValue(Long.valueOf(TimeUnit.MILLISECONDS.toNanos(memoryData.getTimestampMillis()) + jElapsedRealtimeNanos), Long.valueOf(memoryData.getUsedNativeMemory())));
                    }
                }
            }
            if (!arrayDeque3.isEmpty()) {
                this.measurementsMap.put(ProfileMeasurement.ID_CPU_USAGE, new ProfileMeasurement(ProfileMeasurement.UNIT_PERCENT, arrayDeque3));
            }
            if (!arrayDeque.isEmpty()) {
                this.measurementsMap.put(ProfileMeasurement.ID_MEMORY_FOOTPRINT, new ProfileMeasurement(ProfileMeasurement.UNIT_BYTES, arrayDeque));
            }
            if (arrayDeque2.isEmpty()) {
                return;
            }
            this.measurementsMap.put(ProfileMeasurement.ID_MEMORY_NATIVE_FOOTPRINT, new ProfileMeasurement(ProfileMeasurement.UNIT_BYTES, arrayDeque2));
        }
    }
}
