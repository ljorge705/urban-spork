package org.spongycastle.crypto.prng;

/* loaded from: classes7.dex */
public interface EntropySource {
    int entropySize();

    byte[] getEntropy();

    boolean isPredictionResistant();
}
