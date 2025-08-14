package com.nimbusds.jose.mint;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.produce.JWSSignerFactory;

/* loaded from: classes2.dex */
public interface JWSMinterConfiguration<C extends SecurityContext> {
    JWKSource<C> getJWKSource();

    JWSSignerFactory getJWSSignerFactory();

    void setJWKSource(JWKSource<C> jWKSource);

    void setJWSSignerFactory(JWSSignerFactory jWSSignerFactory);
}
