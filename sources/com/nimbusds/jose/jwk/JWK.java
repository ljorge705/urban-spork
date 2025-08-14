package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONArrayUtils;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.nimbusds.jose.util.X509CertChainUtils;
import com.nimbusds.jose.util.X509CertUtils;
import com.nimbusds.jwt.util.DateUtils;
import java.io.Serializable;
import java.net.URI;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.ECParameterSpec;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes2.dex */
public abstract class JWK implements Serializable {
    public static final String MIME_TYPE = "application/jwk+json; charset=UTF-8";
    private static final long serialVersionUID = 1;
    private final Algorithm alg;
    private final Date exp;
    private final Date iat;
    private final KeyStore keyStore;
    private final String kid;
    private final KeyType kty;
    private final Date nbf;
    private final Set<KeyOperation> ops;
    private final List<X509Certificate> parsedX5c;
    private final KeyUse use;
    private final List<Base64> x5c;

    @Deprecated
    private final Base64URL x5t;
    private final Base64URL x5t256;
    private final URI x5u;

    public Algorithm getAlgorithm() {
        return this.alg;
    }

    public Date getExpirationTime() {
        return this.exp;
    }

    public Date getIssueTime() {
        return this.iat;
    }

    public String getKeyID() {
        return this.kid;
    }

    public Set<KeyOperation> getKeyOperations() {
        return this.ops;
    }

    public KeyStore getKeyStore() {
        return this.keyStore;
    }

    public KeyType getKeyType() {
        return this.kty;
    }

    public KeyUse getKeyUse() {
        return this.use;
    }

    public Date getNotBeforeTime() {
        return this.nbf;
    }

    public abstract LinkedHashMap<String, ?> getRequiredParams();

    public Base64URL getX509CertSHA256Thumbprint() {
        return this.x5t256;
    }

    @Deprecated
    public Base64URL getX509CertThumbprint() {
        return this.x5t;
    }

    public URI getX509CertURL() {
        return this.x5u;
    }

    public abstract boolean isPrivate();

    public abstract int size();

    public abstract JWK toPublicJWK();

    @Deprecated
    protected JWK(KeyType keyType, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(keyType, keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, null, null, null, keyStore);
    }

    protected JWK(KeyType keyType, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, Date date, Date date2, Date date3, KeyStore keyStore) {
        if (keyType == null) {
            throw new IllegalArgumentException("The key type \"kty\" parameter must not be null");
        }
        this.kty = keyType;
        if (!KeyUseAndOpsConsistency.areConsistent(keyUse, set)) {
            throw new IllegalArgumentException("The key use \"use\" and key options \"key_ops\" parameters are not consistent, see RFC 7517, section 4.3");
        }
        this.use = keyUse;
        this.ops = set;
        this.alg = algorithm;
        this.kid = str;
        this.x5u = uri;
        this.x5t = base64URL;
        this.x5t256 = base64URL2;
        if (list != null && list.isEmpty()) {
            throw new IllegalArgumentException("The X.509 certificate chain \"x5c\" must not be empty");
        }
        this.x5c = list;
        try {
            this.parsedX5c = X509CertChainUtils.parse(list);
            this.exp = date;
            this.nbf = date2;
            this.iat = date3;
            this.keyStore = keyStore;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid X.509 certificate chain \"x5c\": " + e.getMessage(), e);
        }
    }

    public List<Base64> getX509CertChain() {
        List<Base64> list = this.x5c;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public List<X509Certificate> getParsedX509CertChain() {
        List<X509Certificate> list = this.parsedX5c;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public Base64URL computeThumbprint() throws JOSEException {
        return computeThumbprint("SHA-256");
    }

    public Base64URL computeThumbprint(String str) throws JOSEException {
        return ThumbprintUtils.compute(str, this);
    }

    public ThumbprintURI computeThumbprintURI() throws JOSEException {
        return new ThumbprintURI("sha-256", computeThumbprint("SHA-256"));
    }

    public RSAKey toRSAKey() {
        return (RSAKey) this;
    }

    public ECKey toECKey() {
        return (ECKey) this;
    }

    public OctetSequenceKey toOctetSequenceKey() {
        return (OctetSequenceKey) this;
    }

    public OctetKeyPair toOctetKeyPair() {
        return (OctetKeyPair) this;
    }

    public Map<String, Object> toJSONObject() {
        Map<String, Object> mapNewJSONObject = JSONObjectUtils.newJSONObject();
        mapNewJSONObject.put(JWKParameterNames.KEY_TYPE, this.kty.getValue());
        KeyUse keyUse = this.use;
        if (keyUse != null) {
            mapNewJSONObject.put(JWKParameterNames.PUBLIC_KEY_USE, keyUse.identifier());
        }
        if (this.ops != null) {
            List<Object> listNewJSONArray = JSONArrayUtils.newJSONArray();
            Iterator<KeyOperation> it = this.ops.iterator();
            while (it.hasNext()) {
                listNewJSONArray.add(it.next().identifier());
            }
            mapNewJSONObject.put(JWKParameterNames.KEY_OPS, listNewJSONArray);
        }
        Algorithm algorithm = this.alg;
        if (algorithm != null) {
            mapNewJSONObject.put("alg", algorithm.getName());
        }
        String str = this.kid;
        if (str != null) {
            mapNewJSONObject.put("kid", str);
        }
        URI uri = this.x5u;
        if (uri != null) {
            mapNewJSONObject.put("x5u", uri.toString());
        }
        Base64URL base64URL = this.x5t;
        if (base64URL != null) {
            mapNewJSONObject.put("x5t", base64URL.toString());
        }
        Base64URL base64URL2 = this.x5t256;
        if (base64URL2 != null) {
            mapNewJSONObject.put("x5t#S256", base64URL2.toString());
        }
        if (this.x5c != null) {
            List<Object> listNewJSONArray2 = JSONArrayUtils.newJSONArray();
            Iterator<Base64> it2 = this.x5c.iterator();
            while (it2.hasNext()) {
                listNewJSONArray2.add(it2.next().toString());
            }
            mapNewJSONObject.put("x5c", listNewJSONArray2);
        }
        Date date = this.exp;
        if (date != null) {
            mapNewJSONObject.put("exp", Long.valueOf(DateUtils.toSecondsSinceEpoch(date)));
        }
        Date date2 = this.nbf;
        if (date2 != null) {
            mapNewJSONObject.put("nbf", Long.valueOf(DateUtils.toSecondsSinceEpoch(date2)));
        }
        Date date3 = this.iat;
        if (date3 != null) {
            mapNewJSONObject.put("iat", Long.valueOf(DateUtils.toSecondsSinceEpoch(date3)));
        }
        return mapNewJSONObject;
    }

    public String toJSONString() {
        return JSONObjectUtils.toJSONString(toJSONObject());
    }

    public String toString() {
        return JSONObjectUtils.toJSONString(toJSONObject());
    }

    public static JWK parse(String str) throws ParseException {
        return parse(JSONObjectUtils.parse(str));
    }

    public static JWK parse(Map<String, Object> map) throws ParseException {
        String string = JSONObjectUtils.getString(map, JWKParameterNames.KEY_TYPE);
        if (string == null) {
            throw new ParseException("Missing key type \"kty\" parameter", 0);
        }
        KeyType keyType = KeyType.parse(string);
        if (keyType == KeyType.EC) {
            return ECKey.parse(map);
        }
        if (keyType == KeyType.RSA) {
            return RSAKey.parse(map);
        }
        if (keyType == KeyType.OCT) {
            return OctetSequenceKey.parse(map);
        }
        if (keyType == KeyType.OKP) {
            return OctetKeyPair.parse(map);
        }
        throw new ParseException("Unsupported key type \"kty\" parameter: " + keyType, 0);
    }

    public static JWK parse(X509Certificate x509Certificate) throws JOSEException {
        if (x509Certificate.getPublicKey() instanceof RSAPublicKey) {
            return RSAKey.parse(x509Certificate);
        }
        if (x509Certificate.getPublicKey() instanceof ECPublicKey) {
            return ECKey.parse(x509Certificate);
        }
        throw new JOSEException("Unsupported public key algorithm: " + x509Certificate.getPublicKey().getAlgorithm());
    }

    public static JWK parseFromPEMEncodedX509Cert(String str) throws JOSEException {
        X509Certificate x509Certificate = X509CertUtils.parse(str);
        if (x509Certificate == null) {
            throw new JOSEException("Couldn't parse PEM-encoded X.509 certificate");
        }
        return parse(x509Certificate);
    }

    public static JWK load(KeyStore keyStore, String str, char[] cArr) throws JOSEException, KeyStoreException {
        Certificate certificate = keyStore.getCertificate(str);
        if (certificate == null) {
            return OctetSequenceKey.load(keyStore, str, cArr);
        }
        if (certificate.getPublicKey() instanceof RSAPublicKey) {
            return RSAKey.load(keyStore, str, cArr);
        }
        if (certificate.getPublicKey() instanceof ECPublicKey) {
            return ECKey.load(keyStore, str, cArr);
        }
        throw new JOSEException("Unsupported public key algorithm: " + certificate.getPublicKey().getAlgorithm());
    }

    public static JWK parseFromPEMEncodedObjects(String str) throws JOSEException {
        List<KeyPair> keys = PEMEncodedKeyParser.parseKeys(str);
        if (keys.isEmpty()) {
            throw new JOSEException("No PEM-encoded keys found");
        }
        KeyPair keyPairMergeKeyPairs = mergeKeyPairs(keys);
        PublicKey publicKey = keyPairMergeKeyPairs.getPublic();
        PrivateKey privateKey = keyPairMergeKeyPairs.getPrivate();
        if (publicKey == null) {
            throw new JOSEException("Missing PEM-encoded public key to construct JWK");
        }
        if (publicKey instanceof ECPublicKey) {
            ECPublicKey eCPublicKey = (ECPublicKey) publicKey;
            ECParameterSpec params = eCPublicKey.getParams();
            boolean z = privateKey instanceof ECPrivateKey;
            if (z) {
                validateEcCurves(eCPublicKey, (ECPrivateKey) privateKey);
            }
            if (privateKey != null && !z) {
                throw new JOSEException("Unsupported " + KeyType.EC.getValue() + " private key type: " + privateKey);
            }
            ECKey.Builder builder = new ECKey.Builder(Curve.forECParameterSpec(params), eCPublicKey);
            if (privateKey != null) {
                builder.privateKey((ECPrivateKey) privateKey);
            }
            return builder.build();
        }
        if (publicKey instanceof RSAPublicKey) {
            RSAKey.Builder builder2 = new RSAKey.Builder((RSAPublicKey) publicKey);
            if (privateKey instanceof RSAPrivateKey) {
                builder2.privateKey((RSAPrivateKey) privateKey);
            } else if (privateKey != null) {
                throw new JOSEException("Unsupported " + KeyType.RSA.getValue() + " private key type: " + privateKey);
            }
            return builder2.build();
        }
        throw new JOSEException("Unsupported algorithm of PEM-encoded key: " + publicKey.getAlgorithm());
    }

    private static void validateEcCurves(ECPublicKey eCPublicKey, ECPrivateKey eCPrivateKey) throws JOSEException {
        ECParameterSpec params = eCPublicKey.getParams();
        ECParameterSpec params2 = eCPrivateKey.getParams();
        if (!params.getCurve().equals(params2.getCurve())) {
            throw new JOSEException("Public/private " + KeyType.EC.getValue() + " key curve mismatch: " + eCPublicKey);
        }
        if (params.getCofactor() != params2.getCofactor()) {
            throw new JOSEException("Public/private " + KeyType.EC.getValue() + " key cofactor mismatch: " + eCPublicKey);
        }
        if (!params.getGenerator().equals(params2.getGenerator())) {
            throw new JOSEException("Public/private " + KeyType.EC.getValue() + " key generator mismatch: " + eCPublicKey);
        }
        if (!params.getOrder().equals(params2.getOrder())) {
            throw new JOSEException("Public/private " + KeyType.EC.getValue() + " key order mismatch: " + eCPublicKey);
        }
    }

    private static KeyPair mergeKeyPairs(List<KeyPair> list) throws JOSEException {
        if (list.size() == 1) {
            return list.get(0);
        }
        if (list.size() == 2) {
            return twoKeysToKeyPair(list);
        }
        throw new JOSEException("Expected key or pair of PEM-encoded keys");
    }

    private static KeyPair twoKeysToKeyPair(List<? extends KeyPair> list) throws JOSEException {
        KeyPair keyPair = list.get(0);
        KeyPair keyPair2 = list.get(1);
        if (keyPair.getPublic() != null && keyPair2.getPrivate() != null) {
            return new KeyPair(keyPair.getPublic(), keyPair2.getPrivate());
        }
        if (keyPair.getPrivate() != null && keyPair2.getPublic() != null) {
            return new KeyPair(keyPair2.getPublic(), keyPair.getPrivate());
        }
        throw new JOSEException("Not a public/private key pair");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof JWK)) {
            return false;
        }
        JWK jwk = (JWK) obj;
        return Objects.equals(this.kty, jwk.kty) && Objects.equals(this.use, jwk.use) && Objects.equals(this.ops, jwk.ops) && Objects.equals(this.alg, jwk.alg) && Objects.equals(this.kid, jwk.kid) && Objects.equals(this.x5u, jwk.x5u) && Objects.equals(this.x5t, jwk.x5t) && Objects.equals(this.x5t256, jwk.x5t256) && Objects.equals(this.x5c, jwk.x5c) && Objects.equals(this.exp, jwk.exp) && Objects.equals(this.nbf, jwk.nbf) && Objects.equals(this.iat, jwk.iat) && Objects.equals(this.keyStore, jwk.keyStore);
    }

    public int hashCode() {
        return Objects.hash(this.kty, this.use, this.ops, this.alg, this.kid, this.x5u, this.x5t, this.x5t256, this.x5c, this.exp, this.nbf, this.iat, this.keyStore);
    }
}
