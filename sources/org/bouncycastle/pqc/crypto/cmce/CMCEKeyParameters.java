package org.bouncycastle.pqc.crypto.cmce;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes4.dex */
public class CMCEKeyParameters extends AsymmetricKeyParameter {
    private CMCEParameters params;

    public CMCEKeyParameters(boolean z, CMCEParameters cMCEParameters) {
        super(z);
        this.params = cMCEParameters;
    }

    public CMCEParameters getParameters() {
        return this.params;
    }
}
