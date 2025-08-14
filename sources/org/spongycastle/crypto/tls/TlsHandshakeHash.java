package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.Digest;

/* loaded from: classes7.dex */
public interface TlsHandshakeHash extends Digest {
    Digest forkPRFHash();

    byte[] getFinalHash(short s);

    void init(TlsContext tlsContext);

    TlsHandshakeHash notifyPRFDetermined();

    void sealHashAlgorithms();

    TlsHandshakeHash stopTracking();

    void trackHashAlgorithm(short s);
}
