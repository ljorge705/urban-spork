package com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection;

import android.os.Bundle;
import android.view.View;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentPoaDocumentSelectionBinding;
import com.onfido.android.sdk.capture.document.selection.DocumentSelectionButton;
import com.onfido.android.sdk.capture.ui.BaseFragment;
import com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostFragment;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.api.client.data.PoaDocumentType;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Provider;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 !2\u00020\u0001:\u0001!B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0016\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00170\u0016j\b\u0012\u0004\u0012\u00020\u0017`\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0010H\u0016J\u001a\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u0017H\u0002R$\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001b\u0010\n\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\""}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSelection/PoaDocumentSelectionFragment;", "Lcom/onfido/android/sdk/capture/ui/BaseFragment;", "()V", "poaViewModelFactory", "Lcom/onfido/javax/inject/Provider;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSelection/PoaDocumentSelectionViewModel;", "getPoaViewModelFactory", "()Lcom/onfido/javax/inject/Provider;", "setPoaViewModelFactory", "(Lcom/onfido/javax/inject/Provider;)V", "viewModel", "getViewModel", "()Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSelection/PoaDocumentSelectionViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "adjustUiDependingOnDocumentType", "", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentPoaDocumentSelectionBinding;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "supportedPoaDocumentTypes", "Ljava/util/ArrayList;", "Lcom/onfido/api/client/data/PoaDocumentType;", "Lkotlin/collections/ArrayList;", "onStart", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "submitResult", "documentType", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PoaDocumentSelectionFragment extends BaseFragment {
    private static final String COUNTRY_CODE = "country_code";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_DOCUMENT_TYPE = "document_type";
    private static final String KEY_RESULT = "key_result";
    private static final String SUPPORTED_DOCS = "supported_docs";

    @Inject
    public Provider<PoaDocumentSelectionViewModel> poaViewModelFactory;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010H\u0007J\u000e\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSelection/PoaDocumentSelectionFragment$Companion;", "", "()V", "COUNTRY_CODE", "", "KEY_DOCUMENT_TYPE", "KEY_RESULT", "SUPPORTED_DOCS", "createInstance", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentSelection/PoaDocumentSelectionFragment;", "resultKey", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "supportedDocs", "Ljava/util/ArrayList;", "Lcom/onfido/api/client/data/PoaDocumentType;", "Lkotlin/collections/ArrayList;", "getDocumentType", "bundle", "Landroid/os/Bundle;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final PoaDocumentSelectionFragment createInstance(String resultKey, CountryCode countryCode, ArrayList<PoaDocumentType> supportedDocs) {
            Intrinsics.checkNotNullParameter(resultKey, "resultKey");
            Intrinsics.checkNotNullParameter(countryCode, "countryCode");
            Intrinsics.checkNotNullParameter(supportedDocs, "supportedDocs");
            PoaDocumentSelectionFragment poaDocumentSelectionFragment = new PoaDocumentSelectionFragment();
            poaDocumentSelectionFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(PoaDocumentSelectionFragment.KEY_RESULT, resultKey), TuplesKt.to("country_code", countryCode), TuplesKt.to(PoaDocumentSelectionFragment.SUPPORTED_DOCS, supportedDocs)));
            return poaDocumentSelectionFragment;
        }

        public final PoaDocumentType getDocumentType(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Serializable serializable = bundle.getSerializable("document_type");
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.onfido.api.client.data.PoaDocumentType");
            return (PoaDocumentType) serializable;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public PoaDocumentSelectionFragment() {
        super(R.layout.onfido_fragment_poa_document_selection);
        Function0<ViewModelProvider.Factory> function0 = new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection.PoaDocumentSelectionFragment$viewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                final PoaDocumentSelectionFragment poaDocumentSelectionFragment = this.this$0;
                return new ViewModelProvider.Factory() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection.PoaDocumentSelectionFragment$viewModel$2$invoke$$inlined$createViewModelFactory$1
                    @Override // androidx.lifecycle.ViewModelProvider.Factory
                    public <T extends ViewModel> T create(Class<T> modelClass) {
                        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                        PoaDocumentSelectionViewModel poaDocumentSelectionViewModel = poaDocumentSelectionFragment.getPoaViewModelFactory().get();
                        Intrinsics.checkNotNull(poaDocumentSelectionViewModel, "null cannot be cast to non-null type T of com.onfido.android.sdk.capture.component.utils.ViewModelExtKt.createViewModelFactory.<no name provided>.create");
                        return poaDocumentSelectionViewModel;
                    }
                };
            }
        };
        final Function0<Fragment> function02 = new Function0<Fragment>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection.PoaDocumentSelectionFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection.PoaDocumentSelectionFragment$special$$inlined$viewModels$default$2
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(PoaDocumentSelectionViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection.PoaDocumentSelectionFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection.PoaDocumentSelectionFragment$special$$inlined$viewModels$default$4
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

    private final void adjustUiDependingOnDocumentType(OnfidoFragmentPoaDocumentSelectionBinding binding, CountryCode countryCode, ArrayList<PoaDocumentType> supportedPoaDocumentTypes) {
        if (supportedPoaDocumentTypes.contains(PoaDocumentType.BANK_BUILDING_SOCIETY_STATEMENT)) {
            binding.poaBank.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection.PoaDocumentSelectionFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PoaDocumentSelectionFragment.adjustUiDependingOnDocumentType$lambda$5$lambda$0(this.f$0, view);
                }
            });
        } else {
            DocumentSelectionButton poaBank = binding.poaBank;
            Intrinsics.checkNotNullExpressionValue(poaBank, "poaBank");
            ViewExtensionsKt.toGone$default(poaBank, false, 1, null);
            View poaBankSeparator = binding.poaBankSeparator;
            Intrinsics.checkNotNullExpressionValue(poaBankSeparator, "poaBankSeparator");
            ViewExtensionsKt.toGone$default(poaBankSeparator, false, 1, null);
        }
        if (supportedPoaDocumentTypes.contains(PoaDocumentType.UTILITY_BILL)) {
            binding.poaUtilityBill.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection.PoaDocumentSelectionFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PoaDocumentSelectionFragment.adjustUiDependingOnDocumentType$lambda$5$lambda$1(this.f$0, view);
                }
            });
        } else {
            DocumentSelectionButton poaUtilityBill = binding.poaUtilityBill;
            Intrinsics.checkNotNullExpressionValue(poaUtilityBill, "poaUtilityBill");
            ViewExtensionsKt.toGone$default(poaUtilityBill, false, 1, null);
            View poaUtilityBillSeparator = binding.poaUtilityBillSeparator;
            Intrinsics.checkNotNullExpressionValue(poaUtilityBillSeparator, "poaUtilityBillSeparator");
            ViewExtensionsKt.toGone$default(poaUtilityBillSeparator, false, 1, null);
        }
        if (supportedPoaDocumentTypes.contains(PoaDocumentType.COUNCIL_TAX_LETTER)) {
            binding.poaCouncilTaxLetter.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection.PoaDocumentSelectionFragment$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PoaDocumentSelectionFragment.adjustUiDependingOnDocumentType$lambda$5$lambda$2(this.f$0, view);
                }
            });
        } else {
            DocumentSelectionButton poaCouncilTaxLetter = binding.poaCouncilTaxLetter;
            Intrinsics.checkNotNullExpressionValue(poaCouncilTaxLetter, "poaCouncilTaxLetter");
            ViewExtensionsKt.toGone$default(poaCouncilTaxLetter, false, 1, null);
            View poaCouncilTaxLetterSeparator = binding.poaCouncilTaxLetterSeparator;
            Intrinsics.checkNotNullExpressionValue(poaCouncilTaxLetterSeparator, "poaCouncilTaxLetterSeparator");
            ViewExtensionsKt.toGone$default(poaCouncilTaxLetterSeparator, false, 1, null);
        }
        if (supportedPoaDocumentTypes.contains(PoaDocumentType.BENEFITS_LETTER)) {
            binding.poaBenefitsLetter.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection.PoaDocumentSelectionFragment$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PoaDocumentSelectionFragment.adjustUiDependingOnDocumentType$lambda$5$lambda$3(this.f$0, view);
                }
            });
        } else {
            DocumentSelectionButton poaBenefitsLetter = binding.poaBenefitsLetter;
            Intrinsics.checkNotNullExpressionValue(poaBenefitsLetter, "poaBenefitsLetter");
            ViewExtensionsKt.toGone$default(poaBenefitsLetter, false, 1, null);
            View poaBenefitsLetterSeparator = binding.poaBenefitsLetterSeparator;
            Intrinsics.checkNotNullExpressionValue(poaBenefitsLetterSeparator, "poaBenefitsLetterSeparator");
            ViewExtensionsKt.toGone$default(poaBenefitsLetterSeparator, false, 1, null);
        }
        if (supportedPoaDocumentTypes.contains(PoaDocumentType.ADDRESS_CARD)) {
            binding.poaAddressCard.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentSelection.PoaDocumentSelectionFragment$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PoaDocumentSelectionFragment.adjustUiDependingOnDocumentType$lambda$5$lambda$4(this.f$0, view);
                }
            });
        } else {
            DocumentSelectionButton poaAddressCard = binding.poaAddressCard;
            Intrinsics.checkNotNullExpressionValue(poaAddressCard, "poaAddressCard");
            ViewExtensionsKt.toGone$default(poaAddressCard, false, 1, null);
            View poaAddressCardSeparator = binding.poaAddressCardSeparator;
            Intrinsics.checkNotNullExpressionValue(poaAddressCardSeparator, "poaAddressCardSeparator");
            ViewExtensionsKt.toGone$default(poaAddressCardSeparator, false, 1, null);
        }
        if (getViewModel().isUk(countryCode)) {
            return;
        }
        binding.poaDocumentTitle.setText(getString(R.string.onfido_poa_type_selection_title));
        binding.poaBank.getDocumentName$onfido_capture_sdk_core_release().setText(getString(R.string.onfido_doc_select_button_bank_statement_non_uk));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void adjustUiDependingOnDocumentType$lambda$5$lambda$0(PoaDocumentSelectionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.submitResult(PoaDocumentType.BANK_BUILDING_SOCIETY_STATEMENT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void adjustUiDependingOnDocumentType$lambda$5$lambda$1(PoaDocumentSelectionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.submitResult(PoaDocumentType.UTILITY_BILL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void adjustUiDependingOnDocumentType$lambda$5$lambda$2(PoaDocumentSelectionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.submitResult(PoaDocumentType.COUNCIL_TAX_LETTER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void adjustUiDependingOnDocumentType$lambda$5$lambda$3(PoaDocumentSelectionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.submitResult(PoaDocumentType.BENEFITS_LETTER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void adjustUiDependingOnDocumentType$lambda$5$lambda$4(PoaDocumentSelectionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.submitResult(PoaDocumentType.ADDRESS_CARD);
    }

    @JvmStatic
    public static final PoaDocumentSelectionFragment createInstance(String str, CountryCode countryCode, ArrayList<PoaDocumentType> arrayList) {
        return INSTANCE.createInstance(str, countryCode, arrayList);
    }

    private final PoaDocumentSelectionViewModel getViewModel() {
        return (PoaDocumentSelectionViewModel) this.viewModel.getValue();
    }

    private final void submitResult(PoaDocumentType documentType) {
        FragmentManager parentFragmentManager = getParentFragmentManager();
        String string = requireArguments().getString(KEY_RESULT);
        Intrinsics.checkNotNull(string);
        parentFragmentManager.setFragmentResult(string, BundleKt.bundleOf(TuplesKt.to("document_type", documentType)));
    }

    public final Provider<PoaDocumentSelectionViewModel> getPoaViewModelFactory() {
        Provider<PoaDocumentSelectionViewModel> provider = this.poaViewModelFactory;
        if (provider != null) {
            return provider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("poaViewModelFactory");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        getViewModel().trackPoaDocumentSelection();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        Fragment parentFragment = getParentFragment();
        Intrinsics.checkNotNull(parentFragment, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostFragment");
        ((PoaHostFragment) parentFragment).getPoaComponent$onfido_capture_sdk_core_release().inject(this);
        super.onViewCreated(view, savedInstanceState);
        OnfidoFragmentPoaDocumentSelectionBinding onfidoFragmentPoaDocumentSelectionBindingBind = OnfidoFragmentPoaDocumentSelectionBinding.bind(view);
        Intrinsics.checkNotNullExpressionValue(onfidoFragmentPoaDocumentSelectionBindingBind, "bind(...)");
        Serializable serializable = requireArguments().getSerializable("country_code");
        Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.onfido.android.sdk.capture.utils.CountryCode");
        Serializable serializable2 = requireArguments().getSerializable(SUPPORTED_DOCS);
        Intrinsics.checkNotNull(serializable2, "null cannot be cast to non-null type java.util.ArrayList<com.onfido.api.client.data.PoaDocumentType>{ kotlin.collections.TypeAliasesKt.ArrayList<com.onfido.api.client.data.PoaDocumentType> }");
        adjustUiDependingOnDocumentType(onfidoFragmentPoaDocumentSelectionBindingBind, (CountryCode) serializable, (ArrayList) serializable2);
    }

    public final void setPoaViewModelFactory(Provider<PoaDocumentSelectionViewModel> provider) {
        Intrinsics.checkNotNullParameter(provider, "<set-?>");
        this.poaViewModelFactory = provider;
    }
}
