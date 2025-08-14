package org.spongycastle.jce.spec;

import java.security.spec.AlgorithmParameterSpec;

/* loaded from: classes7.dex */
public class ECNamedCurveGenParameterSpec implements AlgorithmParameterSpec {
    private String name;

    public String getName() {
        return this.name;
    }

    public ECNamedCurveGenParameterSpec(String str) {
        this.name = str;
    }
}
