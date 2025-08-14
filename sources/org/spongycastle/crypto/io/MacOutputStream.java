package org.spongycastle.crypto.io;

import java.io.IOException;
import java.io.OutputStream;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Mac;

/* loaded from: classes2.dex */
public class MacOutputStream extends OutputStream {
    protected Mac mac;

    public MacOutputStream(Mac mac) {
        this.mac = mac;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IllegalStateException, IOException {
        this.mac.update((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IllegalStateException, DataLengthException, IOException {
        this.mac.update(bArr, i, i2);
    }

    public byte[] getMac() throws IllegalStateException, DataLengthException {
        byte[] bArr = new byte[this.mac.getMacSize()];
        this.mac.doFinal(bArr, 0);
        return bArr;
    }
}
