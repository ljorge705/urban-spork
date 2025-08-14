package com.uxcam.internals;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.uxcam.internals.gk;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import io.sentry.cache.EnvelopeCache;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class hc implements Application.ActivityLifecycleCallbacks {
    public static int m;

    /* renamed from: a, reason: collision with root package name */
    public boolean f188a;
    public final hm b;
    public final fu c;
    public final ck d;
    public final ex e;
    public int f;
    public aa g;
    public Future<?> h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;

    public interface aa {
        void a(Activity activity);
    }

    public hc(boolean z, hm uxCamStopper, fu sessionRepository, ck fragmentUtils, ex screenTagManager) {
        Intrinsics.checkNotNullParameter(uxCamStopper, "uxCamStopper");
        Intrinsics.checkNotNullParameter(sessionRepository, "sessionRepository");
        Intrinsics.checkNotNullParameter(fragmentUtils, "fragmentUtils");
        Intrinsics.checkNotNullParameter(screenTagManager, "screenTagManager");
        this.f188a = z;
        this.b = uxCamStopper;
        this.c = sessionRepository;
        this.d = fragmentUtils;
        this.e = screenTagManager;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        try {
            this.d.getClass();
            ck.a(activity);
        } catch (Exception unused) {
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(outState, "outState");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        gk.aa aaVarA = gk.a(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE);
        activity.getClass();
        aaVarA.getClass();
        if (this.l) {
            this.l = false;
            a();
        }
        this.k = true;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.k = false;
        this.e.f();
        this.c.a(activity);
        if (m == 0) {
            gk.a("UXCam").a("UXCam 3.6.13[580](Warning): Error in integration, see integration docs for instruction.", new Object[0]);
            this.b.a();
        }
        m--;
        gk.aa aaVarA = gk.a(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE);
        activity.getClass();
        aaVarA.getClass();
        a();
    }

    public final void a(Activity activity, boolean z) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.f188a = false;
        if (this.i) {
            this.j = true;
        }
        String canonicalName = activity.getClass().getCanonicalName();
        if (m == 0 || Util.getCurrentContext() == null || (canonicalName != null && !Intrinsics.areEqual(canonicalName, Util.getCurrentContext().getClass().getCanonicalName()))) {
            Util.setCurrentContext(activity);
            if (!z) {
                m++;
            }
            aa aaVar = this.g;
            if (aaVar != null && this.f == 0) {
                Intrinsics.checkNotNull(aaVar);
                aaVar.a(activity);
            }
            this.f++;
            if (bi.D == null) {
                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
            }
            bi biVar = bi.D;
            Intrinsics.checkNotNull(biVar);
            if (biVar.B == null) {
                fu fuVarF = biVar.f();
                ck ckVarA = biVar.a();
                ex exVarD = biVar.d();
                Intrinsics.checkNotNull(exVarD);
                biVar.B = new ae(fuVarF, ckVarA, exVarD);
            }
            ae aeVar = biVar.B;
            Intrinsics.checkNotNull(aeVar);
            aeVar.b(activity, false);
        }
        gk.a(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE).getClass();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.l = false;
        if (this.f188a) {
            this.f188a = false;
        } else {
            a(activity, false);
        }
    }

    public final void a() {
        if (m == 0) {
            if (ScreenshotModule.INSTANCE.getInstance().getOcclusionRepository().shouldOcclude(this.e.e())) {
                ScreenshotModule.INSTANCE.getInstance().getScreenshotStateHolder().setOccludeScreenAndWaitingToStop(true);
            }
            Future<?> future = this.h;
            if (future != null) {
                Intrinsics.checkNotNull(future);
                future.cancel(true);
            }
            this.i = true;
            this.h = Executors.newSingleThreadExecutor().submit(new Runnable() { // from class: com.uxcam.internals.hc$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    hc.a(this.f$0);
                }
            });
        }
    }

    public static final void a(hc this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            ScreenshotModule.INSTANCE.getInstance().getScreenshotStateHolder().setWaitingToStop(true);
            Thread.sleep(ga.f167a);
            this$0.i = false;
            ScreenshotModule.INSTANCE.getInstance().getScreenshotStateHolder().setWaitingToStop(false);
            if (ff.j > 0 && !this$0.j) {
                this$0.c.c(true);
                Thread.sleep(ff.j);
                ff.j = 0L;
                this$0.c.c(false);
            }
            ScreenshotModule.INSTANCE.getInstance().getScreenshotStateHolder().setOccludeScreenAndWaitingToStop(false);
            if (m == 0 && this$0.k) {
                this$0.b.a();
            } else if (!this$0.k) {
                this$0.l = true;
            }
        } catch (InterruptedException unused) {
            gk.a("UXCam").getClass();
        } finally {
            this$0.j = false;
        }
    }
}
