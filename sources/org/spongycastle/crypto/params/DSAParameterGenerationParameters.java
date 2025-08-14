package org.spongycastle.crypto.params;

import java.security.SecureRandom;

/* loaded from: classes7.dex */
public class DSAParameterGenerationParameters {
    public static final int DIGITAL_SIGNATURE_USAGE = 1;
    public static final int KEY_ESTABLISHMENT_USAGE = 2;
    private final int certainty;
    private final int l;

    /* renamed from: n, reason: collision with root package name */
    private final int f390n;
    private final SecureRandom random;
    private final int usageIndex;

    public int getCertainty() {
        return this.certainty;
    }

    public int getL() {
        return this.l;
    }

    public int getN() {
        return this.f390n;
    }

    public SecureRandom getRandom() {
        return this.random;
    }

    public int getUsageIndex() {
        return this.usageIndex;
    }

    public DSAParameterGenerationParameters(int i, int i2, int i3, SecureRandom secureRandom) {
        this(i, i2, i3, secureRandom, -1);
    }

    public DSAParameterGenerationParameters(int i, int i2, int i3, SecureRandom secureRandom, int i4) {
        this.l = i;
        this.f390n = i2;
        this.certainty = i3;
        this.usageIndex = i4;
        this.random = secureRandom;
    }
}
