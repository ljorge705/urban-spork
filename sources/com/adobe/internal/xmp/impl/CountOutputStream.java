package com.adobe.internal.xmp.impl;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes5.dex */
public final class CountOutputStream extends OutputStream {
    private int bytesWritten = 0;
    private final OutputStream out;

    public int getBytesWritten() {
        return this.bytesWritten;
    }

    CountOutputStream(OutputStream outputStream) {
        this.out = outputStream;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        this.bytesWritten += i2;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr);
        this.bytesWritten += bArr.length;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.out.write(i);
        this.bytesWritten++;
    }
}
