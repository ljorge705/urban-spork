package org.spongycastle.pqc.crypto.sphincs;

import java.security.SecureRandom;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.KeyGenerationParameters;

/* loaded from: classes7.dex */
public class SPHINCS256KeyGenerationParameters extends KeyGenerationParameters {
    private final Digest treeDigest;

    public Digest getTreeDigest() {
        return this.treeDigest;
    }

    public SPHINCS256KeyGenerationParameters(SecureRandom secureRandom, Digest digest) {
        super(secureRandom, 8448);
        this.treeDigest = digest;
    }
}
