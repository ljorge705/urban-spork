package com.uxcam.internals;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.uxcam.UXCam;
import com.uxcam.aa;
import com.uxcam.datamodel.UXConfig;
import com.uxcam.internals.gk;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.utils.Connectivity;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.model.UXCamOcclusion;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class hl implements hk {

    /* renamed from: a, reason: collision with root package name */
    public final fu f194a;
    public final Context b;
    public final hr c;
    public final ad d;
    public final ck e;
    public final ex f;

    public hl(fu sessionRepository, Application application, hr uxConfigRepository, ad activityStartTasks, ck fragmentUtils, ex screenTagManager) {
        Intrinsics.checkNotNullParameter(sessionRepository, "sessionRepository");
        Intrinsics.checkNotNullParameter(uxConfigRepository, "uxConfigRepository");
        Intrinsics.checkNotNullParameter(activityStartTasks, "activityStartTasks");
        Intrinsics.checkNotNullParameter(fragmentUtils, "fragmentUtils");
        Intrinsics.checkNotNullParameter(screenTagManager, "screenTagManager");
        this.f194a = sessionRepository;
        this.b = application;
        this.c = uxConfigRepository;
        this.d = activityStartTasks;
        this.e = fragmentUtils;
        this.f = screenTagManager;
    }

    public static void c() {
        if (com.uxcam.aa.l) {
            return;
        }
        com.uxcam.aa.l = true;
        ho hoVar = new ho();
        if (hoVar == gk.c) {
            throw new IllegalArgumentException("Cannot plant Timber into itself.");
        }
        ArrayList arrayList = gk.f176a;
        synchronized (arrayList) {
            arrayList.add(hoVar);
            gk.b = (gk.ab[]) arrayList.toArray(new gk.ab[arrayList.size()]);
        }
        gk.a("UXCam").getClass();
    }

    public final void a(boolean z) {
        boolean z2 = this.f194a.j() != 3;
        UXConfig.MultiSessionRecordStatus multiSessionRecordStatus = this.c.a().d;
        UXConfig.MultiSessionRecordStatus multiSessionRecordStatus2 = UXConfig.MultiSessionRecordStatus.DISABLED_BUT_NOT_STARTED;
        if (multiSessionRecordStatus == multiSessionRecordStatus2) {
            this.c.a().d = UXConfig.MultiSessionRecordStatus.DISABLED;
        }
        if (!this.f194a.h() && z2 && !this.f194a.b()) {
            Intrinsics.checkNotNullExpressionValue(multiSessionRecordStatus, "multiSessionRecordStatus");
            try {
                boolean z3 = multiSessionRecordStatus == UXConfig.MultiSessionRecordStatus.ENABLED || multiSessionRecordStatus == multiSessionRecordStatus2 || z;
                d();
                boolean zB = this.f194a.b(this.b);
                if (zB) {
                    gk.a("UXCam 3.6.13[580]").getClass();
                }
                boolean z4 = this.f194a.j() != 1;
                if (!zB && !bh.f97a && z4 && z3) {
                    a(this.b);
                    this.f194a.a(true);
                    b(this.c.a().b);
                } else if (!z3) {
                    this.f194a.b(true);
                    if (!bh.f97a) {
                        gk.c.a("UXCam 3.6.13[580] : Multi Session status FALSE", new Object[0]);
                    }
                }
            } catch (Exception e) {
                e.getMessage();
                gk.c.getClass();
                this.f194a.a(3);
                e.printStackTrace();
            }
        }
        if (this.f194a.e().f201a != null) {
            fu fuVar = this.f194a;
            Context context = this.b;
            String str = fuVar.e().f201a;
            Intrinsics.checkNotNullExpressionValue(str, "sessionRepository.getUser().id");
            fuVar.a(context, str);
        } else {
            hx hxVarE = this.f194a.e();
            hxVarE.f201a = this.f194a.a(this.b);
            this.f194a.a(hxVarE);
        }
        int i = ga.q;
        if (i == 0) {
            this.f194a.a(this.b, false);
            ga.q = -1;
        } else if (i == 1) {
            this.f194a.a(this.b, true);
            ga.q = -1;
        }
    }

    @Override // com.uxcam.internals.hk
    public final void b() {
        if (id.f206a && bh.f97a) {
            try {
                if (this.c.a().c) {
                    if (bi.D == null) {
                        bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                    }
                    bi biVar = bi.D;
                    Intrinsics.checkNotNull(biVar);
                    gs gsVarH = biVar.h();
                    Context currentApplicationContext = Util.getCurrentApplicationContext();
                    Context currentContext = Util.getCurrentContext();
                    Intrinsics.checkNotNull(currentContext, "null cannot be cast to non-null type android.app.Activity");
                    String simpleName = ((Activity) currentContext).getClass().getSimpleName();
                    Context currentContext2 = Util.getCurrentContext();
                    Intrinsics.checkNotNull(currentContext2, "null cannot be cast to non-null type android.app.Activity");
                    ((gu) gsVarH).b(currentApplicationContext, simpleName, false, (Activity) currentContext2, fp.f157n);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public final void d() {
        if (this.c.a().b == null) {
            this.c.a(Util.getAppKeyString(this.b));
        }
        if (this.f194a.j() == 2) {
            this.f194a.a(0);
        }
        gk.aa aaVarA = gk.a("UXCamStarterImpl");
        String str = this.c.a().b;
        aaVarA.getClass();
    }

    public final void b(String str) {
        SharedPreferences sharedPreferences;
        JSONObject jSONObjectOptJSONObject;
        ga.b = null;
        if (Connectivity.isConnected(this.b, true)) {
            Context context = this.b;
            Cif cif = new Cif(context);
            gk.a(Cif.c).getClass();
            ie ieVar = new ie(cif, str);
            if (context != null) {
                try {
                    sharedPreferences = context.getSharedPreferences("UXCamPreferences", 0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                sharedPreferences = null;
            }
            String string = sharedPreferences == null ? "" : sharedPreferences.getString("settings_" + str.hashCode(), null);
            if (string != null && (jSONObjectOptJSONObject = new JSONObject(string).optJSONObject("verifyLimits")) != null) {
                if (Cif.a(context, jSONObjectOptJSONObject)) {
                    return;
                }
            }
            bh.b = true;
            cif.a(str, ieVar, null, true);
            return;
        }
        new Cif(this.b).a(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(android.content.Context r7) throws java.lang.Exception {
        /*
            r6 = this;
            java.lang.String r0 = "UXCamStarterImpl"
            com.uxcam.internals.gk$aa r0 = com.uxcam.internals.gk.a(r0)
            r0.getClass()
            if (r7 == 0) goto L10
            android.content.pm.PackageManager r0 = r7.getPackageManager()
            goto L11
        L10:
            r0 = 0
        L11:
            java.lang.String r1 = "UXCam 3.6.13[580]"
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L3c
            android.content.ComponentName r4 = new android.content.ComponentName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2b
            java.lang.String r7 = r7.getPackageName()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2b
            java.lang.Class<com.uxcam.service.HttpPostService> r5 = com.uxcam.service.HttpPostService.class
            java.lang.String r5 = r5.getName()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2b
            r4.<init>(r7, r5)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2b
            r7 = 4
            r0.getServiceInfo(r4, r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2b
            goto L3c
        L2b:
            r7 = move-exception
            r7.printStackTrace()
            com.uxcam.internals.gk$aa r7 = com.uxcam.internals.gk.a(r1)
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r4 = "HttpPostService service not included in project's manifest"
            r7.a(r4, r0)
            r7 = r2
            goto L3d
        L3c:
            r7 = r3
        L3d:
            com.uxcam.internals.hr r0 = r6.c
            com.uxcam.datamodel.UXConfig r0 = r0.a()
            java.lang.String r0 = r0.b
            if (r0 == 0) goto L5a
            com.uxcam.internals.hr r0 = r6.c
            com.uxcam.datamodel.UXConfig r0 = r0.a()
            java.lang.String r0 = r0.b
            java.lang.String r4 = "uxConfigRepository.getUXConfig().appKey"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r4)
            int r0 = r0.length()
            if (r0 != 0) goto L60
        L5a:
            com.uxcam.internals.gk$aa r7 = com.uxcam.internals.gk.c
            r7.getClass()
            r7 = r2
        L60:
            java.lang.String[] r0 = com.uxcam.screenaction.utils.Util.getBasicPermissions()     // Catch: java.lang.Exception -> L6d
            boolean r0 = com.uxcam.screenaction.utils.Util.findPermission(r0, r2)     // Catch: java.lang.Exception -> L6d
            if (r0 != 0) goto L6b
            goto L79
        L6b:
            r2 = r7
            goto L79
        L6d:
            r7 = move-exception
            r7.printStackTrace()
            r7.getMessage()
            com.uxcam.internals.gk$aa r7 = com.uxcam.internals.gk.c
            r7.getClass()
        L79:
            float r7 = com.uxcam.screenaction.utils.Util.getAvailableStorageSize()
            r0 = 1120403456(0x42c80000, float:100.0)
            int r7 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r7 < 0) goto L86
            if (r2 != 0) goto L91
            return
        L86:
            com.uxcam.internals.gk$aa r7 = com.uxcam.internals.gk.a(r1)
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r1 = "Unable to start session due to lack of storage"
            r7.a(r1, r0)
        L91:
            java.lang.Exception r7 = new java.lang.Exception
            java.lang.String r0 = "Pre-Condition validation failed"
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.internals.hl.a(android.content.Context):void");
    }

    public final void a(Activity context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.f194a.g()) {
            return;
        }
        c();
        this.f194a.f();
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
        hc hcVar = new hc(false, hnVar, this.f194a, this.e, this.f);
        this.f194a.a(hcVar);
        hcVar.onActivityResumed(context);
        context.getApplication().registerActivityLifecycleCallbacks(hcVar);
    }

    @Override // com.uxcam.internals.hk
    public final void a(Activity context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.c.a(str);
        a(context);
    }

    @Override // com.uxcam.internals.hk
    public final void a(Activity context, UXConfig config) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        try {
            this.c.a(config);
            ScreenshotModule.INSTANCE.getInstance().getScreenshotStateHolder().setIsImprovedScreenCaptureEnabledForCustomer(Boolean.valueOf(config.f));
            a(context);
            Iterator<UXCamOcclusion> it = config.f71a.iterator();
            while (it.hasNext()) {
                ScreenshotModule.INSTANCE.getInstance().getOcclusionRepository().applyOcclusionFromSDK(it.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void a(Activity activity, boolean z) {
        c();
        boolean z2 = com.uxcam.aa.h;
        aa.C0190aa.b();
        gk.a("startWithKeyCalled").getClass();
        Context currentApplicationContext = Util.getCurrentApplicationContext();
        Intrinsics.checkNotNull(currentApplicationContext, "null cannot be cast to non-null type android.app.Application");
        Application application = (Application) currentApplicationContext;
        SharedPreferences sharedPreferences = application != null ? application.getSharedPreferences("UXCamPreferences", 0) : null;
        if (StringsKt.equals(this.c.a().b, sharedPreferences == null ? "" : sharedPreferences.getString("killed_app_key", null), true)) {
            gk.a("UXCam").getClass();
        } else {
            this.d.a(activity, z);
        }
    }

    @Override // com.uxcam.internals.hk
    public final void a(UXConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        try {
            UXConfig uXConfigA = this.c.a();
            uXConfigA.getClass();
            uXConfigA.b = config.b;
            uXConfigA.c = config.c;
            uXConfigA.d = config.d;
            uXConfigA.e = config.e;
            uXConfigA.f = config.f;
            uXConfigA.g = config.g;
            ScreenshotModule companion = ScreenshotModule.INSTANCE.getInstance();
            companion.getScreenshotStateHolder().setIsImprovedScreenCaptureEnabledForCustomer(Boolean.valueOf(config.f));
            a((Activity) null, false);
            Iterator<UXCamOcclusion> it = config.f71a.iterator();
            while (it.hasNext()) {
                companion.getOcclusionRepository().applyOcclusionFromSDK(it.next());
            }
            companion.getScreenshotStateHolder().setHonorFlagSecure(Boolean.valueOf(config.g));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override // com.uxcam.internals.hk
    public final void a(UXConfig config, Activity activity) {
        Intrinsics.checkNotNullParameter(config, "config");
        try {
            UXConfig uXConfigA = this.c.a();
            uXConfigA.getClass();
            uXConfigA.b = config.b;
            uXConfigA.c = config.c;
            uXConfigA.d = config.d;
            uXConfigA.e = config.e;
            uXConfigA.f = config.f;
            uXConfigA.g = config.g;
            ScreenshotModule.INSTANCE.getInstance().getScreenshotStateHolder().setIsImprovedScreenCaptureEnabledForCustomer(Boolean.valueOf(config.f));
            a(activity, false);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override // com.uxcam.internals.hk
    public final void a(Context context, UXConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        Util.setCurrentApplicationContext(context);
        a(config);
    }

    @Override // com.uxcam.internals.hk
    @Deprecated(message = "")
    public final void a(String str) {
        try {
            this.c.a(str);
            a((Activity) null, false);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Deprecated(message = "")
    public final void a(String str, Activity activity) {
        try {
            this.c.a(str);
            a(activity, false);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0028 A[Catch: NullPointerException -> 0x0032, TRY_LEAVE, TryCatch #0 {NullPointerException -> 0x0032, blocks: (B:2:0x0000, B:4:0x000a, B:6:0x001d, B:7:0x0028), top: B:13:0x0000 }] */
    @Override // com.uxcam.internals.hk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a() {
        /*
            r2 = this;
            com.uxcam.internals.hr r0 = r2.c     // Catch: java.lang.NullPointerException -> L32
            com.uxcam.datamodel.UXConfig r0 = r0.a()     // Catch: java.lang.NullPointerException -> L32
            java.lang.String r0 = r0.b     // Catch: java.lang.NullPointerException -> L32
            if (r0 == 0) goto L28
            com.uxcam.internals.hr r0 = r2.c     // Catch: java.lang.NullPointerException -> L32
            com.uxcam.datamodel.UXConfig r0 = r0.a()     // Catch: java.lang.NullPointerException -> L32
            java.lang.String r0 = r0.b     // Catch: java.lang.NullPointerException -> L32
            java.lang.String r1 = "uxConfigRepository.getUXConfig().appKey"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)     // Catch: java.lang.NullPointerException -> L32
            int r0 = r0.length()     // Catch: java.lang.NullPointerException -> L32
            if (r0 <= 0) goto L28
            com.uxcam.internals.fu r0 = r2.f194a     // Catch: java.lang.NullPointerException -> L32
            r1 = 1
            r0.b(r1)     // Catch: java.lang.NullPointerException -> L32
            r0 = 0
            r2.a(r0, r1)     // Catch: java.lang.NullPointerException -> L32
            goto L36
        L28:
            java.lang.String r0 = "UXCamStarterImpl"
            com.uxcam.internals.gk$aa r0 = com.uxcam.internals.gk.a(r0)     // Catch: java.lang.NullPointerException -> L32
            r0.getClass()     // Catch: java.lang.NullPointerException -> L32
            goto L36
        L32:
            r0 = move-exception
            r0.printStackTrace()
        L36:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.internals.hl.a():void");
    }

    @Override // com.uxcam.internals.hk
    public final void a(String str, String str2) {
        ga.c = str2;
        UXCam.startWithKey(str);
    }
}
