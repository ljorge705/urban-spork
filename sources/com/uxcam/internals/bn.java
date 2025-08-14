package com.uxcam.internals;

import java.nio.ByteBuffer;
import java.util.HashMap;

/* loaded from: classes6.dex */
public final class bn extends dz {
    public static final /* synthetic */ int c = 0;

    public static class aa extends ao {
        public aa() {
            HashMap map = new HashMap();
            map.put("url ", hw.class);
            int i = af.c;
            map.put("alis", af.class);
            map.put("cios", af.class);
        }
    }

    static {
        new aa();
    }

    public bn() {
        this(new cy("dref"));
    }

    @Override // com.uxcam.internals.dz, com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(0);
        byteBuffer.putInt(this.b.size());
        super.a(byteBuffer);
    }

    public bn(cy cyVar) {
        super(cyVar);
    }
}
