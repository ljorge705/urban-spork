package org.spongycastle.operator.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.jcajce.util.DefaultJcaJceHelper;
import org.spongycastle.jcajce.util.NamedJcaJceHelper;
import org.spongycastle.jcajce.util.ProviderJcaJceHelper;
import org.spongycastle.operator.ContentSigner;
import org.spongycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.operator.OperatorStreamException;
import org.spongycastle.operator.RuntimeOperatorException;

/* loaded from: classes7.dex */
public class JcaContentSignerBuilder {
    private OperatorHelper helper = new OperatorHelper(new DefaultJcaJceHelper());
    private SecureRandom random;
    private AlgorithmIdentifier sigAlgId;
    private String signatureAlgorithm;

    public JcaContentSignerBuilder setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public JcaContentSignerBuilder(String str) {
        this.signatureAlgorithm = str;
        this.sigAlgId = new DefaultSignatureAlgorithmIdentifierFinder().find(str);
    }

    public JcaContentSignerBuilder setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcaContentSignerBuilder setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public ContentSigner build(PrivateKey privateKey) throws OperatorCreationException, InvalidKeyException {
        try {
            Signature signatureCreateSignature = this.helper.createSignature(this.sigAlgId);
            AlgorithmIdentifier algorithmIdentifier = this.sigAlgId;
            SecureRandom secureRandom = this.random;
            if (secureRandom != null) {
                signatureCreateSignature.initSign(privateKey, secureRandom);
            } else {
                signatureCreateSignature.initSign(privateKey);
            }
            return new ContentSigner(signatureCreateSignature, algorithmIdentifier) { // from class: org.spongycastle.operator.jcajce.JcaContentSignerBuilder.1
                private SignatureOutputStream stream;
                final /* synthetic */ Signature val$sig;
                final /* synthetic */ AlgorithmIdentifier val$signatureAlgId;

                @Override // org.spongycastle.operator.ContentSigner
                public AlgorithmIdentifier getAlgorithmIdentifier() {
                    return this.val$signatureAlgId;
                }

                @Override // org.spongycastle.operator.ContentSigner
                public OutputStream getOutputStream() {
                    return this.stream;
                }

                {
                    this.val$sig = signatureCreateSignature;
                    this.val$signatureAlgId = algorithmIdentifier;
                    this.stream = JcaContentSignerBuilder.this.new SignatureOutputStream(signatureCreateSignature);
                }

                @Override // org.spongycastle.operator.ContentSigner
                public byte[] getSignature() {
                    try {
                        return this.stream.getSignature();
                    } catch (SignatureException e) {
                        throw new RuntimeOperatorException("exception obtaining signature: " + e.getMessage(), e);
                    }
                }
            };
        } catch (GeneralSecurityException e) {
            throw new OperatorCreationException("cannot create signer: " + e.getMessage(), e);
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

        byte[] getSignature() throws SignatureException {
            return this.sig.sign();
        }
    }
}
