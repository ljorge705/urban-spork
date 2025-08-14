package com.onfido.android.sdk.capture.validation;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/DutchIDMRZ;", "", "line1", "", "line2", "line3", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getLine1", "()Ljava/lang/String;", "getLine2", "getLine3", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class DutchIDMRZ {
    private final String line1;
    private final String line2;
    private final String line3;

    public DutchIDMRZ(String line1, String line2, String line3) {
        Intrinsics.checkNotNullParameter(line1, "line1");
        Intrinsics.checkNotNullParameter(line2, "line2");
        Intrinsics.checkNotNullParameter(line3, "line3");
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
    }

    public static /* synthetic */ DutchIDMRZ copy$default(DutchIDMRZ dutchIDMRZ, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = dutchIDMRZ.line1;
        }
        if ((i & 2) != 0) {
            str2 = dutchIDMRZ.line2;
        }
        if ((i & 4) != 0) {
            str3 = dutchIDMRZ.line3;
        }
        return dutchIDMRZ.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getLine1() {
        return this.line1;
    }

    /* renamed from: component2, reason: from getter */
    public final String getLine2() {
        return this.line2;
    }

    /* renamed from: component3, reason: from getter */
    public final String getLine3() {
        return this.line3;
    }

    public final DutchIDMRZ copy(String line1, String line2, String line3) {
        Intrinsics.checkNotNullParameter(line1, "line1");
        Intrinsics.checkNotNullParameter(line2, "line2");
        Intrinsics.checkNotNullParameter(line3, "line3");
        return new DutchIDMRZ(line1, line2, line3);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DutchIDMRZ)) {
            return false;
        }
        DutchIDMRZ dutchIDMRZ = (DutchIDMRZ) other;
        return Intrinsics.areEqual(this.line1, dutchIDMRZ.line1) && Intrinsics.areEqual(this.line2, dutchIDMRZ.line2) && Intrinsics.areEqual(this.line3, dutchIDMRZ.line3);
    }

    public final String getLine1() {
        return this.line1;
    }

    public final String getLine2() {
        return this.line2;
    }

    public final String getLine3() {
        return this.line3;
    }

    public int hashCode() {
        return (((this.line1.hashCode() * 31) + this.line2.hashCode()) * 31) + this.line3.hashCode();
    }

    public String toString() {
        return "DutchIDMRZ(line1=" + this.line1 + ", line2=" + this.line2 + ", line3=" + this.line3 + ')';
    }
}
