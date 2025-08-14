package com.onfido.android.sdk.capture.internal.validation;

import com.onfido.android.sdk.capture.validation.OnDeviceValidationType;
import com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\r\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000bJ\u0019\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\rH\u0000¢\u0006\u0002\b\u000eJ!\u0010\u000f\u001a\u00020\n2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\rH\u0000¢\u0006\u0002\b\u0011J\u0015\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u0015R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/validation/RetainableValidationsResult;", "", "retainableValidations", "", "Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationType;", "(Ljava/util/Set;)V", "retainedValidationResultMap", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/onfido/android/sdk/capture/validation/device/OnDeviceValidationResult;", "clear", "", "clear$onfido_capture_sdk_core_release", "getRetainedValidationResults", "", "getRetainedValidationResults$onfido_capture_sdk_core_release", "retainValidValidationResult", "validationTypeResultMap", "retainValidValidationResult$onfido_capture_sdk_core_release", "wasValidationRetained", "", "validationType", "wasValidationRetained$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RetainableValidationsResult {
    private final Set<OnDeviceValidationType> retainableValidations;
    private final ConcurrentHashMap<OnDeviceValidationType, OnDeviceValidationResult> retainedValidationResultMap;

    /* JADX WARN: Multi-variable type inference failed */
    public RetainableValidationsResult(Set<? extends OnDeviceValidationType> retainableValidations) {
        Intrinsics.checkNotNullParameter(retainableValidations, "retainableValidations");
        this.retainableValidations = retainableValidations;
        this.retainedValidationResultMap = new ConcurrentHashMap<>();
    }

    public final void clear$onfido_capture_sdk_core_release() {
        this.retainedValidationResultMap.clear();
    }

    public final Map<OnDeviceValidationType, OnDeviceValidationResult> getRetainedValidationResults$onfido_capture_sdk_core_release() {
        return this.retainedValidationResultMap;
    }

    public final void retainValidValidationResult$onfido_capture_sdk_core_release(Map<OnDeviceValidationType, ? extends OnDeviceValidationResult> validationTypeResultMap) {
        Intrinsics.checkNotNullParameter(validationTypeResultMap, "validationTypeResultMap");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<OnDeviceValidationType, ? extends OnDeviceValidationResult> entry : validationTypeResultMap.entrySet()) {
            OnDeviceValidationType key = entry.getKey();
            OnDeviceValidationResult value = entry.getValue();
            if (this.retainableValidations.contains(key) && value.isValid()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            this.retainedValidationResultMap.put((OnDeviceValidationType) entry2.getKey(), (OnDeviceValidationResult) entry2.getValue());
        }
    }

    public final boolean wasValidationRetained$onfido_capture_sdk_core_release(OnDeviceValidationType validationType) {
        Intrinsics.checkNotNullParameter(validationType, "validationType");
        OnDeviceValidationResult onDeviceValidationResult = this.retainedValidationResultMap.get(validationType);
        return onDeviceValidationResult != null && this.retainableValidations.contains(validationType) && onDeviceValidationResult.isValid();
    }
}
