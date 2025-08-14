package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.constraints.ConstraintUtils;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.math.ec.WNafUtil;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes4.dex */
public class ECKeyPairGenerator implements AsymmetricCipherKeyPairGenerator, ECConstants {
    private final String name;
    ECDomainParameters params;
    SecureRandom random;

    public ECKeyPairGenerator() {
        this("ECKeyGen");
    }

    protected ECKeyPairGenerator(String str) {
        this.name = str;
    }

    protected ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        BigInteger n2 = this.params.getN();
        int iBitLength = n2.bitLength();
        int i = iBitLength >>> 2;
        while (true) {
            BigInteger bigIntegerCreateRandomBigInteger = BigIntegers.createRandomBigInteger(iBitLength, this.random);
            if (!isOutOfRangeD(bigIntegerCreateRandomBigInteger, n2) && WNafUtil.getNafWeight(bigIntegerCreateRandomBigInteger) >= i) {
                return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new ECPublicKeyParameters(createBasePointMultiplier().multiply(this.params.getG(), bigIntegerCreateRandomBigInteger), this.params), (AsymmetricKeyParameter) new ECPrivateKeyParameters(bigIntegerCreateRandomBigInteger, this.params));
            }
        }
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        ECKeyGenerationParameters eCKeyGenerationParameters = (ECKeyGenerationParameters) keyGenerationParameters;
        this.random = eCKeyGenerationParameters.getRandom();
        this.params = eCKeyGenerationParameters.getDomainParameters();
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(this.name, ConstraintUtils.bitsOfSecurityFor(this.params.getCurve()), eCKeyGenerationParameters.getDomainParameters(), CryptoServicePurpose.KEYGEN));
    }

    protected boolean isOutOfRangeD(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger.compareTo(ONE) < 0 || bigInteger.compareTo(bigInteger2) >= 0;
    }
}
