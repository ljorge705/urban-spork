package com.uxcam.internals;

import androidx.core.view.ViewCompat;
import io.sentry.protocol.Device;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class dr extends cm {
    public final long c;
    public final long d;
    public final int e;
    public final long f;
    public final int g;
    public final int h;

    public dr(int i, long j, long j2, long j3) {
        super(new cy("mdhd"));
        this.e = i;
        this.f = j;
        this.g = 0;
        this.c = j2;
        this.d = j3;
        this.h = 0;
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(this.b & ViewCompat.MEASURED_SIZE_MASK);
        byteBuffer.putInt(gm.a(this.c));
        byteBuffer.putInt(gm.a(this.d));
        byteBuffer.putInt(this.e);
        byteBuffer.putInt((int) this.f);
        byteBuffer.putShort((short) this.g);
        byteBuffer.putShort((short) this.h);
    }

    public dr() {
        super(new cy("mdhd"));
    }

    @Override // com.uxcam.internals.an
    public final void a(StringBuilder sb) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        super.a(sb);
        sb.append(": ");
        gx.a(this, sb, "created", "modified", "timescale", "duration", Device.JsonKeys.LANGUAGE, "quality");
    }
}
