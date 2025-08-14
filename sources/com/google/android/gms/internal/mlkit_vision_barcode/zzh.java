package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public final class zzh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzh> CREATOR = new zzu();
    public zzl zza;
    public String zzb;
    public String zzc;
    public zzm[] zzd;
    public zzj[] zze;
    public String[] zzf;
    public zze[] zzg;

    public zzh() {
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zza, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zzd, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 6, this.zze, i, false);
        SafeParcelWriter.writeStringArray(parcel, 7, this.zzf, false);
        SafeParcelWriter.writeTypedArray(parcel, 8, this.zzg, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zzh(zzl zzlVar, String str, String str2, zzm[] zzmVarArr, zzj[] zzjVarArr, String[] strArr, zze[] zzeVarArr) {
        this.zza = zzlVar;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = zzmVarArr;
        this.zze = zzjVarArr;
        this.zzf = strArr;
        this.zzg = zzeVarArr;
    }
}
