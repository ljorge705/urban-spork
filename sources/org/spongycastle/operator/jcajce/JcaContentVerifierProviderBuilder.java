package org.spongycastle.operator.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.cert.X509CertificateHolder;
import org.spongycastle.cert.jcajce.JcaX509CertificateHolder;
import org.spongycastle.jcajce.util.DefaultJcaJceHelper;
import org.spongycastle.jcajce.util.NamedJcaJceHelper;
import org.spongycastle.jcajce.util.ProviderJcaJceHelper;
import org.spongycastle.operator.ContentVerifier;
import org.spongycastle.operator.ContentVerifierProvider;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.operator.OperatorStreamException;
import org.spongycastle.operator.RawContentVerifier;
import org.spongycastle.operator.RuntimeOperatorException;

/* loaded from: classes7.dex */
public class JcaContentVerifierProviderBuilder {
    private OperatorHelper helper = new OperatorHelper(new DefaultJcaJceHelper());

    public JcaContentVerifierProviderBuilder setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcaContentVerifierProviderBuilder setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public ContentVerifierProvider build(X509CertificateHolder x509CertificateHolder) throws OperatorCreationException, CertificateException {
        return build(this.helper.convertCertificate(x509CertificateHolder));
    }

    public ContentVerifierProvider build(final X509Certificate x509Certificate) throws OperatorCreationException {
        try {
            final JcaX509CertificateHolder jcaX509CertificateHolder = new JcaX509CertificateHolder(x509Certificate);
            return new ContentVerifierProvider() { // from class: org.spongycastle.operator.jcajce.JcaContentVerifierProviderBuilder.1
                private SignatureOutputStream stream;

                @Override // org.spongycastle.operator.ContentVerifierProvider
                public X509CertificateHolder getAssociatedCertificate() {
                    return jcaX509CertificateHolder;
                }

                @Override // org.spongycastle.operator.ContentVerifierProvider
                public boolean hasAssociatedCertificate() {
                    return true;
                }

                @Override // org.spongycastle.operator.ContentVerifierProvider
                public ContentVerifier get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException, InvalidKeyException {
                    try {
                        Signature signatureCreateSignature = JcaContentVerifierProviderBuilder.this.helper.createSignature(algorithmIdentifier);
                        signatureCreateSignature.initVerify(x509Certificate.getPublicKey());
                        this.stream = JcaContentVerifierProviderBuilder.this.new SignatureOutputStream(signatureCreateSignature);
                        Signature signatureCreateRawSig = JcaContentVerifierProviderBuilder.this.createRawSig(algorithmIdentifier, x509Certificate.getPublicKey());
                        if (signatureCreateRawSig != null) {
                            return JcaContentVerifierProviderBuilder.this.new RawSigVerifier(algorithmIdentifier, this.stream, signatureCreateRawSig);
                        }
                        return JcaContentVerifierProviderBuilder.this.new SigVerifier(algorithmIdentifier, this.stream);
                    } catch (GeneralSecurityException e) {
                        throw new OperatorCreationException("exception on setup: " + e, e);
                    }
                }
            };
        } catch (CertificateEncodingException e) {
            throw new OperatorCreationException("cannot process certificate: " + e.getMessage(), e);
        }
    }

    public ContentVerifierProvider build(final PublicKey publicKey) throws OperatorCreationException {
        return new ContentVerifierProvider() { // from class: org.spongycastle.operator.jcajce.JcaContentVerifierProviderBuilder.2
            @Override // org.spongycastle.operator.ContentVerifierProvider
            public X509CertificateHolder getAssociatedCertificate() {
                return null;
            }

            @Override // org.spongycastle.operator.ContentVerifierProvider
            public boolean hasAssociatedCertificate() {
                return false;
            }

            @Override // org.spongycastle.operator.ContentVerifierProvider
            public ContentVerifier get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException, InvalidKeyException {
                SignatureOutputStream signatureOutputStreamCreateSignatureStream = JcaContentVerifierProviderBuilder.this.createSignatureStream(algorithmIdentifier, publicKey);
                Signature signatureCreateRawSig = JcaContentVerifierProviderBuilder.this.createRawSig(algorithmIdentifier, publicKey);
                if (signatureCreateRawSig != null) {
                    return JcaContentVerifierProviderBuilder.this.new RawSigVerifier(algorithmIdentifier, signatureOutputStreamCreateSignatureStream, signatureCreateRawSig);
                }
                return JcaContentVerifierProviderBuilder.this.new SigVerifier(algorithmIdentifier, signatureOutputStreamCreateSignatureStream);
            }
        };
    }

    public ContentVerifierProvider build(SubjectPublicKeyInfo subjectPublicKeyInfo) throws OperatorCreationException {
        return build(this.helper.convertPublicKey(subjectPublicKeyInfo));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SignatureOutputStream createSignatureStream(AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) throws OperatorCreationException, InvalidKeyException {
        try {
            Signature signatureCreateSignature = this.helper.createSignature(algorithmIdentifier);
            signatureCreateSignature.initVerify(publicKey);
            return new SignatureOutputStream(signatureCreateSignature);
        } catch (GeneralSecurityException e) {
            throw new OperatorCreationException("exception on setup: " + e, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Signature createRawSig(AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) throws InvalidKeyException {
        try {
            Signature signatureCreateRawSignature = this.helper.createRawSignature(algorithmIdentifier);
            if (signatureCreateRawSignature == null) {
                return signatureCreateRawSignature;
            }
            signatureCreateRawSignature.initVerify(publicKey);
            return signatureCreateRawSignature;
        } catch (Exception unused) {
            return null;
        }
    }

    private class SigVerifier implements ContentVerifier {
        private AlgorithmIdentifier algorithm;
        protected SignatureOutputStream stream;

        @Override // org.spongycastle.operator.ContentVerifier
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.algorithm;
        }

        SigVerifier(AlgorithmIdentifier algorithmIdentifier, SignatureOutputStream signatureOutputStream) {
            this.algorithm = algorithmIdentifier;
            this.stream = signatureOutputStream;
        }

        @Override // org.spongycastle.operator.ContentVerifier
        public OutputStream getOutputStream() {
            SignatureOutputStream signatureOutputStream = this.stream;
            if (signatureOutputStream != null) {
                return signatureOutputStream;
            }
            throw new IllegalStateException("verifier not initialised");
        }

        @Override // org.spongycastle.operator.ContentVerifier
        public boolean verify(byte[] bArr) {
            try {
                return this.stream.verify(bArr);
            } catch (SignatureException e) {
                throw new RuntimeOperatorException("exception obtaining signature: " + e.getMessage(), e);
            }
        }
    }

    private class RawSigVerifier extends SigVerifier implements RawContentVerifier {
        private Signature rawSignature;

        RawSigVerifier(AlgorithmIdentifier algorithmIdentifier, SignatureOutputStream signatureOutputStream, Signature signature) {
            super(algorithmIdentifier, signatureOutputStream);
            this.rawSignature = signature;
        }

        @Override // org.spongycastle.operator.jcajce.JcaContentVerifierProviderBuilder.SigVerifier, org.spongycastle.operator.ContentVerifier
        public boolean verify(byte[] bArr) throws SignatureException {
            try {
                return super.verify(bArr);
            } finally {
                try {
                    this.rawSignature.verify(bArr);
                } catch (Exception unused) {
                }
            }
        }

        @Override // org.spongycastle.operator.RawContentVerifier
        public boolean verify(byte[] bArr, byte[] bArr2) {
            try {
                try {
                    this.rawSignature.update(bArr);
                    return this.rawSignature.verify(bArr2);
                } catch (SignatureException e) {
                    throw new RuntimeOperatorException("exception obtaining raw signature: " + e.getMessage(), e);
                }
            } finally {
                try {
                    this.stream.verify(bArr2);
                } catch (Exception unused) {
                }
            }
        }
    }

    private class SignatureOutputStream extends OutputStream {
        private Signature sig;

        SignatureOutputStream(Signature signature) {
            this.sig = signature;
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws SignatureException, IOException {
            try {
                this.sig.update(bArr, i, i2);
            } catch (SignatureException e) {
                throw new OperatorStreamException("exception in content signer: " + e.getMessage(), e);
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws SignatureException, IOException {
            try {
                this.sig.update(bArr);
            } catch (SignatureException e) {
                throw new OperatorStreamException("exception in content signer: " + e.getMessage(), e);
            }
        }

        @Override // java.io.OutputStream
        public void write(int i) throws SignatureException, IOException {
            try {
                this.sig.update((byte) i);
            } catch (SignatureException e) {
                throw new OperatorStreamException("exception in content signer: " + e.getMessage(), e);
            }
        }

        boolean verify(byte[] bArr) throws SignatureException {
            return this.sig.verify(bArr);
        }
    }
}
