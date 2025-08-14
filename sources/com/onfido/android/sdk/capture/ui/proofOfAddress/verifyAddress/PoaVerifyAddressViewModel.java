package com.onfido.android.sdk.capture.ui.proofOfAddress.verifyAddress;

import androidx.lifecycle.ViewModel;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/verifyAddress/PoaVerifyAddressViewModel;", "Landroidx/lifecycle/ViewModel;", "screenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;)V", "trackPoaVerifyAddress", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PoaVerifyAddressViewModel extends ViewModel {
    private final ScreenTracker screenTracker;

    @Inject
    public PoaVerifyAddressViewModel(ScreenTracker screenTracker) {
        Intrinsics.checkNotNullParameter(screenTracker, "screenTracker");
        this.screenTracker = screenTracker;
    }

    public final void trackPoaVerifyAddress() {
        this.screenTracker.trackPoaVerifyAddress$onfido_capture_sdk_core_release();
    }
}
