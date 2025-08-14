package com.uxcam.internals;

import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class dg extends an {
    public final ByteBuffer b;

    public dg(cy cyVar, ByteBuffer byteBuffer) {
        super(cyVar);
        this.b = byteBuffer;
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        dx.a(byteBuffer, this.b);
    }
}
