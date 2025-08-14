package org.spongycastle.crypto.ec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.math.ec.ECMultiplier;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.math.ec.FixedPointCombMultiplier;

/* loaded from: classes4.dex */
public class ECElGamalEncryptor implements ECEncryptor {
    private ECPublicKeyParameters key;
    private SecureRandom random;

    @Override // org.spongycastle.crypto.ec.ECEncryptor
    public void init(CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            if (!(parametersWithRandom.getParameters() instanceof ECPublicKeyParameters)) {
                throw new IllegalArgumentException("ECPublicKeyParameters are required for encryption.");
            }
            this.key = (ECPublicKeyParameters) parametersWithRandom.getParameters();
            this.random = parametersWithRandom.getRandom();
            return;
        }
        if (!(cipherParameters instanceof ECPublicKeyParameters)) {
            throw new IllegalArgumentException("ECPublicKeyParameters are required for encryption.");
        }
        this.key = (ECPublicKeyParameters) cipherParameters;
        this.random = new SecureRandom();
    }

    @Override // org.spongycastle.crypto.ec.ECEncryptor
    public ECPair encrypt(ECPoint eCPoint) {
        ECPublicKeyParameters eCPublicKeyParameters = this.key;
        if (eCPublicKeyParameters == null) {
            throw new IllegalStateException("ECElGamalEncryptor not initialised");
        }
        ECDomainParameters parameters = eCPublicKeyParameters.getParameters();
        BigInteger bigIntegerGenerateK = ECUtil.generateK(parameters.getN(), this.random);
        ECPoint[] eCPointArr = {createBasePointMultiplier().multiply(parameters.getG(), bigIntegerGenerateK), this.key.getQ().multiply(bigIntegerGenerateK).add(eCPoint)};
        parameters.getCurve().normalizeAll(eCPointArr);
        return new ECPair(eCPointArr[0], eCPointArr[1]);
    }

    protected ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }
}
