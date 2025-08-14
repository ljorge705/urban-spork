package com.onfido.android.sdk.capture.ui.nfc;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;
import com.onfido.javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u001a\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\t\u001a\u00020\n8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcCanMaxAttemptsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "nfcViewModel", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel;", "getNfcViewModel", "()Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel;", "nfcViewModel$delegate", "Lkotlin/Lazy;", "screenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;", "getScreenTracker$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;", "setScreenTracker$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;)V", "onStart", "", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcCanMaxAttemptsFragment extends Fragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* renamed from: nfcViewModel$delegate, reason: from kotlin metadata */
    private final Lazy nfcViewModel;

    @Inject
    public ScreenTracker screenTracker;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcCanMaxAttemptsFragment$Companion;", "", "()V", "createInstance", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcCanMaxAttemptsFragment;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final NfcCanMaxAttemptsFragment createInstance() {
            return new NfcCanMaxAttemptsFragment();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public NfcCanMaxAttemptsFragment() {
        super(R.layout.onfido_fragment_nfc_can_max_attempts);
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcCanMaxAttemptsFragment$nfcViewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                Fragment fragmentRequireParentFragment = this.this$0.requireParentFragment();
                Intrinsics.checkNotNullExpressionValue(fragmentRequireParentFragment, "requireParentFragment(...)");
                return fragmentRequireParentFragment;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcCanMaxAttemptsFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) function0.invoke();
            }
        });
        final Function0 function02 = null;
        this.nfcViewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(NfcHostViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcCanMaxAttemptsFragment$special$$inlined$viewModels$default$2
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
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcCanMaxAttemptsFragment$special$$inlined$viewModels$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function03 = function02;
                if (function03 != null && (creationExtras = (CreationExtras) function03.invoke()) != null) {
                    return creationExtras;
                }
                ViewModelStoreOwner viewModelStoreOwnerM4679viewModels$lambda1 = FragmentViewModelLazyKt.m4679viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM4679viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM4679viewModels$lambda1 : null;
                CreationExtras defaultViewModelCreationExtras = hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : null;
                return defaultViewModelCreationExtras == null ? CreationExtras.Empty.INSTANCE : defaultViewModelCreationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcCanMaxAttemptsFragment$special$$inlined$viewModels$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory;
                ViewModelStoreOwner viewModelStoreOwnerM4679viewModels$lambda1 = FragmentViewModelLazyKt.m4679viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM4679viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM4679viewModels$lambda1 : null;
                if (hasDefaultViewModelProviderFactory == null || (defaultViewModelProviderFactory = hasDefaultViewModelProviderFactory.getDefaultViewModelProviderFactory()) == null) {
                    defaultViewModelProviderFactory = this.getDefaultViewModelProviderFactory();
                }
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "(owner as? HasDefaultVie…tViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
    }

    @JvmStatic
    public static final NfcCanMaxAttemptsFragment createInstance() {
        return INSTANCE.createInstance();
    }

    private final NfcHostViewModel getNfcViewModel() {
        return (NfcHostViewModel) this.nfcViewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(NfcCanMaxAttemptsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getNfcViewModel().onNfcCanMaxAttemptsButtonClicked();
    }

    public final ScreenTracker getScreenTracker$onfido_capture_sdk_core_release() {
        ScreenTracker screenTracker = this.screenTracker;
        if (screenTracker != null) {
            return screenTracker;
        }
        Intrinsics.throwUninitializedPropertyAccessException("screenTracker");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        getScreenTracker$onfido_capture_sdk_core_release().trackNfcCanMaxAttemptsReached$onfido_capture_sdk_core_release();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        SdkController companion = SdkController.INSTANCE.getInstance();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        super.onViewCreated(view, savedInstanceState);
        OnfidoButton onfidoButton = (OnfidoButton) view.findViewById(R.id.btnExit);
        onfidoButton.setText$onfido_capture_sdk_core_release(getNfcViewModel().getNfcCanMaxAttemptsButtonText());
        onfidoButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcCanMaxAttemptsFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                NfcCanMaxAttemptsFragment.onViewCreated$lambda$0(this.f$0, view2);
            }
        });
    }

    public final void setScreenTracker$onfido_capture_sdk_core_release(ScreenTracker screenTracker) {
        Intrinsics.checkNotNullParameter(screenTracker, "<set-?>");
        this.screenTracker = screenTracker;
    }
}
