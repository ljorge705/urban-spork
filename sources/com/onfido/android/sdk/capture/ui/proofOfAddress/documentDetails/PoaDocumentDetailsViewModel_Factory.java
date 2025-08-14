package com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class PoaDocumentDetailsViewModel_Factory implements Factory<PoaDocumentDetailsViewModel> {
    private final Provider<ScreenTracker> screenTrackerProvider;

    public PoaDocumentDetailsViewModel_Factory(Provider<ScreenTracker> provider) {
        this.screenTrackerProvider = provider;
    }

    public static PoaDocumentDetailsViewModel_Factory create(Provider<ScreenTracker> provider) {
        return new PoaDocumentDetailsViewModel_Factory(provider);
    }

    public static PoaDocumentDetailsViewModel newInstance(ScreenTracker screenTracker) {
        return new PoaDocumentDetailsViewModel(screenTracker);
    }

    @Override // com.onfido.javax.inject.Provider
    public PoaDocumentDetailsViewModel get() {
        return newInstance(this.screenTrackerProvider.get());
    }
}
