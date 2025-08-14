package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
public final class zzak extends zza implements zzam {
    zzak(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzam
    public final float zzd() throws RemoteException {
        Parcel parcelZzJ = zzJ(13, zza());
        float f = parcelZzJ.readFloat();
        parcelZzJ.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.maps.zzam
    public final float zze() throws RemoteException {
        Parcel parcelZzJ = zzJ(5, zza());
        float f = parcelZzJ.readFloat();
        parcelZzJ.recycle();
        return f;
    }

    @Override // com.google.android.gms.internal.maps.zzam
    public final int zzf() throws RemoteException {
        Parcel parcelZzJ = zzJ(9, zza());
        int i = parcelZzJ.readInt();
        parcelZzJ.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.maps.zzam
    public final String zzg() throws RemoteException {
        Parcel parcelZzJ = zzJ(3, zza());
        String string = parcelZzJ.readString();
        parcelZzJ.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.maps.zzam
    public final void zzh() throws RemoteException {
        zzc(2, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzam
    public final void zzi() throws RemoteException {
        zzc(1, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzam
    public final void zzj(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        int i = zzc.zza;
        parcelZza.writeInt(z ? 1 : 0);
        zzc(10, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzam
    public final void zzk(float f) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f);
        zzc(12, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzam
    public final void zzl(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        int i = zzc.zza;
        parcelZza.writeInt(z ? 1 : 0);
        zzc(6, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzam
    public final void zzm(float f) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f);
        zzc(4, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzam
    public final boolean zzn(zzam zzamVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zze(parcelZza, zzamVar);
        Parcel parcelZzJ = zzJ(8, parcelZza);
        boolean zZzf = zzc.zzf(parcelZzJ);
        parcelZzJ.recycle();
        return zZzf;
    }

    @Override // com.google.android.gms.internal.maps.zzam
    public final boolean zzo() throws RemoteException {
        Parcel parcelZzJ = zzJ(11, zza());
        boolean zZzf = zzc.zzf(parcelZzJ);
        parcelZzJ.recycle();
        return zZzf;
    }

    @Override // com.google.android.gms.internal.maps.zzam
    public final boolean zzp() throws RemoteException {
        Parcel parcelZzJ = zzJ(7, zza());
        boolean zZzf = zzc.zzf(parcelZzJ);
        parcelZzJ.recycle();
        return zZzf;
    }
}
