package com.onfido.android.sdk.capture.ui.documentselection;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentRestrictedDocumentSelectionBinding;
import com.onfido.android.sdk.capture.document.DocumentPages;
import com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionState;
import com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment;
import com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostViewModel;
import com.onfido.android.sdk.capture.ui.widget.OnfidoButton;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.LifecycleDisposableKt;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Provider;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
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
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 02\u00020\u0001:\u00010B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001cH\u0002J$\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0002J\u001a\u0010%\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010*\u001a\u00020\u001cH\u0002J\b\u0010+\u001a\u00020\u001cH\u0002J\b\u0010,\u001a\u00020\u001cH\u0002J\u0010\u0010-\u001a\u00020\u001c2\u0006\u0010.\u001a\u00020/H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0012\u0010\u0013R$\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00168\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u00061"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentRestrictedDocumentSelectionBinding;", "binding", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentRestrictedDocumentSelectionBinding;", "documentsAdapter", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentAdapter;", "viewModel", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionViewModel;", "getViewModel", "()Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "viewModelHost", "Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostViewModel;", "getViewModelHost", "()Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostViewModel;", "viewModelHost$delegate", "viewModelProvider", "Lcom/onfido/javax/inject/Provider;", "getViewModelProvider", "()Lcom/onfido/javax/inject/Provider;", "setViewModelProvider", "(Lcom/onfido/javax/inject/Provider;)V", "hideDocumentViews", "", "observeState", "onDocumentSelected", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "genericDocumentTitle", "", "genericDocPages", "Lcom/onfido/android/sdk/capture/document/DocumentPages;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "pickCountry", "setupViews", "showDocumentViews", "updateView", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentTypeSelectionFragment extends Fragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_NFC_WARNING_VISIBLE = "KEY_NFC_WARNING_VISIBLE";
    private OnfidoFragmentRestrictedDocumentSelectionBinding _binding;
    private final DocumentAdapter documentsAdapter;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* renamed from: viewModelHost$delegate, reason: from kotlin metadata */
    private final Lazy viewModelHost;

    @Inject
    public Provider<DocumentTypeSelectionViewModel> viewModelProvider;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionFragment$Companion;", "", "()V", DocumentTypeSelectionFragment.KEY_NFC_WARNING_VISIBLE, "", "createInstance", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentTypeSelectionFragment;", "nfcRequiredWarningVisible", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final DocumentTypeSelectionFragment createInstance(boolean nfcRequiredWarningVisible) {
            DocumentTypeSelectionFragment documentTypeSelectionFragment = new DocumentTypeSelectionFragment();
            documentTypeSelectionFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(DocumentTypeSelectionFragment.KEY_NFC_WARNING_VISIBLE, Boolean.valueOf(nfcRequiredWarningVisible))));
            return documentTypeSelectionFragment;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DocumentTypeSelectionFragment() {
        super(R.layout.onfido_fragment_restricted_document_selection);
        Function0<ViewModelProvider.Factory> function0 = new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionFragment$viewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                final DocumentTypeSelectionFragment documentTypeSelectionFragment = this.this$0;
                return new ViewModelProvider.Factory() { // from class: com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionFragment$viewModel$2$invoke$$inlined$createViewModelFactory$1
                    @Override // androidx.lifecycle.ViewModelProvider.Factory
                    public <T extends ViewModel> T create(Class<T> modelClass) {
                        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                        DocumentTypeSelectionViewModel documentTypeSelectionViewModel = documentTypeSelectionFragment.getViewModelProvider().get();
                        Intrinsics.checkNotNull(documentTypeSelectionViewModel, "null cannot be cast to non-null type T of com.onfido.android.sdk.capture.component.utils.ViewModelExtKt.createViewModelFactory.<no name provided>.create");
                        return documentTypeSelectionViewModel;
                    }
                };
            }
        };
        final Function0<Fragment> function02 = new Function0<Fragment>() { // from class: com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        final Lazy lazy = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionFragment$special$$inlined$viewModels$default$2
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(DocumentTypeSelectionViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionFragment$special$$inlined$viewModels$default$4
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
        final Function0<ViewModelStoreOwner> function04 = new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionFragment$viewModelHost$2
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
        final Lazy lazy2 = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionFragment$special$$inlined$viewModels$default$6
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) function04.invoke();
            }
        });
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(DocumentSelectionHostViewModel.class);
        Function0<ViewModelStore> function05 = new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionFragment$special$$inlined$viewModels$default$7
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = FragmentViewModelLazyKt.m4679viewModels$lambda1(lazy2).getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "owner.viewModelStore");
                return viewModelStore;
            }
        };
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModelHost = FragmentViewModelLazyKt.createViewModelLazy(this, orCreateKotlinClass, function05, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionFragment$special$$inlined$viewModels$default$8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function06 = objArr;
                if (function06 != null && (creationExtras = (CreationExtras) function06.invoke()) != null) {
                    return creationExtras;
                }
                ViewModelStoreOwner viewModelStoreOwnerM4679viewModels$lambda1 = FragmentViewModelLazyKt.m4679viewModels$lambda1(lazy2);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM4679viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM4679viewModels$lambda1 : null;
                CreationExtras defaultViewModelCreationExtras = hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : null;
                return defaultViewModelCreationExtras == null ? CreationExtras.Empty.INSTANCE : defaultViewModelCreationExtras;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionFragment$special$$inlined$viewModels$default$9
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory;
                ViewModelStoreOwner viewModelStoreOwnerM4679viewModels$lambda1 = FragmentViewModelLazyKt.m4679viewModels$lambda1(lazy2);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM4679viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM4679viewModels$lambda1 : null;
                if (hasDefaultViewModelProviderFactory == null || (defaultViewModelProviderFactory = hasDefaultViewModelProviderFactory.getDefaultViewModelProviderFactory()) == null) {
                    defaultViewModelProviderFactory = this.getDefaultViewModelProviderFactory();
                }
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "(owner as? HasDefaultVie…tViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
        this.documentsAdapter = new DocumentAdapter(new DocumentTypeSelectionFragment$documentsAdapter$1(this), 0 == true ? 1 : 0, 2, 0 == true ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OnfidoFragmentRestrictedDocumentSelectionBinding getBinding() {
        OnfidoFragmentRestrictedDocumentSelectionBinding onfidoFragmentRestrictedDocumentSelectionBinding = this._binding;
        Intrinsics.checkNotNull(onfidoFragmentRestrictedDocumentSelectionBinding);
        return onfidoFragmentRestrictedDocumentSelectionBinding;
    }

    private final DocumentTypeSelectionViewModel getViewModel() {
        return (DocumentTypeSelectionViewModel) this.viewModel.getValue();
    }

    private final DocumentSelectionHostViewModel getViewModelHost() {
        return (DocumentSelectionHostViewModel) this.viewModelHost.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideDocumentViews() {
        OnfidoFragmentRestrictedDocumentSelectionBinding binding = getBinding();
        binding.headerDocumentType.setVisibility(8);
        binding.documentList.setVisibility(8);
    }

    private final void observeState() {
        Disposable disposableSubscribe = getViewModelHost().getState$onfido_capture_sdk_core_release().subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionFragment.observeState.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(DocumentTypeSelectionState documentTypeSelectionState) {
                DocumentTypeSelectionFragment documentTypeSelectionFragment;
                CountryCode countryCode;
                if (documentTypeSelectionState instanceof DocumentTypeSelectionState.DocumentTypeSelected) {
                    documentTypeSelectionFragment = DocumentTypeSelectionFragment.this;
                    countryCode = ((DocumentTypeSelectionState.DocumentTypeSelected) documentTypeSelectionState).getCountryCode();
                } else {
                    if (!(documentTypeSelectionState instanceof DocumentTypeSelectionState.CountrySelected)) {
                        if (Intrinsics.areEqual(documentTypeSelectionState, DocumentTypeSelectionState.NoCountrySelected.INSTANCE)) {
                            DocumentTypeSelectionFragment.this.getBinding().countryPicker.countryName.setText(DocumentTypeSelectionFragment.this.getString(R.string.onfido_doc_select_section_input_placeholder_country));
                            DocumentTypeSelectionFragment.this.documentsAdapter.submitList(CollectionsKt.emptyList());
                            DocumentTypeSelectionFragment.this.hideDocumentViews();
                            OnfidoButton btRetry = DocumentTypeSelectionFragment.this.getBinding().btRetry;
                            Intrinsics.checkNotNullExpressionValue(btRetry, "btRetry");
                            btRetry.setVisibility(8);
                            return;
                        }
                        if (Intrinsics.areEqual(documentTypeSelectionState, DocumentTypeSelectionState.LoadingDocumentsFailed.INSTANCE)) {
                            TextView tvSelectedCountryTitle = DocumentTypeSelectionFragment.this.getBinding().tvSelectedCountryTitle;
                            Intrinsics.checkNotNullExpressionValue(tvSelectedCountryTitle, "tvSelectedCountryTitle");
                            tvSelectedCountryTitle.setVisibility(8);
                            ConstraintLayout root = DocumentTypeSelectionFragment.this.getBinding().countryPicker.getRoot();
                            Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
                            root.setVisibility(8);
                            OnfidoButton btRetry2 = DocumentTypeSelectionFragment.this.getBinding().btRetry;
                            Intrinsics.checkNotNullExpressionValue(btRetry2, "btRetry");
                            btRetry2.setVisibility(0);
                            return;
                        }
                        return;
                    }
                    documentTypeSelectionFragment = DocumentTypeSelectionFragment.this;
                    countryCode = ((DocumentTypeSelectionState.CountrySelected) documentTypeSelectionState).getCountryCode();
                }
                documentTypeSelectionFragment.updateView(countryCode);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        LifecycleDisposableKt.disposeOnStop(disposableSubscribe, viewLifecycleOwner);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onDocumentSelected(DocumentType documentType, String genericDocumentTitle, DocumentPages genericDocPages) {
        getViewModelHost().onDocumentSelected(documentType, genericDocumentTitle, genericDocPages);
    }

    private final void pickCountry() {
        getViewModelHost().onCountrySelectionClicked();
    }

    private final void setupViews() {
        OnfidoFragmentRestrictedDocumentSelectionBinding binding = getBinding();
        hideDocumentViews();
        RecyclerView recyclerView = binding.documentList;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), 1, false));
        recyclerView.setAdapter(this.documentsAdapter);
        binding.countryPicker.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DocumentTypeSelectionFragment.setupViews$lambda$3$lambda$1(this.f$0, view);
            }
        });
        binding.btRetry.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DocumentTypeSelectionFragment.setupViews$lambda$3$lambda$2(this.f$0, view);
            }
        });
        TextView tvNfcRequiredWarning = binding.tvNfcRequiredWarning;
        Intrinsics.checkNotNullExpressionValue(tvNfcRequiredWarning, "tvNfcRequiredWarning");
        tvNfcRequiredWarning.setVisibility(requireArguments().getBoolean(KEY_NFC_WARNING_VISIBLE) ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupViews$lambda$3$lambda$1(DocumentTypeSelectionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.pickCountry();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupViews$lambda$3$lambda$2(DocumentTypeSelectionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView tvSelectedCountryTitle = this$0.getBinding().tvSelectedCountryTitle;
        Intrinsics.checkNotNullExpressionValue(tvSelectedCountryTitle, "tvSelectedCountryTitle");
        tvSelectedCountryTitle.setVisibility(0);
        ConstraintLayout root = this$0.getBinding().countryPicker.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        root.setVisibility(0);
        OnfidoButton btRetry = this$0.getBinding().btRetry;
        Intrinsics.checkNotNullExpressionValue(btRetry, "btRetry");
        btRetry.setVisibility(8);
        this$0.getViewModelHost().retryFetchingDocuments(true);
    }

    private final void showDocumentViews() {
        OnfidoFragmentRestrictedDocumentSelectionBinding binding = getBinding();
        binding.headerDocumentType.setVisibility(0);
        binding.documentList.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateView(CountryCode countryCode) {
        OnfidoButton btRetry = getBinding().btRetry;
        Intrinsics.checkNotNullExpressionValue(btRetry, "btRetry");
        btRetry.setVisibility(8);
        DisplayCountry displayCountryFrom$onfido_capture_sdk_core_release = DisplayCountry.INSTANCE.from$onfido_capture_sdk_core_release(countryCode);
        getBinding().countryPicker.countryName.setText(displayCountryFrom$onfido_capture_sdk_core_release.getDisplayName());
        getBinding().countryPicker.countryName.setContentDescription(getString(R.string.onfido_doc_select_section_header_country) + ' ' + displayCountryFrom$onfido_capture_sdk_core_release.getDisplayName());
        this.documentsAdapter.submitList(getViewModel().getFullListOfDocumentsToDisplay(displayCountryFrom$onfido_capture_sdk_core_release.getCountryCode()));
        showDocumentViews();
    }

    public final Provider<DocumentTypeSelectionViewModel> getViewModelProvider() {
        Provider<DocumentTypeSelectionViewModel> provider = this.viewModelProvider;
        if (provider != null) {
            return provider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelProvider");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        Fragment parentFragment = getParentFragment();
        Intrinsics.checkNotNull(parentFragment, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment");
        ((DocumentSelectionHostFragment) parentFragment).getRdsComponent$onfido_capture_sdk_core_release().inject(this);
        super.onViewCreated(view, savedInstanceState);
        this._binding = OnfidoFragmentRestrictedDocumentSelectionBinding.bind(view);
        setupViews();
        observeState();
    }

    public final void setViewModelProvider(Provider<DocumentTypeSelectionViewModel> provider) {
        Intrinsics.checkNotNullParameter(provider, "<set-?>");
        this.viewModelProvider = provider;
    }
}
