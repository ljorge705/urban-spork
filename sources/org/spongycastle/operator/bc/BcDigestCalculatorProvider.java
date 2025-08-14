package org.spongycastle.operator.bc;

import java.io.IOException;
import java.io.OutputStream;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.Digest;
import org.spongycastle.operator.DigestCalculator;
import org.spongycastle.operator.DigestCalculatorProvider;
import org.spongycastle.operator.OperatorCreationException;

/* loaded from: classes7.dex */
public class BcDigestCalculatorProvider implements DigestCalculatorProvider {
    private BcDigestProvider digestProvider = BcDefaultDigestProvider.INSTANCE;

    @Override // org.spongycastle.operator.DigestCalculatorProvider
    public DigestCalculator get(final AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
        final DigestOutputStream digestOutputStream = new DigestOutputStream(this.digestProvider.get(algorithmIdentifier));
        return new DigestCalculator() { // from class: org.spongycastle.operator.bc.BcDigestCalculatorProvider.1
            @Override // org.spongycastle.operator.DigestCalculator
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return algorithmIdentifier;
            }

            @Override // org.spongycastle.operator.DigestCalculator
            public OutputStream getOutputStream() {
                return digestOutputStream;
            }

            @Override // org.spongycastle.operator.DigestCalculator
            public byte[] getDigest() {
                return digestOutputStream.getDigest();
            }
        };
    }

    private class DigestOutputStream extends OutputStream {
        private Digest dig;

        DigestOutputStream(Digest digest) {
            this.dig = digest;
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.dig.update(bArr, i, i2);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            this.dig.update(bArr, 0, bArr.length);
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            this.dig.update((byte) i);
        }

        byte[] getDigest() {
            byte[] bArr = new byte[this.dig.getDigestSize()];
            this.dig.doFinal(bArr, 0);
            return bArr;
        }
    }
}
