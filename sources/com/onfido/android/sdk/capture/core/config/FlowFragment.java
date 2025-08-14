package com.onfido.android.sdk.capture.core.config;

import androidx.core.os.BundleKt;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.core.config.Flow;
import com.onfido.android.sdk.capture.ui.BaseFragment;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0011\b\u0016\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/core/config/FlowFragment;", "Lcom/onfido/android/sdk/capture/ui/BaseFragment;", "()V", "layoutRes", "", "(I)V", "finishFlow", "", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/core/config/Flow$Result;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class FlowFragment extends BaseFragment {
    public static final String REQUEST_KEY = "request";

    public FlowFragment() {
    }

    public final void finishFlow(Flow.Result result) {
        Intrinsics.checkNotNullParameter(result, "result");
        getParentFragmentManager().setFragmentResult("request", BundleKt.bundleOf(TuplesKt.to("request", result)));
    }

    public FlowFragment(int i) {
        super(i);
    }
}
