package org.spongycastle.pqc.crypto.rainbow;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

/* loaded from: classes7.dex */
public class RainbowKeyGenerationParameters extends KeyGenerationParameters {
    private RainbowParameters params;

    public RainbowParameters getParameters() {
        return this.params;
    }

    public RainbowKeyGenerationParameters(SecureRandom secureRandom, RainbowParameters rainbowParameters) {
        super(secureRandom, rainbowParameters.getVi()[rainbowParameters.getVi().length - 1] - rainbowParameters.getVi()[0]);
        this.params = rainbowParameters;
    }
}
