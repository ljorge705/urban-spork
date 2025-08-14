package org.spongycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

/* loaded from: classes7.dex */
public class McElieceKeyGenerationParameters extends KeyGenerationParameters {
    private McElieceParameters params;

    public McElieceParameters getParameters() {
        return this.params;
    }

    public McElieceKeyGenerationParameters(SecureRandom secureRandom, McElieceParameters mcElieceParameters) {
        super(secureRandom, 256);
        this.params = mcElieceParameters;
    }
}
