package com.onfido.android.sdk.capture.internal.camera.camerax;

import android.content.Context;
import android.graphics.RectF;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraState;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCaseGroup;
import androidx.camera.core.ViewPort;
import androidx.camera.core.resolutionselector.ResolutionSelector;
import androidx.camera.core.resolutionselector.ResolutionStrategy;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.video.FallbackStrategy;
import androidx.camera.video.Quality;
import androidx.camera.video.QualitySelector;
import androidx.camera.video.Recorder;
import androidx.camera.video.VideoCapture;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.facebook.react.uimanager.ViewProps;
import com.google.common.util.concurrent.ListenableFuture;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.PermissionsTracker;
import com.onfido.android.sdk.capture.internal.camera.OnfidoCamera;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.camera.capture.PhotoCaptureConfig;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.dagger.assisted.Assisted;
import com.onfido.dagger.assisted.AssistedFactory;
import com.onfido.dagger.assisted.AssistedInject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000ª\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 t2\u00020\u0001:\u0002tuB\u00ad\u0001\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0001\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0001\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0001\u0010\u0015\u001a\u00020\u0016\u0012\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u001a\u0012\n\b\u0001\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u0012\u0010\b\u0001\u0010\u001d\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001f0\u001e\u0012\u0010\b\u0001\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001f0!¢\u0006\u0002\u0010\"J\b\u0010E\u001a\u00020FH\u0002J\u0010\u0010G\u001a\u00020F2\u0006\u0010H\u001a\u00020AH\u0016J\u001a\u0010I\u001a\u0004\u0018\u00010J2\b\b\u0002\u0010K\u001a\u00020LH\u0082@¢\u0006\u0002\u0010MJK\u0010N\u001a\u00020F2\u0006\u0010O\u001a\u00020P2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020S0R2!\u0010T\u001a\u001d\u0012\u0013\u0012\u00110V¢\u0006\f\bW\u0012\b\bX\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020F0U2\b\b\u0002\u0010Z\u001a\u00020[H\u0002JK\u0010\\\u001a\u00020F2\u0006\u0010O\u001a\u00020P2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020S0R2!\u0010T\u001a\u001d\u0012\u0013\u0012\u00110]¢\u0006\f\bW\u0012\b\bX\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020F0U2\b\b\u0002\u0010Z\u001a\u00020[H\u0002J\u0010\u0010^\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001f0_H\u0016J7\u0010`\u001a\u00020F2\n\b\u0002\u0010a\u001a\u0004\u0018\u00010J2!\u0010T\u001a\u001d\u0012\u0013\u0012\u00110b¢\u0006\f\bW\u0012\b\bX\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020F0UH\u0002J+\u0010d\u001a\u00020F2!\u0010T\u001a\u001d\u0012\u0013\u0012\u00110b¢\u0006\f\bW\u0012\b\bX\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020F0UH\u0002J\b\u0010e\u001a\u00020FH\u0002J\b\u0010f\u001a\u00020FH\u0002J\u0016\u0010g\u001a\u00020F2\f\u0010T\u001a\b\u0012\u0004\u0012\u00020F0hH\u0002J\b\u0010i\u001a\u00020FH\u0002J3\u0010j\u001a\u00020F2\u0006\u0010)\u001a\u00020*2!\u0010T\u001a\u001d\u0012\u0013\u0012\u00110b¢\u0006\f\bW\u0012\b\bX\u0012\u0004\b\b(c\u0012\u0004\u0012\u00020F0UH\u0017J\b\u0010k\u001a\u00020FH\u0016J3\u0010l\u001a\u00020F2\u0006\u0010m\u001a\u00020n2!\u0010T\u001a\u001d\u0012\u0013\u0012\u00110V¢\u0006\f\bW\u0012\b\bX\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020F0UH\u0016J-\u0010o\u001a\u0004\u0018\u00010p2!\u0010T\u001a\u001d\u0012\u0013\u0012\u00110]¢\u0006\f\bW\u0012\b\bX\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020F0UH\u0017J\u001a\u0010q\u001a\u00020F*\u00020r2\f\u0010s\u001a\b\u0012\u0004\u0012\u00020D0CH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\u00020&8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u000e\u0010)\u001a\u00020*X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010,\u001a\u00020-8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001f0!X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001f0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R&\u00104\u001a\u0004\u0018\u0001058\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b6\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020?X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010@\u001a\u0004\u0018\u00010AX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010B\u001a\n\u0012\u0004\u0012\u00020D\u0018\u00010CX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006v"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraX;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera;", "applicationContext", "Landroid/content/Context;", "cameraXTakeVideoUseCase", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraXTakeVideoUseCase;", "cameraXTakePictureUseCase", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraXTakePictureUseCase;", "cameraXImageAnalysisUseCase", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraXImageAnalysisUseCase;", "dispatchersProvider", "Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;", "cameraProviderFuture", "Lcom/google/common/util/concurrent/ListenableFuture;", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "remoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "previewView", "Landroidx/camera/view/PreviewView;", "previewConfig", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/PreviewConfig;", "imageCaptureConfig", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageCaptureConfig;", "videoCaptureConfig", "Lcom/onfido/android/sdk/capture/ui/camera/capture/VideoCaptureConfig;", "imageAnalysisConfig", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalysisConfig;", "imageAnalyzer", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalyzer;", "", "frameSampler", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/FrameSampler;", "(Landroid/content/Context;Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraXTakeVideoUseCase;Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraXTakePictureUseCase;Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraXImageAnalysisUseCase;Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;Lcom/google/common/util/concurrent/ListenableFuture;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;Landroidx/lifecycle/LifecycleOwner;Landroidx/camera/view/PreviewView;Lcom/onfido/android/sdk/capture/internal/camera/camerax/PreviewConfig;Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageCaptureConfig;Lcom/onfido/android/sdk/capture/ui/camera/capture/VideoCaptureConfig;Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalysisConfig;Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalyzer;Lcom/onfido/android/sdk/capture/internal/camera/camerax/FrameSampler;)V", PermissionsTracker.CAMERA, "Landroidx/camera/core/Camera;", "cameraControl", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraControl;", "getCameraControl", "()Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraControl;", "cameraFacing", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraFacing;", "cameraProvider", "cameraXConfiguration", "Lcom/onfido/api/client/data/SdkConfiguration$CameraXConfiguration;", "getCameraXConfiguration", "()Lcom/onfido/api/client/data/SdkConfiguration$CameraXConfiguration;", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "imageAnalysis", "Landroidx/camera/core/ImageAnalysis;", "imageCapture", "Landroidx/camera/core/ImageCapture;", "getImageCapture$onfido_capture_sdk_core_release$annotations", "()V", "getImageCapture$onfido_capture_sdk_core_release", "()Landroidx/camera/core/ImageCapture;", "setImageCapture$onfido_capture_sdk_core_release", "(Landroidx/camera/core/ImageCapture;)V", "mainExecutor", "Ljava/util/concurrent/Executor;", "preview", "Landroidx/camera/core/Preview;", "previewViewCenter", "Landroid/graphics/RectF;", "videoCapture", "Landroidx/camera/video/VideoCapture;", "Landroidx/camera/video/Recorder;", "adjustPreviewViewCenter", "", "centerPreviewAccordingTo", "rectF", "getViewPortWithRetry", "Landroidx/camera/core/ViewPort;", "currentRetry", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleFailedImageCapture", "e", "", "observer", "Landroidx/lifecycle/Observer;", "Landroidx/camera/core/CameraState;", "callback", "Lkotlin/Function1;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$PictureCaptureEvent;", "Lkotlin/ParameterName;", "name", "event", "shouldInterrupt", "", "handleFailedVideoRecording", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent;", "observeFrame", "Lio/reactivex/rxjava3/core/Observable;", "setupCameraUseCases", "viewPort", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraStatus;", "cameraStatus", "setupCameraUseCasesWithViewPort", "setupImageAnalysis", "setupImageCapture", "setupPreview", "Lkotlin/Function0;", "setupVideoCapture", ViewProps.START, "stop", "takePicture", "photoCaptureConfig", "Lcom/onfido/android/sdk/capture/ui/camera/capture/PhotoCaptureConfig;", "takeVideo", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoRecorder;", "addVideoCaptureUseCase", "Landroidx/camera/core/UseCaseGroup$Builder;", "videoCaptureNonNull", "Companion", "Factory", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CameraX implements OnfidoCamera {
    private static final Companion Companion = new Companion(null);
    private static final Quality DEFAULT_VIDEO_QUALITY;

    @Deprecated
    public static final String ERROR_MESSAGE_FOR_CAMERA_PICTURE = "Unable to setup CameraX for taking picture";

    @Deprecated
    public static final String ERROR_MESSAGE_FOR_CAMERA_RECORDING = "Unable to setup CameraX for recording";

    @Deprecated
    public static final String ERROR_MESSAGE_FOR_CAMERA_START = "Starting CameraX failed";

    @Deprecated
    public static final String ERROR_VIEW_PORT_NOT_READY = "ViewPort is not ready";

    @Deprecated
    public static final int MAX_RETRY_COUNT_FOR_VIEWPORT = 30;

    @Deprecated
    public static final long RETRY_PERIOD_VIEW_PORT_IN_MS = 100;
    private final Context applicationContext;
    private Camera camera;
    private OnfidoCamera.CameraFacing cameraFacing;
    private ProcessCameraProvider cameraProvider;
    private final ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private final CameraXImageAnalysisUseCase cameraXImageAnalysisUseCase;
    private final CameraXTakePictureUseCase cameraXTakePictureUseCase;
    private final CameraXTakeVideoUseCase cameraXTakeVideoUseCase;
    private final CompositeDisposable compositeDisposable;
    private final DispatchersProvider dispatchersProvider;
    private final FrameSampler<? extends Object> frameSampler;
    private ImageAnalysis imageAnalysis;
    private final ImageAnalysisConfig imageAnalysisConfig;
    private final ImageAnalyzer<? extends Object> imageAnalyzer;
    private ImageCapture imageCapture;
    private final ImageCaptureConfig imageCaptureConfig;
    private final LifecycleOwner lifecycleOwner;
    private final Executor mainExecutor;
    private Preview preview;
    private final PreviewConfig previewConfig;
    private final PreviewView previewView;
    private RectF previewViewCenter;
    private final OnfidoRemoteConfig remoteConfig;
    private VideoCapture<Recorder> videoCapture;
    private final VideoCaptureConfig videoCaptureConfig;

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0010\u001a\u00020\u0011*\u00020\u0012R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraX$Companion;", "", "()V", "DEFAULT_VIDEO_QUALITY", "Landroidx/camera/video/Quality;", "getDEFAULT_VIDEO_QUALITY", "()Landroidx/camera/video/Quality;", "ERROR_MESSAGE_FOR_CAMERA_PICTURE", "", "ERROR_MESSAGE_FOR_CAMERA_RECORDING", "ERROR_MESSAGE_FOR_CAMERA_START", "ERROR_VIEW_PORT_NOT_READY", "MAX_RETRY_COUNT_FOR_VIEWPORT", "", "RETRY_PERIOD_VIEW_PORT_IN_MS", "", "toCameraSelector", "Landroidx/camera/core/CameraSelector;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$CameraFacing;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public final Quality getDEFAULT_VIDEO_QUALITY() {
            return CameraX.DEFAULT_VIDEO_QUALITY;
        }

        public final CameraSelector toCameraSelector(OnfidoCamera.CameraFacing cameraFacing) {
            Intrinsics.checkNotNullParameter(cameraFacing, "<this>");
            CameraSelector cameraSelector = cameraFacing == OnfidoCamera.CameraFacing.FRONT ? CameraSelector.DEFAULT_FRONT_CAMERA : CameraSelector.DEFAULT_BACK_CAMERA;
            Intrinsics.checkNotNull(cameraSelector);
            return cameraSelector;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @AssistedFactory
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001Jd\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u000e\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00112\u000e\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0013H&¨\u0006\u0014"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraX$Factory;", "", "create", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraX;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "previewView", "Landroidx/camera/view/PreviewView;", "previewConfig", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/PreviewConfig;", "imageCaptureConfig", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageCaptureConfig;", "videoCaptureConfig", "Lcom/onfido/android/sdk/capture/ui/camera/capture/VideoCaptureConfig;", "imageAnalysisConfig", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalysisConfig;", "imageAnalyzer", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalyzer;", "frameSampler", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/FrameSampler;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class DefaultImpls {
            public static /* synthetic */ CameraX create$default(Factory factory, LifecycleOwner lifecycleOwner, PreviewView previewView, PreviewConfig previewConfig, ImageCaptureConfig imageCaptureConfig, VideoCaptureConfig videoCaptureConfig, ImageAnalysisConfig imageAnalysisConfig, ImageAnalyzer imageAnalyzer, FrameSampler frameSampler, int i, Object obj) {
                if (obj == null) {
                    return factory.create(lifecycleOwner, previewView, previewConfig, (i & 8) != 0 ? null : imageCaptureConfig, (i & 16) != 0 ? null : videoCaptureConfig, (i & 32) != 0 ? null : imageAnalysisConfig, imageAnalyzer, frameSampler);
                }
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: create");
            }
        }

        CameraX create(LifecycleOwner lifecycleOwner, PreviewView previewView, PreviewConfig previewConfig, ImageCaptureConfig imageCaptureConfig, VideoCaptureConfig videoCaptureConfig, ImageAnalysisConfig imageAnalysisConfig, ImageAnalyzer<? extends Object> imageAnalyzer, FrameSampler<? extends Object> frameSampler);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroidx/camera/core/ViewPort;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.internal.camera.camerax.CameraX$getViewPortWithRetry$2", f = "CameraX.kt", i = {}, l = {312, ExifDirectoryBase.TAG_ARTIST, 316}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.internal.camera.camerax.CameraX$getViewPortWithRetry$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ViewPort>, Object> {
        final /* synthetic */ int $currentRetry;
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroidx/camera/core/ViewPort;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.internal.camera.camerax.CameraX$getViewPortWithRetry$2$1", f = "CameraX.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.internal.camera.camerax.CameraX$getViewPortWithRetry$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ViewPort>, Object> {
            int label;
            final /* synthetic */ CameraX this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(CameraX cameraX, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = cameraX;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return this.this$0.previewView.getViewPort();
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ViewPort> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(int i, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$currentRetry = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CameraX.this.new AnonymousClass2(this.$currentRetry, continuation);
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x006f A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) throws java.lang.Throwable {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r2 = 0
                r3 = 3
                r4 = 2
                r5 = 1
                if (r1 == 0) goto L26
                if (r1 == r5) goto L22
                if (r1 == r4) goto L1e
                if (r1 != r3) goto L16
                kotlin.ResultKt.throwOnFailure(r8)
                goto L70
            L16:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L1e:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L62
            L22:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L43
            L26:
                kotlin.ResultKt.throwOnFailure(r8)
                com.onfido.android.sdk.capture.internal.camera.camerax.CameraX r8 = com.onfido.android.sdk.capture.internal.camera.camerax.CameraX.this
                com.onfido.android.sdk.capture.internal.util.DispatchersProvider r8 = com.onfido.android.sdk.capture.internal.camera.camerax.CameraX.access$getDispatchersProvider$p(r8)
                kotlinx.coroutines.CoroutineDispatcher r8 = r8.getMain()
                com.onfido.android.sdk.capture.internal.camera.camerax.CameraX$getViewPortWithRetry$2$1 r1 = new com.onfido.android.sdk.capture.internal.camera.camerax.CameraX$getViewPortWithRetry$2$1
                com.onfido.android.sdk.capture.internal.camera.camerax.CameraX r6 = com.onfido.android.sdk.capture.internal.camera.camerax.CameraX.this
                r1.<init>(r6, r2)
                r7.label = r5
                java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r1, r7)
                if (r8 != r0) goto L43
                return r0
            L43:
                androidx.camera.core.ViewPort r8 = (androidx.camera.core.ViewPort) r8
                if (r8 != 0) goto L74
                int r8 = r7.$currentRetry
                r1 = 30
                if (r8 >= r1) goto L75
                com.onfido.android.sdk.capture.internal.util.logging.Timber$Forest r8 = com.onfido.android.sdk.capture.internal.util.logging.Timber.INSTANCE
                r1 = 0
                java.lang.Object[] r1 = new java.lang.Object[r1]
                java.lang.String r2 = "CameraX Preview was not ready, trying again..."
                r8.e(r2, r1)
                r7.label = r4
                r1 = 100
                java.lang.Object r8 = kotlinx.coroutines.DelayKt.delay(r1, r7)
                if (r8 != r0) goto L62
                return r0
            L62:
                com.onfido.android.sdk.capture.internal.camera.camerax.CameraX r8 = com.onfido.android.sdk.capture.internal.camera.camerax.CameraX.this
                int r1 = r7.$currentRetry
                int r1 = r1 + r5
                r7.label = r3
                java.lang.Object r8 = com.onfido.android.sdk.capture.internal.camera.camerax.CameraX.access$getViewPortWithRetry(r8, r1, r7)
                if (r8 != r0) goto L70
                return r0
            L70:
                r2 = r8
                androidx.camera.core.ViewPort r2 = (androidx.camera.core.ViewPort) r2
                goto L75
            L74:
                r2 = r8
            L75:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.internal.camera.camerax.CameraX.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ViewPort> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.internal.camera.camerax.CameraX$setupCameraUseCasesWithViewPort$1", f = "CameraX.kt", i = {}, l = {298, 299}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.internal.camera.camerax.CameraX$setupCameraUseCasesWithViewPort$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<ViewPort, Unit> $viewPortSetup;
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.internal.camera.camerax.CameraX$setupCameraUseCasesWithViewPort$1$1", f = "CameraX.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.internal.camera.camerax.CameraX$setupCameraUseCasesWithViewPort$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01461 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ ViewPort $viewPort;
            final /* synthetic */ Function1<ViewPort, Unit> $viewPortSetup;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C01461(Function1<? super ViewPort, Unit> function1, ViewPort viewPort, Continuation<? super C01461> continuation) {
                super(2, continuation);
                this.$viewPortSetup = function1;
                this.$viewPort = viewPort;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01461(this.$viewPortSetup, this.$viewPort, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.$viewPortSetup.invoke(this.$viewPort);
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01461) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function1<? super ViewPort, Unit> function1, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$viewPortSetup = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CameraX.this.new AnonymousClass1(this.$viewPortSetup, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CameraX cameraX = CameraX.this;
                this.label = 1;
                obj = CameraX.getViewPortWithRetry$default(cameraX, 0, this, 1, null);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            CoroutineDispatcher main = CameraX.this.dispatchersProvider.getMain();
            C01461 c01461 = new C01461(this.$viewPortSetup, (ViewPort) obj, null);
            this.label = 2;
            if (BuildersKt.withContext(main, c01461, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    static {
        Quality SD = Quality.SD;
        Intrinsics.checkNotNullExpressionValue(SD, "SD");
        DEFAULT_VIDEO_QUALITY = SD;
    }

    @AssistedInject
    public CameraX(Context applicationContext, CameraXTakeVideoUseCase cameraXTakeVideoUseCase, CameraXTakePictureUseCase cameraXTakePictureUseCase, CameraXImageAnalysisUseCase cameraXImageAnalysisUseCase, DispatchersProvider dispatchersProvider, ListenableFuture<ProcessCameraProvider> cameraProviderFuture, OnfidoRemoteConfig remoteConfig, @Assisted LifecycleOwner lifecycleOwner, @Assisted PreviewView previewView, @Assisted PreviewConfig previewConfig, @Assisted ImageCaptureConfig imageCaptureConfig, @Assisted VideoCaptureConfig videoCaptureConfig, @Assisted ImageAnalysisConfig imageAnalysisConfig, @Assisted ImageAnalyzer<? extends Object> imageAnalyzer, @Assisted FrameSampler<? extends Object> frameSampler) {
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        Intrinsics.checkNotNullParameter(cameraXTakeVideoUseCase, "cameraXTakeVideoUseCase");
        Intrinsics.checkNotNullParameter(cameraXTakePictureUseCase, "cameraXTakePictureUseCase");
        Intrinsics.checkNotNullParameter(cameraXImageAnalysisUseCase, "cameraXImageAnalysisUseCase");
        Intrinsics.checkNotNullParameter(dispatchersProvider, "dispatchersProvider");
        Intrinsics.checkNotNullParameter(cameraProviderFuture, "cameraProviderFuture");
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(previewView, "previewView");
        Intrinsics.checkNotNullParameter(previewConfig, "previewConfig");
        Intrinsics.checkNotNullParameter(imageAnalyzer, "imageAnalyzer");
        Intrinsics.checkNotNullParameter(frameSampler, "frameSampler");
        this.applicationContext = applicationContext;
        this.cameraXTakeVideoUseCase = cameraXTakeVideoUseCase;
        this.cameraXTakePictureUseCase = cameraXTakePictureUseCase;
        this.cameraXImageAnalysisUseCase = cameraXImageAnalysisUseCase;
        this.dispatchersProvider = dispatchersProvider;
        this.cameraProviderFuture = cameraProviderFuture;
        this.remoteConfig = remoteConfig;
        this.lifecycleOwner = lifecycleOwner;
        this.previewView = previewView;
        this.previewConfig = previewConfig;
        this.imageCaptureConfig = imageCaptureConfig;
        this.videoCaptureConfig = videoCaptureConfig;
        this.imageAnalysisConfig = imageAnalysisConfig;
        this.imageAnalyzer = imageAnalyzer;
        this.frameSampler = frameSampler;
        Executor mainExecutor = ContextCompat.getMainExecutor(applicationContext);
        Intrinsics.checkNotNullExpressionValue(mainExecutor, "getMainExecutor(...)");
        this.mainExecutor = mainExecutor;
        this.compositeDisposable = new CompositeDisposable();
    }

    private final void addVideoCaptureUseCase(UseCaseGroup.Builder builder, VideoCapture<Recorder> videoCapture) {
        if (this.imageCapture == null) {
            builder.addUseCase(videoCapture);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void adjustPreviewViewCenter() {
        RectF rectF = this.previewViewCenter;
        if (rectF != null) {
            this.previewView.setX(0.0f);
            this.previewView.setY(0.0f);
            float fCenterX = rectF.centerX();
            float fCenterY = rectF.centerY();
            int width = this.previewView.getWidth();
            int height = this.previewView.getHeight();
            float x = this.previewView.getX() + (width / 2);
            float y = fCenterY - (this.previewView.getY() + (height / 2));
            this.previewView.setTranslationX(fCenterX - x);
            this.previewView.setTranslationY(y);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SdkConfiguration.CameraXConfiguration getCameraXConfiguration() {
        return this.remoteConfig.getCameraXConfiguration();
    }

    public static /* synthetic */ void getImageCapture$onfido_capture_sdk_core_release$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getViewPortWithRetry(int i, Continuation<? super ViewPort> continuation) {
        return BuildersKt.withContext(this.dispatchersProvider.mo5607getDefault(), new AnonymousClass2(i, null), continuation);
    }

    static /* synthetic */ Object getViewPortWithRetry$default(CameraX cameraX, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return cameraX.getViewPortWithRetry(i, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleFailedImageCapture(Throwable e, Observer<CameraState> observer, Function1<? super OnfidoCamera.PictureCaptureEvent, Unit> callback, boolean shouldInterrupt) {
        Timber.INSTANCE.e(e, ERROR_MESSAGE_FOR_CAMERA_PICTURE, new Object[0]);
        if (shouldInterrupt) {
            Camera camera = this.camera;
            if (camera == null) {
                Intrinsics.throwUninitializedPropertyAccessException(PermissionsTracker.CAMERA);
                camera = null;
            }
            camera.getCameraInfo().getCameraState().removeObserver(observer);
            callback.invoke(new OnfidoCamera.PictureCaptureEvent.Error(e));
        }
    }

    static /* synthetic */ void handleFailedImageCapture$default(CameraX cameraX, Throwable th, Observer observer, Function1 function1, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        cameraX.handleFailedImageCapture(th, observer, function1, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleFailedVideoRecording(Throwable e, Observer<CameraState> observer, Function1<? super OnfidoCamera.VideoCaptureEvent, Unit> callback, boolean shouldInterrupt) {
        Timber.INSTANCE.e(e, ERROR_MESSAGE_FOR_CAMERA_RECORDING, new Object[0]);
        if (shouldInterrupt) {
            Camera camera = this.camera;
            if (camera == null) {
                Intrinsics.throwUninitializedPropertyAccessException(PermissionsTracker.CAMERA);
                camera = null;
            }
            camera.getCameraInfo().getCameraState().removeObserver(observer);
            callback.invoke(new OnfidoCamera.VideoCaptureEvent.Error(e));
        }
    }

    static /* synthetic */ void handleFailedVideoRecording$default(CameraX cameraX, Throwable th, Observer observer, Function1 function1, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        cameraX.handleFailedVideoRecording(th, observer, function1, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupCameraUseCases(ViewPort viewPort, Function1<? super OnfidoCamera.CameraStatus, Unit> callback) {
        try {
            Companion companion = Companion;
            OnfidoCamera.CameraFacing cameraFacing = this.cameraFacing;
            ProcessCameraProvider processCameraProvider = null;
            if (cameraFacing == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cameraFacing");
                cameraFacing = null;
            }
            CameraSelector cameraSelector = companion.toCameraSelector(cameraFacing);
            UseCaseGroup.Builder builder = new UseCaseGroup.Builder();
            Preview preview = this.preview;
            if (preview == null) {
                Intrinsics.throwUninitializedPropertyAccessException("preview");
                preview = null;
            }
            builder.addUseCase(preview);
            ImageCapture imageCapture = this.imageCapture;
            if (imageCapture != null) {
                builder.addUseCase(imageCapture);
            }
            VideoCapture<Recorder> videoCapture = this.videoCapture;
            if (videoCapture != null) {
                addVideoCaptureUseCase(builder, videoCapture);
            }
            ImageAnalysis imageAnalysis = this.imageAnalysis;
            if (imageAnalysis != null) {
                builder.addUseCase(imageAnalysis);
            }
            if (viewPort != null) {
                builder.setViewPort(viewPort);
            }
            ProcessCameraProvider processCameraProvider2 = this.cameraProvider;
            if (processCameraProvider2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cameraProvider");
                processCameraProvider2 = null;
            }
            processCameraProvider2.unbindAll();
            ProcessCameraProvider processCameraProvider3 = this.cameraProvider;
            if (processCameraProvider3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cameraProvider");
            } else {
                processCameraProvider = processCameraProvider3;
            }
            Camera cameraBindToLifecycle = processCameraProvider.bindToLifecycle(this.lifecycleOwner, cameraSelector, builder.build());
            Intrinsics.checkNotNullExpressionValue(cameraBindToLifecycle, "bindToLifecycle(...)");
            this.camera = cameraBindToLifecycle;
        } catch (IllegalArgumentException e) {
            Timber.INSTANCE.e(e, ERROR_MESSAGE_FOR_CAMERA_START, new Object[0]);
            callback.invoke(OnfidoCamera.CameraStatus.NotAvailable.INSTANCE);
        } catch (IllegalStateException e2) {
            Timber.INSTANCE.e(e2, ERROR_MESSAGE_FOR_CAMERA_START, new Object[0]);
            callback.invoke(new OnfidoCamera.CameraStatus.Failed(e2));
        }
    }

    static /* synthetic */ void setupCameraUseCases$default(CameraX cameraX, ViewPort viewPort, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            viewPort = null;
        }
        cameraX.setupCameraUseCases(viewPort, function1);
    }

    private final void setupCameraUseCasesWithViewPort(final Function1<? super OnfidoCamera.CameraStatus, Unit> callback) {
        Function1<ViewPort, Unit> function1 = new Function1<ViewPort, Unit>() { // from class: com.onfido.android.sdk.capture.internal.camera.camerax.CameraX$setupCameraUseCasesWithViewPort$viewPortSetup$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ViewPort viewPort) {
                invoke2(viewPort);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ViewPort viewPort) {
                if (viewPort == null && this.this$0.getCameraXConfiguration().isViewPortRequired()) {
                    callback.invoke(new OnfidoCamera.CameraStatus.Failed(new Throwable(CameraX.ERROR_VIEW_PORT_NOT_READY)));
                } else {
                    this.this$0.setupCameraUseCases(viewPort, callback);
                }
            }
        };
        if (getCameraXConfiguration().getShouldRetryViewPortFailure()) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.lifecycleOwner), null, null, new AnonymousClass1(function1, null), 3, null);
        } else {
            function1.invoke(this.previewView.getViewPort());
        }
    }

    private final void setupImageAnalysis() {
        ImageAnalysisConfig imageAnalysisConfig;
        if (!this.remoteConfig.isCameraxStreamSharingEnabled() || (imageAnalysisConfig = this.imageAnalysisConfig) == null) {
            return;
        }
        CameraXImageAnalysisUseCase cameraXImageAnalysisUseCase = this.cameraXImageAnalysisUseCase;
        PreviewView previewView = this.previewView;
        OnfidoCamera.CameraFacing cameraFacing = this.cameraFacing;
        if (cameraFacing == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraFacing");
            cameraFacing = null;
        }
        this.imageAnalysis = CameraXImageAnalysisUseCase.invoke$default(cameraXImageAnalysisUseCase, previewView, cameraFacing, imageAnalysisConfig, this.imageAnalyzer, null, 16, null);
    }

    private final void setupImageCapture() {
        if (this.imageCaptureConfig != null) {
            ResolutionSelector resolutionSelectorBuild = new ResolutionSelector.Builder().setResolutionStrategy(new ResolutionStrategy(this.imageCaptureConfig.getTargetResolution(), 1)).build();
            Intrinsics.checkNotNullExpressionValue(resolutionSelectorBuild, "build(...)");
            ImageCapture.Builder builder = new ImageCapture.Builder();
            if (getCameraXConfiguration().getShouldUseResolutionStrategy()) {
                builder.setResolutionSelector(resolutionSelectorBuild);
            }
            if (!getCameraXConfiguration().getShouldUseResolutionStrategy()) {
                builder.setTargetResolution(this.imageCaptureConfig.getTargetResolution());
            }
            this.imageCapture = builder.setCaptureMode(this.imageCaptureConfig.getCaptureMode()).setFlashMode(this.imageCaptureConfig.getFlashMode()).build();
        }
    }

    private final void setupPreview(final Function0<Unit> callback) {
        Preview previewBuild = new Preview.Builder().build();
        Intrinsics.checkNotNullExpressionValue(previewBuild, "build(...)");
        previewBuild.setSurfaceProvider(this.previewView.getSurfaceProvider());
        this.preview = previewBuild;
        this.previewView.getPreviewStreamState().observe(this.lifecycleOwner, new Observer<PreviewView.StreamState>() { // from class: com.onfido.android.sdk.capture.internal.camera.camerax.CameraX$setupPreview$observer$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(PreviewView.StreamState state) {
                if (state == PreviewView.StreamState.STREAMING) {
                    callback.invoke();
                    this.previewView.getPreviewStreamState().removeObserver(this);
                }
            }
        });
    }

    private final void setupVideoCapture() {
        if (this.videoCaptureConfig != null) {
            Quality quality = CameraXExtKt.getQUALITY_MAPPER().get(Integer.valueOf(this.videoCaptureConfig.getQualityProfile()));
            if (quality == null) {
                quality = DEFAULT_VIDEO_QUALITY;
            }
            Quality quality2 = quality;
            QualitySelector qualitySelectorFrom = QualitySelector.from(quality2, FallbackStrategy.higherQualityOrLowerThan(quality2));
            Intrinsics.checkNotNullExpressionValue(qualitySelectorFrom, "from(...)");
            Recorder recorderBuild = new Recorder.Builder().setAspectRatio(this.videoCaptureConfig.getAspectRatio()).setTargetVideoEncodingBitRate(this.videoCaptureConfig.getBitrate()).setQualitySelector(qualitySelectorFrom).build();
            Intrinsics.checkNotNullExpressionValue(recorderBuild, "build(...)");
            this.videoCapture = VideoCapture.withOutput(recorderBuild);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void start$lambda$0(final CameraX this$0, final Function1 callback) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        ProcessCameraProvider processCameraProvider = this$0.cameraProviderFuture.get();
        Intrinsics.checkNotNullExpressionValue(processCameraProvider, "get(...)");
        this$0.cameraProvider = processCameraProvider;
        this$0.setupPreview(new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.internal.camera.camerax.CameraX$start$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                callback.invoke(OnfidoCamera.CameraStatus.Started.INSTANCE);
                if (!this$0.remoteConfig.isCameraxStreamSharingEnabled()) {
                    this$0.frameSampler.mo5541onPreviewAvailableHG0u8IE(this$0.previewView, this$0.previewConfig.m5592getFrameSamplingPeriodUwyO8pc());
                }
                this$0.adjustPreviewViewCenter();
            }
        });
        this$0.setupImageCapture();
        this$0.setupVideoCapture();
        this$0.setupImageAnalysis();
        if (this$0.previewConfig.getViewPortEnabled()) {
            this$0.setupCameraUseCasesWithViewPort(callback);
        } else {
            setupCameraUseCases$default(this$0, null, callback, 1, null);
        }
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera
    public void centerPreviewAccordingTo(RectF rectF) {
        Intrinsics.checkNotNullParameter(rectF, "rectF");
        this.previewViewCenter = rectF;
        if (this.previewView.getHeight() != 0) {
            adjustPreviewViewCenter();
        }
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera
    public OnfidoCamera.CameraControl getCameraControl() {
        return new OnfidoCamera.CameraControl() { // from class: com.onfido.android.sdk.capture.internal.camera.camerax.CameraX$cameraControl$1
            @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera.CameraControl
            public void enableTorch(boolean enabled) {
                Camera camera = this.this$0.camera;
                Camera camera2 = null;
                if (camera == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(PermissionsTracker.CAMERA);
                    camera = null;
                }
                if (camera.getCameraInfo().hasFlashUnit()) {
                    Camera camera3 = this.this$0.camera;
                    if (camera3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(PermissionsTracker.CAMERA);
                    } else {
                        camera2 = camera3;
                    }
                    camera2.getCameraControl().enableTorch(enabled);
                }
            }
        };
    }

    /* renamed from: getImageCapture$onfido_capture_sdk_core_release, reason: from getter */
    public final ImageCapture getImageCapture() {
        return this.imageCapture;
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera
    public Observable<? extends Object> observeFrame() {
        return this.remoteConfig.isCameraxStreamSharingEnabled() ? this.imageAnalyzer.observeFrame() : this.frameSampler.observeFrame();
    }

    public final void setImageCapture$onfido_capture_sdk_core_release(ImageCapture imageCapture) {
        this.imageCapture = imageCapture;
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera
    public void start(OnfidoCamera.CameraFacing cameraFacing, final Function1<? super OnfidoCamera.CameraStatus, Unit> callback) {
        Intrinsics.checkNotNullParameter(cameraFacing, "cameraFacing");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.previewView.setScaleType(this.previewConfig.getScaleType());
        ViewExtensionsKt.toVisible$default(this.previewView, false, 1, null);
        this.cameraFacing = cameraFacing;
        try {
            this.cameraProviderFuture.addListener(new Runnable() { // from class: com.onfido.android.sdk.capture.internal.camera.camerax.CameraX$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() throws ExecutionException, InterruptedException {
                    CameraX.start$lambda$0(this.f$0, callback);
                }
            }, this.mainExecutor);
        } catch (RejectedExecutionException e) {
            callback.invoke(new OnfidoCamera.CameraStatus.Failed(e));
            Timber.INSTANCE.e(e, ERROR_MESSAGE_FOR_CAMERA_START, new Object[0]);
        }
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera
    public void stop() {
        this.compositeDisposable.clear();
        ProcessCameraProvider processCameraProvider = this.cameraProvider;
        if (processCameraProvider != null) {
            if (processCameraProvider == null) {
                try {
                    Intrinsics.throwUninitializedPropertyAccessException("cameraProvider");
                    processCameraProvider = null;
                } catch (IllegalArgumentException e) {
                    Timber.INSTANCE.e(e, "Unable to stop CameraX because of failing to unbind", new Object[0]);
                }
            }
            processCameraProvider.unbindAll();
        }
        this.frameSampler.close();
        this.cameraXImageAnalysisUseCase.close(this.imageAnalysis);
        ViewExtensionsKt.toGone$default(this.previewView, false, 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [androidx.lifecycle.Observer, com.onfido.android.sdk.capture.internal.camera.camerax.CameraX$takePicture$cameraStateObserver$1] */
    @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera
    public void takePicture(PhotoCaptureConfig photoCaptureConfig, final Function1<? super OnfidoCamera.PictureCaptureEvent, Unit> callback) {
        Intrinsics.checkNotNullParameter(photoCaptureConfig, "photoCaptureConfig");
        Intrinsics.checkNotNullParameter(callback, "callback");
        ImageCapture imageCapture = this.imageCapture;
        if (imageCapture == null) {
            return;
        }
        final ?? r2 = new Observer<CameraState>() { // from class: com.onfido.android.sdk.capture.internal.camera.camerax.CameraX$takePicture$cameraStateObserver$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(CameraState state) {
                Throwable th;
                Intrinsics.checkNotNullParameter(state, "state");
                CameraState.StateError error = state.getError();
                if (error == null || error.getCode() != 3) {
                    return;
                }
                CameraState.StateError error2 = state.getError();
                if (error2 == null || (th = error2.getCause()) == null) {
                    StringBuilder sb = new StringBuilder("CameraX failed to capture image with error code : ");
                    CameraState.StateError error3 = state.getError();
                    th = new Throwable(sb.append(error3 != null ? Integer.valueOf(error3.getCode()) : null).toString());
                }
                CameraX cameraX = this.this$0;
                cameraX.handleFailedImageCapture(th, this, callback, cameraX.getCameraXConfiguration().getShouldInterruptImageRecoverableError());
            }
        };
        try {
            ProcessCameraProvider processCameraProvider = this.cameraProvider;
            Camera camera = null;
            if (processCameraProvider == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cameraProvider");
                processCameraProvider = null;
            }
            if (!processCameraProvider.isBound(imageCapture)) {
                ProcessCameraProvider processCameraProvider2 = this.cameraProvider;
                if (processCameraProvider2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cameraProvider");
                    processCameraProvider2 = null;
                }
                processCameraProvider2.unbind(this.videoCapture);
                Companion companion = Companion;
                OnfidoCamera.CameraFacing cameraFacing = this.cameraFacing;
                if (cameraFacing == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cameraFacing");
                    cameraFacing = null;
                }
                CameraSelector cameraSelector = companion.toCameraSelector(cameraFacing);
                ProcessCameraProvider processCameraProvider3 = this.cameraProvider;
                if (processCameraProvider3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cameraProvider");
                    processCameraProvider3 = null;
                }
                Camera cameraBindToLifecycle = processCameraProvider3.bindToLifecycle(this.lifecycleOwner, cameraSelector, imageCapture);
                Intrinsics.checkNotNullExpressionValue(cameraBindToLifecycle, "bindToLifecycle(...)");
                this.camera = cameraBindToLifecycle;
            }
            Camera camera2 = this.camera;
            if (camera2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(PermissionsTracker.CAMERA);
            } else {
                camera = camera2;
            }
            camera.getCameraInfo().getCameraState().observe(this.lifecycleOwner, r2);
            this.cameraXTakePictureUseCase.invoke(imageCapture, this.previewView, new Function1<OnfidoCamera.PictureCaptureEvent, Unit>() { // from class: com.onfido.android.sdk.capture.internal.camera.camerax.CameraX.takePicture.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(OnfidoCamera.PictureCaptureEvent pictureCaptureEvent) {
                    invoke2(pictureCaptureEvent);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(OnfidoCamera.PictureCaptureEvent event) {
                    Intrinsics.checkNotNullParameter(event, "event");
                    Camera camera3 = CameraX.this.camera;
                    if (camera3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(PermissionsTracker.CAMERA);
                        camera3 = null;
                    }
                    camera3.getCameraInfo().getCameraState().removeObserver(r2);
                    callback.invoke(event);
                }
            });
        } catch (IllegalArgumentException | IllegalStateException e) {
            handleFailedImageCapture$default(this, e, r2, callback, false, 8, null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [androidx.lifecycle.Observer, com.onfido.android.sdk.capture.internal.camera.camerax.CameraX$takeVideo$cameraStateObserver$1] */
    @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera
    public OnfidoCamera.VideoRecorder takeVideo(final Function1<? super OnfidoCamera.VideoCaptureEvent, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        VideoCapture<Recorder> videoCapture = this.videoCapture;
        if (videoCapture == null) {
            return null;
        }
        final ?? r4 = new Observer<CameraState>() { // from class: com.onfido.android.sdk.capture.internal.camera.camerax.CameraX$takeVideo$cameraStateObserver$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(CameraState state) {
                Throwable th;
                Intrinsics.checkNotNullParameter(state, "state");
                CameraState.StateError error = state.getError();
                if (error == null || error.getCode() != 3) {
                    return;
                }
                CameraState.StateError error2 = state.getError();
                if (error2 == null || (th = error2.getCause()) == null) {
                    StringBuilder sb = new StringBuilder("CameraX failed to record video with error code : ");
                    CameraState.StateError error3 = state.getError();
                    th = new Throwable(sb.append(error3 != null ? Integer.valueOf(error3.getCode()) : null).toString());
                }
                CameraX cameraX = this.this$0;
                cameraX.handleFailedVideoRecording(th, this, callback, cameraX.getCameraXConfiguration().getShouldInterruptVideoRecoverableError());
            }
        };
        try {
            ProcessCameraProvider processCameraProvider = this.cameraProvider;
            if (processCameraProvider == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cameraProvider");
                processCameraProvider = null;
            }
            if (!processCameraProvider.isBound(videoCapture)) {
                ProcessCameraProvider processCameraProvider2 = this.cameraProvider;
                if (processCameraProvider2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cameraProvider");
                    processCameraProvider2 = null;
                }
                processCameraProvider2.unbind(this.imageCapture);
                OnfidoCamera.CameraFacing cameraFacing = this.cameraFacing;
                if (cameraFacing == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cameraFacing");
                    cameraFacing = null;
                }
                if (cameraFacing == OnfidoCamera.CameraFacing.BACK && getCameraXConfiguration().getShouldRemovePreviewVideoRecording()) {
                    ProcessCameraProvider processCameraProvider3 = this.cameraProvider;
                    if (processCameraProvider3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("cameraProvider");
                        processCameraProvider3 = null;
                    }
                    Preview preview = this.preview;
                    if (preview == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("preview");
                        preview = null;
                    }
                    processCameraProvider3.unbind(preview);
                    this.frameSampler.close();
                }
                Companion companion = Companion;
                OnfidoCamera.CameraFacing cameraFacing2 = this.cameraFacing;
                if (cameraFacing2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cameraFacing");
                    cameraFacing2 = null;
                }
                CameraSelector cameraSelector = companion.toCameraSelector(cameraFacing2);
                ProcessCameraProvider processCameraProvider4 = this.cameraProvider;
                if (processCameraProvider4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cameraProvider");
                    processCameraProvider4 = null;
                }
                Camera cameraBindToLifecycle = processCameraProvider4.bindToLifecycle(this.lifecycleOwner, cameraSelector, videoCapture);
                Intrinsics.checkNotNullExpressionValue(cameraBindToLifecycle, "bindToLifecycle(...)");
                this.camera = cameraBindToLifecycle;
                if (cameraBindToLifecycle == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(PermissionsTracker.CAMERA);
                    cameraBindToLifecycle = null;
                }
                cameraBindToLifecycle.getCameraInfo().getCameraState().observe(this.lifecycleOwner, r4);
            }
            CameraXTakeVideoUseCase cameraXTakeVideoUseCase = this.cameraXTakeVideoUseCase;
            VideoCaptureConfig videoCaptureConfig = this.videoCaptureConfig;
            Intrinsics.checkNotNull(videoCaptureConfig);
            return cameraXTakeVideoUseCase.invoke(videoCapture, videoCaptureConfig, new Function1<OnfidoCamera.VideoCaptureEvent, Unit>() { // from class: com.onfido.android.sdk.capture.internal.camera.camerax.CameraX.takeVideo.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(OnfidoCamera.VideoCaptureEvent videoCaptureEvent) {
                    invoke2(videoCaptureEvent);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(OnfidoCamera.VideoCaptureEvent event) {
                    Intrinsics.checkNotNullParameter(event, "event");
                    Camera camera = CameraX.this.camera;
                    if (camera == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(PermissionsTracker.CAMERA);
                        camera = null;
                    }
                    camera.getCameraInfo().getCameraState().removeObserver(r4);
                    callback.invoke(event);
                }
            });
        } catch (IllegalArgumentException | IllegalStateException e) {
            handleFailedVideoRecording$default(this, e, r4, callback, false, 8, null);
            return null;
        }
    }
}
