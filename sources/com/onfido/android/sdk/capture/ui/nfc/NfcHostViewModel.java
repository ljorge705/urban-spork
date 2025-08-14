package com.onfido.android.sdk.capture.ui.nfc;

import android.content.SharedPreferences;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
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
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.navigation.OnfidoNavigation;
import com.onfido.android.sdk.capture.internal.navigation.Screen;
import com.onfido.android.sdk.capture.internal.navigation.navigator.NavigationManagerHolder;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.internal.nfc.data.NfcPassportExtraction;
import com.onfido.android.sdk.capture.internal.nfc.data.PassportBACKey;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConstants;
import com.onfido.android.sdk.capture.ui.nfc.NfcFailedScreen;
import com.onfido.android.sdk.capture.ui.nfc.model.NFCAdapterArguments;
import com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.dagger.assisted.Assisted;
import com.onfido.dagger.assisted.AssistedFactory;
import com.onfido.dagger.assisted.AssistedInject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.ClassUtils;

@Metadata(d1 = {"\u0000î\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0000\u0018\u0000 \u0081\u00012\u00020\u0001:\u0006\u0081\u0001\u0082\u0001\u0083\u0001BA\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\b\u0001\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u0006\u00105\u001a\u000206J\u0006\u00107\u001a\u00020\u0016J\u000e\u00108\u001a\u0002062\u0006\u00109\u001a\u00020/J\b\u0010:\u001a\u00020\"H\u0002J\u0010\u0010;\u001a\u0002062\u0006\u00109\u001a\u00020/H\u0002J\u0006\u0010<\u001a\u000206J\u0006\u0010=\u001a\u000206J\u0006\u0010>\u001a\u000206J\b\u0010?\u001a\u00020@H\u0002J\b\u0010A\u001a\u0004\u0018\u00010BJ\u0006\u0010C\u001a\u00020DJ\u0014\u0010E\u001a\u000e\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u00020H0FH\u0002J\u0014\u0010I\u001a\u000e\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u00020H0FH\u0002J\u0006\u0010J\u001a\u00020KJ\b\u0010L\u001a\u00020@H\u0007J\u0006\u0010M\u001a\u00020&J\b\u0010N\u001a\u00020GH\u0002J\b\u0010O\u001a\u00020GH\u0002J\u0006\u0010P\u001a\u00020@J\b\u0010Q\u001a\u00020HH\u0002J\b\u0010R\u001a\u00020HH\u0002J\u0006\u0010S\u001a\u000206J\b\u0010T\u001a\u00020\u0016H\u0002J\b\u0010U\u001a\u000206H\u0002J\u0006\u0010V\u001a\u00020\u0016J\b\u0010W\u001a\u00020\u0016H\u0002J&\u0010X\u001a\u0002062\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010B2\u0006\u0010Z\u001a\u00020\u00162\b\b\u0002\u0010[\u001a\u00020\u0016H\u0002J\b\u0010\\\u001a\u000206H\u0002J\u0006\u0010]\u001a\u00020@J\b\u0010^\u001a\u00020_H\u0002J\u0018\u0010`\u001a\u0002062\b\u0010a\u001a\u0004\u0018\u00010b2\u0006\u0010c\u001a\u00020@J\u000e\u0010d\u001a\u0002062\u0006\u0010e\u001a\u00020BJ\u0006\u0010f\u001a\u000206J\u0016\u0010g\u001a\u0002062\u0006\u0010h\u001a\u00020D2\u0006\u0010i\u001a\u00020jJ\u0006\u0010k\u001a\u000206J\u000e\u0010l\u001a\u0002062\u0006\u0010m\u001a\u00020KJ\u0016\u0010l\u001a\u0002062\u0006\u00109\u001a\u00020K2\u0006\u0010n\u001a\u00020oJ\u0016\u0010p\u001a\u0002062\u0006\u0010q\u001a\u00020r2\u0006\u0010h\u001a\u00020DJ\u0006\u0010s\u001a\u000206J\u0006\u0010t\u001a\u000206J\u0006\u0010u\u001a\u000206J\u0006\u0010v\u001a\u000206J\u0006\u0010w\u001a\u000206J\u0006\u0010x\u001a\u000206J\u0006\u0010y\u001a\u000206J\u0006\u0010z\u001a\u000206J\u0006\u0010{\u001a\u000206J\b\u0010|\u001a\u00020@H\u0002J\b\u0010}\u001a\u00020\u0016H\u0002J\u0006\u0010~\u001a\u00020\u0016J\b\u0010\u007f\u001a\u000206H\u0002J\u0007\u0010\u0080\u0001\u001a\u000206R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u0016X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010,\u001a\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0.0-¢\u0006\u0002\b0X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0.02¢\u0006\b\n\u0000\u001a\u0004\b3\u00104¨\u0006\u0084\u0001"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel;", "Landroidx/lifecycle/ViewModel;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "nfcInteractor", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;", "nfcTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;", "screenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", KeychainModule.Maps.STORAGE, "Lcom/onfido/android/sdk/capture/common/preferences/SharedPreferencesDataSource;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "(Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;Lcom/onfido/android/sdk/capture/common/preferences/SharedPreferencesDataSource;Landroidx/lifecycle/SavedStateHandle;)V", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "docType", "Lcom/onfido/android/sdk/capture/DocumentType;", "isOnlyOneDocAvailable", "", "isOnlyOneDocAvailable$onfido_capture_sdk_core_release", "()Z", "navigationManagerHolder", "Lcom/onfido/android/sdk/capture/internal/navigation/navigator/NavigationManagerHolder;", "getNavigationManagerHolder", "()Lcom/onfido/android/sdk/capture/internal/navigation/navigator/NavigationManagerHolder;", "navigator", "Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "getNavigator", "()Lcom/onfido/android/sdk/capture/internal/navigation/Navigator;", "nfcAdapterArguments", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NFCAdapterArguments;", "nfcAttemptsCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "nfcOptions", "Lcom/onfido/android/sdk/capture/model/NFCOptions$Enabled;", "nfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "onfidoNavigation", "Lcom/onfido/android/sdk/capture/internal/navigation/OnfidoNavigation;", "scanAutomatically", "uiMessageSubject", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage;", "Lio/reactivex/rxjava3/annotations/NonNull;", "uiMessages", "Lio/reactivex/rxjava3/core/Observable;", "getUiMessages", "()Lio/reactivex/rxjava3/core/Observable;", "backClickedFromSettingScreen", "", "canNFCScanStart", "consumeUIMessage", "message", "createInitialValidCanEntry", "emitUIMessage", "exitFlow", "exitFlowAtDocumentNotSupportedClicked", "exitFlowAtRetryClicked", "getAttemptsLeft", "", "getCanNumber", "", "getExpectedNfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "getFailedScreenActionsForScanFailure", "Lkotlin/Pair;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$Actions$Primary;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$Actions$Secondary;", "getFailedScreenActionsForVerificationFailure", "getInstructionVideoPathForFailure", "", "getNfcCanMaxAttemptsButtonText", "getNfcOptions", "getPrimaryActionForScanFailure", "getPrimaryActionForVerificationFailure", "getRetriesLeft", "getSecondaryActionForScanFailure", "getSecondaryActionForVerificationFailure", "goToDocumentSelection", "hasValidCan", "initScanScreen", "isDarkModeEnabled", "isSecondAttempt", "navigateToCanEntryScreen", "inputCan", "withRetry", "withRetryWithoutErrorState", "navigateToNfcPermissionsScreen", "nfcScanAttempt", "nfcScanScreen", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcScanScreen;", "onBackClicked", "lastScreen", "Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", "currentStackSize", "onCanNumberEntered", "canNumber", "onNfcCanMaxAttemptsButtonClicked", "onNfcChipRead", "nfcFlowType", "scanDuration", "", "onNfcIntro", "onNfcScanFailed", "reason", "errorState", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcFailedScreen$ErrorState;", "onNfcScanSucceeded", "nfcData", "Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;", "onNfcSettingsActivityResult", "onNoCanClicked", "onOpenNfcSettingClicked", "onRetryNfcClicked", "onSkipNfcScanAtInitialPromptClicked", "onSkipNfcScanAtRetryClicked", "resetNFCAdapterArguments", "resolveNFCArguments", "restartDocumentCapture", "retriesAttempts", "shouldShowCanScreen", "shouldStartScanningAutomatically", "showCANScreen", "trackNFCScanClicked", "Companion", "Factory", "UIMessage", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcHostViewModel extends ViewModel {
    private static final Companion Companion = new Companion(null);
    private static final String KEY_NAVIGATOR_INITIALIZED = "key_navigator_initialized";
    private static final String NFC_LOGGER = "NFC Logger";
    private final CountryCode countryCode;
    private final DocumentType docType;
    private final boolean isOnlyOneDocAvailable;
    private final NavigationManagerHolder navigationManagerHolder;
    private final Navigator navigator;
    private NFCAdapterArguments nfcAdapterArguments;
    private final AtomicInteger nfcAttemptsCount;
    private final NfcInteractor nfcInteractor;
    private final NFCOptions.Enabled nfcOptions;
    private final NfcProperties nfcProperties;
    private final NfcTracker nfcTracker;
    private final OnfidoNavigation onfidoNavigation;
    private final OnfidoRemoteConfig onfidoRemoteConfig;
    private final SavedStateHandle savedStateHandle;
    private boolean scanAutomatically;
    private final ScreenTracker screenTracker;
    private final SharedPreferencesDataSource storage;
    private final BehaviorSubject<List<UIMessage>> uiMessageSubject;
    private final Observable<List<UIMessage>> uiMessages;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$Companion;", "", "()V", "KEY_NAVIGATOR_INITIALIZED", "", "NFC_LOGGER", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @AssistedFactory
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$Factory;", "", "create", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        NfcHostViewModel create(SavedStateHandle savedStateHandle);
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\u0007\b\t\n\u000b\fB\u0007\b\u0004¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0006\r\u000e\u000f\u0010\u0011\u0012¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage;", "", "()V", "id", "", "getId", "()J", "CanNumberEntered", "Exit", "ExitOnfidoFlow", "NfcScanSkipped", "NfcScanSuccess", "OpenNfcSettings", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage$CanNumberEntered;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage$Exit;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage$ExitOnfidoFlow;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage$NfcScanSkipped;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage$NfcScanSuccess;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage$OpenNfcSettings;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class UIMessage {
        private final long id;

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage$CanNumberEntered;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage;", "canNumber", "", "(Ljava/lang/Number;)V", "getCanNumber", "()Ljava/lang/Number;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class CanNumberEntered extends UIMessage {
            private final Number canNumber;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public CanNumberEntered(Number canNumber) {
                super(null);
                Intrinsics.checkNotNullParameter(canNumber, "canNumber");
                this.canNumber = canNumber;
            }

            public static /* synthetic */ CanNumberEntered copy$default(CanNumberEntered canNumberEntered, Number number, int i, Object obj) {
                if ((i & 1) != 0) {
                    number = canNumberEntered.canNumber;
                }
                return canNumberEntered.copy(number);
            }

            /* renamed from: component1, reason: from getter */
            public final Number getCanNumber() {
                return this.canNumber;
            }

            public final CanNumberEntered copy(Number canNumber) {
                Intrinsics.checkNotNullParameter(canNumber, "canNumber");
                return new CanNumberEntered(canNumber);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof CanNumberEntered) && Intrinsics.areEqual(this.canNumber, ((CanNumberEntered) other).canNumber);
            }

            public final Number getCanNumber() {
                return this.canNumber;
            }

            public int hashCode() {
                return this.canNumber.hashCode();
            }

            public String toString() {
                return "CanNumberEntered(canNumber=" + this.canNumber + ')';
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage$Exit;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Exit extends UIMessage {
            public static final Exit INSTANCE = new Exit();

            private Exit() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage$ExitOnfidoFlow;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class ExitOnfidoFlow extends UIMessage {
            public static final ExitOnfidoFlow INSTANCE = new ExitOnfidoFlow();

            private ExitOnfidoFlow() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage$NfcScanSkipped;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class NfcScanSkipped extends UIMessage {
            public static final NfcScanSkipped INSTANCE = new NfcScanSkipped();

            private NfcScanSkipped() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage$NfcScanSuccess;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage;", "nfcData", "Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "(Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;)V", "getNfcData", "()Lcom/onfido/android/sdk/capture/internal/nfc/data/NfcPassportExtraction;", "getNfcFlowType", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class NfcScanSuccess extends UIMessage {
            private final NfcPassportExtraction nfcData;
            private final NfcFlowType nfcFlowType;

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
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage$OpenNfcSettings;", "Lcom/onfido/android/sdk/capture/ui/nfc/NfcHostViewModel$UIMessage;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class OpenNfcSettings extends UIMessage {
            public static final OpenNfcSettings INSTANCE = new OpenNfcSettings();

            private OpenNfcSettings() {
                super(null);
            }
        }

        private UIMessage() {
            this.id = UUID.randomUUID().getMostSignificantBits();
        }

        public final long getId() {
            return this.id;
        }

        public /* synthetic */ UIMessage(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[NfcFailedScreen.ErrorState.values().length];
            try {
                iArr[NfcFailedScreen.ErrorState.ScanningFailed.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[NfcFailedScreen.ErrorState.VerificationFailed.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @AssistedInject
    public NfcHostViewModel(SchedulersProvider schedulersProvider, NfcInteractor nfcInteractor, NfcTracker nfcTracker, ScreenTracker screenTracker, OnfidoRemoteConfig onfidoRemoteConfig, SharedPreferencesDataSource storage, @Assisted SavedStateHandle savedStateHandle) {
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(nfcInteractor, "nfcInteractor");
        Intrinsics.checkNotNullParameter(nfcTracker, "nfcTracker");
        Intrinsics.checkNotNullParameter(screenTracker, "screenTracker");
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        Intrinsics.checkNotNullParameter(storage, "storage");
        Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
        this.nfcInteractor = nfcInteractor;
        this.nfcTracker = nfcTracker;
        this.screenTracker = screenTracker;
        this.onfidoRemoteConfig = onfidoRemoteConfig;
        this.storage = storage;
        this.savedStateHandle = savedStateHandle;
        OnfidoNavigation onfidoNavigation = new OnfidoNavigation(schedulersProvider);
        this.onfidoNavigation = onfidoNavigation;
        Object obj = savedStateHandle.get(NfcHostFragment.KEY_ARG_DOC_TYPE);
        if (obj == null) {
            throw new IllegalArgumentException("docType == null".toString());
        }
        this.docType = (DocumentType) obj;
        Object obj2 = savedStateHandle.get(NfcHostFragment.KEY_NFC_OPTIONS);
        if (obj2 == null) {
            throw new IllegalArgumentException("NFC flow can not be launched without NFCOptions. \nmake sure correct value is passed for this param.   ".toString());
        }
        this.nfcOptions = (NFCOptions.Enabled) obj2;
        this.countryCode = (CountryCode) savedStateHandle.get(NfcHostFragment.KEY_ARG_COUNTRY_CODE);
        Object obj3 = savedStateHandle.get(NfcHostFragment.KEY_ARG_NFC_PROPERTIES);
        if (obj3 == null) {
            throw new IllegalArgumentException("nfcProperties == null".toString());
        }
        this.nfcProperties = (NfcProperties) obj3;
        BehaviorSubject<List<UIMessage>> behaviorSubjectCreateDefault = BehaviorSubject.createDefault(CollectionsKt.emptyList());
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault, "createDefault(...)");
        this.uiMessageSubject = behaviorSubjectCreateDefault;
        Boolean bool = (Boolean) savedStateHandle.get(NfcHostFragment.KEY_IS_ONLY_ONE_DOC_AVAILABLE);
        this.isOnlyOneDocAvailable = bool != null ? bool.booleanValue() : false;
        this.navigator = onfidoNavigation.getNavigator();
        this.navigationManagerHolder = onfidoNavigation.getNavigationManagerHolder();
        Observable<List<UIMessage>> observableHide = behaviorSubjectCreateDefault.hide();
        Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
        this.uiMessages = observableHide;
        this.nfcAttemptsCount = new AtomicInteger(1);
        this.nfcAdapterArguments = NFCAdapterArguments.Empty.INSTANCE;
        if (savedStateHandle.contains(KEY_NAVIGATOR_INITIALIZED)) {
            return;
        }
        initScanScreen();
        savedStateHandle.set(KEY_NAVIGATOR_INITIALIZED, Boolean.TRUE);
    }

    private final NFCAdapterArguments createInitialValidCanEntry() {
        if (this.nfcInteractor.isNfcEnabled()) {
            if (!hasValidCan()) {
                return new NFCAdapterArguments.CAN(null);
            }
            if (!shouldShowCanScreen()) {
                String can = this.nfcProperties.getCan();
                return new NFCAdapterArguments.CAN(can != null ? StringsKt.toLongOrNull(can) : null);
            }
        }
        return NFCAdapterArguments.Empty.INSTANCE;
    }

    private final void emitUIMessage(UIMessage message) {
        BehaviorSubject<List<UIMessage>> behaviorSubject = this.uiMessageSubject;
        List<UIMessage> value = behaviorSubject.getValue();
        if (value == null) {
            value = CollectionsKt.emptyList();
        }
        behaviorSubject.onNext(CollectionsKt.plus((Collection) value, (Iterable) CollectionsKt.listOf(message)));
    }

    private final int getAttemptsLeft() {
        int maxRetries = this.onfidoRemoteConfig.getDocumentCapture().getNfc().getMaxRetries() - this.nfcAttemptsCount.get();
        Timber.INSTANCE.d("NFC Logger - Attempts left= " + maxRetries, new Object[0]);
        return maxRetries;
    }

    private final Pair<NfcFailedScreen.Actions.Primary, NfcFailedScreen.Actions.Secondary> getFailedScreenActionsForScanFailure() {
        return TuplesKt.to(getPrimaryActionForScanFailure(), getSecondaryActionForScanFailure());
    }

    private final Pair<NfcFailedScreen.Actions.Primary, NfcFailedScreen.Actions.Secondary> getFailedScreenActionsForVerificationFailure() {
        return TuplesKt.to(getPrimaryActionForVerificationFailure(), getSecondaryActionForVerificationFailure());
    }

    private final NfcFailedScreen.Actions.Primary getPrimaryActionForScanFailure() {
        return NfcFailedScreen.Actions.Primary.Retry;
    }

    private final NfcFailedScreen.Actions.Primary getPrimaryActionForVerificationFailure() {
        return NfcFailedScreen.Actions.Primary.Retry;
    }

    private final NfcFailedScreen.Actions.Secondary getSecondaryActionForScanFailure() {
        return NfcFailedScreen.Actions.Secondary.Hide;
    }

    private final NfcFailedScreen.Actions.Secondary getSecondaryActionForVerificationFailure() {
        NFCOptions.Enabled enabled = this.nfcOptions;
        if (enabled instanceof NFCOptions.Enabled.Optional) {
            return NfcFailedScreen.Actions.Secondary.Skip;
        }
        if (enabled instanceof NFCOptions.Enabled.Required) {
            return NfcFailedScreen.Actions.Secondary.Exit;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final boolean hasValidCan() {
        return this.nfcProperties.getHasCan() && this.nfcProperties.getCanLength() > 0;
    }

    private final void initScanScreen() {
        Navigator navigator;
        Screen screenNfcScanScreen;
        this.nfcAdapterArguments = createInitialValidCanEntry();
        if (this.nfcProperties.getIsNfcSupported()) {
            navigator = this.navigator;
            screenNfcScanScreen = nfcScanScreen();
        } else {
            navigator = this.navigator;
            screenNfcScanScreen = DocumentNotNfcCompatibleScreen.INSTANCE;
        }
        navigator.navigateTo(screenNfcScanScreen);
    }

    private final boolean isSecondAttempt() {
        return nfcScanAttempt() == 2;
    }

    private final void navigateToCanEntryScreen(Number inputCan, boolean withRetry, boolean withRetryWithoutErrorState) {
        this.navigator.navigateTo(new NfcCanEntryScreen(this.nfcProperties.getCan(), this.nfcProperties.getCanLength(), inputCan, withRetry, withRetryWithoutErrorState));
    }

    static /* synthetic */ void navigateToCanEntryScreen$default(NfcHostViewModel nfcHostViewModel, Number number, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            number = null;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        nfcHostViewModel.navigateToCanEntryScreen(number, z, z2);
    }

    private final void navigateToNfcPermissionsScreen() {
        this.navigator.navigateTo(NfcPermissionsScreen.INSTANCE);
    }

    private final NfcScanScreen nfcScanScreen() {
        return new NfcScanScreen(this.docType, this.countryCode, getExpectedNfcFlowType(), new PassportBACKey(this.nfcProperties.getNumber$onfido_capture_sdk_core_release(), this.nfcProperties.getDateOfBirth$onfido_capture_sdk_core_release(), this.nfcProperties.getExpireDate$onfido_capture_sdk_core_release()), this.nfcProperties.getAaChallenge());
    }

    private final int retriesAttempts() {
        int iNfcScanAttempt = nfcScanAttempt() - 1;
        Timber.INSTANCE.d("NFC Logger - Retries attempt= " + iNfcScanAttempt, new Object[0]);
        return iNfcScanAttempt;
    }

    private final boolean shouldShowCanScreen() {
        return this.onfidoRemoteConfig.getDocumentCapture().getNfc().isCanEntryScreenEnabled() || (this.nfcAdapterArguments instanceof NFCAdapterArguments.WrongCAN);
    }

    private final void showCANScreen() {
        boolean z = this.nfcAdapterArguments instanceof NFCAdapterArguments.WrongCAN;
        boolean z2 = (this.onfidoRemoteConfig.getDocumentCapture().getNfc().isCanEntryScreenEnabled() ^ true) && isSecondAttempt();
        Number number = null;
        if (!z2) {
            NFCAdapterArguments nFCAdapterArguments = this.nfcAdapterArguments;
            NFCAdapterArguments.WrongCAN wrongCAN = nFCAdapterArguments instanceof NFCAdapterArguments.WrongCAN ? (NFCAdapterArguments.WrongCAN) nFCAdapterArguments : null;
            if (wrongCAN != null) {
                number = wrongCAN.getNumber();
            }
        }
        navigateToCanEntryScreen(number, z, z2);
    }

    public final void backClickedFromSettingScreen() {
        this.nfcAdapterArguments = createInitialValidCanEntry();
    }

    public final boolean canNFCScanStart() {
        if (this.nfcInteractor.isNfcEnabled() && Intrinsics.areEqual(this.nfcAdapterArguments, NFCAdapterArguments.Empty.INSTANCE)) {
            this.nfcAdapterArguments = createInitialValidCanEntry();
        }
        if (!this.nfcInteractor.isNfcEnabled()) {
            this.nfcAdapterArguments = NFCAdapterArguments.Empty.INSTANCE;
            return false;
        }
        NFCAdapterArguments nFCAdapterArguments = this.nfcAdapterArguments;
        if (nFCAdapterArguments instanceof NFCAdapterArguments.CAN) {
            return true;
        }
        if ((nFCAdapterArguments instanceof NFCAdapterArguments.WrongCAN) || (nFCAdapterArguments instanceof NFCAdapterArguments.Empty)) {
            return false;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final void consumeUIMessage(UIMessage message) {
        Intrinsics.checkNotNullParameter(message, "message");
        List<UIMessage> value = this.uiMessageSubject.getValue();
        if (value == null) {
            value = CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : value) {
            if (((UIMessage) obj).getId() != message.getId()) {
                arrayList.add(obj);
            }
        }
        this.uiMessageSubject.onNext(arrayList);
    }

    public final void exitFlow() {
        emitUIMessage(UIMessage.ExitOnfidoFlow.INSTANCE);
    }

    public final void exitFlowAtDocumentNotSupportedClicked() {
        this.nfcTracker.trackNfcExitVerificationClicked$onfido_capture_sdk_core_release();
        exitFlow();
    }

    public final void exitFlowAtRetryClicked() {
        this.nfcTracker.trackNfcExitVerificationClicked$onfido_capture_sdk_core_release();
        exitFlow();
    }

    public final Number getCanNumber() {
        NFCAdapterArguments nFCAdapterArguments = this.nfcAdapterArguments;
        Intrinsics.checkNotNull(nFCAdapterArguments, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.nfc.model.NFCAdapterArguments.CAN");
        return ((NFCAdapterArguments.CAN) nFCAdapterArguments).getNumber();
    }

    public final NfcFlowType getExpectedNfcFlowType() {
        return (this.nfcProperties.getHasCan() || this.nfcProperties.getHasPin()) ? NfcFlowType.PACE : NfcFlowType.BAC;
    }

    public final String getInstructionVideoPathForFailure() {
        String str = (this.docType == DocumentType.PASSPORT ? "passport" : "card") + "-intro-" + (isDarkModeEnabled() ? OnfidoAnimWebView.THEME_DARK : OnfidoAnimWebView.THEME_LIGHT) + LivenessConstants.VIDEO_RECORDING_FILE_FORMAT;
        String animationVersion = this.onfidoRemoteConfig.getDocumentCapture().getNfc().getAnimationVersion();
        StringBuilder sb = new StringBuilder();
        String str2 = String.format(BuildConfig.NFC_ASSERT_PATH, Arrays.copyOf(new Object[]{animationVersion}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
        return sb.append(str2).append(str).toString();
    }

    public final NavigationManagerHolder getNavigationManagerHolder() {
        return this.navigationManagerHolder;
    }

    public final Navigator getNavigator() {
        return this.navigator;
    }

    public final int getNfcCanMaxAttemptsButtonText() {
        return Intrinsics.areEqual(this.nfcOptions, NFCOptions.Enabled.Required.INSTANCE) ? R.string.onfido_nfc_scan_error_can_primary_button : R.string.onfido_nfc_scan_error_final_possible_card_secondary_button;
    }

    public final NFCOptions.Enabled getNfcOptions() {
        return this.nfcOptions;
    }

    public final int getRetriesLeft() {
        int attemptsLeft = getAttemptsLeft() + 1;
        Timber.INSTANCE.d("NFC Logger - Retries left= " + attemptsLeft, new Object[0]);
        return attemptsLeft;
    }

    public final Observable<List<UIMessage>> getUiMessages() {
        return this.uiMessages;
    }

    public final void goToDocumentSelection() {
        emitUIMessage(UIMessage.Exit.INSTANCE);
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

    /* renamed from: isOnlyOneDocAvailable$onfido_capture_sdk_core_release, reason: from getter */
    public final boolean getIsOnlyOneDocAvailable() {
        return this.isOnlyOneDocAvailable;
    }

    public final int nfcScanAttempt() {
        int i = this.nfcAttemptsCount.get();
        Timber.INSTANCE.d("NFC Logger - nfcScanAttempt= " + i, new Object[0]);
        return i;
    }

    public final void onBackClicked(Screen lastScreen, int currentStackSize) {
        if (lastScreen instanceof NfcFailedScreen) {
            resetNFCAdapterArguments();
        } else if (lastScreen instanceof NfcPermissionsScreen) {
            backClickedFromSettingScreen();
        } else if (lastScreen instanceof DocumentNotNfcCompatibleScreen) {
            goToDocumentSelection();
            return;
        } else if (lastScreen instanceof NfcCanMaxAttemptsReachedScreen) {
            exitFlow();
            return;
        } else if (currentStackSize <= 1) {
            this.uiMessageSubject.onNext(CollectionsKt.listOf(UIMessage.Exit.INSTANCE));
            return;
        }
        this.navigator.exitCurrentScreen();
    }

    public final void onCanNumberEntered(Number canNumber) {
        Intrinsics.checkNotNullParameter(canNumber, "canNumber");
        emitUIMessage(new UIMessage.CanNumberEntered(canNumber));
        if (!this.nfcInteractor.isNfcEnabled()) {
            navigateToNfcPermissionsScreen();
            return;
        }
        this.nfcAdapterArguments = new NFCAdapterArguments.CAN(canNumber);
        this.scanAutomatically = true;
        this.navigator.exitCurrentScreen();
    }

    public final void onNfcCanMaxAttemptsButtonClicked() {
        if (Intrinsics.areEqual(this.nfcOptions, NFCOptions.Enabled.Required.INSTANCE)) {
            this.nfcTracker.trackNfcExitVerificationClicked$onfido_capture_sdk_core_release();
            exitFlow();
        } else {
            this.nfcTracker.trackNfcSkipAtManualCanEntryClicked$onfido_capture_sdk_core_release();
            emitUIMessage(UIMessage.NfcScanSkipped.INSTANCE);
        }
    }

    public final void onNfcChipRead(NfcFlowType nfcFlowType, long scanDuration) {
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        this.screenTracker.trackNfcReadSuccess$onfido_capture_sdk_core_release(scanDuration, nfcScanAttempt(), nfcFlowType, this.nfcOptions);
    }

    public final void onNfcIntro() {
        this.screenTracker.trackNfcIntro$onfido_capture_sdk_core_release(this.docType, this.countryCode, getExpectedNfcFlowType(), this.nfcOptions);
    }

    public final void onNfcScanFailed(String reason) {
        NfcFailedScreen.ErrorState errorState;
        Intrinsics.checkNotNullParameter(reason, "reason");
        Timber.Companion companion = Timber.INSTANCE;
        companion.d("NFC Logger - Scan failed because= " + reason + ClassUtils.PACKAGE_SEPARATOR_CHAR, new Object[0]);
        companion.d("NFC Logger - retryAttemptsLeft= " + getAttemptsLeft(), new Object[0]);
        if (getAttemptsLeft() > 0) {
            errorState = NfcFailedScreen.ErrorState.ScanningFailed;
        } else {
            if (hasValidCan()) {
                this.navigator.navigateTo(NfcCanMaxAttemptsReachedScreen.INSTANCE);
                this.nfcAttemptsCount.incrementAndGet();
            }
            errorState = NfcFailedScreen.ErrorState.VerificationFailed;
        }
        onNfcScanFailed(reason, errorState);
        this.nfcAttemptsCount.incrementAndGet();
    }

    public final void onNfcScanSucceeded(NfcPassportExtraction nfcData, NfcFlowType nfcFlowType) {
        Intrinsics.checkNotNullParameter(nfcData, "nfcData");
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        emitUIMessage(new UIMessage.NfcScanSuccess(nfcData, nfcFlowType));
    }

    public final void onNfcSettingsActivityResult() {
        if (this.nfcInteractor.isNfcEnabled()) {
            this.nfcAdapterArguments = createInitialValidCanEntry();
            if (hasValidCan() && shouldShowCanScreen()) {
                NFCAdapterArguments nFCAdapterArguments = this.nfcAdapterArguments;
                if (!(nFCAdapterArguments instanceof NFCAdapterArguments.CAN)) {
                    if (nFCAdapterArguments instanceof NFCAdapterArguments.Empty) {
                        this.navigator.exitCurrentScreen();
                        this.navigator.navigateTo(new NfcCanEntryScreen(this.nfcProperties.getCan(), this.nfcProperties.getCanLength(), null, false, false, 20, null));
                        return;
                    }
                    if (nFCAdapterArguments instanceof NFCAdapterArguments.WrongCAN) {
                        this.navigator.exitCurrentScreen();
                        this.navigator.navigateTo(new NfcCanEntryScreen(this.nfcProperties.getCan(), this.nfcProperties.getCanLength(), ((NFCAdapterArguments.WrongCAN) nFCAdapterArguments).getNumber(), false, false, 16, null));
                        return;
                    }
                    return;
                }
            }
            this.scanAutomatically = true;
            this.navigator.exitCurrentScreen();
        }
    }

    public final void onNoCanClicked() {
        this.nfcTracker.trackNfcNoCanClicked$onfido_capture_sdk_core_release();
        this.navigator.navigateTo(DocumentNotNfcCompatibleScreen.INSTANCE);
    }

    public final void onOpenNfcSettingClicked() {
        this.nfcTracker.trackNfcOpenSettingsClicked$onfido_capture_sdk_core_release();
        emitUIMessage(UIMessage.OpenNfcSettings.INSTANCE);
    }

    public final void onRetryNfcClicked() {
        this.scanAutomatically = true;
        if (hasValidCan()) {
            NFCAdapterArguments nFCAdapterArguments = this.nfcAdapterArguments;
            NFCAdapterArguments.CAN can = nFCAdapterArguments instanceof NFCAdapterArguments.CAN ? (NFCAdapterArguments.CAN) nFCAdapterArguments : null;
            if (can == null) {
                throw new IllegalStateException(("Document has CAN, but args are incorrect= " + this.nfcAdapterArguments).toString());
            }
            this.nfcAdapterArguments = new NFCAdapterArguments.WrongCAN(can.getNumber());
        }
        this.navigator.exitCurrentScreen();
        this.nfcTracker.trackNfcRetryScanSelected$onfido_capture_sdk_core_release(getExpectedNfcFlowType(), this.docType, this.countryCode, retriesAttempts(), this.nfcOptions);
    }

    public final void onSkipNfcScanAtInitialPromptClicked() {
        emitUIMessage(UIMessage.NfcScanSkipped.INSTANCE);
        this.nfcTracker.trackSkipNfcScanAtInitialPrompt$onfido_capture_sdk_core_release(this.nfcOptions);
    }

    public final void onSkipNfcScanAtRetryClicked() {
        emitUIMessage(UIMessage.NfcScanSkipped.INSTANCE);
        this.nfcTracker.trackSkipNfcScanAtRetry$onfido_capture_sdk_core_release(getExpectedNfcFlowType(), retriesAttempts(), this.nfcOptions);
    }

    public final void resetNFCAdapterArguments() {
        if (hasValidCan() && shouldShowCanScreen()) {
            this.nfcAdapterArguments = NFCAdapterArguments.Empty.INSTANCE;
        }
    }

    public final void resolveNFCArguments() {
        if (!this.nfcInteractor.isNfcEnabled()) {
            navigateToNfcPermissionsScreen();
        } else {
            if (!hasValidCan() || !shouldShowCanScreen()) {
                throw new IllegalStateException(("The screen should be opened with correct ValidCANEntry= " + this.nfcAdapterArguments).toString());
            }
            showCANScreen();
        }
    }

    public final void restartDocumentCapture() {
        this.nfcTracker.trackNfcChooseAnotherDocumentClicked$onfido_capture_sdk_core_release();
        emitUIMessage(UIMessage.Exit.INSTANCE);
    }

    public final boolean shouldStartScanningAutomatically() {
        if (!this.scanAutomatically) {
            return false;
        }
        this.scanAutomatically = false;
        return true;
    }

    public final void trackNFCScanClicked() {
        this.nfcTracker.trackContinueToNfcScanSelected$onfido_capture_sdk_core_release(getExpectedNfcFlowType());
    }

    public final void onNfcScanFailed(String message, NfcFailedScreen.ErrorState errorState) {
        Pair<NfcFailedScreen.Actions.Primary, NfcFailedScreen.Actions.Secondary> failedScreenActionsForScanFailure;
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(errorState, "errorState");
        int i = WhenMappings.$EnumSwitchMapping$0[errorState.ordinal()];
        if (i == 1) {
            failedScreenActionsForScanFailure = getFailedScreenActionsForScanFailure();
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            failedScreenActionsForScanFailure = getFailedScreenActionsForVerificationFailure();
        }
        this.navigator.navigateTo(new NfcFailedScreen(this.docType, message, errorState, failedScreenActionsForScanFailure.component1(), failedScreenActionsForScanFailure.component2()));
    }
}
