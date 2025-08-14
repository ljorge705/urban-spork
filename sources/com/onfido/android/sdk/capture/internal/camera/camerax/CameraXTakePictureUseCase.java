package com.onfido.android.sdk.capture.internal.camera.camerax;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import com.onfido.android.sdk.capture.internal.camera.OnfidoCamera;
import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J<\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2!\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\n0\u0010H\u0086\u0002J\u0014\u0010\u0015\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraXTakePictureUseCase;", "", "applicationContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "invoke", "", "imageCapture", "Landroidx/camera/core/ImageCapture;", "previewView", "Landroidx/camera/view/PreviewView;", "callback", "Lkotlin/Function1;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$PictureCaptureEvent;", "Lkotlin/ParameterName;", "name", "event", "isRotated", "", "Landroid/graphics/Bitmap;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CameraXTakePictureUseCase {
    private final Context applicationContext;
    private final Executor executor;

    @Inject
    public CameraXTakePictureUseCase(Context applicationContext) {
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        this.applicationContext = applicationContext;
        Executor mainExecutor = ContextCompat.getMainExecutor(applicationContext);
        Intrinsics.checkNotNullExpressionValue(mainExecutor, "getMainExecutor(...)");
        this.executor = mainExecutor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isRotated(Bitmap bitmap, PreviewView previewView) {
        return bitmap.getWidth() > bitmap.getHeight() && previewView.getWidth() < previewView.getHeight();
    }

    public final Executor getExecutor() {
        return this.executor;
    }

    public final void invoke(ImageCapture imageCapture, final PreviewView previewView, final Function1<? super OnfidoCamera.PictureCaptureEvent, Unit> callback) {
        Intrinsics.checkNotNullParameter(imageCapture, "imageCapture");
        Intrinsics.checkNotNullParameter(previewView, "previewView");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageCapture.OutputFileOptions outputFileOptionsBuild = new ImageCapture.OutputFileOptions.Builder(byteArrayOutputStream).build();
        Intrinsics.checkNotNullExpressionValue(outputFileOptionsBuild, "build(...)");
        imageCapture.m124lambda$takePicture$2$androidxcameracoreImageCapture(outputFileOptionsBuild, this.executor, new ImageCapture.OnImageSavedCallback() { // from class: com.onfido.android.sdk.capture.internal.camera.camerax.CameraXTakePictureUseCase.invoke.1
            @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
            public void onError(ImageCaptureException exception) {
                Intrinsics.checkNotNullParameter(exception, "exception");
                callback.invoke(new OnfidoCamera.PictureCaptureEvent.Error(exception));
                Timber.INSTANCE.e(exception);
            }

            @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
            public void onImageSaved(ImageCapture.OutputFileResults outputFileResults) {
                float width;
                float f;
                Intrinsics.checkNotNullParameter(outputFileResults, "outputFileResults");
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                int width2 = bitmapDecodeByteArray.getWidth();
                int height = bitmapDecodeByteArray.getHeight();
                CameraXTakePictureUseCase cameraXTakePictureUseCase = this;
                Intrinsics.checkNotNull(bitmapDecodeByteArray);
                if (cameraXTakePictureUseCase.isRotated(bitmapDecodeByteArray, previewView)) {
                    width = previewView.getWidth();
                    f = height;
                } else {
                    width = previewView.getWidth();
                    f = width2;
                }
                float f2 = width / f;
                bitmapDecodeByteArray.recycle();
                Function1<OnfidoCamera.PictureCaptureEvent, Unit> function1 = callback;
                Intrinsics.checkNotNull(byteArray);
                function1.invoke(new OnfidoCamera.PictureCaptureEvent.Captured(new OnfidoImage(byteArray, width2, height, 0, new OnfidoImage.CropRect(f2, 0, 0, previewView.getWidth(), previewView.getHeight()), bitmapDecodeByteArray)));
            }
        });
    }
}
