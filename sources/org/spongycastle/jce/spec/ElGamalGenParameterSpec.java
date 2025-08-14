package org.spongycastle.jce.spec;

import java.security.spec.AlgorithmParameterSpec;

/* loaded from: classes7.dex */
public class ElGamalGenParameterSpec implements AlgorithmParameterSpec {
    private int primeSize;

    public int getPrimeSize() {
        return this.primeSize;
    }

    public ElGamalGenParameterSpec(int i) {
        this.primeSize = i;
    }
}
