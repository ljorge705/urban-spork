package org.jmrtd.cbeff;

import org.jmrtd.cbeff.BiometricDataBlock;

/* loaded from: classes4.dex */
public class SimpleCBEFFInfo<B extends BiometricDataBlock> implements CBEFFInfo {
    private B bdb;

    public B getBiometricDataBlock() {
        return this.bdb;
    }

    public SimpleCBEFFInfo(B b) {
        this.bdb = b;
    }
}
