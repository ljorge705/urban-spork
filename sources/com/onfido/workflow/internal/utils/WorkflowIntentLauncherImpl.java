package com.onfido.workflow.internal.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.internal.OnfidoConstants;
import com.onfido.android.sdk.capture.internal.model.UploadedArtifact;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.navigation.screens.EmptyScreen;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.OnfidoActivity;
import com.onfido.android.sdk.capture.ui.camera.CaptureActivity;
import com.onfido.android.sdk.capture.ui.camera.exception.UnknownCameraException;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationFragment;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessPerformedChallenges;
import com.onfido.android.sdk.capture.ui.camera.util.IntentHelper;
import com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties;
import com.onfido.api.client.data.DocSide;
import com.onfido.dagger.assisted.Assisted;
import com.onfido.dagger.assisted.AssistedInject;
import com.onfido.workflow.internal.ui.model.OneOffUIEvent;
import com.onfido.workflow.internal.utils.WorkflowIntentLauncher;
import com.onfido.workflow.internal.workflow.model.CaptureResult;
import com.onfido.workflow.internal.workflow.model.DocumentUploadPayload;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.io.Serializable;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkflowIntentLauncherImpl.kt */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 ,2\u00020\u0001:\u0001,B+\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ4\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0012H\u0002J\u0010\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020\u00142\u0006\u0010$\u001a\u00020%H\u0016J\u000e\u0010&\u001a\b\u0012\u0004\u0012\u00020\r0'H\u0016J\u0010\u0010(\u001a\u00020\u00142\u0006\u0010)\u001a\u00020*H\u0016J\u0010\u0010+\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u000b\u001a\u0015\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\r0\r0\f¢\u0006\u0002\b\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/onfido/workflow/internal/utils/WorkflowIntentLauncherImpl;", "Lcom/onfido/workflow/internal/utils/WorkflowIntentLauncher;", "context", "Landroid/content/Context;", "navigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "activityResultRegistry", "Landroidx/activity/result/ActivityResultRegistry;", "(Landroid/content/Context;Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;Lcom/onfido/android/sdk/capture/OnfidoConfig;Landroidx/activity/result/ActivityResultRegistry;)V", "captureResultSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "launcher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "handleDocumentCaptureResult", "", "captureStepBundle", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "documentId", "", "documentVideoId", "nfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "nfcSupported", "", "handleFaceLivenessResult", "resultIntent", "handleFacePhotoResult", "activityResult", "Landroidx/activity/result/ActivityResult;", "launchCaptureFlow", "uiEvent", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent;", "observeResult", "Lio/reactivex/rxjava3/core/Observable;", "onCreate", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onResultOk", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WorkflowIntentLauncherImpl implements WorkflowIntentLauncher {
    private static final Companion Companion = new Companion(null);
    private static final String KEY_WORKFLOW = "workflow";
    private final ActivityResultRegistry activityResultRegistry;
    private final PublishSubject<CaptureResult> captureResultSubject;
    private final Context context;
    private ActivityResultLauncher<Intent> launcher;
    private final Navigator navigator;
    private final OnfidoConfig onfidoConfig;

    @AssistedInject
    public WorkflowIntentLauncherImpl(Context context, Navigator navigator, OnfidoConfig onfidoConfig, @Assisted ActivityResultRegistry activityResultRegistry) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        Intrinsics.checkNotNullParameter(activityResultRegistry, "activityResultRegistry");
        this.context = context;
        this.navigator = navigator;
        this.onfidoConfig = onfidoConfig;
        this.activityResultRegistry = activityResultRegistry;
        PublishSubject<CaptureResult> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create(...)");
        this.captureResultSubject = publishSubjectCreate;
    }

    @Override // com.onfido.android.sdk.capture.internal.util.DefaultLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(LifecycleOwner lifecycleOwner) {
        WorkflowIntentLauncher.DefaultImpls.onDestroy(this, lifecycleOwner);
    }

    @Override // com.onfido.android.sdk.capture.internal.util.DefaultLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(LifecycleOwner lifecycleOwner) {
        WorkflowIntentLauncher.DefaultImpls.onPause(this, lifecycleOwner);
    }

    @Override // com.onfido.android.sdk.capture.internal.util.DefaultLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(LifecycleOwner lifecycleOwner) {
        WorkflowIntentLauncher.DefaultImpls.onResume(this, lifecycleOwner);
    }

    @Override // com.onfido.android.sdk.capture.internal.util.DefaultLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(LifecycleOwner lifecycleOwner) {
        WorkflowIntentLauncher.DefaultImpls.onStart(this, lifecycleOwner);
    }

    @Override // com.onfido.android.sdk.capture.internal.util.DefaultLifecycleObserver
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(LifecycleOwner lifecycleOwner) {
        WorkflowIntentLauncher.DefaultImpls.onStop(this, lifecycleOwner);
    }

    @Override // com.onfido.workflow.internal.utils.WorkflowIntentLauncher
    public Observable<CaptureResult> observeResult() {
        Observable<CaptureResult> observableHide = this.captureResultSubject.hide();
        Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
        return observableHide;
    }

    @Override // com.onfido.android.sdk.capture.internal.util.DefaultLifecycleObserver
    public void onCreate(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        WorkflowIntentLauncher.DefaultImpls.onCreate(this, owner);
        ActivityResultLauncher<Intent> activityResultLauncherRegister = this.activityResultRegistry.register(KEY_WORKFLOW, owner, new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.onfido.workflow.internal.utils.WorkflowIntentLauncherImpl$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                WorkflowIntentLauncherImpl.onCreate$lambda$0(this.f$0, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegister, "register(...)");
        this.launcher = activityResultLauncherRegister;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(WorkflowIntentLauncherImpl this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.navigator.exitCurrentScreen();
        int resultCode = activityResult.getResultCode();
        if (resultCode == -2) {
            Intent data = activityResult.getData();
            if (data == null) {
                return;
            }
            Serializable serializableExtra = data.getSerializableExtra(OnfidoConstants.ONFIDO_EXCEPTION_RESULT);
            UnknownCameraException unknownCameraException = serializableExtra instanceof UnknownCameraException ? (UnknownCameraException) serializableExtra : null;
            if (unknownCameraException != null) {
                this$0.captureResultSubject.onNext(new CaptureResult.ErrorResult.UnknownCamera(unknownCameraException));
                return;
            }
            return;
        }
        if (resultCode == -1) {
            Intrinsics.checkNotNull(activityResult);
            this$0.onResultOk(activityResult);
            return;
        }
        if (resultCode == 0) {
            this$0.captureResultSubject.onNext(CaptureResult.Cancelled.INSTANCE);
            return;
        }
        if (resultCode != 445) {
            if (resultCode == 446) {
                this$0.captureResultSubject.onNext(CaptureResult.ErrorResult.OnTokenExpired.INSTANCE);
                return;
            } else {
                if (resultCode != 448) {
                    return;
                }
                this$0.captureResultSubject.onNext(CaptureResult.Exit.INSTANCE);
                return;
            }
        }
        Intent data2 = activityResult.getData();
        if (data2 == null) {
            return;
        }
        String stringExtra = data2.getStringExtra(OnfidoActivity.INVALID_CERTIFICATE_MESSAGE_EXTRA);
        if (stringExtra == null) {
            stringExtra = "";
        }
        this$0.captureResultSubject.onNext(new CaptureResult.ErrorResult.InvalidSSLCertificate(stringExtra));
    }

    static /* synthetic */ void handleDocumentCaptureResult$default(WorkflowIntentLauncherImpl workflowIntentLauncherImpl, CaptureStepDataBundle captureStepDataBundle, String str, String str2, NfcProperties nfcProperties, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            nfcProperties = null;
        }
        workflowIntentLauncherImpl.handleDocumentCaptureResult(captureStepDataBundle, str, str2, nfcProperties, z);
    }

    private final void handleDocumentCaptureResult(CaptureStepDataBundle captureStepBundle, String documentId, String documentVideoId, NfcProperties nfcProperties, boolean nfcSupported) {
        DocumentUploadPayload.UploadedArtifactPayload uploadedArtifactPayload;
        if (nfcProperties != null) {
            uploadedArtifactPayload = new DocumentUploadPayload.ScannedNfcPayload(documentId, documentVideoId, nfcProperties, nfcSupported);
        } else {
            uploadedArtifactPayload = new DocumentUploadPayload.UploadedArtifactPayload(documentId, documentVideoId);
        }
        this.captureResultSubject.onNext(new CaptureResult.DocumentUpload(captureStepBundle, uploadedArtifactPayload));
    }

    private final void handleFacePhotoResult(ActivityResult activityResult) {
        Bundle extras;
        Intent data = activityResult.getData();
        Set<String> setKeySet = null;
        String uploadedFileId = data != null ? CaptureActivity.INSTANCE.getUploadedFileId(data) : null;
        Intent data2 = activityResult.getData();
        UploadedArtifact uploadedArtifact = data2 != null ? CaptureActivity.INSTANCE.getUploadedArtifact(data2) : null;
        if (uploadedFileId == null || uploadedArtifact == null) {
            Timber.Companion companion = Timber.INSTANCE;
            Intent data3 = activityResult.getData();
            if (data3 != null && (extras = data3.getExtras()) != null) {
                setKeySet = extras.keySet();
            }
            companion.i("Failed to parse intent that contains " + setKeySet, new Object[0]);
            return;
        }
        this.captureResultSubject.onNext(new CaptureResult.FaceUploadPhoto(uploadedArtifact));
    }

    private final void handleFaceLivenessResult(Intent resultIntent) {
        String stringExtra = resultIntent.getStringExtra("video_path");
        if (stringExtra == null) {
            return;
        }
        Serializable serializableExtra = resultIntent.getSerializableExtra(LivenessConfirmationFragment.ONFIDO_LIVENESS_CHALLENGES_EXTRA);
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessPerformedChallenges");
        this.captureResultSubject.onNext(new CaptureResult.FaceUploadVideo(stringExtra, (LivenessPerformedChallenges) serializableExtra));
    }

    @Override // com.onfido.workflow.internal.utils.WorkflowIntentLauncher
    public void launchCaptureFlow(OneOffUIEvent uiEvent) {
        Intrinsics.checkNotNullParameter(uiEvent, "uiEvent");
        this.navigator.navigateTo(EmptyScreen.INSTANCE);
        if (Intrinsics.areEqual(uiEvent, OneOffUIEvent.DoNothing.INSTANCE)) {
            return;
        }
        ActivityResultLauncher<Intent> activityResultLauncher = null;
        if (Intrinsics.areEqual(uiEvent, OneOffUIEvent.NavigateToFaceUploadFlow.INSTANCE)) {
            ActivityResultLauncher<Intent> activityResultLauncher2 = this.launcher;
            if (activityResultLauncher2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("launcher");
                activityResultLauncher2 = null;
            }
            activityResultLauncher2.launch(CaptureActivity.INSTANCE.createFaceIntent(this.context, null, this.onfidoConfig));
            return;
        }
        if (Intrinsics.areEqual(uiEvent, OneOffUIEvent.NavigateToFaceVideoFlow.INSTANCE)) {
            ActivityResultLauncher<Intent> activityResultLauncher3 = this.launcher;
            if (activityResultLauncher3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("launcher");
            } else {
                activityResultLauncher = activityResultLauncher3;
            }
            activityResultLauncher.launch(CaptureActivity.INSTANCE.createLivenessIntent(this.context, this.onfidoConfig));
            return;
        }
        if (uiEvent instanceof OneOffUIEvent.NavigateToDocumentFlow) {
            ActivityResultLauncher<Intent> activityResultLauncher4 = this.launcher;
            if (activityResultLauncher4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("launcher");
            } else {
                activityResultLauncher = activityResultLauncher4;
            }
            OneOffUIEvent.NavigateToDocumentFlow navigateToDocumentFlow = (OneOffUIEvent.NavigateToDocumentFlow) uiEvent;
            activityResultLauncher.launch(CaptureActivity.INSTANCE.createDocumentIntent(this.context, this.onfidoConfig, navigateToDocumentFlow.getDocSide() == DocSide.FRONT, navigateToDocumentFlow.getDocumentType(), navigateToDocumentFlow.getCountryCode(), navigateToDocumentFlow.getDocumentFormat(), navigateToDocumentFlow.getNfcArguments(), (128 & 128) != 0 ? false : false, (128 & 256) != 0 ? null : null, (128 & 512) != 0 ? null : null));
            return;
        }
        Timber.INSTANCE.e(uiEvent + " has not been handled", new Object[0]);
    }

    private final void onResultOk(ActivityResult activityResult) {
        Intent data = activityResult.getData();
        if (data == null) {
            return;
        }
        if (IntentHelper.INSTANCE.getDocTypeFrom(data) != null) {
            handleDocumentCaptureResult(IntentHelper.INSTANCE.getDocumentDataBundle(data), CaptureActivity.INSTANCE.getUploadedFileId(data), CaptureActivity.INSTANCE.getDocumentVideoId(data), IntentHelper.INSTANCE.getNfcProperties(data), CaptureActivity.INSTANCE.getMediaSupportedNFC(data));
        } else if (data.hasExtra("video_path")) {
            handleFaceLivenessResult(data);
        } else {
            handleFacePhotoResult(activityResult);
        }
    }

    /* compiled from: WorkflowIntentLauncherImpl.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onfido/workflow/internal/utils/WorkflowIntentLauncherImpl$Companion;", "", "()V", "KEY_WORKFLOW", "", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
