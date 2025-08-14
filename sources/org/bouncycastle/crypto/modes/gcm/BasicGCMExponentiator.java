package org.bouncycastle.crypto.modes.gcm;

/* loaded from: classes4.dex */
public class BasicGCMExponentiator implements GCMExponentiator {
    private long[] x;

    @Override // org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void exponentiateX(long j, byte[] bArr) {
        long[] jArrOneAsLongs = GCMUtil.oneAsLongs();
        if (j > 0) {
            long[] jArr = new long[2];
            GCMUtil.copy(this.x, jArr);
            do {
                if ((1 & j) != 0) {
                    GCMUtil.multiply(jArrOneAsLongs, jArr);
                }
                GCMUtil.square(jArr, jArr);
                j >>>= 1;
            } while (j > 0);
        }
        GCMUtil.asBytes(jArrOneAsLongs, bArr);
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMExponentiator
    public void init(byte[] bArr) {
        this.x = GCMUtil.asLongs(bArr);
    }
}
