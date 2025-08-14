package com.onfido.workflow.internal.utils;

import androidx.activity.result.ActivityResultRegistry;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.onfido.android.sdk.capture.internal.util.DefaultLifecycleObserver;
import com.onfido.workflow.internal.ui.model.OneOffUIEvent;
import com.onfido.workflow.internal.workflow.model.CaptureResult;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkflowIntentLauncher.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001:\u0001\tJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&¨\u0006\n"}, d2 = {"Lcom/onfido/workflow/internal/utils/WorkflowIntentLauncher;", "Lcom/onfido/android/sdk/capture/internal/util/DefaultLifecycleObserver;", "launchCaptureFlow", "", "uiEvent", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent;", "observeResult", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/workflow/internal/workflow/model/CaptureResult;", "Factory", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface WorkflowIntentLauncher extends DefaultLifecycleObserver {

    /* compiled from: WorkflowIntentLauncher.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/onfido/workflow/internal/utils/WorkflowIntentLauncher$Factory;", "", "create", "Lcom/onfido/workflow/internal/utils/WorkflowIntentLauncher;", "activityResultRegistry", "Landroidx/activity/result/ActivityResultRegistry;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        WorkflowIntentLauncher create(ActivityResultRegistry activityResultRegistry);
    }

    void launchCaptureFlow(OneOffUIEvent uiEvent);

    Observable<CaptureResult> observeResult();

    /* compiled from: WorkflowIntentLauncher.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        public static void onCreate(WorkflowIntentLauncher workflowIntentLauncher, LifecycleOwner owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            DefaultLifecycleObserver.DefaultImpls.onCreate(workflowIntentLauncher, owner);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public static void onDestroy(WorkflowIntentLauncher workflowIntentLauncher, LifecycleOwner owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            DefaultLifecycleObserver.DefaultImpls.onDestroy(workflowIntentLauncher, owner);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public static void onPause(WorkflowIntentLauncher workflowIntentLauncher, LifecycleOwner owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            DefaultLifecycleObserver.DefaultImpls.onPause(workflowIntentLauncher, owner);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public static void onResume(WorkflowIntentLauncher workflowIntentLauncher, LifecycleOwner owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            DefaultLifecycleObserver.DefaultImpls.onResume(workflowIntentLauncher, owner);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public static void onStart(WorkflowIntentLauncher workflowIntentLauncher, LifecycleOwner owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            DefaultLifecycleObserver.DefaultImpls.onStart(workflowIntentLauncher, owner);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public static void onStop(WorkflowIntentLauncher workflowIntentLauncher, LifecycleOwner owner) {
            Intrinsics.checkNotNullParameter(owner, "owner");
            DefaultLifecycleObserver.DefaultImpls.onStop(workflowIntentLauncher, owner);
        }
    }
}
