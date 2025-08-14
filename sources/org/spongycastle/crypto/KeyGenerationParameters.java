package org.spongycastle.crypto;

import java.security.SecureRandom;

/* loaded from: classes.dex */
public class KeyGenerationParameters {
    private SecureRandom random;
    private int strength;

    public SecureRandom getRandom() {
        return this.random;
    }

    public int getStrength() {
        return this.strength;
    }

    public KeyGenerationParameters(SecureRandom secureRandom, int i) {
        this.random = secureRandom;
        this.strength = i;
    }
}
