package com.nimbusds.jose.crypto.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Signature;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;

/* loaded from: classes2.dex */
public class RSASSA {
    public static Signature getSignerAndVerifier(JWSAlgorithm jWSAlgorithm, Provider provider) throws JOSEException {
        Signature signerAndVerifier;
        Signature signerAndVerifier2;
        Signature signerAndVerifier3;
        Signature signerAndVerifier4;
        Signature signerAndVerifier5;
        Signature signerAndVerifier6;
        Signature signerAndVerifier7;
        Signature signerAndVerifier8;
        Signature signerAndVerifier9;
        if (jWSAlgorithm.equals(JWSAlgorithm.RS256) && (signerAndVerifier9 = getSignerAndVerifier("SHA256withRSA", provider)) != null) {
            return signerAndVerifier9;
        }
        if (jWSAlgorithm.equals(JWSAlgorithm.RS384) && (signerAndVerifier8 = getSignerAndVerifier("SHA384withRSA", provider)) != null) {
            return signerAndVerifier8;
        }
        if (jWSAlgorithm.equals(JWSAlgorithm.RS512) && (signerAndVerifier7 = getSignerAndVerifier("SHA512withRSA", provider)) != null) {
            return signerAndVerifier7;
        }
        if (jWSAlgorithm.equals(JWSAlgorithm.PS256) && (signerAndVerifier6 = getSignerAndVerifier("RSASSA-PSS", provider, new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1))) != null) {
            return signerAndVerifier6;
        }
        if (jWSAlgorithm.equals(JWSAlgorithm.PS256) && (signerAndVerifier5 = getSignerAndVerifier("SHA256withRSAandMGF1", provider)) != null) {
            return signerAndVerifier5;
        }
        if (jWSAlgorithm.equals(JWSAlgorithm.PS384) && (signerAndVerifier4 = getSignerAndVerifier("RSASSA-PSS", provider, new PSSParameterSpec("SHA-384", "MGF1", MGF1ParameterSpec.SHA384, 48, 1))) != null) {
            return signerAndVerifier4;
        }
        if (jWSAlgorithm.equals(JWSAlgorithm.PS384) && (signerAndVerifier3 = getSignerAndVerifier("SHA384withRSAandMGF1", provider)) != null) {
            return signerAndVerifier3;
        }
        if (jWSAlgorithm.equals(JWSAlgorithm.PS512) && (signerAndVerifier2 = getSignerAndVerifier("RSASSA-PSS", provider, new PSSParameterSpec("SHA-512", "MGF1", MGF1ParameterSpec.SHA512, 64, 1))) != null) {
            return signerAndVerifier2;
        }
        if (!jWSAlgorithm.equals(JWSAlgorithm.PS512) || (signerAndVerifier = getSignerAndVerifier("SHA512withRSAandMGF1", provider)) == null) {
            throw new JOSEException(AlgorithmSupportMessage.unsupportedJWSAlgorithm(jWSAlgorithm, RSASSAProvider.SUPPORTED_ALGORITHMS));
        }
        return signerAndVerifier;
    }

    private static Signature getSignerAndVerifier(String str, Provider provider) throws JOSEException {
        return getSignerAndVerifier(str, provider, null);
    }

    private static Signature getSignerAndVerifier(String str, Provider provider, PSSParameterSpec pSSParameterSpec) throws NoSuchAlgorithmException, JOSEException, InvalidAlgorithmParameterException {
        Signature signature;
        try {
            if (provider != null) {
                signature = Signature.getInstance(str, provider);
            } else {
                signature = Signature.getInstance(str);
            }
            if (pSSParameterSpec != null) {
                try {
                    signature.setParameter(pSSParameterSpec);
                } catch (InvalidAlgorithmParameterException e) {
                    throw new JOSEException("Invalid RSASSA-PSS salt length parameter: " + e.getMessage(), e);
                }
            }
            return signature;
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    private RSASSA() {
    }
}
