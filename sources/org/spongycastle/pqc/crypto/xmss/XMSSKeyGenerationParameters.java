package org.spongycastle.pqc.crypto.xmss;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

/* loaded from: classes7.dex */
public final class XMSSKeyGenerationParameters extends KeyGenerationParameters {
    private final XMSSParameters xmssParameters;

    public XMSSParameters getParameters() {
        return this.xmssParameters;
    }

    public XMSSKeyGenerationParameters(XMSSParameters xMSSParameters, SecureRandom secureRandom) {
        super(secureRandom, -1);
        this.xmssParameters = xMSSParameters;
    }
}
