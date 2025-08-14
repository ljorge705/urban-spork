package com.nimbusds.jose.jwk.gen;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.KeyOperation;
import com.nimbusds.jose.jwk.KeyUse;
import java.security.KeyStore;
import java.security.Provider;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Set;

/* loaded from: classes2.dex */
public abstract class JWKGenerator<T extends JWK> {
    protected Algorithm alg;
    protected Date exp;
    protected Date iat;
    protected KeyStore keyStore;
    protected String kid;
    protected Date nbf;
    protected Set<KeyOperation> ops;
    protected Provider provider;
    protected SecureRandom secureRandom;
    protected KeyUse use;
    protected boolean x5tKid;

    public JWKGenerator<T> algorithm(Algorithm algorithm) {
        this.alg = algorithm;
        return this;
    }

    public JWKGenerator<T> expirationTime(Date date) {
        this.exp = date;
        return this;
    }

    public abstract T generate() throws JOSEException;

    public JWKGenerator<T> issueTime(Date date) {
        this.iat = date;
        return this;
    }

    public JWKGenerator<T> keyID(String str) {
        this.kid = str;
        return this;
    }

    public JWKGenerator<T> keyIDFromThumbprint(boolean z) {
        this.x5tKid = z;
        return this;
    }

    public JWKGenerator<T> keyOperations(Set<KeyOperation> set) {
        this.ops = set;
        return this;
    }

    public JWKGenerator<T> keyStore(KeyStore keyStore) {
        this.keyStore = keyStore;
        return this;
    }

    public JWKGenerator<T> keyUse(KeyUse keyUse) {
        this.use = keyUse;
        return this;
    }

    public JWKGenerator<T> notBeforeTime(Date date) {
        this.nbf = date;
        return this;
    }

    public JWKGenerator<T> provider(Provider provider) {
        this.provider = provider;
        return this;
    }

    public JWKGenerator<T> secureRandom(SecureRandom secureRandom) {
        this.secureRandom = secureRandom;
        return this;
    }
}
