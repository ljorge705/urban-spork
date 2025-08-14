package com.onfido.android.sdk.capture.ui.documentselection.host;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import androidx.activity.OnBackPressedCallback;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.document.DocumentPages;
import com.onfido.android.sdk.capture.internal.navigation.navigator.FragmentNavigationManager;
import com.onfido.android.sdk.capture.internal.ui.countryselection.CountrySelectionFragment;
import com.onfido.android.sdk.capture.internal.util.FragmentManagerExtKt;
import com.onfido.android.sdk.capture.ui.BaseFragment;
import com.onfido.android.sdk.capture.ui.documentselection.DocumentTypeSelectionState;
import com.onfido.android.sdk.capture.ui.documentselection.di.RestrictedDocumentSelectionHostComponent;
import com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostViewModel;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.LifecycleDisposableKt;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.io.Serializable;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 %2\u00020\u0001:\u0002%&B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001aH\u0016J\u001a\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010!\u001a\u00020\u001aH\u0002J\u0010\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u000e\u001a\u0004\b\u0016\u0010\u0017¨\u0006'"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostFragment;", "Lcom/onfido/android/sdk/capture/ui/BaseFragment;", "()V", "factory", "Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostViewModel$Factory;", "getFactory$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostViewModel$Factory;", "setFactory$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostViewModel$Factory;)V", "navigationManager", "Lcom/onfido/android/sdk/capture/internal/navigation/navigator/FragmentNavigationManager;", "getNavigationManager", "()Lcom/onfido/android/sdk/capture/internal/navigation/navigator/FragmentNavigationManager;", "navigationManager$delegate", "Lkotlin/Lazy;", "rdsComponent", "Lcom/onfido/android/sdk/capture/ui/documentselection/di/RestrictedDocumentSelectionHostComponent;", "getRdsComponent$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/documentselection/di/RestrictedDocumentSelectionHostComponent;", "rdsComponent$delegate", "viewModel", "Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostViewModel;", "getViewModel", "()Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostViewModel;", "viewModel$delegate", "handleBackNavigation", "", "onStart", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setListenerForCountrySelection", "submitResult", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostFragment$DocumentSelectionResult;", "Companion", "DocumentSelectionResult", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentSelectionHostFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_IS_IN_WORKFLOW = "key_is_in_workflow";
    private static final String KEY_NFC_REQUIRED_WARNING_VISIBLE = "key_nfc_warning";
    private static final String KEY_RDS_HOST_RESULT = "key_rds_host_result";
    private static final String KEY_SELECTED_COUNTRY = "key_selected_country";

    @Inject
    public DocumentSelectionHostViewModel.Factory factory;

    /* renamed from: navigationManager$delegate, reason: from kotlin metadata */
    private final Lazy navigationManager;

    /* renamed from: rdsComponent$delegate, reason: from kotlin metadata */
    private final Lazy rdsComponent;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000eJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostFragment$Companion;", "", "()V", "KEY_IS_IN_WORKFLOW", "", "KEY_NFC_REQUIRED_WARNING_VISIBLE", "KEY_RDS_HOST_RESULT", "KEY_SELECTED_COUNTRY", "createInstance", "Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostFragment;", "selectedCountryCode", "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "resultKey", "nfcRequiredWarningVisible", "", "isInWorkflow", "getResult", "Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostFragment$DocumentSelectionResult;", "bundle", "Landroid/os/Bundle;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public static /* synthetic */ DocumentSelectionHostFragment createInstance$default(Companion companion, CountryCode countryCode, String str, boolean z, boolean z2, int i, Object obj) {
            if ((i & 4) != 0) {
                z = false;
            }
            if ((i & 8) != 0) {
                z2 = false;
            }
            return companion.createInstance(countryCode, str, z, z2);
        }

        public final DocumentSelectionHostFragment createInstance(CountryCode selectedCountryCode, String resultKey, boolean nfcRequiredWarningVisible, boolean isInWorkflow) {
            Intrinsics.checkNotNullParameter(resultKey, "resultKey");
            DocumentSelectionHostFragment documentSelectionHostFragment = new DocumentSelectionHostFragment();
            documentSelectionHostFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(DocumentSelectionHostFragment.KEY_RDS_HOST_RESULT, resultKey), TuplesKt.to(DocumentSelectionHostFragment.KEY_SELECTED_COUNTRY, selectedCountryCode), TuplesKt.to(DocumentSelectionHostFragment.KEY_NFC_REQUIRED_WARNING_VISIBLE, Boolean.valueOf(nfcRequiredWarningVisible)), TuplesKt.to(DocumentSelectionHostFragment.KEY_IS_IN_WORKFLOW, Boolean.valueOf(isInWorkflow))));
            return documentSelectionHostFragment;
        }

        public final DocumentSelectionResult getResult(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Parcelable parcelable = bundle.getParcelable(DocumentSelectionHostFragment.KEY_RDS_HOST_RESULT);
            if (parcelable != null) {
                return (DocumentSelectionResult) parcelable;
            }
            throw new IllegalArgumentException("Required value was null.".toString());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostFragment$DocumentSelectionResult;", "Landroid/os/Parcelable;", "Completed", "Exit", "Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostFragment$DocumentSelectionResult$Completed;", "Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostFragment$DocumentSelectionResult$Exit;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface DocumentSelectionResult extends Parcelable {

        @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\u0019\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001a"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostFragment$DocumentSelectionResult$Completed;", "Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostFragment$DocumentSelectionResult;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "genericDocTitle", "", "genericDocPages", "Lcom/onfido/android/sdk/capture/document/DocumentPages;", "(Lcom/onfido/android/sdk/capture/utils/CountryCode;Lcom/onfido/android/sdk/capture/DocumentType;Ljava/lang/String;Lcom/onfido/android/sdk/capture/document/DocumentPages;)V", "getCountryCode", "()Lcom/onfido/android/sdk/capture/utils/CountryCode;", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "getGenericDocPages", "()Lcom/onfido/android/sdk/capture/document/DocumentPages;", "getGenericDocTitle", "()Ljava/lang/String;", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Completed implements DocumentSelectionResult {
            public static final Parcelable.Creator<Completed> CREATOR = new Creator();
            private final CountryCode countryCode;
            private final DocumentType documentType;
            private final DocumentPages genericDocPages;
            private final String genericDocTitle;

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Completed> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Completed createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new Completed(CountryCode.valueOf(parcel.readString()), DocumentType.valueOf(parcel.readString()), parcel.readString(), parcel.readInt() == 0 ? null : DocumentPages.valueOf(parcel.readString()));
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Completed[] newArray(int i) {
                    return new Completed[i];
                }
            }

            public Completed(CountryCode countryCode, DocumentType documentType, String str, DocumentPages documentPages) {
                Intrinsics.checkNotNullParameter(countryCode, "countryCode");
                Intrinsics.checkNotNullParameter(documentType, "documentType");
                this.countryCode = countryCode;
                this.documentType = documentType;
                this.genericDocTitle = str;
                this.genericDocPages = documentPages;
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public final CountryCode getCountryCode() {
                return this.countryCode;
            }

            public final DocumentType getDocumentType() {
                return this.documentType;
            }

            public final DocumentPages getGenericDocPages() {
                return this.genericDocPages;
            }

            public final String getGenericDocTitle() {
                return this.genericDocTitle;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeString(this.countryCode.name());
                parcel.writeString(this.documentType.name());
                parcel.writeString(this.genericDocTitle);
                DocumentPages documentPages = this.genericDocPages;
                if (documentPages == null) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(1);
                    parcel.writeString(documentPages.name());
                }
            }
        }

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004HÖ\u0001¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostFragment$DocumentSelectionResult$Exit;", "Lcom/onfido/android/sdk/capture/ui/documentselection/host/DocumentSelectionHostFragment$DocumentSelectionResult;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Exit implements DocumentSelectionResult {
            public static final Exit INSTANCE = new Exit();
            public static final Parcelable.Creator<Exit> CREATOR = new Creator();

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Exit> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Exit createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    parcel.readInt();
                    return Exit.INSTANCE;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final Exit[] newArray(int i) {
                    return new Exit[i];
                }
            }

            private Exit() {
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                parcel.writeInt(1);
            }
        }
    }

    public DocumentSelectionHostFragment() {
        super(R.layout.onfido_fragment_restricted_document_host);
        this.rdsComponent = LazyKt.lazy(new Function0<RestrictedDocumentSelectionHostComponent>() { // from class: com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment$rdsComponent$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RestrictedDocumentSelectionHostComponent invoke() {
                SdkController companion = SdkController.INSTANCE.getInstance();
                Context contextRequireContext = this.this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
                return SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null).rdsHostComponentFactory$onfido_capture_sdk_core_release().create();
            }
        });
        Function0<ViewModelProvider.Factory> function0 = new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment$viewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                final DocumentSelectionHostFragment documentSelectionHostFragment = this.this$0;
                final Bundle bundle = null;
                return new AbstractSavedStateViewModelFactory(documentSelectionHostFragment, bundle) { // from class: com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment$viewModel$2$invoke$$inlined$createAbstractSavedStateFactory$default$1
                    @Override // androidx.lifecycle.AbstractSavedStateViewModelFactory
                    protected <T extends ViewModel> T create(String key, Class<T> modelClass, SavedStateHandle handle) {
                        Intrinsics.checkNotNullParameter(key, "key");
                        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                        Intrinsics.checkNotNullParameter(handle, "handle");
                        DocumentSelectionHostViewModel documentSelectionHostViewModelCreate = documentSelectionHostFragment.getFactory$onfido_capture_sdk_core_release().create(handle);
                        Intrinsics.checkNotNull(documentSelectionHostViewModelCreate, "null cannot be cast to non-null type T of com.onfido.android.sdk.capture.component.utils.ViewModelExtKt.createAbstractSavedStateFactory.<no name provided>.create");
                        return documentSelectionHostViewModelCreate;
                    }
                };
            }
        };
        final Function0<Fragment> function02 = new Function0<Fragment>() { // from class: com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment$special$$inlined$viewModels$default$2
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(DocumentSelectionHostViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment$special$$inlined$viewModels$default$4
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
        this.navigationManager = LazyKt.lazy(new Function0<FragmentNavigationManager>() { // from class: com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment$navigationManager$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FragmentNavigationManager invoke() {
                DocumentSelectionHostFragment documentSelectionHostFragment = this.this$0;
                FragmentManager childFragmentManager = documentSelectionHostFragment.getChildFragmentManager();
                Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
                return new FragmentNavigationManager(documentSelectionHostFragment, childFragmentManager, R.id.fragmentContainerView);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentNavigationManager getNavigationManager() {
        return (FragmentNavigationManager) this.navigationManager.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DocumentSelectionHostViewModel getViewModel() {
        return (DocumentSelectionHostViewModel) this.viewModel.getValue();
    }

    private final void handleBackNavigation() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback() { // from class: com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment.handleBackNavigation.1
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                if (DocumentSelectionHostFragment.this.getNavigationManager().getCurrentScreensSnapshot().size() > 1) {
                    DocumentSelectionHostFragment.this.getViewModel().getNavigator().exitCurrentScreen();
                    return;
                }
                DocumentSelectionHostFragment.this.submitResult(DocumentSelectionResult.Exit.INSTANCE);
                Bundle arguments = DocumentSelectionHostFragment.this.getArguments();
                if (arguments == null || !arguments.getBoolean(DocumentSelectionHostFragment.KEY_IS_IN_WORKFLOW)) {
                    DocumentSelectionHostFragment.this.getParentFragmentManager().popBackStack();
                }
            }
        });
    }

    private final void setListenerForCountrySelection() {
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
        FragmentManagerExtKt.setFragmentResultsListeners(childFragmentManager, this, new String[]{DocumentSelectionHostViewModel.KEY_COUNTRY_PICKER_RESULT}, new Function2<String, Bundle, Unit>() { // from class: com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment.setListenerForCountrySelection.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Bundle bundle) {
                invoke2(str, bundle);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String str, Bundle bundle) {
                Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                DocumentSelectionHostFragment.this.getViewModel().onCountrySelectionScreenResult(CountrySelectionFragment.INSTANCE.getResult(bundle).getCountryCode());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void submitResult(DocumentSelectionResult result) {
        String string = requireArguments().getString(KEY_RDS_HOST_RESULT);
        if (string == null) {
            string = "";
        }
        getParentFragmentManager().setFragmentResult(string, BundleKt.bundleOf(TuplesKt.to(KEY_RDS_HOST_RESULT, result)));
    }

    public final DocumentSelectionHostViewModel.Factory getFactory$onfido_capture_sdk_core_release() {
        DocumentSelectionHostViewModel.Factory factory = this.factory;
        if (factory != null) {
            return factory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("factory");
        return null;
    }

    public final RestrictedDocumentSelectionHostComponent getRdsComponent$onfido_capture_sdk_core_release() {
        return (RestrictedDocumentSelectionHostComponent) this.rdsComponent.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        getViewModel().getNavigationManagerHolder().setNavigationManager(getNavigationManager());
        getViewModel().onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        getRdsComponent$onfido_capture_sdk_core_release().inject(this);
        super.onViewCreated(view, savedInstanceState);
        handleBackNavigation();
        Bundle arguments = getArguments();
        Serializable serializable = arguments != null ? arguments.getSerializable(KEY_SELECTED_COUNTRY) : null;
        CountryCode countryCode = serializable instanceof CountryCode ? (CountryCode) serializable : null;
        if (countryCode != null) {
            getViewModel().onCountrySelected(countryCode);
        }
        getViewModel().setNfcRquiredWarning(requireArguments().getBoolean(KEY_NFC_REQUIRED_WARNING_VISIBLE));
        getViewModel().loadScreens();
        Disposable disposableSubscribe = getViewModel().getState$onfido_capture_sdk_core_release().subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostFragment.onViewCreated.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(DocumentTypeSelectionState documentTypeSelectionState) {
                if (documentTypeSelectionState instanceof DocumentTypeSelectionState.DocumentTypeSelected) {
                    DocumentTypeSelectionState.DocumentTypeSelected documentTypeSelected = (DocumentTypeSelectionState.DocumentTypeSelected) documentTypeSelectionState;
                    DocumentSelectionHostFragment.this.getViewModel().onCountrySelected(documentTypeSelected.getCountryCode());
                    DocumentSelectionHostFragment.this.submitResult(new DocumentSelectionResult.Completed(documentTypeSelected.getCountryCode(), documentTypeSelected.getDocumentType(), documentTypeSelected.getGenericDocTitle(), documentTypeSelected.getGenericDocPages()));
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        LifecycleDisposableKt.disposeOnDestroy(disposableSubscribe, viewLifecycleOwner);
        setListenerForCountrySelection();
    }

    public final void setFactory$onfido_capture_sdk_core_release(DocumentSelectionHostViewModel.Factory factory) {
        Intrinsics.checkNotNullParameter(factory, "<set-?>");
        this.factory = factory;
    }
}
