package org.spongycastle.jce.provider;

import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KDFParameters;

/* loaded from: classes7.dex */
public class BrokenKDF2BytesGenerator implements DerivationFunction {
    private Digest digest;
    private byte[] iv;
    private byte[] shared;

    public Digest getDigest() {
        return this.digest;
    }

    public BrokenKDF2BytesGenerator(Digest digest) {
        this.digest = digest;
    }

    @Override // org.spongycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        if (!(derivationParameters instanceof KDFParameters)) {
            throw new IllegalArgumentException("KDF parameters required for generator");
        }
        KDFParameters kDFParameters = (KDFParameters) derivationParameters;
        this.shared = kDFParameters.getSharedSecret();
        this.iv = kDFParameters.getIV();
    }

    @Override // org.spongycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i, int i2) throws DataLengthException, IllegalArgumentException {
        if (bArr.length - i2 < i) {
            throw new OutputLengthException("output buffer too small");
        }
        long j = i2 * 8;
        if (j > this.digest.getDigestSize() * 17179869184L) {
            new IllegalArgumentException("Output length to large");
        }
        int digestSize = (int) (j / this.digest.getDigestSize());
        int digestSize2 = this.digest.getDigestSize();
        byte[] bArr2 = new byte[digestSize2];
        for (int i3 = 1; i3 <= digestSize; i3++) {
            Digest digest = this.digest;
            byte[] bArr3 = this.shared;
            digest.update(bArr3, 0, bArr3.length);
            this.digest.update((byte) (i3 & 255));
            this.digest.update((byte) ((i3 >> 8) & 255));
            this.digest.update((byte) ((i3 >> 16) & 255));
            this.digest.update((byte) ((i3 >> 24) & 255));
            Digest digest2 = this.digest;
            byte[] bArr4 = this.iv;
            digest2.update(bArr4, 0, bArr4.length);
            this.digest.doFinal(bArr2, 0);
            int i4 = i2 - i;
            if (i4 > digestSize2) {
                System.arraycopy(bArr2, 0, bArr, i, digestSize2);
                i += digestSize2;
            } else {
                System.arraycopy(bArr2, 0, bArr, i, i4);
            }
        }
        this.digest.reset();
        return i2;
    }
}
