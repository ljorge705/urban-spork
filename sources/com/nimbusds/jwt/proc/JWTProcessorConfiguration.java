package com.nimbusds.jwt.proc;

import com.nimbusds.jose.proc.JOSEProcessorConfiguration;
import com.nimbusds.jose.proc.SecurityContext;

/* loaded from: classes2.dex */
public interface JWTProcessorConfiguration<C extends SecurityContext> extends JOSEProcessorConfiguration<C> {
    JWTClaimsSetAwareJWSKeySelector<C> getJWTClaimsSetAwareJWSKeySelector();

    JWTClaimsSetVerifier<C> getJWTClaimsSetVerifier();

    void setJWTClaimsSetAwareJWSKeySelector(JWTClaimsSetAwareJWSKeySelector<C> jWTClaimsSetAwareJWSKeySelector);

    void setJWTClaimsSetVerifier(JWTClaimsSetVerifier<C> jWTClaimsSetVerifier);
}
