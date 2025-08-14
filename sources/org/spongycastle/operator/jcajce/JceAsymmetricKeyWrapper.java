package org.spongycastle.operator.jcajce;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Provider;
import java.security.ProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSKeyParameters;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.RSAESOAEPparams;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.jcajce.util.DefaultJcaJceHelper;
import org.spongycastle.jcajce.util.NamedJcaJceHelper;
import org.spongycastle.jcajce.util.ProviderJcaJceHelper;
import org.spongycastle.operator.AsymmetricKeyWrapper;
import org.spongycastle.operator.GenericKey;
import org.spongycastle.operator.OperatorException;

/* loaded from: classes7.dex */
public class JceAsymmetricKeyWrapper extends AsymmetricKeyWrapper {
    private static final Map digests;
    private Map extraMappings;
    private OperatorHelper helper;
    private PublicKey publicKey;
    private SecureRandom random;

    public JceAsymmetricKeyWrapper setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public JceAsymmetricKeyWrapper(PublicKey publicKey) {
        super(SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()).getAlgorithm());
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.extraMappings = new HashMap();
        this.publicKey = publicKey;
    }

    public JceAsymmetricKeyWrapper(X509Certificate x509Certificate) {
        this(x509Certificate.getPublicKey());
    }

    public JceAsymmetricKeyWrapper(AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) {
        super(algorithmIdentifier);
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.extraMappings = new HashMap();
        this.publicKey = publicKey;
    }

    public JceAsymmetricKeyWrapper(AlgorithmParameterSpec algorithmParameterSpec, PublicKey publicKey) {
        super(extractFromSpec(algorithmParameterSpec));
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.extraMappings = new HashMap();
        this.publicKey = publicKey;
    }

    public JceAsymmetricKeyWrapper setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceAsymmetricKeyWrapper setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public JceAsymmetricKeyWrapper setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        this.extraMappings.put(aSN1ObjectIdentifier, str);
        return this;
    }

    @Override // org.spongycastle.operator.KeyWrapper
    public byte[] generateWrappedKey(GenericKey genericKey) throws IllegalBlockSizeException, IOException, InvalidKeyException, OperatorException, InvalidAlgorithmParameterException {
        byte[] bArrWrap;
        Cipher cipherCreateAsymmetricWrapper = this.helper.createAsymmetricWrapper(getAlgorithmIdentifier().getAlgorithm(), this.extraMappings);
        AlgorithmParameters algorithmParametersCreateAlgorithmParameters = this.helper.createAlgorithmParameters(getAlgorithmIdentifier());
        try {
            if (algorithmParametersCreateAlgorithmParameters != null) {
                cipherCreateAsymmetricWrapper.init(3, this.publicKey, algorithmParametersCreateAlgorithmParameters, this.random);
            } else {
                cipherCreateAsymmetricWrapper.init(3, this.publicKey, this.random);
            }
            bArrWrap = cipherCreateAsymmetricWrapper.wrap(OperatorUtils.getJceKey(genericKey));
        } catch (IllegalStateException | UnsupportedOperationException | InvalidKeyException | GeneralSecurityException | ProviderException unused) {
            bArrWrap = null;
        }
        if (bArrWrap != null) {
            return bArrWrap;
        }
        try {
            cipherCreateAsymmetricWrapper.init(1, this.publicKey, this.random);
            return cipherCreateAsymmetricWrapper.doFinal(OperatorUtils.getJceKey(genericKey).getEncoded());
        } catch (InvalidKeyException e) {
            throw new OperatorException("unable to encrypt contents key", e);
        } catch (GeneralSecurityException e2) {
            throw new OperatorException("unable to encrypt contents key", e2);
        }
    }

    private static AlgorithmIdentifier extractFromSpec(AlgorithmParameterSpec algorithmParameterSpec) {
        if (algorithmParameterSpec instanceof OAEPParameterSpec) {
            OAEPParameterSpec oAEPParameterSpec = (OAEPParameterSpec) algorithmParameterSpec;
            if (oAEPParameterSpec.getMGFAlgorithm().equals(OAEPParameterSpec.DEFAULT.getMGFAlgorithm())) {
                if (oAEPParameterSpec.getPSource() instanceof PSource.PSpecified) {
                    return new AlgorithmIdentifier(PKCSObjectIdentifiers.id_RSAES_OAEP, new RSAESOAEPparams(getDigest(oAEPParameterSpec.getDigestAlgorithm()), new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, getDigest(((MGF1ParameterSpec) oAEPParameterSpec.getMGFParameters()).getDigestAlgorithm())), new AlgorithmIdentifier(PKCSObjectIdentifiers.id_pSpecified, new DEROctetString(((PSource.PSpecified) oAEPParameterSpec.getPSource()).getValue()))));
                }
                throw new IllegalArgumentException("unknown PSource: " + oAEPParameterSpec.getPSource().getAlgorithm());
            }
            throw new IllegalArgumentException("unknown MGF: " + oAEPParameterSpec.getMGFAlgorithm());
        }
        throw new IllegalArgumentException("unknown spec: " + algorithmParameterSpec.getClass().getName());
    }

    static {
        HashMap map = new HashMap();
        digests = map;
        map.put("SHA-1", new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE));
        map.put("SHA-1", new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE));
        map.put("SHA224", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha224, DERNull.INSTANCE));
        map.put("SHA-224", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha224, DERNull.INSTANCE));
        map.put("SHA256", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256, DERNull.INSTANCE));
        map.put("SHA-256", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256, DERNull.INSTANCE));
        map.put("SHA384", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384, DERNull.INSTANCE));
        map.put("SHA-384", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384, DERNull.INSTANCE));
        map.put("SHA512", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512, DERNull.INSTANCE));
        map.put("SHA-512", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512, DERNull.INSTANCE));
        map.put("SHA512/224", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_224, DERNull.INSTANCE));
        map.put("SHA-512/224", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_224, DERNull.INSTANCE));
        map.put("SHA-512(224)", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_224, DERNull.INSTANCE));
        map.put("SHA512/256", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_256, DERNull.INSTANCE));
        map.put(SPHINCSKeyParameters.SHA512_256, new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_256, DERNull.INSTANCE));
        map.put("SHA-512(256)", new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_256, DERNull.INSTANCE));
    }

    private static AlgorithmIdentifier getDigest(String str) {
        AlgorithmIdentifier algorithmIdentifier = (AlgorithmIdentifier) digests.get(str);
        if (algorithmIdentifier != null) {
            return algorithmIdentifier;
        }
        throw new IllegalArgumentException("unknown digest name: " + str);
    }
}
