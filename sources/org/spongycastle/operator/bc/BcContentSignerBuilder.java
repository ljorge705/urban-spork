package org.spongycastle.operator.bc;

import java.io.OutputStream;
import java.security.SecureRandom;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.operator.ContentSigner;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.operator.RuntimeOperatorException;

/* loaded from: classes7.dex */
public abstract class BcContentSignerBuilder {
    private AlgorithmIdentifier digAlgId;
    protected BcDigestProvider digestProvider = BcDefaultDigestProvider.INSTANCE;
    private SecureRandom random;
    private AlgorithmIdentifier sigAlgId;

    protected abstract Signer createSigner(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) throws OperatorCreationException;

    public BcContentSignerBuilder setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public BcContentSignerBuilder(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) {
        this.sigAlgId = algorithmIdentifier;
        this.digAlgId = algorithmIdentifier2;
    }

    public ContentSigner build(AsymmetricKeyParameter asymmetricKeyParameter) throws OperatorCreationException {
        Signer signerCreateSigner = createSigner(this.sigAlgId, this.digAlgId);
        if (this.random != null) {
            signerCreateSigner.init(true, new ParametersWithRandom(asymmetricKeyParameter, this.random));
        } else {
            signerCreateSigner.init(true, asymmetricKeyParameter);
        }
        return new ContentSigner(signerCreateSigner) { // from class: org.spongycastle.operator.bc.BcContentSignerBuilder.1
            private BcSignerOutputStream stream;
            final /* synthetic */ Signer val$sig;

            @Override // org.spongycastle.operator.ContentSigner
            public OutputStream getOutputStream() {
                return this.stream;
            }

            {
                this.val$sig = signerCreateSigner;
                this.stream = new BcSignerOutputStream(signerCreateSigner);
            }

            @Override // org.spongycastle.operator.ContentSigner
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return BcContentSignerBuilder.this.sigAlgId;
            }

            @Override // org.spongycastle.operator.ContentSigner
            public byte[] getSignature() {
                try {
                    return this.stream.getSignature();
                } catch (CryptoException e) {
                    throw new RuntimeOperatorException("exception obtaining signature: " + e.getMessage(), e);
                }
            }
        };
    }
}
