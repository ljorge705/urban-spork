package com.google.mlkit.vision.barcode;

import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.barcode.internal.zze;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes2.dex */
public class BarcodeScanning {
    private BarcodeScanning() {
    }

    public static BarcodeScanner getClient() {
        return ((zze) MlKitContext.getInstance().get(zze.class)).zza();
    }

    public static BarcodeScanner getClient(BarcodeScannerOptions barcodeScannerOptions) {
        Preconditions.checkNotNull(barcodeScannerOptions, "You must provide a valid BarcodeScannerOptions.");
        return ((zze) MlKitContext.getInstance().get(zze.class)).zzb(barcodeScannerOptions);
    }
}
