package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.jwk.JWKSet;

@Deprecated
/* loaded from: classes2.dex */
public interface JWKSetCache {
    JWKSet get();

    void put(JWKSet jWKSet);

    boolean requiresRefresh();
}
