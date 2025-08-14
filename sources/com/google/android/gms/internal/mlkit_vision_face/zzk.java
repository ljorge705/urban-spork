package com.google.android.gms.internal.mlkit_vision_face;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzk extends zza implements zzm {
    zzk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzm
    public final zzj zzd(IObjectWrapper iObjectWrapper, zzh zzhVar) throws RemoteException {
        zzj zzjVar;
        Parcel parcelZza = zza();
        zzc.zzb(parcelZza, iObjectWrapper);
        zzc.zza(parcelZza, zzhVar);
        Parcel parcelZzb = zzb(1, parcelZza);
        IBinder strongBinder = parcelZzb.readStrongBinder();
        if (strongBinder == null) {
            zzjVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
            zzjVar = iInterfaceQueryLocalInterface instanceof zzj ? (zzj) iInterfaceQueryLocalInterface : new zzj(strongBinder);
        }
        parcelZzb.recycle();
        return zzjVar;
    }
}
