package org.spongycastle.operator.bc;

import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.signers.DSADigestSigner;
import org.spongycastle.crypto.signers.ECDSASigner;
import org.spongycastle.operator.OperatorCreationException;

/* loaded from: classes7.dex */
public class BcECContentSignerBuilder extends BcContentSignerBuilder {
    public BcECContentSignerBuilder(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) {
        super(algorithmIdentifier, algorithmIdentifier2);
    }

    @Override // org.spongycastle.operator.bc.BcContentSignerBuilder
    protected Signer createSigner(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) throws OperatorCreationException {
        return new DSADigestSigner(new ECDSASigner(), this.digestProvider.get(algorithmIdentifier2));
    }
}
