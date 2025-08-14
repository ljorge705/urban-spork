package com.google.android.gms.internal.mlkit_vision_barcode;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public final class zznm {
    public static final /* synthetic */ int zza = 0;
    private static zzcc zzb;
    private static final zzce zzc = zzce.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
    private final String zzd;
    private final String zze;
    private final zznl zzf;
    private final SharedPrefManager zzg;
    private final Task zzh;
    private final Task zzi;
    private final String zzj;
    private final int zzk;
    private final Map zzl = new HashMap();
    private final Map zzm = new HashMap();

    public zznm(Context context, final SharedPrefManager sharedPrefManager, zznl zznlVar, final String str) {
        this.zzd = context.getPackageName();
        this.zze = CommonUtils.getAppVersion(context);
        this.zzg = sharedPrefManager;
        this.zzf = zznlVar;
        this.zzj = str;
        this.zzh = MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_barcode.zznj
            @Override // java.util.concurrent.Callable
            public final Object call() {
                String str2 = str;
                int i = zznm.zza;
                return LibraryVersion.getInstance().getVersion(str2);
            }
        });
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.zzi = mLTaskExecutor.scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_barcode.zzni
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return sharedPrefManager.getMlSdkInstanceId();
            }
        });
        zzce zzceVar = zzc;
        this.zzk = zzceVar.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzceVar.get(str)) : -1;
    }

    static long zza(List list, double d) {
        return ((Long) list.get(Math.max(((int) Math.ceil((d / 100.0d) * list.size())) - 1, 0))).longValue();
    }

    private static synchronized zzcc zzg() {
        zzcc zzccVar = zzb;
        if (zzccVar != null) {
            return zzccVar;
        }
        LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
        zzbz zzbzVar = new zzbz();
        for (int i = 0; i < locales.size(); i++) {
            zzbzVar.zzd(CommonUtils.languageTagFromLocale(locales.get(i)));
        }
        zzcc zzccVarZzf = zzbzVar.zzf();
        zzb = zzccVarZzf;
        return zzccVarZzf;
    }

    private final String zzh() {
        return this.zzh.isSuccessful() ? (String) this.zzh.getResult() : LibraryVersion.getInstance().getVersion(this.zzj);
    }

    private final boolean zzi(zzkk zzkkVar, long j, long j2) {
        return this.zzl.get(zzkkVar) == null || j - ((Long) this.zzl.get(zzkkVar)).longValue() > TimeUnit.SECONDS.toMillis(30L);
    }

    public final void zzb(zznk zznkVar, zzkk zzkkVar) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (zzi(zzkkVar, jElapsedRealtime, 30L)) {
            this.zzl.put(zzkkVar, Long.valueOf(jElapsedRealtime));
            zzf(zznkVar.zza(), zzkkVar, zzh());
        }
    }

    final /* synthetic */ void zzc(zznp zznpVar, zzkk zzkkVar, String str) {
        zznpVar.zzf(zzkkVar);
        String strZzb = zznpVar.zzb();
        zzmc zzmcVar = new zzmc();
        zzmcVar.zzb(this.zzd);
        zzmcVar.zzc(this.zze);
        zzmcVar.zzh(zzg());
        zzmcVar.zzg(true);
        zzmcVar.zzl(strZzb);
        zzmcVar.zzj(str);
        zzmcVar.zzi(this.zzi.isSuccessful() ? (String) this.zzi.getResult() : this.zzg.getMlSdkInstanceId());
        zzmcVar.zzd(10);
        zzmcVar.zzk(Integer.valueOf(this.zzk));
        zznpVar.zzg(zzmcVar);
        this.zzf.zza(zznpVar);
    }

    final /* synthetic */ void zzd(zzkk zzkkVar, Object obj, long j, com.google.mlkit.vision.barcode.internal.zzg zzgVar) {
        if (!this.zzm.containsKey(zzkkVar)) {
            this.zzm.put(zzkkVar, zzbh.zzr());
        }
        zzch zzchVar = (zzch) this.zzm.get(zzkkVar);
        zzchVar.zzo(obj, Long.valueOf(j));
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (zzi(zzkkVar, jElapsedRealtime, 30L)) {
            this.zzl.put(zzkkVar, Long.valueOf(jElapsedRealtime));
            for (Object obj2 : zzchVar.zzq()) {
                ArrayList arrayList = new ArrayList(zzchVar.zzc(obj2));
                Collections.sort(arrayList);
                zzjq zzjqVar = new zzjq();
                Iterator it = arrayList.iterator();
                long jLongValue = 0;
                while (it.hasNext()) {
                    jLongValue += ((Long) it.next()).longValue();
                }
                zzjqVar.zza(Long.valueOf(jLongValue / arrayList.size()));
                zzjqVar.zzc(Long.valueOf(zza(arrayList, 100.0d)));
                zzjqVar.zzf(Long.valueOf(zza(arrayList, 75.0d)));
                zzjqVar.zzd(Long.valueOf(zza(arrayList, 50.0d)));
                zzjqVar.zzb(Long.valueOf(zza(arrayList, 25.0d)));
                zzjqVar.zze(Long.valueOf(zza(arrayList, 0.0d)));
                zzf(zzgVar.zza(obj2, arrayList.size(), zzjqVar.zzg()), zzkkVar, zzh());
            }
            this.zzm.remove(zzkkVar);
        }
    }

    public final void zze(zznp zznpVar, zzkk zzkkVar) {
        zzf(zznpVar, zzkkVar, zzh());
    }

    public final void zzf(final zznp zznpVar, final zzkk zzkkVar, final String str) {
        final byte[] bArr = null;
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable(zznpVar, zzkkVar, str, bArr) { // from class: com.google.android.gms.internal.mlkit_vision_barcode.zznh
            public final /* synthetic */ zzkk zzb;
            public final /* synthetic */ String zzc;
            public final /* synthetic */ zznp zzd;

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzc(this.zzd, this.zzb, this.zzc);
            }
        });
    }
}
