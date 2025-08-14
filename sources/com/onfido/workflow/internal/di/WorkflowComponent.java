package com.onfido.workflow.internal.di;

import android.content.Context;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.common.di.SdkComponent;
import com.onfido.dagger.Component;
import com.onfido.workflow.internal.ui.WorkflowFragment;
import com.onfido.workflow.internal.ui.WorkflowLoadingFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkflowComponent.kt */
@WorkflowScope
@Component(dependencies = {SdkComponent.class}, modules = {WorkflowModule.class, WorkflowNavigationModule.class})
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\ba\u0018\u0000 \b2\u00020\u0001:\u0002\b\tJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\n"}, d2 = {"Lcom/onfido/workflow/internal/di/WorkflowComponent;", "", "inject", "", "workflowFragment", "Lcom/onfido/workflow/internal/ui/WorkflowFragment;", "loadingFragment", "Lcom/onfido/workflow/internal/ui/WorkflowLoadingFragment;", "Companion", "Factory", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface WorkflowComponent {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* compiled from: WorkflowComponent.kt */
    @Component.Factory
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bç\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/onfido/workflow/internal/di/WorkflowComponent$Factory;", "", "create", "Lcom/onfido/workflow/internal/di/WorkflowComponent;", "appComponent", "Lcom/onfido/android/sdk/capture/common/di/SdkComponent;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        WorkflowComponent create(SdkComponent appComponent);
    }

    void inject(WorkflowFragment workflowFragment);

    void inject(WorkflowLoadingFragment loadingFragment);

    /* compiled from: WorkflowComponent.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eR&\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Lcom/onfido/workflow/internal/di/WorkflowComponent$Companion;", "", "()V", "component", "Lcom/onfido/workflow/internal/di/WorkflowComponent;", "getComponent$onfido_workflow_release$annotations", "getComponent$onfido_workflow_release", "()Lcom/onfido/workflow/internal/di/WorkflowComponent;", "setComponent$onfido_workflow_release", "(Lcom/onfido/workflow/internal/di/WorkflowComponent;)V", "destroy", "", "get", "context", "Landroid/content/Context;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static WorkflowComponent component;

        public static /* synthetic */ void getComponent$onfido_workflow_release$annotations() {
        }

        public final void destroy() {
            component = null;
        }

        public final WorkflowComponent getComponent$onfido_workflow_release() {
            return component;
        }

        public final void setComponent$onfido_workflow_release(WorkflowComponent workflowComponent) {
            component = workflowComponent;
        }

        private Companion() {
        }

        public final WorkflowComponent get(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            WorkflowComponent workflowComponent = component;
            if (workflowComponent != null) {
                return workflowComponent;
            }
            WorkflowComponent workflowComponentCreate = DaggerWorkflowComponent.factory().create(SdkController.getSdkComponent$default(SdkController.INSTANCE.getInstance(), context, null, 2, null));
            component = workflowComponentCreate;
            Intrinsics.checkNotNull(workflowComponentCreate);
            return workflowComponentCreate;
        }
    }
}
