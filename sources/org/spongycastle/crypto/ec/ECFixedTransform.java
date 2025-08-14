package org.spongycastle.crypto.ec;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.math.ec.ECMultiplier;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.math.ec.FixedPointCombMultiplier;

/* loaded from: classes4.dex */
public class ECFixedTransform implements ECPairFactorTransform {
    private BigInteger k;
    private ECPublicKeyParameters key;

    @Override // org.spongycastle.crypto.ec.ECPairFactorTransform
    public BigInteger getTransformValue() {
        return this.k;
    }

    public ECFixedTransform(BigInteger bigInteger) {
        this.k = bigInteger;
    }

    @Override // org.spongycastle.crypto.ec.ECPairTransform
    public void init(CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ECPublicKeyParameters)) {
            throw new IllegalArgumentException("ECPublicKeyParameters are required for fixed transform.");
        }
        this.key = (ECPublicKeyParameters) cipherParameters;
    }

    @Override // org.spongycastle.crypto.ec.ECPairTransform
    public ECPair transform(ECPair eCPair) {
        ECPublicKeyParameters eCPublicKeyParameters = this.key;
        if (eCPublicKeyParameters == null) {
            throw new IllegalStateException("ECFixedTransform not initialised");
        }
        ECDomainParameters parameters = eCPublicKeyParameters.getParameters();
        BigInteger n2 = parameters.getN();
        ECMultiplier eCMultiplierCreateBasePointMultiplier = createBasePointMultiplier();
        BigInteger bigIntegerMod = this.k.mod(n2);
        ECPoint[] eCPointArr = {eCMultiplierCreateBasePointMultiplier.multiply(parameters.getG(), bigIntegerMod).add(eCPair.getX()), this.key.getQ().multiply(bigIntegerMod).add(eCPair.getY())};
        parameters.getCurve().normalizeAll(eCPointArr);
        return new ECPair(eCPointArr[0], eCPointArr[1]);
    }

    protected ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }
}
