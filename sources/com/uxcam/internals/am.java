package com.uxcam.internals;

import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class am {

    /* renamed from: a, reason: collision with root package name */
    public final ByteBuffer f82a;
    public final int b;
    public int c;
    public int d;

    public am(ByteBuffer byteBuffer) {
        this.f82a = byteBuffer;
        this.b = byteBuffer.position();
    }

    public final void a() {
        int i = (this.d + 7) >> 3;
        for (int i2 = 0; i2 < i; i2++) {
            this.f82a.put((byte) (this.c >>> 24));
            this.c <<= 8;
        }
    }

    public am(ByteBuffer byteBuffer, int i, int i2, int i3) {
        this.f82a = byteBuffer;
        this.d = i;
        this.c = i2;
        this.b = i3;
    }

    public final void b(int i) {
        int i2 = this.c;
        int i3 = this.d;
        int i4 = (i << (31 - i3)) | i2;
        this.c = i4;
        int i5 = i3 + 1;
        this.d = i5;
        if (i5 == 32) {
            a(i4);
            this.d = 0;
            this.c = 0;
        }
    }

    public final void a(int i) {
        this.f82a.put((byte) (i >>> 24));
        this.f82a.put((byte) (i >> 16));
        this.f82a.put((byte) (i >> 8));
        this.f82a.put((byte) i);
    }

    public final void a(int i, int i2) {
        if (i2 > 32) {
            throw new IllegalArgumentException("Max 32 bit to write");
        }
        if (i2 == 0) {
            return;
        }
        int i3 = i & ((-1) >>> (32 - i2));
        int i4 = this.d;
        int i5 = 32 - i4;
        if (i5 < i2) {
            int i6 = i2 - i5;
            int i7 = this.c | (i3 >>> i6);
            this.c = i7;
            a(i7);
            this.c = i3 << (32 - i6);
            this.d = i6;
            return;
        }
        int i8 = (i3 << (i5 - i2)) | this.c;
        this.c = i8;
        int i9 = i4 + i2;
        this.d = i9;
        if (i9 == 32) {
            a(i8);
            this.d = 0;
            this.c = 0;
        }
    }
}
