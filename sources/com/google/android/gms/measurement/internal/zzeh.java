package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzeh extends com.google.android.gms.internal.measurement.zzbm implements zzej {
    zzeh(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override // com.google.android.gms.measurement.internal.zzej
    public final String zzd(zzq zzqVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.measurement.zzbo.zzd(parcelZza, zzqVar);
        Parcel parcelZzb = zzb(11, parcelZza);
        String string = parcelZzb.readString();
        parcelZzb.recycle();
        return string;
    }

    @Override // com.google.android.gms.measurement.internal.zzej
    public final List zze(zzq zzqVar, boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.measurement.zzbo.zzd(parcelZza, zzqVar);
        parcelZza.writeInt(z ? 1 : 0);
        Parcel parcelZzb = zzb(7, parcelZza);
        ArrayList arrayListCreateTypedArrayList = parcelZzb.createTypedArrayList(zzlk.CREATOR);
        parcelZzb.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzej
    public final List zzf(String str, String str2, zzq zzqVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        com.google.android.gms.internal.measurement.zzbo.zzd(parcelZza, zzqVar);
        Parcel parcelZzb = zzb(16, parcelZza);
        ArrayList arrayListCreateTypedArrayList = parcelZzb.createTypedArrayList(zzac.CREATOR);
        parcelZzb.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzej
    public final List zzg(String str, String str2, String str3) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(null);
        parcelZza.writeString(str2);
        parcelZza.writeString(str3);
        Parcel parcelZzb = zzb(17, parcelZza);
        ArrayList arrayListCreateTypedArrayList = parcelZzb.createTypedArrayList(zzac.CREATOR);
        parcelZzb.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzej
    public final List zzh(String str, String str2, boolean z, zzq zzqVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        int i = com.google.android.gms.internal.measurement.zzbo.zza;
        parcelZza.writeInt(z ? 1 : 0);
        com.google.android.gms.internal.measurement.zzbo.zzd(parcelZza, zzqVar);
        Parcel parcelZzb = zzb(14, parcelZza);
        ArrayList arrayListCreateTypedArrayList = parcelZzb.createTypedArrayList(zzlk.CREATOR);
        parcelZzb.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzej
    public final List zzi(String str, String str2, String str3, boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(null);
        parcelZza.writeString(str2);
        parcelZza.writeString(str3);
        int i = com.google.android.gms.internal.measurement.zzbo.zza;
        parcelZza.writeInt(z ? 1 : 0);
        Parcel parcelZzb = zzb(15, parcelZza);
        ArrayList arrayListCreateTypedArrayList = parcelZzb.createTypedArrayList(zzlk.CREATOR);
        parcelZzb.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzej
    public final void zzj(zzq zzqVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.measurement.zzbo.zzd(parcelZza, zzqVar);
        zzc(4, parcelZza);
    }

    @Override // com.google.android.gms.measurement.internal.zzej
    public final void zzk(zzau zzauVar, zzq zzqVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.measurement.zzbo.zzd(parcelZza, zzauVar);
        com.google.android.gms.internal.measurement.zzbo.zzd(parcelZza, zzqVar);
        zzc(1, parcelZza);
    }

    @Override // com.google.android.gms.measurement.internal.zzej
    public final void zzl(zzau zzauVar, String str, String str2) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.measurement.internal.zzej
    public final void zzm(zzq zzqVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.measurement.zzbo.zzd(parcelZza, zzqVar);
        zzc(18, parcelZza);
    }

    @Override // com.google.android.gms.measurement.internal.zzej
    public final void zzn(zzac zzacVar, zzq zzqVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.measurement.zzbo.zzd(parcelZza, zzacVar);
        com.google.android.gms.internal.measurement.zzbo.zzd(parcelZza, zzqVar);
        zzc(12, parcelZza);
    }

    @Override // com.google.android.gms.measurement.internal.zzej
    public final void zzo(zzac zzacVar) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.measurement.internal.zzej
    public final void zzp(zzq zzqVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.measurement.zzbo.zzd(parcelZza, zzqVar);
        zzc(20, parcelZza);
    }

    @Override // com.google.android.gms.measurement.internal.zzej
    public final void zzq(long j, String str, String str2, String str3) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeLong(j);
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        parcelZza.writeString(str3);
        zzc(10, parcelZza);
    }

    @Override // com.google.android.gms.measurement.internal.zzej
    public final void zzr(Bundle bundle, zzq zzqVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.measurement.zzbo.zzd(parcelZza, bundle);
        com.google.android.gms.internal.measurement.zzbo.zzd(parcelZza, zzqVar);
        zzc(19, parcelZza);
    }

    @Override // com.google.android.gms.measurement.internal.zzej
    public final void zzs(zzq zzqVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.measurement.zzbo.zzd(parcelZza, zzqVar);
        zzc(6, parcelZza);
    }

    @Override // com.google.android.gms.measurement.internal.zzej
    public final void zzt(zzlk zzlkVar, zzq zzqVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.measurement.zzbo.zzd(parcelZza, zzlkVar);
        com.google.android.gms.internal.measurement.zzbo.zzd(parcelZza, zzqVar);
        zzc(2, parcelZza);
    }

    @Override // com.google.android.gms.measurement.internal.zzej
    public final byte[] zzu(zzau zzauVar, String str) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.measurement.zzbo.zzd(parcelZza, zzauVar);
        parcelZza.writeString(str);
        Parcel parcelZzb = zzb(9, parcelZza);
        byte[] bArrCreateByteArray = parcelZzb.createByteArray();
        parcelZzb.recycle();
        return bArrCreateByteArray;
    }
}
