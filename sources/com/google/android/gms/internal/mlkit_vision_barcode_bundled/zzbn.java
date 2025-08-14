package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public abstract class zzbn extends zzb implements zzbo {
    public zzbn() {
        super("com.google.mlkit.vision.barcode.aidls.IBarcodeScannerCreator");
    }

    public static zzbo asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.mlkit.vision.barcode.aidls.IBarcodeScannerCreator");
        return iInterfaceQueryLocalInterface instanceof zzbo ? (zzbo) iInterfaceQueryLocalInterface : new zzbm(iBinder);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zzbl zzblVarNewBarcodeScanner = newBarcodeScanner(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzbc) zzc.zza(parcel, zzbc.CREATOR));
        parcel2.writeNoException();
        zzc.zzb(parcel2, zzblVarNewBarcodeScanner);
        return true;
    }
}
