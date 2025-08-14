package com.onfido.android.sdk.capture.core;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.onfido.android.sdk.FlowConfig;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.core.config.Flow;
import com.onfido.android.sdk.capture.core.config.FlowFragment;
import com.onfido.android.sdk.capture.core.config.OnfidoNewConfig;
import com.onfido.android.sdk.capture.core.di.DaggerOnfidoComponent;
import com.onfido.android.sdk.capture.core.di.OnfidoComponent;
import com.onfido.javax.inject.Inject;
import io.sentry.protocol.Request;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0004H\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0001H\u0002J\u001a\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006 "}, d2 = {"Lcom/onfido/android/sdk/capture/core/OnfidoFragment;", "Landroidx/fragment/app/Fragment;", "()V", "currentFlow", "Lcom/onfido/android/sdk/capture/core/config/Flow;", "currentFlowIndex", "", "onfidoComponent", "Lcom/onfido/android/sdk/capture/core/di/OnfidoComponent;", "getOnfidoComponent", "()Lcom/onfido/android/sdk/capture/core/di/OnfidoComponent;", "onfidoComponent$delegate", "Lkotlin/Lazy;", "results", "", "Lcom/onfido/android/sdk/capture/core/config/Flow$Result;", "viewModel", "Lcom/onfido/android/sdk/capture/core/OnfidoViewModel;", "getViewModel", "()Lcom/onfido/android/sdk/capture/core/OnfidoViewModel;", "setViewModel", "(Lcom/onfido/android/sdk/capture/core/OnfidoViewModel;)V", "getNextFlow", "navigateTo", "", Request.JsonKeys.FRAGMENT, "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoFragment extends Fragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Flow currentFlow;
    private int currentFlowIndex;

    /* renamed from: onfidoComponent$delegate, reason: from kotlin metadata */
    private final Lazy onfidoComponent;
    private final List<Flow.Result> results;

    @Inject
    public OnfidoViewModel viewModel;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/core/OnfidoFragment$Companion;", "", "()V", "newInstance", "Lcom/onfido/android/sdk/capture/core/OnfidoFragment;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final OnfidoFragment newInstance() {
            return new OnfidoFragment();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public OnfidoFragment() {
        super(R.layout.onfido_fragment);
        this.currentFlowIndex = -1;
        this.results = new ArrayList();
        this.onfidoComponent = LazyKt.lazy(new Function0<OnfidoComponent>() { // from class: com.onfido.android.sdk.capture.core.OnfidoFragment$onfidoComponent$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final OnfidoComponent invoke() {
                final OnfidoFragment onfidoFragment = this.this$0;
                return (OnfidoComponent) new ViewModelProvider(onfidoFragment, new ViewModelProvider.Factory() { // from class: com.onfido.android.sdk.capture.core.OnfidoFragment$onfidoComponent$2$invoke$$inlined$createViewModelFactory$1
                    @Override // androidx.lifecycle.ViewModelProvider.Factory
                    public <T extends ViewModel> T create(Class<T> modelClass) {
                        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                        OnfidoComponent.Factory factory = DaggerOnfidoComponent.factory();
                        SdkController companion = SdkController.INSTANCE.getInstance();
                        Context contextRequireContext = onfidoFragment.requireContext();
                        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
                        OnfidoComponent onfidoComponentCreate = factory.create(SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null));
                        Intrinsics.checkNotNull(onfidoComponentCreate, "null cannot be cast to non-null type T of com.onfido.android.sdk.capture.component.utils.ViewModelExtKt.createViewModelFactory.<no name provided>.create");
                        return onfidoComponentCreate;
                    }
                }).get(OnfidoComponent.class);
            }
        });
    }

    private final Flow getNextFlow() {
        FlowConfig flowConfig = getViewModel().getFlowConfig();
        Intrinsics.checkNotNull(flowConfig, "null cannot be cast to non-null type com.onfido.android.sdk.capture.core.config.OnfidoNewConfig");
        List<Flow> flows = ((OnfidoNewConfig) flowConfig).getFlows();
        int i = this.currentFlowIndex + 1;
        this.currentFlowIndex = i;
        Flow flow = (Flow) CollectionsKt.getOrNull(flows, i);
        this.currentFlow = flow;
        return flow;
    }

    private final OnfidoComponent getOnfidoComponent() {
        return (OnfidoComponent) this.onfidoComponent.getValue();
    }

    private final void navigateTo(Fragment fragment) {
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
        FragmentTransaction fragmentTransactionBeginTransaction = childFragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction()");
        fragmentTransactionBeginTransaction.setReorderingAllowed(true);
        fragmentTransactionBeginTransaction.replace(R.id.container, fragment);
        fragmentTransactionBeginTransaction.commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(OnfidoFragment this$0, String str, Bundle result) {
        FlowFragment flowFragmentCreateFragment$default;
        List<Flow> followUpFlows;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(result, "result");
        Flow.Result result2 = (Flow.Result) result.getParcelable("request");
        if (result2 != null) {
            this$0.results.add(result2);
        }
        Flow flow = this$0.currentFlow;
        Flow flow2 = (flow == null || (followUpFlows = flow.getFollowUpFlows()) == null) ? null : (Flow) CollectionsKt.firstOrNull((List) followUpFlows);
        if (flow2 != null) {
            this$0.currentFlow = null;
            flowFragmentCreateFragment$default = flow2.createFragment(result2);
        } else {
            Flow nextFlow = this$0.getNextFlow();
            if (nextFlow == null) {
                Intent intentPutExtra = new Intent().putExtra(OnfidoLauncher.KEY_RESULT, new OnfidoResult(this$0.results, true));
                Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
                this$0.requireActivity().setResult(-1, intentPutExtra);
                this$0.requireActivity().finish();
                return;
            }
            flowFragmentCreateFragment$default = Flow.createFragment$default(nextFlow, null, 1, null);
        }
        this$0.navigateTo(flowFragmentCreateFragment$default);
    }

    public final OnfidoViewModel getViewModel() {
        OnfidoViewModel onfidoViewModel = this.viewModel;
        if (onfidoViewModel != null) {
            return onfidoViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        getOnfidoComponent().inject$onfido_capture_sdk_core_release(this);
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            Flow nextFlow = getNextFlow();
            getChildFragmentManager().setFragmentResultListener("request", this, new FragmentResultListener() { // from class: com.onfido.android.sdk.capture.core.OnfidoFragment$$ExternalSyntheticLambda0
                @Override // androidx.fragment.app.FragmentResultListener
                public final void onFragmentResult(String str, Bundle bundle) {
                    OnfidoFragment.onViewCreated$lambda$0(this.f$0, str, bundle);
                }
            });
            if (nextFlow != null) {
                navigateTo(Flow.createFragment$default(nextFlow, null, 1, null));
            }
        }
    }

    public final void setViewModel(OnfidoViewModel onfidoViewModel) {
        Intrinsics.checkNotNullParameter(onfidoViewModel, "<set-?>");
        this.viewModel = onfidoViewModel;
    }
}
