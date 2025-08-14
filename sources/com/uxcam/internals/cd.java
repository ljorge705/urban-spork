package com.uxcam.internals;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/* loaded from: classes6.dex */
public final class cd implements ByteChannel, Channel, Closeable, ReadableByteChannel, WritableByteChannel {

    /* renamed from: a, reason: collision with root package name */
    public final FileChannel f107a;

    public cd(FileChannel fileChannel) {
        this.f107a = fileChannel;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f107a.close();
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return this.f107a.isOpen();
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        return this.f107a.read(byteBuffer);
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        return this.f107a.write(byteBuffer);
    }
}
