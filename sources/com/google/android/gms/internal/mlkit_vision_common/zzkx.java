package com.google.android.gms.internal.mlkit_vision_common;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.mlkit:vision-common@@17.2.0 */
/* loaded from: classes3.dex */
public final class zzkx {
    private static zzp zza;
    private static final zzr zzb = zzr.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
    private final String zzc;
    private final String zzd;
    private final zzkw zze;
    private final SharedPrefManager zzf;
    private final Task zzg;
    private final Task zzh;
    private final String zzi;
    private final int zzj;
    private final Map zzk = new HashMap();
    private final Map zzl = new HashMap();

    public zzkx(Context context, final SharedPrefManager sharedPrefManager, zzkw zzkwVar, String str) {
        this.zzc = context.getPackageName();
        this.zzd = CommonUtils.getAppVersion(context);
        this.zzf = sharedPrefManager;
        this.zze = zzkwVar;
        zzlk.zza();
        this.zzi = str;
        this.zzg = MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzkt
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zza.zza();
            }
        });
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.zzh = mLTaskExecutor.scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzku
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return sharedPrefManager.getMlSdkInstanceId();
            }
        });
        zzr zzrVar = zzb;
        this.zzj = zzrVar.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzrVar.get(str)) : -1;
    }

    private static synchronized zzp zzd() {
        zzp zzpVar = zza;
        if (zzpVar != null) {
            return zzpVar;
        }
        LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
        zzm zzmVar = new zzm();
        for (int i = 0; i < locales.size(); i++) {
            zzmVar.zzb(CommonUtils.languageTagFromLocale(locales.get(i)));
        }
        zzp zzpVarZzc = zzmVar.zzc();
        zza = zzpVarZzc;
        return zzpVarZzc;
    }

    final /* synthetic */ String zza() throws Exception {
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    final /* synthetic */ void zzb(zzkp zzkpVar, zzhs zzhsVar, String str) {
        zzkpVar.zza(zzhsVar);
        String strZzc = zzkpVar.zzc();
        zzjo zzjoVar = new zzjo();
        zzjoVar.zzb(this.zzc);
        zzjoVar.zzc(this.zzd);
        zzjoVar.zzh(zzd());
        zzjoVar.zzg(true);
        zzjoVar.zzl(strZzc);
        zzjoVar.zzj(str);
        zzjoVar.zzi(this.zzh.isSuccessful() ? (String) this.zzh.getResult() : this.zzf.getMlSdkInstanceId());
        zzjoVar.zzd(10);
        zzjoVar.zzk(Integer.valueOf(this.zzj));
        zzkpVar.zzb(zzjoVar);
        this.zze.zza(zzkpVar);
    }

    public final void zzc(zzlh zzlhVar, final zzhs zzhsVar) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (this.zzk.get(zzhsVar) != null && jElapsedRealtime - ((Long) this.zzk.get(zzhsVar)).longValue() <= TimeUnit.SECONDS.toMillis(30L)) {
            return;
        }
        this.zzk.put(zzhsVar, Long.valueOf(jElapsedRealtime));
        int i = zzlhVar.zza;
        int i2 = zzlhVar.zzb;
        int i3 = zzlhVar.zzc;
        int i4 = zzlhVar.zzd;
        int i5 = zzlhVar.zze;
        long j = zzlhVar.zzf;
        int i6 = zzlhVar.zzg;
        zzhk zzhkVar = new zzhk();
        zzhkVar.zzd(i != -1 ? i != 35 ? i != 842094169 ? i != 16 ? i != 17 ? zzhg.UNKNOWN_FORMAT : zzhg.NV21 : zzhg.NV16 : zzhg.YV12 : zzhg.YUV_420_888 : zzhg.BITMAP);
        zzhkVar.zzf(i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? zzhl.ANDROID_MEDIA_IMAGE : zzhl.FILEPATH : zzhl.BYTEBUFFER : zzhl.BYTEARRAY : zzhl.BITMAP);
        zzhkVar.zzc(Integer.valueOf(i3));
        zzhkVar.zze(Integer.valueOf(i4));
        zzhkVar.zzg(Integer.valueOf(i5));
        zzhkVar.zzb(Long.valueOf(j));
        zzhkVar.zzh(Integer.valueOf(i6));
        zzhn zzhnVarZzj = zzhkVar.zzj();
        zzht zzhtVar = new zzht();
        zzhtVar.zzd(zzhnVarZzj);
        final zzkp zzkpVarZze = zzky.zze(zzhtVar);
        final String version = this.zzg.isSuccessful() ? (String) this.zzg.getResult() : LibraryVersion.getInstance().getVersion(this.zzi);
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzkv
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzb(zzkpVarZze, zzhsVar, version);
            }
        });
    }
}
