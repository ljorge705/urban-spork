package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.view.PreviewView;
import androidx.camera.viewfinder.CameraViewfinder;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.window.core.layout.WindowSizeClass;
import com.clevertap.android.sdk.Constants;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.result.HapticFeedback;
import com.onfido.android.sdk.capture.component.active.video.capture.di.capture.MotionCaptureComponent;
import com.onfido.android.sdk.capture.component.active.video.capture.di.capture.MotionCaptureComponentHolder;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraControllerFactory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvc;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcResultMapperTestImpl;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.MotionFaceDetectorFactory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.facealignment.FaceAlignmentView;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.facealignment.FeedbackLabelView;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.HeadTurnCompletedView;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.HeadTurnGuidanceView;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.util.OvalRect;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.util.RectDebuggingView;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModel;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModel;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.model.FaceAlignmentFeedback;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.AvcTimer;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.WindowHelperKt;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostViewModel;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionNoFaceDetectedScreen;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionUploadScreen;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentMotionCaptureBinding;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions;
import com.onfido.android.sdk.capture.utils.LifecycleAwareDialog;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

@Metadata(d1 = {"\u0000è\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 \u009d\u00012\u00020\u0001:\u0002\u009d\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010r\u001a\u00020sH\u0002J\u0012\u0010t\u001a\u00020s2\b\u0010u\u001a\u0004\u0018\u00010vH\u0002J\b\u0010w\u001a\u00020xH\u0002J\u0010\u0010y\u001a\u00020s2\u0006\u0010z\u001a\u00020{H\u0002J\u0010\u0010|\u001a\u00020s2\u0006\u0010}\u001a\u00020\u0007H\u0002J\u0010\u0010~\u001a\u00020s2\u0006\u0010}\u001a\u00020\u0007H\u0002J\b\u0010\u007f\u001a\u00020sH\u0002J\t\u0010\u0080\u0001\u001a\u00020sH\u0002J\t\u0010\u0081\u0001\u001a\u00020sH\u0002J\t\u0010\u0082\u0001\u001a\u00020sH\u0002J\t\u0010\u0083\u0001\u001a\u00020sH\u0002J\t\u0010\u0084\u0001\u001a\u00020sH\u0002J\t\u0010\u0085\u0001\u001a\u00020sH\u0002J\t\u0010\u0086\u0001\u001a\u00020sH\u0016J\t\u0010\u0087\u0001\u001a\u00020sH\u0016J\t\u0010\u0088\u0001\u001a\u00020sH\u0016J\t\u0010\u0089\u0001\u001a\u00020sH\u0016J\u001f\u0010\u008a\u0001\u001a\u00020s2\b\u0010\u008b\u0001\u001a\u00030\u008c\u00012\n\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u0001H\u0016J\t\u0010\u008f\u0001\u001a\u00020sH\u0002J\t\u0010\u0090\u0001\u001a\u00020GH\u0002J:\u0010\u0091\u0001\u001a\u00020s2\t\b\u0001\u0010\u0092\u0001\u001a\u00020\b2\t\b\u0001\u0010\u0093\u0001\u001a\u00020\b2\t\b\u0001\u0010\u0094\u0001\u001a\u00020\b2\u000e\u0010\u0095\u0001\u001a\t\u0012\u0004\u0012\u00020s0\u0096\u0001H\u0002J\u0014\u0010\u0097\u0001\u001a\u00020s2\t\b\u0001\u0010\u0098\u0001\u001a\u00020\bH\u0002J\u0019\u0010\u0099\u0001\u001a\u00020s2\u000e\u0010\u009a\u0001\u001a\t\u0012\u0004\u0012\u00020s0\u0096\u0001H\u0002J\u0019\u0010\u009b\u0001\u001a\u00020s2\u000e\u0010\u009a\u0001\u001a\t\u0012\u0004\u0012\u00020s0\u0096\u0001H\u0002J\t\u0010\u009c\u0001\u001a\u00020sH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u001a\u001a\u00020\u001b8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001b\u0010 \u001a\u00020!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b\"\u0010#R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010(\u001a\u00020)8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001e\u0010.\u001a\u00020)8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010+\"\u0004\b0\u0010-R\u000e\u00101\u001a\u000202X\u0082.¢\u0006\u0002\n\u0000R$\u00103\u001a\b\u0012\u0004\u0012\u000205048\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001e\u0010:\u001a\u00020;8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001e\u0010@\u001a\u00020A8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u001b\u0010F\u001a\u00020G8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bI\u0010%\u001a\u0004\bF\u0010HR\u000e\u0010J\u001a\u00020KX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010L\u001a\u00020M8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u001b\u0010R\u001a\u00020S8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bV\u0010%\u001a\u0004\bT\u0010UR\u001e\u0010W\u001a\u00020X8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\R\u001e\u0010]\u001a\u00020^8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010`\"\u0004\ba\u0010bR\u001b\u0010c\u001a\u00020G8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\be\u0010%\u001a\u0004\bd\u0010HR\u001a\u0010f\u001a\u00020gX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010i\"\u0004\bj\u0010kR\u001e\u0010l\u001a\u00020m8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010o\"\u0004\bp\u0010q¨\u0006\u009e\u0001"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/MotionCaptureFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentMotionCaptureBinding;", "accessibilityAnnouncementMap", "", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback;", "", "analytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "getAnalytics", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "setAnalytics", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;)V", "announcementService", "Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "getAnnouncementService", "()Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "setAnnouncementService", "(Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;)V", "binding", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentMotionCaptureBinding;", "cameraController", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionCameraController;", "cameraControllerFactory", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionCameraControllerFactory;", "getCameraControllerFactory", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionCameraControllerFactory;", "setCameraControllerFactory", "(Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionCameraControllerFactory;)V", "captureComponent", "Lcom/onfido/android/sdk/capture/component/active/video/capture/di/capture/MotionCaptureComponent;", "getCaptureComponent", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/di/capture/MotionCaptureComponent;", "captureComponent$delegate", "Lkotlin/Lazy;", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "delayStartRecordingTimer", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/util/AvcTimer;", "getDelayStartRecordingTimer", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/util/AvcTimer;", "setDelayStartRecordingTimer", "(Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/util/AvcTimer;)V", "delayTimer", "getDelayTimer", "setDelayTimer", "faceDetectorAvc", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvc;", "faceDetectorAvcResultMapperTestImpl", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvcResultMapperTestImpl;", "", "getFaceDetectorAvcResultMapperTestImpl", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvcResultMapperTestImpl;", "setFaceDetectorAvcResultMapperTestImpl", "(Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvcResultMapperTestImpl;)V", "hapticFeedback", "Lcom/onfido/android/sdk/capture/common/result/HapticFeedback;", "getHapticFeedback", "()Lcom/onfido/android/sdk/capture/common/result/HapticFeedback;", "setHapticFeedback", "(Lcom/onfido/android/sdk/capture/common/result/HapticFeedback;)V", "headTurnGuidanceViewModel", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel;", "getHeadTurnGuidanceViewModel", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel;", "setHeadTurnGuidanceViewModel", "(Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel;)V", "isCameraXEnabled", "", "()Z", "isCameraXEnabled$delegate", "lifecycleAwareDialog", "Lcom/onfido/android/sdk/capture/utils/LifecycleAwareDialog;", "motionFaceDetectorFactory", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/MotionFaceDetectorFactory;", "getMotionFaceDetectorFactory", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/MotionFaceDetectorFactory;", "setMotionFaceDetectorFactory", "(Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/MotionFaceDetectorFactory;)V", "motionHostViewModel", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel;", "getMotionHostViewModel", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostViewModel;", "motionHostViewModel$delegate", "motionOptions", "Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;", "getMotionOptions", "()Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;", "setMotionOptions", "(Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;)V", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "getSchedulersProvider", "()Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "setSchedulersProvider", "(Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;)V", "shouldUseMlKit", "getShouldUseMlKit", "shouldUseMlKit$delegate", "viewModel", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel;", "getViewModel", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel;", "setViewModel", "(Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel;)V", "viewModelFactory", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModelImpl$Factory;", "getViewModelFactory", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModelImpl$Factory;", "setViewModelFactory", "(Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModelImpl$Factory;)V", "announceCameraInUse", "", "enableToolbarBackNavigation", "supportActionBar", "Landroidx/appcompat/app/ActionBar;", "getOvalRect", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "handleErrorStates", "it", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error;", "handleFaceAlignmentFeedback", "feedback", "handleFaceAlignmentFeedbackAccessibility", "handleTestEnvInFaceAlignment", "handleTestEnvInRecordingStarted", "hideFaceAlignmentFeedback", "observeDetectedFace", "observeFaceAlignmentFeedback", "observeFaceAlignmentFeedbackAccessibility", "observeViewState", "onDestroy", "onDestroyView", "onStart", "onStop", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setupToolbar", "shouldObserveAccessibilityFaceAlignmentFeedback", "showAlertDialog", "titleResId", "messageResId", "positiveButtonResId", "positiveButtonAction", "Lkotlin/Function0;", "showFaceAlignmentFeedback", "resId", "showRecordingIsTooShortDialog", Constants.KEY_ACTION, "showRecordingTimeoutDialog", "startCamera", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MotionCaptureFragment extends Fragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final long DELAY_FINISH_RECORDING_MILLISECONDS = 1000;
    private static final long DELAY_FLOW_COMPLETED_MILLISECONDS = 2000;
    public static final long DELAY_START_RECORDING_MILLISECONDS = 1000;
    private static final String KEY_IS_CAMERA_X_ENABLED = "is_camera_x_enabled";
    private static final String KEY_SHOULD_USE_MLKIT = "should_use_mlkit";
    private OnfidoFragmentMotionCaptureBinding _binding;
    private final Map<FaceAlignmentFeedback, Integer> accessibilityAnnouncementMap;

    @Inject
    public OnfidoAnalytics analytics;

    @Inject
    public AnnouncementService announcementService;
    private MotionCameraController cameraController;

    @Inject
    public MotionCameraControllerFactory cameraControllerFactory;

    /* renamed from: captureComponent$delegate, reason: from kotlin metadata */
    private final Lazy captureComponent;
    private final CompositeDisposable compositeDisposable;

    @Inject
    public AvcTimer delayStartRecordingTimer;

    @Inject
    public AvcTimer delayTimer;
    private FaceDetectorAvc faceDetectorAvc;

    @Inject
    public FaceDetectorAvcResultMapperTestImpl<Object> faceDetectorAvcResultMapperTestImpl;

    @Inject
    public HapticFeedback hapticFeedback;

    @Inject
    public HeadTurnGuidanceViewModel headTurnGuidanceViewModel;

    /* renamed from: isCameraXEnabled$delegate, reason: from kotlin metadata */
    private final Lazy isCameraXEnabled;
    private final LifecycleAwareDialog lifecycleAwareDialog;

    @Inject
    public MotionFaceDetectorFactory motionFaceDetectorFactory;

    /* renamed from: motionHostViewModel$delegate, reason: from kotlin metadata */
    private final Lazy motionHostViewModel;

    @Inject
    public MotionCaptureVariantOptions motionOptions;

    @Inject
    public SchedulersProvider schedulersProvider;

    /* renamed from: shouldUseMlKit$delegate, reason: from kotlin metadata */
    private final Lazy shouldUseMlKit;
    public MotionCaptureViewModel viewModel;

    @Inject
    public MotionCaptureViewModelImpl.Factory viewModelFactory;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/MotionCaptureFragment$Companion;", "", "()V", "DELAY_FINISH_RECORDING_MILLISECONDS", "", "DELAY_FLOW_COMPLETED_MILLISECONDS", "DELAY_START_RECORDING_MILLISECONDS", "KEY_IS_CAMERA_X_ENABLED", "", "KEY_SHOULD_USE_MLKIT", "createInstance", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/MotionCaptureFragment;", "shouldUseMlKit", "", "isCameraXEnabled", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final MotionCaptureFragment createInstance(boolean shouldUseMlKit, boolean isCameraXEnabled) {
            MotionCaptureFragment motionCaptureFragment = new MotionCaptureFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean(MotionCaptureFragment.KEY_SHOULD_USE_MLKIT, shouldUseMlKit);
            bundle.putBoolean(MotionCaptureFragment.KEY_IS_CAMERA_X_ENABLED, isCameraXEnabled);
            motionCaptureFragment.setArguments(bundle);
            return motionCaptureFragment;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment$handleTestEnvInFaceAlignment$1", f = "MotionCaptureFragment.kt", i = {}, l = {312}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment$handleTestEnvInFaceAlignment$1, reason: invalid class name and case insensitive filesystem */
    static final class C05401 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05401(Continuation<? super C05401> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MotionCaptureFragment.this.new C05401(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (DelayKt.delay(2000L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            MotionCaptureFragment.this.getFaceDetectorAvcResultMapperTestImpl().alignFace(MotionCaptureFragment.this.getOvalRect());
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05401) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment$handleTestEnvInRecordingStarted$1", f = "MotionCaptureFragment.kt", i = {}, l = {ExifDirectoryBase.TAG_TILE_WIDTH, ExifDirectoryBase.TAG_TILE_OFFSETS}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment$handleTestEnvInRecordingStarted$1, reason: invalid class name and case insensitive filesystem */
    static final class C05411 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05411(Continuation<? super C05411> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MotionCaptureFragment.this.new C05411(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    MotionCaptureFragment.this.getFaceDetectorAvcResultMapperTestImpl().completeRightTurn(MotionCaptureFragment.this.getOvalRect());
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            MotionCaptureFragment.this.getFaceDetectorAvcResultMapperTestImpl().completeLeftTurn(MotionCaptureFragment.this.getOvalRect());
            this.label = 2;
            if (DelayKt.delay(1500L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            MotionCaptureFragment.this.getFaceDetectorAvcResultMapperTestImpl().completeRightTurn(MotionCaptureFragment.this.getOvalRect());
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05411) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment$startCamera$2, reason: invalid class name and case insensitive filesystem */
    /* synthetic */ class C05482 extends FunctionReferenceImpl implements Function1<Throwable, Unit> {
        C05482(Object obj) {
            super(1, obj, MotionHostViewModel.class, "onError", "onError(Ljava/lang/Throwable;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Throwable p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            ((MotionHostViewModel) this.receiver).onError(p0);
        }
    }

    public MotionCaptureFragment() {
        super(R.layout.onfido_fragment_motion_capture);
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment$motionHostViewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                Fragment fragmentRequireParentFragment = this.this$0.requireParentFragment();
                Intrinsics.checkNotNullExpressionValue(fragmentRequireParentFragment, "requireParentFragment(...)");
                return fragmentRequireParentFragment;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) function0.invoke();
            }
        });
        final Function0 function02 = null;
        this.motionHostViewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(MotionHostViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = FragmentViewModelLazyKt.m4679viewModels$lambda1(lazy).getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "owner.viewModelStore");
                return viewModelStore;
            }
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment$special$$inlined$viewModels$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function03 = function02;
                if (function03 != null && (creationExtras = (CreationExtras) function03.invoke()) != null) {
                    return creationExtras;
                }
                ViewModelStoreOwner viewModelStoreOwnerM4679viewModels$lambda1 = FragmentViewModelLazyKt.m4679viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM4679viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM4679viewModels$lambda1 : null;
                CreationExtras defaultViewModelCreationExtras = hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : null;
                return defaultViewModelCreationExtras == null ? CreationExtras.Empty.INSTANCE : defaultViewModelCreationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment$special$$inlined$viewModels$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory;
                ViewModelStoreOwner viewModelStoreOwnerM4679viewModels$lambda1 = FragmentViewModelLazyKt.m4679viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM4679viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM4679viewModels$lambda1 : null;
                if (hasDefaultViewModelProviderFactory == null || (defaultViewModelProviderFactory = hasDefaultViewModelProviderFactory.getDefaultViewModelProviderFactory()) == null) {
                    defaultViewModelProviderFactory = this.getDefaultViewModelProviderFactory();
                }
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "(owner as? HasDefaultVie…tViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
        this.lifecycleAwareDialog = new LifecycleAwareDialog(this, (Function1) null, 2, (DefaultConstructorMarker) null);
        this.compositeDisposable = new CompositeDisposable();
        this.shouldUseMlKit = LazyKt.lazy(new Function0<Boolean>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment$shouldUseMlKit$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(this.this$0.requireArguments().getBoolean("should_use_mlkit"));
            }
        });
        this.isCameraXEnabled = LazyKt.lazy(new Function0<Boolean>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment.isCameraXEnabled.2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(MotionCaptureFragment.this.requireArguments().getBoolean(MotionCaptureFragment.KEY_IS_CAMERA_X_ENABLED));
            }
        });
        this.captureComponent = LazyKt.lazy(new Function0<MotionCaptureComponent>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment$captureComponent$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MotionCaptureComponent invoke() {
                MotionCaptureComponentHolder companion = MotionCaptureComponentHolder.INSTANCE.getInstance();
                Fragment parentFragment = this.this$0.getParentFragment();
                Intrinsics.checkNotNull(parentFragment, "null cannot be cast to non-null type com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment");
                return companion.getComponent$onfido_capture_sdk_core_release(((MotionHostFragment) parentFragment).getMotionHostComponent$onfido_capture_sdk_core_release());
            }
        });
        this.accessibilityAnnouncementMap = MapsKt.mapOf(TuplesKt.to(FaceAlignmentFeedback.Idle.INSTANCE, Integer.valueOf(R.string.onfido_avc_face_alignment_title_accessibility)), TuplesKt.to(FaceAlignmentFeedback.MoveDeviceLeft.INSTANCE, Integer.valueOf(R.string.onfido_avc_face_alignment_feedback_move_left_accessibility)), TuplesKt.to(FaceAlignmentFeedback.MoveDeviceRight.INSTANCE, Integer.valueOf(R.string.onfido_avc_face_alignment_feedback_move_right_accessibility)), TuplesKt.to(FaceAlignmentFeedback.MoveDeviceDown.INSTANCE, Integer.valueOf(R.string.onfido_avc_face_alignment_feedback_move_down_accessibility)), TuplesKt.to(FaceAlignmentFeedback.MoveDeviceUp.INSTANCE, Integer.valueOf(R.string.onfido_avc_face_alignment_feedback_move_up_accessibility)), TuplesKt.to(FaceAlignmentFeedback.MoveBack.INSTANCE, Integer.valueOf(R.string.onfido_avc_face_alignment_feedback_move_back_accessibility)), TuplesKt.to(FaceAlignmentFeedback.MoveCloser.INSTANCE, Integer.valueOf(R.string.onfido_avc_face_alignment_feedback_move_closer_accessibility)), TuplesKt.to(FaceAlignmentFeedback.FaceNotDetected.INSTANCE, Integer.valueOf(R.string.onfido_avc_face_alignment_feedback_no_face_detected_accessibility)), TuplesKt.to(FaceAlignmentFeedback.FaceAligned.INSTANCE, Integer.valueOf(R.string.onfido_avc_face_alignment_feedback_face_aligned_accessibility)), TuplesKt.to(FaceAlignmentFeedback.FaceNotCentered.INSTANCE, Integer.valueOf(R.string.onfido_avc_face_alignment_feedback_face_misaligned_accessibility)));
    }

    private final void announceCameraInUse() {
        getAnnouncementService().announceStringAsync(new int[]{R.string.onfido_avc_face_capture_frame_accessibility}, true);
    }

    private final void enableToolbarBackNavigation(ActionBar supportActionBar) {
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (supportActionBar != null) {
            supportActionBar.setHomeActionContentDescription(R.string.onfido_generic_back);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OnfidoFragmentMotionCaptureBinding getBinding() {
        OnfidoFragmentMotionCaptureBinding onfidoFragmentMotionCaptureBinding = this._binding;
        Intrinsics.checkNotNull(onfidoFragmentMotionCaptureBinding);
        return onfidoFragmentMotionCaptureBinding;
    }

    private final MotionCaptureComponent getCaptureComponent() {
        return (MotionCaptureComponent) this.captureComponent.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MotionHostViewModel getMotionHostViewModel() {
        return (MotionHostViewModel) this.motionHostViewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OnfidoRectF getOvalRect() {
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        WindowSizeClass windowSizeClass = WindowHelperKt.getWindowSizeClass(contextRequireContext);
        FrameLayout frameLayout = getBinding().previewContainer;
        return OvalRect.INSTANCE.get(frameLayout.getWidth(), frameLayout.getHeight(), windowSizeClass);
    }

    private final boolean getShouldUseMlKit() {
        return ((Boolean) this.shouldUseMlKit.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleErrorStates(MotionCaptureViewModel.ViewState.Error it) {
        int i;
        int i2;
        int i3;
        Function0<Unit> function0;
        if (Intrinsics.areEqual(it, MotionCaptureViewModel.ViewState.Error.MicIsNotAvailable.INSTANCE)) {
            i = R.string.onfido_avc_face_capture_alert_mic_conflict_title;
            i2 = R.string.onfido_avc_face_capture_alert_mic_conflict_body;
            i3 = R.string.onfido_avc_face_capture_alert_mic_conflict_button_primary;
            function0 = new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment.handleErrorStates.1
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
                    MotionCaptureFragment.this.getViewModel().onMicIsNotAvailableAlertDialogDismissed();
                }
            };
        } else {
            if (!Intrinsics.areEqual(it, MotionCaptureViewModel.ViewState.Error.AudioConflict.INSTANCE)) {
                if (Intrinsics.areEqual(it, MotionCaptureViewModel.ViewState.Error.FaceAlignmentTimeout.INSTANCE)) {
                    getMotionHostViewModel().getNavigator().replace(MotionNoFaceDetectedScreen.INSTANCE);
                    return;
                }
                if (Intrinsics.areEqual(it, MotionCaptureViewModel.ViewState.Error.RecordingIsTooShort.INSTANCE)) {
                    HapticFeedback hapticFeedback = getHapticFeedback();
                    HeadTurnCompletedView headTurnCompletedView = getBinding().headTurnCompletedView;
                    Intrinsics.checkNotNullExpressionValue(headTurnCompletedView, "headTurnCompletedView");
                    hapticFeedback.performError(headTurnCompletedView);
                    showRecordingIsTooShortDialog(new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment.handleErrorStates.3
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
                            MotionCaptureFragment.this.getViewModel().onRecordingIsTooShortAlertDialogDismissed();
                        }
                    });
                    return;
                }
                if (Intrinsics.areEqual(it, MotionCaptureViewModel.ViewState.Error.RecordingTimeout.INSTANCE)) {
                    showRecordingTimeoutDialog(new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment.handleErrorStates.4
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
                            MotionCaptureFragment.this.getViewModel().onRecordingTimeoutAlertDialogDismissed();
                        }
                    });
                    return;
                } else {
                    if (it instanceof MotionCaptureViewModel.ViewState.Error.RecordingFailed) {
                        getMotionHostViewModel().onError(((MotionCaptureViewModel.ViewState.Error.RecordingFailed) it).getError());
                        return;
                    }
                    return;
                }
            }
            i = R.string.onfido_avc_face_capture_alert_audio_conflict_title;
            i2 = R.string.onfido_avc_face_capture_alert_audio_conflict_body;
            i3 = R.string.onfido_avc_face_capture_alert_audio_conflict_button_primary;
            function0 = new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment.handleErrorStates.2
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
                    MotionCaptureFragment.this.getViewModel().onAudioConflictAlertDialogDismissed();
                }
            };
        }
        showAlertDialog(i, i2, i3, function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleFaceAlignmentFeedback(FaceAlignmentFeedback feedback) {
        int i;
        if (Intrinsics.areEqual(feedback, FaceAlignmentFeedback.MoveBack.INSTANCE)) {
            i = R.string.onfido_avc_face_alignment_feedback_move_back;
        } else if (Intrinsics.areEqual(feedback, FaceAlignmentFeedback.MoveCloser.INSTANCE)) {
            i = R.string.onfido_avc_face_alignment_feedback_move_closer;
        } else if (Intrinsics.areEqual(feedback, FaceAlignmentFeedback.FaceNotCentered.INSTANCE)) {
            i = R.string.onfido_avc_face_alignment_feedback_not_centered;
        } else {
            if (!Intrinsics.areEqual(feedback, FaceAlignmentFeedback.FaceNotDetected.INSTANCE)) {
                if (Intrinsics.areEqual(feedback, FaceAlignmentFeedback.FaceAligned.INSTANCE)) {
                    hideFaceAlignmentFeedback();
                    return;
                }
                return;
            }
            i = R.string.onfido_avc_face_alignment_feedback_no_face_detected;
        }
        showFaceAlignmentFeedback(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleFaceAlignmentFeedbackAccessibility(FaceAlignmentFeedback feedback) {
        Integer num = this.accessibilityAnnouncementMap.get(feedback);
        if (num != null) {
            getAnnouncementService().announceStringAsync(new int[]{num.intValue()}, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleTestEnvInFaceAlignment() {
        if (getMotionOptions().isTestEnv()) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C05401(null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleTestEnvInRecordingStarted() {
        if (getMotionOptions().isTestEnv()) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C05411(null), 3, null);
        }
    }

    private final void hideFaceAlignmentFeedback() {
        FeedbackLabelView feedbackLabelView = getBinding().feedbackLabelView;
        Intrinsics.checkNotNullExpressionValue(feedbackLabelView, "feedbackLabelView");
        ViewExtensionsKt.hideWithAlphaAnim$default(feedbackLabelView, 0.0f, 0L, 3, null);
    }

    private final boolean isCameraXEnabled() {
        return ((Boolean) this.isCameraXEnabled.getValue()).booleanValue();
    }

    private final void observeDetectedFace() {
    }

    private final void observeFaceAlignmentFeedback() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = getViewModel().getFaceAlignmentFeedback().subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment.observeFaceAlignmentFeedback.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(FaceAlignmentFeedback p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                MotionCaptureFragment.this.handleFaceAlignmentFeedback(p0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeFaceAlignmentFeedbackAccessibility() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = getViewModel().getFaceAlignmentFeedbackAccessibility().subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment.observeFaceAlignmentFeedbackAccessibility.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(FaceAlignmentFeedback p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                MotionCaptureFragment.this.handleFaceAlignmentFeedbackAccessibility(p0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeViewState() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = getViewModel().getViewState().subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment.observeViewState.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(MotionCaptureViewModel.ViewState it) {
                AvcTimer delayTimer;
                Function0<Unit> function0;
                Intrinsics.checkNotNullParameter(it, "it");
                MotionCameraController motionCameraController = null;
                if (Intrinsics.areEqual(it, MotionCaptureViewModel.ViewState.FaceNotPresent.INSTANCE) || Intrinsics.areEqual(it, MotionCaptureViewModel.ViewState.FaceAlignment.INSTANCE)) {
                    MotionCaptureFragment.this.getDelayStartRecordingTimer().cancel();
                    OnfidoFragmentMotionCaptureBinding binding = MotionCaptureFragment.this.getBinding();
                    FaceAlignmentView faceAlignmentView = binding.faceAlignmentView;
                    Intrinsics.checkNotNullExpressionValue(faceAlignmentView, "faceAlignmentView");
                    ViewExtensionsKt.toVisible$default(faceAlignmentView, false, 1, null);
                    binding.headTurnGuidanceView.hide();
                    HeadTurnCompletedView headTurnCompletedView = binding.headTurnCompletedView;
                    Intrinsics.checkNotNullExpressionValue(headTurnCompletedView, "headTurnCompletedView");
                    ViewExtensionsKt.toGone$default(headTurnCompletedView, false, 1, null);
                    MotionCaptureFragment.this.handleTestEnvInFaceAlignment();
                    return;
                }
                if (Intrinsics.areEqual(it, MotionCaptureViewModel.ViewState.DelayStartRecording.INSTANCE)) {
                    delayTimer = MotionCaptureFragment.this.getDelayStartRecordingTimer();
                    final MotionCaptureFragment motionCaptureFragment = MotionCaptureFragment.this;
                    function0 = new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment.observeViewState.1.2
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
                            motionCaptureFragment.getViewModel().startRecording();
                        }
                    };
                } else {
                    if (Intrinsics.areEqual(it, MotionCaptureViewModel.ViewState.RecordingStarted.INSTANCE)) {
                        HapticFeedback hapticFeedback = MotionCaptureFragment.this.getHapticFeedback();
                        HeadTurnGuidanceView headTurnGuidanceView = MotionCaptureFragment.this.getBinding().headTurnGuidanceView;
                        Intrinsics.checkNotNullExpressionValue(headTurnGuidanceView, "headTurnGuidanceView");
                        hapticFeedback.performTap(headTurnGuidanceView);
                        MotionCaptureFragment.this.getAnnouncementService().announceStringAsync(new int[]{R.string.onfido_avc_face_capture_recording_started_accessibility, R.string.onfido_avc_face_capture_title_accessibility}, true);
                        OnfidoFragmentMotionCaptureBinding binding2 = MotionCaptureFragment.this.getBinding();
                        FaceAlignmentView faceAlignmentView2 = binding2.faceAlignmentView;
                        Intrinsics.checkNotNullExpressionValue(faceAlignmentView2, "faceAlignmentView");
                        ViewExtensionsKt.toGone$default(faceAlignmentView2, false, 1, null);
                        RectDebuggingView faceRectDebuggingView = binding2.faceRectDebuggingView;
                        Intrinsics.checkNotNullExpressionValue(faceRectDebuggingView, "faceRectDebuggingView");
                        ViewExtensionsKt.toGone$default(faceRectDebuggingView, false, 1, null);
                        TextView faceYawAngleDebuggingView = binding2.faceYawAngleDebuggingView;
                        Intrinsics.checkNotNullExpressionValue(faceYawAngleDebuggingView, "faceYawAngleDebuggingView");
                        ViewExtensionsKt.toGone$default(faceYawAngleDebuggingView, false, 1, null);
                        binding2.headTurnGuidanceView.show();
                        MotionCaptureFragment.this.handleTestEnvInRecordingStarted();
                        return;
                    }
                    if (!Intrinsics.areEqual(it, MotionCaptureViewModel.ViewState.DelayFinishRecording.INSTANCE)) {
                        if (Intrinsics.areEqual(it, MotionCaptureViewModel.ViewState.RecordingFinished.INSTANCE)) {
                            OnfidoFragmentMotionCaptureBinding binding3 = MotionCaptureFragment.this.getBinding();
                            binding3.headTurnGuidanceView.hide();
                            HeadTurnCompletedView headTurnCompletedView2 = binding3.headTurnCompletedView;
                            Intrinsics.checkNotNullExpressionValue(headTurnCompletedView2, "headTurnCompletedView");
                            ViewExtensionsKt.toVisible$default(headTurnCompletedView2, false, 1, null);
                            AvcTimer delayTimer2 = MotionCaptureFragment.this.getDelayTimer();
                            final MotionCaptureFragment motionCaptureFragment2 = MotionCaptureFragment.this;
                            delayTimer2.start(2000L, new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment.observeViewState.1.6
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
                                    motionCaptureFragment2.getViewModel().completeFlow();
                                }
                            });
                            MotionCaptureFragment.this.getAnnouncementService().announceStringAsync(new int[]{R.string.onfido_avc_face_capture_title_completed}, true);
                            return;
                        }
                        if (!Intrinsics.areEqual(it, MotionCaptureViewModel.ViewState.Completed.INSTANCE)) {
                            if (it instanceof MotionCaptureViewModel.ViewState.Error) {
                                MotionCaptureFragment.this.handleErrorStates((MotionCaptureViewModel.ViewState.Error) it);
                                return;
                            }
                            return;
                        } else {
                            Navigator navigator = MotionCaptureFragment.this.getMotionHostViewModel().getNavigator();
                            MotionCameraController motionCameraController2 = MotionCaptureFragment.this.cameraController;
                            if (motionCameraController2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("cameraController");
                            } else {
                                motionCameraController = motionCameraController2;
                            }
                            navigator.replace(new MotionUploadScreen(motionCameraController.getVideoFilePath()));
                            return;
                        }
                    }
                    delayTimer = MotionCaptureFragment.this.getDelayTimer();
                    final MotionCaptureFragment motionCaptureFragment3 = MotionCaptureFragment.this;
                    function0 = new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment.observeViewState.1.4
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
                            motionCaptureFragment3.getViewModel().finishRecording();
                        }
                    };
                }
                delayTimer.start(1000L, function0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void setupToolbar() {
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNull(fragmentActivityRequireActivity, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        AppCompatActivity appCompatActivity = (AppCompatActivity) fragmentActivityRequireActivity;
        ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        appCompatActivity.setSupportActionBar(getBinding().avcCaptureToolbar);
        ActionBar supportActionBar2 = appCompatActivity.getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setTitle("");
        }
        enableToolbarBackNavigation(appCompatActivity.getSupportActionBar());
    }

    private final boolean shouldObserveAccessibilityFaceAlignmentFeedback() {
        return getAnnouncementService().isEnabled();
    }

    private final void showAlertDialog(int titleResId, int messageResId, int positiveButtonResId, final Function0<Unit> positiveButtonAction) {
        this.lifecycleAwareDialog.show((56 & 1) != 0 ? null : Integer.valueOf(titleResId), messageResId, (56 & 4) != 0 ? R.string.onfido_ok : positiveButtonResId, (56 & 8) != 0 ? null : null, (56 & 16) != 0 ? false : false, (Function1<? super DialogInterface, Unit>) ((56 & 32) != 0 ? null : null), (Function1<? super DialogInterface, Unit>) ((56 & 64) != 0 ? LifecycleAwareDialog.C07234.INSTANCE : new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment.showAlertDialog.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                invoke2(dialogInterface);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DialogInterface dialog) {
                Intrinsics.checkNotNullParameter(dialog, "dialog");
                dialog.dismiss();
                positiveButtonAction.invoke();
            }
        }));
    }

    private final void showFaceAlignmentFeedback(int resId) {
        FeedbackLabelView feedbackLabelView = getBinding().feedbackLabelView;
        Intrinsics.checkNotNull(feedbackLabelView);
        ViewExtensionsKt.showWithAlphaAnim$default(feedbackLabelView, 0.0f, 0L, 3, null);
        String string = feedbackLabelView.getContext().getString(resId);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        feedbackLabelView.setText(string);
    }

    private final void showRecordingIsTooShortDialog(Function0<Unit> action) {
        showAlertDialog(R.string.onfido_avc_face_capture_alert_too_fast_title, R.string.onfido_avc_face_capture_alert_too_fast_body, R.string.onfido_avc_face_capture_alert_too_fast_button_primary, action);
    }

    private final void showRecordingTimeoutDialog(Function0<Unit> action) {
        showAlertDialog(R.string.onfido_avc_face_capture_alert_timeout_title, R.string.onfido_avc_face_capture_alert_timeout_body, R.string.onfido_avc_face_capture_alert_timeout_button_primary, action);
    }

    private final void startCamera() {
        if (getViewModel().isCompletedState()) {
            return;
        }
        MotionCameraController motionCameraController = this.cameraController;
        if (motionCameraController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraController");
            motionCameraController = null;
        }
        motionCameraController.start(new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.MotionCaptureFragment.startCamera.1
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
                MotionCaptureFragment.this.getViewModel().initialize(MotionCaptureFragment.this.getOvalRect());
            }
        }, new C05482(getMotionHostViewModel()));
    }

    public final OnfidoAnalytics getAnalytics() {
        OnfidoAnalytics onfidoAnalytics = this.analytics;
        if (onfidoAnalytics != null) {
            return onfidoAnalytics;
        }
        Intrinsics.throwUninitializedPropertyAccessException("analytics");
        return null;
    }

    public final AnnouncementService getAnnouncementService() {
        AnnouncementService announcementService = this.announcementService;
        if (announcementService != null) {
            return announcementService;
        }
        Intrinsics.throwUninitializedPropertyAccessException("announcementService");
        return null;
    }

    public final MotionCameraControllerFactory getCameraControllerFactory() {
        MotionCameraControllerFactory motionCameraControllerFactory = this.cameraControllerFactory;
        if (motionCameraControllerFactory != null) {
            return motionCameraControllerFactory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("cameraControllerFactory");
        return null;
    }

    public final AvcTimer getDelayStartRecordingTimer() {
        AvcTimer avcTimer = this.delayStartRecordingTimer;
        if (avcTimer != null) {
            return avcTimer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("delayStartRecordingTimer");
        return null;
    }

    public final AvcTimer getDelayTimer() {
        AvcTimer avcTimer = this.delayTimer;
        if (avcTimer != null) {
            return avcTimer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("delayTimer");
        return null;
    }

    public final FaceDetectorAvcResultMapperTestImpl<Object> getFaceDetectorAvcResultMapperTestImpl() {
        FaceDetectorAvcResultMapperTestImpl<Object> faceDetectorAvcResultMapperTestImpl = this.faceDetectorAvcResultMapperTestImpl;
        if (faceDetectorAvcResultMapperTestImpl != null) {
            return faceDetectorAvcResultMapperTestImpl;
        }
        Intrinsics.throwUninitializedPropertyAccessException("faceDetectorAvcResultMapperTestImpl");
        return null;
    }

    public final HapticFeedback getHapticFeedback() {
        HapticFeedback hapticFeedback = this.hapticFeedback;
        if (hapticFeedback != null) {
            return hapticFeedback;
        }
        Intrinsics.throwUninitializedPropertyAccessException("hapticFeedback");
        return null;
    }

    public final HeadTurnGuidanceViewModel getHeadTurnGuidanceViewModel() {
        HeadTurnGuidanceViewModel headTurnGuidanceViewModel = this.headTurnGuidanceViewModel;
        if (headTurnGuidanceViewModel != null) {
            return headTurnGuidanceViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("headTurnGuidanceViewModel");
        return null;
    }

    public final MotionFaceDetectorFactory getMotionFaceDetectorFactory() {
        MotionFaceDetectorFactory motionFaceDetectorFactory = this.motionFaceDetectorFactory;
        if (motionFaceDetectorFactory != null) {
            return motionFaceDetectorFactory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("motionFaceDetectorFactory");
        return null;
    }

    public final MotionCaptureVariantOptions getMotionOptions() {
        MotionCaptureVariantOptions motionCaptureVariantOptions = this.motionOptions;
        if (motionCaptureVariantOptions != null) {
            return motionCaptureVariantOptions;
        }
        Intrinsics.throwUninitializedPropertyAccessException("motionOptions");
        return null;
    }

    public final SchedulersProvider getSchedulersProvider() {
        SchedulersProvider schedulersProvider = this.schedulersProvider;
        if (schedulersProvider != null) {
            return schedulersProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("schedulersProvider");
        return null;
    }

    public final MotionCaptureViewModel getViewModel() {
        MotionCaptureViewModel motionCaptureViewModel = this.viewModel;
        if (motionCaptureViewModel != null) {
            return motionCaptureViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final MotionCaptureViewModelImpl.Factory getViewModelFactory() {
        MotionCaptureViewModelImpl.Factory factory = this.viewModelFactory;
        if (factory != null) {
            return factory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelFactory");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        getViewModel().onDestroy();
        MotionCaptureComponentHolder.INSTANCE.getInstance().onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (getMotionHostViewModel().hasPermissions()) {
            startCamera();
        } else {
            getMotionHostViewModel().restorePermissions();
        }
        observeViewState();
        observeFaceAlignmentFeedback();
        if (shouldObserveAccessibilityFaceAlignmentFeedback()) {
            announceCameraInUse();
            observeFaceAlignmentFeedbackAccessibility();
        }
        observeDetectedFace();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.lifecycleAwareDialog.dismiss();
        hideFaceAlignmentFeedback();
        getDelayStartRecordingTimer().cancel();
        getDelayTimer().cancel();
        this.compositeDisposable.clear();
        getViewModel().onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        FaceDetectorAvc faceDetectorAvc;
        Intrinsics.checkNotNullParameter(view, "view");
        getCaptureComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        this._binding = OnfidoFragmentMotionCaptureBinding.bind(view);
        this.faceDetectorAvc = getMotionFaceDetectorFactory().create(getShouldUseMlKit());
        MotionCameraControllerFactory cameraControllerFactory = getCameraControllerFactory();
        boolean audioEnabled = getMotionHostViewModel().getAudioEnabled();
        boolean zIsCameraXEnabled = isCameraXEnabled();
        CameraViewfinder camera2PreviewView = getBinding().camera2PreviewView;
        Intrinsics.checkNotNullExpressionValue(camera2PreviewView, "camera2PreviewView");
        PreviewView cameraXPreviewView = getBinding().cameraXPreviewView;
        Intrinsics.checkNotNullExpressionValue(cameraXPreviewView, "cameraXPreviewView");
        FaceDetectorAvc faceDetectorAvc2 = this.faceDetectorAvc;
        FaceDetectorAvc faceDetectorAvc3 = null;
        if (faceDetectorAvc2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceDetectorAvc");
            faceDetectorAvc = null;
        } else {
            faceDetectorAvc = faceDetectorAvc2;
        }
        this.cameraController = cameraControllerFactory.create(this, audioEnabled, zIsCameraXEnabled, camera2PreviewView, cameraXPreviewView, faceDetectorAvc);
        MotionCaptureViewModelImpl.Factory viewModelFactory = getViewModelFactory();
        boolean audioEnabled2 = getMotionHostViewModel().getAudioEnabled();
        MotionCameraController motionCameraController = this.cameraController;
        if (motionCameraController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraController");
            motionCameraController = null;
        }
        FaceDetectorAvc faceDetectorAvc4 = this.faceDetectorAvc;
        if (faceDetectorAvc4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceDetectorAvc");
        } else {
            faceDetectorAvc3 = faceDetectorAvc4;
        }
        setViewModel(viewModelFactory.create(audioEnabled2, motionCameraController, faceDetectorAvc3));
        setupToolbar();
        getBinding().headTurnGuidanceView.initialize(getHeadTurnGuidanceViewModel(), getHapticFeedback(), getAnnouncementService());
        getViewModel().trackScreenAnalyticsEvent();
    }

    public final void setAnalytics(OnfidoAnalytics onfidoAnalytics) {
        Intrinsics.checkNotNullParameter(onfidoAnalytics, "<set-?>");
        this.analytics = onfidoAnalytics;
    }

    public final void setAnnouncementService(AnnouncementService announcementService) {
        Intrinsics.checkNotNullParameter(announcementService, "<set-?>");
        this.announcementService = announcementService;
    }

    public final void setCameraControllerFactory(MotionCameraControllerFactory motionCameraControllerFactory) {
        Intrinsics.checkNotNullParameter(motionCameraControllerFactory, "<set-?>");
        this.cameraControllerFactory = motionCameraControllerFactory;
    }

    public final void setDelayStartRecordingTimer(AvcTimer avcTimer) {
        Intrinsics.checkNotNullParameter(avcTimer, "<set-?>");
        this.delayStartRecordingTimer = avcTimer;
    }

    public final void setDelayTimer(AvcTimer avcTimer) {
        Intrinsics.checkNotNullParameter(avcTimer, "<set-?>");
        this.delayTimer = avcTimer;
    }

    public final void setFaceDetectorAvcResultMapperTestImpl(FaceDetectorAvcResultMapperTestImpl<Object> faceDetectorAvcResultMapperTestImpl) {
        Intrinsics.checkNotNullParameter(faceDetectorAvcResultMapperTestImpl, "<set-?>");
        this.faceDetectorAvcResultMapperTestImpl = faceDetectorAvcResultMapperTestImpl;
    }

    public final void setHapticFeedback(HapticFeedback hapticFeedback) {
        Intrinsics.checkNotNullParameter(hapticFeedback, "<set-?>");
        this.hapticFeedback = hapticFeedback;
    }

    public final void setHeadTurnGuidanceViewModel(HeadTurnGuidanceViewModel headTurnGuidanceViewModel) {
        Intrinsics.checkNotNullParameter(headTurnGuidanceViewModel, "<set-?>");
        this.headTurnGuidanceViewModel = headTurnGuidanceViewModel;
    }

    public final void setMotionFaceDetectorFactory(MotionFaceDetectorFactory motionFaceDetectorFactory) {
        Intrinsics.checkNotNullParameter(motionFaceDetectorFactory, "<set-?>");
        this.motionFaceDetectorFactory = motionFaceDetectorFactory;
    }

    public final void setMotionOptions(MotionCaptureVariantOptions motionCaptureVariantOptions) {
        Intrinsics.checkNotNullParameter(motionCaptureVariantOptions, "<set-?>");
        this.motionOptions = motionCaptureVariantOptions;
    }

    public final void setSchedulersProvider(SchedulersProvider schedulersProvider) {
        Intrinsics.checkNotNullParameter(schedulersProvider, "<set-?>");
        this.schedulersProvider = schedulersProvider;
    }

    public final void setViewModel(MotionCaptureViewModel motionCaptureViewModel) {
        Intrinsics.checkNotNullParameter(motionCaptureViewModel, "<set-?>");
        this.viewModel = motionCaptureViewModel;
    }

    public final void setViewModelFactory(MotionCaptureViewModelImpl.Factory factory) {
        Intrinsics.checkNotNullParameter(factory, "<set-?>");
        this.viewModelFactory = factory;
    }
}
