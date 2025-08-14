package org.spongycastle.jce.spec;

import java.security.spec.KeySpec;

/* loaded from: classes7.dex */
public class ElGamalKeySpec implements KeySpec {
    private ElGamalParameterSpec spec;

    public ElGamalParameterSpec getParams() {
        return this.spec;
    }

    public ElGamalKeySpec(ElGamalParameterSpec elGamalParameterSpec) {
        this.spec = elGamalParameterSpec;
    }
}
