package com.onfido.android.sdk.capture.core.features.liveness;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.core.config.FailureReason;
import com.onfido.android.sdk.capture.core.config.Flow;
import com.onfido.android.sdk.capture.core.config.FlowFragment;
import com.onfido.android.sdk.capture.core.features.liveness.LivenessFlow;
import com.onfido.android.sdk.capture.ui.camera.CaptureActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\nB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016R\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/core/features/liveness/LivenessFragment;", "Lcom/onfido/android/sdk/capture/core/config/FlowFragment;", "()V", "onfidoLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "Launcher", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LivenessFragment extends FlowFragment {
    private final ActivityResultLauncher<Unit> onfidoLauncher;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u001d\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\nJ\u001a\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/core/features/liveness/LivenessFragment$Launcher;", "Landroidx/activity/result/contract/ActivityResultContract;", "", "Lcom/onfido/android/sdk/capture/core/config/Flow$Result;", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "(Landroid/content/Context;Lkotlin/Unit;)Landroid/content/Intent;", "parseResult", "resultCode", "", "intent", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Launcher extends ActivityResultContract<Unit, Flow.Result> {
        public static final Launcher INSTANCE = new Launcher();

        private Launcher() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.activity.result.contract.ActivityResultContract
        public Flow.Result parseResult(int resultCode, Intent intent) {
            return (resultCode != -1 || intent == null) ? new LivenessFlow.Result.Failed(FailureReason.Canceled.INSTANCE) : new LivenessFlow.Result.Success(CaptureActivity.INSTANCE.getUploadedFileId(intent));
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context, Unit input) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(input, "input");
            return CaptureActivity.INSTANCE.createLivenessIntent(context, new OnfidoConfig.Builder(context).build());
        }
    }

    public LivenessFragment() {
        super(R.layout.onfido_fragment_document_capture);
        ActivityResultLauncher<Unit> activityResultLauncherRegisterForActivityResult = registerForActivityResult(Launcher.INSTANCE, new ActivityResultCallback() { // from class: com.onfido.android.sdk.capture.core.features.liveness.LivenessFragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                LivenessFragment.onfidoLauncher$lambda$0(this.f$0, (Flow.Result) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResult(...)");
        this.onfidoLauncher = activityResultLauncherRegisterForActivityResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onfidoLauncher$lambda$0(LivenessFragment this$0, Flow.Result result) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNull(result);
        this$0.finishFlow(result);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.onfidoLauncher.launch(Unit.INSTANCE);
        }
    }
}
