package org.spongycastle.pqc.crypto.gmss;

/* loaded from: classes7.dex */
public class GMSSPublicKeyParameters extends GMSSKeyParameters {
    private byte[] gmssPublicKey;

    public byte[] getPublicKey() {
        return this.gmssPublicKey;
    }

    public GMSSPublicKeyParameters(byte[] bArr, GMSSParameters gMSSParameters) {
        super(false, gMSSParameters);
        this.gmssPublicKey = bArr;
    }
}
