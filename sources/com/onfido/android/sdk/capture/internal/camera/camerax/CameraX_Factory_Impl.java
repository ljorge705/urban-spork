package com.onfido.android.sdk.capture.internal.camera.camerax;

import androidx.camera.view.PreviewView;
import androidx.lifecycle.LifecycleOwner;
import com.onfido.android.sdk.capture.internal.camera.camerax.CameraX;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import com.onfido.dagger.internal.InstanceFactory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class CameraX_Factory_Impl implements CameraX.Factory {
    private final C0579CameraX_Factory delegateFactory;

    CameraX_Factory_Impl(C0579CameraX_Factory c0579CameraX_Factory) {
        this.delegateFactory = c0579CameraX_Factory;
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.camerax.CameraX.Factory
    public CameraX create(LifecycleOwner lifecycleOwner, PreviewView previewView, PreviewConfig previewConfig, ImageCaptureConfig imageCaptureConfig, VideoCaptureConfig videoCaptureConfig, ImageAnalysisConfig imageAnalysisConfig, ImageAnalyzer<?> imageAnalyzer, FrameSampler<?> frameSampler) {
        return this.delegateFactory.get(lifecycleOwner, previewView, previewConfig, imageCaptureConfig, videoCaptureConfig, imageAnalysisConfig, imageAnalyzer, frameSampler);
    }

    public static Provider<CameraX.Factory> create(C0579CameraX_Factory c0579CameraX_Factory) {
        return InstanceFactory.create(new CameraX_Factory_Impl(c0579CameraX_Factory));
    }
}
