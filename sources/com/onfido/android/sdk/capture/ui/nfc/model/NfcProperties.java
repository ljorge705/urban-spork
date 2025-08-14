package com.onfido.android.sdk.capture.ui.nfc.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 ,2\u00020\u0001:\u0001,B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000fJ\t\u0010\u001c\u001a\u00020\fHÖ\u0001J\u0013\u0010\u001d\u001a\u00020\u00032\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0096\u0002J\r\u0010 \u001a\u00020\u0005H\u0000¢\u0006\u0002\b!J\r\u0010\"\u001a\u00020\u0005H\u0000¢\u0006\u0002\b#J\r\u0010$\u001a\u00020\u0005H\u0000¢\u0006\u0002\b%J\b\u0010&\u001a\u00020\fH\u0016J\u0019\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\fHÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015¨\u0006-"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "Landroid/os/Parcelable;", "isNfcSupported", "", "nfcKey", "", "aaChallenge", "", "can", "hasCan", "hasPin", "canLength", "", "pinLength", "error", "(ZLjava/lang/String;[BLjava/lang/String;ZZIILjava/lang/String;)V", "getAaChallenge", "()[B", "getCan", "()Ljava/lang/String;", "getCanLength", "()I", "getError", "getHasCan", "()Z", "getHasPin", "getNfcKey", "getPinLength", "describeContents", "equals", "other", "", "getDateOfBirth", "getDateOfBirth$onfido_capture_sdk_core_release", "getExpireDate", "getExpireDate$onfido_capture_sdk_core_release", "getNumber", "getNumber$onfido_capture_sdk_core_release", "hashCode", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcProperties implements Parcelable {
    private static final int CHECK_DIGIT_LENGTH = 1;
    private static final int CHECK_DIGIT_OFFSET = 3;
    private static final int DATE_LENGTH = 6;
    private final byte[] aaChallenge;
    private final String can;
    private final int canLength;
    private final String error;
    private final boolean hasCan;
    private final boolean hasPin;
    private final boolean isNfcSupported;
    private final String nfcKey;
    private final int pinLength;
    private static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<NfcProperties> CREATOR = new Creator();

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties$Companion;", "", "()V", "CHECK_DIGIT_LENGTH", "", "CHECK_DIGIT_OFFSET", "DATE_LENGTH", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<NfcProperties> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NfcProperties createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new NfcProperties(parcel.readInt() != 0, parcel.readString(), parcel.createByteArray(), parcel.readString(), parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt(), parcel.readInt(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final NfcProperties[] newArray(int i) {
            return new NfcProperties[i];
        }
    }

    public NfcProperties(boolean z, String nfcKey, byte[] aaChallenge, String str, boolean z2, boolean z3, int i, int i2, String str2) {
        Intrinsics.checkNotNullParameter(nfcKey, "nfcKey");
        Intrinsics.checkNotNullParameter(aaChallenge, "aaChallenge");
        this.isNfcSupported = z;
        this.nfcKey = nfcKey;
        this.aaChallenge = aaChallenge;
        this.can = str;
        this.hasCan = z2;
        this.hasPin = z3;
        this.canLength = i;
        this.pinLength = i2;
        this.error = str2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(NfcProperties.class, other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties");
        NfcProperties nfcProperties = (NfcProperties) other;
        return this.isNfcSupported == nfcProperties.isNfcSupported && Intrinsics.areEqual(this.nfcKey, nfcProperties.nfcKey) && Arrays.equals(this.aaChallenge, nfcProperties.aaChallenge) && Intrinsics.areEqual(this.can, nfcProperties.can) && this.hasCan == nfcProperties.hasCan && this.hasPin == nfcProperties.hasPin && this.canLength == nfcProperties.canLength && this.pinLength == nfcProperties.pinLength;
    }

    public final byte[] getAaChallenge() {
        return this.aaChallenge;
    }

    public final String getCan() {
        return this.can;
    }

    public final int getCanLength() {
        return this.canLength;
    }

    public final String getDateOfBirth$onfido_capture_sdk_core_release() {
        if (this.nfcKey.length() < 15) {
            return "";
        }
        String strSubstring = this.nfcKey.substring(r0.length() - 14, this.nfcKey.length() - 8);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public final String getError() {
        return this.error;
    }

    public final String getExpireDate$onfido_capture_sdk_core_release() {
        if (this.nfcKey.length() < 15) {
            return "";
        }
        String strSubstring = this.nfcKey.substring(r0.length() - 7, this.nfcKey.length() - 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public final boolean getHasCan() {
        return this.hasCan;
    }

    public final boolean getHasPin() {
        return this.hasPin;
    }

    public final String getNfcKey() {
        return this.nfcKey;
    }

    public final String getNumber$onfido_capture_sdk_core_release() {
        if (this.nfcKey.length() < 15) {
            return "";
        }
        String str = this.nfcKey;
        String strSubstring = str.substring(0, str.length() - 15);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    public final int getPinLength() {
        return this.pinLength;
    }

    public int hashCode() {
        int iHashCode = ((((Boolean.hashCode(this.isNfcSupported) * 31) + this.nfcKey.hashCode()) * 31) + Arrays.hashCode(this.aaChallenge)) * 31;
        String str = this.can;
        return ((((((((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + Boolean.hashCode(this.hasCan)) * 31) + Boolean.hashCode(this.hasPin)) * 31) + Integer.hashCode(this.canLength)) * 31) + Integer.hashCode(this.pinLength);
    }

    /* renamed from: isNfcSupported, reason: from getter */
    public final boolean getIsNfcSupported() {
        return this.isNfcSupported;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeInt(this.isNfcSupported ? 1 : 0);
        parcel.writeString(this.nfcKey);
        parcel.writeByteArray(this.aaChallenge);
        parcel.writeString(this.can);
        parcel.writeInt(this.hasCan ? 1 : 0);
        parcel.writeInt(this.hasPin ? 1 : 0);
        parcel.writeInt(this.canLength);
        parcel.writeInt(this.pinLength);
        parcel.writeString(this.error);
    }

    public /* synthetic */ NfcProperties(boolean z, String str, byte[] bArr, String str2, boolean z2, boolean z3, int i, int i2, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, str, bArr, str2, (i3 & 16) != 0 ? false : z2, (i3 & 32) != 0 ? false : z3, (i3 & 64) != 0 ? 0 : i, (i3 & 128) != 0 ? 0 : i2, (i3 & 256) != 0 ? null : str3);
    }
}
