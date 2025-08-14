package com.nimbusds.jose.jwk;

import com.nimbusds.jose.JOSEException;
import java.io.StringReader;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

/* loaded from: classes2.dex */
class PEMEncodedKeyParser {
    private static final JcaPEMKeyConverter pemConverter = new JcaPEMKeyConverter();

    private PEMEncodedKeyParser() {
    }

    static List<KeyPair> parseKeys(String str) throws JOSEException {
        Object object;
        PEMParser pEMParser = new PEMParser(new StringReader(str));
        ArrayList arrayList = new ArrayList();
        do {
            try {
                object = pEMParser.readObject();
                if (object instanceof SubjectPublicKeyInfo) {
                    arrayList.add(toKeyPair((SubjectPublicKeyInfo) object));
                } else if (object instanceof X509CertificateHolder) {
                    arrayList.add(toKeyPair((X509CertificateHolder) object));
                } else if (object instanceof PEMKeyPair) {
                    arrayList.add(toKeyPair((PEMKeyPair) object));
                } else if (object instanceof PrivateKeyInfo) {
                    arrayList.add(toKeyPair((PrivateKeyInfo) object));
                }
            } catch (Exception e) {
                throw new JOSEException(e.getMessage(), e);
            }
        } while (object != null);
        return arrayList;
    }

    private static KeyPair toKeyPair(SubjectPublicKeyInfo subjectPublicKeyInfo) throws PEMException {
        return new KeyPair(pemConverter.getPublicKey(subjectPublicKeyInfo), null);
    }

    private static KeyPair toKeyPair(X509CertificateHolder x509CertificateHolder) throws PEMException {
        return new KeyPair(pemConverter.getPublicKey(x509CertificateHolder.getSubjectPublicKeyInfo()), null);
    }

    private static KeyPair toKeyPair(PEMKeyPair pEMKeyPair) throws PEMException {
        return pemConverter.getKeyPair(pEMKeyPair);
    }

    private static KeyPair toKeyPair(PrivateKeyInfo privateKeyInfo) throws InvalidKeySpecException, NoSuchAlgorithmException, PEMException {
        PrivateKey privateKey = pemConverter.getPrivateKey(privateKeyInfo);
        if (privateKey instanceof RSAPrivateCrtKey) {
            RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) privateKey;
            return new KeyPair(KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(rSAPrivateCrtKey.getModulus(), rSAPrivateCrtKey.getPublicExponent())), privateKey);
        }
        return new KeyPair(null, privateKey);
    }
}
