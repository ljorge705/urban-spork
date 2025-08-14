package org.spongycastle.operator.bc;

import java.io.IOException;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.signers.DSADigestSigner;
import org.spongycastle.crypto.signers.DSASigner;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.operator.DigestAlgorithmIdentifierFinder;
import org.spongycastle.operator.OperatorCreationException;

/* loaded from: classes7.dex */
public class BcDSAContentVerifierProviderBuilder extends BcContentVerifierProviderBuilder {
    private DigestAlgorithmIdentifierFinder digestAlgorithmFinder;

    public BcDSAContentVerifierProviderBuilder(DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder) {
        this.digestAlgorithmFinder = digestAlgorithmIdentifierFinder;
    }

    @Override // org.spongycastle.operator.bc.BcContentVerifierProviderBuilder
    protected Signer createSigner(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
        return new DSADigestSigner(new DSASigner(), this.digestProvider.get(this.digestAlgorithmFinder.find(algorithmIdentifier)));
    }

    @Override // org.spongycastle.operator.bc.BcContentVerifierProviderBuilder
    protected AsymmetricKeyParameter extractKeyParameters(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        return PublicKeyFactory.createKey(subjectPublicKeyInfo);
    }
}
