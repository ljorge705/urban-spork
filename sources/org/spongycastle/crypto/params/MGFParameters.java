package org.spongycastle.crypto.params;

import org.spongycastle.crypto.DerivationParameters;

/* loaded from: classes7.dex */
public class MGFParameters implements DerivationParameters {
    byte[] seed;

    public byte[] getSeed() {
        return this.seed;
    }

    public MGFParameters(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public MGFParameters(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        this.seed = bArr2;
        System.arraycopy(bArr, i, bArr2, 0, i2);
    }
}
