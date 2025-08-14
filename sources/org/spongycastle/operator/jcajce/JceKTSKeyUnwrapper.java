package org.spongycastle.operator.jcajce;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import org.spongycastle.asn1.cms.GenericHybridParameters;
import org.spongycastle.asn1.cms.RsaKemParameters;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.util.DEROtherInfo;
import org.spongycastle.jcajce.spec.KTSParameterSpec;
import org.spongycastle.jcajce.util.DefaultJcaJceHelper;
import org.spongycastle.jcajce.util.NamedJcaJceHelper;
import org.spongycastle.jcajce.util.ProviderJcaJceHelper;
import org.spongycastle.operator.AsymmetricKeyUnwrapper;
import org.spongycastle.operator.GenericKey;
import org.spongycastle.operator.OperatorException;
import org.spongycastle.util.Arrays;

/* loaded from: classes7.dex */
public class JceKTSKeyUnwrapper extends AsymmetricKeyUnwrapper {
    private Map extraMappings;
    private OperatorHelper helper;
    private byte[] partyUInfo;
    private byte[] partyVInfo;
    private PrivateKey privKey;

    public JceKTSKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey, byte[] bArr, byte[] bArr2) {
        super(algorithmIdentifier);
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.extraMappings = new HashMap();
        this.privKey = privateKey;
        this.partyUInfo = Arrays.clone(bArr);
        this.partyVInfo = Arrays.clone(bArr2);
    }

    public JceKTSKeyUnwrapper setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceKTSKeyUnwrapper setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    @Override // org.spongycastle.operator.KeyUnwrapper
    public GenericKey generateUnwrappedKey(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) throws InvalidKeyException, OperatorException, InvalidAlgorithmParameterException {
        GenericHybridParameters genericHybridParameters = GenericHybridParameters.getInstance(getAlgorithmIdentifier().getParameters());
        Cipher cipherCreateAsymmetricWrapper = this.helper.createAsymmetricWrapper(getAlgorithmIdentifier().getAlgorithm(), this.extraMappings);
        String wrappingAlgorithmName = this.helper.getWrappingAlgorithmName(genericHybridParameters.getDem().getAlgorithm());
        RsaKemParameters rsaKemParameters = RsaKemParameters.getInstance(genericHybridParameters.getKem().getParameters());
        try {
            cipherCreateAsymmetricWrapper.init(4, this.privKey, new KTSParameterSpec.Builder(wrappingAlgorithmName, rsaKemParameters.getKeyLength().intValue() * 8, new DEROtherInfo.Builder(genericHybridParameters.getDem(), this.partyUInfo, this.partyVInfo).build().getEncoded()).withKdfAlgorithm(rsaKemParameters.getKeyDerivationFunction()).build());
            return new JceGenericKey(algorithmIdentifier, cipherCreateAsymmetricWrapper.unwrap(bArr, this.helper.getKeyAlgorithmName(algorithmIdentifier.getAlgorithm()), 3));
        } catch (Exception e) {
            throw new OperatorException("Unable to unwrap contents key: " + e.getMessage(), e);
        }
    }
}
