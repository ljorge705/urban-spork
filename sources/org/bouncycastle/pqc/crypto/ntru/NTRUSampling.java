package org.bouncycastle.pqc.crypto.ntru;

import net.sf.scuba.smartcards.ISO7816;
import org.bouncycastle.pqc.math.ntru.HPSPolynomial;
import org.bouncycastle.pqc.math.ntru.HRSSPolynomial;
import org.bouncycastle.pqc.math.ntru.Polynomial;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUHPSParameterSet;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUHRSSParameterSet;
import org.bouncycastle.pqc.math.ntru.parameters.NTRUParameterSet;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
class NTRUSampling {
    private final NTRUParameterSet params;

    public NTRUSampling(NTRUParameterSet nTRUParameterSet) {
        this.params = nTRUParameterSet;
    }

    private static int mod3(int i) {
        return i % 3;
    }

    public PolynomialPair sampleFg(byte[] bArr) {
        NTRUParameterSet nTRUParameterSet = this.params;
        if (nTRUParameterSet instanceof NTRUHRSSParameterSet) {
            return new PolynomialPair(sampleIidPlus(Arrays.copyOfRange(bArr, 0, nTRUParameterSet.sampleIidBytes())), sampleIidPlus(Arrays.copyOfRange(bArr, this.params.sampleIidBytes(), bArr.length)));
        }
        if (nTRUParameterSet instanceof NTRUHPSParameterSet) {
            return new PolynomialPair((HPSPolynomial) sampleIid(Arrays.copyOfRange(bArr, 0, nTRUParameterSet.sampleIidBytes())), sampleFixedType(Arrays.copyOfRange(bArr, this.params.sampleIidBytes(), bArr.length)));
        }
        throw new IllegalArgumentException("Invalid polynomial type");
    }

    public HPSPolynomial sampleFixedType(byte[] bArr) {
        int i;
        int i2;
        int iN = this.params.n();
        int iWeight = ((NTRUHPSParameterSet) this.params).weight();
        HPSPolynomial hPSPolynomial = new HPSPolynomial((NTRUHPSParameterSet) this.params);
        int i3 = iN - 1;
        int[] iArr = new int[i3];
        int i4 = 0;
        while (true) {
            i = i3 / 4;
            if (i4 >= i) {
                break;
            }
            int i5 = i4 * 4;
            int i6 = i4 * 15;
            iArr[i5] = ((bArr[i6] & 255) << 2) + ((bArr[i6 + 1] & 255) << 10) + ((bArr[i6 + 2] & 255) << 18) + ((bArr[i6 + 3] & 255) << 26);
            iArr[i5 + 1] = ((bArr[(i4 * 3) + 15] & ISO7816.INS_GET_RESPONSE) >> 4) + ((bArr[i6 + 4] & 255) << 4) + ((bArr[i6 + 5] & 255) << 12) + ((bArr[i6 + 6] & 255) << 20) + ((bArr[i6 + 7] & 255) << 28);
            int i7 = ((bArr[(i4 * 7) + 15] & 240) >> 2) + ((bArr[i6 + 8] & 255) << 6) + ((bArr[i6 + 9] & 255) << 14) + ((bArr[i6 + 10] & 255) << 22);
            byte b = bArr[i6 + 11];
            iArr[i5 + 2] = i7 + ((b & 255) << 30);
            iArr[i5 + 3] = (b & 252) + ((bArr[i6 + 12] & 255) << 8) + ((bArr[i6 + 13] & 255) << 16) + ((bArr[i6 + 14] & 255) << 24);
            i4++;
        }
        int i8 = i * 4;
        if (i3 > i8) {
            int i9 = i * 15;
            iArr[i8] = ((bArr[i9] & 255) << 2) + ((bArr[i9 + 1] & 255) << 10) + ((bArr[i9 + 2] & 255) << 18) + ((bArr[i9 + 3] & 255) << 26);
            iArr[i8 + 1] = ((bArr[(i * 3) + 15] & ISO7816.INS_GET_RESPONSE) >> 4) + ((bArr[i9 + 4] & 255) << 4) + ((bArr[i9 + 5] & 255) << 12) + ((bArr[i9 + 6] & 255) << 20) + ((bArr[i9 + 7] & 255) << 28);
        }
        int i10 = 0;
        while (true) {
            i2 = iWeight / 2;
            if (i10 >= i2) {
                break;
            }
            iArr[i10] = iArr[i10] | 1;
            i10++;
        }
        while (i2 < iWeight) {
            iArr[i2] = iArr[i2] | 2;
            i2++;
        }
        java.util.Arrays.sort(iArr);
        for (int i11 = 0; i11 < i3; i11++) {
            hPSPolynomial.coeffs[i11] = (short) (iArr[i11] & 3);
        }
        hPSPolynomial.coeffs[i3] = 0;
        return hPSPolynomial;
    }

    public Polynomial sampleIid(byte[] bArr) {
        Polynomial polynomialCreatePolynomial = this.params.createPolynomial();
        for (int i = 0; i < this.params.n() - 1; i++) {
            polynomialCreatePolynomial.coeffs[i] = (short) mod3(bArr[i] & 255);
        }
        polynomialCreatePolynomial.coeffs[this.params.n() - 1] = 0;
        return polynomialCreatePolynomial;
    }

    public HRSSPolynomial sampleIidPlus(byte[] bArr) {
        int i;
        int iN = this.params.n();
        HRSSPolynomial hRSSPolynomial = (HRSSPolynomial) sampleIid(bArr);
        int i2 = 0;
        while (true) {
            i = iN - 1;
            if (i2 >= i) {
                break;
            }
            hRSSPolynomial.coeffs[i2] = (short) (hRSSPolynomial.coeffs[i2] | (-(hRSSPolynomial.coeffs[i2] >>> 1)));
            i2++;
        }
        int i3 = 0;
        short s = 0;
        while (i3 < i) {
            int i4 = i3 + 1;
            s = (short) (s + ((short) (hRSSPolynomial.coeffs[i4] * hRSSPolynomial.coeffs[i3])));
            i3 = i4;
        }
        short s2 = (short) ((-((s & 65535) >>> 15)) | 1);
        for (int i5 = 0; i5 < i; i5 += 2) {
            hRSSPolynomial.coeffs[i5] = (short) (hRSSPolynomial.coeffs[i5] * s2);
        }
        for (int i6 = 0; i6 < i; i6++) {
            hRSSPolynomial.coeffs[i6] = (short) (((hRSSPolynomial.coeffs[i6] & 65535) ^ ((hRSSPolynomial.coeffs[i6] & 65535) >>> 15)) & 3);
        }
        return hRSSPolynomial;
    }

    public PolynomialPair sampleRm(byte[] bArr) {
        NTRUParameterSet nTRUParameterSet = this.params;
        if (nTRUParameterSet instanceof NTRUHRSSParameterSet) {
            return new PolynomialPair((HRSSPolynomial) sampleIid(Arrays.copyOfRange(bArr, 0, nTRUParameterSet.sampleIidBytes())), (HRSSPolynomial) sampleIid(Arrays.copyOfRange(bArr, this.params.sampleIidBytes(), bArr.length)));
        }
        if (nTRUParameterSet instanceof NTRUHPSParameterSet) {
            return new PolynomialPair((HPSPolynomial) sampleIid(Arrays.copyOfRange(bArr, 0, nTRUParameterSet.sampleIidBytes())), sampleFixedType(Arrays.copyOfRange(bArr, this.params.sampleIidBytes(), bArr.length)));
        }
        throw new IllegalArgumentException("Invalid polynomial type");
    }
}
