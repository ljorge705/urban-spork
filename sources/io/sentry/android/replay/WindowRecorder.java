package io.sentry.android.replay;

import android.view.View;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.SentryOptions;
import io.sentry.android.replay.WindowRecorder;
import io.sentry.android.replay.util.ExecutorsKt;
import io.sentry.android.replay.util.MainLooperHandler;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WindowRecorder.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 +2\u00020\u00012\u00020\u0002:\u0002+,B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u001f\u001a\u00020 H\u0016J\u0018\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020 H\u0016J\b\u0010&\u001a\u00020 H\u0016J\u0010\u0010'\u001a\u00020 2\u0006\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020 H\u0016R#\u0010\f\u001a\n \r*\u0004\u0018\u00010\n0\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0012\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0018\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\u0019j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a`\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lio/sentry/android/replay/WindowRecorder;", "Lio/sentry/android/replay/Recorder;", "Lio/sentry/android/replay/OnRootViewsChangedListener;", RRWebOptionsEvent.EVENT_TAG, "Lio/sentry/SentryOptions;", "screenshotRecorderCallback", "Lio/sentry/android/replay/ScreenshotRecorderCallback;", "mainLooperHandler", "Lio/sentry/android/replay/util/MainLooperHandler;", "replayExecutor", "Ljava/util/concurrent/ScheduledExecutorService;", "(Lio/sentry/SentryOptions;Lio/sentry/android/replay/ScreenshotRecorderCallback;Lio/sentry/android/replay/util/MainLooperHandler;Ljava/util/concurrent/ScheduledExecutorService;)V", "capturer", "kotlin.jvm.PlatformType", "getCapturer", "()Ljava/util/concurrent/ScheduledExecutorService;", "capturer$delegate", "Lkotlin/Lazy;", "capturingTask", "Ljava/util/concurrent/ScheduledFuture;", "isRecording", "Ljava/util/concurrent/atomic/AtomicBoolean;", "recorder", "Lio/sentry/android/replay/ScreenshotRecorder;", "rootViews", "Ljava/util/ArrayList;", "Ljava/lang/ref/WeakReference;", "Landroid/view/View;", "Lkotlin/collections/ArrayList;", "rootViewsLock", "", Constants.KEY_HIDE_CLOSE, "", "onRootViewsChanged", "root", "added", "", "pause", "resume", ViewProps.START, "recorderConfig", "Lio/sentry/android/replay/ScreenshotRecorderConfig;", "stop", "Companion", "RecorderExecutorServiceThreadFactory", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WindowRecorder implements Recorder, OnRootViewsChangedListener {
    private static final String TAG = "WindowRecorder";

    /* renamed from: capturer$delegate, reason: from kotlin metadata */
    private final Lazy capturer;
    private ScheduledFuture<?> capturingTask;
    private final AtomicBoolean isRecording;
    private final MainLooperHandler mainLooperHandler;
    private final SentryOptions options;
    private ScreenshotRecorder recorder;
    private final ScheduledExecutorService replayExecutor;
    private final ArrayList<WeakReference<View>> rootViews;
    private final Object rootViewsLock;
    private final ScreenshotRecorderCallback screenshotRecorderCallback;

    public WindowRecorder(SentryOptions options, ScreenshotRecorderCallback screenshotRecorderCallback, MainLooperHandler mainLooperHandler, ScheduledExecutorService replayExecutor) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(mainLooperHandler, "mainLooperHandler");
        Intrinsics.checkNotNullParameter(replayExecutor, "replayExecutor");
        this.options = options;
        this.screenshotRecorderCallback = screenshotRecorderCallback;
        this.mainLooperHandler = mainLooperHandler;
        this.replayExecutor = replayExecutor;
        this.isRecording = new AtomicBoolean(false);
        this.rootViews = new ArrayList<>();
        this.rootViewsLock = new Object();
        this.capturer = LazyKt.lazy(new Function0<ScheduledExecutorService>() { // from class: io.sentry.android.replay.WindowRecorder$capturer$2
            @Override // kotlin.jvm.functions.Function0
            public final ScheduledExecutorService invoke() {
                return Executors.newSingleThreadScheduledExecutor(new WindowRecorder.RecorderExecutorServiceThreadFactory());
            }
        });
    }

    public /* synthetic */ WindowRecorder(SentryOptions sentryOptions, ScreenshotRecorderCallback screenshotRecorderCallback, MainLooperHandler mainLooperHandler, ScheduledExecutorService scheduledExecutorService, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(sentryOptions, (i & 2) != 0 ? null : screenshotRecorderCallback, mainLooperHandler, scheduledExecutorService);
    }

    private final ScheduledExecutorService getCapturer() {
        return (ScheduledExecutorService) this.capturer.getValue();
    }

    @Override // io.sentry.android.replay.OnRootViewsChangedListener
    public void onRootViewsChanged(final View root, boolean added) {
        Intrinsics.checkNotNullParameter(root, "root");
        synchronized (this.rootViewsLock) {
            if (added) {
                this.rootViews.add(new WeakReference<>(root));
                ScreenshotRecorder screenshotRecorder = this.recorder;
                if (screenshotRecorder != null) {
                    screenshotRecorder.bind(root);
                    Unit unit = Unit.INSTANCE;
                }
            } else {
                ScreenshotRecorder screenshotRecorder2 = this.recorder;
                if (screenshotRecorder2 != null) {
                    screenshotRecorder2.unbind(root);
                }
                CollectionsKt.removeAll((List) this.rootViews, (Function1) new Function1<WeakReference<View>, Boolean>() { // from class: io.sentry.android.replay.WindowRecorder$onRootViewsChanged$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(WeakReference<View> it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return Boolean.valueOf(Intrinsics.areEqual(it.get(), root));
                    }
                });
                WeakReference weakReference = (WeakReference) CollectionsKt.lastOrNull((List) this.rootViews);
                View view = weakReference != null ? (View) weakReference.get() : null;
                if (view == null || Intrinsics.areEqual(root, view)) {
                    Unit unit2 = Unit.INSTANCE;
                } else {
                    ScreenshotRecorder screenshotRecorder3 = this.recorder;
                    if (screenshotRecorder3 != null) {
                        screenshotRecorder3.bind(view);
                        Unit unit3 = Unit.INSTANCE;
                    }
                }
            }
        }
    }

    @Override // io.sentry.android.replay.Recorder
    public void start(ScreenshotRecorderConfig recorderConfig) {
        Intrinsics.checkNotNullParameter(recorderConfig, "recorderConfig");
        if (this.isRecording.getAndSet(true)) {
            return;
        }
        this.recorder = new ScreenshotRecorder(recorderConfig, this.options, this.mainLooperHandler, this.replayExecutor, this.screenshotRecorderCallback);
        ScheduledExecutorService capturer = getCapturer();
        Intrinsics.checkNotNullExpressionValue(capturer, "capturer");
        this.capturingTask = ExecutorsKt.scheduleAtFixedRateSafely(capturer, this.options, "WindowRecorder.capture", 100L, 1000 / recorderConfig.getFrameRate(), TimeUnit.MILLISECONDS, new Runnable() { // from class: io.sentry.android.replay.WindowRecorder$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                WindowRecorder.start$lambda$1(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void start$lambda$1(WindowRecorder this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ScreenshotRecorder screenshotRecorder = this$0.recorder;
        if (screenshotRecorder != null) {
            screenshotRecorder.capture();
        }
    }

    @Override // io.sentry.android.replay.Recorder
    public void resume() {
        ScreenshotRecorder screenshotRecorder = this.recorder;
        if (screenshotRecorder != null) {
            screenshotRecorder.resume();
        }
    }

    @Override // io.sentry.android.replay.Recorder
    public void pause() {
        ScreenshotRecorder screenshotRecorder = this.recorder;
        if (screenshotRecorder != null) {
            screenshotRecorder.pause();
        }
    }

    @Override // io.sentry.android.replay.Recorder
    public void stop() {
        synchronized (this.rootViewsLock) {
            Iterator<T> it = this.rootViews.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                ScreenshotRecorder screenshotRecorder = this.recorder;
                if (screenshotRecorder != null) {
                    screenshotRecorder.unbind((View) weakReference.get());
                }
            }
            this.rootViews.clear();
            Unit unit = Unit.INSTANCE;
        }
        ScreenshotRecorder screenshotRecorder2 = this.recorder;
        if (screenshotRecorder2 != null) {
            screenshotRecorder2.close();
        }
        this.recorder = null;
        ScheduledFuture<?> scheduledFuture = this.capturingTask;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.capturingTask = null;
        this.isRecording.set(false);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        stop();
        ScheduledExecutorService capturer = getCapturer();
        Intrinsics.checkNotNullExpressionValue(capturer, "capturer");
        ExecutorsKt.gracefullyShutdown(capturer, this.options);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: WindowRecorder.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lio/sentry/android/replay/WindowRecorder$RecorderExecutorServiceThreadFactory;", "Ljava/util/concurrent/ThreadFactory;", "()V", "cnt", "", "newThread", "Ljava/lang/Thread;", "r", "Ljava/lang/Runnable;", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    static final class RecorderExecutorServiceThreadFactory implements ThreadFactory {
        private int cnt;

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r) {
            Intrinsics.checkNotNullParameter(r, "r");
            StringBuilder sb = new StringBuilder("SentryWindowRecorder-");
            int i = this.cnt;
            this.cnt = i + 1;
            Thread thread = new Thread(r, sb.append(i).toString());
            thread.setDaemon(true);
            return thread;
        }
    }
}
