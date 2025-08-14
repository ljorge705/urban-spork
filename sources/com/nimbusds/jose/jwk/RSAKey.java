package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.ByteUtils;
import com.nimbusds.jose.util.IntegerOverflowException;
import com.nimbusds.jose.util.JSONArrayUtils;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.io.Serializable;
import java.math.BigInteger;
import java.net.URI;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.RSAMultiPrimePrivateCrtKeySpec;
import java.security.spec.RSAOtherPrimeInfo;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes2.dex */
public final class RSAKey extends JWK implements AsymmetricJWK {
    private static final long serialVersionUID = 1;
    private final Base64URL d;
    private final Base64URL dp;
    private final Base64URL dq;
    private final Base64URL e;

    /* renamed from: n, reason: collision with root package name */
    private final Base64URL f65n;
    private final List<OtherPrimesInfo> oth;
    private final Base64URL p;
    private final PrivateKey privateKey;
    private final Base64URL q;
    private final Base64URL qi;

    public Base64URL getFirstCRTCoefficient() {
        return this.qi;
    }

    public Base64URL getFirstFactorCRTExponent() {
        return this.dp;
    }

    public Base64URL getFirstPrimeFactor() {
        return this.p;
    }

    public Base64URL getModulus() {
        return this.f65n;
    }

    public List<OtherPrimesInfo> getOtherPrimes() {
        return this.oth;
    }

    public Base64URL getPrivateExponent() {
        return this.d;
    }

    public Base64URL getPublicExponent() {
        return this.e;
    }

    public Base64URL getSecondFactorCRTExponent() {
        return this.dq;
    }

    public Base64URL getSecondPrimeFactor() {
        return this.q;
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public boolean isPrivate() {
        return (this.d == null && this.p == null && this.privateKey == null) ? false : true;
    }

    @Immutable
    public static class OtherPrimesInfo implements Serializable {
        private static final long serialVersionUID = 1;
        private final Base64URL d;
        private final Base64URL r;
        private final Base64URL t;

        public Base64URL getFactorCRTCoefficient() {
            return this.t;
        }

        public Base64URL getFactorCRTExponent() {
            return this.d;
        }

        public Base64URL getPrimeFactor() {
            return this.r;
        }

        public OtherPrimesInfo(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3) {
            if (base64URL == null) {
                throw new IllegalArgumentException("The prime factor must not be null");
            }
            this.r = base64URL;
            if (base64URL2 == null) {
                throw new IllegalArgumentException("The factor CRT exponent must not be null");
            }
            this.d = base64URL2;
            if (base64URL3 == null) {
                throw new IllegalArgumentException("The factor CRT coefficient must not be null");
            }
            this.t = base64URL3;
        }

        public OtherPrimesInfo(RSAOtherPrimeInfo rSAOtherPrimeInfo) {
            this.r = Base64URL.encode(rSAOtherPrimeInfo.getPrime());
            this.d = Base64URL.encode(rSAOtherPrimeInfo.getExponent());
            this.t = Base64URL.encode(rSAOtherPrimeInfo.getCrtCoefficient());
        }

        public static List<OtherPrimesInfo> toList(RSAOtherPrimeInfo[] rSAOtherPrimeInfoArr) {
            ArrayList arrayList = new ArrayList();
            if (rSAOtherPrimeInfoArr == null) {
                return arrayList;
            }
            for (RSAOtherPrimeInfo rSAOtherPrimeInfo : rSAOtherPrimeInfoArr) {
                arrayList.add(new OtherPrimesInfo(rSAOtherPrimeInfo));
            }
            return arrayList;
        }
    }

    public static class Builder {
        private Algorithm alg;
        private Base64URL d;
        private Base64URL dp;
        private Base64URL dq;
        private final Base64URL e;
        private Date exp;
        private Date iat;
        private String kid;
        private KeyStore ks;

        /* renamed from: n, reason: collision with root package name */
        private final Base64URL f66n;
        private Date nbf;
        private Set<KeyOperation> ops;
        private List<OtherPrimesInfo> oth;
        private Base64URL p;
        private PrivateKey priv;
        private Base64URL q;
        private Base64URL qi;
        private KeyUse use;
        private List<Base64> x5c;

        @Deprecated
        private Base64URL x5t;
        private Base64URL x5t256;
        private URI x5u;

        public Builder algorithm(Algorithm algorithm) {
            this.alg = algorithm;
            return this;
        }

        public Builder expirationTime(Date date) {
            this.exp = date;
            return this;
        }

        public Builder firstCRTCoefficient(Base64URL base64URL) {
            this.qi = base64URL;
            return this;
        }

        public Builder firstFactorCRTExponent(Base64URL base64URL) {
            this.dp = base64URL;
            return this;
        }

        public Builder firstPrimeFactor(Base64URL base64URL) {
            this.p = base64URL;
            return this;
        }

        public Builder issueTime(Date date) {
            this.iat = date;
            return this;
        }

        public Builder keyID(String str) {
            this.kid = str;
            return this;
        }

        public Builder keyOperations(Set<KeyOperation> set) {
            this.ops = set;
            return this;
        }

        public Builder keyStore(KeyStore keyStore) {
            this.ks = keyStore;
            return this;
        }

        public Builder keyUse(KeyUse keyUse) {
            this.use = keyUse;
            return this;
        }

        public Builder notBeforeTime(Date date) {
            this.nbf = date;
            return this;
        }

        public Builder otherPrimes(List<OtherPrimesInfo> list) {
            this.oth = list;
            return this;
        }

        public Builder privateExponent(Base64URL base64URL) {
            this.d = base64URL;
            return this;
        }

        public Builder secondFactorCRTExponent(Base64URL base64URL) {
            this.dq = base64URL;
            return this;
        }

        public Builder secondPrimeFactor(Base64URL base64URL) {
            this.q = base64URL;
            return this;
        }

        public Builder x509CertChain(List<Base64> list) {
            this.x5c = list;
            return this;
        }

        public Builder x509CertSHA256Thumbprint(Base64URL base64URL) {
            this.x5t256 = base64URL;
            return this;
        }

        @Deprecated
        public Builder x509CertThumbprint(Base64URL base64URL) {
            this.x5t = base64URL;
            return this;
        }

        public Builder x509CertURL(URI uri) {
            this.x5u = uri;
            return this;
        }

        public Builder(Base64URL base64URL, Base64URL base64URL2) {
            if (base64URL == null) {
                throw new IllegalArgumentException("The modulus value must not be null");
            }
            this.f66n = base64URL;
            if (base64URL2 == null) {
                throw new IllegalArgumentException("The public exponent value must not be null");
            }
            this.e = base64URL2;
        }

        public Builder(RSAPublicKey rSAPublicKey) {
            this.f66n = Base64URL.encode(rSAPublicKey.getModulus());
            this.e = Base64URL.encode(rSAPublicKey.getPublicExponent());
        }

        public Builder(RSAKey rSAKey) {
            this.f66n = rSAKey.f65n;
            this.e = rSAKey.e;
            this.d = rSAKey.d;
            this.p = rSAKey.p;
            this.q = rSAKey.q;
            this.dp = rSAKey.dp;
            this.dq = rSAKey.dq;
            this.qi = rSAKey.qi;
            this.oth = rSAKey.oth;
            this.priv = rSAKey.privateKey;
            this.use = rSAKey.getKeyUse();
            this.ops = rSAKey.getKeyOperations();
            this.alg = rSAKey.getAlgorithm();
            this.kid = rSAKey.getKeyID();
            this.x5u = rSAKey.getX509CertURL();
            this.x5t = rSAKey.getX509CertThumbprint();
            this.x5t256 = rSAKey.getX509CertSHA256Thumbprint();
            this.x5c = rSAKey.getX509CertChain();
            this.exp = rSAKey.getExpirationTime();
            this.nbf = rSAKey.getNotBeforeTime();
            this.iat = rSAKey.getIssueTime();
            this.ks = rSAKey.getKeyStore();
        }

        public Builder privateKey(RSAPrivateKey rSAPrivateKey) {
            if (rSAPrivateKey instanceof RSAPrivateCrtKey) {
                return privateKey((RSAPrivateCrtKey) rSAPrivateKey);
            }
            if (rSAPrivateKey instanceof RSAMultiPrimePrivateCrtKey) {
                return privateKey((RSAMultiPrimePrivateCrtKey) rSAPrivateKey);
            }
            this.d = Base64URL.encode(rSAPrivateKey.getPrivateExponent());
            return this;
        }

        public Builder privateKey(PrivateKey privateKey) {
            if (privateKey instanceof RSAPrivateKey) {
                return privateKey((RSAPrivateKey) privateKey);
            }
            if (!"RSA".equalsIgnoreCase(privateKey.getAlgorithm())) {
                throw new IllegalArgumentException("The private key algorithm must be RSA");
            }
            this.priv = privateKey;
            return this;
        }

        public Builder privateKey(RSAPrivateCrtKey rSAPrivateCrtKey) {
            this.d = Base64URL.encode(rSAPrivateCrtKey.getPrivateExponent());
            this.p = Base64URL.encode(rSAPrivateCrtKey.getPrimeP());
            this.q = Base64URL.encode(rSAPrivateCrtKey.getPrimeQ());
            this.dp = Base64URL.encode(rSAPrivateCrtKey.getPrimeExponentP());
            this.dq = Base64URL.encode(rSAPrivateCrtKey.getPrimeExponentQ());
            this.qi = Base64URL.encode(rSAPrivateCrtKey.getCrtCoefficient());
            return this;
        }

        public Builder privateKey(RSAMultiPrimePrivateCrtKey rSAMultiPrimePrivateCrtKey) {
            this.d = Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrivateExponent());
            this.p = Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrimeP());
            this.q = Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrimeQ());
            this.dp = Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrimeExponentP());
            this.dq = Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrimeExponentQ());
            this.qi = Base64URL.encode(rSAMultiPrimePrivateCrtKey.getCrtCoefficient());
            this.oth = OtherPrimesInfo.toList(rSAMultiPrimePrivateCrtKey.getOtherPrimeInfo());
            return this;
        }

        public Builder keyIDFromThumbprint() throws JOSEException {
            return keyIDFromThumbprint("SHA-256");
        }

        public Builder keyIDFromThumbprint(String str) throws JOSEException {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("e", this.e.toString());
            linkedHashMap.put(JWKParameterNames.KEY_TYPE, KeyType.RSA.getValue());
            linkedHashMap.put("n", this.f66n.toString());
            this.kid = ThumbprintUtils.compute(str, (LinkedHashMap<String, ?>) linkedHashMap).toString();
            return this;
        }

        public RSAKey build() {
            try {
                return new RSAKey(this.f66n, this.e, this.d, this.p, this.q, this.dp, this.dq, this.qi, this.oth, this.priv, this.use, this.ops, this.alg, this.kid, this.x5u, this.x5t, this.x5t256, this.x5c, this.exp, this.nbf, this.iat, this.ks);
            } catch (IllegalArgumentException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }
    }

    @Deprecated
    public RSAKey(Base64URL base64URL, Base64URL base64URL2, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL3, Base64URL base64URL4, List<Base64> list, KeyStore keyStore) {
        this(base64URL, base64URL2, null, null, null, null, null, null, null, null, keyUse, set, algorithm, str, uri, base64URL3, base64URL4, list, null, null, null, keyStore);
    }

    public RSAKey(Base64URL base64URL, Base64URL base64URL2, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL3, Base64URL base64URL4, List<Base64> list, Date date, Date date2, Date date3, KeyStore keyStore) {
        this(base64URL, base64URL2, null, null, null, null, null, null, null, null, keyUse, set, algorithm, str, uri, base64URL3, base64URL4, list, date, date2, date3, keyStore);
    }

    @Deprecated
    public RSAKey(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL4, Base64URL base64URL5, List<Base64> list, KeyStore keyStore) {
        this(base64URL, base64URL2, base64URL3, null, null, null, null, null, null, null, keyUse, set, algorithm, str, uri, base64URL4, base64URL5, list, null, null, null, keyStore);
    }

    public RSAKey(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL4, Base64URL base64URL5, List<Base64> list, Date date, Date date2, Date date3, KeyStore keyStore) {
        this(base64URL, base64URL2, base64URL3, null, null, null, null, null, null, null, keyUse, set, algorithm, str, uri, base64URL4, base64URL5, list, date, date2, date3, keyStore);
        if (base64URL3 == null) {
            throw new IllegalArgumentException("The private exponent must not be null");
        }
    }

    @Deprecated
    public RSAKey(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, Base64URL base64URL5, Base64URL base64URL6, Base64URL base64URL7, List<OtherPrimesInfo> list, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL8, Base64URL base64URL9, List<Base64> list2, KeyStore keyStore) {
        this(base64URL, base64URL2, null, base64URL3, base64URL4, base64URL5, base64URL6, base64URL7, list, null, keyUse, set, algorithm, str, uri, base64URL8, base64URL9, list2, null, null, null, keyStore);
    }

    public RSAKey(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, Base64URL base64URL5, Base64URL base64URL6, Base64URL base64URL7, List<OtherPrimesInfo> list, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL8, Base64URL base64URL9, List<Base64> list2, Date date, Date date2, Date date3, KeyStore keyStore) {
        this(base64URL, base64URL2, null, base64URL3, base64URL4, base64URL5, base64URL6, base64URL7, list, null, keyUse, set, algorithm, str, uri, base64URL8, base64URL9, list2, date, date2, date3, keyStore);
        if (base64URL3 == null) {
            throw new IllegalArgumentException("The first prime factor must not be null");
        }
        if (base64URL4 == null) {
            throw new IllegalArgumentException("The second prime factor must not be null");
        }
        if (base64URL5 == null) {
            throw new IllegalArgumentException("The first factor CRT exponent must not be null");
        }
        if (base64URL6 == null) {
            throw new IllegalArgumentException("The second factor CRT exponent must not be null");
        }
        if (base64URL7 == null) {
            throw new IllegalArgumentException("The first CRT coefficient must not be null");
        }
    }

    @Deprecated
    public RSAKey(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, Base64URL base64URL5, Base64URL base64URL6, Base64URL base64URL7, Base64URL base64URL8, List<OtherPrimesInfo> list, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL9, Base64URL base64URL10, List<Base64> list2) {
        this(base64URL, base64URL2, base64URL3, base64URL4, base64URL5, base64URL6, base64URL7, base64URL8, list, null, keyUse, set, algorithm, str, uri, base64URL9, base64URL10, list2, null);
    }

    @Deprecated
    public RSAKey(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, Base64URL base64URL5, Base64URL base64URL6, Base64URL base64URL7, Base64URL base64URL8, List<OtherPrimesInfo> list, PrivateKey privateKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL9, Base64URL base64URL10, List<Base64> list2, KeyStore keyStore) {
        this(base64URL, base64URL2, base64URL3, base64URL4, base64URL5, base64URL6, base64URL7, base64URL8, list, privateKey, keyUse, set, algorithm, str, uri, base64URL9, base64URL10, list2, null, null, null, keyStore);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00f6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public RSAKey(com.nimbusds.jose.util.Base64URL r17, com.nimbusds.jose.util.Base64URL r18, com.nimbusds.jose.util.Base64URL r19, com.nimbusds.jose.util.Base64URL r20, com.nimbusds.jose.util.Base64URL r21, com.nimbusds.jose.util.Base64URL r22, com.nimbusds.jose.util.Base64URL r23, com.nimbusds.jose.util.Base64URL r24, java.util.List<com.nimbusds.jose.jwk.RSAKey.OtherPrimesInfo> r25, java.security.PrivateKey r26, com.nimbusds.jose.jwk.KeyUse r27, java.util.Set<com.nimbusds.jose.jwk.KeyOperation> r28, com.nimbusds.jose.Algorithm r29, java.lang.String r30, java.net.URI r31, com.nimbusds.jose.util.Base64URL r32, com.nimbusds.jose.util.Base64URL r33, java.util.List<com.nimbusds.jose.util.Base64> r34, java.util.Date r35, java.util.Date r36, java.util.Date r37, java.security.KeyStore r38) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nimbusds.jose.jwk.RSAKey.<init>(com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, java.util.List, java.security.PrivateKey, com.nimbusds.jose.jwk.KeyUse, java.util.Set, com.nimbusds.jose.Algorithm, java.lang.String, java.net.URI, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, java.util.List, java.util.Date, java.util.Date, java.util.Date, java.security.KeyStore):void");
    }

    @Deprecated
    public RSAKey(RSAPublicKey rSAPublicKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(rSAPublicKey, keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, null, null, null, keyStore);
    }

    public RSAKey(RSAPublicKey rSAPublicKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, Date date, Date date2, Date date3, KeyStore keyStore) {
        this(Base64URL.encode(rSAPublicKey.getModulus()), Base64URL.encode(rSAPublicKey.getPublicExponent()), keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, date, date2, date3, keyStore);
    }

    @Deprecated
    public RSAKey(RSAPublicKey rSAPublicKey, RSAPrivateKey rSAPrivateKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(rSAPublicKey, rSAPrivateKey, keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, (Date) null, (Date) null, (Date) null, keyStore);
    }

    public RSAKey(RSAPublicKey rSAPublicKey, RSAPrivateKey rSAPrivateKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, Date date, Date date2, Date date3, KeyStore keyStore) {
        this(Base64URL.encode(rSAPublicKey.getModulus()), Base64URL.encode(rSAPublicKey.getPublicExponent()), Base64URL.encode(rSAPrivateKey.getPrivateExponent()), keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, date, date2, date3, keyStore);
    }

    @Deprecated
    public RSAKey(RSAPublicKey rSAPublicKey, RSAPrivateCrtKey rSAPrivateCrtKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(rSAPublicKey, rSAPrivateCrtKey, keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, (Date) null, (Date) null, (Date) null, keyStore);
    }

    public RSAKey(RSAPublicKey rSAPublicKey, RSAPrivateCrtKey rSAPrivateCrtKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, Date date, Date date2, Date date3, KeyStore keyStore) {
        this(Base64URL.encode(rSAPublicKey.getModulus()), Base64URL.encode(rSAPublicKey.getPublicExponent()), Base64URL.encode(rSAPrivateCrtKey.getPrivateExponent()), Base64URL.encode(rSAPrivateCrtKey.getPrimeP()), Base64URL.encode(rSAPrivateCrtKey.getPrimeQ()), Base64URL.encode(rSAPrivateCrtKey.getPrimeExponentP()), Base64URL.encode(rSAPrivateCrtKey.getPrimeExponentQ()), Base64URL.encode(rSAPrivateCrtKey.getCrtCoefficient()), null, null, keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, date, date2, date3, keyStore);
    }

    @Deprecated
    public RSAKey(RSAPublicKey rSAPublicKey, RSAMultiPrimePrivateCrtKey rSAMultiPrimePrivateCrtKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(rSAPublicKey, rSAMultiPrimePrivateCrtKey, keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, (Date) null, (Date) null, (Date) null, keyStore);
    }

    public RSAKey(RSAPublicKey rSAPublicKey, RSAMultiPrimePrivateCrtKey rSAMultiPrimePrivateCrtKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, Date date, Date date2, Date date3, KeyStore keyStore) {
        this(Base64URL.encode(rSAPublicKey.getModulus()), Base64URL.encode(rSAPublicKey.getPublicExponent()), Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrivateExponent()), Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrimeP()), Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrimeQ()), Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrimeExponentP()), Base64URL.encode(rSAMultiPrimePrivateCrtKey.getPrimeExponentQ()), Base64URL.encode(rSAMultiPrimePrivateCrtKey.getCrtCoefficient()), OtherPrimesInfo.toList(rSAMultiPrimePrivateCrtKey.getOtherPrimeInfo()), null, keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, date, date2, date3, keyStore);
    }

    @Deprecated
    public RSAKey(RSAPublicKey rSAPublicKey, PrivateKey privateKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, KeyStore keyStore) {
        this(rSAPublicKey, privateKey, keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, (Date) null, (Date) null, (Date) null, keyStore);
    }

    public RSAKey(RSAPublicKey rSAPublicKey, PrivateKey privateKey, KeyUse keyUse, Set<KeyOperation> set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List<Base64> list, Date date, Date date2, Date date3, KeyStore keyStore) {
        this(Base64URL.encode(rSAPublicKey.getModulus()), Base64URL.encode(rSAPublicKey.getPublicExponent()), null, null, null, null, null, null, null, privateKey, keyUse, set, algorithm, str, uri, base64URL, base64URL2, list, date, date2, date3, keyStore);
    }

    public RSAPublicKey toRSAPublicKey() throws JOSEException {
        try {
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(this.f65n.decodeToBigInteger(), this.e.decodeToBigInteger()));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new JOSEException(e.getMessage(), e);
        }
    }

    public RSAPrivateKey toRSAPrivateKey() throws JOSEException {
        KeySpec rSAPrivateCrtKeySpec;
        if (this.d == null) {
            return null;
        }
        BigInteger bigIntegerDecodeToBigInteger = this.f65n.decodeToBigInteger();
        BigInteger bigIntegerDecodeToBigInteger2 = this.d.decodeToBigInteger();
        if (this.p == null) {
            rSAPrivateCrtKeySpec = new RSAPrivateKeySpec(bigIntegerDecodeToBigInteger, bigIntegerDecodeToBigInteger2);
        } else {
            BigInteger bigIntegerDecodeToBigInteger3 = this.e.decodeToBigInteger();
            BigInteger bigIntegerDecodeToBigInteger4 = this.p.decodeToBigInteger();
            BigInteger bigIntegerDecodeToBigInteger5 = this.q.decodeToBigInteger();
            BigInteger bigIntegerDecodeToBigInteger6 = this.dp.decodeToBigInteger();
            BigInteger bigIntegerDecodeToBigInteger7 = this.dq.decodeToBigInteger();
            BigInteger bigIntegerDecodeToBigInteger8 = this.qi.decodeToBigInteger();
            List<OtherPrimesInfo> list = this.oth;
            if (list != null && !list.isEmpty()) {
                RSAOtherPrimeInfo[] rSAOtherPrimeInfoArr = new RSAOtherPrimeInfo[this.oth.size()];
                for (int i = 0; i < this.oth.size(); i++) {
                    OtherPrimesInfo otherPrimesInfo = this.oth.get(i);
                    rSAOtherPrimeInfoArr[i] = new RSAOtherPrimeInfo(otherPrimesInfo.getPrimeFactor().decodeToBigInteger(), otherPrimesInfo.getFactorCRTExponent().decodeToBigInteger(), otherPrimesInfo.getFactorCRTCoefficient().decodeToBigInteger());
                }
                rSAPrivateCrtKeySpec = new RSAMultiPrimePrivateCrtKeySpec(bigIntegerDecodeToBigInteger, bigIntegerDecodeToBigInteger3, bigIntegerDecodeToBigInteger2, bigIntegerDecodeToBigInteger4, bigIntegerDecodeToBigInteger5, bigIntegerDecodeToBigInteger6, bigIntegerDecodeToBigInteger7, bigIntegerDecodeToBigInteger8, rSAOtherPrimeInfoArr);
            } else {
                rSAPrivateCrtKeySpec = new RSAPrivateCrtKeySpec(bigIntegerDecodeToBigInteger, bigIntegerDecodeToBigInteger3, bigIntegerDecodeToBigInteger2, bigIntegerDecodeToBigInteger4, bigIntegerDecodeToBigInteger5, bigIntegerDecodeToBigInteger6, bigIntegerDecodeToBigInteger7, bigIntegerDecodeToBigInteger8);
            }
        }
        try {
            return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(rSAPrivateCrtKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new JOSEException(e.getMessage(), e);
        }
    }

    @Override // com.nimbusds.jose.jwk.AsymmetricJWK
    public PublicKey toPublicKey() throws JOSEException {
        return toRSAPublicKey();
    }

    @Override // com.nimbusds.jose.jwk.AsymmetricJWK
    public PrivateKey toPrivateKey() throws JOSEException {
        RSAPrivateKey rSAPrivateKey = toRSAPrivateKey();
        return rSAPrivateKey != null ? rSAPrivateKey : this.privateKey;
    }

    @Override // com.nimbusds.jose.jwk.AsymmetricJWK
    public KeyPair toKeyPair() throws JOSEException {
        return new KeyPair(toRSAPublicKey(), toPrivateKey());
    }

    @Override // com.nimbusds.jose.jwk.AsymmetricJWK
    public boolean matches(X509Certificate x509Certificate) {
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) getParsedX509CertChain().get(0).getPublicKey();
            if (this.e.decodeToBigInteger().equals(rSAPublicKey.getPublicExponent())) {
                return this.f65n.decodeToBigInteger().equals(rSAPublicKey.getModulus());
            }
            return false;
        } catch (ClassCastException unused) {
            return false;
        }
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public LinkedHashMap<String, ?> getRequiredParams() {
        LinkedHashMap<String, ?> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("e", this.e.toString());
        linkedHashMap.put(JWKParameterNames.KEY_TYPE, getKeyType().getValue());
        linkedHashMap.put("n", this.f65n.toString());
        return linkedHashMap;
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public int size() {
        try {
            return ByteUtils.safeBitLength(this.f65n.decode());
        } catch (IntegerOverflowException e) {
            throw new ArithmeticException(e.getMessage());
        }
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public RSAKey toPublicJWK() {
        return new RSAKey(getModulus(), getPublicExponent(), getKeyUse(), getKeyOperations(), getAlgorithm(), getKeyID(), getX509CertURL(), getX509CertThumbprint(), getX509CertSHA256Thumbprint(), getX509CertChain(), getExpirationTime(), getNotBeforeTime(), getIssueTime(), getKeyStore());
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public Map<String, Object> toJSONObject() {
        Map<String, Object> jSONObject = super.toJSONObject();
        jSONObject.put("n", this.f65n.toString());
        jSONObject.put("e", this.e.toString());
        Base64URL base64URL = this.d;
        if (base64URL != null) {
            jSONObject.put("d", base64URL.toString());
        }
        Base64URL base64URL2 = this.p;
        if (base64URL2 != null) {
            jSONObject.put("p", base64URL2.toString());
        }
        Base64URL base64URL3 = this.q;
        if (base64URL3 != null) {
            jSONObject.put(JWKParameterNames.RSA_SECOND_PRIME_FACTOR, base64URL3.toString());
        }
        Base64URL base64URL4 = this.dp;
        if (base64URL4 != null) {
            jSONObject.put(JWKParameterNames.RSA_FIRST_FACTOR_CRT_EXPONENT, base64URL4.toString());
        }
        Base64URL base64URL5 = this.dq;
        if (base64URL5 != null) {
            jSONObject.put(JWKParameterNames.RSA_SECOND_FACTOR_CRT_EXPONENT, base64URL5.toString());
        }
        Base64URL base64URL6 = this.qi;
        if (base64URL6 != null) {
            jSONObject.put(JWKParameterNames.RSA_FIRST_CRT_COEFFICIENT, base64URL6.toString());
        }
        List<OtherPrimesInfo> list = this.oth;
        if (list != null && !list.isEmpty()) {
            List<Object> listNewJSONArray = JSONArrayUtils.newJSONArray();
            for (OtherPrimesInfo otherPrimesInfo : this.oth) {
                Map<String, Object> mapNewJSONObject = JSONObjectUtils.newJSONObject();
                mapNewJSONObject.put("r", otherPrimesInfo.r.toString());
                mapNewJSONObject.put("d", otherPrimesInfo.d.toString());
                mapNewJSONObject.put("t", otherPrimesInfo.t.toString());
                listNewJSONArray.add(mapNewJSONObject);
            }
            jSONObject.put(JWKParameterNames.RSA_OTHER_PRIMES, listNewJSONArray);
        }
        return jSONObject;
    }

    public static RSAKey parse(String str) throws ParseException {
        return parse(JSONObjectUtils.parse(str));
    }

    public static RSAKey parse(Map<String, Object> map) throws ParseException {
        ArrayList arrayList;
        List<Object> jSONArray;
        if (!KeyType.RSA.equals(JWKMetadata.parseKeyType(map))) {
            throw new ParseException("The key type \"kty\" must be RSA", 0);
        }
        Base64URL base64URL = JSONObjectUtils.getBase64URL(map, "n");
        Base64URL base64URL2 = JSONObjectUtils.getBase64URL(map, "e");
        Base64URL base64URL3 = JSONObjectUtils.getBase64URL(map, "d");
        Base64URL base64URL4 = JSONObjectUtils.getBase64URL(map, "p");
        Base64URL base64URL5 = JSONObjectUtils.getBase64URL(map, JWKParameterNames.RSA_SECOND_PRIME_FACTOR);
        Base64URL base64URL6 = JSONObjectUtils.getBase64URL(map, JWKParameterNames.RSA_FIRST_FACTOR_CRT_EXPONENT);
        Base64URL base64URL7 = JSONObjectUtils.getBase64URL(map, JWKParameterNames.RSA_SECOND_FACTOR_CRT_EXPONENT);
        Base64URL base64URL8 = JSONObjectUtils.getBase64URL(map, JWKParameterNames.RSA_FIRST_CRT_COEFFICIENT);
        if (!map.containsKey(JWKParameterNames.RSA_OTHER_PRIMES) || (jSONArray = JSONObjectUtils.getJSONArray(map, JWKParameterNames.RSA_OTHER_PRIMES)) == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(jSONArray.size());
            for (Object obj : jSONArray) {
                if (obj instanceof Map) {
                    Map map2 = (Map) obj;
                    try {
                        arrayList.add(new OtherPrimesInfo(JSONObjectUtils.getBase64URL(map2, "r"), JSONObjectUtils.getBase64URL(map2, JWKParameterNames.RSA_SECOND_FACTOR_CRT_EXPONENT), JSONObjectUtils.getBase64URL(map2, "t")));
                    } catch (IllegalArgumentException e) {
                        throw new ParseException(e.getMessage(), 0);
                    }
                }
            }
        }
        try {
            return new RSAKey(base64URL, base64URL2, base64URL3, base64URL4, base64URL5, base64URL6, base64URL7, base64URL8, arrayList, null, JWKMetadata.parseKeyUse(map), JWKMetadata.parseKeyOperations(map), JWKMetadata.parseAlgorithm(map), JWKMetadata.parseKeyID(map), JWKMetadata.parseX509CertURL(map), JWKMetadata.parseX509CertThumbprint(map), JWKMetadata.parseX509CertSHA256Thumbprint(map), JWKMetadata.parseX509CertChain(map), JWKMetadata.parseExpirationTime(map), JWKMetadata.parseNotBeforeTime(map), JWKMetadata.parseIssueTime(map), null);
        } catch (IllegalArgumentException e2) {
            throw new ParseException(e2.getMessage(), 0);
        }
    }

    public static RSAKey parse(X509Certificate x509Certificate) throws NoSuchAlgorithmException, JOSEException {
        if (!(x509Certificate.getPublicKey() instanceof RSAPublicKey)) {
            throw new JOSEException("The public key of the X.509 certificate is not RSA");
        }
        try {
            return new Builder((RSAPublicKey) x509Certificate.getPublicKey()).keyUse(KeyUse.from(x509Certificate)).keyID(x509Certificate.getSerialNumber().toString(10)).x509CertChain(Collections.singletonList(Base64.encode(x509Certificate.getEncoded()))).x509CertSHA256Thumbprint(Base64URL.encode(MessageDigest.getInstance("SHA-256").digest(x509Certificate.getEncoded()))).expirationTime(x509Certificate.getNotAfter()).notBeforeTime(x509Certificate.getNotBefore()).build();
        } catch (NoSuchAlgorithmException e) {
            throw new JOSEException("Couldn't encode x5t parameter: " + e.getMessage(), e);
        } catch (CertificateEncodingException e2) {
            throw new JOSEException("Couldn't encode x5c parameter: " + e2.getMessage(), e2);
        }
    }

    public static RSAKey load(KeyStore keyStore, String str, char[] cArr) throws JOSEException, KeyStoreException {
        Certificate certificate = keyStore.getCertificate(str);
        if (!(certificate instanceof X509Certificate)) {
            return null;
        }
        X509Certificate x509Certificate = (X509Certificate) certificate;
        if (!(x509Certificate.getPublicKey() instanceof RSAPublicKey)) {
            throw new JOSEException("Couldn't load RSA JWK: The key algorithm is not RSA");
        }
        RSAKey rSAKeyBuild = new Builder(parse(x509Certificate)).keyID(str).keyStore(keyStore).build();
        try {
            Key key = keyStore.getKey(str, cArr);
            if (key instanceof RSAPrivateKey) {
                return new Builder(rSAKeyBuild).privateKey((RSAPrivateKey) key).build();
            }
            return ((key instanceof PrivateKey) && "RSA".equalsIgnoreCase(key.getAlgorithm())) ? new Builder(rSAKeyBuild).privateKey((PrivateKey) key).build() : rSAKeyBuild;
        } catch (NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new JOSEException("Couldn't retrieve private RSA key (bad pin?): " + e.getMessage(), e);
        }
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RSAKey) || !super.equals(obj)) {
            return false;
        }
        RSAKey rSAKey = (RSAKey) obj;
        return Objects.equals(this.f65n, rSAKey.f65n) && Objects.equals(this.e, rSAKey.e) && Objects.equals(this.d, rSAKey.d) && Objects.equals(this.p, rSAKey.p) && Objects.equals(this.q, rSAKey.q) && Objects.equals(this.dp, rSAKey.dp) && Objects.equals(this.dq, rSAKey.dq) && Objects.equals(this.qi, rSAKey.qi) && Objects.equals(this.oth, rSAKey.oth) && Objects.equals(this.privateKey, rSAKey.privateKey);
    }

    @Override // com.nimbusds.jose.jwk.JWK
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.f65n, this.e, this.d, this.p, this.q, this.dp, this.dq, this.qi, this.oth, this.privateKey);
    }
}
