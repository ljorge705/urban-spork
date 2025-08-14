package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DSAExt;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes4.dex */
public class ECGOST3410Signer implements DSAExt {
    ECKeyParameters key;
    SecureRandom random;

    protected ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    @Override // org.bouncycastle.crypto.DSA
    public BigInteger[] generateSignature(byte[] bArr) {
        BigInteger bigInteger = new BigInteger(1, Arrays.reverse(bArr));
        ECDomainParameters parameters = this.key.getParameters();
        BigInteger n2 = parameters.getN();
        BigInteger d = ((ECPrivateKeyParameters) this.key).getD();
        ECMultiplier eCMultiplierCreateBasePointMultiplier = createBasePointMultiplier();
        while (true) {
            BigInteger bigIntegerCreateRandomBigInteger = BigIntegers.createRandomBigInteger(n2.bitLength(), this.random);
            if (!bigIntegerCreateRandomBigInteger.equals(ECConstants.ZERO)) {
                BigInteger bigIntegerMod = eCMultiplierCreateBasePointMultiplier.multiply(parameters.getG(), bigIntegerCreateRandomBigInteger).normalize().getAffineXCoord().toBigInteger().mod(n2);
                if (bigIntegerMod.equals(ECConstants.ZERO)) {
                    continue;
                } else {
                    BigInteger bigIntegerMod2 = bigIntegerCreateRandomBigInteger.multiply(bigInteger).add(d.multiply(bigIntegerMod)).mod(n2);
                    if (!bigIntegerMod2.equals(ECConstants.ZERO)) {
                        return new BigInteger[]{bigIntegerMod, bigIntegerMod2};
                    }
                }
            }
        }
    }

    @Override // org.bouncycastle.crypto.DSAExt
    public BigInteger getOrder() {
        return this.key.getParameters().getN();
    }

    @Override // org.bouncycastle.crypto.DSA
    public void init(boolean z, CipherParameters cipherParameters) {
        ECKeyParameters eCKeyParameters;
        if (z) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.random = parametersWithRandom.getRandom();
                cipherParameters = parametersWithRandom.getParameters();
            } else {
                this.random = CryptoServicesRegistrar.getSecureRandom();
            }
            eCKeyParameters = (ECPrivateKeyParameters) cipherParameters;
        } else {
            eCKeyParameters = (ECPublicKeyParameters) cipherParameters;
        }
        this.key = eCKeyParameters;
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties("ECGOST3410", this.key, z));
    }

    @Override // org.bouncycastle.crypto.DSA
    public boolean verifySignature(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigInteger3 = new BigInteger(1, Arrays.reverse(bArr));
        BigInteger n2 = this.key.getParameters().getN();
        if (bigInteger.compareTo(ECConstants.ONE) < 0 || bigInteger.compareTo(n2) >= 0 || bigInteger2.compareTo(ECConstants.ONE) < 0 || bigInteger2.compareTo(n2) >= 0) {
            return false;
        }
        BigInteger bigIntegerModOddInverseVar = BigIntegers.modOddInverseVar(n2, bigInteger3);
        ECPoint eCPointNormalize = ECAlgorithms.sumOfTwoMultiplies(this.key.getParameters().getG(), bigInteger2.multiply(bigIntegerModOddInverseVar).mod(n2), ((ECPublicKeyParameters) this.key).getQ(), n2.subtract(bigInteger).multiply(bigIntegerModOddInverseVar).mod(n2)).normalize();
        if (eCPointNormalize.isInfinity()) {
            return false;
        }
        return eCPointNormalize.getAffineXCoord().toBigInteger().mod(n2).equals(bigInteger);
    }
}
