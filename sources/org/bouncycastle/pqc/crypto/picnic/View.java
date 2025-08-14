package org.bouncycastle.pqc.crypto.picnic;

/* loaded from: classes4.dex */
class View {
    final byte[] communicatedBits;
    final int[] inputShare;
    final int[] outputShare;

    public View(PicnicEngine picnicEngine) {
        this.inputShare = new int[picnicEngine.stateSizeWords];
        this.communicatedBits = new byte[picnicEngine.andSizeBytes];
        this.outputShare = new int[picnicEngine.stateSizeWords];
    }
}
