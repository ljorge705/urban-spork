package com.onfido.android.sdk.capture.internal.nfc;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import androidx.core.os.BundleKt;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.nfc.PassportNfcExtractionState;
import com.onfido.android.sdk.capture.internal.nfc.data.PassportBACKey;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.nfc.MRTDDataGroup;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Cancellable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 72\u00020\u0001:\u00017B1\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J$\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000e0\u0013H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0016Ju\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00140\u001c2\u0006\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\b\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00192\b\u0010-\u001a\u0004\u0018\u00010.H\u0016¢\u0006\u0002\u0010/J\u0016\u00100\u001a\u00020\u000e2\f\u00101\u001a\b\u0012\u0004\u0012\u00020302H\u0016J\u0010\u00104\u001a\u00020\u000e2\u0006\u00105\u001a\u000206H\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractorImpl;", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;", "nfcAdapter", "Landroid/nfc/NfcAdapter;", "passportReader", "Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcReader;", "nfcTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;", "nfcTimeouts", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcTimeouts;", "timeProvider", "Lcom/onfido/android/sdk/capture/utils/TimeProvider;", "(Landroid/nfc/NfcAdapter;Lcom/onfido/android/sdk/capture/internal/nfc/PassportNfcReader;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;Lcom/onfido/android/sdk/capture/internal/nfc/NfcTimeouts;Lcom/onfido/android/sdk/capture/utils/TimeProvider;)V", "disableReaderMode", "", "activity", "Landroid/app/Activity;", "enableReaderMode", "tagReadCallback", "Lkotlin/Function1;", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcTagDelegate;", "getNfcChipReadDuration", "", "startTime", "isNfcEnabled", "", "isNfcSupported", "readNfcTag", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcReadState;", "nfcTagRetries", "tag", "passportBACKey", "Lcom/onfido/android/sdk/capture/internal/nfc/data/PassportBACKey;", "aaChallenge", "", "fileIDs", "", "Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;", "canNumber", "", "isPaceEnabled", "nfcFlowType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "chipAuthentication", "realtimeNfcEvents", "Lcom/onfido/android/sdk/capture/internal/nfc/RealtimeNfcEvents;", "(Lio/reactivex/rxjava3/core/Observable;Lcom/onfido/android/sdk/capture/internal/nfc/NfcTagDelegate;Lcom/onfido/android/sdk/capture/internal/nfc/data/PassportBACKey;[B[Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;Ljava/lang/Number;ZLcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;ZLcom/onfido/android/sdk/capture/internal/nfc/RealtimeNfcEvents;)Lio/reactivex/rxjava3/core/Observable;", "redirectToNfcSetting", "launcher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "trackAuthError", Constants.AUTH, "Lcom/onfido/android/sdk/capture/internal/nfc/PassportAuthAccess;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcInteractorImpl implements NfcInteractor {
    private static final String NFC_LOGGER = "NFC Logger";
    private static final int READER_FLAGS = 131;
    private static final int READER_PRESENCE_CHECK_DELAY = 1000;
    private final NfcAdapter nfcAdapter;
    private final NfcTimeouts nfcTimeouts;
    private final NfcTracker nfcTracker;
    private final PassportNfcReader passportReader;
    private final TimeProvider timeProvider;

    @Inject
    public NfcInteractorImpl(NfcAdapter nfcAdapter, PassportNfcReader passportReader, NfcTracker nfcTracker, NfcTimeouts nfcTimeouts, TimeProvider timeProvider) {
        Intrinsics.checkNotNullParameter(passportReader, "passportReader");
        Intrinsics.checkNotNullParameter(nfcTracker, "nfcTracker");
        Intrinsics.checkNotNullParameter(nfcTimeouts, "nfcTimeouts");
        Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
        this.nfcAdapter = nfcAdapter;
        this.passportReader = passportReader;
        this.nfcTracker = nfcTracker;
        this.nfcTimeouts = nfcTimeouts;
        this.timeProvider = timeProvider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void enableReaderMode$lambda$0(Function1 tagReadCallback, Tag tag) {
        Intrinsics.checkNotNullParameter(tagReadCallback, "$tagReadCallback");
        Intrinsics.checkNotNull(tag);
        tagReadCallback.invoke(new NfcTagDelegate(tag));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long getNfcChipReadDuration(long startTime) {
        return this.timeProvider.getCurrentTimestamp() - (startTime + this.nfcTimeouts.nfcScanDelayMs());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void readNfcTag$lambda$1(final NfcInteractorImpl this$0, Observable nfcTagRetries, PassportBACKey passportBACKey, byte[] bArr, NfcTagDelegate tag, MRTDDataGroup[] fileIDs, Number number, boolean z, NfcFlowType nfcFlowType, boolean z2, RealtimeNfcEvents realtimeNfcEvents, final ObservableEmitter emitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(nfcTagRetries, "$nfcTagRetries");
        Intrinsics.checkNotNullParameter(passportBACKey, "$passportBACKey");
        Intrinsics.checkNotNullParameter(tag, "$tag");
        Intrinsics.checkNotNullParameter(fileIDs, "$fileIDs");
        Intrinsics.checkNotNullParameter(nfcFlowType, "$nfcFlowType");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        final long currentTimestamp = this$0.timeProvider.getCurrentTimestamp();
        PassportNfcReader passportNfcReader = this$0.passportReader;
        Observable<Tag> map = nfcTagRetries.map(new Function() { // from class: com.onfido.android.sdk.capture.internal.nfc.NfcInteractorImpl$readNfcTag$1$disposable$1
            @Override // io.reactivex.rxjava3.functions.Function
            public final Tag apply(NfcTagDelegate it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getTag();
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        final Disposable disposableSubscribe = passportNfcReader.readNfcTag(map, passportBACKey, bArr, tag.getTag(), fileIDs, number, z, nfcFlowType, z2, realtimeNfcEvents).retry(this$0.nfcTimeouts.nfcScanRetryCount(), new Predicate() { // from class: com.onfido.android.sdk.capture.internal.nfc.NfcInteractorImpl$readNfcTag$1$disposable$2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                emitter.onNext(Retrying.INSTANCE);
                return true;
            }
        }).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.internal.nfc.NfcInteractorImpl$readNfcTag$1$disposable$3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(PassportNfcExtractionState result) {
                Intrinsics.checkNotNullParameter(result, "result");
                if (result instanceof PassportNfcExtractionState.Reading) {
                    emitter.onNext(new Reading(((PassportNfcExtractionState.Reading) result).getProgress()));
                    return;
                }
                if (result instanceof PassportNfcExtractionState.Success) {
                    PassportNfcExtractionState.Success success = (PassportNfcExtractionState.Success) result;
                    emitter.onNext(new Success(success.getData(), success.getAuthAccess().getHasPaceAuthSucceeded() ? NfcFlowType.PACE : NfcFlowType.BAC, this$0.getNfcChipReadDuration(currentTimestamp)));
                    emitter.onComplete();
                } else if (!(result instanceof PassportNfcExtractionState.Failed)) {
                    if (result instanceof PassportNfcExtractionState.ConnectionLost) {
                        emitter.onNext(ConnectionLost.INSTANCE);
                    }
                } else {
                    PassportNfcExtractionState.Failed failed = (PassportNfcExtractionState.Failed) result;
                    PassportAuthAccess authAccess = failed.getAuthAccess();
                    this$0.trackAuthError(authAccess);
                    emitter.onNext(new Error(failed.getMessage(), authAccess));
                }
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.internal.nfc.NfcInteractorImpl$readNfcTag$1$disposable$4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable error) {
                Intrinsics.checkNotNullParameter(error, "error");
                String message = error.getMessage();
                if (message == null) {
                    message = "";
                }
                Timber.INSTANCE.e("NFC Logger - readNfcTag - " + message, new Object[0]);
                emitter.onNext(new Error(message, null, 2, null));
                emitter.onComplete();
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        emitter.setCancellable(new Cancellable() { // from class: com.onfido.android.sdk.capture.internal.nfc.NfcInteractorImpl$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                disposableSubscribe.dispose();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void trackAuthError(PassportAuthAccess auth) {
        if (!auth.getHasPaceAuthSucceeded() && auth.getPaceException() != null) {
            NfcTracker nfcTracker = this.nfcTracker;
            String message = auth.getPaceException().getMessage();
            if (message == null) {
                message = "PACE error - no further details";
            }
            nfcTracker.trackPaceErrorEvent$onfido_capture_sdk_core_release(message, auth.getCardAccessFileString(), auth.getUsedSecurityInfoString());
        }
        if (auth.getHasBacAuthSucceeded() || auth.getBacException() == null) {
            return;
        }
        NfcTracker nfcTracker2 = this.nfcTracker;
        String message2 = auth.getBacException().getMessage();
        if (message2 == null) {
            message2 = "BAC error - no further details";
        }
        nfcTracker2.trackBacAuthenticationError$onfido_capture_sdk_core_release(message2);
    }

    @Override // com.onfido.android.sdk.capture.internal.nfc.NfcInteractor
    public void disableReaderMode(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Timber.Companion companion = Timber.INSTANCE;
        companion.i("NFC Logger - Attempting to disable reader mode", new Object[0]);
        NfcAdapter nfcAdapter = this.nfcAdapter;
        if (nfcAdapter != null) {
            nfcAdapter.disableReaderMode(activity);
        }
        companion.i("NFC Logger - reader mode disabled", new Object[0]);
    }

    @Override // com.onfido.android.sdk.capture.internal.nfc.NfcInteractor
    public void enableReaderMode(Activity activity, final Function1<? super NfcTagDelegate, Unit> tagReadCallback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(tagReadCallback, "tagReadCallback");
        Bundle bundleBundleOf = BundleKt.bundleOf(TuplesKt.to("presence", 1000));
        NfcAdapter nfcAdapter = this.nfcAdapter;
        if (nfcAdapter != null) {
            nfcAdapter.enableReaderMode(activity, new NfcAdapter.ReaderCallback() { // from class: com.onfido.android.sdk.capture.internal.nfc.NfcInteractorImpl$$ExternalSyntheticLambda1
                @Override // android.nfc.NfcAdapter.ReaderCallback
                public final void onTagDiscovered(Tag tag) {
                    NfcInteractorImpl.enableReaderMode$lambda$0(tagReadCallback, tag);
                }
            }, 131, bundleBundleOf);
        }
    }

    @Override // com.onfido.android.sdk.capture.internal.nfc.NfcInteractor
    public boolean isNfcEnabled() {
        NfcAdapter nfcAdapter = this.nfcAdapter;
        if (nfcAdapter != null) {
            return nfcAdapter.isEnabled();
        }
        return false;
    }

    @Override // com.onfido.android.sdk.capture.internal.nfc.NfcInteractor
    public boolean isNfcSupported() {
        return this.nfcAdapter != null;
    }

    @Override // com.onfido.android.sdk.capture.internal.nfc.NfcInteractor
    public Observable<NfcReadState> readNfcTag(final Observable<NfcTagDelegate> nfcTagRetries, final NfcTagDelegate tag, final PassportBACKey passportBACKey, final byte[] aaChallenge, final MRTDDataGroup[] fileIDs, final Number canNumber, final boolean isPaceEnabled, final NfcFlowType nfcFlowType, final boolean chipAuthentication, final RealtimeNfcEvents realtimeNfcEvents) {
        Intrinsics.checkNotNullParameter(nfcTagRetries, "nfcTagRetries");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(passportBACKey, "passportBACKey");
        Intrinsics.checkNotNullParameter(fileIDs, "fileIDs");
        Intrinsics.checkNotNullParameter(nfcFlowType, "nfcFlowType");
        Observable<NfcReadState> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.onfido.android.sdk.capture.internal.nfc.NfcInteractorImpl$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                NfcInteractorImpl.readNfcTag$lambda$1(this.f$0, nfcTagRetries, passportBACKey, aaChallenge, tag, fileIDs, canNumber, isPaceEnabled, nfcFlowType, chipAuthentication, realtimeNfcEvents, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create(...)");
        return observableCreate;
    }

    @Override // com.onfido.android.sdk.capture.internal.nfc.NfcInteractor
    public void redirectToNfcSetting(ActivityResultLauncher<Intent> launcher) {
        Intrinsics.checkNotNullParameter(launcher, "launcher");
        launcher.launch(new Intent("android.settings.NFC_SETTINGS"));
    }
}
