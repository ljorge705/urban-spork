package com.uxcam.internals;

import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class al {

    /* renamed from: a, reason: collision with root package name */
    public final ByteBuffer f81a;
    public int b;
    public int c;

    public al(ByteBuffer byteBuffer) {
        this.f81a = byteBuffer;
        byteBuffer.position();
        this.c = b();
        this.b = 0;
    }

    public final int b() {
        if (this.f81a.remaining() >= 4) {
            this.b -= 32;
            return ((this.f81a.get() & 255) << 24) | ((this.f81a.get() & 255) << 16) | ((this.f81a.get() & 255) << 8) | (this.f81a.get() & 255);
        }
        this.b -= this.f81a.remaining() << 3;
        int i = (this.f81a.hasRemaining() ? this.f81a.get() & 255 : 0) << 8;
        if (this.f81a.hasRemaining()) {
            i |= this.f81a.get() & 255;
        }
        int i2 = i << 8;
        if (this.f81a.hasRemaining()) {
            i2 |= this.f81a.get() & 255;
        }
        int i3 = i2 << 8;
        return this.f81a.hasRemaining() ? i3 | (this.f81a.get() & 255) : i3;
    }

    public final int a() {
        int i = this.c;
        int i2 = i >>> 31;
        this.c = i << 1;
        int i3 = this.b + 1;
        this.b = i3;
        if (i3 == 32) {
            this.c = b();
        }
        return i2;
    }

    public final int a(int i) {
        if (i > 24) {
            throw new IllegalArgumentException("Can not check more then 24 bit");
        }
        while (true) {
            int i2 = this.b;
            if (i2 + i <= 32) {
                return this.c >>> (32 - i);
            }
            this.b = i2 - 8;
            this.c |= (this.f81a.hasRemaining() ? this.f81a.get() & 255 : 0) << this.b;
        }
    }

    public final int b(int i) {
        int i2;
        if (i > 32) {
            throw new IllegalArgumentException("Can not read more then 32 bit");
        }
        int i3 = this.b;
        if (i + i3 > 31) {
            i -= 32 - i3;
            i2 = (this.c >>> i3) << i;
            this.b = 32;
            this.c = b();
        } else {
            i2 = 0;
        }
        if (i == 0) {
            return i2;
        }
        int i4 = this.c;
        int i5 = i2 | (i4 >>> (32 - i));
        this.c = i4 << i;
        this.b += i;
        return i5;
    }
}
