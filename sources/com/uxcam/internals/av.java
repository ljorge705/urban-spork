package com.uxcam.internals;

import androidx.core.view.ViewCompat;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class av extends cm {
    public final long[] c;

    public av() {
        super(new cy(0L, "co64"));
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b & ViewCompat.MEASURED_SIZE_MASK);
        byteBuffer.putInt(this.c.length);
        for (long j : this.c) {
            byteBuffer.putLong(j);
        }
    }

    public av(long[] jArr) {
        this();
        this.c = jArr;
    }
}
