package com.google.mlkit.vision.face.internal;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.internal.mlkit_vision_face.zzoc;
import com.google.android.gms.internal.mlkit_vision_face.zzon;
import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.face.FaceDetectorOptions;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes2.dex */
public final class zze extends LazyInstanceMap {
    private final MlKitContext zza;

    public zze(MlKitContext mlKitContext) {
        this.zza = mlKitContext;
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object create(Object obj) {
        FaceDetectorOptions faceDetectorOptions = (FaceDetectorOptions) obj;
        Context applicationContext = this.zza.getApplicationContext();
        zzoc zzocVarZzb = zzon.zzb(zzj.zzb());
        return new zzh(zzon.zzb(zzj.zzb()), faceDetectorOptions, (zza.zzc(applicationContext) || GoogleApiAvailabilityLight.getInstance().getApkVersion(applicationContext) >= 204500000) ? new zza(applicationContext, faceDetectorOptions, zzocVarZzb) : new zzm(applicationContext, faceDetectorOptions, zzocVarZzb));
    }
}
