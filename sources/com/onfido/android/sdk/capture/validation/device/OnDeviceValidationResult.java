package com.onfido.android.sdk.capture.validation.device;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000f\u0010\u0007\u001a\u0004\u0018\u00010\u0003H&¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\u0003H&R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/device/OnDeviceValidationResult;", "", "wasExecuted", "", "(Z)V", "getWasExecuted", "()Z", "executionResult", "()Ljava/lang/Boolean;", "isValid", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class OnDeviceValidationResult {
    private final boolean wasExecuted;

    public OnDeviceValidationResult(boolean z) {
        this.wasExecuted = z;
    }

    public abstract Boolean executionResult();

    public boolean getWasExecuted() {
        return this.wasExecuted;
    }

    public abstract boolean isValid();
}
