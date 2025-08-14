package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.Requirement;
import com.nimbusds.jose.util.JSONStringUtils;
import java.io.Serializable;
import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes2.dex */
public final class KeyType implements Serializable {
    private static final long serialVersionUID = 1;
    private final Requirement requirement;
    private final String value;
    public static final KeyType EC = new KeyType("EC", Requirement.RECOMMENDED);
    public static final KeyType RSA = new KeyType("RSA", Requirement.REQUIRED);
    public static final KeyType OCT = new KeyType("oct", Requirement.OPTIONAL);
    public static final KeyType OKP = new KeyType("OKP", Requirement.OPTIONAL);

    public Requirement getRequirement() {
        return this.requirement;
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        return this.value;
    }

    public KeyType(String str, Requirement requirement) {
        if (str == null) {
            throw new IllegalArgumentException("The key type value must not be null");
        }
        this.value = str;
        this.requirement = requirement;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof KeyType) && toString().equals(obj.toString());
    }

    public String toJSONString() {
        return JSONStringUtils.toJSONString(this.value);
    }

    public static KeyType parse(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The key type to parse must not be null");
        }
        KeyType keyType = EC;
        if (str.equals(keyType.getValue())) {
            return keyType;
        }
        KeyType keyType2 = RSA;
        if (str.equals(keyType2.getValue())) {
            return keyType2;
        }
        KeyType keyType3 = OCT;
        if (str.equals(keyType3.getValue())) {
            return keyType3;
        }
        KeyType keyType4 = OKP;
        return str.equals(keyType4.getValue()) ? keyType4 : new KeyType(str, null);
    }

    public static KeyType forAlgorithm(Algorithm algorithm) {
        if (algorithm == null) {
            return null;
        }
        if (JWSAlgorithm.Family.RSA.contains(algorithm)) {
            return RSA;
        }
        if (JWSAlgorithm.Family.EC.contains(algorithm)) {
            return EC;
        }
        if (JWSAlgorithm.Family.HMAC_SHA.contains(algorithm)) {
            return OCT;
        }
        if (JWEAlgorithm.Family.RSA.contains(algorithm)) {
            return RSA;
        }
        if (JWEAlgorithm.Family.ECDH_ES.contains(algorithm)) {
            return EC;
        }
        if (!JWEAlgorithm.DIR.equals(algorithm) && !JWEAlgorithm.Family.AES_GCM_KW.contains(algorithm) && !JWEAlgorithm.Family.AES_KW.contains(algorithm) && !JWEAlgorithm.Family.PBES2.contains(algorithm)) {
            if (JWSAlgorithm.Family.ED.contains(algorithm)) {
                return OKP;
            }
            return null;
        }
        return OCT;
    }
}
