package org.bouncycastle.pqc.crypto.bike;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

/* loaded from: classes4.dex */
class BIKERing {
    private static final int PERMUTATION_CUTOFF = 64;
    private final int bits;
    private final Map<Integer, Integer> halfPowers;
    private final int size;
    private final int sizeExt;

    BIKERing(int i) {
        HashMap map = new HashMap();
        this.halfPowers = map;
        if (((-65535) & i) != 1) {
            throw new IllegalArgumentException();
        }
        this.bits = i;
        int i2 = (i + 63) >>> 6;
        this.size = i2;
        this.sizeExt = i2 * 2;
        generateHalfPowersInv(map, i);
    }

    private static int generateHalfPower(int i, int i2, int i3) {
        int i4 = 1;
        while (i3 >= 32) {
            i4 = (int) ((((4294967295L & (i2 * i4)) * i) + i4) >>> 32);
            i3 -= 32;
        }
        if (i3 <= 0) {
            return i4;
        }
        return (int) ((((4294967295L & ((i2 * i4) & ((-1) >>> (-i3)))) * i) + i4) >>> i3);
    }

    private static void generateHalfPowersInv(Map<Integer, Integer> map, int i) {
        int i2;
        int i3 = i - 2;
        int iNumberOfLeadingZeros = 32 - Integers.numberOfLeadingZeros(i3);
        int iInverse32 = Mod.inverse32(-i);
        for (int i4 = 1; i4 < iNumberOfLeadingZeros; i4++) {
            int i5 = 1 << (i4 - 1);
            if (i5 >= 64 && !map.containsKey(Integers.valueOf(i5))) {
                map.put(Integers.valueOf(i5), Integers.valueOf(generateHalfPower(i, iInverse32, i5)));
            }
            int i6 = 1 << i4;
            if ((i3 & i6) != 0 && (i2 = (i6 - 1) & i3) >= 64 && !map.containsKey(Integers.valueOf(i2))) {
                map.put(Integers.valueOf(i2), Integers.valueOf(generateHalfPower(i, iInverse32, i2)));
            }
        }
    }

    private static int implModAdd(int i, int i2, int i3) {
        int i4 = (i2 + i3) - i;
        return i4 + (i & (i4 >> 31));
    }

    private static void implMulwAcc(long[] jArr, long j, long j2, long[] jArr2, int i) {
        long j3 = j;
        jArr[1] = j2;
        for (int i2 = 2; i2 < 16; i2 += 2) {
            long j4 = jArr[i2 >>> 1] << 1;
            jArr[i2] = j4;
            jArr[i2 + 1] = j4 ^ j2;
        }
        int i3 = (int) j3;
        long j5 = jArr[i3 & 15] ^ (jArr[(i3 >>> 4) & 15] << 4);
        long j6 = 0;
        int i4 = 56;
        do {
            int i5 = (int) (j3 >>> i4);
            long j7 = jArr[i5 & 15] ^ (jArr[(i5 >>> 4) & 15] << 4);
            j5 ^= j7 << i4;
            j6 ^= j7 >>> (-i4);
            i4 -= 8;
        } while (i4 > 0);
        for (int i6 = 0; i6 < 7; i6++) {
            j3 = (j3 & (-72340172838076674L)) >>> 1;
            j6 ^= ((j2 << i6) >> 63) & j3;
        }
        jArr2[i] = jArr2[i] ^ j5;
        int i7 = i + 1;
        jArr2[i7] = jArr2[i7] ^ j6;
    }

    private void implPermute(long[] jArr, int i, long[] jArr2) {
        int i2 = this.bits;
        int iIntValue = this.halfPowers.get(Integers.valueOf(i)).intValue();
        int iImplModAdd = implModAdd(i2, iIntValue, iIntValue);
        int iImplModAdd2 = implModAdd(i2, iImplModAdd, iImplModAdd);
        int iImplModAdd3 = implModAdd(i2, iImplModAdd2, iImplModAdd2);
        int iImplModAdd4 = i2 - iImplModAdd3;
        int iImplModAdd5 = implModAdd(i2, iImplModAdd4, iIntValue);
        int iImplModAdd6 = implModAdd(i2, iImplModAdd4, iImplModAdd);
        int iImplModAdd7 = implModAdd(i2, iImplModAdd5, iImplModAdd);
        int iImplModAdd8 = implModAdd(i2, iImplModAdd4, iImplModAdd2);
        int iImplModAdd9 = implModAdd(i2, iImplModAdd5, iImplModAdd2);
        int iImplModAdd10 = implModAdd(i2, iImplModAdd6, iImplModAdd2);
        int iImplModAdd11 = implModAdd(i2, iImplModAdd7, iImplModAdd2);
        int i3 = 0;
        while (true) {
            int i4 = this.size;
            if (i3 >= i4) {
                int i5 = i4 - 1;
                jArr2[i5] = jArr2[i5] & ((-1) >>> (-i2));
                return;
            }
            long j = 0;
            for (int i6 = 0; i6 < 64; i6 += 8) {
                iImplModAdd4 = implModAdd(i2, iImplModAdd4, iImplModAdd3);
                iImplModAdd5 = implModAdd(i2, iImplModAdd5, iImplModAdd3);
                iImplModAdd6 = implModAdd(i2, iImplModAdd6, iImplModAdd3);
                iImplModAdd7 = implModAdd(i2, iImplModAdd7, iImplModAdd3);
                iImplModAdd8 = implModAdd(i2, iImplModAdd8, iImplModAdd3);
                iImplModAdd9 = implModAdd(i2, iImplModAdd9, iImplModAdd3);
                iImplModAdd10 = implModAdd(i2, iImplModAdd10, iImplModAdd3);
                iImplModAdd11 = implModAdd(i2, iImplModAdd11, iImplModAdd3);
                j = j | (((jArr[iImplModAdd4 >>> 6] >>> iImplModAdd4) & 1) << i6) | (((jArr[iImplModAdd5 >>> 6] >>> iImplModAdd5) & 1) << (i6 + 1)) | (((jArr[iImplModAdd6 >>> 6] >>> iImplModAdd6) & 1) << (i6 + 2)) | (((jArr[iImplModAdd7 >>> 6] >>> iImplModAdd7) & 1) << (i6 + 3)) | (((jArr[iImplModAdd8 >>> 6] >>> iImplModAdd8) & 1) << (i6 + 4)) | (((jArr[iImplModAdd9 >>> 6] >>> iImplModAdd9) & 1) << (i6 + 5)) | (((jArr[iImplModAdd10 >>> 6] >>> iImplModAdd10) & 1) << (i6 + 6)) | (((jArr[iImplModAdd11 >>> 6] >>> iImplModAdd11) & 1) << (i6 + 7));
            }
            jArr2[i3] = j;
            i3++;
        }
    }

    private void implSquare(long[] jArr, long[] jArr2) {
        Interleave.expand64To128(jArr, 0, this.size, jArr2, 0);
    }

    void add(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i = 0; i < this.size; i++) {
            jArr3[i] = jArr[i] ^ jArr2[i];
        }
    }

    void addTo(long[] jArr, long[] jArr2) {
        for (int i = 0; i < this.size; i++) {
            jArr2[i] = jArr2[i] ^ jArr[i];
        }
    }

    void copy(long[] jArr, long[] jArr2) {
        for (int i = 0; i < this.size; i++) {
            jArr2[i] = jArr[i];
        }
    }

    long[] create() {
        return new long[this.size];
    }

    long[] createExt() {
        return new long[this.sizeExt];
    }

    void decodeBytes(byte[] bArr, long[] jArr) {
        int i = this.bits & 63;
        Pack.littleEndianToLong(bArr, 0, jArr, 0, this.size - 1);
        byte[] bArr2 = new byte[8];
        System.arraycopy(bArr, (this.size - 1) << 3, bArr2, 0, (i + 7) >>> 3);
        jArr[this.size - 1] = Pack.littleEndianToLong(bArr2, 0);
    }

    byte[] encodeBitsTransposed(long[] jArr) {
        byte[] bArr = new byte[this.bits];
        bArr[0] = (byte) (jArr[0] & 1);
        int i = 1;
        while (true) {
            int i2 = this.bits;
            if (i >= i2) {
                return bArr;
            }
            bArr[i2 - i] = (byte) ((jArr[i >>> 6] >>> (i & 63)) & 1);
            i++;
        }
    }

    void encodeBytes(long[] jArr, byte[] bArr) {
        int i = this.bits & 63;
        Pack.longToLittleEndian(jArr, 0, this.size - 1, bArr, 0);
        byte[] bArr2 = new byte[8];
        Pack.longToLittleEndian(jArr[this.size - 1], bArr2, 0);
        System.arraycopy(bArr2, 0, bArr, (this.size - 1) << 3, (i + 7) >>> 3);
    }

    int getSize() {
        return this.size;
    }

    int getSizeExt() {
        return this.sizeExt;
    }

    protected void implMultiplyAcc(long[] jArr, long[] jArr2, long[] jArr3) {
        int i;
        long[] jArr4 = new long[16];
        int i2 = 0;
        for (int i3 = 0; i3 < this.size; i3++) {
            implMulwAcc(jArr4, jArr[i3], jArr2[i3], jArr3, i3 << 1);
        }
        long j = jArr3[0];
        long j2 = jArr3[1];
        for (int i4 = 1; i4 < this.size; i4++) {
            int i5 = i4 << 1;
            j ^= jArr3[i5];
            jArr3[i4] = j ^ j2;
            j2 ^= jArr3[i5 + 1];
        }
        long j3 = j ^ j2;
        while (true) {
            i = this.size;
            if (i2 >= i) {
                break;
            }
            jArr3[i + i2] = jArr3[i2] ^ j3;
            i2++;
        }
        int i6 = i - 1;
        for (int i7 = 1; i7 < i6 * 2; i7++) {
            int iMin = Math.min(i6, i7);
            int i8 = i7 - iMin;
            for (int i9 = iMin; i8 < i9; i9--) {
                implMulwAcc(jArr4, jArr[i8] ^ jArr[i9], jArr2[i8] ^ jArr2[i9], jArr3, i7);
                i8++;
            }
        }
    }

    void inv(long[] jArr, long[] jArr2) {
        long[] jArrCreate = create();
        long[] jArrCreate2 = create();
        long[] jArrCreate3 = create();
        copy(jArr, jArrCreate);
        copy(jArr, jArrCreate3);
        int i = this.bits - 2;
        int iNumberOfLeadingZeros = 32 - Integers.numberOfLeadingZeros(i);
        for (int i2 = 1; i2 < iNumberOfLeadingZeros; i2++) {
            squareN(jArrCreate, 1 << (i2 - 1), jArrCreate2);
            multiply(jArrCreate, jArrCreate2, jArrCreate);
            int i3 = 1 << i2;
            if ((i & i3) != 0) {
                squareN(jArrCreate, (i3 - 1) & i, jArrCreate2);
                multiply(jArrCreate3, jArrCreate2, jArrCreate3);
            }
        }
        square(jArrCreate3, jArr2);
    }

    void multiply(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrCreateExt = createExt();
        implMultiplyAcc(jArr, jArr2, jArrCreateExt);
        reduce(jArrCreateExt, jArr3);
    }

    void reduce(long[] jArr, long[] jArr2) {
        int i = 64 - (this.bits & 63);
        int i2 = this.size;
        Nat.shiftUpBits64(i2, jArr, i2, i, jArr[i2 - 1], jArr2, 0);
        addTo(jArr, jArr2);
        int i3 = this.size - 1;
        jArr2[i3] = jArr2[i3] & ((-1) >>> i);
    }

    void square(long[] jArr, long[] jArr2) {
        long[] jArrCreateExt = createExt();
        implSquare(jArr, jArrCreateExt);
        reduce(jArrCreateExt, jArr2);
    }

    void squareN(long[] jArr, int i, long[] jArr2) {
        if (i >= 64) {
            implPermute(jArr, i, jArr2);
            return;
        }
        long[] jArrCreateExt = createExt();
        implSquare(jArr, jArrCreateExt);
        while (true) {
            reduce(jArrCreateExt, jArr2);
            i--;
            if (i <= 0) {
                return;
            } else {
                implSquare(jArr2, jArrCreateExt);
            }
        }
    }
}
