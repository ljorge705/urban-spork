package io.grpc.okhttp;

import io.grpc.internal.WritableBuffer;
import okio.Buffer;

/* loaded from: classes6.dex */
class OkHttpWritableBuffer implements WritableBuffer {
    private final Buffer buffer;
    private int readableBytes;
    private int writableBytes;

    Buffer buffer() {
        return this.buffer;
    }

    @Override // io.grpc.internal.WritableBuffer
    public int readableBytes() {
        return this.readableBytes;
    }

    @Override // io.grpc.internal.WritableBuffer
    public void release() {
    }

    @Override // io.grpc.internal.WritableBuffer
    public int writableBytes() {
        return this.writableBytes;
    }

    OkHttpWritableBuffer(Buffer buffer, int i) {
        this.buffer = buffer;
        this.writableBytes = i;
    }

    @Override // io.grpc.internal.WritableBuffer
    public void write(byte[] bArr, int i, int i2) {
        this.buffer.write(bArr, i, i2);
        this.writableBytes -= i2;
        this.readableBytes += i2;
    }

    @Override // io.grpc.internal.WritableBuffer
    public void write(byte b) {
        this.buffer.writeByte((int) b);
        this.writableBytes--;
        this.readableBytes++;
    }
}
