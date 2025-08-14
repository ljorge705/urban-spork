package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.mapper;

import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.FaceAlignmentChecks;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class ToFaceAlignmentMapper_Factory implements Factory<ToFaceAlignmentMapper> {
    private final Provider<FaceAlignmentChecks> faceAlignmentChecksProvider;

    public ToFaceAlignmentMapper_Factory(Provider<FaceAlignmentChecks> provider) {
        this.faceAlignmentChecksProvider = provider;
    }

    public static ToFaceAlignmentMapper_Factory create(Provider<FaceAlignmentChecks> provider) {
        return new ToFaceAlignmentMapper_Factory(provider);
    }

    public static ToFaceAlignmentMapper newInstance(FaceAlignmentChecks faceAlignmentChecks) {
        return new ToFaceAlignmentMapper(faceAlignmentChecks);
    }

    @Override // com.onfido.javax.inject.Provider
    public ToFaceAlignmentMapper get() {
        return newInstance(this.faceAlignmentChecksProvider.get());
    }
}
