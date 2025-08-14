package com.rncamerakit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.RectF;
import android.media.AudioManager;
import android.media.MediaActionSound;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.MeteringPointFactory;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.core.ZoomState;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.media3.common.MimeTypes;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.google.common.util.concurrent.ListenableFuture;
import com.henninghall.date_picker.props.ModeProp;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.PermissionsTracker;
import com.rncamerakit.barcode.BarcodeFrame;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

/* compiled from: CKCamera.kt */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0019\b\u0007\u0018\u0000 ]2\u00020\u00012\u00020\u0002:\u0001]B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010(\u001a\u00020\u00142\u0006\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020\u0014H\u0002J\b\u0010+\u001a\u00020,H\u0002J\"\u0010-\u001a\u00020,2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002000/2\u0006\u00101\u001a\u000202J\u0018\u00103\u001a\u00020\u00142\u0006\u00104\u001a\u00020\u00142\u0006\u00105\u001a\u00020\u0014H\u0002J\b\u00106\u001a\u00020,H\u0002J!\u00107\u001a\u00020,2\b\u00108\u001a\u0004\u0018\u0001092\b\u0010:\u001a\u0004\u0018\u000109H\u0002¢\u0006\u0002\u0010;J\b\u0010<\u001a\u00020=H\u0002J\b\u0010>\u001a\u00020#H\u0002J\u0010\u0010?\u001a\u00020,2\u0006\u0010@\u001a\u00020AH\u0002J\b\u0010B\u001a\u00020,H\u0014J\u0016\u0010C\u001a\u00020,2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00070EH\u0002J\b\u0010F\u001a\u00020,H\u0014J\u0010\u0010G\u001a\u00020,2\u0006\u0010H\u001a\u00020\u0014H\u0002J\u0010\u0010I\u001a\u00020,2\u0006\u0010J\u001a\u00020\u0007H\u0002J\u0010\u0010K\u001a\u00020,2\b\b\u0002\u0010L\u001a\u00020\u0007J\u0010\u0010M\u001a\u00020,2\b\b\u0002\u0010N\u001a\u00020\u0007J\u0010\u0010O\u001a\u00020,2\b\u0010L\u001a\u0004\u0018\u00010\u0007J\u0010\u0010P\u001a\u00020,2\b\b\u0001\u0010Q\u001a\u00020\u0014J\u0010\u0010R\u001a\u00020,2\b\b\u0001\u0010Q\u001a\u00020\u0014J\u000e\u0010S\u001a\u00020,2\u0006\u0010T\u001a\u00020\u0007J\u000e\u0010U\u001a\u00020,2\u0006\u0010V\u001a\u00020#J\u000e\u0010W\u001a\u00020,2\u0006\u0010V\u001a\u00020#J\u000e\u0010X\u001a\u00020,2\u0006\u0010Y\u001a\u00020\u0014J\u0010\u0010Z\u001a\u00020,2\b\u0010L\u001a\u0004\u0018\u00010\u0007J\u0010\u0010[\u001a\u00020,2\b\b\u0002\u0010L\u001a\u00020\u0007J\b\u0010\\\u001a\u00020,H\u0003R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006^"}, d2 = {"Lcom/rncamerakit/CKCamera;", "Landroid/widget/FrameLayout;", "Landroidx/lifecycle/LifecycleObserver;", "context", "Lcom/facebook/react/uimanager/ThemedReactContext;", "(Lcom/facebook/react/uimanager/ThemedReactContext;)V", "autoFocus", "", "barcodeFrame", "Lcom/rncamerakit/barcode/BarcodeFrame;", PermissionsTracker.CAMERA, "Landroidx/camera/core/Camera;", "cameraExecutor", "Ljava/util/concurrent/ExecutorService;", "cameraProvider", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "currentContext", "effectLayer", "Landroid/view/View;", "frameColor", "", "imageAnalyzer", "Landroidx/camera/core/ImageAnalysis;", "imageCapture", "Landroidx/camera/core/ImageCapture;", "laserColor", "lensType", "orientationListener", "Landroid/view/OrientationEventListener;", "outputPath", "preview", "Landroidx/camera/core/Preview;", "rectOverlay", "Lcom/rncamerakit/RectOverlay;", "scanBarcode", "", "shutterAnimationDuration", "viewFinder", "Landroidx/camera/view/PreviewView;", "zoomMode", ViewProps.ASPECT_RATIO, "width", "height", "bindCameraUseCases", "", "capture", RRWebOptionsEvent.EVENT_TAG, "", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "convertDeviceHeightToSupportedAspectRatio", "actualWidth", "actualHeight", "flashViewFinder", "focusOnPoint", "x", "", "y", "(Ljava/lang/Float;Ljava/lang/Float;)V", "getActivity", "Landroid/app/Activity;", "hasPermissions", "installHierarchyFitter", "view", "Landroid/view/ViewGroup;", "onAttachedToWindow", "onBarcodeRead", "barcodes", "", "onDetachedFromWindow", "onOrientationChange", "orientation", "onPictureTaken", ReactNativeBlobUtilConst.DATA_ENCODE_URI, "setAutoFocus", ModeProp.name, "setCameraType", "type", "setFlashMode", "setFrameColor", "color", "setLaserColor", "setOutputPath", ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH, "setScanBarcode", ViewProps.ENABLED, "setShowFrame", "setShutterAnimationDuration", "duration", "setTorchMode", "setZoomMode", "setupCamera", "Companion", "react-native-camera-kit_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class CKCamera extends FrameLayout implements LifecycleObserver {
    private static final double RATIO_16_9_VALUE = 1.7777777777777777d;
    private static final double RATIO_4_3_VALUE = 1.3333333333333333d;
    private static final String TAG = "CameraKit";
    private String autoFocus;
    private BarcodeFrame barcodeFrame;
    private Camera camera;
    private ExecutorService cameraExecutor;
    private ProcessCameraProvider cameraProvider;
    private final ThemedReactContext currentContext;
    private View effectLayer;
    private int frameColor;
    private ImageAnalysis imageAnalyzer;
    private ImageCapture imageCapture;
    private int laserColor;
    private int lensType;
    private OrientationEventListener orientationListener;
    private String outputPath;
    private Preview preview;
    private RectOverlay rectOverlay;
    private boolean scanBarcode;
    private int shutterAnimationDuration;
    private PreviewView viewFinder;
    private String zoomMode;

    public final void setOutputPath(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        this.outputPath = path;
    }

    public final void setShutterAnimationDuration(int duration) {
        this.shutterAnimationDuration = duration;
    }

    public final void setZoomMode(String mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        this.zoomMode = mode;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public CKCamera(ThemedReactContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ThemedReactContext themedReactContext = context;
        super(themedReactContext);
        this.currentContext = context;
        this.viewFinder = new PreviewView(themedReactContext);
        this.rectOverlay = new RectOverlay(themedReactContext);
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor(...)");
        this.cameraExecutor = executorServiceNewSingleThreadExecutor;
        this.shutterAnimationDuration = 50;
        this.effectLayer = new View(themedReactContext);
        this.lensType = 1;
        this.autoFocus = "on";
        this.zoomMode = "on";
        this.frameColor = -16711936;
        this.laserColor = SupportMenu.CATEGORY_MASK;
        this.viewFinder.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        installHierarchyFitter(this.viewFinder);
        addView(this.viewFinder);
        this.effectLayer.setAlpha(0.0f);
        this.effectLayer.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        addView(this.effectLayer);
        addView(this.rectOverlay);
    }

    private final Activity getActivity() {
        Activity currentActivity = this.currentContext.getCurrentActivity();
        Intrinsics.checkNotNull(currentActivity);
        return currentActivity;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (hasPermissions()) {
            this.viewFinder.post(new Runnable() { // from class: com.rncamerakit.CKCamera$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    CKCamera.onAttachedToWindow$lambda$0(this.f$0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onAttachedToWindow$lambda$0(CKCamera this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setupCamera();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.cameraExecutor.shutdown();
        OrientationEventListener orientationEventListener = this.orientationListener;
        if (orientationEventListener != null) {
            orientationEventListener.disable();
        }
        ProcessCameraProvider processCameraProvider = this.cameraProvider;
        if (processCameraProvider != null) {
            processCameraProvider.unbindAll();
        }
    }

    private final void installHierarchyFitter(ViewGroup view) {
        Log.d(TAG, "CameraView looking for ThemedReactContext");
        if (getContext() instanceof ThemedReactContext) {
            Log.d(TAG, "CameraView found ThemedReactContext");
            view.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() { // from class: com.rncamerakit.CKCamera.installHierarchyFitter.1
                @Override // android.view.ViewGroup.OnHierarchyChangeListener
                public void onChildViewRemoved(View parent, View child) {
                }

                @Override // android.view.ViewGroup.OnHierarchyChangeListener
                public void onChildViewAdded(View parent, View child) {
                    if (parent != null) {
                        parent.measure(View.MeasureSpec.makeMeasureSpec(CKCamera.this.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(CKCamera.this.getMeasuredHeight(), 1073741824));
                    }
                    if (parent != null) {
                        parent.layout(0, 0, parent.getMeasuredWidth(), parent.getMeasuredHeight());
                    }
                }
            });
        }
    }

    private final void setupCamera() {
        final ListenableFuture<ProcessCameraProvider> processCameraProvider = ProcessCameraProvider.getInstance(getActivity());
        Intrinsics.checkNotNullExpressionValue(processCameraProvider, "getInstance(...)");
        processCameraProvider.addListener(new Runnable() { // from class: com.rncamerakit.CKCamera$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                CKCamera.setupCamera$lambda$2(this.f$0, processCameraProvider);
            }
        }, ContextCompat.getMainExecutor(getActivity()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void setupCamera$lambda$2(final CKCamera this$0, ListenableFuture cameraProviderFuture) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(cameraProviderFuture, "$cameraProviderFuture");
        this$0.cameraProvider = (ProcessCameraProvider) cameraProviderFuture.get();
        final Context context = this$0.getContext();
        OrientationEventListener orientationEventListener = new OrientationEventListener(context) { // from class: com.rncamerakit.CKCamera$setupCamera$1$1
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int orientation) {
                ImageCapture imageCapture = this.this$0.imageCapture;
                if (imageCapture == null) {
                    return;
                }
                int targetRotation = imageCapture.getTargetRotation();
                if (orientation >= 315 || orientation < 45) {
                    targetRotation = 0;
                } else if (225 <= orientation && orientation < 315) {
                    targetRotation = 1;
                } else if (135 <= orientation && orientation < 225) {
                    targetRotation = 2;
                } else if (45 <= orientation && orientation < 135) {
                    targetRotation = 3;
                }
                if (targetRotation != imageCapture.getTargetRotation()) {
                    imageCapture.setTargetRotation(targetRotation);
                    this.this$0.onOrientationChange(targetRotation);
                }
            }
        };
        this$0.orientationListener = orientationEventListener;
        Intrinsics.checkNotNull(orientationEventListener);
        orientationEventListener.enable();
        final ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(this$0.getContext(), new ScaleGestureDetector.SimpleOnScaleGestureListener() { // from class: com.rncamerakit.CKCamera$setupCamera$1$scaleDetector$1
            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(ScaleGestureDetector detector) {
                Camera camera;
                CameraControl cameraControl;
                Camera camera2;
                CameraInfo cameraInfo;
                LiveData<ZoomState> zoomState;
                ZoomState value;
                if (!Intrinsics.areEqual(this.this$0.zoomMode, DebugKt.DEBUG_PROPERTY_VALUE_OFF) && (camera = this.this$0.camera) != null && (cameraControl = camera.getCameraControl()) != null && (camera2 = this.this$0.camera) != null && (cameraInfo = camera2.getCameraInfo()) != null && (zoomState = cameraInfo.getZoomState()) != null && (value = zoomState.getValue()) != null) {
                    float zoomRatio = value.getZoomRatio();
                    if (detector != null) {
                        cameraControl.setZoomRatio(zoomRatio * detector.getScaleFactor());
                    }
                }
                return true;
            }
        });
        this$0.viewFinder.setOnTouchListener(new View.OnTouchListener() { // from class: com.rncamerakit.CKCamera$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return CKCamera.setupCamera$lambda$2$lambda$1(scaleGestureDetector, this$0, view, motionEvent);
            }
        });
        this$0.bindCameraUseCases();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean setupCamera$lambda$2$lambda$1(ScaleGestureDetector scaleDetector, CKCamera this$0, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(scaleDetector, "$scaleDetector");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (motionEvent.getAction() != 1) {
            return scaleDetector.onTouchEvent(motionEvent);
        }
        this$0.focusOnPoint(Float.valueOf(motionEvent.getX()), Float.valueOf(motionEvent.getY()));
        return true;
    }

    private final void bindCameraUseCases() {
        if (this.viewFinder.getDisplay() == null) {
            return;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.viewFinder.getDisplay().getRealMetrics(displayMetrics);
        Log.d(TAG, "Screen metrics: " + displayMetrics.widthPixels + " x " + displayMetrics.heightPixels);
        int iAspectRatio = aspectRatio(displayMetrics.widthPixels, displayMetrics.heightPixels);
        Log.d(TAG, "Preview aspect ratio: " + iAspectRatio);
        int rotation = this.viewFinder.getDisplay().getRotation();
        ProcessCameraProvider processCameraProvider = this.cameraProvider;
        if (processCameraProvider == null) {
            throw new IllegalStateException("Camera initialization failed.");
        }
        CameraSelector cameraSelectorBuild = new CameraSelector.Builder().requireLensFacing(this.lensType).build();
        Intrinsics.checkNotNullExpressionValue(cameraSelectorBuild, "build(...)");
        this.preview = new Preview.Builder().setTargetAspectRatio(iAspectRatio).setTargetRotation(rotation).build();
        this.imageCapture = new ImageCapture.Builder().setCaptureMode(1).setTargetAspectRatio(iAspectRatio).setTargetRotation(rotation).build();
        this.imageAnalyzer = new ImageAnalysis.Builder().setBackpressureStrategy(0).build();
        List listMutableListOf = CollectionsKt.mutableListOf(this.preview, this.imageCapture);
        if (this.scanBarcode) {
            QRCodeAnalyzer qRCodeAnalyzer = new QRCodeAnalyzer(new Function1<List<? extends String>, Unit>() { // from class: com.rncamerakit.CKCamera$bindCameraUseCases$analyzer$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(List<? extends String> list) {
                    invoke2((List<String>) list);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(List<String> barcodes) {
                    Intrinsics.checkNotNullParameter(barcodes, "barcodes");
                    if (!barcodes.isEmpty()) {
                        this.this$0.onBarcodeRead(barcodes);
                    }
                }
            });
            ImageAnalysis imageAnalysis = this.imageAnalyzer;
            Intrinsics.checkNotNull(imageAnalysis);
            imageAnalysis.setAnalyzer(this.cameraExecutor, qRCodeAnalyzer);
            listMutableListOf.add(this.imageAnalyzer);
        }
        processCameraProvider.unbindAll();
        try {
            Activity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
            UseCase[] useCaseArr = (UseCase[]) listMutableListOf.toArray(new UseCase[0]);
            this.camera = processCameraProvider.bindToLifecycle((AppCompatActivity) activity, cameraSelectorBuild, (UseCase[]) Arrays.copyOf(useCaseArr, useCaseArr.length));
            Preview preview = this.preview;
            if (preview != null) {
                preview.setSurfaceProvider(this.viewFinder.getSurfaceProvider());
            }
        } catch (Exception e) {
            Log.e(TAG, "Use case binding failed", e);
        }
    }

    private final int aspectRatio(int width, int height) {
        double dMax = Math.max(width, height) / Math.min(width, height);
        return Math.abs(dMax - RATIO_4_3_VALUE) <= Math.abs(dMax - RATIO_16_9_VALUE) ? 0 : 1;
    }

    private final void flashViewFinder() {
        if (this.shutterAnimationDuration == 0) {
            return;
        }
        this.effectLayer.animate().alpha(1.0f).setDuration(this.shutterAnimationDuration).setListener(new AnimatorListenerAdapter() { // from class: com.rncamerakit.CKCamera.flashViewFinder.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                CKCamera.this.effectLayer.animate().alpha(0.0f).setDuration(CKCamera.this.shutterAnimationDuration);
            }
        }).start();
    }

    public final void capture(Map<String, ? extends Object> options, final Promise promise) throws IOException {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(promise, "promise");
        final String canonicalPath = this.outputPath;
        if (canonicalPath != null) {
            Intrinsics.checkNotNull(canonicalPath);
        } else {
            File fileCreateTempFile = File.createTempFile("ckcap", ".jpg", getContext().getCacheDir());
            fileCreateTempFile.deleteOnExit();
            canonicalPath = fileCreateTempFile.getCanonicalPath();
            Intrinsics.checkNotNull(canonicalPath);
        }
        final File file = new File(canonicalPath);
        ImageCapture.OutputFileOptions outputFileOptionsBuild = new ImageCapture.OutputFileOptions.Builder(file).build();
        Intrinsics.checkNotNullExpressionValue(outputFileOptionsBuild, "build(...)");
        flashViewFinder();
        Object systemService = getActivity().getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        if (((AudioManager) systemService).getRingerMode() == 2) {
            new MediaActionSound().play(0);
        }
        ImageCapture imageCapture = this.imageCapture;
        if (imageCapture != null) {
            imageCapture.m124lambda$takePicture$2$androidxcameracoreImageCapture(outputFileOptionsBuild, ContextCompat.getMainExecutor(getActivity()), new ImageCapture.OnImageSavedCallback() { // from class: com.rncamerakit.CKCamera.capture.1
                @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
                public void onError(ImageCaptureException ex) {
                    Intrinsics.checkNotNullParameter(ex, "ex");
                    Log.e(CKCamera.TAG, "CameraView: Photo capture failed: " + ex.getMessage(), ex);
                    promise.reject("E_CAPTURE_FAILED", "takePicture failed: " + ex.getMessage());
                }

                @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
                public void onImageSaved(ImageCapture.OutputFileResults output) {
                    Intrinsics.checkNotNullParameter(output, "output");
                    try {
                        Uri savedUri = output.getSavedUri();
                        if (savedUri == null) {
                            savedUri = Uri.fromFile(file);
                        }
                        String path = savedUri != null ? savedUri.getPath() : null;
                        String lastPathSegment = savedUri != null ? savedUri.getLastPathSegment() : null;
                        String path2 = savedUri != null ? savedUri.getPath() : null;
                        Comparable savedUri2 = output.getSavedUri();
                        if (savedUri2 == null) {
                            savedUri2 = canonicalPath;
                        }
                        String string = savedUri2.toString();
                        this.onPictureTaken(string);
                        Log.d(CKCamera.TAG, "CameraView: Photo capture succeeded: " + string);
                        WritableMap writableMapCreateMap = Arguments.createMap();
                        writableMapCreateMap.putString(ReactNativeBlobUtilConst.DATA_ENCODE_URI, savedUri.toString());
                        writableMapCreateMap.putString("id", path);
                        writableMapCreateMap.putString("name", lastPathSegment);
                        writableMapCreateMap.putInt("width", this.getWidth());
                        writableMapCreateMap.putInt("height", this.getHeight());
                        writableMapCreateMap.putString(ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH, path2);
                        promise.resolve(writableMapCreateMap);
                    } catch (Exception e) {
                        Log.e(CKCamera.TAG, "Error while saving or decoding saved photo: " + e.getMessage(), e);
                        promise.reject("E_ON_IMG_SAVED", "Error while reading saved photo: " + e.getMessage());
                    }
                }
            });
        }
    }

    private final void focusOnPoint(Float x, Float y) {
        CameraControl cameraControl;
        CameraControl cameraControl2;
        if (x == null || y == null) {
            Camera camera = this.camera;
            if (camera == null || (cameraControl = camera.getCameraControl()) == null) {
                return;
            }
            cameraControl.cancelFocusAndMetering();
            return;
        }
        MeteringPointFactory meteringPointFactory = this.viewFinder.getMeteringPointFactory();
        Intrinsics.checkNotNullExpressionValue(meteringPointFactory, "getMeteringPointFactory(...)");
        FocusMeteringAction.Builder builder = new FocusMeteringAction.Builder(meteringPointFactory.createPoint(x.floatValue(), y.floatValue()));
        if (Intrinsics.areEqual(this.autoFocus, DebugKt.DEBUG_PROPERTY_VALUE_OFF)) {
            builder.disableAutoCancel();
        }
        Camera camera2 = this.camera;
        if (camera2 != null && (cameraControl2 = camera2.getCameraControl()) != null) {
            cameraControl2.startFocusAndMetering(builder.build());
        }
        float f = 75;
        this.rectOverlay.drawRectBounds(CollectionsKt.listOf(new RectF(x.floatValue() - f, y.floatValue() - f, x.floatValue() + f, y.floatValue() + f)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onBarcodeRead(List<String> barcodes) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        Intrinsics.checkNotNullExpressionValue(writableMapCreateMap, "createMap(...)");
        writableMapCreateMap.putString("codeStringValue", (String) CollectionsKt.first((List) barcodes));
        ((RCTEventEmitter) this.currentContext.getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), "onReadCode", writableMapCreateMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onOrientationChange(int orientation) {
        int i;
        if (orientation != 0) {
            i = 1;
            if (orientation != 1) {
                i = 2;
                if (orientation != 2) {
                    i = 3;
                    if (orientation != 3) {
                        Log.e(TAG, "CameraView: Unknown device orientation detected: " + orientation);
                        return;
                    }
                }
            }
        } else {
            i = 0;
        }
        WritableMap writableMapCreateMap = Arguments.createMap();
        Intrinsics.checkNotNullExpressionValue(writableMapCreateMap, "createMap(...)");
        writableMapCreateMap.putInt("orientation", i);
        ((RCTEventEmitter) this.currentContext.getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), "onOrientationChange", writableMapCreateMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPictureTaken(String uri) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        Intrinsics.checkNotNullExpressionValue(writableMapCreateMap, "createMap(...)");
        writableMapCreateMap.putString(ReactNativeBlobUtilConst.DATA_ENCODE_URI, uri);
        ((RCTEventEmitter) this.currentContext.getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), "onPictureTaken", writableMapCreateMap);
    }

    public final void setFlashMode(String mode) {
        Camera camera;
        ImageCapture imageCapture = this.imageCapture;
        if (imageCapture == null || (camera = this.camera) == null) {
            return;
        }
        if (Intrinsics.areEqual(mode, "on")) {
            camera.getCameraControl().enableTorch(false);
            imageCapture.setFlashMode(1);
        } else if (Intrinsics.areEqual(mode, DebugKt.DEBUG_PROPERTY_VALUE_OFF)) {
            camera.getCameraControl().enableTorch(false);
            imageCapture.setFlashMode(2);
        } else {
            imageCapture.setFlashMode(0);
            camera.getCameraControl().enableTorch(false);
        }
    }

    public final void setTorchMode(String mode) {
        Camera camera = this.camera;
        if (camera == null) {
            return;
        }
        if (Intrinsics.areEqual(mode, "on")) {
            camera.getCameraControl().enableTorch(true);
        } else if (Intrinsics.areEqual(mode, DebugKt.DEBUG_PROPERTY_VALUE_OFF)) {
            camera.getCameraControl().enableTorch(false);
        } else {
            camera.getCameraControl().enableTorch(false);
        }
    }

    public static /* synthetic */ void setAutoFocus$default(CKCamera cKCamera, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "on";
        }
        cKCamera.setAutoFocus(str);
    }

    public final void setAutoFocus(String mode) {
        Camera camera;
        CameraControl cameraControl;
        Intrinsics.checkNotNullParameter(mode, "mode");
        this.autoFocus = mode;
        if (!Intrinsics.areEqual(mode, "on") || (camera = this.camera) == null || (cameraControl = camera.getCameraControl()) == null) {
            return;
        }
        cameraControl.cancelFocusAndMetering();
    }

    public static /* synthetic */ void setZoomMode$default(CKCamera cKCamera, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "on";
        }
        cKCamera.setZoomMode(str);
    }

    public final void setScanBarcode(boolean enabled) {
        boolean z = enabled != this.scanBarcode;
        this.scanBarcode = enabled;
        if (z) {
            bindCameraUseCases();
        }
    }

    public static /* synthetic */ void setCameraType$default(CKCamera cKCamera, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "back";
        }
        cKCamera.setCameraType(str);
    }

    public final void setCameraType(String type) {
        Intrinsics.checkNotNullParameter(type, "type");
        int i = !Intrinsics.areEqual(type, "front") ? 1 : 0;
        boolean z = this.lensType != i;
        this.lensType = i;
        if (z) {
            bindCameraUseCases();
        }
    }

    public final void setShowFrame(boolean enabled) {
        if (!enabled) {
            BarcodeFrame barcodeFrame = this.barcodeFrame;
            if (barcodeFrame != null) {
                removeView(barcodeFrame);
                this.barcodeFrame = null;
                return;
            }
            return;
        }
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        this.barcodeFrame = new BarcodeFrame(context);
        int i = getResources().getDisplayMetrics().widthPixels;
        int iConvertDeviceHeightToSupportedAspectRatio = convertDeviceHeightToSupportedAspectRatio(i, getResources().getDisplayMetrics().heightPixels);
        BarcodeFrame barcodeFrame2 = this.barcodeFrame;
        Intrinsics.checkNotNull(barcodeFrame2);
        barcodeFrame2.setFrameColor(this.frameColor);
        BarcodeFrame barcodeFrame3 = this.barcodeFrame;
        Intrinsics.checkNotNull(barcodeFrame3);
        barcodeFrame3.setLaserColor(this.laserColor);
        BarcodeFrame barcodeFrame4 = this.barcodeFrame;
        Intrinsics.checkNotNull(barcodeFrame4, "null cannot be cast to non-null type android.view.View");
        barcodeFrame4.layout(0, 0, i, iConvertDeviceHeightToSupportedAspectRatio);
        addView(this.barcodeFrame);
    }

    public final void setLaserColor(int color) {
        this.laserColor = color;
        BarcodeFrame barcodeFrame = this.barcodeFrame;
        if (barcodeFrame != null) {
            Intrinsics.checkNotNull(barcodeFrame);
            barcodeFrame.setLaserColor(this.laserColor);
        }
    }

    public final void setFrameColor(int color) {
        this.frameColor = color;
        BarcodeFrame barcodeFrame = this.barcodeFrame;
        if (barcodeFrame != null) {
            Intrinsics.checkNotNull(barcodeFrame);
            barcodeFrame.setFrameColor(color);
        }
    }

    private final int convertDeviceHeightToSupportedAspectRatio(int actualWidth, int actualHeight) {
        return (((float) (actualHeight / actualWidth)) > 1.7777778f ? Float.valueOf(actualWidth * 1.7777778f) : Integer.valueOf(actualHeight)).intValue();
    }

    private final boolean hasPermissions() {
        String[] strArr = {"android.permission.CAMERA"};
        if (ContextCompat.checkSelfPermission(getContext(), strArr[0]) == 0) {
            return true;
        }
        ActivityCompat.requestPermissions(getActivity(), strArr, 42);
        return false;
    }
}
