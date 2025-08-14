package com.onfido.android.sdk.capture.component.active.video.capture.presentation.uploaderror;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionUploadErrorFragment_MembersInjector implements MembersInjector<MotionUploadErrorFragment> {
    private final Provider<OnfidoAnalytics> analyticsProvider;

    public MotionUploadErrorFragment_MembersInjector(Provider<OnfidoAnalytics> provider) {
        this.analyticsProvider = provider;
    }

    public static MembersInjector<MotionUploadErrorFragment> create(Provider<OnfidoAnalytics> provider) {
        return new MotionUploadErrorFragment_MembersInjector(provider);
    }

    public static void injectAnalytics(MotionUploadErrorFragment motionUploadErrorFragment, OnfidoAnalytics onfidoAnalytics) {
        motionUploadErrorFragment.analytics = onfidoAnalytics;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(MotionUploadErrorFragment motionUploadErrorFragment) {
        injectAnalytics(motionUploadErrorFragment, this.analyticsProvider.get());
    }
}
