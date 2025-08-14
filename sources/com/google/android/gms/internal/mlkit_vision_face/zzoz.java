package com.google.android.gms.internal.mlkit_vision_face;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzoz extends zza implements zzpb {
    zzoz(IBinder iBinder) {
        super(iBinder, "com.google.mlkit.vision.face.aidls.IFaceDetectorCreator");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face.zzpb
    public final zzoy zzd(IObjectWrapper iObjectWrapper, zzou zzouVar) throws RemoteException {
        zzoy zzoyVar;
        Parcel parcelZza = zza();
        zzc.zzb(parcelZza, iObjectWrapper);
        zzc.zza(parcelZza, zzouVar);
        Parcel parcelZzb = zzb(1, parcelZza);
        IBinder strongBinder = parcelZzb.readStrongBinder();
        if (strongBinder == null) {
            zzoyVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.mlkit.vision.face.aidls.IFaceDetector");
            zzoyVar = iInterfaceQueryLocalInterface instanceof zzoy ? (zzoy) iInterfaceQueryLocalInterface : new zzoy(strongBinder);
        }
        parcelZzb.recycle();
        return zzoyVar;
    }
}
