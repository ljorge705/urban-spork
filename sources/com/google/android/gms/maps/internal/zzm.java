package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.CameraPosition;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
public abstract class zzm extends com.google.android.gms.internal.maps.zzb implements zzn {
    public zzm() {
        super("com.google.android.gms.maps.internal.IOnCameraChangeListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        CameraPosition cameraPosition = (CameraPosition) com.google.android.gms.internal.maps.zzc.zza(parcel, CameraPosition.CREATOR);
        com.google.android.gms.internal.maps.zzc.zzc(parcel);
        zzb(cameraPosition);
        parcel2.writeNoException();
        return true;
    }
}
