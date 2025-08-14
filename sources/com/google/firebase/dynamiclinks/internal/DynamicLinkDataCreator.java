package com.google.firebase.dynamiclinks.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public class DynamicLinkDataCreator implements Parcelable.Creator<DynamicLinkData> {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public DynamicLinkData createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        String strCreateString2 = null;
        Bundle bundleCreateBundle = null;
        Uri uri = null;
        int i = 0;
        long j = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 2:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 3:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 4:
                    j = SafeParcelReader.readLong(parcel, header);
                    break;
                case 5:
                    bundleCreateBundle = SafeParcelReader.createBundle(parcel, header);
                    break;
                case 6:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new DynamicLinkData(strCreateString, strCreateString2, i, j, bundleCreateBundle, uri);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public DynamicLinkData[] newArray(int i) {
        return new DynamicLinkData[i];
    }

    static void writeToParcel(DynamicLinkData dynamicLinkData, Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, dynamicLinkData.getDynamicLink(), false);
        SafeParcelWriter.writeString(parcel, 2, dynamicLinkData.getDeepLink(), false);
        SafeParcelWriter.writeInt(parcel, 3, dynamicLinkData.getMinVersion());
        SafeParcelWriter.writeLong(parcel, 4, dynamicLinkData.getClickTimestamp());
        SafeParcelWriter.writeBundle(parcel, 5, dynamicLinkData.getExtensionBundle(), false);
        SafeParcelWriter.writeParcelable(parcel, 6, dynamicLinkData.getRedirectUrl(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
