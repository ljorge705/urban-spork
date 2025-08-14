package org.spongycastle.jcajce.spec;

import javax.crypto.SecretKey;

/* loaded from: classes7.dex */
public class RepeatedSecretKeySpec implements SecretKey {
    private String algorithm;

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.algorithm;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return null;
    }

    @Override // java.security.Key
    public String getFormat() {
        return null;
    }

    public RepeatedSecretKeySpec(String str) {
        this.algorithm = str;
    }
}
