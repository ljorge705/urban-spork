package com.onfido.android.sdk.capture.core;

import com.onfido.android.sdk.FlowConfig;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tR\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/core/OnfidoViewModel;", "", "flowConfig", "Lcom/onfido/android/sdk/FlowConfig;", "(Lcom/onfido/android/sdk/FlowConfig;)V", "getFlowConfig$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/FlowConfig;", "onClear", "", "onClear$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoViewModel {
    private final FlowConfig flowConfig;

    @Inject
    public OnfidoViewModel(FlowConfig flowConfig) {
        this.flowConfig = flowConfig;
    }

    /* renamed from: getFlowConfig$onfido_capture_sdk_core_release, reason: from getter */
    public final FlowConfig getFlowConfig() {
        return this.flowConfig;
    }

    public final void onClear$onfido_capture_sdk_core_release() {
    }
}
