package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
public abstract class zzbg extends com.google.android.gms.internal.maps.zzb implements zzbh {
    public zzbg() {
        super("com.google.android.gms.maps.internal.IOnPolygonClickListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        com.google.android.gms.internal.maps.zzag zzagVarZzb = com.google.android.gms.internal.maps.zzaf.zzb(parcel.readStrongBinder());
        com.google.android.gms.internal.maps.zzc.zzc(parcel);
        zzb(zzagVarZzb);
        parcel2.writeNoException();
        return true;
    }
}
