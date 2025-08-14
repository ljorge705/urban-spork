package com.google.android.gms.internal.mlkit_vision_barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public final class zzon extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzon> CREATOR = new zzoo();
    private final int zza;
    private final String zzb;
    private final String zzc;
    private final byte[] zzd;
    private final Point[] zze;
    private final int zzf;
    private final zzog zzg;
    private final zzoj zzh;
    private final zzok zzi;
    private final zzom zzj;
    private final zzol zzk;
    private final zzoh zzl;
    private final zzod zzm;
    private final zzoe zzn;
    private final zzof zzo;

    public zzon(int i, String str, String str2, byte[] bArr, Point[] pointArr, int i2, zzog zzogVar, zzoj zzojVar, zzok zzokVar, zzom zzomVar, zzol zzolVar, zzoh zzohVar, zzod zzodVar, zzoe zzoeVar, zzof zzofVar) {
        this.zza = i;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = bArr;
        this.zze = pointArr;
        this.zzf = i2;
        this.zzg = zzogVar;
        this.zzh = zzojVar;
        this.zzi = zzokVar;
        this.zzj = zzomVar;
        this.zzk = zzolVar;
        this.zzl = zzohVar;
        this.zzm = zzodVar;
        this.zzn = zzoeVar;
        this.zzo = zzofVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeByteArray(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zze, i, false);
        SafeParcelWriter.writeInt(parcel, 6, this.zzf);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzg, i, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzh, i, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzi, i, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzj, i, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzk, i, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzl, i, false);
        SafeParcelWriter.writeParcelable(parcel, 13, this.zzm, i, false);
        SafeParcelWriter.writeParcelable(parcel, 14, this.zzn, i, false);
        SafeParcelWriter.writeParcelable(parcel, 15, this.zzo, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final int zza() {
        return this.zza;
    }

    public final int zzb() {
        return this.zzf;
    }

    public final zzod zzc() {
        return this.zzm;
    }

    public final zzoe zzd() {
        return this.zzn;
    }

    public final zzof zze() {
        return this.zzo;
    }

    public final zzog zzf() {
        return this.zzg;
    }

    public final zzoh zzg() {
        return this.zzl;
    }

    public final zzoj zzh() {
        return this.zzh;
    }

    public final zzok zzi() {
        return this.zzi;
    }

    public final zzol zzj() {
        return this.zzk;
    }

    public final zzom zzk() {
        return this.zzj;
    }

    public final String zzl() {
        return this.zzb;
    }

    public final String zzm() {
        return this.zzc;
    }

    public final byte[] zzn() {
        return this.zzd;
    }

    public final Point[] zzo() {
        return this.zze;
    }
}
