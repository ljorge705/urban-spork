package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
public final class zzv extends zza implements zzx {
    zzv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final int zzd() throws RemoteException {
        Parcel parcelZzJ = zzJ(5, zza());
        int i = parcelZzJ.readInt();
        parcelZzJ.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final String zze() throws RemoteException {
        Parcel parcelZzJ = zzJ(1, zza());
        String string = parcelZzJ.readString();
        parcelZzJ.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final String zzf() throws RemoteException {
        Parcel parcelZzJ = zzJ(2, zza());
        String string = parcelZzJ.readString();
        parcelZzJ.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final void zzg() throws RemoteException {
        zzc(3, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzx
    public final boolean zzh(zzx zzxVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zze(parcelZza, zzxVar);
        Parcel parcelZzJ = zzJ(4, parcelZza);
        boolean zZzf = zzc.zzf(parcelZzJ);
        parcelZzJ.recycle();
        return zZzf;
    }
}
