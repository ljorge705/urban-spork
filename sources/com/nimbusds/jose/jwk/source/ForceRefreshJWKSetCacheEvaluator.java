package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.jwk.JWKSet;

/* loaded from: classes2.dex */
class ForceRefreshJWKSetCacheEvaluator extends JWKSetCacheRefreshEvaluator {
    public int hashCode() {
        return 0;
    }

    @Override // com.nimbusds.jose.jwk.source.JWKSetCacheRefreshEvaluator
    public boolean requiresRefresh(JWKSet jWKSet) {
        return true;
    }

    ForceRefreshJWKSetCacheEvaluator() {
    }

    public boolean equals(Object obj) {
        return obj instanceof ForceRefreshJWKSetCacheEvaluator;
    }
}
