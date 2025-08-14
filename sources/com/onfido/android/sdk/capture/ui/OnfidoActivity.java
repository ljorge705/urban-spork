package com.onfido.android.sdk.capture.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import com.clevertap.android.sdk.leanplum.Constants;
import com.google.android.gms.security.ProviderInstaller;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.FlowConfig;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.ExitCode;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.common.permissions.PermissionsManagementFragment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.databinding.OnfidoActivityOnfidoBinding;
import com.onfido.android.sdk.capture.errors.MissingOnfidoConfigException;
import com.onfido.android.sdk.capture.errors.OnfidoException;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.flow.FlowStepDirection;
import com.onfido.android.sdk.capture.flow.NfcArguments;
import com.onfido.android.sdk.capture.internal.OnfidoConstants;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.model.UploadedArtifact;
import com.onfido.android.sdk.capture.internal.navigation.navigator.FragmentNavigationManager;
import com.onfido.android.sdk.capture.internal.navigation.screens.DocumentSelectionScreen;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.internal.performance.domain.PerformanceAnalyticsScreen;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.ui.OnfidoPresenter;
import com.onfido.android.sdk.capture.ui.camera.CaptureActivity;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureScreen;
import com.onfido.android.sdk.capture.ui.camera.exception.CameraException;
import com.onfido.android.sdk.capture.ui.camera.exception.InvalidCertificateException;
import com.onfido.android.sdk.capture.ui.camera.exception.TokenExpiredException;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationFragment;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessIntroFragment;
import com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureScreen;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessPerformedChallenges;
import com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureScreen;
import com.onfido.android.sdk.capture.ui.camera.util.IntentHelper;
import com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment;
import com.onfido.android.sdk.capture.ui.faceintro.FaceIntroFragment;
import com.onfido.android.sdk.capture.ui.finalscreen.FinalScreenFragment;
import com.onfido.android.sdk.capture.ui.nfc.NfcDeviceNotSupportedFragment;
import com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment;
import com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties;
import com.onfido.android.sdk.capture.ui.nfc.permission.NfcPermissionFragment;
import com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions;
import com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostFragment;
import com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment;
import com.onfido.android.sdk.capture.upload.Captures;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.LifecycleAwareDialog;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
import com.onfido.android.sdk.capture.utils.ToolbarExtensionsKt$performActionOnTitleTextView$$inlined$filterIsInstance$1;
import com.onfido.android.sdk.capture.utils.ToolbarHost;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.android.sdk.capture.utils.listeners.ActivityTitleSetterFragmentLifecycleCallbacks;
import com.onfido.hosted.web.module.HostedWebModuleFragment;
import com.onfido.hosted.web.module.model.HostedWebModuleModuleInfo;
import com.onfido.javax.inject.Inject;
import io.sentry.SentryEvent;
import io.sentry.protocol.Request;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLContext;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import okhttp3.TlsVersion;
import okhttp3.internal.ws.WebSocketProtocol;

@Metadata(d1 = {"\u0000¬\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000 ®\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002®\u0001B\u0005¢\u0006\u0002\u0010\u0005J\b\u00106\u001a\u000207H\u0016J\b\u00108\u001a\u00020\nH\u0012J\b\u00109\u001a\u000207H\u0016J\b\u0010:\u001a\u000207H\u0016J\b\u0010;\u001a\u000207H\u0016J\u0010\u0010<\u001a\u0002072\u0006\u0010=\u001a\u00020>H\u0016J\u001d\u0010?\u001a\u0002072\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\nH\u0010¢\u0006\u0002\bCJ\u0012\u0010D\u001a\u0002072\b\u0010B\u001a\u0004\u0018\u00010\nH\u0012J\u001a\u0010E\u001a\u0002072\b\u0010F\u001a\u0004\u0018\u00010\n2\u0006\u0010G\u001a\u00020AH\u0012J\u001a\u0010H\u001a\u0002072\u0006\u0010G\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010\nH\u0012J\b\u0010I\u001a\u000207H\u0016J\b\u0010J\u001a\u000207H\u0016J\b\u0010K\u001a\u000207H\u0012J\u0012\u0010L\u001a\u0002072\b\u0010M\u001a\u0004\u0018\u00010NH\u0012J$\u0010O\u001a\u0002072\f\u0010P\u001a\b\u0012\u0004\u0012\u0002070Q2\f\u0010R\u001a\b\u0012\u0004\u0012\u0002070QH\u0012J\u0010\u0010S\u001a\u00020T2\u0006\u0010B\u001a\u00020\nH\u0012J\u0018\u0010U\u001a\u0002072\u0006\u0010G\u001a\u00020A2\u0006\u0010@\u001a\u00020VH\u0012J\b\u0010W\u001a\u000207H\u0017J\u0012\u0010X\u001a\u0002072\b\u0010M\u001a\u0004\u0018\u00010NH\u0014J\b\u0010Y\u001a\u000207H\u0014J\u0010\u0010Z\u001a\u0002072\u0006\u0010B\u001a\u00020\nH\u0012J\u0014\u0010[\u001a\u0002072\n\u0010\\\u001a\u00060]j\u0002`^H\u0016J\u0018\u0010_\u001a\u0002072\u000e\u0010`\u001a\n\u0012\u0006\b\u0001\u0012\u00020b0aH\u0016J\b\u0010c\u001a\u000207H\u0016J\u0010\u0010d\u001a\u00020T2\u0006\u0010e\u001a\u00020fH\u0016J\u0010\u0010g\u001a\u0002072\u0006\u0010h\u001a\u00020NH\u0014J\b\u0010i\u001a\u000207H\u0014J\b\u0010j\u001a\u000207H\u0014J\r\u0010k\u001a\u000207H\u0010¢\u0006\u0002\blJ\b\u0010m\u001a\u000207H\u0016J.\u0010n\u001a\u0002072\u0006\u0010o\u001a\u00020p2\u0006\u0010q\u001a\u00020r2\n\b\u0002\u0010s\u001a\u0004\u0018\u00010t2\b\b\u0002\u0010u\u001a\u00020TH\u0012J\u0012\u0010v\u001a\u0002072\b\u00102\u001a\u0004\u0018\u000103H\u0016J\b\u0010w\u001a\u000207H\u0012J\u001a\u0010x\u001a\u0002072\b\u0010M\u001a\u0004\u0018\u00010N2\u0006\u0010y\u001a\u00020zH\u0012J\u0010\u0010{\u001a\u0002072\u0006\u0010q\u001a\u00020rH\u0016J)\u0010|\u001a\u0002072\u0006\u0010q\u001a\u00020r2\u0006\u0010}\u001a\u00020t2\u0006\u0010~\u001a\u00020\u007f2\u0007\u0010\u0080\u0001\u001a\u00020TH\u0016J\u0011\u0010\u0081\u0001\u001a\u0002072\u0006\u0010q\u001a\u00020rH\u0016J&\u0010\u0082\u0001\u001a\u0002072\u0007\u0010\u0083\u0001\u001a\u00020T2\b\u0010\u0084\u0001\u001a\u00030\u0085\u00012\b\u0010\u0086\u0001\u001a\u00030\u0087\u0001H\u0016J\u0015\u0010\u0088\u0001\u001a\u0002072\n\u0010\u0089\u0001\u001a\u0005\u0018\u00010\u008a\u0001H\u0016J\u0011\u0010\u008b\u0001\u001a\u0002072\u0006\u0010q\u001a\u00020rH\u0016J\t\u0010\u008c\u0001\u001a\u000207H\u0016J\u001a\u0010\u008d\u0001\u001a\u0002072\u0007\u0010\u008e\u0001\u001a\u00020T2\u0006\u0010q\u001a\u00020rH\u0016J\u0012\u0010\u008f\u0001\u001a\u0002072\u0007\u0010\u0090\u0001\u001a\u00020tH\u0016J\t\u0010\u0091\u0001\u001a\u000207H\u0016J#\u0010\u0092\u0001\u001a\u0002072\u0007\u0010\u0093\u0001\u001a\u00020t2\u0007\u0010\u0094\u0001\u001a\u00020t2\u0006\u0010q\u001a\u00020rH\u0016J\u001d\u0010\u0095\u0001\u001a\u0002072\n\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0097\u00012\u0006\u0010q\u001a\u00020rH\u0016J\t\u0010\u0098\u0001\u001a\u000207H\u0016J<\u0010\u0099\u0001\u001a\u0002072\n\u0010\u0089\u0001\u001a\u0005\u0018\u00010\u008a\u00012\n\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u009b\u00012\b\u0010\u009c\u0001\u001a\u00030\u009d\u00012\u0006\u0010q\u001a\u00020r2\u0007\u0010\u009e\u0001\u001a\u00020TH\u0016J\u0011\u0010\u009f\u0001\u001a\u0002072\u0006\u0010q\u001a\u00020rH\u0016J\u001b\u0010 \u0001\u001a\u0002072\b\u0010\u0084\u0001\u001a\u00030\u0085\u00012\u0006\u0010q\u001a\u00020rH\u0016J\u0011\u0010¡\u0001\u001a\u0002072\u0006\u0010q\u001a\u00020rH\u0016J\u001b\u0010¢\u0001\u001a\u0002072\b\u0010£\u0001\u001a\u00030¤\u00012\u0006\u0010q\u001a\u00020rH\u0016J\u0011\u0010¥\u0001\u001a\u0002072\u0006\u0010q\u001a\u00020rH\u0016J\u0015\u0010¦\u0001\u001a\u0002072\n\u0010§\u0001\u001a\u0005\u0018\u00010¨\u0001H\u0016J\u001c\u0010©\u0001\u001a\u0011\u0012\r\u0012\u000b ª\u0001*\u0004\u0018\u00010\n0\n0\t*\u00020\bH\u0012J\u0015\u0010«\u0001\u001a\u000207*\u00020\b2\u0006\u0010B\u001a\u00020\nH\u0012J\u000e\u0010¬\u0001\u001a\u00030\u00ad\u0001*\u00020\bH\u0012R \u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0007X\u0092\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0090.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0092\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0092\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\u00020\u00168RX\u0092\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u001b\u001a\u00020\u001c8\u0010@\u0010X\u0091.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001e\u0010!\u001a\u00020\"8\u0010@\u0010X\u0091.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001b\u0010'\u001a\u00020(8RX\u0092\u0084\u0002¢\u0006\f\n\u0004\b+\u0010\u001a\u001a\u0004\b)\u0010*R\u001e\u0010,\u001a\u00020-8\u0010@\u0010X\u0091.¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u0014\u00102\u001a\u0002038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b4\u00105¨\u0006¯\u0001"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/OnfidoActivity;", "Lcom/onfido/android/sdk/capture/ui/BaseActivity;", "Lcom/onfido/android/sdk/capture/ui/OnfidoView;", "Lcom/onfido/android/sdk/capture/ui/NextActionListener;", "Lcom/onfido/android/sdk/capture/utils/ToolbarHost;", "()V", "activityLauncherMap", "", "Lcom/onfido/android/sdk/capture/ui/OnfidoActivity$Companion$FlowActivities;", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoActivityOnfidoBinding;", "getBinding$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoActivityOnfidoBinding;", "setBinding$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/databinding/OnfidoActivityOnfidoBinding;)V", "handler", "Landroid/os/Handler;", "lifecycleAwareDialog", "Lcom/onfido/android/sdk/capture/utils/LifecycleAwareDialog;", "navigationManager", "Lcom/onfido/android/sdk/capture/internal/navigation/navigator/FragmentNavigationManager;", "getNavigationManager", "()Lcom/onfido/android/sdk/capture/internal/navigation/navigator/FragmentNavigationManager;", "navigationManager$delegate", "Lkotlin/Lazy;", "nfcInteractor", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;", "getNfcInteractor$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;", "setNfcInteractor$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;)V", "onfidoAnalytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "getOnfidoAnalytics$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "setOnfidoAnalytics$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;)V", "presenter", "Lcom/onfido/android/sdk/capture/ui/OnfidoPresenter;", "getPresenter", "()Lcom/onfido/android/sdk/capture/ui/OnfidoPresenter;", "presenter$delegate", "presenterFactory", "Lcom/onfido/android/sdk/capture/ui/OnfidoPresenter$Factory;", "getPresenterFactory$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/OnfidoPresenter$Factory;", "setPresenterFactory$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/OnfidoPresenter$Factory;)V", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "getToolbar", "()Landroidx/appcompat/widget/Toolbar;", "completeFlow", "", "createExitIntent", "disableImmersiveFragment", "enableImmersiveFragment", "enableToolbarBackNavigation", "exitFlow", "exitCode", "Lcom/onfido/android/sdk/capture/ExitCode;", "finishWithResult", OnfidoLauncher.KEY_RESULT, "", "intent", "finishWithResult$onfido_capture_sdk_core_release", "handleErrorResult", "handleRecreationResult", "resultIntent", "requestCode", "handleSuccessResult", "hideLoadingDialog", "hideLoadingView", "hideToolbar", "initFlow", "savedInstanceState", "Landroid/os/Bundle;", "installSecurityUpdates", FirebaseAnalytics.Param.SUCCESS, "Lkotlin/Function0;", "error", "needToCaptureBackOfDocument", "", "onActivityResult", "Landroidx/activity/result/ActivityResult;", "onBackPressed", "onCreate", "onDestroy", "onDocumentCaptured", "onError", SentryEvent.JsonKeys.EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "onFragmentBackPress", "fragmentClass", "Ljava/lang/Class;", "Lcom/onfido/android/sdk/capture/ui/BaseFragment;", "onNext", "onOptionsItemSelected", Constants.IAP_ITEM_PARAM, "Landroid/view/MenuItem;", "onSaveInstanceState", "outState", "onStart", "onStop", "onStopDuringExitWhenSentToBackgroundMode", "onStopDuringExitWhenSentToBackgroundMode$onfido_capture_sdk_core_release", "restoreToolbar", "setFragment", Request.JsonKeys.FRAGMENT, "Landroidx/fragment/app/Fragment;", "flowStepDirection", "Lcom/onfido/android/sdk/capture/flow/FlowStepDirection;", "backStackStateName", "", "clearBackStack", "setSupportActionBar", "setupFragmentResultListeners", "setupPresenterWith", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "showCaptureFaceMessage", "showCaptureLivenessConfirmation", "videoFilePath", "livenessPerformedChallenges", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessPerformedChallenges;", "showConfirmationVideo", "showDeviceNotSupportedFragment", "showDocumentCapture", "isFrontSide", "captureDataBundle", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "nfcArguments", "Lcom/onfido/android/sdk/capture/flow/NfcArguments;", "showFaceCapture", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "showFinalScreen", "showLivenessCapture", "showLivenessIntro", "showIntroVideo", "showLoadingDialog", "reason", "showLoadingView", "showMessageScreen", "title", "message", "showMotionFlow", RRWebOptionsEvent.EVENT_TAG, "Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;", "showNetworkError", "showNfcHostFragment", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "nfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "isOnlyOneDocAvailable", "showNfcPermissionFragment", "showPermissionsManagementFragment", "showProofOfAddressFlow", "showProofOfAddressFlowWeb", "hostedWebModuleModuleInfo", "Lcom/onfido/hosted/web/module/model/HostedWebModuleModuleInfo;", "showUserConsentScreen", "showWorkflowFragment", "workflowConfig", "Lcom/onfido/android/sdk/FlowConfig;", "createLauncher", "kotlin.jvm.PlatformType", "launch", "toPerformanceAnalyticsScreen", "Lcom/onfido/android/sdk/capture/internal/performance/domain/PerformanceAnalyticsScreen;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class OnfidoActivity extends BaseActivity implements OnfidoView, NextActionListener, ToolbarHost {
    private static final String CURRENTLY_DISPLAYED_FRAGMENT_TAG = "CURRENTLY_DISPLAYED_FRAGMENT";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long FLOW_EXIT_NETWORK_BUFFER_MS = 500;
    private static final long FRAGMENT_ALPHA_ANIM_DURATION_MS = 500;
    public static final String INVALID_CERTIFICATE_MESSAGE_EXTRA = "INVALID_CERTIFICATE_MESSAGE";
    public static final String KEY_CAPTURE_MISSING_PERMISSIONS_CAPTURE_DATA = "capture_missing_permissions_capture_data";
    public static final String KEY_CAPTURE_MISSING_PERMISSIONS_CAPTURE_TYPE = "capture_missing_permissions_capture_type";
    public static final String KEY_RESULT_CAPTURE_MISSING_PERMISSIONS = "capture_missing_permissions";
    public static final String KEY_RESULT_WORKFLOW = "result_workflow";
    public static final String KEY_WORKFLOW_RESULT_CODE = "key_workflow_result_code";
    public static final String ONFIDO_INTENT_EXTRA = "onfido_intent_extra";
    public static final int ONFIDO_RECREATE = 433;
    private static final String ONFIDO_TOKEN = "onfido_token";
    public static final String ONFIDO_UPLOAD_RESULT = "onfido_upload_result";
    public static final int RESULT_EXIT_BACKGROUND_STOP = 444;
    public static final int RESULT_EXIT_INVALID_CERTIFICATE = 445;
    public static final int RESULT_EXIT_MISSING_ONFIDO_CONFIG = 447;
    public static final int RESULT_EXIT_TOKEN_EXPIRED = 446;
    private static final String SECURITY_UPDATES_INSTALLER = "com.google.android.gms.security.ProviderInstaller";
    private final Map<Companion.FlowActivities, ActivityResultLauncher<Intent>> activityLauncherMap;
    public OnfidoActivityOnfidoBinding binding;
    private Handler handler;
    private final LifecycleAwareDialog lifecycleAwareDialog = new LifecycleAwareDialog(this, (Function1) null, 2, (DefaultConstructorMarker) null);

    /* renamed from: navigationManager$delegate, reason: from kotlin metadata */
    private final Lazy navigationManager;

    @Inject
    public NfcInteractor nfcInteractor;

    @Inject
    public OnfidoAnalytics onfidoAnalytics;

    /* renamed from: presenter$delegate, reason: from kotlin metadata */
    private final Lazy presenter;

    @Inject
    public OnfidoPresenter.Factory presenterFactory;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\u0003\u0018\u00002\u00020\u0001:\u0003 !\"B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0002\b\u001fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/OnfidoActivity$Companion;", "", "()V", "CURRENTLY_DISPLAYED_FRAGMENT_TAG", "", "FLOW_EXIT_NETWORK_BUFFER_MS", "", "FRAGMENT_ALPHA_ANIM_DURATION_MS", "INVALID_CERTIFICATE_MESSAGE_EXTRA", "KEY_CAPTURE_MISSING_PERMISSIONS_CAPTURE_DATA", "KEY_CAPTURE_MISSING_PERMISSIONS_CAPTURE_TYPE", "KEY_RESULT_CAPTURE_MISSING_PERMISSIONS", "KEY_RESULT_WORKFLOW", "KEY_WORKFLOW_RESULT_CODE", "ONFIDO_INTENT_EXTRA", "ONFIDO_RECREATE", "", "ONFIDO_TOKEN", "ONFIDO_UPLOAD_RESULT", "RESULT_EXIT_BACKGROUND_STOP", "RESULT_EXIT_INVALID_CERTIFICATE", "RESULT_EXIT_MISSING_ONFIDO_CONFIG", "RESULT_EXIT_TOKEN_EXPIRED", "SECURITY_UPDATES_INSTALLER", "create", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "customBridgeUrl", "create$onfido_capture_sdk_core_release", "FlowActivities", "RequestCode", "RequestKey", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/OnfidoActivity$Companion$FlowActivities;", "", "requestCode", "", "(Ljava/lang/String;II)V", "getRequestCode", "()I", "ACTIVITY_CAPTURE_DOCUMENT", "ACTIVITY_CAPTURE_FACE", "ACTIVITY_CAPTURE_LIVENESS", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        static final class FlowActivities {
            private static final /* synthetic */ EnumEntries $ENTRIES;
            private static final /* synthetic */ FlowActivities[] $VALUES;
            public static final FlowActivities ACTIVITY_CAPTURE_DOCUMENT = new FlowActivities("ACTIVITY_CAPTURE_DOCUMENT", 0, 1);
            public static final FlowActivities ACTIVITY_CAPTURE_FACE = new FlowActivities("ACTIVITY_CAPTURE_FACE", 1, 2);
            public static final FlowActivities ACTIVITY_CAPTURE_LIVENESS = new FlowActivities("ACTIVITY_CAPTURE_LIVENESS", 2, 4);
            private final int requestCode;

            private static final /* synthetic */ FlowActivities[] $values() {
                return new FlowActivities[]{ACTIVITY_CAPTURE_DOCUMENT, ACTIVITY_CAPTURE_FACE, ACTIVITY_CAPTURE_LIVENESS};
            }

            static {
                FlowActivities[] flowActivitiesArr$values = $values();
                $VALUES = flowActivitiesArr$values;
                $ENTRIES = EnumEntriesKt.enumEntries(flowActivitiesArr$values);
            }

            private FlowActivities(String str, int i, int i2) {
                this.requestCode = i2;
            }

            public static EnumEntries<FlowActivities> getEntries() {
                return $ENTRIES;
            }

            public static FlowActivities valueOf(String str) {
                return (FlowActivities) Enum.valueOf(FlowActivities.class, str);
            }

            public static FlowActivities[] values() {
                return (FlowActivities[]) $VALUES.clone();
            }

            public final int getRequestCode() {
                return this.requestCode;
            }
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/OnfidoActivity$Companion$RequestCode;", "", "()V", "REQUEST_CAPTURE_DOCUMENT", "", "REQUEST_CAPTURE_FACE", "REQUEST_CAPTURE_LIVENESS", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        private static final class RequestCode {
            public static final RequestCode INSTANCE = new RequestCode();
            public static final int REQUEST_CAPTURE_DOCUMENT = 1;
            public static final int REQUEST_CAPTURE_FACE = 2;
            public static final int REQUEST_CAPTURE_LIVENESS = 4;

            private RequestCode() {
            }
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/OnfidoActivity$Companion$RequestKey;", "", "()V", "KEY_REQUEST_AVC_HOST", "", "KEY_REQUEST_DEVICE_NOT_SUPPORTED", "KEY_REQUEST_FACE_LIVENESS_CAPTURE", "KEY_REQUEST_FACE_LIVENESS_INTRO", "KEY_REQUEST_FACE_SELFIE_INTRO", "KEY_REQUEST_HOSTED_MODULE", "KEY_REQUEST_LIVENESS_CONFIRMATION", "KEY_REQUEST_NFC_HOST", "KEY_REQUEST_PERMISSIONS_MANAGEMENT", "KEY_REQUEST_POA_HOST", "KEY_REQUEST_USER_CONSENT", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class RequestKey {
            public static final RequestKey INSTANCE = new RequestKey();
            public static final String KEY_REQUEST_AVC_HOST = "result_avc_host";
            public static final String KEY_REQUEST_DEVICE_NOT_SUPPORTED = "device_not_supported";
            public static final String KEY_REQUEST_FACE_LIVENESS_CAPTURE = "face_liveness_capture";
            public static final String KEY_REQUEST_FACE_LIVENESS_INTRO = "face_liveness_intro";
            public static final String KEY_REQUEST_FACE_SELFIE_INTRO = "face_selfie_intro";
            public static final String KEY_REQUEST_HOSTED_MODULE = "result_hosted_web_module";
            public static final String KEY_REQUEST_LIVENESS_CONFIRMATION = "liveness_confirmation";
            public static final String KEY_REQUEST_NFC_HOST = "result_nfc_host";
            public static final String KEY_REQUEST_PERMISSIONS_MANAGEMENT = "permissions_management";
            public static final String KEY_REQUEST_POA_HOST = "result_poa_host";
            public static final String KEY_REQUEST_USER_CONSENT = "result_user_consent";

            private RequestKey() {
            }
        }

        private Companion() {
        }

        public static /* synthetic */ Intent create$onfido_capture_sdk_core_release$default(Companion companion, Context context, OnfidoConfig onfidoConfig, String str, int i, Object obj) {
            if ((i & 4) != 0) {
                str = null;
            }
            return companion.create$onfido_capture_sdk_core_release(context, onfidoConfig, str);
        }

        public final Intent create$onfido_capture_sdk_core_release(Context context, OnfidoConfig onfidoConfig, String customBridgeUrl) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
            Intent intent = new Intent(context, (Class<?>) OnfidoActivity.class);
            intent.putExtra(OnfidoConstants.ONFIDO_CONFIG, onfidoConfig);
            if (customBridgeUrl != null) {
                intent.putExtra(OnfidoConstants.ONFIDO_BRIDGE_URL, customBridgeUrl);
            }
            return intent;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Companion.FlowActivities.values().length];
            try {
                iArr[Companion.FlowActivities.ACTIVITY_CAPTURE_DOCUMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Companion.FlowActivities.ACTIVITY_CAPTURE_LIVENESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Companion.FlowActivities.ACTIVITY_CAPTURE_FACE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[FlowStepDirection.values().length];
            try {
                iArr2[FlowStepDirection.LEFT_TO_RIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[FlowStepDirection.RIGHT_TO_LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public OnfidoActivity() {
        Companion.FlowActivities flowActivities = Companion.FlowActivities.ACTIVITY_CAPTURE_DOCUMENT;
        Pair pair = TuplesKt.to(flowActivities, createLauncher(flowActivities));
        Companion.FlowActivities flowActivities2 = Companion.FlowActivities.ACTIVITY_CAPTURE_FACE;
        Pair pair2 = TuplesKt.to(flowActivities2, createLauncher(flowActivities2));
        Companion.FlowActivities flowActivities3 = Companion.FlowActivities.ACTIVITY_CAPTURE_LIVENESS;
        this.activityLauncherMap = MapsKt.mapOf(pair, pair2, TuplesKt.to(flowActivities3, createLauncher(flowActivities3)));
        this.presenter = LazyKt.lazy(new Function0<OnfidoPresenter>() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$presenter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final OnfidoPresenter invoke() {
                return this.this$0.getPresenterFactory$onfido_capture_sdk_core_release().create(this.this$0.getOnfidoConfig$onfido_capture_sdk_core_release());
            }
        });
        this.navigationManager = LazyKt.lazy(new Function0<FragmentNavigationManager>() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$navigationManager$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FragmentNavigationManager invoke() {
                OnfidoActivity onfidoActivity = this.this$0;
                FragmentManager supportFragmentManager = onfidoActivity.getSupportFragmentManager();
                Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
                return new FragmentNavigationManager(onfidoActivity, supportFragmentManager, R.id.hostFragmentContainer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void completeFlow$lambda$13(OnfidoActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getOnfidoAnalytics$onfido_capture_sdk_core_release().clear();
        this$0.finishWithResult$onfido_capture_sdk_core_release(-1, this$0.createExitIntent());
    }

    private Intent createExitIntent() {
        Intent intent = new Intent();
        OnfidoConfig onfidoConfig = getPresenter().getOnfidoConfig();
        intent.putExtra(ONFIDO_TOKEN, onfidoConfig.getToken());
        intent.putExtra(OnfidoConstants.ONFIDO_CONFIG, onfidoConfig);
        intent.putExtra("onfido_upload_result", getPresenter().getState$onfido_capture_sdk_core_release().getCaptures());
        return intent;
    }

    private ActivityResultLauncher<Intent> createLauncher(final Companion.FlowActivities flowActivities) {
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda11
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                OnfidoActivity.createLauncher$lambda$0(this.f$0, flowActivities, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResult(...)");
        return activityResultLauncherRegisterForActivityResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createLauncher$lambda$0(OnfidoActivity this$0, Companion.FlowActivities this_createLauncher, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_createLauncher, "$this_createLauncher");
        int requestCode = this_createLauncher.getRequestCode();
        Intrinsics.checkNotNull(activityResult);
        this$0.onActivityResult(requestCode, activityResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void exitFlow$lambda$14(OnfidoActivity this$0, Intent intent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(intent, "$intent");
        this$0.getOnfidoAnalytics$onfido_capture_sdk_core_release().clear();
        this$0.finishWithResult$onfido_capture_sdk_core_release(0, intent);
    }

    private FragmentNavigationManager getNavigationManager() {
        return (FragmentNavigationManager) this.navigationManager.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OnfidoPresenter getPresenter() {
        return (OnfidoPresenter) this.presenter.getValue();
    }

    private void handleErrorResult(Intent intent) {
        if (intent != null) {
            Serializable serializableExtra = intent.getSerializableExtra(OnfidoConstants.ONFIDO_EXCEPTION_RESULT);
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type java.lang.Exception{ kotlin.TypeAliasesKt.Exception }");
            Exception exc = (Exception) serializableExtra;
            if (exc instanceof CameraException) {
                getPresenter().onBackPressed$onfido_capture_sdk_core_release();
            } else {
                onError(exc);
            }
        }
    }

    private void handleRecreationResult(Intent resultIntent, int requestCode) {
        Bundle extras;
        Companion.FlowActivities flowActivities;
        if (resultIntent == null || (extras = resultIntent.getExtras()) == null) {
            return;
        }
        Object obj = extras.get(ONFIDO_INTENT_EXTRA);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type android.content.Intent");
        Intent intent = (Intent) obj;
        Companion.FlowActivities[] flowActivitiesArrValues = Companion.FlowActivities.values();
        int length = flowActivitiesArrValues.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                flowActivities = null;
                break;
            }
            flowActivities = flowActivitiesArrValues[i];
            if (flowActivities.getRequestCode() == requestCode) {
                break;
            } else {
                i++;
            }
        }
        if (flowActivities != null) {
            OnfidoPresenter.onNavigationStarted$onfido_capture_sdk_core_release$default(getPresenter(), PerformanceAnalyticsScreen.UNKNOWN, null, toPerformanceAnalyticsScreen(flowActivities), 2, null);
            launch(flowActivities, intent);
        }
    }

    private void handleSuccessResult(int requestCode, Intent intent) {
        if (requestCode != 1) {
            if (requestCode == 2) {
                CaptureActivity.Companion companion = CaptureActivity.INSTANCE;
                Intrinsics.checkNotNull(intent);
                String uploadedFileId = companion.getUploadedFileId(intent);
                UploadedArtifact uploadedArtifact = companion.getUploadedArtifact(intent);
                if (uploadedArtifact == null) {
                    throw new IllegalStateException(("No uploaded artifact for SelfieResult from CaptureActivity= " + intent.getExtras()).toString());
                }
                getPresenter().onFaceSelfieCaptureResult(new SelfieCaptureScreen.SelfieCaptureResult.Completed(uploadedFileId, uploadedArtifact));
                return;
            }
            if (requestCode != 4) {
                return;
            }
            Intrinsics.checkNotNull(intent);
            String stringExtra = intent.getStringExtra("video_path");
            Intrinsics.checkNotNull(stringExtra);
            Serializable serializableExtra = intent.getSerializableExtra(LivenessConfirmationFragment.ONFIDO_LIVENESS_CHALLENGES_EXTRA);
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessPerformedChallenges");
            getPresenter().setLivenessVideoOptions(stringExtra, (LivenessPerformedChallenges) serializableExtra);
        } else {
            if (intent == null) {
                return;
            }
            onDocumentCaptured(intent);
            if (needToCaptureBackOfDocument(intent)) {
                getPresenter().showCaptureStep$onfido_capture_sdk_core_release(false, IntentHelper.INSTANCE.getDocumentDataBundle(intent), PerformanceAnalyticsScreen.DOCUMENT_CAPTURE);
                return;
            }
            getPresenter().appendNfcFeature$onfido_capture_sdk_core_release();
        }
        getPresenter().nextAction();
    }

    private void hideToolbar() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initFlow(Bundle savedInstanceState) {
        getPresenter().initFlow$onfido_capture_sdk_core_release(savedInstanceState != null, ContextUtilsKt.isSystemDarkModeEnabled(this));
    }

    private void installSecurityUpdates(final Function0<Unit> success, final Function0<Unit> error) throws ClassNotFoundException {
        try {
            String[] protocols = SSLContext.getDefault().getDefaultSSLParameters().getProtocols();
            Intrinsics.checkNotNull(protocols);
            if (ArraysKt.contains(protocols, TlsVersion.TLS_1_2.javaName())) {
                success.invoke();
            } else {
                try {
                    Class.forName(SECURITY_UPDATES_INSTALLER);
                    ProviderInstaller.installIfNeededAsync(this, new ProviderInstaller.ProviderInstallListener() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity.installSecurityUpdates.1
                        @Override // com.google.android.gms.security.ProviderInstaller.ProviderInstallListener
                        public void onProviderInstallFailed(int p0, Intent p1) {
                            error.invoke();
                        }

                        @Override // com.google.android.gms.security.ProviderInstaller.ProviderInstallListener
                        public void onProviderInstalled() {
                            success.invoke();
                        }
                    });
                } catch (ClassNotFoundException unused) {
                    error.invoke();
                }
            }
        } catch (NoSuchAlgorithmException unused2) {
            error.invoke();
        }
    }

    private void launch(Companion.FlowActivities flowActivities, Intent intent) {
        getStartingNewActivity().compareAndSet(false, true);
        ActivityResultLauncher<Intent> activityResultLauncher = this.activityLauncherMap.get(flowActivities);
        if (activityResultLauncher != null) {
            activityResultLauncher.launch(intent);
        }
    }

    private boolean needToCaptureBackOfDocument(Intent intent) {
        IntentHelper intentHelper = IntentHelper.INSTANCE;
        return intentHelper.getIsDocumentFrontSide(intent, true) && getPresenter().backSideCaptureNeeded(intentHelper.getDocTypeFrom(intent), intentHelper.getGenericDocumentPages(intent));
    }

    private void onActivityResult(int requestCode, ActivityResult result) {
        Exception missingOnfidoConfigException;
        Intent data = result.getData();
        Handler handler = this.handler;
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OnfidoActivity.onActivityResult$lambda$1(this.f$0);
                }
            }, 500L);
        }
        int resultCode = result.getResultCode();
        if (resultCode == -2) {
            handleErrorResult(data);
            return;
        }
        if (resultCode == -1) {
            handleSuccessResult(requestCode, data);
            return;
        }
        if (resultCode != 0) {
            if (resultCode == 433) {
                handleRecreationResult(data, requestCode);
                return;
            }
            switch (resultCode) {
                case RESULT_EXIT_BACKGROUND_STOP /* 444 */:
                    exitFlow(ExitCode.USER_LEFT_ACTIVITY);
                    return;
                case RESULT_EXIT_INVALID_CERTIFICATE /* 445 */:
                    if (data != null) {
                        String stringExtra = data.getStringExtra(INVALID_CERTIFICATE_MESSAGE_EXTRA);
                        if (stringExtra == null) {
                            stringExtra = "";
                        }
                        onError(new InvalidCertificateException(stringExtra));
                        return;
                    }
                    return;
                case RESULT_EXIT_TOKEN_EXPIRED /* 446 */:
                    missingOnfidoConfigException = TokenExpiredException.INSTANCE;
                    break;
                case RESULT_EXIT_MISSING_ONFIDO_CONFIG /* 447 */:
                    missingOnfidoConfigException = new MissingOnfidoConfigException();
                    break;
            }
            onError(missingOnfidoConfigException);
            return;
        }
        getPresenter().onBackPressed$onfido_capture_sdk_core_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onActivityResult$lambda$1(OnfidoActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentContainerView hostFragmentContainer = this$0.getBinding$onfido_capture_sdk_core_release().hostFragmentContainer;
        Intrinsics.checkNotNullExpressionValue(hostFragmentContainer, "hostFragmentContainer");
        ViewExtensionsKt.animateToAlpha$default(hostFragmentContainer, 1.0f, 0L, 2, null);
    }

    private void onDocumentCaptured(Intent intent) {
        IntentHelper intentHelper = IntentHelper.INSTANCE;
        getPresenter().onDocumentCaptured$onfido_capture_sdk_core_release(intentHelper.getDocumentResultFrom(intent), intentHelper.getNfcProperties(intent));
    }

    private void setFragment(final Fragment fragment, final FlowStepDirection flowStepDirection, final String backStackStateName, final boolean clearBackStack) {
        Handler handler = this.handler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda19
                @Override // java.lang.Runnable
                public final void run() {
                    OnfidoActivity.setFragment$lambda$12(this.f$0, clearBackStack, flowStepDirection, fragment, backStackStateName);
                }
            });
        }
    }

    static /* synthetic */ void setFragment$default(OnfidoActivity onfidoActivity, Fragment fragment, FlowStepDirection flowStepDirection, String str, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setFragment");
        }
        if ((i & 4) != 0) {
            str = null;
        }
        if ((i & 8) != 0) {
            z = true;
        }
        onfidoActivity.setFragment(fragment, flowStepDirection, str, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setFragment$lambda$12(OnfidoActivity this$0, boolean z, FlowStepDirection flowStepDirection, Fragment fragment, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(flowStepDirection, "$flowStepDirection");
        Intrinsics.checkNotNullParameter(fragment, "$fragment");
        if (this$0.isFinishing() || this$0.isDestroyed()) {
            return;
        }
        if (z && !this$0.getSupportFragmentManager().isStateSaved()) {
            this$0.getSupportFragmentManager().popBackStackImmediate((String) null, 1);
        }
        FragmentTransaction fragmentTransactionBeginTransaction = this$0.getSupportFragmentManager().beginTransaction();
        int i = WhenMappings.$EnumSwitchMapping$1[flowStepDirection.ordinal()];
        if (i == 1 || i == 2) {
            Integer slideInAnimation = flowStepDirection.getSlideInAnimation();
            Intrinsics.checkNotNull(slideInAnimation);
            int iIntValue = slideInAnimation.intValue();
            Integer slideOutAnimation = flowStepDirection.getSlideOutAnimation();
            Intrinsics.checkNotNull(slideOutAnimation);
            fragmentTransactionBeginTransaction.setCustomAnimations(iIntValue, slideOutAnimation.intValue());
        }
        List<Fragment> fragments = this$0.getSupportFragmentManager().getFragments();
        Intrinsics.checkNotNullExpressionValue(fragments, "getFragments(...)");
        Iterator<T> it = fragments.iterator();
        while (it.hasNext()) {
            View view = ((Fragment) it.next()).getView();
            if (view != null) {
                view.setImportantForAccessibility(4);
            }
        }
        (z ? fragmentTransactionBeginTransaction.replace(R.id.hostFragmentContainer, fragment, CURRENTLY_DISPLAYED_FRAGMENT_TAG) : fragmentTransactionBeginTransaction.add(R.id.hostFragmentContainer, fragment, CURRENTLY_DISPLAYED_FRAGMENT_TAG)).addToBackStack(str);
        View view2 = fragment.getView();
        if (view2 != null) {
            view2.setImportantForAccessibility(1);
        }
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    private void setupFragmentResultListeners() {
        getSupportFragmentManager().setFragmentResultListener(Companion.RequestKey.KEY_REQUEST_LIVENESS_CONFIRMATION, this, new FragmentResultListener() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda21
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str, Bundle bundle) {
                OnfidoActivity.setupFragmentResultListeners$lambda$15(this.f$0, str, bundle);
            }
        });
        getSupportFragmentManager().setFragmentResultListener(Companion.RequestKey.KEY_REQUEST_FACE_LIVENESS_INTRO, this, new FragmentResultListener() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda5
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str, Bundle bundle) {
                OnfidoActivity.setupFragmentResultListeners$lambda$16(this.f$0, str, bundle);
            }
        });
        getSupportFragmentManager().setFragmentResultListener("face_liveness_capture", this, new FragmentResultListener() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda6
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str, Bundle bundle) {
                OnfidoActivity.setupFragmentResultListeners$lambda$17(this.f$0, str, bundle);
            }
        });
        getSupportFragmentManager().setFragmentResultListener(SelfieCaptureScreen.KEY_REQUEST, this, new FragmentResultListener() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda7
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str, Bundle bundle) {
                OnfidoActivity.setupFragmentResultListeners$lambda$18(this.f$0, str, bundle);
            }
        });
        getSupportFragmentManager().setFragmentResultListener(DocumentCaptureScreen.KEY_REQUEST, this, new FragmentResultListener() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda8
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str, Bundle bundle) {
                OnfidoActivity.setupFragmentResultListeners$lambda$19(this.f$0, str, bundle);
            }
        });
        getSupportFragmentManager().setFragmentResultListener(KEY_RESULT_CAPTURE_MISSING_PERMISSIONS, this, new FragmentResultListener() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda9
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str, Bundle bundle) {
                OnfidoActivity.setupFragmentResultListeners$lambda$21(this.f$0, str, bundle);
            }
        });
        getSupportFragmentManager().setFragmentResultListener(Companion.RequestKey.KEY_REQUEST_FACE_SELFIE_INTRO, this, new FragmentResultListener() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda10
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str, Bundle bundle) {
                OnfidoActivity.setupFragmentResultListeners$lambda$22(this.f$0, str, bundle);
            }
        });
        getSupportFragmentManager().setFragmentResultListener(Companion.RequestKey.KEY_REQUEST_PERMISSIONS_MANAGEMENT, this, new FragmentResultListener() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda12
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str, Bundle bundle) {
                OnfidoActivity.setupFragmentResultListeners$lambda$23(this.f$0, str, bundle);
            }
        });
        getSupportFragmentManager().setFragmentResultListener(Companion.RequestKey.KEY_REQUEST_NFC_HOST, this, new FragmentResultListener() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda13
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str, Bundle bundle) {
                OnfidoActivity.setupFragmentResultListeners$lambda$24(this.f$0, str, bundle);
            }
        });
        getSupportFragmentManager().setFragmentResultListener(Companion.RequestKey.KEY_REQUEST_AVC_HOST, this, new FragmentResultListener() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda14
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str, Bundle bundle) {
                OnfidoActivity.setupFragmentResultListeners$lambda$25(this.f$0, str, bundle);
            }
        });
        getSupportFragmentManager().setFragmentResultListener(DocumentSelectionScreen.KEY_REQUEST, this, new FragmentResultListener() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda22
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str, Bundle bundle) {
                OnfidoActivity.setupFragmentResultListeners$lambda$26(this.f$0, str, bundle);
            }
        });
        getSupportFragmentManager().setFragmentResultListener(Companion.RequestKey.KEY_REQUEST_USER_CONSENT, this, new FragmentResultListener() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda23
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str, Bundle bundle) {
                OnfidoActivity.setupFragmentResultListeners$lambda$27(this.f$0, str, bundle);
            }
        });
        getSupportFragmentManager().setFragmentResultListener(Companion.RequestKey.KEY_REQUEST_POA_HOST, this, new FragmentResultListener() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda1
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str, Bundle bundle) {
                OnfidoActivity.setupFragmentResultListeners$lambda$28(this.f$0, str, bundle);
            }
        });
        getSupportFragmentManager().setFragmentResultListener(Companion.RequestKey.KEY_REQUEST_HOSTED_MODULE, this, new FragmentResultListener() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda2
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str, Bundle bundle) {
                OnfidoActivity.setupFragmentResultListeners$lambda$29(this.f$0, str, bundle);
            }
        });
        getSupportFragmentManager().setFragmentResultListener(KEY_RESULT_WORKFLOW, this, new FragmentResultListener() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda3
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str, Bundle bundle) {
                OnfidoActivity.setupFragmentResultListeners$lambda$31(this.f$0, str, bundle);
            }
        });
        getSupportFragmentManager().setFragmentResultListener(Companion.RequestKey.KEY_REQUEST_DEVICE_NOT_SUPPORTED, this, new FragmentResultListener() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda4
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str, Bundle bundle) {
                OnfidoActivity.setupFragmentResultListeners$lambda$32(this.f$0, str, bundle);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupFragmentResultListeners$lambda$15(OnfidoActivity this$0, String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        this$0.getPresenter().onLivenessConfirmationResultReceived$onfido_capture_sdk_core_release(LivenessConfirmationFragment.INSTANCE.getResult(bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupFragmentResultListeners$lambda$16(OnfidoActivity this$0, String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(bundle, "<anonymous parameter 1>");
        this$0.getPresenter().nextAction();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupFragmentResultListeners$lambda$17(OnfidoActivity this$0, String str, Bundle resultBundle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(resultBundle, "resultBundle");
        this$0.getPresenter().onLivenessCaptureResult(LivenessCaptureScreen.INSTANCE.retrieveResult(resultBundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupFragmentResultListeners$lambda$18(OnfidoActivity this$0, String str, Bundle resultBundle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(resultBundle, "resultBundle");
        this$0.getPresenter().onFaceSelfieCaptureResult(SelfieCaptureScreen.INSTANCE.retrieveResult(resultBundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupFragmentResultListeners$lambda$19(OnfidoActivity this$0, String str, Bundle resultBundle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(resultBundle, "resultBundle");
        this$0.getPresenter().onDocumentCaptureResult(DocumentCaptureScreen.INSTANCE.toDocumentCaptureResult(resultBundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupFragmentResultListeners$lambda$21(OnfidoActivity this$0, String str, Bundle resultBundle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(resultBundle, "resultBundle");
        Serializable serializable = resultBundle.getSerializable(KEY_CAPTURE_MISSING_PERMISSIONS_CAPTURE_DATA);
        CaptureStepDataBundle captureStepDataBundle = serializable instanceof CaptureStepDataBundle ? (CaptureStepDataBundle) serializable : null;
        if (captureStepDataBundle == null) {
            Serializable serializable2 = resultBundle.getSerializable(KEY_CAPTURE_MISSING_PERMISSIONS_CAPTURE_TYPE);
            Intrinsics.checkNotNull(serializable2, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.CaptureType");
            captureStepDataBundle = new CaptureStepDataBundle((CaptureType) serializable2, null, null, null, null, null, null, WebSocketProtocol.PAYLOAD_SHORT, null);
        }
        this$0.getPresenter().onCaptureWithoutPermissions$onfido_capture_sdk_core_release(captureStepDataBundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupFragmentResultListeners$lambda$22(OnfidoActivity this$0, String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(bundle, "<anonymous parameter 1>");
        this$0.getPresenter().nextAction();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupFragmentResultListeners$lambda$23(OnfidoActivity this$0, String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        this$0.getSupportFragmentManager().popBackStack();
        this$0.getPresenter().onPermissionResult$onfido_capture_sdk_core_release(PermissionsManagementFragment.INSTANCE.getResult(bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupFragmentResultListeners$lambda$24(OnfidoActivity this$0, String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        this$0.getPresenter().onNfcHostResult$onfido_capture_sdk_core_release(NfcHostFragment.INSTANCE.getNfcHostResult(bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupFragmentResultListeners$lambda$25(OnfidoActivity this$0, String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        this$0.getPresenter().onAvcHostResult$onfido_capture_sdk_core_release(MotionHostFragment.INSTANCE.getAvcHostResult(bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupFragmentResultListeners$lambda$26(OnfidoActivity this$0, String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        this$0.getPresenter().onRestrictedDocumentSelectionResult$onfido_capture_sdk_core_release(DocumentSelectionHostFragment.INSTANCE.getResult(bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupFragmentResultListeners$lambda$27(OnfidoActivity this$0, String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        this$0.getPresenter().onUserConsentResult$onfido_capture_sdk_core_release(UserConsentFragment.INSTANCE.getUserConsentResult(bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupFragmentResultListeners$lambda$28(OnfidoActivity this$0, String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        this$0.getPresenter().onPoaResult$onfido_capture_sdk_core_release(PoaHostFragment.INSTANCE.getPoaResult(bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupFragmentResultListeners$lambda$29(OnfidoActivity this$0, String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        this$0.getPresenter().onWebPoaResult$onfido_capture_sdk_core_release(HostedWebModuleFragment.INSTANCE.getResult(bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupFragmentResultListeners$lambda$31(OnfidoActivity this$0, String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        int i = bundle.getInt(KEY_WORKFLOW_RESULT_CODE);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        Unit unit = Unit.INSTANCE;
        this$0.finishWithResult$onfido_capture_sdk_core_release(i, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupFragmentResultListeners$lambda$32(OnfidoActivity this$0, String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(bundle, "<anonymous parameter 1>");
        this$0.exitFlow(ExitCode.REQUIRED_NFC_FLOW_NOT_COMPLETED);
    }

    private void setupPresenterWith(Bundle savedInstanceState, OnfidoConfig onfidoConfig) {
        OnfidoPresenter.State state;
        if (savedInstanceState != null) {
            state = (OnfidoPresenter.State) savedInstanceState.getParcelable("OnfidoPresenter");
        } else {
            int i = 0;
            state = new OnfidoPresenter.State(onfidoConfig.getFlowSteps(), i, new Captures(null, null, null, 7, null), null, null, null, 56, null);
        }
        getPresenter().setup$onfido_capture_sdk_core_release(this, state);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDocumentCapture$lambda$3(OnfidoActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentContainerView hostFragmentContainer = this$0.getBinding$onfido_capture_sdk_core_release().hostFragmentContainer;
        Intrinsics.checkNotNullExpressionValue(hostFragmentContainer, "hostFragmentContainer");
        ViewExtensionsKt.animateToAlpha$default(hostFragmentContainer, 0.0f, 0L, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showFaceCapture$lambda$4(OnfidoActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentContainerView hostFragmentContainer = this$0.getBinding$onfido_capture_sdk_core_release().hostFragmentContainer;
        Intrinsics.checkNotNullExpressionValue(hostFragmentContainer, "hostFragmentContainer");
        ViewExtensionsKt.animateToAlpha$default(hostFragmentContainer, 0.0f, 0L, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showLivenessCapture$lambda$5(OnfidoActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentContainerView hostFragmentContainer = this$0.getBinding$onfido_capture_sdk_core_release().hostFragmentContainer;
        Intrinsics.checkNotNullExpressionValue(hostFragmentContainer, "hostFragmentContainer");
        ViewExtensionsKt.animateToAlpha$default(hostFragmentContainer, 0.0f, 0L, 2, null);
    }

    private PerformanceAnalyticsScreen toPerformanceAnalyticsScreen(Companion.FlowActivities flowActivities) {
        int i = WhenMappings.$EnumSwitchMapping$0[flowActivities.ordinal()];
        if (i == 1) {
            return PerformanceAnalyticsScreen.DOCUMENT_CAPTURE;
        }
        if (i == 2) {
            return PerformanceAnalyticsScreen.FACE_VIDEO_CAPTURE;
        }
        if (i == 3) {
            return PerformanceAnalyticsScreen.FACE_SELFIE_CAPTURE;
        }
        throw new NoWhenBranchMatchedException();
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void completeFlow() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda17
                @Override // java.lang.Runnable
                public final void run() {
                    OnfidoActivity.completeFlow$lambda$13(this.f$0);
                }
            }, 500L);
        }
    }

    @Override // com.onfido.android.sdk.capture.utils.ToolbarHost
    public void disableImmersiveFragment() {
        FragmentContainerView hostFragmentContainer = getBinding$onfido_capture_sdk_core_release().hostFragmentContainer;
        Intrinsics.checkNotNullExpressionValue(hostFragmentContainer, "hostFragmentContainer");
        ViewGroup.LayoutParams layoutParams = hostFragmentContainer.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.addRule(3, getBinding$onfido_capture_sdk_core_release().toolbar.getId());
        hostFragmentContainer.setLayoutParams(layoutParams2);
    }

    @Override // com.onfido.android.sdk.capture.utils.ToolbarHost
    public void enableImmersiveFragment() {
        FragmentContainerView hostFragmentContainer = getBinding$onfido_capture_sdk_core_release().hostFragmentContainer;
        Intrinsics.checkNotNullExpressionValue(hostFragmentContainer, "hostFragmentContainer");
        ViewGroup.LayoutParams layoutParams = hostFragmentContainer.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.removeRule(3);
        hostFragmentContainer.setLayoutParams(layoutParams2);
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void enableToolbarBackNavigation() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setHomeActionContentDescription(R.string.onfido_generic_back);
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void exitFlow(ExitCode exitCode) {
        Intrinsics.checkNotNullParameter(exitCode, "exitCode");
        final Intent intentPutExtra = createExitIntent().putExtra(OnfidoConstants.ONFIDO_EXIT_CODE, exitCode);
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
        getPresenter().onFlowExited$onfido_capture_sdk_core_release();
        Handler handler = this.handler;
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda15
                @Override // java.lang.Runnable
                public final void run() {
                    OnfidoActivity.exitFlow$lambda$14(this.f$0, intentPutExtra);
                }
            }, 500L);
        }
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseActivity
    public void finishWithResult$onfido_capture_sdk_core_release(int result, Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.finishWithResult$onfido_capture_sdk_core_release(result, intent);
        if (this.presenterFactory != null) {
            getPresenter().removeLoggerTree$onfido_capture_sdk_core_release();
        }
    }

    public OnfidoActivityOnfidoBinding getBinding$onfido_capture_sdk_core_release() {
        OnfidoActivityOnfidoBinding onfidoActivityOnfidoBinding = this.binding;
        if (onfidoActivityOnfidoBinding != null) {
            return onfidoActivityOnfidoBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public NfcInteractor getNfcInteractor$onfido_capture_sdk_core_release() {
        NfcInteractor nfcInteractor = this.nfcInteractor;
        if (nfcInteractor != null) {
            return nfcInteractor;
        }
        Intrinsics.throwUninitializedPropertyAccessException("nfcInteractor");
        return null;
    }

    public OnfidoAnalytics getOnfidoAnalytics$onfido_capture_sdk_core_release() {
        OnfidoAnalytics onfidoAnalytics = this.onfidoAnalytics;
        if (onfidoAnalytics != null) {
            return onfidoAnalytics;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onfidoAnalytics");
        return null;
    }

    public OnfidoPresenter.Factory getPresenterFactory$onfido_capture_sdk_core_release() {
        OnfidoPresenter.Factory factory = this.presenterFactory;
        if (factory != null) {
            return factory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("presenterFactory");
        return null;
    }

    @Override // com.onfido.android.sdk.capture.utils.ToolbarHost
    public Toolbar getToolbar() {
        Toolbar toolbar = getBinding$onfido_capture_sdk_core_release().toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar, "toolbar");
        return toolbar;
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void hideLoadingDialog() {
        dismissLoadingDialog$onfido_capture_sdk_core_release();
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void hideLoadingView() {
        RelativeLayout loadingView = getBinding$onfido_capture_sdk_core_release().loadingView;
        Intrinsics.checkNotNullExpressionValue(loadingView, "loadingView");
        ViewExtensionsKt.toGone$default(loadingView, false, 1, null);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    @Deprecated(message = "Overrides a deprecated method, compilation error with Kotlin 1.9")
    public void onBackPressed() {
        super.onBackPressed();
        if (getPresenter().useLocalBackNavigation$onfido_capture_sdk_core_release() || getOnBackPressedDispatcher().hasEnabledCallbacks()) {
            return;
        }
        getPresenter().onBackPressed$onfido_capture_sdk_core_release();
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(final Bundle savedInstanceState) throws ClassNotFoundException {
        super.onCreate(savedInstanceState);
        OnfidoActivityOnfidoBinding onfidoActivityOnfidoBindingInflate = OnfidoActivityOnfidoBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(onfidoActivityOnfidoBindingInflate, "inflate(...)");
        setBinding$onfido_capture_sdk_core_release(onfidoActivityOnfidoBindingInflate);
        setContentView(getBinding$onfido_capture_sdk_core_release().getRoot());
        SdkController.getSdkComponent$default(SdkController.INSTANCE.getInstance(), this, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        this.handler = new Handler(Looper.getMainLooper());
        setSupportActionBar(getBinding$onfido_capture_sdk_core_release().toolbar);
        setupPresenterWith(savedInstanceState, getOnfidoConfig$onfido_capture_sdk_core_release());
        getSupportFragmentManager().registerFragmentLifecycleCallbacks(new ActivityTitleSetterFragmentLifecycleCallbacks(), true);
        installSecurityUpdates(new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity.onCreate.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                OnfidoActivity.this.initFlow(savedInstanceState);
            }
        }, new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity.onCreate.2
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
                OnfidoActivity.this.onError(new SecurityException("You are using an unsupported TLS < 1.2 version"));
            }
        });
        setupFragmentResultListeners();
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        getPresenter().cleanFiles(getFilesDir());
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void onError(Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        dismissLoadingDialog$onfido_capture_sdk_core_release();
        Intent intentCreateExitIntent = createExitIntent();
        intentCreateExitIntent.putExtra(OnfidoConstants.ONFIDO_EXCEPTION_RESULT, exception instanceof OnfidoException ? (OnfidoException) exception : new OnfidoException(exception.toString()));
        finishWithResult$onfido_capture_sdk_core_release(-2, intentCreateExitIntent);
    }

    public void onFragmentBackPress(Class<? extends BaseFragment> fragmentClass) {
        Intrinsics.checkNotNullParameter(fragmentClass, "fragmentClass");
        getPresenter().onBackPressed$onfido_capture_sdk_core_release();
    }

    @Override // com.onfido.android.sdk.capture.ui.NextActionListener
    public void onNext() {
        getPresenter().nextAction();
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        onBackPressed();
        return true;
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putParcelable("OnfidoPresenter", getPresenter().getState$onfido_capture_sdk_core_release());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        getPresenter().onViewStarted$onfido_capture_sdk_core_release(getNavigationManager());
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        getPresenter().onViewStopped();
        super.onStop();
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseActivity
    public void onStopDuringExitWhenSentToBackgroundMode$onfido_capture_sdk_core_release() {
        exitFlow(ExitCode.USER_LEFT_ACTIVITY);
    }

    @Override // com.onfido.android.sdk.capture.utils.ToolbarHost
    public void resetToolbar() {
        ToolbarHost.DefaultImpls.resetToolbar(this);
    }

    @Override // com.onfido.android.sdk.capture.utils.ToolbarHost
    public void resetToolbarColors() {
        ToolbarHost.DefaultImpls.resetToolbarColors(this);
    }

    @Override // com.onfido.android.sdk.capture.ui.NextActionListener, com.onfido.android.sdk.capture.utils.ToolbarHost
    public void restoreToolbar() {
        setSupportActionBar(getBinding$onfido_capture_sdk_core_release().toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.show();
        }
    }

    public void setBinding$onfido_capture_sdk_core_release(OnfidoActivityOnfidoBinding onfidoActivityOnfidoBinding) {
        Intrinsics.checkNotNullParameter(onfidoActivityOnfidoBinding, "<set-?>");
        this.binding = onfidoActivityOnfidoBinding;
    }

    public void setNfcInteractor$onfido_capture_sdk_core_release(NfcInteractor nfcInteractor) {
        Intrinsics.checkNotNullParameter(nfcInteractor, "<set-?>");
        this.nfcInteractor = nfcInteractor;
    }

    public void setOnfidoAnalytics$onfido_capture_sdk_core_release(OnfidoAnalytics onfidoAnalytics) {
        Intrinsics.checkNotNullParameter(onfidoAnalytics, "<set-?>");
        this.onfidoAnalytics = onfidoAnalytics;
    }

    public void setPresenterFactory$onfido_capture_sdk_core_release(OnfidoPresenter.Factory factory) {
        Intrinsics.checkNotNullParameter(factory, "<set-?>");
        this.presenterFactory = factory;
    }

    @Override // com.onfido.android.sdk.capture.ui.BaseActivity, androidx.appcompat.app.AppCompatActivity
    public void setSupportActionBar(Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        if (toolbar != null) {
            Sequence sequenceFilter = SequencesKt.filter(ViewGroupKt.getChildren(toolbar), ToolbarExtensionsKt$performActionOnTitleTextView$$inlined$filterIsInstance$1.INSTANCE);
            Intrinsics.checkNotNull(sequenceFilter, "null cannot be cast to non-null type kotlin.sequences.Sequence<R of kotlin.sequences.SequencesKt___SequencesKt.filterIsInstance>");
            TextView textView = (TextView) SequencesKt.firstOrNull(sequenceFilter);
            if (textView != null) {
                ViewCompat.setAccessibilityHeading(textView, true);
            }
        }
    }

    @Override // com.onfido.android.sdk.capture.utils.ToolbarHost
    public void setToolbarColor(int i, int i2, int i3) {
        ToolbarHost.DefaultImpls.setToolbarColor(this, i, i2, i3);
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showCaptureFaceMessage(FlowStepDirection flowStepDirection) {
        Intrinsics.checkNotNullParameter(flowStepDirection, "flowStepDirection");
        FaceIntroFragment.Companion companion = FaceIntroFragment.INSTANCE;
        String string = getString(R.string.onfido_selfie_intro_title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = getString(R.string.onfido_selfie_intro_subtitle);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        String string3 = getString(R.string.onfido_selfie_intro_banner_nudity_message);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        setFragment$default(this, companion.createInstance(Companion.RequestKey.KEY_REQUEST_FACE_SELFIE_INTRO, string, string2, string3, CollectionsKt.listOf((Object[]) new String[]{getString(R.string.onfido_selfie_intro_list_item_face_forward), getString(R.string.onfido_selfie_intro_list_item_no_face_cover)})), flowStepDirection, null, false, 12, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showCaptureLivenessConfirmation(FlowStepDirection flowStepDirection, String videoFilePath, LivenessPerformedChallenges livenessPerformedChallenges, boolean showConfirmationVideo) {
        Intrinsics.checkNotNullParameter(flowStepDirection, "flowStepDirection");
        Intrinsics.checkNotNullParameter(videoFilePath, "videoFilePath");
        Intrinsics.checkNotNullParameter(livenessPerformedChallenges, "livenessPerformedChallenges");
        setFragment$default(this, LivenessConfirmationFragment.INSTANCE.createInstance(Companion.RequestKey.KEY_REQUEST_LIVENESS_CONFIRMATION, videoFilePath, showConfirmationVideo, livenessPerformedChallenges), flowStepDirection, null, false, 12, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showDeviceNotSupportedFragment(FlowStepDirection flowStepDirection) {
        Intrinsics.checkNotNullParameter(flowStepDirection, "flowStepDirection");
        setFragment$default(this, NfcDeviceNotSupportedFragment.INSTANCE.createInstance(Companion.RequestKey.KEY_REQUEST_DEVICE_NOT_SUPPORTED), flowStepDirection, null, false, 12, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showDocumentCapture(boolean isFrontSide, CaptureStepDataBundle captureDataBundle, NfcArguments nfcArguments) {
        Intrinsics.checkNotNullParameter(captureDataBundle, "captureDataBundle");
        Intrinsics.checkNotNullParameter(nfcArguments, "nfcArguments");
        Handler handler = this.handler;
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda18
                @Override // java.lang.Runnable
                public final void run() {
                    OnfidoActivity.showDocumentCapture$lambda$3(this.f$0);
                }
            }, 500L);
        }
        launch(Companion.FlowActivities.ACTIVITY_CAPTURE_DOCUMENT, CaptureActivity.INSTANCE.createDocumentIntent(this, getOnfidoConfig$onfido_capture_sdk_core_release(), isFrontSide, captureDataBundle.getDocumentType(), captureDataBundle.getCountryCode(), captureDataBundle.getDocumentFormat(), nfcArguments, (128 & 128) != 0 ? false : false, (128 & 256) != 0 ? null : captureDataBundle.getGenericDocTitle(), (128 & 512) != 0 ? null : captureDataBundle.getGenericDocPages()));
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showFaceCapture(DocumentType documentType) {
        Handler handler = this.handler;
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda20
                @Override // java.lang.Runnable
                public final void run() {
                    OnfidoActivity.showFaceCapture$lambda$4(this.f$0);
                }
            }, 500L);
        }
        launch(Companion.FlowActivities.ACTIVITY_CAPTURE_FACE, CaptureActivity.INSTANCE.createFaceIntent(this, documentType, getOnfidoConfig$onfido_capture_sdk_core_release()));
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showFinalScreen(FlowStepDirection flowStepDirection) {
        Intrinsics.checkNotNullParameter(flowStepDirection, "flowStepDirection");
        setFragment$default(this, FinalScreenFragment.INSTANCE.createInstance(), flowStepDirection, null, false, 12, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showLivenessCapture() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity$$ExternalSyntheticLambda16
                @Override // java.lang.Runnable
                public final void run() {
                    OnfidoActivity.showLivenessCapture$lambda$5(this.f$0);
                }
            }, 500L);
        }
        launch(Companion.FlowActivities.ACTIVITY_CAPTURE_LIVENESS, CaptureActivity.INSTANCE.createLivenessIntent(this, getOnfidoConfig$onfido_capture_sdk_core_release()));
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showLivenessIntro(boolean showIntroVideo, FlowStepDirection flowStepDirection) {
        Intrinsics.checkNotNullParameter(flowStepDirection, "flowStepDirection");
        setFragment$default(this, LivenessIntroFragment.INSTANCE.createInstance(Companion.RequestKey.KEY_REQUEST_FACE_LIVENESS_INTRO, showIntroVideo), flowStepDirection, null, false, 12, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showLoadingDialog(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        showLoadingDialog$onfido_capture_sdk_core_release(new LoadingFragment.Companion.DialogMode.Loading(reason));
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showLoadingView() {
        RelativeLayout loadingView = getBinding$onfido_capture_sdk_core_release().loadingView;
        Intrinsics.checkNotNullExpressionValue(loadingView, "loadingView");
        ViewExtensionsKt.toVisible$default(loadingView, false, 1, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showMessageScreen(String title, String message, FlowStepDirection flowStepDirection) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(flowStepDirection, "flowStepDirection");
        setFragment$default(this, MessageFragment.INSTANCE.createInstance(title, message), flowStepDirection, null, false, 12, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showMotionFlow(MotionCaptureVariantOptions options, FlowStepDirection flowStepDirection) {
        Intrinsics.checkNotNullParameter(flowStepDirection, "flowStepDirection");
        setFragment$default(this, MotionHostFragment.Companion.createInstance$default(MotionHostFragment.INSTANCE, Companion.RequestKey.KEY_REQUEST_AVC_HOST, options, false, 4, null), flowStepDirection, null, false, 12, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showNetworkError() {
        this.lifecycleAwareDialog.show((56 & 1) != 0 ? null : null, R.string.onfido_generic_error_network_detail, (56 & 4) != 0 ? R.string.onfido_ok : 0, (56 & 8) != 0 ? null : null, (56 & 16) != 0 ? false : false, (Function1<? super DialogInterface, Unit>) ((56 & 32) != 0 ? null : null), (Function1<? super DialogInterface, Unit>) ((56 & 64) != 0 ? LifecycleAwareDialog.C07234.INSTANCE : new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.ui.OnfidoActivity.showNetworkError.1
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
                OnfidoActivity.this.getPresenter().onBackPressed$onfido_capture_sdk_core_release();
            }
        }));
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showNfcHostFragment(DocumentType documentType, CountryCode countryCode, NfcProperties nfcProperties, FlowStepDirection flowStepDirection, boolean isOnlyOneDocAvailable) {
        Intrinsics.checkNotNullParameter(nfcProperties, "nfcProperties");
        Intrinsics.checkNotNullParameter(flowStepDirection, "flowStepDirection");
        NfcHostFragment.Companion companion = NfcHostFragment.INSTANCE;
        NFCOptions nfcOptions = getOnfidoConfig$onfido_capture_sdk_core_release().getNfcOptions();
        Intrinsics.checkNotNull(nfcOptions, "null cannot be cast to non-null type com.onfido.android.sdk.capture.model.NFCOptions.Enabled");
        setFragment$default(this, companion.newInstance(Companion.RequestKey.KEY_REQUEST_NFC_HOST, documentType, countryCode, nfcProperties, (NFCOptions.Enabled) nfcOptions, isOnlyOneDocAvailable, (64 & 64) != 0 ? false : false), flowStepDirection, null, false, 12, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showNfcPermissionFragment(FlowStepDirection flowStepDirection) {
        Intrinsics.checkNotNullParameter(flowStepDirection, "flowStepDirection");
        setFragment$default(this, NfcPermissionFragment.INSTANCE.createInstance(), flowStepDirection, null, false, 12, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showPermissionsManagementFragment(CaptureStepDataBundle captureDataBundle, FlowStepDirection flowStepDirection) {
        Intrinsics.checkNotNullParameter(captureDataBundle, "captureDataBundle");
        Intrinsics.checkNotNullParameter(flowStepDirection, "flowStepDirection");
        setFragment$default(this, PermissionsManagementFragment.INSTANCE.createInstance(Companion.RequestKey.KEY_REQUEST_PERMISSIONS_MANAGEMENT, captureDataBundle), flowStepDirection, null, false, 4, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showProofOfAddressFlow(FlowStepDirection flowStepDirection) {
        Intrinsics.checkNotNullParameter(flowStepDirection, "flowStepDirection");
        setFragment$default(this, PoaHostFragment.Companion.createInstance$default(PoaHostFragment.INSTANCE, Companion.RequestKey.KEY_REQUEST_POA_HOST, false, 2, null), flowStepDirection, null, false, 12, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showProofOfAddressFlowWeb(HostedWebModuleModuleInfo hostedWebModuleModuleInfo, FlowStepDirection flowStepDirection) {
        Intrinsics.checkNotNullParameter(hostedWebModuleModuleInfo, "hostedWebModuleModuleInfo");
        Intrinsics.checkNotNullParameter(flowStepDirection, "flowStepDirection");
        setFragment$default(this, HostedWebModuleFragment.INSTANCE.newInstance(Companion.RequestKey.KEY_REQUEST_HOSTED_MODULE, hostedWebModuleModuleInfo, getIntent().getStringExtra(OnfidoConstants.ONFIDO_BRIDGE_URL), getIsDarkModeEnabled()), flowStepDirection, null, false, 12, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showUserConsentScreen(FlowStepDirection flowStepDirection) {
        Intrinsics.checkNotNullParameter(flowStepDirection, "flowStepDirection");
        setFragment$default(this, UserConsentFragment.INSTANCE.createInstance(Companion.RequestKey.KEY_REQUEST_USER_CONSENT), flowStepDirection, null, false, 12, null);
    }

    @Override // com.onfido.android.sdk.capture.ui.OnfidoView
    public void showWorkflowFragment(FlowConfig workflowConfig) {
        if (workflowConfig == null) {
            onError(new IllegalStateException("Workflow configuration is missing."));
        } else {
            hideToolbar();
            setFragment$default(this, workflowConfig.createFragment(), FlowStepDirection.NO_DIRECTION, null, false, 12, null);
        }
    }
}
