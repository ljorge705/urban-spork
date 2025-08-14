package com.onfido.android.sdk.capture.ui.nfc.scan;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentNfcCanEntryBinding;
import com.onfido.android.sdk.capture.ui.nfc.NfcHostViewModel;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryViewModel;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.dagger.assisted.AssistedFactory;
import com.onfido.javax.inject.Inject;
import java.io.Serializable;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u0000 52\u00020\u0001:\u000256B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020!H\u0002J\b\u0010#\u001a\u00020!H\u0002J\u0010\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020!H\u0002J\b\u0010(\u001a\u00020!H\u0016J\u001a\u0010)\u001a\u00020!2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\b\u0010.\u001a\u00020!H\u0002J\u0010\u0010/\u001a\u00020!2\u0006\u00100\u001a\u00020\tH\u0002J\u0010\u00101\u001a\u00020!2\u0006\u00102\u001a\u000203H\u0002J\b\u00104\u001a\u00020!H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0083\u000e¢\u0006\u0004\n\u0002\u0010\nR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\t8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u0010\u001a\u0004\b\u001d\u0010\u001e¨\u00067"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcCanEntryFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentNfcCanEntryBinding;", "binding", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentNfcCanEntryBinding;", "errorColor", "", "Ljava/lang/Integer;", "nfcHostViewModel", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel;", "getNfcHostViewModel", "()Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel;", "nfcHostViewModel$delegate", "Lkotlin/Lazy;", "nfcViewModelFactory", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcCanEntryViewModel$Factory;", "getNfcViewModelFactory", "()Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcCanEntryViewModel$Factory;", "setNfcViewModelFactory", "(Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcCanEntryViewModel$Factory;)V", "onFocusTimestamp", "", "Ljava/lang/Long;", "validInputColor", "viewModel", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcCanEntryViewModel;", "getViewModel", "()Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcCanEntryViewModel;", "viewModel$delegate", "addEditTextChangedListener", "", "addOnFocusChangedListener", "bindNoCanButton", "fillCanNumber", "value", "", "onContinueButtonClicked", "onPause", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "prefillCanOrShowHint", "setEditTextMaxLength", "maxLength", "showErrorFeedback", "shouldShow", "", "showHint", "Companion", "Factory", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcCanEntryFragment extends Fragment {
    public static final String CAN_LENGTH = "can_number_length";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String INPUT_CAN_NUMBER_KEY = "input_can_number";
    public static final String KNOWN_CAN_NUMBER_KEY = "known_can_number_for_document";
    private static final String RETRY_STATE_KEY = "retry_state";
    private static final String WITH_ERROR_KEY = "with_error";
    private OnfidoFragmentNfcCanEntryBinding _binding;
    private Integer errorColor;

    /* renamed from: nfcHostViewModel$delegate, reason: from kotlin metadata */
    private final Lazy nfcHostViewModel;

    @Inject
    public NfcCanEntryViewModel.Factory nfcViewModelFactory;
    private Long onFocusTimestamp;
    private int validInputColor;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0004\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00042\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcCanEntryFragment$Companion;", "", "()V", "CAN_LENGTH", "", "INPUT_CAN_NUMBER_KEY", "KNOWN_CAN_NUMBER_KEY", "RETRY_STATE_KEY", "WITH_ERROR_KEY", "createInstance", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcCanEntryFragment;", "knownCanNumber", "inputCanNumber", "", "canLength", "", "isRetry", "", "withError", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final NfcCanEntryFragment createInstance(String knownCanNumber, Number inputCanNumber, int canLength, boolean isRetry, boolean withError) {
            NfcCanEntryFragment nfcCanEntryFragment = new NfcCanEntryFragment();
            nfcCanEntryFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(NfcCanEntryFragment.INPUT_CAN_NUMBER_KEY, inputCanNumber), TuplesKt.to(NfcCanEntryFragment.KNOWN_CAN_NUMBER_KEY, knownCanNumber), TuplesKt.to(NfcCanEntryFragment.CAN_LENGTH, Integer.valueOf(canLength)), TuplesKt.to(NfcCanEntryFragment.RETRY_STATE_KEY, Boolean.valueOf(isRetry)), TuplesKt.to(NfcCanEntryFragment.WITH_ERROR_KEY, Boolean.valueOf(withError))));
            return nfcCanEntryFragment;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @AssistedFactory
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcCanEntryFragment$Factory;", "", "create", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcCanEntryViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        NfcCanEntryViewModel create(SavedStateHandle savedStateHandle);
    }

    public NfcCanEntryFragment() {
        super(R.layout.onfido_fragment_nfc_can_entry);
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment$nfcHostViewModel$2
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
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        final Lazy lazy = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment$special$$inlined$viewModels$default$1
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
        this.nfcHostViewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(NfcHostViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment$special$$inlined$viewModels$default$2
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
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment$special$$inlined$viewModels$default$4
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
        Function0<ViewModelProvider.Factory> function03 = new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment$viewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                final NfcCanEntryFragment nfcCanEntryFragment = this.this$0;
                final Bundle bundleRequireArguments = nfcCanEntryFragment.requireArguments();
                final NfcCanEntryFragment nfcCanEntryFragment2 = this.this$0;
                return new AbstractSavedStateViewModelFactory(nfcCanEntryFragment, bundleRequireArguments) { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment$viewModel$2$invoke$$inlined$createAbstractSavedStateFactory$1
                    @Override // androidx.lifecycle.AbstractSavedStateViewModelFactory
                    protected <T extends ViewModel> T create(String key, Class<T> modelClass, SavedStateHandle handle) {
                        Intrinsics.checkNotNullParameter(key, "key");
                        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                        Intrinsics.checkNotNullParameter(handle, "handle");
                        NfcCanEntryViewModel nfcCanEntryViewModelCreate = nfcCanEntryFragment2.getNfcViewModelFactory().create(handle);
                        Intrinsics.checkNotNull(nfcCanEntryViewModelCreate, "null cannot be cast to non-null type T of com.onfido.android.sdk.capture.component.utils.ViewModelExtKt.createAbstractSavedStateFactory.<no name provided>.create");
                        return nfcCanEntryViewModelCreate;
                    }
                };
            }
        };
        final Function0<Fragment> function04 = new Function0<Fragment>() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment$special$$inlined$viewModels$default$5
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        final Lazy lazy2 = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment$special$$inlined$viewModels$default$6
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) function04.invoke();
            }
        });
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(NfcCanEntryViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment$special$$inlined$viewModels$default$7
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
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment$special$$inlined$viewModels$default$8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function05 = function02;
                if (function05 != null && (creationExtras = (CreationExtras) function05.invoke()) != null) {
                    return creationExtras;
                }
                ViewModelStoreOwner viewModelStoreOwnerM4679viewModels$lambda1 = FragmentViewModelLazyKt.m4679viewModels$lambda1(lazy2);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM4679viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM4679viewModels$lambda1 : null;
                CreationExtras defaultViewModelCreationExtras = hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : null;
                return defaultViewModelCreationExtras == null ? CreationExtras.Empty.INSTANCE : defaultViewModelCreationExtras;
            }
        }, function03);
    }

    private final void addEditTextChangedListener() {
        final int canLength = getViewModel().getCanLength();
        EditText editText = getBinding().canNumberField.getEditText();
        if (editText != null) {
            editText.addTextChangedListener(new TextWatcher() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment.addEditTextChangedListener.1
                private boolean ignoreChange;

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable s) {
                    CharSequence charSequenceTrim;
                    if (this.ignoreChange) {
                        return;
                    }
                    NfcCanEntryFragment.this.setEditTextMaxLength(canLength);
                    this.ignoreChange = true;
                    NfcCanEntryFragment.this.fillCanNumber(String.valueOf((s == null || (charSequenceTrim = StringsKt.trim(s, '-')) == null) ? null : StringsKt.padEnd(charSequenceTrim, canLength, '-')));
                    this.ignoreChange = false;
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    NfcCanEntryFragment.this.showErrorFeedback(false);
                }

                public final boolean getIgnoreChange() {
                    return this.ignoreChange;
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    EditText editText2;
                    int length;
                    NfcCanEntryFragment.this.setEditTextMaxLength(canLength + 1);
                    if (s != null && StringsKt.contains$default(s, '-', false, 2, (Object) null)) {
                        editText2 = NfcCanEntryFragment.this.getBinding().canNumberField.getEditText();
                        if (editText2 != null) {
                            length = StringsKt.indexOf$default(s, '-', 0, false, 6, (Object) null);
                            editText2.setSelection(length);
                        }
                    } else if (s != null && s.length() == canLength && (editText2 = NfcCanEntryFragment.this.getBinding().canNumberField.getEditText()) != null) {
                        length = s.length();
                        editText2.setSelection(length);
                    }
                    NfcCanEntryFragment.this.getBinding().continueButton.getAppCompatButton().setEnabled((s == null || s.length() != canLength || StringsKt.contains$default(s, '-', false, 2, (Object) null)) ? false : true);
                }

                public final void setIgnoreChange(boolean z) {
                    this.ignoreChange = z;
                }
            });
        }
    }

    private final void addOnFocusChangedListener() {
        getBinding().canTextInput.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                NfcCanEntryFragment.addOnFocusChangedListener$lambda$4(this.f$0, view, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addOnFocusChangedListener$lambda$4(NfcCanEntryFragment this$0, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z && this$0.onFocusTimestamp == null) {
            this$0.onFocusTimestamp = Long.valueOf(System.currentTimeMillis());
        }
    }

    private final void bindNoCanButton() throws Resources.NotFoundException {
        String string = getResources().getString(R.string.onfido_enter_can_secondary_button);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new UnderlineSpan(), 0, string.length(), 0);
        getBinding().noCanButton.setText(new SpannableString(spannableString));
        getBinding().noCanButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NfcCanEntryFragment.bindNoCanButton$lambda$3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindNoCanButton$lambda$3(NfcCanEntryFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getNfcHostViewModel().onNoCanClicked();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fillCanNumber(String value) {
        EditText editText = getBinding().canNumberField.getEditText();
        if (editText != null) {
            editText.setText(value, TextView.BufferType.EDITABLE);
        }
        EditText editText2 = getBinding().canNumberField.getEditText();
        if (editText2 != null) {
            editText2.requestFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OnfidoFragmentNfcCanEntryBinding getBinding() {
        OnfidoFragmentNfcCanEntryBinding onfidoFragmentNfcCanEntryBinding = this._binding;
        Intrinsics.checkNotNull(onfidoFragmentNfcCanEntryBinding);
        return onfidoFragmentNfcCanEntryBinding;
    }

    private final NfcHostViewModel getNfcHostViewModel() {
        return (NfcHostViewModel) this.nfcHostViewModel.getValue();
    }

    private final NfcCanEntryViewModel getViewModel() {
        return (NfcCanEntryViewModel) this.viewModel.getValue();
    }

    private final void onContinueButtonClicked() {
        NfcCanEntryViewModel viewModel = getViewModel();
        EditText editText = getBinding().canNumberField.getEditText();
        Long lIsCanInputValid = viewModel.isCanInputValid(String.valueOf(editText != null ? editText.getText() : null));
        if (lIsCanInputValid == null) {
            showErrorFeedback(true);
            return;
        }
        showErrorFeedback(false);
        getViewModel().onContinueButtonClicked(getNfcHostViewModel().nfcScanAttempt(), this.onFocusTimestamp);
        getNfcHostViewModel().onCanNumberEntered(lIsCanInputValid);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$1$lambda$0(NfcCanEntryFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onContinueButtonClicked();
    }

    private final void prefillCanOrShowHint() {
        Bundle arguments = getArguments();
        String string = arguments != null ? arguments.getString(KNOWN_CAN_NUMBER_KEY) : null;
        if (string == null) {
            string = "";
        }
        Bundle arguments2 = getArguments();
        Serializable serializable = arguments2 != null ? arguments2.getSerializable(INPUT_CAN_NUMBER_KEY) : null;
        Number number = serializable instanceof Number ? (Number) serializable : null;
        Bundle arguments3 = getArguments();
        boolean z = arguments3 != null ? arguments3.getBoolean(RETRY_STATE_KEY) : false;
        Bundle arguments4 = getArguments();
        boolean z2 = arguments4 != null ? arguments4.getBoolean(WITH_ERROR_KEY) : false;
        if (number != null) {
            fillCanNumber(number.toString());
            showErrorFeedback(true);
            return;
        }
        if (z2) {
            showErrorFeedback(false);
        } else {
            showErrorFeedback(z);
            if (string.length() > 0 && getViewModel().isCanInputValid(string) != null) {
                fillCanNumber(string);
                return;
            }
        }
        showHint();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setEditTextMaxLength(int maxLength) {
        EditText editText = getBinding().canNumberField.getEditText();
        if (editText == null) {
            return;
        }
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showErrorFeedback(boolean shouldShow) {
        OnfidoFragmentNfcCanEntryBinding binding = getBinding();
        binding.canNumberField.setErrorEnabled(shouldShow);
        if (!shouldShow) {
            binding.canTextInput.setTextColor(this.validInputColor);
            return;
        }
        int retriesLeft = getNfcHostViewModel().getRetriesLeft();
        binding.canNumberField.setError(getString(R.string.onfido_enter_can_error_label, Integer.valueOf(retriesLeft), getResources().getQuantityString(R.plurals.onfido_enter_can_substring_attempt, retriesLeft)));
        Integer num = this.errorColor;
        if (num != null) {
            binding.canTextInput.setTextColor(num.intValue());
        }
    }

    private final void showHint() {
        EditText editText = getBinding().canNumberField.getEditText();
        if (editText == null) {
            return;
        }
        editText.setHint(StringsKt.repeat("-", getViewModel().getCanLength()));
    }

    public final NfcCanEntryViewModel.Factory getNfcViewModelFactory() {
        NfcCanEntryViewModel.Factory factory = this.nfcViewModelFactory;
        if (factory != null) {
            return factory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("nfcViewModelFactory");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        EditText editText = getBinding().canNumberField.getEditText();
        if (editText != null) {
            ViewExtensionsKt.clearText(editText);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) throws Resources.NotFoundException {
        EditText editText;
        Editable text;
        Intrinsics.checkNotNullParameter(view, "view");
        SdkController companion = SdkController.INSTANCE.getInstance();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        super.onViewCreated(view, savedInstanceState);
        Context contextRequireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext(...)");
        this.errorColor = Integer.valueOf(ContextUtilsKt.colorFromAttr(contextRequireContext2, R.attr.onfidoColorContentNegative));
        Context contextRequireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext(...)");
        this.validInputColor = ContextUtilsKt.colorFromAttr(contextRequireContext3, R.attr.onfidoColorContentMain);
        this._binding = OnfidoFragmentNfcCanEntryBinding.bind(view);
        OnfidoFragmentNfcCanEntryBinding binding = getBinding();
        int canLength = getViewModel().getCanLength();
        binding.description.setText(getResources().getString(R.string.onfido_enter_can_subtitle, Integer.valueOf(canLength)));
        bindNoCanButton();
        setEditTextMaxLength(canLength);
        prefillCanOrShowHint();
        addEditTextChangedListener();
        addOnFocusChangedListener();
        AppCompatButton appCompatButton = binding.continueButton.getAppCompatButton();
        EditText editText2 = binding.canNumberField.getEditText();
        appCompatButton.setEnabled((editText2 != null ? editText2.getError() : null) == null && (editText = binding.canNumberField.getEditText()) != null && (text = editText.getText()) != null && text.length() == canLength);
        binding.continueButton.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcCanEntryFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                NfcCanEntryFragment.onViewCreated$lambda$1$lambda$0(this.f$0, view2);
            }
        });
        getViewModel().onViewCreated(getNfcHostViewModel().nfcScanAttempt());
    }

    public final void setNfcViewModelFactory(NfcCanEntryViewModel.Factory factory) {
        Intrinsics.checkNotNullParameter(factory, "<set-?>");
        this.nfcViewModelFactory = factory;
    }
}
