package org.bouncycastle.math.ec.rfc8032;

import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;

/* loaded from: classes4.dex */
abstract class Scalar25519 {
    private static final int L0 = -50998291;
    private static final int L1 = 19280294;
    private static final int L2 = 127719000;
    private static final int L3 = -6428113;
    private static final int L4 = 5343;
    private static final long M08L = 255;
    private static final long M28L = 268435455;
    private static final long M32L = 4294967295L;
    private static final int SCALAR_BYTES = 32;
    static final int SIZE = 8;
    private static final int TARGET_LENGTH = 254;
    private static final int[] L = {1559614445, 1477600026, -1560830762, 350157278, 0, 0, 0, 268435456};
    private static final int[] LSq = {-1424848535, -487721339, 580428573, 1745064566, -770181698, 1036971123, 461123738, -1582065343, 1268693629, -889041821, -731974758, 43769659, 0, 0, 0, 16777216};

    Scalar25519() {
    }

    static boolean checkVar(byte[] bArr, int[] iArr) {
        decode(bArr, iArr);
        return !Nat256.gte(iArr, L);
    }

    static void decode(byte[] bArr, int[] iArr) {
        Codec.decode32(bArr, 0, iArr, 0, 8);
    }

    static void getOrderWnafVar(int i, byte[] bArr) {
        Wnaf.getSignedVar(L, i, bArr);
    }

    static void multiply128Var(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArr4 = new int[12];
        Nat256.mul128(iArr, iArr2, iArr4);
        if (iArr2[3] < 0) {
            Nat256.addTo(L, 0, iArr4, 4, 0);
            Nat256.subFrom(iArr, 0, iArr4, 4, 0);
        }
        byte[] bArr = new byte[48];
        Codec.encode32(iArr4, 0, 12, bArr, 0);
        decode(reduce384(bArr), iArr3);
    }

    static byte[] reduce384(byte[] bArr) {
        long jDecode24 = Codec.decode24(bArr, 32) << 4;
        long j = jDecode24 & 4294967295L;
        long jDecode32 = Codec.decode32(bArr, 35);
        long j2 = jDecode32 & 4294967295L;
        long jDecode242 = Codec.decode24(bArr, 39) << 4;
        long j3 = jDecode242 & 4294967295L;
        long jDecode322 = Codec.decode32(bArr, 42);
        long jDecode16 = ((Codec.decode16(bArr, 46) << 4) & 4294967295L) + ((jDecode322 & 4294967295L) >> 28);
        long j4 = (jDecode322 & M28L) + (j3 >> 28);
        long jDecode323 = ((Codec.decode32(bArr, 21) & 4294967295L) - (jDecode16 * 127719000)) - (j4 * (-6428113));
        long jDecode243 = (((Codec.decode24(bArr, 25) << 4) & 4294967295L) - (jDecode16 * (-6428113))) - (j4 * 5343);
        long j5 = (jDecode242 & M28L) + (j2 >> 28);
        long jDecode244 = (((Codec.decode24(bArr, 11) << 4) & 4294967295L) - (j4 * (-50998291))) - (j5 * 19280294);
        long jDecode324 = (((Codec.decode32(bArr, 14) & 4294967295L) - (jDecode16 * (-50998291))) - (j4 * 19280294)) - (j5 * 127719000);
        long jDecode245 = ((((Codec.decode24(bArr, 18) << 4) & 4294967295L) - (jDecode16 * 19280294)) - (j4 * 127719000)) - (j5 * (-6428113));
        long j6 = (jDecode32 & M28L) + (j >> 28);
        long j7 = jDecode24 & M28L;
        long jDecode246 = ((Codec.decode24(bArr, 4) << 4) & 4294967295L) - (j6 * (-50998291));
        long jDecode325 = ((Codec.decode32(bArr, 7) & 4294967295L) - (j5 * (-50998291))) - (j6 * 19280294);
        long j8 = jDecode244 - (j6 * 127719000);
        long j9 = jDecode324 - (j6 * (-6428113));
        long j10 = jDecode245 - (j6 * 5343);
        long jDecode326 = ((Codec.decode32(bArr, 28) & 4294967295L) - (jDecode16 * 5343)) + (jDecode243 >> 28);
        long j11 = jDecode243 & M28L;
        long j12 = j7 + (jDecode326 >> 28);
        long j13 = jDecode326 & M28L;
        long j14 = j13 >>> 27;
        long j15 = j12 + j14;
        long jDecode327 = (Codec.decode32(bArr, 0) & 4294967295L) - (j15 * (-50998291));
        long j16 = (jDecode246 - (j15 * 19280294)) + (jDecode327 >> 28);
        long j17 = jDecode327 & M28L;
        long j18 = (jDecode325 - (j15 * 127719000)) + (j16 >> 28);
        long j19 = j16 & M28L;
        long j20 = (j8 - (j15 * (-6428113))) + (j18 >> 28);
        long j21 = j18 & M28L;
        long j22 = (j9 - (j15 * 5343)) + (j20 >> 28);
        long j23 = j20 & M28L;
        long j24 = j10 + (j22 >> 28);
        long j25 = j22 & M28L;
        long j26 = (jDecode323 - (j5 * 5343)) + (j24 >> 28);
        long j27 = j24 & M28L;
        long j28 = j11 + (j26 >> 28);
        long j29 = j26 & M28L;
        long j30 = j13 + (j28 >> 28);
        long j31 = j28 & M28L;
        long j32 = j30 >> 28;
        long j33 = j30 & M28L;
        long j34 = j32 - j14;
        long j35 = j17 + (j34 & (-50998291));
        long j36 = j19 + (j34 & 19280294) + (j35 >> 28);
        long j37 = j35 & M28L;
        long j38 = j21 + (j34 & 127719000) + (j36 >> 28);
        long j39 = j36 & M28L;
        long j40 = j23 + (j34 & (-6428113)) + (j38 >> 28);
        long j41 = j38 & M28L;
        long j42 = j25 + (j34 & 5343) + (j40 >> 28);
        long j43 = j40 & M28L;
        long j44 = j27 + (j42 >> 28);
        long j45 = j42 & M28L;
        long j46 = j29 + (j44 >> 28);
        long j47 = j44 & M28L;
        long j48 = j31 + (j46 >> 28);
        long j49 = j46 & M28L;
        long j50 = j33 + (j48 >> 28);
        long j51 = j48 & M28L;
        byte[] bArr2 = new byte[64];
        Codec.encode56((j39 << 28) | j37, bArr2, 0);
        Codec.encode56(j41 | (j43 << 28), bArr2, 7);
        Codec.encode56((j47 << 28) | j45, bArr2, 14);
        Codec.encode56((j51 << 28) | j49, bArr2, 21);
        Codec.encode32((int) j50, bArr2, 28);
        return bArr2;
    }

    static byte[] reduce512(byte[] bArr) {
        long jDecode32 = Codec.decode32(bArr, 49);
        long j = jDecode32 & 4294967295L;
        long jDecode322 = Codec.decode32(bArr, 56);
        long j2 = jDecode322 & 4294967295L;
        long j3 = bArr[63] & M08L;
        long jDecode24 = ((Codec.decode24(bArr, 60) << 4) & 4294967295L) + (j2 >> 28);
        long j4 = jDecode322 & M28L;
        long jDecode323 = (Codec.decode32(bArr, 28) & 4294967295L) - (jDecode24 * (-50998291));
        long jDecode242 = (((Codec.decode24(bArr, 32) << 4) & 4294967295L) - (j3 * (-50998291))) - (jDecode24 * 19280294);
        long jDecode324 = ((Codec.decode32(bArr, 42) & 4294967295L) - (j3 * (-6428113))) - (jDecode24 * 5343);
        long jDecode243 = ((((Codec.decode24(bArr, 39) << 4) & 4294967295L) - (j3 * 127719000)) - (jDecode24 * (-6428113))) - (j4 * 5343);
        long jDecode244 = ((Codec.decode24(bArr, 53) << 4) & 4294967295L) + (j >> 28);
        long j5 = jDecode32 & M28L;
        long jDecode325 = ((((Codec.decode32(bArr, 35) & 4294967295L) - (j3 * 19280294)) - (jDecode24 * 127719000)) - (j4 * (-6428113))) - (jDecode244 * 5343);
        long jDecode245 = ((((Codec.decode24(bArr, 25) << 4) & 4294967295L) - (j4 * (-50998291))) - (jDecode244 * 19280294)) - (j5 * 127719000);
        long j6 = ((jDecode242 - (j4 * 127719000)) - (jDecode244 * (-6428113))) - (j5 * 5343);
        long jDecode246 = (((Codec.decode24(bArr, 46) << 4) & 4294967295L) - (j3 * 5343)) + (jDecode324 >> 28);
        long j7 = (jDecode324 & M28L) + (jDecode243 >> 28);
        long jDecode247 = ((Codec.decode24(bArr, 11) << 4) & 4294967295L) - (j7 * (-50998291));
        long jDecode326 = ((Codec.decode32(bArr, 14) & 4294967295L) - (jDecode246 * (-50998291))) - (j7 * 19280294);
        long jDecode248 = ((((Codec.decode24(bArr, 18) << 4) & 4294967295L) - (j5 * (-50998291))) - (jDecode246 * 19280294)) - (j7 * 127719000);
        long jDecode327 = ((((Codec.decode32(bArr, 21) & 4294967295L) - (jDecode244 * (-50998291))) - (j5 * 19280294)) - (jDecode246 * 127719000)) - (j7 * (-6428113));
        long j8 = (jDecode245 - (jDecode246 * (-6428113))) - (j7 * 5343);
        long j9 = (jDecode243 & M28L) + (jDecode325 >> 28);
        long j10 = jDecode325 & M28L;
        long jDecode328 = (Codec.decode32(bArr, 7) & 4294967295L) - (j9 * (-50998291));
        long j11 = jDecode247 - (j9 * 19280294);
        long j12 = jDecode326 - (j9 * 127719000);
        long j13 = jDecode248 - (j9 * (-6428113));
        long j14 = jDecode327 - (j9 * 5343);
        long j15 = j10 + (j6 >> 28);
        long j16 = j6 & M28L;
        long jDecode249 = ((Codec.decode24(bArr, 4) << 4) & 4294967295L) - (j15 * (-50998291));
        long j17 = jDecode328 - (j15 * 19280294);
        long j18 = j11 - (j15 * 127719000);
        long j19 = j12 - (j15 * (-6428113));
        long j20 = j13 - (j15 * 5343);
        long j21 = ((((jDecode323 - (j4 * 19280294)) - (jDecode244 * 127719000)) - (j5 * (-6428113))) - (jDecode246 * 5343)) + (j8 >> 28);
        long j22 = j8 & M28L;
        long j23 = j21 & M28L;
        long j24 = j23 >>> 27;
        long j25 = j16 + (j21 >> 28) + j24;
        long jDecode329 = (Codec.decode32(bArr, 0) & 4294967295L) - (j25 * (-50998291));
        long j26 = (jDecode249 - (j25 * 19280294)) + (jDecode329 >> 28);
        long j27 = jDecode329 & M28L;
        long j28 = (j17 - (j25 * 127719000)) + (j26 >> 28);
        long j29 = j26 & M28L;
        long j30 = (j18 - (j25 * (-6428113))) + (j28 >> 28);
        long j31 = j28 & M28L;
        long j32 = (j19 - (j25 * 5343)) + (j30 >> 28);
        long j33 = j30 & M28L;
        long j34 = j20 + (j32 >> 28);
        long j35 = j32 & M28L;
        long j36 = j14 + (j34 >> 28);
        long j37 = j34 & M28L;
        long j38 = j22 + (j36 >> 28);
        long j39 = j36 & M28L;
        long j40 = j23 + (j38 >> 28);
        long j41 = j38 & M28L;
        long j42 = j40 >> 28;
        long j43 = j40 & M28L;
        long j44 = j42 - j24;
        long j45 = j27 + (j44 & (-50998291));
        long j46 = j29 + (j44 & 19280294) + (j45 >> 28);
        long j47 = j45 & M28L;
        long j48 = j31 + (j44 & 127719000) + (j46 >> 28);
        long j49 = j46 & M28L;
        long j50 = j33 + (j44 & (-6428113)) + (j48 >> 28);
        long j51 = j48 & M28L;
        long j52 = j35 + (j44 & 5343) + (j50 >> 28);
        long j53 = j50 & M28L;
        long j54 = j37 + (j52 >> 28);
        long j55 = j52 & M28L;
        long j56 = j39 + (j54 >> 28);
        long j57 = j54 & M28L;
        long j58 = j41 + (j56 >> 28);
        long j59 = j56 & M28L;
        long j60 = j43 + (j58 >> 28);
        long j61 = j58 & M28L;
        byte[] bArr2 = new byte[32];
        Codec.encode56(j47 | (j49 << 28), bArr2, 0);
        Codec.encode56((j53 << 28) | j51, bArr2, 7);
        Codec.encode56(j55 | (j57 << 28), bArr2, 14);
        Codec.encode56(j59 | (j61 << 28), bArr2, 21);
        Codec.encode32((int) j60, bArr2, 28);
        return bArr2;
    }

    static boolean reduceBasisVar(int[] iArr, int[] iArr2, int[] iArr3) {
        int i;
        int i2;
        int[] iArr4;
        int[] iArr5 = new int[16];
        System.arraycopy(LSq, 0, iArr5, 0, 16);
        int[] iArr6 = new int[16];
        Nat256.square(iArr, iArr6);
        iArr6[0] = iArr6[0] + 1;
        int[] iArr7 = new int[16];
        int[] iArr8 = L;
        Nat256.mul(iArr8, iArr, iArr7);
        int[] iArr9 = new int[16];
        int[] iArr10 = new int[4];
        System.arraycopy(iArr8, 0, iArr10, 0, 4);
        int[] iArr11 = new int[4];
        System.arraycopy(iArr, 0, iArr11, 0, 4);
        int[] iArr12 = new int[4];
        iArr12[0] = 1;
        int[] iArr13 = new int[4];
        int[] iArr14 = iArr10;
        int[] iArr15 = iArr11;
        int i3 = 15;
        int i4 = 1016;
        int bitLengthPositive = ScalarUtil.getBitLengthPositive(15, iArr6);
        while (bitLengthPositive > 254) {
            int i5 = i4 - 1;
            if (i5 < 0) {
                return false;
            }
            int bitLength = ScalarUtil.getBitLength(i3, iArr7) - bitLengthPositive;
            int i6 = bitLength & (~(bitLength >> 31));
            if (iArr7[i3] < 0) {
                i = bitLengthPositive;
                ScalarUtil.addShifted_NP(i3, i6, iArr5, iArr6, iArr7, iArr9);
                int[] iArr16 = iArr15;
                ScalarUtil.addShifted_UV(3, i6, iArr14, iArr13, iArr16, iArr12);
                iArr4 = iArr16;
                i2 = i3;
            } else {
                i = bitLengthPositive;
                ScalarUtil.subShifted_NP(i3, i6, iArr5, iArr6, iArr7, iArr9);
                i2 = i3;
                iArr4 = iArr15;
                ScalarUtil.subShifted_UV(3, i6, iArr14, iArr13, iArr4, iArr12);
            }
            if (ScalarUtil.lessThan(i2, iArr5, iArr6)) {
                int i7 = i >>> 5;
                i3 = i7;
                bitLengthPositive = ScalarUtil.getBitLengthPositive(i7, iArr5);
                iArr15 = iArr14;
                iArr14 = iArr4;
                int[] iArr17 = iArr13;
                iArr13 = iArr12;
                iArr12 = iArr17;
                int[] iArr18 = iArr6;
                iArr6 = iArr5;
                iArr5 = iArr18;
            } else {
                iArr15 = iArr4;
                i3 = i2;
                bitLengthPositive = i;
            }
            i4 = i5;
        }
        System.arraycopy(iArr15, 0, iArr2, 0, 4);
        System.arraycopy(iArr12, 0, iArr3, 0, 4);
        return true;
    }

    static void toSignedDigits(int i, int[] iArr) {
        Nat.caddTo(8, (~iArr[0]) & 1, L, iArr);
        Nat.shiftDownBit(8, iArr, 1);
    }
}
