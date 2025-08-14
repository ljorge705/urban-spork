package org.spongycastle.pqc.crypto;

import org.spongycastle.crypto.CipherParameters;

/* loaded from: classes7.dex */
public interface MessageSigner {
    byte[] generateSignature(byte[] bArr);

    void init(boolean z, CipherParameters cipherParameters);

    boolean verifySignature(byte[] bArr, byte[] bArr2);
}
