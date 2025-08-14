package org.spongycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import org.spongycastle.crypto.Digest;

/* loaded from: classes7.dex */
class DigestInputBuffer extends ByteArrayOutputStream {
    DigestInputBuffer() {
    }

    void updateDigest(Digest digest) {
        digest.update(this.buf, 0, this.count);
    }
}
