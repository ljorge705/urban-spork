package com.uxcam.internals;

import android.graphics.Bitmap;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class bt {
    public static boolean c = true;

    /* renamed from: a, reason: collision with root package name */
    public final fn f103a;
    public Bitmap b;

    public bt(fn fnVar) {
        gk.a();
        this.f103a = fnVar;
    }

    public final void a(int i) {
        try {
            Bitmap bitmap = this.b;
            if (bitmap != null) {
                this.f103a.a(i, bitmap);
                if (bi.D == null) {
                    bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                }
                bi biVar = bi.D;
                Intrinsics.checkNotNull(biVar);
                gv gvVarI = biVar.i();
                if (((gw) gvVarI).f <= 0.0f) {
                    ((gw) gvVarI).f = Util.getCurrentUxcamTime(fp.f157n);
                }
            }
        } catch (IOException e) {
            gk.a("bt").getClass();
            fj fjVarB = new fj().b("EncodeVideo::doEncode()");
            fjVarB.a("reason", e.getMessage());
            fjVarB.a(2);
        }
    }
}
