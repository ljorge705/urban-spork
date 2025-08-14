package org.spongycastle.pqc.jcajce.provider.mceliece;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactorySpi;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;
import org.spongycastle.pqc.asn1.McEliecePrivateKey;
import org.spongycastle.pqc.asn1.McEliecePublicKey;
import org.spongycastle.pqc.asn1.PQCObjectIdentifiers;
import org.spongycastle.pqc.crypto.mceliece.McEliecePrivateKeyParameters;
import org.spongycastle.pqc.crypto.mceliece.McEliecePublicKeyParameters;

/* loaded from: classes7.dex */
public class McElieceKeyFactorySpi extends KeyFactorySpi implements AsymmetricKeyInfoConverter {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.1";

    @Override // java.security.KeyFactorySpi
    protected KeySpec engineGetKeySpec(Key key, Class cls) throws InvalidKeySpecException {
        return null;
    }

    @Override // java.security.KeyFactorySpi
    protected Key engineTranslateKey(Key key) throws InvalidKeyException {
        return null;
    }

    @Override // java.security.KeyFactorySpi
    protected PublicKey engineGeneratePublic(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof X509EncodedKeySpec) {
            try {
                SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(((X509EncodedKeySpec) keySpec).getEncoded()));
                try {
                    if (PQCObjectIdentifiers.mcEliece.equals(subjectPublicKeyInfo.getAlgorithm().getAlgorithm())) {
                        McEliecePublicKey mcEliecePublicKey = McEliecePublicKey.getInstance(subjectPublicKeyInfo.parsePublicKey());
                        return new BCMcEliecePublicKey(new McEliecePublicKeyParameters(mcEliecePublicKey.getN(), mcEliecePublicKey.getT(), mcEliecePublicKey.getG()));
                    }
                    throw new InvalidKeySpecException("Unable to recognise OID in McEliece public key");
                } catch (IOException e) {
                    throw new InvalidKeySpecException("Unable to decode X509EncodedKeySpec: " + e.getMessage());
                }
            } catch (IOException e2) {
                throw new InvalidKeySpecException(e2.toString());
            }
        }
        throw new InvalidKeySpecException("Unsupported key specification: " + keySpec.getClass() + ".");
    }

    @Override // java.security.KeyFactorySpi
    protected PrivateKey engineGeneratePrivate(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof PKCS8EncodedKeySpec) {
            try {
                PrivateKeyInfo privateKeyInfo = PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(((PKCS8EncodedKeySpec) keySpec).getEncoded()));
                try {
                    if (PQCObjectIdentifiers.mcEliece.equals(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm())) {
                        McEliecePrivateKey mcEliecePrivateKey = McEliecePrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
                        return new BCMcEliecePrivateKey(new McEliecePrivateKeyParameters(mcEliecePrivateKey.getN(), mcEliecePrivateKey.getK(), mcEliecePrivateKey.getField(), mcEliecePrivateKey.getGoppaPoly(), mcEliecePrivateKey.getP1(), mcEliecePrivateKey.getP2(), mcEliecePrivateKey.getSInv()));
                    }
                    throw new InvalidKeySpecException("Unable to recognise OID in McEliece private key");
                } catch (IOException unused) {
                    throw new InvalidKeySpecException("Unable to decode PKCS8EncodedKeySpec.");
                }
            } catch (IOException e) {
                throw new InvalidKeySpecException("Unable to decode PKCS8EncodedKeySpec: " + e);
            }
        }
        throw new InvalidKeySpecException("Unsupported key specification: " + keySpec.getClass() + ".");
    }

    public KeySpec getKeySpec(Key key, Class cls) throws InvalidKeySpecException {
        if (key instanceof BCMcEliecePrivateKey) {
            if (PKCS8EncodedKeySpec.class.isAssignableFrom(cls)) {
                return new PKCS8EncodedKeySpec(key.getEncoded());
            }
        } else if (key instanceof BCMcEliecePublicKey) {
            if (X509EncodedKeySpec.class.isAssignableFrom(cls)) {
                return new X509EncodedKeySpec(key.getEncoded());
            }
        } else {
            throw new InvalidKeySpecException("Unsupported key type: " + key.getClass() + ".");
        }
        throw new InvalidKeySpecException("Unknown key specification: " + cls + ".");
    }

    public Key translateKey(Key key) throws InvalidKeyException {
        if ((key instanceof BCMcEliecePrivateKey) || (key instanceof BCMcEliecePublicKey)) {
            return key;
        }
        throw new InvalidKeyException("Unsupported key type.");
    }

    @Override // org.spongycastle.jcajce.provider.util.AsymmetricKeyInfoConverter
    public PublicKey generatePublic(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        McEliecePublicKey mcEliecePublicKey = McEliecePublicKey.getInstance(subjectPublicKeyInfo.parsePublicKey());
        return new BCMcEliecePublicKey(new McEliecePublicKeyParameters(mcEliecePublicKey.getN(), mcEliecePublicKey.getT(), mcEliecePublicKey.getG()));
    }

    @Override // org.spongycastle.jcajce.provider.util.AsymmetricKeyInfoConverter
    public PrivateKey generatePrivate(PrivateKeyInfo privateKeyInfo) throws IOException {
        McEliecePrivateKey mcEliecePrivateKey = McEliecePrivateKey.getInstance(privateKeyInfo.parsePrivateKey().toASN1Primitive());
        return new BCMcEliecePrivateKey(new McEliecePrivateKeyParameters(mcEliecePrivateKey.getN(), mcEliecePrivateKey.getK(), mcEliecePrivateKey.getField(), mcEliecePrivateKey.getGoppaPoly(), mcEliecePrivateKey.getP1(), mcEliecePrivateKey.getP2(), mcEliecePrivateKey.getSInv()));
    }

    private static Digest getDigest(AlgorithmIdentifier algorithmIdentifier) {
        return new SHA256Digest();
    }
}
