package com.nimbusds.jose.crypto.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;

/* loaded from: classes2.dex */
public class RSAKeyUtils {
    public static PrivateKey toRSAPrivateKey(RSAKey rSAKey) throws JOSEException {
        if (!rSAKey.isPrivate()) {
            throw new JOSEException("The RSA JWK doesn't contain a private part");
        }
        return rSAKey.toPrivateKey();
    }

    public static int keyBitLength(PrivateKey privateKey) {
        if (!(privateKey instanceof RSAPrivateKey)) {
            return -1;
        }
        try {
            return ((RSAPrivateKey) privateKey).getModulus().bitLength();
        } catch (Exception unused) {
            return -1;
        }
    }
}
