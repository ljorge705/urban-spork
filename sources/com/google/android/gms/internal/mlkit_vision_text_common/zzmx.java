package com.google.android.gms.internal.mlkit_vision_text_common;

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

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public final class zzmx {
    public static final /* synthetic */ int zza = 0;
    private static zzbm zzb;
    private static final zzbo zzc = zzbo.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
    private final String zzd;
    private final String zze;
    private final zzmw zzf;
    private final SharedPrefManager zzg;
    private final Task zzh;
    private final Task zzi;
    private final String zzj;
    private final int zzk;
    private final Map zzl = new HashMap();
    private final Map zzm = new HashMap();

    public zzmx(Context context, final SharedPrefManager sharedPrefManager, zzmw zzmwVar, final String str) {
        this.zzd = context.getPackageName();
        this.zze = CommonUtils.getAppVersion(context);
        this.zzg = sharedPrefManager;
        this.zzf = zzmwVar;
        this.zzj = str;
        this.zzh = MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzmv
            @Override // java.util.concurrent.Callable
            public final Object call() {
                String str2 = str;
                int i = zzmx.zza;
                return LibraryVersion.getInstance().getVersion(str2);
            }
        });
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.zzi = mLTaskExecutor.scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzmu
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return sharedPrefManager.getMlSdkInstanceId();
            }
        });
        zzbo zzboVar = zzc;
        this.zzk = zzboVar.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzboVar.get(str)) : -1;
    }

    static long zza(List list, double d) {
        return ((Long) list.get(Math.max(((int) Math.ceil((d / 100.0d) * list.size())) - 1, 0))).longValue();
    }

    private static synchronized zzbm zzg() {
        zzbm zzbmVar = zzb;
        if (zzbmVar != null) {
            return zzbmVar;
        }
        LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
        zzbj zzbjVar = new zzbj();
        for (int i = 0; i < locales.size(); i++) {
            zzbjVar.zzb(CommonUtils.languageTagFromLocale(locales.get(i)));
        }
        zzbm zzbmVarZzc = zzbjVar.zzc();
        zzb = zzbmVarZzc;
        return zzbmVarZzc;
    }

    private final String zzh() {
        return this.zzh.isSuccessful() ? (String) this.zzh.getResult() : LibraryVersion.getInstance().getVersion(this.zzj);
    }

    private final boolean zzi(zzka zzkaVar, long j, long j2) {
        return this.zzl.get(zzkaVar) == null || j - ((Long) this.zzl.get(zzkaVar)).longValue() > TimeUnit.SECONDS.toMillis(30L);
    }

    final /* synthetic */ void zzb(zzna zznaVar, zzka zzkaVar, String str) {
        zznaVar.zzf(zzkaVar);
        String strZzb = zznaVar.zzb();
        zzlr zzlrVar = new zzlr();
        zzlrVar.zzb(this.zzd);
        zzlrVar.zzc(this.zze);
        zzlrVar.zzh(zzg());
        zzlrVar.zzg(true);
        zzlrVar.zzl(strZzb);
        zzlrVar.zzj(str);
        zzlrVar.zzi(this.zzi.isSuccessful() ? (String) this.zzi.getResult() : this.zzg.getMlSdkInstanceId());
        zzlrVar.zzd(10);
        zzlrVar.zzk(Integer.valueOf(this.zzk));
        zznaVar.zzg(zzlrVar);
        this.zzf.zza(zznaVar);
    }

    final /* synthetic */ void zzc(zzka zzkaVar, Object obj, long j, com.google.mlkit.vision.text.internal.zzm zzmVar) {
        if (!this.zzm.containsKey(zzkaVar)) {
            this.zzm.put(zzkaVar, zzar.zzr());
        }
        zzbr zzbrVar = (zzbr) this.zzm.get(zzkaVar);
        zzbrVar.zzo(obj, Long.valueOf(j));
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (zzi(zzkaVar, jElapsedRealtime, 30L)) {
            this.zzl.put(zzkaVar, Long.valueOf(jElapsedRealtime));
            for (Object obj2 : zzbrVar.zzq()) {
                ArrayList arrayList = new ArrayList(zzbrVar.zzc(obj2));
                Collections.sort(arrayList);
                zzjg zzjgVar = new zzjg();
                Iterator it = arrayList.iterator();
                long jLongValue = 0;
                while (it.hasNext()) {
                    jLongValue += ((Long) it.next()).longValue();
                }
                zzjgVar.zza(Long.valueOf(jLongValue / arrayList.size()));
                zzjgVar.zzc(Long.valueOf(zza(arrayList, 100.0d)));
                zzjgVar.zzf(Long.valueOf(zza(arrayList, 75.0d)));
                zzjgVar.zzd(Long.valueOf(zza(arrayList, 50.0d)));
                zzjgVar.zzb(Long.valueOf(zza(arrayList, 25.0d)));
                zzjgVar.zze(Long.valueOf(zza(arrayList, 0.0d)));
                zze(zzmVar.zza(obj2, arrayList.size(), zzjgVar.zzg()), zzkaVar, zzh());
            }
            this.zzm.remove(zzkaVar);
        }
    }

    public final void zzd(zzna zznaVar, zzka zzkaVar) {
        zze(zznaVar, zzkaVar, zzh());
    }

    public final void zze(final zzna zznaVar, final zzka zzkaVar, final String str) {
        final byte[] bArr = null;
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable(zznaVar, zzkaVar, str, bArr) { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzmt
            public final /* synthetic */ zzka zzb;
            public final /* synthetic */ String zzc;
            public final /* synthetic */ zzna zzd;

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzb(this.zzd, this.zzb, this.zzc);
            }
        });
    }

    public final void zzf(com.google.mlkit.vision.text.internal.zzn zznVar, zzka zzkaVar) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (zzi(zzkaVar, jElapsedRealtime, 30L)) {
            this.zzl.put(zzkaVar, Long.valueOf(jElapsedRealtime));
            zze(zznVar.zza(), zzkaVar, zzh());
        }
    }
}
