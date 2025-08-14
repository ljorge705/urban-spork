package com.onfido.android.sdk.capture.ui.camera.face;

import com.facebook.hermes.intl.Constants;
import com.onfido.android.sdk.capture.ui.options.FlowAction;
import com.onfido.android.sdk.capture.ui.options.FlowStep;
import com.onfido.android.sdk.capture.ui.options.PhotoCaptureVariantOptions;
import com.onfido.android.sdk.capture.ui.options.VideoCaptureVariantOptions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FaceCaptureStep.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/face/FaceCaptureStep;", "Lcom/onfido/android/sdk/capture/ui/options/FlowStep;", Constants.SENSITIVITY_VARIANT, "Lcom/onfido/android/sdk/capture/ui/camera/face/FaceCaptureVariantPhoto;", "(Lcom/onfido/android/sdk/capture/ui/camera/face/FaceCaptureVariantPhoto;)V", "Lcom/onfido/android/sdk/capture/ui/camera/face/FaceCaptureVariantVideo;", "(Lcom/onfido/android/sdk/capture/ui/camera/face/FaceCaptureVariantVideo;)V", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FaceCaptureStep extends FlowStep {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FaceCaptureStep(FaceCaptureVariantPhoto variant) {
        super(FlowAction.CAPTURE_FACE);
        Intrinsics.checkNotNullParameter(variant, "variant");
        setOptions(new PhotoCaptureVariantOptions(variant.getWithIntro()));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FaceCaptureStep(FaceCaptureVariantVideo variant) {
        super(FlowAction.CAPTURE_LIVENESS);
        Intrinsics.checkNotNullParameter(variant, "variant");
        setOptions(new VideoCaptureVariantOptions(variant.getShowIntroVideo(), variant.getShowConfirmationVideo()));
    }
}
