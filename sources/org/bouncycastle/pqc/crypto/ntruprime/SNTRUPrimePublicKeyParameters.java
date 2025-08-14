package org.bouncycastle.pqc.crypto.ntruprime;

import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class SNTRUPrimePublicKeyParameters extends SNTRUPrimeKeyParameters {
    private final byte[] encH;

    public SNTRUPrimePublicKeyParameters(SNTRUPrimeParameters sNTRUPrimeParameters, byte[] bArr) {
        super(false, sNTRUPrimeParameters);
        this.encH = Arrays.clone(bArr);
    }

    byte[] getEncH() {
        return this.encH;
    }

    public byte[] getEncoded() {
        return Arrays.clone(this.encH);
    }
}
