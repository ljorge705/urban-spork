package org.spongycastle.pqc.crypto;

import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes7.dex */
public class DigestingStateAwareMessageSigner extends DigestingMessageSigner {
    private final StateAwareMessageSigner signer;

    public DigestingStateAwareMessageSigner(StateAwareMessageSigner stateAwareMessageSigner, Digest digest) {
        super(stateAwareMessageSigner, digest);
        this.signer = stateAwareMessageSigner;
    }

    public AsymmetricKeyParameter getUpdatedPrivateKey() {
        return this.signer.getUpdatedPrivateKey();
    }
}
