package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzbb implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        int i2 = 0;
        String strCreateString = null;
        String strCreateString2 = null;
        byte[] bArrCreateByteArray = null;
        Point[] pointArr = null;
        zzat zzatVar = null;
        zzaw zzawVar = null;
        zzax zzaxVar = null;
        zzaz zzazVar = null;
        zzay zzayVar = null;
        zzau zzauVar = null;
        zzaq zzaqVar = null;
        zzar zzarVar = null;
        zzas zzasVar = null;
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
                    zzatVar = (zzat) SafeParcelReader.createParcelable(parcel, header, zzat.CREATOR);
                    break;
                case 8:
                    zzawVar = (zzaw) SafeParcelReader.createParcelable(parcel, header, zzaw.CREATOR);
                    break;
                case 9:
                    zzaxVar = (zzax) SafeParcelReader.createParcelable(parcel, header, zzax.CREATOR);
                    break;
                case 10:
                    zzazVar = (zzaz) SafeParcelReader.createParcelable(parcel, header, zzaz.CREATOR);
                    break;
                case 11:
                    zzayVar = (zzay) SafeParcelReader.createParcelable(parcel, header, zzay.CREATOR);
                    break;
                case 12:
                    zzauVar = (zzau) SafeParcelReader.createParcelable(parcel, header, zzau.CREATOR);
                    break;
                case 13:
                    zzaqVar = (zzaq) SafeParcelReader.createParcelable(parcel, header, zzaq.CREATOR);
                    break;
                case 14:
                    zzarVar = (zzar) SafeParcelReader.createParcelable(parcel, header, zzar.CREATOR);
                    break;
                case 15:
                    zzasVar = (zzas) SafeParcelReader.createParcelable(parcel, header, zzas.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzba(i, strCreateString, strCreateString2, bArrCreateByteArray, pointArr, i2, zzatVar, zzawVar, zzaxVar, zzazVar, zzayVar, zzauVar, zzaqVar, zzarVar, zzasVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzba[i];
    }
}
