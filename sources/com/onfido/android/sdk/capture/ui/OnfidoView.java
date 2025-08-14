package com.onfido.android.sdk.capture.ui;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.FlowConfig;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.ExitCode;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.flow.FlowStepDirection;
import com.onfido.android.sdk.capture.flow.NfcArguments;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessPerformedChallenges;
import com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties;
import com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.hosted.web.module.model.HostedWebModuleModuleInfo;
import io.sentry.SentryEvent;
import io.sentry.rrweb.RRWebOptionsEvent;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&J\u0014\u0010\n\u001a\u00020\u00032\n\u0010\u000b\u001a\u00060\fj\u0002`\rH&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&J(\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H&J\u0010\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&J \u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH&J\u0012\u0010\u001f\u001a\u00020\u00032\b\u0010 \u001a\u0004\u0018\u00010!H&J\u0010\u0010\"\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&J\b\u0010#\u001a\u00020\u0003H&J\u001a\u0010$\u001a\u00020\u00032\b\b\u0002\u0010%\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0010\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\u0013H&J\b\u0010(\u001a\u00020\u0003H&J \u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u00132\u0006\u0010+\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0010H&J\u001a\u0010,\u001a\u00020\u00032\b\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010\u000f\u001a\u00020\u0010H&J\b\u0010/\u001a\u00020\u0003H&J4\u00100\u001a\u00020\u00032\b\u0010 \u001a\u0004\u0018\u00010!2\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u0002042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u00105\u001a\u00020\u0017H&J\u0010\u00106\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0018\u00107\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0010\u00108\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0018\u00109\u001a\u00020\u00032\u0006\u0010:\u001a\u00020;2\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0010\u0010<\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0012\u0010=\u001a\u00020\u00032\b\u0010>\u001a\u0004\u0018\u00010?H&¨\u0006@"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/OnfidoView;", "", "completeFlow", "", "enableToolbarBackNavigation", "exitFlow", "exitCode", "Lcom/onfido/android/sdk/capture/ExitCode;", "hideLoadingDialog", "hideLoadingView", "onError", SentryEvent.JsonKeys.EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "showCaptureFaceMessage", "flowStepDirection", "Lcom/onfido/android/sdk/capture/flow/FlowStepDirection;", "showCaptureLivenessConfirmation", "videoFilePath", "", "livenessPerformedChallenges", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessPerformedChallenges;", "showConfirmationVideo", "", "showDeviceNotSupportedFragment", "showDocumentCapture", "isFrontSide", "captureDataBundle", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "nfcArguments", "Lcom/onfido/android/sdk/capture/flow/NfcArguments;", "showFaceCapture", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "showFinalScreen", "showLivenessCapture", "showLivenessIntro", "showIntroVideo", "showLoadingDialog", "reason", "showLoadingView", "showMessageScreen", "title", "message", "showMotionFlow", RRWebOptionsEvent.EVENT_TAG, "Lcom/onfido/android/sdk/capture/ui/options/MotionCaptureVariantOptions;", "showNetworkError", "showNfcHostFragment", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "nfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "isOnlyOneDocAvailable", "showNfcPermissionFragment", "showPermissionsManagementFragment", "showProofOfAddressFlow", "showProofOfAddressFlowWeb", "hostedWebModuleModuleInfo", "Lcom/onfido/hosted/web/module/model/HostedWebModuleModuleInfo;", "showUserConsentScreen", "showWorkflowFragment", "workflowConfig", "Lcom/onfido/android/sdk/FlowConfig;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface OnfidoView {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void showLivenessIntro$default(OnfidoView onfidoView, boolean z, FlowStepDirection flowStepDirection, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showLivenessIntro");
            }
            if ((i & 1) != 0) {
                z = true;
            }
            onfidoView.showLivenessIntro(z, flowStepDirection);
        }
    }

    void completeFlow();

    void enableToolbarBackNavigation();

    void exitFlow(ExitCode exitCode);

    void hideLoadingDialog();

    void hideLoadingView();

    void onError(Exception exception);

    void showCaptureFaceMessage(FlowStepDirection flowStepDirection);

    void showCaptureLivenessConfirmation(FlowStepDirection flowStepDirection, String videoFilePath, LivenessPerformedChallenges livenessPerformedChallenges, boolean showConfirmationVideo);

    void showDeviceNotSupportedFragment(FlowStepDirection flowStepDirection);

    void showDocumentCapture(boolean isFrontSide, CaptureStepDataBundle captureDataBundle, NfcArguments nfcArguments);

    void showFaceCapture(DocumentType documentType);

    void showFinalScreen(FlowStepDirection flowStepDirection);

    void showLivenessCapture();

    void showLivenessIntro(boolean showIntroVideo, FlowStepDirection flowStepDirection);

    void showLoadingDialog(String reason);

    void showLoadingView();

    void showMessageScreen(String title, String message, FlowStepDirection flowStepDirection);

    void showMotionFlow(MotionCaptureVariantOptions options, FlowStepDirection flowStepDirection);

    void showNetworkError();

    void showNfcHostFragment(DocumentType documentType, CountryCode countryCode, NfcProperties nfcProperties, FlowStepDirection flowStepDirection, boolean isOnlyOneDocAvailable);

    void showNfcPermissionFragment(FlowStepDirection flowStepDirection);

    void showPermissionsManagementFragment(CaptureStepDataBundle captureDataBundle, FlowStepDirection flowStepDirection);

    void showProofOfAddressFlow(FlowStepDirection flowStepDirection);

    void showProofOfAddressFlowWeb(HostedWebModuleModuleInfo hostedWebModuleModuleInfo, FlowStepDirection flowStepDirection);

    void showUserConsentScreen(FlowStepDirection flowStepDirection);

    void showWorkflowFragment(FlowConfig workflowConfig);
}
