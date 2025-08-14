package com.uxcam.internals;

import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public class et extends dz {
    public final short c;

    public et(cy cyVar) {
        super(cyVar);
    }

    @Override // com.uxcam.internals.dz, com.uxcam.internals.an
    public void a(ByteBuffer byteBuffer) {
        byteBuffer.put(new byte[]{0, 0, 0, 0, 0, 0});
        byteBuffer.putShort(this.c);
    }

    public final void c(ByteBuffer byteBuffer) {
        super.a(byteBuffer);
    }

    public et(cy cyVar, int i) {
        super(cyVar);
        this.c = (short) 1;
    }
}
