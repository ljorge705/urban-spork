package com.onfido.android.sdk.capture.internal.camera.camerax;

import android.content.Context;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.lifecycle.LifecycleOwner;
import com.google.common.util.concurrent.ListenableFuture;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import com.onfido.javax.inject.Provider;

/* renamed from: com.onfido.android.sdk.capture.internal.camera.camerax.CameraX_Factory, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0579CameraX_Factory {
    private final Provider<Context> applicationContextProvider;
    private final Provider<ListenableFuture<ProcessCameraProvider>> cameraProviderFutureProvider;
    private final Provider<CameraXImageAnalysisUseCase> cameraXImageAnalysisUseCaseProvider;
    private final Provider<CameraXTakePictureUseCase> cameraXTakePictureUseCaseProvider;
    private final Provider<CameraXTakeVideoUseCase> cameraXTakeVideoUseCaseProvider;
    private final Provider<DispatchersProvider> dispatchersProvider;
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;

    public C0579CameraX_Factory(Provider<Context> provider, Provider<CameraXTakeVideoUseCase> provider2, Provider<CameraXTakePictureUseCase> provider3, Provider<CameraXImageAnalysisUseCase> provider4, Provider<DispatchersProvider> provider5, Provider<ListenableFuture<ProcessCameraProvider>> provider6, Provider<OnfidoRemoteConfig> provider7) {
        this.applicationContextProvider = provider;
        this.cameraXTakeVideoUseCaseProvider = provider2;
        this.cameraXTakePictureUseCaseProvider = provider3;
        this.cameraXImageAnalysisUseCaseProvider = provider4;
        this.dispatchersProvider = provider5;
        this.cameraProviderFutureProvider = provider6;
        this.remoteConfigProvider = provider7;
    }

    public static C0579CameraX_Factory create(Provider<Context> provider, Provider<CameraXTakeVideoUseCase> provider2, Provider<CameraXTakePictureUseCase> provider3, Provider<CameraXImageAnalysisUseCase> provider4, Provider<DispatchersProvider> provider5, Provider<ListenableFuture<ProcessCameraProvider>> provider6, Provider<OnfidoRemoteConfig> provider7) {
        return new C0579CameraX_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static CameraX newInstance(Context context, CameraXTakeVideoUseCase cameraXTakeVideoUseCase, CameraXTakePictureUseCase cameraXTakePictureUseCase, CameraXImageAnalysisUseCase cameraXImageAnalysisUseCase, DispatchersProvider dispatchersProvider, ListenableFuture<ProcessCameraProvider> listenableFuture, OnfidoRemoteConfig onfidoRemoteConfig, LifecycleOwner lifecycleOwner, PreviewView previewView, PreviewConfig previewConfig, ImageCaptureConfig imageCaptureConfig, VideoCaptureConfig videoCaptureConfig, ImageAnalysisConfig imageAnalysisConfig, ImageAnalyzer<?> imageAnalyzer, FrameSampler<?> frameSampler) {
        return new CameraX(context, cameraXTakeVideoUseCase, cameraXTakePictureUseCase, cameraXImageAnalysisUseCase, dispatchersProvider, listenableFuture, onfidoRemoteConfig, lifecycleOwner, previewView, previewConfig, imageCaptureConfig, videoCaptureConfig, imageAnalysisConfig, imageAnalyzer, frameSampler);
    }

    public CameraX get(LifecycleOwner lifecycleOwner, PreviewView previewView, PreviewConfig previewConfig, ImageCaptureConfig imageCaptureConfig, VideoCaptureConfig videoCaptureConfig, ImageAnalysisConfig imageAnalysisConfig, ImageAnalyzer<?> imageAnalyzer, FrameSampler<?> frameSampler) {
        return newInstance(this.applicationContextProvider.get(), this.cameraXTakeVideoUseCaseProvider.get(), this.cameraXTakePictureUseCaseProvider.get(), this.cameraXImageAnalysisUseCaseProvider.get(), this.dispatchersProvider.get(), this.cameraProviderFutureProvider.get(), this.remoteConfigProvider.get(), lifecycleOwner, previewView, previewConfig, imageCaptureConfig, videoCaptureConfig, imageAnalysisConfig, imageAnalyzer, frameSampler);
    }
}
