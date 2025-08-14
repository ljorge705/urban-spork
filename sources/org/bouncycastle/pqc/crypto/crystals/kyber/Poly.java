package org.bouncycastle.pqc.crypto.crystals.kyber;

import com.google.common.base.Ascii;

/* loaded from: classes4.dex */
class Poly {
    private KyberEngine engine;
    private int eta1;
    private int polyCompressedBytes;
    private Symmetric symmetric;
    private short[] coeffs = new short[256];
    private int eta2 = KyberEngine.getKyberEta2();

    public Poly(KyberEngine kyberEngine) {
        this.engine = kyberEngine;
        this.polyCompressedBytes = kyberEngine.getKyberPolyCompressedBytes();
        this.eta1 = kyberEngine.getKyberEta1();
        this.symmetric = kyberEngine.getSymmetric();
    }

    public static void baseMultMontgomery(Poly poly, Poly poly2, Poly poly3) {
        for (int i = 0; i < 64; i++) {
            int i2 = i * 4;
            int i3 = i2 + 1;
            int i4 = i + 64;
            Ntt.baseMult(poly, i2, poly2.getCoeffIndex(i2), poly2.getCoeffIndex(i3), poly3.getCoeffIndex(i2), poly3.getCoeffIndex(i3), Ntt.nttZetas[i4]);
            int i5 = i2 + 2;
            int i6 = i2 + 3;
            Ntt.baseMult(poly, i5, poly2.getCoeffIndex(i5), poly2.getCoeffIndex(i6), poly3.getCoeffIndex(i5), poly3.getCoeffIndex(i6), (short) (Ntt.nttZetas[i4] * (-1)));
        }
    }

    public void addCoeffs(Poly poly) {
        for (int i = 0; i < 256; i++) {
            setCoeffIndex(i, (short) (getCoeffIndex(i) + poly.getCoeffIndex(i)));
        }
    }

    public byte[] compressPoly() {
        int i = 8;
        byte[] bArr = new byte[8];
        byte[] bArr2 = new byte[this.polyCompressedBytes];
        conditionalSubQ();
        int i2 = this.polyCompressedBytes;
        if (i2 == 128) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < 32) {
                int i5 = 0;
                while (i5 < i) {
                    bArr[i5] = (byte) (((((getCoeffIndex((i3 * 8) + i5) << 4) + 1665) * 80635) >> 28) & 15);
                    i5++;
                    i = 8;
                }
                bArr2[i4] = (byte) (bArr[0] | (bArr[1] << 4));
                bArr2[i4 + 1] = (byte) (bArr[2] | (bArr[3] << 4));
                bArr2[i4 + 2] = (byte) (bArr[4] | (bArr[5] << 4));
                bArr2[i4 + 3] = (byte) (bArr[6] | (bArr[7] << 4));
                i4 += 4;
                i3++;
                i = 8;
            }
        } else {
            if (i2 != 160) {
                throw new RuntimeException("PolyCompressedBytes is neither 128 or 160!");
            }
            int i6 = 0;
            int i7 = 0;
            for (int i8 = 32; i6 < i8; i8 = 32) {
                for (int i9 = 0; i9 < 8; i9++) {
                    bArr[i9] = (byte) (((((getCoeffIndex((i6 * 8) + i9) << 5) + 1664) * 40318) >> 27) & 31);
                }
                bArr2[i7] = (byte) (bArr[0] | (bArr[1] << 5));
                bArr2[i7 + 1] = (byte) ((bArr[1] >> 3) | (bArr[2] << 2) | (bArr[3] << 7));
                bArr2[i7 + 2] = (byte) ((bArr[3] >> 1) | (bArr[4] << 4));
                bArr2[i7 + 3] = (byte) ((bArr[4] >> 4) | (bArr[5] << 1) | (bArr[6] << 6));
                bArr2[i7 + 4] = (byte) ((bArr[6] >> 2) | (bArr[7] << 3));
                i7 += 5;
                i6++;
            }
        }
        return bArr2;
    }

    public void conditionalSubQ() {
        for (int i = 0; i < 256; i++) {
            setCoeffIndex(i, Reduce.conditionalSubQ(getCoeffIndex(i)));
        }
    }

    public void convertToMont() {
        for (int i = 0; i < 256; i++) {
            setCoeffIndex(i, Reduce.montgomeryReduce(getCoeffIndex(i) * 1353));
        }
    }

    public void decompressPoly(byte[] bArr) {
        if (this.engine.getKyberPolyCompressedBytes() == 128) {
            int i = 0;
            for (int i2 = 0; i2 < 128; i2++) {
                int i3 = i2 * 2;
                setCoeffIndex(i3, (short) (((((short) (bArr[i] & 15)) * 3329) + 8) >> 4));
                setCoeffIndex(i3 + 1, (short) (((((short) ((bArr[i] & 255) >> 4)) * 3329) + 8) >> 4));
                i++;
            }
            return;
        }
        if (this.engine.getKyberPolyCompressedBytes() != 160) {
            throw new RuntimeException("PolyCompressedBytes is neither 128 or 160!");
        }
        byte[] bArr2 = new byte[8];
        int i4 = 0;
        for (int i5 = 0; i5 < 32; i5++) {
            bArr2[0] = (byte) (bArr[i4] & 255);
            int i6 = i4 + 1;
            bArr2[1] = (byte) (((bArr[i4] & 255) >> 5) | ((bArr[i6] & 255) << 3));
            bArr2[2] = (byte) ((bArr[i6] & 255) >> 2);
            int i7 = i4 + 2;
            bArr2[3] = (byte) (((bArr[i6] & 255) >> 7) | ((bArr[i7] & 255) << 1));
            int i8 = (bArr[i7] & 255) >> 4;
            int i9 = i4 + 3;
            bArr2[4] = (byte) (i8 | ((bArr[i9] & 255) << 4));
            bArr2[5] = (byte) ((bArr[i9] & 255) >> 1);
            int i10 = i4 + 4;
            bArr2[6] = (byte) (((bArr[i9] & 255) >> 6) | ((bArr[i10] & 255) << 2));
            bArr2[7] = (byte) ((bArr[i10] & 255) >> 3);
            i4 += 5;
            for (int i11 = 0; i11 < 8; i11++) {
                setCoeffIndex((i5 * 8) + i11, (short) ((((bArr2[i11] & Ascii.US) * KyberEngine.KyberQ) + 16) >> 5));
            }
        }
    }

    public void fromBytes(byte[] bArr) {
        for (int i = 0; i < 128; i++) {
            int i2 = i * 2;
            int i3 = i * 3;
            setCoeffIndex(i2, (short) (((bArr[i3] & 255) | ((bArr[i3 + 1] & 255) << 8)) & 4095));
            setCoeffIndex(i2 + 1, (short) ((((bArr[r4] & 255) >> 4) | ((bArr[i3 + 2] & 255) << 4)) & 4095));
        }
    }

    public void fromMsg(byte[] bArr) {
        if (bArr.length != 32) {
            throw new RuntimeException("KYBER_INDCPA_MSGBYTES must be equal to KYBER_N/8 bytes!");
        }
        for (int i = 0; i < 32; i++) {
            for (int i2 = 0; i2 < 8; i2++) {
                setCoeffIndex((i * 8) + i2, (short) (((short) (((short) (((bArr[i] & 255) >> i2) & 1)) * (-1))) & 1665));
            }
        }
    }

    public short getCoeffIndex(int i) {
        return this.coeffs[i];
    }

    public short[] getCoeffs() {
        return this.coeffs;
    }

    public void getEta1Noise(byte[] bArr, byte b) {
        byte[] bArr2 = new byte[(this.eta1 * 256) / 4];
        this.symmetric.prf(bArr2, bArr, b);
        CBD.kyberCBD(this, bArr2, this.eta1);
    }

    public void getEta2Noise(byte[] bArr, byte b) {
        byte[] bArr2 = new byte[(this.eta2 * 256) / 4];
        this.symmetric.prf(bArr2, bArr, b);
        CBD.kyberCBD(this, bArr2, this.eta2);
    }

    public void polyInverseNttToMont() {
        setCoeffs(Ntt.invNtt(getCoeffs()));
    }

    public void polyNtt() {
        setCoeffs(Ntt.ntt(getCoeffs()));
        reduce();
    }

    public void polySubtract(Poly poly) {
        for (int i = 0; i < 256; i++) {
            setCoeffIndex(i, (short) (poly.getCoeffIndex(i) - getCoeffIndex(i)));
        }
    }

    public void reduce() {
        for (int i = 0; i < 256; i++) {
            setCoeffIndex(i, Reduce.barretReduce(getCoeffIndex(i)));
        }
    }

    public void setCoeffIndex(int i, short s) {
        this.coeffs[i] = s;
    }

    public void setCoeffs(short[] sArr) {
        this.coeffs = sArr;
    }

    public byte[] toBytes() {
        byte[] bArr = new byte[384];
        conditionalSubQ();
        for (int i = 0; i < 128; i++) {
            int i2 = i * 2;
            short coeffIndex = getCoeffIndex(i2);
            short coeffIndex2 = getCoeffIndex(i2 + 1);
            int i3 = i * 3;
            bArr[i3] = (byte) coeffIndex;
            bArr[i3 + 1] = (byte) ((coeffIndex >> 8) | (coeffIndex2 << 4));
            bArr[i3 + 2] = (byte) (coeffIndex2 >> 4);
        }
        return bArr;
    }

    public byte[] toMsg() {
        byte[] bArr = new byte[KyberEngine.getKyberIndCpaMsgBytes()];
        conditionalSubQ();
        for (int i = 0; i < 32; i++) {
            bArr[i] = 0;
            for (int i2 = 0; i2 < 8; i2++) {
                bArr[i] = (byte) (((byte) (((((((getCoeffIndex((i * 8) + i2) & 65535) << 1) + 1665) * 80635) >> 28) & 1) << i2)) | bArr[i]);
            }
        }
        return bArr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("[");
        int i = 0;
        while (true) {
            short[] sArr = this.coeffs;
            if (i >= sArr.length) {
                stringBuffer.append("]");
                return stringBuffer.toString();
            }
            stringBuffer.append((int) sArr[i]);
            if (i != this.coeffs.length - 1) {
                stringBuffer.append(", ");
            }
            i++;
        }
    }
}
