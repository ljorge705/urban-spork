package org.spongycastle.operator.bc;

import java.security.SecureRandom;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.operator.AsymmetricKeyWrapper;
import org.spongycastle.operator.GenericKey;
import org.spongycastle.operator.OperatorException;

/* loaded from: classes7.dex */
public abstract class BcAsymmetricKeyWrapper extends AsymmetricKeyWrapper {
    private AsymmetricKeyParameter publicKey;
    private SecureRandom random;

    protected abstract AsymmetricBlockCipher createAsymmetricWrapper(ASN1ObjectIdentifier aSN1ObjectIdentifier);

    public BcAsymmetricKeyWrapper setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public BcAsymmetricKeyWrapper(AlgorithmIdentifier algorithmIdentifier, AsymmetricKeyParameter asymmetricKeyParameter) {
        super(algorithmIdentifier);
        this.publicKey = asymmetricKeyParameter;
    }

    @Override // org.spongycastle.operator.KeyWrapper
    public byte[] generateWrappedKey(GenericKey genericKey) throws OperatorException {
        AsymmetricBlockCipher asymmetricBlockCipherCreateAsymmetricWrapper = createAsymmetricWrapper(getAlgorithmIdentifier().getAlgorithm());
        CipherParameters parametersWithRandom = this.publicKey;
        if (this.random != null) {
            parametersWithRandom = new ParametersWithRandom(parametersWithRandom, this.random);
        }
        try {
            byte[] keyBytes = OperatorUtils.getKeyBytes(genericKey);
            asymmetricBlockCipherCreateAsymmetricWrapper.init(true, parametersWithRandom);
            return asymmetricBlockCipherCreateAsymmetricWrapper.processBlock(keyBytes, 0, keyBytes.length);
        } catch (InvalidCipherTextException e) {
            throw new OperatorException("unable to encrypt contents key", e);
        }
    }
}
