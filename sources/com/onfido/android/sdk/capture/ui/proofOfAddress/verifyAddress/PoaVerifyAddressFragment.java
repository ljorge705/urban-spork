package com.onfido.android.sdk.capture.ui.proofOfAddress.verifyAddress;

import android.os.Bundle;
import android.view.View;
import androidx.activity.result.ActivityResultCaller;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.BulletStepView;
import com.onfido.android.sdk.capture.ui.BulletedMessageFragment;
import com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaFlowController;
import com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostFragment;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Provider;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u001a\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0010\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\b\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/verifyAddress/PoaVerifyAddressFragment;", "Lcom/onfido/android/sdk/capture/ui/BulletedMessageFragment;", "()V", "poaController", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/host/PoaFlowController;", "getPoaController", "()Lcom/onfido/android/sdk/capture/ui/proofOfAddress/host/PoaFlowController;", "poaController$delegate", "Lkotlin/Lazy;", "poaViewModelProvider", "Lcom/onfido/javax/inject/Provider;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/verifyAddress/PoaVerifyAddressViewModel;", "getPoaViewModelProvider", "()Lcom/onfido/javax/inject/Provider;", "setPoaViewModelProvider", "(Lcom/onfido/javax/inject/Provider;)V", "viewModel", "getViewModel", "()Lcom/onfido/android/sdk/capture/ui/proofOfAddress/verifyAddress/PoaVerifyAddressViewModel;", "viewModel$delegate", "onStart", "", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setCustomStep", "index", "", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PoaVerifyAddressFragment extends BulletedMessageFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int STEP_COUNT = 3;

    /* renamed from: poaController$delegate, reason: from kotlin metadata */
    private final Lazy poaController = LazyKt.lazy(new Function0<PoaFlowController>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.verifyAddress.PoaVerifyAddressFragment$poaController$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PoaFlowController invoke() {
            ActivityResultCaller parentFragment = this.this$0.getParentFragment();
            Intrinsics.checkNotNull(parentFragment, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaFlowController");
            return (PoaFlowController) parentFragment;
        }
    });

    @Inject
    public Provider<PoaVerifyAddressViewModel> poaViewModelProvider;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/verifyAddress/PoaVerifyAddressFragment$Companion;", "", "()V", "STEP_COUNT", "", "createInstance", "Lcom/onfido/android/sdk/capture/ui/BulletedMessageFragment;", "title", "", KeychainModule.AuthPromptOptions.SUBTITLE, "bullets", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final BulletedMessageFragment createInstance(String title, String subtitle, List<String> bullets) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(subtitle, "subtitle");
            Intrinsics.checkNotNullParameter(bullets, "bullets");
            PoaVerifyAddressFragment poaVerifyAddressFragment = new PoaVerifyAddressFragment();
            BulletedMessageFragment.setInfo$onfido_capture_sdk_core_release$default(poaVerifyAddressFragment, "", title, subtitle, bullets, true, true, null, 64, null);
            return poaVerifyAddressFragment;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public PoaVerifyAddressFragment() {
        Function0<ViewModelProvider.Factory> function0 = new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.verifyAddress.PoaVerifyAddressFragment$viewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                final PoaVerifyAddressFragment poaVerifyAddressFragment = this.this$0;
                return new ViewModelProvider.Factory() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.verifyAddress.PoaVerifyAddressFragment$viewModel$2$invoke$$inlined$createViewModelFactory$1
                    @Override // androidx.lifecycle.ViewModelProvider.Factory
                    public <T extends ViewModel> T create(Class<T> modelClass) {
                        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                        PoaVerifyAddressViewModel poaVerifyAddressViewModel = poaVerifyAddressFragment.getPoaViewModelProvider().get();
                        Intrinsics.checkNotNull(poaVerifyAddressViewModel, "null cannot be cast to non-null type T of com.onfido.android.sdk.capture.component.utils.ViewModelExtKt.createViewModelFactory.<no name provided>.create");
                        return poaVerifyAddressViewModel;
                    }
                };
            }
        };
        final Function0<Fragment> function02 = new Function0<Fragment>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.verifyAddress.PoaVerifyAddressFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.verifyAddress.PoaVerifyAddressFragment$special$$inlined$viewModels$default$2
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(PoaVerifyAddressViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.verifyAddress.PoaVerifyAddressFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.verifyAddress.PoaVerifyAddressFragment$special$$inlined$viewModels$default$4
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

    @JvmStatic
    public static final BulletedMessageFragment createInstance(String str, String str2, List<String> list) {
        return INSTANCE.createInstance(str, str2, list);
    }

    private final PoaFlowController getPoaController() {
        return (PoaFlowController) this.poaController.getValue();
    }

    private final PoaVerifyAddressViewModel getViewModel() {
        return (PoaVerifyAddressViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$2$lambda$1(PoaVerifyAddressFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getPoaController().showPoaCountrySelectionScreen();
    }

    private final void setCustomStep(int index) {
        View childAt = getBinding().stepsContainer.getChildAt(index);
        Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.BulletStepView");
        BulletStepView bulletStepView = (BulletStepView) childAt;
        bulletStepView.setStepNumber(index);
        bulletStepView.setHtmlTitle();
    }

    public final Provider<PoaVerifyAddressViewModel> getPoaViewModelProvider() {
        Provider<PoaVerifyAddressViewModel> provider = this.poaViewModelProvider;
        if (provider != null) {
            return provider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("poaViewModelProvider");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        getViewModel().trackPoaVerifyAddress();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        Fragment parentFragment = getParentFragment();
        Intrinsics.checkNotNull(parentFragment, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostFragment");
        ((PoaHostFragment) parentFragment).getPoaComponent$onfido_capture_sdk_core_release().inject(this);
        super.onViewCreated(view, savedInstanceState);
        Iterator<Integer> it = new IntRange(1, 3).iterator();
        while (it.hasNext()) {
            setCustomStep(((IntIterator) it).nextInt());
        }
        OnfidoButton onfidoButton = getBinding().next;
        onfidoButton.setText$onfido_capture_sdk_core_release(R.string.onfido_poa_intro_button_primary);
        onfidoButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.verifyAddress.PoaVerifyAddressFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PoaVerifyAddressFragment.onViewCreated$lambda$2$lambda$1(this.f$0, view2);
            }
        });
    }

    public final void setPoaViewModelProvider(Provider<PoaVerifyAddressViewModel> provider) {
        Intrinsics.checkNotNullParameter(provider, "<set-?>");
        this.poaViewModelProvider = provider;
    }
}
