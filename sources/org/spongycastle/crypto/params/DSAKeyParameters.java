package org.spongycastle.crypto.params;

/* loaded from: classes7.dex */
public class DSAKeyParameters extends AsymmetricKeyParameter {
    private DSAParameters params;

    public DSAParameters getParameters() {
        return this.params;
    }

    public DSAKeyParameters(boolean z, DSAParameters dSAParameters) {
        super(z);
        this.params = dSAParameters;
    }
}
