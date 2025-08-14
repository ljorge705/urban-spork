package org.spongycastle.crypto.params;

import org.spongycastle.crypto.DerivationParameters;

/* loaded from: classes7.dex */
public class ISO18033KDFParameters implements DerivationParameters {
    byte[] seed;

    public byte[] getSeed() {
        return this.seed;
    }

    public ISO18033KDFParameters(byte[] bArr) {
        this.seed = bArr;
    }
}
