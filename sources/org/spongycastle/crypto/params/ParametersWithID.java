package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

/* loaded from: classes7.dex */
public class ParametersWithID implements CipherParameters {
    private byte[] id;
    private CipherParameters parameters;

    public byte[] getID() {
        return this.id;
    }

    public CipherParameters getParameters() {
        return this.parameters;
    }

    public ParametersWithID(CipherParameters cipherParameters, byte[] bArr) {
        this.parameters = cipherParameters;
        this.id = bArr;
    }
}
