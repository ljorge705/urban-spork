package com.onfido.android.sdk.capture.ui.options;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0002J\b\u0010\f\u001a\u00020\rH\u0016R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/options/WelcomeScreenOptions;", "Lcom/onfido/android/sdk/capture/ui/options/BaseOptions;", "flowSteps", "", "Lcom/onfido/android/sdk/capture/ui/options/FlowStep;", "(Ljava/util/List;)V", "getFlowSteps$onfido_capture_sdk_core_release", "()Ljava/util/List;", "equals", "", "other", "", "hashCode", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class WelcomeScreenOptions extends BaseOptions {
    private final List<FlowStep> flowSteps;

    /* JADX WARN: Multi-variable type inference failed */
    public WelcomeScreenOptions(List<? extends FlowStep> flowSteps) {
        Intrinsics.checkNotNullParameter(flowSteps, "flowSteps");
        this.flowSteps = flowSteps;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof WelcomeScreenOptions) {
            return Intrinsics.areEqual(this.flowSteps, ((WelcomeScreenOptions) other).flowSteps);
        }
        return false;
    }

    public final List<FlowStep> getFlowSteps$onfido_capture_sdk_core_release() {
        return this.flowSteps;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
