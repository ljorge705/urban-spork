package org.spongycastle.cert.dane;

import java.io.OutputStream;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.operator.DigestCalculator;

/* loaded from: classes4.dex */
public class TruncatingDigestCalculator implements DigestCalculator {
    private final DigestCalculator baseCalculator;
    private final int length;

    public TruncatingDigestCalculator(DigestCalculator digestCalculator) {
        this(digestCalculator, 28);
    }

    public TruncatingDigestCalculator(DigestCalculator digestCalculator, int i) {
        this.baseCalculator = digestCalculator;
        this.length = i;
    }

    @Override // org.spongycastle.operator.DigestCalculator
    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return this.baseCalculator.getAlgorithmIdentifier();
    }

    @Override // org.spongycastle.operator.DigestCalculator
    public OutputStream getOutputStream() {
        return this.baseCalculator.getOutputStream();
    }

    @Override // org.spongycastle.operator.DigestCalculator
    public byte[] getDigest() {
        int i = this.length;
        byte[] bArr = new byte[i];
        System.arraycopy(this.baseCalculator.getDigest(), 0, bArr, 0, i);
        return bArr;
    }
}
