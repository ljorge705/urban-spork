package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CryptoServicePurpose;

/* loaded from: classes4.dex */
class Utils {
    Utils() {
    }

    static CryptoServicePurpose getPurpose(boolean z) {
        return z ? CryptoServicePurpose.ENCRYPTION : CryptoServicePurpose.DECRYPTION;
    }
}
