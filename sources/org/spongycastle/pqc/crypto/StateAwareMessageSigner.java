package org.spongycastle.pqc.crypto;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes7.dex */
public interface StateAwareMessageSigner extends MessageSigner {
    AsymmetricKeyParameter getUpdatedPrivateKey();
}
