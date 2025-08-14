package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public final class zzu implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzl zzlVar = null;
        String strCreateString = null;
        String strCreateString2 = null;
        zzm[] zzmVarArr = null;
        zzj[] zzjVarArr = null;
        String[] strArrCreateStringArray = null;
        zze[] zzeVarArr = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 2:
                    zzlVar = (zzl) SafeParcelReader.createParcelable(parcel, header, zzl.CREATOR);
                    break;
                case 3:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 5:
                    zzmVarArr = (zzm[]) SafeParcelReader.createTypedArray(parcel, header, zzm.CREATOR);
                    break;
                case 6:
                    zzjVarArr = (zzj[]) SafeParcelReader.createTypedArray(parcel, header, zzj.CREATOR);
                    break;
                case 7:
                    strArrCreateStringArray = SafeParcelReader.createStringArray(parcel, header);
                    break;
                case 8:
                    zzeVarArr = (zze[]) SafeParcelReader.createTypedArray(parcel, header, zze.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzh(zzlVar, strCreateString, strCreateString2, zzmVarArr, zzjVarArr, strArrCreateStringArray, zzeVarArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzh[i];
    }
}
