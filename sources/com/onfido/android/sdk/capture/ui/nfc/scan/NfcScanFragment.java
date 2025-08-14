package com.onfido.android.sdk.capture.ui.nfc.scan;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.common.result.HapticFeedback;
import com.onfido.android.sdk.capture.databinding.OnfidoFragmentNfcScanBinding;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.internal.nfc.NfcTagDelegate;
import com.onfido.android.sdk.capture.internal.nfc.data.NfcPassportExtraction;
import com.onfido.android.sdk.capture.internal.nfc.data.PassportBACKey;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.nfc.MRTDDataGroup;
import com.onfido.android.sdk.capture.ui.camera.view.PlaybackControlsVideoPlayerView;
import com.onfido.android.sdk.capture.ui.nfc.NfcHostViewModel;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanBottomDialog;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanPresenter;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.sentry.ProfilingTraceData;
import java.io.IOException;
import java.io.Serializable;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import org.jmrtd.PassportService;

@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 ]2\u00020\u00012\u00020\u0002:\u0001]B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@H\u0002J\b\u0010A\u001a\u00020<H\u0002J\b\u0010B\u001a\u00020<H\u0002J\b\u0010C\u001a\u00020<H\u0016J\u0018\u0010D\u001a\u00020<2\u0006\u0010?\u001a\u00020@2\u0006\u0010E\u001a\u00020FH\u0002J\b\u0010G\u001a\u00020<H\u0002J\b\u0010H\u001a\u00020<H\u0016J\u0010\u0010I\u001a\u00020<2\u0006\u0010J\u001a\u00020KH\u0002J\b\u0010L\u001a\u00020<H\u0002J\b\u0010M\u001a\u00020<H\u0002J\u0010\u0010N\u001a\u00020<2\u0006\u0010O\u001a\u00020PH\u0002J\b\u0010Q\u001a\u00020<H\u0016J\b\u0010R\u001a\u00020<H\u0016J\b\u0010S\u001a\u00020<H\u0016J\b\u0010T\u001a\u00020<H\u0016J\u001a\u0010U\u001a\u00020<2\u0006\u0010V\u001a\u00020W2\b\u0010X\u001a\u0004\u0018\u00010YH\u0016J\b\u0010Z\u001a\u00020<H\u0002J\b\u0010[\u001a\u00020<H\u0002J\b\u0010\\\u001a\u00020<H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u00020\u00078\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00128\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0017\u001a\u00020\u00188\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001d\u001a\u00020\u001e8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001b\u0010#\u001a\u00020$8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b%\u0010&R\u001b\u0010)\u001a\u00020*8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b-\u0010(\u001a\u0004\b+\u0010,R\u001b\u0010.\u001a\u00020/8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b2\u0010(\u001a\u0004\b0\u00101R\u001e\u00103\u001a\u0002048\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u000e\u00109\u001a\u00020:X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006^"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog$NfcScanBottomActions;", "()V", "_binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentNfcScanBinding;", "announcementService", "Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "getAnnouncementService$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "setAnnouncementService$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;)V", "binding", "getBinding", "()Lcom/onfido/android/sdk/capture/databinding/OnfidoFragmentNfcScanBinding;", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "dispatchersProvider", "Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;", "getDispatchersProvider$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;", "setDispatchersProvider$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;)V", "hapticFeedback", "Lcom/onfido/android/sdk/capture/common/result/HapticFeedback;", "getHapticFeedback$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/common/result/HapticFeedback;", "setHapticFeedback$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/common/result/HapticFeedback;)V", "nfcInteractor", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;", "getNfcInteractor$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;", "setNfcInteractor$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;)V", "nfcScanBottomDialog", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog;", "getNfcScanBottomDialog", "()Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanBottomDialog;", "nfcScanBottomDialog$delegate", "Lkotlin/Lazy;", "nfcViewModel", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel;", "getNfcViewModel", "()Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel;", "nfcViewModel$delegate", "presenter", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanPresenter;", "getPresenter", "()Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanPresenter;", "presenter$delegate", "presenterFactory", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanPresenter$Factory;", "getPresenterFactory$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanPresenter$Factory;", "setPresenterFactory$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanPresenter$Factory;)V", "readerModeStarted", "", "finishNFCScanning", "", "nfcData", "Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "observeNfcScanState", "observeViewState", "onDestroyView", "onNfcChipRead", "duration", "", "onNfcConnectionLost", "onNfcDialogDismissedByDialog", "onNfcScanFailed", "reason", "", "onNfcScanRetry", "onNfcScanTimeout", "onNfcScanning", "state", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/Scanning;", "onPause", "onResume", "onStart", "onStop", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setInstructions", "startEnableReaderMode", "startScanning", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcScanFragment extends Fragment implements NfcScanBottomDialog.NfcScanBottomActions {
    private static final String AA_CHALLENGE = "key_aa_challenge";
    private static final String COUNTRY_CODE_KEY = "country_code_key";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String DOCUMENT_TYPE_KEY = "document_type_key";
    private static final String NFC_FLOW_TYPE_KEY = "nfc_flow_type_key";
    private static final String PASSPORT_BAC_KEY = "key_passport_bac_key";
    private OnfidoFragmentNfcScanBinding _binding;

    @Inject
    public AnnouncementService announcementService;
    private final CompositeDisposable compositeDisposable;

    @Inject
    public DispatchersProvider dispatchersProvider;

    @Inject
    public HapticFeedback hapticFeedback;

    @Inject
    public NfcInteractor nfcInteractor;

    /* renamed from: nfcScanBottomDialog$delegate, reason: from kotlin metadata */
    private final Lazy nfcScanBottomDialog;

    /* renamed from: nfcViewModel$delegate, reason: from kotlin metadata */
    private final Lazy nfcViewModel;

    /* renamed from: presenter$delegate, reason: from kotlin metadata */
    private final Lazy presenter;

    @Inject
    public NfcScanPresenter.Factory presenterFactory;
    private boolean readerModeStarted;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J4\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanFragment$Companion;", "", "()V", "AA_CHALLENGE", "", "COUNTRY_CODE_KEY", "DOCUMENT_TYPE_KEY", "NFC_FLOW_TYPE_KEY", "PASSPORT_BAC_KEY", "createInstance", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanFragment;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "passportBACKey", "Lcom/onfido/android/sdk/capture/internal/nfc/data/PassportBACKey;", "aaChallenge", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final NfcScanFragment createInstance(DocumentType documentType, CountryCode countryCode, NfcFlowType nfcFlowType, PassportBACKey passportBACKey, byte[] aaChallenge) {
            Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
            Intrinsics.checkNotNullParameter(passportBACKey, "passportBACKey");
            NfcScanFragment nfcScanFragment = new NfcScanFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(NfcScanFragment.DOCUMENT_TYPE_KEY, documentType);
            bundle.putSerializable(NfcScanFragment.COUNTRY_CODE_KEY, countryCode);
            bundle.putSerializable(NfcScanFragment.NFC_FLOW_TYPE_KEY, nfcFlowType);
            bundle.putSerializable(NfcScanFragment.PASSPORT_BAC_KEY, passportBACKey);
            bundle.putByteArray(NfcScanFragment.AA_CHALLENGE, aaChallenge);
            nfcScanFragment.setArguments(bundle);
            return nfcScanFragment;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "viewState", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanViewState;", "accept"}, k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment$observeViewState$1, reason: invalid class name and case insensitive filesystem */
    static final class C06991<T> implements Consumer {
        C06991() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void accept$lambda$1$lambda$0(NfcScanFragment this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.getNfcViewModel().trackNFCScanClicked();
            this$0.startScanning();
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public final void accept(NfcScanViewState viewState) {
            Intrinsics.checkNotNullParameter(viewState, "viewState");
            OnfidoFragmentNfcScanBinding binding = NfcScanFragment.this.getBinding();
            final NfcScanFragment nfcScanFragment = NfcScanFragment.this;
            binding.title.setText(viewState.getTitleResId());
            binding.btnStartScanning.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment$observeViewState$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    NfcScanFragment.C06991.accept$lambda$1$lambda$0(nfcScanFragment, view);
                }
            });
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment$onPause$1", f = "NfcScanFragment.kt", i = {}, l = {PassportService.DEFAULT_MAX_BLOCKSIZE}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment$onPause$1, reason: invalid class name and case insensitive filesystem */
    static final class C07001 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment$onPause$1$1", f = "NfcScanFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment$onPause$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01611 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ NfcScanFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01611(NfcScanFragment nfcScanFragment, Continuation<? super C01611> continuation) {
                super(2, continuation);
                this.this$0 = nfcScanFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01611(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.readerModeStarted = false;
                NfcInteractor nfcInteractor$onfido_capture_sdk_core_release = this.this$0.getNfcInteractor$onfido_capture_sdk_core_release();
                FragmentActivity fragmentActivityRequireActivity = this.this$0.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
                nfcInteractor$onfido_capture_sdk_core_release.disableReaderMode(fragmentActivityRequireActivity);
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01611) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        C07001(Continuation<? super C07001> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NfcScanFragment.this.new C07001(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher coroutineDispatcherMo5607getDefault = NfcScanFragment.this.getDispatchersProvider$onfido_capture_sdk_core_release().mo5607getDefault();
                C01611 c01611 = new C01611(NfcScanFragment.this, null);
                this.label = 1;
                if (BuildersKt.withContext(coroutineDispatcherMo5607getDefault, c01611, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07001) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment$onResume$1", f = "NfcScanFragment.kt", i = {}, l = {205}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment$onResume$1, reason: invalid class name and case insensitive filesystem */
    static final class C07011 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment$onResume$1$1", f = "NfcScanFragment.kt", i = {}, l = {Mp4VideoDirectory.TAG_COMPRESSION_TYPE}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment$onResume$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01621 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ NfcScanFragment this$0;

            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
            @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment$onResume$1$1$1", f = "NfcScanFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment$onResume$1$1$1, reason: invalid class name and collision with other inner class name */
            static final class C01631 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;
                final /* synthetic */ NfcScanFragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C01631(NfcScanFragment nfcScanFragment, Continuation<? super C01631> continuation) {
                    super(2, continuation);
                    this.this$0 = nfcScanFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C01631(this.this$0, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    this.this$0.getBinding().btnStartScanning.setEnabled(true);
                    return Unit.INSTANCE;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C01631) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01621(NfcScanFragment nfcScanFragment, Continuation<? super C01621> continuation) {
                super(2, continuation);
                this.this$0 = nfcScanFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01621(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (this.this$0.getNfcViewModel().canNFCScanStart()) {
                        this.this$0.startEnableReaderMode();
                    }
                    CoroutineDispatcher main = this.this$0.getDispatchersProvider$onfido_capture_sdk_core_release().getMain();
                    C01631 c01631 = new C01631(this.this$0, null);
                    this.label = 1;
                    if (BuildersKt.withContext(main, c01631, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                if (this.this$0.getNfcViewModel().shouldStartScanningAutomatically()) {
                    this.this$0.startScanning();
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01621) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        C07011(Continuation<? super C07011> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NfcScanFragment.this.new C07011(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher coroutineDispatcherMo5607getDefault = NfcScanFragment.this.getDispatchersProvider$onfido_capture_sdk_core_release().mo5607getDefault();
                C01621 c01621 = new C01621(NfcScanFragment.this, null);
                this.label = 1;
                if (BuildersKt.withContext(coroutineDispatcherMo5607getDefault, c01621, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07011) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public NfcScanFragment() {
        super(R.layout.onfido_fragment_nfc_scan);
        final Function0<ViewModelStoreOwner> function0 = new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment$nfcViewModel$2
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
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment$special$$inlined$viewModels$default$1
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
        this.nfcViewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(NfcHostViewModel.class), new Function0<ViewModelStore>() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment$special$$inlined$viewModels$default$2
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
        }, new Function0<CreationExtras>() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment$special$$inlined$viewModels$default$3
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment$special$$inlined$viewModels$default$4
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
        this.presenter = LazyKt.lazy(new Function0<NfcScanPresenter>() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment$presenter$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final NfcScanPresenter invoke() {
                Serializable serializable = this.this$0.requireArguments().getSerializable("document_type_key");
                Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.onfido.android.sdk.capture.DocumentType");
                DocumentType documentType = (DocumentType) serializable;
                Serializable serializable2 = this.this$0.requireArguments().getSerializable("country_code_key");
                CountryCode countryCode = serializable2 instanceof CountryCode ? (CountryCode) serializable2 : null;
                Serializable serializable3 = this.this$0.requireArguments().getSerializable("nfc_flow_type_key");
                Intrinsics.checkNotNull(serializable3, "null cannot be cast to non-null type com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType");
                NfcFlowType nfcFlowType = (NfcFlowType) serializable3;
                Serializable serializable4 = this.this$0.requireArguments().getSerializable("key_passport_bac_key");
                Intrinsics.checkNotNull(serializable4, "null cannot be cast to non-null type com.onfido.android.sdk.capture.internal.nfc.data.PassportBACKey");
                PassportBACKey passportBACKey = (PassportBACKey) serializable4;
                byte[] byteArray = this.this$0.requireArguments().getByteArray("key_aa_challenge");
                Intrinsics.checkNotNull(byteArray);
                return this.this$0.getPresenterFactory$onfido_capture_sdk_core_release().create(documentType, countryCode, nfcFlowType, passportBACKey, byteArray, false, MRTDDataGroup.INSTANCE.m5616default(), null);
            }
        });
        this.nfcScanBottomDialog = LazyKt.lazy(new Function0<NfcScanBottomDialog>() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment$nfcScanBottomDialog$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final NfcScanBottomDialog invoke() {
                Context contextRequireContext = this.this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
                Serializable serializable = this.this$0.requireArguments().getSerializable("document_type_key");
                Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.onfido.android.sdk.capture.DocumentType");
                NfcScanFragment nfcScanFragment = this.this$0;
                return new NfcScanBottomDialog(contextRequireContext, (DocumentType) serializable, nfcScanFragment, nfcScanFragment.getAnnouncementService$onfido_capture_sdk_core_release(), this.this$0.getPresenter().getInstructionVideoPath(false));
            }
        });
        this.compositeDisposable = new CompositeDisposable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishNFCScanning(NfcPassportExtraction nfcData, NfcFlowType nfcFlowType) {
        getNfcScanBottomDialog().dismissDialog();
        getNfcViewModel().onNfcScanSucceeded(nfcData, nfcFlowType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OnfidoFragmentNfcScanBinding getBinding() {
        OnfidoFragmentNfcScanBinding onfidoFragmentNfcScanBinding = this._binding;
        Intrinsics.checkNotNull(onfidoFragmentNfcScanBinding);
        return onfidoFragmentNfcScanBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NfcScanBottomDialog getNfcScanBottomDialog() {
        return (NfcScanBottomDialog) this.nfcScanBottomDialog.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NfcHostViewModel getNfcViewModel() {
        return (NfcHostViewModel) this.nfcViewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NfcScanPresenter getPresenter() {
        return (NfcScanPresenter) this.presenter.getValue();
    }

    private final void observeNfcScanState() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = getPresenter().getScanState().subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment.observeNfcScanState.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(NfcScanState state) throws IllegalStateException {
                Intrinsics.checkNotNullParameter(state, "state");
                if (Intrinsics.areEqual(state, ScanReady.INSTANCE)) {
                    NfcScanFragment.this.getNfcScanBottomDialog().showNfcScanReadyView();
                } else if (state instanceof Scanning) {
                    NfcScanFragment.this.onNfcScanning((Scanning) state);
                } else if (state instanceof Scanned) {
                    Scanned scanned = (Scanned) state;
                    NfcScanFragment.this.onNfcChipRead(scanned.getNfcFlowType(), scanned.getDuration());
                } else if (Intrinsics.areEqual(state, ScanRetry.INSTANCE)) {
                    NfcScanFragment.this.onNfcScanRetry();
                } else if (Intrinsics.areEqual(state, ScanningTimeout.INSTANCE)) {
                    NfcScanFragment.this.onNfcScanTimeout();
                } else if (state instanceof ScanFailed) {
                    NfcScanFragment.this.onNfcScanFailed(((ScanFailed) state).getMessage());
                } else if (state instanceof ScanCompleted) {
                    ScanCompleted scanCompleted = (ScanCompleted) state;
                    NfcScanFragment.this.finishNFCScanning(scanCompleted.getNfcData(), scanCompleted.getNfcFlowType());
                } else if (state instanceof ConnectionLost) {
                    NfcScanFragment.this.onNfcConnectionLost();
                }
                if (Intrinsics.areEqual(state, NotStarted.INSTANCE)) {
                    NfcScanFragment.this.getBinding().videoView.start();
                } else {
                    NfcScanFragment.this.getBinding().videoView.pause();
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeViewState() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = getPresenter().getViewState().subscribe(new C06991());
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onNfcChipRead(NfcFlowType nfcFlowType, long duration) {
        getNfcViewModel().onNfcChipRead(nfcFlowType, duration);
        getNfcScanBottomDialog().showNfcSuccessView();
        HapticFeedback hapticFeedback$onfido_capture_sdk_core_release = getHapticFeedback$onfido_capture_sdk_core_release();
        LinearLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        hapticFeedback$onfido_capture_sdk_core_release.performSuccess(root);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onNfcConnectionLost() {
        getNfcScanBottomDialog().showConnectionLost();
        HapticFeedback hapticFeedback$onfido_capture_sdk_core_release = getHapticFeedback$onfido_capture_sdk_core_release();
        LinearLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        hapticFeedback$onfido_capture_sdk_core_release.performError(root);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onNfcScanFailed(String reason) {
        getNfcViewModel().onNfcScanFailed(reason);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onNfcScanRetry() {
        getNfcScanBottomDialog().showNfcScanRetry();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onNfcScanTimeout() {
        getNfcScanBottomDialog().dismissDialog();
        onNfcScanFailed(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onNfcScanning(Scanning state) {
        getNfcScanBottomDialog().showNfcScanningView(state.getProgress());
        if (state.getConnectionReestablished()) {
            HapticFeedback hapticFeedback$onfido_capture_sdk_core_release = getHapticFeedback$onfido_capture_sdk_core_release();
            LinearLayout root = getBinding().getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
            hapticFeedback$onfido_capture_sdk_core_release.performSuccess(root);
        }
    }

    private final void setInstructions() throws Resources.NotFoundException {
        LinearLayout stepsContainer = getBinding().stepsContainer;
        Intrinsics.checkNotNullExpressionValue(stepsContainer, "stepsContainer");
        ViewExtensionsKt.setInstructions(stepsContainer, getPresenter().getNfcInstructions());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startEnableReaderMode() {
        this.readerModeStarted = true;
        NfcInteractor nfcInteractor$onfido_capture_sdk_core_release = getNfcInteractor$onfido_capture_sdk_core_release();
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
        nfcInteractor$onfido_capture_sdk_core_release.enableReaderMode(fragmentActivityRequireActivity, new Function1<NfcTagDelegate, Unit>() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanFragment.startEnableReaderMode.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(NfcTagDelegate nfcTagDelegate) {
                invoke2(nfcTagDelegate);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(NfcTagDelegate tag) {
                Intrinsics.checkNotNullParameter(tag, "tag");
                NfcScanFragment.this.getPresenter().onNfcTagDetected(tag, NfcScanFragment.this.getNfcViewModel().getCanNumber());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startScanning() {
        if (!getNfcViewModel().canNFCScanStart()) {
            getNfcViewModel().resolveNFCArguments();
            return;
        }
        getPresenter().onNfcScanClicked();
        if (this.readerModeStarted) {
            return;
        }
        startEnableReaderMode();
    }

    public final AnnouncementService getAnnouncementService$onfido_capture_sdk_core_release() {
        AnnouncementService announcementService = this.announcementService;
        if (announcementService != null) {
            return announcementService;
        }
        Intrinsics.throwUninitializedPropertyAccessException("announcementService");
        return null;
    }

    public final DispatchersProvider getDispatchersProvider$onfido_capture_sdk_core_release() {
        DispatchersProvider dispatchersProvider = this.dispatchersProvider;
        if (dispatchersProvider != null) {
            return dispatchersProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dispatchersProvider");
        return null;
    }

    public final HapticFeedback getHapticFeedback$onfido_capture_sdk_core_release() {
        HapticFeedback hapticFeedback = this.hapticFeedback;
        if (hapticFeedback != null) {
            return hapticFeedback;
        }
        Intrinsics.throwUninitializedPropertyAccessException("hapticFeedback");
        return null;
    }

    public final NfcInteractor getNfcInteractor$onfido_capture_sdk_core_release() {
        NfcInteractor nfcInteractor = this.nfcInteractor;
        if (nfcInteractor != null) {
            return nfcInteractor;
        }
        Intrinsics.throwUninitializedPropertyAccessException("nfcInteractor");
        return null;
    }

    public final NfcScanPresenter.Factory getPresenterFactory$onfido_capture_sdk_core_release() {
        NfcScanPresenter.Factory factory = this.presenterFactory;
        if (factory != null) {
            return factory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("presenterFactory");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        getBinding().videoView.release();
        this._binding = null;
    }

    @Override // com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanBottomDialog.NfcScanBottomActions
    public void onNfcDialogDismissedByDialog() {
        getPresenter().nfcScanDialogDismissed();
        getNfcViewModel().onNfcScanFailed("UserCanceled");
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(fragmentActivityRequireActivity), null, null, new C07001(null), 3, null);
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getBinding().btnStartScanning.setEnabled(false);
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(fragmentActivityRequireActivity), null, null, new C07011(null), 3, null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() throws IllegalStateException {
        super.onStart();
        getNfcViewModel().onNfcIntro();
        observeNfcScanState();
        observeViewState();
        PlaybackControlsVideoPlayerView playbackControlsVideoPlayerView = getBinding().videoView;
        playbackControlsVideoPlayerView.restartVideo();
        playbackControlsVideoPlayerView.resume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() throws IllegalStateException {
        super.onStop();
        getBinding().videoView.pause();
        getNfcScanBottomDialog().dismissDialog();
        this.compositeDisposable.clear();
        getPresenter().onClean();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) throws IllegalStateException, Resources.NotFoundException, IOException, SecurityException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter(view, "view");
        SdkController companion = SdkController.INSTANCE.getInstance();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        SdkController.getSdkComponent$default(companion, contextRequireContext, null, 2, null).inject$onfido_capture_sdk_core_release(this);
        super.onViewCreated(view, savedInstanceState);
        this._binding = OnfidoFragmentNfcScanBinding.bind(view);
        PlaybackControlsVideoPlayerView playbackControlsVideoPlayerView = getBinding().videoView;
        Intrinsics.checkNotNull(playbackControlsVideoPlayerView);
        ViewExtensionsKt.toVisible$default(playbackControlsVideoPlayerView, false, 1, null);
        playbackControlsVideoPlayerView.setLoading(false);
        playbackControlsVideoPlayerView.setVideoUrl(getPresenter().getInstructionVideoPath(true));
        setInstructions();
        TextView tvCanDisclaimer = getBinding().tvCanDisclaimer;
        Intrinsics.checkNotNullExpressionValue(tvCanDisclaimer, "tvCanDisclaimer");
        tvCanDisclaimer.setVisibility(getPresenter().getCanDisclaimerVisible() ? 0 : 8);
    }

    public final void setAnnouncementService$onfido_capture_sdk_core_release(AnnouncementService announcementService) {
        Intrinsics.checkNotNullParameter(announcementService, "<set-?>");
        this.announcementService = announcementService;
    }

    public final void setDispatchersProvider$onfido_capture_sdk_core_release(DispatchersProvider dispatchersProvider) {
        Intrinsics.checkNotNullParameter(dispatchersProvider, "<set-?>");
        this.dispatchersProvider = dispatchersProvider;
    }

    public final void setHapticFeedback$onfido_capture_sdk_core_release(HapticFeedback hapticFeedback) {
        Intrinsics.checkNotNullParameter(hapticFeedback, "<set-?>");
        this.hapticFeedback = hapticFeedback;
    }

    public final void setNfcInteractor$onfido_capture_sdk_core_release(NfcInteractor nfcInteractor) {
        Intrinsics.checkNotNullParameter(nfcInteractor, "<set-?>");
        this.nfcInteractor = nfcInteractor;
    }

    public final void setPresenterFactory$onfido_capture_sdk_core_release(NfcScanPresenter.Factory factory) {
        Intrinsics.checkNotNullParameter(factory, "<set-?>");
        this.presenterFactory = factory;
    }
}
