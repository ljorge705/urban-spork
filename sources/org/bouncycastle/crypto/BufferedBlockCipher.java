package org.bouncycastle.crypto;

/* loaded from: classes4.dex */
public class BufferedBlockCipher {
    protected byte[] buf;
    protected int bufOff;
    protected BlockCipher cipher;
    protected boolean forEncryption;
    protected MultiBlockCipher mbCipher;
    protected boolean partialBlockOkay;
    protected boolean pgpCFB;

    BufferedBlockCipher() {
    }

    public BufferedBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        if (blockCipher instanceof MultiBlockCipher) {
            MultiBlockCipher multiBlockCipher = (MultiBlockCipher) blockCipher;
            this.mbCipher = multiBlockCipher;
            this.buf = new byte[multiBlockCipher.getMultiBlockSize()];
        } else {
            this.mbCipher = null;
            this.buf = new byte[blockCipher.getBlockSize()];
        }
        boolean z = false;
        this.bufOff = 0;
        String algorithmName = blockCipher.getAlgorithmName();
        int iIndexOf = algorithmName.indexOf(47) + 1;
        boolean z2 = iIndexOf > 0 && algorithmName.startsWith("PGP", iIndexOf);
        this.pgpCFB = z2;
        if (z2 || (blockCipher instanceof StreamCipher)) {
            this.partialBlockOkay = true;
            return;
        }
        if (iIndexOf > 0 && algorithmName.startsWith("OpenPGP", iIndexOf)) {
            z = true;
        }
        this.partialBlockOkay = z;
    }

    public int doFinal(byte[] bArr, int i) throws InvalidCipherTextException, IllegalStateException, DataLengthException {
        try {
            int i2 = this.bufOff;
            if (i + i2 > bArr.length) {
                throw new OutputLengthException("output buffer too short for doFinal()");
            }
            int i3 = 0;
            if (i2 != 0) {
                if (!this.partialBlockOkay) {
                    throw new DataLengthException("data not block size aligned");
                }
                BlockCipher blockCipher = this.cipher;
                byte[] bArr2 = this.buf;
                blockCipher.processBlock(bArr2, 0, bArr2, 0);
                int i4 = this.bufOff;
                this.bufOff = 0;
                System.arraycopy(this.buf, 0, bArr, i, i4);
                i3 = i4;
            }
            return i3;
        } finally {
            reset();
        }
    }

    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    public int getOutputSize(int i) {
        int blockSize;
        if (this.pgpCFB && this.forEncryption) {
            i += this.bufOff;
            blockSize = this.cipher.getBlockSize() + 2;
        } else {
            blockSize = this.bufOff;
        }
        return i + blockSize;
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    public int getUpdateOutputSize(int i) {
        int length;
        int i2 = i + this.bufOff;
        if (this.pgpCFB && this.forEncryption) {
            length = (i2 % this.buf.length) - (this.cipher.getBlockSize() + 2);
            return i2 - length;
        }
        int length2 = this.buf.length;
        length = i2 % length2;
        return i2 - length;
    }

    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.forEncryption = z;
        reset();
        this.cipher.init(z, cipherParameters);
    }

    public int processByte(byte b, byte[] bArr, int i) throws IllegalStateException, DataLengthException {
        byte[] bArr2 = this.buf;
        int i2 = this.bufOff;
        int i3 = i2 + 1;
        this.bufOff = i3;
        bArr2[i2] = b;
        if (i3 != bArr2.length) {
            return 0;
        }
        int iProcessBlock = this.cipher.processBlock(bArr2, 0, bArr, i);
        this.bufOff = 0;
        return iProcessBlock;
    }

    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws IllegalStateException, DataLengthException {
        int i4;
        int i5;
        int iProcessBlock;
        if (i2 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int blockSize = getBlockSize();
        int updateOutputSize = getUpdateOutputSize(i2);
        if (updateOutputSize > 0 && updateOutputSize + i3 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        byte[] bArr3 = this.buf;
        int length = bArr3.length;
        int i6 = this.bufOff;
        int i7 = length - i6;
        if (i2 > i7) {
            System.arraycopy(bArr, i, bArr3, i6, i7);
            iProcessBlock = this.cipher.processBlock(this.buf, 0, bArr2, i3);
            this.bufOff = 0;
            i5 = i2 - i7;
            i4 = i + i7;
            MultiBlockCipher multiBlockCipher = this.mbCipher;
            if (multiBlockCipher != null) {
                int multiBlockSize = i5 / multiBlockCipher.getMultiBlockSize();
                if (multiBlockSize > 0) {
                    iProcessBlock += this.mbCipher.processBlocks(bArr, i4, multiBlockSize, bArr2, i3 + iProcessBlock);
                    int multiBlockSize2 = multiBlockSize * this.mbCipher.getMultiBlockSize();
                    i5 -= multiBlockSize2;
                    i4 += multiBlockSize2;
                }
            } else {
                while (i5 > this.buf.length) {
                    iProcessBlock += this.cipher.processBlock(bArr, i4, bArr2, i3 + iProcessBlock);
                    i5 -= blockSize;
                    i4 += blockSize;
                }
            }
        } else {
            i4 = i;
            i5 = i2;
            iProcessBlock = 0;
        }
        System.arraycopy(bArr, i4, this.buf, this.bufOff, i5);
        int i8 = this.bufOff + i5;
        this.bufOff = i8;
        byte[] bArr4 = this.buf;
        if (i8 != bArr4.length) {
            return iProcessBlock;
        }
        int iProcessBlock2 = iProcessBlock + this.cipher.processBlock(bArr4, 0, bArr2, i3 + iProcessBlock);
        this.bufOff = 0;
        return iProcessBlock2;
    }

    public void reset() {
        int i = 0;
        while (true) {
            byte[] bArr = this.buf;
            if (i >= bArr.length) {
                this.bufOff = 0;
                this.cipher.reset();
                return;
            } else {
                bArr[i] = 0;
                i++;
            }
        }
    }
}
