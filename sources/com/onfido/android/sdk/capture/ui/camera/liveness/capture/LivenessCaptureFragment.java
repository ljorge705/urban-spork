package com.onfido.android.sdk.capture.ui.camera.liveness.capture;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.activity.OnBackPressedCallback;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentKt;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.databinding.OnfidoDummyAccessibilityViewBinding;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentLivenessCaptureBinding;
import com.onfido.android.sdk.capture.detector.face.FaceDetectionRect;
import com.onfido.android.sdk.capture.detector.face.FaceDetectionResult;
import com.onfido.android.sdk.capture.internal.camera.OnfidoCamera;
import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.internal.camera.factory.CameraFactory;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.service.VibratorService;
import com.onfido.android.sdk.capture.ui.BaseActivity;
import com.onfido.android.sdk.capture.ui.BaseFragment;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.OnfidoActivity;
import com.onfido.android.sdk.capture.ui.camera.FaceDetectionFrame;
import com.onfido.android.sdk.capture.ui.camera.Frame;
import com.onfido.android.sdk.capture.ui.camera.OverlayMetrics;
import com.onfido.android.sdk.capture.ui.camera.OverlayTextView;
import com.onfido.android.sdk.capture.ui.camera.OverlayView;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import com.onfido.android.sdk.capture.ui.camera.exception.CameraNotAvailableException;
import com.onfido.android.sdk.capture.ui.camera.exception.CameraNotFoundException;
import com.onfido.android.sdk.capture.ui.camera.exception.InvalidCertificateException;
import com.onfido.android.sdk.capture.ui.camera.exception.TokenExpiredException;
import com.onfido.android.sdk.capture.ui.camera.exception.UnknownCameraException;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayView;
import com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureScreen;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallenge;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengeViewModel;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesViewModel;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessPerformedChallenges;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingView;
import com.onfido.android.sdk.capture.ui.camera.util.CameraPermissionsUtils;
import com.onfido.android.sdk.capture.ui.camera.util.LivenessCaptureLayoutAdjuster;
import com.onfido.android.sdk.capture.ui.options.Orientation;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;
import com.onfido.android.sdk.capture.utils.AccessibilityExtensionsKt;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.FileUtils;
import com.onfido.android.sdk.capture.utils.FragmentExtentionsKt;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.android.sdk.capture.utils.LifecycleAwareDialog;
import com.onfido.android.sdk.capture.utils.LifecycleDisposableKt;
import com.onfido.android.sdk.capture.utils.StringExtensionsKt;
import com.onfido.android.sdk.capture.utils.StringRepresentation;
import com.onfido.android.sdk.capture.utils.ToolbarExtensionsKt;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.android.sdk.capture.utils.ViewUtil;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Provider;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.sentry.SentryEvent;
import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

@Metadata(d1 = {"\u0000¬\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 µ\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002µ\u0001B\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020SH\u0002J\u0012\u0010T\u001a\u00020Q2\b\b\u0001\u0010U\u001a\u00020\u0015H\u0002J\u0014\u0010V\u001a\u00020Q2\n\u0010W\u001a\u00060Xj\u0002`YH\u0002J\u0010\u0010Z\u001a\u00020Q2\u0006\u0010[\u001a\u00020\\H\u0002J\b\u0010]\u001a\u00020QH\u0002J\b\u0010^\u001a\u00020QH\u0002J\b\u0010_\u001a\u00020QH\u0002J\b\u0010`\u001a\u00020QH\u0002J\b\u0010a\u001a\u00020\rH\u0002J\b\u0010b\u001a\u00020QH\u0002J\b\u0010c\u001a\u00020QH\u0002J\b\u0010d\u001a\u00020QH\u0002J\u0010\u0010e\u001a\u00020Q2\u0006\u0010f\u001a\u00020gH\u0016J\u0010\u0010h\u001a\u00020Q2\u0006\u0010i\u001a\u00020jH\u0002J\b\u0010k\u001a\u00020QH\u0002J\b\u0010l\u001a\u00020QH\u0016J\b\u0010m\u001a\u00020QH\u0016J\b\u0010n\u001a\u00020QH\u0016J\u0010\u0010o\u001a\u00020Q2\u0006\u0010p\u001a\u00020qH\u0002J\u0010\u0010r\u001a\u00020Q2\u0006\u0010s\u001a\u00020tH\u0002J\b\u0010u\u001a\u00020QH\u0002J\u0010\u0010v\u001a\u00020Q2\u0006\u0010w\u001a\u00020xH\u0016J\b\u0010y\u001a\u00020QH\u0016J\u0010\u0010z\u001a\u00020Q2\u0006\u0010{\u001a\u00020|H\u0002J\u0010\u0010}\u001a\u00020Q2\u0006\u0010~\u001a\u00020\u007fH\u0002J\u0011\u0010\u0080\u0001\u001a\u00020Q2\u0006\u00107\u001a\u000208H\u0016J\t\u0010\u0081\u0001\u001a\u00020QH\u0016J\t\u0010\u0082\u0001\u001a\u00020QH\u0016J\t\u0010\u0083\u0001\u001a\u00020QH\u0016J\t\u0010\u0084\u0001\u001a\u00020QH\u0016J\t\u0010\u0085\u0001\u001a\u00020QH\u0002J\t\u0010\u0086\u0001\u001a\u00020QH\u0016J\u0012\u0010\u0087\u0001\u001a\u00020Q2\u0007\u0010\u0088\u0001\u001a\u00020xH\u0002J\t\u0010\u0089\u0001\u001a\u00020QH\u0007J\u0013\u0010\u008a\u0001\u001a\u00020Q2\b\u0010\u008b\u0001\u001a\u00030\u008c\u0001H\u0002J\u001f\u0010\u008d\u0001\u001a\u00020Q2\b\u0010\u008e\u0001\u001a\u00030\u008f\u00012\n\u0010\u0090\u0001\u001a\u0005\u0018\u00010\u0091\u0001H\u0016J\t\u0010\u0092\u0001\u001a\u00020QH\u0002J\t\u0010\u0093\u0001\u001a\u00020QH\u0002J\t\u0010\u0094\u0001\u001a\u00020QH\u0002J\t\u0010\u0095\u0001\u001a\u00020QH\u0002J\u0012\u0010\u0096\u0001\u001a\u00020Q2\u0007\u0010\u0097\u0001\u001a\u00020SH\u0002J\u0012\u0010\u0098\u0001\u001a\u00020Q2\u0007\u0010\u0097\u0001\u001a\u00020SH\u0002J\t\u0010\u0099\u0001\u001a\u00020QH\u0002J\t\u0010\u009a\u0001\u001a\u00020QH\u0002J\u0013\u0010\u009b\u0001\u001a\u00020Q2\b\u0010\u008e\u0001\u001a\u00030\u008f\u0001H\u0002JO\u0010\u009c\u0001\u001a\u00020Q2\u0007\u0010\u009d\u0001\u001a\u00020\u00152\u0007\u0010\u009e\u0001\u001a\u00020\u00152\t\b\u0002\u0010\u009f\u0001\u001a\u00020\u00152'\u0010 \u0001\u001a\"\u0012\u0017\u0012\u00150\u008c\u0001¢\u0006\u000f\b¢\u0001\u0012\n\b£\u0001\u0012\u0005\b\b(¤\u0001\u0012\u0004\u0012\u00020Q0¡\u0001H\u0002J\u001c\u0010¥\u0001\u001a\u00020Q2\u0007\u0010¦\u0001\u001a\u00020x2\b\u0010§\u0001\u001a\u00030¨\u0001H\u0002J\t\u0010©\u0001\u001a\u00020QH\u0002J\t\u0010ª\u0001\u001a\u00020QH\u0002J\t\u0010«\u0001\u001a\u00020QH\u0002J\u0012\u0010¬\u0001\u001a\u00020Q2\u0007\u0010\u00ad\u0001\u001a\u00020-H\u0002J\t\u0010®\u0001\u001a\u00020QH\u0002J\u001b\u0010¯\u0001\u001a\u00020Q2\u0010\b\u0002\u0010°\u0001\u001a\t\u0012\u0004\u0012\u00020Q0±\u0001H\u0002J\u0017\u0010²\u0001\u001a\u00020-*\u00020t2\b\u0010³\u0001\u001a\u00030´\u0001H\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u00020\u000f8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001b\u001a\u00020\u001c8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010#\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u001e\u0010&\u001a\u00020'8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u000e\u0010,\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010.\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b/\u00100R\u000e\u00101\u001a\u000202X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000208X\u0082.¢\u0006\u0002\n\u0000R\u0014\u00109\u001a\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;R\u0010\u0010<\u001a\u0004\u0018\u00010=X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010>\u001a\u00020?8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u001b\u0010D\u001a\u00020E8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bH\u0010I\u001a\u0004\bF\u0010GR$\u0010J\u001a\b\u0012\u0004\u0012\u00020E0K8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010O¨\u0006¶\u0001"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureFragment;", "Lcom/onfido/android/sdk/capture/ui/BaseFragment;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessOverlayView$ChallengesListener;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/loading/LivenessChallengesLoadingView$Listener;", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayView$Listener;", "()V", "_binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentLivenessCaptureBinding;", "_dummyAccessibilityBinding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoDummyAccessibilityViewBinding;", "_layoutAdjuster", "Lcom/onfido/android/sdk/capture/ui/camera/util/LivenessCaptureLayoutAdjuster;", "_overlayView", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayView;", "announcementService", "Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "getAnnouncementService$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "setAnnouncementService$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;)V", "backgroundColorCaptureScreen", "", "getBackgroundColorCaptureScreen", "()I", "binding", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentLivenessCaptureBinding;", "cameraFactory", "Lcom/onfido/android/sdk/capture/internal/camera/factory/CameraFactory;", "getCameraFactory$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/camera/factory/CameraFactory;", "setCameraFactory$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/camera/factory/CameraFactory;)V", "captureButton", "Lcom/onfido/android/sdk/capture/ui/widget/OnfidoButton;", "dummyAccessibilityBinding", "getDummyAccessibilityBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoDummyAccessibilityViewBinding;", "imageUtils", "Lcom/onfido/android/sdk/capture/utils/ImageUtils;", "getImageUtils$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/utils/ImageUtils;", "setImageUtils$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/utils/ImageUtils;)V", "isCameraViewInitialised", "", "layoutAdjuster", "getLayoutAdjuster", "()Lcom/onfido/android/sdk/capture/ui/camera/util/LivenessCaptureLayoutAdjuster;", "lifecycleAwareDialog", "Lcom/onfido/android/sdk/capture/utils/LifecycleAwareDialog;", "livenessChallengesLoadingView", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/loading/LivenessChallengesLoadingView;", "onfidoCamera", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera;", "overlayMetrics", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayMetrics;", "overlayView", "getOverlayView", "()Lcom/onfido/android/sdk/capture/ui/camera/OverlayView;", "recorder", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoRecorder;", "vibratorService", "Lcom/onfido/android/sdk/capture/internal/service/VibratorService;", "getVibratorService$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/service/VibratorService;", "setVibratorService$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/service/VibratorService;)V", "viewModel", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureViewModel;", "getViewModel", "()Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "viewModelProvider", "Lcom/onfido/javax/inject/Provider;", "getViewModelProvider$onfido_capture_sdk_core_release", "()Lcom/onfido/javax/inject/Provider;", "setViewModelProvider$onfido_capture_sdk_core_release", "(Lcom/onfido/javax/inject/Provider;)V", "adjustDummyAccessibilityView", "", "visibleCaptureRect", "Landroid/graphics/RectF;", "changeStatusBarColor", "color", "finishWithException", SentryEvent.JsonKeys.EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "finishWithResult", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureScreen$LivenessCaptureResult;", "hideLivenessControlButton", "hideLoading", "inflateCaptureButton", "inflateDummyAccessibilityView", "inflateOverlayView", "onCameraNotFound", "onCameraStarted", "onCameraUnavailable", "onChallengeLoadingViewStateChanged", "screenState", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/loading/LivenessChallengesLoadingView$ScreenState;", "onChallengesAvailable", "livenessChallengesViewModel", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesViewModel;", "onChallengesCompleted", "onChallengesErrorBackPressed", "onDestroyView", "onErrorObservingHeadTurnResults", "onErrorTakingPicture", "error", "", "onFaceDetected", "faceDetectionResult", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult;", "onFaceOutTimeout", "onInvalidCertificateDetected", "message", "", "onLivenessChallengeFinished", "onNextChallenge", "livenessChallengeViewModel", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengeViewModel;", "onNextFrame", "frame", "", "onOverlayMetrics", "onPause", "onResume", "onStart", "onStop", "onStorageThresholdReached", "onTokenExpired", "onVideoCaptured", "filePath", "onVideoTimeoutExceeded", "onVideoTimeoutRetryClick", "dialog", "Landroid/content/DialogInterface;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "removeViewsAccessibility", "setCaptureFrameContentDescriptionAndTitle", "setColorsForCaptureScreen", "setOverlay", "setOverlayMargin", "captureRect", "setVideoRecordingIndicatorMargin", "setupObservers", "setupOverlayView", "setupUiElements", "showDialog", "titleResId", "messageResId", "positiveButtonResId", "callback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "diadlog", "showFaceLivenessConfirmationScreen", "videoPath", "performedChallenges", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessPerformedChallenges;", "showLivenessButtonAndFocusWithDelay", "showLivenessControlButtonWithAccessibilityEvent", "startCamera", "startLivenessVideoRecording", "isStartedManually", "startTrackingLivenessCapture", "startVideoRecording", "videoRecordingStarted", "Lkotlin/Function0;", "isInsideOval", "ovalFrame", "Lcom/onfido/android/sdk/capture/ui/camera/Frame;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LivenessCaptureFragment extends BaseFragment implements LivenessOverlayView.ChallengesListener, LivenessChallengesLoadingView.Listener, OverlayView.Listener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final float FACE_DETECTION_OVAL_THRESHOLD_PERCENTAGE = 0.3f;
    private static final String KEY_REQUEST = "key_request";
    private static final long LIVENESS_CHALLENGE_FINISHED_DELAY_MS = 500;
    public static final String VIDEO_PATH = "video_path";
    private OnfidoFragmentLivenessCaptureBinding _binding;
    private OnfidoDummyAccessibilityViewBinding _dummyAccessibilityBinding;
    private LivenessCaptureLayoutAdjuster _layoutAdjuster;
    private OverlayView _overlayView;

    @Inject
    public AnnouncementService announcementService;

    @Inject
    public CameraFactory cameraFactory;
    private OnfidoButton captureButton;

    @Inject
    public ImageUtils imageUtils;
    private boolean isCameraViewInitialised;
    private final LifecycleAwareDialog lifecycleAwareDialog;
    private LivenessChallengesLoadingView livenessChallengesLoadingView;
    private OnfidoCamera onfidoCamera;
    private OverlayMetrics overlayMetrics;
    private OnfidoCamera.VideoRecorder recorder;

    @Inject
    public VibratorService vibratorService;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    @Inject
    public Provider<LivenessCaptureViewModel> viewModelProvider;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0001¢\u0006\u0002\b\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\u00020\u00068\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0002¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureFragment$Companion;", "", "()V", "FACE_DETECTION_OVAL_THRESHOLD_PERCENTAGE", "", "KEY_REQUEST", "", "LIVENESS_CHALLENGE_FINISHED_DELAY_MS", "", "VIDEO_PATH", "getVIDEO_PATH$annotations", "createInstance", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureFragment;", "requestKey", "createInstance$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public static /* synthetic */ void getVIDEO_PATH$annotations() {
        }

        @JvmStatic
        public final LivenessCaptureFragment createInstance$onfido_capture_sdk_core_release(String requestKey) {
            Intrinsics.checkNotNullParameter(requestKey, "requestKey");
            LivenessCaptureFragment livenessCaptureFragment = new LivenessCaptureFragment();
            livenessCaptureFragment.setArguments(BundleKt.bundleOf(TuplesKt.to("key_request", requestKey)));
            return livenessCaptureFragment;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$onFaceDetected$1", f = "LivenessCaptureFragment.kt", i = {}, l = {492, 493, 496}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$onFaceDetected$1, reason: invalid class name and case insensitive filesystem */
    static final class C06601 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06601(Continuation<? super C06601> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LivenessCaptureFragment.this.new C06601(continuation);
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x0073 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 3
                r3 = 1
                r4 = 2
                if (r1 == 0) goto L25
                if (r1 == r3) goto L21
                if (r1 == r4) goto L1d
                if (r1 != r2) goto L15
                kotlin.ResultKt.throwOnFailure(r7)
                goto L74
            L15:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L1d:
                kotlin.ResultKt.throwOnFailure(r7)
                goto L4e
            L21:
                kotlin.ResultKt.throwOnFailure(r7)
                goto L3b
            L25:
                kotlin.ResultKt.throwOnFailure(r7)
                com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment r7 = com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.this
                com.onfido.android.sdk.capture.internal.service.VibratorService r7 = r7.getVibratorService$onfido_capture_sdk_core_release()
                io.reactivex.rxjava3.core.Completable r7 = r7.vibrateForSuccess()
                r6.label = r3
                java.lang.Object r7 = kotlinx.coroutines.rx3.RxAwaitKt.await(r7, r6)
                if (r7 != r0) goto L3b
                return r0
            L3b:
                com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment r7 = com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.this
                com.onfido.android.sdk.capture.internal.service.AnnouncementService r7 = r7.getAnnouncementService$onfido_capture_sdk_core_release()
                io.reactivex.rxjava3.core.Completable r7 = r7.interruptAnnouncement()
                r6.label = r4
                java.lang.Object r7 = kotlinx.coroutines.rx3.RxAwaitKt.await(r7, r6)
                if (r7 != r0) goto L4e
                return r0
            L4e:
                com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment r7 = com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.this
                com.onfido.android.sdk.capture.internal.service.AnnouncementService r7 = r7.getAnnouncementService$onfido_capture_sdk_core_release()
                com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment r1 = com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.this
                int r3 = com.onfido.android.sdk.capture.R.string.onfido_video_capture_frame_success_accessibility
                java.lang.String r1 = r1.getString(r3)
                java.lang.String r3 = "getString(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
                java.lang.String[] r1 = new java.lang.String[]{r1}
                r3 = 0
                r5 = 0
                io.reactivex.rxjava3.core.Completable r7 = com.onfido.android.sdk.capture.internal.service.AnnouncementService.announceString$default(r7, r1, r3, r4, r5)
                r6.label = r2
                java.lang.Object r7 = kotlinx.coroutines.rx3.RxAwaitKt.await(r7, r6)
                if (r7 != r0) goto L74
                return r0
            L74:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.C06601.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06601) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$onFaceDetected$2", f = "LivenessCaptureFragment.kt", i = {}, l = {503}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$onFaceDetected$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LivenessCaptureFragment.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                String string = LivenessCaptureFragment.this.getString(R.string.onfido_video_capture_frame_success_accessibility);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                long j = StringExtensionsKt.readingTimeMilliseconds(string);
                this.label = 1;
                if (DelayKt.delay(j, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            OnfidoCamera.VideoRecorder videoRecorder = LivenessCaptureFragment.this.recorder;
            if (videoRecorder == null || !videoRecorder.isRecording()) {
                OverlayView.resetFaceDetectedState$default(LivenessCaptureFragment.this.getOverlayView(), false, false, false, 7, null);
                LivenessCaptureFragment.this.getViewModel().onAutoLivenessRecordingStart$onfido_capture_sdk_core_release();
                LivenessCaptureFragment.this.startLivenessVideoRecording(false);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$onVideoCaptured$1", f = "LivenessCaptureFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$onVideoCaptured$1, reason: invalid class name and case insensitive filesystem */
    static final class C06641 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $filePath;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06641(String str, Continuation<? super C06641> continuation) {
            super(2, continuation);
            this.$filePath = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LivenessCaptureFragment.this.new C06641(this.$filePath, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            LivenessCaptureFragment livenessCaptureFragment = LivenessCaptureFragment.this;
            livenessCaptureFragment.showFaceLivenessConfirmationScreen(this.$filePath, livenessCaptureFragment.getViewModel().getUploadChallengesList$onfido_capture_sdk_core_release());
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06641) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$showLivenessButtonAndFocusWithDelay$1", f = "LivenessCaptureFragment.kt", i = {}, l = {455}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$showLivenessButtonAndFocusWithDelay$1, reason: invalid class name and case insensitive filesystem */
    static final class C06691 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $delayTime;
        int label;
        final /* synthetic */ LivenessCaptureFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06691(long j, LivenessCaptureFragment livenessCaptureFragment, Continuation<? super C06691> continuation) {
            super(2, continuation);
            this.$delayTime = j;
            this.this$0 = livenessCaptureFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C06691(this.$delayTime, this.this$0, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                long j = this.$delayTime;
                this.label = 1;
                if (DelayKt.delay(j, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            this.this$0.showLivenessControlButtonWithAccessibilityEvent();
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06691) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public LivenessCaptureFragment() {
        super(R.layout.onfido_fragment_liveness_capture);
        Function0<ViewModelProvider.Factory> function0 = new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$viewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                final LivenessCaptureFragment livenessCaptureFragment = this.this$0;
                return new ViewModelProvider.Factory() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$viewModel$2$invoke$$inlined$createViewModelFactory$1
                    @Override // androidx.lifecycle.ViewModelProvider.Factory
                    public <T extends ViewModel> T create(Class<T> modelClass) {
                        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                        LivenessCaptureViewModel livenessCaptureViewModel = livenessCaptureFragment.getViewModelProvider$onfido_capture_sdk_core_release().get();
                        Intrinsics.checkNotNull(livenessCaptureViewModel, "null cannot be cast to non-null type T of com.onfido.android.sdk.capture.component.utils.ViewModelExtKt.createViewModelFactory.<no name provided>.create");
                        return livenessCaptureViewModel;
                    }
                };
            }
        };
        final Function0<Fragment> function02 = new Function0<Fragment>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) function02.invoke();
            }
        });
        final Function0 function03 = null;
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(LivenessCaptureViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$special$$inlined$viewModels$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function04 = function03;
                if (function04 != null && (creationExtras = (CreationExtras) function04.invoke()) != null) {
                    return creationExtras;
                }
                ViewModelStoreOwner viewModelStoreOwnerM4679viewModels$lambda1 = FragmentViewModelLazyKt.m4679viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM4679viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM4679viewModels$lambda1 : null;
                CreationExtras defaultViewModelCreationExtras = hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : null;
                return defaultViewModelCreationExtras == null ? CreationExtras.Empty.INSTANCE : defaultViewModelCreationExtras;
            }
        }, function0);
        this.lifecycleAwareDialog = new LifecycleAwareDialog(this, (Function1) null, 2, (DefaultConstructorMarker) null);
    }

    private final void adjustDummyAccessibilityView(RectF visibleCaptureRect) {
        Rect rect = new Rect();
        visibleCaptureRect.roundOut(rect);
        View dummyAccessibility = getDummyAccessibilityBinding().dummyAccessibility;
        Intrinsics.checkNotNullExpressionValue(dummyAccessibility, "dummyAccessibility");
        ViewGroup.LayoutParams layoutParams = dummyAccessibility.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.width = rect.width();
        layoutParams2.height = rect.height();
        layoutParams2.leftMargin = rect.left;
        layoutParams2.topMargin = rect.top;
        layoutParams2.bottomMargin = getResources().getDimensionPixelSize(R.dimen.onfido_capture_instructions_outer_top_margin);
        dummyAccessibility.setLayoutParams(layoutParams2);
    }

    private final void changeStatusBarColor(int color) {
        Window window = requireActivity().getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(color);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishWithException(Exception exception) {
        finishWithResult(new LivenessCaptureScreen.LivenessCaptureResult.Error(exception));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishWithResult(LivenessCaptureScreen.LivenessCaptureResult result) {
        if (FragmentExtentionsKt.isAttached(this)) {
            String string = requireArguments().getString("key_request");
            if (string == null) {
                throw new IllegalArgumentException("request key argument is missing".toString());
            }
            FragmentKt.setFragmentResult(this, string, LivenessCaptureScreen.INSTANCE.storeResult(result));
        }
    }

    private final int getBackgroundColorCaptureScreen() {
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        return ContextUtilsKt.color(contextRequireContext, R.color.onfido_camera_blur);
    }

    private final OnfidoFragmentLivenessCaptureBinding getBinding() {
        OnfidoFragmentLivenessCaptureBinding onfidoFragmentLivenessCaptureBinding = this._binding;
        Intrinsics.checkNotNull(onfidoFragmentLivenessCaptureBinding);
        return onfidoFragmentLivenessCaptureBinding;
    }

    private final OnfidoDummyAccessibilityViewBinding getDummyAccessibilityBinding() {
        OnfidoDummyAccessibilityViewBinding onfidoDummyAccessibilityViewBinding = this._dummyAccessibilityBinding;
        Intrinsics.checkNotNull(onfidoDummyAccessibilityViewBinding);
        return onfidoDummyAccessibilityViewBinding;
    }

    private final LivenessCaptureLayoutAdjuster getLayoutAdjuster() {
        LivenessCaptureLayoutAdjuster livenessCaptureLayoutAdjuster = this._layoutAdjuster;
        Intrinsics.checkNotNull(livenessCaptureLayoutAdjuster);
        return livenessCaptureLayoutAdjuster;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OverlayView getOverlayView() {
        OverlayView overlayView = this._overlayView;
        Intrinsics.checkNotNull(overlayView);
        return overlayView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LivenessCaptureViewModel getViewModel() {
        return (LivenessCaptureViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideLivenessControlButton() {
        OnfidoButton onfidoButton = this.captureButton;
        if (onfidoButton != null) {
            ViewExtensionsKt.toInvisible(onfidoButton, false);
        }
    }

    private final void hideLoading() {
        if (FragmentExtentionsKt.isAttached(this)) {
            FragmentActivity fragmentActivityRequireActivity = requireActivity();
            Intrinsics.checkNotNull(fragmentActivityRequireActivity, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.BaseActivity");
            ((BaseActivity) fragmentActivityRequireActivity).dismissLoadingDialog$onfido_capture_sdk_core_release();
        }
    }

    private final void inflateCaptureButton() {
        this.captureButton = (OnfidoButton) getLayoutInflater().inflate(R.layout.onfido_capture_button_video, (ViewGroup) getBinding().content, true).findViewById(R.id.livenessControlButton);
        LivenessCaptureLayoutAdjuster layoutAdjuster = getLayoutAdjuster();
        OnfidoButton onfidoButton = this.captureButton;
        Intrinsics.checkNotNull(onfidoButton);
        layoutAdjuster.setCaptureInstructionsAboveView(onfidoButton);
        OnfidoButton onfidoButton2 = this.captureButton;
        if (onfidoButton2 != null) {
            onfidoButton2.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$$ExternalSyntheticLambda7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LivenessCaptureFragment.inflateCaptureButton$lambda$11(this.f$0, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void inflateCaptureButton$lambda$11(LivenessCaptureFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OnfidoCamera.VideoRecorder videoRecorder = this$0.recorder;
        if (videoRecorder == null || !videoRecorder.isRecording()) {
            this$0.getViewModel().onManualLivenessRecordingStart$onfido_capture_sdk_core_release();
            this$0.startLivenessVideoRecording(true);
        } else {
            this$0.getViewModel().onManualLivenessNextClick$onfido_capture_sdk_core_release();
            this$0.getViewModel().issueNextChallenge$onfido_capture_sdk_core_release();
        }
    }

    private final void inflateDummyAccessibilityView() {
        this._dummyAccessibilityBinding = OnfidoDummyAccessibilityViewBinding.inflate(getLayoutInflater(), getBinding().content, true);
        setCaptureFrameContentDescriptionAndTitle();
    }

    private final OverlayView inflateOverlayView() {
        View viewInflate = getLayoutInflater().inflate(R.layout.onfido_view_overlay_video, (ViewGroup) getBinding().contentLayout, false);
        Intrinsics.checkNotNull(viewInflate, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.camera.OverlayView");
        return (OverlayView) viewInflate;
    }

    private final boolean isInsideOval(FaceDetectionResult faceDetectionResult, Frame frame) {
        if (faceDetectionResult instanceof FaceDetectionResult.FaceDetected) {
            float width = frame.getWidth() * FACE_DETECTION_OVAL_THRESHOLD_PERCENTAGE;
            float left = frame.getLeft() - width;
            float left2 = frame.getLeft() + frame.getWidth() + width;
            float top = frame.getTop() - width;
            float top2 = frame.getTop() + frame.getHeight() + width;
            FaceDetectionRect faceRect = ((FaceDetectionResult.FaceDetected) faceDetectionResult).getFaceRect();
            if (faceRect.getLeft() >= left && faceRect.getTop() >= top && faceRect.getTop() + faceRect.height$onfido_capture_sdk_core_release() <= top2 && faceRect.getLeft() + faceRect.width$onfido_capture_sdk_core_release() <= left2) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCameraNotFound() {
        getViewModel().trackCaptureError$onfido_capture_sdk_core_release();
        hideLoading();
        showDialog$default(this, R.string.onfido_generic_error_title, R.string.onfido_generic_error_camera_unavailable, 0, new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.onCameraNotFound.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                invoke2(dialogInterface);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DialogInterface it) {
                Intrinsics.checkNotNullParameter(it, "it");
                LivenessCaptureFragment.this.finishWithException(CameraNotFoundException.INSTANCE);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCameraStarted() {
        OnfidoCamera onfidoCamera = this.onfidoCamera;
        if (onfidoCamera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
            onfidoCamera = null;
        }
        Disposable disposableSubscribe = onfidoCamera.observeFrame().subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.onCameraStarted.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                LivenessCaptureFragment.this.onNextFrame(p0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        LifecycleDisposableKt.disposeOnDestroy(disposableSubscribe, viewLifecycleOwner);
        this.isCameraViewInitialised = true;
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        LivenessChallengesLoadingView livenessChallengesLoadingView = new LivenessChallengesLoadingView(contextRequireContext, null, 0, 6, null);
        livenessChallengesLoadingView.setListener(this);
        livenessChallengesLoadingView.fetchChallenges();
        this.livenessChallengesLoadingView = livenessChallengesLoadingView;
        getBinding().content.addView(this.livenessChallengesLoadingView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCameraUnavailable() {
        getViewModel().trackCaptureError$onfido_capture_sdk_core_release();
        hideLoading();
        showDialog$default(this, R.string.onfido_generic_error_title, R.string.onfido_generic_error_camera_used_by_other_app, 0, new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.onCameraUnavailable.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                invoke2(dialogInterface);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DialogInterface it) {
                Intrinsics.checkNotNullParameter(it, "it");
                LivenessCaptureFragment.this.finishWithException(CameraNotAvailableException.INSTANCE);
            }
        }, 4, null);
    }

    private final void onChallengesAvailable(LivenessChallengesViewModel livenessChallengesViewModel) {
        getBinding().content.removeView(this.livenessChallengesLoadingView);
        LivenessCaptureLayoutAdjuster layoutAdjuster = getLayoutAdjuster();
        OnfidoButton onfidoButton = this.captureButton;
        Intrinsics.checkNotNull(onfidoButton);
        layoutAdjuster.setCaptureInstructionsAboveView(onfidoButton);
        ViewExtensionsKt.toVisible$default(getOverlayView(), false, 1, null);
        OverlayTextView overlayTextContainer = getBinding().overlayTextContainer;
        Intrinsics.checkNotNullExpressionValue(overlayTextContainer, "overlayTextContainer");
        ViewExtensionsKt.toVisible$default(overlayTextContainer, false, 1, null);
        getViewModel().startLivenessProcessing$onfido_capture_sdk_core_release(livenessChallengesViewModel);
        AnnouncementService announcementService$onfido_capture_sdk_core_release = getAnnouncementService$onfido_capture_sdk_core_release();
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(2048);
        Intrinsics.checkNotNullExpressionValue(accessibilityEventObtain, "obtain(...)");
        announcementService$onfido_capture_sdk_core_release.sendEvent(accessibilityEventObtain).blockingAwait();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onChallengesCompleted() {
        OnfidoCamera.VideoRecorder videoRecorder = this.recorder;
        if (videoRecorder != null) {
            videoRecorder.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onErrorTakingPicture(final Throwable error) {
        hideLoading();
        showDialog$default(this, R.string.onfido_generic_error_title, R.string.onfido_generic_error_video_capture, 0, new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.onErrorTakingPicture.1
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
            public final void invoke2(DialogInterface it) {
                Intrinsics.checkNotNullParameter(it, "it");
                LivenessCaptureFragment livenessCaptureFragment = LivenessCaptureFragment.this;
                Throwable th = error;
                Intrinsics.checkNotNull(th, "null cannot be cast to non-null type java.lang.Exception{ kotlin.TypeAliasesKt.Exception }");
                livenessCaptureFragment.finishWithException((Exception) th);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFaceDetected(FaceDetectionResult faceDetectionResult) {
        if (faceDetectionResult instanceof FaceDetectionResult.FaceDetected) {
            ImageUtils imageUtils$onfido_capture_sdk_core_release = getImageUtils$onfido_capture_sdk_core_release();
            OverlayMetrics overlayMetrics = this.overlayMetrics;
            if (overlayMetrics == null) {
                Intrinsics.throwUninitializedPropertyAccessException("overlayMetrics");
                overlayMetrics = null;
            }
            Frame frameLimitRect$onfido_capture_sdk_core_release = imageUtils$onfido_capture_sdk_core_release.limitRect$onfido_capture_sdk_core_release(overlayMetrics.getRealCaptureRect(), ((FaceDetectionResult.FaceDetected) faceDetectionResult).getCropRect());
            OnfidoCamera.VideoRecorder videoRecorder = this.recorder;
            if ((videoRecorder == null || !videoRecorder.isRecording()) && isInsideOval(faceDetectionResult, frameLimitRect$onfido_capture_sdk_core_release) && !getViewModel().isManualCapture$onfido_capture_sdk_core_release()) {
                OnfidoButton onfidoButton = this.captureButton;
                if (onfidoButton != null) {
                    ViewExtensionsKt.toInvisible(onfidoButton, false);
                }
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C06601(null), 3, null);
                ViewUtil.setViewVisibilityWithoutAnimation(getBinding().overlayTextContainer, false);
                OverlayView.showFaceConfirmationTick$default(getOverlayView(), null, 1, null);
                getViewModel().cancelFaceDetectionJobs$onfido_capture_sdk_core_release();
                LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new AnonymousClass2(null), 3, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFaceOutTimeout() {
        OnfidoCamera.VideoRecorder videoRecorder = this.recorder;
        if (videoRecorder != null) {
            videoRecorder.cancel();
        }
        getBinding().livenessOverlayView.stopFaceTracking$onfido_capture_sdk_core_release();
        showDialog(R.string.onfido_video_capture_prompt_header_restart, R.string.onfido_video_capture_prompt_detail_restart, R.string.onfido_video_capture_prompt_button_restart, new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.onFaceOutTimeout.1
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
                LivenessCaptureFragment.this.onVideoTimeoutRetryClick(dialog);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onNextChallenge(LivenessChallengeViewModel livenessChallengeViewModel) {
        LivenessChallenge challenge = livenessChallengeViewModel.getChallenge();
        boolean zIsLastChallenge = livenessChallengeViewModel.isLastChallenge();
        getBinding().livenessOverlayView.updateInfo$onfido_capture_sdk_core_release(challenge, getViewModel().isManualCapture$onfido_capture_sdk_core_release());
        String string = getString(zIsLastChallenge ? R.string.onfido_video_capture_button_primary_finish : R.string.onfido_video_capture_button_primary_fallback);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        OnfidoButton onfidoButton = this.captureButton;
        if (onfidoButton != null) {
            onfidoButton.setText(string);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onNextFrame(Object frame) {
        Intrinsics.checkNotNull(frame, "null cannot be cast to non-null type com.onfido.android.sdk.capture.internal.camera.OnfidoImage");
        OnfidoImage onfidoImage = (OnfidoImage) frame;
        getViewModel().onNextFaceDetectionFrame$onfido_capture_sdk_core_release(new FaceDetectionFrame(onfidoImage.getData(), onfidoImage.getWidth(), onfidoImage.getHeight(), onfidoImage.getRotation(), onfidoImage.getCropRect(), onfidoImage.getBitmap()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onStorageThresholdReached() {
        OnfidoCamera.VideoRecorder videoRecorder = this.recorder;
        if (videoRecorder != null) {
            videoRecorder.cancel();
        }
        showDialog(R.string.onfido_video_capture_error_storage_title, R.string.onfido_video_capture_error_storage_detail, R.string.onfido_ok, new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.onStorageThresholdReached.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                invoke2(dialogInterface);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DialogInterface it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (FragmentExtentionsKt.isAttached(LivenessCaptureFragment.this)) {
                    LivenessCaptureFragment.this.requireActivity().onBackPressed();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onVideoCaptured(String filePath) {
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new C06641(filePath, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onVideoTimeoutRetryClick(DialogInterface dialog) {
        dialog.dismiss();
        getViewModel().trackVideoTimeoutRetryButtonClicked$onfido_capture_sdk_core_release();
        if (FragmentExtentionsKt.isAttached(this)) {
            requireActivity().onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeViewsAccessibility() {
        getDummyAccessibilityBinding().dummyAccessibility.setImportantForAccessibility(2);
        ToolbarExtensionsKt.makeTitleNotImportantForAccessibility(ContextUtilsKt.requireToolbarHost(this).getToolbar());
    }

    private final void setCaptureFrameContentDescriptionAndTitle() {
        if (FragmentExtentionsKt.isAttached(this)) {
            StringRepresentation.SingleStringResIdRepresentation singleStringResIdRepresentation = new StringRepresentation.SingleStringResIdRepresentation(R.string.onfido_video_capture_frame_accessibility);
            FragmentActivity fragmentActivityRequireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
            getDummyAccessibilityBinding().dummyAccessibility.setContentDescription(singleStringResIdRepresentation.getString(fragmentActivityRequireActivity));
            fragmentActivityRequireActivity.setTitle(singleStringResIdRepresentation.getString(fragmentActivityRequireActivity));
        }
    }

    private final void setColorsForCaptureScreen() {
        ContextUtilsKt.requireToolbarHost(this).setToolbarColor(R.attr.onfidoColorToolbarBackgroundDark, R.attr.onfidoColorContentToolbarTitleDark, R.attr.onfidoColorContentToolbarSubtitleDark);
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        changeStatusBarColor(ContextUtilsKt.colorFromAttr(contextRequireContext, android.R.attr.colorPrimaryDark));
        OverlayView.setColorOutsideOverlay$default(getOverlayView(), getBackgroundColorCaptureScreen(), false, 2, null);
    }

    private final void setOverlay() throws Resources.NotFoundException {
        setupOverlayView();
        setColorsForCaptureScreen();
    }

    private final void setOverlayMargin(RectF captureRect) {
        getBinding().livenessOverlayView.updateTextPosition$onfido_capture_sdk_core_release(captureRect);
    }

    private final void setVideoRecordingIndicatorMargin(RectF captureRect) throws Resources.NotFoundException {
        LinearLayout root = getBinding().videoRecordingContainer.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewGroup.LayoutParams layoutParams = root.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        float f = 2;
        layoutParams2.setMargins(0, (int) ((captureRect.top + (captureRect.height() / f)) - (getResources().getDimension(R.dimen.onfido_document_capture_video_indicator_height) / f)), 0, 0);
        root.setLayoutParams(layoutParams2);
    }

    private final void setupObservers() {
        MutableLiveData<Boolean> livenessControlButtonLiveData$onfido_capture_sdk_core_release = getViewModel().getLivenessControlButtonLiveData$onfido_capture_sdk_core_release();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final Function1<Boolean, Unit> function1 = new Function1<Boolean, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.setupObservers.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean bool) {
                Intrinsics.checkNotNull(bool);
                if (bool.booleanValue()) {
                    LivenessCaptureFragment.this.showLivenessControlButtonWithAccessibilityEvent();
                } else {
                    LivenessCaptureFragment.this.hideLivenessControlButton();
                }
            }
        };
        livenessControlButtonLiveData$onfido_capture_sdk_core_release.observe(viewLifecycleOwner, new Observer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LivenessCaptureFragment.setupObservers$lambda$0(function1, obj);
            }
        });
        MutableLiveData<Boolean> livenessControlButtonWithDelayLiveData$onfido_capture_sdk_core_release = getViewModel().getLivenessControlButtonWithDelayLiveData$onfido_capture_sdk_core_release();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        final Function1<Boolean, Unit> function12 = new Function1<Boolean, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.setupObservers.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean bool) {
                LivenessCaptureFragment.this.showLivenessButtonAndFocusWithDelay();
            }
        };
        livenessControlButtonWithDelayLiveData$onfido_capture_sdk_core_release.observe(viewLifecycleOwner2, new Observer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LivenessCaptureFragment.setupObservers$lambda$1(function12, obj);
            }
        });
        MutableLiveData<Boolean> faceOutTimeoutLiveData$onfido_capture_sdk_core_release = getViewModel().getFaceOutTimeoutLiveData$onfido_capture_sdk_core_release();
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        final Function1<Boolean, Unit> function13 = new Function1<Boolean, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.setupObservers.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean bool) {
                LivenessCaptureFragment.this.onFaceOutTimeout();
            }
        };
        faceOutTimeoutLiveData$onfido_capture_sdk_core_release.observe(viewLifecycleOwner3, new Observer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LivenessCaptureFragment.setupObservers$lambda$2(function13, obj);
            }
        });
        MutableLiveData<Boolean> storageThresholdReachedLiveData$onfido_capture_sdk_core_release = getViewModel().getStorageThresholdReachedLiveData$onfido_capture_sdk_core_release();
        LifecycleOwner viewLifecycleOwner4 = getViewLifecycleOwner();
        final Function1<Boolean, Unit> function14 = new Function1<Boolean, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.setupObservers.4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean bool) {
                LivenessCaptureFragment.this.onStorageThresholdReached();
            }
        };
        storageThresholdReachedLiveData$onfido_capture_sdk_core_release.observe(viewLifecycleOwner4, new Observer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LivenessCaptureFragment.setupObservers$lambda$3(function14, obj);
            }
        });
        MutableLiveData<Boolean> challengesCompletedLiveData$onfido_capture_sdk_core_release = getViewModel().getChallengesCompletedLiveData$onfido_capture_sdk_core_release();
        LifecycleOwner viewLifecycleOwner5 = getViewLifecycleOwner();
        final Function1<Boolean, Unit> function15 = new Function1<Boolean, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.setupObservers.5
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke2(bool);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Boolean bool) {
                LivenessCaptureFragment.this.onChallengesCompleted();
            }
        };
        challengesCompletedLiveData$onfido_capture_sdk_core_release.observe(viewLifecycleOwner5, new Observer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LivenessCaptureFragment.setupObservers$lambda$4(function15, obj);
            }
        });
        MutableLiveData<LivenessChallengeViewModel> challengeProviderLiveData$onfido_capture_sdk_core_release = getViewModel().getChallengeProviderLiveData$onfido_capture_sdk_core_release();
        LifecycleOwner viewLifecycleOwner6 = getViewLifecycleOwner();
        final Function1<LivenessChallengeViewModel, Unit> function16 = new Function1<LivenessChallengeViewModel, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.setupObservers.6
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LivenessChallengeViewModel livenessChallengeViewModel) {
                invoke2(livenessChallengeViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LivenessChallengeViewModel livenessChallengeViewModel) {
                LivenessCaptureFragment livenessCaptureFragment = LivenessCaptureFragment.this;
                Intrinsics.checkNotNull(livenessChallengeViewModel);
                livenessCaptureFragment.onNextChallenge(livenessChallengeViewModel);
                LivenessCaptureFragment.this.removeViewsAccessibility();
            }
        };
        challengeProviderLiveData$onfido_capture_sdk_core_release.observe(viewLifecycleOwner6, new Observer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LivenessCaptureFragment.setupObservers$lambda$5(function16, obj);
            }
        });
        MutableLiveData<FaceDetectionResult> faceDetectionResultLiveData$onfido_capture_sdk_core_release = getViewModel().getFaceDetectionResultLiveData$onfido_capture_sdk_core_release();
        LifecycleOwner viewLifecycleOwner7 = getViewLifecycleOwner();
        final Function1<FaceDetectionResult, Unit> function17 = new Function1<FaceDetectionResult, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.setupObservers.7
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FaceDetectionResult faceDetectionResult) {
                invoke2(faceDetectionResult);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(FaceDetectionResult faceDetectionResult) {
                LivenessCaptureFragment livenessCaptureFragment = LivenessCaptureFragment.this;
                Intrinsics.checkNotNull(faceDetectionResult);
                livenessCaptureFragment.onFaceDetected(faceDetectionResult);
            }
        };
        faceDetectionResultLiveData$onfido_capture_sdk_core_release.observe(viewLifecycleOwner7, new Observer() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LivenessCaptureFragment.setupObservers$lambda$6(function17, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupObservers$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupObservers$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupObservers$lambda$2(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupObservers$lambda$3(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupObservers$lambda$4(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupObservers$lambda$5(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupObservers$lambda$6(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    private final void setupOverlayView() throws Resources.NotFoundException {
        if (this._overlayView != null) {
            getBinding().contentLayout.removeView(getOverlayView());
        }
        OverlayView overlayViewInflateOverlayView = inflateOverlayView();
        overlayViewInflateOverlayView.setUp(CaptureType.VIDEO, this);
        OverlayView.setColorOutsideOverlay$default(overlayViewInflateOverlayView, getBackgroundColorCaptureScreen(), false, 2, null);
        ViewExtensionsKt.runOnMeasured(overlayViewInflateOverlayView, new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$setupOverlayView$1$1
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
                this.this$0.startCamera();
            }
        });
        this._overlayView = overlayViewInflateOverlayView;
        getBinding().overlayTextContainer.setLivenessOverlayText$onfido_capture_sdk_core_release();
        getOverlayView().setIsProofOfAddress(false);
        getBinding().contentLayout.addView(getOverlayView());
    }

    private final void setupUiElements(View view) {
        this._binding = OnfidoFragmentLivenessCaptureBinding.bind(view);
        inflateDummyAccessibilityView();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        this._layoutAdjuster = new LivenessCaptureLayoutAdjuster(contextRequireContext, getBinding().overlayTextContainer, getDummyAccessibilityBinding().dummyAccessibility);
        getLifecycleRegistry().addObserver(getLayoutAdjuster());
    }

    private final void showDialog(int titleResId, int messageResId, int positiveButtonResId, Function1<? super DialogInterface, Unit> callback) {
        if (FragmentExtentionsKt.isAttached(this)) {
            this.lifecycleAwareDialog.show((56 & 1) != 0 ? null : Integer.valueOf(titleResId), messageResId, (56 & 4) != 0 ? R.string.onfido_ok : positiveButtonResId, (56 & 8) != 0 ? null : null, (56 & 16) != 0 ? false : false, (Function1<? super DialogInterface, Unit>) ((56 & 32) != 0 ? null : null), (Function1<? super DialogInterface, Unit>) ((56 & 64) != 0 ? LifecycleAwareDialog.C07234.INSTANCE : callback));
        }
    }

    static /* synthetic */ void showDialog$default(LivenessCaptureFragment livenessCaptureFragment, int i, int i2, int i3, Function1 function1, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i3 = R.string.onfido_ok;
        }
        livenessCaptureFragment.showDialog(i, i2, i3, function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showFaceLivenessConfirmationScreen(String videoPath, LivenessPerformedChallenges performedChallenges) {
        finishWithResult(new LivenessCaptureScreen.LivenessCaptureResult.Completed(videoPath, performedChallenges));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLivenessButtonAndFocusWithDelay() {
        long livenessButtonDisplayDelay$onfido_capture_sdk_core_release = getViewModel().getLivenessButtonDisplayDelay$onfido_capture_sdk_core_release();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new C06691(livenessButtonDisplayDelay$onfido_capture_sdk_core_release, this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLivenessControlButtonWithAccessibilityEvent() {
        OnfidoButton onfidoButton = this.captureButton;
        if (onfidoButton != null) {
            ViewExtensionsKt.toVisible$default(onfidoButton, false, 1, null);
            AccessibilityExtensionsKt.sendAccessibilityFocusEvent(onfidoButton);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startCamera() {
        OnfidoCamera onfidoCameraCreate = getCameraFactory$onfido_capture_sdk_core_release().create(this, getBinding().cameraViewCamera1, getBinding().cameraViewCameraX, getOverlayView(), CaptureType.VIDEO);
        this.onfidoCamera = onfidoCameraCreate;
        if (onfidoCameraCreate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
            onfidoCameraCreate = null;
        }
        onfidoCameraCreate.start(OnfidoCamera.CameraFacing.FRONT, new Function1<OnfidoCamera.CameraStatus, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.startCamera.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(OnfidoCamera.CameraStatus cameraStatus) {
                invoke2(cameraStatus);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(OnfidoCamera.CameraStatus cameraStatus) {
                Intrinsics.checkNotNullParameter(cameraStatus, "cameraStatus");
                if (Intrinsics.areEqual(cameraStatus, OnfidoCamera.CameraStatus.Started.INSTANCE)) {
                    LivenessCaptureFragment.this.onCameraStarted();
                    return;
                }
                if (cameraStatus instanceof OnfidoCamera.CameraStatus.Failed) {
                    LivenessCaptureFragment livenessCaptureFragment = LivenessCaptureFragment.this;
                    String message = ((OnfidoCamera.CameraStatus.Failed) cameraStatus).getError().getMessage();
                    if (message == null) {
                        message = "";
                    }
                    livenessCaptureFragment.finishWithException(new UnknownCameraException(message));
                    return;
                }
                if (Intrinsics.areEqual(cameraStatus, OnfidoCamera.CameraStatus.NotFound.INSTANCE)) {
                    LivenessCaptureFragment.this.onCameraNotFound();
                } else if (Intrinsics.areEqual(cameraStatus, OnfidoCamera.CameraStatus.NotAvailable.INSTANCE)) {
                    LivenessCaptureFragment.this.onCameraUnavailable();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startLivenessVideoRecording(boolean isStartedManually) {
        getViewModel().onRecordingStarted$onfido_capture_sdk_core_release(isStartedManually);
        getViewModel().issueNextChallenge$onfido_capture_sdk_core_release();
        ViewUtil.setViewVisibilityWithoutAnimation(getBinding().overlayTextContainer, false);
        LivenessOverlayView livenessOverlayView = getBinding().livenessOverlayView;
        Intrinsics.checkNotNullExpressionValue(livenessOverlayView, "livenessOverlayView");
        ViewExtensionsKt.toVisible$default(livenessOverlayView, false, 1, null);
        getBinding().livenessOverlayView.setListener$onfido_capture_sdk_core_release(this);
        getOverlayView().paintCaptureArea();
        startVideoRecording$default(this, null, 1, null);
    }

    private final void startTrackingLivenessCapture() {
        getViewModel().trackCapture$onfido_capture_sdk_core_release((getResources().getConfiguration().orientation == 2 ? Orientation.LANDSCAPE : Orientation.PORTRAIT).isPortrait$onfido_capture_sdk_core_release());
    }

    private final void startVideoRecording(final Function0<Unit> videoRecordingStarted) {
        FileUtils fileUtils = FileUtils.INSTANCE;
        File cacheDir = requireContext().getCacheDir();
        Intrinsics.checkNotNullExpressionValue(cacheDir, "getCacheDir(...)");
        fileUtils.deleteFilesWithPrefixFromFolder(cacheDir, VideoCaptureConfig.DEFAULT_FILE_NAME_PREFIX);
        OnfidoCamera onfidoCamera = this.onfidoCamera;
        if (onfidoCamera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
            onfidoCamera = null;
        }
        this.recorder = onfidoCamera.takeVideo(new Function1<OnfidoCamera.VideoCaptureEvent, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.startVideoRecording.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                if (Intrinsics.areEqual(event, OnfidoCamera.VideoCaptureEvent.Canceled.INSTANCE)) {
                    return;
                }
                if (event instanceof OnfidoCamera.VideoCaptureEvent.Error) {
                    LivenessCaptureFragment.this.onErrorTakingPicture(((OnfidoCamera.VideoCaptureEvent.Error) event).getError());
                    return;
                }
                if (event instanceof OnfidoCamera.VideoCaptureEvent.Recorded) {
                    LivenessCaptureFragment.this.onVideoCaptured(((OnfidoCamera.VideoCaptureEvent.Recorded) event).getFilePath());
                } else if (Intrinsics.areEqual(event, OnfidoCamera.VideoCaptureEvent.Started.INSTANCE)) {
                    videoRecordingStarted.invoke();
                } else if (Intrinsics.areEqual(event, OnfidoCamera.VideoCaptureEvent.Timeout.INSTANCE)) {
                    LivenessCaptureFragment.this.onVideoTimeoutExceeded();
                }
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void startVideoRecording$default(LivenessCaptureFragment livenessCaptureFragment, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.startVideoRecording.1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
        }
        livenessCaptureFragment.startVideoRecording(function0);
    }

    public final AnnouncementService getAnnouncementService$onfido_capture_sdk_core_release() {
        AnnouncementService announcementService = this.announcementService;
        if (announcementService != null) {
            return announcementService;
        }
        Intrinsics.throwUninitializedPropertyAccessException("announcementService");
        return null;
    }

    public final CameraFactory getCameraFactory$onfido_capture_sdk_core_release() {
        CameraFactory cameraFactory = this.cameraFactory;
        if (cameraFactory != null) {
            return cameraFactory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("cameraFactory");
        return null;
    }

    public final ImageUtils getImageUtils$onfido_capture_sdk_core_release() {
        ImageUtils imageUtils = this.imageUtils;
        if (imageUtils != null) {
            return imageUtils;
        }
        Intrinsics.throwUninitializedPropertyAccessException("imageUtils");
        return null;
    }

    public final VibratorService getVibratorService$onfido_capture_sdk_core_release() {
        VibratorService vibratorService = this.vibratorService;
        if (vibratorService != null) {
            return vibratorService;
        }
        Intrinsics.throwUninitializedPropertyAccessException("vibratorService");
        return null;
    }

    public final Provider<LivenessCaptureViewModel> getViewModelProvider$onfido_capture_sdk_core_release() {
        Provider<LivenessCaptureViewModel> provider = this.viewModelProvider;
        if (provider != null) {
            return provider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelProvider");
        return null;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingView.Listener
    public void onChallengeLoadingViewStateChanged(LivenessChallengesLoadingView.ScreenState screenState) {
        Intrinsics.checkNotNullParameter(screenState, "screenState");
        if (Intrinsics.areEqual(screenState, LivenessChallengesLoadingView.ScreenState.Loading.INSTANCE)) {
            ViewExtensionsKt.toGone$default(getOverlayView(), false, 1, null);
        } else if (screenState instanceof LivenessChallengesLoadingView.ScreenState.Success) {
            onChallengesAvailable(((LivenessChallengesLoadingView.ScreenState.Success) screenState).getLivenessChallengesViewModel());
        } else {
            Intrinsics.areEqual(screenState, LivenessChallengesLoadingView.ScreenState.Error.INSTANCE);
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingView.Listener
    public void onChallengesErrorBackPressed() {
        requireActivity().onBackPressed();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._layoutAdjuster = null;
        this._overlayView = null;
        this.captureButton = null;
        this._dummyAccessibilityBinding = null;
        this.livenessChallengesLoadingView = null;
        this._binding = null;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayView.ChallengesListener
    public void onErrorObservingHeadTurnResults() {
        getViewModel().stopFaceTracking$onfido_capture_sdk_core_release();
        showLivenessControlButtonWithAccessibilityEvent();
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingView.Listener
    public void onInvalidCertificateDetected(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        finishWithResult(new LivenessCaptureScreen.LivenessCaptureResult.Error(new InvalidCertificateException(message)));
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayView.ChallengesListener
    public void onLivenessChallengeFinished() {
        getOverlayView().showFaceConfirmationTick(new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.onLivenessChallengeFinished.1

            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
            @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$onLivenessChallengeFinished$1$1", f = "LivenessCaptureFragment.kt", i = {}, l = {645}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment$onLivenessChallengeFinished$1$1, reason: invalid class name and collision with other inner class name */
            static final class C01541 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;
                final /* synthetic */ LivenessCaptureFragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C01541(LivenessCaptureFragment livenessCaptureFragment, Continuation<? super C01541> continuation) {
                    super(2, continuation);
                    this.this$0 = livenessCaptureFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C01541(this.this$0, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (DelayKt.delay(500L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    this.this$0.getViewModel().issueNextChallenge$onfido_capture_sdk_core_release();
                    return Unit.INSTANCE;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C01541) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }
            }

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
                OverlayView.resetFaceDetectedState$default(LivenessCaptureFragment.this.getOverlayView(), false, false, false, 4, null);
                LifecycleOwner viewLifecycleOwner = LivenessCaptureFragment.this.getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new C01541(LivenessCaptureFragment.this, null), 3, null);
            }
        });
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.OverlayView.Listener
    public void onOverlayMetrics(OverlayMetrics overlayMetrics) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(overlayMetrics, "overlayMetrics");
        this.overlayMetrics = overlayMetrics;
        RectF visibleCaptureRect = overlayMetrics.getVisibleCaptureRect();
        setOverlayMargin(visibleCaptureRect);
        setVideoRecordingIndicatorMargin(visibleCaptureRect);
        LivenessChallengesLoadingView livenessChallengesLoadingView = this.livenessChallengesLoadingView;
        if (livenessChallengesLoadingView != null) {
            livenessChallengesLoadingView.setTopLimit(overlayMetrics.getVisibleCaptureRect().bottom);
        }
        adjustDummyAccessibilityView(overlayMetrics.getVisibleCaptureRect());
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        View view = getView();
        if (view != null) {
            view.setImportantForAccessibility(4);
        }
        OnfidoCamera.VideoRecorder videoRecorder = this.recorder;
        if (videoRecorder != null) {
            videoRecorder.cancel();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() throws Resources.NotFoundException {
        super.onResume();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        CameraPermissionsUtils cameraPermissionsUtils = new CameraPermissionsUtils(contextRequireContext);
        CaptureType captureType = CaptureType.VIDEO;
        if (!(cameraPermissionsUtils.getMissingPermissions$onfido_capture_sdk_core_release(captureType).length == 0)) {
            FragmentKt.setFragmentResult(this, OnfidoActivity.KEY_RESULT_CAPTURE_MISSING_PERMISSIONS, BundleKt.bundleOf(TuplesKt.to(OnfidoActivity.KEY_CAPTURE_MISSING_PERMISSIONS_CAPTURE_TYPE, captureType)));
            return;
        }
        startTrackingLivenessCapture();
        setOverlay();
        View view = getView();
        if (view == null) {
            return;
        }
        view.setImportantForAccessibility(1);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        inflateCaptureButton();
        ContextUtilsKt.requireToolbarHost(this).enableImmersiveFragment();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        ContextUtilsKt.requireToolbarHost(this).disableImmersiveFragment();
        ContextUtilsKt.requireToolbarHost(this).resetToolbar();
        OnfidoCamera onfidoCamera = this.onfidoCamera;
        if (onfidoCamera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
            onfidoCamera = null;
        }
        onfidoCamera.stop();
        LivenessOverlayView livenessOverlayView = getBinding().livenessOverlayView;
        Intrinsics.checkNotNullExpressionValue(livenessOverlayView, "livenessOverlayView");
        ViewExtensionsKt.toGone$default(livenessOverlayView, false, 1, null);
        getBinding().content.removeView(this.captureButton);
        inflateCaptureButton();
        getViewModel().reset$onfido_capture_sdk_core_release();
        if (this.isCameraViewInitialised) {
            OverlayView.resetFaceDetectedState$default(getOverlayView(), false, false, false, 2, null);
        }
        OnfidoCamera.VideoRecorder videoRecorder = this.recorder;
        if (videoRecorder == null || videoRecorder.isRecording()) {
            return;
        }
        ViewUtil.setViewVisibility(this.captureButton, true);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingView.Listener
    public void onTokenExpired() {
        finishWithResult(new LivenessCaptureScreen.LivenessCaptureResult.Error(TokenExpiredException.INSTANCE));
    }

    public final void onVideoTimeoutExceeded() {
        getViewModel().reset$onfido_capture_sdk_core_release();
        getBinding().livenessOverlayView.stopFaceTracking$onfido_capture_sdk_core_release();
        getViewModel().trackVideoCaptureTimeout$onfido_capture_sdk_core_release();
        showDialog(R.string.onfido_video_capture_prompt_title_timeout, R.string.onfido_video_capture_prompt_detail_timeout, R.string.onfido_video_capture_prompt_button_timeout, new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.onVideoTimeoutExceeded.1
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
                LivenessCaptureFragment.this.onVideoTimeoutRetryClick(dialog);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        SdkController companion = SdkController.INSTANCE.getInstance();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        setupUiElements(view);
        setupObservers();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment.onViewCreated.1
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                LivenessCaptureFragment.this.finishWithResult(LivenessCaptureScreen.LivenessCaptureResult.Back.INSTANCE);
            }
        });
    }

    public final void setAnnouncementService$onfido_capture_sdk_core_release(AnnouncementService announcementService) {
        Intrinsics.checkNotNullParameter(announcementService, "<set-?>");
        this.announcementService = announcementService;
    }

    public final void setCameraFactory$onfido_capture_sdk_core_release(CameraFactory cameraFactory) {
        Intrinsics.checkNotNullParameter(cameraFactory, "<set-?>");
        this.cameraFactory = cameraFactory;
    }

    public final void setImageUtils$onfido_capture_sdk_core_release(ImageUtils imageUtils) {
        Intrinsics.checkNotNullParameter(imageUtils, "<set-?>");
        this.imageUtils = imageUtils;
    }

    public final void setVibratorService$onfido_capture_sdk_core_release(VibratorService vibratorService) {
        Intrinsics.checkNotNullParameter(vibratorService, "<set-?>");
        this.vibratorService = vibratorService;
    }

    public final void setViewModelProvider$onfido_capture_sdk_core_release(Provider<LivenessCaptureViewModel> provider) {
        Intrinsics.checkNotNullParameter(provider, "<set-?>");
        this.viewModelProvider = provider;
    }
}
