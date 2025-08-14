package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.math.ec.ECAlgorithms;
import org.spongycastle.math.ec.ECConstants;
import org.spongycastle.math.ec.ECMultiplier;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.math.ec.FixedPointCombMultiplier;

/* loaded from: classes7.dex */
public class ECGOST3410_2012Signer implements DSA {
    ECKeyParameters key;
    SecureRandom random;

    @Override // org.spongycastle.crypto.DSA
    public void init(boolean z, CipherParameters cipherParameters) {
        if (z) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.random = parametersWithRandom.getRandom();
                this.key = (ECPrivateKeyParameters) parametersWithRandom.getParameters();
                return;
            } else {
                this.random = new SecureRandom();
                this.key = (ECPrivateKeyParameters) cipherParameters;
                return;
            }
        }
        this.key = (ECPublicKeyParameters) cipherParameters;
    }

    @Override // org.spongycastle.crypto.DSA
    public BigInteger[] generateSignature(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i != length; i++) {
            bArr2[i] = bArr[(length - 1) - i];
        }
        BigInteger bigInteger = new BigInteger(1, bArr2);
        ECDomainParameters parameters = this.key.getParameters();
        BigInteger n2 = parameters.getN();
        BigInteger d = ((ECPrivateKeyParameters) this.key).getD();
        ECMultiplier eCMultiplierCreateBasePointMultiplier = createBasePointMultiplier();
        while (true) {
            BigInteger bigInteger2 = new BigInteger(n2.bitLength(), this.random);
            if (!bigInteger2.equals(ECConstants.ZERO)) {
                BigInteger bigIntegerMod = eCMultiplierCreateBasePointMultiplier.multiply(parameters.getG(), bigInteger2).normalize().getAffineXCoord().toBigInteger().mod(n2);
                if (bigIntegerMod.equals(ECConstants.ZERO)) {
                    continue;
                } else {
                    BigInteger bigIntegerMod2 = bigInteger2.multiply(bigInteger).add(d.multiply(bigIntegerMod)).mod(n2);
                    if (!bigIntegerMod2.equals(ECConstants.ZERO)) {
                        return new BigInteger[]{bigIntegerMod, bigIntegerMod2};
                    }
                }
            }
        }
    }

    @Override // org.spongycastle.crypto.DSA
    public boolean verifySignature(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i != length; i++) {
            bArr2[i] = bArr[(length - 1) - i];
        }
        BigInteger bigInteger3 = new BigInteger(1, bArr2);
        BigInteger n2 = this.key.getParameters().getN();
        if (bigInteger.compareTo(ECConstants.ONE) < 0 || bigInteger.compareTo(n2) >= 0 || bigInteger2.compareTo(ECConstants.ONE) < 0 || bigInteger2.compareTo(n2) >= 0) {
            return false;
        }
        BigInteger bigIntegerModInverse = bigInteger3.modInverse(n2);
        ECPoint eCPointNormalize = ECAlgorithms.sumOfTwoMultiplies(this.key.getParameters().getG(), bigInteger2.multiply(bigIntegerModInverse).mod(n2), ((ECPublicKeyParameters) this.key).getQ(), n2.subtract(bigInteger).multiply(bigIntegerModInverse).mod(n2)).normalize();
        if (eCPointNormalize.isInfinity()) {
            return false;
        }
        return eCPointNormalize.getAffineXCoord().toBigInteger().mod(n2).equals(bigInteger);
    }

    protected ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }
}
