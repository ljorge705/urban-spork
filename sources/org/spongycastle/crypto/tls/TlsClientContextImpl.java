package org.spongycastle.crypto.tls;

import java.security.SecureRandom;

/* loaded from: classes7.dex */
class TlsClientContextImpl extends AbstractTlsContext implements TlsClientContext {
    @Override // org.spongycastle.crypto.tls.TlsContext
    public boolean isServer() {
        return false;
    }

    TlsClientContextImpl(SecureRandom secureRandom, SecurityParameters securityParameters) {
        super(secureRandom, securityParameters);
    }
}
