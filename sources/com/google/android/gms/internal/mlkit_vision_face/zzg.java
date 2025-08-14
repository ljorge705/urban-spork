package com.google.android.gms.internal.mlkit_vision_face;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
public final class zzg implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        int i2 = 0;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = Float.MAX_VALUE;
        float f9 = Float.MAX_VALUE;
        float f10 = Float.MAX_VALUE;
        zzn[] zznVarArr = null;
        zzd[] zzdVarArr = null;
        float f11 = -1.0f;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 2:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 3:
                    f = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 4:
                    f2 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 5:
                    f3 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 6:
                    f4 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 7:
                    f8 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 8:
                    f9 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 9:
                    zznVarArr = (zzn[]) SafeParcelReader.createTypedArray(parcel, header, zzn.CREATOR);
                    break;
                case 10:
                    f5 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 11:
                    f6 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 12:
                    f7 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 13:
                    zzdVarArr = (zzd[]) SafeParcelReader.createTypedArray(parcel, header, zzd.CREATOR);
                    break;
                case 14:
                    f10 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 15:
                    f11 = SafeParcelReader.readFloat(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzf(i, i2, f, f2, f3, f4, f8, f9, f10, zznVarArr, f5, f6, f7, zzdVarArr, f11);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzf[i];
    }
}
