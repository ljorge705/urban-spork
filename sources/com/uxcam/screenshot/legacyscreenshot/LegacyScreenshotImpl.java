package com.uxcam.screenshot.legacyscreenshot;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.webkit.WebView;
import com.uxcam.screenaction.models.ViewRootData;
import com.uxcam.screenshot.flutterviewfinder.FlutterConfig;
import com.uxcam.screenshot.screenshotTaker.ScreenshotTakerImpl$legacyScreenshot$1;
import com.uxcam.screenshot.utils.AnyExtensionKt;
import io.flutter.embedding.android.FlutterSurfaceView;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/uxcam/screenshot/legacyscreenshot/LegacyScreenshotImpl;", "Lcom/uxcam/screenshot/legacyscreenshot/LegacyScreenshot;", "<init>", "()V", "OnLegacyScreenshotGoogleMapFound", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class LegacyScreenshotImpl implements LegacyScreenshot {

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/uxcam/screenshot/legacyscreenshot/LegacyScreenshotImpl$OnLegacyScreenshotGoogleMapFound;", "", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0})
    public interface OnLegacyScreenshotGoogleMapFound {
        void a(Bitmap bitmap);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b(com.uxcam.screenshot.legacyscreenshot.LegacyScreenshotConfig r7, final com.uxcam.screenshot.screenshotTaker.ScreenshotTakerImpl$legacyScreenshot$1 r8) {
        /*
            android.graphics.Canvas r0 = r7.c
            com.google.android.gms.maps.GoogleMap r1 = r7.e
            java.lang.ref.WeakReference<android.view.View> r2 = r7.j     // Catch: java.lang.Exception -> L7a
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L45
            java.lang.Object r2 = r2.get()     // Catch: java.lang.Exception -> L7a
            if (r2 == 0) goto L45
            java.lang.ref.WeakReference<android.view.View> r2 = r7.j     // Catch: java.lang.Exception -> L7a
            java.lang.Object r2 = r2.get()     // Catch: java.lang.Exception -> L7a
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch: java.lang.Exception -> L7a
            android.view.View r2 = (android.view.View) r2     // Catch: java.lang.Exception -> L7a
            boolean r2 = r2.isShown()     // Catch: java.lang.Exception -> L7a
            if (r2 == 0) goto L45
            java.lang.ref.WeakReference<android.view.View> r2 = r7.j     // Catch: java.lang.Exception -> L7a
            java.lang.Object r2 = r2.get()     // Catch: java.lang.Exception -> L7a
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch: java.lang.Exception -> L7a
            android.view.View r2 = (android.view.View) r2     // Catch: java.lang.Exception -> L7a
            int r2 = r2.getMeasuredWidth()     // Catch: java.lang.Exception -> L7a
            if (r2 <= 0) goto L45
            java.lang.ref.WeakReference<android.view.View> r2 = r7.j     // Catch: java.lang.Exception -> L7a
            java.lang.Object r2 = r2.get()     // Catch: java.lang.Exception -> L7a
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch: java.lang.Exception -> L7a
            android.view.View r2 = (android.view.View) r2     // Catch: java.lang.Exception -> L7a
            int r2 = r2.getMeasuredHeight()     // Catch: java.lang.Exception -> L7a
            if (r2 <= 0) goto L45
            r2 = r3
            goto L46
        L45:
            r2 = r4
        L46:
            android.graphics.Bitmap r5 = r7.k     // Catch: java.lang.Exception -> L7a
            if (r5 == 0) goto L6d
            if (r2 == 0) goto L6d
            int[] r5 = new int[]{r4, r4}     // Catch: java.lang.Exception -> L7a
            java.lang.ref.WeakReference<android.view.View> r6 = r7.j     // Catch: java.lang.Exception -> L7a
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)     // Catch: java.lang.Exception -> L7a
            java.lang.Object r6 = r6.get()     // Catch: java.lang.Exception -> L7a
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)     // Catch: java.lang.Exception -> L7a
            android.view.View r6 = (android.view.View) r6     // Catch: java.lang.Exception -> L7a
            r6.getLocationOnScreen(r5)     // Catch: java.lang.Exception -> L7a
            android.graphics.Bitmap r7 = r7.k     // Catch: java.lang.Exception -> L7a
            r4 = r5[r4]     // Catch: java.lang.Exception -> L7a
            float r4 = (float) r4     // Catch: java.lang.Exception -> L7a
            r3 = r5[r3]     // Catch: java.lang.Exception -> L7a
            float r3 = (float) r3     // Catch: java.lang.Exception -> L7a
            r5 = 0
            r0.drawBitmap(r7, r4, r3, r5)     // Catch: java.lang.Exception -> L7a
        L6d:
            if (r1 == 0) goto L7e
            if (r2 == 0) goto L7e
            com.uxcam.screenshot.legacyscreenshot.LegacyScreenshotImpl$$ExternalSyntheticLambda0 r7 = new com.uxcam.screenshot.legacyscreenshot.LegacyScreenshotImpl$$ExternalSyntheticLambda0     // Catch: java.lang.Exception -> L7a
            r7.<init>()     // Catch: java.lang.Exception -> L7a
            r1.snapshot(r7)     // Catch: java.lang.Exception -> L7a
            goto L7e
        L7a:
            r7 = move-exception
            r7.printStackTrace()
        L7e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.screenshot.legacyscreenshot.LegacyScreenshotImpl.b(com.uxcam.screenshot.legacyscreenshot.LegacyScreenshotConfig, com.uxcam.screenshot.screenshotTaker.ScreenshotTakerImpl$legacyScreenshot$1):void");
    }

    @Override // com.uxcam.screenshot.legacyscreenshot.LegacyScreenshot
    public final void a(LegacyScreenshotConfig screenshotConfig, ScreenshotTakerImpl$legacyScreenshot$1 googleMapFound) throws InterruptedException {
        FlutterConfig flutterConfig;
        WeakReference<WebView> weakReference;
        Intrinsics.checkNotNullParameter(screenshotConfig, "screenshotConfig");
        Intrinsics.checkNotNullParameter(googleMapFound, "googleMapFound");
        ViewRootData viewRootData = screenshotConfig.f269a;
        if (viewRootData != null && (viewRootData.getLayoutParams().flags & 2) == 2) {
            screenshotConfig.c.drawARGB((int) (255 * screenshotConfig.f269a.getLayoutParams().dimAmount), 0, 0, 0);
        }
        if (screenshotConfig.f < 23) {
            ViewRootData viewRootData2 = screenshotConfig.f269a;
            Intrinsics.checkNotNull(viewRootData2);
            viewRootData2.getView().draw(screenshotConfig.c);
            return;
        }
        ViewRootData viewRootData3 = screenshotConfig.f269a;
        Intrinsics.checkNotNull(viewRootData3);
        if (viewRootData3.getView().isAttachedToWindow()) {
            if (!screenshotConfig.g || (weakReference = screenshotConfig.h) == null || weakReference.get() == null) {
                ViewRootData viewRootData4 = screenshotConfig.f269a;
                if (viewRootData4 != null) {
                    viewRootData4.getView().draw(screenshotConfig.c);
                }
            } else {
                BuildersKt__BuildersKt.runBlocking$default(null, new LegacyScreenshotImpl$drawViewOnCanvas$1(this, screenshotConfig, null), 1, null);
            }
            Canvas canvas = screenshotConfig.c;
            if (screenshotConfig.i && (flutterConfig = screenshotConfig.d) != null && (!flutterConfig.b.isEmpty())) {
                List<WeakReference<FlutterSurfaceView>> list = screenshotConfig.d.b;
                System.currentTimeMillis();
                BuildersKt__BuildersKt.runBlocking$default(null, new LegacyScreenshotImpl$captureSurfaceView$timeTaken$1$1(list, canvas, null), 1, null);
                System.currentTimeMillis();
                AnyExtensionKt.a(this);
            }
            b(screenshotConfig, googleMapFound);
        }
    }

    public static final void a(OnLegacyScreenshotGoogleMapFound googleMapFound, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(googleMapFound, "$googleMapFound");
        googleMapFound.a(bitmap);
    }
}
