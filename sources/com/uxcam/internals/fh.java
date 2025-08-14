package com.uxcam.internals;

import android.graphics.Bitmap;
import android.os.Handler;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.tracker.ScreenActionTracker;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.helper.ScreenshotHelper;
import com.uxcam.screenshot.state.ScreenshotStateHolder;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class fh extends TimerTask {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ScreenshotHelper f150a;
    public final /* synthetic */ ff b;

    public fh(ff ffVar, ScreenshotHelper screenshotHelper) {
        this.b = ffVar;
        this.f150a = screenshotHelper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ScreenshotHelper screenshotHelper) {
        Bitmap lastFrameCache;
        if (bh.f97a) {
            Timer timer = ff.d;
            ScreenshotStateHolder screenshotStateHolder = ScreenshotModule.getInstance().getScreenshotStateHolder();
            if (screenshotStateHolder.getE() && (lastFrameCache = ScreenshotModule.getInstance().getBitmapSource().getLastFrameCache()) != null && screenshotStateHolder.isImprovedScreenCaptureInUse()) {
                this.b.getClass();
                if (bi.D == null) {
                    bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                }
                bi biVar = bi.D;
                Intrinsics.checkNotNull(biVar);
                biVar.f98a.getBitmapSource().add(lastFrameCache.copy(lastFrameCache.getConfig(), false));
                if (bi.D == null) {
                    bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                }
                bi biVar2 = bi.D;
                Intrinsics.checkNotNull(biVar2);
                biVar2.f98a.getBitmapSource().count();
            }
            if (ga.B) {
                new ScreenActionTracker(com.uxcam.aa.i, ScreenActionModule.getInstance().getScreenActionViewsRepository()).loopLayout();
            }
            if (ff.m) {
                this.b.getClass();
                ff.a(screenshotHelper);
                return;
            }
            dd<ew> ddVar = ff.f;
            if (ddVar != null) {
                int size = ddVar.size();
                ScreenshotModule.getInstance().getScreenshotStateHolder().increaseImageCount();
                if (size < 10) {
                    this.b.getClass();
                    ff.a(screenshotHelper);
                }
            }
        }
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        Handler handler = this.b.f147a;
        final ScreenshotHelper screenshotHelper = this.f150a;
        handler.post(new Runnable() { // from class: com.uxcam.internals.fh$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(screenshotHelper);
            }
        });
    }
}
