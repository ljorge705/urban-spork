package com.onfido.workflow.internal.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultRegistry;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import com.onfido.android.sdk.capture.ExitCode;
import com.onfido.android.sdk.capture.common.permissions.PermissionResult;
import com.onfido.android.sdk.capture.common.permissions.PermissionsManagementFragment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostFragment;
import com.onfido.android.sdk.capture.internal.OnfidoConstants;
import com.onfido.android.sdk.capture.internal.navigation.Screen;
import com.onfido.android.sdk.capture.internal.navigation.navigator.FragmentNavigationManager;
import com.onfido.android.sdk.capture.internal.navigation.navigator.NavigationManagerHolder;
import com.onfido.android.sdk.capture.internal.navigation.screens.DocumentSelectionScreen;
import com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionFragment;
import com.onfido.android.sdk.capture.internal.util.FragmentManagerExtKt;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.NextActionListener;
import com.onfido.android.sdk.capture.ui.OnfidoActivity;
import com.onfido.android.sdk.capture.ui.camera.document.DocumentCaptureScreen;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationFragment;
import com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureScreen;
import com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureScreen;
import com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment;
import com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment;
import com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostFragment;
import com.onfido.android.sdk.capture.utils.LifecycleAwareDialog;
import com.onfido.android.sdk.capture.utils.LifecycleDisposableKt;
import com.onfido.android.sdk.capture.utils.UserExitFlowMenuProvider;
import com.onfido.android.sdk.capture.utils.WorkflowToolbarUpdateListener;
import com.onfido.hosted.web.module.HostedWebModuleFragment;
import com.onfido.javax.inject.Inject;
import com.onfido.workflow.OnfidoWorkflow;
import com.onfido.workflow.R;
import com.onfido.workflow.internal.di.WorkflowComponent;
import com.onfido.workflow.internal.ui.backstack.WorkflowBackstackManager;
import com.onfido.workflow.internal.ui.model.OneOffUIEvent;
import com.onfido.workflow.internal.ui.model.ToolbarState;
import com.onfido.workflow.internal.ui.model.UIEvent;
import com.onfido.workflow.internal.utils.WorkflowIntentLauncher;
import com.onfido.workflow.internal.workflow.model.CaptureResult;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.io.Serializable;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkflowFragment.kt */
@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0001\u0018\u0000 W2\u00020\u0001:\u0001WB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0002J\u0010\u0010;\u001a\u0002082\u0006\u0010<\u001a\u00020=H\u0002J\u0010\u0010>\u001a\u0002082\u0006\u0010?\u001a\u00020@H\u0016J\b\u0010A\u001a\u000208H\u0016J\b\u0010B\u001a\u000208H\u0016J\b\u0010C\u001a\u000208H\u0016J\u001a\u0010D\u001a\u0002082\u0006\u0010E\u001a\u00020F2\b\u0010G\u001a\u0004\u0018\u00010HH\u0016J\u0010\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020HH\u0002J&\u0010L\u001a\u0002082\u0006\u0010M\u001a\u00020N2\u0014\u0010O\u001a\u0010\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020R\u0018\u00010PH\u0002J\b\u0010S\u001a\u000208H\u0002J\u0010\u0010T\u001a\u0002082\u0006\u0010U\u001a\u00020VH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\b\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\b\u001a\u0004\b\u001d\u0010\u001eR\u001e\u0010 \u001a\u00020!8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001b\u0010&\u001a\u00020'8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b*\u0010\b\u001a\u0004\b(\u0010)R\u001e\u0010+\u001a\u00020,8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001e\u00101\u001a\u0002028\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106¨\u0006X"}, d2 = {"Lcom/onfido/workflow/internal/ui/WorkflowFragment;", "Landroidx/fragment/app/Fragment;", "()V", "backStackManager", "Lcom/onfido/workflow/internal/ui/backstack/WorkflowBackstackManager;", "getBackStackManager", "()Lcom/onfido/workflow/internal/ui/backstack/WorkflowBackstackManager;", "backStackManager$delegate", "Lkotlin/Lazy;", "lifecycleAwareDialog", "Lcom/onfido/android/sdk/capture/utils/LifecycleAwareDialog;", "getLifecycleAwareDialog", "()Lcom/onfido/android/sdk/capture/utils/LifecycleAwareDialog;", "lifecycleAwareDialog$delegate", "navigationManager", "Lcom/onfido/android/sdk/capture/internal/navigation/navigator/FragmentNavigationManager;", "getNavigationManager", "()Lcom/onfido/android/sdk/capture/internal/navigation/navigator/FragmentNavigationManager;", "navigationManager$delegate", "navigationManagerHolder", "Lcom/onfido/android/sdk/capture/internal/navigation/navigator/NavigationManagerHolder;", "getNavigationManagerHolder", "()Lcom/onfido/android/sdk/capture/internal/navigation/navigator/NavigationManagerHolder;", "setNavigationManagerHolder", "(Lcom/onfido/android/sdk/capture/internal/navigation/navigator/NavigationManagerHolder;)V", "nextActionListener", "Lcom/onfido/android/sdk/capture/ui/NextActionListener;", "orchestrationIntentLauncher", "Lcom/onfido/workflow/internal/utils/WorkflowIntentLauncher;", "getOrchestrationIntentLauncher", "()Lcom/onfido/workflow/internal/utils/WorkflowIntentLauncher;", "orchestrationIntentLauncher$delegate", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "getSchedulersProvider", "()Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "setSchedulersProvider", "(Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;)V", "userExitFlowMenuProvider", "Lcom/onfido/android/sdk/capture/utils/UserExitFlowMenuProvider;", "getUserExitFlowMenuProvider", "()Lcom/onfido/android/sdk/capture/utils/UserExitFlowMenuProvider;", "userExitFlowMenuProvider$delegate", "workflowIntentFactory", "Lcom/onfido/workflow/internal/utils/WorkflowIntentLauncher$Factory;", "getWorkflowIntentFactory", "()Lcom/onfido/workflow/internal/utils/WorkflowIntentLauncher$Factory;", "setWorkflowIntentFactory", "(Lcom/onfido/workflow/internal/utils/WorkflowIntentLauncher$Factory;)V", "workflowViewModel", "Lcom/onfido/workflow/internal/ui/WorkflowViewModel;", "getWorkflowViewModel", "()Lcom/onfido/workflow/internal/ui/WorkflowViewModel;", "setWorkflowViewModel", "(Lcom/onfido/workflow/internal/ui/WorkflowViewModel;)V", "handleOneOffUIEvent", "", "uiEvent", "Lcom/onfido/workflow/internal/ui/model/OneOffUIEvent;", "handleToolbarState", "toolbarState", "Lcom/onfido/workflow/internal/ui/model/ToolbarState;", "onAttach", "context", "Landroid/content/Context;", "onDetach", "onStart", "onStop", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "permissionResultToUiEvent", "Lcom/onfido/workflow/internal/ui/model/UIEvent$OnFragmentResult$OnPermissionsScreenResult;", "bundle", "setFragmentResult", "resultCode", "", "data", "Lkotlin/Pair;", "", "Ljava/io/Serializable;", "setupFragmentListeners", "setupToolbarBackButton", "showBackButton", "", "Companion", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WorkflowFragment extends Fragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* renamed from: backStackManager$delegate, reason: from kotlin metadata */
    private final Lazy backStackManager;

    /* renamed from: lifecycleAwareDialog$delegate, reason: from kotlin metadata */
    private final Lazy lifecycleAwareDialog;

    /* renamed from: navigationManager$delegate, reason: from kotlin metadata */
    private final Lazy navigationManager;

    @Inject
    public NavigationManagerHolder navigationManagerHolder;
    private NextActionListener nextActionListener;

    /* renamed from: orchestrationIntentLauncher$delegate, reason: from kotlin metadata */
    private final Lazy orchestrationIntentLauncher;

    @Inject
    public SchedulersProvider schedulersProvider;

    /* renamed from: userExitFlowMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy userExitFlowMenuProvider;

    @Inject
    public WorkflowIntentLauncher.Factory workflowIntentFactory;

    @Inject
    public WorkflowViewModel workflowViewModel;

    @JvmStatic
    public static final WorkflowFragment newInstance() {
        return INSTANCE.newInstance();
    }

    public final void setNavigationManagerHolder(NavigationManagerHolder navigationManagerHolder) {
        Intrinsics.checkNotNullParameter(navigationManagerHolder, "<set-?>");
        this.navigationManagerHolder = navigationManagerHolder;
    }

    public final void setSchedulersProvider(SchedulersProvider schedulersProvider) {
        Intrinsics.checkNotNullParameter(schedulersProvider, "<set-?>");
        this.schedulersProvider = schedulersProvider;
    }

    public final void setWorkflowIntentFactory(WorkflowIntentLauncher.Factory factory) {
        Intrinsics.checkNotNullParameter(factory, "<set-?>");
        this.workflowIntentFactory = factory;
    }

    public final void setWorkflowViewModel(WorkflowViewModel workflowViewModel) {
        Intrinsics.checkNotNullParameter(workflowViewModel, "<set-?>");
        this.workflowViewModel = workflowViewModel;
    }

    public WorkflowFragment() {
        super(R.layout.onfido_orchestration_fragment);
        this.orchestrationIntentLauncher = LazyKt.lazy(new Function0<WorkflowIntentLauncher>() { // from class: com.onfido.workflow.internal.ui.WorkflowFragment$orchestrationIntentLauncher$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final WorkflowIntentLauncher invoke() {
                WorkflowIntentLauncher.Factory workflowIntentFactory = this.this$0.getWorkflowIntentFactory();
                ActivityResultRegistry activityResultRegistry = this.this$0.requireActivity().getActivityResultRegistry();
                Intrinsics.checkNotNullExpressionValue(activityResultRegistry, "getActivityResultRegistry(...)");
                return workflowIntentFactory.create(activityResultRegistry);
            }
        });
        this.navigationManager = LazyKt.lazy(new Function0<FragmentNavigationManager>() { // from class: com.onfido.workflow.internal.ui.WorkflowFragment$navigationManager$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FragmentNavigationManager invoke() {
                WorkflowFragment workflowFragment = this.this$0;
                WorkflowFragment workflowFragment2 = workflowFragment;
                FragmentManager childFragmentManager = workflowFragment.getChildFragmentManager();
                Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
                return new FragmentNavigationManager(workflowFragment2, childFragmentManager, R.id.fragment_container);
            }
        });
        this.backStackManager = LazyKt.lazy(new Function0<WorkflowBackstackManager>() { // from class: com.onfido.workflow.internal.ui.WorkflowFragment$backStackManager$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final WorkflowBackstackManager invoke() {
                FragmentNavigationManager navigationManager = this.this$0.getNavigationManager();
                FragmentManager childFragmentManager = this.this$0.getChildFragmentManager();
                final WorkflowFragment workflowFragment = this.this$0;
                Function1<List<? extends Screen>, Unit> function1 = new Function1<List<? extends Screen>, Unit>() { // from class: com.onfido.workflow.internal.ui.WorkflowFragment$backStackManager$2.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(List<? extends Screen> list) {
                        invoke2(list);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(List<? extends Screen> screens) {
                        Intrinsics.checkNotNullParameter(screens, "screens");
                        workflowFragment.getWorkflowViewModel().dispatchUIEvent(new UIEvent.OnBackstackChange(screens));
                    }
                };
                Intrinsics.checkNotNull(childFragmentManager);
                final WorkflowFragment workflowFragment2 = this.this$0;
                return new WorkflowBackstackManager(navigationManager, function1, childFragmentManager, new Function1<Integer, Unit>() { // from class: com.onfido.workflow.internal.ui.WorkflowFragment$backStackManager$2.2
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                        invoke(num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i) {
                        workflowFragment2.setupToolbarBackButton(i > 1);
                    }
                });
            }
        });
        this.lifecycleAwareDialog = LazyKt.lazy(new Function0<LifecycleAwareDialog>() { // from class: com.onfido.workflow.internal.ui.WorkflowFragment$lifecycleAwareDialog$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LifecycleAwareDialog invoke() {
                return new LifecycleAwareDialog(this.this$0, (Function1) null, 2, (DefaultConstructorMarker) null);
            }
        });
        this.userExitFlowMenuProvider = LazyKt.lazy(new Function0<UserExitFlowMenuProvider>() { // from class: com.onfido.workflow.internal.ui.WorkflowFragment$userExitFlowMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final UserExitFlowMenuProvider invoke() {
                LifecycleAwareDialog lifecycleAwareDialog = this.this$0.getLifecycleAwareDialog();
                final WorkflowFragment workflowFragment = this.this$0;
                Function0<Unit> function0 = new Function0<Unit>() { // from class: com.onfido.workflow.internal.ui.WorkflowFragment$userExitFlowMenuProvider$2.1
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
                        workflowFragment.getWorkflowViewModel().flowUserExit();
                    }
                };
                final WorkflowFragment workflowFragment2 = this.this$0;
                Function1<DialogInterface, Unit> function1 = new Function1<DialogInterface, Unit>() { // from class: com.onfido.workflow.internal.ui.WorkflowFragment$userExitFlowMenuProvider$2.2
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                        invoke2(dialogInterface);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DialogInterface it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        workflowFragment2.getWorkflowViewModel().flowUserExitConfirmed();
                        workflowFragment2.setFragmentResult(0, TuplesKt.to(OnfidoConstants.ONFIDO_EXIT_CODE, ExitCode.USER_LEFT_ACTIVITY));
                    }
                };
                final WorkflowFragment workflowFragment3 = this.this$0;
                return new UserExitFlowMenuProvider(lifecycleAwareDialog, 0, function0, function1, new Function1<DialogInterface, Unit>() { // from class: com.onfido.workflow.internal.ui.WorkflowFragment$userExitFlowMenuProvider$2.3
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                        invoke2(dialogInterface);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DialogInterface it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        workflowFragment3.getWorkflowViewModel().flowUserExitCanceled();
                    }
                }, 2, null);
            }
        });
    }

    public final WorkflowViewModel getWorkflowViewModel() {
        WorkflowViewModel workflowViewModel = this.workflowViewModel;
        if (workflowViewModel != null) {
            return workflowViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("workflowViewModel");
        return null;
    }

    public final WorkflowIntentLauncher.Factory getWorkflowIntentFactory() {
        WorkflowIntentLauncher.Factory factory = this.workflowIntentFactory;
        if (factory != null) {
            return factory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("workflowIntentFactory");
        return null;
    }

    public final SchedulersProvider getSchedulersProvider() {
        SchedulersProvider schedulersProvider = this.schedulersProvider;
        if (schedulersProvider != null) {
            return schedulersProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("schedulersProvider");
        return null;
    }

    public final NavigationManagerHolder getNavigationManagerHolder() {
        NavigationManagerHolder navigationManagerHolder = this.navigationManagerHolder;
        if (navigationManagerHolder != null) {
            return navigationManagerHolder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("navigationManagerHolder");
        return null;
    }

    private final WorkflowIntentLauncher getOrchestrationIntentLauncher() {
        return (WorkflowIntentLauncher) this.orchestrationIntentLauncher.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentNavigationManager getNavigationManager() {
        return (FragmentNavigationManager) this.navigationManager.getValue();
    }

    private final WorkflowBackstackManager getBackStackManager() {
        return (WorkflowBackstackManager) this.backStackManager.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LifecycleAwareDialog getLifecycleAwareDialog() {
        return (LifecycleAwareDialog) this.lifecycleAwareDialog.getValue();
    }

    private final UserExitFlowMenuProvider getUserExitFlowMenuProvider() {
        return (UserExitFlowMenuProvider) this.userExitFlowMenuProvider.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        this.nextActionListener = context instanceof NextActionListener ? (NextActionListener) context : null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        getWorkflowViewModel().onDetach();
        this.nextActionListener = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        WorkflowComponent.Companion companion = WorkflowComponent.INSTANCE;
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        companion.get(contextRequireContext).inject(this);
        super.onViewCreated(view, savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback() { // from class: com.onfido.workflow.internal.ui.WorkflowFragment.onViewCreated.1
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                WorkflowFragment.this.getWorkflowViewModel().dispatchUIEvent(new UIEvent.OnBackPressed(WorkflowFragment.this.getNavigationManager().getCurrentScreensSnapshot()));
            }
        });
        setupFragmentListeners();
        getWorkflowViewModel().dispatchUIEvent(UIEvent.OnCreate.INSTANCE);
        getViewLifecycleOwner().getLifecycleRegistry().addObserver(getOrchestrationIntentLauncher());
        Disposable disposableSubscribe = getWorkflowViewModel().getOneOffUiEvents().subscribeOn(getSchedulersProvider().getUi()).observeOn(getSchedulersProvider().getUi()).subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowFragment.onViewCreated.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(OneOffUIEvent result) {
                Intrinsics.checkNotNullParameter(result, "result");
                WorkflowFragment.this.handleOneOffUIEvent(result);
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowFragment.onViewCreated.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Failure in listening for one off events", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        LifecycleDisposableKt.disposeOnDestroy(disposableSubscribe, viewLifecycleOwner);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        getChildFragmentManager().addOnBackStackChangedListener(getBackStackManager());
        getNavigationManagerHolder().setNavigationManager(getNavigationManager());
        Observable<R> map = getOrchestrationIntentLauncher().observeResult().map(new Function() { // from class: com.onfido.workflow.internal.ui.WorkflowFragment.onStart.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final UIEvent.OnActivityResult apply(CaptureResult p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new UIEvent.OnActivityResult(p0);
            }
        });
        final WorkflowViewModel workflowViewModel = getWorkflowViewModel();
        Disposable disposableSubscribe = map.subscribe((Consumer<? super R>) new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowFragment.onStart.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(UIEvent p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                workflowViewModel.dispatchUIEvent(p0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        LifecycleDisposableKt.disposeOnStop(disposableSubscribe, viewLifecycleOwner);
        Disposable disposableSubscribe2 = getWorkflowViewModel().getToolbarState().subscribeOn(getSchedulersProvider().getComputation()).observeOn(getSchedulersProvider().getUi()).subscribe(new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowFragment.onStart.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(ToolbarState p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                WorkflowFragment.this.handleToolbarState(p0);
            }
        }, new Consumer() { // from class: com.onfido.workflow.internal.ui.WorkflowFragment.onStart.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Timber.INSTANCE.e(throwable, "Failure during listening to toolbar state", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe2, "subscribe(...)");
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner2, "getViewLifecycleOwner(...)");
        LifecycleDisposableKt.disposeOnStop(disposableSubscribe2, viewLifecycleOwner2);
        getWorkflowViewModel().dispatchUIEvent(UIEvent.OnStart.INSTANCE);
        Intent intent = requireActivity().getIntent();
        String stringExtra = intent != null ? intent.getStringExtra(OnfidoConstants.ONFIDO_BRIDGE_URL) : null;
        if (stringExtra == null) {
            stringExtra = "";
        }
        getWorkflowViewModel().setCustomBridgeUrl(stringExtra);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        getChildFragmentManager().removeOnBackStackChangedListener(getBackStackManager());
        getNavigationManagerHolder().resetNavigationManager();
        getWorkflowViewModel().dispatchUIEvent(UIEvent.OnStop.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleOneOffUIEvent(OneOffUIEvent uiEvent) {
        if (Intrinsics.areEqual(uiEvent, OneOffUIEvent.NavigateToFaceUploadFlow.INSTANCE) || Intrinsics.areEqual(uiEvent, OneOffUIEvent.NavigateToFaceVideoFlow.INSTANCE) || (uiEvent instanceof OneOffUIEvent.NavigateToDocumentFlow)) {
            getOrchestrationIntentLauncher().launchCaptureFlow(uiEvent);
            return;
        }
        if (Intrinsics.areEqual(uiEvent, OneOffUIEvent.DoNothing.INSTANCE)) {
            return;
        }
        if (uiEvent instanceof OneOffUIEvent.FinishFlow.Error) {
            OneOffUIEvent.FinishFlow.Error error = (OneOffUIEvent.FinishFlow.Error) uiEvent;
            if (error.getException() instanceof OnfidoWorkflow.WorkflowException.WorkflowCaptureCancelledException) {
                if (WorkflowFragmentKt.hasOnlyLoadingScreen(getNavigationManager())) {
                    setFragmentResult(0, TuplesKt.to(OnfidoConstants.ONFIDO_EXIT_CODE, ExitCode.USER_LEFT_ACTIVITY));
                    return;
                }
                return;
            }
            setFragmentResult(error.getResultCode(), TuplesKt.to(OnfidoConstants.ONFIDO_EXCEPTION_RESULT, error.getException()));
            return;
        }
        if (uiEvent instanceof OneOffUIEvent.FinishFlow.Exit) {
            OneOffUIEvent.FinishFlow.Exit exit = (OneOffUIEvent.FinishFlow.Exit) uiEvent;
            if (exit.getReason() == OneOffUIEvent.FinishFlow.Exit.Reason.EXIT_NFC_REQUIRED_FLOW) {
                setFragmentResult(0, TuplesKt.to(OnfidoConstants.ONFIDO_EXIT_CODE, ExitCode.REQUIRED_NFC_FLOW_NOT_COMPLETED));
                return;
            } else {
                if (exit.getReason() == OneOffUIEvent.FinishFlow.Exit.Reason.USER_EXIT_FLOW) {
                    setFragmentResult(0, TuplesKt.to(OnfidoConstants.ONFIDO_EXIT_CODE, ExitCode.USER_LEFT_ACTIVITY));
                    return;
                }
                return;
            }
        }
        if (uiEvent instanceof OneOffUIEvent.FinishFlow.Success) {
            setFragmentResult(((OneOffUIEvent.FinishFlow.Success) uiEvent).getResultCode(), null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFragmentResult(int resultCode, Pair<String, ? extends Serializable> data) {
        Bundle bundleBundleOf = BundleKt.bundleOf(TuplesKt.to(OnfidoActivity.KEY_WORKFLOW_RESULT_CODE, Integer.valueOf(resultCode)));
        if (data != null) {
            bundleBundleOf.putSerializable(data.getFirst(), data.getSecond());
        }
        getParentFragmentManager().setFragmentResult(OnfidoActivity.KEY_RESULT_WORKFLOW, bundleBundleOf);
    }

    private final void setupFragmentListeners() {
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        FragmentManagerExtKt.setFragmentResultsListeners(childFragmentManager, viewLifecycleOwner, new String[]{LivenessConfirmationScreen.KEY_REQUEST, DocumentSelectionScreen.KEY_REQUEST, CountrySelectionScreen.KEY_REQUEST, FaceSelfieIntroScreen.KEY_REQUEST, FaceLivenessIntroScreen.KEY_REQUEST, "request_key_permissions_screen", NfcFlowScreen.KEY_REQUEST, RetryWorkflowScreen.KEY_REQUEST, PoaScreen.KEY_REQUEST, MotionScreen.KEY_REQUEST, HostedWebModuleScreen.KEY_REQUEST, NfcNotSupportedScreen.KEY_REQUEST, SelfieCaptureScreen.KEY_REQUEST, "face_liveness_capture", DocumentCaptureScreen.KEY_REQUEST}, new Function2<String, Bundle, Unit>() { // from class: com.onfido.workflow.internal.ui.WorkflowFragment.setupFragmentListeners.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Bundle bundle) {
                invoke2(str, bundle);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String requestKey, Bundle bundle) {
                Intrinsics.checkNotNullParameter(requestKey, "requestKey");
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                switch (requestKey.hashCode()) {
                    case -1543953662:
                        if (requestKey.equals(FaceLivenessIntroScreen.KEY_REQUEST)) {
                            WorkflowFragment.this.getWorkflowViewModel().dispatchUIEvent(UIEvent.OnFragmentResult.OnFaceLivenessIntroFragmentResult.INSTANCE);
                            return;
                        }
                        break;
                    case -1275886617:
                        if (requestKey.equals(FaceSelfieIntroScreen.KEY_REQUEST)) {
                            WorkflowFragment.this.getWorkflowViewModel().dispatchUIEvent(UIEvent.OnFragmentResult.OnFaceSelfieIntroFragmentResult.INSTANCE);
                            return;
                        }
                        break;
                    case -646142568:
                        if (requestKey.equals(CountrySelectionScreen.KEY_REQUEST)) {
                            WorkflowFragment.this.getWorkflowViewModel().dispatchUIEvent(new UIEvent.OnFragmentResult.CountrySelectionFragmentResult(CountrySelectionFragment.INSTANCE.getResult(bundle).getCountryCode()));
                            return;
                        }
                        break;
                    case -631944929:
                        if (requestKey.equals(SelfieCaptureScreen.KEY_REQUEST)) {
                            SelfieCaptureScreen.SelfieCaptureResult selfieCaptureResultRetrieveResult = SelfieCaptureScreen.INSTANCE.retrieveResult(bundle);
                            if (selfieCaptureResultRetrieveResult instanceof SelfieCaptureScreen.SelfieCaptureResult.Back) {
                                WorkflowFragment.this.getWorkflowViewModel().dispatchUIEvent(new UIEvent.OnBackPressed(WorkflowFragment.this.getNavigationManager().getCurrentScreensSnapshot()));
                                return;
                            } else {
                                WorkflowFragment.this.getWorkflowViewModel().dispatchUIEvent(new UIEvent.OnFragmentResult.OnFaceSelfieCaptureFragmentResult(selfieCaptureResultRetrieveResult));
                                return;
                            }
                        }
                        break;
                    case -227487054:
                        if (requestKey.equals(PoaScreen.KEY_REQUEST)) {
                            WorkflowFragment.this.getWorkflowViewModel().dispatchUIEvent(new UIEvent.OnFragmentResult.OnPoaSubmissionResult(PoaHostFragment.INSTANCE.getPoaResult(bundle)));
                            return;
                        }
                        break;
                    case -114262742:
                        if (requestKey.equals(HostedWebModuleScreen.KEY_REQUEST)) {
                            WorkflowFragment.this.getWorkflowViewModel().dispatchUIEvent(new UIEvent.OnFragmentResult.OnHostedWebModuleResult(HostedWebModuleFragment.INSTANCE.getResult(bundle)));
                            return;
                        }
                        break;
                    case -100988718:
                        if (requestKey.equals(NfcFlowScreen.KEY_REQUEST)) {
                            WorkflowFragment.this.getWorkflowViewModel().dispatchUIEvent(new UIEvent.OnFragmentResult.OnNfcFlowResult(NfcHostFragment.INSTANCE.getNfcHostResult(bundle)));
                            return;
                        }
                        break;
                    case 142689746:
                        if (requestKey.equals(DocumentCaptureScreen.KEY_REQUEST)) {
                            WorkflowFragment.this.getWorkflowViewModel().dispatchUIEvent(new UIEvent.OnFragmentResult.OnDocumentCaptureFragmentResult(DocumentCaptureScreen.INSTANCE.toDocumentCaptureResult(bundle)));
                            return;
                        }
                        break;
                    case 194551046:
                        if (requestKey.equals(RetryWorkflowScreen.KEY_REQUEST)) {
                            WorkflowFragment.this.getWorkflowViewModel().dispatchUIEvent(UIEvent.OnFragmentResult.OnWorkflowRetryClick.INSTANCE);
                            return;
                        }
                        break;
                    case 306350406:
                        if (requestKey.equals(MotionScreen.KEY_REQUEST)) {
                            WorkflowFragment.this.getWorkflowViewModel().dispatchUIEvent(new UIEvent.OnFragmentResult.OnMotionResult(MotionHostFragment.INSTANCE.getAvcHostResult(bundle)));
                            return;
                        }
                        break;
                    case 452907500:
                        if (requestKey.equals("face_liveness_capture")) {
                            LivenessCaptureScreen.LivenessCaptureResult livenessCaptureResultRetrieveResult = LivenessCaptureScreen.INSTANCE.retrieveResult(bundle);
                            if (livenessCaptureResultRetrieveResult instanceof LivenessCaptureScreen.LivenessCaptureResult.Back) {
                                WorkflowFragment.this.getWorkflowViewModel().dispatchUIEvent(new UIEvent.OnBackPressed(WorkflowFragment.this.getNavigationManager().getCurrentScreensSnapshot()));
                                return;
                            } else {
                                WorkflowFragment.this.getWorkflowViewModel().dispatchUIEvent(new UIEvent.OnFragmentResult.OnFaceLivenessCaptureFragmentResult(livenessCaptureResultRetrieveResult));
                                return;
                            }
                        }
                        break;
                    case 1122753057:
                        if (requestKey.equals(LivenessConfirmationScreen.KEY_REQUEST)) {
                            WorkflowFragment.this.getWorkflowViewModel().dispatchUIEvent(new UIEvent.OnFragmentResult.LivenessConfirmationFragmentResult(LivenessConfirmationFragment.INSTANCE.getResult(bundle)));
                            return;
                        }
                        break;
                    case 1218562296:
                        if (requestKey.equals(DocumentSelectionScreen.KEY_REQUEST)) {
                            DocumentSelectionHostFragment.DocumentSelectionResult result = DocumentSelectionHostFragment.INSTANCE.getResult(bundle);
                            if (!(result instanceof DocumentSelectionHostFragment.DocumentSelectionResult.Completed)) {
                                WorkflowFragment.this.getWorkflowViewModel().dispatchUIEvent(new UIEvent.OnBackPressed(WorkflowFragment.this.getNavigationManager().getCurrentScreensSnapshot()));
                                return;
                            } else {
                                DocumentSelectionHostFragment.DocumentSelectionResult.Completed completed = (DocumentSelectionHostFragment.DocumentSelectionResult.Completed) result;
                                WorkflowFragment.this.getWorkflowViewModel().dispatchUIEvent(new UIEvent.OnFragmentResult.DocumentSelectionFragmentResult(completed.getDocumentType(), completed.getCountryCode()));
                                return;
                            }
                        }
                        break;
                    case 1270914846:
                        if (requestKey.equals(NfcNotSupportedScreen.KEY_REQUEST)) {
                            WorkflowFragment.this.getWorkflowViewModel().dispatchUIEvent(UIEvent.OnFragmentResult.OnNfcNotSupportedFragmentResult.INSTANCE);
                            return;
                        }
                        break;
                    case 2074172951:
                        if (requestKey.equals("request_key_permissions_screen")) {
                            WorkflowFragment.this.getWorkflowViewModel().dispatchUIEvent(WorkflowFragment.this.permissionResultToUiEvent(bundle));
                            return;
                        }
                        break;
                }
                throw new IllegalArgumentException("Unknown request key: " + requestKey);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final UIEvent.OnFragmentResult.OnPermissionsScreenResult permissionResultToUiEvent(Bundle bundle) {
        PermissionResult result = PermissionsManagementFragment.INSTANCE.getResult(bundle);
        if (result instanceof PermissionResult.Granted) {
            return new UIEvent.OnFragmentResult.OnPermissionsScreenResult.Granted(((PermissionResult.Granted) result).getCaptureStepDataBundle());
        }
        if (result instanceof PermissionResult.Canceled) {
            return UIEvent.OnFragmentResult.OnPermissionsScreenResult.Canceled.INSTANCE;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleToolbarState(ToolbarState toolbarState) {
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNull(fragmentActivityRequireActivity, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        AppCompatActivity appCompatActivity = (AppCompatActivity) fragmentActivityRequireActivity;
        if (toolbarState.getUseWebToolbar()) {
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.hide();
            }
        } else {
            if (toolbarState.getShowExitButton()) {
                appCompatActivity.removeMenuProvider(getUserExitFlowMenuProvider());
                appCompatActivity.addMenuProvider(getUserExitFlowMenuProvider(), getViewLifecycleOwner());
            } else {
                appCompatActivity.removeMenuProvider(getUserExitFlowMenuProvider());
            }
            ActionBar supportActionBar2 = appCompatActivity.getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.show();
            }
            ActionBar supportActionBar3 = appCompatActivity.getSupportActionBar();
            if (supportActionBar3 != null) {
                supportActionBar3.setHomeButtonEnabled(toolbarState.getShowBackButton());
            }
            setupToolbarBackButton(toolbarState.getShowBackButton());
        }
        ActivityResultCaller activityResultCallerFindFragmentById = getChildFragmentManager().findFragmentById(R.id.fragment_container);
        WorkflowToolbarUpdateListener workflowToolbarUpdateListener = activityResultCallerFindFragmentById instanceof WorkflowToolbarUpdateListener ? (WorkflowToolbarUpdateListener) activityResultCallerFindFragmentById : null;
        if (workflowToolbarUpdateListener != null) {
            workflowToolbarUpdateListener.onWorkflowToolbarUpdated();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupToolbarBackButton(boolean showBackButton) {
        ColorDrawable colorDrawable;
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNull(fragmentActivityRequireActivity, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        AppCompatActivity appCompatActivity = (AppCompatActivity) fragmentActivityRequireActivity;
        ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(showBackButton);
        }
        if (showBackButton) {
            colorDrawable = ContextCompat.getDrawable(requireContext(), androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        } else {
            colorDrawable = new ColorDrawable(0);
        }
        ActionBar supportActionBar2 = appCompatActivity.getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setHomeAsUpIndicator(colorDrawable);
        }
    }

    /* compiled from: WorkflowFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/onfido/workflow/internal/ui/WorkflowFragment$Companion;", "", "()V", "newInstance", "Lcom/onfido/workflow/internal/ui/WorkflowFragment;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final WorkflowFragment newInstance() {
            return new WorkflowFragment();
        }
    }
}
