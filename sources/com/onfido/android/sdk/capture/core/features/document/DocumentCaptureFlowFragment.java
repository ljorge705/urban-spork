package com.onfido.android.sdk.capture.core.features.document;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.core.config.FailureReason;
import com.onfido.android.sdk.capture.core.config.Flow;
import com.onfido.android.sdk.capture.core.config.FlowFragment;
import com.onfido.android.sdk.capture.core.features.document.DocumentCaptureFlow;
import com.onfido.android.sdk.capture.core.features.face.FaceCaptureFlow;
import com.onfido.android.sdk.capture.document.DocumentPages;
import com.onfido.android.sdk.capture.flow.NfcArguments;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.ui.camera.CaptureActivity;
import com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment;
import com.onfido.android.sdk.capture.utils.CountryCode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016R\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/core/features/document/DocumentCaptureFlowFragment;", "Lcom/onfido/android/sdk/capture/core/config/FlowFragment;", "()V", "onfidoLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostFragment$DocumentSelectionResult$Completed;", "kotlin.jvm.PlatformType", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "Launcher", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentCaptureFlowFragment extends FlowFragment {
    private final ActivityResultLauncher<DocumentSelectionHostFragment.DocumentSelectionResult.Completed> onfidoLauncher;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u001a\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/core/features/document/DocumentCaptureFlowFragment$Launcher;", "Landroidx/activity/result/contract/ActivityResultContract;", "Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostFragment$DocumentSelectionResult$Completed;", "Lcom/onfido/android/sdk/capture/core/config/Flow$Result;", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "parseResult", "resultCode", "", "intent", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Launcher extends ActivityResultContract<DocumentSelectionHostFragment.DocumentSelectionResult.Completed, Flow.Result> {
        public static final Launcher INSTANCE = new Launcher();

        private Launcher() {
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context, DocumentSelectionHostFragment.DocumentSelectionResult.Completed input) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(input, "input");
            CaptureActivity.Companion companion = CaptureActivity.INSTANCE;
            DocumentType documentType = input.getDocumentType();
            CountryCode countryCode = input.getCountryCode();
            NfcArguments nfcArguments = new NfcArguments(NFCOptions.Disabled.INSTANCE, null, 2, null);
            DocumentPages genericDocPages = input.getGenericDocPages();
            return companion.createDocumentIntent(context, new OnfidoConfig.Builder(context).build(), true, documentType, countryCode, null, nfcArguments, (128 & 128) != 0 ? false : false, (128 & 256) != 0 ? null : input.getGenericDocTitle(), (128 & 512) != 0 ? null : genericDocPages);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.activity.result.contract.ActivityResultContract
        public Flow.Result parseResult(int resultCode, Intent intent) {
            return (resultCode != -1 || intent == null) ? new FaceCaptureFlow.Result.Failed(FailureReason.Canceled.INSTANCE) : new FaceCaptureFlow.Result.Success(CaptureActivity.INSTANCE.getUploadedFileId(intent));
        }
    }

    public DocumentCaptureFlowFragment() {
        super(R.layout.onfido_fragment_document_capture);
        ActivityResultLauncher<DocumentSelectionHostFragment.DocumentSelectionResult.Completed> activityResultLauncherRegisterForActivityResult = registerForActivityResult(Launcher.INSTANCE, new ActivityResultCallback() { // from class: com.onfido.android.sdk.capture.core.features.document.DocumentCaptureFlowFragment$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                DocumentCaptureFlowFragment.onfidoLauncher$lambda$0(this.f$0, (Flow.Result) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResult(...)");
        this.onfidoLauncher = activityResultLauncherRegisterForActivityResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onCreate$lambda$1(DocumentCaptureFlowFragment this$0, String str, Bundle result) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(result, "result");
        DocumentSelectionHostFragment.DocumentSelectionResult result2 = DocumentSelectionHostFragment.INSTANCE.getResult(result);
        if (result2 instanceof DocumentSelectionHostFragment.DocumentSelectionResult.Completed) {
            this$0.onfidoLauncher.launch(result2);
        } else {
            this$0.finishFlow(new DocumentCaptureFlow.Result.Failed(FailureReason.Canceled.INSTANCE));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onfidoLauncher$lambda$0(DocumentCaptureFlowFragment this$0, Flow.Result result) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNull(result);
        this$0.finishFlow(result);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            DocumentSelectionHostFragment documentSelectionHostFragmentCreateInstance$default = DocumentSelectionHostFragment.Companion.createInstance$default(DocumentSelectionHostFragment.INSTANCE, null, "request", false, false, 8, null);
            getChildFragmentManager().setFragmentResultListener("request", this, new FragmentResultListener() { // from class: com.onfido.android.sdk.capture.core.features.document.DocumentCaptureFlowFragment$$ExternalSyntheticLambda0
                @Override // androidx.fragment.app.FragmentResultListener
                public final void onFragmentResult(String str, Bundle bundle) {
                    DocumentCaptureFlowFragment.onCreate$lambda$1(this.f$0, str, bundle);
                }
            });
            FragmentManager childFragmentManager = getChildFragmentManager();
            Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
            FragmentTransaction fragmentTransactionBeginTransaction = childFragmentManager.beginTransaction();
            Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction()");
            fragmentTransactionBeginTransaction.setReorderingAllowed(true);
            fragmentTransactionBeginTransaction.replace(R.id.container, documentSelectionHostFragmentCreateInstance$default);
            fragmentTransactionBeginTransaction.commit();
        }
    }
}
