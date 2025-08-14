package com.uxcam.internals;

import androidx.core.view.ViewCompat;
import com.drew.metadata.mp4.Mp4BoxTypes;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class gy extends cm {
    public final int c;
    public final long d;
    public final float e;
    public final float f;
    public final long g;
    public final long h;
    public final float i;
    public final short j;
    public final long k;
    public final int[] l;

    public gy(int i, long j, float f, float f2, long j2, long j3, int[] iArr) {
        super(new cy(Mp4BoxTypes.BOX_TRACK_HEADER));
        this.c = i;
        this.d = j;
        this.e = f;
        this.f = f2;
        this.g = j2;
        this.h = j3;
        this.i = 1.0f;
        this.j = (short) 0;
        this.k = 0L;
        this.l = iArr;
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b & ViewCompat.MEASURED_SIZE_MASK);
        byteBuffer.putInt(gm.a(this.g));
        byteBuffer.putInt(gm.a(this.h));
        byteBuffer.putInt(this.c);
        byteBuffer.putInt(0);
        byteBuffer.putInt((int) this.d);
        byteBuffer.putInt(0);
        byteBuffer.putInt(0);
        byteBuffer.putShort(this.j);
        byteBuffer.putShort((short) this.k);
        byteBuffer.putShort((short) (this.i * 256.0d));
        byteBuffer.putShort((short) 0);
        for (int i = 0; i < 9; i++) {
            byteBuffer.putInt(this.l[i]);
        }
        byteBuffer.putInt((int) (this.e * 65536.0f));
        byteBuffer.putInt((int) (this.f * 65536.0f));
    }

    public gy() {
        super(new cy(Mp4BoxTypes.BOX_TRACK_HEADER));
    }

    @Override // com.uxcam.internals.an
    public final void a(StringBuilder sb) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        super.a(sb);
        sb.append(": ");
        gx.a(this, sb, "trackId", "duration", "width", "height", "created", "modified", "volume", "layer", "altGroup");
    }
}
