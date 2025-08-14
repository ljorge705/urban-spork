package org.bouncycastle.pqc.crypto.rainbow;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* loaded from: classes4.dex */
public class RainbowKeyGenerationParameters extends KeyGenerationParameters {
    private RainbowParameters params;

    public RainbowKeyGenerationParameters(SecureRandom secureRandom, RainbowParameters rainbowParameters) {
        super(secureRandom, 256);
        this.params = rainbowParameters;
    }

    public RainbowParameters getParameters() {
        return this.params;
    }
}
