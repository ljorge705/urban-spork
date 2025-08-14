package com.nimbusds.jose.crypto.factories;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.crypto.Ed25519Signer;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jca.JCAContext;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKException;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.OctetKeyPair;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.produce.JWSSignerFactory;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public class DefaultJWSSignerFactory implements JWSSignerFactory {
    public static final Set<JWSAlgorithm> SUPPORTED_ALGORITHMS;
    private final JCAContext jcaContext = new JCAContext();

    @Override // com.nimbusds.jose.jca.JCAAware
    public JCAContext getJCAContext() {
        return this.jcaContext;
    }

    @Override // com.nimbusds.jose.JWSProvider
    public Set<JWSAlgorithm> supportedJWSAlgorithms() {
        return SUPPORTED_ALGORITHMS;
    }

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(MACSigner.SUPPORTED_ALGORITHMS);
        linkedHashSet.addAll(RSASSASigner.SUPPORTED_ALGORITHMS);
        linkedHashSet.addAll(ECDSASigner.SUPPORTED_ALGORITHMS);
        linkedHashSet.addAll(Ed25519Signer.SUPPORTED_ALGORITHMS);
        SUPPORTED_ALGORITHMS = Collections.unmodifiableSet(linkedHashSet);
    }

    @Override // com.nimbusds.jose.produce.JWSSignerFactory
    public JWSSigner createJWSSigner(JWK jwk) throws JOSEException {
        JWSSigner ed25519Signer;
        if (!jwk.isPrivate()) {
            throw JWKException.expectedPrivate();
        }
        if (jwk.getKeyUse() != null && !KeyUse.SIGNATURE.equals(jwk.getKeyUse())) {
            throw new JWKException("The JWK use must be sig (signature) or unspecified");
        }
        if (jwk instanceof OctetSequenceKey) {
            ed25519Signer = new MACSigner((OctetSequenceKey) jwk);
        } else if (jwk instanceof RSAKey) {
            ed25519Signer = new RSASSASigner((RSAKey) jwk);
        } else if (jwk instanceof ECKey) {
            ed25519Signer = new ECDSASigner((ECKey) jwk);
        } else if (jwk instanceof OctetKeyPair) {
            ed25519Signer = new Ed25519Signer((OctetKeyPair) jwk);
        } else {
            throw new JOSEException("Unsupported JWK type: " + jwk);
        }
        ed25519Signer.getJCAContext().setSecureRandom(this.jcaContext.getSecureRandom());
        ed25519Signer.getJCAContext().setProvider(this.jcaContext.getProvider());
        return ed25519Signer;
    }

    @Override // com.nimbusds.jose.produce.JWSSignerFactory
    public JWSSigner createJWSSigner(JWK jwk, JWSAlgorithm jWSAlgorithm) throws JOSEException {
        JWSSigner ed25519Signer;
        if (!jwk.isPrivate()) {
            throw JWKException.expectedPrivate();
        }
        if (jwk.getKeyUse() != null && !KeyUse.SIGNATURE.equals(jwk.getKeyUse())) {
            throw new JWKException("The JWK use must be sig (signature) or unspecified");
        }
        if (MACSigner.SUPPORTED_ALGORITHMS.contains(jWSAlgorithm)) {
            if (!(jwk instanceof OctetSequenceKey)) {
                throw JWKException.expectedClass(OctetSequenceKey.class);
            }
            ed25519Signer = new MACSigner((OctetSequenceKey) jwk);
        } else if (RSASSASigner.SUPPORTED_ALGORITHMS.contains(jWSAlgorithm)) {
            if (!(jwk instanceof RSAKey)) {
                throw JWKException.expectedClass(RSAKey.class);
            }
            ed25519Signer = new RSASSASigner((RSAKey) jwk);
        } else if (ECDSASigner.SUPPORTED_ALGORITHMS.contains(jWSAlgorithm)) {
            if (!(jwk instanceof ECKey)) {
                throw JWKException.expectedClass(ECKey.class);
            }
            ed25519Signer = new ECDSASigner((ECKey) jwk);
        } else if (Ed25519Signer.SUPPORTED_ALGORITHMS.contains(jWSAlgorithm)) {
            if (!(jwk instanceof OctetKeyPair)) {
                throw JWKException.expectedClass(OctetKeyPair.class);
            }
            ed25519Signer = new Ed25519Signer((OctetKeyPair) jwk);
        } else {
            throw new JOSEException("Unsupported JWS algorithm: " + jWSAlgorithm);
        }
        ed25519Signer.getJCAContext().setSecureRandom(this.jcaContext.getSecureRandom());
        ed25519Signer.getJCAContext().setProvider(this.jcaContext.getProvider());
        return ed25519Signer;
    }
}
