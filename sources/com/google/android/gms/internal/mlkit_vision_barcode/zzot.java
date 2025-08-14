package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public final class zzot implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzoi zzoiVar = null;
        String strCreateString = null;
        String strCreateString2 = null;
        zzoj[] zzojVarArr = null;
        zzog[] zzogVarArr = null;
        String[] strArrCreateStringArray = null;
        zzob[] zzobVarArr = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    zzoiVar = (zzoi) SafeParcelReader.createParcelable(parcel, header, zzoi.CREATOR);
                    break;
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 3:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    zzojVarArr = (zzoj[]) SafeParcelReader.createTypedArray(parcel, header, zzoj.CREATOR);
                    break;
                case 5:
                    zzogVarArr = (zzog[]) SafeParcelReader.createTypedArray(parcel, header, zzog.CREATOR);
                    break;
                case 6:
                    strArrCreateStringArray = SafeParcelReader.createStringArray(parcel, header);
                    break;
                case 7:
                    zzobVarArr = (zzob[]) SafeParcelReader.createTypedArray(parcel, header, zzob.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzoe(zzoiVar, strCreateString, strCreateString2, zzojVarArr, zzogVarArr, strArrCreateStringArray, zzobVarArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzoe[i];
    }
}
