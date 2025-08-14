package com.google.android.gms.internal.mlkit_vision_barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public final class zzoo implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        int i2 = 0;
        String strCreateString = null;
        String strCreateString2 = null;
        byte[] bArrCreateByteArray = null;
        Point[] pointArr = null;
        zzog zzogVar = null;
        zzoj zzojVar = null;
        zzok zzokVar = null;
        zzom zzomVar = null;
        zzol zzolVar = null;
        zzoh zzohVar = null;
        zzod zzodVar = null;
        zzoe zzoeVar = null;
        zzof zzofVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 3:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    bArrCreateByteArray = SafeParcelReader.createByteArray(parcel, header);
                    break;
                case 5:
                    pointArr = (Point[]) SafeParcelReader.createTypedArray(parcel, header, Point.CREATOR);
                    break;
                case 6:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 7:
                    zzogVar = (zzog) SafeParcelReader.createParcelable(parcel, header, zzog.CREATOR);
                    break;
                case 8:
                    zzojVar = (zzoj) SafeParcelReader.createParcelable(parcel, header, zzoj.CREATOR);
                    break;
                case 9:
                    zzokVar = (zzok) SafeParcelReader.createParcelable(parcel, header, zzok.CREATOR);
                    break;
                case 10:
                    zzomVar = (zzom) SafeParcelReader.createParcelable(parcel, header, zzom.CREATOR);
                    break;
                case 11:
                    zzolVar = (zzol) SafeParcelReader.createParcelable(parcel, header, zzol.CREATOR);
                    break;
                case 12:
                    zzohVar = (zzoh) SafeParcelReader.createParcelable(parcel, header, zzoh.CREATOR);
                    break;
                case 13:
                    zzodVar = (zzod) SafeParcelReader.createParcelable(parcel, header, zzod.CREATOR);
                    break;
                case 14:
                    zzoeVar = (zzoe) SafeParcelReader.createParcelable(parcel, header, zzoe.CREATOR);
                    break;
                case 15:
                    zzofVar = (zzof) SafeParcelReader.createParcelable(parcel, header, zzof.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzon(i, strCreateString, strCreateString2, bArrCreateByteArray, pointArr, i2, zzogVar, zzojVar, zzokVar, zzomVar, zzolVar, zzohVar, zzodVar, zzoeVar, zzofVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzon[i];
    }
}
