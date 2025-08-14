package io.grpc.internal;

import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public abstract class AbstractReadableBuffer implements ReadableBuffer {
    @Override // io.grpc.internal.ReadableBuffer
    public boolean byteBufferSupported() {
        return false;
    }

    @Override // io.grpc.internal.ReadableBuffer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // io.grpc.internal.ReadableBuffer
    public boolean hasArray() {
        return false;
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void mark() {
    }

    @Override // io.grpc.internal.ReadableBuffer
    public boolean markSupported() {
        return false;
    }

    @Override // io.grpc.internal.ReadableBuffer
    public final int readInt() {
        checkReadable(4);
        return (readUnsignedByte() << 24) | (readUnsignedByte() << 16) | (readUnsignedByte() << 8) | readUnsignedByte();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public byte[] array() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public int arrayOffset() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void reset() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public ByteBuffer getByteBuffer() {
        throw new UnsupportedOperationException();
    }

    protected final void checkReadable(int i) {
        if (readableBytes() < i) {
            throw new IndexOutOfBoundsException();
        }
    }
}
