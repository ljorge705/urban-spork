package org.spongycastle.pqc.crypto.gmss;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

/* loaded from: classes7.dex */
public class GMSSKeyGenerationParameters extends KeyGenerationParameters {
    private GMSSParameters params;

    public GMSSParameters getParameters() {
        return this.params;
    }

    public GMSSKeyGenerationParameters(SecureRandom secureRandom, GMSSParameters gMSSParameters) {
        super(secureRandom, 1);
        this.params = gMSSParameters;
    }
}
