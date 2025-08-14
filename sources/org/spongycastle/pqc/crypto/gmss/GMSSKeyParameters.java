package org.spongycastle.pqc.crypto.gmss;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes7.dex */
public class GMSSKeyParameters extends AsymmetricKeyParameter {
    private GMSSParameters params;

    public GMSSParameters getParameters() {
        return this.params;
    }

    public GMSSKeyParameters(boolean z, GMSSParameters gMSSParameters) {
        super(z);
        this.params = gMSSParameters;
    }
}
