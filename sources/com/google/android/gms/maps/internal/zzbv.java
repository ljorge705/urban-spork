package com.google.android.gms.maps.internal;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
public abstract class zzbv extends com.google.android.gms.internal.maps.zzb implements zzbw {
    public zzbv() {
        super("com.google.android.gms.maps.internal.ISnapshotReadyCallback");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            Bitmap bitmap = (Bitmap) com.google.android.gms.internal.maps.zzc.zza(parcel, Bitmap.CREATOR);
            com.google.android.gms.internal.maps.zzc.zzc(parcel);
            zzb(bitmap);
        } else {
            if (i != 2) {
                return false;
            }
            IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
            com.google.android.gms.internal.maps.zzc.zzc(parcel);
            zzc(iObjectWrapperAsInterface);
        }
        parcel2.writeNoException();
        return true;
    }
}
