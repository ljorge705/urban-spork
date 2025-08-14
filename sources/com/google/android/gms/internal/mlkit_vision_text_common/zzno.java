package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public final class zzno extends zza implements zznq {
    zzno(IBinder iBinder) {
        super(iBinder, "com.google.mlkit.vision.text.aidls.ITextRecognizerCreator");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zznq
    public final zznn zzd(IObjectWrapper iObjectWrapper) throws RemoteException {
        zznn zznnVar;
        Parcel parcelZza = zza();
        zzc.zzc(parcelZza, iObjectWrapper);
        Parcel parcelZzb = zzb(1, parcelZza);
        IBinder strongBinder = parcelZzb.readStrongBinder();
        if (strongBinder == null) {
            zznnVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.mlkit.vision.text.aidls.ITextRecognizer");
            zznnVar = iInterfaceQueryLocalInterface instanceof zznn ? (zznn) iInterfaceQueryLocalInterface : new zznn(strongBinder);
        }
        parcelZzb.recycle();
        return zznnVar;
    }
}
