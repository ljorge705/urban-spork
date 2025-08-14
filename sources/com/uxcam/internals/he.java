package com.uxcam.internals;

import android.app.Activity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.state.ScreenshotStateHolder;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class he {
    public static /* synthetic */ void a(ScreenshotStateHolder screenshotStateHolder) {
        if (!ga.F || screenshotStateHolder.getWebView() == null || screenshotStateHolder.getWebView().get() == null) {
            return;
        }
        screenshotStateHolder.getWebView().get().addJavascriptInterface(new hp(), "UXCam");
    }

    public static void b(final Activity activity, final boolean z) {
        new Thread(new Runnable() { // from class: com.uxcam.internals.he$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                he.a(activity, z);
            }
        }).start();
    }

    public static void a(MotionEvent motionEvent) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        gu guVar = (gu) biVar.h();
        GestureDetector gestureDetector = guVar.m;
        ScaleGestureDetector scaleGestureDetector = guVar.f184n;
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(motionEvent);
        }
        if (scaleGestureDetector != null) {
            scaleGestureDetector.onTouchEvent(motionEvent);
        }
    }

    public static void a(Activity activity, boolean z) {
        if (activity != null) {
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar = bi.D;
            Intrinsics.checkNotNull(biVar);
            hl hlVar = (hl) biVar.k();
            hlVar.a(z);
            try {
                final ScreenshotStateHolder screenshotStateHolder = ScreenshotModule.getInstance().getScreenshotStateHolder();
                if (screenshotStateHolder.getWebView() != null && screenshotStateHolder.getWebView().get() != null) {
                    screenshotStateHolder.getWebView().get().post(new Runnable() { // from class: com.uxcam.internals.he$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            he.a(screenshotStateHolder);
                        }
                    });
                }
            } catch (Exception e) {
                fj fjVarB = new fj().b("UXCamActivityData::onStartTask()");
                fjVarB.a("reason", e.getMessage());
                fjVarB.a(2);
            }
            hlVar.b();
        }
    }
}
