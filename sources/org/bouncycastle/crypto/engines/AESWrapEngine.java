package org.bouncycastle.crypto.engines;

/* loaded from: classes4.dex */
public class AESWrapEngine extends RFC3394WrapEngine {
    public AESWrapEngine() {
        super(AESEngine.newInstance());
    }

    public AESWrapEngine(boolean z) {
        super(AESEngine.newInstance(), z);
    }
}
