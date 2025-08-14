package com.onfido.android.sdk.capture.ui.faceintro;

import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class FaceIntroFragment_MembersInjector implements MembersInjector<FaceIntroFragment> {
    private final Provider<FaceIntroPresenter> presenterProvider;

    public FaceIntroFragment_MembersInjector(Provider<FaceIntroPresenter> provider) {
        this.presenterProvider = provider;
    }

    public static MembersInjector<FaceIntroFragment> create(Provider<FaceIntroPresenter> provider) {
        return new FaceIntroFragment_MembersInjector(provider);
    }

    public static void injectPresenter(FaceIntroFragment faceIntroFragment, FaceIntroPresenter faceIntroPresenter) {
        faceIntroFragment.presenter = faceIntroPresenter;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(FaceIntroFragment faceIntroFragment) {
        injectPresenter(faceIntroFragment, this.presenterProvider.get());
    }
}
