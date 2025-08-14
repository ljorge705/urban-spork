package com.onfido.android.sdk.capture.validation;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/MRZData;", "", "type", "Lcom/onfido/android/sdk/capture/validation/MRZDataType;", "value", "", "checkSum", "(Lcom/onfido/android/sdk/capture/validation/MRZDataType;Ljava/lang/String;Ljava/lang/String;)V", "getCheckSum", "()Ljava/lang/String;", "getType", "()Lcom/onfido/android/sdk/capture/validation/MRZDataType;", "getValue", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class MRZData {
    private final String checkSum;
    private final MRZDataType type;
    private final String value;

    public MRZData(MRZDataType type, String value, String checkSum) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(checkSum, "checkSum");
        this.type = type;
        this.value = value;
        this.checkSum = checkSum;
    }

    public static /* synthetic */ MRZData copy$default(MRZData mRZData, MRZDataType mRZDataType, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            mRZDataType = mRZData.type;
        }
        if ((i & 2) != 0) {
            str = mRZData.value;
        }
        if ((i & 4) != 0) {
            str2 = mRZData.checkSum;
        }
        return mRZData.copy(mRZDataType, str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final MRZDataType getType() {
        return this.type;
    }

    /* renamed from: component2, reason: from getter */
    public final String getValue() {
        return this.value;
    }

    /* renamed from: component3, reason: from getter */
    public final String getCheckSum() {
        return this.checkSum;
    }

    public final MRZData copy(MRZDataType type, String value, String checkSum) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(checkSum, "checkSum");
        return new MRZData(type, value, checkSum);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MRZData)) {
            return false;
        }
        MRZData mRZData = (MRZData) other;
        return this.type == mRZData.type && Intrinsics.areEqual(this.value, mRZData.value) && Intrinsics.areEqual(this.checkSum, mRZData.checkSum);
    }

    public final String getCheckSum() {
        return this.checkSum;
    }

    public final MRZDataType getType() {
        return this.type;
    }

    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        return (((this.type.hashCode() * 31) + this.value.hashCode()) * 31) + this.checkSum.hashCode();
    }

    public String toString() {
        return "MRZData(type=" + this.type + ", value=" + this.value + ", checkSum=" + this.checkSum + ')';
    }
}
