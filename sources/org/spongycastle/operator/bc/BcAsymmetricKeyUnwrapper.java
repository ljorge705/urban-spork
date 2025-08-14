package org.spongycastle.operator.bc;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.operator.AsymmetricKeyUnwrapper;
import org.spongycastle.operator.GenericKey;
import org.spongycastle.operator.OperatorException;

/* loaded from: classes7.dex */
public abstract class BcAsymmetricKeyUnwrapper extends AsymmetricKeyUnwrapper {
    private AsymmetricKeyParameter privateKey;

    protected abstract AsymmetricBlockCipher createAsymmetricUnwrapper(ASN1ObjectIdentifier aSN1ObjectIdentifier);

    public BcAsymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, AsymmetricKeyParameter asymmetricKeyParameter) {
        super(algorithmIdentifier);
        this.privateKey = asymmetricKeyParameter;
    }

    @Override // org.spongycastle.operator.KeyUnwrapper
    public GenericKey generateUnwrappedKey(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) throws OperatorException {
        AsymmetricBlockCipher asymmetricBlockCipherCreateAsymmetricUnwrapper = createAsymmetricUnwrapper(getAlgorithmIdentifier().getAlgorithm());
        asymmetricBlockCipherCreateAsymmetricUnwrapper.init(false, this.privateKey);
        try {
            byte[] bArrProcessBlock = asymmetricBlockCipherCreateAsymmetricUnwrapper.processBlock(bArr, 0, bArr.length);
            if (algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.des_EDE3_CBC)) {
                return new GenericKey(algorithmIdentifier, bArrProcessBlock);
            }
            return new GenericKey(algorithmIdentifier, bArrProcessBlock);
        } catch (InvalidCipherTextException e) {
            throw new OperatorException("unable to recover secret key: " + e.getMessage(), e);
        }
    }
}
