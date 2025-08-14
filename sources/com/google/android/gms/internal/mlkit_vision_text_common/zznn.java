package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public final class zznn extends zza implements IInterface {
    zznn(IBinder iBinder) {
        super(iBinder, "com.google.mlkit.vision.text.aidls.ITextRecognizer");
    }

    public final zznx zzd(IObjectWrapper iObjectWrapper, zznl zznlVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, iObjectWrapper);
        zzc.zzb(parcelZza, zznlVar);
        Parcel parcelZzb = zzb(3, parcelZza);
        zznx zznxVar = (zznx) zzc.zza(parcelZzb, zznx.CREATOR);
        parcelZzb.recycle();
        return zznxVar;
    }

    public final void zze() throws RemoteException {
        zzc(1, zza());
    }

    public final void zzf() throws RemoteException {
        zzc(2, zza());
    }
}
