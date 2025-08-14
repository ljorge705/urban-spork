package org.bouncycastle.crypto.prng;

/* loaded from: classes4.dex */
public interface EntropySource {
    int entropySize();

    byte[] getEntropy();

    boolean isPredictionResistant();
}
