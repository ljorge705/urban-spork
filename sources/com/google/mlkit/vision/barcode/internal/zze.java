package com.google.mlkit.vision.barcode.internal;

import com.google.android.gms.internal.mlkit_vision_barcode.zznx;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes2.dex */
public final class zze {
    private final zzf zza;
    private final ExecutorSelector zzb;

    zze(zzf zzfVar, ExecutorSelector executorSelector) {
        this.zza = zzfVar;
        this.zzb = executorSelector;
    }

    public final BarcodeScannerImpl zza() {
        return zzb(BarcodeScannerImpl.zzb);
    }

    public final BarcodeScannerImpl zzb(BarcodeScannerOptions barcodeScannerOptions) {
        return new BarcodeScannerImpl(barcodeScannerOptions, (zzi) this.zza.get(barcodeScannerOptions), this.zzb.getExecutorToUse(barcodeScannerOptions.zzb()), zznx.zzb(zzb.zzd()));
    }
}
