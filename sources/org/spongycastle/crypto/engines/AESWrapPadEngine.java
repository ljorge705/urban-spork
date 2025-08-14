package org.spongycastle.crypto.engines;

/* loaded from: classes4.dex */
public class AESWrapPadEngine extends RFC5649WrapEngine {
    public AESWrapPadEngine() {
        super(new AESEngine());
    }
}
