package com.onfido.workflow.internal.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.RepeatOnLifecycleKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Provider;
import com.onfido.workflow.databinding.WorkflowLoadingFragmentBinding;
import com.onfido.workflow.internal.di.WorkflowComponent;
import com.onfido.workflow.internal.ui.WorkflowLoadingViewModel;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Function;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: WorkflowLoadingFragment.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001aH\u0016J\u0010\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001f"}, d2 = {"Lcom/onfido/workflow/internal/ui/WorkflowLoadingFragment;", "Landroidx/fragment/app/Fragment;", "()V", "binding", "Lcom/onfido/workflow/databinding/WorkflowLoadingFragmentBinding;", "viewModel", "Lcom/onfido/workflow/internal/ui/WorkflowLoadingViewModel;", "getViewModel", "()Lcom/onfido/workflow/internal/ui/WorkflowLoadingViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "viewModelProvider", "Lcom/onfido/javax/inject/Provider;", "getViewModelProvider", "()Lcom/onfido/javax/inject/Provider;", "setViewModelProvider", "(Lcom/onfido/javax/inject/Provider;)V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "", "onResume", "renderState", "state", "Lcom/onfido/workflow/internal/ui/WorkflowLoadingViewModel$ViewState;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WorkflowLoadingFragment extends Fragment {
    private WorkflowLoadingFragmentBinding binding;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    @Inject
    public Provider<WorkflowLoadingViewModel> viewModelProvider;

    public final void setViewModelProvider(Provider<WorkflowLoadingViewModel> provider) {
        Intrinsics.checkNotNullParameter(provider, "<set-?>");
        this.viewModelProvider = provider;
    }

    public final Provider<WorkflowLoadingViewModel> getViewModelProvider() {
        Provider<WorkflowLoadingViewModel> provider = this.viewModelProvider;
        if (provider != null) {
            return provider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelProvider");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WorkflowLoadingViewModel getViewModel() {
        return (WorkflowLoadingViewModel) this.viewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        WorkflowComponent.Companion companion = WorkflowComponent.INSTANCE;
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        companion.get(contextRequireContext).inject(this);
        WorkflowLoadingFragmentBinding workflowLoadingFragmentBindingInflate = WorkflowLoadingFragmentBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(workflowLoadingFragmentBindingInflate, "inflate(...)");
        this.binding = workflowLoadingFragmentBindingInflate;
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        WorkflowLoadingFragmentBinding workflowLoadingFragmentBinding = null;
        LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner).launchWhenStarted(new AnonymousClass1(null));
        WorkflowLoadingFragmentBinding workflowLoadingFragmentBinding2 = this.binding;
        if (workflowLoadingFragmentBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            workflowLoadingFragmentBinding = workflowLoadingFragmentBinding2;
        }
        ConstraintLayout root = workflowLoadingFragmentBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    /* compiled from: WorkflowLoadingFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.workflow.internal.ui.WorkflowLoadingFragment$onCreateView$1", f = "WorkflowLoadingFragment.kt", i = {}, l = {45}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.workflow.internal.ui.WorkflowLoadingFragment$onCreateView$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WorkflowLoadingFragment.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: WorkflowLoadingFragment.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.workflow.internal.ui.WorkflowLoadingFragment$onCreateView$1$1", f = "WorkflowLoadingFragment.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.workflow.internal.ui.WorkflowLoadingFragment$onCreateView$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01751 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ WorkflowLoadingFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01751(WorkflowLoadingFragment workflowLoadingFragment, Continuation<? super C01751> continuation) {
                super(2, continuation);
                this.this$0 = workflowLoadingFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01751(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01751) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* compiled from: WorkflowLoadingFragment.kt */
            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            /* renamed from: com.onfido.workflow.internal.ui.WorkflowLoadingFragment$onCreateView$1$1$1, reason: invalid class name and collision with other inner class name */
            /* synthetic */ class C01761 implements FlowCollector, FunctionAdapter {
                final /* synthetic */ WorkflowLoadingFragment $tmp0;

                C01761(WorkflowLoadingFragment workflowLoadingFragment) {
                    this.$tmp0 = workflowLoadingFragment;
                }

                public final boolean equals(Object obj) {
                    if ((obj instanceof FlowCollector) && (obj instanceof FunctionAdapter)) {
                        return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
                    }
                    return false;
                }

                @Override // kotlin.jvm.internal.FunctionAdapter
                public final Function<?> getFunctionDelegate() {
                    return new AdaptedFunctionReference(2, this.$tmp0, WorkflowLoadingFragment.class, "renderState", "renderState(Lcom/onfido/workflow/internal/ui/WorkflowLoadingViewModel$ViewState;)V", 4);
                }

                public final int hashCode() {
                    return getFunctionDelegate().hashCode();
                }

                public final Object emit(WorkflowLoadingViewModel.ViewState viewState, Continuation<? super Unit> continuation) {
                    Object objInvokeSuspend$renderState = C01751.invokeSuspend$renderState(this.$tmp0, viewState, continuation);
                    return objInvokeSuspend$renderState == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInvokeSuspend$renderState : Unit.INSTANCE;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                    return emit((WorkflowLoadingViewModel.ViewState) obj, (Continuation<? super Unit>) continuation);
                }
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (this.this$0.getViewModel().getViewState().collect(new C01761(this.this$0), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                throw new KotlinNothingValueException();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final /* synthetic */ Object invokeSuspend$renderState(WorkflowLoadingFragment workflowLoadingFragment, WorkflowLoadingViewModel.ViewState viewState, Continuation continuation) {
                workflowLoadingFragment.renderState(viewState);
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Lifecycle lifecycle = WorkflowLoadingFragment.this.getViewLifecycleOwner().getLifecycleRegistry();
                Intrinsics.checkNotNullExpressionValue(lifecycle, "getLifecycle(...)");
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycle, Lifecycle.State.STARTED, new C01751(WorkflowLoadingFragment.this, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getViewModel().onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        getViewModel().onPause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderState(WorkflowLoadingViewModel.ViewState state) {
        WorkflowLoadingFragmentBinding workflowLoadingFragmentBinding = this.binding;
        if (workflowLoadingFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            workflowLoadingFragmentBinding = null;
        }
        if (Intrinsics.areEqual(state, WorkflowLoadingViewModel.ViewState.ShowProgressOnly.INSTANCE)) {
            TextView title = workflowLoadingFragmentBinding.title;
            Intrinsics.checkNotNullExpressionValue(title, "title");
            ViewExtensionsKt.toInvisible$default(title, false, 1, null);
            TextView subtitle = workflowLoadingFragmentBinding.subtitle;
            Intrinsics.checkNotNullExpressionValue(subtitle, "subtitle");
            ViewExtensionsKt.toInvisible$default(subtitle, false, 1, null);
            return;
        }
        if (state instanceof WorkflowLoadingViewModel.ViewState.ShowWaitingMessages) {
            TextView title2 = workflowLoadingFragmentBinding.title;
            Intrinsics.checkNotNullExpressionValue(title2, "title");
            ViewExtensionsKt.toVisible$default(title2, false, 1, null);
            TextView subtitle2 = workflowLoadingFragmentBinding.subtitle;
            Intrinsics.checkNotNullExpressionValue(subtitle2, "subtitle");
            ViewExtensionsKt.toVisible$default(subtitle2, false, 1, null);
            TextView title3 = workflowLoadingFragmentBinding.title;
            Intrinsics.checkNotNullExpressionValue(title3, "title");
            WorkflowLoadingViewModel.ViewState.ShowWaitingMessages showWaitingMessages = (WorkflowLoadingViewModel.ViewState.ShowWaitingMessages) state;
            TextViewUtilsKt.setTextWithAnimation(title3, showWaitingMessages.getTitleId());
            TextView subtitle3 = workflowLoadingFragmentBinding.subtitle;
            Intrinsics.checkNotNullExpressionValue(subtitle3, "subtitle");
            TextViewUtilsKt.setTextWithAnimation(subtitle3, showWaitingMessages.getSubTitleId());
        }
    }

    public WorkflowLoadingFragment() {
        final WorkflowLoadingFragment workflowLoadingFragment = this;
        Function0<ViewModelProvider.Factory> function0 = new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.workflow.internal.ui.WorkflowLoadingFragment$viewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                final WorkflowLoadingFragment workflowLoadingFragment2 = this.this$0;
                return new ViewModelProvider.Factory() { // from class: com.onfido.workflow.internal.ui.WorkflowLoadingFragment$viewModel$2$invoke$$inlined$createViewModelFactory$1
                    @Override // androidx.lifecycle.ViewModelProvider.Factory
                    public <T extends ViewModel> T create(Class<T> modelClass) {
                        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                        WorkflowLoadingViewModel workflowLoadingViewModel = workflowLoadingFragment2.getViewModelProvider().get();
                        Intrinsics.checkNotNull(workflowLoadingViewModel, "null cannot be cast to non-null type T of com.onfido.android.sdk.capture.component.utils.ViewModelExtKt.createViewModelFactory.<no name provided>.create");
                        return workflowLoadingViewModel;
                    }
                };
            }
        };
        final Function0<Fragment> function02 = new Function0<Fragment>() { // from class: com.onfido.workflow.internal.ui.WorkflowLoadingFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return workflowLoadingFragment;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.workflow.internal.ui.WorkflowLoadingFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) function02.invoke();
            }
        });
        final Function0 function03 = null;
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(workflowLoadingFragment, Reflection.getOrCreateKotlinClass(WorkflowLoadingViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.workflow.internal.ui.WorkflowLoadingFragment$special$$inlined$viewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = FragmentViewModelLazyKt.m4679viewModels$lambda1(lazy).getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "owner.viewModelStore");
                return viewModelStore;
            }
        }, new Function0<CreationExtras>() { // from class: com.onfido.workflow.internal.ui.WorkflowLoadingFragment$special$$inlined$viewModels$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function04 = function03;
                if (function04 != null && (creationExtras = (CreationExtras) function04.invoke()) != null) {
                    return creationExtras;
                }
                ViewModelStoreOwner viewModelStoreOwnerM4679viewModels$lambda1 = FragmentViewModelLazyKt.m4679viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM4679viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM4679viewModels$lambda1 : null;
                CreationExtras defaultViewModelCreationExtras = hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : null;
                return defaultViewModelCreationExtras == null ? CreationExtras.Empty.INSTANCE : defaultViewModelCreationExtras;
            }
        }, function0);
    }
}
