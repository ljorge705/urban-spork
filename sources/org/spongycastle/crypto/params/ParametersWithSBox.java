package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

/* loaded from: classes7.dex */
public class ParametersWithSBox implements CipherParameters {
    private CipherParameters parameters;
    private byte[] sBox;

    public CipherParameters getParameters() {
        return this.parameters;
    }

    public byte[] getSBox() {
        return this.sBox;
    }

    public ParametersWithSBox(CipherParameters cipherParameters, byte[] bArr) {
        this.parameters = cipherParameters;
        this.sBox = bArr;
    }
}
