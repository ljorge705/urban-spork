package org.spongycastle.operator.bc;

import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.signers.RSADigestSigner;
import org.spongycastle.operator.OperatorCreationException;

/* loaded from: classes7.dex */
public class BcRSAContentSignerBuilder extends BcContentSignerBuilder {
    public BcRSAContentSignerBuilder(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) {
        super(algorithmIdentifier, algorithmIdentifier2);
    }

    @Override // org.spongycastle.operator.bc.BcContentSignerBuilder
    protected Signer createSigner(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) throws OperatorCreationException {
        return new RSADigestSigner(this.digestProvider.get(algorithmIdentifier2));
    }
}
