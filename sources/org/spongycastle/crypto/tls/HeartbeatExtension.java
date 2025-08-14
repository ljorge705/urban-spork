package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes7.dex */
public class HeartbeatExtension {
    protected short mode;

    public short getMode() {
        return this.mode;
    }

    public HeartbeatExtension(short s) {
        if (!HeartbeatMode.isValid(s)) {
            throw new IllegalArgumentException("'mode' is not a valid HeartbeatMode value");
        }
        this.mode = s;
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsUtils.writeUint8(this.mode, outputStream);
    }

    public static HeartbeatExtension parse(InputStream inputStream) throws IOException {
        short uint8 = TlsUtils.readUint8(inputStream);
        if (!HeartbeatMode.isValid(uint8)) {
            throw new TlsFatalAlert((short) 47);
        }
        return new HeartbeatExtension(uint8);
    }
}
