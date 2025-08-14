package com.onfido.android.sdk.capture.validation.device;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\u000f\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u0016¢\u0006\u0002\u0010\u0010J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\b\u0010\u0013\u001a\u00020\u0003H\u0016J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/device/BlurValidationResult;", "Lcom/onfido/android/sdk/capture/validation/device/OnDeviceValidationResult;", "hasBlur", "", "wasExecuted", "(ZZ)V", "getHasBlur", "()Z", "getWasExecuted", "component1", "component2", Constants.COPY_TYPE, "equals", "other", "", "executionResult", "()Ljava/lang/Boolean;", "hashCode", "", "isValid", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class BlurValidationResult extends OnDeviceValidationResult {
    private final boolean hasBlur;
    private final boolean wasExecuted;

    /* JADX WARN: Illegal instructions before constructor call */
    public BlurValidationResult() {
        boolean z = false;
        this(z, z, 3, null);
    }

    public static /* synthetic */ BlurValidationResult copy$default(BlurValidationResult blurValidationResult, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = blurValidationResult.hasBlur;
        }
        if ((i & 2) != 0) {
            z2 = blurValidationResult.wasExecuted;
        }
        return blurValidationResult.copy(z, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getHasBlur() {
        return this.hasBlur;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getWasExecuted() {
        return this.wasExecuted;
    }

    public final BlurValidationResult copy(boolean hasBlur, boolean wasExecuted) {
        return new BlurValidationResult(hasBlur, wasExecuted);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BlurValidationResult)) {
            return false;
        }
        BlurValidationResult blurValidationResult = (BlurValidationResult) other;
        return this.hasBlur == blurValidationResult.hasBlur && this.wasExecuted == blurValidationResult.wasExecuted;
    }

    @Override // com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult
    public Boolean executionResult() {
        if (getWasExecuted()) {
            return Boolean.valueOf(this.hasBlur);
        }
        return null;
    }

    public final boolean getHasBlur() {
        return this.hasBlur;
    }

    @Override // com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult
    public boolean getWasExecuted() {
        return this.wasExecuted;
    }

    public int hashCode() {
        return (Boolean.hashCode(this.hasBlur) * 31) + Boolean.hashCode(this.wasExecuted);
    }

    @Override // com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult
    public boolean isValid() {
        return (getWasExecuted() && this.hasBlur) ? false : true;
    }

    public String toString() {
        return "BlurValidationResult(hasBlur=" + this.hasBlur + ", wasExecuted=" + this.wasExecuted + ')';
    }

    public BlurValidationResult(boolean z, boolean z2) {
        super(z2);
        this.hasBlur = z;
        this.wasExecuted = z2;
    }

    public /* synthetic */ BlurValidationResult(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2);
    }
}
