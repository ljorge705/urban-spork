package com.onfido.workflow.internal.ui.model;

import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.internal.navigation.Screen;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureScreen;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationFragment;
import com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureScreen;
import com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureScreen;
import com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment;
import com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostFragment;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.hosted.web.module.model.HostedWebModuleResult;
import com.onfido.workflow.internal.workflow.model.CaptureResult;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UIEvent.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0007\u0003\u0004\u0005\u0006\u0007\b\tB\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0007\n\u000b\f\r\u000e\u000f\u0010¨\u0006\u0011"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent;", "", "()V", "OnActivityResult", "OnBackPressed", "OnBackstackChange", "OnCreate", "OnFragmentResult", "OnStart", "OnStop", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnActivityResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnBackPressed;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnBackstackChange;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnCreate;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnStart;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnStop;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public abstract class UIEvent {
    public /* synthetic */ UIEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: UIEvent.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnCreate;", "Lcom/onfido/workflow/internal/ui/model/UIEvent;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class OnCreate extends UIEvent {
        public static final OnCreate INSTANCE = new OnCreate();

        private OnCreate() {
            super(null);
        }
    }

    private UIEvent() {
    }

    /* compiled from: UIEvent.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnStart;", "Lcom/onfido/workflow/internal/ui/model/UIEvent;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class OnStart extends UIEvent {
        public static final OnStart INSTANCE = new OnStart();

        private OnStart() {
            super(null);
        }
    }

    /* compiled from: UIEvent.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnStop;", "Lcom/onfido/workflow/internal/ui/model/UIEvent;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class OnStop extends UIEvent {
        public static final OnStop INSTANCE = new OnStop();

        private OnStop() {
            super(null);
        }
    }

    /* compiled from: UIEvent.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnActivityResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent;", "captureResult", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult;", "(Lcom/onfido/workflow/internal/workflow/model/CaptureResult;)V", "getCaptureResult", "()Lcom/onfido/workflow/internal/workflow/model/CaptureResult;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class OnActivityResult extends UIEvent {
        private final CaptureResult captureResult;

        public static /* synthetic */ OnActivityResult copy$default(OnActivityResult onActivityResult, CaptureResult captureResult, int i, Object obj) {
            if ((i & 1) != 0) {
                captureResult = onActivityResult.captureResult;
            }
            return onActivityResult.copy(captureResult);
        }

        /* renamed from: component1, reason: from getter */
        public final CaptureResult getCaptureResult() {
            return this.captureResult;
        }

        public final OnActivityResult copy(CaptureResult captureResult) {
            Intrinsics.checkNotNullParameter(captureResult, "captureResult");
            return new OnActivityResult(captureResult);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof OnActivityResult) && Intrinsics.areEqual(this.captureResult, ((OnActivityResult) other).captureResult);
        }

        public final CaptureResult getCaptureResult() {
            return this.captureResult;
        }

        public int hashCode() {
            return this.captureResult.hashCode();
        }

        public String toString() {
            return "OnActivityResult(captureResult=" + this.captureResult + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OnActivityResult(CaptureResult captureResult) {
            super(null);
            Intrinsics.checkNotNullParameter(captureResult, "captureResult");
            this.captureResult = captureResult;
        }
    }

    /* compiled from: UIEvent.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnBackstackChange;", "Lcom/onfido/workflow/internal/ui/model/UIEvent;", "backstackSnapshot", "", "Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", "(Ljava/util/List;)V", "getBackstackSnapshot", "()Ljava/util/List;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class OnBackstackChange extends UIEvent {
        private final List<Screen> backstackSnapshot;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ OnBackstackChange copy$default(OnBackstackChange onBackstackChange, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = onBackstackChange.backstackSnapshot;
            }
            return onBackstackChange.copy(list);
        }

        public final List<Screen> component1() {
            return this.backstackSnapshot;
        }

        public final OnBackstackChange copy(List<? extends Screen> backstackSnapshot) {
            Intrinsics.checkNotNullParameter(backstackSnapshot, "backstackSnapshot");
            return new OnBackstackChange(backstackSnapshot);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof OnBackstackChange) && Intrinsics.areEqual(this.backstackSnapshot, ((OnBackstackChange) other).backstackSnapshot);
        }

        public final List<Screen> getBackstackSnapshot() {
            return this.backstackSnapshot;
        }

        public int hashCode() {
            return this.backstackSnapshot.hashCode();
        }

        public String toString() {
            return "OnBackstackChange(backstackSnapshot=" + this.backstackSnapshot + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public OnBackstackChange(List<? extends Screen> backstackSnapshot) {
            super(null);
            Intrinsics.checkNotNullParameter(backstackSnapshot, "backstackSnapshot");
            this.backstackSnapshot = backstackSnapshot;
        }
    }

    /* compiled from: UIEvent.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnBackPressed;", "Lcom/onfido/workflow/internal/ui/model/UIEvent;", "backstackSnapshot", "", "Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", "(Ljava/util/List;)V", "getBackstackSnapshot", "()Ljava/util/List;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class OnBackPressed extends UIEvent {
        private final List<Screen> backstackSnapshot;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ OnBackPressed copy$default(OnBackPressed onBackPressed, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = onBackPressed.backstackSnapshot;
            }
            return onBackPressed.copy(list);
        }

        public final List<Screen> component1() {
            return this.backstackSnapshot;
        }

        public final OnBackPressed copy(List<? extends Screen> backstackSnapshot) {
            Intrinsics.checkNotNullParameter(backstackSnapshot, "backstackSnapshot");
            return new OnBackPressed(backstackSnapshot);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof OnBackPressed) && Intrinsics.areEqual(this.backstackSnapshot, ((OnBackPressed) other).backstackSnapshot);
        }

        public final List<Screen> getBackstackSnapshot() {
            return this.backstackSnapshot;
        }

        public int hashCode() {
            return this.backstackSnapshot.hashCode();
        }

        public String toString() {
            return "OnBackPressed(backstackSnapshot=" + this.backstackSnapshot + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public OnBackPressed(List<? extends Screen> backstackSnapshot) {
            super(null);
            Intrinsics.checkNotNullParameter(backstackSnapshot, "backstackSnapshot");
            this.backstackSnapshot = backstackSnapshot;
        }
    }

    /* compiled from: UIEvent.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u000f\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u000f\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f ¨\u0006!"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent;", "()V", "CountrySelectionFragmentResult", "DocumentSelectionFragmentResult", "LivenessConfirmationFragmentResult", "OnDocumentCaptureFragmentResult", "OnFaceLivenessCaptureFragmentResult", "OnFaceLivenessIntroFragmentResult", "OnFaceSelfieCaptureFragmentResult", "OnFaceSelfieIntroFragmentResult", "OnHostedWebModuleResult", "OnMotionResult", "OnNfcFlowResult", "OnNfcNotSupportedFragmentResult", "OnPermissionsScreenResult", "OnPoaSubmissionResult", "OnWorkflowRetryClick", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$CountrySelectionFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$DocumentSelectionFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$LivenessConfirmationFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnDocumentCaptureFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnFaceLivenessCaptureFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnFaceLivenessIntroFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnFaceSelfieCaptureFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnFaceSelfieIntroFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnHostedWebModuleResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnMotionResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnNfcFlowResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnNfcNotSupportedFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnPermissionsScreenResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnPoaSubmissionResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnWorkflowRetryClick;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class OnFragmentResult extends UIEvent {
        public /* synthetic */ OnFragmentResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private OnFragmentResult() {
            super(null);
        }

        /* compiled from: UIEvent.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$LivenessConfirmationFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult;", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessConfirmationFragment$LivenessConfirmationResult;", "(Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessConfirmationFragment$LivenessConfirmationResult;)V", "getResult", "()Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessConfirmationFragment$LivenessConfirmationResult;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class LivenessConfirmationFragmentResult extends OnFragmentResult {
            private final LivenessConfirmationFragment.LivenessConfirmationResult result;

            public static /* synthetic */ LivenessConfirmationFragmentResult copy$default(LivenessConfirmationFragmentResult livenessConfirmationFragmentResult, LivenessConfirmationFragment.LivenessConfirmationResult livenessConfirmationResult, int i, Object obj) {
                if ((i & 1) != 0) {
                    livenessConfirmationResult = livenessConfirmationFragmentResult.result;
                }
                return livenessConfirmationFragmentResult.copy(livenessConfirmationResult);
            }

            /* renamed from: component1, reason: from getter */
            public final LivenessConfirmationFragment.LivenessConfirmationResult getResult() {
                return this.result;
            }

            public final LivenessConfirmationFragmentResult copy(LivenessConfirmationFragment.LivenessConfirmationResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                return new LivenessConfirmationFragmentResult(result);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof LivenessConfirmationFragmentResult) && Intrinsics.areEqual(this.result, ((LivenessConfirmationFragmentResult) other).result);
            }

            public final LivenessConfirmationFragment.LivenessConfirmationResult getResult() {
                return this.result;
            }

            public int hashCode() {
                return this.result.hashCode();
            }

            public String toString() {
                return "LivenessConfirmationFragmentResult(result=" + this.result + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public LivenessConfirmationFragmentResult(LivenessConfirmationFragment.LivenessConfirmationResult result) {
                super(null);
                Intrinsics.checkNotNullParameter(result, "result");
                this.result = result;
            }
        }

        /* compiled from: UIEvent.kt */
        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$DocumentSelectionFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/utils/CountryCode;)V", "getCountryCode", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class DocumentSelectionFragmentResult extends OnFragmentResult {
            private final CountryCode countryCode;
            private final DocumentType documentType;

            public static /* synthetic */ DocumentSelectionFragmentResult copy$default(DocumentSelectionFragmentResult documentSelectionFragmentResult, DocumentType documentType, CountryCode countryCode, int i, Object obj) {
                if ((i & 1) != 0) {
                    documentType = documentSelectionFragmentResult.documentType;
                }
                if ((i & 2) != 0) {
                    countryCode = documentSelectionFragmentResult.countryCode;
                }
                return documentSelectionFragmentResult.copy(documentType, countryCode);
            }

            /* renamed from: component1, reason: from getter */
            public final DocumentType getDocumentType() {
                return this.documentType;
            }

            /* renamed from: component2, reason: from getter */
            public final CountryCode getCountryCode() {
                return this.countryCode;
            }

            public final DocumentSelectionFragmentResult copy(DocumentType documentType, CountryCode countryCode) {
                Intrinsics.checkNotNullParameter(documentType, "documentType");
                Intrinsics.checkNotNullParameter(countryCode, "countryCode");
                return new DocumentSelectionFragmentResult(documentType, countryCode);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof DocumentSelectionFragmentResult)) {
                    return false;
                }
                DocumentSelectionFragmentResult documentSelectionFragmentResult = (DocumentSelectionFragmentResult) other;
                return this.documentType == documentSelectionFragmentResult.documentType && this.countryCode == documentSelectionFragmentResult.countryCode;
            }

            public final CountryCode getCountryCode() {
                return this.countryCode;
            }

            public final DocumentType getDocumentType() {
                return this.documentType;
            }

            public int hashCode() {
                return (this.documentType.hashCode() * 31) + this.countryCode.hashCode();
            }

            public String toString() {
                return "DocumentSelectionFragmentResult(documentType=" + this.documentType + ", countryCode=" + this.countryCode + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public DocumentSelectionFragmentResult(DocumentType documentType, CountryCode countryCode) {
                super(null);
                Intrinsics.checkNotNullParameter(documentType, "documentType");
                Intrinsics.checkNotNullParameter(countryCode, "countryCode");
                this.documentType = documentType;
                this.countryCode = countryCode;
            }
        }

        /* compiled from: UIEvent.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$CountrySelectionFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "(Lcom/onfido/android/sdk/capture/utils/CountryCode;)V", "getCountryCode", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class CountrySelectionFragmentResult extends OnFragmentResult {
            private final CountryCode countryCode;

            public static /* synthetic */ CountrySelectionFragmentResult copy$default(CountrySelectionFragmentResult countrySelectionFragmentResult, CountryCode countryCode, int i, Object obj) {
                if ((i & 1) != 0) {
                    countryCode = countrySelectionFragmentResult.countryCode;
                }
                return countrySelectionFragmentResult.copy(countryCode);
            }

            /* renamed from: component1, reason: from getter */
            public final CountryCode getCountryCode() {
                return this.countryCode;
            }

            public final CountrySelectionFragmentResult copy(CountryCode countryCode) {
                Intrinsics.checkNotNullParameter(countryCode, "countryCode");
                return new CountrySelectionFragmentResult(countryCode);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof CountrySelectionFragmentResult) && this.countryCode == ((CountrySelectionFragmentResult) other).countryCode;
            }

            public final CountryCode getCountryCode() {
                return this.countryCode;
            }

            public int hashCode() {
                return this.countryCode.hashCode();
            }

            public String toString() {
                return "CountrySelectionFragmentResult(countryCode=" + this.countryCode + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public CountrySelectionFragmentResult(CountryCode countryCode) {
                super(null);
                Intrinsics.checkNotNullParameter(countryCode, "countryCode");
                this.countryCode = countryCode;
            }
        }

        /* compiled from: UIEvent.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnFaceSelfieIntroFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class OnFaceSelfieIntroFragmentResult extends OnFragmentResult {
            public static final OnFaceSelfieIntroFragmentResult INSTANCE = new OnFaceSelfieIntroFragmentResult();

            private OnFaceSelfieIntroFragmentResult() {
                super(null);
            }
        }

        /* compiled from: UIEvent.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnFaceSelfieCaptureFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult;", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureScreen$SelfieCaptureResult;", "(Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureScreen$SelfieCaptureResult;)V", "getResult", "()Lcom/onfido/android/sdk/capture/ui/camera/selfie/SelfieCaptureScreen$SelfieCaptureResult;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class OnFaceSelfieCaptureFragmentResult extends OnFragmentResult {
            private final SelfieCaptureScreen.SelfieCaptureResult result;

            public static /* synthetic */ OnFaceSelfieCaptureFragmentResult copy$default(OnFaceSelfieCaptureFragmentResult onFaceSelfieCaptureFragmentResult, SelfieCaptureScreen.SelfieCaptureResult selfieCaptureResult, int i, Object obj) {
                if ((i & 1) != 0) {
                    selfieCaptureResult = onFaceSelfieCaptureFragmentResult.result;
                }
                return onFaceSelfieCaptureFragmentResult.copy(selfieCaptureResult);
            }

            /* renamed from: component1, reason: from getter */
            public final SelfieCaptureScreen.SelfieCaptureResult getResult() {
                return this.result;
            }

            public final OnFaceSelfieCaptureFragmentResult copy(SelfieCaptureScreen.SelfieCaptureResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                return new OnFaceSelfieCaptureFragmentResult(result);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof OnFaceSelfieCaptureFragmentResult) && Intrinsics.areEqual(this.result, ((OnFaceSelfieCaptureFragmentResult) other).result);
            }

            public final SelfieCaptureScreen.SelfieCaptureResult getResult() {
                return this.result;
            }

            public int hashCode() {
                return this.result.hashCode();
            }

            public String toString() {
                return "OnFaceSelfieCaptureFragmentResult(result=" + this.result + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public OnFaceSelfieCaptureFragmentResult(SelfieCaptureScreen.SelfieCaptureResult result) {
                super(null);
                Intrinsics.checkNotNullParameter(result, "result");
                this.result = result;
            }
        }

        /* compiled from: UIEvent.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnFaceLivenessCaptureFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult;", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureScreen$LivenessCaptureResult;", "(Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureScreen$LivenessCaptureResult;)V", "getResult", "()Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureScreen$LivenessCaptureResult;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class OnFaceLivenessCaptureFragmentResult extends OnFragmentResult {
            private final LivenessCaptureScreen.LivenessCaptureResult result;

            public static /* synthetic */ OnFaceLivenessCaptureFragmentResult copy$default(OnFaceLivenessCaptureFragmentResult onFaceLivenessCaptureFragmentResult, LivenessCaptureScreen.LivenessCaptureResult livenessCaptureResult, int i, Object obj) {
                if ((i & 1) != 0) {
                    livenessCaptureResult = onFaceLivenessCaptureFragmentResult.result;
                }
                return onFaceLivenessCaptureFragmentResult.copy(livenessCaptureResult);
            }

            /* renamed from: component1, reason: from getter */
            public final LivenessCaptureScreen.LivenessCaptureResult getResult() {
                return this.result;
            }

            public final OnFaceLivenessCaptureFragmentResult copy(LivenessCaptureScreen.LivenessCaptureResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                return new OnFaceLivenessCaptureFragmentResult(result);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof OnFaceLivenessCaptureFragmentResult) && Intrinsics.areEqual(this.result, ((OnFaceLivenessCaptureFragmentResult) other).result);
            }

            public final LivenessCaptureScreen.LivenessCaptureResult getResult() {
                return this.result;
            }

            public int hashCode() {
                return this.result.hashCode();
            }

            public String toString() {
                return "OnFaceLivenessCaptureFragmentResult(result=" + this.result + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public OnFaceLivenessCaptureFragmentResult(LivenessCaptureScreen.LivenessCaptureResult result) {
                super(null);
                Intrinsics.checkNotNullParameter(result, "result");
                this.result = result;
            }
        }

        /* compiled from: UIEvent.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnFaceLivenessIntroFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class OnFaceLivenessIntroFragmentResult extends OnFragmentResult {
            public static final OnFaceLivenessIntroFragmentResult INSTANCE = new OnFaceLivenessIntroFragmentResult();

            private OnFaceLivenessIntroFragmentResult() {
                super(null);
            }
        }

        /* compiled from: UIEvent.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnDocumentCaptureFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult;", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult;", "(Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult;)V", "getResult", "()Lcom/onfido/android/sdk/capture/ui/camera/document/DocumentCaptureScreen$DocumentCaptureResult;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class OnDocumentCaptureFragmentResult extends OnFragmentResult {
            private final DocumentCaptureScreen.DocumentCaptureResult result;

            public static /* synthetic */ OnDocumentCaptureFragmentResult copy$default(OnDocumentCaptureFragmentResult onDocumentCaptureFragmentResult, DocumentCaptureScreen.DocumentCaptureResult documentCaptureResult, int i, Object obj) {
                if ((i & 1) != 0) {
                    documentCaptureResult = onDocumentCaptureFragmentResult.result;
                }
                return onDocumentCaptureFragmentResult.copy(documentCaptureResult);
            }

            /* renamed from: component1, reason: from getter */
            public final DocumentCaptureScreen.DocumentCaptureResult getResult() {
                return this.result;
            }

            public final OnDocumentCaptureFragmentResult copy(DocumentCaptureScreen.DocumentCaptureResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                return new OnDocumentCaptureFragmentResult(result);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof OnDocumentCaptureFragmentResult) && Intrinsics.areEqual(this.result, ((OnDocumentCaptureFragmentResult) other).result);
            }

            public final DocumentCaptureScreen.DocumentCaptureResult getResult() {
                return this.result;
            }

            public int hashCode() {
                return this.result.hashCode();
            }

            public String toString() {
                return "OnDocumentCaptureFragmentResult(result=" + this.result + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public OnDocumentCaptureFragmentResult(DocumentCaptureScreen.DocumentCaptureResult result) {
                super(null);
                Intrinsics.checkNotNullParameter(result, "result");
                this.result = result;
            }
        }

        /* compiled from: UIEvent.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnPermissionsScreenResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult;", "()V", "Canceled", "Granted", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnPermissionsScreenResult$Canceled;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnPermissionsScreenResult$Granted;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static abstract class OnPermissionsScreenResult extends OnFragmentResult {
            public /* synthetic */ OnPermissionsScreenResult(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* compiled from: UIEvent.kt */
            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnPermissionsScreenResult$Granted;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnPermissionsScreenResult;", "captureStepDataBundle", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "(Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;)V", "getCaptureStepDataBundle", "()Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final /* data */ class Granted extends OnPermissionsScreenResult {
                private final CaptureStepDataBundle captureStepDataBundle;

                public static /* synthetic */ Granted copy$default(Granted granted, CaptureStepDataBundle captureStepDataBundle, int i, Object obj) {
                    if ((i & 1) != 0) {
                        captureStepDataBundle = granted.captureStepDataBundle;
                    }
                    return granted.copy(captureStepDataBundle);
                }

                /* renamed from: component1, reason: from getter */
                public final CaptureStepDataBundle getCaptureStepDataBundle() {
                    return this.captureStepDataBundle;
                }

                public final Granted copy(CaptureStepDataBundle captureStepDataBundle) {
                    Intrinsics.checkNotNullParameter(captureStepDataBundle, "captureStepDataBundle");
                    return new Granted(captureStepDataBundle);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    return (other instanceof Granted) && Intrinsics.areEqual(this.captureStepDataBundle, ((Granted) other).captureStepDataBundle);
                }

                public final CaptureStepDataBundle getCaptureStepDataBundle() {
                    return this.captureStepDataBundle;
                }

                public int hashCode() {
                    return this.captureStepDataBundle.hashCode();
                }

                public String toString() {
                    return "Granted(captureStepDataBundle=" + this.captureStepDataBundle + ")";
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public Granted(CaptureStepDataBundle captureStepDataBundle) {
                    super(null);
                    Intrinsics.checkNotNullParameter(captureStepDataBundle, "captureStepDataBundle");
                    this.captureStepDataBundle = captureStepDataBundle;
                }
            }

            private OnPermissionsScreenResult() {
                super(null);
            }

            /* compiled from: UIEvent.kt */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnPermissionsScreenResult$Canceled;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnPermissionsScreenResult;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final class Canceled extends OnPermissionsScreenResult {
                public static final Canceled INSTANCE = new Canceled();

                private Canceled() {
                    super(null);
                }
            }
        }

        /* compiled from: UIEvent.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnNfcFlowResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult;", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult;", "(Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult;)V", "getResult", "()Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class OnNfcFlowResult extends OnFragmentResult {
            private final NfcHostFragment.NfcHostResult result;

            public final NfcHostFragment.NfcHostResult getResult() {
                return this.result;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public OnNfcFlowResult(NfcHostFragment.NfcHostResult result) {
                super(null);
                Intrinsics.checkNotNullParameter(result, "result");
                this.result = result;
            }
        }

        /* compiled from: UIEvent.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnWorkflowRetryClick;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class OnWorkflowRetryClick extends OnFragmentResult {
            public static final OnWorkflowRetryClick INSTANCE = new OnWorkflowRetryClick();

            private OnWorkflowRetryClick() {
                super(null);
            }
        }

        /* compiled from: UIEvent.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnPoaSubmissionResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult;", "poaResult", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/host/PoaHostFragment$PoaResult;", "(Lcom/onfido/android/sdk/capture/ui/proofOfAddress/host/PoaHostFragment$PoaResult;)V", "getPoaResult", "()Lcom/onfido/android/sdk/capture/ui/proofOfAddress/host/PoaHostFragment$PoaResult;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class OnPoaSubmissionResult extends OnFragmentResult {
            private final PoaHostFragment.PoaResult poaResult;

            public final PoaHostFragment.PoaResult getPoaResult() {
                return this.poaResult;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public OnPoaSubmissionResult(PoaHostFragment.PoaResult poaResult) {
                super(null);
                Intrinsics.checkNotNullParameter(poaResult, "poaResult");
                this.poaResult = poaResult;
            }
        }

        /* compiled from: UIEvent.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnMotionResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult;", "avcHostResult", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment$AvcHostResult;", "(Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment$AvcHostResult;)V", "getAvcHostResult", "()Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/host/MotionHostFragment$AvcHostResult;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class OnMotionResult extends OnFragmentResult {
            private final MotionHostFragment.AvcHostResult avcHostResult;

            public final MotionHostFragment.AvcHostResult getAvcHostResult() {
                return this.avcHostResult;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public OnMotionResult(MotionHostFragment.AvcHostResult avcHostResult) {
                super(null);
                Intrinsics.checkNotNullParameter(avcHostResult, "avcHostResult");
                this.avcHostResult = avcHostResult;
            }
        }

        /* compiled from: UIEvent.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnHostedWebModuleResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult;", "hostedWebModuleResult", "Lcom/onfido/hosted/web/module/model/HostedWebModuleResult;", "(Lcom/onfido/hosted/web/module/model/HostedWebModuleResult;)V", "getHostedWebModuleResult", "()Lcom/onfido/hosted/web/module/model/HostedWebModuleResult;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class OnHostedWebModuleResult extends OnFragmentResult {
            private final HostedWebModuleResult hostedWebModuleResult;

            public final HostedWebModuleResult getHostedWebModuleResult() {
                return this.hostedWebModuleResult;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public OnHostedWebModuleResult(HostedWebModuleResult hostedWebModuleResult) {
                super(null);
                Intrinsics.checkNotNullParameter(hostedWebModuleResult, "hostedWebModuleResult");
                this.hostedWebModuleResult = hostedWebModuleResult;
            }
        }

        /* compiled from: UIEvent.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnNfcNotSupportedFragmentResult;", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult;", "()V", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class OnNfcNotSupportedFragmentResult extends OnFragmentResult {
            public static final OnNfcNotSupportedFragmentResult INSTANCE = new OnNfcNotSupportedFragmentResult();

            private OnNfcNotSupportedFragmentResult() {
                super(null);
            }
        }
    }
}
