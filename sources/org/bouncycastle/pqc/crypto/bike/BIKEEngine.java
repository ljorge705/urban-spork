package org.bouncycastle.pqc.crypto.bike;

import java.security.SecureRandom;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Bytes;

/* loaded from: classes4.dex */
class BIKEEngine {
    private int L_BYTE;
    private int R2_BYTE;
    private int R_BYTE;
    private final BIKERing bikeRing;
    private int hw;
    private int nbIter;
    private int r;
    private int t;
    private int tau;
    private int w;

    public BIKEEngine(int i, int i2, int i3, int i4, int i5, int i6) {
        this.r = i;
        this.w = i2;
        this.t = i3;
        this.nbIter = i5;
        this.tau = i6;
        this.hw = i2 / 2;
        this.L_BYTE = i4 / 8;
        this.R_BYTE = (i + 7) >>> 3;
        this.R2_BYTE = ((i * 2) + 7) >>> 3;
        this.bikeRing = new BIKERing(i);
    }

    private void BFIter(byte[] bArr, byte[] bArr2, int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        ctrAll(iArr3, bArr, bArr5);
        int i2 = bArr5[0] & 255;
        int i3 = ((i2 - i) >> 31) + 1;
        int i4 = ((i2 - (i - this.tau)) >> 31) + 1;
        byte b = (byte) i3;
        bArr2[0] = (byte) (bArr2[0] ^ b);
        bArr3[0] = b;
        bArr4[0] = (byte) i4;
        int i5 = 1;
        while (true) {
            int i6 = this.r;
            if (i5 >= i6) {
                break;
            }
            int i7 = bArr5[i5] & 255;
            int i8 = ((i7 - i) >> 31) + 1;
            int i9 = ((i7 - (i - this.tau)) >> 31) + 1;
            int i10 = i6 - i5;
            byte b2 = (byte) i8;
            bArr2[i10] = (byte) (bArr2[i10] ^ b2);
            bArr3[i5] = b2;
            bArr4[i5] = (byte) i9;
            i5++;
        }
        ctrAll(iArr4, bArr, bArr5);
        int i11 = bArr5[0] & 255;
        int i12 = ((i11 - i) >> 31) + 1;
        int i13 = ((i11 - (i - this.tau)) >> 31) + 1;
        int i14 = this.r;
        byte b3 = (byte) i12;
        bArr2[i14] = (byte) (bArr2[i14] ^ b3);
        bArr3[i14] = b3;
        bArr4[i14] = (byte) i13;
        int i15 = 1;
        while (true) {
            int i16 = this.r;
            if (i15 >= i16) {
                break;
            }
            int i17 = bArr5[i15] & 255;
            int i18 = ((i17 - i) >> 31) + 1;
            int i19 = ((i17 - (i - this.tau)) >> 31) + 1;
            int i20 = (i16 + i16) - i15;
            byte b4 = (byte) i18;
            bArr2[i20] = (byte) (bArr2[i20] ^ b4);
            bArr3[i16 + i15] = b4;
            bArr4[i16 + i15] = (byte) i19;
            i15++;
        }
        for (int i21 = 0; i21 < this.r * 2; i21++) {
            recomputeSyndrome(bArr, i21, iArr, iArr2, bArr3[i21] != 0);
        }
    }

    private void BFIter2(byte[] bArr, byte[] bArr2, int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, byte[] bArr3) {
        int[] iArr5 = new int[this.r * 2];
        ctrAll(iArr3, bArr, bArr3);
        int i2 = (((bArr3[0] & 255) - i) >> 31) + 1;
        bArr2[0] = (byte) (bArr2[0] ^ ((byte) i2));
        iArr5[0] = i2;
        int i3 = 1;
        while (true) {
            int i4 = this.r;
            if (i3 >= i4) {
                break;
            }
            int i5 = (((bArr3[i3] & 255) - i) >> 31) + 1;
            int i6 = i4 - i3;
            bArr2[i6] = (byte) (bArr2[i6] ^ ((byte) i5));
            iArr5[i3] = i5;
            i3++;
        }
        ctrAll(iArr4, bArr, bArr3);
        int i7 = (((bArr3[0] & 255) - i) >> 31) + 1;
        int i8 = this.r;
        bArr2[i8] = (byte) (bArr2[i8] ^ ((byte) i7));
        iArr5[i8] = i7;
        int i9 = 1;
        while (true) {
            int i10 = this.r;
            if (i9 >= i10) {
                break;
            }
            int i11 = (((bArr3[i9] & 255) - i) >> 31) + 1;
            int i12 = (i10 + i10) - i9;
            bArr2[i12] = (byte) (bArr2[i12] ^ ((byte) i11));
            iArr5[i10 + i9] = i11;
            i9++;
        }
        for (int i13 = 0; i13 < this.r * 2; i13++) {
            recomputeSyndrome(bArr, i13, iArr, iArr2, iArr5[i13] == 1);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v4 */
    private void BFMaskedIter(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        int[] iArr5 = new int[this.r * 2];
        int i2 = 0;
        while (true) {
            if (i2 >= this.r) {
                break;
            }
            if (bArr3[i2] == 1) {
                ?? r10 = ctr(iArr3, bArr, i2) < i ? 0 : 1;
                updateNewErrorIndex(bArr2, i2, r10);
                iArr5[i2] = r10;
            }
            i2++;
        }
        int i3 = 0;
        while (true) {
            int i4 = this.r;
            if (i3 >= i4) {
                break;
            }
            if (bArr3[i4 + i3] == 1) {
                ?? r4 = ctr(iArr4, bArr, i3) >= i ? 1 : 0;
                updateNewErrorIndex(bArr2, this.r + i3, r4);
                iArr5[this.r + i3] = r4;
            }
            i3++;
        }
        for (int i5 = 0; i5 < this.r * 2; i5++) {
            recomputeSyndrome(bArr, i5, iArr, iArr2, iArr5[i5] == 1);
        }
    }

    private byte[] BGFDecoder(byte[] bArr, int[] iArr, int[] iArr2) {
        byte[] bArr2 = new byte[this.r * 2];
        int[] columnFromCompactVersion = getColumnFromCompactVersion(iArr);
        int[] columnFromCompactVersion2 = getColumnFromCompactVersion(iArr2);
        int i = this.r;
        byte[] bArr3 = new byte[i * 2];
        byte[] bArr4 = new byte[i];
        byte[] bArr5 = new byte[i * 2];
        BFIter(bArr, bArr2, threshold(BIKEUtils.getHammingWeight(bArr), this.r), iArr, iArr2, columnFromCompactVersion, columnFromCompactVersion2, bArr3, bArr5, bArr4);
        BFMaskedIter(bArr, bArr2, bArr3, ((this.hw + 1) / 2) + 1, iArr, iArr2, columnFromCompactVersion, columnFromCompactVersion2);
        BFMaskedIter(bArr, bArr2, bArr5, ((this.hw + 1) / 2) + 1, iArr, iArr2, columnFromCompactVersion, columnFromCompactVersion2);
        for (int i2 = 1; i2 < this.nbIter; i2++) {
            Arrays.fill(bArr3, (byte) 0);
            BFIter2(bArr, bArr2, threshold(BIKEUtils.getHammingWeight(bArr), this.r), iArr, iArr2, columnFromCompactVersion, columnFromCompactVersion2, bArr4);
        }
        if (BIKEUtils.getHammingWeight(bArr) == 0) {
            return bArr2;
        }
        return null;
    }

    private byte[] computeSyndrome(byte[] bArr, byte[] bArr2) {
        long[] jArrCreate = this.bikeRing.create();
        long[] jArrCreate2 = this.bikeRing.create();
        this.bikeRing.decodeBytes(bArr, jArrCreate);
        this.bikeRing.decodeBytes(bArr2, jArrCreate2);
        this.bikeRing.multiply(jArrCreate, jArrCreate2, jArrCreate);
        return this.bikeRing.encodeBitsTransposed(jArrCreate);
    }

    private void convertToCompact(int[] iArr, byte[] bArr) {
        int i;
        int i2 = 0;
        for (int i3 = 0; i3 < this.R_BYTE; i3++) {
            for (int i4 = 0; i4 < 8 && (i = (i3 * 8) + i4) != this.r; i4++) {
                int i5 = (bArr[i3] >> i4) & 1;
                int i6 = -i5;
                iArr[i2] = (i & i6) | ((~i6) & iArr[i2]);
                i2 = (i2 + i5) % this.hw;
            }
        }
    }

    private int ctr(int[] iArr, byte[] bArr, int i) {
        int i2 = this.hw - 4;
        int i3 = 0;
        int i4 = 0;
        while (i3 <= i2) {
            int i5 = iArr[i3] + i;
            int i6 = this.r;
            int i7 = i5 - i6;
            int i8 = (iArr[i3 + 1] + i) - i6;
            int i9 = (iArr[i3 + 2] + i) - i6;
            int i10 = (iArr[i3 + 3] + i) - i6;
            i4 = i4 + (bArr[i7 + ((i7 >> 31) & i6)] & 255) + (bArr[i8 + ((i8 >> 31) & i6)] & 255) + (bArr[i9 + ((i9 >> 31) & i6)] & 255) + (bArr[i10 + (i6 & (i10 >> 31))] & 255);
            i3 += 4;
        }
        while (i3 < this.hw) {
            int i11 = iArr[i3] + i;
            int i12 = this.r;
            int i13 = i11 - i12;
            i4 += bArr[i13 + (i12 & (i13 >> 31))] & 255;
            i3++;
        }
        return i4;
    }

    private void ctrAll(int[] iArr, byte[] bArr, byte[] bArr2) {
        int i = iArr[0];
        int i2 = this.r - i;
        System.arraycopy(bArr, i, bArr2, 0, i2);
        System.arraycopy(bArr, 0, bArr2, i2, i);
        for (int i3 = 1; i3 < this.hw; i3++) {
            int i4 = iArr[i3];
            int i5 = this.r - i4;
            int i6 = i5 - 4;
            int i7 = 0;
            while (i7 <= i6) {
                int i8 = i4 + i7;
                bArr2[i7] = (byte) (bArr2[i7] + (bArr[i8] & 255));
                int i9 = i7 + 1;
                bArr2[i9] = (byte) (bArr2[i9] + (bArr[i8 + 1] & 255));
                int i10 = i7 + 2;
                bArr2[i10] = (byte) (bArr2[i10] + (bArr[i8 + 2] & 255));
                int i11 = i7 + 3;
                bArr2[i11] = (byte) (bArr2[i11] + (bArr[i8 + 3] & 255));
                i7 += 4;
            }
            while (i7 < i5) {
                bArr2[i7] = (byte) (bArr2[i7] + (bArr[i4 + i7] & 255));
                i7++;
            }
            int i12 = this.r - 4;
            int i13 = i5;
            while (i13 <= i12) {
                bArr2[i13] = (byte) (bArr2[i13] + (bArr[i13 - i5] & 255));
                int i14 = i13 + 1;
                bArr2[i14] = (byte) (bArr2[i14] + (bArr[i14 - i5] & 255));
                int i15 = i13 + 2;
                bArr2[i15] = (byte) (bArr2[i15] + (bArr[i15 - i5] & 255));
                int i16 = i13 + 3;
                bArr2[i16] = (byte) (bArr2[i16] + (bArr[i16 - i5] & 255));
                i13 += 4;
            }
            while (i13 < this.r) {
                bArr2[i13] = (byte) (bArr2[i13] + (bArr[i13 - i5] & 255));
                i13++;
            }
        }
    }

    private byte[] functionH(byte[] bArr) {
        byte[] bArr2 = new byte[this.R_BYTE * 2];
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(bArr, 0, bArr.length);
        BIKEUtils.generateRandomByteArray(bArr2, this.r * 2, this.t, sHAKEDigest);
        return bArr2;
    }

    private void functionK(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        byte[] bArr5 = new byte[48];
        SHA3Digest sHA3Digest = new SHA3Digest(384);
        sHA3Digest.update(bArr, 0, bArr.length);
        sHA3Digest.update(bArr2, 0, bArr2.length);
        sHA3Digest.update(bArr3, 0, bArr3.length);
        sHA3Digest.doFinal(bArr5, 0);
        System.arraycopy(bArr5, 0, bArr4, 0, this.L_BYTE);
    }

    private void functionL(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[48];
        SHA3Digest sHA3Digest = new SHA3Digest(384);
        sHA3Digest.update(bArr, 0, bArr.length);
        sHA3Digest.update(bArr2, 0, bArr2.length);
        sHA3Digest.doFinal(bArr4, 0);
        System.arraycopy(bArr4, 0, bArr3, 0, this.L_BYTE);
    }

    private int[] getColumnFromCompactVersion(int[] iArr) {
        int[] iArr2 = new int[this.hw];
        int i = 0;
        if (iArr[0] != 0) {
            while (true) {
                int i2 = this.hw;
                if (i >= i2) {
                    break;
                }
                iArr2[i] = this.r - iArr[(i2 - 1) - i];
                i++;
            }
        } else {
            iArr2[0] = 0;
            int i3 = 1;
            while (true) {
                int i4 = this.hw;
                if (i3 >= i4) {
                    break;
                }
                iArr2[i3] = this.r - iArr[i4 - i3];
                i3++;
            }
        }
        return iArr2;
    }

    private void recomputeSyndrome(byte[] bArr, int i, int[] iArr, int[] iArr2, boolean z) {
        int i2 = 0;
        if (i < this.r) {
            while (i2 < this.hw) {
                int i3 = iArr[i2];
                if (i3 <= i) {
                    int i4 = i - i3;
                    bArr[i4] = (byte) (bArr[i4] ^ (z ? 1 : 0));
                } else {
                    int i5 = (this.r + i) - i3;
                    bArr[i5] = (byte) (bArr[i5] ^ (z ? 1 : 0));
                }
                i2++;
            }
            return;
        }
        while (i2 < this.hw) {
            int i6 = iArr2[i2];
            int i7 = this.r;
            if (i6 <= i - i7) {
                int i8 = (i - i7) - i6;
                bArr[i8] = (byte) (bArr[i8] ^ (z ? 1 : 0));
            } else {
                int i9 = (i7 - i6) + (i - i7);
                bArr[i9] = (byte) (bArr[i9] ^ (z ? 1 : 0));
            }
            i2++;
        }
    }

    private void splitEBytes(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int i = this.r & 7;
        int i2 = 0;
        System.arraycopy(bArr, 0, bArr2, 0, this.R_BYTE - 1);
        int i3 = this.R_BYTE;
        byte b = bArr[i3 - 1];
        byte b2 = (byte) ((-1) << i);
        bArr2[i3 - 1] = (byte) ((~b2) & b);
        byte b3 = (byte) (b & b2);
        while (true) {
            int i4 = this.R_BYTE;
            if (i2 >= i4) {
                return;
            }
            byte b4 = bArr[i4 + i2];
            bArr3[i2] = (byte) (((b3 & 255) >>> i) | (b4 << (8 - i)));
            i2++;
            b3 = b4;
        }
    }

    private int threshold(int i, int i2) {
        if (i2 == 12323) {
            return thresholdFromParameters(i, 0.0069722d, 13.53d, 36);
        }
        if (i2 == 24659) {
            return thresholdFromParameters(i, 0.005265d, 15.2588d, 52);
        }
        if (i2 == 40973) {
            return thresholdFromParameters(i, 0.00402312d, 17.8785d, 69);
        }
        throw new IllegalArgumentException();
    }

    private static int thresholdFromParameters(int i, double d, double d2, int i2) {
        return Math.max(i2, (int) Math.floor((d * i) + d2));
    }

    private void updateNewErrorIndex(byte[] bArr, int i, boolean z) {
        int i2;
        if (i != 0 && i != (i2 = this.r)) {
            i = i > i2 ? ((i2 * 2) - i) + i2 : i2 - i;
        }
        bArr[i] = (byte) ((z ? 1 : 0) ^ bArr[i]);
    }

    public void decaps(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
        int i = this.hw;
        int[] iArr = new int[i];
        int[] iArr2 = new int[i];
        convertToCompact(iArr, bArr2);
        convertToCompact(iArr2, bArr3);
        byte[] bArrBGFDecoder = BGFDecoder(computeSyndrome(bArr5, bArr2), iArr, iArr2);
        byte[] bArr7 = new byte[this.R_BYTE * 2];
        BIKEUtils.fromBitArrayToByteArray(bArr7, bArrBGFDecoder, 0, this.r * 2);
        int i2 = this.R_BYTE;
        byte[] bArr8 = new byte[i2];
        byte[] bArr9 = new byte[i2];
        splitEBytes(bArr7, bArr8, bArr9);
        byte[] bArr10 = new byte[this.L_BYTE];
        functionL(bArr8, bArr9, bArr10);
        Bytes.xorTo(this.L_BYTE, bArr6, bArr10);
        byte[] bArrFunctionH = functionH(bArr10);
        int i3 = this.R2_BYTE;
        if (Arrays.areEqual(bArr7, 0, i3, bArrFunctionH, 0, i3)) {
            functionK(bArr10, bArr5, bArr6, bArr);
        } else {
            functionK(bArr4, bArr5, bArr6, bArr);
        }
    }

    public void encaps(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, SecureRandom secureRandom) {
        byte[] bArr5 = new byte[this.L_BYTE];
        secureRandom.nextBytes(bArr5);
        byte[] bArrFunctionH = functionH(bArr5);
        int i = this.R_BYTE;
        byte[] bArr6 = new byte[i];
        byte[] bArr7 = new byte[i];
        splitEBytes(bArrFunctionH, bArr6, bArr7);
        long[] jArrCreate = this.bikeRing.create();
        long[] jArrCreate2 = this.bikeRing.create();
        this.bikeRing.decodeBytes(bArr6, jArrCreate);
        this.bikeRing.decodeBytes(bArr7, jArrCreate2);
        long[] jArrCreate3 = this.bikeRing.create();
        this.bikeRing.decodeBytes(bArr4, jArrCreate3);
        this.bikeRing.multiply(jArrCreate3, jArrCreate2, jArrCreate3);
        this.bikeRing.add(jArrCreate3, jArrCreate, jArrCreate3);
        this.bikeRing.encodeBytes(jArrCreate3, bArr);
        functionL(bArr6, bArr7, bArr2);
        Bytes.xorTo(this.L_BYTE, bArr5, bArr2);
        functionK(bArr5, bArr, bArr2, bArr3);
    }

    public void genKeyPair(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, SecureRandom secureRandom) {
        byte[] bArr5 = new byte[64];
        secureRandom.nextBytes(bArr5);
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(bArr5, 0, this.L_BYTE);
        BIKEUtils.generateRandomByteArray(bArr, this.r, this.hw, sHAKEDigest);
        BIKEUtils.generateRandomByteArray(bArr2, this.r, this.hw, sHAKEDigest);
        long[] jArrCreate = this.bikeRing.create();
        long[] jArrCreate2 = this.bikeRing.create();
        this.bikeRing.decodeBytes(bArr, jArrCreate);
        this.bikeRing.decodeBytes(bArr2, jArrCreate2);
        long[] jArrCreate3 = this.bikeRing.create();
        this.bikeRing.inv(jArrCreate, jArrCreate3);
        this.bikeRing.multiply(jArrCreate3, jArrCreate2, jArrCreate3);
        this.bikeRing.encodeBytes(jArrCreate3, bArr4);
        System.arraycopy(bArr5, this.L_BYTE, bArr3, 0, bArr3.length);
    }

    public int getSessionKeySize() {
        return this.L_BYTE;
    }
}
