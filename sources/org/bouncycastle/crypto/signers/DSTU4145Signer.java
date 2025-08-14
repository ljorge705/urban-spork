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
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes4.dex */
public class DSTU4145Signer implements DSAExt {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private ECKeyParameters key;
    private SecureRandom random;

    private static BigInteger fieldElement2Integer(BigInteger bigInteger, ECFieldElement eCFieldElement) {
        return truncate(eCFieldElement.toBigInteger(), bigInteger.bitLength() - 1);
    }

    private static BigInteger generateRandomInteger(BigInteger bigInteger, SecureRandom secureRandom) {
        return BigIntegers.createRandomBigInteger(bigInteger.bitLength() - 1, secureRandom);
    }

    private static ECFieldElement hash2FieldElement(ECCurve eCCurve, byte[] bArr) {
        return eCCurve.fromBigInteger(truncate(new BigInteger(1, Arrays.reverse(bArr)), eCCurve.getFieldSize()));
    }

    private static BigInteger truncate(BigInteger bigInteger, int i) {
        return bigInteger.bitLength() > i ? bigInteger.mod(ONE.shiftLeft(i)) : bigInteger;
    }

    protected ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    @Override // org.bouncycastle.crypto.DSA
    public BigInteger[] generateSignature(byte[] bArr) {
        ECDomainParameters parameters = this.key.getParameters();
        ECCurve curve = parameters.getCurve();
        ECFieldElement eCFieldElementHash2FieldElement = hash2FieldElement(curve, bArr);
        if (eCFieldElementHash2FieldElement.isZero()) {
            eCFieldElementHash2FieldElement = curve.fromBigInteger(ONE);
        }
        BigInteger n2 = parameters.getN();
        BigInteger d = ((ECPrivateKeyParameters) this.key).getD();
        ECMultiplier eCMultiplierCreateBasePointMultiplier = createBasePointMultiplier();
        while (true) {
            BigInteger bigIntegerGenerateRandomInteger = generateRandomInteger(n2, this.random);
            ECFieldElement affineXCoord = eCMultiplierCreateBasePointMultiplier.multiply(parameters.getG(), bigIntegerGenerateRandomInteger).normalize().getAffineXCoord();
            if (!affineXCoord.isZero()) {
                BigInteger bigIntegerFieldElement2Integer = fieldElement2Integer(n2, eCFieldElementHash2FieldElement.multiply(affineXCoord));
                if (bigIntegerFieldElement2Integer.signum() != 0) {
                    BigInteger bigIntegerMod = bigIntegerFieldElement2Integer.multiply(d).add(bigIntegerGenerateRandomInteger).mod(n2);
                    if (bigIntegerMod.signum() != 0) {
                        return new BigInteger[]{bigIntegerFieldElement2Integer, bigIntegerMod};
                    }
                } else {
                    continue;
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
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties("DSTU4145", this.key, z));
    }

    @Override // org.bouncycastle.crypto.DSA
    public boolean verifySignature(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        if (bigInteger.signum() <= 0 || bigInteger2.signum() <= 0) {
            return false;
        }
        ECDomainParameters parameters = this.key.getParameters();
        BigInteger n2 = parameters.getN();
        if (bigInteger.compareTo(n2) >= 0 || bigInteger2.compareTo(n2) >= 0) {
            return false;
        }
        ECCurve curve = parameters.getCurve();
        ECFieldElement eCFieldElementHash2FieldElement = hash2FieldElement(curve, bArr);
        if (eCFieldElementHash2FieldElement.isZero()) {
            eCFieldElementHash2FieldElement = curve.fromBigInteger(ONE);
        }
        ECPoint eCPointNormalize = ECAlgorithms.sumOfTwoMultiplies(parameters.getG(), bigInteger2, ((ECPublicKeyParameters) this.key).getQ(), bigInteger).normalize();
        return !eCPointNormalize.isInfinity() && fieldElement2Integer(n2, eCFieldElementHash2FieldElement.multiply(eCPointNormalize.getAffineXCoord())).compareTo(bigInteger) == 0;
    }
}
