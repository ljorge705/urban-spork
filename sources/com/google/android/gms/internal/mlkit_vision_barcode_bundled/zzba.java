package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzba extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzba> CREATOR = new zzbb();
    private final int zza;
    private final String zzb;
    private final String zzc;
    private final byte[] zzd;
    private final Point[] zze;
    private final int zzf;
    private final zzat zzg;
    private final zzaw zzh;
    private final zzax zzi;
    private final zzaz zzj;
    private final zzay zzk;
    private final zzau zzl;
    private final zzaq zzm;
    private final zzar zzn;
    private final zzas zzo;

    public zzba(int i, String str, String str2, byte[] bArr, Point[] pointArr, int i2, zzat zzatVar, zzaw zzawVar, zzax zzaxVar, zzaz zzazVar, zzay zzayVar, zzau zzauVar, zzaq zzaqVar, zzar zzarVar, zzas zzasVar) {
        this.zza = i;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = bArr;
        this.zze = pointArr;
        this.zzf = i2;
        this.zzg = zzatVar;
        this.zzh = zzawVar;
        this.zzi = zzaxVar;
        this.zzj = zzazVar;
        this.zzk = zzayVar;
        this.zzl = zzauVar;
        this.zzm = zzaqVar;
        this.zzn = zzarVar;
        this.zzo = zzasVar;
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
}
