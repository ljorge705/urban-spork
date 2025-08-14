package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWECryptoParts;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.UnprotectedHeader;
import com.nimbusds.jose.crypto.impl.AAD;
import com.nimbusds.jose.crypto.impl.JWEHeaderValidation;
import com.nimbusds.jose.crypto.impl.MultiCryptoProvider;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyType;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONArrayUtils;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.SecretKey;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class MultiEncrypter extends MultiCryptoProvider implements JWEEncrypter {
    private static final String[] RECIPIENT_HEADER_PARAMS = {"kid", "alg", "x5u", "x5t", "x5t#S256", "x5c"};
    private final JWKSet keys;

    public MultiEncrypter(JWKSet jWKSet) throws KeyLengthException {
        this(jWKSet, findDirectCEK(jWKSet));
    }

    public MultiEncrypter(JWKSet jWKSet, SecretKey secretKey) throws KeyLengthException {
        super(secretKey);
        if (jWKSet == null) {
            throw new IllegalArgumentException("The JWK set must not be null");
        }
        for (JWK jwk : jWKSet.getKeys()) {
            KeyType keyType = jwk.getKeyType();
            if (jwk.getAlgorithm() == null) {
                throw new IllegalArgumentException("Each JWK must specify a key encryption algorithm");
            }
            JWEAlgorithm jWEAlgorithm = JWEAlgorithm.parse(jwk.getAlgorithm().toString());
            if (JWEAlgorithm.DIR.equals(jWEAlgorithm) && KeyType.OCT.equals(keyType) && !jwk.toOctetSequenceKey().toSecretKey(CipherStorageKeystoreAesCbc.ALGORITHM_AES).equals(secretKey)) {
                throw new IllegalArgumentException("Bad CEK");
            }
            if (!KeyType.RSA.equals(keyType) || !RSAEncrypter.SUPPORTED_ALGORITHMS.contains(jWEAlgorithm)) {
                if (!KeyType.EC.equals(keyType) || !ECDHEncrypter.SUPPORTED_ALGORITHMS.contains(jWEAlgorithm)) {
                    if (!KeyType.OCT.equals(keyType) || !AESEncrypter.SUPPORTED_ALGORITHMS.contains(jWEAlgorithm)) {
                        if (!KeyType.OCT.equals(keyType) || !DirectEncrypter.SUPPORTED_ALGORITHMS.contains(jWEAlgorithm)) {
                            if (!KeyType.OKP.equals(keyType) || !X25519Encrypter.SUPPORTED_ALGORITHMS.contains(jWEAlgorithm)) {
                                throw new IllegalArgumentException("Unsupported key encryption algorithm: " + jWEAlgorithm);
                            }
                        }
                    }
                }
            }
        }
        this.keys = jWKSet;
    }

    private static SecretKey findDirectCEK(JWKSet jWKSet) {
        if (jWKSet == null) {
            return null;
        }
        for (JWK jwk : jWKSet.getKeys()) {
            if (JWEAlgorithm.DIR.equals(jwk.getAlgorithm()) && KeyType.OCT.equals(jwk.getKeyType())) {
                return jwk.toOctetSequenceKey().toSecretKey(CipherStorageKeystoreAesCbc.ALGORITHM_AES);
            }
        }
        return null;
    }

    @Deprecated
    public JWECryptoParts encrypt(JWEHeader jWEHeader, byte[] bArr) throws JOSEException {
        return encrypt(jWEHeader, bArr, AAD.compute(jWEHeader));
    }

    @Override // com.nimbusds.jose.JWEEncrypter
    public JWECryptoParts encrypt(JWEHeader jWEHeader, byte[] bArr, byte[] bArr2) throws JOSEException {
        Base64URL base64URLEncode;
        JWEEncrypter x25519Encrypter;
        Base64URL base64URL;
        if (bArr2 == null) {
            throw new JOSEException("Missing JWE additional authenticated data (AAD)");
        }
        SecretKey cek = getCEK(jWEHeader.getEncryptionMethod());
        Payload payload = new Payload(bArr);
        List<Object> listNewJSONArray = JSONArrayUtils.newJSONArray();
        Iterator<JWK> it = this.keys.getKeys().iterator();
        Base64URL encryptedKey = null;
        Base64URL base64URL2 = null;
        Base64URL base64URL3 = null;
        Base64URL authenticationTag = null;
        while (it.hasNext()) {
            JWK next = it.next();
            KeyType keyType = next.getKeyType();
            Map<String, Object> jSONObject = next.toJSONObject();
            UnprotectedHeader.Builder builder = new UnprotectedHeader.Builder();
            String[] strArr = RECIPIENT_HEADER_PARAMS;
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                Iterator<JWK> it2 = it;
                String str = strArr[i];
                if (jSONObject.containsKey(str)) {
                    base64URL = encryptedKey;
                    builder.param(str, jSONObject.get(str));
                } else {
                    base64URL = encryptedKey;
                }
                i++;
                it = it2;
                encryptedKey = base64URL;
            }
            Iterator<JWK> it3 = it;
            Base64URL base64URL4 = encryptedKey;
            try {
                JWEHeader jWEHeader2 = (JWEHeader) jWEHeader.join(builder.build());
                JWEAlgorithm algorithmAndEnsureNotNull = JWEHeaderValidation.getAlgorithmAndEnsureNotNull(jWEHeader2);
                if (KeyType.RSA.equals(keyType) && RSAEncrypter.SUPPORTED_ALGORITHMS.contains(algorithmAndEnsureNotNull)) {
                    x25519Encrypter = new RSAEncrypter(next.toRSAKey().toRSAPublicKey(), cek);
                } else if (KeyType.EC.equals(keyType) && ECDHEncrypter.SUPPORTED_ALGORITHMS.contains(algorithmAndEnsureNotNull)) {
                    x25519Encrypter = new ECDHEncrypter(next.toECKey().toECPublicKey(), cek);
                } else if (KeyType.OCT.equals(keyType) && AESEncrypter.SUPPORTED_ALGORITHMS.contains(algorithmAndEnsureNotNull)) {
                    x25519Encrypter = new AESEncrypter(next.toOctetSequenceKey().toSecretKey(CipherStorageKeystoreAesCbc.ALGORITHM_AES), cek);
                } else if (KeyType.OCT.equals(keyType) && DirectEncrypter.SUPPORTED_ALGORITHMS.contains(algorithmAndEnsureNotNull)) {
                    x25519Encrypter = new DirectEncrypter(next.toOctetSequenceKey().toSecretKey(CipherStorageKeystoreAesCbc.ALGORITHM_AES));
                } else if (KeyType.OKP.equals(keyType) && X25519Encrypter.SUPPORTED_ALGORITHMS.contains(algorithmAndEnsureNotNull)) {
                    x25519Encrypter = new X25519Encrypter(next.toOctetKeyPair().toPublicJWK(), cek);
                } else {
                    it = it3;
                    encryptedKey = base64URL4;
                }
                JWECryptoParts jWECryptoPartsEncrypt = x25519Encrypter.encrypt(jWEHeader2, payload.toBytes(), bArr2);
                Map<String, Object> jSONObject2 = jWECryptoPartsEncrypt.getHeader().toJSONObject();
                Iterator<String> it4 = jWEHeader.getIncludedParams().iterator();
                while (it4.hasNext()) {
                    jSONObject2.remove(it4.next());
                }
                Map<String, Object> mapNewJSONObject = JSONObjectUtils.newJSONObject();
                mapNewJSONObject.put("header", jSONObject2);
                if (!JWEAlgorithm.DIR.equals(algorithmAndEnsureNotNull)) {
                    mapNewJSONObject.put("encrypted_key", jWECryptoPartsEncrypt.getEncryptedKey().toString());
                }
                listNewJSONArray.add(mapNewJSONObject);
                if (listNewJSONArray.size() == 1) {
                    payload = new Payload("");
                    encryptedKey = jWECryptoPartsEncrypt.getEncryptedKey();
                    Base64URL initializationVector = jWECryptoPartsEncrypt.getInitializationVector();
                    Base64URL cipherText = jWECryptoPartsEncrypt.getCipherText();
                    authenticationTag = jWECryptoPartsEncrypt.getAuthenticationTag();
                    base64URL2 = initializationVector;
                    base64URL3 = cipherText;
                } else {
                    encryptedKey = base64URL4;
                }
                it = it3;
            } catch (Exception e) {
                throw new JOSEException(e.getMessage(), e);
            }
        }
        Base64URL base64URL5 = encryptedKey;
        if (listNewJSONArray.size() > 1) {
            Map<String, Object> mapNewJSONObject2 = JSONObjectUtils.newJSONObject();
            mapNewJSONObject2.put("recipients", listNewJSONArray);
            base64URLEncode = Base64URL.encode(JSONObjectUtils.toJSONString(mapNewJSONObject2));
        } else {
            base64URLEncode = base64URL5;
        }
        return new JWECryptoParts(jWEHeader, base64URLEncode, base64URL2, base64URL3, authenticationTag);
    }
}
