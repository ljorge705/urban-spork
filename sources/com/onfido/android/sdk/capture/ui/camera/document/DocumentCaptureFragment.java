package com.onfido.android.sdk.capture.ui.camera.document;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.activity.OnBackPressedCallback;
import androidx.camera.view.PreviewView;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentKt;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.media3.common.MimeTypes;
import com.drew.metadata.iptc.IptcDirectory;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.imageview.ShapeableImageView;
import com.onfido.android.sdk.capture.DocumentFormat;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.databinding.OnfidoCaptureButtonPictureBinding;
import com.onfido.android.sdk.capture.databinding.OnfidoDummyAccessibilityViewBinding;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentDocumentCaptureBinding;
import com.onfido.android.sdk.capture.databinding.OnfidoViewDocumentFormatSelectionBinding;
import com.onfido.android.sdk.capture.errors.ErrorDescriptor;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.flow.NfcArguments;
import com.onfido.android.sdk.capture.internal.camera.OnfidoCamera;
import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.internal.camera.factory.CameraFactory;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.service.VibratorService;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.BaseActivity;
import com.onfido.android.sdk.capture.ui.BaseFragment;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.OnfidoActivity;
import com.onfido.android.sdk.capture.ui.ZoomImageView;
import com.onfido.android.sdk.capture.ui.camera.ConfirmationStepButtons;
import com.onfido.android.sdk.capture.ui.camera.OverlayMetrics;
import com.onfido.android.sdk.capture.ui.camera.OverlayTextView;
import com.onfido.android.sdk.capture.ui.camera.OverlayView;
import com.onfido.android.sdk.capture.ui.camera.capture.PhotoCaptureConfig;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import com.onfido.android.sdk.capture.ui.camera.document.CameraState;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureScreen;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel;
import com.onfido.android.sdk.capture.ui.camera.exception.CameraNotAvailableException;
import com.onfido.android.sdk.capture.ui.camera.exception.CameraNotFoundException;
import com.onfido.android.sdk.capture.ui.camera.exception.UnknownCameraException;
import com.onfido.android.sdk.capture.ui.camera.util.CaptureLayoutAdjuster;
import com.onfido.android.sdk.capture.ui.camera.util.ValidationBubblesOffsetDelegate;
import com.onfido.android.sdk.capture.ui.camera.view.CameraSourcePreview;
import com.onfido.android.sdk.capture.ui.model.DocumentTypeUIModel;
import com.onfido.android.sdk.capture.ui.options.Orientation;
import com.onfido.android.sdk.capture.upload.ErrorType;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.ErrorTypeUtilsKt;
import com.onfido.android.sdk.capture.utils.FileUtils;
import com.onfido.android.sdk.capture.utils.FlowExtKt;
import com.onfido.android.sdk.capture.utils.FragmentExtentionsKt;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.android.sdk.capture.utils.ImageUtilsExtKt;
import com.onfido.android.sdk.capture.utils.LifecycleAwareDialog;
import com.onfido.android.sdk.capture.utils.LifecycleDisposableKt;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
import com.onfido.android.sdk.capture.utils.StringRepresentation;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.android.sdk.capture.utils.ViewUtil;
import com.onfido.android.sdk.capture.utils.WorkflowToolbarUpdateListener;
import com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble;
import com.onfido.api.client.data.DocSide;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Provider;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.sentry.SentryEvent;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Function;
import kotlin.KotlinNothingValueException;
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
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.rx3.RxAwaitKt;

@Metadata(d1 = {"\u0000Æ\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u0000 ð\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002ð\u0001B\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010a\u001a\u00020b2\u0006\u0010c\u001a\u00020dH\u0002J\u0010\u0010e\u001a\u00020b2\u0006\u0010f\u001a\u00020gH\u0002J\u0010\u0010h\u001a\u00020b2\u0006\u0010i\u001a\u000200H\u0002J\u0010\u0010j\u001a\u00020b2\u0006\u0010k\u001a\u00020lH\u0002J\u0012\u0010m\u001a\u00020b2\b\b\u0001\u0010n\u001a\u00020\u0015H\u0002J\u0010\u0010o\u001a\u00020b2\u0006\u0010p\u001a\u000200H\u0002J\u0014\u0010q\u001a\u00020b2\n\u0010r\u001a\u00060sj\u0002`tH\u0002J\u0010\u0010u\u001a\u00020b2\u0006\u0010v\u001a\u00020wH\u0002J\b\u0010x\u001a\u00020yH\u0002J\u0010\u0010z\u001a\u00020b2\u0006\u0010{\u001a\u00020|H\u0002J\b\u0010}\u001a\u00020bH\u0002J\b\u0010~\u001a\u00020bH\u0002J\b\u0010\u007f\u001a\u00020bH\u0002J\t\u0010\u0080\u0001\u001a\u00020bH\u0002J\t\u0010\u0081\u0001\u001a\u00020bH\u0002J\t\u0010\u0082\u0001\u001a\u00020\rH\u0002J\t\u0010\u0083\u0001\u001a\u00020bH\u0002J\t\u0010\u0084\u0001\u001a\u00020bH\u0002J\t\u0010\u0085\u0001\u001a\u00020bH\u0002J\u0010\u0010\u0086\u0001\u001a\u00020bH\u0082@¢\u0006\u0003\u0010\u0087\u0001J\u0010\u0010\u0088\u0001\u001a\u00020bH\u0082@¢\u0006\u0003\u0010\u0087\u0001J\u0010\u0010\u0089\u0001\u001a\u00020bH\u0082@¢\u0006\u0003\u0010\u0087\u0001J\u0010\u0010\u008a\u0001\u001a\u00020bH\u0082@¢\u0006\u0003\u0010\u0087\u0001J\u0010\u0010\u008b\u0001\u001a\u00020bH\u0082@¢\u0006\u0003\u0010\u0087\u0001J\u0010\u0010\u008c\u0001\u001a\u00020bH\u0082@¢\u0006\u0003\u0010\u0087\u0001J\u0010\u0010\u008d\u0001\u001a\u00020bH\u0082@¢\u0006\u0003\u0010\u0087\u0001J\u0010\u0010\u008e\u0001\u001a\u00020bH\u0082@¢\u0006\u0003\u0010\u0087\u0001J\u0010\u0010\u008f\u0001\u001a\u00020bH\u0082@¢\u0006\u0003\u0010\u0087\u0001J\u0010\u0010\u0090\u0001\u001a\u00020bH\u0082@¢\u0006\u0003\u0010\u0087\u0001J\t\u0010\u0091\u0001\u001a\u00020bH\u0002J\t\u0010\u0092\u0001\u001a\u00020bH\u0002J\t\u0010\u0093\u0001\u001a\u00020bH\u0002J\t\u0010\u0094\u0001\u001a\u00020bH\u0016J\t\u0010\u0095\u0001\u001a\u00020bH\u0016J\t\u0010\u0096\u0001\u001a\u00020bH\u0002J\t\u0010\u0097\u0001\u001a\u00020bH\u0016J\u0012\u0010\u0098\u0001\u001a\u00020b2\u0007\u0010v\u001a\u00030\u0099\u0001H\u0002J\t\u0010\u009a\u0001\u001a\u00020bH\u0002J\t\u0010\u009b\u0001\u001a\u00020bH\u0002J\t\u0010\u009c\u0001\u001a\u00020bH\u0002J\u0013\u0010\u009d\u0001\u001a\u00020b2\b\u0010\u009e\u0001\u001a\u00030\u009f\u0001H\u0002J\u0011\u0010 \u0001\u001a\u00020b2\u0006\u0010@\u001a\u00020AH\u0016J\t\u0010¡\u0001\u001a\u00020bH\u0016J\t\u0010¢\u0001\u001a\u00020bH\u0016J\t\u0010£\u0001\u001a\u00020bH\u0016J\t\u0010¤\u0001\u001a\u00020bH\u0002J\u0013\u0010¥\u0001\u001a\u00020b2\b\u0010¦\u0001\u001a\u00030§\u0001H\u0002J\t\u0010¨\u0001\u001a\u00020bH\u0002J\u0013\u0010©\u0001\u001a\u00020b2\b\u0010ª\u0001\u001a\u00030«\u0001H\u0002J\u001f\u0010¬\u0001\u001a\u00020b2\b\u0010\u00ad\u0001\u001a\u00030®\u00012\n\u0010¯\u0001\u001a\u0005\u0018\u00010°\u0001H\u0016J\t\u0010±\u0001\u001a\u00020bH\u0016J\t\u0010²\u0001\u001a\u00020bH\u0002J\t\u0010³\u0001\u001a\u00020bH\u0002J\u0011\u0010´\u0001\u001a\u00020b2\u0006\u0010v\u001a\u00020wH\u0002J\t\u0010µ\u0001\u001a\u00020bH\u0002J\t\u0010¶\u0001\u001a\u00020bH\u0002J\u0017\u0010·\u0001\u001a\u00020b2\f\b\u0002\u0010¸\u0001\u001a\u0005\u0018\u00010¹\u0001H\u0002J\u0012\u0010º\u0001\u001a\u00020b2\u0007\u0010»\u0001\u001a\u00020dH\u0002J\t\u0010¼\u0001\u001a\u00020bH\u0002J\u0012\u0010½\u0001\u001a\u00020b2\u0007\u0010¾\u0001\u001a\u000200H\u0002J\u0012\u0010¿\u0001\u001a\u00020b2\u0007\u0010À\u0001\u001a\u000200H\u0002J\u0012\u0010Á\u0001\u001a\u00020b2\u0007\u0010Â\u0001\u001a\u000200H\u0002J\u0012\u0010Ã\u0001\u001a\u00020b2\u0007\u0010Ä\u0001\u001a\u00020gH\u0002J\u0013\u0010Å\u0001\u001a\u00020b2\b\u0010Æ\u0001\u001a\u00030Ç\u0001H\u0002J\u001e\u0010È\u0001\u001a\u00020b2\b\u0010É\u0001\u001a\u00030Ê\u00012\t\b\u0002\u0010Ë\u0001\u001a\u000200H\u0002J\u0012\u0010Ì\u0001\u001a\u00020b2\u0007\u0010Í\u0001\u001a\u00020dH\u0002J\t\u0010Î\u0001\u001a\u00020bH\u0002J\t\u0010Ï\u0001\u001a\u00020bH\u0002J\u0010\u0010Ð\u0001\u001a\u00020bH\u0082@¢\u0006\u0003\u0010\u0087\u0001J\t\u0010Ñ\u0001\u001a\u00020bH\u0002J\t\u0010Ò\u0001\u001a\u00020bH\u0002J\u0013\u0010Ó\u0001\u001a\u00020b2\b\u0010\u00ad\u0001\u001a\u00030®\u0001H\u0002J\t\u0010Ô\u0001\u001a\u00020bH\u0002J\t\u0010Õ\u0001\u001a\u00020bH\u0002J\u0014\u0010Ö\u0001\u001a\u00020b2\t\b\u0001\u0010×\u0001\u001a\u00020\u0015H\u0002J\u0012\u0010Ø\u0001\u001a\u00020b2\u0007\u0010»\u0001\u001a\u00020dH\u0002J\u0013\u0010Ù\u0001\u001a\u00020b2\b\u0010Ú\u0001\u001a\u00030Û\u0001H\u0002JH\u0010Ü\u0001\u001a\u00020b2\t\b\u0001\u0010Ý\u0001\u001a\u00020\u00152\t\b\u0001\u0010Þ\u0001\u001a\u00020\u00152'\u0010ß\u0001\u001a\"\u0012\u0017\u0012\u00150«\u0001¢\u0006\u000f\bá\u0001\u0012\n\bâ\u0001\u0012\u0005\b\b(ª\u0001\u0012\u0004\u0012\u00020b0à\u0001H\u0002J\u0013\u0010ã\u0001\u001a\u00020b2\b\u0010ä\u0001\u001a\u00030å\u0001H\u0002J\u0012\u0010æ\u0001\u001a\u00020b2\u0007\u0010ç\u0001\u001a\u000200H\u0002J\t\u0010è\u0001\u001a\u00020bH\u0002J\t\u0010é\u0001\u001a\u00020bH\u0002J\t\u0010ê\u0001\u001a\u00020bH\u0002J\t\u0010ë\u0001\u001a\u00020bH\u0002J\t\u0010ì\u0001\u001a\u00020bH\u0002J\t\u0010í\u0001\u001a\u00020bH\u0002J\t\u0010î\u0001\u001a\u00020bH\u0002J\t\u0010ï\u0001\u001a\u00020bH\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u00020\u000f8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017R\u0014\u0010\u001a\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001d\u001a\u00020\u001e8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u001b\u0010&\u001a\u00020'8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b(\u0010)R\u0014\u0010,\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.R\u000e\u0010/\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u00101\u001a\u0002028\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u000e\u00107\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020;X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020?X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020AX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010B\u001a\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bC\u0010DR\u001e\u0010E\u001a\u00020F8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u0010\u0010K\u001a\u0004\u0018\u00010LX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020NX\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010O\u001a\u00020P8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u001b\u0010U\u001a\u00020V8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\bY\u0010+\u001a\u0004\bW\u0010XR$\u0010Z\u001a\b\u0012\u0004\u0012\u00020V0[8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_R\u000e\u0010`\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006ñ\u0001"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureFragment;", "Lcom/onfido/android/sdk/capture/ui/BaseFragment;", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayView$Listener;", "Lcom/onfido/android/sdk/capture/ui/camera/ConfirmationStepButtons$Listener;", "Lcom/onfido/android/sdk/capture/utils/WorkflowToolbarUpdateListener;", "()V", "_binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentDocumentCaptureBinding;", "_captureButtonBinding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoCaptureButtonPictureBinding;", "_dummyAccessibilityBinding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoDummyAccessibilityViewBinding;", "_overlayView", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayView;", "announcementService", "Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "getAnnouncementService$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "setAnnouncementService$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;)V", "backgroundColorCaptureScreen", "", "getBackgroundColorCaptureScreen", "()I", "backgroundColorConfirmationScreen", "getBackgroundColorConfirmationScreen", "binding", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentDocumentCaptureBinding;", "cameraFactory", "Lcom/onfido/android/sdk/capture/internal/camera/factory/CameraFactory;", "getCameraFactory$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/camera/factory/CameraFactory;", "setCameraFactory$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/camera/factory/CameraFactory;)V", "captureButtonBinding", "getCaptureButtonBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoCaptureButtonPictureBinding;", "documentFormatBottomDialog", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "getDocumentFormatBottomDialog", "()Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "documentFormatBottomDialog$delegate", "Lkotlin/Lazy;", "dummyAccessibilityBinding", "getDummyAccessibilityBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoDummyAccessibilityViewBinding;", "hasOngoingCaptureRequest", "", "imageUtils", "Lcom/onfido/android/sdk/capture/utils/ImageUtils;", "getImageUtils$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/utils/ImageUtils;", "setImageUtils$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/utils/ImageUtils;)V", "isCameraViewInitialised", "isOnConfirmationStep", "isProofOfAddress", "layoutAdjuster", "Lcom/onfido/android/sdk/capture/ui/camera/util/CaptureLayoutAdjuster;", "lifecycleAwareDialog", "Lcom/onfido/android/sdk/capture/utils/LifecycleAwareDialog;", "onfidoCamera", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera;", "overlayMetrics", "Lcom/onfido/android/sdk/capture/ui/camera/OverlayMetrics;", "overlayView", "getOverlayView", "()Lcom/onfido/android/sdk/capture/ui/camera/OverlayView;", "permissionsManager", "Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;", "getPermissionsManager$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;", "setPermissionsManager$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/common/permissions/RuntimePermissionsManager;)V", "recorder", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoRecorder;", "validationBubbleOffsetDelegate", "Lcom/onfido/android/sdk/capture/ui/camera/util/ValidationBubblesOffsetDelegate;", "vibratorService", "Lcom/onfido/android/sdk/capture/internal/service/VibratorService;", "getVibratorService$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/service/VibratorService;", "setVibratorService$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/service/VibratorService;)V", "viewModel", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel;", "getViewModel$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel;", "viewModel$delegate", "viewModelProvider", "Lcom/onfido/javax/inject/Provider;", "getViewModelProvider$onfido_capture_sdk_core_release", "()Lcom/onfido/javax/inject/Provider;", "setViewModelProvider$onfido_capture_sdk_core_release", "(Lcom/onfido/javax/inject/Provider;)V", "wasConfirmationNotShown", "adjustDummyAccessibilityView", "", "visibleCaptureRect", "Landroid/graphics/RectF;", "applyValidations", MimeTypes.BASE_TYPE_IMAGE, "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage;", "capture", "playSingleFrameAutoCapturedAnimation", "changeCameraState", "state", "Lcom/onfido/android/sdk/capture/ui/camera/document/CameraState;", "changeStatusBarColor", "color", "enableTorch", "isEnabled", "finishWithException", SentryEvent.JsonKeys.EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "finishWithResult", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult;", "getOrientation", "Lcom/onfido/android/sdk/capture/ui/options/Orientation;", "handleBinaryUploadWarning", "event", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$BinaryUploadWarningEvent;", "hideDocumentOverlay", "hideLoading", "hideVideoRecordingProgressBar", "inflateCaptureButton", "inflateDummyAccessibilityView", "inflateOverlayView", "initDocumentTypeUIModel", "initLayoutAdjuster", "initValidationBubbleDelegate", "observeAccessibilityCaptureResult", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeAccessibilityRectangleDetection", "observeCaptureErrorDialog", "observeConfirmation", "observeErrorMessages", "observeLiveValidationBubbleContent", "observeLiveValidationBubbleVisibility", "observeLoading", "observeVideoRecordingInfo", "observeVideoRecordingState", "onCameraNotFound", "onCameraStarted", "onCameraUnavailable", "onCaptureConfirmed", "onCaptureDiscarded", "onCardFormatSelected", "onDestroyView", "onDocumentCreated", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult$Completed;", "onFoldedFormatSelected", "onImageProcessingFinished", "onManualFallbackDelayFinished", "onNextFrame", "frame", "", "onOverlayMetrics", "onResume", "onStart", "onStop", "onVideoCanceled", "onVideoCaptured", "filePath", "", "onVideoTimeoutExceeded", "onVideoTimeoutRetryClick", "dialog", "Landroid/content/DialogInterface;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "onWorkflowToolbarUpdated", "openCaptureScreen", "prepareColorsForConfirmationStep", "processCaptureResult", "runAutoCaptureAccessibilityEvents", "setButtonsForForceRetry", "setCaptureFrameContentDescriptionAndTitle", "forFormat", "Lcom/onfido/android/sdk/capture/DocumentFormat;", "setCaptureRegion", "rect", "setColorsForCaptureScreen", "setConfirmationButtons", "isWarning", "setConfirmationButtonsForError", "isError", "setConfirmationStepVisibility", ViewProps.VISIBLE, "setImagePreview", "onfidoImage", "setLiveValidationBubbleContent", "content", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$Content;", "setLiveValidationBubbleVisibilityCommand", "command", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble$VisibilityCommand;", "shouldIgnoreLock", "setVideoRecordingIndicatorMargin", "captureRect", "setupCaptureButton", "setupConfirmationButtonsListener", "setupObservers", "setupOverlayView", "setupToolbar", "setupUiElements", "showCaptureErrorDialog", "showConfirmationStep", "showDocumentFormatSelectionDialog", "dialogTitle", "showDocumentOverlay", "showErrorInValidationBubble", "descriptor", "Lcom/onfido/android/sdk/capture/errors/ErrorDescriptor;", "showErrorMessage", "titleResId", "messageResId", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "showLoading", "dialogMode", "Lcom/onfido/android/sdk/capture/utils/LoadingFragment$Companion$DialogMode;", "showPostCaptureValidationBubble", "shouldShow", "showVideoRecordingProgressBar", "startCamera", "startDocumentVideoRecording", "startVideoRecording", "stopDocumentVideoRecording", "updateColorsForConfirmationScreen", "updateConfirmationImageTranslationAndScale", "updateOverlayView", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentCaptureFragment extends BaseFragment implements OverlayView.Listener, ConfirmationStepButtons.Listener, WorkflowToolbarUpdateListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long GLARE_BUBBLE_FINAL_ANIMATION_DELAY_MS = 300;
    private static final float IMAGE_PREVIEW_SCALE_X = 1.0f;
    private static final String KEY_DATA_BUNDLE = "key_data_bundle";
    public static final String KEY_DOCUMENT_CAPTURE_FRAGMENT_TAG = "DocumentCapture";
    private static final String KEY_FRONT_SIDE = "key_front_side";
    private static final String KEY_NFC_ARGUMENTS = "key_nfc_arguments";
    private static final String KEY_PROOF_OF_ADDRESS = "key_proof_of_address";
    private static final String KEY_REQUEST = "key_request";
    private OnfidoFragmentDocumentCaptureBinding _binding;
    private OnfidoCaptureButtonPictureBinding _captureButtonBinding;
    private OnfidoDummyAccessibilityViewBinding _dummyAccessibilityBinding;
    private OverlayView _overlayView;

    @Inject
    public AnnouncementService announcementService;

    @Inject
    public CameraFactory cameraFactory;

    /* renamed from: documentFormatBottomDialog$delegate, reason: from kotlin metadata */
    private final Lazy documentFormatBottomDialog;
    private boolean hasOngoingCaptureRequest;

    @Inject
    public ImageUtils imageUtils;
    private boolean isCameraViewInitialised;
    private boolean isOnConfirmationStep;
    private boolean isProofOfAddress;
    private CaptureLayoutAdjuster layoutAdjuster;
    private final LifecycleAwareDialog lifecycleAwareDialog;
    private OnfidoCamera onfidoCamera;
    private OverlayMetrics overlayMetrics;

    @Inject
    public RuntimePermissionsManager permissionsManager;
    private OnfidoCamera.VideoRecorder recorder;
    private ValidationBubblesOffsetDelegate validationBubbleOffsetDelegate;

    @Inject
    public VibratorService vibratorService;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    @Inject
    public Provider<DocumentCaptureViewModel> viewModelProvider;
    private boolean wasConfirmationNotShown;

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureFragment$Companion;", "", "()V", "GLARE_BUBBLE_FINAL_ANIMATION_DELAY_MS", "", "IMAGE_PREVIEW_SCALE_X", "", "KEY_DATA_BUNDLE", "", "KEY_DOCUMENT_CAPTURE_FRAGMENT_TAG", "KEY_FRONT_SIDE", "KEY_NFC_ARGUMENTS", "KEY_PROOF_OF_ADDRESS", "KEY_REQUEST", "newInstance", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureFragment;", "requestKey", "captureDataBundle", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "isFrontSide", "", "isProofOfAddress", "nfcArguments", "Lcom/onfido/android/sdk/capture/flow/NfcArguments;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final DocumentCaptureFragment newInstance(String requestKey, CaptureStepDataBundle captureDataBundle, boolean isFrontSide, boolean isProofOfAddress, NfcArguments nfcArguments) {
            Intrinsics.checkNotNullParameter(requestKey, "requestKey");
            Intrinsics.checkNotNullParameter(captureDataBundle, "captureDataBundle");
            Intrinsics.checkNotNullParameter(nfcArguments, "nfcArguments");
            DocumentCaptureFragment documentCaptureFragment = new DocumentCaptureFragment();
            documentCaptureFragment.setArguments(BundleKt.bundleOf(TuplesKt.to("key_request", requestKey), TuplesKt.to(DocumentCaptureFragment.KEY_DATA_BUNDLE, captureDataBundle), TuplesKt.to(DocumentCaptureFragment.KEY_FRONT_SIDE, Boolean.valueOf(isFrontSide)), TuplesKt.to(DocumentCaptureFragment.KEY_PROOF_OF_ADDRESS, Boolean.valueOf(isProofOfAddress)), TuplesKt.to(DocumentCaptureFragment.KEY_NFC_ARGUMENTS, nfcArguments)));
            return documentCaptureFragment;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment", f = "DocumentCaptureFragment.kt", i = {}, l = {515}, m = "observeAccessibilityCaptureResult", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeAccessibilityCaptureResult$1, reason: invalid class name and case insensitive filesystem */
    static final class C06151 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C06151(Continuation<? super C06151> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DocumentCaptureFragment.this.observeAccessibilityCaptureResult(this);
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment", f = "DocumentCaptureFragment.kt", i = {}, l = {508}, m = "observeAccessibilityRectangleDetection", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeAccessibilityRectangleDetection$1, reason: invalid class name and case insensitive filesystem */
    static final class C06171 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C06171(Continuation<? super C06171> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DocumentCaptureFragment.this.observeAccessibilityRectangleDetection(this);
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment", f = "DocumentCaptureFragment.kt", i = {}, l = {503}, m = "observeCaptureErrorDialog", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeCaptureErrorDialog$1, reason: invalid class name and case insensitive filesystem */
    static final class C06191 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C06191(Continuation<? super C06191> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DocumentCaptureFragment.this.observeCaptureErrorDialog(this);
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment", f = "DocumentCaptureFragment.kt", i = {}, l = {442}, m = "observeConfirmation", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeConfirmation$1, reason: invalid class name and case insensitive filesystem */
    static final class C06211 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C06211(Continuation<? super C06211> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DocumentCaptureFragment.this.observeConfirmation(this);
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment", f = "DocumentCaptureFragment.kt", i = {}, l = {492}, m = "observeErrorMessages", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeErrorMessages$1, reason: invalid class name and case insensitive filesystem */
    static final class C06231 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C06231(Continuation<? super C06231> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DocumentCaptureFragment.this.observeErrorMessages(this);
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment", f = "DocumentCaptureFragment.kt", i = {}, l = {481}, m = "observeLoading", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeLoading$1, reason: invalid class name and case insensitive filesystem */
    static final class C06271 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C06271(Continuation<? super C06271> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DocumentCaptureFragment.this.observeLoading(this);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$onImageProcessingFinished$1", f = "DocumentCaptureFragment.kt", i = {}, l = {639}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$onImageProcessingFinished$1, reason: invalid class name and case insensitive filesystem */
    static final class C06341 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06341(Continuation<? super C06341> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DocumentCaptureFragment.this.new C06341(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (DelayKt.delay(300L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            DocumentCaptureFragment.setLiveValidationBubbleVisibilityCommand$default(DocumentCaptureFragment.this, OnfidoCaptureValidationBubble.VisibilityCommand.Invisible.INSTANCE, false, 2, null);
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06341) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$onViewCreated$2", f = "DocumentCaptureFragment.kt", i = {}, l = {229}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$onViewCreated$2, reason: invalid class name and case insensitive filesystem */
    static final class C06372 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$onViewCreated$2$1", f = "DocumentCaptureFragment.kt", i = {}, l = {230}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$onViewCreated$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
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
                    DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    this.label = 1;
                    if (documentCaptureFragment.setupObservers(this) == coroutine_suspended) {
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

        C06372(Continuation<? super C06372> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DocumentCaptureFragment.this.new C06372(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                DocumentCaptureFragment documentCaptureFragment = DocumentCaptureFragment.this;
                Lifecycle.State state = Lifecycle.State.RESUMED;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(documentCaptureFragment, null);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(documentCaptureFragment, state, anonymousClass1, this) == coroutine_suspended) {
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
            return ((C06372) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$runAutoCaptureAccessibilityEvents$1", f = "DocumentCaptureFragment.kt", i = {}, l = {741, 742, 745}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$runAutoCaptureAccessibilityEvents$1, reason: invalid class name and case insensitive filesystem */
    static final class C06381 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06381(Continuation<? super C06381> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DocumentCaptureFragment.this.new C06381(continuation);
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
                com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment r7 = com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.this
                com.onfido.android.sdk.capture.internal.service.VibratorService r7 = r7.getVibratorService$onfido_capture_sdk_core_release()
                io.reactivex.rxjava3.core.Completable r7 = r7.vibrateForSuccess()
                r6.label = r3
                java.lang.Object r7 = kotlinx.coroutines.rx3.RxAwaitKt.await(r7, r6)
                if (r7 != r0) goto L3b
                return r0
            L3b:
                com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment r7 = com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.this
                com.onfido.android.sdk.capture.internal.service.AnnouncementService r7 = r7.getAnnouncementService$onfido_capture_sdk_core_release()
                io.reactivex.rxjava3.core.Completable r7 = r7.interruptAnnouncement()
                r6.label = r4
                java.lang.Object r7 = kotlinx.coroutines.rx3.RxAwaitKt.await(r7, r6)
                if (r7 != r0) goto L4e
                return r0
            L4e:
                com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment r7 = com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.this
                com.onfido.android.sdk.capture.internal.service.AnnouncementService r7 = r7.getAnnouncementService$onfido_capture_sdk_core_release()
                com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment r1 = com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.this
                int r3 = com.onfido.android.sdk.capture.R.string.onfido_doc_capture_frame_success_accessibility
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
            throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.C06381.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06381) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2", f = "DocumentCaptureFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2, reason: invalid class name and case insensitive filesystem */
    static final class C06392 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$1", f = "DocumentCaptureFragment.kt", i = {}, l = {350}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$1$1, reason: invalid class name and collision with other inner class name */
            /* synthetic */ class C01491 implements FlowCollector, FunctionAdapter {
                final /* synthetic */ DocumentCaptureFragment $tmp0;

                C01491(DocumentCaptureFragment documentCaptureFragment) {
                    this.$tmp0 = documentCaptureFragment;
                }

                public final Object emit(int i, Continuation<? super Unit> continuation) {
                    Object objInvokeSuspend$showDocumentFormatSelectionDialog = AnonymousClass1.invokeSuspend$showDocumentFormatSelectionDialog(this.$tmp0, i, continuation);
                    return objInvokeSuspend$showDocumentFormatSelectionDialog == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInvokeSuspend$showDocumentFormatSelectionDialog : Unit.INSTANCE;
                }

                public final boolean equals(Object obj) {
                    if ((obj instanceof FlowCollector) && (obj instanceof FunctionAdapter)) {
                        return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
                    }
                    return false;
                }

                @Override // kotlin.jvm.internal.FunctionAdapter
                public final Function<?> getFunctionDelegate() {
                    return new AdaptedFunctionReference(2, this.$tmp0, DocumentCaptureFragment.class, "showDocumentFormatSelectionDialog", "showDocumentFormatSelectionDialog(I)V", 4);
                }

                public final int hashCode() {
                    return getFunctionDelegate().hashCode();
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                    return emit(((Number) obj).intValue(), (Continuation<? super Unit>) continuation);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final /* synthetic */ Object invokeSuspend$showDocumentFormatSelectionDialog(DocumentCaptureFragment documentCaptureFragment, int i, Continuation continuation) {
                documentCaptureFragment.showDocumentFormatSelectionDialog(i);
                return Unit.INSTANCE;
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
                    StateFlow<Integer> docFormatDialogTitleFlow$onfido_capture_sdk_core_release = this.this$0.getViewModel$onfido_capture_sdk_core_release().getDocFormatDialogTitleFlow$onfido_capture_sdk_core_release();
                    C01491 c01491 = new C01491(this.this$0);
                    this.label = 1;
                    if (FlowExtKt.collectNotNull(docFormatDialogTitleFlow$onfido_capture_sdk_core_release, c01491, this) == coroutine_suspended) {
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

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$10", f = "DocumentCaptureFragment.kt", i = {}, l = {396}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$10, reason: invalid class name */
        static final class AnonymousClass10 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass10(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass10> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass10(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow flowFilterNotNull = FlowKt.filterNotNull(this.this$0.getViewModel$onfido_capture_sdk_core_release().getStartCapture$onfido_capture_sdk_core_release());
                    final DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.setupObservers.2.10.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit(((Boolean) obj2).booleanValue(), (Continuation<? super Unit>) continuation);
                        }

                        public final Object emit(boolean z, Continuation<? super Unit> continuation) {
                            documentCaptureFragment.capture(z);
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (flowFilterNotNull.collect(flowCollector, this) == coroutine_suspended) {
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
                return ((AnonymousClass10) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$11", f = "DocumentCaptureFragment.kt", i = {}, l = {400}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$11, reason: invalid class name */
        static final class AnonymousClass11 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass11(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass11> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass11(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    MutableSharedFlow<Boolean> imageProcessingFinished$onfido_capture_sdk_core_release = this.this$0.getViewModel$onfido_capture_sdk_core_release().getImageProcessingFinished$onfido_capture_sdk_core_release();
                    final DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    FlowCollector<? super Boolean> flowCollector = new FlowCollector() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.setupObservers.2.11.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit(((Boolean) obj2).booleanValue(), (Continuation<? super Unit>) continuation);
                        }

                        public final Object emit(boolean z, Continuation<? super Unit> continuation) {
                            documentCaptureFragment.onImageProcessingFinished();
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (imageProcessingFinished$onfido_capture_sdk_core_release.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass11) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$12", f = "DocumentCaptureFragment.kt", i = {}, l = {404}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$12, reason: invalid class name */
        static final class AnonymousClass12 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass12(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass12> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass12(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    MutableSharedFlow<Boolean> manualFallbackDelayFinished$onfido_capture_sdk_core_release = this.this$0.getViewModel$onfido_capture_sdk_core_release().getManualFallbackDelayFinished$onfido_capture_sdk_core_release();
                    final DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    FlowCollector<? super Boolean> flowCollector = new FlowCollector() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.setupObservers.2.12.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit(((Boolean) obj2).booleanValue(), (Continuation<? super Unit>) continuation);
                        }

                        public final Object emit(boolean z, Continuation<? super Unit> continuation) {
                            documentCaptureFragment.onManualFallbackDelayFinished();
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (manualFallbackDelayFinished$onfido_capture_sdk_core_release.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass12) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$13", f = "DocumentCaptureFragment.kt", i = {}, l = {409}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$13, reason: invalid class name */
        static final class AnonymousClass13 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass13(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass13> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass13(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow<Boolean> captureButtonVisibility$onfido_capture_sdk_core_release = this.this$0.getViewModel$onfido_capture_sdk_core_release().getCaptureButtonVisibility$onfido_capture_sdk_core_release();
                    final DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    FlowCollector<? super Boolean> flowCollector = new FlowCollector() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.setupObservers.2.13.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit(((Boolean) obj2).booleanValue(), (Continuation<? super Unit>) continuation);
                        }

                        public final Object emit(boolean z, Continuation<? super Unit> continuation) {
                            if (z) {
                                ImageView captureButton = documentCaptureFragment.getCaptureButtonBinding().captureButton;
                                Intrinsics.checkNotNullExpressionValue(captureButton, "captureButton");
                                ViewExtensionsKt.toVisible$default(captureButton, false, 1, null);
                            } else {
                                ImageView captureButton2 = documentCaptureFragment.getCaptureButtonBinding().captureButton;
                                Intrinsics.checkNotNullExpressionValue(captureButton2, "captureButton");
                                ViewExtensionsKt.toGone$default(captureButton2, false, 1, null);
                            }
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (captureButtonVisibility$onfido_capture_sdk_core_release.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass13) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$14", f = "DocumentCaptureFragment.kt", i = {}, l = {420}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$14, reason: invalid class name */
        static final class AnonymousClass14 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass14(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass14> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass14(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow<Boolean> shouldOpenCaptureScreen$onfido_capture_sdk_core_release = this.this$0.getViewModel$onfido_capture_sdk_core_release().getShouldOpenCaptureScreen$onfido_capture_sdk_core_release();
                    final DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    FlowCollector<? super Boolean> flowCollector = new FlowCollector() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.setupObservers.2.14.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit(((Boolean) obj2).booleanValue(), (Continuation<? super Unit>) continuation);
                        }

                        public final Object emit(boolean z, Continuation<? super Unit> continuation) throws Resources.NotFoundException {
                            if (z) {
                                documentCaptureFragment.openCaptureScreen();
                            }
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (shouldOpenCaptureScreen$onfido_capture_sdk_core_release.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass14) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$15", f = "DocumentCaptureFragment.kt", i = {}, l = {424}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$15, reason: invalid class name */
        static final class AnonymousClass15 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$15$1, reason: invalid class name */
            /* synthetic */ class AnonymousClass1 implements FlowCollector, FunctionAdapter {
                final /* synthetic */ DocumentCaptureFragment $tmp0;

                AnonymousClass1(DocumentCaptureFragment documentCaptureFragment) {
                    this.$tmp0 = documentCaptureFragment;
                }

                public final Object emit(DocumentCaptureViewModel.BinaryUploadWarningEvent binaryUploadWarningEvent, Continuation<? super Unit> continuation) throws Resources.NotFoundException {
                    Object objInvokeSuspend$handleBinaryUploadWarning = AnonymousClass15.invokeSuspend$handleBinaryUploadWarning(this.$tmp0, binaryUploadWarningEvent, continuation);
                    return objInvokeSuspend$handleBinaryUploadWarning == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInvokeSuspend$handleBinaryUploadWarning : Unit.INSTANCE;
                }

                public final boolean equals(Object obj) {
                    if ((obj instanceof FlowCollector) && (obj instanceof FunctionAdapter)) {
                        return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
                    }
                    return false;
                }

                @Override // kotlin.jvm.internal.FunctionAdapter
                public final Function<?> getFunctionDelegate() {
                    return new AdaptedFunctionReference(2, this.$tmp0, DocumentCaptureFragment.class, "handleBinaryUploadWarning", "handleBinaryUploadWarning(Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureViewModel$BinaryUploadWarningEvent;)V", 4);
                }

                public final int hashCode() {
                    return getFunctionDelegate().hashCode();
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                    return emit((DocumentCaptureViewModel.BinaryUploadWarningEvent) obj, (Continuation<? super Unit>) continuation);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass15(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass15> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final /* synthetic */ Object invokeSuspend$handleBinaryUploadWarning(DocumentCaptureFragment documentCaptureFragment, DocumentCaptureViewModel.BinaryUploadWarningEvent binaryUploadWarningEvent, Continuation continuation) throws Resources.NotFoundException {
                documentCaptureFragment.handleBinaryUploadWarning(binaryUploadWarningEvent);
                return Unit.INSTANCE;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass15(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    MutableSharedFlow<DocumentCaptureViewModel.BinaryUploadWarningEvent> binaryUploadWarning$onfido_capture_sdk_core_release = this.this$0.getViewModel$onfido_capture_sdk_core_release().getBinaryUploadWarning$onfido_capture_sdk_core_release();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0);
                    this.label = 1;
                    if (binaryUploadWarning$onfido_capture_sdk_core_release.collect(anonymousClass1, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass15) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$16", f = "DocumentCaptureFragment.kt", i = {}, l = {427}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$16, reason: invalid class name */
        static final class AnonymousClass16 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass16(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass16> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass16(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    this.label = 1;
                    if (documentCaptureFragment.observeConfirmation(this) == coroutine_suspended) {
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
                return ((AnonymousClass16) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$17", f = "DocumentCaptureFragment.kt", i = {}, l = {428}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$17, reason: invalid class name */
        static final class AnonymousClass17 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass17(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass17> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass17(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    this.label = 1;
                    if (documentCaptureFragment.observeLoading(this) == coroutine_suspended) {
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
                return ((AnonymousClass17) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$18", f = "DocumentCaptureFragment.kt", i = {}, l = {429}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$18, reason: invalid class name */
        static final class AnonymousClass18 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass18(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass18> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass18(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    this.label = 1;
                    if (documentCaptureFragment.observeErrorMessages(this) == coroutine_suspended) {
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
                return ((AnonymousClass18) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$19", f = "DocumentCaptureFragment.kt", i = {}, l = {430}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$19, reason: invalid class name */
        static final class AnonymousClass19 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass19(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass19> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass19(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    this.label = 1;
                    if (documentCaptureFragment.observeCaptureErrorDialog(this) == coroutine_suspended) {
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
                return ((AnonymousClass19) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$2", f = "DocumentCaptureFragment.kt", i = {}, l = {354}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$2, reason: invalid class name and collision with other inner class name */
        static final class C01502 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$2$1, reason: invalid class name */
            /* synthetic */ class AnonymousClass1 implements FlowCollector, FunctionAdapter {
                final /* synthetic */ DocumentCaptureFragment $tmp0;

                AnonymousClass1(DocumentCaptureFragment documentCaptureFragment) {
                    this.$tmp0 = documentCaptureFragment;
                }

                public final Object emit(CameraState cameraState, Continuation<? super Unit> continuation) {
                    Object objInvokeSuspend$changeCameraState = C01502.invokeSuspend$changeCameraState(this.$tmp0, cameraState, continuation);
                    return objInvokeSuspend$changeCameraState == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInvokeSuspend$changeCameraState : Unit.INSTANCE;
                }

                public final boolean equals(Object obj) {
                    if ((obj instanceof FlowCollector) && (obj instanceof FunctionAdapter)) {
                        return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
                    }
                    return false;
                }

                @Override // kotlin.jvm.internal.FunctionAdapter
                public final Function<?> getFunctionDelegate() {
                    return new AdaptedFunctionReference(2, this.$tmp0, DocumentCaptureFragment.class, "changeCameraState", "changeCameraState(Lcom/onfido/android/sdk/capture/ui/camera/document/CameraState;)V", 4);
                }

                public final int hashCode() {
                    return getFunctionDelegate().hashCode();
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                    return emit((CameraState) obj, (Continuation<? super Unit>) continuation);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01502(DocumentCaptureFragment documentCaptureFragment, Continuation<? super C01502> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final /* synthetic */ Object invokeSuspend$changeCameraState(DocumentCaptureFragment documentCaptureFragment, CameraState cameraState, Continuation continuation) {
                documentCaptureFragment.changeCameraState(cameraState);
                return Unit.INSTANCE;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01502(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow<CameraState> cameraState$onfido_capture_sdk_core_release = this.this$0.getViewModel$onfido_capture_sdk_core_release().getCameraState$onfido_capture_sdk_core_release();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0);
                    this.label = 1;
                    if (cameraState$onfido_capture_sdk_core_release.collect(anonymousClass1, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01502) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$20", f = "DocumentCaptureFragment.kt", i = {}, l = {431}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$20, reason: invalid class name */
        static final class AnonymousClass20 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass20(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass20> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass20(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    this.label = 1;
                    if (documentCaptureFragment.observeVideoRecordingState(this) == coroutine_suspended) {
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
                return ((AnonymousClass20) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$21", f = "DocumentCaptureFragment.kt", i = {}, l = {432}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$21, reason: invalid class name */
        static final class AnonymousClass21 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass21(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass21> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass21(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    this.label = 1;
                    if (documentCaptureFragment.observeVideoRecordingInfo(this) == coroutine_suspended) {
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
                return ((AnonymousClass21) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$22", f = "DocumentCaptureFragment.kt", i = {}, l = {OnfidoActivity.ONFIDO_RECREATE}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$22, reason: invalid class name */
        static final class AnonymousClass22 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass22(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass22> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass22(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    this.label = 1;
                    if (documentCaptureFragment.observeLiveValidationBubbleContent(this) == coroutine_suspended) {
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
                return ((AnonymousClass22) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$23", f = "DocumentCaptureFragment.kt", i = {}, l = {434}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$23, reason: invalid class name */
        static final class AnonymousClass23 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass23(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass23> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass23(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    this.label = 1;
                    if (documentCaptureFragment.observeLiveValidationBubbleVisibility(this) == coroutine_suspended) {
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
                return ((AnonymousClass23) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$24", f = "DocumentCaptureFragment.kt", i = {}, l = {435}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$24, reason: invalid class name */
        static final class AnonymousClass24 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass24(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass24> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass24(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    this.label = 1;
                    if (documentCaptureFragment.observeAccessibilityRectangleDetection(this) == coroutine_suspended) {
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
                return ((AnonymousClass24) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$25", f = "DocumentCaptureFragment.kt", i = {}, l = {436}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$25, reason: invalid class name */
        static final class AnonymousClass25 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass25(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass25> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass25(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    this.label = 1;
                    if (documentCaptureFragment.observeAccessibilityCaptureResult(this) == coroutine_suspended) {
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
                return ((AnonymousClass25) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$3", f = "DocumentCaptureFragment.kt", i = {}, l = {358}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$3, reason: invalid class name */
        static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass3> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass3(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    MutableSharedFlow<OnfidoImage> applyValidationsFlow$onfido_capture_sdk_core_release = this.this$0.getViewModel$onfido_capture_sdk_core_release().getApplyValidationsFlow$onfido_capture_sdk_core_release();
                    final DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    FlowCollector<? super OnfidoImage> flowCollector = new FlowCollector() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.setupObservers.2.3.1
                        public final Object emit(OnfidoImage onfidoImage, Continuation<? super Unit> continuation) throws Resources.NotFoundException {
                            documentCaptureFragment.applyValidations(onfidoImage);
                            return Unit.INSTANCE;
                        }

                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit((OnfidoImage) obj2, (Continuation<? super Unit>) continuation);
                        }
                    };
                    this.label = 1;
                    if (applyValidationsFlow$onfido_capture_sdk_core_release.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$4", f = "DocumentCaptureFragment.kt", i = {}, l = {363}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$4, reason: invalid class name */
        static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass4(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass4> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass4(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    MutableSharedFlow<Boolean> hidePostCaptureValidationBubbleFlow$onfido_capture_sdk_core_release = this.this$0.getViewModel$onfido_capture_sdk_core_release().getHidePostCaptureValidationBubbleFlow$onfido_capture_sdk_core_release();
                    final DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    FlowCollector<? super Boolean> flowCollector = new FlowCollector() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.setupObservers.2.4.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit(((Boolean) obj2).booleanValue(), (Continuation<? super Unit>) continuation);
                        }

                        public final Object emit(boolean z, Continuation<? super Unit> continuation) {
                            documentCaptureFragment.showPostCaptureValidationBubble(false);
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (hidePostCaptureValidationBubbleFlow$onfido_capture_sdk_core_release.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$5", f = "DocumentCaptureFragment.kt", i = {}, l = {368}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$5, reason: invalid class name */
        static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass5(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass5> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass5(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    MutableSharedFlow<DocumentCaptureViewModel.ValidCaptureEvent> validCaptureFlow$onfido_capture_sdk_core_release = this.this$0.getViewModel$onfido_capture_sdk_core_release().getValidCaptureFlow$onfido_capture_sdk_core_release();
                    final DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    FlowCollector<? super DocumentCaptureViewModel.ValidCaptureEvent> flowCollector = new FlowCollector() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.setupObservers.2.5.1
                        public final Object emit(DocumentCaptureViewModel.ValidCaptureEvent validCaptureEvent, Continuation<? super Unit> continuation) {
                            if (validCaptureEvent.isValid()) {
                                documentCaptureFragment.setConfirmationButtons(false);
                            } else {
                                documentCaptureFragment.onCaptureDiscarded();
                            }
                            return Unit.INSTANCE;
                        }

                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit((DocumentCaptureViewModel.ValidCaptureEvent) obj2, (Continuation<? super Unit>) continuation);
                        }
                    };
                    this.label = 1;
                    if (validCaptureFlow$onfido_capture_sdk_core_release.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$6", f = "DocumentCaptureFragment.kt", i = {}, l = {IptcDirectory.TAG_ARM_VERSION}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$6, reason: invalid class name */
        static final class AnonymousClass6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass6(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass6> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass6(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    StateFlow<Boolean> shouldEnableTorch$onfido_capture_sdk_core_release = this.this$0.getViewModel$onfido_capture_sdk_core_release().getShouldEnableTorch$onfido_capture_sdk_core_release();
                    final DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    FlowCollector<? super Boolean> flowCollector = new FlowCollector() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.setupObservers.2.6.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit(((Boolean) obj2).booleanValue(), (Continuation<? super Unit>) continuation);
                        }

                        public final Object emit(boolean z, Continuation<? super Unit> continuation) {
                            documentCaptureFragment.enableTorch(z);
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (shouldEnableTorch$onfido_capture_sdk_core_release.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass6) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$7", f = "DocumentCaptureFragment.kt", i = {}, l = {382}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$7, reason: invalid class name */
        static final class AnonymousClass7 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass7(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass7> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass7(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    MutableSharedFlow<DocumentCaptureScreen.DocumentCaptureResult> captureResult$onfido_capture_sdk_core_release = this.this$0.getViewModel$onfido_capture_sdk_core_release().getCaptureResult$onfido_capture_sdk_core_release();
                    final DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    FlowCollector<? super DocumentCaptureScreen.DocumentCaptureResult> flowCollector = new FlowCollector() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.setupObservers.2.7.1
                        public final Object emit(DocumentCaptureScreen.DocumentCaptureResult documentCaptureResult, Continuation<? super Unit> continuation) throws Resources.NotFoundException {
                            documentCaptureFragment.processCaptureResult(documentCaptureResult);
                            return Unit.INSTANCE;
                        }

                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit((DocumentCaptureScreen.DocumentCaptureResult) obj2, (Continuation<? super Unit>) continuation);
                        }
                    };
                    this.label = 1;
                    if (captureResult$onfido_capture_sdk_core_release.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass7) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$8", f = "DocumentCaptureFragment.kt", i = {}, l = {386}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$8, reason: invalid class name */
        static final class AnonymousClass8 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass8(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass8> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass8(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    MutableSharedFlow<ErrorDescriptor> errorDescriptorFlow$onfido_capture_sdk_core_release = this.this$0.getViewModel$onfido_capture_sdk_core_release().getErrorDescriptorFlow$onfido_capture_sdk_core_release();
                    final DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    FlowCollector<? super ErrorDescriptor> flowCollector = new FlowCollector() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.setupObservers.2.8.1
                        public final Object emit(ErrorDescriptor errorDescriptor, Continuation<? super Unit> continuation) {
                            documentCaptureFragment.showErrorInValidationBubble(errorDescriptor);
                            return Unit.INSTANCE;
                        }

                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit((ErrorDescriptor) obj2, (Continuation<? super Unit>) continuation);
                        }
                    };
                    this.label = 1;
                    if (errorDescriptorFlow$onfido_capture_sdk_core_release.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass8) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$9", f = "DocumentCaptureFragment.kt", i = {}, l = {390}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$setupObservers$2$9, reason: invalid class name */
        static final class AnonymousClass9 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ DocumentCaptureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass9(DocumentCaptureFragment documentCaptureFragment, Continuation<? super AnonymousClass9> continuation) {
                super(2, continuation);
                this.this$0 = documentCaptureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass9(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    MutableSharedFlow<Boolean> shouldHideOverlay$onfido_capture_sdk_core_release = this.this$0.getViewModel$onfido_capture_sdk_core_release().getShouldHideOverlay$onfido_capture_sdk_core_release();
                    final DocumentCaptureFragment documentCaptureFragment = this.this$0;
                    FlowCollector<? super Boolean> flowCollector = new FlowCollector() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.setupObservers.2.9.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit(((Boolean) obj2).booleanValue(), (Continuation<? super Unit>) continuation);
                        }

                        public final Object emit(boolean z, Continuation<? super Unit> continuation) {
                            documentCaptureFragment.hideDocumentOverlay();
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (shouldHideOverlay$onfido_capture_sdk_core_release.collect(flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass9) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        C06392(Continuation<? super C06392> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06392 c06392 = DocumentCaptureFragment.this.new C06392(continuation);
            c06392.L$0 = obj;
            return c06392;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C01502(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass3(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass4(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass5(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass6(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass7(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass8(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass9(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass10(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass11(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass12(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass13(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass14(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass15(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass16(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass17(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass18(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass19(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass20(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass21(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass22(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass23(DocumentCaptureFragment.this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass24(DocumentCaptureFragment.this, null), 3, null);
            return BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass25(DocumentCaptureFragment.this, null), 3, null);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return ((C06392) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$showVideoRecordingProgressBar$1", f = "DocumentCaptureFragment.kt", i = {}, l = {971, 974}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$showVideoRecordingProgressBar$1, reason: invalid class name and case insensitive filesystem */
    static final class C06411 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06411(Continuation<? super C06411> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DocumentCaptureFragment.this.new C06411(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Completable completableInterruptAnnouncement = DocumentCaptureFragment.this.getAnnouncementService$onfido_capture_sdk_core_release().interruptAnnouncement();
                this.label = 1;
                if (RxAwaitKt.await(completableInterruptAnnouncement, this) == coroutine_suspended) {
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
            AnnouncementService announcementService$onfido_capture_sdk_core_release = DocumentCaptureFragment.this.getAnnouncementService$onfido_capture_sdk_core_release();
            String string = DocumentCaptureFragment.this.getString(R.string.onfido_doc_capture_header_capturing);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            Completable completableAnnounceString$default = AnnouncementService.announceString$default(announcementService$onfido_capture_sdk_core_release, new String[]{string}, false, 2, (Object) null);
            this.label = 2;
            if (RxAwaitKt.await(completableAnnounceString$default, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06411) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public DocumentCaptureFragment() {
        super(R.layout.onfido_fragment_document_capture);
        Function0<ViewModelProvider.Factory> function0 = new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$viewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                final DocumentCaptureFragment documentCaptureFragment = this.this$0;
                return new ViewModelProvider.Factory() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$viewModel$2$invoke$$inlined$createViewModelFactory$1
                    @Override // androidx.lifecycle.ViewModelProvider.Factory
                    public <T extends ViewModel> T create(Class<T> modelClass) {
                        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                        DocumentCaptureViewModel documentCaptureViewModel = documentCaptureFragment.getViewModelProvider$onfido_capture_sdk_core_release().get();
                        Intrinsics.checkNotNull(documentCaptureViewModel, "null cannot be cast to non-null type T of com.onfido.android.sdk.capture.component.utils.ViewModelExtKt.createViewModelFactory.<no name provided>.create");
                        return documentCaptureViewModel;
                    }
                };
            }
        };
        final Function0<Fragment> function02 = new Function0<Fragment>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$special$$inlined$viewModels$default$2
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(DocumentCaptureViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$special$$inlined$viewModels$default$4
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
        this.wasConfirmationNotShown = true;
        this.lifecycleAwareDialog = new LifecycleAwareDialog(this, (Function1) null, 2, (DefaultConstructorMarker) null);
        this.documentFormatBottomDialog = LazyKt.lazy(new Function0<BottomSheetDialog>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$documentFormatBottomDialog$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BottomSheetDialog invoke() {
                return new BottomSheetDialog(this.this$0.requireContext());
            }
        });
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void applyValidations(OnfidoImage image) throws Resources.NotFoundException {
        setImagePreview(image);
        if (getViewModel$onfido_capture_sdk_core_release().getDocumentType$onfido_capture_sdk_core_release() == null) {
            showConfirmationStep();
            return;
        }
        DocumentCaptureViewModel viewModel$onfido_capture_sdk_core_release = getViewModel$onfido_capture_sdk_core_release();
        OverlayMetrics overlayMetrics = this.overlayMetrics;
        if (overlayMetrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayMetrics");
            overlayMetrics = null;
        }
        viewModel$onfido_capture_sdk_core_release.applyPostCaptureValidations$onfido_capture_sdk_core_release(image, overlayMetrics.getRealCaptureRect());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void capture(boolean playSingleFrameAutoCapturedAnimation) {
        if (this.hasOngoingCaptureRequest) {
            Timber.INSTANCE.i("There is an ongoing capture request! This request has been ignored!", new Object[0]);
            return;
        }
        this.hasOngoingCaptureRequest = true;
        if (!getViewModel$onfido_capture_sdk_core_release().isCutoffImprovementsEnabled$onfido_capture_sdk_core_release() && playSingleFrameAutoCapturedAnimation) {
            getOverlayView().onSingleFrameAutoCaptured(new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.capture.1
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
                    DocumentCaptureFragment.this.runAutoCaptureAccessibilityEvents();
                }
            });
        }
        getViewModel$onfido_capture_sdk_core_release().onCaptureButtonClicked$onfido_capture_sdk_core_release();
        OnfidoCamera onfidoCamera = this.onfidoCamera;
        if (onfidoCamera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
            onfidoCamera = null;
        }
        onfidoCamera.takePicture(new PhotoCaptureConfig(false), new Function1<OnfidoCamera.PictureCaptureEvent, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.capture.2
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
                if (event instanceof OnfidoCamera.PictureCaptureEvent.Captured) {
                    DocumentCaptureFragment.this.getViewModel$onfido_capture_sdk_core_release().onPictureCaptured$onfido_capture_sdk_core_release(((OnfidoCamera.PictureCaptureEvent.Captured) event).getImage());
                } else if (event instanceof OnfidoCamera.PictureCaptureEvent.Error) {
                    DocumentCaptureFragment.this.getViewModel$onfido_capture_sdk_core_release().onPictureCaptureFailed$onfido_capture_sdk_core_release();
                }
                DocumentCaptureFragment.this.hasOngoingCaptureRequest = false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeCameraState(CameraState state) {
        if (!Intrinsics.areEqual(state, CameraState.OFF.INSTANCE)) {
            if (!Intrinsics.areEqual(state, CameraState.ON.INSTANCE)) {
                Intrinsics.areEqual(state, CameraState.Uninitialized.INSTANCE);
                return;
            } else {
                if (this.isOnConfirmationStep) {
                    return;
                }
                startCamera();
                return;
            }
        }
        OnfidoCamera onfidoCamera = this.onfidoCamera;
        if (onfidoCamera != null) {
            if (onfidoCamera == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
                onfidoCamera = null;
            }
            onfidoCamera.stop();
        }
    }

    private final void changeStatusBarColor(int color) {
        Window window = requireActivity().getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(color);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enableTorch(boolean isEnabled) {
        OnfidoCamera onfidoCamera = this.onfidoCamera;
        if (onfidoCamera != null) {
            if (onfidoCamera == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
                onfidoCamera = null;
            }
            onfidoCamera.getCameraControl().enableTorch(isEnabled);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishWithException(Exception exception) {
        if (FragmentExtentionsKt.isAttached(this)) {
            finishWithResult(new DocumentCaptureScreen.DocumentCaptureResult.Error(exception));
        }
    }

    private final void finishWithResult(DocumentCaptureScreen.DocumentCaptureResult result) {
        getViewModel$onfido_capture_sdk_core_release().onCaptureCompleted$onfido_capture_sdk_core_release(result);
        String string = requireArguments().getString("key_request");
        if (string == null) {
            throw new IllegalArgumentException("request key key_request argument is missing".toString());
        }
        FragmentKt.setFragmentResult(this, string, DocumentCaptureScreen.INSTANCE.toBundle$onfido_capture_sdk_core_release(result));
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

    /* JADX INFO: Access modifiers changed from: private */
    public final OnfidoFragmentDocumentCaptureBinding getBinding() {
        OnfidoFragmentDocumentCaptureBinding onfidoFragmentDocumentCaptureBinding = this._binding;
        Intrinsics.checkNotNull(onfidoFragmentDocumentCaptureBinding);
        return onfidoFragmentDocumentCaptureBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OnfidoCaptureButtonPictureBinding getCaptureButtonBinding() {
        OnfidoCaptureButtonPictureBinding onfidoCaptureButtonPictureBinding = this._captureButtonBinding;
        Intrinsics.checkNotNull(onfidoCaptureButtonPictureBinding);
        return onfidoCaptureButtonPictureBinding;
    }

    private final BottomSheetDialog getDocumentFormatBottomDialog() {
        return (BottomSheetDialog) this.documentFormatBottomDialog.getValue();
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
    public final void handleBinaryUploadWarning(DocumentCaptureViewModel.BinaryUploadWarningEvent event) throws Resources.NotFoundException {
        if (this.isOnConfirmationStep) {
            processCaptureResult(event.getResult());
            return;
        }
        showConfirmationStep();
        setConfirmationButtons(false);
        showErrorInValidationBubble(event.getErrorDescription());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideDocumentOverlay() {
        OnfidoFragmentDocumentCaptureBinding binding = getBinding();
        ShapeableImageView[] shapeableImageViewArr = {binding.passportOverlay, binding.frenchDLOverlay, binding.germanDLOverlay, binding.italianIDOverlay};
        ArrayList<ShapeableImageView> arrayList = new ArrayList();
        for (int i = 0; i < 4; i++) {
            ShapeableImageView shapeableImageView = shapeableImageViewArr[i];
            Intrinsics.checkNotNull(shapeableImageView);
            if (ViewExtensionsKt.isVisible(shapeableImageView)) {
                arrayList.add(shapeableImageView);
            }
        }
        for (ShapeableImageView shapeableImageView2 : arrayList) {
            Intrinsics.checkNotNull(shapeableImageView2);
            ViewExtensionsKt.toGone$default(shapeableImageView2, false, 1, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideLoading() {
        if (FragmentExtentionsKt.isAttached(this)) {
            FragmentActivity fragmentActivityRequireActivity = requireActivity();
            Intrinsics.checkNotNull(fragmentActivityRequireActivity, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.BaseActivity");
            ((BaseActivity) fragmentActivityRequireActivity).dismissLoadingDialog$onfido_capture_sdk_core_release();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideVideoRecordingProgressBar() {
        LinearLayout root = getBinding().videoRecordingContainer.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewExtensionsKt.toGone(root, false);
    }

    private final void inflateCaptureButton() {
        this._captureButtonBinding = OnfidoCaptureButtonPictureBinding.inflate(getLayoutInflater(), getBinding().content, true);
        getCaptureButtonBinding().captureButton.setContentDescription(getString(R.string.onfido_doc_capture_button_accessibility));
    }

    private final void inflateDummyAccessibilityView() {
        this._dummyAccessibilityBinding = OnfidoDummyAccessibilityViewBinding.inflate(getLayoutInflater(), getBinding().content, true);
        setCaptureFrameContentDescriptionAndTitle$default(this, null, 1, null);
    }

    private final OverlayView inflateOverlayView() {
        View viewInflate = getLayoutInflater().inflate(getViewModel$onfido_capture_sdk_core_release().getOverlayLayout$onfido_capture_sdk_core_release(), (ViewGroup) getBinding().contentLayout, false);
        Intrinsics.checkNotNull(viewInflate, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.camera.OverlayView");
        return (OverlayView) viewInflate;
    }

    private final void initDocumentTypeUIModel() {
        getViewModel$onfido_capture_sdk_core_release().initDocumentTypeUIModel$onfido_capture_sdk_core_release(this.isProofOfAddress);
    }

    private final void initLayoutAdjuster() {
        CaptureLayoutAdjuster.ViewHolder viewHolder = new CaptureLayoutAdjuster.ViewHolder(requireActivity(), getBinding().overlayTextContainer, getBinding().postCaptureValidationBubble, getBinding().confirmationButtons, getDummyAccessibilityBinding().dummyAccessibility, getCaptureButtonBinding().captureButton, getBinding().flipArrow, getBinding().watermark, getBinding().videoRecordingContainer.getRoot());
        CaptureType captureType = CaptureType.DOCUMENT;
        DocSide documentSide$onfido_capture_sdk_core_release = getViewModel$onfido_capture_sdk_core_release().getDocumentSide();
        if (documentSide$onfido_capture_sdk_core_release == null) {
            documentSide$onfido_capture_sdk_core_release = DocSide.FRONT;
        }
        this.layoutAdjuster = new CaptureLayoutAdjuster(captureType, documentSide$onfido_capture_sdk_core_release, viewHolder);
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
        this.validationBubbleOffsetDelegate = new ValidationBubblesOffsetDelegate(content, CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.id.liveValidationBubble), Integer.valueOf(R.id.postCaptureValidationBubble)}), CaptureType.DOCUMENT);
        Lifecycle lifecycleRegistry = getViewLifecycleOwner().getLifecycleRegistry();
        ValidationBubblesOffsetDelegate validationBubblesOffsetDelegate = this.validationBubbleOffsetDelegate;
        if (validationBubblesOffsetDelegate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("validationBubbleOffsetDelegate");
            validationBubblesOffsetDelegate = null;
        }
        lifecycleRegistry.addObserver(validationBubblesOffsetDelegate);
        showPostCaptureValidationBubble(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object observeAccessibilityCaptureResult(kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.C06151
            if (r0 == 0) goto L13
            r0 = r5
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeAccessibilityCaptureResult$1 r0 = (com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.C06151) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeAccessibilityCaptureResult$1 r0 = new com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeAccessibilityCaptureResult$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 == r3) goto L2d
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            kotlin.ResultKt.throwOnFailure(r5)
            goto L4a
        L31:
            kotlin.ResultKt.throwOnFailure(r5)
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel r5 = r4.getViewModel$onfido_capture_sdk_core_release()
            kotlinx.coroutines.flow.MutableSharedFlow r5 = r5.getAccessibleCaptureResult$onfido_capture_sdk_core_release()
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeAccessibilityCaptureResult$2 r2 = new com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeAccessibilityCaptureResult$2
            r2.<init>()
            r0.label = r3
            java.lang.Object r5 = r5.collect(r2, r0)
            if (r5 != r1) goto L4a
            return r1
        L4a:
            kotlin.KotlinNothingValueException r5 = new kotlin.KotlinNothingValueException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.observeAccessibilityCaptureResult(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object observeAccessibilityRectangleDetection(kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.C06171
            if (r0 == 0) goto L13
            r0 = r5
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeAccessibilityRectangleDetection$1 r0 = (com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.C06171) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeAccessibilityRectangleDetection$1 r0 = new com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeAccessibilityRectangleDetection$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 == r3) goto L2d
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            kotlin.ResultKt.throwOnFailure(r5)
            goto L4a
        L31:
            kotlin.ResultKt.throwOnFailure(r5)
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel r5 = r4.getViewModel$onfido_capture_sdk_core_release()
            kotlinx.coroutines.flow.MutableSharedFlow r5 = r5.getAccessibleCaptureRectangleDetection$onfido_capture_sdk_core_release()
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeAccessibilityRectangleDetection$2 r2 = new com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeAccessibilityRectangleDetection$2
            r2.<init>()
            r0.label = r3
            java.lang.Object r5 = r5.collect(r2, r0)
            if (r5 != r1) goto L4a
            return r1
        L4a:
            kotlin.KotlinNothingValueException r5 = new kotlin.KotlinNothingValueException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.observeAccessibilityRectangleDetection(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object observeCaptureErrorDialog(kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.C06191
            if (r0 == 0) goto L13
            r0 = r5
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeCaptureErrorDialog$1 r0 = (com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.C06191) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeCaptureErrorDialog$1 r0 = new com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeCaptureErrorDialog$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 == r3) goto L2d
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            kotlin.ResultKt.throwOnFailure(r5)
            goto L4a
        L31:
            kotlin.ResultKt.throwOnFailure(r5)
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel r5 = r4.getViewModel$onfido_capture_sdk_core_release()
            kotlinx.coroutines.flow.StateFlow r5 = r5.getShouldShowCaptureErrorDialog$onfido_capture_sdk_core_release()
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeCaptureErrorDialog$2 r2 = new com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeCaptureErrorDialog$2
            r2.<init>()
            r0.label = r3
            java.lang.Object r5 = r5.collect(r2, r0)
            if (r5 != r1) goto L4a
            return r1
        L4a:
            kotlin.KotlinNothingValueException r5 = new kotlin.KotlinNothingValueException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.observeCaptureErrorDialog(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object observeConfirmation(kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.C06211
            if (r0 == 0) goto L13
            r0 = r5
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeConfirmation$1 r0 = (com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.C06211) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeConfirmation$1 r0 = new com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeConfirmation$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 == r3) goto L2d
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            kotlin.ResultKt.throwOnFailure(r5)
            goto L4a
        L31:
            kotlin.ResultKt.throwOnFailure(r5)
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel r5 = r4.getViewModel$onfido_capture_sdk_core_release()
            kotlinx.coroutines.flow.MutableSharedFlow r5 = r5.getShowConfirmationFlow$onfido_capture_sdk_core_release()
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeConfirmation$2 r2 = new com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeConfirmation$2
            r2.<init>()
            r0.label = r3
            java.lang.Object r5 = r5.collect(r2, r0)
            if (r5 != r1) goto L4a
            return r1
        L4a:
            kotlin.KotlinNothingValueException r5 = new kotlin.KotlinNothingValueException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.observeConfirmation(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object observeErrorMessages(kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.C06231
            if (r0 == 0) goto L13
            r0 = r5
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeErrorMessages$1 r0 = (com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.C06231) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeErrorMessages$1 r0 = new com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeErrorMessages$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 == r3) goto L2d
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            kotlin.ResultKt.throwOnFailure(r5)
            goto L4a
        L31:
            kotlin.ResultKt.throwOnFailure(r5)
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel r5 = r4.getViewModel$onfido_capture_sdk_core_release()
            kotlinx.coroutines.flow.MutableSharedFlow r5 = r5.getErrorMessageFlow$onfido_capture_sdk_core_release()
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeErrorMessages$2 r2 = new com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeErrorMessages$2
            r2.<init>()
            r0.label = r3
            java.lang.Object r5 = r5.collect(r2, r0)
            if (r5 != r1) goto L4a
            return r1
        L4a:
            kotlin.KotlinNothingValueException r5 = new kotlin.KotlinNothingValueException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.observeErrorMessages(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object observeLiveValidationBubbleContent(Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.filterNotNull(getViewModel$onfido_capture_sdk_core_release().getShowLiveValidation$onfido_capture_sdk_core_release()).collect(new FlowCollector() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.observeLiveValidationBubbleContent.2
            public final Object emit(OnfidoCaptureValidationBubble.Content content, Continuation<? super Unit> continuation2) {
                DocumentCaptureFragment.this.setLiveValidationBubbleContent(content);
                return Unit.INSTANCE;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((OnfidoCaptureValidationBubble.Content) obj, (Continuation<? super Unit>) continuation2);
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object observeLiveValidationBubbleVisibility(Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.filterNotNull(getViewModel$onfido_capture_sdk_core_release().getLiveValidationBubbleVisibility$onfido_capture_sdk_core_release()).collect(new FlowCollector() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.observeLiveValidationBubbleVisibility.2
            public final Object emit(OnfidoCaptureValidationBubble.VisibilityCommand visibilityCommand, Continuation<? super Unit> continuation2) {
                DocumentCaptureFragment.setLiveValidationBubbleVisibilityCommand$default(DocumentCaptureFragment.this, visibilityCommand, false, 2, null);
                return Unit.INSTANCE;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((OnfidoCaptureValidationBubble.VisibilityCommand) obj, (Continuation<? super Unit>) continuation2);
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object observeLoading(kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.C06271
            if (r0 == 0) goto L13
            r0 = r5
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeLoading$1 r0 = (com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.C06271) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeLoading$1 r0 = new com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeLoading$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 == r3) goto L2d
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            kotlin.ResultKt.throwOnFailure(r5)
            goto L4a
        L31:
            kotlin.ResultKt.throwOnFailure(r5)
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureViewModel r5 = r4.getViewModel$onfido_capture_sdk_core_release()
            kotlinx.coroutines.flow.MutableStateFlow r5 = r5.getLoadingFlow$onfido_capture_sdk_core_release()
            com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeLoading$2 r2 = new com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$observeLoading$2
            r2.<init>()
            r0.label = r3
            java.lang.Object r5 = r5.collect(r2, r0)
            if (r5 != r1) goto L4a
            return r1
        L4a:
            kotlin.KotlinNothingValueException r5 = new kotlin.KotlinNothingValueException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.observeLoading(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object observeVideoRecordingInfo(Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.filterNotNull(getViewModel$onfido_capture_sdk_core_release().getShowVideoRecordingInfo$onfido_capture_sdk_core_release()).collect(new FlowCollector() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.observeVideoRecordingInfo.2
            public final Object emit(DocumentCaptureViewModel.VideoRecordingEvent videoRecordingEvent, Continuation<? super Unit> continuation2) {
                if (videoRecordingEvent.getShouldShow()) {
                    DocumentCaptureFragment.this.showVideoRecordingProgressBar();
                    OverlayTextView overlayTextContainer = DocumentCaptureFragment.this.getBinding().overlayTextContainer;
                    Intrinsics.checkNotNullExpressionValue(overlayTextContainer, "overlayTextContainer");
                    OverlayTextView.setMainText$onfido_capture_sdk_core_release$default(overlayTextContainer, videoRecordingEvent.getWithMessage(), 0, false, 6, null);
                } else {
                    DocumentCaptureFragment.this.hideVideoRecordingProgressBar();
                }
                return Unit.INSTANCE;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((DocumentCaptureViewModel.VideoRecordingEvent) obj, (Continuation<? super Unit>) continuation2);
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object observeVideoRecordingState(Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.filterNotNull(getViewModel$onfido_capture_sdk_core_release().getStartVideoRecording$onfido_capture_sdk_core_release()).collect(new FlowCollector() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.observeVideoRecordingState.2
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit(((Boolean) obj).booleanValue(), (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(boolean z, Continuation<? super Unit> continuation2) {
                if (z) {
                    DocumentCaptureFragment.this.startDocumentVideoRecording();
                } else {
                    DocumentCaptureFragment.this.stopDocumentVideoRecording();
                }
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCameraNotFound() {
        getViewModel$onfido_capture_sdk_core_release().trackCaptureError$onfido_capture_sdk_core_release();
        hideLoading();
        this.lifecycleAwareDialog.show((56 & 1) != 0 ? null : Integer.valueOf(R.string.onfido_generic_error_title), R.string.onfido_generic_error_camera_unavailable, (56 & 4) != 0 ? R.string.onfido_ok : 0, (56 & 8) != 0 ? null : null, (56 & 16) != 0 ? false : false, (Function1<? super DialogInterface, Unit>) ((56 & 32) != 0 ? null : null), (Function1<? super DialogInterface, Unit>) ((56 & 64) != 0 ? LifecycleAwareDialog.C07234.INSTANCE : new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.onCameraNotFound.1
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
                DocumentCaptureFragment.this.finishWithException(CameraNotFoundException.INSTANCE);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCameraStarted() {
        getViewModel$onfido_capture_sdk_core_release().onCameraStarted$onfido_capture_sdk_core_release();
        OnfidoCamera onfidoCamera = this.onfidoCamera;
        OverlayMetrics overlayMetrics = null;
        if (onfidoCamera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
            onfidoCamera = null;
        }
        Disposable disposableSubscribe = onfidoCamera.observeFrame().subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.onCameraStarted.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                DocumentCaptureFragment.this.onNextFrame(p0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        LifecycleDisposableKt.disposeOnDestroy(disposableSubscribe, viewLifecycleOwner);
        if (this.isCameraViewInitialised && !this.isOnConfirmationStep && this.onfidoCamera != null && getViewModel$onfido_capture_sdk_core_release().shouldRecordDocumentVideo$onfido_capture_sdk_core_release()) {
            hideVideoRecordingProgressBar();
            ViewUtil.setViewVisibilityWithoutAnimation(getBinding().overlayTextContainer, true);
            getOverlayView().resetDocumentOverlay();
            getBinding().confirmationImage.setImageBitmap(null);
        }
        this.isCameraViewInitialised = true;
        if (getViewModel$onfido_capture_sdk_core_release().isFourByThreeEnabled$onfido_capture_sdk_core_release()) {
            OnfidoCamera onfidoCamera2 = this.onfidoCamera;
            if (onfidoCamera2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
                onfidoCamera2 = null;
            }
            OverlayMetrics overlayMetrics2 = this.overlayMetrics;
            if (overlayMetrics2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("overlayMetrics");
            } else {
                overlayMetrics = overlayMetrics2;
            }
            onfidoCamera2.centerPreviewAccordingTo(overlayMetrics.getVisibleCaptureRect());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCameraUnavailable() {
        if (FragmentExtentionsKt.isAttached(this)) {
            getViewModel$onfido_capture_sdk_core_release().trackCaptureError$onfido_capture_sdk_core_release();
            hideLoading();
            this.lifecycleAwareDialog.show((56 & 1) != 0 ? null : Integer.valueOf(R.string.onfido_generic_error_title), R.string.onfido_generic_error_camera_used_by_other_app, (56 & 4) != 0 ? R.string.onfido_ok : 0, (56 & 8) != 0 ? null : null, (56 & 16) != 0 ? false : false, (Function1<? super DialogInterface, Unit>) ((56 & 32) != 0 ? null : null), (Function1<? super DialogInterface, Unit>) ((56 & 64) != 0 ? LifecycleAwareDialog.C07234.INSTANCE : new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.onCameraUnavailable.1
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
                    DocumentCaptureFragment.this.finishWithException(CameraNotAvailableException.INSTANCE);
                }
            }));
        }
    }

    private final void onCardFormatSelected() {
        DocumentCaptureViewModel viewModel$onfido_capture_sdk_core_release = getViewModel$onfido_capture_sdk_core_release();
        DocumentFormat documentFormat = DocumentFormat.CARD;
        viewModel$onfido_capture_sdk_core_release.onDocumentFormatSelected$onfido_capture_sdk_core_release(documentFormat);
        setCaptureFrameContentDescriptionAndTitle(documentFormat);
    }

    private final void onDocumentCreated(DocumentCaptureScreen.DocumentCaptureResult.Completed result) throws Resources.NotFoundException {
        ErrorType currentCaptureFlowError$onfido_capture_sdk_core_release;
        if (this.isOnConfirmationStep) {
            hideLoading();
            finishWithResult(result);
            return;
        }
        showConfirmationStep();
        setConfirmationButtons(false);
        if (!result.getWithWarning() || (currentCaptureFlowError$onfido_capture_sdk_core_release = getViewModel$onfido_capture_sdk_core_release().getCurrentCaptureFlowError()) == null) {
            return;
        }
        showErrorInValidationBubble(ErrorTypeUtilsKt.mapErrorType(currentCaptureFlowError$onfido_capture_sdk_core_release));
    }

    private final void onFoldedFormatSelected() throws Resources.NotFoundException {
        DocumentCaptureViewModel viewModel$onfido_capture_sdk_core_release = getViewModel$onfido_capture_sdk_core_release();
        DocumentFormat documentFormat = DocumentFormat.FOLDED;
        viewModel$onfido_capture_sdk_core_release.onDocumentFormatSelected$onfido_capture_sdk_core_release(documentFormat);
        setCaptureFrameContentDescriptionAndTitle(documentFormat);
        updateOverlayView();
        OverlayMetrics overlayMetrics = this.overlayMetrics;
        if (overlayMetrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayMetrics");
            overlayMetrics = null;
        }
        setCaptureRegion(overlayMetrics.getVisibleCaptureRect());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onImageProcessingFinished() {
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new C06341(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onManualFallbackDelayFinished() {
        if (this.isOnConfirmationStep || !getViewModel$onfido_capture_sdk_core_release().shouldAutoCaptureDocument$onfido_capture_sdk_core_release()) {
            return;
        }
        getViewModel$onfido_capture_sdk_core_release().onManualFallbackDelayFinished$onfido_capture_sdk_core_release();
        setLiveValidationBubbleContent(new OnfidoCaptureValidationBubble.Content.Info(R.string.onfido_doc_capture_alert_manual_capture_title, Integer.valueOf(R.string.onfido_doc_capture_alert_manual_capture_detail)));
        setLiveValidationBubbleVisibilityCommand$default(this, new OnfidoCaptureValidationBubble.VisibilityCommand.Visible(new OnfidoCaptureValidationBubble.FocusType.AnnounceContent(true)), false, 2, null);
        OnfidoCaptureValidationBubble onfidoCaptureValidationBubble = getBinding().liveValidationBubble;
        onfidoCaptureValidationBubble.setState$onfido_capture_sdk_core_release(OnfidoCaptureValidationBubble.State.HARD_LOCK);
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new DocumentCaptureFragment$onManualFallbackDelayFinished$1$1(onfidoCaptureValidationBubble, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onNextFrame(Object frame) {
        DocumentCaptureViewModel viewModel$onfido_capture_sdk_core_release = getViewModel$onfido_capture_sdk_core_release();
        Intrinsics.checkNotNull(frame, "null cannot be cast to non-null type com.onfido.android.sdk.capture.internal.camera.OnfidoImage");
        OnfidoImage onfidoImage = (OnfidoImage) frame;
        OverlayMetrics overlayMetrics = this.overlayMetrics;
        if (overlayMetrics == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overlayMetrics");
            overlayMetrics = null;
        }
        viewModel$onfido_capture_sdk_core_release.onNextFrame$onfido_capture_sdk_core_release(onfidoImage, overlayMetrics);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onVideoCanceled() {
        if (getViewModel$onfido_capture_sdk_core_release().shouldRecordDocumentVideo$onfido_capture_sdk_core_release()) {
            hideVideoRecordingProgressBar();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onVideoCaptured(String filePath) {
        getViewModel$onfido_capture_sdk_core_release().onVideoCaptured$onfido_capture_sdk_core_release(filePath);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onVideoTimeoutExceeded() {
        getViewModel$onfido_capture_sdk_core_release().reset$onfido_capture_sdk_core_release();
        getViewModel$onfido_capture_sdk_core_release().trackVideoCaptureTimeout();
        this.lifecycleAwareDialog.show((56 & 1) != 0 ? null : Integer.valueOf(R.string.onfido_video_capture_prompt_title_timeout), R.string.onfido_video_capture_prompt_detail_timeout, (56 & 4) != 0 ? R.string.onfido_ok : R.string.onfido_video_capture_prompt_button_timeout, (56 & 8) != 0 ? null : null, (56 & 16) != 0 ? false : false, (Function1<? super DialogInterface, Unit>) ((56 & 32) != 0 ? null : null), (Function1<? super DialogInterface, Unit>) ((56 & 64) != 0 ? LifecycleAwareDialog.C07234.INSTANCE : new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.onVideoTimeoutExceeded.1
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
                DocumentCaptureFragment.this.onVideoTimeoutRetryClick(dialog);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onVideoTimeoutRetryClick(DialogInterface dialog) {
        dialog.dismiss();
        getViewModel$onfido_capture_sdk_core_release().trackVideoTimeoutRetryButtonClicked$onfido_capture_sdk_core_release();
        if (FragmentExtentionsKt.isAttached(this)) {
            requireActivity().onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openCaptureScreen() throws Resources.NotFoundException {
        getViewModel$onfido_capture_sdk_core_release().onCaptureScreenOpened$onfido_capture_sdk_core_release();
        hideLoading();
        getBinding().confirmationImage.setImageBitmap(null);
        this.isOnConfirmationStep = false;
        setConfirmationStepVisibility(false);
        getOverlayView().switchConfirmationMode(false);
        getBinding().overlayTextContainer.setDocumentOverlayText$onfido_capture_sdk_core_release(getViewModel$onfido_capture_sdk_core_release().getDocumentTypeUIModel$onfido_capture_sdk_core_release(), getViewModel$onfido_capture_sdk_core_release().getDocumentSide());
        showPostCaptureValidationBubble(false);
        getBinding().confirmationButtons.setListener$onfido_capture_sdk_core_release(this);
        setConfirmationButtonsForError(false);
        setColorsForCaptureScreen();
        CaptureLayoutAdjuster captureLayoutAdjuster = this.layoutAdjuster;
        if (captureLayoutAdjuster == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layoutAdjuster");
            captureLayoutAdjuster = null;
        }
        captureLayoutAdjuster.adjustLayoutParams(false);
        setCaptureFrameContentDescriptionAndTitle$default(this, null, 1, null);
        getViewModel$onfido_capture_sdk_core_release().prepareCaptureStart$onfido_capture_sdk_core_release(false, getOrientation());
        if (this.isOnConfirmationStep) {
            return;
        }
        startCamera();
    }

    private final void prepareColorsForConfirmationStep() throws Resources.NotFoundException {
        if (getViewModel$onfido_capture_sdk_core_release().isDarkModeEnabled$onfido_capture_sdk_core_release()) {
            OverlayView.setColorOutsideOverlay$default(getOverlayView(), getBackgroundColorConfirmationScreen(), false, 2, null);
        } else {
            updateColorsForConfirmationScreen();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processCaptureResult(DocumentCaptureScreen.DocumentCaptureResult result) throws Resources.NotFoundException {
        if (result instanceof DocumentCaptureScreen.DocumentCaptureResult.Completed) {
            onDocumentCreated((DocumentCaptureScreen.DocumentCaptureResult.Completed) result);
            return;
        }
        if (result instanceof DocumentCaptureScreen.DocumentCaptureResult.POACompleted) {
            hideLoading();
        } else if (!(result instanceof DocumentCaptureScreen.DocumentCaptureResult.Error)) {
            return;
        }
        finishWithResult(result);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void runAutoCaptureAccessibilityEvents() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C06381(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setButtonsForForceRetry() {
        getBinding().confirmationButtons.setWarningState(false, getViewModel$onfido_capture_sdk_core_release().getDocumentTypeUIModel$onfido_capture_sdk_core_release());
        setConfirmationButtonsForError(true);
    }

    private final void setCaptureFrameContentDescriptionAndTitle(DocumentFormat forFormat) {
        StringRepresentation captureFrameContentDescription$onfido_capture_sdk_core_release = getViewModel$onfido_capture_sdk_core_release().getCaptureFrameContentDescription$onfido_capture_sdk_core_release(forFormat);
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        String string = captureFrameContentDescription$onfido_capture_sdk_core_release.getString(contextRequireContext);
        getDummyAccessibilityBinding().dummyAccessibility.setContentDescription(string);
        requireActivity().setTitle(string);
    }

    static /* synthetic */ void setCaptureFrameContentDescriptionAndTitle$default(DocumentCaptureFragment documentCaptureFragment, DocumentFormat documentFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            documentFormat = null;
        }
        documentCaptureFragment.setCaptureFrameContentDescriptionAndTitle(documentFormat);
    }

    private final void setCaptureRegion(RectF rect) {
        showDocumentOverlay(rect);
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
    public final void setConfirmationButtons(boolean isWarning) {
        DocumentTypeUIModel documentTypeUIModel$onfido_capture_sdk_core_release = getViewModel$onfido_capture_sdk_core_release().getDocumentTypeUIModel$onfido_capture_sdk_core_release();
        getBinding().confirmationButtons.setWarningState(isWarning, documentTypeUIModel$onfido_capture_sdk_core_release);
        ConfirmationStepButtons confirmationButtons = getBinding().confirmationButtons;
        Intrinsics.checkNotNullExpressionValue(confirmationButtons, "confirmationButtons");
        ConfirmationStepButtons.setDocumentCaptureCopy$default(confirmationButtons, documentTypeUIModel$onfido_capture_sdk_core_release.getReadabilityConfirmationLabel(), documentTypeUIModel$onfido_capture_sdk_core_release.getReadabilityDiscardLabel(), false, 4, null);
    }

    private final void setConfirmationButtonsForError(boolean isError) {
        DocumentTypeUIModel documentTypeUIModel$onfido_capture_sdk_core_release = getViewModel$onfido_capture_sdk_core_release().getDocumentTypeUIModel$onfido_capture_sdk_core_release();
        getBinding().confirmationButtons.setDocumentErrorState(isError, documentTypeUIModel$onfido_capture_sdk_core_release.getReadabilityDiscardLabel(), documentTypeUIModel$onfido_capture_sdk_core_release.getReadabilityConfirmationLabel());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setConfirmationStepVisibility(boolean visible) {
        ViewUtil.setViewVisibility(getBinding().confirmationImage, visible);
        ViewUtil.setViewVisibility(getBinding().confirmationButtons, visible);
    }

    private final void setImagePreview(OnfidoImage onfidoImage) {
        Bitmap bitmap;
        getBinding().confirmationImage.setScaleX(1.0f);
        Bitmap bitmapDecodeScaledResource$default = ImageUtils.decodeScaledResource$default(getImageUtils$onfido_capture_sdk_core_release(), onfidoImage.getData(), getBinding().confirmationImage.getWidth(), getBinding().confirmationImage.getHeight(), null, null, 24, null);
        Drawable drawable = getBinding().confirmationImage.getDrawable();
        BitmapDrawable bitmapDrawable = drawable instanceof BitmapDrawable ? (BitmapDrawable) drawable : null;
        if (bitmapDrawable != null && (bitmap = bitmapDrawable.getBitmap()) != null) {
            bitmap.recycle();
        }
        getBinding().confirmationImage.setImageBitmap(bitmapDecodeScaledResource$default);
        updateConfirmationImageTranslationAndScale();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLiveValidationBubbleContent(OnfidoCaptureValidationBubble.Content content) {
        OnfidoCaptureValidationBubble liveValidationBubble = getBinding().liveValidationBubble;
        Intrinsics.checkNotNullExpressionValue(liveValidationBubble, "liveValidationBubble");
        OnfidoCaptureValidationBubble.setContent$onfido_capture_sdk_core_release$default(liveValidationBubble, content, false, 2, null);
    }

    private final void setLiveValidationBubbleVisibilityCommand(OnfidoCaptureValidationBubble.VisibilityCommand command, boolean shouldIgnoreLock) {
        getBinding().liveValidationBubble.setVisibilityCommand$onfido_capture_sdk_core_release(command, shouldIgnoreLock);
    }

    static /* synthetic */ void setLiveValidationBubbleVisibilityCommand$default(DocumentCaptureFragment documentCaptureFragment, OnfidoCaptureValidationBubble.VisibilityCommand visibilityCommand, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        documentCaptureFragment.setLiveValidationBubbleVisibilityCommand(visibilityCommand, z);
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

    private final void setupCaptureButton() {
        getCaptureButtonBinding().captureButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DocumentCaptureFragment.setupCaptureButton$lambda$6(this.f$0, view);
            }
        });
        setupConfirmationButtonsListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupCaptureButton$lambda$6(DocumentCaptureFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.capture(false);
    }

    private final void setupConfirmationButtonsListener() {
        getBinding().confirmationButtons.setListener$onfido_capture_sdk_core_release(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object setupObservers(Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C06392(null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    private final void setupOverlayView() throws Resources.NotFoundException {
        if (this._overlayView != null) {
            getBinding().contentLayout.removeView(getOverlayView());
        }
        OverlayView overlayViewInflateOverlayView = inflateOverlayView();
        overlayViewInflateOverlayView.setUp(CaptureType.DOCUMENT, this);
        OverlayView.setColorOutsideOverlay$default(overlayViewInflateOverlayView, getBackgroundColorCaptureScreen(), false, 2, null);
        this._overlayView = overlayViewInflateOverlayView;
        getBinding().overlayTextContainer.setDocumentOverlayText$onfido_capture_sdk_core_release(getViewModel$onfido_capture_sdk_core_release().getDocumentTypeUIModel$onfido_capture_sdk_core_release(), getViewModel$onfido_capture_sdk_core_release().getDocumentSide());
        getOverlayView().setIsProofOfAddress(this.isProofOfAddress);
        getBinding().contentLayout.addView(getOverlayView());
    }

    private final void setupToolbar() {
        ContextUtilsKt.requireToolbarHost(this).enableImmersiveFragment();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        changeStatusBarColor(ContextUtilsKt.colorFromAttr(contextRequireContext, android.R.attr.colorPrimaryDark));
    }

    private final void setupUiElements(View view) {
        this._binding = OnfidoFragmentDocumentCaptureBinding.bind(view);
        inflateCaptureButton();
        inflateDummyAccessibilityView();
        initDocumentTypeUIModel();
        initLayoutAdjuster();
        initValidationBubbleDelegate();
        setConfirmationStepVisibility(false);
        DocumentTypeUIModel documentTypeUIModel$onfido_capture_sdk_core_release = getViewModel$onfido_capture_sdk_core_release().getDocumentTypeUIModel$onfido_capture_sdk_core_release();
        getBinding().confirmationButtons.setDocumentCapture$onfido_capture_sdk_core_release(documentTypeUIModel$onfido_capture_sdk_core_release.getReadabilityDiscardLabel(), documentTypeUIModel$onfido_capture_sdk_core_release.getReadabilityConfirmationLabel());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCaptureErrorDialog() {
        hideLoading();
        showErrorMessage(R.string.onfido_generic_error_title, R.string.onfido_generic_error_doc_capture, new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.showCaptureErrorDialog.1
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
                DocumentCaptureFragment.this.getViewModel$onfido_capture_sdk_core_release().onCaptureErrorConfirmed$onfido_capture_sdk_core_release();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showConfirmationStep() throws Resources.NotFoundException {
        this.isOnConfirmationStep = true;
        hideLoading();
        this.wasConfirmationNotShown = false;
        getViewModel$onfido_capture_sdk_core_release().trackDocumentConfirmationStep$onfido_capture_sdk_core_release();
        prepareColorsForConfirmationStep();
        setConfirmationStepVisibility(true);
        if (getBinding().confirmationButtons.getIsConfirmationButtonsHorizontal()) {
            getBinding().confirmationButtons.enableAdaptableHorizontalButtonHeight();
            getBinding().confirmationButtons.forceInnerButtonsMeasurement();
        }
        getBinding().overlayTextContainer.setConfirmationOverlayText(CaptureType.DOCUMENT, getViewModel$onfido_capture_sdk_core_release().getDocumentTypeUIModel$onfido_capture_sdk_core_release());
        CaptureLayoutAdjuster captureLayoutAdjuster = this.layoutAdjuster;
        if (captureLayoutAdjuster == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layoutAdjuster");
            captureLayoutAdjuster = null;
        }
        captureLayoutAdjuster.adjustLayoutParams(true);
        getOverlayView().switchConfirmationMode(true);
        OverlayTextView overlayTextContainer = getBinding().overlayTextContainer;
        Intrinsics.checkNotNullExpressionValue(overlayTextContainer, "overlayTextContainer");
        ViewExtensionsKt.toVisible$default(overlayTextContainer, false, 1, null);
        int i = R.string.onfido_doc_confirmation_image_accessibility;
        View dummyAccessibility = getDummyAccessibilityBinding().dummyAccessibility;
        Intrinsics.checkNotNullExpressionValue(dummyAccessibility, "dummyAccessibility");
        ViewExtensionsKt.setContentDescription(dummyAccessibility, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDocumentFormatSelectionDialog(int dialogTitle) {
        final BottomSheetDialog documentFormatBottomDialog = getDocumentFormatBottomDialog();
        OnfidoViewDocumentFormatSelectionBinding onfidoViewDocumentFormatSelectionBindingInflate = OnfidoViewDocumentFormatSelectionBinding.inflate(documentFormatBottomDialog.getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(onfidoViewDocumentFormatSelectionBindingInflate, "inflate(...)");
        documentFormatBottomDialog.setCancelable(false);
        documentFormatBottomDialog.setContentView(onfidoViewDocumentFormatSelectionBindingInflate.getRoot());
        onfidoViewDocumentFormatSelectionBindingInflate.title.setText(dialogTitle);
        onfidoViewDocumentFormatSelectionBindingInflate.cardFormat.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DocumentCaptureFragment.showDocumentFormatSelectionDialog$lambda$14$lambda$13$lambda$11(documentFormatBottomDialog, this, view);
            }
        });
        onfidoViewDocumentFormatSelectionBindingInflate.foldedFormat.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Resources.NotFoundException {
                DocumentCaptureFragment.showDocumentFormatSelectionDialog$lambda$14$lambda$13$lambda$12(documentFormatBottomDialog, this, view);
            }
        });
        documentFormatBottomDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDocumentFormatSelectionDialog$lambda$14$lambda$13$lambda$11(BottomSheetDialog this_with, DocumentCaptureFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this_with.dismiss();
        this$0.onCardFormatSelected();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDocumentFormatSelectionDialog$lambda$14$lambda$13$lambda$12(BottomSheetDialog this_with, DocumentCaptureFragment this$0, View view) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this_with.dismiss();
        this$0.onFoldedFormatSelected();
    }

    private final void showDocumentOverlay(RectF rect) {
        ShapeableImageView italianIDOverlay;
        boolean z = requireArguments().getBoolean(KEY_FRONT_SIDE);
        DocumentCaptureViewModel viewModel$onfido_capture_sdk_core_release = getViewModel$onfido_capture_sdk_core_release();
        ShapeableImageView frenchDLOverlay = getBinding().frenchDLOverlay;
        Intrinsics.checkNotNullExpressionValue(frenchDLOverlay, "frenchDLOverlay");
        boolean zShouldShowFrenchDLOverlay$onfido_capture_sdk_core_release = viewModel$onfido_capture_sdk_core_release.shouldShowFrenchDLOverlay$onfido_capture_sdk_core_release(z, ViewExtensionsKt.isGone(frenchDLOverlay));
        DocumentCaptureViewModel viewModel$onfido_capture_sdk_core_release2 = getViewModel$onfido_capture_sdk_core_release();
        ShapeableImageView germanDLOverlay = getBinding().germanDLOverlay;
        Intrinsics.checkNotNullExpressionValue(germanDLOverlay, "germanDLOverlay");
        boolean zShouldShowGermanDLOverlay$onfido_capture_sdk_core_release = viewModel$onfido_capture_sdk_core_release2.shouldShowGermanDLOverlay$onfido_capture_sdk_core_release(z, ViewExtensionsKt.isGone(germanDLOverlay));
        DocumentCaptureViewModel viewModel$onfido_capture_sdk_core_release3 = getViewModel$onfido_capture_sdk_core_release();
        ShapeableImageView italianIDOverlay2 = getBinding().italianIDOverlay;
        Intrinsics.checkNotNullExpressionValue(italianIDOverlay2, "italianIDOverlay");
        boolean zShouldShowItalianIDOverlay$onfido_capture_sdk_core_release = viewModel$onfido_capture_sdk_core_release3.shouldShowItalianIDOverlay$onfido_capture_sdk_core_release(z, ViewExtensionsKt.isGone(italianIDOverlay2));
        DocumentCaptureViewModel viewModel$onfido_capture_sdk_core_release4 = getViewModel$onfido_capture_sdk_core_release();
        ShapeableImageView italianIDOverlay3 = getBinding().italianIDOverlay;
        Intrinsics.checkNotNullExpressionValue(italianIDOverlay3, "italianIDOverlay");
        boolean zShouldShowSouthAfricanIDOverlay$onfido_capture_sdk_core_release = viewModel$onfido_capture_sdk_core_release4.shouldShowSouthAfricanIDOverlay$onfido_capture_sdk_core_release(z, ViewExtensionsKt.isGone(italianIDOverlay3));
        ShapeableImageView passportOverlay = getBinding().passportOverlay;
        Intrinsics.checkNotNullExpressionValue(passportOverlay, "passportOverlay");
        boolean z2 = !ViewExtensionsKt.isGone(passportOverlay) && getViewModel$onfido_capture_sdk_core_release().shouldShowInitialOverlay$onfido_capture_sdk_core_release() && getViewModel$onfido_capture_sdk_core_release().shouldAutoCaptureDocument$onfido_capture_sdk_core_release();
        if (zShouldShowFrenchDLOverlay$onfido_capture_sdk_core_release) {
            italianIDOverlay = getBinding().frenchDLOverlay;
            Intrinsics.checkNotNullExpressionValue(italianIDOverlay, "frenchDLOverlay");
        } else if (zShouldShowGermanDLOverlay$onfido_capture_sdk_core_release) {
            italianIDOverlay = getBinding().germanDLOverlay;
            Intrinsics.checkNotNullExpressionValue(italianIDOverlay, "germanDLOverlay");
        } else if (zShouldShowItalianIDOverlay$onfido_capture_sdk_core_release || zShouldShowSouthAfricanIDOverlay$onfido_capture_sdk_core_release) {
            italianIDOverlay = getBinding().italianIDOverlay;
            Intrinsics.checkNotNullExpressionValue(italianIDOverlay, "italianIDOverlay");
        } else {
            if (!z2) {
                return;
            }
            italianIDOverlay = getBinding().passportOverlay;
            Intrinsics.checkNotNullExpressionValue(italianIDOverlay, "passportOverlay");
        }
        ImageUtilsExtKt.showOverlay(italianIDOverlay, rect);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showErrorInValidationBubble(ErrorDescriptor descriptor) {
        OnfidoCaptureValidationBubble onfidoCaptureValidationBubble = getBinding().postCaptureValidationBubble;
        Intrinsics.checkNotNull(onfidoCaptureValidationBubble);
        OnfidoCaptureValidationBubble.setContent$onfido_capture_sdk_core_release$default(onfidoCaptureValidationBubble, new OnfidoCaptureValidationBubble.Content.Error(descriptor.getTitle(), descriptor.getSubtitle()), false, 2, null);
        OnfidoCaptureValidationBubble.setVisibilityCommand$onfido_capture_sdk_core_release$default(onfidoCaptureValidationBubble, new OnfidoCaptureValidationBubble.VisibilityCommand.Visible(new OnfidoCaptureValidationBubble.FocusType.AnnounceContent(false, 1, null)), false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showErrorMessage(int titleResId, int messageResId, Function1<? super DialogInterface, Unit> listener) {
        this.lifecycleAwareDialog.show((56 & 1) != 0 ? null : Integer.valueOf(titleResId), messageResId, (56 & 4) != 0 ? R.string.onfido_ok : 0, (56 & 8) != 0 ? null : null, (56 & 16) != 0 ? false : false, (Function1<? super DialogInterface, Unit>) ((56 & 32) != 0 ? null : null), (Function1<? super DialogInterface, Unit>) ((56 & 64) != 0 ? LifecycleAwareDialog.C07234.INSTANCE : listener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLoading(LoadingFragment.Companion.DialogMode dialogMode) {
        if (FragmentExtentionsKt.isAttached(this)) {
            FragmentActivity fragmentActivityRequireActivity = requireActivity();
            Intrinsics.checkNotNull(fragmentActivityRequireActivity, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.BaseActivity");
            ((BaseActivity) fragmentActivityRequireActivity).showLoadingDialog$onfido_capture_sdk_core_release(dialogMode);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPostCaptureValidationBubble(boolean shouldShow) {
        OnfidoCaptureValidationBubble onfidoCaptureValidationBubble = getBinding().postCaptureValidationBubble;
        Intrinsics.checkNotNull(onfidoCaptureValidationBubble);
        OnfidoCaptureValidationBubble.setVisibilityCommand$onfido_capture_sdk_core_release$default(onfidoCaptureValidationBubble, shouldShow ? new OnfidoCaptureValidationBubble.VisibilityCommand.Visible(new OnfidoCaptureValidationBubble.FocusType.AnnounceContent(false, 1, null)) : OnfidoCaptureValidationBubble.VisibilityCommand.Gone.INSTANCE, false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showVideoRecordingProgressBar() {
        ImageView flipArrow = getBinding().flipArrow;
        Intrinsics.checkNotNullExpressionValue(flipArrow, "flipArrow");
        flipArrow.setVisibility(8);
        LinearLayout root = getBinding().videoRecordingContainer.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        ViewExtensionsKt.toVisible$default(root, false, 1, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C06411(null), 3, null);
    }

    private final void startCamera() {
        CameraFactory cameraFactory$onfido_capture_sdk_core_release = getCameraFactory$onfido_capture_sdk_core_release();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        CameraSourcePreview cameraSourcePreview = getBinding().cameraViewCamera1;
        PreviewView previewView = getBinding().cameraViewCameraX;
        CaptureType captureType = CaptureType.DOCUMENT;
        OverlayView overlayView = getOverlayView();
        Intrinsics.checkNotNull(viewLifecycleOwner);
        final OnfidoCamera onfidoCameraCreate = cameraFactory$onfido_capture_sdk_core_release.create(viewLifecycleOwner, cameraSourcePreview, previewView, overlayView, captureType);
        onfidoCameraCreate.start(OnfidoCamera.CameraFacing.BACK, new Function1<OnfidoCamera.CameraStatus, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.startCamera.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    DocumentCaptureFragment.this.onfidoCamera = onfidoCameraCreate;
                    DocumentCaptureFragment.this.onCameraStarted();
                } else {
                    if (Intrinsics.areEqual(cameraStatus, OnfidoCamera.CameraStatus.NotAvailable.INSTANCE)) {
                        DocumentCaptureFragment.this.onCameraUnavailable();
                        return;
                    }
                    if (Intrinsics.areEqual(cameraStatus, OnfidoCamera.CameraStatus.NotFound.INSTANCE)) {
                        DocumentCaptureFragment.this.onCameraNotFound();
                        return;
                    }
                    if (cameraStatus instanceof OnfidoCamera.CameraStatus.Failed) {
                        DocumentCaptureFragment documentCaptureFragment = DocumentCaptureFragment.this;
                        String message = ((OnfidoCamera.CameraStatus.Failed) cameraStatus).getError().getMessage();
                        if (message == null) {
                            message = "";
                        }
                        documentCaptureFragment.finishWithException(new UnknownCameraException(message));
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startDocumentVideoRecording() {
        getOverlayView().onDocumentVideoRecordStarted();
        startVideoRecording();
    }

    private final void startVideoRecording() {
        FileUtils fileUtils = FileUtils.INSTANCE;
        File cacheDir = requireContext().getCacheDir();
        Intrinsics.checkNotNullExpressionValue(cacheDir, "getCacheDir(...)");
        fileUtils.deleteFilesWithPrefixFromFolder(cacheDir, VideoCaptureConfig.DEFAULT_FILE_NAME_PREFIX);
        OnfidoCamera onfidoCamera = this.onfidoCamera;
        if (onfidoCamera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoCamera");
            onfidoCamera = null;
        }
        this.recorder = onfidoCamera.takeVideo(new Function1<OnfidoCamera.VideoCaptureEvent, Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.startVideoRecording.1
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
                    DocumentCaptureFragment.this.onVideoCanceled();
                    return;
                }
                if (event instanceof OnfidoCamera.VideoCaptureEvent.Error) {
                    DocumentCaptureFragment.this.getViewModel$onfido_capture_sdk_core_release().onErrorVideoRecording$onfido_capture_sdk_core_release();
                    return;
                }
                if (event instanceof OnfidoCamera.VideoCaptureEvent.Recorded) {
                    DocumentCaptureFragment.this.onVideoCaptured(((OnfidoCamera.VideoCaptureEvent.Recorded) event).getFilePath());
                    return;
                }
                if (Intrinsics.areEqual(event, OnfidoCamera.VideoCaptureEvent.Started.INSTANCE)) {
                    DocumentCaptureViewModel viewModel$onfido_capture_sdk_core_release = DocumentCaptureFragment.this.getViewModel$onfido_capture_sdk_core_release();
                    final DocumentCaptureFragment documentCaptureFragment = DocumentCaptureFragment.this;
                    viewModel$onfido_capture_sdk_core_release.startDocumentVideoRecordingTimer$onfido_capture_sdk_core_release(new Function0<Boolean>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.startVideoRecording.1.1
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        /* JADX WARN: Removed duplicated region for block: B:7:0x0010  */
                        @Override // kotlin.jvm.functions.Function0
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public final java.lang.Boolean invoke() {
                            /*
                                r2 = this;
                                com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment r0 = r1
                                com.onfido.android.sdk.capture.internal.camera.OnfidoCamera$VideoRecorder r0 = com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.access$getRecorder$p(r0)
                                if (r0 == 0) goto L10
                                boolean r0 = r0.getHasValidRecording()
                                r1 = 1
                                if (r0 != r1) goto L10
                                goto L11
                            L10:
                                r1 = 0
                            L11:
                                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.C06431.C01511.invoke():java.lang.Boolean");
                        }
                    });
                } else if (Intrinsics.areEqual(event, OnfidoCamera.VideoCaptureEvent.Timeout.INSTANCE)) {
                    DocumentCaptureFragment.this.onVideoTimeoutExceeded();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopDocumentVideoRecording() {
        getViewModel$onfido_capture_sdk_core_release().onVideoRecordingStopped$onfido_capture_sdk_core_release();
        OnfidoCamera.VideoRecorder videoRecorder = this.recorder;
        if (videoRecorder != null) {
            videoRecorder.finish();
        }
        hideVideoRecordingProgressBar();
        ZoomImageView confirmationImage = getBinding().confirmationImage;
        Intrinsics.checkNotNullExpressionValue(confirmationImage, "confirmationImage");
        ViewExtensionsKt.toVisible$default(confirmationImage, false, 1, null);
        getOverlayView().onDocumentVideoRecordFinished();
        getOverlayView().inflateDocumentDetectionTick(new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.stopDocumentVideoRecording.1
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
                DocumentCaptureFragment.this.runAutoCaptureAccessibilityEvents();
            }
        });
    }

    private final void updateColorsForConfirmationScreen() throws Resources.NotFoundException {
        ContextUtilsKt.requireToolbarHost(this).setToolbarColor(R.attr.onfidoColorToolbarBackground, R.attr.onfidoColorContentToolbarTitle, R.attr.onfidoColorContentToolbarSubtitle);
        OverlayView.setColorOutsideOverlay$default(getOverlayView(), getBackgroundColorConfirmationScreen(), false, 2, null);
        getBinding().watermark.setDarkBackground();
    }

    private final void updateConfirmationImageTranslationAndScale() {
        if (getViewModel$onfido_capture_sdk_core_release().isFourByThreeEnabled$onfido_capture_sdk_core_release()) {
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

    private final void updateOverlayView() throws Resources.NotFoundException {
        OverlayView overlayView = getOverlayView();
        OverlayView overlayViewInflateOverlayView = inflateOverlayView();
        CaptureType captureType = CaptureType.DOCUMENT;
        overlayViewInflateOverlayView.setUp(captureType, this);
        overlayViewInflateOverlayView.setColorOutsideOverlay(getBackgroundColorCaptureScreen(), false);
        this._overlayView = overlayViewInflateOverlayView;
        OnfidoFragmentDocumentCaptureBinding binding = getBinding();
        binding.overlayTextContainer.setCaptureOverlayText$onfido_capture_sdk_core_release(captureType, getViewModel$onfido_capture_sdk_core_release().getDocumentTypeUIModel$onfido_capture_sdk_core_release(), getViewModel$onfido_capture_sdk_core_release().getDocumentSide());
        binding.contentLayout.removeView(overlayView);
        binding.contentLayout.addView(getOverlayView());
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

    public final RuntimePermissionsManager getPermissionsManager$onfido_capture_sdk_core_release() {
        RuntimePermissionsManager runtimePermissionsManager = this.permissionsManager;
        if (runtimePermissionsManager != null) {
            return runtimePermissionsManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("permissionsManager");
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

    public final DocumentCaptureViewModel getViewModel$onfido_capture_sdk_core_release() {
        return (DocumentCaptureViewModel) this.viewModel.getValue();
    }

    public final Provider<DocumentCaptureViewModel> getViewModelProvider$onfido_capture_sdk_core_release() {
        Provider<DocumentCaptureViewModel> provider = this.viewModelProvider;
        if (provider != null) {
            return provider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelProvider");
        return null;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.ConfirmationStepButtons.Listener
    public void onCaptureConfirmed() {
        getViewModel$onfido_capture_sdk_core_release().onCaptureConfirmed$onfido_capture_sdk_core_release();
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.ConfirmationStepButtons.Listener
    public void onCaptureDiscarded() {
        getViewModel$onfido_capture_sdk_core_release().onCaptureDiscarded$onfido_capture_sdk_core_release();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
        this._overlayView = null;
        this._captureButtonBinding = null;
        this._dummyAccessibilityBinding = null;
        this.isCameraViewInitialised = false;
        this.recorder = null;
        this.isOnConfirmationStep = false;
        this.wasConfirmationNotShown = true;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.OverlayView.Listener
    public void onOverlayMetrics(OverlayMetrics overlayMetrics) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(overlayMetrics, "overlayMetrics");
        this.overlayMetrics = overlayMetrics;
        getViewModel$onfido_capture_sdk_core_release().onOverlayMetrics$onfido_capture_sdk_core_release(overlayMetrics);
        RectF visibleCaptureRect = overlayMetrics.getVisibleCaptureRect();
        CaptureLayoutAdjuster captureLayoutAdjuster = this.layoutAdjuster;
        if (captureLayoutAdjuster == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layoutAdjuster");
            captureLayoutAdjuster = null;
        }
        captureLayoutAdjuster.adjustLayoutParams(this.isOnConfirmationStep);
        ValidationBubblesOffsetDelegate validationBubblesOffsetDelegate = this.validationBubbleOffsetDelegate;
        if (validationBubblesOffsetDelegate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("validationBubbleOffsetDelegate");
            validationBubblesOffsetDelegate = null;
        }
        validationBubblesOffsetDelegate.onCaptureRegionSet(visibleCaptureRect);
        setCaptureRegion(visibleCaptureRect);
        setVideoRecordingIndicatorMargin(visibleCaptureRect);
        if (getViewModel$onfido_capture_sdk_core_release().shouldHideManualDocumentCaptureButton$onfido_capture_sdk_core_release() && this.wasConfirmationNotShown) {
            setLiveValidationBubbleVisibilityCommand$default(this, OnfidoCaptureValidationBubble.VisibilityCommand.Invisible.INSTANCE, false, 2, null);
        }
        adjustDummyAccessibilityView(overlayMetrics.getVisibleCaptureRect());
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() throws Resources.NotFoundException {
        super.onResume();
        if (!getPermissionsManager$onfido_capture_sdk_core_release().hasPermission("android.permission.CAMERA")) {
            FragmentKt.setFragmentResult(this, OnfidoActivity.KEY_RESULT_CAPTURE_MISSING_PERMISSIONS, BundleKt.bundleOf(TuplesKt.to(OnfidoActivity.KEY_CAPTURE_MISSING_PERMISSIONS_CAPTURE_DATA, getViewModel$onfido_capture_sdk_core_release().getDocumentData$onfido_capture_sdk_core_release())));
            return;
        }
        if (!this.isOnConfirmationStep) {
            setupOverlayView();
            setColorsForCaptureScreen();
        }
        setupCaptureButton();
        getViewModel$onfido_capture_sdk_core_release().onViewResumed$onfido_capture_sdk_core_release();
        View view = getView();
        if (view == null) {
            return;
        }
        view.setImportantForAccessibility(1);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        setupToolbar();
        getViewModel$onfido_capture_sdk_core_release().prepareCaptureStart$onfido_capture_sdk_core_release(this.wasConfirmationNotShown, getOrientation());
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        ContextUtilsKt.requireToolbarHost(this).disableImmersiveFragment();
        ContextUtilsKt.requireToolbarHost(this).resetToolbar();
        getDocumentFormatBottomDialog().hide();
        getViewModel$onfido_capture_sdk_core_release().reset$onfido_capture_sdk_core_release();
        if (this.isCameraViewInitialised) {
            OverlayView.resetFaceDetectedState$default(getOverlayView(), false, false, false, 2, null);
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
        Serializable serializable = requireArguments().getSerializable(KEY_DATA_BUNDLE);
        CaptureStepDataBundle captureStepDataBundle = serializable instanceof CaptureStepDataBundle ? (CaptureStepDataBundle) serializable : null;
        if (captureStepDataBundle == null) {
            throw new IllegalArgumentException("key_data_bundle parameter is missing".toString());
        }
        Parcelable parcelable = requireArguments().getParcelable(KEY_NFC_ARGUMENTS);
        NfcArguments nfcArguments = parcelable instanceof NfcArguments ? (NfcArguments) parcelable : null;
        if (nfcArguments == null) {
            throw new IllegalArgumentException("key_nfc_arguments parameter is missing".toString());
        }
        getViewModel$onfido_capture_sdk_core_release().setCaptureData$onfido_capture_sdk_core_release(captureStepDataBundle, nfcArguments);
        getViewModel$onfido_capture_sdk_core_release().setDocumentSide$onfido_capture_sdk_core_release(requireArguments().getBoolean(KEY_FRONT_SIDE, false) ? DocSide.FRONT : DocSide.BACK);
        this.isProofOfAddress = requireArguments().getBoolean(KEY_PROOF_OF_ADDRESS, false);
        getViewModel$onfido_capture_sdk_core_release().setProofOfAddress$onfido_capture_sdk_core_release(this.isProofOfAddress);
        DocumentCaptureViewModel viewModel$onfido_capture_sdk_core_release = getViewModel$onfido_capture_sdk_core_release();
        File filesDir = requireActivity().getFilesDir();
        Intrinsics.checkNotNullExpressionValue(filesDir, "getFilesDir(...)");
        viewModel$onfido_capture_sdk_core_release.setCapturedFilesDir$onfido_capture_sdk_core_release(filesDir);
        setupUiElements(view);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback() { // from class: com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment.onViewCreated.1
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() throws Resources.NotFoundException {
                DocumentCaptureFragment.this.getViewModel$onfido_capture_sdk_core_release().trackCaptureBackButtonClicked$onfido_capture_sdk_core_release();
                if (!DocumentCaptureFragment.this.isOnConfirmationStep) {
                    setEnabled(false);
                    DocumentCaptureFragment.this.requireActivity().onBackPressed();
                } else {
                    DocumentCaptureFragment.this.setConfirmationStepVisibility(false);
                    DocumentCaptureFragment.this.isOnConfirmationStep = false;
                    DocumentCaptureFragment.this.openCaptureScreen();
                }
            }
        });
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), null, null, new C06372(null), 3, null);
    }

    @Override // com.onfido.android.sdk.capture.utils.WorkflowToolbarUpdateListener
    public void onWorkflowToolbarUpdated() throws Resources.NotFoundException {
        if (this.isOnConfirmationStep) {
            updateColorsForConfirmationScreen();
        } else {
            setColorsForCaptureScreen();
        }
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

    public final void setPermissionsManager$onfido_capture_sdk_core_release(RuntimePermissionsManager runtimePermissionsManager) {
        Intrinsics.checkNotNullParameter(runtimePermissionsManager, "<set-?>");
        this.permissionsManager = runtimePermissionsManager;
    }

    public final void setVibratorService$onfido_capture_sdk_core_release(VibratorService vibratorService) {
        Intrinsics.checkNotNullParameter(vibratorService, "<set-?>");
        this.vibratorService = vibratorService;
    }

    public final void setViewModelProvider$onfido_capture_sdk_core_release(Provider<DocumentCaptureViewModel> provider) {
        Intrinsics.checkNotNullParameter(provider, "<set-?>");
        this.viewModelProvider = provider;
    }
}
