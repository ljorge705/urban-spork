package org.spongycastle.crypto.tls;

/* loaded from: classes7.dex */
public abstract class ServerOnlyTlsAuthentication implements TlsAuthentication {
    @Override // org.spongycastle.crypto.tls.TlsAuthentication
    public final TlsCredentials getClientCredentials(CertificateRequest certificateRequest) {
        return null;
    }
}
