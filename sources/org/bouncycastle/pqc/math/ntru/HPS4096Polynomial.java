package org.bouncycastle.pqc.math.ntru;

import org.bouncycastle.pqc.math.ntru.parameters.NTRUHPSParameterSet;

/* loaded from: classes4.dex */
public class HPS4096Polynomial extends HPSPolynomial {
    public HPS4096Polynomial(NTRUHPSParameterSet nTRUHPSParameterSet) {
        super(nTRUHPSParameterSet);
    }

    @Override // org.bouncycastle.pqc.math.ntru.HPSPolynomial, org.bouncycastle.pqc.math.ntru.Polynomial
    public void sqFromBytes(byte[] bArr) {
        for (int i = 0; i < this.params.packDegree() / 2; i++) {
            int i2 = i * 2;
            int i3 = i * 3;
            int i4 = i3 + 1;
            this.coeffs[i2] = (short) ((bArr[i3] & 255) | ((((short) (bArr[i4] & 255)) & 15) << 8));
            this.coeffs[i2 + 1] = (short) (((((short) (bArr[i3 + 2] & 255)) & 255) << 4) | ((bArr[i4] & 255) >>> 4));
        }
        this.coeffs[this.params.n() - 1] = 0;
    }

    @Override // org.bouncycastle.pqc.math.ntru.HPSPolynomial, org.bouncycastle.pqc.math.ntru.Polynomial
    public byte[] sqToBytes(int i) {
        byte[] bArr = new byte[i];
        int iQ = this.params.q();
        for (int i2 = 0; i2 < this.params.packDegree() / 2; i2++) {
            int i3 = i2 * 3;
            int i4 = i2 * 2;
            bArr[i3] = (byte) (modQ(this.coeffs[i4] & 65535, iQ) & 255);
            int iModQ = modQ(this.coeffs[i4] & 65535, iQ) >>> 8;
            int i5 = i4 + 1;
            bArr[i3 + 1] = (byte) (iModQ | ((modQ(this.coeffs[i5] & 65535, iQ) & 15) << 4));
            bArr[i3 + 2] = (byte) (modQ(this.coeffs[i5] & 65535, iQ) >>> 4);
        }
        return bArr;
    }
}
