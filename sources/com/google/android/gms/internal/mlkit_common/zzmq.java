package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import android.content.res.Resources;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes3.dex */
public final class zzmq {
    private static zzao zza;
    private static final zzar zzb = zzar.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
    private final String zzc;
    private final String zzd;
    private final zzmp zze;
    private final SharedPrefManager zzf;
    private final Task zzg;
    private final Task zzh;
    private final String zzi;
    private final int zzj;
    private final Map zzk = new HashMap();
    private final Map zzl = new HashMap();

    public zzmq(Context context, final SharedPrefManager sharedPrefManager, zzmp zzmpVar, String str) {
        this.zzc = context.getPackageName();
        this.zzd = CommonUtils.getAppVersion(context);
        this.zzf = sharedPrefManager;
        this.zze = zzmpVar;
        zzne.zza();
        this.zzi = str;
        this.zzg = MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzml
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zza.zza();
            }
        });
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.zzh = mLTaskExecutor.scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzmm
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return sharedPrefManager.getMlSdkInstanceId();
            }
        });
        zzar zzarVar = zzb;
        this.zzj = zzarVar.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzarVar.get(str)) : -1;
    }

    private static synchronized zzao zzh() {
        zzao zzaoVar = zza;
        if (zzaoVar != null) {
            return zzaoVar;
        }
        LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
        zzal zzalVar = new zzal();
        for (int i = 0; i < locales.size(); i++) {
            zzalVar.zzc(CommonUtils.languageTagFromLocale(locales.get(i)));
        }
        zzao zzaoVarZzd = zzalVar.zzd();
        zza = zzaoVarZzd;
        return zzaoVarZzd;
    }

    private final zzle zzi(String str, String str2) {
        zzle zzleVar = new zzle();
        zzleVar.zzb(this.zzc);
        zzleVar.zzc(this.zzd);
        zzleVar.zzh(zzh());
        zzleVar.zzg(true);
        zzleVar.zzl(str);
        zzleVar.zzj(str2);
        zzleVar.zzi(this.zzh.isSuccessful() ? (String) this.zzh.getResult() : this.zzf.getMlSdkInstanceId());
        zzleVar.zzd(10);
        zzleVar.zzk(Integer.valueOf(this.zzj));
        return zzleVar;
    }

    private final String zzj() {
        return this.zzg.isSuccessful() ? (String) this.zzg.getResult() : LibraryVersion.getInstance().getVersion(this.zzi);
    }

    final /* synthetic */ String zza() throws Exception {
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    final /* synthetic */ void zzb(zzmh zzmhVar, zziz zzizVar, String str) {
        zzmhVar.zza(zzizVar);
        zzmhVar.zzc(zzi(zzmhVar.zzd(), str));
        this.zze.zza(zzmhVar);
    }

    final /* synthetic */ void zzc(zzmh zzmhVar, zzms zzmsVar, RemoteModel remoteModel) {
        zzmhVar.zza(zziz.MODEL_DOWNLOAD);
        zzmhVar.zzc(zzi(zzmsVar.zze(), zzj()));
        zzmhVar.zzb(zznc.zza(remoteModel, this.zzf, zzmsVar));
        this.zze.zza(zzmhVar);
    }

    public final void zzd(final zzmh zzmhVar, final zziz zzizVar) {
        final String strZzj = zzj();
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_common.zzmn
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzb(zzmhVar, zzizVar, strZzj);
            }
        });
    }

    public final void zze(zzmh zzmhVar, RemoteModel remoteModel, boolean z, int i) {
        zzmr zzmrVarZzh = zzms.zzh();
        zzmrVarZzh.zzf(false);
        zzmrVarZzh.zzd(remoteModel.getModelType());
        zzmrVarZzh.zza(zzje.FAILED);
        zzmrVarZzh.zzb(zziy.DOWNLOAD_FAILED);
        zzmrVarZzh.zzc(i);
        zzg(zzmhVar, remoteModel, zzmrVarZzh.zzh());
    }

    public final void zzf(zzmh zzmhVar, RemoteModel remoteModel, zziy zziyVar, boolean z, ModelType modelType, zzje zzjeVar) {
        zzmr zzmrVarZzh = zzms.zzh();
        zzmrVarZzh.zzf(z);
        zzmrVarZzh.zzd(modelType);
        zzmrVarZzh.zzb(zziyVar);
        zzmrVarZzh.zza(zzjeVar);
        zzg(zzmhVar, remoteModel, zzmrVarZzh.zzh());
    }

    public final void zzg(final zzmh zzmhVar, final RemoteModel remoteModel, final zzms zzmsVar) {
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_common.zzmo
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzc(zzmhVar, zzmsVar, remoteModel);
            }
        });
    }
}
