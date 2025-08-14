package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.mapper;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class ToFaceAlignmentFeedbackAccessibilityMapper_Factory implements Factory<ToFaceAlignmentFeedbackAccessibilityMapper> {

    private static final class InstanceHolder {
        private static final ToFaceAlignmentFeedbackAccessibilityMapper_Factory INSTANCE = new ToFaceAlignmentFeedbackAccessibilityMapper_Factory();

        private InstanceHolder() {
        }
    }

    public static ToFaceAlignmentFeedbackAccessibilityMapper_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ToFaceAlignmentFeedbackAccessibilityMapper newInstance() {
        return new ToFaceAlignmentFeedbackAccessibilityMapper();
    }

    @Override // com.onfido.javax.inject.Provider
    public ToFaceAlignmentFeedbackAccessibilityMapper get() {
        return newInstance();
    }
}
