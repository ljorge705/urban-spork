package com.onfido.android.sdk.capture.component.active.video.capture.di.capture;

import android.content.Context;
import androidx.camera.lifecycle.ProcessCameraProvider;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.face.Face;
import com.onfido.android.sdk.capture.common.di.FragmentScope;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2Wrapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.Camera2WrapperImpl;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapperFactory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface.IsPersistentSurfaceSupportedUseCase;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface.IsPersistentSurfaceSupportedUseCaseImpl;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface.LocalPersistentRecorderSurfaceRepository;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface.PersistentRecorderSurfaceRepository;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKitResultMapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcResultMapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcResultMapperTestImpl;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcTfliteResultMapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.tfmodel.Detection;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.util.OvalRect;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModel;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModelImpl;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.dagger.Binds;
import com.onfido.dagger.Module;
import com.onfido.dagger.Provides;
import com.onfido.javax.inject.Provider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Module
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\ba\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH'J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H'¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/di/capture/MotionCaptureModule;", "", "provideCamera2Wrapper", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/Camera2Wrapper;", "camera2WrapperImpl", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/Camera2WrapperImpl;", "provideHeadTurnGuidanceViewModel", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel;", "headTurnGuidanceViewModelImpl", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModelImpl;", "provideIsPersistentSurfaceSupportedUseCase", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/persistentsurface/IsPersistentSurfaceSupportedUseCase;", "isPersistentSurfaceSupportedUseCaseImpl", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/persistentsurface/IsPersistentSurfaceSupportedUseCaseImpl;", "providePersistentRecorderSurfaceRepository", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/persistentsurface/PersistentRecorderSurfaceRepository;", "localPersistentRecorderSurfaceRepository", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/persistentsurface/LocalPersistentRecorderSurfaceRepository;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface MotionCaptureModule {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J2\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0007J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\b\u0010\u0016\u001a\u00020\u0017H\u0007J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J2\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\t2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0010H\u0007¨\u0006 "}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/di/capture/MotionCaptureModule$Companion;", "", "()V", "provideCameraProvider", "Lcom/google/common/util/concurrent/ListenableFuture;", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "applicationContext", "Landroid/content/Context;", "provideMLKitResultMapper", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvcResultMapper;", "Lcom/google/mlkit/vision/face/Face;", "motionOptions", "Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;", "testImpl", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvcResultMapperTestImpl;", "mLKitResultMapperProvider", "Lcom/onfido/javax/inject/Provider;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvcMLKitResultMapper;", "provideMotionVideoSettings", "Lcom/onfido/api/client/data/SdkConfiguration$MotionCapture$MotionVideoSettings;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "provideOvalRect", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/util/OvalRect;", "provideRecorderWrapper", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/RecorderWrapper;", "recorderWrapperFactory", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/RecorderWrapperFactory;", "provideTFLiteResultMapper", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/tfmodel/Detection;", "tFLiteResultMapperProvider", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvcTfliteResultMapper;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        @Provides
        public final ListenableFuture<ProcessCameraProvider> provideCameraProvider(Context applicationContext) {
            Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
            ListenableFuture<ProcessCameraProvider> processCameraProvider = ProcessCameraProvider.getInstance(applicationContext);
            Intrinsics.checkNotNullExpressionValue(processCameraProvider, "getInstance(...)");
            return processCameraProvider;
        }

        @Provides
        @FragmentScope
        public final FaceDetectorAvcResultMapper<Face> provideMLKitResultMapper(MotionCaptureVariantOptions motionOptions, FaceDetectorAvcResultMapperTestImpl<Object> testImpl, Provider<FaceDetectorAvcMLKitResultMapper> mLKitResultMapperProvider) {
            Intrinsics.checkNotNullParameter(motionOptions, "motionOptions");
            Intrinsics.checkNotNullParameter(testImpl, "testImpl");
            Intrinsics.checkNotNullParameter(mLKitResultMapperProvider, "mLKitResultMapperProvider");
            if (motionOptions.isTestEnv()) {
                return testImpl;
            }
            FaceDetectorAvcMLKitResultMapper faceDetectorAvcMLKitResultMapper = mLKitResultMapperProvider.get();
            Intrinsics.checkNotNull(faceDetectorAvcMLKitResultMapper);
            return faceDetectorAvcMLKitResultMapper;
        }

        @Provides
        public final SdkConfiguration.MotionCapture.MotionVideoSettings provideMotionVideoSettings(OnfidoRemoteConfig onfidoRemoteConfig) {
            Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
            return onfidoRemoteConfig.getMotionCapture().getVideoSettings();
        }

        @Provides
        @FragmentScope
        public final OvalRect provideOvalRect() {
            return OvalRect.INSTANCE;
        }

        @Provides
        @FragmentScope
        public final RecorderWrapper provideRecorderWrapper(RecorderWrapperFactory recorderWrapperFactory) {
            Intrinsics.checkNotNullParameter(recorderWrapperFactory, "recorderWrapperFactory");
            return recorderWrapperFactory.create();
        }

        @Provides
        @FragmentScope
        public final FaceDetectorAvcResultMapper<Detection> provideTFLiteResultMapper(MotionCaptureVariantOptions motionOptions, FaceDetectorAvcResultMapperTestImpl<Object> testImpl, Provider<FaceDetectorAvcTfliteResultMapper> tFLiteResultMapperProvider) {
            Intrinsics.checkNotNullParameter(motionOptions, "motionOptions");
            Intrinsics.checkNotNullParameter(testImpl, "testImpl");
            Intrinsics.checkNotNullParameter(tFLiteResultMapperProvider, "tFLiteResultMapperProvider");
            if (motionOptions.isTestEnv()) {
                return testImpl;
            }
            FaceDetectorAvcTfliteResultMapper faceDetectorAvcTfliteResultMapper = tFLiteResultMapperProvider.get();
            Intrinsics.checkNotNull(faceDetectorAvcTfliteResultMapper);
            return faceDetectorAvcTfliteResultMapper;
        }
    }

    @FragmentScope
    @Binds
    Camera2Wrapper provideCamera2Wrapper(Camera2WrapperImpl camera2WrapperImpl);

    @FragmentScope
    @Binds
    HeadTurnGuidanceViewModel provideHeadTurnGuidanceViewModel(HeadTurnGuidanceViewModelImpl headTurnGuidanceViewModelImpl);

    @FragmentScope
    @Binds
    IsPersistentSurfaceSupportedUseCase provideIsPersistentSurfaceSupportedUseCase(IsPersistentSurfaceSupportedUseCaseImpl isPersistentSurfaceSupportedUseCaseImpl);

    @FragmentScope
    @Binds
    PersistentRecorderSurfaceRepository providePersistentRecorderSurfaceRepository(LocalPersistentRecorderSurfaceRepository localPersistentRecorderSurfaceRepository);
}
