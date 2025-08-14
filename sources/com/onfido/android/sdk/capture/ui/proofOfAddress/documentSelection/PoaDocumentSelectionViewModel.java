package com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection;

import androidx.lifecycle.ViewModel;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSelection/PoaDocumentSelectionViewModel;", "Landroidx/lifecycle/ViewModel;", "screenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;)V", "isUk", "", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "trackPoaDocumentSelection", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PoaDocumentSelectionViewModel extends ViewModel {
    private final ScreenTracker screenTracker;

    @Inject
    public PoaDocumentSelectionViewModel(ScreenTracker screenTracker) {
        Intrinsics.checkNotNullParameter(screenTracker, "screenTracker");
        this.screenTracker = screenTracker;
    }

    public final boolean isUk(CountryCode countryCode) {
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        return countryCode == CountryCode.GB;
    }

    public final void trackPoaDocumentSelection() {
        this.screenTracker.trackPoaDocumentTypeSelection$onfido_capture_sdk_core_release();
    }
}
