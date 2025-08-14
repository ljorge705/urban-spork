package org.bouncycastle.pqc.crypto.picnic;

import java.lang.reflect.Array;

/* loaded from: classes4.dex */
class Msg {
    byte[][] msgs;
    int pos = 0;
    int unopened = -1;

    public Msg(PicnicEngine picnicEngine) {
        this.msgs = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, picnicEngine.numMPCParties, picnicEngine.andSizeBytes);
    }
}
