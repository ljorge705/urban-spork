package com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class PoaDocumentSubmissionViewModel_Factory implements Factory<PoaDocumentSubmissionViewModel> {
    private final Provider<CompressPoaDocumentUseCase> compressPoaDocumentUseCaseProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<ScreenTracker> screenTrackerProvider;
    private final Provider<UploadPoaDocumentUseCase> uploadPoaDocumentUseCaseProvider;
    private final Provider<WaitingScreenTracker> waitingScreenTrackerProvider;

    public PoaDocumentSubmissionViewModel_Factory(Provider<ScreenTracker> provider, Provider<SchedulersProvider> provider2, Provider<CompressPoaDocumentUseCase> provider3, Provider<UploadPoaDocumentUseCase> provider4, Provider<WaitingScreenTracker> provider5) {
        this.screenTrackerProvider = provider;
        this.schedulersProvider = provider2;
        this.compressPoaDocumentUseCaseProvider = provider3;
        this.uploadPoaDocumentUseCaseProvider = provider4;
        this.waitingScreenTrackerProvider = provider5;
    }

    public static PoaDocumentSubmissionViewModel_Factory create(Provider<ScreenTracker> provider, Provider<SchedulersProvider> provider2, Provider<CompressPoaDocumentUseCase> provider3, Provider<UploadPoaDocumentUseCase> provider4, Provider<WaitingScreenTracker> provider5) {
        return new PoaDocumentSubmissionViewModel_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static PoaDocumentSubmissionViewModel newInstance(ScreenTracker screenTracker, SchedulersProvider schedulersProvider, CompressPoaDocumentUseCase compressPoaDocumentUseCase, UploadPoaDocumentUseCase uploadPoaDocumentUseCase, WaitingScreenTracker waitingScreenTracker) {
        return new PoaDocumentSubmissionViewModel(screenTracker, schedulersProvider, compressPoaDocumentUseCase, uploadPoaDocumentUseCase, waitingScreenTracker);
    }

    @Override // com.onfido.javax.inject.Provider
    public PoaDocumentSubmissionViewModel get() {
        return newInstance(this.screenTrackerProvider.get(), this.schedulersProvider.get(), this.compressPoaDocumentUseCaseProvider.get(), this.uploadPoaDocumentUseCaseProvider.get(), this.waitingScreenTrackerProvider.get());
    }
}
