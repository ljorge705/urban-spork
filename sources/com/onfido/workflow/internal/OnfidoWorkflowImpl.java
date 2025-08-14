package com.onfido.workflow.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.onfido.android.sdk.capture.EnterpriseFeatures;
import com.onfido.android.sdk.capture.ExitCode;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.internal.OnfidoConstants;
import com.onfido.android.sdk.capture.token.TokenExpirationHandlerService;
import com.onfido.workflow.OnfidoWorkflow;
import com.onfido.workflow.WorkflowConfig;
import java.io.Serializable;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: OnfidoWorkflowImpl.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002J\"\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J \u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\f\u0010\u0017\u001a\u00020\u000b*\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/onfido/workflow/internal/OnfidoWorkflowImpl;", "Lcom/onfido/workflow/OnfidoWorkflow;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "createIntent", "Landroid/content/Intent;", "workflowConfig", "Lcom/onfido/workflow/WorkflowConfig;", "createIntentInternal", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "handleActivityResult", "", "resultCode", "", "intent", "resultListener", "Lcom/onfido/workflow/OnfidoWorkflow$ResultListener;", "startActivityForResult", "activity", "Landroid/app/Activity;", "requestCode", "createOnfidoConfig", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class OnfidoWorkflowImpl implements OnfidoWorkflow {
    private final Context context;

    public OnfidoWorkflowImpl(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.onfido.workflow.OnfidoWorkflow
    public void startActivityForResult(Activity activity, int requestCode, WorkflowConfig workflowConfig) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(workflowConfig, "workflowConfig");
        activity.startActivityForResult(createIntent(workflowConfig), requestCode);
    }

    @Override // com.onfido.workflow.OnfidoWorkflow
    public Intent createIntent(WorkflowConfig workflowConfig) {
        Intrinsics.checkNotNullParameter(workflowConfig, "workflowConfig");
        WorkflowExtensions.ensureWorkflowFragmentExists();
        return createIntentInternal(createOnfidoConfig(workflowConfig));
    }

    @Override // com.onfido.workflow.OnfidoWorkflow
    public void handleActivityResult(final int resultCode, final Intent intent, final OnfidoWorkflow.ResultListener resultListener) {
        Intrinsics.checkNotNullParameter(resultListener, "resultListener");
        Function0<Unit> function0 = new Function0<Unit>() { // from class: com.onfido.workflow.internal.OnfidoWorkflowImpl$handleActivityResult$corruptedIntentCallback$1
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
                OnfidoWorkflow.ResultListener resultListener2 = resultListener;
                Intent intent2 = intent;
                resultListener2.onException(new OnfidoWorkflow.WorkflowException.WorkflowUnknownResultException(StringsKt.trimMargin$default("\n                    |Unknown result intent: " + (intent2 != null ? intent2.toString() : null) + "\n                    |result code: " + resultCode + "\n                    ", null, 1, null)));
            }
        };
        Serializable serializableExtra = intent != null ? intent.getSerializableExtra(OnfidoConstants.ONFIDO_EXIT_CODE) : null;
        ExitCode exitCode = serializableExtra instanceof ExitCode ? (ExitCode) serializableExtra : null;
        if (resultCode == -2) {
            Serializable serializableExtra2 = intent != null ? intent.getSerializableExtra(OnfidoConstants.ONFIDO_EXCEPTION_RESULT) : null;
            OnfidoWorkflow.WorkflowException workflowException = serializableExtra2 instanceof OnfidoWorkflow.WorkflowException ? (OnfidoWorkflow.WorkflowException) serializableExtra2 : null;
            if (workflowException != null) {
                resultListener.onException(workflowException);
                return;
            } else {
                function0.invoke();
                return;
            }
        }
        if (resultCode == -1) {
            resultListener.onUserCompleted();
        } else {
            if (resultCode != 0) {
                function0.invoke();
                return;
            }
            if (exitCode == null) {
                exitCode = ExitCode.USER_LEFT_ACTIVITY;
            }
            resultListener.onUserExited(exitCode);
        }
    }

    private final Intent createIntentInternal(OnfidoConfig onfidoConfig) {
        Intent intent = new Intent();
        intent.setClassName(this.context, "com.onfido.android.sdk.capture.ui.OnfidoSplashActivity");
        intent.putExtra(OnfidoConstants.ONFIDO_CONFIG, onfidoConfig);
        intent.putExtra(OnfidoConstants.ONFIDO_SESSION_ID, System.currentTimeMillis());
        return intent;
    }

    private final OnfidoConfig createOnfidoConfig(WorkflowConfig workflowConfig) {
        OnfidoConfig.Builder builderWithWorkflowConfig = OnfidoConfig.INSTANCE.builder(this.context).withSDKToken(workflowConfig.getSdkToken(), TokenExpirationHandlerService.INSTANCE.getTokenExpirationHandler()).withWorkflowConfig(workflowConfig);
        Locale locale = workflowConfig.getLocale();
        if (locale != null) {
            builderWithWorkflowConfig.withLocale(locale);
        }
        EnterpriseFeatures enterpriseFeatures = workflowConfig.getEnterpriseFeatures();
        if (enterpriseFeatures != null) {
            builderWithWorkflowConfig.withEnterpriseFeatures(enterpriseFeatures);
        }
        builderWithWorkflowConfig.withTheme(workflowConfig.getTheme());
        return builderWithWorkflowConfig.build();
    }
}
