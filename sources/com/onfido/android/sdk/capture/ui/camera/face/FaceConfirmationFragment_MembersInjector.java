package com.onfido.android.sdk.capture.ui.camera.face;

import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class FaceConfirmationFragment_MembersInjector implements MembersInjector<FaceConfirmationFragment> {
    private final Provider<ImageUtils> imageUtilsProvider;

    public FaceConfirmationFragment_MembersInjector(Provider<ImageUtils> provider) {
        this.imageUtilsProvider = provider;
    }

    public static MembersInjector<FaceConfirmationFragment> create(Provider<ImageUtils> provider) {
        return new FaceConfirmationFragment_MembersInjector(provider);
    }

    public static void injectImageUtils(FaceConfirmationFragment faceConfirmationFragment, ImageUtils imageUtils) {
        faceConfirmationFragment.imageUtils = imageUtils;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(FaceConfirmationFragment faceConfirmationFragment) {
        injectImageUtils(faceConfirmationFragment, this.imageUtilsProvider.get());
    }
}
