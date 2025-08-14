package com.uxcam.internals;

import androidx.core.view.ViewCompat;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public class gj extends cm {
    public final int[] c;

    public gj() {
        super(new cy("stss"));
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b & ViewCompat.MEASURED_SIZE_MASK);
        byteBuffer.putInt(this.c.length);
        int i = 0;
        while (true) {
            int[] iArr = this.c;
            if (i >= iArr.length) {
                return;
            }
            byteBuffer.putInt(iArr[i]);
            i++;
        }
    }

    public gj(int[] iArr) {
        this();
        this.c = iArr;
    }

    public gj(cy cyVar) {
        super(cyVar);
    }
}
