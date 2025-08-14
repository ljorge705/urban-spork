package org.spongycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import org.spongycastle.crypto.Signer;

/* loaded from: classes7.dex */
class SignerInputBuffer extends ByteArrayOutputStream {
    SignerInputBuffer() {
    }

    void updateSigner(Signer signer) {
        signer.update(this.buf, 0, this.count);
    }
}
