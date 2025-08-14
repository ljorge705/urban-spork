package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzll implements Parcelable.Creator {
    static void zza(zzlk zzlkVar, Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, zzlkVar.zza);
        SafeParcelWriter.writeString(parcel, 2, zzlkVar.zzb, false);
        SafeParcelWriter.writeLong(parcel, 3, zzlkVar.zzc);
        SafeParcelWriter.writeLongObject(parcel, 4, zzlkVar.zzd, false);
        SafeParcelWriter.writeFloatObject(parcel, 5, null, false);
        SafeParcelWriter.writeString(parcel, 6, zzlkVar.zze, false);
        SafeParcelWriter.writeString(parcel, 7, zzlkVar.zzf, false);
        SafeParcelWriter.writeDoubleObject(parcel, 8, zzlkVar.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        Long longObject = null;
        Float floatObject = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        Double doubleObject = null;
        long j = 0;
        int i = 0;
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
                    j = SafeParcelReader.readLong(parcel, header);
                    break;
                case 4:
                    longObject = SafeParcelReader.readLongObject(parcel, header);
                    break;
                case 5:
                    floatObject = SafeParcelReader.readFloatObject(parcel, header);
                    break;
                case 6:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 7:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    break;
                case 8:
                    doubleObject = SafeParcelReader.readDoubleObject(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzlk(i, strCreateString, j, longObject, floatObject, strCreateString2, strCreateString3, doubleObject);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzlk[i];
    }
}
