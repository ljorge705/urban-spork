package io.sentry.android.replay.capture;

import android.graphics.Bitmap;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.IHub;
import io.sentry.IScope;
import io.sentry.ScopeCallback;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.SentryReplayEvent;
import io.sentry.android.replay.ReplayCache;
import io.sentry.android.replay.ScreenshotRecorderConfig;
import io.sentry.android.replay.capture.CaptureStrategy;
import io.sentry.android.replay.util.ExecutorsKt;
import io.sentry.protocol.SentryId;
import io.sentry.rrweb.RRWebOptionsEvent;
import io.sentry.rrweb.RRWebVideoEvent;
import io.sentry.transport.ICurrentDateProvider;
import io.sentry.util.FileUtils;
import java.io.File;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: SessionCaptureStrategy.kt */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 12\u00020\u0001:\u00011BN\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012%\b\u0002\u0010\n\u001a\u001f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000b¢\u0006\u0002\u0010\u0011J$\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00130\u000bH\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J$\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001c2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00130\u000bH\u0002J\u0010\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!H\u0016J@\u0010\"\u001a\u00020\u00132\b\u0010#\u001a\u0004\u0018\u00010$2,\u0010%\u001a(\u0012\u0004\u0012\u00020\u0010\u0012\u0013\u0012\u00110'¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b((\u0012\u0004\u0012\u00020\u00130&¢\u0006\u0002\b)H\u0016J\b\u0010*\u001a\u00020\u0013H\u0016J*\u0010+\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!2\u0006\u0010,\u001a\u00020-2\u0006\u0010\u000f\u001a\u00020\f2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u00100\u001a\u00020\u0013H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lio/sentry/android/replay/capture/SessionCaptureStrategy;", "Lio/sentry/android/replay/capture/BaseCaptureStrategy;", RRWebOptionsEvent.EVENT_TAG, "Lio/sentry/SentryOptions;", "hub", "Lio/sentry/IHub;", "dateProvider", "Lio/sentry/transport/ICurrentDateProvider;", "executor", "Ljava/util/concurrent/ScheduledExecutorService;", "replayCacheProvider", "Lkotlin/Function1;", "Lio/sentry/protocol/SentryId;", "Lkotlin/ParameterName;", "name", "replayId", "Lio/sentry/android/replay/ReplayCache;", "(Lio/sentry/SentryOptions;Lio/sentry/IHub;Lio/sentry/transport/ICurrentDateProvider;Ljava/util/concurrent/ScheduledExecutorService;Lkotlin/jvm/functions/Function1;)V", "captureReplay", "", "isTerminating", "", "onSegmentSent", "Ljava/util/Date;", "convert", "Lio/sentry/android/replay/capture/CaptureStrategy;", "createCurrentSegment", "taskName", "", "onSegmentCreated", "Lio/sentry/android/replay/capture/CaptureStrategy$ReplaySegment;", "onConfigurationChanged", "recorderConfig", "Lio/sentry/android/replay/ScreenshotRecorderConfig;", "onScreenshotRecorded", "bitmap", "Landroid/graphics/Bitmap;", "store", "Lkotlin/Function2;", "", "frameTimestamp", "Lkotlin/ExtensionFunctionType;", "pause", ViewProps.START, RRWebVideoEvent.JsonKeys.SEGMENT_ID, "", "replayType", "Lio/sentry/SentryReplayEvent$ReplayType;", "stop", "Companion", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class SessionCaptureStrategy extends BaseCaptureStrategy {
    private static final String TAG = "SessionCaptureStrategy";
    private final ICurrentDateProvider dateProvider;
    private final IHub hub;
    private final SentryOptions options;

    public /* synthetic */ SessionCaptureStrategy(SentryOptions sentryOptions, IHub iHub, ICurrentDateProvider iCurrentDateProvider, ScheduledExecutorService scheduledExecutorService, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(sentryOptions, iHub, iCurrentDateProvider, scheduledExecutorService, (i & 16) != 0 ? null : function1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionCaptureStrategy(SentryOptions options, IHub iHub, ICurrentDateProvider dateProvider, ScheduledExecutorService executor, Function1<? super SentryId, ReplayCache> function1) {
        super(options, iHub, dateProvider, executor, function1);
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(dateProvider, "dateProvider");
        Intrinsics.checkNotNullParameter(executor, "executor");
        this.options = options;
        this.hub = iHub;
        this.dateProvider = dateProvider;
    }

    @Override // io.sentry.android.replay.capture.BaseCaptureStrategy, io.sentry.android.replay.capture.CaptureStrategy
    public void start(ScreenshotRecorderConfig recorderConfig, int segmentId, SentryId replayId, SentryReplayEvent.ReplayType replayType) {
        Intrinsics.checkNotNullParameter(recorderConfig, "recorderConfig");
        Intrinsics.checkNotNullParameter(replayId, "replayId");
        super.start(recorderConfig, segmentId, replayId, replayType);
        IHub iHub = this.hub;
        if (iHub != null) {
            iHub.configureScope(new ScopeCallback() { // from class: io.sentry.android.replay.capture.SessionCaptureStrategy$$ExternalSyntheticLambda0
                @Override // io.sentry.ScopeCallback
                public final void run(IScope iScope) {
                    SessionCaptureStrategy.start$lambda$0(this.f$0, iScope);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void start$lambda$0(SessionCaptureStrategy this$0, IScope it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        it.setReplayId(this$0.getCurrentReplayId());
        String screen = it.getScreen();
        this$0.setScreenAtStart(screen != null ? StringsKt.substringAfterLast$default(screen, ClassUtils.PACKAGE_SEPARATOR_CHAR, (String) null, 2, (Object) null) : null);
    }

    @Override // io.sentry.android.replay.capture.BaseCaptureStrategy, io.sentry.android.replay.capture.CaptureStrategy
    public void pause() {
        createCurrentSegment("pause", new Function1<CaptureStrategy.ReplaySegment, Unit>() { // from class: io.sentry.android.replay.capture.SessionCaptureStrategy.pause.1
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
                    CaptureStrategy.ReplaySegment.Created.capture$default((CaptureStrategy.ReplaySegment.Created) segment, SessionCaptureStrategy.this.hub, null, 2, null);
                    SessionCaptureStrategy sessionCaptureStrategy = SessionCaptureStrategy.this;
                    sessionCaptureStrategy.setCurrentSegment(sessionCaptureStrategy.getCurrentSegment() + 1);
                }
            }
        });
        super.pause();
    }

    @Override // io.sentry.android.replay.capture.BaseCaptureStrategy, io.sentry.android.replay.capture.CaptureStrategy
    public void stop() {
        ReplayCache cache = getCache();
        final File replayCacheDir$sentry_android_replay_release = cache != null ? cache.getReplayCacheDir$sentry_android_replay_release() : null;
        createCurrentSegment("stop", new Function1<CaptureStrategy.ReplaySegment, Unit>() { // from class: io.sentry.android.replay.capture.SessionCaptureStrategy.stop.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    CaptureStrategy.ReplaySegment.Created.capture$default((CaptureStrategy.ReplaySegment.Created) segment, SessionCaptureStrategy.this.hub, null, 2, null);
                }
                FileUtils.deleteRecursively(replayCacheDir$sentry_android_replay_release);
            }
        });
        IHub iHub = this.hub;
        if (iHub != null) {
            iHub.configureScope(new ScopeCallback() { // from class: io.sentry.android.replay.capture.SessionCaptureStrategy$$ExternalSyntheticLambda3
                @Override // io.sentry.ScopeCallback
                public final void run(IScope iScope) {
                    SessionCaptureStrategy.stop$lambda$1(iScope);
                }
            });
        }
        super.stop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void stop$lambda$1(IScope it) {
        Intrinsics.checkNotNullParameter(it, "it");
        it.setReplayId(SentryId.EMPTY_ID);
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public void captureReplay(boolean isTerminating, Function1<? super Date, Unit> onSegmentSent) {
        Intrinsics.checkNotNullParameter(onSegmentSent, "onSegmentSent");
        this.options.getLogger().log(SentryLevel.DEBUG, "Replay is already running in 'session' mode, not capturing for event", new Object[0]);
        getIsTerminating().set(isTerminating);
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public void onScreenshotRecorded(Bitmap bitmap, final Function2<? super ReplayCache, ? super Long, Unit> store) {
        Intrinsics.checkNotNullParameter(store, "store");
        final long currentTimeMillis = this.dateProvider.getCurrentTimeMillis();
        final int recordingHeight = getRecorderConfig().getRecordingHeight();
        final int recordingWidth = getRecorderConfig().getRecordingWidth();
        ExecutorsKt.submitSafely(getReplayExecutor(), this.options, "SessionCaptureStrategy.add_frame", new Runnable() { // from class: io.sentry.android.replay.capture.SessionCaptureStrategy$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                SessionCaptureStrategy.onScreenshotRecorded$lambda$3(this.f$0, store, currentTimeMillis, recordingHeight, recordingWidth);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onScreenshotRecorded$lambda$3(SessionCaptureStrategy this$0, Function2 store, long j, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(store, "$store");
        ReplayCache cache = this$0.getCache();
        if (cache != null) {
            store.invoke(cache, Long.valueOf(j));
        }
        Date segmentTimestamp = this$0.getSegmentTimestamp();
        if (segmentTimestamp == null) {
            this$0.options.getLogger().log(SentryLevel.DEBUG, "Segment timestamp is not set, not recording frame", new Object[0]);
            return;
        }
        if (this$0.getIsTerminating().get()) {
            this$0.options.getLogger().log(SentryLevel.DEBUG, "Not capturing segment, because the app is terminating, will be captured on next launch", new Object[0]);
            return;
        }
        long currentTimeMillis = this$0.dateProvider.getCurrentTimeMillis();
        if (currentTimeMillis - segmentTimestamp.getTime() >= this$0.options.getSessionReplay().getSessionSegmentDuration()) {
            CaptureStrategy.ReplaySegment replaySegmentCreateSegmentInternal$default = BaseCaptureStrategy.createSegmentInternal$default(this$0, this$0.options.getSessionReplay().getSessionSegmentDuration(), segmentTimestamp, this$0.getCurrentReplayId(), this$0.getCurrentSegment(), i, i2, null, null, 0, 0, null, null, null, 8128, null);
            if (replaySegmentCreateSegmentInternal$default instanceof CaptureStrategy.ReplaySegment.Created) {
                CaptureStrategy.ReplaySegment.Created created = (CaptureStrategy.ReplaySegment.Created) replaySegmentCreateSegmentInternal$default;
                CaptureStrategy.ReplaySegment.Created.capture$default(created, this$0.hub, null, 2, null);
                this$0.setCurrentSegment(this$0.getCurrentSegment() + 1);
                this$0.setSegmentTimestamp(created.getReplay().getTimestamp());
            }
        }
        if (currentTimeMillis - this$0.getReplayStartTimestamp().get() >= this$0.options.getSessionReplay().getSessionDuration()) {
            this$0.options.getReplayController().stop();
            this$0.options.getLogger().log(SentryLevel.INFO, "Session replay deadline exceeded (1h), stopping recording", new Object[0]);
        }
    }

    @Override // io.sentry.android.replay.capture.BaseCaptureStrategy, io.sentry.android.replay.capture.CaptureStrategy
    public void onConfigurationChanged(ScreenshotRecorderConfig recorderConfig) {
        Intrinsics.checkNotNullParameter(recorderConfig, "recorderConfig");
        createCurrentSegment("onConfigurationChanged", new Function1<CaptureStrategy.ReplaySegment, Unit>() { // from class: io.sentry.android.replay.capture.SessionCaptureStrategy.onConfigurationChanged.1
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
                    CaptureStrategy.ReplaySegment.Created created = (CaptureStrategy.ReplaySegment.Created) segment;
                    CaptureStrategy.ReplaySegment.Created.capture$default(created, SessionCaptureStrategy.this.hub, null, 2, null);
                    SessionCaptureStrategy sessionCaptureStrategy = SessionCaptureStrategy.this;
                    sessionCaptureStrategy.setCurrentSegment(sessionCaptureStrategy.getCurrentSegment() + 1);
                    SessionCaptureStrategy.this.setSegmentTimestamp(created.getReplay().getTimestamp());
                }
            }
        });
        super.onConfigurationChanged(recorderConfig);
    }

    @Override // io.sentry.android.replay.capture.CaptureStrategy
    public CaptureStrategy convert() {
        return this;
    }

    private final void createCurrentSegment(String taskName, final Function1<? super CaptureStrategy.ReplaySegment, Unit> onSegmentCreated) {
        long currentTimeMillis = this.dateProvider.getCurrentTimeMillis();
        final Date segmentTimestamp = getSegmentTimestamp();
        if (segmentTimestamp == null) {
            return;
        }
        final int currentSegment = getCurrentSegment();
        final long time = currentTimeMillis - segmentTimestamp.getTime();
        final SentryId currentReplayId = getCurrentReplayId();
        final int recordingHeight = getRecorderConfig().getRecordingHeight();
        final int recordingWidth = getRecorderConfig().getRecordingWidth();
        ExecutorsKt.submitSafely(getReplayExecutor(), this.options, "SessionCaptureStrategy." + taskName, new Runnable() { // from class: io.sentry.android.replay.capture.SessionCaptureStrategy$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SessionCaptureStrategy.createCurrentSegment$lambda$4(this.f$0, time, segmentTimestamp, currentReplayId, currentSegment, recordingHeight, recordingWidth, onSegmentCreated);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createCurrentSegment$lambda$4(SessionCaptureStrategy this$0, long j, Date currentSegmentTimestamp, SentryId replayId, int i, int i2, int i3, Function1 onSegmentCreated) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(currentSegmentTimestamp, "$currentSegmentTimestamp");
        Intrinsics.checkNotNullParameter(replayId, "$replayId");
        Intrinsics.checkNotNullParameter(onSegmentCreated, "$onSegmentCreated");
        onSegmentCreated.invoke(BaseCaptureStrategy.createSegmentInternal$default(this$0, j, currentSegmentTimestamp, replayId, i, i2, i3, null, null, 0, 0, null, null, null, 8128, null));
    }
}
