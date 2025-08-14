package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public final class zzaf extends zza implements IInterface {
    zzaf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
    }

    public final void zzd() throws RemoteException {
        zzc(3, zza());
    }

    public final zzq[] zze(IObjectWrapper iObjectWrapper, zzaj zzajVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzb(parcelZza, iObjectWrapper);
        zzc.zza(parcelZza, zzajVar);
        Parcel parcelZzb = zzb(1, parcelZza);
        zzq[] zzqVarArr = (zzq[]) parcelZzb.createTypedArray(zzq.CREATOR);
        parcelZzb.recycle();
        return zzqVarArr;
    }

    public final zzq[] zzf(IObjectWrapper iObjectWrapper, zzaj zzajVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzb(parcelZza, iObjectWrapper);
        zzc.zza(parcelZza, zzajVar);
        Parcel parcelZzb = zzb(2, parcelZza);
        zzq[] zzqVarArr = (zzq[]) parcelZzb.createTypedArray(zzq.CREATOR);
        parcelZzb.recycle();
        return zzqVarArr;
    }
}
