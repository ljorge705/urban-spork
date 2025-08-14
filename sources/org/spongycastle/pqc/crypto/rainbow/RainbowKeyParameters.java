package org.spongycastle.pqc.crypto.rainbow;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes7.dex */
public class RainbowKeyParameters extends AsymmetricKeyParameter {
    private int docLength;

    public int getDocLength() {
        return this.docLength;
    }

    public RainbowKeyParameters(boolean z, int i) {
        super(z);
        this.docLength = i;
    }
}
