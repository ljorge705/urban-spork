package com.uxcam.internals;

import androidx.core.view.ViewCompat;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class bg extends cm {
    public final aa[] c;

    public static class aa {

        /* renamed from: a, reason: collision with root package name */
        public final int f96a;
        public int b;

        public aa(int i, int i2) {
            this.f96a = i;
            this.b = i2;
        }
    }

    public bg() {
        super(new cy("ctts"));
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b & ViewCompat.MEASURED_SIZE_MASK);
        byteBuffer.putInt(this.c.length);
        int i = 0;
        while (true) {
            aa[] aaVarArr = this.c;
            if (i >= aaVarArr.length) {
                return;
            }
            byteBuffer.putInt(aaVarArr[i].f96a);
            byteBuffer.putInt(this.c[i].b);
            i++;
        }
    }

    public bg(aa[] aaVarArr) {
        super(new cy("ctts"));
        this.c = aaVarArr;
    }
}
