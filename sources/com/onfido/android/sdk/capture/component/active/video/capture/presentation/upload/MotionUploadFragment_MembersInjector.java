package com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload;

import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionUploadFragment_MembersInjector implements MembersInjector<MotionUploadFragment> {
    private final Provider<MotionUploadViewModel> viewModelProvider;

    public MotionUploadFragment_MembersInjector(Provider<MotionUploadViewModel> provider) {
        this.viewModelProvider = provider;
    }

    public static MembersInjector<MotionUploadFragment> create(Provider<MotionUploadViewModel> provider) {
        return new MotionUploadFragment_MembersInjector(provider);
    }

    public static void injectViewModelProvider(MotionUploadFragment motionUploadFragment, Provider<MotionUploadViewModel> provider) {
        motionUploadFragment.viewModelProvider = provider;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(MotionUploadFragment motionUploadFragment) {
        injectViewModelProvider(motionUploadFragment, this.viewModelProvider);
    }
}
