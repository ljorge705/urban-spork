package org.spongycastle.crypto.tls;

/* loaded from: classes7.dex */
public abstract class AbstractTlsSignerCredentials extends AbstractTlsCredentials implements TlsSignerCredentials {
    @Override // org.spongycastle.crypto.tls.TlsSignerCredentials
    public SignatureAndHashAlgorithm getSignatureAndHashAlgorithm() {
        throw new IllegalStateException("TlsSignerCredentials implementation does not support (D)TLS 1.2+");
    }
}
