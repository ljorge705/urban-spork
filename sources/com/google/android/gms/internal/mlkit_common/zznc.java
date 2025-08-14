package com.google.android.gms.internal.mlkit_common;

import android.os.SystemClock;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes3.dex */
public final class zznc {
    private static final GmsLogger zza = new GmsLogger("RemoteModelUtils", "");

    public static zzjg zza(RemoteModel remoteModel, SharedPrefManager sharedPrefManager, zzms zzmsVar) {
        ModelType modelTypeZzb = zzmsVar.zzb();
        String modelHash = remoteModel.getModelHash();
        zzjm zzjmVar = new zzjm();
        zzjh zzjhVar = new zzjh();
        zzjhVar.zzc(remoteModel.getModelNameForBackend());
        zzjhVar.zzd(zzjj.CLOUD);
        zzjhVar.zza(zzad.zzb(modelHash));
        int iOrdinal = modelTypeZzb.ordinal();
        zzjhVar.zzb(iOrdinal != 2 ? iOrdinal != 4 ? iOrdinal != 5 ? zzji.TYPE_UNKNOWN : zzji.BASE_DIGITAL_INK : zzji.CUSTOM : zzji.BASE_TRANSLATE);
        zzjmVar.zzb(zzjhVar.zzg());
        zzjp zzjpVarZzc = zzjmVar.zzc();
        zzjd zzjdVar = new zzjd();
        zzjdVar.zzd(zzmsVar.zzc());
        zzjdVar.zzc(zzmsVar.zzd());
        zzjdVar.zzb(Long.valueOf(zzmsVar.zza()));
        zzjdVar.zzf(zzjpVarZzc);
        if (zzmsVar.zzg()) {
            long modelDownloadBeginTimeMs = sharedPrefManager.getModelDownloadBeginTimeMs(remoteModel);
            if (modelDownloadBeginTimeMs == 0) {
                zza.w("RemoteModelUtils", "Model downloaded without its beginning time recorded.");
            } else {
                long modelFirstUseTimeMs = sharedPrefManager.getModelFirstUseTimeMs(remoteModel);
                if (modelFirstUseTimeMs == 0) {
                    modelFirstUseTimeMs = SystemClock.elapsedRealtime();
                    sharedPrefManager.setModelFirstUseTimeMs(remoteModel, modelFirstUseTimeMs);
                }
                zzjdVar.zzg(Long.valueOf(modelFirstUseTimeMs - modelDownloadBeginTimeMs));
            }
        }
        if (zzmsVar.zzf()) {
            long modelDownloadBeginTimeMs2 = sharedPrefManager.getModelDownloadBeginTimeMs(remoteModel);
            if (modelDownloadBeginTimeMs2 == 0) {
                zza.w("RemoteModelUtils", "Model downloaded without its beginning time recorded.");
            } else {
                zzjdVar.zze(Long.valueOf(SystemClock.elapsedRealtime() - modelDownloadBeginTimeMs2));
            }
        }
        return zzjdVar.zzi();
    }
}
