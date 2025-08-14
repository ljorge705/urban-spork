package com.onfido.android.sdk.capture.internal.nfc;

import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\n\u001a\u00020\u0003H&J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007H&J\u001a\u0010\u000f\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/RealtimeNfcEvents;", "", "accessControlFailed", "", KeychainModule.Maps.ACCESS_CONTROL, "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/NfcFlowType;", "throwable", "", "accessControlFinished", "accessControlStarted", "documentDetected", "verificationFailed", "type", "Lcom/onfido/android/sdk/capture/internal/nfc/OnfidoVerificationType;", "error", "verificationFinished", OnfidoLauncher.KEY_RESULT, "", "verificationStarted", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface RealtimeNfcEvents {
    void accessControlFailed(NfcFlowType accessControl, Throwable throwable);

    void accessControlFinished(NfcFlowType accessControl);

    void accessControlStarted(NfcFlowType accessControl);

    void documentDetected();

    void verificationFailed(OnfidoVerificationType type, Throwable error);

    void verificationFinished(OnfidoVerificationType type, byte[] result);

    void verificationStarted(OnfidoVerificationType type);
}
