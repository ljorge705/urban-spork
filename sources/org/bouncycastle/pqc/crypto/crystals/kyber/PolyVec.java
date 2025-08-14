package org.bouncycastle.pqc.crypto.crystals.kyber;

import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
class PolyVec {
    private KyberEngine engine;
    private int kyberK;
    private int polyVecBytes;
    Poly[] vec;

    public PolyVec() throws Exception {
        throw new Exception("Requires Parameter");
    }

    public PolyVec(KyberEngine kyberEngine) {
        this.engine = kyberEngine;
        this.kyberK = kyberEngine.getKyberK();
        this.polyVecBytes = kyberEngine.getKyberPolyVecBytes();
        this.vec = new Poly[this.kyberK];
        for (int i = 0; i < this.kyberK; i++) {
            this.vec[i] = new Poly(kyberEngine);
        }
    }

    public static void pointwiseAccountMontgomery(Poly poly, PolyVec polyVec, PolyVec polyVec2, KyberEngine kyberEngine) {
        Poly poly2 = new Poly(kyberEngine);
        Poly.baseMultMontgomery(poly, polyVec.getVectorIndex(0), polyVec2.getVectorIndex(0));
        for (int i = 1; i < kyberEngine.getKyberK(); i++) {
            Poly.baseMultMontgomery(poly2, polyVec.getVectorIndex(i), polyVec2.getVectorIndex(i));
            poly.addCoeffs(poly2);
        }
        poly.reduce();
    }

    public void addPoly(PolyVec polyVec) {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).addCoeffs(polyVec.getVectorIndex(i));
        }
    }

    public byte[] compressPolyVec() {
        conditionalSubQ();
        byte[] bArr = new byte[this.engine.getKyberPolyVecCompressedBytes()];
        int i = 32;
        int i2 = 4;
        if (this.engine.getKyberPolyVecCompressedBytes() == this.kyberK * 320) {
            short[] sArr = new short[4];
            int i3 = 0;
            int i4 = 0;
            while (i3 < this.kyberK) {
                int i5 = 0;
                while (i5 < 64) {
                    int i6 = 0;
                    while (i6 < i2) {
                        sArr[i6] = (short) (((((getVectorIndex(i3).getCoeffIndex((i5 * 4) + i6) << 10) + 1665) * 1290167) >> 32) & 1023);
                        i6++;
                        i2 = 4;
                    }
                    short s = sArr[0];
                    bArr[i4] = (byte) s;
                    short s2 = sArr[1];
                    bArr[i4 + 1] = (byte) ((s >> 8) | (s2 << 2));
                    short s3 = sArr[2];
                    bArr[i4 + 2] = (byte) ((s2 >> 6) | (s3 << 4));
                    int i7 = s3 >> 4;
                    short s4 = sArr[3];
                    bArr[i4 + 3] = (byte) ((s4 << 6) | i7);
                    bArr[i4 + 4] = (byte) (s4 >> 2);
                    i4 += 5;
                    i5++;
                    i2 = 4;
                }
                i3++;
                i2 = 4;
            }
        } else {
            if (this.engine.getKyberPolyVecCompressedBytes() != this.kyberK * 352) {
                throw new RuntimeException("Kyber PolyVecCompressedBytes neither 320 * KyberK or 352 * KyberK!");
            }
            short[] sArr2 = new short[8];
            int i8 = 0;
            int i9 = 0;
            while (i8 < this.kyberK) {
                int i10 = 0;
                while (i10 < i) {
                    for (int i11 = 0; i11 < 8; i11++) {
                        sArr2[i11] = (short) (((((getVectorIndex(i8).getCoeffIndex((i10 * 8) + i11) << 11) + 1664) * 645084) >> 31) & 2047);
                    }
                    short s5 = sArr2[0];
                    bArr[i9] = (byte) s5;
                    short s6 = sArr2[1];
                    bArr[i9 + 1] = (byte) ((s5 >> 8) | (s6 << 3));
                    short s7 = sArr2[2];
                    bArr[i9 + 2] = (byte) ((s6 >> 5) | (s7 << 6));
                    bArr[i9 + 3] = (byte) (s7 >> 2);
                    int i12 = s7 >> 10;
                    short s8 = sArr2[3];
                    bArr[i9 + 4] = (byte) (i12 | (s8 << 1));
                    short s9 = sArr2[4];
                    bArr[i9 + 5] = (byte) ((s8 >> 7) | (s9 << 4));
                    short s10 = sArr2[5];
                    bArr[i9 + 6] = (byte) ((s9 >> 4) | (s10 << 7));
                    bArr[i9 + 7] = (byte) (s10 >> 1);
                    int i13 = s10 >> 9;
                    short s11 = sArr2[6];
                    bArr[i9 + 8] = (byte) (i13 | (s11 << 2));
                    int i14 = s11 >> 6;
                    short s12 = sArr2[7];
                    bArr[i9 + 9] = (byte) (i14 | (s12 << 5));
                    bArr[i9 + 10] = (byte) (s12 >> 3);
                    i9 += 11;
                    i10++;
                    i = 32;
                }
                i8++;
                i = 32;
            }
        }
        return bArr;
    }

    public void conditionalSubQ() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).conditionalSubQ();
        }
    }

    public void decompressPolyVec(byte[] bArr) {
        if (this.engine.getKyberPolyVecCompressedBytes() == this.kyberK * 320) {
            short[] sArr = new short[4];
            int i = 0;
            for (int i2 = 0; i2 < this.kyberK; i2++) {
                for (int i3 = 0; i3 < 64; i3++) {
                    int i4 = bArr[i] & 255;
                    byte b = bArr[i + 1];
                    sArr[0] = (short) (i4 | ((short) ((b & 255) << 8)));
                    int i5 = (b & 255) >> 2;
                    byte b2 = bArr[i + 2];
                    sArr[1] = (short) (i5 | ((short) ((b2 & 255) << 6)));
                    int i6 = (b2 & 255) >> 4;
                    byte b3 = bArr[i + 3];
                    sArr[2] = (short) (i6 | ((short) ((b3 & 255) << 4)));
                    sArr[3] = (short) (((b3 & 255) >> 6) | ((short) ((bArr[i + 4] & 255) << 2)));
                    i += 5;
                    for (int i7 = 0; i7 < 4; i7++) {
                        this.vec[i2].setCoeffIndex((i3 * 4) + i7, (short) ((((sArr[i7] & 1023) * KyberEngine.KyberQ) + 512) >> 10));
                    }
                }
            }
            return;
        }
        if (this.engine.getKyberPolyVecCompressedBytes() != this.kyberK * 352) {
            throw new RuntimeException("Kyber PolyVecCompressedBytes neither 320 * KyberK or 352 * KyberK!");
        }
        short[] sArr2 = new short[8];
        int i8 = 0;
        for (int i9 = 0; i9 < this.kyberK; i9++) {
            for (int i10 = 0; i10 < 32; i10++) {
                int i11 = bArr[i8] & 255;
                byte b4 = bArr[i8 + 1];
                sArr2[0] = (short) (i11 | (((short) (b4 & 255)) << 8));
                int i12 = (b4 & 255) >> 3;
                byte b5 = bArr[i8 + 2];
                sArr2[1] = (short) (i12 | (((short) (b5 & 255)) << 5));
                int i13 = ((b5 & 255) >> 6) | (((short) (bArr[i8 + 3] & 255)) << 2);
                byte b6 = bArr[i8 + 4];
                sArr2[2] = (short) (i13 | ((short) ((b6 & 255) << 10)));
                int i14 = (b6 & 255) >> 1;
                byte b7 = bArr[i8 + 5];
                sArr2[3] = (short) (i14 | (((short) (b7 & 255)) << 7));
                int i15 = (b7 & 255) >> 4;
                byte b8 = bArr[i8 + 6];
                sArr2[4] = (short) (i15 | (((short) (b8 & 255)) << 4));
                int i16 = ((b8 & 255) >> 7) | (((short) (bArr[i8 + 7] & 255)) << 1);
                byte b9 = bArr[i8 + 8];
                sArr2[5] = (short) (i16 | ((short) ((b9 & 255) << 9)));
                int i17 = (b9 & 255) >> 2;
                byte b10 = bArr[i8 + 9];
                sArr2[6] = (short) (i17 | (((short) (b10 & 255)) << 6));
                sArr2[7] = (short) (((b10 & 255) >> 5) | (((short) (bArr[i8 + 10] & 255)) << 3));
                i8 += 11;
                for (int i18 = 0; i18 < 8; i18++) {
                    this.vec[i9].setCoeffIndex((i10 * 8) + i18, (short) ((((sArr2[i18] & 2047) * KyberEngine.KyberQ) + 1024) >> 11));
                }
            }
        }
    }

    public void fromBytes(byte[] bArr) {
        int i = 0;
        while (i < this.kyberK) {
            Poly vectorIndex = getVectorIndex(i);
            int i2 = i * 384;
            i++;
            vectorIndex.fromBytes(Arrays.copyOfRange(bArr, i2, i * 384));
        }
    }

    public Poly getVectorIndex(int i) {
        return this.vec[i];
    }

    public void polyVecInverseNttToMont() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).polyInverseNttToMont();
        }
    }

    public void polyVecNtt() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).polyNtt();
        }
    }

    public void reducePoly() {
        for (int i = 0; i < this.kyberK; i++) {
            getVectorIndex(i).reduce();
        }
    }

    public byte[] toBytes() {
        byte[] bArr = new byte[this.polyVecBytes];
        for (int i = 0; i < this.kyberK; i++) {
            System.arraycopy(this.vec[i].toBytes(), 0, bArr, i * 384, 384);
        }
        return bArr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("[");
        for (int i = 0; i < this.kyberK; i++) {
            stringBuffer.append(this.vec[i].toString());
            if (i != this.kyberK - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
