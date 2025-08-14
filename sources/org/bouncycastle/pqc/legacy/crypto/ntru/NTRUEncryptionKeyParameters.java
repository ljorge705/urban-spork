package org.bouncycastle.pqc.legacy.crypto.ntru;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes4.dex */
public class NTRUEncryptionKeyParameters extends AsymmetricKeyParameter {
    protected final NTRUEncryptionParameters params;

    public NTRUEncryptionKeyParameters(boolean z, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(z);
        this.params = nTRUEncryptionParameters;
    }

    public NTRUEncryptionParameters getParameters() {
        return this.params;
    }
}
