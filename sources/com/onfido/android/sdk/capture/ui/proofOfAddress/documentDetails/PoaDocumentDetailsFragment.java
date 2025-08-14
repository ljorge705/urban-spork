package com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentPoaDocumentDetailsBinding;
import com.onfido.android.sdk.capture.ui.BaseFragment;
import com.onfido.android.sdk.capture.ui.proofOfAddress.PoaUtils;
import com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails.PoaDocumentDetailsViewModel;
import com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostFragment;
import com.onfido.android.sdk.capture.ui.userconsent.htmlutil.TextViewExtensionKt;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.api.client.data.PoaDocumentType;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Provider;
import java.io.Serializable;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u001a\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0010\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012¨\u0006 "}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentDetails/PoaDocumentDetailsFragment;", "Lcom/onfido/android/sdk/capture/ui/BaseFragment;", "()V", "poaUtils", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/PoaUtils;", "getPoaUtils$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/proofOfAddress/PoaUtils;", "setPoaUtils$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/proofOfAddress/PoaUtils;)V", "poaViewModelFactory", "Lcom/onfido/javax/inject/Provider;", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentDetails/PoaDocumentDetailsViewModel;", "getPoaViewModelFactory", "()Lcom/onfido/javax/inject/Provider;", "setPoaViewModelFactory", "(Lcom/onfido/javax/inject/Provider;)V", "viewModel", "getViewModel", "()Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentDetails/PoaDocumentDetailsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onStart", "", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "submitActionButtonPressed", "isTakePhoto", "", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PoaDocumentDetailsFragment extends BaseFragment {
    private static final String COUNTRY_CODE = "poa_country_code";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String IS_TAKE_PHOTO = "is_take_photo";
    private static final String KEY_RESULT = "key_result";
    private static final String POA_DOCUMENT_TYPE = "document_type";

    @Inject
    public PoaUtils poaUtils;

    @Inject
    public Provider<PoaDocumentDetailsViewModel> poaViewModelFactory;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentDetails/PoaDocumentDetailsFragment$Companion;", "", "()V", "COUNTRY_CODE", "", "IS_TAKE_PHOTO", "KEY_RESULT", "POA_DOCUMENT_TYPE", "createInstance", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/documentDetails/PoaDocumentDetailsFragment;", "resultKey", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "documentType", "Lcom/onfido/api/client/data/PoaDocumentType;", "isTakePhotoButton", "", "bundle", "Landroid/os/Bundle;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final PoaDocumentDetailsFragment createInstance(String resultKey, CountryCode countryCode, PoaDocumentType documentType) {
            Intrinsics.checkNotNullParameter(resultKey, "resultKey");
            Intrinsics.checkNotNullParameter(countryCode, "countryCode");
            Intrinsics.checkNotNullParameter(documentType, "documentType");
            PoaDocumentDetailsFragment poaDocumentDetailsFragment = new PoaDocumentDetailsFragment();
            poaDocumentDetailsFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(PoaDocumentDetailsFragment.KEY_RESULT, resultKey), TuplesKt.to(PoaDocumentDetailsFragment.COUNTRY_CODE, countryCode), TuplesKt.to("document_type", documentType)));
            return poaDocumentDetailsFragment;
        }

        public final boolean isTakePhotoButton(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            return bundle.getBoolean(PoaDocumentDetailsFragment.IS_TAKE_PHOTO);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public PoaDocumentDetailsFragment() {
        super(R.layout.onfido_fragment_poa_document_details);
        Function0<ViewModelProvider.Factory> function0 = new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails.PoaDocumentDetailsFragment$viewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                final PoaDocumentDetailsFragment poaDocumentDetailsFragment = this.this$0;
                return new ViewModelProvider.Factory() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails.PoaDocumentDetailsFragment$viewModel$2$invoke$$inlined$createViewModelFactory$1
                    @Override // androidx.lifecycle.ViewModelProvider.Factory
                    public <T extends ViewModel> T create(Class<T> modelClass) {
                        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                        PoaDocumentDetailsViewModel poaDocumentDetailsViewModel = poaDocumentDetailsFragment.getPoaViewModelFactory().get();
                        Intrinsics.checkNotNull(poaDocumentDetailsViewModel, "null cannot be cast to non-null type T of com.onfido.android.sdk.capture.component.utils.ViewModelExtKt.createViewModelFactory.<no name provided>.create");
                        return poaDocumentDetailsViewModel;
                    }
                };
            }
        };
        final Function0<Fragment> function02 = new Function0<Fragment>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails.PoaDocumentDetailsFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails.PoaDocumentDetailsFragment$special$$inlined$viewModels$default$2
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(PoaDocumentDetailsViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails.PoaDocumentDetailsFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails.PoaDocumentDetailsFragment$special$$inlined$viewModels$default$4
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
    public static final PoaDocumentDetailsFragment createInstance(String str, CountryCode countryCode, PoaDocumentType poaDocumentType) {
        return INSTANCE.createInstance(str, countryCode, poaDocumentType);
    }

    private final PoaDocumentDetailsViewModel getViewModel() {
        return (PoaDocumentDetailsViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(PoaDocumentDetailsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.submitActionButtonPressed(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$2(PoaDocumentDetailsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.submitActionButtonPressed(true);
    }

    private final void submitActionButtonPressed(boolean isTakePhoto) {
        FragmentManager parentFragmentManager = getParentFragmentManager();
        String string = requireArguments().getString(KEY_RESULT);
        Intrinsics.checkNotNull(string);
        parentFragmentManager.setFragmentResult(string, BundleKt.bundleOf(TuplesKt.to(IS_TAKE_PHOTO, Boolean.valueOf(isTakePhoto))));
    }

    public final PoaUtils getPoaUtils$onfido_capture_sdk_core_release() {
        PoaUtils poaUtils = this.poaUtils;
        if (poaUtils != null) {
            return poaUtils;
        }
        Intrinsics.throwUninitializedPropertyAccessException("poaUtils");
        return null;
    }

    public final Provider<PoaDocumentDetailsViewModel> getPoaViewModelFactory() {
        Provider<PoaDocumentDetailsViewModel> provider = this.poaViewModelFactory;
        if (provider != null) {
            return provider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("poaViewModelFactory");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        getViewModel().trackPoaDocumentDetails();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        Fragment parentFragment = getParentFragment();
        Intrinsics.checkNotNull(parentFragment, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostFragment");
        ((PoaHostFragment) parentFragment).getPoaComponent$onfido_capture_sdk_core_release().inject(this);
        super.onViewCreated(view, savedInstanceState);
        final OnfidoFragmentPoaDocumentDetailsBinding onfidoFragmentPoaDocumentDetailsBindingBind = OnfidoFragmentPoaDocumentDetailsBinding.bind(view);
        Intrinsics.checkNotNullExpressionValue(onfidoFragmentPoaDocumentDetailsBindingBind, "bind(...)");
        Serializable serializable = requireArguments().getSerializable(COUNTRY_CODE);
        Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.onfido.android.sdk.capture.utils.CountryCode");
        Serializable serializable2 = requireArguments().getSerializable("document_type");
        Intrinsics.checkNotNull(serializable2, "null cannot be cast to non-null type com.onfido.api.client.data.PoaDocumentType");
        getViewModel().init((CountryCode) serializable, (PoaDocumentType) serializable2);
        LiveData<PoaDocumentDetailsViewModel.PoaDocumentDetailsViewState> viewState = getViewModel().getViewState();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final Function1<PoaDocumentDetailsViewModel.PoaDocumentDetailsViewState, Unit> function1 = new Function1<PoaDocumentDetailsViewModel.PoaDocumentDetailsViewState, Unit>() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails.PoaDocumentDetailsFragment.onViewCreated.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PoaDocumentDetailsViewModel.PoaDocumentDetailsViewState poaDocumentDetailsViewState) {
                invoke2(poaDocumentDetailsViewState);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PoaDocumentDetailsViewModel.PoaDocumentDetailsViewState poaDocumentDetailsViewState) {
                OnfidoFragmentPoaDocumentDetailsBinding onfidoFragmentPoaDocumentDetailsBinding = onfidoFragmentPoaDocumentDetailsBindingBind;
                PoaDocumentDetailsFragment poaDocumentDetailsFragment = this;
                onfidoFragmentPoaDocumentDetailsBinding.poaDocumentDetailsTitle.setText(poaDocumentDetailsFragment.getString(poaDocumentDetailsViewState.getTitleResId()));
                TextView poaDocumentDetailsSubTitle = onfidoFragmentPoaDocumentDetailsBinding.poaDocumentDetailsSubTitle;
                Intrinsics.checkNotNullExpressionValue(poaDocumentDetailsSubTitle, "poaDocumentDetailsSubTitle");
                String string = poaDocumentDetailsFragment.getString(poaDocumentDetailsViewState.getSubtitleResId());
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                TextViewExtensionKt.setTextFromHtml(poaDocumentDetailsSubTitle, string);
                onfidoFragmentPoaDocumentDetailsBinding.poaCaptureBullet1.setText(poaDocumentDetailsFragment.getString(poaDocumentDetailsViewState.getInstructions().getBullet1()));
                onfidoFragmentPoaDocumentDetailsBinding.poaCaptureBullet2.setText(poaDocumentDetailsFragment.getString(poaDocumentDetailsViewState.getInstructions().getBullet2()));
                onfidoFragmentPoaDocumentDetailsBinding.poaCaptureBullet3.setText(poaDocumentDetailsFragment.getString(poaDocumentDetailsViewState.getInstructions().getBullet3()));
                onfidoFragmentPoaDocumentDetailsBinding.poaCaptureBullet4.setText(poaDocumentDetailsFragment.getString(poaDocumentDetailsViewState.getInstructions().getBullet4()));
            }
        };
        viewState.observe(viewLifecycleOwner, new Observer() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails.PoaDocumentDetailsFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                PoaDocumentDetailsFragment.onViewCreated$lambda$0(function1, obj);
            }
        });
        onfidoFragmentPoaDocumentDetailsBindingBind.uploadButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails.PoaDocumentDetailsFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PoaDocumentDetailsFragment.onViewCreated$lambda$1(this.f$0, view2);
            }
        });
        onfidoFragmentPoaDocumentDetailsBindingBind.takePhotoButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.proofOfAddress.documentDetails.PoaDocumentDetailsFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PoaDocumentDetailsFragment.onViewCreated$lambda$2(this.f$0, view2);
            }
        });
    }

    public final void setPoaUtils$onfido_capture_sdk_core_release(PoaUtils poaUtils) {
        Intrinsics.checkNotNullParameter(poaUtils, "<set-?>");
        this.poaUtils = poaUtils;
    }

    public final void setPoaViewModelFactory(Provider<PoaDocumentDetailsViewModel> provider) {
        Intrinsics.checkNotNullParameter(provider, "<set-?>");
        this.poaViewModelFactory = provider;
    }
}
