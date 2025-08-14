package org.spongycastle.crypto.io;

import java.io.IOException;
import java.io.OutputStream;
import org.spongycastle.crypto.Signer;

/* loaded from: classes2.dex */
public class SignerOutputStream extends OutputStream {
    protected Signer signer;

    public Signer getSigner() {
        return this.signer;
    }

    public SignerOutputStream(Signer signer) {
        this.signer = signer;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.signer.update((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.signer.update(bArr, i, i2);
    }
}
