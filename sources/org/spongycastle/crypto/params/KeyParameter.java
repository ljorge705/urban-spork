package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

/* loaded from: classes7.dex */
public class KeyParameter implements CipherParameters {
    private byte[] key;

    public byte[] getKey() {
        return this.key;
    }

    public KeyParameter(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public KeyParameter(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        this.key = bArr2;
        System.arraycopy(bArr, i, bArr2, 0, i2);
    }
}
