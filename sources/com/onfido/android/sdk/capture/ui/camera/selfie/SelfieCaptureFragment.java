package com.onfido.android.sdk.capture.ui.camera.selfie;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.camera.view.PreviewView;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentKt;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.media3.common.MimeTypes;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.databinding.OnfidoCaptureButtonPictureBinding;
import com.onfido.android.sdk.capture.databinding.OnfidoDummyAccessibilityViewBinding;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentSelfieCaptureBinding;
import com.onfido.android.sdk.capture.databinding.OnfidoViewOverlayFaceBinding;
import com.onfido.android.sdk.capture.errors.ErrorDescriptor;
import com.onfido.android.sdk.capture.internal.camera.OnfidoCamera;
import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.internal.camera.factory.CameraFactory;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.performance.AggregatedPerformanceAnalytics;
import com.onfido.android.sdk.capture.internal.performance.domain.PerformanceEventName;
import com.onfido.android.sdk.capture.internal.performance.trackers.PerformanceEvents;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.ui.BaseActivity;
import com.onfido.android.sdk.capture.ui.BaseFragment;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.OnfidoActivity;
import com.onfido.android.sdk.capture.ui.camera.CaptureConfirmationScreen;
import com.onfido.android.sdk.capture.ui.camera.ConfirmationStepButtons;
import com.onfido.android.sdk.capture.ui.camera.OverlayMetrics;
import com.onfido.android.sdk.capture.ui.camera.OverlayView;
import com.onfido.android.sdk.capture.ui.camera.capture.PhotoCaptureConfig;
import com.onfido.android.sdk.capture.ui.camera.exception.CameraNotAvailableException;
import com.onfido.android.sdk.capture.ui.camera.exception.CameraNotFoundException;
import com.onfido.android.sdk.capture.ui.camera.exception.UnknownCameraException;
import com.onfido.android.sdk.capture.ui.camera.face.FaceConfirmationFragment;
import com.onfido.android.sdk.capture.ui.camera.face.FaceConfirmationFragmentContainer;
import com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureScreen;
import com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureViewModel;
import com.onfido.android.sdk.capture.ui.camera.util.CaptureLayoutAdjuster;
import com.onfido.android.sdk.capture.ui.camera.util.ValidationBubblesOffsetDelegate;
import com.onfido.android.sdk.capture.ui.camera.view.CameraSourcePreview;
import com.onfido.android.sdk.capture.ui.model.DocumentTypeUIModel;
import com.onfido.android.sdk.capture.ui.model.DocumentUITextModelMapper;
import com.onfido.android.sdk.capture.ui.options.Orientation;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.android.sdk.capture.utils.LifecycleAwareDialog;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
import com.onfido.android.sdk.capture.utils.StringRepresentation;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.android.sdk.capture.utils.ViewUtil;
import com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble;
import com.onfido.api.client.data.DocSide;
import com.onfido.javax.inject.Inject;
import io.sentry.SentryEvent;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

@Metadata(d1 = {"\u0000\u0088\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0003\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 º\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002º\u0001B\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020aH\u0002J\u0010\u0010b\u001a\u00020_2\u0006\u0010c\u001a\u00020OH\u0002J\b\u0010d\u001a\u00020_H\u0002J\u0012\u0010e\u001a\u00020_2\b\b\u0001\u0010f\u001a\u00020\u0015H\u0002J\b\u0010g\u001a\u00020_H\u0002J\u0014\u0010h\u001a\u00020_2\n\u0010i\u001a\u00060jj\u0002`kH\u0002J\u0010\u0010l\u001a\u00020_2\u0006\u0010m\u001a\u00020nH\u0002J\n\u0010o\u001a\u0004\u0018\u00010pH\u0002J\b\u0010q\u001a\u00020rH\u0002J\n\u0010s\u001a\u0004\u0018\u00010OH\u0016J\b\u0010t\u001a\u00020_H\u0002J\b\u0010u\u001a\u00020_H\u0002J\b\u0010v\u001a\u00020_H\u0002J\b\u0010w\u001a\u00020_H\u0002J\b\u0010x\u001a\u00020_H\u0002J\b\u0010y\u001a\u00020_H\u0002J\b\u0010z\u001a\u00020_H\u0002J\b\u0010{\u001a\u00020_H\u0002J\b\u0010|\u001a\u00020_H\u0002J\b\u0010}\u001a\u00020_H\u0002J\b\u0010~\u001a\u00020_H\u0002J\b\u0010\u007f\u001a\u00020_H\u0002J\t\u0010\u0080\u0001\u001a\u00020_H\u0002J\t\u0010\u0081\u0001\u001a\u00020_H\u0002J\t\u0010\u0082\u0001\u001a\u00020_H\u0002J\t\u0010\u0083\u0001\u001a\u00020_H\u0016J\t\u0010\u0084\u0001\u001a\u00020_H\u0016J\t\u0010\u0085\u0001\u001a\u00020_H\u0016J\u0013\u0010\u0086\u0001\u001a\u00020_2\b\u0010\u0087\u0001\u001a\u00030\u0088\u0001H\u0002J\u0011\u0010\u0089\u0001\u001a\u00020_2\u0006\u0010=\u001a\u00020>H\u0016J\u000f\u0010\u008a\u0001\u001a\u00020_2\u0006\u0010c\u001a\u00020OJ\t\u0010\u008b\u0001\u001a\u00020_H\u0016J\t\u0010\u008c\u0001\u001a\u00020_H\u0016J\t\u0010\u008d\u0001\u001a\u00020_H\u0016J\t\u0010\u008e\u0001\u001a\u00020_H\u0016J\t\u0010\u008f\u0001\u001a\u00020_H\u0016J\u001f\u0010\u0090\u0001\u001a\u00020_2\b\u0010\u0091\u0001\u001a\u00030\u0092\u00012\n\u0010\u0093\u0001\u001a\u0005\u0018\u00010\u0094\u0001H\u0017J\t\u0010\u0095\u0001\u001a\u00020_H\u0002J\t\u0010\u0096\u0001\u001a\u00020_H\u0002J\u0012\u0010\u0097\u0001\u001a\u00020_2\u0007\u0010\u0098\u0001\u001a\u00020aH\u0002J\t\u0010\u0099\u0001\u001a\u00020_H\u0002J\t\u0010\u009a\u0001\u001a\u00020_H\u0002J\t\u0010\u009b\u0001\u001a\u00020_H\u0002J\u0012\u0010\u009c\u0001\u001a\u00020_2\u0007\u0010\u009d\u0001\u001a\u00020OH\u0002J'\u0010\u009e\u0001\u001a\u00020_2\t\b\u0001\u0010\u009f\u0001\u001a\u00020\u00152\u000b\b\u0003\u0010 \u0001\u001a\u0004\u0018\u00010\u0015H\u0002¢\u0006\u0003\u0010¡\u0001J\t\u0010¢\u0001\u001a\u00020_H\u0002J\t\u0010£\u0001\u001a\u00020_H\u0002J\t\u0010¤\u0001\u001a\u00020_H\u0002J\t\u0010¥\u0001\u001a\u00020_H\u0002J\u0013\u0010¦\u0001\u001a\u00020_2\b\u0010§\u0001\u001a\u00030¨\u0001H\u0002J\t\u0010©\u0001\u001a\u00020_H\u0002JH\u0010ª\u0001\u001a\u00020_2\t\b\u0001\u0010«\u0001\u001a\u00020\u00152\t\b\u0001\u0010¬\u0001\u001a\u00020\u00152'\u0010\u00ad\u0001\u001a\"\u0012\u0017\u0012\u00150¯\u0001¢\u0006\u000f\b°\u0001\u0012\n\b±\u0001\u0012\u0005\b\b(²\u0001\u0012\u0004\u0012\u00020_0®\u0001H\u0002J\t\u0010³\u0001\u001a\u00020_H\u0002J\u0013\u0010´\u0001\u001a\u00020_2\b\u0010µ\u0001\u001a\u00030¶\u0001H\u0002J\t\u0010·\u0001\u001a\u00020_H\u0002J\t\u0010¸\u0001\u001a\u00020_H\u0002J\t\u0010¹\u0001\u001a\u00020_H\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u00020\u000f8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017R\u0014\u0010\u001a\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001d\u001a\u00020\u001e8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u001e\u0010)\u001a\u00020*8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u000e\u0010/\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082.¢\u0006\u0002\n\u0000R\u001e\u00107\u001a\u0002088\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u000e\u0010=\u001a\u00020>X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010?\u001a\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b@\u0010AR\u001e\u0010B\u001a\u00020C8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\u001e\u0010H\u001a\u00020I8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR\u0010\u0010N\u001a\u0004\u0018\u00010OX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020QX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010R\u001a\u00020S8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bV\u0010W\u001a\u0004\bT\u0010UR\u001e\u0010X\u001a\u00020Y8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]¨\u0006»\u0001"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureFragment;", "Lcom/onfido/android/sdk/capture/ui/BaseFragment;", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayView$Listener;", "Lcom/onfido/android/sdk/capture/ui/camera/face/FaceConfirmationFragmentContainer;", "Lcom/onfido/android/sdk/capture/ui/camera/ConfirmationStepButtons$Listener;", "()V", "_binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentSelfieCaptureBinding;", "_captureButtonBinding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoCaptureButtonPictureBinding;", "_dummyAccessibilityBinding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoDummyAccessibilityViewBinding;", "_overlayView", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayView;", "announcementService", "Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "getAnnouncementService$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "setAnnouncementService$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;)V", "backgroundColorCaptureScreen", "", "getBackgroundColorCaptureScreen", "()I", "backgroundColorConfirmationScreen", "getBackgroundColorConfirmationScreen", "binding", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentSelfieCaptureBinding;", "cameraFactory", "Lcom/onfido/android/sdk/capture/internal/camera/factory/CameraFactory;", "getCameraFactory$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/camera/factory/CameraFactory;", "setCameraFactory$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/camera/factory/CameraFactory;)V", "captureButtonBinding", "getCaptureButtonBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoCaptureButtonPictureBinding;", "dummyAccessibilityBinding", "getDummyAccessibilityBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoDummyAccessibilityViewBinding;", "imageUtils", "Lcom/onfido/android/sdk/capture/utils/ImageUtils;", "getImageUtils$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/utils/ImageUtils;", "setImageUtils$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/utils/ImageUtils;)V", "isCameraViewInitialised", "", "layoutAdjuster", "Lcom/onfido/android/sdk/capture/ui/camera/util/CaptureLayoutAdjuster;", "lifecycleAwareDialog", "Lcom/onfido/android/sdk/capture/utils/LifecycleAwareDialog;", "onfidoCamera", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "getOnfidoRemoteConfig$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "setOnfidoRemoteConfig$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "overlayMetrics", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayMetrics;", "overlayView", "getOverlayView", "()Lcom/onfido/android/sdk/capture/ui/camera/OverlayView;", "performanceAnalytics", "Lcom/onfido/android/sdk/capture/internal/performance/AggregatedPerformanceAnalytics;", "getPerformanceAnalytics$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/performance/AggregatedPerformanceAnalytics;", "setPerformanceAnalytics$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/performance/AggregatedPerformanceAnalytics;)V", "permissionsManager", "Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;", "getPermissionsManager$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;", "setPermissionsManager$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;)V", "previewImage", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage;", "validationBubbleOffsetDelegate", "Lcom/onfido/android/sdk/capture/ui/camera/util/ValidationBubblesOffsetDelegate;", "viewModel", "Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureViewModel;", "getViewModel", "()Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "viewModelFactory", "Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureViewModel$Factory;", "getViewModelFactory$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureViewModel$Factory;", "setViewModelFactory$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureViewModel$Factory;)V", "adjustDummyAccessibilityView", "", "visibleCaptureRect", "Landroid/graphics/RectF;", "applyValidations", MimeTypes.BASE_TYPE_IMAGE, "capture", "changeStatusBarColor", "color", "closeConfirmationScreen", "finishWithException", SentryEvent.JsonKeys.EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "finishWithResult", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureScreen$SelfieCaptureResult;", "getConfirmationScreen", "Lcom/onfido/android/sdk/capture/ui/camera/CaptureConfirmationScreen;", "getOrientation", "Lcom/onfido/android/sdk/capture/ui/options/Orientation;", "getPreviewImage", "hideConfirmationStep", "hideLoading", "inflateCaptureButton", "inflateDummyAccessibilityView", "initLayoutAdjuster", "initValidationBubbleDelegate", "observeCaptureResult", "observeErrorDescription", "observeErrors", "observeLoading", "observeShowConfirmation", "observeViewModel", "onCameraNotFound", "onCameraStarted", "onCameraUnavailable", "onCaptureConfirmed", "onCaptureDiscarded", "onDestroyView", "onErrorTakingPicture", "error", "", "onOverlayMetrics", "onPictureCaptured", "onResume", "onRetakeSelfieButtonClick", "onStart", "onStop", "onUploadSelfieButtonClick", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "openCaptureScreen", "setCaptureFrameContentDescriptionAndTitle", "setCaptureRegion", "rect", "setColorsForCaptureScreen", "setConfirmationButtons", "setForceRetryButton", "setImagePreview", "onfidoImage", "setValidationBubbleContent", "title", KeychainModule.AuthPromptOptions.SUBTITLE, "(ILjava/lang/Integer;)V", "setupCaptureButton", "setupConfirmationButtons", "setupOverlayView", "setupToolbar", "showConfirmationError", "descriptor", "Lcom/onfido/android/sdk/capture/errors/ErrorDescriptor;", "showConfirmationStep", "showErrorMessage", "titleResId", "messageResId", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lkotlin/Function1;", "Landroid/content/DialogInterface;", "Lkotlin/ParameterName;", "name", "dialog", "showFaceConfirmationFragment", "showLoading", "dialogMode", "Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode;", "startCamera", "updateColorsForConfirmationScreen", "updateConfirmationImageTranslationAndScale", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SelfieCaptureFragment extends BaseFragment implements OverlayView.Listener, FaceConfirmationFragmentContainer, ConfirmationStepButtons.Listener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FRAGMENT_TAG_CONFIRMATION = "FRAGMENT_TAG_CONFIRMATION";
    private static final String KEY_REQUEST = "KEY_REQUEST";
    private OnfidoFragmentSelfieCaptureBinding _binding;
    private OnfidoCaptureButtonPictureBinding _captureButtonBinding;
    private OnfidoDummyAccessibilityViewBinding _dummyAccessibilityBinding;
    private OverlayView _overlayView;

    @Inject
    public AnnouncementService announcementService;

    @Inject
    public CameraFactory cameraFactory;

    @Inject
    public ImageUtils imageUtils;
    private boolean isCameraViewInitialised;
    private CaptureLayoutAdjuster layoutAdjuster;
    private final LifecycleAwareDialog lifecycleAwareDialog;
    private OnfidoCamera onfidoCamera;

    @Inject
    public OnfidoRemoteConfig onfidoRemoteConfig;
    private OverlayMetrics overlayMetrics;

    @Inject
    public AggregatedPerformanceAnalytics performanceAnalytics;

    @Inject
    public RuntimePermissionsManager permissionsManager;
    private OnfidoImage previewImage;
    private ValidationBubblesOffsetDelegate validationBubbleOffsetDelegate;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    @Inject
    public SelfieCaptureViewModel.Factory viewModelFactory;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureFragment$Companion;", "", "()V", SelfieCaptureFragment.FRAGMENT_TAG_CONFIRMATION, "", SelfieCaptureFragment.KEY_REQUEST, "newInstance", "Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureFragment;", "requestKey", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final SelfieCaptureFragment newInstance(String requestKey) {
            Intrinsics.checkNotNullParameter(requestKey, "requestKey");
            SelfieCaptureFragment selfieCaptureFragment = new SelfieCaptureFragment();
            selfieCaptureFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(SelfieCaptureFragment.KEY_REQUEST, requestKey)));
            return selfieCaptureFragment;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureScreen$SelfieCaptureResult;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$observeCaptureResult$1", f = "SelfieCaptureFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$observeCaptureResult$1, reason: invalid class name and case insensitive filesystem */
    static final class C06781 extends SuspendLambda implements Function2<SelfieCaptureScreen.SelfieCaptureResult, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        C06781(Continuation<? super C06781> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06781 c06781 = SelfieCaptureFragment.this.new C06781(continuation);
            c06781.L$0 = obj;
            return c06781;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SelfieCaptureScreen.SelfieCaptureResult selfieCaptureResult, Continuation<? super Unit> continuation) {
            return ((C06781) create(selfieCaptureResult, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            SelfieCaptureFragment.this.finishWithResult((SelfieCaptureScreen.SelfieCaptureResult) this.L$0);
            return Unit.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lcom/onfido/android/sdk/capture/errors/ErrorDescriptor;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$observeErrorDescription$1", f = "SelfieCaptureFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$observeErrorDescription$1, reason: invalid class name and case insensitive filesystem */
    static final class C06791 extends SuspendLambda implements Function2<ErrorDescriptor, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        C06791(Continuation<? super C06791> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06791 c06791 = SelfieCaptureFragment.this.new C06791(continuation);
            c06791.L$0 = obj;
            return c06791;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ErrorDescriptor errorDescriptor, Continuation<? super Unit> continuation) {
            return ((C06791) create(errorDescriptor, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            SelfieCaptureFragment.this.showConfirmationError((ErrorDescriptor) this.L$0);
            return Unit.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureViewModel$ErrorMessageEvent;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$observeErrors$1", f = "SelfieCaptureFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$observeErrors$1, reason: invalid class name and case insensitive filesystem */
    static final class C06801 extends SuspendLambda implements Function2<SelfieCaptureViewModel.ErrorMessageEvent, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        C06801(Continuation<? super C06801> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06801 c06801 = SelfieCaptureFragment.this.new C06801(continuation);
            c06801.L$0 = obj;
            return c06801;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SelfieCaptureViewModel.ErrorMessageEvent errorMessageEvent, Continuation<? super Unit> continuation) {
            return ((C06801) create(errorMessageEvent, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            SelfieCaptureViewModel.ErrorMessageEvent errorMessageEvent = (SelfieCaptureViewModel.ErrorMessageEvent) this.L$0;
            SelfieCaptureFragment selfieCaptureFragment = SelfieCaptureFragment.this;
            int title = errorMessageEvent.getTitle();
            int message = errorMessageEvent.getMessage();
            final SelfieCaptureFragment selfieCaptureFragment2 = SelfieCaptureFragment.this;
            selfieCaptureFragment.showErrorMessage(title, message, new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment.observeErrors.1.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) throws Resources.NotFoundException {
                    invoke2(dialogInterface);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DialogInterface it) throws Resources.NotFoundException {
                    Intrinsics.checkNotNullParameter(it, "it");
                    if (selfieCaptureFragment2.getConfirmationScreen() == null) {
                        selfieCaptureFragment2.openCaptureScreen();
                    }
                }
            });
            return Unit.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureViewModel$LoadingEvent;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$observeLoading$1", f = "SelfieCaptureFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$observeLoading$1, reason: invalid class name and case insensitive filesystem */
    static final class C06811 extends SuspendLambda implements Function2<SelfieCaptureViewModel.LoadingEvent, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        C06811(Continuation<? super C06811> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06811 c06811 = SelfieCaptureFragment.this.new C06811(continuation);
            c06811.L$0 = obj;
            return c06811;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SelfieCaptureViewModel.LoadingEvent loadingEvent, Continuation<? super Unit> continuation) {
            return ((C06811) create(loadingEvent, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            SelfieCaptureViewModel.LoadingEvent loadingEvent = (SelfieCaptureViewModel.LoadingEvent) this.L$0;
            if (Intrinsics.areEqual(loadingEvent, SelfieCaptureViewModel.LoadingEvent.Hide.INSTANCE)) {
                SelfieCaptureFragment.this.hideLoading();
            } else if (loadingEvent instanceof SelfieCaptureViewModel.LoadingEvent.Show) {
                SelfieCaptureFragment.this.showLoading(((SelfieCaptureViewModel.LoadingEvent.Show) loadingEvent).getMode());
            }
            return Unit.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureViewModel$ShowConfirmationEvent;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$observeShowConfirmation$1", f = "SelfieCaptureFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$observeShowConfirmation$1, reason: invalid class name and case insensitive filesystem */
    static final class C06821 extends SuspendLambda implements Function2<SelfieCaptureViewModel.ShowConfirmationEvent, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        C06821(Continuation<? super C06821> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06821 c06821 = SelfieCaptureFragment.this.new C06821(continuation);
            c06821.L$0 = obj;
            return c06821;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SelfieCaptureViewModel.ShowConfirmationEvent showConfirmationEvent, Continuation<? super Unit> continuation) {
            return ((C06821) create(showConfirmationEvent, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Resources.NotFoundException {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            SelfieCaptureViewModel.ShowConfirmationEvent showConfirmationEvent = (SelfieCaptureViewModel.ShowConfirmationEvent) this.L$0;
            SelfieCaptureFragment.this.showConfirmationStep();
            if (showConfirmationEvent.getShowForceRetry()) {
                SelfieCaptureFragment.this.setForceRetryButton();
            } else {
                SelfieCaptureFragment.this.setConfirmationButtons();
            }
            return Unit.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$onViewCreated$2", f = "SelfieCaptureFragment.kt", i = {}, l = {Mp4VideoDirectory.TAG_DEPTH}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$onViewCreated$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$onViewCreated$2$1", f = "SelfieCaptureFragment.kt", i = {}, l = {Mp4VideoDirectory.TAG_COMPRESSION_TYPE}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$onViewCreated$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ SelfieCaptureFragment this$0;

            @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "isVisible", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
            @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$onViewCreated$2$1$1", f = "SelfieCaptureFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$onViewCreated$2$1$1, reason: invalid class name and collision with other inner class name */
            static final class C01601 extends SuspendLambda implements Function2<Boolean, Continuation<? super Unit>, Object> {
                /* synthetic */ boolean Z$0;
                int label;
                final /* synthetic */ SelfieCaptureFragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C01601(SelfieCaptureFragment selfieCaptureFragment, Continuation<? super C01601> continuation) {
                    super(2, continuation);
                    this.this$0 = selfieCaptureFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C01601 c01601 = new C01601(this.this$0, continuation);
                    c01601.Z$0 = ((Boolean) obj).booleanValue();
                    return c01601;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Boolean bool, Continuation<? super Unit> continuation) {
                    return invoke(bool.booleanValue(), continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    if (this.Z$0) {
                        ImageView captureButton = this.this$0.getCaptureButtonBinding().captureButton;
                        Intrinsics.checkNotNullExpressionValue(captureButton, "captureButton");
                        ViewExtensionsKt.toVisible$default(captureButton, false, 1, null);
                    } else {
                        ImageView captureButton2 = this.this$0.getCaptureButtonBinding().captureButton;
                        Intrinsics.checkNotNullExpressionValue(captureButton2, "captureButton");
                        ViewExtensionsKt.toGone$default(captureButton2, false, 1, null);
                    }
                    return Unit.INSTANCE;
                }

                public final Object invoke(boolean z, Continuation<? super Unit> continuation) {
                    return ((C01601) create(Boolean.valueOf(z), continuation)).invokeSuspend(Unit.INSTANCE);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(SelfieCaptureFragment selfieCaptureFragment, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = selfieCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow<Boolean> captureButtonVisibility$onfido_capture_sdk_core_release = this.this$0.getViewModel().getCaptureButtonVisibility$onfido_capture_sdk_core_release();
                    C01601 c01601 = new C01601(this.this$0, null);
                    this.label = 1;
                    if (FlowKt.collectLatest(captureButtonVisibility$onfido_capture_sdk_core_release, c01601, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SelfieCaptureFragment.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LifecycleOwner viewLifecycleOwner = SelfieCaptureFragment.this.getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
                Lifecycle.State state = Lifecycle.State.STARTED;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(SelfieCaptureFragment.this, null);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(viewLifecycleOwner, state, anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public SelfieCaptureFragment() {
        super(R.layout.onfido_fragment_selfie_capture);
        Function0<ViewModelProvider.Factory> function0 = new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$viewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                final SelfieCaptureFragment selfieCaptureFragment = this.this$0;
                final Bundle bundle = null;
                return new AbstractSavedStateViewModelFactory(selfieCaptureFragment, bundle) { // from class: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$viewModel$2$invoke$$inlined$createAbstractSavedStateFactory$default$1
                    @Override // androidx.lifecycle.AbstractSavedStateViewModelFactory
                    protected <T extends ViewModel> T create(String key, Class<T> modelClass, SavedStateHandle handle) {
                        Intrinsics.checkNotNullParameter(key, "key");
                        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                        Intrinsics.checkNotNullParameter(handle, "handle");
                        SelfieCaptureViewModel selfieCaptureViewModelCreate = selfieCaptureFragment.getViewModelFactory$onfido_capture_sdk_core_release().create(handle);
                        Intrinsics.checkNotNull(selfieCaptureViewModelCreate, "null cannot be cast to non-null type T of com.onfido.android.sdk.capture.component.utils.ViewModelExtKt.createAbstractSavedStateFactory.<no name provided>.create");
                        return selfieCaptureViewModelCreate;
                    }
                };
            }
        };
        final Function0<Fragment> function02 = new Function0<Fragment>() { // from class: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$special$$inlined$viewModels$default$2
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(SelfieCaptureViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$special$$inlined$viewModels$default$4
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
        View root = getDummyAccessibilityBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewGroup.LayoutParams layoutParams = root.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.width = rect.width();
        layoutParams2.height = rect.height();
        layoutParams2.leftMargin = rect.left;
        layoutParams2.topMargin = rect.top;
        layoutParams2.bottomMargin = getResources().getDimensionPixelSize(R.dimen.onfido_capture_instructions_outer_top_margin);
        root.setLayoutParams(layoutParams2);
    }

    private final void applyValidations(OnfidoImage image) throws Resources.NotFoundException {
        this.previewImage = image;
        setImagePreview(image);
        showConfirmationStep();
    }

    private final void capture() {
        getViewModel().onCaptureRequested$onfido_capture_sdk_core_release();
        AggregatedPerformanceAnalytics performanceAnalytics$onfido_capture_sdk_core_release = getPerformanceAnalytics$onfido_capture_sdk_core_release();
        final String str = PerformanceEventName.FACE_CAPTURE;
        performanceAnalytics$onfido_capture_sdk_core_release.trackStart(new PerformanceEvents.TraceStart(PerformanceEventName.FACE_CAPTURE));
        OnfidoCamera onfidoCamera = this.onfidoCamera;
        if (onfidoCamera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
            onfidoCamera = null;
        }
        onfidoCamera.takePicture(new PhotoCaptureConfig(true), new Function1<OnfidoCamera.PictureCaptureEvent, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment.capture.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(OnfidoCamera.PictureCaptureEvent pictureCaptureEvent) throws Resources.NotFoundException {
                invoke2(pictureCaptureEvent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(OnfidoCamera.PictureCaptureEvent event) throws Resources.NotFoundException {
                Intrinsics.checkNotNullParameter(event, "event");
                SelfieCaptureFragment.this.getPerformanceAnalytics$onfido_capture_sdk_core_release().trackEnd(new PerformanceEvents.TraceEnd(str));
                if (event instanceof OnfidoCamera.PictureCaptureEvent.Captured) {
                    SelfieCaptureFragment.this.onPictureCaptured(((OnfidoCamera.PictureCaptureEvent.Captured) event).getImage());
                } else if (event instanceof OnfidoCamera.PictureCaptureEvent.Error) {
                    SelfieCaptureFragment.this.onErrorTakingPicture(((OnfidoCamera.PictureCaptureEvent.Error) event).getError());
                }
            }
        });
    }

    private final void changeStatusBarColor(int color) {
        Window window = requireActivity().getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(color);
    }

    private final void closeConfirmationScreen() {
        Object confirmationScreen = getConfirmationScreen();
        Fragment fragment = confirmationScreen instanceof Fragment ? (Fragment) confirmationScreen : null;
        if (fragment != null) {
            getChildFragmentManager().beginTransaction().remove(fragment).commitNowAllowingStateLoss();
        }
        FragmentContainerView fragmentContainer = getBinding().fragmentContainer;
        Intrinsics.checkNotNullExpressionValue(fragmentContainer, "fragmentContainer");
        ViewExtensionsKt.toGone$default(fragmentContainer, false, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishWithException(Exception exception) {
        finishWithResult(new SelfieCaptureScreen.SelfieCaptureResult.Error(exception));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishWithResult(SelfieCaptureScreen.SelfieCaptureResult result) {
        getViewModel().onCaptureCompleted(result, this.previewImage);
        String string = requireArguments().getString(KEY_REQUEST);
        if (string == null) {
            throw new IllegalArgumentException("request key argument is missing".toString());
        }
        FragmentKt.setFragmentResult(this, string, SelfieCaptureScreen.INSTANCE.storeResult(result));
    }

    private final int getBackgroundColorCaptureScreen() {
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        return ContextUtilsKt.color(contextRequireContext, R.color.onfido_camera_blur);
    }

    private final int getBackgroundColorConfirmationScreen() {
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        return ContextUtilsKt.colorFromAttr(contextRequireContext, R.attr.onfidoColorBackground);
    }

    private final OnfidoFragmentSelfieCaptureBinding getBinding() {
        OnfidoFragmentSelfieCaptureBinding onfidoFragmentSelfieCaptureBinding = this._binding;
        Intrinsics.checkNotNull(onfidoFragmentSelfieCaptureBinding);
        return onfidoFragmentSelfieCaptureBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OnfidoCaptureButtonPictureBinding getCaptureButtonBinding() {
        OnfidoCaptureButtonPictureBinding onfidoCaptureButtonPictureBinding = this._captureButtonBinding;
        Intrinsics.checkNotNull(onfidoCaptureButtonPictureBinding);
        return onfidoCaptureButtonPictureBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CaptureConfirmationScreen getConfirmationScreen() {
        ActivityResultCaller activityResultCallerFindFragmentByTag = getChildFragmentManager().findFragmentByTag(FRAGMENT_TAG_CONFIRMATION);
        if (activityResultCallerFindFragmentByTag instanceof CaptureConfirmationScreen) {
            return (CaptureConfirmationScreen) activityResultCallerFindFragmentByTag;
        }
        return null;
    }

    private final OnfidoDummyAccessibilityViewBinding getDummyAccessibilityBinding() {
        OnfidoDummyAccessibilityViewBinding onfidoDummyAccessibilityViewBinding = this._dummyAccessibilityBinding;
        Intrinsics.checkNotNull(onfidoDummyAccessibilityViewBinding);
        return onfidoDummyAccessibilityViewBinding;
    }

    private final Orientation getOrientation() {
        return getResources().getConfiguration().orientation == 2 ? Orientation.LANDSCAPE : Orientation.PORTRAIT;
    }

    private final OverlayView getOverlayView() {
        OverlayView overlayView = this._overlayView;
        Intrinsics.checkNotNull(overlayView);
        return overlayView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SelfieCaptureViewModel getViewModel() {
        return (SelfieCaptureViewModel) this.viewModel.getValue();
    }

    private final void hideConfirmationStep() {
        ViewUtil.setViewVisibility(getBinding().confirmationImage, false);
        ViewUtil.setViewVisibility(getBinding().confirmationButtons, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideLoading() {
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNull(fragmentActivityRequireActivity, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.BaseActivity");
        ((BaseActivity) fragmentActivityRequireActivity).dismissLoadingDialog$onfido_capture_sdk_core_release();
    }

    private final void inflateCaptureButton() {
        this._captureButtonBinding = OnfidoCaptureButtonPictureBinding.inflate(getLayoutInflater(), getBinding().content, true);
        getCaptureButtonBinding().captureButton.setContentDescription(getString(R.string.onfido_selfie_capture_button_accessibility));
    }

    private final void inflateDummyAccessibilityView() {
        this._dummyAccessibilityBinding = OnfidoDummyAccessibilityViewBinding.inflate(getLayoutInflater(), getBinding().content, true);
        setCaptureFrameContentDescriptionAndTitle();
    }

    private final void initLayoutAdjuster() {
        this.layoutAdjuster = new CaptureLayoutAdjuster(CaptureType.FACE, DocSide.FRONT, new CaptureLayoutAdjuster.ViewHolder(requireActivity(), getBinding().overlayTextContainer, getBinding().postCaptureValidationBubble, getBinding().confirmationButtons, getDummyAccessibilityBinding().dummyAccessibility, getCaptureButtonBinding().captureButton, getBinding().flipArrow, null, null, 384, null));
        Lifecycle lifecycleRegistry = getViewLifecycleOwner().getLifecycleRegistry();
        CaptureLayoutAdjuster captureLayoutAdjuster = this.layoutAdjuster;
        CaptureLayoutAdjuster captureLayoutAdjuster2 = null;
        if (captureLayoutAdjuster == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layoutAdjuster");
            captureLayoutAdjuster = null;
        }
        lifecycleRegistry.addObserver(captureLayoutAdjuster);
        CaptureLayoutAdjuster captureLayoutAdjuster3 = this.layoutAdjuster;
        if (captureLayoutAdjuster3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layoutAdjuster");
        } else {
            captureLayoutAdjuster2 = captureLayoutAdjuster3;
        }
        ImageView captureButton = getCaptureButtonBinding().captureButton;
        Intrinsics.checkNotNullExpressionValue(captureButton, "captureButton");
        captureLayoutAdjuster2.setCaptureInstructionsAboveView(captureButton);
    }

    private final void initValidationBubbleDelegate() {
        RelativeLayout content = getBinding().content;
        Intrinsics.checkNotNullExpressionValue(content, "content");
        this.validationBubbleOffsetDelegate = new ValidationBubblesOffsetDelegate(content, CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.id.liveValidationBubble), Integer.valueOf(R.id.postCaptureValidationBubble)}), CaptureType.FACE);
        Lifecycle lifecycleRegistry = getViewLifecycleOwner().getLifecycleRegistry();
        ValidationBubblesOffsetDelegate validationBubblesOffsetDelegate = this.validationBubbleOffsetDelegate;
        if (validationBubblesOffsetDelegate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("validationBubbleOffsetDelegate");
            validationBubblesOffsetDelegate = null;
        }
        lifecycleRegistry.addObserver(validationBubblesOffsetDelegate);
    }

    private final void observeCaptureResult() {
        Flow flowOnEach = FlowKt.onEach(getViewModel().getCaptureResult$onfido_capture_sdk_core_release(), new C06781(null));
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        FlowKt.launchIn(flowOnEach, LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner));
    }

    private final void observeErrorDescription() {
        Flow flowOnEach = FlowKt.onEach(getViewModel().getErrorDescriptorFlow$onfido_capture_sdk_core_release(), new C06791(null));
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        FlowKt.launchIn(flowOnEach, LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner));
    }

    private final void observeErrors() {
        Flow flowOnEach = FlowKt.onEach(getViewModel().getErrorMessageFlow$onfido_capture_sdk_core_release(), new C06801(null));
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        FlowKt.launchIn(flowOnEach, LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner));
    }

    private final void observeLoading() {
        Flow flowOnEach = FlowKt.onEach(getViewModel().getLoadingFlow$onfido_capture_sdk_core_release(), new C06811(null));
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        FlowKt.launchIn(flowOnEach, LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner));
    }

    private final void observeShowConfirmation() {
        Flow flowOnEach = FlowKt.onEach(getViewModel().getShowConfirmationFlow$onfido_capture_sdk_core_release(), new C06821(null));
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        FlowKt.launchIn(flowOnEach, LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner));
    }

    private final void observeViewModel() {
        observeLoading();
        observeErrors();
        observeCaptureResult();
        observeShowConfirmation();
        observeErrorDescription();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCameraNotFound() {
        getViewModel().trackCaptureError(null);
        hideLoading();
        showErrorMessage(R.string.onfido_generic_error_title, R.string.onfido_generic_error_camera_unavailable, new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment.onCameraNotFound.1
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
                SelfieCaptureFragment.this.finishWithException(CameraNotFoundException.INSTANCE);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCameraStarted() {
        getViewModel().onCameraStarted$onfido_capture_sdk_core_release();
        this.isCameraViewInitialised = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCameraUnavailable() {
        getViewModel().trackCaptureError(null);
        hideLoading();
        showErrorMessage(R.string.onfido_generic_error_title, R.string.onfido_generic_error_camera_used_by_other_app, new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment.onCameraUnavailable.1
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
                SelfieCaptureFragment.this.finishWithException(CameraNotAvailableException.INSTANCE);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onErrorTakingPicture(final Throwable error) {
        OnfidoCamera onfidoCamera = this.onfidoCamera;
        if (onfidoCamera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
            onfidoCamera = null;
        }
        onfidoCamera.stop();
        hideLoading();
        showErrorMessage(R.string.onfido_generic_error_title, R.string.onfido_generic_error_face_capture, new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment.onErrorTakingPicture.1
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
                SelfieCaptureFragment selfieCaptureFragment = SelfieCaptureFragment.this;
                Throwable th = error;
                Intrinsics.checkNotNull(th, "null cannot be cast to non-null type java.lang.Exception{ kotlin.TypeAliasesKt.Exception }");
                selfieCaptureFragment.finishWithException((Exception) th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openCaptureScreen() throws Resources.NotFoundException {
        hideLoading();
        startCamera();
        closeConfirmationScreen();
        CaptureLayoutAdjuster captureLayoutAdjuster = null;
        getBinding().confirmationImage.setImageBitmap(null);
        hideConfirmationStep();
        getOverlayView().switchConfirmationMode(false);
        getBinding().overlayTextContainer.setSelfieCaptureOverlayText$onfido_capture_sdk_core_release();
        OnfidoCaptureValidationBubble postCaptureValidationBubble = getBinding().postCaptureValidationBubble;
        Intrinsics.checkNotNullExpressionValue(postCaptureValidationBubble, "postCaptureValidationBubble");
        OnfidoCaptureValidationBubble.setVisibilityCommand$onfido_capture_sdk_core_release$default(postCaptureValidationBubble, OnfidoCaptureValidationBubble.VisibilityCommand.Gone.INSTANCE, false, 2, null);
        getBinding().confirmationButtons.setListener$onfido_capture_sdk_core_release(this);
        getBinding().confirmationButtons.setSelfieErrorState(false);
        setColorsForCaptureScreen();
        CaptureLayoutAdjuster captureLayoutAdjuster2 = this.layoutAdjuster;
        if (captureLayoutAdjuster2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layoutAdjuster");
        } else {
            captureLayoutAdjuster = captureLayoutAdjuster2;
        }
        captureLayoutAdjuster.adjustLayoutParams(false);
        setCaptureFrameContentDescriptionAndTitle();
        getViewModel().trackCapture$onfido_capture_sdk_core_release(getOrientation());
    }

    private final void setCaptureFrameContentDescriptionAndTitle() {
        StringRepresentation.SingleStringResIdRepresentation singleStringResIdRepresentation = new StringRepresentation.SingleStringResIdRepresentation(R.string.onfido_selfie_capture_frame_accessibility);
        View view = getDummyAccessibilityBinding().dummyAccessibility;
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        view.setContentDescription(singleStringResIdRepresentation.getString(contextRequireContext));
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Context contextRequireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext(...)");
        fragmentActivityRequireActivity.setTitle(singleStringResIdRepresentation.getString(contextRequireContext2));
    }

    private final void setCaptureRegion(RectF rect) {
        ValidationBubblesOffsetDelegate validationBubblesOffsetDelegate = this.validationBubbleOffsetDelegate;
        if (validationBubblesOffsetDelegate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("validationBubbleOffsetDelegate");
            validationBubblesOffsetDelegate = null;
        }
        validationBubblesOffsetDelegate.onCaptureRegionSet(rect);
    }

    private final void setColorsForCaptureScreen() throws Resources.NotFoundException {
        ContextUtilsKt.requireToolbarHost(this).setToolbarColor(R.attr.onfidoColorToolbarBackgroundDark, R.attr.onfidoColorContentToolbarTitleDark, R.attr.onfidoColorContentToolbarSubtitleDark);
        OverlayView.setColorOutsideOverlay$default(getOverlayView(), getBackgroundColorCaptureScreen(), false, 2, null);
        getBinding().watermark.setDarkBackground();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setConfirmationButtons() {
        DocumentTypeUIModel documentUIModel = DocumentUITextModelMapper.INSTANCE.toDocumentUIModel(DocumentType.GENERIC, null, null, getAnnouncementService$onfido_capture_sdk_core_release().isEnabled(), false);
        getBinding().confirmationButtons.setWarningState(false, documentUIModel);
        getBinding().confirmationButtons.setDocumentCaptureCopy(documentUIModel.getReadabilityConfirmationLabel(), documentUIModel.getReadabilityDiscardLabel(), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setForceRetryButton() {
        CaptureConfirmationScreen confirmationScreen = getConfirmationScreen();
        if (confirmationScreen != null) {
            confirmationScreen.setForceRetryButton();
        } else {
            getBinding().confirmationButtons.setWarningState(false, DocumentUITextModelMapper.INSTANCE.toDocumentUIModel(DocumentType.GENERIC, null, null, getAnnouncementService$onfido_capture_sdk_core_release().isEnabled(), false));
            getBinding().confirmationButtons.setSelfieErrorState(true);
        }
    }

    private final void setImagePreview(OnfidoImage onfidoImage) {
        BitmapDrawable bitmapDrawable;
        Bitmap bitmap;
        getBinding().confirmationImage.setScaleX(-1);
        Bitmap bitmapDecodeScaledResource$default = ImageUtils.decodeScaledResource$default(getImageUtils$onfido_capture_sdk_core_release(), onfidoImage.getData(), getBinding().confirmationImage.getWidth(), getBinding().confirmationImage.getHeight(), null, null, 24, null);
        if (getBinding().confirmationImage.getDrawable() instanceof BitmapDrawable) {
            Drawable drawable = getBinding().confirmationImage.getDrawable();
            Intrinsics.checkNotNull(drawable, "null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
            bitmapDrawable = (BitmapDrawable) drawable;
        } else {
            bitmapDrawable = null;
        }
        if (bitmapDrawable != null && (bitmap = bitmapDrawable.getBitmap()) != null) {
            bitmap.recycle();
        }
        getBinding().confirmationImage.setImageBitmap(bitmapDecodeScaledResource$default);
        updateConfirmationImageTranslationAndScale();
    }

    private final void setValidationBubbleContent(int title, Integer subtitle) {
        OnfidoCaptureValidationBubble onfidoCaptureValidationBubble = getBinding().postCaptureValidationBubble;
        Intrinsics.checkNotNull(onfidoCaptureValidationBubble);
        OnfidoCaptureValidationBubble.setContent$onfido_capture_sdk_core_release$default(onfidoCaptureValidationBubble, new OnfidoCaptureValidationBubble.Content.Error(title, subtitle), false, 2, null);
        OnfidoCaptureValidationBubble.setVisibilityCommand$onfido_capture_sdk_core_release$default(onfidoCaptureValidationBubble, new OnfidoCaptureValidationBubble.VisibilityCommand.Visible(new OnfidoCaptureValidationBubble.FocusType.AnnounceContent(false, 1, null)), false, 2, null);
    }

    static /* synthetic */ void setValidationBubbleContent$default(SelfieCaptureFragment selfieCaptureFragment, int i, Integer num, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            num = null;
        }
        selfieCaptureFragment.setValidationBubbleContent(i, num);
    }

    private final void setupCaptureButton() {
        getCaptureButtonBinding().captureButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SelfieCaptureFragment.setupCaptureButton$lambda$2(this.f$0, view);
            }
        });
        setupConfirmationButtons();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCaptureButton$lambda$2(SelfieCaptureFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.capture();
        this$0.getViewModel().trackCaptureShutterClicked();
    }

    private final void setupConfirmationButtons() {
        getBinding().confirmationButtons.setListener$onfido_capture_sdk_core_release(this);
    }

    private final void setupOverlayView() throws Resources.NotFoundException {
        if (this._overlayView != null) {
            getBinding().contentLayout.removeView(getOverlayView());
        }
        OverlayView root = OnfidoViewOverlayFaceBinding.inflate(getLayoutInflater(), getBinding().contentLayout, false).getRoot();
        root.setUp(CaptureType.FACE, this);
        Intrinsics.checkNotNull(root);
        OverlayView.setColorOutsideOverlay$default(root, getBackgroundColorCaptureScreen(), false, 2, null);
        ViewExtensionsKt.runOnMeasured(root, new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment$setupOverlayView$1$1
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
        this._overlayView = root;
        getBinding().overlayTextContainer.setSelfieCaptureOverlayText$onfido_capture_sdk_core_release();
        getOverlayView().setIsProofOfAddress(false);
        getBinding().contentLayout.addView(getOverlayView());
    }

    private final void setupToolbar() {
        ContextUtilsKt.requireToolbarHost(this).enableImmersiveFragment();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        changeStatusBarColor(ContextUtilsKt.colorFromAttr(contextRequireContext, android.R.attr.colorPrimaryDark));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showConfirmationError(ErrorDescriptor descriptor) {
        CaptureConfirmationScreen confirmationScreen = getConfirmationScreen();
        if (confirmationScreen != null) {
            confirmationScreen.showError(descriptor);
        } else {
            setValidationBubbleContent(descriptor.getTitle(), descriptor.getSubtitle());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showConfirmationStep() throws Resources.NotFoundException {
        hideLoading();
        getViewModel().onConfirmationStepTracking$onfido_capture_sdk_core_release(getOrientation());
        if (getViewModel().isDarkModeEnabled()) {
            OverlayView.setColorOutsideOverlay$default(getOverlayView(), getBackgroundColorConfirmationScreen(), false, 2, null);
        } else {
            updateColorsForConfirmationScreen();
        }
        showFaceConfirmationFragment();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showErrorMessage(int titleResId, int messageResId, Function1<? super DialogInterface, Unit> listener) {
        this.lifecycleAwareDialog.show((56 & 1) != 0 ? null : Integer.valueOf(titleResId), messageResId, (56 & 4) != 0 ? R.string.onfido_ok : 0, (56 & 8) != 0 ? null : null, (56 & 16) != 0 ? false : false, (Function1<? super DialogInterface, Unit>) ((56 & 32) != 0 ? null : null), (Function1<? super DialogInterface, Unit>) ((56 & 64) != 0 ? LifecycleAwareDialog.C07234.INSTANCE : listener));
    }

    private final void showFaceConfirmationFragment() {
        if (getConfirmationScreen() != null) {
            return;
        }
        getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new FaceConfirmationFragment(), FRAGMENT_TAG_CONFIRMATION).commit();
        FragmentContainerView fragmentContainer = getBinding().fragmentContainer;
        Intrinsics.checkNotNullExpressionValue(fragmentContainer, "fragmentContainer");
        ViewExtensionsKt.toVisible$default(fragmentContainer, false, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLoading(LoadingFragment.Companion.DialogMode dialogMode) {
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNull(fragmentActivityRequireActivity, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.BaseActivity");
        ((BaseActivity) fragmentActivityRequireActivity).showLoadingDialog$onfido_capture_sdk_core_release(dialogMode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startCamera() {
        CameraFactory cameraFactory$onfido_capture_sdk_core_release = getCameraFactory$onfido_capture_sdk_core_release();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        CameraSourcePreview cameraSourcePreview = getBinding().cameraViewCamera1;
        PreviewView previewView = getBinding().cameraViewCameraX;
        CaptureType captureType = CaptureType.FACE;
        OverlayView overlayView = getOverlayView();
        Intrinsics.checkNotNull(viewLifecycleOwner);
        OnfidoCamera onfidoCameraCreate = cameraFactory$onfido_capture_sdk_core_release.create(viewLifecycleOwner, cameraSourcePreview, previewView, overlayView, captureType);
        this.onfidoCamera = onfidoCameraCreate;
        if (onfidoCameraCreate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
            onfidoCameraCreate = null;
        }
        onfidoCameraCreate.start(OnfidoCamera.CameraFacing.FRONT, new Function1<OnfidoCamera.CameraStatus, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment.startCamera.1
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
                    SelfieCaptureFragment.this.onCameraStarted();
                    return;
                }
                if (cameraStatus instanceof OnfidoCamera.CameraStatus.Failed) {
                    SelfieCaptureFragment selfieCaptureFragment = SelfieCaptureFragment.this;
                    String message = ((OnfidoCamera.CameraStatus.Failed) cameraStatus).getError().getMessage();
                    if (message == null) {
                        message = "";
                    }
                    selfieCaptureFragment.finishWithException(new UnknownCameraException(message));
                    return;
                }
                if (Intrinsics.areEqual(cameraStatus, OnfidoCamera.CameraStatus.NotFound.INSTANCE)) {
                    SelfieCaptureFragment.this.onCameraNotFound();
                } else if (Intrinsics.areEqual(cameraStatus, OnfidoCamera.CameraStatus.NotAvailable.INSTANCE)) {
                    SelfieCaptureFragment.this.onCameraUnavailable();
                }
            }
        });
    }

    private final void updateColorsForConfirmationScreen() throws Resources.NotFoundException {
        ContextUtilsKt.requireToolbarHost(this).setToolbarColor(R.attr.onfidoColorToolbarBackground, R.attr.onfidoColorContentToolbarTitle, R.attr.onfidoColorContentToolbarSubtitle);
        OverlayView.setColorOutsideOverlay$default(getOverlayView(), getBackgroundColorConfirmationScreen(), false, 2, null);
        getBinding().watermark.setDarkBackground();
    }

    private final void updateConfirmationImageTranslationAndScale() {
        if (getOnfidoRemoteConfig$onfido_capture_sdk_core_release().isFourByThreeEnabled()) {
            OverlayMetrics overlayMetrics = this.overlayMetrics;
            OverlayMetrics overlayMetrics2 = null;
            if (overlayMetrics == null) {
                Intrinsics.throwUninitializedPropertyAccessException("overlayMetrics");
                overlayMetrics = null;
            }
            float fCenterX = overlayMetrics.getVisibleCaptureRect().centerX();
            OverlayMetrics overlayMetrics3 = this.overlayMetrics;
            if (overlayMetrics3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("overlayMetrics");
            } else {
                overlayMetrics2 = overlayMetrics3;
            }
            float fCenterY = overlayMetrics2.getVisibleCaptureRect().centerY() - (getBinding().confirmationImage.getHeight() / 2);
            getBinding().confirmationImage.setTranslationX(fCenterX - (getBinding().confirmationImage.getWidth() / 2));
            getBinding().confirmationImage.setTranslationY(fCenterY);
            getBinding().confirmationImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
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

    public final OnfidoRemoteConfig getOnfidoRemoteConfig$onfido_capture_sdk_core_release() {
        OnfidoRemoteConfig onfidoRemoteConfig = this.onfidoRemoteConfig;
        if (onfidoRemoteConfig != null) {
            return onfidoRemoteConfig;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onfidoRemoteConfig");
        return null;
    }

    public final AggregatedPerformanceAnalytics getPerformanceAnalytics$onfido_capture_sdk_core_release() {
        AggregatedPerformanceAnalytics aggregatedPerformanceAnalytics = this.performanceAnalytics;
        if (aggregatedPerformanceAnalytics != null) {
            return aggregatedPerformanceAnalytics;
        }
        Intrinsics.throwUninitializedPropertyAccessException("performanceAnalytics");
        return null;
    }

    public final RuntimePermissionsManager getPermissionsManager$onfido_capture_sdk_core_release() {
        RuntimePermissionsManager runtimePermissionsManager = this.permissionsManager;
        if (runtimePermissionsManager != null) {
            return runtimePermissionsManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("permissionsManager");
        return null;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.face.FaceConfirmationFragmentContainer
    public OnfidoImage getPreviewImage() {
        return this.previewImage;
    }

    public final SelfieCaptureViewModel.Factory getViewModelFactory$onfido_capture_sdk_core_release() {
        SelfieCaptureViewModel.Factory factory = this.viewModelFactory;
        if (factory != null) {
            return factory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelFactory");
        return null;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.ConfirmationStepButtons.Listener
    public void onCaptureConfirmed() throws Resources.NotFoundException {
        OnfidoImage onfidoImage = this.previewImage;
        if (onfidoImage == null) {
            onCaptureDiscarded();
        } else {
            getViewModel().uploadSelfie(onfidoImage, false);
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.ConfirmationStepButtons.Listener
    public void onCaptureDiscarded() throws Resources.NotFoundException {
        getViewModel().trackRetakeImage();
        openCaptureScreen();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
        this._captureButtonBinding = null;
        this._dummyAccessibilityBinding = null;
        this._overlayView = null;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.OverlayView.Listener
    public void onOverlayMetrics(OverlayMetrics overlayMetrics) {
        Intrinsics.checkNotNullParameter(overlayMetrics, "overlayMetrics");
        this.overlayMetrics = overlayMetrics;
        RectF visibleCaptureRect = overlayMetrics.getVisibleCaptureRect();
        CaptureLayoutAdjuster captureLayoutAdjuster = this.layoutAdjuster;
        if (captureLayoutAdjuster == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layoutAdjuster");
            captureLayoutAdjuster = null;
        }
        captureLayoutAdjuster.adjustLayoutParams(false);
        setCaptureRegion(visibleCaptureRect);
        adjustDummyAccessibilityView(overlayMetrics.getVisibleCaptureRect());
    }

    public final void onPictureCaptured(OnfidoImage image) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(image, "image");
        getViewModel().onPictureCaptured();
        OnfidoCamera onfidoCamera = this.onfidoCamera;
        if (onfidoCamera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
            onfidoCamera = null;
        }
        onfidoCamera.stop();
        applyValidations(image);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() throws Resources.NotFoundException {
        super.onResume();
        if (!getPermissionsManager$onfido_capture_sdk_core_release().hasPermission("android.permission.CAMERA")) {
            FragmentKt.setFragmentResult(this, OnfidoActivity.KEY_RESULT_CAPTURE_MISSING_PERMISSIONS, BundleKt.bundleOf(TuplesKt.to(OnfidoActivity.KEY_CAPTURE_MISSING_PERMISSIONS_CAPTURE_TYPE, CaptureType.FACE)));
        } else if (getConfirmationScreen() == null) {
            setupOverlayView();
            setColorsForCaptureScreen();
            setupCaptureButton();
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.face.FaceConfirmationFragmentContainer
    public void onRetakeSelfieButtonClick() throws Resources.NotFoundException {
        onCaptureDiscarded();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        setupToolbar();
        getViewModel().trackCapture$onfido_capture_sdk_core_release(getOrientation());
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        ContextUtilsKt.requireToolbarHost(this).disableImmersiveFragment();
        ContextUtilsKt.requireToolbarHost(this).resetToolbar();
        if (this.isCameraViewInitialised && getConfirmationScreen() == null) {
            OverlayView.resetFaceDetectedState$default(getOverlayView(), false, false, false, 2, null);
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.face.FaceConfirmationFragmentContainer
    public void onUploadSelfieButtonClick() throws Resources.NotFoundException {
        OnfidoCaptureValidationBubble postCaptureValidationBubble = getBinding().postCaptureValidationBubble;
        Intrinsics.checkNotNullExpressionValue(postCaptureValidationBubble, "postCaptureValidationBubble");
        OnfidoCaptureValidationBubble.setVisibilityCommand$onfido_capture_sdk_core_release$default(postCaptureValidationBubble, OnfidoCaptureValidationBubble.VisibilityCommand.Gone.INSTANCE, false, 2, null);
        OnfidoImage onfidoImage = this.previewImage;
        if (onfidoImage == null) {
            onCaptureDiscarded();
        } else {
            getViewModel().uploadSelfie(onfidoImage, true);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        SdkController companion = SdkController.INSTANCE.getInstance();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        this._binding = OnfidoFragmentSelfieCaptureBinding.bind(view);
        if (savedInstanceState != null) {
            closeConfirmationScreen();
        }
        inflateCaptureButton();
        inflateDummyAccessibilityView();
        initLayoutAdjuster();
        initValidationBubbleDelegate();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback() { // from class: com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment.onViewCreated.1
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() throws Resources.NotFoundException {
                if (SelfieCaptureFragment.this.getConfirmationScreen() != null) {
                    SelfieCaptureFragment.this.onCaptureDiscarded();
                } else {
                    SelfieCaptureFragment.this.finishWithResult(SelfieCaptureScreen.SelfieCaptureResult.Back.INSTANCE);
                }
            }
        });
        getBinding().confirmationButtons.setFaceCapture();
        getBinding().confirmationImage.setOnTouchListener(null);
        observeViewModel();
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass2(null), 3, null);
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

    public final void setOnfidoRemoteConfig$onfido_capture_sdk_core_release(OnfidoRemoteConfig onfidoRemoteConfig) {
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "<set-?>");
        this.onfidoRemoteConfig = onfidoRemoteConfig;
    }

    public final void setPerformanceAnalytics$onfido_capture_sdk_core_release(AggregatedPerformanceAnalytics aggregatedPerformanceAnalytics) {
        Intrinsics.checkNotNullParameter(aggregatedPerformanceAnalytics, "<set-?>");
        this.performanceAnalytics = aggregatedPerformanceAnalytics;
    }

    public final void setPermissionsManager$onfido_capture_sdk_core_release(RuntimePermissionsManager runtimePermissionsManager) {
        Intrinsics.checkNotNullParameter(runtimePermissionsManager, "<set-?>");
        this.permissionsManager = runtimePermissionsManager;
    }

    public final void setViewModelFactory$onfido_capture_sdk_core_release(SelfieCaptureViewModel.Factory factory) {
        Intrinsics.checkNotNullParameter(factory, "<set-?>");
        this.viewModelFactory = factory;
    }
}
