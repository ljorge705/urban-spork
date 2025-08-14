package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;

/* loaded from: classes4.dex */
public abstract class SerpentEngineBase implements BlockCipher {
    protected static final int BLOCK_SIZE = 16;
    static final int PHI = -1640531527;
    static final int ROUNDS = 32;
    protected boolean encrypting;
    protected int keyBits;
    protected int[] wKey;

    SerpentEngineBase() {
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 256));
    }

    private CryptoServicePurpose getPurpose() {
        return this.wKey == null ? CryptoServicePurpose.ANY : this.encrypting ? CryptoServicePurpose.ENCRYPTION : CryptoServicePurpose.DECRYPTION;
    }

    protected static int rotateLeft(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    protected static int rotateRight(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    protected final void LT(int[] iArr) {
        int iRotateLeft = rotateLeft(iArr[0], 13);
        int iRotateLeft2 = rotateLeft(iArr[2], 3);
        int i = (iArr[1] ^ iRotateLeft) ^ iRotateLeft2;
        int i2 = (iArr[3] ^ iRotateLeft2) ^ (iRotateLeft << 3);
        iArr[1] = rotateLeft(i, 1);
        int iRotateLeft3 = rotateLeft(i2, 7);
        iArr[3] = iRotateLeft3;
        iArr[0] = rotateLeft((iRotateLeft ^ iArr[1]) ^ iRotateLeft3, 5);
        iArr[2] = rotateLeft((iArr[3] ^ iRotateLeft2) ^ (iArr[1] << 7), 22);
    }

    protected abstract void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2);

    protected abstract void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2);

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Serpent";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    protected final void ib0(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i2 ^ i;
        int i7 = (i5 | i6) ^ i4;
        int i8 = i3 ^ i7;
        int i9 = i6 ^ i8;
        iArr[2] = i9;
        int i10 = (i6 & i4) ^ i5;
        int i11 = (i9 & i10) ^ i7;
        iArr[1] = i11;
        int i12 = (i & i7) ^ (i11 | i8);
        iArr[3] = i12;
        iArr[0] = i12 ^ (i10 ^ i8);
    }

    protected final void ib1(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = i4 ^ i2;
        int i6 = i ^ (i2 & i5);
        int i7 = i5 ^ i6;
        int i8 = i3 ^ i7;
        iArr[3] = i8;
        int i9 = i2 ^ (i5 & i6);
        int i10 = i6 ^ (i8 | i9);
        iArr[1] = i10;
        int i11 = ~i10;
        int i12 = i9 ^ i8;
        iArr[0] = i11 ^ i12;
        iArr[2] = (i11 | i12) ^ i7;
    }

    protected final void ib2(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = i2 ^ i4;
        int i6 = ~i5;
        int i7 = i ^ i3;
        int i8 = i3 ^ i5;
        int i9 = (i2 & i8) ^ i7;
        iArr[0] = i9;
        int i10 = (((i | i6) ^ i4) | i7) ^ i5;
        iArr[3] = i10;
        int i11 = ~i8;
        int i12 = i10 | i9;
        iArr[1] = i11 ^ i12;
        iArr[2] = (i12 ^ i7) ^ (i4 & i11);
    }

    protected final void ib3(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = i | i2;
        int i6 = i2 ^ i3;
        int i7 = i ^ (i2 & i6);
        int i8 = i3 ^ i7;
        int i9 = i4 | i7;
        int i10 = i6 ^ i9;
        iArr[0] = i10;
        int i11 = (i9 | i6) ^ i4;
        iArr[2] = i8 ^ i11;
        int i12 = i5 ^ i11;
        int i13 = i7 ^ (i10 & i12);
        iArr[3] = i13;
        iArr[1] = i13 ^ (i12 ^ i10);
    }

    protected final void ib4(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = i2 ^ ((i3 | i4) & i);
        int i6 = i3 ^ (i & i5);
        int i7 = i4 ^ i6;
        iArr[1] = i7;
        int i8 = ~i;
        int i9 = (i6 & i7) ^ i5;
        iArr[3] = i9;
        int i10 = i4 ^ (i7 | i8);
        iArr[0] = i9 ^ i10;
        iArr[2] = (i8 ^ i7) ^ (i5 & i10);
    }

    protected final void ib5(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = ~i3;
        int i6 = (i2 & i5) ^ i4;
        int i7 = i & i6;
        int i8 = (i2 ^ i5) ^ i7;
        iArr[3] = i8;
        int i9 = i8 | i2;
        iArr[1] = i6 ^ (i & i9);
        int i10 = i4 | i;
        iArr[0] = (i5 ^ i9) ^ i10;
        iArr[2] = ((i ^ i3) | i7) ^ (i2 & i10);
    }

    protected final void ib6(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i ^ i2;
        int i7 = i3 ^ i6;
        int i8 = (i3 | i5) ^ i4;
        iArr[1] = i7 ^ i8;
        int i9 = i6 ^ (i7 & i8);
        int i10 = i8 ^ (i2 | i9);
        iArr[3] = i10;
        int i11 = i2 | i10;
        iArr[0] = i9 ^ i11;
        iArr[2] = (i4 & i5) ^ (i11 ^ i7);
    }

    protected final void ib7(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = (i & i2) | i3;
        int i6 = (i | i2) & i4;
        int i7 = i5 ^ i6;
        iArr[3] = i7;
        int i8 = i2 ^ i6;
        int i9 = ((i7 ^ (~i4)) | i8) ^ i;
        iArr[1] = i9;
        int i10 = (i8 ^ i3) ^ (i4 | i9);
        iArr[0] = i10;
        iArr[2] = ((i & i7) ^ i10) ^ (i5 ^ i9);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to " + getAlgorithmName() + " init - " + cipherParameters.getClass().getName());
        }
        this.encrypting = z;
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        this.wKey = makeWorkingKey(key);
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), key.length * 8, cipherParameters, getPurpose()));
    }

    protected final void inverseLT(int[] iArr) {
        int iRotateRight = (rotateRight(iArr[2], 22) ^ iArr[3]) ^ (iArr[1] << 7);
        int iRotateRight2 = rotateRight(iArr[0], 5) ^ iArr[1];
        int i = iArr[3];
        int i2 = iRotateRight2 ^ i;
        int iRotateRight3 = rotateRight(i, 7);
        int iRotateRight4 = rotateRight(iArr[1], 1);
        iArr[3] = (iRotateRight3 ^ iRotateRight) ^ (i2 << 3);
        iArr[1] = (iRotateRight4 ^ i2) ^ iRotateRight;
        iArr[2] = rotateRight(iRotateRight, 3);
        iArr[0] = rotateRight(i2, 13);
    }

    protected abstract int[] makeWorkingKey(byte[] bArr);

    @Override // org.bouncycastle.crypto.BlockCipher
    public final int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.wKey == null) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.encrypting) {
            encryptBlock(bArr, i, bArr2, i2);
            return 16;
        }
        decryptBlock(bArr, i, bArr2, i2);
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }

    protected final void sb0(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = i ^ i4;
        int i6 = i3 ^ i5;
        int i7 = i2 ^ i6;
        int i8 = (i4 & i) ^ i7;
        iArr[3] = i8;
        int i9 = i ^ (i2 & i5);
        iArr[2] = (i3 | i9) ^ i7;
        int i10 = (i6 ^ i9) & i8;
        iArr[1] = (~i6) ^ i10;
        iArr[0] = (~i9) ^ i10;
    }

    protected final void sb1(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = (~i) ^ i2;
        int i6 = (i | i5) ^ i3;
        int i7 = i4 ^ i6;
        iArr[2] = i7;
        int i8 = i2 ^ (i4 | i5);
        int i9 = i7 ^ i5;
        int i10 = (i6 & i8) ^ i9;
        iArr[3] = i10;
        int i11 = i8 ^ i6;
        iArr[1] = i10 ^ i11;
        iArr[0] = i6 ^ (i11 & i9);
    }

    protected final void sb2(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i2 ^ i4;
        int i7 = (i3 & i5) ^ i6;
        iArr[0] = i7;
        int i8 = i3 ^ i5;
        int i9 = i2 & (i3 ^ i7);
        int i10 = i8 ^ i9;
        iArr[3] = i10;
        int i11 = i ^ ((i9 | i4) & (i7 | i8));
        iArr[2] = i11;
        iArr[1] = (i11 ^ (i4 | i5)) ^ (i6 ^ i10);
    }

    protected final void sb3(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = i ^ i2;
        int i6 = i & i3;
        int i7 = i | i4;
        int i8 = i3 ^ i4;
        int i9 = i6 | (i5 & i7);
        int i10 = i8 ^ i9;
        iArr[2] = i10;
        int i11 = (i7 ^ i2) ^ i9;
        int i12 = i5 ^ (i8 & i11);
        iArr[0] = i12;
        int i13 = i12 & i10;
        iArr[1] = i11 ^ i13;
        iArr[3] = (i2 | i4) ^ (i8 ^ i13);
    }

    protected final void sb4(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = i ^ i4;
        int i6 = i3 ^ (i4 & i5);
        int i7 = i2 | i6;
        iArr[3] = i5 ^ i7;
        int i8 = ~i2;
        int i9 = (i5 | i8) ^ i6;
        iArr[0] = i9;
        int i10 = i8 ^ i5;
        int i11 = (i7 & i10) ^ (i9 & i);
        iArr[2] = i11;
        iArr[1] = (i ^ i6) ^ (i10 & i11);
    }

    protected final void sb5(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i ^ i2;
        int i7 = i ^ i4;
        int i8 = (i3 ^ i5) ^ (i6 | i7);
        iArr[0] = i8;
        int i9 = i4 & i8;
        int i10 = (i6 ^ i8) ^ i9;
        iArr[1] = i10;
        int i11 = i7 ^ (i8 | i5);
        iArr[2] = (i6 | i9) ^ i11;
        iArr[3] = (i11 & i10) ^ (i2 ^ i9);
    }

    protected final void sb6(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i ^ i4;
        int i7 = i2 ^ i6;
        int i8 = i3 ^ (i5 | i6);
        int i9 = i2 ^ i8;
        iArr[1] = i9;
        int i10 = (i6 | i9) ^ i4;
        int i11 = (i8 & i10) ^ i7;
        iArr[2] = i11;
        int i12 = i10 ^ i8;
        iArr[0] = i11 ^ i12;
        iArr[3] = (i12 & i7) ^ (~i8);
    }

    protected final void sb7(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = i2 ^ i3;
        int i6 = (i3 & i5) ^ i4;
        int i7 = i ^ i6;
        int i8 = i2 ^ ((i4 | i5) & i7);
        iArr[1] = i8;
        int i9 = (i & i7) ^ i5;
        iArr[3] = i9;
        int i10 = (i8 | i6) ^ i7;
        int i11 = i6 ^ (i9 & i10);
        iArr[2] = i11;
        iArr[0] = (i9 & i11) ^ (~i10);
    }
}
