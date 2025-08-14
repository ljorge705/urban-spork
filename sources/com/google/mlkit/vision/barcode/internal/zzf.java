package com.google.mlkit.vision.barcode.internal;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.internal.mlkit_vision_barcode.zznm;
import com.google.android.gms.internal.mlkit_vision_barcode.zznx;
import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes2.dex */
public final class zzf extends LazyInstanceMap {
    private final MlKitContext zza;

    public zzf(MlKitContext mlKitContext) {
        this.zza = mlKitContext;
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object create(Object obj) {
        BarcodeScannerOptions barcodeScannerOptions = (BarcodeScannerOptions) obj;
        Context applicationContext = this.zza.getApplicationContext();
        zznm zznmVarZzb = zznx.zzb(zzb.zzd());
        return new zzi(this.zza, barcodeScannerOptions, (zzl.zzd(applicationContext) || GoogleApiAvailabilityLight.getInstance().getApkVersion(applicationContext) >= 204500000) ? new zzl(applicationContext, barcodeScannerOptions, zznmVarZzb) : new zzn(applicationContext, barcodeScannerOptions, zznmVarZzb), zznmVarZzb);
    }
}
