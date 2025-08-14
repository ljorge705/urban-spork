package org.bouncycastle.pqc.math.ntru;

import org.bouncycastle.pqc.math.ntru.parameters.NTRUHRSSParameterSet;

/* loaded from: classes4.dex */
public class HRSSPolynomial extends Polynomial {
    public HRSSPolynomial(NTRUHRSSParameterSet nTRUHRSSParameterSet) {
        super(nTRUHRSSParameterSet);
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void lift(Polynomial polynomial) {
        int length = this.coeffs.length;
        Polynomial polynomialCreatePolynomial = this.params.createPolynomial();
        short s = (short) (3 - (length % 3));
        short[] sArr = polynomialCreatePolynomial.coeffs;
        int i = 0;
        int i2 = 2 - s;
        int i3 = polynomial.coeffs[0] * i2;
        short s2 = polynomial.coeffs[1];
        sArr[0] = (short) (i3 + (polynomial.coeffs[2] * s));
        short[] sArr2 = polynomialCreatePolynomial.coeffs;
        int i4 = polynomial.coeffs[1] * i2;
        short s3 = polynomial.coeffs[2];
        sArr2[1] = (short) i4;
        polynomialCreatePolynomial.coeffs[2] = (short) (polynomial.coeffs[2] * i2);
        short s4 = 0;
        for (int i5 = 3; i5 < length; i5++) {
            short[] sArr3 = polynomialCreatePolynomial.coeffs;
            sArr3[0] = (short) (sArr3[0] + (polynomial.coeffs[i5] * ((s * 2) + s4)));
            short[] sArr4 = polynomialCreatePolynomial.coeffs;
            int i6 = s4 + s;
            sArr4[1] = (short) (sArr4[1] + (polynomial.coeffs[i5] * i6));
            short[] sArr5 = polynomialCreatePolynomial.coeffs;
            sArr5[2] = (short) (sArr5[2] + (polynomial.coeffs[i5] * s4));
            s4 = (short) (i6 % 3);
        }
        short[] sArr6 = polynomialCreatePolynomial.coeffs;
        int i7 = s + s4;
        sArr6[1] = (short) (sArr6[1] + (polynomial.coeffs[0] * i7));
        short[] sArr7 = polynomialCreatePolynomial.coeffs;
        sArr7[2] = (short) (sArr7[2] + (polynomial.coeffs[0] * s4));
        short[] sArr8 = polynomialCreatePolynomial.coeffs;
        sArr8[2] = (short) (sArr8[2] + (polynomial.coeffs[1] * i7));
        for (int i8 = 3; i8 < length; i8++) {
            polynomialCreatePolynomial.coeffs[i8] = (short) (polynomialCreatePolynomial.coeffs[i8 - 3] + ((polynomial.coeffs[i8] + polynomial.coeffs[i8 - 1] + polynomial.coeffs[i8 - 2]) * 2));
        }
        polynomialCreatePolynomial.mod3PhiN();
        polynomialCreatePolynomial.z3ToZq();
        this.coeffs[0] = (short) (-polynomialCreatePolynomial.coeffs[0]);
        while (i < length - 1) {
            int i9 = i + 1;
            this.coeffs[i9] = (short) (polynomialCreatePolynomial.coeffs[i] - polynomialCreatePolynomial.coeffs[i9]);
            i = i9;
        }
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public void sqFromBytes(byte[] bArr) {
        int i = 0;
        while (i < this.params.packDegree() / 8) {
            int i2 = i * 8;
            int i3 = i * 13;
            int i4 = i3 + 1;
            this.coeffs[i2] = (short) ((bArr[i3] & 255) | ((((short) (bArr[i4] & 255)) & 31) << 8));
            int i5 = i3 + 3;
            this.coeffs[i2 + 1] = (short) (((bArr[i4] & 255) >>> 5) | (((short) (bArr[i3 + 2] & 255)) << 3) | ((((short) (bArr[i5] & 255)) & 3) << 11));
            int i6 = (bArr[i5] & 255) >>> 2;
            int i7 = i3 + 4;
            this.coeffs[i2 + 2] = (short) (i6 | ((((short) (bArr[i7] & 255)) & 127) << 6));
            int i8 = ((bArr[i7] & 255) >>> 7) | (((short) (bArr[i3 + 5] & 255)) << 1);
            int i9 = i3 + 6;
            this.coeffs[i2 + 3] = (short) (i8 | ((((short) (bArr[i9] & 255)) & 15) << 9));
            int i10 = i3 + 8;
            this.coeffs[i2 + 4] = (short) ((((short) (bArr[i3 + 7] & 255)) << 4) | ((bArr[i9] & 255) >>> 4) | ((((short) (bArr[i10] & 255)) & 1) << 12));
            int i11 = (bArr[i10] & 255) >>> 1;
            int i12 = i3 + 9;
            this.coeffs[i2 + 5] = (short) (i11 | ((((short) (bArr[i12] & 255)) & 63) << 7));
            int i13 = i3 + 11;
            this.coeffs[i2 + 6] = (short) ((((short) (bArr[i3 + 10] & 255)) << 2) | ((bArr[i12] & 255) >>> 6) | ((((short) (bArr[i13] & 255)) & 7) << 10));
            this.coeffs[i2 + 7] = (short) (((bArr[i13] & 255) >>> 3) | (((short) (bArr[i3 + 12] & 255)) << 5));
            i++;
        }
        int iPackDegree = this.params.packDegree() & 7;
        if (iPackDegree == 2) {
            int i14 = i * 8;
            int i15 = i * 13;
            int i16 = i15 + 1;
            this.coeffs[i14] = (short) ((bArr[i15] & 255) | ((((short) (bArr[i16] & 255)) & 31) << 8));
            this.coeffs[i14 + 1] = (short) (((((short) (bArr[i15 + 3] & 255)) & 3) << 11) | ((bArr[i16] & 255) >>> 5) | (((short) (bArr[i15 + 2] & 255)) << 3));
        } else if (iPackDegree == 4) {
            int i17 = i * 8;
            int i18 = i * 13;
            int i19 = i18 + 1;
            this.coeffs[i17] = (short) ((bArr[i18] & 255) | ((((short) (bArr[i19] & 255)) & 31) << 8));
            int i20 = i18 + 3;
            this.coeffs[i17 + 1] = (short) (((bArr[i19] & 255) >>> 5) | (((short) (bArr[i18 + 2] & 255)) << 3) | ((((short) (bArr[i20] & 255)) & 3) << 11));
            int i21 = i18 + 4;
            this.coeffs[i17 + 2] = (short) (((bArr[i20] & 255) >>> 2) | ((((short) (bArr[i21] & 255)) & 127) << 6));
            this.coeffs[i17 + 3] = (short) (((((short) (bArr[i18 + 6] & 255)) & 15) << 9) | ((bArr[i21] & 255) >>> 7) | (((short) (bArr[i18 + 5] & 255)) << 1));
        }
        this.coeffs[this.params.n() - 1] = 0;
    }

    @Override // org.bouncycastle.pqc.math.ntru.Polynomial
    public byte[] sqToBytes(int i) {
        byte[] bArr = new byte[i];
        short[] sArr = new short[8];
        int i2 = 0;
        while (true) {
            short s = 65535;
            if (i2 >= this.params.packDegree() / 8) {
                break;
            }
            int i3 = 0;
            while (i3 < 8) {
                sArr[i3] = (short) modQ(this.coeffs[(i2 * 8) + i3] & s, this.params.q());
                i3++;
                s = 65535;
            }
            int i4 = i2 * 13;
            short s2 = sArr[0];
            bArr[i4] = (byte) (s2 & 255);
            short s3 = sArr[1];
            bArr[i4 + 1] = (byte) ((s2 >>> 8) | ((s3 & 7) << 5));
            bArr[i4 + 2] = (byte) ((s3 >>> 3) & 255);
            int i5 = s3 >>> 11;
            short s4 = sArr[2];
            bArr[i4 + 3] = (byte) (((s4 & 63) << 2) | i5);
            short s5 = sArr[3];
            bArr[i4 + 4] = (byte) ((s4 >>> 6) | ((s5 & 1) << 7));
            bArr[i4 + 5] = (byte) ((s5 >>> 1) & 255);
            int i6 = s5 >>> 9;
            short s6 = sArr[4];
            bArr[i4 + 6] = (byte) (((s6 & 15) << 4) | i6);
            bArr[i4 + 7] = (byte) ((s6 >>> 4) & 255);
            short s7 = sArr[5];
            bArr[i4 + 8] = (byte) ((s6 >>> 12) | ((s7 & 127) << 1));
            int i7 = s7 >>> 7;
            short s8 = sArr[6];
            bArr[i4 + 9] = (byte) (((s8 & 3) << 6) | i7);
            bArr[i4 + 10] = (byte) ((s8 >>> 2) & 255);
            short s9 = sArr[7];
            bArr[i4 + 11] = (byte) ((s8 >>> 10) | ((s9 & 31) << 3));
            bArr[i4 + 12] = (byte) (s9 >>> 5);
            i2++;
        }
        int i8 = 0;
        while (true) {
            int i9 = i2 * 8;
            if (i8 >= this.params.packDegree() - i9) {
                break;
            }
            sArr[i8] = (short) modQ(this.coeffs[i9 + i8] & 65535, this.params.q());
            i8++;
        }
        while (i8 < 8) {
            sArr[i8] = 0;
            i8++;
        }
        int iPackDegree = this.params.packDegree() - ((this.params.packDegree() / 8) * 8);
        if (iPackDegree == 2) {
            int i10 = i2 * 13;
            short s10 = sArr[0];
            bArr[i10] = (byte) (s10 & 255);
            int i11 = s10 >>> 8;
            short s11 = sArr[1];
            bArr[i10 + 1] = (byte) (i11 | ((s11 & 7) << 5));
            bArr[i10 + 2] = (byte) ((s11 >>> 3) & 255);
            bArr[i10 + 3] = (byte) ((s11 >>> 11) | ((sArr[2] & 63) << 2));
        } else if (iPackDegree == 4) {
            int i12 = i2 * 13;
            short s12 = sArr[0];
            bArr[i12] = (byte) (s12 & 255);
            short s13 = sArr[1];
            bArr[i12 + 1] = (byte) ((s12 >>> 8) | ((s13 & 7) << 5));
            bArr[i12 + 2] = (byte) ((s13 >>> 3) & 255);
            int i13 = s13 >>> 11;
            short s14 = sArr[2];
            bArr[i12 + 3] = (byte) (i13 | ((s14 & 63) << 2));
            int i14 = s14 >>> 6;
            short s15 = sArr[3];
            bArr[i12 + 4] = (byte) (((s15 & 1) << 7) | i14);
            bArr[i12 + 5] = (byte) ((s15 >>> 1) & 255);
            bArr[i12 + 6] = (byte) ((s15 >>> 9) | ((sArr[4] & 15) << 4));
            int i102 = i2 * 13;
            short s102 = sArr[0];
            bArr[i102] = (byte) (s102 & 255);
            int i112 = s102 >>> 8;
            short s112 = sArr[1];
            bArr[i102 + 1] = (byte) (i112 | ((s112 & 7) << 5));
            bArr[i102 + 2] = (byte) ((s112 >>> 3) & 255);
            bArr[i102 + 3] = (byte) ((s112 >>> 11) | ((sArr[2] & 63) << 2));
        }
        return bArr;
    }
}
