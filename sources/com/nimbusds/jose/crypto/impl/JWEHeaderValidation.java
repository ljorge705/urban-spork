package com.nimbusds.jose.crypto.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;

/* loaded from: classes2.dex */
public class JWEHeaderValidation {
    public static JWEAlgorithm getAlgorithmAndEnsureNotNull(JWEHeader jWEHeader) throws JOSEException {
        JWEAlgorithm algorithm = jWEHeader.getAlgorithm();
        if (algorithm != null) {
            return algorithm;
        }
        throw new JOSEException("The algorithm \"alg\" header parameter must not be null");
    }

    private JWEHeaderValidation() {
    }
}
