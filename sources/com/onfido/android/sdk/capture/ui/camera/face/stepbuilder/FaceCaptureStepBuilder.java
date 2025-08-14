package com.onfido.android.sdk.capture.ui.camera.face.stepbuilder;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: FaceCaptureStepBuilder.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\b\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/face/stepbuilder/FaceCaptureStepBuilder;", "", "()V", "forMotion", "Lcom/onfido/android/sdk/capture/ui/camera/face/stepbuilder/MotionCaptureStepBuilder;", "forPhoto", "Lcom/onfido/android/sdk/capture/ui/camera/face/stepbuilder/PhotoCaptureStepBuilder;", "forVideo", "Lcom/onfido/android/sdk/capture/ui/camera/face/stepbuilder/VideoCaptureStepBuilder;", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FaceCaptureStepBuilder {
    public static final FaceCaptureStepBuilder INSTANCE = new FaceCaptureStepBuilder();

    private FaceCaptureStepBuilder() {
    }

    @JvmStatic
    public static final PhotoCaptureStepBuilder forPhoto() {
        return new PhotoCaptureStepBuilder();
    }

    @JvmStatic
    public static final VideoCaptureStepBuilder forVideo() {
        return new VideoCaptureStepBuilder();
    }

    @JvmStatic
    public static final MotionCaptureStepBuilder forMotion() {
        return new MotionCaptureStepBuilder();
    }
}
