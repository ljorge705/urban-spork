package org.spongycastle.operator.bc;

import java.io.IOException;
import java.io.OutputStream;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.cert.X509CertificateHolder;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.operator.ContentVerifier;
import org.spongycastle.operator.ContentVerifierProvider;
import org.spongycastle.operator.OperatorCreationException;

/* loaded from: classes7.dex */
public abstract class BcContentVerifierProviderBuilder {
    protected BcDigestProvider digestProvider = BcDefaultDigestProvider.INSTANCE;

    protected abstract Signer createSigner(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException;

    protected abstract AsymmetricKeyParameter extractKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException;

    public ContentVerifierProvider build(final X509CertificateHolder x509CertificateHolder) throws OperatorCreationException {
        return new ContentVerifierProvider() { // from class: org.spongycastle.operator.bc.BcContentVerifierProviderBuilder.1
            @Override // org.spongycastle.operator.ContentVerifierProvider
            public X509CertificateHolder getAssociatedCertificate() {
                return x509CertificateHolder;
            }

            @Override // org.spongycastle.operator.ContentVerifierProvider
            public boolean hasAssociatedCertificate() {
                return true;
            }

            @Override // org.spongycastle.operator.ContentVerifierProvider
            public ContentVerifier get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
                try {
                    return BcContentVerifierProviderBuilder.this.new SigVerifier(algorithmIdentifier, BcContentVerifierProviderBuilder.this.createSignatureStream(algorithmIdentifier, BcContentVerifierProviderBuilder.this.extractKeyParameters(x509CertificateHolder.getSubjectPublicKeyInfo())));
                } catch (IOException e) {
                    throw new OperatorCreationException("exception on setup: " + e, e);
                }
            }
        };
    }

    public ContentVerifierProvider build(final AsymmetricKeyParameter asymmetricKeyParameter) throws OperatorCreationException {
        return new ContentVerifierProvider() { // from class: org.spongycastle.operator.bc.BcContentVerifierProviderBuilder.2
            @Override // org.spongycastle.operator.ContentVerifierProvider
            public X509CertificateHolder getAssociatedCertificate() {
                return null;
            }

            @Override // org.spongycastle.operator.ContentVerifierProvider
            public boolean hasAssociatedCertificate() {
                return false;
            }

            @Override // org.spongycastle.operator.ContentVerifierProvider
            public ContentVerifier get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
                return BcContentVerifierProviderBuilder.this.new SigVerifier(algorithmIdentifier, BcContentVerifierProviderBuilder.this.createSignatureStream(algorithmIdentifier, asymmetricKeyParameter));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BcSignerOutputStream createSignatureStream(AlgorithmIdentifier algorithmIdentifier, AsymmetricKeyParameter asymmetricKeyParameter) throws OperatorCreationException {
        Signer signerCreateSigner = createSigner(algorithmIdentifier);
        signerCreateSigner.init(false, asymmetricKeyParameter);
        return new BcSignerOutputStream(signerCreateSigner);
    }

    private class SigVerifier implements ContentVerifier {
        private AlgorithmIdentifier algorithm;
        private BcSignerOutputStream stream;

        @Override // org.spongycastle.operator.ContentVerifier
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.algorithm;
        }

        SigVerifier(AlgorithmIdentifier algorithmIdentifier, BcSignerOutputStream bcSignerOutputStream) {
            this.algorithm = algorithmIdentifier;
            this.stream = bcSignerOutputStream;
        }

        @Override // org.spongycastle.operator.ContentVerifier
        public OutputStream getOutputStream() {
            BcSignerOutputStream bcSignerOutputStream = this.stream;
            if (bcSignerOutputStream != null) {
                return bcSignerOutputStream;
            }
            throw new IllegalStateException("verifier not initialised");
        }

        @Override // org.spongycastle.operator.ContentVerifier
        public boolean verify(byte[] bArr) {
            return this.stream.verify(bArr);
        }
    }
}
