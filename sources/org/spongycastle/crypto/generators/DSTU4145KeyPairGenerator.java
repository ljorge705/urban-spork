package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ECPublicKeyParameters;

/* loaded from: classes2.dex */
public class DSTU4145KeyPairGenerator extends ECKeyPairGenerator {
    @Override // org.spongycastle.crypto.generators.ECKeyPairGenerator, org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) super.generateKeyPair().getPublic();
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new ECPublicKeyParameters(eCPublicKeyParameters.getQ().negate(), eCPublicKeyParameters.getParameters()), r0.getPrivate());
    }
}
