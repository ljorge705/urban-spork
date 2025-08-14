package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzpz;
import com.google.android.gms.internal.measurement.zzqu;
import com.google.android.gms.internal.measurement.zzrj;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzek extends zzf {
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private String zze;
    private long zzf;
    private final long zzg;
    private List zzh;
    private String zzi;
    private int zzj;
    private String zzk;
    private String zzl;
    private String zzm;
    private long zzn;
    private String zzo;

    zzek(zzgd zzgdVar, long j) {
        super(zzgdVar);
        this.zzn = 0L;
        this.zzo = null;
        this.zzg = j;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(19:0|2|(1:4)(21:88|6|(1:10)(2:11|(1:13))|84|14|(4:16|(1:18)(1:20)|86|21)|26|(1:31)(1:30)|32|33|43|(1:45)|90|46|(1:48)(1:49)|50|(3:52|(1:54)(1:55)|56)|(3:58|(1:60)(1:61)|62)|66|(2:69|(1:71)(4:72|(3:75|(1:93)(1:94)|73)|92|78))(1:78)|(2:80|81)(2:82|83))|5|26|(2:28|31)(0)|32|33|43|(0)|90|46|(0)(0)|50|(0)|(0)|66|(0)(0)|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01db, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x01dc, code lost:
    
        r11.zzt.zzaA().zzd().zzc("Fetching Google App Id failed with exception. appId", com.google.android.gms.measurement.internal.zzet.zzn(r0), r2);
     */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0194 A[Catch: IllegalStateException -> 0x01db, TryCatch #3 {IllegalStateException -> 0x01db, blocks: (B:46:0x0172, B:50:0x018c, B:52:0x0194, B:56:0x01b2, B:55:0x01ae, B:58:0x01bc, B:60:0x01d2, B:62:0x01d7, B:61:0x01d5), top: B:90:0x0172 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01bc A[Catch: IllegalStateException -> 0x01db, TryCatch #3 {IllegalStateException -> 0x01db, blocks: (B:46:0x0172, B:50:0x018c, B:52:0x0194, B:56:0x01b2, B:55:0x01ae, B:58:0x01bc, B:60:0x01d2, B:62:0x01d7, B:61:0x01d5), top: B:90:0x0172 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x024c  */
    @Override // com.google.android.gms.measurement.internal.zzf
    @org.checkerframework.checker.nullness.qual.EnsuresNonNull({com.google.firebase.remoteconfig.RemoteConfigConstants.RequestFieldKey.APP_ID, "appStore", "appName", "gmpAppId", "gaAppId"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected final void zzd() throws android.content.res.Resources.NotFoundException, android.content.pm.PackageManager.NameNotFoundException {
        /*
            Method dump skipped, instructions count: 612
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzek.zzd():void");
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzf() {
        return true;
    }

    final int zzh() {
        zza();
        return this.zzj;
    }

    final int zzi() {
        zza();
        return this.zzc;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v48, types: [com.google.android.gms.measurement.internal.zzgw, com.google.android.gms.measurement.internal.zzlp] */
    /* JADX WARN: Type inference failed for: r9v49, types: [com.google.android.gms.measurement.internal.zzgw] */
    /* JADX WARN: Type inference failed for: r9v53 */
    /* JADX WARN: Type inference failed for: r9v58 */
    /* JADX WARN: Type inference failed for: r9v59 */
    /* JADX WARN: Type inference failed for: r9v60 */
    final zzq zzj(String str) throws IllegalAccessException, NoSuchAlgorithmException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        String str2;
        Class<?> clsLoadClass;
        Object objInvoke;
        String str3;
        long jMin;
        String str4;
        long j;
        int i;
        long j2;
        ApplicationInfo applicationInfo;
        zzg();
        String strZzl = zzl();
        String strZzm = zzm();
        zza();
        String str5 = this.zzb;
        zza();
        long j3 = this.zzc;
        zza();
        Preconditions.checkNotNull(this.zzd);
        String str6 = this.zzd;
        this.zzt.zzf().zzh();
        zza();
        zzg();
        long j4 = this.zzf;
        if (j4 == 0) {
            ?? Zzv = this.zzt.zzv();
            Context contextZzaw = this.zzt.zzaw();
            String packageName = this.zzt.zzaw().getPackageName();
            Zzv.zzg();
            Preconditions.checkNotNull(contextZzaw);
            Preconditions.checkNotEmpty(packageName);
            PackageManager packageManager = contextZzaw.getPackageManager();
            MessageDigest messageDigestZzF = zzlp.zzF();
            long j5 = -1;
            if (messageDigestZzF == null) {
                Zzv.zzt.zzaA().zzd().zza("Could not get MD5 instance");
            } else {
                if (packageManager != null) {
                    try {
                        if (Zzv.zzah(contextZzaw, packageName)) {
                            j5 = 0;
                            Zzv = Zzv;
                        } else {
                            PackageInfo packageInfo = Wrappers.packageManager(contextZzaw).getPackageInfo(Zzv.zzt.zzaw().getPackageName(), 64);
                            if (packageInfo.signatures == null || packageInfo.signatures.length <= 0) {
                                Zzv.zzt.zzaA().zzk().zza("Could not get signatures");
                                Zzv = Zzv;
                            } else {
                                long jZzp = zzlp.zzp(messageDigestZzF.digest(packageInfo.signatures[0].toByteArray()));
                                j5 = jZzp;
                                Zzv = jZzp;
                            }
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        Zzv.zzt.zzaA().zzd().zzb("Package name not found", e);
                    }
                }
                j4 = 0;
                this.zzf = j4;
            }
            j4 = j5;
            this.zzf = j4;
        }
        long j6 = j4;
        boolean zZzJ = this.zzt.zzJ();
        boolean z = !this.zzt.zzm().zzl;
        zzg();
        if (this.zzt.zzJ()) {
            zzrj.zzc();
            if (this.zzt.zzf().zzs(null, zzeg.zzac)) {
                this.zzt.zzaA().zzj().zza("Disabled IID for tests.");
            } else {
                try {
                    clsLoadClass = this.zzt.zzaw().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
                } catch (ClassNotFoundException unused) {
                }
                if (clsLoadClass != null) {
                    try {
                        objInvoke = clsLoadClass.getDeclaredMethod("getInstance", Context.class).invoke(null, this.zzt.zzaw());
                    } catch (Exception unused2) {
                        this.zzt.zzaA().zzm().zza("Failed to obtain Firebase Analytics instance");
                    }
                    if (objInvoke == null) {
                        str2 = null;
                    } else {
                        try {
                            str2 = (String) clsLoadClass.getDeclaredMethod("getFirebaseInstanceId", new Class[0]).invoke(objInvoke, new Object[0]);
                        } catch (Exception unused3) {
                            this.zzt.zzaA().zzl().zza("Failed to retrieve Firebase Instance Id");
                        }
                    }
                }
            }
            str2 = null;
        } else {
            str2 = null;
        }
        zzgd zzgdVar = this.zzt;
        long jZza = zzgdVar.zzm().zzc.zza();
        if (jZza == 0) {
            str3 = strZzl;
            jMin = zzgdVar.zzc;
        } else {
            str3 = strZzl;
            jMin = Math.min(zzgdVar.zzc, jZza);
        }
        zza();
        int i2 = this.zzj;
        boolean zZzr = this.zzt.zzf().zzr();
        zzfi zzfiVarZzm = this.zzt.zzm();
        zzfiVarZzm.zzg();
        boolean z2 = zzfiVarZzm.zza().getBoolean("deferred_analytics_collection", false);
        zza();
        String str7 = this.zzl;
        Boolean boolValueOf = this.zzt.zzf().zzk("google_analytics_default_allow_ad_personalization_signals") == null ? null : Boolean.valueOf(!r2.booleanValue());
        long j7 = this.zzg;
        List list = this.zzh;
        String strZzi = this.zzt.zzm().zzc().zzi();
        if (this.zzi == null) {
            this.zzi = this.zzt.zzv().zzC();
        }
        String str8 = this.zzi;
        zzqu.zzc();
        if (this.zzt.zzf().zzs(null, zzeg.zzan)) {
            zzg();
            if (this.zzn != 0) {
                long jCurrentTimeMillis = this.zzt.zzax().currentTimeMillis() - this.zzn;
                if (this.zzm != null && jCurrentTimeMillis > 86400000 && this.zzo == null) {
                    zzo();
                }
            }
            if (this.zzm == null) {
                zzo();
            }
            str4 = this.zzm;
        } else {
            str4 = null;
        }
        zzag zzagVarZzf = this.zzt.zzf();
        zzgd zzgdVar2 = zzagVarZzf.zzt;
        Boolean boolZzk = zzagVarZzf.zzk("google_analytics_sgtm_upload_enabled");
        boolean zBooleanValue = boolZzk == null ? false : boolZzk.booleanValue();
        zzpz.zzc();
        if (this.zzt.zzf().zzs(null, zzeg.zzaD)) {
            zzlp zzlpVarZzv = this.zzt.zzv();
            String strZzl2 = zzl();
            if (zzlpVarZzv.zzt.zzaw().getPackageManager() == null) {
                j2 = 0;
            } else {
                try {
                    i = 0;
                    try {
                        applicationInfo = Wrappers.packageManager(zzlpVarZzv.zzt.zzaw()).getApplicationInfo(strZzl2, 0);
                    } catch (PackageManager.NameNotFoundException unused4) {
                        zzlpVarZzv.zzt.zzay();
                        zzlpVarZzv.zzt.zzaA().zzi().zzb("PackageManager failed to find running app: app_id", strZzl2);
                        j2 = i;
                        j = j2;
                        return new zzq(str3, strZzm, str5, j3, str6, 79000L, j6, str, zZzJ, z, str2, 0L, jMin, i2, zZzr, z2, str7, boolValueOf, j7, list, (String) null, strZzi, str8, str4, zBooleanValue, j);
                    }
                } catch (PackageManager.NameNotFoundException unused5) {
                    i = 0;
                }
                int i3 = applicationInfo != null ? applicationInfo.targetSdkVersion : i;
                j2 = i3;
            }
            j = j2;
        } else {
            j = 0;
        }
        return new zzq(str3, strZzm, str5, j3, str6, 79000L, j6, str, zZzJ, z, str2, 0L, jMin, i2, zZzr, z2, str7, boolValueOf, j7, list, (String) null, strZzi, str8, str4, zBooleanValue, j);
    }

    final String zzk() {
        zza();
        return this.zzl;
    }

    final String zzl() {
        zza();
        Preconditions.checkNotNull(this.zza);
        return this.zza;
    }

    final String zzm() {
        zzg();
        zza();
        Preconditions.checkNotNull(this.zzk);
        return this.zzk;
    }

    final List zzn() {
        return this.zzh;
    }

    final void zzo() {
        String str;
        zzg();
        if (this.zzt.zzm().zzc().zzj(zzha.ANALYTICS_STORAGE)) {
            byte[] bArr = new byte[16];
            this.zzt.zzv().zzG().nextBytes(bArr);
            str = String.format(Locale.US, "%032x", new BigInteger(1, bArr));
        } else {
            this.zzt.zzaA().zzc().zza("Analytics Storage consent is not granted");
            str = null;
        }
        zzer zzerVarZzc = this.zzt.zzaA().zzc();
        Object[] objArr = new Object[1];
        objArr[0] = str == null ? "null" : "not null";
        zzerVarZzc.zza(String.format("Resetting session stitching token to %s", objArr));
        this.zzm = str;
        this.zzn = this.zzt.zzax().currentTimeMillis();
    }

    final boolean zzp(String str) {
        String str2 = this.zzo;
        boolean z = false;
        if (str2 != null && !str2.equals(str)) {
            z = true;
        }
        this.zzo = str;
        return z;
    }
}
