package org.spongycastle.pqc.crypto.ntru;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes7.dex */
public class NTRUEncryptionKeyParameters extends AsymmetricKeyParameter {
    protected final NTRUEncryptionParameters params;

    public NTRUEncryptionParameters getParameters() {
        return this.params;
    }

    public NTRUEncryptionKeyParameters(boolean z, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(z);
        this.params = nTRUEncryptionParameters;
    }
}
