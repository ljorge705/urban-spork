package com.uxcam.screenshot.screenshotTaker;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import com.uxcam.screenaction.models.ViewRootData;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.helper.OnScreenshotTakenCallback;
import com.uxcam.screenshot.helper.ScreenshotScalingFactor;
import com.uxcam.screenshot.legacyscreenshot.LegacyScreenshot;
import com.uxcam.screenshot.legacyscreenshot.LegacyScreenshotConfig;
import com.uxcam.screenshot.legacyscreenshot.LegacyScreenshotImpl;
import com.uxcam.screenshot.pixelcopyscreenshot.PixelCopyScreenshot;
import com.uxcam.screenshot.pixelcopyscreenshot.PixelCopyScreenshotConfig;
import com.uxcam.screenshot.state.ScreenshotStateHolder;
import com.uxcam.screenshot.utils.AnyExtensionKt;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/uxcam/screenshot/screenshotTaker/ScreenshotTakerImpl;", "Lcom/uxcam/screenshot/screenshotTaker/ScreenshotTaker;", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public class ScreenshotTakerImpl implements ScreenshotTaker {

    /* renamed from: a, reason: collision with root package name */
    public final PixelCopyScreenshot f280a;
    public final LegacyScreenshot b;
    public final LargestViewRootFilter c;
    public final ScreenshotStateHolder d;
    public final BlackScreenDrawer e;
    public final CountDownLatch f;
    public boolean g;

    public ScreenshotTakerImpl(PixelCopyScreenshot pixelCopyScreenshot, LegacyScreenshot legacyScreenshot, LargestViewRootFilter largestViewRootFilter, ScreenshotStateHolder screenshotStateHolder, BlackScreenDrawerImpl blackScreenDrawer) {
        Intrinsics.checkNotNullParameter(pixelCopyScreenshot, "pixelCopyScreenshot");
        Intrinsics.checkNotNullParameter(legacyScreenshot, "legacyScreenshot");
        Intrinsics.checkNotNullParameter(largestViewRootFilter, "largestViewRootFilter");
        Intrinsics.checkNotNullParameter(screenshotStateHolder, "screenshotStateHolder");
        Intrinsics.checkNotNullParameter(blackScreenDrawer, "blackScreenDrawer");
        this.f280a = pixelCopyScreenshot;
        this.b = legacyScreenshot;
        this.c = largestViewRootFilter;
        this.d = screenshotStateHolder;
        this.e = blackScreenDrawer;
        this.f = new CountDownLatch(2);
    }

    public static final void a(Bitmap bitmap) {
    }

    public final void a(Canvas canvas, ViewRootData viewRootData, Bitmap bitmap, ScreenshotTakerConfig screenshotTakerConfig, boolean z, OnScreenshotTakenCallback onScreenshotTakenCallback) {
        try {
            try {
                if (z) {
                    if (screenshotTakerConfig.f279a == null) {
                        onScreenshotTakenCallback.onScreenshotTaken(null);
                        return;
                    } else {
                        AnyExtensionKt.a(this);
                        a(bitmap, canvas, viewRootData, onScreenshotTakenCallback, screenshotTakerConfig.f279a);
                        return;
                    }
                }
                try {
                    AnyExtensionKt.a(this);
                    a(viewRootData, bitmap, canvas, onScreenshotTakenCallback, screenshotTakerConfig);
                } catch (IllegalArgumentException unused) {
                    if (Build.VERSION.SDK_INT >= 26) {
                        if (screenshotTakerConfig.f279a == null) {
                            onScreenshotTakenCallback.onScreenshotTaken(null);
                        } else {
                            AnyExtensionKt.a(this);
                            a(bitmap, canvas, viewRootData, onScreenshotTakenCallback, screenshotTakerConfig.f279a);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (screenshotTakerConfig.f) {
                    this.f.countDown();
                }
                onScreenshotTakenCallback.onScreenshotTaken(null);
            }
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            if (screenshotTakerConfig.f) {
                this.f.countDown();
            }
            onScreenshotTakenCallback.onScreenshotTaken(null);
        }
    }

    @Override // com.uxcam.screenshot.screenshotTaker.ScreenshotTaker
    /* renamed from: a, reason: from getter */
    public final boolean getG() {
        return this.g;
    }

    public final void b(Bitmap bitmap, OnScreenshotTakenCallback onScreenshotTakenCallback, ScreenshotTakerConfig screenshotTakerConfig, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ViewRootData viewRootData = (ViewRootData) it.next();
            ScreenshotScalingFactor screenshotScalingFactor = screenshotTakerConfig.i;
            Canvas canvas = new Canvas(bitmap);
            canvas.translate(viewRootData.getWinFrame().left * screenshotScalingFactor.b, viewRootData.getWinFrame().top * screenshotScalingFactor.b);
            float f = screenshotScalingFactor.b;
            canvas.scale(f, f);
            a(canvas, viewRootData, bitmap, screenshotTakerConfig, false, new OnScreenshotTakenCallback() { // from class: com.uxcam.screenshot.screenshotTaker.ScreenshotTakerImpl$$ExternalSyntheticLambda0
                @Override // com.uxcam.screenshot.helper.OnScreenshotTakenCallback
                public final void onScreenshotTaken(Bitmap bitmap2) {
                    ScreenshotTakerImpl.a(bitmap2);
                }
            });
        }
        onScreenshotTakenCallback.onScreenshotTaken(bitmap);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.util.concurrent.CountDownLatch] */
    @Override // com.uxcam.screenshot.screenshotTaker.ScreenshotTaker
    public final void a(ScreenshotTakerConfig screenshotTakerConfig, OnScreenshotTakenCallback onScreenshotTaken) throws InterruptedException {
        Intrinsics.checkNotNullParameter(screenshotTakerConfig, "screenshotTakerConfig");
        Intrinsics.checkNotNullParameter(onScreenshotTaken, "onScreenshotTaken");
        if (screenshotTakerConfig.f279a != null) {
            if (screenshotTakerConfig.h) {
                this.e.a(screenshotTakerConfig.b);
                this.f.countDown();
                onScreenshotTaken.onScreenshotTaken(screenshotTakerConfig.b);
                return;
            }
            Iterator<ViewRootData> it = screenshotTakerConfig.j.iterator();
            boolean z = true;
            while (it.hasNext()) {
                try {
                    if (Intrinsics.areEqual(it.next().getView().getClass().getCanonicalName(), "androidx.compose.ui.window.PopupLayout")) {
                        z = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (screenshotTakerConfig.f && z) {
                this.c.a(screenshotTakerConfig.j);
            }
            try {
                try {
                    a(screenshotTakerConfig.b, onScreenshotTaken, screenshotTakerConfig, screenshotTakerConfig.j);
                    this.f.countDown();
                } catch (Throwable th) {
                    this.f.countDown();
                    if (!screenshotTakerConfig.f) {
                        this.f.countDown();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                onScreenshotTaken.onScreenshotTaken(null);
                this.f.countDown();
                if (!screenshotTakerConfig.f) {
                }
            }
            if (!screenshotTakerConfig.f) {
                this.f.countDown();
            }
            screenshotTakerConfig = this.f;
            screenshotTakerConfig.await(500L, TimeUnit.MILLISECONDS);
            return;
        }
        throw new IllegalArgumentException("Parameter activity cannot be null.".toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0073  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(android.graphics.Bitmap r10, com.uxcam.screenshot.helper.OnScreenshotTakenCallback r11, com.uxcam.screenshot.screenshotTaker.ScreenshotTakerConfig r12, java.util.List r13) {
        /*
            r9 = this;
            r0 = 1
            r9.g = r0
            boolean r1 = r13.isEmpty()
            if (r1 == 0) goto L14
            java.util.concurrent.CountDownLatch r10 = r9.f
            r10.countDown()
            r10 = 0
            r11.onScreenshotTaken(r10)
            goto L7f
        L14:
            boolean r1 = r12.f
            if (r1 == 0) goto L7c
            boolean r1 = r12.g
            if (r1 == 0) goto L7c
            java.util.Iterator r13 = r13.iterator()
        L20:
            boolean r1 = r13.hasNext()
            if (r1 == 0) goto L7f
            java.lang.Object r1 = r13.next()
            r4 = r1
            com.uxcam.screenaction.models.ViewRootData r4 = (com.uxcam.screenaction.models.ViewRootData) r4
            com.uxcam.screenshot.helper.ScreenshotScalingFactor r1 = r12.i
            android.graphics.Canvas r3 = new android.graphics.Canvas
            r3.<init>(r10)
            android.graphics.Rect r2 = r4.getWinFrame()
            int r2 = r2.left
            float r2 = (float) r2
            float r5 = r1.b
            float r2 = r2 * r5
            android.graphics.Rect r5 = r4.getWinFrame()
            int r5 = r5.top
            float r5 = (float) r5
            float r6 = r1.b
            float r5 = r5 * r6
            r3.translate(r2, r5)
            float r1 = r1.b
            r3.scale(r1, r1)
            java.lang.String r1 = "com.uxcam.UXCamKt"
            boolean r1 = com.uxcam.screenaction.utils.Util.isClass(r1)
            if (r1 == 0) goto L73
            android.view.View r1 = r4.getView()     // Catch: java.lang.Exception -> L6f
            java.lang.Class r1 = r1.getClass()     // Catch: java.lang.Exception -> L6f
            java.lang.String r1 = r1.getCanonicalName()     // Catch: java.lang.Exception -> L6f
            java.lang.String r2 = "androidx.compose.ui.window.PopupLayout"
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r2)     // Catch: java.lang.Exception -> L6f
            if (r1 == 0) goto L73
            r1 = 0
            r7 = r1
            goto L74
        L6f:
            r1 = move-exception
            r1.printStackTrace()
        L73:
            r7 = r0
        L74:
            r2 = r9
            r5 = r10
            r6 = r12
            r8 = r11
            r2.a(r3, r4, r5, r6, r7, r8)
            goto L20
        L7c:
            r9.b(r10, r11, r12, r13)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.screenshot.screenshotTaker.ScreenshotTakerImpl.a(android.graphics.Bitmap, com.uxcam.screenshot.helper.OnScreenshotTakenCallback, com.uxcam.screenshot.screenshotTaker.ScreenshotTakerConfig, java.util.List):void");
    }

    public final void a(Bitmap bitmap, Canvas canvas, ViewRootData viewRootData, final OnScreenshotTakenCallback onScreenshotTakenCallback, Activity activity) {
        this.f280a.a(new PixelCopyScreenshotConfig(bitmap, canvas, new OnScreenshotTakenCallback() { // from class: com.uxcam.screenshot.screenshotTaker.ScreenshotTakerImpl$$ExternalSyntheticLambda1
            @Override // com.uxcam.screenshot.helper.OnScreenshotTakenCallback
            public final void onScreenshotTaken(Bitmap bitmap2) {
                ScreenshotTakerImpl.a(this.f$0, onScreenshotTakenCallback, bitmap2);
            }
        }, ScreenshotModule.INSTANCE.getInstance().getSensitiveViewsFinder().a(viewRootData, this.d.getViewsToHide()), activity));
    }

    public static final void a(ScreenshotTakerImpl this$0, OnScreenshotTakenCallback callback, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        this$0.f.countDown();
        callback.onScreenshotTaken(bitmap);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.uxcam.screenshot.screenshotTaker.ScreenshotTakerImpl$legacyScreenshot$1] */
    public final void a(ViewRootData viewRootData, Bitmap bitmap, Canvas canvas, OnScreenshotTakenCallback onScreenshotTakenCallback, ScreenshotTakerConfig screenshotTakerConfig) {
        this.b.a(new LegacyScreenshotConfig(viewRootData, bitmap, canvas, screenshotTakerConfig.e, screenshotTakerConfig.d, Build.VERSION.SDK_INT, this.d.getWebView(), this.d.isFlutter(), screenshotTakerConfig.c, ScreenshotModule.INSTANCE.getInstance().getScreenshotStateHolder().getMapBitmap()), new LegacyScreenshotImpl.OnLegacyScreenshotGoogleMapFound() { // from class: com.uxcam.screenshot.screenshotTaker.ScreenshotTakerImpl$legacyScreenshot$1
            @Override // com.uxcam.screenshot.legacyscreenshot.LegacyScreenshotImpl.OnLegacyScreenshotGoogleMapFound
            public final void a(Bitmap bitmap2) {
                ScreenshotModule.INSTANCE.getInstance().getScreenshotStateHolder().setMapBitmap(bitmap2);
            }
        });
        onScreenshotTakenCallback.onScreenshotTaken(bitmap);
    }
}
