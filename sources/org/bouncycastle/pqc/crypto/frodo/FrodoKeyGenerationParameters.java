package org.bouncycastle.pqc.crypto.frodo;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* loaded from: classes4.dex */
public class FrodoKeyGenerationParameters extends KeyGenerationParameters {
    private FrodoParameters params;

    public FrodoKeyGenerationParameters(SecureRandom secureRandom, FrodoParameters frodoParameters) {
        super(secureRandom, 256);
        this.params = frodoParameters;
    }

    public FrodoParameters getParameters() {
        return this.params;
    }
}
