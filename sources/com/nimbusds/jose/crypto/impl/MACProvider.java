package com.nimbusds.jose.crypto.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.util.StandardCharset;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes2.dex */
public abstract class MACProvider extends BaseJWSProvider {
    public static final Set<JWSAlgorithm> SUPPORTED_ALGORITHMS;
    private final byte[] secret;
    private final SecretKey secretKey;

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(JWSAlgorithm.HS256);
        linkedHashSet.add(JWSAlgorithm.HS384);
        linkedHashSet.add(JWSAlgorithm.HS512);
        SUPPORTED_ALGORITHMS = Collections.unmodifiableSet(linkedHashSet);
    }

    protected static String getJCAAlgorithmName(JWSAlgorithm jWSAlgorithm) throws JOSEException {
        if (jWSAlgorithm.equals(JWSAlgorithm.HS256)) {
            return "HMACSHA256";
        }
        if (jWSAlgorithm.equals(JWSAlgorithm.HS384)) {
            return "HMACSHA384";
        }
        if (jWSAlgorithm.equals(JWSAlgorithm.HS512)) {
            return "HMACSHA512";
        }
        throw new JOSEException(AlgorithmSupportMessage.unsupportedJWSAlgorithm(jWSAlgorithm, SUPPORTED_ALGORITHMS));
    }

    protected MACProvider(byte[] bArr, Set<JWSAlgorithm> set) throws KeyLengthException {
        super(set);
        if (bArr.length < 32) {
            throw new KeyLengthException("The secret length must be at least 256 bits");
        }
        this.secret = bArr;
        this.secretKey = null;
    }

    protected MACProvider(SecretKey secretKey, Set<JWSAlgorithm> set) throws KeyLengthException {
        super(set);
        if (secretKey.getEncoded() != null && secretKey.getEncoded().length < 32) {
            throw new KeyLengthException("The secret length must be at least 256 bits");
        }
        this.secretKey = secretKey;
        this.secret = null;
    }

    public SecretKey getSecretKey() {
        SecretKey secretKey = this.secretKey;
        if (secretKey != null) {
            return secretKey;
        }
        if (this.secret != null) {
            return new SecretKeySpec(this.secret, "MAC");
        }
        throw new IllegalStateException("Unexpected state");
    }

    public byte[] getSecret() {
        SecretKey secretKey = this.secretKey;
        if (secretKey != null) {
            return secretKey.getEncoded();
        }
        byte[] bArr = this.secret;
        if (bArr != null) {
            return bArr;
        }
        throw new IllegalStateException("Unexpected state");
    }

    public String getSecretString() {
        byte[] secret = getSecret();
        if (secret == null) {
            return null;
        }
        return new String(secret, StandardCharset.UTF_8);
    }
}
