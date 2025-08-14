package org.spongycastle.crypto.engines;

/* loaded from: classes4.dex */
public class SEEDWrapEngine extends RFC3394WrapEngine {
    public SEEDWrapEngine() {
        super(new SEEDEngine());
    }
}
