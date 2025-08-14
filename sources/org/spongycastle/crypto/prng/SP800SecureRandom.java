package org.spongycastle.crypto.prng;

import java.security.SecureRandom;
import org.spongycastle.crypto.prng.drbg.SP80090DRBG;

/* loaded from: classes7.dex */
public class SP800SecureRandom extends SecureRandom {
    private SP80090DRBG drbg;
    private final DRBGProvider drbgProvider;
    private final EntropySource entropySource;
    private final boolean predictionResistant;
    private final SecureRandom randomSource;

    SP800SecureRandom(SecureRandom secureRandom, EntropySource entropySource, DRBGProvider dRBGProvider, boolean z) {
        this.randomSource = secureRandom;
        this.entropySource = entropySource;
        this.drbgProvider = dRBGProvider;
        this.predictionResistant = z;
    }

    @Override // java.security.SecureRandom
    public void setSeed(byte[] bArr) {
        synchronized (this) {
            SecureRandom secureRandom = this.randomSource;
            if (secureRandom != null) {
                secureRandom.setSeed(bArr);
            }
        }
    }

    @Override // java.security.SecureRandom, java.util.Random
    public void setSeed(long j) {
        synchronized (this) {
            SecureRandom secureRandom = this.randomSource;
            if (secureRandom != null) {
                secureRandom.setSeed(j);
            }
        }
    }

    @Override // java.security.SecureRandom, java.util.Random
    public void nextBytes(byte[] bArr) {
        synchronized (this) {
            if (this.drbg == null) {
                this.drbg = this.drbgProvider.get(this.entropySource);
            }
            if (this.drbg.generate(bArr, null, this.predictionResistant) < 0) {
                this.drbg.reseed(null);
                this.drbg.generate(bArr, null, this.predictionResistant);
            }
        }
    }

    @Override // java.security.SecureRandom
    public byte[] generateSeed(int i) {
        return EntropyUtil.generateSeed(this.entropySource, i);
    }

    public void reseed(byte[] bArr) {
        synchronized (this) {
            if (this.drbg == null) {
                this.drbg = this.drbgProvider.get(this.entropySource);
            }
            this.drbg.reseed(bArr);
        }
    }
}
