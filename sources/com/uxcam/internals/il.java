package com.uxcam.internals;

import android.view.MotionEvent;
import android.view.View;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenshot.di.ScreenshotModule;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class il implements View.OnTouchListener {
    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (bi.D == null) {
            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = bi.D;
        Intrinsics.checkNotNull(biVar);
        fv fvVar = (fv) biVar.f();
        if (fvVar.k == null) {
            return false;
        }
        fvVar.k.getClass();
        he.a(motionEvent);
        return false;
    }
}
