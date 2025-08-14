package com.uxcam.internals;

import androidx.core.view.ViewCompat;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class ev extends cm {
    public final aa[] c;

    public static class aa {

        /* renamed from: a, reason: collision with root package name */
        public final long f142a;
        public final int b;
        public final int c;

        public aa(long j, int i, int i2) {
            this.f142a = j;
            this.b = i;
            this.c = i2;
        }
    }

    public ev(aa[] aaVarArr) {
        super(new cy("stsc"));
        this.c = aaVarArr;
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b & ViewCompat.MEASURED_SIZE_MASK);
        byteBuffer.putInt(this.c.length);
        for (aa aaVar : this.c) {
            byteBuffer.putInt((int) aaVar.f142a);
            byteBuffer.putInt(aaVar.b);
            byteBuffer.putInt(aaVar.c);
        }
    }

    public ev() {
        super(new cy("stsc"));
    }
}
