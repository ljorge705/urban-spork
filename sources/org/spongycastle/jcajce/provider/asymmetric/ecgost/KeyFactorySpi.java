package org.spongycastle.jcajce.provider.asymmetric.ecgost;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.jcajce.provider.asymmetric.util.BaseKeyFactorySpi;
import org.spongycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.spec.ECParameterSpec;

/* loaded from: classes7.dex */
public class KeyFactorySpi extends BaseKeyFactorySpi {
    @Override // org.spongycastle.jcajce.provider.asymmetric.util.BaseKeyFactorySpi, java.security.KeyFactorySpi
    protected KeySpec engineGetKeySpec(Key key, Class cls) throws InvalidKeySpecException {
        if (cls.isAssignableFrom(ECPublicKeySpec.class) && (key instanceof ECPublicKey)) {
            ECPublicKey eCPublicKey = (ECPublicKey) key;
            if (eCPublicKey.getParams() != null) {
                return new ECPublicKeySpec(eCPublicKey.getW(), eCPublicKey.getParams());
            }
            ECParameterSpec ecImplicitlyCa = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
            return new ECPublicKeySpec(eCPublicKey.getW(), EC5Util.convertSpec(EC5Util.convertCurve(ecImplicitlyCa.getCurve(), ecImplicitlyCa.getSeed()), ecImplicitlyCa));
        }
        if (cls.isAssignableFrom(ECPrivateKeySpec.class) && (key instanceof ECPrivateKey)) {
            ECPrivateKey eCPrivateKey = (ECPrivateKey) key;
            if (eCPrivateKey.getParams() != null) {
                return new ECPrivateKeySpec(eCPrivateKey.getS(), eCPrivateKey.getParams());
            }
            ECParameterSpec ecImplicitlyCa2 = BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
            return new ECPrivateKeySpec(eCPrivateKey.getS(), EC5Util.convertSpec(EC5Util.convertCurve(ecImplicitlyCa2.getCurve(), ecImplicitlyCa2.getSeed()), ecImplicitlyCa2));
        }
        if (cls.isAssignableFrom(org.spongycastle.jce.spec.ECPublicKeySpec.class) && (key instanceof ECPublicKey)) {
            ECPublicKey eCPublicKey2 = (ECPublicKey) key;
            if (eCPublicKey2.getParams() != null) {
                return new org.spongycastle.jce.spec.ECPublicKeySpec(EC5Util.convertPoint(eCPublicKey2.getParams(), eCPublicKey2.getW(), false), EC5Util.convertSpec(eCPublicKey2.getParams(), false));
            }
            return new org.spongycastle.jce.spec.ECPublicKeySpec(EC5Util.convertPoint(eCPublicKey2.getParams(), eCPublicKey2.getW(), false), BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa());
        }
        if (cls.isAssignableFrom(org.spongycastle.jce.spec.ECPrivateKeySpec.class) && (key instanceof ECPrivateKey)) {
            ECPrivateKey eCPrivateKey2 = (ECPrivateKey) key;
            if (eCPrivateKey2.getParams() != null) {
                return new org.spongycastle.jce.spec.ECPrivateKeySpec(eCPrivateKey2.getS(), EC5Util.convertSpec(eCPrivateKey2.getParams(), false));
            }
            return new org.spongycastle.jce.spec.ECPrivateKeySpec(eCPrivateKey2.getS(), BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa());
        }
        return super.engineGetKeySpec(key, cls);
    }

    @Override // java.security.KeyFactorySpi
    protected Key engineTranslateKey(Key key) throws InvalidKeyException {
        throw new InvalidKeyException("key type unknown");
    }

    @Override // org.spongycastle.jcajce.provider.asymmetric.util.BaseKeyFactorySpi, java.security.KeyFactorySpi
    protected PrivateKey engineGeneratePrivate(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof org.spongycastle.jce.spec.ECPrivateKeySpec) {
            return new BCECGOST3410PrivateKey((org.spongycastle.jce.spec.ECPrivateKeySpec) keySpec);
        }
        if (keySpec instanceof ECPrivateKeySpec) {
            return new BCECGOST3410PrivateKey((ECPrivateKeySpec) keySpec);
        }
        return super.engineGeneratePrivate(keySpec);
    }

    @Override // org.spongycastle.jcajce.provider.asymmetric.util.BaseKeyFactorySpi, java.security.KeyFactorySpi
    protected PublicKey engineGeneratePublic(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof org.spongycastle.jce.spec.ECPublicKeySpec) {
            return new BCECGOST3410PublicKey((org.spongycastle.jce.spec.ECPublicKeySpec) keySpec, BouncyCastleProvider.CONFIGURATION);
        }
        if (keySpec instanceof ECPublicKeySpec) {
            return new BCECGOST3410PublicKey((ECPublicKeySpec) keySpec);
        }
        return super.engineGeneratePublic(keySpec);
    }

    @Override // org.spongycastle.jcajce.provider.util.AsymmetricKeyInfoConverter
    public PrivateKey generatePrivate(PrivateKeyInfo privateKeyInfo) throws IOException {
        ASN1ObjectIdentifier algorithm = privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm();
        if (algorithm.equals(CryptoProObjectIdentifiers.gostR3410_2001)) {
            return new BCECGOST3410PrivateKey(privateKeyInfo);
        }
        if (algorithm.equals(CryptoProObjectIdentifiers.gostR3410_2001DH)) {
            return new BCECGOST3410PrivateKey(privateKeyInfo);
        }
        if (algorithm.equals(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_ESDH)) {
            return new BCECGOST3410PrivateKey(privateKeyInfo);
        }
        throw new IOException("algorithm identifier " + algorithm + " in key not recognised");
    }

    @Override // org.spongycastle.jcajce.provider.util.AsymmetricKeyInfoConverter
    public PublicKey generatePublic(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        ASN1ObjectIdentifier algorithm = subjectPublicKeyInfo.getAlgorithm().getAlgorithm();
        if (algorithm.equals(CryptoProObjectIdentifiers.gostR3410_2001)) {
            return new BCECGOST3410PublicKey(subjectPublicKeyInfo);
        }
        if (algorithm.equals(CryptoProObjectIdentifiers.gostR3410_2001DH)) {
            return new BCECGOST3410PublicKey(subjectPublicKeyInfo);
        }
        if (algorithm.equals(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_ESDH)) {
            return new BCECGOST3410PublicKey(subjectPublicKeyInfo);
        }
        throw new IOException("algorithm identifier " + algorithm + " in key not recognised");
    }
}
