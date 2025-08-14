package com.uxcam.internals;

import android.app.Application;
import android.os.Build;
import com.uxcam.screenaction.ScreenActionProvider;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.tracker.ScreenActionTracker;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.repository.OcclusionRepository;
import com.uxcam.screenshot.state.ScreenshotStateHolder;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;

/* loaded from: classes6.dex */
public final class bi {
    public static bi D;
    public hs A;
    public ae B;
    public fp C;

    /* renamed from: a, reason: collision with root package name */
    public final ScreenshotModule f98a;
    public final ScreenActionModule b;
    public final ch c;
    public final cj d;
    public gd e;
    public im f;
    public bp g;
    public er h;
    public bk i;
    public ez j;
    public fa k;
    public fz l;
    public hi m;

    /* renamed from: n, reason: collision with root package name */
    public gu f99n;
    public gw o;
    public du p;
    public ck q;
    public hy r;
    public el s;
    public ScreenActionTracker t;
    public gr u;
    public cb v;
    public fv w;
    public com.uxcam.aa x;
    public hl y;
    public hn z;

    public bi(ScreenshotModule screenshotModule, ScreenActionModule screenActionModule) {
        Intrinsics.checkNotNullParameter(screenshotModule, "screenshotModule");
        Intrinsics.checkNotNullParameter(screenActionModule, "screenActionModule");
        this.f98a = screenshotModule;
        this.b = screenActionModule;
        this.c = new ch();
        this.d = new cj();
    }

    @JvmStatic
    public static final bi b() {
        if (D == null) {
            D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
        }
        bi biVar = D;
        Intrinsics.checkNotNull(biVar);
        return biVar;
    }

    public final ck a() {
        if (this.q == null) {
            this.q = new ck();
        }
        ck ckVar = this.q;
        Intrinsics.checkNotNull(ckVar);
        return ckVar;
    }

    public final fp e() {
        if (this.C == null) {
            dk dkVar = new dk(Build.VERSION.SDK_INT >= 33 ? new di() : new dj());
            if (this.g == null) {
                this.g = new bp();
            }
            bl blVar = new bl(this.g, dkVar);
            gv gvVarI = i();
            hr hrVarL = l();
            ex exVarD = d();
            if (this.l == null) {
                this.l = new fz(h());
            }
            fz fzVar = this.l;
            Intrinsics.checkNotNull(fzVar);
            if (this.v == null) {
                this.v = new cb(i());
            }
            cb cbVar = this.v;
            Intrinsics.checkNotNull(cbVar);
            this.C = new fp(blVar, gvVarI, hrVarL, exVarD, fzVar, cbVar, m());
        }
        fp fpVar = this.C;
        Intrinsics.checkNotNull(fpVar);
        return fpVar;
    }

    public final fu f() {
        if (this.w == null) {
            this.w = new fv();
        }
        fv fvVar = this.w;
        Intrinsics.checkNotNull(fvVar);
        return fvVar;
    }

    public final gs h() {
        if (this.f99n == null) {
            gv gvVarI = i();
            ex exVarD = d();
            Intrinsics.checkNotNull(exVarD);
            OcclusionRepository occlusionRepository = this.f98a.getOcclusionRepository();
            ScreenshotStateHolder screenshotStateHolder = this.f98a.getScreenshotStateHolder();
            fj fjVar = new fj();
            Intrinsics.checkNotNullExpressionValue(fjVar, "getInstance()");
            if (this.s == null) {
                int i = ga.w[0];
                float f = r1[1] / 1000.0f;
                int iMmToPx = (int) Util.mmToPx(r1[2], Util.getCurrentApplicationContext());
                gk.a("rageClickDetector").getClass();
                this.s = new el(i, f, iMmToPx, null);
            }
            el elVar = this.s;
            Intrinsics.checkNotNull(elVar);
            hy hyVarM = m();
            Intrinsics.checkNotNull(hyVarM);
            if (this.t == null) {
                this.t = new ScreenActionTracker(com.uxcam.aa.i, this.b.getScreenActionViewsRepository());
            }
            ScreenActionTracker screenActionTracker = this.t;
            Intrinsics.checkNotNull(screenActionTracker);
            if (this.u == null) {
                gv gvVarI2 = i();
                gc gcVarG = g();
                Intrinsics.checkNotNull(gcVarG);
                ex exVarD2 = d();
                Intrinsics.checkNotNull(exVarD2);
                this.u = new gr(gvVarI2, gcVarG, exVarD2);
            }
            gr grVar = this.u;
            Intrinsics.checkNotNull(grVar);
            if (this.v == null) {
                this.v = new cb(i());
            }
            cb cbVar = this.v;
            Intrinsics.checkNotNull(cbVar);
            this.f99n = new gu(gvVarI, exVarD, occlusionRepository, screenshotStateHolder, fjVar, elVar, hyVarM, screenActionTracker, grVar, cbVar, Dispatchers.getIO(), Dispatchers.getMain());
        }
        gu guVar = this.f99n;
        Intrinsics.checkNotNull(guVar);
        return guVar;
    }

    public final gv i() {
        if (this.o == null) {
            this.o = new gw();
        }
        gw gwVar = this.o;
        Intrinsics.checkNotNull(gwVar);
        return gwVar;
    }

    public final com.uxcam.aa j() {
        if (this.x == null) {
            fu fuVarF = f();
            Application applicationContext = Util.getApplicationContext();
            if (this.z == null) {
                this.z = new hn(f(), e());
            }
            hn hnVar = this.z;
            Intrinsics.checkNotNull(hnVar);
            hr hrVarL = l();
            if (this.l == null) {
                this.l = new fz(h());
            }
            fz fzVar = this.l;
            Intrinsics.checkNotNull(fzVar);
            gv gvVarI = i();
            if (this.v == null) {
                this.v = new cb(i());
            }
            cb cbVar = this.v;
            Intrinsics.checkNotNull(cbVar);
            this.x = new com.uxcam.aa(fuVarF, applicationContext, hnVar, hrVarL, fzVar, gvVarI, cbVar);
        }
        com.uxcam.aa aaVar = this.x;
        Intrinsics.checkNotNull(aaVar);
        return aaVar;
    }

    public final hk k() {
        if (this.y == null) {
            fu fuVarF = f();
            Application applicationContext = Util.getApplicationContext();
            hr hrVarL = l();
            if (this.B == null) {
                fu fuVarF2 = f();
                ck ckVarA = a();
                ex exVarD = d();
                Intrinsics.checkNotNull(exVarD);
                this.B = new ae(fuVarF2, ckVarA, exVarD);
            }
            ae aeVar = this.B;
            Intrinsics.checkNotNull(aeVar);
            ck ckVarA2 = a();
            ex exVarD2 = d();
            Intrinsics.checkNotNull(exVarD2);
            this.y = new hl(fuVarF, applicationContext, hrVarL, aeVar, ckVarA2, exVarD2);
        }
        hl hlVar = this.y;
        Intrinsics.checkNotNull(hlVar);
        return hlVar;
    }

    public final hr l() {
        if (this.A == null) {
            this.A = new hs();
        }
        hs hsVar = this.A;
        Intrinsics.checkNotNull(hsVar);
        return hsVar;
    }

    public final hy m() {
        if (this.r == null) {
            ScreenActionProvider screenActionProvider = this.b.getScreenActionProvider();
            eq eqVarC = c();
            if (this.s == null) {
                int i = ga.w[0];
                float f = r3[1] / 1000.0f;
                int iMmToPx = (int) Util.mmToPx(r3[2], Util.getCurrentApplicationContext());
                gk.a("rageClickDetector").getClass();
                this.s = new el(i, f, iMmToPx, null);
            }
            this.r = new hy(screenActionProvider, eqVarC, this.s);
        }
        return this.r;
    }

    public final eq c() {
        er erVar = this.h;
        if (erVar != null) {
            return erVar;
        }
        im imVar = this.f;
        if (imVar == null) {
            imVar = new im(this.f98a.getScreenshotStateHolder());
            this.f = imVar;
        }
        er erVar2 = new er(imVar, this.f98a.getScreenshotStateHolder());
        this.h = erVar2;
        return erVar2;
    }

    public final ex d() {
        fa faVar = this.k;
        if (faVar != null) {
            return faVar;
        }
        ch chVar = this.c;
        cj cjVar = this.d;
        if (this.j == null) {
            this.j = new ez(l());
        }
        ez ezVar = this.j;
        Intrinsics.checkNotNull(ezVar);
        fa faVar2 = new fa(chVar, cjVar, ezVar, new fe(new fc()));
        this.k = faVar2;
        Intrinsics.checkNotNull(faVar2);
        return faVar2;
    }

    public final gc g() {
        gd gdVar = this.e;
        if (gdVar != null) {
            return gdVar;
        }
        gd gdVar2 = new gd();
        this.e = gdVar2;
        return gdVar2;
    }
}
