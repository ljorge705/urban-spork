package org.bouncycastle.pqc.crypto.xwing;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* loaded from: classes4.dex */
public class XWingKeyGenerationParameters extends KeyGenerationParameters {
    public XWingKeyGenerationParameters(SecureRandom secureRandom) {
        super(secureRandom, 128);
    }
}
