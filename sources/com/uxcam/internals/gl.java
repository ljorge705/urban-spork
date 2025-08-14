package com.uxcam.internals;

import androidx.core.view.ViewCompat;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class gl extends cm {
    public final aa[] c;

    public static class aa {

        /* renamed from: a, reason: collision with root package name */
        public final int f178a;
        public final int b;

        public aa(int i, int i2) {
            this.f178a = i;
            this.b = i2;
        }
    }

    public gl(aa[] aaVarArr) {
        super(new cy("stts"));
        this.c = aaVarArr;
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b & ViewCompat.MEASURED_SIZE_MASK);
        byteBuffer.putInt(this.c.length);
        for (aa aaVar : this.c) {
            byteBuffer.putInt(aaVar.f178a);
            byteBuffer.putInt(aaVar.b);
        }
    }

    public gl() {
        super(new cy("stts"));
    }
}
