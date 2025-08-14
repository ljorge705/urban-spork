package com.nimbusds.jose.crypto.factories;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyTypeException;
import com.nimbusds.jose.crypto.ECDSAVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jca.JCAContext;
import com.nimbusds.jose.proc.JWSVerifierFactory;
import java.security.Key;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.crypto.SecretKey;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class DefaultJWSVerifierFactory implements JWSVerifierFactory {
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
        linkedHashSet.addAll(MACVerifier.SUPPORTED_ALGORITHMS);
        linkedHashSet.addAll(RSASSAVerifier.SUPPORTED_ALGORITHMS);
        linkedHashSet.addAll(ECDSAVerifier.SUPPORTED_ALGORITHMS);
        SUPPORTED_ALGORITHMS = Collections.unmodifiableSet(linkedHashSet);
    }

    @Override // com.nimbusds.jose.proc.JWSVerifierFactory
    public JWSVerifier createJWSVerifier(JWSHeader jWSHeader, Key key) throws JOSEException {
        JWSVerifier eCDSAVerifier;
        if (MACVerifier.SUPPORTED_ALGORITHMS.contains(jWSHeader.getAlgorithm())) {
            if (!(key instanceof SecretKey)) {
                throw new KeyTypeException(SecretKey.class);
            }
            eCDSAVerifier = new MACVerifier((SecretKey) key);
        } else if (RSASSAVerifier.SUPPORTED_ALGORITHMS.contains(jWSHeader.getAlgorithm())) {
            if (!(key instanceof RSAPublicKey)) {
                throw new KeyTypeException(RSAPublicKey.class);
            }
            eCDSAVerifier = new RSASSAVerifier((RSAPublicKey) key);
        } else if (ECDSAVerifier.SUPPORTED_ALGORITHMS.contains(jWSHeader.getAlgorithm())) {
            if (!(key instanceof ECPublicKey)) {
                throw new KeyTypeException(ECPublicKey.class);
            }
            eCDSAVerifier = new ECDSAVerifier((ECPublicKey) key);
        } else {
            throw new JOSEException("Unsupported JWS algorithm: " + jWSHeader.getAlgorithm());
        }
        eCDSAVerifier.getJCAContext().setProvider(this.jcaContext.getProvider());
        return eCDSAVerifier;
    }
}
