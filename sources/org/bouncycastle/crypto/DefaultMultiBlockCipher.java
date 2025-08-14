package org.bouncycastle.crypto;

/* loaded from: classes4.dex */
public abstract class DefaultMultiBlockCipher implements MultiBlockCipher {
    protected DefaultMultiBlockCipher() {
    }

    @Override // org.bouncycastle.crypto.MultiBlockCipher
    public int getMultiBlockSize() {
        return getBlockSize();
    }

    @Override // org.bouncycastle.crypto.MultiBlockCipher
    public int processBlocks(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        int multiBlockSize = getMultiBlockSize();
        int iProcessBlock = 0;
        for (int i4 = 0; i4 != i2; i4++) {
            iProcessBlock += processBlock(bArr, i, bArr2, i3 + iProcessBlock);
            i += multiBlockSize;
        }
        return iProcessBlock;
    }
}
