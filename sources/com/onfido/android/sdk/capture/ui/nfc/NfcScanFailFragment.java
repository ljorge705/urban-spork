package com.onfido.android.sdk.capture.ui.nfc;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentNfcScanFailBinding;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.android.sdk.capture.ui.camera.view.PlaybackControlsVideoPlayerView;
import com.onfido.android.sdk.capture.ui.nfc.NfcFailedScreen;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0003J\b\u0010\u0018\u001a\u00020\u0016H\u0003J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u001a\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\f\u0010 \u001a\u00020\u001a*\u00020!H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u000e\u001a\u00020\u000f8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006#"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcScanFailFragment;", "Landroidx/fragment/app/Fragment;", "()V", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "documentType$delegate", "Lkotlin/Lazy;", "nfcViewModel", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel;", "getNfcViewModel", "()Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel;", "nfcViewModel$delegate", "screenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;", "getScreenTracker$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;", "setScreenTracker$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;)V", "createInstructions", "", "", "getErrorTitleRes", "getPrimaryActionRes", "onStart", "", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setInstructions", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentNfcScanFailBinding;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcScanFailFragment extends Fragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String DOCUMENT_TYPE_KEY = "document_type_key";
    private static final String ERROR_STATE_KEY = "error_state_key";
    private static final String FAILURE_REASON_KEY = "failure_reason_key";
    private static final String PRIMARY_ACTION_KEY = "primary_action_key";
    private static final String SECONDARY_ACTION_KEY = "secondary_action_key";

    /* renamed from: documentType$delegate, reason: from kotlin metadata */
    private final Lazy documentType;

    /* renamed from: nfcViewModel$delegate, reason: from kotlin metadata */
    private final Lazy nfcViewModel;

    @Inject
    public ScreenTracker screenTracker;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcScanFailFragment$Companion;", "", "()V", "DOCUMENT_TYPE_KEY", "", "ERROR_STATE_KEY", "FAILURE_REASON_KEY", "PRIMARY_ACTION_KEY", "SECONDARY_ACTION_KEY", "createInstance", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcScanFailFragment;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "failureReason", "errorState", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$ErrorState;", "primaryAction", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$Actions;", "secondaryAction", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public static /* synthetic */ NfcScanFailFragment createInstance$default(Companion companion, DocumentType documentType, String str, NfcFailedScreen.ErrorState errorState, NfcFailedScreen.Actions actions, NfcFailedScreen.Actions actions2, int i, Object obj) {
            if ((i & 2) != 0) {
                str = "";
            }
            return companion.createInstance(documentType, str, errorState, actions, actions2);
        }

        public final NfcScanFailFragment createInstance(DocumentType documentType, String failureReason, NfcFailedScreen.ErrorState errorState, NfcFailedScreen.Actions primaryAction, NfcFailedScreen.Actions secondaryAction) {
            Intrinsics.checkNotNullParameter(failureReason, "failureReason");
            Intrinsics.checkNotNullParameter(errorState, "errorState");
            Intrinsics.checkNotNullParameter(primaryAction, "primaryAction");
            Intrinsics.checkNotNullParameter(secondaryAction, "secondaryAction");
            NfcScanFailFragment nfcScanFailFragment = new NfcScanFailFragment();
            nfcScanFailFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(NfcScanFailFragment.DOCUMENT_TYPE_KEY, documentType), TuplesKt.to(NfcScanFailFragment.FAILURE_REASON_KEY, failureReason), TuplesKt.to(NfcScanFailFragment.ERROR_STATE_KEY, errorState), TuplesKt.to(NfcScanFailFragment.PRIMARY_ACTION_KEY, primaryAction), TuplesKt.to(NfcScanFailFragment.SECONDARY_ACTION_KEY, secondaryAction)));
            return nfcScanFailFragment;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[NfcFailedScreen.Actions.Primary.values().length];
            try {
                iArr[NfcFailedScreen.Actions.Primary.Retry.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[NfcFailedScreen.Actions.Primary.DifferentDocument.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[NfcFailedScreen.Actions.Secondary.values().length];
            try {
                iArr2[NfcFailedScreen.Actions.Secondary.Skip.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[NfcFailedScreen.Actions.Secondary.Exit.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public NfcScanFailFragment() {
        super(R.layout.onfido_fragment_nfc_scan_fail);
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcScanFailFragment$nfcViewModel$2
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
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcScanFailFragment$special$$inlined$viewModels$default$1
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
        this.nfcViewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(NfcHostViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcScanFailFragment$special$$inlined$viewModels$default$2
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
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcScanFailFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcScanFailFragment$special$$inlined$viewModels$default$4
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
        this.documentType = LazyKt.lazy(new Function0<DocumentType>() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcScanFailFragment$documentType$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DocumentType invoke() {
                Serializable serializable = this.this$0.requireArguments().getSerializable("document_type_key");
                Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.onfido.android.sdk.capture.DocumentType");
                return (DocumentType) serializable;
            }
        });
    }

    private final List<Integer> createInstructions() {
        return getDocumentType() == DocumentType.PASSPORT ? CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.string.onfido_nfc_fail_passport_list_item_1), Integer.valueOf(R.string.onfido_nfc_fail_passport_list_item_2), Integer.valueOf(R.string.onfido_nfc_capture_scan_intro_passport_scan_guide_android_3), Integer.valueOf(R.string.onfido_nfc_fail_passport_list_item_4), Integer.valueOf(R.string.onfido_nfc_fail_passport_list_item_5)}) : CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.string.onfido_nfc_fail_card_list_item_1), Integer.valueOf(R.string.onfido_nfc_fail_card_list_item_2), Integer.valueOf(R.string.onfido_nfc_fail_card_list_item_3), Integer.valueOf(R.string.onfido_nfc_fail_card_list_item_4)});
    }

    private final DocumentType getDocumentType() {
        return (DocumentType) this.documentType.getValue();
    }

    private final int getErrorTitleRes() {
        return getDocumentType() == DocumentType.PASSPORT ? R.string.onfido_nfc_fail_passport_title : R.string.onfido_nfc_fail_card_title;
    }

    private final NfcHostViewModel getNfcViewModel() {
        return (NfcHostViewModel) this.nfcViewModel.getValue();
    }

    private final int getPrimaryActionRes() {
        return getDocumentType() == DocumentType.PASSPORT ? R.string.onfido_nfc_fail_passport_button_primary : R.string.onfido_nfc_fail_button_primary_card;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$3$lambda$0(NfcFailedScreen.Actions.Primary primaryAction, NfcScanFailFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(primaryAction, "$primaryAction");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = WhenMappings.$EnumSwitchMapping$0[primaryAction.ordinal()];
        if (i == 1) {
            this$0.getNfcViewModel().onRetryNfcClicked();
        } else {
            if (i != 2) {
                return;
            }
            this$0.getNfcViewModel().goToDocumentSelection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$3$lambda$1(NfcFailedScreen.Actions.Secondary secondaryAction, NfcScanFailFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(secondaryAction, "$secondaryAction");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = WhenMappings.$EnumSwitchMapping$1[secondaryAction.ordinal()];
        if (i == 1) {
            this$0.getNfcViewModel().onSkipNfcScanAtRetryClicked();
        } else {
            if (i != 2) {
                return;
            }
            this$0.getNfcViewModel().exitFlowAtRetryClicked();
        }
    }

    private final void setInstructions(OnfidoFragmentNfcScanFailBinding onfidoFragmentNfcScanFailBinding) throws Resources.NotFoundException {
        LinearLayout stepsContainer = onfidoFragmentNfcScanFailBinding.stepsContainer;
        Intrinsics.checkNotNullExpressionValue(stepsContainer, "stepsContainer");
        ViewExtensionsKt.setInstructions(stepsContainer, createInstructions());
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
        Bundle arguments = getArguments();
        String string = arguments != null ? arguments.getString(FAILURE_REASON_KEY) : null;
        if (string == null) {
            string = "";
        }
        getScreenTracker$onfido_capture_sdk_core_release().trackNfcReadError$onfido_capture_sdk_core_release(string, getNfcViewModel().getExpectedNfcFlowType(), getNfcViewModel().getNfcOptions());
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) throws IllegalStateException, Resources.NotFoundException, IOException, SecurityException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter(view, "view");
        SdkController companion = SdkController.INSTANCE.getInstance();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        super.onViewCreated(view, savedInstanceState);
        Parcelable parcelable = requireArguments().getParcelable(PRIMARY_ACTION_KEY);
        Intrinsics.checkNotNull(parcelable, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.nfc.NfcFailedScreen.Actions.Primary");
        final NfcFailedScreen.Actions.Primary primary = (NfcFailedScreen.Actions.Primary) parcelable;
        Parcelable parcelable2 = requireArguments().getParcelable(SECONDARY_ACTION_KEY);
        Intrinsics.checkNotNull(parcelable2, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.nfc.NfcFailedScreen.Actions.Secondary");
        final NfcFailedScreen.Actions.Secondary secondary = (NfcFailedScreen.Actions.Secondary) parcelable2;
        OnfidoFragmentNfcScanFailBinding onfidoFragmentNfcScanFailBindingBind = OnfidoFragmentNfcScanFailBinding.bind(view);
        onfidoFragmentNfcScanFailBindingBind.primaryAction.setText$onfido_capture_sdk_core_release(primary.getTitle());
        if (secondary.getTitle() != 0) {
            onfidoFragmentNfcScanFailBindingBind.secondaryAction.setText$onfido_capture_sdk_core_release(secondary.getTitle());
        }
        OnfidoButton secondaryAction = onfidoFragmentNfcScanFailBindingBind.secondaryAction;
        Intrinsics.checkNotNullExpressionValue(secondaryAction, "secondaryAction");
        secondaryAction.setVisibility(secondary.getVisible() ? 0 : 8);
        onfidoFragmentNfcScanFailBindingBind.primaryAction.setText$onfido_capture_sdk_core_release(getPrimaryActionRes());
        onfidoFragmentNfcScanFailBindingBind.primaryAction.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcScanFailFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                NfcScanFailFragment.onViewCreated$lambda$3$lambda$0(primary, this, view2);
            }
        });
        onfidoFragmentNfcScanFailBindingBind.secondaryAction.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcScanFailFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                NfcScanFailFragment.onViewCreated$lambda$3$lambda$1(secondary, this, view2);
            }
        });
        onfidoFragmentNfcScanFailBindingBind.title.setText(getErrorTitleRes());
        Intrinsics.checkNotNull(onfidoFragmentNfcScanFailBindingBind);
        setInstructions(onfidoFragmentNfcScanFailBindingBind);
        PlaybackControlsVideoPlayerView playbackControlsVideoPlayerView = onfidoFragmentNfcScanFailBindingBind.videoView;
        Intrinsics.checkNotNull(playbackControlsVideoPlayerView);
        ViewExtensionsKt.toVisible$default(playbackControlsVideoPlayerView, false, 1, null);
        playbackControlsVideoPlayerView.setLoading(false);
        playbackControlsVideoPlayerView.setVideoUrl(getNfcViewModel().getInstructionVideoPathForFailure());
    }

    public final void setScreenTracker$onfido_capture_sdk_core_release(ScreenTracker screenTracker) {
        Intrinsics.checkNotNullParameter(screenTracker, "<set-?>");
        this.screenTracker = screenTracker;
    }
}
