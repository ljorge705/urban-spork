package org.bouncycastle.crypto.modes.gcm;

/* loaded from: classes4.dex */
public class BasicGCMMultiplier implements GCMMultiplier {
    private long[] H;

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        this.H = GCMUtil.asLongs(bArr);
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        GCMUtil.multiply(bArr, this.H);
    }
}
