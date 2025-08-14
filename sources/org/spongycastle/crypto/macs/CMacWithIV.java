package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;

/* loaded from: classes7.dex */
public class CMacWithIV extends CMac {
    @Override // org.spongycastle.crypto.macs.CMac
    void validate(CipherParameters cipherParameters) {
    }

    public CMacWithIV(BlockCipher blockCipher) {
        super(blockCipher);
    }

    public CMacWithIV(BlockCipher blockCipher, int i) {
        super(blockCipher, i);
    }
}
