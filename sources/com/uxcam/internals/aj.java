package com.uxcam.internals;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public final class aj extends an {
    public final int b;
    public final int c;
    public final int d;
    public final List<ByteBuffer> e;
    public final List<ByteBuffer> f;

    public aj() {
        super(new cy("avcC"));
        this.e = new ArrayList();
        this.f = new ArrayList();
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) this.b);
        byteBuffer.put((byte) this.c);
        byteBuffer.put((byte) this.d);
        byteBuffer.put((byte) -1);
        byteBuffer.put((byte) (this.e.size() | 224));
        for (ByteBuffer byteBuffer2 : this.e) {
            byteBuffer.putShort((short) (byteBuffer2.remaining() + 1));
            byteBuffer.put((byte) 103);
            dx.a(byteBuffer, byteBuffer2);
        }
        byteBuffer.put((byte) this.f.size());
        for (ByteBuffer byteBuffer3 : this.f) {
            byteBuffer.putShort((byte) (byteBuffer3.remaining() + 1));
            byteBuffer.put((byte) 104);
            dx.a(byteBuffer, byteBuffer3);
        }
    }

    public aj(int i, int i2, ArrayList arrayList, ArrayList arrayList2) {
        this();
        this.b = i;
        this.c = 0;
        this.d = i2;
        this.e = arrayList;
        this.f = arrayList2;
    }
}
