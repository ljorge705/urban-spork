package com.onfido.android.sdk.capture.component.active.video.capture.presentation.nofacedetected;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionNoFaceDetectedFragment_MembersInjector implements MembersInjector<MotionNoFaceDetectedFragment> {
    private final Provider<OnfidoAnalytics> analyticsProvider;

    public MotionNoFaceDetectedFragment_MembersInjector(Provider<OnfidoAnalytics> provider) {
        this.analyticsProvider = provider;
    }

    public static MembersInjector<MotionNoFaceDetectedFragment> create(Provider<OnfidoAnalytics> provider) {
        return new MotionNoFaceDetectedFragment_MembersInjector(provider);
    }

    public static void injectAnalytics(MotionNoFaceDetectedFragment motionNoFaceDetectedFragment, OnfidoAnalytics onfidoAnalytics) {
        motionNoFaceDetectedFragment.analytics = onfidoAnalytics;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(MotionNoFaceDetectedFragment motionNoFaceDetectedFragment) {
        injectAnalytics(motionNoFaceDetectedFragment, this.analyticsProvider.get());
    }
}
