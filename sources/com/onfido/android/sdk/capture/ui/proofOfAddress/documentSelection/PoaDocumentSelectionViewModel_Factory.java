package com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class PoaDocumentSelectionViewModel_Factory implements Factory<PoaDocumentSelectionViewModel> {
    private final Provider<ScreenTracker> screenTrackerProvider;

    public PoaDocumentSelectionViewModel_Factory(Provider<ScreenTracker> provider) {
        this.screenTrackerProvider = provider;
    }

    public static PoaDocumentSelectionViewModel_Factory create(Provider<ScreenTracker> provider) {
        return new PoaDocumentSelectionViewModel_Factory(provider);
    }

    public static PoaDocumentSelectionViewModel newInstance(ScreenTracker screenTracker) {
        return new PoaDocumentSelectionViewModel(screenTracker);
    }

    @Override // com.onfido.javax.inject.Provider
    public PoaDocumentSelectionViewModel get() {
        return newInstance(this.screenTrackerProvider.get());
    }
}
