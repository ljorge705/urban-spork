package com.uxcam.internals;

import androidx.core.view.ViewCompat;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class dw extends cm {
    public final int c;
    public final long d;
    public final float e;
    public final float f;
    public final long g;
    public final long h;
    public final int[] i;
    public final int j;

    public dw(int i, long j, long j2, long j3, int[] iArr, int i2) {
        super(new cy("mvhd"));
        this.c = i;
        this.d = j;
        this.e = 1.0f;
        this.f = 1.0f;
        this.g = j2;
        this.h = j3;
        this.i = iArr;
        this.j = i2;
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b & ViewCompat.MEASURED_SIZE_MASK);
        byteBuffer.putInt(gm.a(this.g));
        byteBuffer.putInt(gm.a(this.h));
        byteBuffer.putInt(this.c);
        byteBuffer.putInt((int) this.d);
        byteBuffer.putInt((int) (this.e * 65536.0d));
        byteBuffer.putShort((short) (this.f * 256.0d));
        byteBuffer.put(new byte[10]);
        for (int i = 0; i < Math.min(9, this.i.length); i++) {
            byteBuffer.putInt(this.i[i]);
        }
        for (int iMin = Math.min(9, this.i.length); iMin < 9; iMin++) {
            byteBuffer.putInt(0);
        }
        byteBuffer.put(new byte[24]);
        byteBuffer.putInt(this.j);
    }

    public dw() {
        super(new cy("mvhd"));
    }

    @Override // com.uxcam.internals.an
    public final void a(StringBuilder sb) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        super.a(sb);
        sb.append(": ");
        gx.a(this, sb, "timescale", "duration", "rate", "volume", "created", "modified", "nextTrackId");
    }
}
