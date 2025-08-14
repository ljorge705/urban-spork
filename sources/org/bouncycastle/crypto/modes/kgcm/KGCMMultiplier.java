package org.bouncycastle.crypto.modes.kgcm;

/* loaded from: classes4.dex */
public interface KGCMMultiplier {
    void init(long[] jArr);

    void multiplyH(long[] jArr);
}
