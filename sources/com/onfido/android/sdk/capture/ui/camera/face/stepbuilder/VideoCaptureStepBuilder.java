package com.onfido.android.sdk.capture.ui.camera.face.stepbuilder;

import com.onfido.android.sdk.capture.ui.options.FlowAction;
import com.onfido.android.sdk.capture.ui.options.FlowStep;
import com.onfido.android.sdk.capture.ui.options.VideoCaptureVariantOptions;
import io.sentry.protocol.OperatingSystem;
import kotlin.Metadata;

/* compiled from: VideoCaptureStepBuilder.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/face/stepbuilder/VideoCaptureStepBuilder;", "Lcom/onfido/android/sdk/capture/ui/camera/face/stepbuilder/BaseFaceCaptureStepBuilder;", "()V", "withConfirmationVideo", "", "withIntro", OperatingSystem.JsonKeys.BUILD, "Lcom/onfido/android/sdk/capture/ui/options/FlowStep;", "withConfirmationVideoPreview", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class VideoCaptureStepBuilder extends BaseFaceCaptureStepBuilder {
    private boolean withIntro = true;
    private boolean withConfirmationVideo = true;

    public final VideoCaptureStepBuilder withIntro(boolean withIntro) {
        this.withIntro = withIntro;
        return this;
    }

    public final VideoCaptureStepBuilder withConfirmationVideoPreview(boolean withConfirmationVideo) {
        this.withConfirmationVideo = withConfirmationVideo;
        return this;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.face.stepbuilder.BaseFaceCaptureStepBuilder
    public FlowStep build() {
        FlowStep flowStep = new FlowStep(FlowAction.CAPTURE_LIVENESS);
        flowStep.setOptions(new VideoCaptureVariantOptions(this.withIntro, this.withConfirmationVideo));
        return flowStep;
    }
}
