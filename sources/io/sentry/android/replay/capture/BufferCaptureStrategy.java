package io.sentry.android.replay.capture;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import io.sentry.DateUtils;
import io.sentry.IHub;
import io.sentry.IScope;
import io.sentry.ScopeCallback;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.SentryReplayEvent;
import io.sentry.android.replay.ReplayCache;
import io.sentry.android.replay.ReplayFrame;
import io.sentry.android.replay.ScreenshotRecorderConfig;
import io.sentry.android.replay.capture.CaptureStrategy;
import io.sentry.android.replay.util.ExecutorsKt;
import io.sentry.android.replay.util.SamplingKt;
import io.sentry.protocol.SentryId;
import io.sentry.rrweb.RRWebOptionsEvent;
import io.sentry.transport.ICurrentDateProvider;
import io.sentry.util.FileUtils;
import io.sentry.util.Random;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: BufferCaptureStrategy.kt */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u0000 :2\u00020\u0001:\u0001:BV\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012%\b\u0002\u0010\f\u001a\u001f\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\r¢\u0006\u0002\u0010\u0013J$\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00180\rH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J$\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00180\rH\u0002J\u0012\u0010$\u001a\u00020\u00182\b\u0010%\u001a\u0004\u0018\u00010&H\u0002J\u0010\u0010'\u001a\u00020\u00182\u0006\u0010(\u001a\u00020)H\u0016J@\u0010*\u001a\u00020\u00182\b\u0010+\u001a\u0004\u0018\u00010,2,\u0010-\u001a(\u0012\u0004\u0012\u00020\u0012\u0012\u0013\u0012\u00110/¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u00180.¢\u0006\u0002\b1H\u0016J\u0010\u00102\u001a\u00020\u00182\u0006\u00103\u001a\u000204H\u0016J\b\u00105\u001a\u00020\u0018H\u0016J\b\u00106\u001a\u00020\u0018H\u0016J\u0012\u00107\u001a\u00020\u0018*\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0002J\u001a\u00108\u001a\u00020\u0018*\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u00109\u001a\u00020/H\u0002R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lio/sentry/android/replay/capture/BufferCaptureStrategy;", "Lio/sentry/android/replay/capture/BaseCaptureStrategy;", RRWebOptionsEvent.EVENT_TAG, "Lio/sentry/SentryOptions;", "hub", "Lio/sentry/IHub;", "dateProvider", "Lio/sentry/transport/ICurrentDateProvider;", "random", "Lio/sentry/util/Random;", "executor", "Ljava/util/concurrent/ScheduledExecutorService;", "replayCacheProvider", "Lkotlin/Function1;", "Lio/sentry/protocol/SentryId;", "Lkotlin/ParameterName;", "name", "replayId", "Lio/sentry/android/replay/ReplayCache;", "(Lio/sentry/SentryOptions;Lio/sentry/IHub;Lio/sentry/transport/ICurrentDateProvider;Lio/sentry/util/Random;Ljava/util/concurrent/ScheduledExecutorService;Lkotlin/jvm/functions/Function1;)V", "bufferedSegments", "", "Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment$Created;", "captureReplay", "", "isTerminating", "", "onSegmentSent", "Ljava/util/Date;", "convert", "Lio/sentry/android/replay/capture/CaptureStrategy;", "createCurrentSegment", "taskName", "", "onSegmentCreated", "Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment;", "deleteFile", "file", "Ljava/io/File;", "onConfigurationChanged", "recorderConfig", "Lio/sentry/android/replay/ScreenshotRecorderConfig;", "onScreenshotRecorded", "bitmap", "Landroid/graphics/Bitmap;", "store", "Lkotlin/Function2;", "", "frameTimestamp", "Lkotlin/ExtensionFunctionType;", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "pause", "stop", "capture", "rotate", "bufferLimit", "Companion", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class BufferCaptureStrategy extends BaseCaptureStrategy {
    private static final long ENVELOPE_PROCESSING_DELAY = 100;
    private static final String TAG = "BufferCaptureStrategy";
    private final List<CaptureStrategy.ReplaySegment.Created> bufferedSegments;
    private final ICurrentDateProvider dateProvider;
    private final IHub hub;
    private final SentryOptions options;
    private final Random random;

    public /* synthetic */ BufferCaptureStrategy(SentryOptions sentryOptions, IHub iHub, ICurrentDateProvider iCurrentDateProvider, Random random, ScheduledExecutorService scheduledExecutorService, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(sentryOptions, iHub, iCurrentDateProvider, random, scheduledExecutorService, (i & 32) != 0 ? null : function1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BufferCaptureStrategy(SentryOptions options, IHub iHub, ICurrentDateProvider dateProvider, Random random, ScheduledExecutorService executor, Function1<? super SentryId, ReplayCache> function1) {
        super(options, iHub, dateProvider, executor, function1);
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(dateProvider, "dateProvider");
        Intrinsics.checkNotNullParameter(random, "random");
        Intrinsics.checkNotNullParameter(executor, "executor");
        this.options = options;
        this.hub = iHub;
        this.dateProvider = dateProvider;
        this.random = random;
        this.bufferedSegments = new ArrayList();
    }

    @Override // io.sentry.android.replay.capture.BaseCaptureStrategy, io.sentry.android.replay.capture.CaptureStrategy
    public void pause() {
        createCurrentSegment("pause", new Function1<CaptureStrategy.ReplaySegment, Unit>() { // from class: io.sentry.android.replay.capture.BufferCaptureStrategy.pause.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(CaptureStrategy.ReplaySegment replaySegment) {
                invoke2(replaySegment);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(CaptureStrategy.ReplaySegment segment) {
                Intrinsics.checkNotNullParameter(segment, "segment");
                if (segment instanceof CaptureStrategy.ReplaySegment.Created) {
                    BufferCaptureStrategy.this.bufferedSegments.add(segment);
                    BufferCaptureStrategy bufferCaptureStrategy = BufferCaptureStrategy.this;
                    bufferCaptureStrategy.setCurrentSegment(bufferCaptureStrategy.getCurrentSegment() + 1);
                }
            }
        });
        super.pause();
    }

    @Override // io.sentry.android.replay.capture.BaseCaptureStrategy, io.sentry.android.replay.capture.CaptureStrategy
    public void stop() {
        ReplayCache cache = getCache();
        final File replayCacheDir$sentry_android_replay_release = cache != null ? cache.getReplayCacheDir$sentry_android_replay_release() : null;
        ExecutorsKt.submitSafely(getReplayExecutor(), this.options, "BufferCaptureStrategy.stop", new Runnable() { // from class: io.sentry.android.replay.capture.BufferCaptureStrategy$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FileUtils.deleteRecursively(replayCacheDir$sentry_android_replay_release);
            }
        });
        super.stop();
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public void captureReplay(boolean isTerminating, final Function1<? super Date, Unit> onSegmentSent) {
        Intrinsics.checkNotNullParameter(onSegmentSent, "onSegmentSent");
        if (!SamplingKt.sample(this.random, this.options.getSessionReplay().getOnErrorSampleRate())) {
            this.options.getLogger().log(SentryLevel.INFO, "Replay wasn't sampled by onErrorSampleRate, not capturing for event", new Object[0]);
            return;
        }
        IHub iHub = this.hub;
        if (iHub != null) {
            iHub.configureScope(new ScopeCallback() { // from class: io.sentry.android.replay.capture.BufferCaptureStrategy$$ExternalSyntheticLambda2
                @Override // io.sentry.ScopeCallback
                public final void run(IScope iScope) {
                    BufferCaptureStrategy.captureReplay$lambda$1(this.f$0, iScope);
                }
            });
        }
        if (isTerminating) {
            getIsTerminating().set(true);
            this.options.getLogger().log(SentryLevel.DEBUG, "Not capturing replay for crashed event, will be captured on next launch", new Object[0]);
        } else {
            createCurrentSegment("capture_replay", new Function1<CaptureStrategy.ReplaySegment, Unit>() { // from class: io.sentry.android.replay.capture.BufferCaptureStrategy.captureReplay.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(CaptureStrategy.ReplaySegment replaySegment) throws InterruptedException {
                    invoke2(replaySegment);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(CaptureStrategy.ReplaySegment segment) throws InterruptedException {
                    Intrinsics.checkNotNullParameter(segment, "segment");
                    BufferCaptureStrategy bufferCaptureStrategy = BufferCaptureStrategy.this;
                    bufferCaptureStrategy.capture(bufferCaptureStrategy.bufferedSegments);
                    if (segment instanceof CaptureStrategy.ReplaySegment.Created) {
                        CaptureStrategy.ReplaySegment.Created created = (CaptureStrategy.ReplaySegment.Created) segment;
                        CaptureStrategy.ReplaySegment.Created.capture$default(created, BufferCaptureStrategy.this.hub, null, 2, null);
                        Function1<Date, Unit> function1 = onSegmentSent;
                        Date timestamp = created.getReplay().getTimestamp();
                        Intrinsics.checkNotNullExpressionValue(timestamp, "segment.replay.timestamp");
                        function1.invoke(timestamp);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void captureReplay$lambda$1(BufferCaptureStrategy this$0, IScope it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        it.setReplayId(this$0.getCurrentReplayId());
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public void onScreenshotRecorded(Bitmap bitmap, final Function2<? super ReplayCache, ? super Long, Unit> store) {
        Intrinsics.checkNotNullParameter(store, "store");
        final long currentTimeMillis = this.dateProvider.getCurrentTimeMillis();
        ExecutorsKt.submitSafely(getReplayExecutor(), this.options, "BufferCaptureStrategy.add_frame", new Runnable() { // from class: io.sentry.android.replay.capture.BufferCaptureStrategy$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                BufferCaptureStrategy.onScreenshotRecorded$lambda$2(this.f$0, store, currentTimeMillis);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onScreenshotRecorded$lambda$2(BufferCaptureStrategy this$0, Function2 store, long j) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(store, "$store");
        ReplayCache cache = this$0.getCache();
        if (cache != null) {
            store.invoke(cache, Long.valueOf(j));
        }
        long currentTimeMillis = this$0.dateProvider.getCurrentTimeMillis() - this$0.options.getSessionReplay().getErrorReplayDuration();
        ReplayCache cache2 = this$0.getCache();
        this$0.setScreenAtStart(cache2 != null ? cache2.rotate(currentTimeMillis) : null);
        this$0.rotate(this$0.bufferedSegments, currentTimeMillis);
    }

    @Override // io.sentry.android.replay.capture.BaseCaptureStrategy, io.sentry.android.replay.capture.CaptureStrategy
    public void onConfigurationChanged(ScreenshotRecorderConfig recorderConfig) {
        Intrinsics.checkNotNullParameter(recorderConfig, "recorderConfig");
        createCurrentSegment("configuration_changed", new Function1<CaptureStrategy.ReplaySegment, Unit>() { // from class: io.sentry.android.replay.capture.BufferCaptureStrategy.onConfigurationChanged.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(CaptureStrategy.ReplaySegment replaySegment) {
                invoke2(replaySegment);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(CaptureStrategy.ReplaySegment segment) {
                Intrinsics.checkNotNullParameter(segment, "segment");
                if (segment instanceof CaptureStrategy.ReplaySegment.Created) {
                    BufferCaptureStrategy.this.bufferedSegments.add(segment);
                    BufferCaptureStrategy bufferCaptureStrategy = BufferCaptureStrategy.this;
                    bufferCaptureStrategy.setCurrentSegment(bufferCaptureStrategy.getCurrentSegment() + 1);
                }
            }
        });
        super.onConfigurationChanged(recorderConfig);
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public CaptureStrategy convert() {
        if (getIsTerminating().get()) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Not converting to session mode, because the process is about to terminate", new Object[0]);
            return this;
        }
        SessionCaptureStrategy sessionCaptureStrategy = new SessionCaptureStrategy(this.options, this.hub, this.dateProvider, getReplayExecutor(), null, 16, null);
        sessionCaptureStrategy.start(getRecorderConfig(), getCurrentSegment(), getCurrentReplayId(), SentryReplayEvent.ReplayType.BUFFER);
        return sessionCaptureStrategy;
    }

    @Override // io.sentry.android.replay.capture.BaseCaptureStrategy, io.sentry.android.replay.capture.CaptureStrategy
    public void onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        super.onTouchEvent(event);
        CaptureStrategy.Companion.rotateEvents$sentry_android_replay_release$default(CaptureStrategy.INSTANCE, getCurrentEvents(), this.dateProvider.getCurrentTimeMillis() - this.options.getSessionReplay().getErrorReplayDuration(), null, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteFile(File file) {
        if (file == null) {
            return;
        }
        try {
            if (file.delete()) {
                return;
            }
            this.options.getLogger().log(SentryLevel.ERROR, "Failed to delete replay segment: %s", file.getAbsolutePath());
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.ERROR, th, "Failed to delete replay segment: %s", file.getAbsolutePath());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void capture(List<CaptureStrategy.ReplaySegment.Created> list) throws InterruptedException {
        CaptureStrategy.ReplaySegment.Created created = (CaptureStrategy.ReplaySegment.Created) CollectionsKt.removeFirstOrNull(list);
        while (created != null) {
            CaptureStrategy.ReplaySegment.Created.capture$default(created, this.hub, null, 2, null);
            created = (CaptureStrategy.ReplaySegment.Created) CollectionsKt.removeFirstOrNull(list);
            Thread.sleep(100L);
        }
    }

    private final void rotate(List<CaptureStrategy.ReplaySegment.Created> list, final long j) {
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        CollectionsKt.removeAll((List) list, (Function1) new Function1<CaptureStrategy.ReplaySegment.Created, Boolean>() { // from class: io.sentry.android.replay.capture.BufferCaptureStrategy.rotate.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(CaptureStrategy.ReplaySegment.Created it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (it.getReplay().getTimestamp().getTime() < j) {
                    this.setCurrentSegment(r0.getCurrentSegment() - 1);
                    this.deleteFile(it.getReplay().getVideoFile());
                    booleanRef.element = true;
                    return true;
                }
                return false;
            }
        });
        if (booleanRef.element) {
            int i = 0;
            for (Object obj : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                ((CaptureStrategy.ReplaySegment.Created) obj).setSegmentId(i);
                i = i2;
            }
        }
    }

    private final void createCurrentSegment(String taskName, final Function1<? super CaptureStrategy.ReplaySegment, Unit> onSegmentCreated) {
        Date dateTime;
        List<ReplayFrame> frames$sentry_android_replay_release;
        long errorReplayDuration = this.options.getSessionReplay().getErrorReplayDuration();
        long currentTimeMillis = this.dateProvider.getCurrentTimeMillis();
        ReplayCache cache = getCache();
        if (cache != null && (frames$sentry_android_replay_release = cache.getFrames$sentry_android_replay_release()) != null && (!frames$sentry_android_replay_release.isEmpty())) {
            ReplayCache cache2 = getCache();
            Intrinsics.checkNotNull(cache2);
            dateTime = DateUtils.getDateTime(((ReplayFrame) CollectionsKt.first((List) cache2.getFrames$sentry_android_replay_release())).getTimestamp());
        } else {
            dateTime = DateUtils.getDateTime(currentTimeMillis - errorReplayDuration);
        }
        final Date date = dateTime;
        Intrinsics.checkNotNullExpressionValue(date, "if (cache?.frames?.isNot…ReplayDuration)\n        }");
        final int currentSegment = getCurrentSegment();
        final long time = currentTimeMillis - date.getTime();
        final SentryId currentReplayId = getCurrentReplayId();
        final int recordingHeight = getRecorderConfig().getRecordingHeight();
        final int recordingWidth = getRecorderConfig().getRecordingWidth();
        ExecutorsKt.submitSafely(getReplayExecutor(), this.options, "BufferCaptureStrategy." + taskName, new Runnable() { // from class: io.sentry.android.replay.capture.BufferCaptureStrategy$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                BufferCaptureStrategy.createCurrentSegment$lambda$4(this.f$0, time, date, currentReplayId, currentSegment, recordingHeight, recordingWidth, onSegmentCreated);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createCurrentSegment$lambda$4(BufferCaptureStrategy this$0, long j, Date currentSegmentTimestamp, SentryId replayId, int i, int i2, int i3, Function1 onSegmentCreated) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(currentSegmentTimestamp, "$currentSegmentTimestamp");
        Intrinsics.checkNotNullParameter(replayId, "$replayId");
        Intrinsics.checkNotNullParameter(onSegmentCreated, "$onSegmentCreated");
        onSegmentCreated.invoke(BaseCaptureStrategy.createSegmentInternal$default(this$0, j, currentSegmentTimestamp, replayId, i, i2, i3, null, null, 0, 0, null, null, null, 8128, null));
    }
}
