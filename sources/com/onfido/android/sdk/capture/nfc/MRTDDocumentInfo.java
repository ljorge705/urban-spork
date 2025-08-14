package com.onfido.android.sdk.capture.nfc;

import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MRTDModels.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J;\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/onfido/android/sdk/capture/nfc/MRTDDocumentInfo;", "", "documentCode", "", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "documentNumber", "birthDate", "expiryDate", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBirthDate", "()Ljava/lang/String;", "getCountryCode", "getDocumentCode", "getDocumentNumber", "getExpiryDate", "component1", "component2", "component3", "component4", "component5", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class MRTDDocumentInfo {
    private final String birthDate;
    private final String countryCode;
    private final String documentCode;
    private final String documentNumber;
    private final String expiryDate;

    public static /* synthetic */ MRTDDocumentInfo copy$default(MRTDDocumentInfo mRTDDocumentInfo, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mRTDDocumentInfo.documentCode;
        }
        if ((i & 2) != 0) {
            str2 = mRTDDocumentInfo.countryCode;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = mRTDDocumentInfo.documentNumber;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = mRTDDocumentInfo.birthDate;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = mRTDDocumentInfo.expiryDate;
        }
        return mRTDDocumentInfo.copy(str, str6, str7, str8, str5);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDocumentCode() {
        return this.documentCode;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCountryCode() {
        return this.countryCode;
    }

    /* renamed from: component3, reason: from getter */
    public final String getDocumentNumber() {
        return this.documentNumber;
    }

    /* renamed from: component4, reason: from getter */
    public final String getBirthDate() {
        return this.birthDate;
    }

    /* renamed from: component5, reason: from getter */
    public final String getExpiryDate() {
        return this.expiryDate;
    }

    public final MRTDDocumentInfo copy(String documentCode, String countryCode, String documentNumber, String birthDate, String expiryDate) {
        Intrinsics.checkNotNullParameter(documentCode, "documentCode");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(documentNumber, "documentNumber");
        Intrinsics.checkNotNullParameter(birthDate, "birthDate");
        Intrinsics.checkNotNullParameter(expiryDate, "expiryDate");
        return new MRTDDocumentInfo(documentCode, countryCode, documentNumber, birthDate, expiryDate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MRTDDocumentInfo)) {
            return false;
        }
        MRTDDocumentInfo mRTDDocumentInfo = (MRTDDocumentInfo) other;
        return Intrinsics.areEqual(this.documentCode, mRTDDocumentInfo.documentCode) && Intrinsics.areEqual(this.countryCode, mRTDDocumentInfo.countryCode) && Intrinsics.areEqual(this.documentNumber, mRTDDocumentInfo.documentNumber) && Intrinsics.areEqual(this.birthDate, mRTDDocumentInfo.birthDate) && Intrinsics.areEqual(this.expiryDate, mRTDDocumentInfo.expiryDate);
    }

    public final String getBirthDate() {
        return this.birthDate;
    }

    public final String getCountryCode() {
        return this.countryCode;
    }

    public final String getDocumentCode() {
        return this.documentCode;
    }

    public final String getDocumentNumber() {
        return this.documentNumber;
    }

    public final String getExpiryDate() {
        return this.expiryDate;
    }

    public int hashCode() {
        return (((((((this.documentCode.hashCode() * 31) + this.countryCode.hashCode()) * 31) + this.documentNumber.hashCode()) * 31) + this.birthDate.hashCode()) * 31) + this.expiryDate.hashCode();
    }

    public String toString() {
        return "MRTDDocumentInfo(documentCode=" + this.documentCode + ", countryCode=" + this.countryCode + ", documentNumber=" + this.documentNumber + ", birthDate=" + this.birthDate + ", expiryDate=" + this.expiryDate + ")";
    }

    public MRTDDocumentInfo(String documentCode, String countryCode, String documentNumber, String birthDate, String expiryDate) {
        Intrinsics.checkNotNullParameter(documentCode, "documentCode");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(documentNumber, "documentNumber");
        Intrinsics.checkNotNullParameter(birthDate, "birthDate");
        Intrinsics.checkNotNullParameter(expiryDate, "expiryDate");
        this.documentCode = documentCode;
        this.countryCode = countryCode;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
        this.expiryDate = expiryDate;
    }
}
