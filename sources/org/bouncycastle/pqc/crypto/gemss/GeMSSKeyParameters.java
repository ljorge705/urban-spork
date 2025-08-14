package org.bouncycastle.pqc.crypto.gemss;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes4.dex */
public class GeMSSKeyParameters extends AsymmetricKeyParameter {
    final GeMSSParameters parameters;

    protected GeMSSKeyParameters(boolean z, GeMSSParameters geMSSParameters) {
        super(z);
        this.parameters = geMSSParameters;
    }

    public GeMSSParameters getParameters() {
        return this.parameters;
    }
}
