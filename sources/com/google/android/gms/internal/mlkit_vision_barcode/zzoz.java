package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.IBinder;
import android.os.IInterface;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public abstract class zzoz extends zzb implements zzpa {
    public static zzpa zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.mlkit.vision.barcode.aidls.IBarcodeScannerCreator");
        return iInterfaceQueryLocalInterface instanceof zzpa ? (zzpa) iInterfaceQueryLocalInterface : new zzoy(iBinder);
    }
}
