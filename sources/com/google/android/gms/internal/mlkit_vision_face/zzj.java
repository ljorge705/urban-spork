package com.google.android.gms.internal.mlkit_vision_face;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzj extends zza implements IInterface {
    zzj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
    }

    public final void zzd() throws RemoteException {
        zzc(3, zza());
    }

    public final zzf[] zze(IObjectWrapper iObjectWrapper, zzp zzpVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzb(parcelZza, iObjectWrapper);
        zzc.zza(parcelZza, zzpVar);
        Parcel parcelZzb = zzb(1, parcelZza);
        zzf[] zzfVarArr = (zzf[]) parcelZzb.createTypedArray(zzf.CREATOR);
        parcelZzb.recycle();
        return zzfVarArr;
    }

    public final zzf[] zzf(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3, int i, int i2, int i3, int i4, int i5, int i6, zzp zzpVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzb(parcelZza, iObjectWrapper);
        zzc.zzb(parcelZza, iObjectWrapper2);
        zzc.zzb(parcelZza, iObjectWrapper3);
        parcelZza.writeInt(i);
        parcelZza.writeInt(i2);
        parcelZza.writeInt(i3);
        parcelZza.writeInt(i4);
        parcelZza.writeInt(i5);
        parcelZza.writeInt(i6);
        zzc.zza(parcelZza, zzpVar);
        Parcel parcelZzb = zzb(4, parcelZza);
        zzf[] zzfVarArr = (zzf[]) parcelZzb.createTypedArray(zzf.CREATOR);
        parcelZzb.recycle();
        return zzfVarArr;
    }
}
