package com.onfido.android.sdk.capture.validation.device;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\u000f\u0010\u0013\u001a\u0004\u0018\u00010\u0005H\u0016¢\u0006\u0002\u0010\u0014J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\b\u0010\u0017\u001a\u00020\u0005H\u0016J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/device/BarcodeValidationResult;", "Lcom/onfido/android/sdk/capture/validation/device/OnDeviceValidationResult;", "barcodeValue", "", "wasExecuted", "", "(Ljava/lang/String;Z)V", "getBarcodeValue", "()Ljava/lang/String;", "hasBarcode", "getHasBarcode", "()Z", "getWasExecuted", "component1", "component2", Constants.COPY_TYPE, "equals", "other", "", "executionResult", "()Ljava/lang/Boolean;", "hashCode", "", "isValid", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class BarcodeValidationResult extends OnDeviceValidationResult {
    private final String barcodeValue;
    private final boolean hasBarcode;
    private final boolean wasExecuted;

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodeValidationResult() {
        this(null, false, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ BarcodeValidationResult copy$default(BarcodeValidationResult barcodeValidationResult, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = barcodeValidationResult.barcodeValue;
        }
        if ((i & 2) != 0) {
            z = barcodeValidationResult.wasExecuted;
        }
        return barcodeValidationResult.copy(str, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getBarcodeValue() {
        return this.barcodeValue;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getWasExecuted() {
        return this.wasExecuted;
    }

    public final BarcodeValidationResult copy(String barcodeValue, boolean wasExecuted) {
        return new BarcodeValidationResult(barcodeValue, wasExecuted);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BarcodeValidationResult)) {
            return false;
        }
        BarcodeValidationResult barcodeValidationResult = (BarcodeValidationResult) other;
        return Intrinsics.areEqual(this.barcodeValue, barcodeValidationResult.barcodeValue) && this.wasExecuted == barcodeValidationResult.wasExecuted;
    }

    @Override // com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult
    public Boolean executionResult() {
        if (getWasExecuted()) {
            return Boolean.valueOf(this.hasBarcode);
        }
        return null;
    }

    public final String getBarcodeValue() {
        return this.barcodeValue;
    }

    public final boolean getHasBarcode() {
        return this.hasBarcode;
    }

    @Override // com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult
    public boolean getWasExecuted() {
        return this.wasExecuted;
    }

    public int hashCode() {
        String str = this.barcodeValue;
        return ((str == null ? 0 : str.hashCode()) * 31) + Boolean.hashCode(this.wasExecuted);
    }

    @Override // com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult
    public boolean isValid() {
        return !getWasExecuted() || this.hasBarcode;
    }

    public String toString() {
        return "BarcodeValidationResult(barcodeValue=" + this.barcodeValue + ", wasExecuted=" + this.wasExecuted + ')';
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0012  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BarcodeValidationResult(java.lang.String r1, boolean r2) {
        /*
            r0 = this;
            r0.<init>(r2)
            r0.barcodeValue = r1
            r0.wasExecuted = r2
            if (r1 == 0) goto L12
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)
            r2 = 1
            r1 = r1 ^ r2
            if (r1 == 0) goto L12
            goto L13
        L12:
            r2 = 0
        L13:
            r0.hasBarcode = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.validation.device.BarcodeValidationResult.<init>(java.lang.String, boolean):void");
    }

    public /* synthetic */ BarcodeValidationResult(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? false : z);
    }
}
