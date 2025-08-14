package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
public final class zzs extends zza implements zzu {
    zzs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final int zzd() throws RemoteException {
        Parcel parcelZzJ = zzJ(1, zza());
        int i = parcelZzJ.readInt();
        parcelZzJ.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final int zze() throws RemoteException {
        Parcel parcelZzJ = zzJ(2, zza());
        int i = parcelZzJ.readInt();
        parcelZzJ.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final int zzf() throws RemoteException {
        Parcel parcelZzJ = zzJ(6, zza());
        int i = parcelZzJ.readInt();
        parcelZzJ.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final List zzg() throws RemoteException {
        Parcel parcelZzJ = zzJ(3, zza());
        ArrayList<IBinder> arrayListCreateBinderArrayList = parcelZzJ.createBinderArrayList();
        parcelZzJ.recycle();
        return arrayListCreateBinderArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final boolean zzh(zzu zzuVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zze(parcelZza, zzuVar);
        Parcel parcelZzJ = zzJ(5, parcelZza);
        boolean zZzf = zzc.zzf(parcelZzJ);
        parcelZzJ.recycle();
        return zZzf;
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final boolean zzi() throws RemoteException {
        Parcel parcelZzJ = zzJ(4, zza());
        boolean zZzf = zzc.zzf(parcelZzJ);
        parcelZzJ.recycle();
        return zZzf;
    }
}
