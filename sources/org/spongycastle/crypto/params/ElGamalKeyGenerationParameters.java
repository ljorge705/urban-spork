package org.spongycastle.crypto.params;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

/* loaded from: classes7.dex */
public class ElGamalKeyGenerationParameters extends KeyGenerationParameters {
    private ElGamalParameters params;

    public ElGamalParameters getParameters() {
        return this.params;
    }

    public ElGamalKeyGenerationParameters(SecureRandom secureRandom, ElGamalParameters elGamalParameters) {
        super(secureRandom, getStrength(elGamalParameters));
        this.params = elGamalParameters;
    }

    static int getStrength(ElGamalParameters elGamalParameters) {
        return elGamalParameters.getL() != 0 ? elGamalParameters.getL() : elGamalParameters.getP().bitLength();
    }
}
