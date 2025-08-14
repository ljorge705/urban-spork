package com.onfido.android.sdk.capture.internal.camera.camerax;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.view.PreviewView;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.camera.OnfidoCamera;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.javax.inject.Inject;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\b\u0010\r\u001a\u00020\fH\u0002J;\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u000e\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00162\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0086\u0002J \u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraXImageAnalysisUseCase;", "", "timeProvider", "Lcom/onfido/android/sdk/capture/utils/TimeProvider;", "(Lcom/onfido/android/sdk/capture/utils/TimeProvider;)V", "executor", "Ljava/util/concurrent/Executor;", "lastProcessedTime", "", Constants.KEY_HIDE_CLOSE, "", "imageAnalysis", "Landroidx/camera/core/ImageAnalysis;", "createImageAnalysis", "invoke", "previewView", "Landroidx/camera/view/PreviewView;", "cameraFacing", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraFacing;", "imageAnalysisConfig", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalysisConfig;", "imageAnalyzer", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalyzer;", "rotateAndMirrorBitmap", "Landroid/graphics/Bitmap;", "bitmap", "degrees", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CameraXImageAnalysisUseCase {
    private final Executor executor;
    private long lastProcessedTime;
    private final TimeProvider timeProvider;

    @Inject
    public CameraXImageAnalysisUseCase(TimeProvider timeProvider) {
        Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
        this.timeProvider = timeProvider;
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor(...)");
        this.executor = executorServiceNewSingleThreadExecutor;
        this.lastProcessedTime = timeProvider.getCurrentTimestamp();
    }

    private final ImageAnalysis createImageAnalysis() {
        ImageAnalysis imageAnalysisBuild = new ImageAnalysis.Builder().setBackpressureStrategy(0).build();
        Intrinsics.checkNotNullExpressionValue(imageAnalysisBuild, "build(...)");
        return imageAnalysisBuild;
    }

    public static /* synthetic */ ImageAnalysis invoke$default(CameraXImageAnalysisUseCase cameraXImageAnalysisUseCase, PreviewView previewView, OnfidoCamera.CameraFacing cameraFacing, ImageAnalysisConfig imageAnalysisConfig, ImageAnalyzer imageAnalyzer, ImageAnalysis imageAnalysis, int i, Object obj) {
        if ((i & 16) != 0) {
            imageAnalysis = cameraXImageAnalysisUseCase.createImageAnalysis();
        }
        return cameraXImageAnalysisUseCase.invoke(previewView, cameraFacing, imageAnalysisConfig, imageAnalyzer, imageAnalysis);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(CameraXImageAnalysisUseCase this$0, ImageAnalysisConfig imageAnalysisConfig, OnfidoCamera.CameraFacing cameraFacing, ImageAnalyzer imageAnalyzer, PreviewView previewView, PreviewView.ScaleType scaleType, ImageProxy imageProxy) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(imageAnalysisConfig, "$imageAnalysisConfig");
        Intrinsics.checkNotNullParameter(cameraFacing, "$cameraFacing");
        Intrinsics.checkNotNullParameter(imageAnalyzer, "$imageAnalyzer");
        Intrinsics.checkNotNullParameter(previewView, "$previewView");
        Intrinsics.checkNotNullParameter(scaleType, "$scaleType");
        Intrinsics.checkNotNullParameter(imageProxy, "imageProxy");
        long currentTimestamp = this$0.timeProvider.getCurrentTimestamp();
        if (currentTimestamp - this$0.lastProcessedTime >= Duration.m7440getInWholeMillisecondsimpl(imageAnalysisConfig.m5588getImageAnalysisFrequencyUwyO8pc())) {
            this$0.lastProcessedTime = currentTimestamp;
            int rotationDegrees = imageProxy.getImageInfo().getRotationDegrees();
            Bitmap bitmap = imageProxy.toBitmap();
            Intrinsics.checkNotNullExpressionValue(bitmap, "toBitmap(...)");
            Bitmap bitmapRotateAndMirrorBitmap = this$0.rotateAndMirrorBitmap(bitmap, rotationDegrees, cameraFacing);
            bitmap.recycle();
            imageAnalyzer.analyze(bitmapRotateAndMirrorBitmap, previewView, scaleType);
        }
        imageProxy.close();
    }

    private final Bitmap rotateAndMirrorBitmap(Bitmap bitmap, float degrees, OnfidoCamera.CameraFacing cameraFacing) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        if (cameraFacing == OnfidoCamera.CameraFacing.FRONT) {
            matrix.postScale(-1.0f, 1.0f);
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
        return bitmapCreateBitmap;
    }

    public final void close(ImageAnalysis imageAnalysis) {
        if (imageAnalysis != null) {
            imageAnalysis.clearAnalyzer();
        }
    }

    public final ImageAnalysis invoke(final PreviewView previewView, final OnfidoCamera.CameraFacing cameraFacing, final ImageAnalysisConfig imageAnalysisConfig, final ImageAnalyzer<? extends Object> imageAnalyzer, ImageAnalysis imageAnalysis) {
        Intrinsics.checkNotNullParameter(previewView, "previewView");
        Intrinsics.checkNotNullParameter(cameraFacing, "cameraFacing");
        Intrinsics.checkNotNullParameter(imageAnalysisConfig, "imageAnalysisConfig");
        Intrinsics.checkNotNullParameter(imageAnalyzer, "imageAnalyzer");
        Intrinsics.checkNotNullParameter(imageAnalysis, "imageAnalysis");
        final PreviewView.ScaleType scaleType = previewView.getScaleType();
        Intrinsics.checkNotNullExpressionValue(scaleType, "getScaleType(...)");
        imageAnalysis.setAnalyzer(this.executor, new ImageAnalysis.Analyzer() { // from class: com.onfido.android.sdk.capture.internal.camera.camerax.CameraXImageAnalysisUseCase$$ExternalSyntheticLambda0
            @Override // androidx.camera.core.ImageAnalysis.Analyzer
            public final void analyze(ImageProxy imageProxy) {
                CameraXImageAnalysisUseCase.invoke$lambda$0(this.f$0, imageAnalysisConfig, cameraFacing, imageAnalyzer, previewView, scaleType, imageProxy);
            }
        });
        return imageAnalysis;
    }
}
