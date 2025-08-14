package org.spongycastle.crypto.tls;

import java.io.IOException;

/* loaded from: classes7.dex */
public interface TlsEncryptionCredentials extends TlsCredentials {
    byte[] decryptPreMasterSecret(byte[] bArr) throws IOException;
}
