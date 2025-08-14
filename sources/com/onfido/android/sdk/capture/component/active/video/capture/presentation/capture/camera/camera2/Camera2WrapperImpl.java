package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Range;
import android.view.Surface;
import android.view.ViewTreeObserver;
import androidx.camera.viewfinder.CameraViewfinder;
import androidx.camera.viewfinder.CameraViewfinderExt;
import androidx.camera.viewfinder.ViewfinderSurfaceRequest;
import androidx.camera.viewfinder.ViewfinderSurfaceRequestUtil;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewKt;
import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.util.SurfaceSizeProvider;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.PermissionsTracker;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import org.jmrtd.lds.LDSFile;

@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020&H\u0016JI\u0010(\u001a\u00020&2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020 0*2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020&0,2#\b\u0002\u0010-\u001a\u001d\u0012\u0013\u0012\u00110/¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020&0.H\u0002J!\u00103\u001a\u0002042\u0012\u0010)\u001a\n\u0012\u0006\b\u0001\u0012\u00020 05\"\u00020 H\u0002¢\u0006\u0002\u00106J!\u00107\u001a\u0002042\u0012\u0010)\u001a\n\u0012\u0006\b\u0001\u0012\u00020 05\"\u00020 H\u0002¢\u0006\u0002\u00106JN\u00108\u001a\u00020&2!\u00109\u001a\u001d\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020&0.2!\u0010-\u001a\u001d\u0012\u0013\u0012\u00110/¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020&0.H\u0016J\n\u0010:\u001a\u0004\u0018\u00010\"H\u0002J9\u0010;\u001a\u00020&2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020&0,2!\u0010-\u001a\u001d\u0012\u0013\u0012\u00110/¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020&0.H\u0003J\u0010\u0010=\u001a\u00020&2\u0006\u0010>\u001a\u00020 H\u0016J\u001e\u0010?\u001a\u00020&2\u0006\u0010@\u001a\u00020A2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020&0,H\u0002J\b\u0010C\u001a\u00020&H\u0016J\u0010\u0010D\u001a\u00020&2\u0006\u0010>\u001a\u00020 H\u0016J\u001e\u0010E\u001a\u00020&2\u0006\u0010@\u001a\u00020A2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020&0,H\u0002JI\u0010F\u001a\u00020&2\u0006\u0010G\u001a\u00020A2\u0006\u0010>\u001a\u00020 2\f\u00109\u001a\b\u0012\u0004\u0012\u00020&0,2!\u0010-\u001a\u001d\u0012\u0013\u0012\u00110/¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020&0.H\u0016J\b\u0010H\u001a\u00020&H\u0016R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0018X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/Camera2WrapperImpl;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/Camera2Wrapper;", "context", "Landroid/content/Context;", "surfaceSizeProvider", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/SurfaceSizeProvider;", "motionVideoSettings", "Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture$MotionVideoSettings;", "(Landroid/content/Context;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/SurfaceSizeProvider;Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture$MotionVideoSettings;)V", "cameraDevice", "Landroid/hardware/camera2/CameraDevice;", "cameraHandler", "Landroid/os/Handler;", "cameraManager", "Landroid/hardware/camera2/CameraManager;", "getCameraManager", "()Landroid/hardware/camera2/CameraManager;", "cameraManager$delegate", "Lkotlin/Lazy;", "cameraThread", "Landroid/os/HandlerThread;", "captureSession", "Landroid/hardware/camera2/CameraCaptureSession;", "characteristics", "Landroid/hardware/camera2/CameraCharacteristics;", "getCharacteristics", "()Landroid/hardware/camera2/CameraCharacteristics;", "setCharacteristics", "(Landroid/hardware/camera2/CameraCharacteristics;)V", "layoutChangedListener", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "previewSurface", "Landroid/view/Surface;", "selfieCameraId", "", "viewfinderSurfaceRequest", "Landroidx/camera/viewfinder/ViewfinderSurfaceRequest;", "cleanup", "", "closeCamera", "createCaptureSession", "surfaces", "", "onConfigured", "Lkotlin/Function0;", "onError", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "error", "createPreviewRequest", "Landroid/hardware/camera2/CaptureRequest;", "", "([Landroid/view/Surface;)Landroid/hardware/camera2/CaptureRequest;", "createRecordRequest", "findSelfieCamera", "onSuccess", "getFrontFacingCamera", "openCamera", "onOpened", "resetCaptureSession", "recorderSurface", "sendSurfaceRequest", "cameraViewFinder", "Landroidx/camera/viewfinder/CameraViewfinder;", "onPreviewSurfaceReady", "setPreviewMode", "setRecordingMode", "setupCameraViewFinder", ViewProps.START, "cameraViewfinder", "stopPreview", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Camera2WrapperImpl implements Camera2Wrapper {
    private CameraDevice cameraDevice;
    private final Handler cameraHandler;

    /* renamed from: cameraManager$delegate, reason: from kotlin metadata */
    private final Lazy cameraManager;
    private final HandlerThread cameraThread;
    private CameraCaptureSession captureSession;
    public CameraCharacteristics characteristics;
    private final Context context;
    private ViewTreeObserver.OnGlobalLayoutListener layoutChangedListener;
    private final SdkConfiguration.MotionCapture.MotionVideoSettings motionVideoSettings;
    private Surface previewSurface;
    private String selfieCameraId;
    private final SurfaceSizeProvider surfaceSizeProvider;
    private ViewfinderSurfaceRequest viewfinderSurfaceRequest;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2WrapperImpl$sendSurfaceRequest$1", f = "Camera2WrapperImpl.kt", i = {}, l = {LDSFile.EF_DG2_TAG}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2WrapperImpl$sendSurfaceRequest$1, reason: invalid class name and case insensitive filesystem */
    static final class C05371 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ CameraViewfinder $cameraViewFinder;
        final /* synthetic */ Function0<Unit> $onPreviewSurfaceReady;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05371(CameraViewfinder cameraViewfinder, Function0<Unit> function0, Continuation<? super C05371> continuation) {
            super(2, continuation);
            this.$cameraViewFinder = cameraViewfinder;
            this.$onPreviewSurfaceReady = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return Camera2WrapperImpl.this.new C05371(this.$cameraViewFinder, this.$onPreviewSurfaceReady, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Camera2WrapperImpl camera2WrapperImpl;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Camera2WrapperImpl.this.viewfinderSurfaceRequest = ViewfinderSurfaceRequestUtil.populateFromCharacteristics(new ViewfinderSurfaceRequest.Builder(Camera2WrapperImpl.this.surfaceSizeProvider.getPreviewSize(Camera2WrapperImpl.this.getCharacteristics()).getLandscape()), Camera2WrapperImpl.this.getCharacteristics()).build();
                Camera2WrapperImpl camera2WrapperImpl2 = Camera2WrapperImpl.this;
                CameraViewfinderExt cameraViewfinderExt = CameraViewfinderExt.INSTANCE;
                CameraViewfinder cameraViewfinder = this.$cameraViewFinder;
                ViewfinderSurfaceRequest viewfinderSurfaceRequest = camera2WrapperImpl2.viewfinderSurfaceRequest;
                Intrinsics.checkNotNull(viewfinderSurfaceRequest);
                this.L$0 = camera2WrapperImpl2;
                this.label = 1;
                Object objRequestSurface = cameraViewfinderExt.requestSurface(cameraViewfinder, viewfinderSurfaceRequest, this);
                if (objRequestSurface == coroutine_suspended) {
                    return coroutine_suspended;
                }
                camera2WrapperImpl = camera2WrapperImpl2;
                obj = objRequestSurface;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                camera2WrapperImpl = (Camera2WrapperImpl) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            camera2WrapperImpl.previewSurface = (Surface) obj;
            this.$onPreviewSurfaceReady.invoke();
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05371) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Inject
    public Camera2WrapperImpl(Context context, SurfaceSizeProvider surfaceSizeProvider, SdkConfiguration.MotionCapture.MotionVideoSettings motionVideoSettings) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(surfaceSizeProvider, "surfaceSizeProvider");
        Intrinsics.checkNotNullParameter(motionVideoSettings, "motionVideoSettings");
        this.context = context;
        this.surfaceSizeProvider = surfaceSizeProvider;
        this.motionVideoSettings = motionVideoSettings;
        HandlerThread handlerThread = new HandlerThread("CameraThread");
        handlerThread.start();
        this.cameraThread = handlerThread;
        this.cameraHandler = new Handler(handlerThread.getLooper());
        this.cameraManager = LazyKt.lazy(new Function0<CameraManager>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2WrapperImpl$cameraManager$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CameraManager invoke() {
                Object systemService = this.this$0.context.getApplicationContext().getSystemService(PermissionsTracker.CAMERA);
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.camera2.CameraManager");
                return (CameraManager) systemService;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createCaptureSession(List<? extends Surface> surfaces, final Function0<Unit> onConfigured, final Function1<? super Throwable, Unit> onError) throws CameraAccessException {
        try {
            CameraDevice cameraDevice = this.cameraDevice;
            if (cameraDevice != null) {
                cameraDevice.createCaptureSession(surfaces, new CameraCaptureSession.StateCallback() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2WrapperImpl.createCaptureSession.2
                    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
                    public void onConfigureFailed(CameraCaptureSession session) {
                        Intrinsics.checkNotNullParameter(session, "session");
                        Integer num = (Integer) Camera2WrapperImpl.this.getCharacteristics().get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
                        StringBuilder sb = new StringBuilder("Camera ");
                        CameraDevice cameraDevice2 = Camera2WrapperImpl.this.cameraDevice;
                        String string = sb.append(cameraDevice2 != null ? cameraDevice2.getId() : null).append(" session configuration failed. Hardware level: ").append(num).toString();
                        Timber.INSTANCE.e(string, new Object[0]);
                        onError.invoke(new IllegalStateException(string));
                    }

                    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
                    public void onConfigured(CameraCaptureSession session) {
                        Intrinsics.checkNotNullParameter(session, "session");
                        Camera2WrapperImpl.this.captureSession = session;
                        onConfigured.invoke();
                    }
                }, this.cameraHandler);
            }
        } catch (IllegalStateException e) {
            Timber.INSTANCE.e(e);
            onError.invoke(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void createCaptureSession$default(Camera2WrapperImpl camera2WrapperImpl, List list, Function0 function0, Function1 function1, int i, Object obj) throws CameraAccessException {
        if ((i & 4) != 0) {
            function1 = new Function1<Throwable, Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2WrapperImpl.createCaptureSession.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Throwable it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }
            };
        }
        camera2WrapperImpl.createCaptureSession(list, function0, function1);
    }

    private final CaptureRequest createPreviewRequest(Surface... surfaces) throws CameraAccessException {
        CameraCaptureSession cameraCaptureSession = this.captureSession;
        if (cameraCaptureSession == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureSession");
            cameraCaptureSession = null;
        }
        CaptureRequest.Builder builderCreateCaptureRequest = cameraCaptureSession.getDevice().createCaptureRequest(3);
        for (Surface surface : surfaces) {
            builderCreateCaptureRequest.addTarget(surface);
        }
        CaptureRequest captureRequestBuild = builderCreateCaptureRequest.build();
        Intrinsics.checkNotNullExpressionValue(captureRequestBuild, "build(...)");
        return captureRequestBuild;
    }

    private final CaptureRequest createRecordRequest(Surface... surfaces) throws CameraAccessException {
        CameraCaptureSession cameraCaptureSession = this.captureSession;
        if (cameraCaptureSession == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureSession");
            cameraCaptureSession = null;
        }
        CaptureRequest.Builder builderCreateCaptureRequest = cameraCaptureSession.getDevice().createCaptureRequest(3);
        for (Surface surface : surfaces) {
            builderCreateCaptureRequest.addTarget(surface);
        }
        builderCreateCaptureRequest.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, new Range(30, 30));
        builderCreateCaptureRequest.set(CaptureRequest.CONTROL_AWB_LOCK, Boolean.TRUE);
        builderCreateCaptureRequest.set(CaptureRequest.CONTROL_AE_LOCK, Boolean.valueOf(this.motionVideoSettings.getExposureLock()));
        CaptureRequest captureRequestBuild = builderCreateCaptureRequest.build();
        Intrinsics.checkNotNullExpressionValue(captureRequestBuild, "build(...)");
        return captureRequestBuild;
    }

    private final CameraManager getCameraManager() {
        return (CameraManager) this.cameraManager.getValue();
    }

    private final String getFrontFacingCamera() throws CameraAccessException {
        Object next;
        String[] cameraIdList = getCameraManager().getCameraIdList();
        Intrinsics.checkNotNullExpressionValue(cameraIdList, "getCameraIdList(...)");
        ArrayList arrayList = new ArrayList();
        for (String str : cameraIdList) {
            CameraCharacteristics cameraCharacteristics = getCameraManager().getCameraCharacteristics(str);
            Intrinsics.checkNotNullExpressionValue(cameraCharacteristics, "getCameraCharacteristics(...)");
            int[] iArr = (int[]) cameraCharacteristics.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
            if (iArr != null) {
                Intrinsics.checkNotNull(iArr);
                if (ArraysKt.contains(iArr, 0)) {
                    arrayList.add(str);
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            CameraCharacteristics cameraCharacteristics2 = getCameraManager().getCameraCharacteristics((String) next);
            Intrinsics.checkNotNullExpressionValue(cameraCharacteristics2, "getCameraCharacteristics(...)");
            Integer num = (Integer) cameraCharacteristics2.get(CameraCharacteristics.LENS_FACING);
            if (num != null && num.intValue() == 0) {
                break;
            }
        }
        return (String) next;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openCamera(final Function0<Unit> onOpened, final Function1<? super Throwable, Unit> onError) throws CameraAccessException {
        CameraManager cameraManager = getCameraManager();
        String str = this.selfieCameraId;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selfieCameraId");
            str = null;
        }
        cameraManager.openCamera(str, new CameraDevice.StateCallback() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2WrapperImpl.openCamera.1
            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onDisconnected(CameraDevice device) {
                Intrinsics.checkNotNullParameter(device, "device");
                StringBuilder sb = new StringBuilder("Camera ");
                String str2 = Camera2WrapperImpl.this.selfieCameraId;
                if (str2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selfieCameraId");
                    str2 = null;
                }
                String string = sb.append(str2).append(" has been disconnected").toString();
                Timber.INSTANCE.e(string, new Object[0]);
                onError.invoke(new IllegalStateException(string));
            }

            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onError(CameraDevice device, int error) {
                Intrinsics.checkNotNullParameter(device, "device");
                String str2 = error != 1 ? error != 2 ? error != 3 ? error != 4 ? error != 5 ? "Unknown" : "Fatal (service)" : "Fatal (device)" : "Device policy" : "Maximum cameras in use" : "Camera in use";
                StringBuilder sb = new StringBuilder("Camera ");
                String str3 = Camera2WrapperImpl.this.selfieCameraId;
                if (str3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selfieCameraId");
                    str3 = null;
                }
                String string = sb.append(str3).append(" error: (").append(error).append(") ").append(str2).toString();
                Timber.INSTANCE.e(string, new Object[0]);
                onError.invoke(new IllegalStateException(string));
            }

            @Override // android.hardware.camera2.CameraDevice.StateCallback
            public void onOpened(CameraDevice device) {
                Intrinsics.checkNotNullParameter(device, "device");
                Camera2WrapperImpl.this.cameraDevice = device;
                onOpened.invoke();
            }
        }, this.cameraHandler);
    }

    private final void sendSurfaceRequest(CameraViewfinder cameraViewFinder, Function0<Unit> onPreviewSurfaceReady) {
        LifecycleCoroutineScope lifecycleScope;
        LifecycleOwner lifecycleOwnerFindViewTreeLifecycleOwner = ViewKt.findViewTreeLifecycleOwner(cameraViewFinder);
        if (lifecycleOwnerFindViewTreeLifecycleOwner == null || (lifecycleScope = LifecycleOwnerKt.getLifecycleScope(lifecycleOwnerFindViewTreeLifecycleOwner)) == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(lifecycleScope, null, null, new C05371(cameraViewFinder, onPreviewSurfaceReady, null), 3, null);
    }

    private final void setupCameraViewFinder(final CameraViewfinder cameraViewFinder, final Function0<Unit> onPreviewSurfaceReady) {
        ViewExtensionsKt.toVisible$default(cameraViewFinder, false, 1, null);
        this.layoutChangedListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2WrapperImpl$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                Camera2WrapperImpl.setupCameraViewFinder$lambda$4$lambda$3(cameraViewFinder, this, onPreviewSurfaceReady);
            }
        };
        cameraViewFinder.getViewTreeObserver().addOnGlobalLayoutListener(this.layoutChangedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCameraViewFinder$lambda$4$lambda$3(CameraViewfinder this_with, Camera2WrapperImpl this$0, Function0 onPreviewSurfaceReady) {
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(onPreviewSurfaceReady, "$onPreviewSurfaceReady");
        this_with.getViewTreeObserver().removeOnGlobalLayoutListener(this$0.layoutChangedListener);
        this$0.layoutChangedListener = null;
        this$0.sendSurfaceRequest(this_with, onPreviewSurfaceReady);
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2Wrapper
    public void cleanup() {
        this.cameraThread.quitSafely();
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2Wrapper
    public void closeCamera() {
        ViewfinderSurfaceRequest viewfinderSurfaceRequest = this.viewfinderSurfaceRequest;
        if (viewfinderSurfaceRequest != null) {
            viewfinderSurfaceRequest.markSurfaceSafeToRelease();
        }
        CameraDevice cameraDevice = this.cameraDevice;
        if (cameraDevice != null) {
            cameraDevice.close();
        }
        this.cameraDevice = null;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2Wrapper
    public void findSelfieCamera(Function1<? super CameraCharacteristics, Unit> onSuccess, Function1<? super Throwable, Unit> onError) throws CameraAccessException {
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onError, "onError");
        String frontFacingCamera = getFrontFacingCamera();
        Unit unit = null;
        String str = null;
        if (frontFacingCamera != null) {
            this.selfieCameraId = frontFacingCamera;
            CameraManager cameraManager = getCameraManager();
            String str2 = this.selfieCameraId;
            if (str2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selfieCameraId");
            } else {
                str = str2;
            }
            CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(str);
            Intrinsics.checkNotNullExpressionValue(cameraCharacteristics, "getCameraCharacteristics(...)");
            setCharacteristics(cameraCharacteristics);
            onSuccess.invoke(getCharacteristics());
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            Timber.INSTANCE.e("No compatible cameras available on the device", new Object[0]);
            onError.invoke(new IllegalStateException("No compatible cameras available on the device"));
        }
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2Wrapper
    public CameraCharacteristics getCharacteristics() {
        CameraCharacteristics cameraCharacteristics = this.characteristics;
        if (cameraCharacteristics != null) {
            return cameraCharacteristics;
        }
        Intrinsics.throwUninitializedPropertyAccessException("characteristics");
        return null;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2Wrapper
    public void resetCaptureSession(Surface recorderSurface) throws CameraAccessException {
        Intrinsics.checkNotNullParameter(recorderSurface, "recorderSurface");
        CameraCaptureSession cameraCaptureSession = this.captureSession;
        Surface surface = null;
        if (cameraCaptureSession == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureSession");
            cameraCaptureSession = null;
        }
        cameraCaptureSession.abortCaptures();
        Surface surface2 = this.previewSurface;
        if (surface2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("previewSurface");
        } else {
            surface = surface2;
        }
        createCaptureSession$default(this, CollectionsKt.listOf((Object[]) new Surface[]{surface, recorderSurface}), new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2WrapperImpl.resetCaptureSession.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() throws CameraAccessException {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() throws CameraAccessException {
                Camera2WrapperImpl.this.setPreviewMode();
            }
        }, null, 4, null);
    }

    public void setCharacteristics(CameraCharacteristics cameraCharacteristics) {
        Intrinsics.checkNotNullParameter(cameraCharacteristics, "<set-?>");
        this.characteristics = cameraCharacteristics;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2Wrapper
    public void setPreviewMode() throws CameraAccessException {
        try {
            if (this.cameraDevice != null) {
                CameraCaptureSession cameraCaptureSession = this.captureSession;
                if (cameraCaptureSession == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("captureSession");
                    cameraCaptureSession = null;
                }
                Surface surface = this.previewSurface;
                if (surface == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("previewSurface");
                    surface = null;
                }
                cameraCaptureSession.setRepeatingRequest(createPreviewRequest(surface), null, this.cameraHandler);
            }
        } catch (IllegalStateException e) {
            Timber.INSTANCE.e(e);
        }
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2Wrapper
    public void setRecordingMode(Surface recorderSurface) throws CameraAccessException {
        Intrinsics.checkNotNullParameter(recorderSurface, "recorderSurface");
        if (this.cameraDevice != null) {
            CameraCaptureSession cameraCaptureSession = this.captureSession;
            if (cameraCaptureSession == null) {
                Intrinsics.throwUninitializedPropertyAccessException("captureSession");
                cameraCaptureSession = null;
            }
            Surface surface = this.previewSurface;
            if (surface == null) {
                Intrinsics.throwUninitializedPropertyAccessException("previewSurface");
                surface = null;
            }
            cameraCaptureSession.setRepeatingRequest(createRecordRequest(surface, recorderSurface), null, this.cameraHandler);
        }
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2Wrapper
    public void start(CameraViewfinder cameraViewfinder, final Surface recorderSurface, final Function0<Unit> onSuccess, final Function1<? super Throwable, Unit> onError) {
        Intrinsics.checkNotNullParameter(cameraViewfinder, "cameraViewfinder");
        Intrinsics.checkNotNullParameter(recorderSurface, "recorderSurface");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onError, "onError");
        setupCameraViewFinder(cameraViewfinder, new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2WrapperImpl.start.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() throws CameraAccessException {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() throws CameraAccessException {
                final Camera2WrapperImpl camera2WrapperImpl = Camera2WrapperImpl.this;
                final Surface surface = recorderSurface;
                final Function1<Throwable, Unit> function1 = onError;
                final Function0<Unit> function0 = onSuccess;
                camera2WrapperImpl.openCamera(new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2WrapperImpl.start.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() throws CameraAccessException {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() throws CameraAccessException {
                        Camera2WrapperImpl camera2WrapperImpl2 = camera2WrapperImpl;
                        Surface surface2 = camera2WrapperImpl2.previewSurface;
                        if (surface2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("previewSurface");
                            surface2 = null;
                        }
                        List listListOf = CollectionsKt.listOf((Object[]) new Surface[]{surface2, surface});
                        final Camera2WrapperImpl camera2WrapperImpl3 = camera2WrapperImpl;
                        final Function0<Unit> function02 = function0;
                        camera2WrapperImpl2.createCaptureSession(listListOf, new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2WrapperImpl.start.1.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() throws CameraAccessException {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() throws CameraAccessException {
                                camera2WrapperImpl3.setPreviewMode();
                                function02.invoke();
                            }
                        }, function1);
                    }
                }, onError);
            }
        });
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2Wrapper
    public void stopPreview() throws CameraAccessException {
        CameraCaptureSession cameraCaptureSession = this.captureSession;
        CameraCaptureSession cameraCaptureSession2 = null;
        if (cameraCaptureSession == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureSession");
            cameraCaptureSession = null;
        }
        cameraCaptureSession.stopRepeating();
        CameraCaptureSession cameraCaptureSession3 = this.captureSession;
        if (cameraCaptureSession3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("captureSession");
        } else {
            cameraCaptureSession2 = cameraCaptureSession3;
        }
        cameraCaptureSession2.abortCaptures();
    }
}
