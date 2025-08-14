package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public abstract class zzbk extends zzb implements zzbl {
    public zzbk() {
        super("com.google.mlkit.vision.barcode.aidls.IBarcodeScanner");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzc();
            parcel2.writeNoException();
        } else if (i == 2) {
            zzd();
            parcel2.writeNoException();
        } else {
            if (i != 3) {
                return false;
            }
            List listZzb = zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzbu) zzc.zza(parcel, zzbu.CREATOR));
            parcel2.writeNoException();
            parcel2.writeTypedList(listZzb);
        }
        return true;
    }
}
