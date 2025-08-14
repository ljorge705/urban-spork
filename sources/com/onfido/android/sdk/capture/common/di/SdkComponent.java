package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import com.onfido.android.sdk.FlowConfig;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.common.cryptography.PayloadHelper;
import com.onfido.android.sdk.capture.common.di.network.NetworkModule;
import com.onfido.android.sdk.capture.common.permissions.PermissionsManagementFragment;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.metadata.SdkUploadMetadataHelper;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.internal.nfc.NfcService;
import com.onfido.android.sdk.capture.internal.token.OnfidoTokenProvider;
import com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionFragment;
import com.onfido.android.sdk.capture.internal.ui.countryselection.OnfidoSupportedDocumentsRepository;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.CrashHandlerWorker;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.android.sdk.capture.ui.BaseActivity;
import com.onfido.android.sdk.capture.ui.OnfidoActivity;
import com.onfido.android.sdk.capture.ui.camera.CaptureActivity;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureFragment;
import com.onfido.android.sdk.capture.ui.camera.face.FaceConfirmationFragment;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationFragment;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessIntroFragment;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayView;
import com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureFragment;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.loading.LivenessChallengesLoadingView;
import com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureFragment;
import com.onfido.android.sdk.capture.ui.camera.util.CameraPermissionsUtils;
import com.onfido.android.sdk.capture.ui.documentselection.di.RestrictedDocumentSelectionHostComponent;
import com.onfido.android.sdk.capture.ui.faceintro.FaceIntroFragment;
import com.onfido.android.sdk.capture.ui.finalscreen.FinalScreenFragment;
import com.onfido.android.sdk.capture.ui.nfc.NfcCanMaxAttemptsFragment;
import com.onfido.android.sdk.capture.ui.nfc.NfcDeviceNotSupportedFragment;
import com.onfido.android.sdk.capture.ui.nfc.NfcDocumentNotSupportedFragment;
import com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment;
import com.onfido.android.sdk.capture.ui.nfc.NfcScanFailFragment;
import com.onfido.android.sdk.capture.ui.nfc.permission.NfcPermissionFragment;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment;
import com.onfido.android.sdk.capture.ui.proofOfAddress.di.PoaComponent;
import com.onfido.android.sdk.capture.ui.userconsent.UserConsentFragment;
import com.onfido.android.sdk.capture.ui.welcome.WelcomeFragment;
import com.onfido.android.sdk.capture.utils.LoadingFragment;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.android.sdk.capture.validation.OnfidoCaptureValidationBubble;
import com.onfido.android.sdk.workflow.internal.workflow.tasks.documentupload.WorkflowSupportedDocumentsStore;
import com.onfido.api.client.OnfidoFetcher;
import com.onfido.api.client.token.sdk.ApplicantId;
import com.onfido.dagger.Component;
import com.onfido.javax.inject.Singleton;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlinx.serialization.json.Json;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Component(modules = {SdkModule.class, NetworkModule.class, SdkProductionModule.class, SdkCaptureProductionModule.class, SdkBindsModule.class})
@Metadata(d1 = {"\u0000À\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H'J\b\u0010\u0005\u001a\u00020\u0006H&J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0018H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001aH ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001cH ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001eH ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001fH ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020 H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020!H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\"H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020#H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020$H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020%H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020&H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020'H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010(\u001a\u00020)H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020*H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020+H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020,H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020-H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020.H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020/H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u000200H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u000201H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u000202H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u000203H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u000204H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u000205H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u000206H ¢\u0006\u0002\b\u0015J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u00107\u001a\u000208H ¢\u0006\u0002\b\u0015J\b\u00109\u001a\u00020:H&J\b\u0010;\u001a\u00020<H&J\b\u0010=\u001a\u00020>H&J\b\u0010?\u001a\u00020@H&J\b\u0010A\u001a\u00020BH&J\b\u0010C\u001a\u00020DH&J\b\u0010E\u001a\u00020FH&J\b\u0010G\u001a\u00020HH&J\r\u0010I\u001a\u00020JH ¢\u0006\u0002\bKJ\b\u0010L\u001a\u00020MH&J\b\u0010N\u001a\u00020OH'J\b\u0010P\u001a\u00020QH&J\r\u0010R\u001a\u00020SH ¢\u0006\u0002\bTJ\r\u0010U\u001a\u00020VH ¢\u0006\u0002\bWJ\r\u0010X\u001a\u00020YH ¢\u0006\u0002\bZJ\r\u0010[\u001a\u00020\\H ¢\u0006\u0002\b]J\b\u0010^\u001a\u00020_H&J\b\u0010`\u001a\u00020aH&J\b\u0010b\u001a\u00020cH&¨\u0006d"}, d2 = {"Lcom/onfido/android/sdk/capture/common/di/SdkComponent;", "", "()V", "applicationContext", "Landroid/content/Context;", "dispatchersProvider", "Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;", "flowConfig", "Lcom/onfido/android/sdk/FlowConfig;", "flowTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/FlowTracker;", "getJsonParser", "Lkotlinx/serialization/json/Json;", "getOkHttpClient", "Lokhttp3/OkHttpClient;", "getRetrofit", "Lretrofit2/Retrofit;", "inject", "", Request.JsonKeys.FRAGMENT, "Lcom/onfido/android/sdk/capture/common/permissions/PermissionsManagementFragment;", "inject$onfido_capture_sdk_core_release", "service", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcService;", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/CountrySelectionFragment;", "crashHandlerWorker", "Lcom/onfido/android/sdk/capture/internal/util/logging/CrashHandlerWorker;", "baseActivity", "Lcom/onfido/android/sdk/capture/ui/BaseActivity;", "activity", "Lcom/onfido/android/sdk/capture/ui/OnfidoActivity;", "Lcom/onfido/android/sdk/capture/ui/camera/CaptureActivity;", "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureFragment;", "Lcom/onfido/android/sdk/capture/ui/camera/face/FaceConfirmationFragment;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessConfirmationFragment;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessIntroFragment;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessOverlayView;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureFragment;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/loading/LivenessChallengesLoadingView;", "Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureFragment;", "cameraPermissionsUtils", "Lcom/onfido/android/sdk/capture/ui/camera/util/CameraPermissionsUtils;", "Lcom/onfido/android/sdk/capture/ui/faceintro/FaceIntroFragment;", "Lcom/onfido/android/sdk/capture/ui/finalscreen/FinalScreenFragment;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcCanMaxAttemptsFragment;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcDeviceNotSupportedFragment;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcDocumentNotSupportedFragment;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcScanFailFragment;", "Lcom/onfido/android/sdk/capture/ui/nfc/permission/NfcPermissionFragment;", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcCanEntryFragment;", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanFragment;", "Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentFragment;", "Lcom/onfido/android/sdk/capture/ui/welcome/WelcomeFragment;", "Lcom/onfido/android/sdk/capture/utils/LoadingFragment;", "view", "Lcom/onfido/android/sdk/capture/validation/OnfidoCaptureValidationBubble;", "nativeDetector", "Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;", "onfidoAnalytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "onfidoApiService", "Lcom/onfido/android/sdk/capture/network/OnfidoApiService;", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "onfidoFetcher", "Lcom/onfido/api/client/OnfidoFetcher;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "onfidoSupportedDocumentsRepository", "Lcom/onfido/android/sdk/capture/internal/ui/countryselection/OnfidoSupportedDocumentsRepository;", "onfidoTokenProvider", "Lcom/onfido/android/sdk/capture/internal/token/OnfidoTokenProvider;", "poaComponentFactory", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/di/PoaComponent$Factory;", "poaComponentFactory$onfido_capture_sdk_core_release", "provideApplicantId", "Lcom/onfido/api/client/token/sdk/ApplicantId;", "provideCustomerUserHash", "", "provideNfcInteractor", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;", "providePayloadHelper", "Lcom/onfido/android/sdk/capture/common/cryptography/PayloadHelper;", "providePayloadHelper$onfido_capture_sdk_core_release", "provideSdkUploadMetadataHelper", "Lcom/onfido/android/sdk/capture/internal/metadata/SdkUploadMetadataHelper;", "provideSdkUploadMetadataHelper$onfido_capture_sdk_core_release", "provideTimeProvider", "Lcom/onfido/android/sdk/capture/utils/TimeProvider;", "provideTimeProvider$onfido_capture_sdk_core_release", "rdsHostComponentFactory", "Lcom/onfido/android/sdk/capture/ui/documentselection/di/RestrictedDocumentSelectionHostComponent$Factory;", "rdsHostComponentFactory$onfido_capture_sdk_core_release", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "waitingScreenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/WaitingScreenTracker;", "workflowSupportedDocumentsStore", "Lcom/onfido/android/sdk/workflow/internal/workflow/tasks/documentupload/WorkflowSupportedDocumentsStore;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Singleton
/* loaded from: classes2.dex */
public abstract class SdkComponent {
    public abstract Context applicationContext();

    public abstract DispatchersProvider dispatchersProvider();

    public abstract FlowConfig flowConfig();

    public abstract FlowTracker flowTracker();

    public abstract Json getJsonParser();

    public abstract OkHttpClient getOkHttpClient();

    public abstract Retrofit getRetrofit();

    public abstract void inject$onfido_capture_sdk_core_release(PermissionsManagementFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(NfcService service);

    public abstract void inject$onfido_capture_sdk_core_release(CountrySelectionFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(CrashHandlerWorker crashHandlerWorker);

    public abstract void inject$onfido_capture_sdk_core_release(BaseActivity baseActivity);

    public abstract void inject$onfido_capture_sdk_core_release(OnfidoActivity activity);

    public abstract void inject$onfido_capture_sdk_core_release(CaptureActivity activity);

    public abstract void inject$onfido_capture_sdk_core_release(DocumentCaptureFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(FaceConfirmationFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(LivenessConfirmationFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(LivenessIntroFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(LivenessOverlayView fragment);

    public abstract void inject$onfido_capture_sdk_core_release(LivenessCaptureFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(LivenessChallengesLoadingView fragment);

    public abstract void inject$onfido_capture_sdk_core_release(SelfieCaptureFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(CameraPermissionsUtils cameraPermissionsUtils);

    public abstract void inject$onfido_capture_sdk_core_release(FaceIntroFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(FinalScreenFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(NfcCanMaxAttemptsFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(NfcDeviceNotSupportedFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(NfcDocumentNotSupportedFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(NfcHostFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(NfcScanFailFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(NfcPermissionFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(NfcCanEntryFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(NfcScanFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(UserConsentFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(WelcomeFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(LoadingFragment fragment);

    public abstract void inject$onfido_capture_sdk_core_release(OnfidoCaptureValidationBubble view);

    public abstract NativeDetector nativeDetector();

    public abstract OnfidoAnalytics onfidoAnalytics();

    public abstract OnfidoApiService onfidoApiService();

    public abstract OnfidoConfig onfidoConfig();

    public abstract OnfidoFetcher onfidoFetcher();

    public abstract OnfidoRemoteConfig onfidoRemoteConfig();

    public abstract OnfidoSupportedDocumentsRepository onfidoSupportedDocumentsRepository();

    public abstract OnfidoTokenProvider onfidoTokenProvider();

    public abstract PoaComponent.Factory poaComponentFactory$onfido_capture_sdk_core_release();

    public abstract ApplicantId provideApplicantId();

    public abstract String provideCustomerUserHash();

    public abstract NfcInteractor provideNfcInteractor();

    public abstract PayloadHelper providePayloadHelper$onfido_capture_sdk_core_release();

    public abstract SdkUploadMetadataHelper provideSdkUploadMetadataHelper$onfido_capture_sdk_core_release();

    public abstract TimeProvider provideTimeProvider$onfido_capture_sdk_core_release();

    public abstract RestrictedDocumentSelectionHostComponent.Factory rdsHostComponentFactory$onfido_capture_sdk_core_release();

    public abstract SchedulersProvider schedulersProvider();

    public abstract WaitingScreenTracker waitingScreenTracker();

    public abstract WorkflowSupportedDocumentsStore workflowSupportedDocumentsStore();
}
