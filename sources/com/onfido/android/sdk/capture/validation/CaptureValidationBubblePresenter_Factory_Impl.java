package com.onfido.android.sdk.capture.validation;

import com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter;
import com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble;
import com.onfido.dagger.internal.InstanceFactory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class CaptureValidationBubblePresenter_Factory_Impl implements CaptureValidationBubblePresenter.Factory {
    private final C0735CaptureValidationBubblePresenter_Factory delegateFactory;

    CaptureValidationBubblePresenter_Factory_Impl(C0735CaptureValidationBubblePresenter_Factory c0735CaptureValidationBubblePresenter_Factory) {
        this.delegateFactory = c0735CaptureValidationBubblePresenter_Factory;
    }

    @Override // com.onfido.android.sdk.capture.validation.CaptureValidationBubblePresenter.Factory
    public CaptureValidationBubblePresenter create(OnfidoCaptureValidationBubble.BubbleMode bubbleMode, CaptureValidationBubblePresenter.View view) {
        return this.delegateFactory.get(bubbleMode, view);
    }

    public static Provider<CaptureValidationBubblePresenter.Factory> create(C0735CaptureValidationBubblePresenter_Factory c0735CaptureValidationBubblePresenter_Factory) {
        return InstanceFactory.create(new CaptureValidationBubblePresenter_Factory_Impl(c0735CaptureValidationBubblePresenter_Factory));
    }
}
