package org.spongycastle.jcajce.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.crypto.Cipher;

/* loaded from: classes7.dex */
public class CipherOutputStream extends FilterOutputStream {
    private final Cipher cipher;
    private final byte[] oneByte;

    public CipherOutputStream(OutputStream outputStream, Cipher cipher) {
        super(outputStream);
        this.oneByte = new byte[1];
        this.cipher = cipher;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.oneByte;
        bArr[0] = (byte) i;
        write(bArr, 0, 1);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        byte[] bArrUpdate = this.cipher.update(bArr, i, i2);
        if (bArrUpdate != null) {
            this.out.write(bArrUpdate);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(5:0|(4:21|2|(1:4)|5)|19|11|(1:17)(1:18)) */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0037, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0038, code lost:
    
        if (r0 == null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003a, code lost:
    
        r0 = r1;
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003e  */
    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void close() throws javax.crypto.BadPaddingException, javax.crypto.IllegalBlockSizeException, java.io.IOException {
        /*
            r4 = this;
            javax.crypto.Cipher r0 = r4.cipher     // Catch: java.lang.Exception -> Lf java.security.GeneralSecurityException -> L25
            byte[] r0 = r0.doFinal()     // Catch: java.lang.Exception -> Lf java.security.GeneralSecurityException -> L25
            if (r0 == 0) goto Ld
            java.io.OutputStream r1 = r4.out     // Catch: java.lang.Exception -> Lf java.security.GeneralSecurityException -> L25
            r1.write(r0)     // Catch: java.lang.Exception -> Lf java.security.GeneralSecurityException -> L25
        Ld:
            r0 = 0
            goto L2e
        Lf:
            r0 = move-exception
            java.io.IOException r1 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Error closing stream: "
            r2.<init>(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            goto L2d
        L25:
            r0 = move-exception
            org.spongycastle.crypto.io.InvalidCipherTextIOException r1 = new org.spongycastle.crypto.io.InvalidCipherTextIOException
            java.lang.String r2 = "Error during cipher finalisation"
            r1.<init>(r2, r0)
        L2d:
            r0 = r1
        L2e:
            r4.flush()     // Catch: java.io.IOException -> L37
            java.io.OutputStream r1 = r4.out     // Catch: java.io.IOException -> L37
            r1.close()     // Catch: java.io.IOException -> L37
            goto L3b
        L37:
            r1 = move-exception
            if (r0 != 0) goto L3b
            r0 = r1
        L3b:
            if (r0 != 0) goto L3e
            return
        L3e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jcajce.io.CipherOutputStream.close():void");
    }
}
