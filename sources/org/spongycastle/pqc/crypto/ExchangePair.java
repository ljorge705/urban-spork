package org.spongycastle.pqc.crypto;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.util.Arrays;

/* loaded from: classes7.dex */
public class ExchangePair {
    private final AsymmetricKeyParameter publicKey;
    private final byte[] shared;

    public AsymmetricKeyParameter getPublicKey() {
        return this.publicKey;
    }

    public ExchangePair(AsymmetricKeyParameter asymmetricKeyParameter, byte[] bArr) {
        this.publicKey = asymmetricKeyParameter;
        this.shared = Arrays.clone(bArr);
    }

    public byte[] getSharedValue() {
        return Arrays.clone(this.shared);
    }
}
