package com.onfido.android.sdk.capture.core.config;

import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b'\u0018\u00002\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0000J\u0014\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00000\rJ\u0006\u0010\u000e\u001a\u00020\u000fR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/core/config/Flow;", "Ljava/io/Serializable;", "()V", "followUpFlows", "", "addFollowUpFlow", "", "flow", "createFragment", "Lcom/onfido/android/sdk/capture/core/config/FlowFragment;", "previousFlowResult", "Lcom/onfido/android/sdk/capture/core/config/Flow$Result;", "getFollowUpFlows", "", "hasFollowUpFlows", "", "Result", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class Flow implements Serializable {
    private final List<Flow> followUpFlows = new ArrayList();

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/core/config/Flow$Result;", "Landroid/os/Parcelable;", "Failed", "Success", "Lcom/onfido/android/sdk/capture/core/config/Flow$Result$Failed;", "Lcom/onfido/android/sdk/capture/core/config/Flow$Result$Success;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Result extends Parcelable {

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/core/config/Flow$Result$Failed;", "Lcom/onfido/android/sdk/capture/core/config/Flow$Result;", "reason", "Lcom/onfido/android/sdk/capture/core/config/FailureReason;", "getReason", "()Lcom/onfido/android/sdk/capture/core/config/FailureReason;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public interface Failed extends Result {
            FailureReason getReason();
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/core/config/Flow$Result$Success;", "Lcom/onfido/android/sdk/capture/core/config/Flow$Result;", "mediaIds", "", "", "getMediaIds", "()Ljava/util/List;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public interface Success extends Result {
            List<String> getMediaIds();
        }
    }

    public static /* synthetic */ FlowFragment createFragment$default(Flow flow, Result result, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createFragment");
        }
        if ((i & 1) != 0) {
            result = null;
        }
        return flow.createFragment(result);
    }

    public final void addFollowUpFlow(Flow flow) {
        Intrinsics.checkNotNullParameter(flow, "flow");
        this.followUpFlows.add(flow);
    }

    public abstract FlowFragment createFragment(Result previousFlowResult);

    public final List<Flow> getFollowUpFlows() {
        return this.followUpFlows;
    }

    public final boolean hasFollowUpFlows() {
        return !this.followUpFlows.isEmpty();
    }
}
