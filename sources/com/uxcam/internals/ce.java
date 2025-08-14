package com.uxcam.internals;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes6.dex */
public final class ce extends an {
    public final String b;
    public final int c;
    public final Collection<String> d;

    public ce(String str, List list) {
        super(new cy("ftyp"));
        new LinkedList();
        this.b = str;
        this.c = 512;
        this.d = list;
    }

    @Override // com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.put(de.a(this.b));
        byteBuffer.putInt(this.c);
        Iterator<String> it = this.d.iterator();
        while (it.hasNext()) {
            byteBuffer.put(de.a(it.next()));
        }
    }

    public ce() {
        super(new cy("ftyp"));
        this.d = new LinkedList();
    }
}
