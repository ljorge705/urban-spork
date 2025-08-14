package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
public abstract class zzak extends com.google.android.gms.internal.maps.zzb implements zzal {
    public zzak() {
        super("com.google.android.gms.maps.internal.IOnMapCapabilitiesChangedListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        com.google.android.gms.internal.maps.zzaa zzaaVarZzb = com.google.android.gms.internal.maps.zzz.zzb(parcel.readStrongBinder());
        com.google.android.gms.internal.maps.zzc.zzc(parcel);
        zzb(zzaaVarZzb);
        parcel2.writeNoException();
        return true;
    }
}
