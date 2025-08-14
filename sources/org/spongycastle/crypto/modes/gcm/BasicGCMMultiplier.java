package org.spongycastle.crypto.modes.gcm;

/* loaded from: classes7.dex */
public class BasicGCMMultiplier implements GCMMultiplier {
    private int[] H;

    @Override // org.spongycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        this.H = GCMUtil.asInts(bArr);
    }

    @Override // org.spongycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        int[] iArrAsInts = GCMUtil.asInts(bArr);
        GCMUtil.multiply(iArrAsInts, this.H);
        GCMUtil.asBytes(iArrAsInts, bArr);
    }
}
