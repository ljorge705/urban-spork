package com.onfido.android.sdk.capture.ui.nfc.scan;

import android.content.SharedPreferences;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.BuildConfig;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.android.sdk.capture.common.preferences.StorageKey;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.OnfidoAnimWebView;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.nfc.Error;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.internal.nfc.NfcReadState;
import com.onfido.android.sdk.capture.internal.nfc.NfcTagDelegate;
import com.onfido.android.sdk.capture.internal.nfc.NfcTimeouts;
import com.onfido.android.sdk.capture.internal.nfc.PassportAuthAccess;
import com.onfido.android.sdk.capture.internal.nfc.Reading;
import com.onfido.android.sdk.capture.internal.nfc.RealtimeNfcEvents;
import com.onfido.android.sdk.capture.internal.nfc.Retrying;
import com.onfido.android.sdk.capture.internal.nfc.Success;
import com.onfido.android.sdk.capture.internal.nfc.data.NfcPassportExtraction;
import com.onfido.android.sdk.capture.internal.nfc.data.PassportBACKey;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.nfc.MRTDDataGroup;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConstants;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.dagger.assisted.Assisted;
import com.onfido.dagger.assisted.AssistedFactory;
import com.onfido.dagger.assisted.AssistedInject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000Ü\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 [2\u00020\u0001:\u0003Z[\\B\u0093\u0001\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\r\u0012\u000e\b\u0001\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u0012\u0006\u0010\u001b\u001a\u00020\u001c\u0012\u0006\u0010\u001d\u001a\u00020\u001e¢\u0006\u0002\u0010\u001fJ\u000e\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\rJ\u0006\u0010C\u001a\u00020\rJ\u0010\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u000206H\u0002J\u0006\u0010G\u001a\u00020EJ\u0006\u0010H\u001a\u00020EJ\u0006\u0010I\u001a\u00020EJ\u0018\u0010J\u001a\u00020E2\u0006\u0010K\u001a\u00020-2\b\u0010L\u001a\u0004\u0018\u00010MJ\u0010\u0010N\u001a\u00020E2\u0006\u0010O\u001a\u00020PH\u0002J\u0010\u0010Q\u001a\u00020E2\u0006\u0010F\u001a\u000206H\u0002J\u0012\u0010R\u001a\u00020\r2\b\u0010S\u001a\u0004\u0018\u00010TH\u0002J\u0018\u0010U\u001a\u00020E2\u0006\u0010V\u001a\u00020W2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010X\u001a\u00020EH\u0002J\b\u0010Y\u001a\u00020EH\u0002R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\"\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010%R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010+\u001a\u0015\u0012\f\u0012\n .*\u0004\u0018\u00010-0-0,¢\u0006\u0002\b/X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\u00020\u001eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0017\u00104\u001a\b\u0012\u0004\u0012\u00020605¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0014\u00109\u001a\b\u0012\u0004\u0012\u0002060:X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010;\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010=\u001a\b\u0012\u0004\u0012\u00020>05¢\u0006\b\n\u0000\u001a\u0004\b?\u00108¨\u0006]"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanPresenter;", "", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "passportBACKey", "Lcom/onfido/android/sdk/capture/internal/nfc/data/PassportBACKey;", "aaChallenge", "", "chipAuthentication", "", "fileIDs", "", "Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;", "realtimeNfcEvents", "Lcom/onfido/android/sdk/capture/internal/nfc/RealtimeNfcEvents;", "nfcInteractor", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "nfcTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;", "nfcTimeouts", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcTimeouts;", KeychainModule.Maps.STORAGE, "Lcom/onfido/android/sdk/capture/common/preferences/SharedPreferencesDataSource;", "remoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/utils/CountryCode;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;Lcom/onfido/android/sdk/capture/internal/nfc/data/PassportBACKey;[BZ[Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;Lcom/onfido/android/sdk/capture/internal/nfc/RealtimeNfcEvents;Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;Lcom/onfido/android/sdk/capture/internal/nfc/NfcTimeouts;Lcom/onfido/android/sdk/capture/common/preferences/SharedPreferencesDataSource;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "allDisposables", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "canDisclaimerVisible", "getCanDisclaimerVisible", "()Z", "[Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;", "nfcInstructions", "", "", "getNfcInstructions", "()Ljava/util/List;", "nfcTagRetrySubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcTagDelegate;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "readDisposable", "Lio/reactivex/rxjava3/disposables/Disposable;", "getRemoteConfig$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "scanState", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanState;", "getScanState", "()Lio/reactivex/rxjava3/core/Observable;", "scanStateSubject", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "scanningTimeoutDisposable", "shouldTryPace", "viewState", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanViewState;", "getViewState", "getInstructionVideoPath", "", "isIntro", "isDarkModeEnabled", "logScanningState", "", "nfcScanState", "nfcScanDialogDismissed", "onClean", "onNfcScanClicked", "onNfcTagDetected", "tag", "canNumber", "", "onNfcTagRead", "successReadState", "Lcom/onfido/android/sdk/capture/internal/nfc/Success;", "setScanningStateTo", "shouldSkipPace", Constants.AUTH, "Lcom/onfido/android/sdk/capture/internal/nfc/PassportAuthAccess;", "startNfcScanSuccessTimeout", "nfcData", "Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;", "startNfcScanTimeout", "stopScanningTimeoutTimer", "AnimationUrl", "Companion", "Factory", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcScanPresenter {
    private static final String NFC_LOGGER = "NFC Logger";
    private final byte[] aaChallenge;
    private final CompositeDisposable allDisposables;
    private final boolean canDisclaimerVisible;
    private final boolean chipAuthentication;
    private final CountryCode countryCode;
    private final DocumentType documentType;
    private final MRTDDataGroup[] fileIDs;
    private final NfcFlowType nfcFlowType;
    private final List<Integer> nfcInstructions;
    private final NfcInteractor nfcInteractor;
    private final PublishSubject<NfcTagDelegate> nfcTagRetrySubject;
    private final NfcTimeouts nfcTimeouts;
    private final NfcTracker nfcTracker;
    private final PassportBACKey passportBACKey;
    private Disposable readDisposable;
    private final RealtimeNfcEvents realtimeNfcEvents;
    private final OnfidoRemoteConfig remoteConfig;
    private final Observable<NfcScanState> scanState;
    private final BehaviorSubject<NfcScanState> scanStateSubject;
    private Disposable scanningTimeoutDisposable;
    private final SchedulersProvider schedulersProvider;
    private boolean shouldTryPace;
    private final SharedPreferencesDataSource storage;
    private final Observable<NfcScanViewState> viewState;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanPresenter$AnimationUrl;", "", "()V", "BASE_URL", "", "CARD_DARK", "CARD_LIGHT", "PASSPORT_DARK", "PASSPORT_LIGHT", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class AnimationUrl {
        private static final String BASE_URL = "https://assets.onfido.com/mobile-sdk-assets/android/v3/nfc/";
        public static final String CARD_DARK = "https://assets.onfido.com/mobile-sdk-assets/android/v3/nfc/nfc_card_intro_dark.mp4";
        public static final String CARD_LIGHT = "https://assets.onfido.com/mobile-sdk-assets/android/v3/nfc/nfc_card_intro.mp4";
        public static final AnimationUrl INSTANCE = new AnimationUrl();
        public static final String PASSPORT_DARK = "https://assets.onfido.com/mobile-sdk-assets/android/v3/nfc/android-passport-dark.mp4";
        public static final String PASSPORT_LIGHT = "https://assets.onfido.com/mobile-sdk-assets/android/v3/nfc/android-passport-light.mp4";

        private AnimationUrl() {
        }
    }

    @AssistedFactory
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001JY\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H&¢\u0006\u0002\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanPresenter$Factory;", "", "create", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanPresenter;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "passportBACKey", "Lcom/onfido/android/sdk/capture/internal/nfc/data/PassportBACKey;", "aaChallenge", "", "chipAuthentication", "", "fileIDs", "", "Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;", "realtimeNfcEvents", "Lcom/onfido/android/sdk/capture/internal/nfc/RealtimeNfcEvents;", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/utils/CountryCode;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;Lcom/onfido/android/sdk/capture/internal/nfc/data/PassportBACKey;[BZ[Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;Lcom/onfido/android/sdk/capture/internal/nfc/RealtimeNfcEvents;)Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcScanPresenter;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        NfcScanPresenter create(DocumentType documentType, CountryCode countryCode, NfcFlowType nfcFlowType, PassportBACKey passportBACKey, byte[] aaChallenge, boolean chipAuthentication, MRTDDataGroup[] fileIDs, RealtimeNfcEvents realtimeNfcEvents);
    }

    @AssistedInject
    public NfcScanPresenter(@Assisted DocumentType documentType, @Assisted CountryCode countryCode, @Assisted NfcFlowType nfcFlowType, @Assisted PassportBACKey passportBACKey, @Assisted byte[] bArr, @Assisted boolean z, @Assisted MRTDDataGroup[] fileIDs, @Assisted RealtimeNfcEvents realtimeNfcEvents, NfcInteractor nfcInteractor, SchedulersProvider schedulersProvider, NfcTracker nfcTracker, NfcTimeouts nfcTimeouts, SharedPreferencesDataSource storage, OnfidoRemoteConfig remoteConfig) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        Intrinsics.checkNotNullParameter(passportBACKey, "passportBACKey");
        Intrinsics.checkNotNullParameter(fileIDs, "fileIDs");
        Intrinsics.checkNotNullParameter(nfcInteractor, "nfcInteractor");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(nfcTracker, "nfcTracker");
        Intrinsics.checkNotNullParameter(nfcTimeouts, "nfcTimeouts");
        Intrinsics.checkNotNullParameter(storage, "storage");
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        this.documentType = documentType;
        this.countryCode = countryCode;
        this.nfcFlowType = nfcFlowType;
        this.passportBACKey = passportBACKey;
        this.aaChallenge = bArr;
        this.chipAuthentication = z;
        this.fileIDs = fileIDs;
        this.realtimeNfcEvents = realtimeNfcEvents;
        this.nfcInteractor = nfcInteractor;
        this.schedulersProvider = schedulersProvider;
        this.nfcTracker = nfcTracker;
        this.nfcTimeouts = nfcTimeouts;
        this.storage = storage;
        this.remoteConfig = remoteConfig;
        this.allDisposables = new CompositeDisposable();
        BehaviorSubject<NfcScanState> behaviorSubjectCreateDefault = BehaviorSubject.createDefault(NotStarted.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault, "createDefault(...)");
        this.scanStateSubject = behaviorSubjectCreateDefault;
        PublishSubject<NfcTagDelegate> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create(...)");
        this.nfcTagRetrySubject = publishSubjectCreate;
        this.shouldTryPace = true;
        Observable<NfcScanState> observableObserveOn = behaviorSubjectCreateDefault.hide().distinctUntilChanged().observeOn(schedulersProvider.getUi());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "observeOn(...)");
        this.scanState = observableObserveOn;
        this.nfcInstructions = documentType == DocumentType.PASSPORT ? CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.string.onfido_nfc_capture_scan_intro_passport_scan_guide_1), Integer.valueOf(R.string.onfido_nfc_capture_scan_intro_passport_scan_guide_android_2), Integer.valueOf(R.string.onfido_nfc_capture_scan_intro_passport_scan_guide_android_3)}) : CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.string.onfido_nfc_scan_welcome_card_list_item), Integer.valueOf(R.string.onfido_nfc_scan_welcome_card_list_item_2), Integer.valueOf(R.string.onfido_nfc_scan_welcome_card_list_item_3)});
        this.canDisclaimerVisible = documentType == DocumentType.NATIONAL_IDENTITY_CARD;
        Observable<NfcScanViewState> observableFromCallable = Observable.fromCallable(new Callable() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanPresenter$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return NfcScanPresenter.viewState$lambda$0(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFromCallable, "fromCallable(...)");
        this.viewState = observableFromCallable;
    }

    private final void logScanningState(NfcScanState nfcScanState) {
        if (nfcScanState instanceof ScanningTimeout) {
            Timber.INSTANCE.e("NFC Logger - Scan FAIL - Timeout", new Object[0]);
        } else if (nfcScanState instanceof ScanFailed) {
            Timber.INSTANCE.e("NFC Logger - Scan FAIL - " + ((ScanFailed) nfcScanState).getMessage(), new Object[0]);
        } else {
            Timber.INSTANCE.i("NFC Logger - Scan state: " + nfcScanState.getClass().getSimpleName(), new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01bf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onNfcTagRead(com.onfido.android.sdk.capture.internal.nfc.Success r8) {
        /*
            Method dump skipped, instructions count: 482
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanPresenter.onNfcTagRead(com.onfido.android.sdk.capture.internal.nfc.Success):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setScanningStateTo(NfcScanState nfcScanState) {
        logScanningState(nfcScanState);
        this.scanStateSubject.onNext(nfcScanState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldSkipPace(PassportAuthAccess auth) {
        return ((auth != null ? auth.getSelectAppletException() : null) == null || auth.getPaceException() == null) ? false : true;
    }

    private final void startNfcScanSuccessTimeout(final NfcPassportExtraction nfcData, final NfcFlowType nfcFlowType) {
        CompositeDisposable compositeDisposable = this.allDisposables;
        Disposable disposableSubscribe = Observable.timer(this.nfcTimeouts.successScanTimeoutMs(), TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanPresenter.startNfcScanSuccessTimeout.1
            public final void accept(long j) {
                NfcScanPresenter.this.setScanningStateTo(new ScanCompleted(nfcData, nfcFlowType));
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Object obj) {
                accept(((Number) obj).longValue());
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanPresenter.startNfcScanSuccessTimeout.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                NfcScanPresenter nfcScanPresenter = NfcScanPresenter.this;
                String message = it.getMessage();
                if (message == null) {
                    message = "";
                }
                nfcScanPresenter.setScanningStateTo(new ScanFailed(message));
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startNfcScanTimeout() {
        Disposable disposableSubscribe = Observable.timer(this.nfcTimeouts.nfcScanTimeoutMs(), TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanPresenter.startNfcScanTimeout.1
            public final void accept(long j) {
                NfcScanPresenter.this.setScanningStateTo(ScanningTimeout.INSTANCE);
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Object obj) {
                accept(((Number) obj).longValue());
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanPresenter.startNfcScanTimeout.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                NfcScanPresenter nfcScanPresenter = NfcScanPresenter.this;
                String message = it.getMessage();
                if (message == null) {
                    message = "";
                }
                nfcScanPresenter.setScanningStateTo(new ScanFailed(message));
            }
        });
        CompositeDisposable compositeDisposable = this.allDisposables;
        Intrinsics.checkNotNull(disposableSubscribe);
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
        this.scanningTimeoutDisposable = disposableSubscribe;
    }

    private final void stopScanningTimeoutTimer() {
        Disposable disposable = this.scanningTimeoutDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NfcScanViewState viewState$lambda$0(NfcScanPresenter this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DocumentType documentType = this$0.documentType;
        DocumentType documentType2 = DocumentType.PASSPORT;
        return new NfcScanViewState(documentType == documentType2 ? R.string.onfido_nfc_intro_title_passport : R.string.onfido_nfc_scan_welcome_card_title, R.string.onfido_nfc_intro_button_primary, documentType == documentType2 ? R.string.onfido_nfc_scan_welcome_passport_secondary_button : R.string.onfido_nfc_scan_welcome_card_secondary_button, false);
    }

    public final boolean getCanDisclaimerVisible() {
        return this.canDisclaimerVisible;
    }

    public final String getInstructionVideoPath(boolean isIntro) {
        String str = (this.documentType == DocumentType.PASSPORT ? "passport" : "card") + '-' + (isIntro ? "intro" : "scan") + '-' + (isDarkModeEnabled() ? OnfidoAnimWebView.THEME_DARK : OnfidoAnimWebView.THEME_LIGHT) + LivenessConstants.VIDEO_RECORDING_FILE_FORMAT;
        String animationVersion = this.remoteConfig.getDocumentCapture().getNfc().getAnimationVersion();
        StringBuilder sb = new StringBuilder();
        String str2 = String.format(BuildConfig.NFC_ASSERT_PATH, Arrays.copyOf(new Object[]{animationVersion}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
        return sb.append(str2).append(str).toString();
    }

    public final List<Integer> getNfcInstructions() {
        return this.nfcInstructions;
    }

    /* renamed from: getRemoteConfig$onfido_capture_sdk_core_release, reason: from getter */
    public final OnfidoRemoteConfig getRemoteConfig() {
        return this.remoteConfig;
    }

    public final Observable<NfcScanState> getScanState() {
        return this.scanState;
    }

    public final Observable<NfcScanViewState> getViewState() {
        return this.viewState;
    }

    public final boolean isDarkModeEnabled() {
        Boolean boolValueOf;
        Object locale;
        SharedPreferencesDataSource sharedPreferencesDataSource = this.storage;
        StorageKey storageKey = StorageKey.IS_IN_DARK_MODE;
        SharedPreferences prefs$onfido_capture_sdk_core_release = sharedPreferencesDataSource.getPrefs$onfido_capture_sdk_core_release();
        Intrinsics.checkNotNullExpressionValue(prefs$onfido_capture_sdk_core_release, "<get-prefs>(...)");
        String strName = storageKey.name();
        if (prefs$onfido_capture_sdk_core_release.contains(strName)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Boolean.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                locale = prefs$onfido_capture_sdk_core_release.getString(strName, "");
                if (locale == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                locale = Integer.valueOf(prefs$onfido_capture_sdk_core_release.getInt(strName, -1));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                boolValueOf = Boolean.valueOf(prefs$onfido_capture_sdk_core_release.getBoolean(strName, false));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Float.TYPE))) {
                locale = Float.valueOf(prefs$onfido_capture_sdk_core_release.getFloat(strName, -1.0f));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                locale = Long.valueOf(prefs$onfido_capture_sdk_core_release.getLong(strName, -1L));
            } else {
                if (!Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Locale.class))) {
                    throw SharedPreferencesDataSource.Companion.getUNSUPPORTED_TYPE_EXCEPTION();
                }
                locale = sharedPreferencesDataSource.getLocale(prefs$onfido_capture_sdk_core_release, strName);
                if (locale == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            }
            boolValueOf = (Boolean) locale;
        } else {
            boolValueOf = null;
        }
        if (boolValueOf != null) {
            return boolValueOf.booleanValue();
        }
        return false;
    }

    public final void nfcScanDialogDismissed() {
        Timber.INSTANCE.i("NFC Logger - NFC Scan cancelled (dialog dismissed)", new Object[0]);
        setScanningStateTo(NotStarted.INSTANCE);
        this.allDisposables.clear();
    }

    public final void onClean() {
        setScanningStateTo(NotStarted.INSTANCE);
        this.allDisposables.clear();
    }

    public final void onNfcScanClicked() {
        this.nfcTracker.trackStartNfcScanSelected$onfido_capture_sdk_core_release(this.documentType, this.countryCode, this.nfcFlowType);
        setScanningStateTo(ScanReady.INSTANCE);
        startNfcScanTimeout();
    }

    public final void onNfcTagDetected(NfcTagDelegate tag, Number canNumber) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Timber.INSTANCE.i("NFC Logger - NFC Tag detected", new Object[0]);
        this.nfcTracker.trackNfcChipDiscovered$onfido_capture_sdk_core_release();
        if (this.scanStateSubject.getValue() instanceof ConnectionLost) {
            this.nfcTagRetrySubject.onNext(tag);
            return;
        }
        setScanningStateTo(ScanReady.INSTANCE);
        stopScanningTimeoutTimer();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        Disposable disposable = this.readDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Disposable disposableSubscribe = this.nfcInteractor.readNfcTag(this.nfcTagRetrySubject, tag, this.passportBACKey, this.aaChallenge, this.fileIDs, canNumber, this.shouldTryPace, this.nfcFlowType, this.chipAuthentication, this.realtimeNfcEvents).delaySubscription(this.nfcTimeouts.nfcScanTagDelayMs(), TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).doOnSubscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanPresenter.onNfcTagDetected.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                NfcScanPresenter.this.setScanningStateTo(new Scanning(0, false, 3, null));
            }
        }).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanPresenter.onNfcTagDetected.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(NfcReadState state) {
                Intrinsics.checkNotNullParameter(state, "state");
                if (state instanceof Success) {
                    NfcScanPresenter.this.onNfcTagRead((Success) state);
                    return;
                }
                if (state instanceof Error) {
                    NfcScanPresenter.this.setScanningStateTo(new ScanFailed(((Error) state).getMessage()));
                    NfcScanPresenter.this.shouldTryPace = !r0.shouldSkipPace(r5.getAuthAccess());
                    return;
                }
                if (state instanceof Retrying) {
                    NfcScanPresenter.this.setScanningStateTo(ScanRetry.INSTANCE);
                    NfcScanPresenter.this.startNfcScanTimeout();
                } else if (state instanceof Reading) {
                    NfcScanPresenter.this.setScanningStateTo(new Scanning(((Reading) state).getProgress(), booleanRef.element));
                    booleanRef.element = false;
                } else if (state instanceof com.onfido.android.sdk.capture.internal.nfc.ConnectionLost) {
                    booleanRef.element = true;
                    NfcScanPresenter.this.setScanningStateTo(ConnectionLost.INSTANCE);
                    NfcScanPresenter.this.nfcTracker.trackNfcChipConnectionLost$onfido_capture_sdk_core_release();
                }
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanPresenter.onNfcTagDetected.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                NfcScanPresenter nfcScanPresenter = NfcScanPresenter.this;
                String message = it.getMessage();
                if (message == null) {
                    message = "";
                }
                nfcScanPresenter.setScanningStateTo(new ScanFailed(message));
            }
        });
        CompositeDisposable compositeDisposable = this.allDisposables;
        Intrinsics.checkNotNull(disposableSubscribe);
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
        this.readDisposable = disposableSubscribe;
    }
}
