package com.onfido.workflow.internal;

import androidx.fragment.app.Fragment;
import com.onfido.android.sdk.capture.errors.MissingDependenciesException;
import java.lang.reflect.InvocationTargetException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkflowExtensions.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000\u001a\b\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, d2 = {"createWorkflowFragment", "Landroidx/fragment/app/Fragment;", "ensureWorkflowFragmentExists", "", "onfido-workflow-api_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WorkflowExtensions {
    public static final Fragment createWorkflowFragment() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object objInvoke = Class.forName("com.onfido.workflow.internal.ui.WorkflowFragment").getMethod("newInstance", new Class[0]).invoke(null, new Object[0]);
        Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type androidx.fragment.app.Fragment");
        return (Fragment) objInvoke;
    }

    public static final void ensureWorkflowFragmentExists() {
        try {
            createWorkflowFragment();
        } catch (Exception unused) {
            throw new MissingDependenciesException("Workflow library is missing, please add it as a dependency and then re-launch the sdk");
        }
    }
}
