package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes7.dex */
public class McElieceKeyParameters extends AsymmetricKeyParameter {
    private McElieceParameters params;

    public McElieceParameters getParameters() {
        return this.params;
    }

    public McElieceKeyParameters(boolean z, McElieceParameters mcElieceParameters) {
        super(z);
        this.params = mcElieceParameters;
    }
}
