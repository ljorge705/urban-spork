package org.spongycastle.operator;

import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes7.dex */
public abstract class AsymmetricKeyUnwrapper implements KeyUnwrapper {
    private AlgorithmIdentifier algorithmId;

    @Override // org.spongycastle.operator.KeyUnwrapper
    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return this.algorithmId;
    }

    protected AsymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier) {
        this.algorithmId = algorithmIdentifier;
    }
}
