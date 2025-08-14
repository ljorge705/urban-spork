package org.spongycastle.operator;

import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes7.dex */
public abstract class SymmetricKeyWrapper implements KeyWrapper {
    private AlgorithmIdentifier algorithmId;

    @Override // org.spongycastle.operator.KeyWrapper
    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return this.algorithmId;
    }

    protected SymmetricKeyWrapper(AlgorithmIdentifier algorithmIdentifier) {
        this.algorithmId = algorithmIdentifier;
    }
}
