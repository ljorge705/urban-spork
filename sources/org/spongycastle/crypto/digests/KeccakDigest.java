package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

/* loaded from: classes4.dex */
public class KeccakDigest implements ExtendedDigest {
    protected int bitsInQueue;
    protected byte[] dataQueue;
    protected int fixedOutputLength;
    protected int rate;
    protected boolean squeezing;
    protected long[] state;
    private static long[] KeccakRoundConstants = keccakInitializeRoundConstants();
    private static int[] KeccakRhoOffsets = keccakInitializeRhoOffsets();

    private static long leftRotate(long j, int i) {
        return (j >>> (-i)) | (j << i);
    }

    private static long[] keccakInitializeRoundConstants() {
        long[] jArr = new long[24];
        byte[] bArr = {1};
        for (int i = 0; i < 24; i++) {
            jArr[i] = 0;
            for (int i2 = 0; i2 < 7; i2++) {
                int i3 = (1 << i2) - 1;
                if (LFSR86540(bArr)) {
                    jArr[i] = jArr[i] ^ (1 << i3);
                }
            }
        }
        return jArr;
    }

    private static boolean LFSR86540(byte[] bArr) {
        byte b = bArr[0];
        boolean z = (b & 1) != 0;
        if ((b & 128) != 0) {
            bArr[0] = (byte) ((b << 1) ^ 113);
        } else {
            bArr[0] = (byte) (b << 1);
        }
        return z;
    }

    private static int[] keccakInitializeRhoOffsets() {
        int[] iArr = new int[25];
        int i = 0;
        iArr[0] = 0;
        int i2 = 1;
        int i3 = 0;
        while (i < 24) {
            int i4 = i + 1;
            iArr[(i2 % 5) + ((i3 % 5) * 5)] = (((i + 2) * i4) / 2) % 64;
            int i5 = i3 % 5;
            i3 = ((i2 * 2) + (i3 * 3)) % 5;
            i2 = i5;
            i = i4;
        }
        return iArr;
    }

    public KeccakDigest() {
        this(288);
    }

    public KeccakDigest(int i) {
        this.state = new long[25];
        this.dataQueue = new byte[192];
        init(i);
    }

    public KeccakDigest(KeccakDigest keccakDigest) {
        long[] jArr = new long[25];
        this.state = jArr;
        this.dataQueue = new byte[192];
        long[] jArr2 = keccakDigest.state;
        System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
        byte[] bArr = keccakDigest.dataQueue;
        System.arraycopy(bArr, 0, this.dataQueue, 0, bArr.length);
        this.rate = keccakDigest.rate;
        this.bitsInQueue = keccakDigest.bitsInQueue;
        this.fixedOutputLength = keccakDigest.fixedOutputLength;
        this.squeezing = keccakDigest.squeezing;
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Keccak-" + this.fixedOutputLength;
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return this.fixedOutputLength / 8;
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte b) {
        absorb(new byte[]{b}, 0, 1);
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        absorb(bArr, i, i2);
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        squeeze(bArr, i, this.fixedOutputLength);
        reset();
        return getDigestSize();
    }

    protected int doFinal(byte[] bArr, int i, byte b, int i2) {
        if (i2 > 0) {
            absorbBits(b, i2);
        }
        squeeze(bArr, i, this.fixedOutputLength);
        reset();
        return getDigestSize();
    }

    @Override // org.spongycastle.crypto.Digest
    public void reset() {
        init(this.fixedOutputLength);
    }

    @Override // org.spongycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return this.rate / 8;
    }

    private void init(int i) {
        if (i == 128 || i == 224 || i == 256 || i == 288 || i == 384 || i == 512) {
            initSponge(1600 - (i << 1));
            return;
        }
        throw new IllegalArgumentException("bitLength must be one of 128, 224, 256, 288, 384, or 512.");
    }

    private void initSponge(int i) {
        if (i <= 0 || i >= 1600 || i % 64 != 0) {
            throw new IllegalStateException("invalid rate value");
        }
        this.rate = i;
        int i2 = 0;
        while (true) {
            long[] jArr = this.state;
            if (i2 < jArr.length) {
                jArr[i2] = 0;
                i2++;
            } else {
                Arrays.fill(this.dataQueue, (byte) 0);
                this.bitsInQueue = 0;
                this.squeezing = false;
                this.fixedOutputLength = (1600 - i) / 2;
                return;
            }
        }
    }

    protected void absorb(byte[] bArr, int i, int i2) {
        int i3;
        int i4 = this.bitsInQueue;
        if (i4 % 8 != 0) {
            throw new IllegalStateException("attempt to absorb with odd length queue");
        }
        if (this.squeezing) {
            throw new IllegalStateException("attempt to absorb while squeezing");
        }
        int i5 = i4 >> 3;
        int i6 = this.rate >> 3;
        int i7 = 0;
        while (i7 < i2) {
            if (i5 != 0 || i7 > (i3 = i2 - i6)) {
                int iMin = Math.min(i6 - i5, i2 - i7);
                System.arraycopy(bArr, i + i7, this.dataQueue, i5, iMin);
                i5 += iMin;
                i7 += iMin;
                if (i5 == i6) {
                    KeccakAbsorb(this.dataQueue, 0);
                    i5 = 0;
                }
            } else {
                do {
                    KeccakAbsorb(bArr, i + i7);
                    i7 += i6;
                } while (i7 <= i3);
            }
        }
        this.bitsInQueue = i5 << 3;
    }

    protected void absorbBits(int i, int i2) {
        if (i2 < 1 || i2 > 7) {
            throw new IllegalArgumentException("'bits' must be in the range 1 to 7");
        }
        int i3 = this.bitsInQueue;
        if (i3 % 8 != 0) {
            throw new IllegalStateException("attempt to absorb with odd length queue");
        }
        if (this.squeezing) {
            throw new IllegalStateException("attempt to absorb while squeezing");
        }
        this.dataQueue[i3 >> 3] = (byte) (i & ((1 << i2) - 1));
        this.bitsInQueue = i3 + i2;
    }

    private void padAndSwitchToSqueezingPhase() {
        byte[] bArr = this.dataQueue;
        int i = this.bitsInQueue;
        int i2 = i >> 3;
        bArr[i2] = (byte) (bArr[i2] | ((byte) (1 << (i & 7))));
        int i3 = i + 1;
        this.bitsInQueue = i3;
        if (i3 == this.rate) {
            KeccakAbsorb(bArr, 0);
            this.bitsInQueue = 0;
        }
        int i4 = this.bitsInQueue;
        int i5 = i4 >> 6;
        int i6 = i4 & 63;
        int i7 = 0;
        for (int i8 = 0; i8 < i5; i8++) {
            long[] jArr = this.state;
            jArr[i8] = jArr[i8] ^ Pack.littleEndianToLong(this.dataQueue, i7);
            i7 += 8;
        }
        if (i6 > 0) {
            long j = (1 << i6) - 1;
            long[] jArr2 = this.state;
            jArr2[i5] = jArr2[i5] ^ (Pack.littleEndianToLong(this.dataQueue, i7) & j);
        }
        long[] jArr3 = this.state;
        int i9 = (this.rate - 1) >> 6;
        jArr3[i9] = jArr3[i9] ^ Long.MIN_VALUE;
        KeccakPermutation();
        KeccakExtract();
        this.bitsInQueue = this.rate;
        this.squeezing = true;
    }

    protected void squeeze(byte[] bArr, int i, long j) {
        if (!this.squeezing) {
            padAndSwitchToSqueezingPhase();
        }
        long j2 = 0;
        if (j % 8 != 0) {
            throw new IllegalStateException("outputLength not a multiple of 8");
        }
        while (j2 < j) {
            if (this.bitsInQueue == 0) {
                KeccakPermutation();
                KeccakExtract();
                this.bitsInQueue = this.rate;
            }
            int iMin = (int) Math.min(this.bitsInQueue, j - j2);
            System.arraycopy(this.dataQueue, (this.rate - this.bitsInQueue) / 8, bArr, ((int) (j2 / 8)) + i, iMin / 8);
            this.bitsInQueue -= iMin;
            j2 += iMin;
        }
    }

    private void KeccakAbsorb(byte[] bArr, int i) {
        int i2 = this.rate >> 6;
        for (int i3 = 0; i3 < i2; i3++) {
            long[] jArr = this.state;
            jArr[i3] = jArr[i3] ^ Pack.littleEndianToLong(bArr, i);
            i += 8;
        }
        KeccakPermutation();
    }

    private void KeccakExtract() {
        Pack.longToLittleEndian(this.state, 0, this.rate >> 6, this.dataQueue, 0);
    }

    private void KeccakPermutation() {
        for (int i = 0; i < 24; i++) {
            theta(this.state);
            rho(this.state);
            pi(this.state);
            chi(this.state);
            iota(this.state, i);
        }
    }

    private static void theta(long[] jArr) {
        long j = (((jArr[0] ^ jArr[5]) ^ jArr[10]) ^ jArr[15]) ^ jArr[20];
        long j2 = (((jArr[1] ^ jArr[6]) ^ jArr[11]) ^ jArr[16]) ^ jArr[21];
        long j3 = (((jArr[2] ^ jArr[7]) ^ jArr[12]) ^ jArr[17]) ^ jArr[22];
        long j4 = (((jArr[3] ^ jArr[8]) ^ jArr[13]) ^ jArr[18]) ^ jArr[23];
        long j5 = (((jArr[4] ^ jArr[9]) ^ jArr[14]) ^ jArr[19]) ^ jArr[24];
        long jLeftRotate = leftRotate(j2, 1) ^ j5;
        jArr[0] = jArr[0] ^ jLeftRotate;
        jArr[5] = jArr[5] ^ jLeftRotate;
        jArr[10] = jArr[10] ^ jLeftRotate;
        jArr[15] = jArr[15] ^ jLeftRotate;
        jArr[20] = jArr[20] ^ jLeftRotate;
        long jLeftRotate2 = leftRotate(j3, 1) ^ j;
        jArr[1] = jArr[1] ^ jLeftRotate2;
        jArr[6] = jArr[6] ^ jLeftRotate2;
        jArr[11] = jArr[11] ^ jLeftRotate2;
        jArr[16] = jArr[16] ^ jLeftRotate2;
        jArr[21] = jLeftRotate2 ^ jArr[21];
        long jLeftRotate3 = leftRotate(j4, 1) ^ j2;
        jArr[2] = jArr[2] ^ jLeftRotate3;
        jArr[7] = jArr[7] ^ jLeftRotate3;
        jArr[12] = jArr[12] ^ jLeftRotate3;
        jArr[17] = jArr[17] ^ jLeftRotate3;
        jArr[22] = jLeftRotate3 ^ jArr[22];
        long jLeftRotate4 = leftRotate(j5, 1) ^ j3;
        jArr[3] = jArr[3] ^ jLeftRotate4;
        jArr[8] = jArr[8] ^ jLeftRotate4;
        jArr[13] = jArr[13] ^ jLeftRotate4;
        jArr[18] = jArr[18] ^ jLeftRotate4;
        jArr[23] = jLeftRotate4 ^ jArr[23];
        long jLeftRotate5 = leftRotate(j, 1) ^ j4;
        jArr[4] = jArr[4] ^ jLeftRotate5;
        jArr[9] = jArr[9] ^ jLeftRotate5;
        jArr[14] = jArr[14] ^ jLeftRotate5;
        jArr[19] = jArr[19] ^ jLeftRotate5;
        jArr[24] = jLeftRotate5 ^ jArr[24];
    }

    private static void rho(long[] jArr) {
        for (int i = 1; i < 25; i++) {
            jArr[i] = leftRotate(jArr[i], KeccakRhoOffsets[i]);
        }
    }

    private static void pi(long[] jArr) {
        long j = jArr[1];
        jArr[1] = jArr[6];
        jArr[6] = jArr[9];
        jArr[9] = jArr[22];
        jArr[22] = jArr[14];
        jArr[14] = jArr[20];
        jArr[20] = jArr[2];
        jArr[2] = jArr[12];
        jArr[12] = jArr[13];
        jArr[13] = jArr[19];
        jArr[19] = jArr[23];
        jArr[23] = jArr[15];
        jArr[15] = jArr[4];
        jArr[4] = jArr[24];
        jArr[24] = jArr[21];
        jArr[21] = jArr[8];
        jArr[8] = jArr[16];
        jArr[16] = jArr[5];
        jArr[5] = jArr[3];
        jArr[3] = jArr[18];
        jArr[18] = jArr[17];
        jArr[17] = jArr[11];
        jArr[11] = jArr[7];
        jArr[7] = jArr[10];
        jArr[10] = j;
    }

    private static void chi(long[] jArr) {
        for (int i = 0; i < 25; i += 5) {
            long j = jArr[i];
            int i2 = i + 1;
            long j2 = jArr[i2];
            long j3 = ~j2;
            int i3 = i + 2;
            long j4 = jArr[i3];
            long j5 = (j3 & j4) ^ j;
            long j6 = ~j4;
            int i4 = i + 3;
            long j7 = jArr[i4];
            long j8 = (j6 & j7) ^ j2;
            long j9 = ~j7;
            int i5 = i + 4;
            long j10 = jArr[i5];
            long j11 = j4 ^ (j9 & j10);
            long j12 = ((~j10) & j) ^ j7;
            long j13 = ((~j) & j2) ^ j10;
            jArr[i] = j5;
            jArr[i2] = j8;
            jArr[i3] = j11;
            jArr[i4] = j12;
            jArr[i5] = j13;
        }
    }

    private static void iota(long[] jArr, int i) {
        jArr[0] = jArr[0] ^ KeccakRoundConstants[i];
    }
}
