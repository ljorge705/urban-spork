package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public final class zzoe extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzoe> CREATOR = new zzot();
    private final zzoi zza;
    private final String zzb;
    private final String zzc;
    private final zzoj[] zzd;
    private final zzog[] zze;
    private final String[] zzf;
    private final zzob[] zzg;

    public zzoe(zzoi zzoiVar, String str, String str2, zzoj[] zzojVarArr, zzog[] zzogVarArr, String[] strArr, zzob[] zzobVarArr) {
        this.zza = zzoiVar;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = zzojVarArr;
        this.zze = zzogVarArr;
        this.zzf = strArr;
        this.zzg = zzobVarArr;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeTypedArray(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zze, i, false);
        SafeParcelWriter.writeStringArray(parcel, 6, this.zzf, false);
        SafeParcelWriter.writeTypedArray(parcel, 7, this.zzg, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final zzoi zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final zzob[] zzd() {
        return this.zzg;
    }

    public final zzog[] zze() {
        return this.zze;
    }

    public final zzoj[] zzf() {
        return this.zzd;
    }

    public final String[] zzg() {
        return this.zzf;
    }
}
