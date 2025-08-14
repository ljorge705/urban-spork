package org.spongycastle.pqc.crypto;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;

/* loaded from: classes7.dex */
public interface MessageEncryptor {
    void init(boolean z, CipherParameters cipherParameters);

    byte[] messageDecrypt(byte[] bArr) throws InvalidCipherTextException;

    byte[] messageEncrypt(byte[] bArr);
}
