package com.onfido.android.sdk.capture.internal.nfc.data;

import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.variables.CTVariableUtils;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jmrtd.BACKey;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0087\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u0007\u001a\u00020\u0003HÂ\u0003J\t\u0010\b\u001a\u00020\u0003HÂ\u0003J\t\u0010\t\u001a\u00020\u0003HÂ\u0003J'\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\r\u0010\u000f\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\u0011J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\r\u0010\u0014\u001a\u00020\fH\u0000¢\u0006\u0002\b\u0015J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/data/PassportBACKey;", "Ljava/io/Serializable;", CTVariableUtils.NUMBER, "", "dateOfBirth", "expireDate", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "", "getBACKey", "Lorg/jmrtd/BACKey;", "getBACKey$onfido_capture_sdk_core_release", "hashCode", "", "isValid", "isValid$onfido_capture_sdk_core_release", "toString", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class PassportBACKey implements Serializable {
    private static final int VALID_DATE_LENGTH = 6;
    private final String dateOfBirth;
    private final String expireDate;
    private final String number;

    public PassportBACKey(String number, String dateOfBirth, String expireDate) {
        Intrinsics.checkNotNullParameter(number, "number");
        Intrinsics.checkNotNullParameter(dateOfBirth, "dateOfBirth");
        Intrinsics.checkNotNullParameter(expireDate, "expireDate");
        this.number = number;
        this.dateOfBirth = dateOfBirth;
        this.expireDate = expireDate;
    }

    /* renamed from: component1, reason: from getter */
    private final String getNumber() {
        return this.number;
    }

    /* renamed from: component2, reason: from getter */
    private final String getDateOfBirth() {
        return this.dateOfBirth;
    }

    /* renamed from: component3, reason: from getter */
    private final String getExpireDate() {
        return this.expireDate;
    }

    public static /* synthetic */ PassportBACKey copy$default(PassportBACKey passportBACKey, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = passportBACKey.number;
        }
        if ((i & 2) != 0) {
            str2 = passportBACKey.dateOfBirth;
        }
        if ((i & 4) != 0) {
            str3 = passportBACKey.expireDate;
        }
        return passportBACKey.copy(str, str2, str3);
    }

    public final PassportBACKey copy(String number, String dateOfBirth, String expireDate) {
        Intrinsics.checkNotNullParameter(number, "number");
        Intrinsics.checkNotNullParameter(dateOfBirth, "dateOfBirth");
        Intrinsics.checkNotNullParameter(expireDate, "expireDate");
        return new PassportBACKey(number, dateOfBirth, expireDate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PassportBACKey)) {
            return false;
        }
        PassportBACKey passportBACKey = (PassportBACKey) other;
        return Intrinsics.areEqual(this.number, passportBACKey.number) && Intrinsics.areEqual(this.dateOfBirth, passportBACKey.dateOfBirth) && Intrinsics.areEqual(this.expireDate, passportBACKey.expireDate);
    }

    public final BACKey getBACKey$onfido_capture_sdk_core_release() {
        return new BACKey(this.number, this.dateOfBirth, this.expireDate);
    }

    public int hashCode() {
        return (((this.number.hashCode() * 31) + this.dateOfBirth.hashCode()) * 31) + this.expireDate.hashCode();
    }

    public final boolean isValid$onfido_capture_sdk_core_release() {
        return (StringsKt.isBlank(this.number) ^ true) && this.dateOfBirth.length() == 6 && this.expireDate.length() == 6;
    }

    public String toString() {
        return "PassportBACKey(number=" + this.number + ", dateOfBirth=" + this.dateOfBirth + ", expireDate=" + this.expireDate + ')';
    }
}
