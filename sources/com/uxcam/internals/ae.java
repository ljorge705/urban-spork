package com.uxcam.internals;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.Window;
import com.uxcam.internals.gk;
import com.uxcam.internals.hc;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* loaded from: classes6.dex */
public final class ae implements ad {

    /* renamed from: a, reason: collision with root package name */
    public final fu f76a;
    public final ck b;
    public final ex c;

    public static final class aa implements hc.aa {
        public aa() {
        }

        @Override // com.uxcam.internals.hc.aa
        public final void a(Activity activity) {
            ae.this.f76a.b(false);
            ae.this.b(activity, true);
        }
    }

    public ae(fu sessionRepository, ck fragmentUtils, ex screenTagManager) {
        Intrinsics.checkNotNullParameter(sessionRepository, "sessionRepository");
        Intrinsics.checkNotNullParameter(fragmentUtils, "fragmentUtils");
        Intrinsics.checkNotNullParameter(screenTagManager, "screenTagManager");
        this.f76a = sessionRepository;
        this.b = fragmentUtils;
        this.c = screenTagManager;
    }

    @Override // com.uxcam.internals.ad
    public final void a(Activity activity, boolean z) {
        Context currentApplicationContext = Util.getCurrentApplicationContext();
        Intrinsics.checkNotNull(currentApplicationContext, "null cannot be cast to non-null type android.app.Application");
        Application application = (Application) currentApplicationContext;
        if (!this.f76a.g()) {
            this.f76a.f();
            gk.a("UXCamStarterImpl").getClass();
            boolean z2 = activity != null;
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
            hc hcVar = new hc(z2, hnVar, this.f76a, this.b, this.c);
            this.f76a.a(hcVar);
            application.registerActivityLifecycleCallbacks(hcVar);
        }
        if (Util.getCurrentContext() != null && z && (com.uxcam.aa.k || this.f76a.b())) {
            this.f76a.b(false);
            Context currentContext = Util.getCurrentContext();
            Intrinsics.checkNotNull(currentContext, "null cannot be cast to non-null type android.app.Activity");
            activity = (Activity) currentContext;
            b(activity, true);
        }
        if (z && (com.uxcam.aa.k || this.f76a.b())) {
            hc hcVar2 = (hc) this.f76a.d();
            Intrinsics.checkNotNull(hcVar2);
            if (hcVar2.f > 0) {
                this.f76a.b(false);
                Context currentContext2 = Util.getCurrentContext();
                Intrinsics.checkNotNull(currentContext2, "null cannot be cast to non-null type android.app.Activity");
                activity = (Activity) currentContext2;
                b(activity, true);
            } else {
                hcVar2.g = new aa();
            }
        }
        if (activity != null) {
            this.f76a.b(false);
        }
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacksD = this.f76a.d();
        if (activity == null || !(activityLifecycleCallbacksD instanceof hc)) {
            return;
        }
        ((hc) activityLifecycleCallbacksD).a(activity, z);
    }

    public final void b(Activity activity, boolean z) {
        try {
            a(activity);
            if (this.f76a.a()) {
                this.f76a.c(false);
                ga.f167a = 2000;
            }
            Util.setCurrentContext(activity);
            this.f76a.a(new he());
            if (this.f76a.i() != null) {
                he.b(activity, z);
            }
            Intrinsics.checkNotNull(activity);
            Window window = activity.getWindow();
            Window.Callback callback = window.getCallback();
            if (callback == null || !StringsKt.equals(callback.getClass().getName(), hu.class.getName(), true)) {
                window.setCallback(new hu(callback, this.f76a.i()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void a(Activity activity) {
        Iterator<WeakReference<Activity>> it = this.f76a.c().iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(it.next().get(), activity)) {
                return;
            }
        }
        if (activity != null) {
            this.f76a.b(activity);
        }
        gk.aa aaVarA = gk.a("ActivityStack");
        Intrinsics.checkNotNull(activity);
        activity.getClass();
        aaVarA.getClass();
    }
}
