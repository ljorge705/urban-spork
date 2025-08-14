package com.onfido.android.sdk.capture.validation.device;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\u000f\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\u000fJ\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\b\u0010\u0012\u001a\u00020\u0003H\u0016J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0006¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/device/MRZValidationResult;", "Lcom/onfido/android/sdk/capture/validation/device/OnDeviceValidationResult;", "isMrzReadable", "", "wasExecuted", "(ZZ)V", "()Z", "getWasExecuted", "component1", "component2", Constants.COPY_TYPE, "equals", "other", "", "executionResult", "()Ljava/lang/Boolean;", "hashCode", "", "isValid", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class MRZValidationResult extends OnDeviceValidationResult {
    private final boolean isMrzReadable;
    private final boolean wasExecuted;

    /* JADX WARN: Illegal instructions before constructor call */
    public MRZValidationResult() {
        boolean z = false;
        this(z, z, 3, null);
    }

    public static /* synthetic */ MRZValidationResult copy$default(MRZValidationResult mRZValidationResult, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = mRZValidationResult.isMrzReadable;
        }
        if ((i & 2) != 0) {
            z2 = mRZValidationResult.wasExecuted;
        }
        return mRZValidationResult.copy(z, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getIsMrzReadable() {
        return this.isMrzReadable;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getWasExecuted() {
        return this.wasExecuted;
    }

    public final MRZValidationResult copy(boolean isMrzReadable, boolean wasExecuted) {
        return new MRZValidationResult(isMrzReadable, wasExecuted);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MRZValidationResult)) {
            return false;
        }
        MRZValidationResult mRZValidationResult = (MRZValidationResult) other;
        return this.isMrzReadable == mRZValidationResult.isMrzReadable && this.wasExecuted == mRZValidationResult.wasExecuted;
    }

    @Override // com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult
    public Boolean executionResult() {
        if (getWasExecuted()) {
            return Boolean.valueOf(this.isMrzReadable);
        }
        return null;
    }

    @Override // com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult
    public boolean getWasExecuted() {
        return this.wasExecuted;
    }

    public int hashCode() {
        return (Boolean.hashCode(this.isMrzReadable) * 31) + Boolean.hashCode(this.wasExecuted);
    }

    public final boolean isMrzReadable() {
        return this.isMrzReadable;
    }

    @Override // com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult
    public boolean isValid() {
        return !getWasExecuted() || this.isMrzReadable;
    }

    public String toString() {
        return "MRZValidationResult(isMrzReadable=" + this.isMrzReadable + ", wasExecuted=" + this.wasExecuted + ')';
    }

    public MRZValidationResult(boolean z, boolean z2) {
        super(z2);
        this.isMrzReadable = z;
        this.wasExecuted = z2;
    }

    public /* synthetic */ MRZValidationResult(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2);
    }
}
