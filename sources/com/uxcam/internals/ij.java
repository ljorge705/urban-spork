package com.uxcam.internals;

import com.drew.metadata.heif.HeifBoxTypes;
import io.sentry.rrweb.RRWebVideoEvent;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.HashMap;

/* loaded from: classes6.dex */
public final class ij extends et {
    public final short d;
    public final short e;
    public final String f;
    public final int g;
    public final int h;
    public final short i;
    public final short j;
    public final float k;
    public final float l;
    public final short m;

    /* renamed from: n, reason: collision with root package name */
    public final String f211n;
    public final short o;
    public final short p;

    public static class aa extends ao {
        public aa() {
            HashMap map = new HashMap();
            map.put("pasp", ee.class);
            map.put(HeifBoxTypes.BOX_COLOUR_INFO, bc.class);
            map.put("gama", cp.class);
            map.put("clap", ax.class);
            map.put("fiel", cc.class);
        }
    }

    static {
        new aa();
    }

    public ij(cy cyVar, short s, short s2, String str) {
        super(cyVar, 0);
        this.d = (short) 0;
        this.e = (short) 0;
        this.f = "jcod";
        this.g = 0;
        this.h = 768;
        this.i = s;
        this.j = s2;
        float f = 72L;
        this.k = f;
        this.l = f;
        this.m = (short) 1;
        this.f211n = str;
        this.o = (short) 24;
        this.p = (short) -1;
    }

    @Override // com.uxcam.internals.et, com.uxcam.internals.dz, com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        super.a(byteBuffer);
        byteBuffer.putShort(this.d);
        byteBuffer.putShort(this.e);
        byteBuffer.put(de.a(this.f), 0, 4);
        byteBuffer.putInt(this.g);
        byteBuffer.putInt(this.h);
        byteBuffer.putShort(this.i);
        byteBuffer.putShort(this.j);
        byteBuffer.putInt((int) (this.k * 65536.0f));
        byteBuffer.putInt((int) (this.l * 65536.0f));
        byteBuffer.putInt(0);
        byteBuffer.putShort(this.m);
        String str = this.f211n;
        byteBuffer.put((byte) str.length());
        byteBuffer.put(de.a(str));
        byteBuffer.position(byteBuffer.position() + Math.min(byteBuffer.remaining(), 31 - str.length()));
        byteBuffer.putShort(this.o);
        byteBuffer.putShort(this.p);
        c(byteBuffer);
    }

    @Override // com.uxcam.internals.dz, com.uxcam.internals.an
    public final void a(StringBuilder sb) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        sb.append(this.f83a.f115a + ": {\n");
        sb.append("entry: ");
        gx.a(this, sb, "version", "revision", "vendor", "temporalQual", "spacialQual", "width", "height", "hRes", "vRes", RRWebVideoEvent.JsonKeys.FRAME_COUNT, "compressorName", "depth", "clrTbl");
        sb.append(",\nexts: [\n");
        b(sb);
        sb.append("\n]\n");
        sb.append("}\n");
    }
}
