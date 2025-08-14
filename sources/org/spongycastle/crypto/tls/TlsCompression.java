package org.spongycastle.crypto.tls;

import java.io.OutputStream;

/* loaded from: classes7.dex */
public interface TlsCompression {
    OutputStream compress(OutputStream outputStream);

    OutputStream decompress(OutputStream outputStream);
}
