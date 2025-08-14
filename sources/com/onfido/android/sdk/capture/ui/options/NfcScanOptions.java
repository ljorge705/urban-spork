package com.onfido.android.sdk.capture.ui.options;

import com.onfido.android.sdk.capture.internal.nfc.data.PassportBACKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/options/NfcScanOptions;", "Lcom/onfido/android/sdk/capture/ui/options/BaseOptions;", "passportBACKey", "Lcom/onfido/android/sdk/capture/internal/nfc/data/PassportBACKey;", "aaChallenge", "", "(Lcom/onfido/android/sdk/capture/internal/nfc/data/PassportBACKey;[B)V", "getAaChallenge", "()[B", "getPassportBACKey", "()Lcom/onfido/android/sdk/capture/internal/nfc/data/PassportBACKey;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcScanOptions extends BaseOptions {
    private final byte[] aaChallenge;
    private final PassportBACKey passportBACKey;

    public NfcScanOptions(PassportBACKey passportBACKey, byte[] bArr) {
        Intrinsics.checkNotNullParameter(passportBACKey, "passportBACKey");
        this.passportBACKey = passportBACKey;
        this.aaChallenge = bArr;
    }

    public final byte[] getAaChallenge() {
        return this.aaChallenge;
    }

    public final PassportBACKey getPassportBACKey() {
        return this.passportBACKey;
    }
}
