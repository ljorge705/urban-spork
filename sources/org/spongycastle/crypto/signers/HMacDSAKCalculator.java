package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

/* loaded from: classes7.dex */
public class HMacDSAKCalculator implements DSAKCalculator {
    private static final BigInteger ZERO = BigInteger.valueOf(0);
    private final byte[] K;
    private final byte[] V;
    private final HMac hMac;

    /* renamed from: n, reason: collision with root package name */
    private BigInteger f395n;

    @Override // org.spongycastle.crypto.signers.DSAKCalculator
    public boolean isDeterministic() {
        return true;
    }

    public HMacDSAKCalculator(Digest digest) {
        HMac hMac = new HMac(digest);
        this.hMac = hMac;
        this.V = new byte[hMac.getMacSize()];
        this.K = new byte[hMac.getMacSize()];
    }

    @Override // org.spongycastle.crypto.signers.DSAKCalculator
    public void init(BigInteger bigInteger, SecureRandom secureRandom) {
        throw new IllegalStateException("Operation not supported");
    }

    @Override // org.spongycastle.crypto.signers.DSAKCalculator
    public void init(BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.f395n = bigInteger;
        Arrays.fill(this.V, (byte) 1);
        Arrays.fill(this.K, (byte) 0);
        int iBitLength = (bigInteger.bitLength() + 7) / 8;
        byte[] bArr2 = new byte[iBitLength];
        byte[] bArrAsUnsignedByteArray = BigIntegers.asUnsignedByteArray(bigInteger2);
        System.arraycopy(bArrAsUnsignedByteArray, 0, bArr2, iBitLength - bArrAsUnsignedByteArray.length, bArrAsUnsignedByteArray.length);
        int iBitLength2 = (bigInteger.bitLength() + 7) / 8;
        byte[] bArr3 = new byte[iBitLength2];
        BigInteger bigIntegerBitsToInt = bitsToInt(bArr);
        if (bigIntegerBitsToInt.compareTo(bigInteger) >= 0) {
            bigIntegerBitsToInt = bigIntegerBitsToInt.subtract(bigInteger);
        }
        byte[] bArrAsUnsignedByteArray2 = BigIntegers.asUnsignedByteArray(bigIntegerBitsToInt);
        System.arraycopy(bArrAsUnsignedByteArray2, 0, bArr3, iBitLength2 - bArrAsUnsignedByteArray2.length, bArrAsUnsignedByteArray2.length);
        this.hMac.init(new KeyParameter(this.K));
        HMac hMac = this.hMac;
        byte[] bArr4 = this.V;
        hMac.update(bArr4, 0, bArr4.length);
        this.hMac.update((byte) 0);
        this.hMac.update(bArr2, 0, iBitLength);
        this.hMac.update(bArr3, 0, iBitLength2);
        this.hMac.doFinal(this.K, 0);
        this.hMac.init(new KeyParameter(this.K));
        HMac hMac2 = this.hMac;
        byte[] bArr5 = this.V;
        hMac2.update(bArr5, 0, bArr5.length);
        this.hMac.doFinal(this.V, 0);
        HMac hMac3 = this.hMac;
        byte[] bArr6 = this.V;
        hMac3.update(bArr6, 0, bArr6.length);
        this.hMac.update((byte) 1);
        this.hMac.update(bArr2, 0, iBitLength);
        this.hMac.update(bArr3, 0, iBitLength2);
        this.hMac.doFinal(this.K, 0);
        this.hMac.init(new KeyParameter(this.K));
        HMac hMac4 = this.hMac;
        byte[] bArr7 = this.V;
        hMac4.update(bArr7, 0, bArr7.length);
        this.hMac.doFinal(this.V, 0);
    }

    @Override // org.spongycastle.crypto.signers.DSAKCalculator
    public BigInteger nextK() {
        int iBitLength = (this.f395n.bitLength() + 7) / 8;
        byte[] bArr = new byte[iBitLength];
        while (true) {
            int i = 0;
            while (i < iBitLength) {
                HMac hMac = this.hMac;
                byte[] bArr2 = this.V;
                hMac.update(bArr2, 0, bArr2.length);
                this.hMac.doFinal(this.V, 0);
                int iMin = Math.min(iBitLength - i, this.V.length);
                System.arraycopy(this.V, 0, bArr, i, iMin);
                i += iMin;
            }
            BigInteger bigIntegerBitsToInt = bitsToInt(bArr);
            if (bigIntegerBitsToInt.compareTo(ZERO) > 0 && bigIntegerBitsToInt.compareTo(this.f395n) < 0) {
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

    private BigInteger bitsToInt(byte[] bArr) {
        BigInteger bigInteger = new BigInteger(1, bArr);
        return bArr.length * 8 > this.f395n.bitLength() ? bigInteger.shiftRight((bArr.length * 8) - this.f395n.bitLength()) : bigInteger;
    }
}
