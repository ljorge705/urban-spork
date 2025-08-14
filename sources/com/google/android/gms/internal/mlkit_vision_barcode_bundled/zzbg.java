package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzbg implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzav zzavVar = null;
        String strCreateString = null;
        String strCreateString2 = null;
        zzaw[] zzawVarArr = null;
        zzat[] zzatVarArr = null;
        String[] strArrCreateStringArray = null;
        zzao[] zzaoVarArr = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    zzavVar = (zzav) SafeParcelReader.createParcelable(parcel, header, zzav.CREATOR);
                    break;
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 3:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    zzawVarArr = (zzaw[]) SafeParcelReader.createTypedArray(parcel, header, zzaw.CREATOR);
                    break;
                case 5:
                    zzatVarArr = (zzat[]) SafeParcelReader.createTypedArray(parcel, header, zzat.CREATOR);
                    break;
                case 6:
                    strArrCreateStringArray = SafeParcelReader.createStringArray(parcel, header);
                    break;
                case 7:
                    zzaoVarArr = (zzao[]) SafeParcelReader.createTypedArray(parcel, header, zzao.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzar(zzavVar, strCreateString, strCreateString2, zzawVarArr, zzatVarArr, strArrCreateStringArray, zzaoVarArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzar[i];
    }
}
