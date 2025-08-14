package io.sentry.android.replay;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import androidx.core.view.ViewCompat;
import com.clevertap.android.sdk.Constants;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.android.replay.util.ExecutorsKt;
import io.sentry.android.replay.util.MainLooperHandler;
import io.sentry.android.replay.util.TextLayout;
import io.sentry.android.replay.util.ViewsKt;
import io.sentry.android.replay.viewhierarchy.ViewHierarchyNode;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreenshotRecorder.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001:\u0001;B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\u000e\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\"J\u0006\u00101\u001a\u00020/J\u0006\u00102\u001a\u00020/J\b\u00103\u001a\u00020/H\u0016J\u0006\u00104\u001a\u00020/J\u0006\u00105\u001a\u00020/J\u0010\u00106\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u00010\"J\u0014\u00107\u001a\u000208*\u00020$2\u0006\u00109\u001a\u00020:H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u0018\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010 \u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010%\u001a\u00020$8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b(\u0010\u0018\u001a\u0004\b&\u0010'R\u001b\u0010)\u001a\u00020*8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b-\u0010\u0018\u001a\u0004\b+\u0010,¨\u0006<"}, d2 = {"Lio/sentry/android/replay/ScreenshotRecorder;", "Landroid/view/ViewTreeObserver$OnDrawListener;", "config", "Lio/sentry/android/replay/ScreenshotRecorderConfig;", RRWebOptionsEvent.EVENT_TAG, "Lio/sentry/SentryOptions;", "mainLooperHandler", "Lio/sentry/android/replay/util/MainLooperHandler;", "recorder", "Ljava/util/concurrent/ScheduledExecutorService;", "screenshotRecorderCallback", "Lio/sentry/android/replay/ScreenshotRecorderCallback;", "(Lio/sentry/android/replay/ScreenshotRecorderConfig;Lio/sentry/SentryOptions;Lio/sentry/android/replay/util/MainLooperHandler;Ljava/util/concurrent/ScheduledExecutorService;Lio/sentry/android/replay/ScreenshotRecorderCallback;)V", "getConfig", "()Lio/sentry/android/replay/ScreenshotRecorderConfig;", "contentChanged", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isCapturing", "lastCaptureSuccessful", "maskingPaint", "Landroid/graphics/Paint;", "getMaskingPaint", "()Landroid/graphics/Paint;", "maskingPaint$delegate", "Lkotlin/Lazy;", "getOptions", "()Lio/sentry/SentryOptions;", "prescaledMatrix", "Landroid/graphics/Matrix;", "getPrescaledMatrix", "()Landroid/graphics/Matrix;", "prescaledMatrix$delegate", "rootView", "Ljava/lang/ref/WeakReference;", "Landroid/view/View;", "screenshot", "Landroid/graphics/Bitmap;", "singlePixelBitmap", "getSinglePixelBitmap", "()Landroid/graphics/Bitmap;", "singlePixelBitmap$delegate", "singlePixelBitmapCanvas", "Landroid/graphics/Canvas;", "getSinglePixelBitmapCanvas", "()Landroid/graphics/Canvas;", "singlePixelBitmapCanvas$delegate", "bind", "", "root", "capture", Constants.KEY_HIDE_CLOSE, "onDraw", "pause", "resume", "unbind", "dominantColorForRect", "", "rect", "Landroid/graphics/Rect;", "RecorderExecutorServiceThreadFactory", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ScreenshotRecorder implements ViewTreeObserver.OnDrawListener {
    private final ScreenshotRecorderConfig config;
    private final AtomicBoolean contentChanged;
    private final AtomicBoolean isCapturing;
    private final AtomicBoolean lastCaptureSuccessful;
    private final MainLooperHandler mainLooperHandler;

    /* renamed from: maskingPaint$delegate, reason: from kotlin metadata */
    private final Lazy maskingPaint;
    private final SentryOptions options;

    /* renamed from: prescaledMatrix$delegate, reason: from kotlin metadata */
    private final Lazy prescaledMatrix;
    private final ScheduledExecutorService recorder;
    private WeakReference<View> rootView;
    private final Bitmap screenshot;
    private final ScreenshotRecorderCallback screenshotRecorderCallback;

    /* renamed from: singlePixelBitmap$delegate, reason: from kotlin metadata */
    private final Lazy singlePixelBitmap;

    /* renamed from: singlePixelBitmapCanvas$delegate, reason: from kotlin metadata */
    private final Lazy singlePixelBitmapCanvas;

    public final ScreenshotRecorderConfig getConfig() {
        return this.config;
    }

    public final SentryOptions getOptions() {
        return this.options;
    }

    public ScreenshotRecorder(ScreenshotRecorderConfig config, SentryOptions options, MainLooperHandler mainLooperHandler, ScheduledExecutorService recorder, ScreenshotRecorderCallback screenshotRecorderCallback) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(mainLooperHandler, "mainLooperHandler");
        Intrinsics.checkNotNullParameter(recorder, "recorder");
        this.config = config;
        this.options = options;
        this.mainLooperHandler = mainLooperHandler;
        this.recorder = recorder;
        this.screenshotRecorderCallback = screenshotRecorderCallback;
        this.maskingPaint = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<Paint>() { // from class: io.sentry.android.replay.ScreenshotRecorder$maskingPaint$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Paint invoke() {
                return new Paint();
            }
        });
        this.singlePixelBitmap = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<Bitmap>() { // from class: io.sentry.android.replay.ScreenshotRecorder$singlePixelBitmap$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Bitmap invoke() {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
                Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(\n          ….Config.RGB_565\n        )");
                return bitmapCreateBitmap;
            }
        });
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(config.getRecordingWidth(), config.getRecordingHeight(), Bitmap.Config.RGB_565);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(\n        co…tmap.Config.RGB_565\n    )");
        this.screenshot = bitmapCreateBitmap;
        this.singlePixelBitmapCanvas = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<Canvas>() { // from class: io.sentry.android.replay.ScreenshotRecorder$singlePixelBitmapCanvas$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Canvas invoke() {
                return new Canvas(this.this$0.getSinglePixelBitmap());
            }
        });
        this.prescaledMatrix = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<Matrix>() { // from class: io.sentry.android.replay.ScreenshotRecorder$prescaledMatrix$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Matrix invoke() {
                Matrix matrix = new Matrix();
                ScreenshotRecorder screenshotRecorder = this.this$0;
                matrix.preScale(screenshotRecorder.getConfig().getScaleFactorX(), screenshotRecorder.getConfig().getScaleFactorY());
                return matrix;
            }
        });
        this.contentChanged = new AtomicBoolean(false);
        this.isCapturing = new AtomicBoolean(true);
        this.lastCaptureSuccessful = new AtomicBoolean(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Paint getMaskingPaint() {
        return (Paint) this.maskingPaint.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap getSinglePixelBitmap() {
        return (Bitmap) this.singlePixelBitmap.getValue();
    }

    private final Canvas getSinglePixelBitmapCanvas() {
        return (Canvas) this.singlePixelBitmapCanvas.getValue();
    }

    private final Matrix getPrescaledMatrix() {
        return (Matrix) this.prescaledMatrix.getValue();
    }

    public final void capture() {
        if (!this.isCapturing.get()) {
            this.options.getLogger().log(SentryLevel.DEBUG, "ScreenshotRecorder is paused, not capturing screenshot", new Object[0]);
            return;
        }
        if (!this.contentChanged.get() && this.lastCaptureSuccessful.get()) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Content hasn't changed, repeating last known frame", new Object[0]);
            ScreenshotRecorderCallback screenshotRecorderCallback = this.screenshotRecorderCallback;
            if (screenshotRecorderCallback != null) {
                screenshotRecorderCallback.onScreenshotRecorded(this.screenshot);
                return;
            }
            return;
        }
        WeakReference<View> weakReference = this.rootView;
        final View view = weakReference != null ? weakReference.get() : null;
        if (view == null || view.getWidth() <= 0 || view.getHeight() <= 0 || !view.isShown()) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Root view is invalid, not capturing screenshot", new Object[0]);
            return;
        }
        final Window phoneWindow = WindowsKt.getPhoneWindow(view);
        if (phoneWindow == null) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Window is invalid, not capturing screenshot", new Object[0]);
        } else {
            this.mainLooperHandler.post(new Runnable() { // from class: io.sentry.android.replay.ScreenshotRecorder$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ScreenshotRecorder.capture$lambda$2(this.f$0, phoneWindow, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void capture$lambda$2(final ScreenshotRecorder this$0, Window window, final View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            this$0.contentChanged.set(false);
            PixelCopy.request(window, this$0.screenshot, new PixelCopy.OnPixelCopyFinishedListener() { // from class: io.sentry.android.replay.ScreenshotRecorder$$ExternalSyntheticLambda2
                @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
                public final void onPixelCopyFinished(int i) {
                    ScreenshotRecorder.capture$lambda$2$lambda$1(this.f$0, view, i);
                }
            }, this$0.mainLooperHandler.getHandler());
        } catch (Throwable th) {
            this$0.options.getLogger().log(SentryLevel.WARNING, "Failed to capture replay recording", th);
            this$0.lastCaptureSuccessful.set(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void capture$lambda$2$lambda$1(final ScreenshotRecorder this$0, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i != 0) {
            this$0.options.getLogger().log(SentryLevel.INFO, "Failed to capture replay recording: %d", Integer.valueOf(i));
            this$0.lastCaptureSuccessful.set(false);
        } else if (this$0.contentChanged.get()) {
            this$0.options.getLogger().log(SentryLevel.INFO, "Failed to determine view hierarchy, not capturing", new Object[0]);
            this$0.lastCaptureSuccessful.set(false);
        } else {
            final ViewHierarchyNode viewHierarchyNodeFromView = ViewHierarchyNode.INSTANCE.fromView(view, null, 0, this$0.options);
            ViewsKt.traverse(view, viewHierarchyNodeFromView, this$0.options);
            ExecutorsKt.submitSafely(this$0.recorder, this$0.options, "screenshot_recorder.mask", new Runnable() { // from class: io.sentry.android.replay.ScreenshotRecorder$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ScreenshotRecorder.capture$lambda$2$lambda$1$lambda$0(this.f$0, viewHierarchyNodeFromView);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void capture$lambda$2$lambda$1$lambda$0(final ScreenshotRecorder this$0, ViewHierarchyNode viewHierarchy) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(viewHierarchy, "$viewHierarchy");
        final Canvas canvas = new Canvas(this$0.screenshot);
        canvas.setMatrix(this$0.getPrescaledMatrix());
        viewHierarchy.traverse(new Function1<ViewHierarchyNode, Boolean>() { // from class: io.sentry.android.replay.ScreenshotRecorder$capture$1$1$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(ViewHierarchyNode node) {
                Pair pair;
                Integer dominantColor;
                Intrinsics.checkNotNullParameter(node, "node");
                if (node.getShouldMask() && node.getWidth() > 0 && node.getHeight() > 0) {
                    if (node.getVisibleRect() == null) {
                        return false;
                    }
                    if (node instanceof ViewHierarchyNode.ImageViewHierarchyNode) {
                        List listListOf = CollectionsKt.listOf(node.getVisibleRect());
                        ScreenshotRecorder screenshotRecorder = this.this$0;
                        pair = TuplesKt.to(listListOf, Integer.valueOf(screenshotRecorder.dominantColorForRect(screenshotRecorder.screenshot, node.getVisibleRect())));
                    } else {
                        boolean z = node instanceof ViewHierarchyNode.TextViewHierarchyNode;
                        int iIntValue = ViewCompat.MEASURED_STATE_MASK;
                        if (z) {
                            ViewHierarchyNode.TextViewHierarchyNode textViewHierarchyNode = (ViewHierarchyNode.TextViewHierarchyNode) node;
                            TextLayout layout = textViewHierarchyNode.getLayout();
                            if ((layout != null && (dominantColor = layout.getDominantTextColor()) != null) || (dominantColor = textViewHierarchyNode.getDominantColor()) != null) {
                                iIntValue = dominantColor.intValue();
                            }
                            pair = TuplesKt.to(ViewsKt.getVisibleRects(textViewHierarchyNode.getLayout(), node.getVisibleRect(), textViewHierarchyNode.getPaddingLeft(), textViewHierarchyNode.getPaddingTop()), Integer.valueOf(iIntValue));
                        } else {
                            pair = TuplesKt.to(CollectionsKt.listOf(node.getVisibleRect()), Integer.valueOf(ViewCompat.MEASURED_STATE_MASK));
                        }
                    }
                    List list = (List) pair.component1();
                    this.this$0.getMaskingPaint().setColor(((Number) pair.component2()).intValue());
                    Canvas canvas2 = canvas;
                    ScreenshotRecorder screenshotRecorder2 = this.this$0;
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        canvas2.drawRoundRect(new RectF((Rect) it.next()), 10.0f, 10.0f, screenshotRecorder2.getMaskingPaint());
                    }
                }
                return true;
            }
        });
        ScreenshotRecorderCallback screenshotRecorderCallback = this$0.screenshotRecorderCallback;
        if (screenshotRecorderCallback != null) {
            screenshotRecorderCallback.onScreenshotRecorded(this$0.screenshot);
        }
        this$0.lastCaptureSuccessful.set(true);
        this$0.contentChanged.set(false);
    }

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public void onDraw() {
        WeakReference<View> weakReference = this.rootView;
        View view = weakReference != null ? weakReference.get() : null;
        if (view == null || view.getWidth() <= 0 || view.getHeight() <= 0 || !view.isShown()) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Root view is invalid, not capturing screenshot", new Object[0]);
        } else {
            this.contentChanged.set(true);
        }
    }

    public final void bind(View root) {
        Intrinsics.checkNotNullParameter(root, "root");
        WeakReference<View> weakReference = this.rootView;
        unbind(weakReference != null ? weakReference.get() : null);
        WeakReference<View> weakReference2 = this.rootView;
        if (weakReference2 != null) {
            weakReference2.clear();
        }
        this.rootView = new WeakReference<>(root);
        ViewsKt.addOnDrawListenerSafe(root, this);
        this.contentChanged.set(true);
    }

    public final void unbind(View root) {
        if (root != null) {
            ViewsKt.removeOnDrawListenerSafe(root, this);
        }
    }

    public final void pause() {
        this.isCapturing.set(false);
        WeakReference<View> weakReference = this.rootView;
        unbind(weakReference != null ? weakReference.get() : null);
    }

    public final void resume() {
        View view;
        WeakReference<View> weakReference = this.rootView;
        if (weakReference != null && (view = weakReference.get()) != null) {
            ViewsKt.addOnDrawListenerSafe(view, this);
        }
        this.isCapturing.set(true);
    }

    public final void close() {
        WeakReference<View> weakReference = this.rootView;
        unbind(weakReference != null ? weakReference.get() : null);
        WeakReference<View> weakReference2 = this.rootView;
        if (weakReference2 != null) {
            weakReference2.clear();
        }
        this.screenshot.recycle();
        this.isCapturing.set(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dominantColorForRect(Bitmap bitmap, Rect rect) {
        Rect rect2 = new Rect(rect);
        RectF rectF = new RectF(rect2);
        getPrescaledMatrix().mapRect(rectF);
        rectF.round(rect2);
        getSinglePixelBitmapCanvas().drawBitmap(bitmap, rect2, new Rect(0, 0, 1, 1), (Paint) null);
        return getSinglePixelBitmap().getPixel(0, 0);
    }

    /* compiled from: ScreenshotRecorder.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lio/sentry/android/replay/ScreenshotRecorder$RecorderExecutorServiceThreadFactory;", "Ljava/util/concurrent/ThreadFactory;", "()V", "cnt", "", "newThread", "Ljava/lang/Thread;", "r", "Ljava/lang/Runnable;", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class RecorderExecutorServiceThreadFactory implements ThreadFactory {
        private int cnt;

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r) {
            Intrinsics.checkNotNullParameter(r, "r");
            StringBuilder sb = new StringBuilder("SentryReplayRecorder-");
            int i = this.cnt;
            this.cnt = i + 1;
            Thread thread = new Thread(r, sb.append(i).toString());
            thread.setDaemon(true);
            return thread;
        }
    }
}
