package com.uxcam.internals;

import androidx.core.view.ViewCompat;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class eu extends cm {
    public final int[] c;

    public eu(int[] iArr) {
        this();
        this.c = iArr;
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b & ViewCompat.MEASURED_SIZE_MASK);
        byteBuffer.putInt(0);
        byteBuffer.putInt(this.c.length);
        int length = this.c.length;
        for (int i = 0; i < length; i++) {
            byteBuffer.putInt(r1[i]);
        }
    }

    public eu() {
        super(new cy("stsz"));
    }
}
