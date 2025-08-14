package com.uxcam.internals;

import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.HashMap;

/* loaded from: classes6.dex */
public final class go extends et {

    public static class aa extends ao {
        public aa() {
            new HashMap();
        }
    }

    static {
        new aa();
    }

    public go() {
        super(new cy("tmcd"));
    }

    @Override // com.uxcam.internals.et, com.uxcam.internals.dz, com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        super.a(byteBuffer);
        byteBuffer.putInt(0);
        byteBuffer.putInt(0);
        byteBuffer.putInt(0);
        byteBuffer.putInt(0);
        byteBuffer.put((byte) 0);
        byteBuffer.put((byte) -49);
    }

    @Override // com.uxcam.internals.dz, com.uxcam.internals.an
    public final void a(StringBuilder sb) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        sb.append(this.f83a.f115a + ": {\n");
        sb.append("entry: ");
        gx.a(this, sb, "flags", "timescale", "frameDuration", "numFrames");
        sb.append(",\nexts: [\n");
        b(sb);
        sb.append("\n]\n");
        sb.append("}\n");
    }
}
