package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes4.dex */
public class HMacDSAKCalculator implements DSAKCalculator {
    private final byte[] K;
    private final byte[] V;
    private final HMac hMac;

    /* renamed from: n, reason: collision with root package name */
    private BigInteger f333n;

    public HMacDSAKCalculator(Digest digest) {
        HMac hMac = new HMac(digest);
        this.hMac = hMac;
        int macSize = hMac.getMacSize();
        this.V = new byte[macSize];
        this.K = new byte[macSize];
    }

    private BigInteger bitsToInt(byte[] bArr) {
        int length = bArr.length * 8;
        int iBitLength = this.f333n.bitLength();
        BigInteger bigIntegerFromUnsignedByteArray = BigIntegers.fromUnsignedByteArray(bArr);
        return length > iBitLength ? bigIntegerFromUnsignedByteArray.shiftRight(length - iBitLength) : bigIntegerFromUnsignedByteArray;
    }

    @Override // org.bouncycastle.crypto.signers.DSAKCalculator
    public void init(BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.f333n = bigInteger;
        BigInteger bigIntegerBitsToInt = bitsToInt(bArr);
        if (bigIntegerBitsToInt.compareTo(bigInteger) >= 0) {
            bigIntegerBitsToInt = bigIntegerBitsToInt.subtract(bigInteger);
        }
        int unsignedByteLength = BigIntegers.getUnsignedByteLength(bigInteger);
        byte[] bArrAsUnsignedByteArray = BigIntegers.asUnsignedByteArray(unsignedByteLength, bigInteger2);
        byte[] bArrAsUnsignedByteArray2 = BigIntegers.asUnsignedByteArray(unsignedByteLength, bigIntegerBitsToInt);
        Arrays.fill(this.K, (byte) 0);
        Arrays.fill(this.V, (byte) 1);
        this.hMac.init(new KeyParameter(this.K));
        HMac hMac = this.hMac;
        byte[] bArr2 = this.V;
        hMac.update(bArr2, 0, bArr2.length);
        this.hMac.update((byte) 0);
        this.hMac.update(bArrAsUnsignedByteArray, 0, bArrAsUnsignedByteArray.length);
        this.hMac.update(bArrAsUnsignedByteArray2, 0, bArrAsUnsignedByteArray2.length);
        initAdditionalInput0(this.hMac);
        this.hMac.doFinal(this.K, 0);
        this.hMac.init(new KeyParameter(this.K));
        HMac hMac2 = this.hMac;
        byte[] bArr3 = this.V;
        hMac2.update(bArr3, 0, bArr3.length);
        this.hMac.doFinal(this.V, 0);
        HMac hMac3 = this.hMac;
        byte[] bArr4 = this.V;
        hMac3.update(bArr4, 0, bArr4.length);
        this.hMac.update((byte) 1);
        this.hMac.update(bArrAsUnsignedByteArray, 0, bArrAsUnsignedByteArray.length);
        this.hMac.update(bArrAsUnsignedByteArray2, 0, bArrAsUnsignedByteArray2.length);
        initAdditionalInput1(this.hMac);
        this.hMac.doFinal(this.K, 0);
        this.hMac.init(new KeyParameter(this.K));
        HMac hMac4 = this.hMac;
        byte[] bArr5 = this.V;
        hMac4.update(bArr5, 0, bArr5.length);
        this.hMac.doFinal(this.V, 0);
    }

    @Override // org.bouncycastle.crypto.signers.DSAKCalculator
    public void init(BigInteger bigInteger, SecureRandom secureRandom) {
        throw new IllegalStateException("Operation not supported");
    }

    protected void initAdditionalInput0(HMac hMac) {
    }

    protected void initAdditionalInput1(HMac hMac) {
    }

    @Override // org.bouncycastle.crypto.signers.DSAKCalculator
    public boolean isDeterministic() {
        return true;
    }

    @Override // org.bouncycastle.crypto.signers.DSAKCalculator
    public BigInteger nextK() {
        int unsignedByteLength = BigIntegers.getUnsignedByteLength(this.f333n);
        byte[] bArr = new byte[unsignedByteLength];
        while (true) {
            int i = 0;
            while (i < unsignedByteLength) {
                HMac hMac = this.hMac;
                byte[] bArr2 = this.V;
                hMac.update(bArr2, 0, bArr2.length);
                this.hMac.doFinal(this.V, 0);
                int iMin = Math.min(unsignedByteLength - i, this.V.length);
                System.arraycopy(this.V, 0, bArr, i, iMin);
                i += iMin;
            }
            BigInteger bigIntegerBitsToInt = bitsToInt(bArr);
            if (bigIntegerBitsToInt.signum() > 0 && bigIntegerBitsToInt.compareTo(this.f333n) < 0) {
                return bigIntegerBitsToInt;
            }
            HMac hMac2 = this.hMac;
            byte[] bArr3 = this.V;
            hMac2.update(bArr3, 0, bArr3.length);
            this.hMac.update((byte) 0);
            this.hMac.doFinal(this.K, 0);
            this.hMac.init(new KeyParameter(this.K));
            HMac hMac3 = this.hMac;
            byte[] bArr4 = this.V;
            hMac3.update(bArr4, 0, bArr4.length);
            this.hMac.doFinal(this.V, 0);
        }
    }
}
