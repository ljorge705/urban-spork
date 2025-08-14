package com.onfido.android.sdk.capture.ui.camera.face.stepbuilder;

import com.onfido.android.sdk.capture.ui.camera.face.FaceCaptureStep;
import com.onfido.android.sdk.capture.ui.camera.face.FaceCaptureVariantPhoto;
import com.onfido.android.sdk.capture.ui.options.FlowStep;
import io.sentry.protocol.OperatingSystem;
import kotlin.Metadata;

/* compiled from: PhotoCaptureStepBuilder.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/face/stepbuilder/PhotoCaptureStepBuilder;", "Lcom/onfido/android/sdk/capture/ui/camera/face/stepbuilder/BaseFaceCaptureStepBuilder;", "()V", "withIntro", "", OperatingSystem.JsonKeys.BUILD, "Lcom/onfido/android/sdk/capture/ui/options/FlowStep;", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PhotoCaptureStepBuilder extends BaseFaceCaptureStepBuilder {
    private boolean withIntro = true;

    public final PhotoCaptureStepBuilder withIntro(boolean withIntro) {
        this.withIntro = withIntro;
        return this;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.face.stepbuilder.BaseFaceCaptureStepBuilder
    public FlowStep build() {
        return new FaceCaptureStep(new FaceCaptureVariantPhoto(this.withIntro));
    }
}
