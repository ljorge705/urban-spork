package com.uxcam.screenshot.helper;

import android.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.core.view.ViewCompat;
import com.uxcam.screenaction.models.OccludeComposable;
import com.uxcam.screenaction.models.ViewRootData;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.BitmapCreator;
import com.uxcam.screenshot.flutterviewfinder.FlutterConfig;
import com.uxcam.screenshot.flutterviewfinder.FlutterViewFinder;
import com.uxcam.screenshot.fullscreenocclusion.FullScreenOcclusionCallback;
import com.uxcam.screenshot.fullscreenocclusion.FullScreenOcclusionConfig;
import com.uxcam.screenshot.fullscreenocclusion.FullScreenOcclusionDrawer;
import com.uxcam.screenshot.keyboardoverlay.KeyboardOverlayDrawer;
import com.uxcam.screenshot.model.UXCamOcclusion;
import com.uxcam.screenshot.model.UXCamOverlay;
import com.uxcam.screenshot.repository.ComposeOcclusionRepository;
import com.uxcam.screenshot.repository.OcclusionRepository;
import com.uxcam.screenshot.screenshotTaker.ScreenshotTaker;
import com.uxcam.screenshot.screenshotTaker.ScreenshotTakerConfig;
import com.uxcam.screenshot.state.ScreenshotStateHolder;
import com.uxcam.screenshot.utils.DeviceInfo;
import com.uxcam.screenshot.utils.ScreenShotBitmapUtil;
import com.uxcam.screenshot.utils.UxOrientationKt;
import com.uxcam.screenshot.viewocclusion.KeyboardVisibilityCheckResult;
import com.uxcam.screenshot.viewocclusion.SensitiveComposableOcclusion;
import com.uxcam.screenshot.viewocclusion.SensitiveViewsFinder;
import com.uxcam.screenshot.viewocclusion.SensitiveViewsFinderResult;
import com.uxcam.screenshot.viewocclusion.SensitiveViewsOcclusion;
import com.uxcam.screenshot.viewocclusion.WebViewOcclusion;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/uxcam/screenshot/helper/ScreenShotHelperImpl;", "Lcom/uxcam/screenshot/helper/ScreenshotHelper;", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class ScreenShotHelperImpl implements ScreenshotHelper {

    /* renamed from: a, reason: collision with root package name */
    public final ScreenshotStateHolder f266a;
    public final ScreenshotTaker b;
    public final SensitiveViewsFinder c;
    public final KeyboardOverlayDrawer d;
    public final FlutterViewFinder e;
    public final FullScreenOcclusionDrawer f;
    public final SensitiveViewsOcclusion g;
    public final WebViewOcclusion h;
    public final SensitiveComposableOcclusion i;
    public final ScreenShotBitmapUtil j;
    public final ComposeOcclusionRepository k;
    public final OcclusionRepository l;
    public final BitmapCreator m;

    /* renamed from: n, reason: collision with root package name */
    public final boolean f267n;
    public final BitmapSource o;

    public ScreenShotHelperImpl(ScreenshotStateHolder screenshotStateHolder, ScreenshotTaker screenshotTaker, SensitiveViewsFinder sensitiveViewsFinder, KeyboardOverlayDrawer keyboardOverlayDrawer, FlutterViewFinder flutterViewFinder, FullScreenOcclusionDrawer fullScreenOcclusionDrawer, SensitiveViewsOcclusion sensitiveViewsOcclusion, WebViewOcclusion webViewOcclusion, SensitiveComposableOcclusion sensitiveComposableOcclusion, ScreenShotBitmapUtil screenShotBitmapUtil, ComposeOcclusionRepository composeOcclusionRepository, OcclusionRepository occlusionRepository, BitmapCreator bitmapCreator, boolean z, BitmapSource bitmapSource) {
        Intrinsics.checkNotNullParameter(screenshotStateHolder, "screenshotStateHolder");
        Intrinsics.checkNotNullParameter(screenshotTaker, "screenshotTaker");
        Intrinsics.checkNotNullParameter(sensitiveViewsFinder, "sensitiveViewsFinder");
        Intrinsics.checkNotNullParameter(keyboardOverlayDrawer, "keyboardOverlayDrawer");
        Intrinsics.checkNotNullParameter(flutterViewFinder, "flutterViewFinder");
        Intrinsics.checkNotNullParameter(fullScreenOcclusionDrawer, "fullScreenOcclusionDrawer");
        Intrinsics.checkNotNullParameter(sensitiveViewsOcclusion, "sensitiveViewsOcclusion");
        Intrinsics.checkNotNullParameter(webViewOcclusion, "webViewOcclusion");
        Intrinsics.checkNotNullParameter(sensitiveComposableOcclusion, "sensitiveComposableOcclusion");
        Intrinsics.checkNotNullParameter(screenShotBitmapUtil, "screenShotBitmapUtil");
        Intrinsics.checkNotNullParameter(composeOcclusionRepository, "composeOcclusionRepository");
        Intrinsics.checkNotNullParameter(occlusionRepository, "occlusionRepository");
        Intrinsics.checkNotNullParameter(bitmapCreator, "bitmapCreator");
        Intrinsics.checkNotNullParameter(bitmapSource, "bitmapSource");
        this.f266a = screenshotStateHolder;
        this.b = screenshotTaker;
        this.c = sensitiveViewsFinder;
        this.d = keyboardOverlayDrawer;
        this.e = flutterViewFinder;
        this.f = fullScreenOcclusionDrawer;
        this.g = sensitiveViewsOcclusion;
        this.h = webViewOcclusion;
        this.i = sensitiveComposableOcclusion;
        this.j = screenShotBitmapUtil;
        this.k = composeOcclusionRepository;
        this.l = occlusionRepository;
        this.m = bitmapCreator;
        this.f267n = z;
        this.o = bitmapSource;
    }

    public final void a(final OnScreenshotTakenCallback onScreenshotTakenCallback, final String str, Boolean bool, final List<ViewRootData> list, final Activity activity) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        Bitmap bitmapA = this.m.a(activity);
        try {
            final boolean zA = UxOrientationKt.a(activity);
            b(activity);
            FlutterConfig flutterConfigA = a(activity);
            final ScreenshotScalingFactor screenshotScalingFactor = new ScreenshotScalingFactor(DeviceInfo.getDeviceResolution(activity).y, bitmapA.getWidth() / r2.x);
            this.b.a(new ScreenshotTakerConfig(activity, bitmapA, this.f266a.getGoogleMapView(), this.f266a.getI(), flutterConfigA, this.f266a.isImprovedScreenCaptureInUse(), this.f266a.isPixelCopySupported(), bool != null ? bool.booleanValue() : true, screenshotScalingFactor, arrayList), new OnScreenshotTakenCallback() { // from class: com.uxcam.screenshot.helper.ScreenShotHelperImpl$$ExternalSyntheticLambda2
                @Override // com.uxcam.screenshot.helper.OnScreenshotTakenCallback
                public final void onScreenshotTaken(Bitmap bitmap) throws InterruptedException {
                    ScreenShotHelperImpl.a(this.f$0, activity, onScreenshotTakenCallback, zA, list, str, screenshotScalingFactor, bitmap);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            new Paint().setColor(-16776961);
        }
    }

    public final void b(Activity activity) {
        View decorView = activity.findViewById(R.id.content).getRootView();
        SensitiveViewsFinder sensitiveViewsFinder = this.c;
        Intrinsics.checkNotNullExpressionValue(decorView, "decorView");
        KeyboardVisibilityCheckResult keyboardVisibilityCheckResultA = sensitiveViewsFinder.a(decorView, this.f266a.getJ());
        this.f266a.setLastVisibleDecorViewHeight(keyboardVisibilityCheckResultA.b);
        if (keyboardVisibilityCheckResultA.f287a == -1 || this.f266a.getD() != activity.getResources().getConfiguration().orientation) {
            return;
        }
        this.f266a.setKeyboardHeight(keyboardVisibilityCheckResultA.f287a);
    }

    @Override // com.uxcam.screenshot.helper.ScreenshotHelper
    public final void takeScreenshotAndEncode(String str, Boolean bool, Integer num, List<ViewRootData> list, Activity activity, OnScreenshotTakenCallback onScreenshotTakenCallback) {
        try {
            if (activity != null && list != null) {
                a(onScreenshotTakenCallback, str, bool, CollectionsKt.filterNotNull(list), activity);
            } else if (onScreenshotTakenCallback == null) {
            } else {
                onScreenshotTakenCallback.onScreenshotTaken(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final void a(ScreenShotHelperImpl this$0, Activity activity, OnScreenshotTakenCallback onScreenshotTakenCallback, boolean z, List viewRootDataList, String str, ScreenshotScalingFactor scalingFactor, Bitmap bitmap) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(viewRootDataList, "$viewRootDataList");
        Intrinsics.checkNotNullParameter(scalingFactor, "$scalingFactor");
        if (bitmap == null) {
            return;
        }
        this$0.a(bitmap, activity, onScreenshotTakenCallback, z, viewRootDataList, str, scalingFactor);
    }

    public static final void a(ScreenShotHelperImpl this$0, Bitmap bitmap, Activity activity, OnScreenshotTakenCallback onScreenshotTakenCallback, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        this$0.getClass();
        Intrinsics.checkNotNullParameter(activity, "<this>");
        if (!UxOrientationKt.a(activity)) {
            this$0.o.add(bitmap);
        } else {
            Matrix matrix = new Matrix();
            matrix.postRotate(90.0f);
            Intrinsics.checkNotNullExpressionValue(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true), "createBitmap(\n          …t, matrix, true\n        )");
            Matrix matrix2 = new Matrix();
            matrix2.postRotate(90.0f);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix2, true);
            Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(\n          …t, matrix, true\n        )");
            this$0.o.add(bitmapCreateBitmap);
        }
        if (!this$0.b.getG() && onScreenshotTakenCallback != null) {
            onScreenshotTakenCallback.onScreenshotTaken(null);
        }
        if (z) {
            if (onScreenshotTakenCallback != null) {
                Matrix matrix3 = new Matrix();
                matrix3.postRotate(90.0f);
                Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix3, true);
                Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap2, "createBitmap(\n          …t, matrix, true\n        )");
                onScreenshotTakenCallback.onScreenshotTaken(bitmapCreateBitmap2);
            }
        } else if (onScreenshotTakenCallback != null) {
            onScreenshotTakenCallback.onScreenshotTaken(bitmap);
        }
        this$0.o.removeFromQueue();
    }

    public final void a(final Bitmap bitmap, final Activity activity, final OnScreenshotTakenCallback onScreenshotTakenCallback, final boolean z, List<ViewRootData> list, String str, ScreenshotScalingFactor screenshotScalingFactor) throws InterruptedException {
        SensitiveViewsFinderResult sensitiveViewsFinderResultA;
        if (bitmap == null) {
            if (onScreenshotTakenCallback != null) {
                onScreenshotTakenCallback.onScreenshotTaken(null);
                return;
            }
            return;
        }
        if (activity != null && this.f266a.getG()) {
            if ((activity.getWindow().getAttributes().flags & 8192) != 0) {
                this.l.applyOcclusionFromSDK(new UXCamOverlay.Builder().build());
            } else {
                this.l.removeOcclusionFromSDK(new UXCamOverlay.Builder().build());
            }
        }
        Iterator<ViewRootData> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ViewRootData next = it.next();
            if (next.getView() instanceof ViewGroup) {
                SensitiveViewsFinder sensitiveViewsFinder = this.c;
                View view = next.getView();
                Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.view.ViewGroup");
                sensitiveViewsFinderResultA = sensitiveViewsFinder.a((ViewGroup) view, str, (List) this.f266a.getViewsToHide(), this.l.getOccludeAllTextFields(str) != null);
            } else {
                sensitiveViewsFinderResultA = this.c.a(next.getView(), str, this.f266a.getViewsToHide(), this.l.getOccludeAllTextFields(str) != null);
            }
            this.f266a.addViewsToHide(sensitiveViewsFinderResultA.f288a);
            this.f266a.removeViewsToHide(sensitiveViewsFinderResultA.b);
            this.f266a.setWebView(sensitiveViewsFinderResultA.c);
            int i = next.getWinFrame().left;
            int i2 = next.getWinFrame().top;
            Canvas canvas = new Canvas(bitmap);
            float f = screenshotScalingFactor.b;
            canvas.translate(i * f, i2 * f);
            float f2 = screenshotScalingFactor.b;
            canvas.scale(f2, f2);
            float f3 = screenshotScalingFactor.b;
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(new Rect(0, screenshotScalingFactor.f268a, (int) (bitmap.getWidth() / f3), (int) (bitmap.getHeight() / f3)), paint);
            this.f266a.setXOffset(0);
            this.f266a.setYOffset((int) (r3.height() * screenshotScalingFactor.b));
            this.g.a(next.getView(), canvas, this.f266a.getViewsToHide(), this.f266a.getRectsToHide());
            this.f266a.clearRectsToHide();
        }
        a(str, this.f266a.getWebView());
        List<OccludeComposable> composablesToHide = this.k.getComposablesToHide();
        Canvas canvas2 = new Canvas(bitmap);
        float f4 = 0 * screenshotScalingFactor.b;
        canvas2.translate(f4, f4);
        float f5 = screenshotScalingFactor.b;
        canvas2.scale(f5, f5);
        this.i.a(canvas2, composablesToHide);
        Paint paint2 = new Paint();
        paint2.setColor(-3355444);
        paint2.setStrokeWidth(3.0f);
        Paint paint3 = new Paint();
        paint3.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint3.setAntiAlias(true);
        paint3.setTextSize(16.0f);
        this.d.a(this.f266a.getK(), this.j.getScalingFactor(), new Canvas(bitmap), paint2, paint3);
        boolean z2 = this.l.shouldOcclude(str) || this.f266a.getP();
        boolean zIsPreviousFrameOccluded = this.f266a.getF();
        this.f266a.setPreviousFrameOccluded(z2);
        boolean z3 = zIsPreviousFrameOccluded || z2;
        FullScreenOcclusionCallback fullScreenOcclusionCallback = new FullScreenOcclusionCallback() { // from class: com.uxcam.screenshot.helper.ScreenShotHelperImpl$$ExternalSyntheticLambda1
            @Override // com.uxcam.screenshot.fullscreenocclusion.FullScreenOcclusionCallback
            public final void a() {
                ScreenShotHelperImpl.a(this.f$0, bitmap, activity, onScreenshotTakenCallback, z);
            }
        };
        if (z3) {
            FullScreenOcclusionConfig fullScreenOcclusionConfig = new FullScreenOcclusionConfig(bitmap, new Canvas(bitmap), fullScreenOcclusionCallback);
            UXCamOcclusion occlusion = this.l.getOcclusion(str);
            if (occlusion == null) {
                occlusion = this.f266a.getB();
                this.f266a.setLastOcclusion(null);
            } else {
                this.f266a.setLastOcclusion(occlusion);
            }
            this.f.a(fullScreenOcclusionConfig, occlusion, Util.getCurrentApplicationContext());
            return;
        }
        fullScreenOcclusionCallback.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.uxcam.screenshot.flutterviewfinder.FlutterConfig a(android.app.Activity r4) {
        /*
            r3 = this;
            boolean r0 = r3.f267n
            if (r0 == 0) goto L7b
            r0 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r4 = r4.findViewById(r0)
            android.view.View r4 = r4.getRootView()
            boolean r0 = r4 instanceof android.view.ViewGroup
            if (r0 == 0) goto L7b
            com.uxcam.screenshot.flutterviewfinder.FlutterViewFinder r0 = r3.e
            android.view.ViewGroup r4 = (android.view.ViewGroup) r4
            com.uxcam.screenshot.flutterviewfinder.FlutterConfig r4 = r0.a(r4)
            com.uxcam.screenshot.state.ScreenshotStateHolder r0 = r3.f266a
            java.util.List<java.lang.ref.WeakReference<io.flutter.view.FlutterView>> r1 = r4.f263a
            boolean r2 = r1 instanceof java.util.Collection
            if (r2 == 0) goto L2a
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L2a
            goto L49
        L2a:
            java.util.Iterator r1 = r1.iterator()
        L2e:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L49
            java.lang.Object r2 = r1.next()
            java.lang.ref.WeakReference r2 = (java.lang.ref.WeakReference) r2
            java.lang.Object r2 = r2.get()
            io.flutter.view.FlutterView r2 = (io.flutter.view.FlutterView) r2
            if (r2 == 0) goto L2e
            boolean r2 = r2.isShown()
            if (r2 == 0) goto L2e
            goto L74
        L49:
            java.util.List<java.lang.ref.WeakReference<io.flutter.embedding.android.FlutterSurfaceView>> r1 = r4.b
            boolean r2 = r1 instanceof java.util.Collection
            if (r2 == 0) goto L56
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L56
            goto L76
        L56:
            java.util.Iterator r1 = r1.iterator()
        L5a:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L76
            java.lang.Object r2 = r1.next()
            java.lang.ref.WeakReference r2 = (java.lang.ref.WeakReference) r2
            java.lang.Object r2 = r2.get()
            io.flutter.embedding.android.FlutterSurfaceView r2 = (io.flutter.embedding.android.FlutterSurfaceView) r2
            if (r2 == 0) goto L5a
            boolean r2 = r2.isShown()
            if (r2 == 0) goto L5a
        L74:
            r1 = 1
            goto L77
        L76:
            r1 = 0
        L77:
            r0.setIsFlutter(r1)
            return r4
        L7b:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.screenshot.helper.ScreenShotHelperImpl.a(android.app.Activity):com.uxcam.screenshot.flutterviewfinder.FlutterConfig");
    }

    public final void a(final String str, WeakReference weakReference) throws InterruptedException {
        WebView webView;
        if (weakReference != null) {
            try {
                webView = (WebView) weakReference.get();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        } else {
            webView = null;
        }
        if (webView != null) {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.uxcam.screenshot.helper.ScreenShotHelperImpl$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ScreenShotHelperImpl.a(this.f$0, str);
                }
            });
            countDownLatch.await(10L, TimeUnit.MILLISECONDS);
        }
    }

    public static final void a(ScreenShotHelperImpl this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.h.a(this$0.f266a.getWebView(), this$0.l.getOccludeAllTextFields(str));
    }
}
