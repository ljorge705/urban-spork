package org.spongycastle.crypto.params;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

/* loaded from: classes7.dex */
public class ECKeyGenerationParameters extends KeyGenerationParameters {
    private ECDomainParameters domainParams;

    public ECDomainParameters getDomainParameters() {
        return this.domainParams;
    }

    public ECKeyGenerationParameters(ECDomainParameters eCDomainParameters, SecureRandom secureRandom) {
        super(secureRandom, eCDomainParameters.getN().bitLength());
        this.domainParams = eCDomainParameters;
    }
}
