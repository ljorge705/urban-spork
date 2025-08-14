package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;

/* loaded from: classes4.dex */
public class NullEngine implements BlockCipher {
    protected static final int DEFAULT_BLOCK_SIZE = 1;
    private final int blockSize;
    private boolean initialised;

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Null";
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.blockSize;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.initialised = true;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
    }

    public NullEngine() {
        this(1);
    }

    public NullEngine(int i) {
        this.blockSize = i;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws IllegalStateException, DataLengthException {
        if (!this.initialised) {
            throw new IllegalStateException("Null engine not initialised");
        }
        int i3 = this.blockSize;
        if (i + i3 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int i4 = 0;
        while (true) {
            int i5 = this.blockSize;
            if (i4 >= i5) {
                return i5;
            }
            bArr2[i2 + i4] = bArr[i + i4];
            i4++;
        }
    }
}
