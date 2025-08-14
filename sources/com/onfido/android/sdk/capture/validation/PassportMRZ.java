package com.onfido.android.sdk.capture.validation;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/PassportMRZ;", "", "line1", "", "line2", "(Ljava/lang/String;Ljava/lang/String;)V", "getLine1", "()Ljava/lang/String;", "getLine2", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class PassportMRZ {
    private final String line1;
    private final String line2;

    public PassportMRZ(String line1, String line2) {
        Intrinsics.checkNotNullParameter(line1, "line1");
        Intrinsics.checkNotNullParameter(line2, "line2");
        this.line1 = line1;
        this.line2 = line2;
    }

    public static /* synthetic */ PassportMRZ copy$default(PassportMRZ passportMRZ, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = passportMRZ.line1;
        }
        if ((i & 2) != 0) {
            str2 = passportMRZ.line2;
        }
        return passportMRZ.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getLine1() {
        return this.line1;
    }

    /* renamed from: component2, reason: from getter */
    public final String getLine2() {
        return this.line2;
    }

    public final PassportMRZ copy(String line1, String line2) {
        Intrinsics.checkNotNullParameter(line1, "line1");
        Intrinsics.checkNotNullParameter(line2, "line2");
        return new PassportMRZ(line1, line2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PassportMRZ)) {
            return false;
        }
        PassportMRZ passportMRZ = (PassportMRZ) other;
        return Intrinsics.areEqual(this.line1, passportMRZ.line1) && Intrinsics.areEqual(this.line2, passportMRZ.line2);
    }

    public final String getLine1() {
        return this.line1;
    }

    public final String getLine2() {
        return this.line2;
    }

    public int hashCode() {
        return (this.line1.hashCode() * 31) + this.line2.hashCode();
    }

    public String toString() {
        return "PassportMRZ(line1=" + this.line1 + ", line2=" + this.line2 + ')';
    }
}
