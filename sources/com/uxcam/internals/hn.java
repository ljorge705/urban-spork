package com.uxcam.internals;

import android.content.Context;
import android.os.Build;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.utils.FilePath;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import java.util.HashMap;
import java.util.UUID;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class hn implements hm {

    /* renamed from: a, reason: collision with root package name */
    public final fu f195a;
    public final fp b;

    public hn(fu sessionRepository, fp serviceHandler) {
        Intrinsics.checkNotNullParameter(sessionRepository, "sessionRepository");
        Intrinsics.checkNotNullParameter(serviceHandler, "serviceHandler");
        this.f195a = sessionRepository;
        this.b = serviceHandler;
    }

    public final void a(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ff.j = 0L;
        int i = ga.f167a;
        this.f195a.a(false);
        gk.a("UXCamStopperImpl").getClass();
        this.f195a.a((he) null);
        ScreenshotModule.INSTANCE.getInstance().getScreenshotStateHolder().setOrientation(-1);
        this.f195a.a(2);
        if (this.f195a.j() == 1) {
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar = bi.D;
            Intrinsics.checkNotNull(biVar);
            ((gw) biVar.i()).b.clear();
        }
        try {
            if (bh.f97a) {
                id.f206a = false;
                fp fpVar = this.b;
                Util.getCurrentApplicationContext();
                fpVar.a("");
                return;
            }
            if (!bh.b) {
                if (ga.f168n) {
                    ga.f168n = false;
                    return;
                }
                return;
            }
            bh.b = false;
            String str = FilePath.getRootUrl(true) + '/' + UUID.randomUUID() + '/';
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar2 = bi.D;
            Intrinsics.checkNotNull(biVar2);
            biVar2.getClass();
            dk dkVar = new dk(Build.VERSION.SDK_INT >= 33 ? new di() : new dj());
            if (biVar2.g == null) {
                biVar2.g = new bp();
            }
            bl blVar = new bl(biVar2.g, dkVar);
            blVar.c = 4;
            blVar.a("", (fr) null, str);
            ht.a("createdCancelledSessionFile", (HashMap) null);
        } catch (Exception e) {
            e.printStackTrace();
            gk.c.getClass();
        }
    }

    @Override // com.uxcam.internals.hm
    public final void b() {
        this.f195a.b(true);
        com.uxcam.aa.k = false;
        Context currentApplicationContext = Util.getCurrentApplicationContext();
        Intrinsics.checkNotNullExpressionValue(currentApplicationContext, "getCurrentApplicationContext()");
        a(currentApplicationContext);
    }

    @Override // com.uxcam.internals.hm
    public final void a() {
        gk.a("UXCamStopperImpl").getClass();
        this.f195a.b(false);
        if (this.f195a.i() != null) {
            he heVarI = this.f195a.i();
            Intrinsics.checkNotNull(heVarI);
            heVarI.getClass();
            try {
                if (bi.D == null) {
                    bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                }
                bi biVar = bi.D;
                Intrinsics.checkNotNull(biVar);
                if (biVar.z == null) {
                    biVar.z = new hn(biVar.f(), biVar.e());
                }
                hn hnVar = biVar.z;
                Intrinsics.checkNotNull(hnVar);
                hnVar.a(Util.getCurrentApplicationContext());
                gk.a("he").getClass();
            } catch (Exception unused) {
                gk.a("he").getClass();
            }
        }
    }
}
