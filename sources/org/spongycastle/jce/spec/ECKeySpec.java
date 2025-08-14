package org.spongycastle.jce.spec;

import java.security.spec.KeySpec;

/* loaded from: classes7.dex */
public class ECKeySpec implements KeySpec {
    private ECParameterSpec spec;

    public ECParameterSpec getParams() {
        return this.spec;
    }

    protected ECKeySpec(ECParameterSpec eCParameterSpec) {
        this.spec = eCParameterSpec;
    }
}
