package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.params.DSAKeyParameters;
import org.spongycastle.crypto.params.DSAParameters;
import org.spongycastle.crypto.params.DSAPrivateKeyParameters;
import org.spongycastle.crypto.params.DSAPublicKeyParameters;

/* loaded from: classes7.dex */
public class DSASigner implements DSA {
    private final DSAKCalculator kCalculator;
    private DSAKeyParameters key;
    private SecureRandom random;

    public DSASigner() {
        this.kCalculator = new RandomDSAKCalculator();
    }

    public DSASigner(DSAKCalculator dSAKCalculator) {
        this.kCalculator = dSAKCalculator;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002b  */
    @Override // org.spongycastle.crypto.DSA
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void init(boolean r2, org.spongycastle.crypto.CipherParameters r3) {
        /*
            r1 = this;
            if (r2 == 0) goto L1a
            boolean r0 = r3 instanceof org.spongycastle.crypto.params.ParametersWithRandom
            if (r0 == 0) goto L15
            org.spongycastle.crypto.params.ParametersWithRandom r3 = (org.spongycastle.crypto.params.ParametersWithRandom) r3
            org.spongycastle.crypto.CipherParameters r0 = r3.getParameters()
            org.spongycastle.crypto.params.DSAPrivateKeyParameters r0 = (org.spongycastle.crypto.params.DSAPrivateKeyParameters) r0
            r1.key = r0
            java.security.SecureRandom r3 = r3.getRandom()
            goto L1f
        L15:
            org.spongycastle.crypto.params.DSAPrivateKeyParameters r3 = (org.spongycastle.crypto.params.DSAPrivateKeyParameters) r3
            r1.key = r3
            goto L1e
        L1a:
            org.spongycastle.crypto.params.DSAPublicKeyParameters r3 = (org.spongycastle.crypto.params.DSAPublicKeyParameters) r3
            r1.key = r3
        L1e:
            r3 = 0
        L1f:
            if (r2 == 0) goto L2b
            org.spongycastle.crypto.signers.DSAKCalculator r2 = r1.kCalculator
            boolean r2 = r2.isDeterministic()
            if (r2 != 0) goto L2b
            r2 = 1
            goto L2c
        L2b:
            r2 = 0
        L2c:
            java.security.SecureRandom r2 = r1.initSecureRandom(r2, r3)
            r1.random = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.signers.DSASigner.init(boolean, org.spongycastle.crypto.CipherParameters):void");
    }

    @Override // org.spongycastle.crypto.DSA
    public BigInteger[] generateSignature(byte[] bArr) {
        DSAParameters parameters = this.key.getParameters();
        BigInteger q = parameters.getQ();
        BigInteger bigIntegerCalculateE = calculateE(q, bArr);
        BigInteger x = ((DSAPrivateKeyParameters) this.key).getX();
        if (this.kCalculator.isDeterministic()) {
            this.kCalculator.init(q, x, bArr);
        } else {
            this.kCalculator.init(q, this.random);
        }
        BigInteger bigIntegerNextK = this.kCalculator.nextK();
        BigInteger bigIntegerMod = parameters.getG().modPow(bigIntegerNextK.add(getRandomizer(q, this.random)), parameters.getP()).mod(q);
        return new BigInteger[]{bigIntegerMod, bigIntegerNextK.modInverse(q).multiply(bigIntegerCalculateE.add(x.multiply(bigIntegerMod))).mod(q)};
    }

    @Override // org.spongycastle.crypto.DSA
    public boolean verifySignature(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        DSAParameters parameters = this.key.getParameters();
        BigInteger q = parameters.getQ();
        BigInteger bigIntegerCalculateE = calculateE(q, bArr);
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0L);
        if (bigIntegerValueOf.compareTo(bigInteger) >= 0 || q.compareTo(bigInteger) <= 0 || bigIntegerValueOf.compareTo(bigInteger2) >= 0 || q.compareTo(bigInteger2) <= 0) {
            return false;
        }
        BigInteger bigIntegerModInverse = bigInteger2.modInverse(q);
        BigInteger bigIntegerMod = bigIntegerCalculateE.multiply(bigIntegerModInverse).mod(q);
        BigInteger bigIntegerMod2 = bigInteger.multiply(bigIntegerModInverse).mod(q);
        BigInteger p = parameters.getP();
        return parameters.getG().modPow(bigIntegerMod, p).multiply(((DSAPublicKeyParameters) this.key).getY().modPow(bigIntegerMod2, p)).mod(p).mod(q).equals(bigInteger);
    }

    private BigInteger calculateE(BigInteger bigInteger, byte[] bArr) {
        if (bigInteger.bitLength() >= bArr.length * 8) {
            return new BigInteger(1, bArr);
        }
        int iBitLength = bigInteger.bitLength() / 8;
        byte[] bArr2 = new byte[iBitLength];
        System.arraycopy(bArr, 0, bArr2, 0, iBitLength);
        return new BigInteger(1, bArr2);
    }

    protected SecureRandom initSecureRandom(boolean z, SecureRandom secureRandom) {
        if (z) {
            return secureRandom != null ? secureRandom : new SecureRandom();
        }
        return null;
    }

    private BigInteger getRandomizer(BigInteger bigInteger, SecureRandom secureRandom) {
        if (secureRandom == null) {
            secureRandom = new SecureRandom();
        }
        return new BigInteger(7, secureRandom).add(BigInteger.valueOf(128L)).multiply(bigInteger);
    }
}
