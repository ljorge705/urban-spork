package com.uxcam.internals;

import android.app.Activity;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.state.ScreenshotStateHolder;
import java.util.TimerTask;

/* loaded from: classes6.dex */
public final class fo extends TimerTask {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ fp f156a;

    public fo(fp fpVar) {
        this.f156a = fpVar;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        try {
            Activity activity = (Activity) Util.getCurrentContext();
            if (ga.f || activity == null) {
                return;
            }
            int i = activity.getResources().getConfiguration().orientation;
            ScreenshotStateHolder screenshotStateHolder = ScreenshotModule.getInstance().getScreenshotStateHolder();
            if (screenshotStateHolder.getD() == i || screenshotStateHolder.getE()) {
                return;
            }
            screenshotStateHolder.setOrientation(i);
            this.f156a.m.a(10, 0.0f, 0.0f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
