package com.uxcam.internals;

import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class cy {

    /* renamed from: a, reason: collision with root package name */
    public final String f115a;
    public long b;

    public cy(String str) {
        this.f115a = str;
    }

    public final void a(ByteBuffer byteBuffer) {
        long j = this.b;
        if (j > 4294967296L) {
            byteBuffer.putInt(1);
        } else {
            byteBuffer.putInt((int) j);
        }
        byteBuffer.put(de.a(this.f115a));
        long j2 = this.b;
        if (j2 > 4294967296L) {
            byteBuffer.putLong(j2);
        }
    }

    public cy(long j, String str) {
        this.b = j;
        this.f115a = str;
    }
}
