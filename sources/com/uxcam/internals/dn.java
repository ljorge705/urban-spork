package com.uxcam.internals;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public final class dn {
    public final long b;
    public final cd c;

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f124a = new ArrayList();
    public int d = 1;

    public dn(cd cdVar, ce ceVar) {
        this.c = cdVar;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(1024);
        ceVar.b(byteBufferAllocate);
        new cy(8L, "wide").a(byteBufferAllocate);
        new cy(1L, "mdat").a(byteBufferAllocate);
        this.b = byteBufferAllocate.position();
        byteBufferAllocate.putLong(0L);
        byteBufferAllocate.flip();
        cdVar.write(byteBufferAllocate);
    }
}
