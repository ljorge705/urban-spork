package com.onfido.android.sdk.capture.internal.nfc;

import android.app.Activity;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType;
import com.onfido.android.sdk.capture.internal.nfc.OnfidoVerificationType;
import com.onfido.android.sdk.capture.internal.nfc.data.NfcPassportExtraction;
import com.onfido.android.sdk.capture.internal.nfc.data.PassportBACKey;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.nfc.MRTDAccessControl;
import com.onfido.android.sdk.capture.nfc.MRTDData;
import com.onfido.android.sdk.capture.nfc.MRTDDataGroup;
import com.onfido.android.sdk.capture.nfc.MRTDDocumentInfo;
import com.onfido.android.sdk.capture.nfc.MRTDReader;
import com.onfido.android.sdk.capture.nfc.MRTDReaderDelegate;
import com.onfido.android.sdk.capture.nfc.VerificationType;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanPresenter;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanState;
import com.onfido.android.sdk.capture.ui.nfc.scan.NotStarted;
import com.onfido.android.sdk.capture.ui.nfc.scan.ScanCompleted;
import com.onfido.android.sdk.capture.ui.nfc.scan.ScanFailed;
import com.onfido.android.sdk.capture.ui.nfc.scan.ScanReady;
import com.onfido.android.sdk.capture.ui.nfc.scan.ScanRetry;
import com.onfido.android.sdk.capture.ui.nfc.scan.Scanned;
import com.onfido.android.sdk.capture.ui.nfc.scan.Scanning;
import com.onfido.android.sdk.capture.ui.nfc.scan.ScanningTimeout;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\u0017H\u0016J\u0006\u0010\"\u001a\u00020\u0000J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0019H\u0002J\u0010\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0002J\u0010\u0010*\u001a\u00020\u00192\u0006\u0010+\u001a\u00020$H\u0002J\u0010\u0010,\u001a\u00020-2\u0006\u0010%\u001a\u00020.H\u0002J\b\u0010/\u001a\u00020\u0017H\u0002JQ\u00100\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020 2\u0006\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010$2\u000e\u00104\u001a\n\u0012\u0004\u0012\u000206\u0018\u0001052\b\u00107\u001a\u0004\u0018\u0001082\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\u0015H\u0016¢\u0006\u0002\u0010<J\b\u0010=\u001a\u00020\u0017H\u0002J\u0018\u0010>\u001a\u00020\u00172\u0006\u0010%\u001a\u00020.2\u0006\u0010?\u001a\u00020\u001bH\u0016J\u001a\u0010@\u001a\u00020\u00172\u0006\u0010%\u001a\u00020.2\b\u0010A\u001a\u0004\u0018\u000108H\u0016J\u0010\u0010B\u001a\u00020\u00172\u0006\u0010%\u001a\u00020.H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u00020\u00078\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u00020\u000f8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/NfcService;", "Lcom/onfido/android/sdk/capture/nfc/MRTDReader;", "Lcom/onfido/android/sdk/capture/internal/nfc/RealtimeNfcEvents;", "()V", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "nfcInteractor", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;", "getNfcInteractor$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;", "setNfcInteractor$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;)V", "nfcScanPresenter", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanPresenter;", "presenterFactory", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanPresenter$Factory;", "getPresenterFactory$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanPresenter$Factory;", "setPresenterFactory$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanPresenter$Factory;)V", "readerDelegate", "Lcom/onfido/android/sdk/capture/nfc/MRTDReaderDelegate;", "accessControlFailed", "", KeychainModule.Maps.ACCESS_CONTROL, "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "throwable", "", "accessControlFinished", "accessControlStarted", "cancelSession", "activity", "Landroid/app/Activity;", "documentDetected", "getService", "mapAccessControlType", "Lcom/onfido/android/sdk/capture/nfc/MRTDAccessControl;", "type", "mapNfcData", "Lcom/onfido/android/sdk/capture/nfc/MRTDData;", "internalNfcData", "Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;", "mapNfcFlowType", "flowType", "mapVerificationType", "Lcom/onfido/android/sdk/capture/nfc/VerificationType;", "Lcom/onfido/android/sdk/capture/internal/nfc/OnfidoVerificationType;", "observeNfcScanState", "read", "documentInfo", "Lcom/onfido/android/sdk/capture/nfc/MRTDDocumentInfo;", "preferredAccessControl", "dataGroups", "", "Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;", "activeAuthenticationChallenge", "", "chipAuthentication", "", "delegate", "(Landroid/app/Activity;Lcom/onfido/android/sdk/capture/nfc/MRTDDocumentInfo;Lcom/onfido/android/sdk/capture/nfc/MRTDAccessControl;[Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;[BZLcom/onfido/android/sdk/capture/nfc/MRTDReaderDelegate;)V", "startChipScanning", "verificationFailed", "error", "verificationFinished", OnfidoLauncher.KEY_RESULT, "verificationStarted", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcService implements MRTDReader, RealtimeNfcEvents {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public NfcInteractor nfcInteractor;
    private NfcScanPresenter nfcScanPresenter;

    @Inject
    public NfcScanPresenter.Factory presenterFactory;
    private MRTDReaderDelegate readerDelegate;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[MRTDAccessControl.values().length];
            try {
                iArr[MRTDAccessControl.BAC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MRTDAccessControl.PACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[NfcFlowType.values().length];
            try {
                iArr2[NfcFlowType.PACE.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[NfcFlowType.BAC.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private final MRTDAccessControl mapAccessControlType(NfcFlowType type) {
        int i = WhenMappings.$EnumSwitchMapping$1[type.ordinal()];
        if (i == 1) {
            return MRTDAccessControl.PACE;
        }
        if (i == 2) {
            return MRTDAccessControl.BAC;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MRTDData mapNfcData(NfcPassportExtraction internalNfcData) {
        return new MRTDData(internalNfcData.getDg1(), internalNfcData.getDg2(), internalNfcData.getDg3(), internalNfcData.getDg4(), internalNfcData.getDg5(), internalNfcData.getDg6(), internalNfcData.getDg7(), internalNfcData.getDg8(), internalNfcData.getDg9(), internalNfcData.getDg10(), internalNfcData.getDg11(), internalNfcData.getDg12(), internalNfcData.getDg13(), internalNfcData.getDg14(), internalNfcData.getDg15(), internalNfcData.getDg16(), internalNfcData.getCom(), internalNfcData.getSod(), internalNfcData.getAaResponse());
    }

    private final NfcFlowType mapNfcFlowType(MRTDAccessControl flowType) {
        int i = WhenMappings.$EnumSwitchMapping$0[flowType.ordinal()];
        if (i == 1) {
            return NfcFlowType.BAC;
        }
        if (i == 2) {
            return NfcFlowType.PACE;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final VerificationType mapVerificationType(OnfidoVerificationType type) {
        if (Intrinsics.areEqual(type, OnfidoVerificationType.ActiveAuthentication.INSTANCE)) {
            return VerificationType.ActiveAuthentication.INSTANCE;
        }
        if (Intrinsics.areEqual(type, OnfidoVerificationType.ChipAuthentication.INSTANCE)) {
            return VerificationType.ChipAuthentication.INSTANCE;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final void observeNfcScanState() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        NfcScanPresenter nfcScanPresenter = this.nfcScanPresenter;
        if (nfcScanPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nfcScanPresenter");
            nfcScanPresenter = null;
        }
        Disposable disposableSubscribe = nfcScanPresenter.getScanState().subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.internal.nfc.NfcService.observeNfcScanState.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(NfcScanState state) {
                MRTDReaderDelegate mRTDReaderDelegate;
                Throwable th;
                Intrinsics.checkNotNullParameter(state, "state");
                if (state instanceof Scanning) {
                    MRTDReaderDelegate mRTDReaderDelegate2 = NfcService.this.readerDelegate;
                    if (mRTDReaderDelegate2 != null) {
                        mRTDReaderDelegate2.readProgress(((Scanning) state).getProgress());
                        return;
                    }
                    return;
                }
                if (state instanceof ScanCompleted) {
                    MRTDReaderDelegate mRTDReaderDelegate3 = NfcService.this.readerDelegate;
                    if (mRTDReaderDelegate3 != null) {
                        mRTDReaderDelegate3.readFinished(NfcService.this.mapNfcData(((ScanCompleted) state).getNfcData()));
                        return;
                    }
                    return;
                }
                if (state instanceof com.onfido.android.sdk.capture.ui.nfc.scan.ConnectionLost) {
                    MRTDReaderDelegate mRTDReaderDelegate4 = NfcService.this.readerDelegate;
                    if (mRTDReaderDelegate4 != null) {
                        mRTDReaderDelegate4.documentLost();
                        return;
                    }
                    return;
                }
                if (Intrinsics.areEqual(state, ScanningTimeout.INSTANCE)) {
                    mRTDReaderDelegate = NfcService.this.readerDelegate;
                    if (mRTDReaderDelegate == null) {
                        return;
                    } else {
                        th = new Throwable("ScanningTimeout");
                    }
                } else {
                    if (!(state instanceof ScanFailed)) {
                        if (Intrinsics.areEqual(state, NotStarted.INSTANCE) || Intrinsics.areEqual(state, ScanRetry.INSTANCE) || (state instanceof Scanned)) {
                            return;
                        }
                        Intrinsics.areEqual(state, ScanReady.INSTANCE);
                        return;
                    }
                    mRTDReaderDelegate = NfcService.this.readerDelegate;
                    if (mRTDReaderDelegate == null) {
                        return;
                    } else {
                        th = new Throwable("ScanFailed");
                    }
                }
                mRTDReaderDelegate.readFailed(th);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void startChipScanning() {
        NfcScanPresenter nfcScanPresenter = this.nfcScanPresenter;
        if (nfcScanPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nfcScanPresenter");
            nfcScanPresenter = null;
        }
        nfcScanPresenter.onNfcScanClicked();
        MRTDReaderDelegate mRTDReaderDelegate = this.readerDelegate;
        if (mRTDReaderDelegate != null) {
            mRTDReaderDelegate.readStarted();
        }
    }

    @Override // com.onfido.android.sdk.capture.internal.nfc.RealtimeNfcEvents
    public void accessControlFailed(NfcFlowType accessControl, Throwable throwable) {
        Intrinsics.checkNotNullParameter(accessControl, "accessControl");
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        MRTDAccessControl mRTDAccessControlMapAccessControlType = mapAccessControlType(accessControl);
        MRTDReaderDelegate mRTDReaderDelegate = this.readerDelegate;
        if (mRTDReaderDelegate != null) {
            mRTDReaderDelegate.accessControlFailed(mRTDAccessControlMapAccessControlType, throwable);
        }
    }

    @Override // com.onfido.android.sdk.capture.internal.nfc.RealtimeNfcEvents
    public void accessControlFinished(NfcFlowType accessControl) {
        Intrinsics.checkNotNullParameter(accessControl, "accessControl");
        MRTDAccessControl mRTDAccessControlMapAccessControlType = mapAccessControlType(accessControl);
        MRTDReaderDelegate mRTDReaderDelegate = this.readerDelegate;
        if (mRTDReaderDelegate != null) {
            mRTDReaderDelegate.accessControlFinished(mRTDAccessControlMapAccessControlType);
        }
    }

    @Override // com.onfido.android.sdk.capture.internal.nfc.RealtimeNfcEvents
    public void accessControlStarted(NfcFlowType accessControl) {
        Intrinsics.checkNotNullParameter(accessControl, "accessControl");
        MRTDAccessControl mRTDAccessControlMapAccessControlType = mapAccessControlType(accessControl);
        MRTDReaderDelegate mRTDReaderDelegate = this.readerDelegate;
        if (mRTDReaderDelegate != null) {
            mRTDReaderDelegate.accessControlStarted(mRTDAccessControlMapAccessControlType);
        }
    }

    @Override // com.onfido.android.sdk.capture.nfc.MRTDReader
    public void cancelSession(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        getNfcInteractor$onfido_capture_sdk_core_release().disableReaderMode(activity);
        this.readerDelegate = null;
    }

    @Override // com.onfido.android.sdk.capture.internal.nfc.RealtimeNfcEvents
    public void documentDetected() {
        MRTDReaderDelegate mRTDReaderDelegate = this.readerDelegate;
        if (mRTDReaderDelegate != null) {
            mRTDReaderDelegate.documentDetected();
        }
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

    public final NfcService getService() {
        return this;
    }

    @Override // com.onfido.android.sdk.capture.nfc.MRTDReader
    public void read(Activity activity, MRTDDocumentInfo documentInfo, MRTDAccessControl preferredAccessControl, MRTDDataGroup[] dataGroups, byte[] activeAuthenticationChallenge, boolean chipAuthentication, MRTDReaderDelegate delegate) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(documentInfo, "documentInfo");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.readerDelegate = delegate;
        try {
            SdkController.INSTANCE.getInstance().getUILessSdkComponent(activity, OnfidoConfig.Builder.withSDKToken$default(new OnfidoConfig.Builder(activity), "demo", null, 2, null).build()).inject$onfido_capture_sdk_core_release(this);
            String countryCode = documentInfo.getCountryCode();
            this.nfcScanPresenter = getPresenterFactory$onfido_capture_sdk_core_release().create(DocumentType.GENERIC, CountryCode.INSTANCE.fromAlpha3(countryCode), mapNfcFlowType(preferredAccessControl == null ? MRTDAccessControl.PACE : preferredAccessControl), new PassportBACKey(documentInfo.getDocumentNumber(), documentInfo.getBirthDate(), documentInfo.getExpiryDate()), activeAuthenticationChallenge, chipAuthentication, dataGroups == null ? MRTDDataGroup.INSTANCE.all() : dataGroups, this);
            observeNfcScanState();
            getNfcInteractor$onfido_capture_sdk_core_release().enableReaderMode(activity, new Function1<NfcTagDelegate, Unit>() { // from class: com.onfido.android.sdk.capture.internal.nfc.NfcService.read.1
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
                    Timber.INSTANCE.d("DEBUG", "NfcService enableReaderMode tag=" + tag);
                    NfcScanPresenter nfcScanPresenter = NfcService.this.nfcScanPresenter;
                    if (nfcScanPresenter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("nfcScanPresenter");
                        nfcScanPresenter = null;
                    }
                    nfcScanPresenter.onNfcTagDetected(tag, null);
                }
            });
            startChipScanning();
        } catch (Exception e) {
            Timber.INSTANCE.e(e, "initWithActivity onError", new Object[0]);
            delegate.readFailed(e);
        }
    }

    public final void setNfcInteractor$onfido_capture_sdk_core_release(NfcInteractor nfcInteractor) {
        Intrinsics.checkNotNullParameter(nfcInteractor, "<set-?>");
        this.nfcInteractor = nfcInteractor;
    }

    public final void setPresenterFactory$onfido_capture_sdk_core_release(NfcScanPresenter.Factory factory) {
        Intrinsics.checkNotNullParameter(factory, "<set-?>");
        this.presenterFactory = factory;
    }

    @Override // com.onfido.android.sdk.capture.internal.nfc.RealtimeNfcEvents
    public void verificationFailed(OnfidoVerificationType type, Throwable error) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(error, "error");
        VerificationType verificationTypeMapVerificationType = mapVerificationType(type);
        MRTDReaderDelegate mRTDReaderDelegate = this.readerDelegate;
        if (mRTDReaderDelegate != null) {
            mRTDReaderDelegate.verificationFailed(verificationTypeMapVerificationType, error);
        }
    }

    @Override // com.onfido.android.sdk.capture.internal.nfc.RealtimeNfcEvents
    public void verificationFinished(OnfidoVerificationType type, byte[] result) {
        Intrinsics.checkNotNullParameter(type, "type");
        VerificationType verificationTypeMapVerificationType = mapVerificationType(type);
        MRTDReaderDelegate mRTDReaderDelegate = this.readerDelegate;
        if (mRTDReaderDelegate != null) {
            mRTDReaderDelegate.verificationFinished(verificationTypeMapVerificationType, result);
        }
    }

    @Override // com.onfido.android.sdk.capture.internal.nfc.RealtimeNfcEvents
    public void verificationStarted(OnfidoVerificationType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        MRTDReaderDelegate mRTDReaderDelegate = this.readerDelegate;
        if (mRTDReaderDelegate != null) {
            mRTDReaderDelegate.verificationStarted(mapVerificationType(type));
        }
    }
}
