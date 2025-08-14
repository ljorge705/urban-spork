package com.onfido.android.sdk.capture.ui.nfc;

import android.content.Context;
import android.content.Intent;
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
import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.core.config.FailureReason;
import com.onfido.android.sdk.capture.core.config.FlowFragment;
import com.onfido.android.sdk.capture.core.features.nfc.NfcFlow;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType;
import com.onfido.android.sdk.capture.internal.navigation.Screen;
import com.onfido.android.sdk.capture.internal.navigation.navigator.FragmentNavigationManager;
import com.onfido.android.sdk.capture.internal.nfc.data.NfcPassportExtraction;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.ui.nfc.NfcHostViewModel;
import com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.LifecycleDisposableKt;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 )2\u00020\u0001:\u0002)*B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\"\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0017J\b\u0010\u001e\u001a\u00020\u0015H\u0016J\b\u0010\u001f\u001a\u00020\u0015H\u0016J\u001a\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010%\u001a\u00020\u0015H\u0002J\u0010\u0010&\u001a\u00020\u00152\u0006\u0010'\u001a\u00020(H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\t\u001a\u00020\n8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\b\u001a\u0004\b\u0011\u0010\u0012¨\u0006+"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment;", "Lcom/onfido/android/sdk/capture/core/config/FlowFragment;", "()V", "navigationManager", "Lcom/onfido/android/sdk/capture/internal/navigation/navigator/FragmentNavigationManager;", "getNavigationManager", "()Lcom/onfido/android/sdk/capture/internal/navigation/navigator/FragmentNavigationManager;", "navigationManager$delegate", "Lkotlin/Lazy;", "nfcViewModelFactory", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$Factory;", "getNfcViewModelFactory$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$Factory;", "setNfcViewModelFactory$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$Factory;)V", "viewModel", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel;", "getViewModel", "()Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel;", "viewModel$delegate", "handleUIMessage", "", "uiMessage", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage;", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onStart", "onStop", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "registerBackPressDispatcher", "submitResult", "nfcHostResult", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult;", "Companion", "NfcHostResult", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcHostFragment extends FlowFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String KEY_ARG_COUNTRY_CODE = "key_country_code";
    public static final String KEY_ARG_DOC_TYPE = "key_doc_type";
    private static final String KEY_ARG_IS_IN_WORKFLOW = "key_is_in_workflow";
    public static final String KEY_ARG_NFC_PROPERTIES = "key_nfc_properties";
    public static final String KEY_ARG_REQUEST = "key_request";
    private static final String KEY_HOST_RESULT = "key_host_result";
    public static final String KEY_IS_ONLY_ONE_DOC_AVAILABLE = "key_is_only_one_doc";
    public static final String KEY_NFC_OPTIONS = "key_nfc_options";
    private static final int REQUEST_CODE_NFC_SETTINGS = 1;

    /* renamed from: navigationManager$delegate, reason: from kotlin metadata */
    private final Lazy navigationManager;

    @Inject
    public NfcHostViewModel.Factory nfcViewModelFactory;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011JD\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u001eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$Companion;", "", "()V", "KEY_ARG_COUNTRY_CODE", "", "KEY_ARG_DOC_TYPE", "KEY_ARG_IS_IN_WORKFLOW", "KEY_ARG_NFC_PROPERTIES", "KEY_ARG_REQUEST", "KEY_HOST_RESULT", "KEY_IS_ONLY_ONE_DOC_AVAILABLE", "KEY_NFC_OPTIONS", "REQUEST_CODE_NFC_SETTINGS", "", "getNfcHostResult", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult;", "bundle", "Landroid/os/Bundle;", "newInstance", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment;", "requestKey", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "nfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "nfcOptions", "Lcom/onfido/android/sdk/capture/model/NFCOptions$Enabled;", "isOnlyOneDocAvailable", "", "isInWorkflow", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final NfcHostResult getNfcHostResult(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Parcelable parcelable = bundle.getParcelable(NfcHostFragment.KEY_HOST_RESULT);
            if (parcelable != null) {
                return (NfcHostResult) parcelable;
            }
            throw new IllegalArgumentException("Required value was null.".toString());
        }

        public final NfcHostFragment newInstance(String requestKey, DocumentType documentType, CountryCode countryCode, NfcProperties nfcProperties, NFCOptions.Enabled nfcOptions, boolean isOnlyOneDocAvailable, boolean isInWorkflow) {
            Intrinsics.checkNotNullParameter(requestKey, "requestKey");
            Intrinsics.checkNotNullParameter(nfcProperties, "nfcProperties");
            Intrinsics.checkNotNullParameter(nfcOptions, "nfcOptions");
            NfcHostFragment nfcHostFragment = new NfcHostFragment();
            nfcHostFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(NfcHostFragment.KEY_ARG_NFC_PROPERTIES, nfcProperties), TuplesKt.to(NfcHostFragment.KEY_ARG_DOC_TYPE, documentType), TuplesKt.to(NfcHostFragment.KEY_ARG_COUNTRY_CODE, countryCode), TuplesKt.to(NfcHostFragment.KEY_ARG_REQUEST, requestKey), TuplesKt.to(NfcHostFragment.KEY_NFC_OPTIONS, nfcOptions), TuplesKt.to(NfcHostFragment.KEY_IS_ONLY_ONE_DOC_AVAILABLE, Boolean.valueOf(isOnlyOneDocAvailable)), TuplesKt.to(NfcHostFragment.KEY_ARG_IS_IN_WORKFLOW, Boolean.valueOf(isInWorkflow))));
            return nfcHostFragment;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult;", "Landroid/os/Parcelable;", "()V", "Exit", "ExitOnfidoFlow", "NfcScanSkipped", "NfcScanSuccess", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult$Exit;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult$ExitOnfidoFlow;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult$NfcScanSkipped;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult$NfcScanSuccess;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class NfcHostResult implements Parcelable {

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004HÖ\u0001¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult$Exit;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Exit extends NfcHostResult {
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
                super(null);
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

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004HÖ\u0001¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult$ExitOnfidoFlow;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class ExitOnfidoFlow extends NfcHostResult {
            public static final ExitOnfidoFlow INSTANCE = new ExitOnfidoFlow();
            public static final Parcelable.Creator<ExitOnfidoFlow> CREATOR = new Creator();

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<ExitOnfidoFlow> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final ExitOnfidoFlow createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    parcel.readInt();
                    return ExitOnfidoFlow.INSTANCE;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final ExitOnfidoFlow[] newArray(int i) {
                    return new ExitOnfidoFlow[i];
                }
            }

            private ExitOnfidoFlow() {
                super(null);
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

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004HÖ\u0001¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult$NfcScanSkipped;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class NfcScanSkipped extends NfcHostResult {
            public static final NfcScanSkipped INSTANCE = new NfcScanSkipped();
            public static final Parcelable.Creator<NfcScanSkipped> CREATOR = new Creator();

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<NfcScanSkipped> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final NfcScanSkipped createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    parcel.readInt();
                    return NfcScanSkipped.INSTANCE;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final NfcScanSkipped[] newArray(int i) {
                    return new NfcScanSkipped[i];
                }
            }

            private NfcScanSkipped() {
                super(null);
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

        @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u0019\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001c"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult$NfcScanSuccess;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostFragment$NfcHostResult;", "nfcData", "Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "(Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;)V", "getNfcData", "()Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;", "getNfcFlowType", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "component1", "component2", Constants.COPY_TYPE, "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class NfcScanSuccess extends NfcHostResult {
            public static final Parcelable.Creator<NfcScanSuccess> CREATOR = new Creator();
            private final NfcPassportExtraction nfcData;
            private final NfcFlowType nfcFlowType;

            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<NfcScanSuccess> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final NfcScanSuccess createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new NfcScanSuccess(NfcPassportExtraction.CREATOR.createFromParcel(parcel), NfcFlowType.valueOf(parcel.readString()));
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public final NfcScanSuccess[] newArray(int i) {
                    return new NfcScanSuccess[i];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public NfcScanSuccess(NfcPassportExtraction nfcData, NfcFlowType nfcFlowType) {
                super(null);
                Intrinsics.checkNotNullParameter(nfcData, "nfcData");
                Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
                this.nfcData = nfcData;
                this.nfcFlowType = nfcFlowType;
            }

            public static /* synthetic */ NfcScanSuccess copy$default(NfcScanSuccess nfcScanSuccess, NfcPassportExtraction nfcPassportExtraction, NfcFlowType nfcFlowType, int i, Object obj) {
                if ((i & 1) != 0) {
                    nfcPassportExtraction = nfcScanSuccess.nfcData;
                }
                if ((i & 2) != 0) {
                    nfcFlowType = nfcScanSuccess.nfcFlowType;
                }
                return nfcScanSuccess.copy(nfcPassportExtraction, nfcFlowType);
            }

            /* renamed from: component1, reason: from getter */
            public final NfcPassportExtraction getNfcData() {
                return this.nfcData;
            }

            /* renamed from: component2, reason: from getter */
            public final NfcFlowType getNfcFlowType() {
                return this.nfcFlowType;
            }

            public final NfcScanSuccess copy(NfcPassportExtraction nfcData, NfcFlowType nfcFlowType) {
                Intrinsics.checkNotNullParameter(nfcData, "nfcData");
                Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
                return new NfcScanSuccess(nfcData, nfcFlowType);
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof NfcScanSuccess)) {
                    return false;
                }
                NfcScanSuccess nfcScanSuccess = (NfcScanSuccess) other;
                return Intrinsics.areEqual(this.nfcData, nfcScanSuccess.nfcData) && this.nfcFlowType == nfcScanSuccess.nfcFlowType;
            }

            public final NfcPassportExtraction getNfcData() {
                return this.nfcData;
            }

            public final NfcFlowType getNfcFlowType() {
                return this.nfcFlowType;
            }

            public int hashCode() {
                return (this.nfcData.hashCode() * 31) + this.nfcFlowType.hashCode();
            }

            public String toString() {
                return "NfcScanSuccess(nfcData=" + this.nfcData + ", nfcFlowType=" + this.nfcFlowType + ')';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int flags) {
                Intrinsics.checkNotNullParameter(parcel, "out");
                this.nfcData.writeToParcel(parcel, flags);
                parcel.writeString(this.nfcFlowType.name());
            }
        }

        private NfcHostResult() {
        }

        public /* synthetic */ NfcHostResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public NfcHostFragment() {
        super(R.layout.onfido_nfc_fragment_host);
        this.navigationManager = LazyKt.lazy(new Function0<FragmentNavigationManager>() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment$navigationManager$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FragmentNavigationManager invoke() {
                NfcHostFragment nfcHostFragment = this.this$0;
                FragmentManager childFragmentManager = nfcHostFragment.getChildFragmentManager();
                Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
                return new FragmentNavigationManager(nfcHostFragment, childFragmentManager, R.id.fragment_container);
            }
        });
        Function0<ViewModelProvider.Factory> function0 = new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment$viewModel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                final NfcHostFragment nfcHostFragment = this.this$0;
                final Bundle bundleRequireArguments = nfcHostFragment.requireArguments();
                final NfcHostFragment nfcHostFragment2 = this.this$0;
                return new AbstractSavedStateViewModelFactory(nfcHostFragment, bundleRequireArguments) { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment$viewModel$2$invoke$$inlined$createAbstractSavedStateFactory$1
                    @Override // androidx.lifecycle.AbstractSavedStateViewModelFactory
                    protected <T extends ViewModel> T create(String key, Class<T> modelClass, SavedStateHandle handle) {
                        Intrinsics.checkNotNullParameter(key, "key");
                        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                        Intrinsics.checkNotNullParameter(handle, "handle");
                        NfcHostViewModel nfcHostViewModelCreate = nfcHostFragment2.getNfcViewModelFactory$onfido_capture_sdk_core_release().create(handle);
                        Intrinsics.checkNotNull(nfcHostViewModelCreate, "null cannot be cast to non-null type T of com.onfido.android.sdk.capture.component.utils.ViewModelExtKt.createAbstractSavedStateFactory.<no name provided>.create");
                        return nfcHostViewModelCreate;
                    }
                };
            }
        };
        final Function0<Fragment> function02 = new Function0<Fragment>() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment$special$$inlined$viewModels$default$2
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
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(NfcHostViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment$special$$inlined$viewModels$default$4
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

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentNavigationManager getNavigationManager() {
        return (FragmentNavigationManager) this.navigationManager.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NfcHostViewModel getViewModel() {
        return (NfcHostViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleUIMessage(NfcHostViewModel.UIMessage uiMessage) {
        NfcHostResult nfcScanSuccess;
        if (Intrinsics.areEqual(uiMessage, NfcHostViewModel.UIMessage.OpenNfcSettings.INSTANCE)) {
            startActivityForResult(new Intent("android.settings.NFC_SETTINGS"), 1);
        } else {
            if (Intrinsics.areEqual(uiMessage, NfcHostViewModel.UIMessage.NfcScanSkipped.INSTANCE)) {
                nfcScanSuccess = NfcHostResult.NfcScanSkipped.INSTANCE;
            } else if (uiMessage instanceof NfcHostViewModel.UIMessage.NfcScanSuccess) {
                NfcHostViewModel.UIMessage.NfcScanSuccess nfcScanSuccess2 = (NfcHostViewModel.UIMessage.NfcScanSuccess) uiMessage;
                nfcScanSuccess = new NfcHostResult.NfcScanSuccess(nfcScanSuccess2.getNfcData(), nfcScanSuccess2.getNfcFlowType());
            } else if (!(uiMessage instanceof NfcHostViewModel.UIMessage.CanNumberEntered)) {
                if (Intrinsics.areEqual(uiMessage, NfcHostViewModel.UIMessage.Exit.INSTANCE)) {
                    nfcScanSuccess = NfcHostResult.Exit.INSTANCE;
                } else {
                    if (!Intrinsics.areEqual(uiMessage, NfcHostViewModel.UIMessage.ExitOnfidoFlow.INSTANCE)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    nfcScanSuccess = NfcHostResult.ExitOnfidoFlow.INSTANCE;
                }
            }
            submitResult(nfcScanSuccess);
        }
        Unit unit = Unit.INSTANCE;
        getViewModel().consumeUIMessage(uiMessage);
    }

    private final void registerBackPressDispatcher() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment.registerBackPressDispatcher.1
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                List<Screen> currentScreensSnapshot = NfcHostFragment.this.getNavigationManager().getCurrentScreensSnapshot();
                NfcHostFragment.this.getViewModel().onBackClicked((Screen) CollectionsKt.lastOrNull((List) currentScreensSnapshot), currentScreensSnapshot.size());
            }
        });
    }

    private final void submitResult(NfcHostResult nfcHostResult) {
        Bundle arguments = getArguments();
        String string = arguments != null ? arguments.getString(KEY_ARG_REQUEST) : null;
        if (string == null) {
            finishFlow(nfcHostResult instanceof NfcHostResult.NfcScanSuccess ? NfcFlow.Result.Success.INSTANCE : new NfcFlow.Result.Failed(FailureReason.Canceled.INSTANCE));
            return;
        }
        getParentFragmentManager().setFragmentResult(string, BundleKt.bundleOf(TuplesKt.to(KEY_HOST_RESULT, nfcHostResult)));
        Bundle arguments2 = getArguments();
        if (arguments2 == null || !arguments2.getBoolean(KEY_ARG_IS_IN_WORKFLOW)) {
            getParentFragmentManager().popBackStack();
        }
    }

    public final NfcHostViewModel.Factory getNfcViewModelFactory$onfido_capture_sdk_core_release() {
        NfcHostViewModel.Factory factory = this.nfcViewModelFactory;
        if (factory != null) {
            return factory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("nfcViewModelFactory");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    @Deprecated(message = "Overrides a deprecated method, compilation error with Kotlin 1.9")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            getViewModel().onNfcSettingsActivityResult();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        getViewModel().getNavigationManagerHolder().setNavigationManager(getNavigationManager());
        Disposable disposableSubscribe = getViewModel().getUiMessages().subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.nfc.NfcHostFragment.onStart.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(List<? extends NfcHostViewModel.UIMessage> messages) {
                Intrinsics.checkNotNullParameter(messages, "messages");
                NfcHostFragment nfcHostFragment = NfcHostFragment.this;
                Iterator<T> it = messages.iterator();
                while (it.hasNext()) {
                    nfcHostFragment.handleUIMessage((NfcHostViewModel.UIMessage) it.next());
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        LifecycleDisposableKt.disposeOnStop(disposableSubscribe, viewLifecycleOwner);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        getViewModel().getNavigationManagerHolder().resetNavigationManager();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        SdkController companion = SdkController.INSTANCE.getInstance();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        registerBackPressDispatcher();
    }

    public final void setNfcViewModelFactory$onfido_capture_sdk_core_release(NfcHostViewModel.Factory factory) {
        Intrinsics.checkNotNullParameter(factory, "<set-?>");
        this.nfcViewModelFactory = factory;
    }
}
