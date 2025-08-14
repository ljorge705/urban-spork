package com.uxcam.internals;

import androidx.core.view.ViewCompat;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class ii extends cm {
    public final int c;
    public final int d;
    public final int e;
    public final int f;

    public ii() {
        super(new cy("vmhd"));
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b & ViewCompat.MEASURED_SIZE_MASK);
        byteBuffer.putShort((short) this.c);
        byteBuffer.putShort((short) this.d);
        byteBuffer.putShort((short) this.e);
        byteBuffer.putShort((short) this.f);
    }

    public ii(int i) {
        super(new cy("vmhd"));
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
    }
}
