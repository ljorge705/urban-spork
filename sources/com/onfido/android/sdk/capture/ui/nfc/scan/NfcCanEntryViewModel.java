package com.onfido.android.sdk.capture.ui.nfc.scan;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.facebook.hermes.intl.Constants;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.utils.StringExtensionsKt;
import com.onfido.dagger.assisted.Assisted;
import com.onfido.dagger.assisted.AssistedFactory;
import com.onfido.dagger.assisted.AssistedInject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001b\u001cB!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0010¢\u0006\u0002\u0010\u0014J\u001d\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010\u0019J\u000e\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\nR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcCanEntryViewModel;", "Landroidx/lifecycle/ViewModel;", "screenTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;", "nfcTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/ScreenTracker;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;Landroidx/lifecycle/SavedStateHandle;)V", "canLength", "", "getCanLength", "()I", "setCanLength", "(I)V", "knownCanNumberKey", "", "isCanInputValid", "", "canInput", "(Ljava/lang/String;)Ljava/lang/Long;", "onContinueButtonClicked", "", "numberOfAttempts", "instantTextInputFocusWasEntered", "(ILjava/lang/Long;)V", "onViewCreated", "Companion", "Factory", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcCanEntryViewModel extends ViewModel {
    private static final Companion Companion = new Companion(null);
    private static final String NFC_LOGGER = "NFC Logger";
    private static final int VALID_CAN_LENGTH = 12;
    private int canLength;
    private final String knownCanNumberKey;
    private final NfcTracker nfcTracker;
    private final SavedStateHandle savedStateHandle;
    private final ScreenTracker screenTracker;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcCanEntryViewModel$Companion;", "", "()V", "NFC_LOGGER", "", "VALID_CAN_LENGTH", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @AssistedFactory
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcCanEntryViewModel$Factory;", "", "create", "Lcom/onfido/android/sdk/capture/ui/nfc/scan/NfcCanEntryViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        NfcCanEntryViewModel create(SavedStateHandle savedStateHandle);
    }

    @AssistedInject
    public NfcCanEntryViewModel(ScreenTracker screenTracker, NfcTracker nfcTracker, @Assisted SavedStateHandle savedStateHandle) {
        Intrinsics.checkNotNullParameter(screenTracker, "screenTracker");
        Intrinsics.checkNotNullParameter(nfcTracker, "nfcTracker");
        Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
        this.screenTracker = screenTracker;
        this.nfcTracker = nfcTracker;
        this.savedStateHandle = savedStateHandle;
        Integer num = (Integer) savedStateHandle.get(NfcCanEntryFragment.CAN_LENGTH);
        this.canLength = num != null ? num.intValue() : 12;
        this.knownCanNumberKey = (String) savedStateHandle.get(NfcCanEntryFragment.KNOWN_CAN_NUMBER_KEY);
    }

    public final int getCanLength() {
        return this.canLength;
    }

    public final Long isCanInputValid(String canInput) {
        Intrinsics.checkNotNullParameter(canInput, "canInput");
        String strReplace = new Regex("\\s").replace(canInput, "");
        Long longOrNull = StringsKt.toLongOrNull(strReplace);
        boolean z = longOrNull != null && strReplace.length() == this.canLength;
        Timber.INSTANCE.d("NFC Logger - CAN Input is " + (z ? "valid" : Constants.COLLATION_INVALID) + ' ', new Object[0]);
        if (!z) {
            return null;
        }
        Intrinsics.checkNotNull(longOrNull);
        return longOrNull;
    }

    public final void onContinueButtonClicked(int numberOfAttempts, Long instantTextInputFocusWasEntered) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.nfcTracker.trackCanEntryCompleted$onfido_capture_sdk_core_release(numberOfAttempts, jCurrentTimeMillis - (instantTextInputFocusWasEntered != null ? instantTextInputFocusWasEntered.longValue() : jCurrentTimeMillis));
    }

    public final void onViewCreated(int numberOfAttempts) {
        this.screenTracker.trackNfcCanEntry$onfido_capture_sdk_core_release(StringExtensionsKt.isNotNullOrEmpty(this.knownCanNumberKey), this.canLength, numberOfAttempts);
    }

    public final void setCanLength(int i) {
        this.canLength = i;
    }
}
