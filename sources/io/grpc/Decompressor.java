package io.grpc;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes6.dex */
public interface Decompressor {
    InputStream decompress(InputStream inputStream) throws IOException;

    String getMessageEncoding();
}
