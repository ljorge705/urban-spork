package com.uxcam.internals;

import androidx.core.view.ViewCompat;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class cq extends cm {
    public cq(cy cyVar) {
        super(cyVar);
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b & ViewCompat.MEASURED_SIZE_MASK);
        byteBuffer.putShort((short) 0);
        byteBuffer.putShort((short) 0);
        byteBuffer.putShort((short) 0);
        byteBuffer.putShort((short) 0);
        byteBuffer.putShort((short) 0);
        byteBuffer.putShort((short) 0);
    }

    public cq() {
        this(new cy("gmin"));
    }
}
