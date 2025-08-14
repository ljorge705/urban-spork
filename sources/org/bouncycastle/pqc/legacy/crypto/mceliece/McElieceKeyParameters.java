package org.bouncycastle.pqc.legacy.crypto.mceliece;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes4.dex */
public class McElieceKeyParameters extends AsymmetricKeyParameter {
    private McElieceParameters params;

    public McElieceKeyParameters(boolean z, McElieceParameters mcElieceParameters) {
        super(z);
        this.params = mcElieceParameters;
    }

    public McElieceParameters getParameters() {
        return this.params;
    }
}
