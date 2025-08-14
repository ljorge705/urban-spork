package com.onfido.android.sdk.capture.internal.nfc;

import com.onfido.javax.inject.Inject;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\u0007\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0004¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/nfc/NfcTimeouts;", "", "()V", "nfcScanDelayMs", "", "nfcScanRetryCount", "nfcScanTagDelayMs", "nfcScanTimeoutMs", "successScanTimeoutMs", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcTimeouts {
    @Inject
    public NfcTimeouts() {
    }

    public final long nfcScanDelayMs() {
        return 5000L;
    }

    public final long nfcScanRetryCount() {
        return 10L;
    }

    public final long nfcScanTagDelayMs() {
        return 1000L;
    }

    public final long nfcScanTimeoutMs() {
        return 60000L;
    }

    public final long successScanTimeoutMs() {
        return 5000L;
    }
}
