package com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission;

import com.onfido.android.sdk.capture.ui.proofOfAddress.PoaUtils;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class PoaDocumentSubmissionFragment_MembersInjector implements MembersInjector<PoaDocumentSubmissionFragment> {
    private final Provider<ImageUtils> imageUtilsProvider;
    private final Provider<PoaUtils> poaUtilsProvider;
    private final Provider<PoaDocumentSubmissionViewModel> poaViewModelFactoryProvider;

    public PoaDocumentSubmissionFragment_MembersInjector(Provider<ImageUtils> provider, Provider<PoaDocumentSubmissionViewModel> provider2, Provider<PoaUtils> provider3) {
        this.imageUtilsProvider = provider;
        this.poaViewModelFactoryProvider = provider2;
        this.poaUtilsProvider = provider3;
    }

    public static MembersInjector<PoaDocumentSubmissionFragment> create(Provider<ImageUtils> provider, Provider<PoaDocumentSubmissionViewModel> provider2, Provider<PoaUtils> provider3) {
        return new PoaDocumentSubmissionFragment_MembersInjector(provider, provider2, provider3);
    }

    public static void injectImageUtils(PoaDocumentSubmissionFragment poaDocumentSubmissionFragment, ImageUtils imageUtils) {
        poaDocumentSubmissionFragment.imageUtils = imageUtils;
    }

    public static void injectPoaUtils(PoaDocumentSubmissionFragment poaDocumentSubmissionFragment, PoaUtils poaUtils) {
        poaDocumentSubmissionFragment.poaUtils = poaUtils;
    }

    public static void injectPoaViewModelFactory(PoaDocumentSubmissionFragment poaDocumentSubmissionFragment, Provider<PoaDocumentSubmissionViewModel> provider) {
        poaDocumentSubmissionFragment.poaViewModelFactory = provider;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(PoaDocumentSubmissionFragment poaDocumentSubmissionFragment) {
        injectImageUtils(poaDocumentSubmissionFragment, this.imageUtilsProvider.get());
        injectPoaViewModelFactory(poaDocumentSubmissionFragment, this.poaViewModelFactoryProvider);
        injectPoaUtils(poaDocumentSubmissionFragment, this.poaUtilsProvider.get());
    }
}
