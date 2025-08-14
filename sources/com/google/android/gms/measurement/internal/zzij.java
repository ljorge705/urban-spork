package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
final class zzij implements Application.ActivityLifecycleCallbacks {
    final /* synthetic */ zzik zza;

    zzij(zzik zzikVar) {
        this.zza = zzikVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00bc  */
    @Override // android.app.Application.ActivityLifecycleCallbacks
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onActivityCreated(android.app.Activity r9, android.os.Bundle r10) {
        /*
            r8 = this;
            com.google.android.gms.measurement.internal.zzik r0 = r8.zza     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            com.google.android.gms.measurement.internal.zzgd r0 = r0.zzt     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzaA()     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            com.google.android.gms.measurement.internal.zzer r0 = r0.zzj()     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            java.lang.String r1 = "onActivityCreated"
            r0.zza(r1)     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            android.content.Intent r0 = r9.getIntent()     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            if (r0 != 0) goto L23
            com.google.android.gms.measurement.internal.zzik r0 = r8.zza
            com.google.android.gms.measurement.internal.zzgd r0 = r0.zzt
        L1b:
            com.google.android.gms.measurement.internal.zziz r0 = r0.zzs()
            r0.zzr(r9, r10)
            return
        L23:
            com.google.android.gms.internal.measurement.zzpb.zzc()     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            com.google.android.gms.measurement.internal.zzik r1 = r8.zza     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            com.google.android.gms.measurement.internal.zzgd r1 = r1.zzt     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            com.google.android.gms.measurement.internal.zzag r1 = r1.zzf()     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            com.google.android.gms.measurement.internal.zzef r2 = com.google.android.gms.measurement.internal.zzeg.zzaF     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            r3 = 0
            boolean r1 = r1.zzs(r3, r2)     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            if (r1 != 0) goto L3d
            android.net.Uri r3 = r0.getData()     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
        L3b:
            r4 = r3
            goto L63
        L3d:
            android.net.Uri r1 = r0.getData()     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            if (r1 == 0) goto L4c
            boolean r2 = r1.isHierarchical()     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            if (r2 != 0) goto L4a
            goto L4c
        L4a:
            r4 = r1
            goto L63
        L4c:
            android.os.Bundle r1 = r0.getExtras()     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            if (r1 == 0) goto L3b
            java.lang.String r2 = "com.android.vending.referral_url"
            java.lang.String r1 = r1.getString(r2)     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            if (r2 != 0) goto L3b
            android.net.Uri r3 = android.net.Uri.parse(r1)     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            goto L3b
        L63:
            if (r4 == 0) goto Lbc
            boolean r1 = r4.isHierarchical()     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            if (r1 != 0) goto L6c
            goto Lbc
        L6c:
            com.google.android.gms.measurement.internal.zzik r1 = r8.zza     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            com.google.android.gms.measurement.internal.zzgd r1 = r1.zzt     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            r1.zzv()     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            java.lang.String r1 = "android.intent.extra.REFERRER_NAME"
            java.lang.String r0 = r0.getStringExtra(r1)     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            java.lang.String r1 = "android-app://com.google.android.googlequicksearchbox/https/www.google.com"
            boolean r1 = r1.equals(r0)     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            if (r1 != 0) goto L95
            java.lang.String r1 = "https://www.google.com"
            boolean r1 = r1.equals(r0)     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            if (r1 != 0) goto L95
            java.lang.String r1 = "android-app://com.google.appcrawler"
            boolean r0 = r1.equals(r0)     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            if (r0 == 0) goto L92
            goto L95
        L92:
            java.lang.String r0 = "auto"
            goto L97
        L95:
            java.lang.String r0 = "gs"
        L97:
            r5 = r0
            java.lang.String r0 = "referrer"
            java.lang.String r6 = r4.getQueryParameter(r0)     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            if (r10 != 0) goto La2
            r0 = 1
            goto La3
        La2:
            r0 = 0
        La3:
            r3 = r0
            com.google.android.gms.measurement.internal.zzik r0 = r8.zza     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            com.google.android.gms.measurement.internal.zzgd r0 = r0.zzt     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            com.google.android.gms.measurement.internal.zzga r0 = r0.zzaB()     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            com.google.android.gms.measurement.internal.zzii r7 = new com.google.android.gms.measurement.internal.zzii     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            r1 = r7
            r2 = r8
            r1.<init>(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            r0.zzp(r7)     // Catch: java.lang.Throwable -> Lc2 java.lang.RuntimeException -> Lc4
            com.google.android.gms.measurement.internal.zzik r0 = r8.zza
            com.google.android.gms.measurement.internal.zzgd r0 = r0.zzt
            goto L1b
        Lbc:
            com.google.android.gms.measurement.internal.zzik r0 = r8.zza
            com.google.android.gms.measurement.internal.zzgd r0 = r0.zzt
            goto L1b
        Lc2:
            r0 = move-exception
            goto Ldc
        Lc4:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzik r1 = r8.zza     // Catch: java.lang.Throwable -> Lc2
            com.google.android.gms.measurement.internal.zzgd r1 = r1.zzt     // Catch: java.lang.Throwable -> Lc2
            com.google.android.gms.measurement.internal.zzet r1 = r1.zzaA()     // Catch: java.lang.Throwable -> Lc2
            com.google.android.gms.measurement.internal.zzer r1 = r1.zzd()     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r2 = "Throwable caught in onActivityCreated"
            r1.zzb(r2, r0)     // Catch: java.lang.Throwable -> Lc2
            com.google.android.gms.measurement.internal.zzik r0 = r8.zza
            com.google.android.gms.measurement.internal.zzgd r0 = r0.zzt
            goto L1b
        Ldc:
            com.google.android.gms.measurement.internal.zzik r1 = r8.zza
            com.google.android.gms.measurement.internal.zzgd r1 = r1.zzt
            com.google.android.gms.measurement.internal.zziz r1 = r1.zzs()
            r1.zzr(r9, r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzij.onActivityCreated(android.app.Activity, android.os.Bundle):void");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        this.zza.zzt.zzs().zzs(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) throws IllegalStateException {
        this.zza.zzt.zzs().zzt(activity);
        zzkp zzkpVarZzu = this.zza.zzt.zzu();
        zzkpVarZzu.zzt.zzaB().zzp(new zzki(zzkpVarZzu, zzkpVarZzu.zzt.zzax().elapsedRealtime()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) throws IllegalStateException {
        zzkp zzkpVarZzu = this.zza.zzt.zzu();
        zzkpVarZzu.zzt.zzaB().zzp(new zzkh(zzkpVarZzu, zzkpVarZzu.zzt.zzax().elapsedRealtime()));
        this.zza.zzt.zzs().zzu(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zza.zzt.zzs().zzv(activity, bundle);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}
