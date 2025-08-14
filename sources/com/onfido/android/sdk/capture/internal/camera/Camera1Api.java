package com.onfido.android.sdk.capture.internal.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.YuvImage;
import androidx.core.app.FrameMetricsAggregator;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.internal.camera.OnfidoCamera;
import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.camera.FaceDetectionFrame;
import com.onfido.android.sdk.capture.ui.camera.FrameCallback;
import com.onfido.android.sdk.capture.ui.camera.MediaCaptureCallback;
import com.onfido.android.sdk.capture.ui.camera.OverlayView;
import com.onfido.android.sdk.capture.ui.camera.TorchMode;
import com.onfido.android.sdk.capture.ui.camera.capture.PhotoCaptureConfig;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import com.onfido.android.sdk.capture.ui.camera.exception.UnknownCameraException;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConstants;
import com.onfido.android.sdk.capture.ui.camera.view.CameraSource;
import com.onfido.android.sdk.capture.ui.camera.view.CameraSourcePreview;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000Â\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 P2\u00020\u00012\u00020\u0002:\u0002OPB5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0016J\u0018\u0010\u001c\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.H\u0002J\u0018\u00100\u001a\u0002012\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.H\u0002J(\u00102\u001a\u00020&2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u00020.2\u0006\u00106\u001a\u00020.2\u0006\u00107\u001a\u00020.H\u0002J\u000e\u00108\u001a\b\u0012\u0004\u0012\u00020 09H\u0016J\b\u0010:\u001a\u00020*H\u0007J\b\u0010;\u001a\u00020*H\u0002J3\u0010<\u001a\u00020*2\u0006\u0010=\u001a\u00020>2!\u0010?\u001a\u001d\u0012\u0013\u0012\u00110A¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(D\u0012\u0004\u0012\u00020*0@H\u0016J\b\u0010E\u001a\u00020*H\u0002J\b\u0010F\u001a\u00020*H\u0016J3\u0010G\u001a\u00020*2\u0006\u0010H\u001a\u00020I2!\u0010?\u001a\u001d\u0012\u0013\u0012\u00110J¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020*0@H\u0016J+\u0010L\u001a\u00020M2!\u0010?\u001a\u001d\u0012\u0013\u0012\u00110N¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020*0@H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR!\u0010\u001e\u001a\u0015\u0012\f\u0012\n !*\u0004\u0018\u00010 0 0\u001f¢\u0006\u0002\b\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006Q"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/Camera1Api;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera;", "Landroidx/lifecycle/LifecycleObserver;", "applicationContext", "Landroid/content/Context;", "cameraSourcePreview", "Lcom/onfido/android/sdk/capture/ui/camera/view/CameraSourcePreview;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "overlayView", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayView;", "videoCaptureConfig", "Lcom/onfido/android/sdk/capture/ui/camera/capture/VideoCaptureConfig;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "(Landroid/content/Context;Lcom/onfido/android/sdk/capture/ui/camera/view/CameraSourcePreview;Landroidx/lifecycle/LifecycleOwner;Lcom/onfido/android/sdk/capture/ui/camera/OverlayView;Lcom/onfido/android/sdk/capture/ui/camera/capture/VideoCaptureConfig;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "getApplicationContext", "()Landroid/content/Context;", "cameraControl", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraControl;", "getCameraControl", "()Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraControl;", "getCameraSourcePreview", "()Lcom/onfido/android/sdk/capture/ui/camera/view/CameraSourcePreview;", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "cropRect", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage$CropRect;", "getCropRect", "()Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage$CropRect;", "framePublishSubjectFrame", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "getOverlayView", "()Lcom/onfido/android/sdk/capture/ui/camera/OverlayView;", "previousEmittedBitmap", "Landroid/graphics/Bitmap;", "shouldProcessNextFrame", "", "centerPreviewAccordingTo", "", "rect", "Landroid/graphics/RectF;", "pictureWidth", "", "pictureHeight", "getZoomFactor", "", "nv21ToBitmap", "data", "", "width", "height", ViewProps.ROTATION, "observeFrame", "Lio/reactivex/rxjava3/core/Observable;", "onPause", "restartCameraPreview", ViewProps.START, "cameraFacing", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraFacing;", "callback", "Lkotlin/Function1;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraStatus;", "Lkotlin/ParameterName;", "name", "cameraStatus", "startNextVideoFrameSampling", "stop", "takePicture", "photoCaptureConfig", "Lcom/onfido/android/sdk/capture/ui/camera/capture/PhotoCaptureConfig;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$PictureCaptureEvent;", "event", "takeVideo", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoRecorder;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent;", "AbstractMediaCaptureCallback", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Camera1Api implements OnfidoCamera, LifecycleObserver {
    private static final int FACE_TRACKING_MIN_BITMAP_WIDTH = 480;
    private static final int MAX_QUALITY = 100;
    private static final float ROTATION_MULTIPLIER = 90.0f;
    private static final long VIDEO_FRAME_SAMPLING_PERIOD = 200;
    private static final String VIDEO_PREFIX = "onfido-video";
    private final Context applicationContext;
    private final OnfidoCamera.CameraControl cameraControl;
    private final CameraSourcePreview cameraSourcePreview;
    private final CompositeDisposable compositeDisposable;
    private final PublishSubject<OnfidoImage> framePublishSubjectFrame;
    private final LifecycleOwner lifecycleOwner;
    private final OnfidoRemoteConfig onfidoRemoteConfig;
    private final OverlayView overlayView;
    private Bitmap previousEmittedBitmap;
    private boolean shouldProcessNextFrame;
    private final VideoCaptureConfig videoCaptureConfig;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\"\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J \u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u0004H\u0016J\u001a\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0004H\u0016¨\u0006\u0014"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/Camera1Api$AbstractMediaCaptureCallback;", "Lcom/onfido/android/sdk/capture/ui/camera/MediaCaptureCallback;", "()V", "onErrorTakingPicture", "", "takePictureException", "Lcom/onfido/android/sdk/capture/ui/camera/view/CameraSource$TakePictureException;", "onPictureCaptured", "data", "", "pictureWidth", "", "pictureHeight", "onVideoCanceled", "onVideoCaptured", "isSuccess", "", ReactNativeBridgeUtiles.KEY_FILE_NAME, "", "onVideoTimeoutExceeded", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static abstract class AbstractMediaCaptureCallback implements MediaCaptureCallback {
        @Override // com.onfido.android.sdk.capture.ui.camera.MediaCaptureCallback
        public void onErrorTakingPicture(CameraSource.TakePictureException takePictureException) {
            Intrinsics.checkNotNullParameter(takePictureException, "takePictureException");
        }

        @Override // com.onfido.android.sdk.capture.ui.camera.MediaCaptureCallback
        public void onPictureCaptured(byte[] data, int pictureWidth, int pictureHeight) {
            Intrinsics.checkNotNullParameter(data, "data");
        }

        @Override // com.onfido.android.sdk.capture.ui.camera.MediaCaptureCallback
        public void onVideoCanceled() {
        }

        @Override // com.onfido.android.sdk.capture.ui.camera.MediaCaptureCallback
        public void onVideoCaptured(boolean isSuccess, String fileName) {
        }

        @Override // com.onfido.android.sdk.capture.ui.camera.MediaCaptureCallback
        public void onVideoTimeoutExceeded() {
        }
    }

    public Camera1Api(Context applicationContext, CameraSourcePreview cameraSourcePreview, LifecycleOwner lifecycleOwner, OverlayView overlayView, VideoCaptureConfig videoCaptureConfig, OnfidoRemoteConfig onfidoRemoteConfig) {
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        Intrinsics.checkNotNullParameter(cameraSourcePreview, "cameraSourcePreview");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(overlayView, "overlayView");
        Intrinsics.checkNotNullParameter(videoCaptureConfig, "videoCaptureConfig");
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        this.applicationContext = applicationContext;
        this.cameraSourcePreview = cameraSourcePreview;
        this.lifecycleOwner = lifecycleOwner;
        this.overlayView = overlayView;
        this.videoCaptureConfig = videoCaptureConfig;
        this.onfidoRemoteConfig = onfidoRemoteConfig;
        this.shouldProcessNextFrame = true;
        PublishSubject<OnfidoImage> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create(...)");
        this.framePublishSubjectFrame = publishSubjectCreate;
        this.compositeDisposable = new CompositeDisposable();
        this.cameraControl = new OnfidoCamera.CameraControl() { // from class: com.onfido.android.sdk.capture.internal.camera.Camera1Api$cameraControl$1
            @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera.CameraControl
            public void enableTorch(boolean enabled) {
                this.this$0.getCameraSourcePreview().setTorchMode(enabled ? TorchMode.ON : TorchMode.OFF);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OnfidoImage.CropRect getCropRect() {
        return new OnfidoImage.CropRect(this.cameraSourcePreview.getPreviewZoomFactor(), this.cameraSourcePreview.getPreviewVerticalOffset(), this.cameraSourcePreview.getPreviewHorizontalOffset(), this.cameraSourcePreview.getActualPreviewWidth(), this.cameraSourcePreview.getActualPreviewHeight());
    }

    private final float getZoomFactor(int pictureWidth, int pictureHeight) {
        return Math.max(this.cameraSourcePreview.getActualPreviewWidth() / pictureWidth, this.cameraSourcePreview.getActualPreviewHeight() / pictureHeight);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap nv21ToBitmap(byte[] data, int width, int height, int rotation) {
        YuvImage yuvImage = new YuvImage(data, 17, width, height, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, width, height), 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        Matrix matrix = new Matrix();
        matrix.postRotate(rotation * ROTATION_MULTIPLIER);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeByteArray, 0, 0, bitmapDecodeByteArray.getWidth(), bitmapDecodeByteArray.getHeight(), matrix, true);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
        bitmapDecodeByteArray.recycle();
        return bitmapCreateBitmap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void observeFrame$lambda$0(Camera1Api this$0, byte[] data, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(data, "data");
        int i4 = i3 != 0 ? i2 : i;
        int i5 = i3 != 0 ? i : i2;
        boolean z = false;
        if (this$0.shouldProcessNextFrame) {
            this$0.shouldProcessNextFrame = false;
            Bitmap bitmapNv21ToBitmap = this$0.nv21ToBitmap(data, i, i2, i3);
            this$0.previousEmittedBitmap = bitmapNv21ToBitmap;
            this$0.framePublishSubjectFrame.onNext(new OnfidoImage(data, i, i2, i3, this$0.getCropRect(i4, i5), bitmapNv21ToBitmap));
            return;
        }
        Bitmap bitmap = this$0.previousEmittedBitmap;
        if (bitmap != null && bitmap.isRecycled()) {
            z = true;
        }
        this$0.shouldProcessNextFrame = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void restartCameraPreview() {
        this.cameraSourcePreview.start(this.onfidoRemoteConfig.isAutoFlashModeEnabled(), true, new VideoCaptureConfig(null, false, 0, 0, 0, 0, 0, 0L, 0L, FrameMetricsAggregator.EVERY_DURATION, null));
    }

    private final void startNextVideoFrameSampling() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = Observable.interval(200L, TimeUnit.MILLISECONDS).map(new Function() { // from class: com.onfido.android.sdk.capture.internal.camera.Camera1Api.startNextVideoFrameSampling.1
            public final FaceDetectionFrame apply(long j) {
                return Camera1Api.this.getCameraSourcePreview().getFaceDetectionFrame(480);
            }

            @Override // io.reactivex.rxjava3.functions.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply(((Number) obj).longValue());
            }
        }).subscribeOn(Schedulers.computation()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.internal.camera.Camera1Api.startNextVideoFrameSampling.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(FaceDetectionFrame faceDetectionFrame) {
                Intrinsics.checkNotNullParameter(faceDetectionFrame, "faceDetectionFrame");
                byte[] yuv = faceDetectionFrame.getYuv();
                int pictureWidth = faceDetectionFrame.getPictureWidth();
                int pictureHeight = faceDetectionFrame.getPictureHeight();
                int rotation = faceDetectionFrame.getRotation();
                Camera1Api.this.framePublishSubjectFrame.onNext(new OnfidoImage(yuv, pictureWidth, pictureHeight, rotation, Camera1Api.this.getCropRect(rotation != 0 ? pictureHeight : pictureWidth, rotation != 0 ? pictureWidth : pictureHeight), faceDetectionFrame.getBitmap()));
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.internal.camera.Camera1Api.startNextVideoFrameSampling.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Timber.INSTANCE.e(it, "Error on video frames subscription: " + it.getMessage(), new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera
    public void centerPreviewAccordingTo(RectF rect) {
        Intrinsics.checkNotNullParameter(rect, "rect");
        this.cameraSourcePreview.setDocumentOverlayRect(rect);
    }

    public final Context getApplicationContext() {
        return this.applicationContext;
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera
    public OnfidoCamera.CameraControl getCameraControl() {
        return this.cameraControl;
    }

    public final CameraSourcePreview getCameraSourcePreview() {
        return this.cameraSourcePreview;
    }

    public final OverlayView getOverlayView() {
        return this.overlayView;
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera
    public Observable<OnfidoImage> observeFrame() {
        this.cameraSourcePreview.setFrameCallback(new FrameCallback() { // from class: com.onfido.android.sdk.capture.internal.camera.Camera1Api$$ExternalSyntheticLambda0
            @Override // com.onfido.android.sdk.capture.ui.camera.FrameCallback
            public final void onNextFrame(byte[] bArr, int i, int i2, int i3) {
                Camera1Api.observeFrame$lambda$0(this.f$0, bArr, i, i2, i3);
            }
        });
        Observable<OnfidoImage> observableHide = this.framePublishSubjectFrame.hide();
        Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
        return observableHide;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public final void onPause() {
        this.lifecycleOwner.getLifecycleRegistry().removeObserver(this);
        Bitmap bitmap = this.previousEmittedBitmap;
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.previousEmittedBitmap = null;
        stop();
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera
    public void start(OnfidoCamera.CameraFacing cameraFacing, final Function1<? super OnfidoCamera.CameraStatus, Unit> callback) {
        Intrinsics.checkNotNullParameter(cameraFacing, "cameraFacing");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.shouldProcessNextFrame = true;
        ViewExtensionsKt.toVisible$default(this.cameraSourcePreview, false, 1, null);
        this.compositeDisposable.clear();
        this.lifecycleOwner.getLifecycleRegistry().addObserver(this);
        this.cameraSourcePreview.setIsFront(cameraFacing == OnfidoCamera.CameraFacing.FRONT);
        this.cameraSourcePreview.setListener(new CameraSourcePreview.CameraPreviewListener() { // from class: com.onfido.android.sdk.capture.internal.camera.Camera1Api$start$cameraPreviewListener$1
            @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSourcePreview.CameraPreviewListener
            public void onCameraNotFound() {
                callback.invoke(OnfidoCamera.CameraStatus.NotFound.INSTANCE);
            }

            @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSourcePreview.CameraPreviewListener
            public void onCameraPreviewAvailable() {
                this.getCameraSourcePreview().setFocusMeterAreaWeight(this.getOverlayView().getBigHorizontalWeight$onfido_capture_sdk_core_release(), this.getOverlayView().getVerticalWeight());
                this.getCameraSourcePreview().setPictureWeightSize(this.getOverlayView().getBigHorizontalWeight$onfido_capture_sdk_core_release(), this.getOverlayView().getVerticalWeight());
                this.getCameraSourcePreview().start(this.onfidoRemoteConfig.isAutoFlashModeEnabled(), true, new VideoCaptureConfig(null, false, 0, 0, 0, 0, 0, 0L, 0L, FrameMetricsAggregator.EVERY_DURATION, null));
                callback.invoke(OnfidoCamera.CameraStatus.Started.INSTANCE);
            }

            @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSourcePreview.CameraPreviewListener
            public void onCameraUnavailable() {
                callback.invoke(OnfidoCamera.CameraStatus.NotAvailable.INSTANCE);
            }

            @Override // com.onfido.android.sdk.capture.ui.camera.view.CameraSourcePreview.CameraPreviewListener
            public void onUnknownCameraError(UnknownCameraException exception) {
                Intrinsics.checkNotNullParameter(exception, "exception");
                callback.invoke(new OnfidoCamera.CameraStatus.Failed(exception));
            }
        });
        this.cameraSourcePreview.setupTextureView();
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera
    public void stop() {
        this.compositeDisposable.clear();
        this.cameraSourcePreview.stop();
        this.cameraSourcePreview.release();
        ViewExtensionsKt.toGone$default(this.cameraSourcePreview, false, 1, null);
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera
    public void takePicture(PhotoCaptureConfig photoCaptureConfig, final Function1<? super OnfidoCamera.PictureCaptureEvent, Unit> callback) {
        Intrinsics.checkNotNullParameter(photoCaptureConfig, "photoCaptureConfig");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.cameraSourcePreview.setTorchMode(TorchMode.AUTO);
        this.cameraSourcePreview.takePicture(null, new AbstractMediaCaptureCallback() { // from class: com.onfido.android.sdk.capture.internal.camera.Camera1Api.takePicture.1
            @Override // com.onfido.android.sdk.capture.internal.camera.Camera1Api.AbstractMediaCaptureCallback, com.onfido.android.sdk.capture.ui.camera.MediaCaptureCallback
            public void onErrorTakingPicture(CameraSource.TakePictureException takePictureException) {
                Intrinsics.checkNotNullParameter(takePictureException, "takePictureException");
                Camera1Api.this.getCameraSourcePreview().release();
                callback.invoke(new OnfidoCamera.PictureCaptureEvent.Error(takePictureException));
                Camera1Api.this.restartCameraPreview();
            }

            @Override // com.onfido.android.sdk.capture.internal.camera.Camera1Api.AbstractMediaCaptureCallback, com.onfido.android.sdk.capture.ui.camera.MediaCaptureCallback
            public void onPictureCaptured(byte[] data, int pictureWidth, int pictureHeight) {
                Intrinsics.checkNotNullParameter(data, "data");
                Camera1Api.this.getCameraSourcePreview().release();
                callback.invoke(new OnfidoCamera.PictureCaptureEvent.Captured(new OnfidoImage(data, pictureWidth, pictureHeight, 0, Camera1Api.this.getCropRect(), Camera1Api.this.nv21ToBitmap(data, pictureWidth, pictureHeight, 0))));
            }
        }, photoCaptureConfig.getShouldUseDefaultJpegQuality());
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera
    public OnfidoCamera.VideoRecorder takeVideo(final Function1<? super OnfidoCamera.VideoCaptureEvent, Unit> callback) throws IOException {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (!this.cameraSourcePreview.hasCameraSource()) {
            restartCameraPreview();
        }
        final File fileCreateTempFile = File.createTempFile("onfido-video-" + System.currentTimeMillis() + '-', LivenessConstants.VIDEO_RECORDING_FILE_FORMAT, this.applicationContext.getCacheDir());
        fileCreateTempFile.deleteOnExit();
        OnfidoCamera.VideoRecorder videoRecorder = new OnfidoCamera.VideoRecorder() { // from class: com.onfido.android.sdk.capture.internal.camera.Camera1Api$takeVideo$recorder$1
            private final boolean hasValidRecording = true;

            @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera.VideoRecorder
            public void cancel() {
                this.this$0.getCameraSourcePreview().stopRecording();
            }

            @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera.VideoRecorder
            public void finish() {
                this.this$0.getCameraSourcePreview().finishRecording(true);
            }

            @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera.VideoRecorder
            public boolean getHasValidRecording() {
                return this.hasValidRecording;
            }

            @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera.VideoRecorder
            public boolean isRecording() {
                return this.this$0.getCameraSourcePreview().isReady() && this.this$0.getCameraSourcePreview().isRecording();
            }
        };
        this.cameraSourcePreview.startVideo(this.onfidoRemoteConfig.isAutoFlashModeEnabled(), new AbstractMediaCaptureCallback() { // from class: com.onfido.android.sdk.capture.internal.camera.Camera1Api.takeVideo.1
            @Override // com.onfido.android.sdk.capture.internal.camera.Camera1Api.AbstractMediaCaptureCallback, com.onfido.android.sdk.capture.ui.camera.MediaCaptureCallback
            public void onErrorTakingPicture(CameraSource.TakePictureException takePictureException) {
                Intrinsics.checkNotNullParameter(takePictureException, "takePictureException");
                callback.invoke(new OnfidoCamera.VideoCaptureEvent.Error(takePictureException));
            }

            @Override // com.onfido.android.sdk.capture.internal.camera.Camera1Api.AbstractMediaCaptureCallback, com.onfido.android.sdk.capture.ui.camera.MediaCaptureCallback
            public void onVideoCanceled() {
                callback.invoke(OnfidoCamera.VideoCaptureEvent.Canceled.INSTANCE);
                fileCreateTempFile.delete();
            }

            @Override // com.onfido.android.sdk.capture.internal.camera.Camera1Api.AbstractMediaCaptureCallback, com.onfido.android.sdk.capture.ui.camera.MediaCaptureCallback
            public void onVideoCaptured(boolean isSuccess, String fileName) {
                if (!isSuccess) {
                    callback.invoke(OnfidoCamera.VideoCaptureEvent.Canceled.INSTANCE);
                    fileCreateTempFile.delete();
                } else {
                    Function1<OnfidoCamera.VideoCaptureEvent, Unit> function1 = callback;
                    if (fileName == null) {
                        fileName = "";
                    }
                    function1.invoke(new OnfidoCamera.VideoCaptureEvent.Recorded(fileName));
                }
            }

            @Override // com.onfido.android.sdk.capture.internal.camera.Camera1Api.AbstractMediaCaptureCallback, com.onfido.android.sdk.capture.ui.camera.MediaCaptureCallback
            public void onVideoTimeoutExceeded() {
                callback.invoke(OnfidoCamera.VideoCaptureEvent.Timeout.INSTANCE);
            }
        }, fileCreateTempFile.getAbsolutePath(), this.videoCaptureConfig);
        startNextVideoFrameSampling();
        callback.invoke(OnfidoCamera.VideoCaptureEvent.Started.INSTANCE);
        return videoRecorder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OnfidoImage.CropRect getCropRect(int pictureWidth, int pictureHeight) {
        int i;
        float f;
        int i2;
        int i3;
        int i4;
        int i5;
        float zoomFactor = getZoomFactor(pictureWidth, pictureHeight);
        int actualPreviewWidth = this.cameraSourcePreview.getActualPreviewWidth();
        int actualPreviewHeight = this.cameraSourcePreview.getActualPreviewHeight();
        OnfidoImage.CropRect cropRect = new OnfidoImage.CropRect(zoomFactor, 0, 0, actualPreviewWidth, actualPreviewHeight, 6, null);
        float f2 = pictureWidth;
        float f3 = actualPreviewWidth / f2;
        float f4 = pictureHeight;
        float f5 = actualPreviewHeight / f4;
        if (f3 > f5) {
            i2 = (((int) (f4 * f3)) - actualPreviewHeight) / 2;
            f = 0.0f;
            i = 0;
            i3 = 0;
            i4 = 0;
            i5 = 29;
        } else {
            i = (((int) (f2 * f5)) - actualPreviewWidth) / 2;
            f = 0.0f;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 27;
        }
        return OnfidoImage.CropRect.copy$default(cropRect, f, i2, i, i3, i4, i5, null);
    }
}
