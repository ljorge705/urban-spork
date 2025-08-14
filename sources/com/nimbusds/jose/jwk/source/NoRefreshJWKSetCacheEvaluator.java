package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.jwk.JWKSet;

/* loaded from: classes2.dex */
class NoRefreshJWKSetCacheEvaluator extends JWKSetCacheRefreshEvaluator {
    public int hashCode() {
        return 0;
    }

    @Override // com.nimbusds.jose.jwk.source.JWKSetCacheRefreshEvaluator
    public boolean requiresRefresh(JWKSet jWKSet) {
        return false;
    }

    NoRefreshJWKSetCacheEvaluator() {
    }

    public boolean equals(Object obj) {
        return obj instanceof NoRefreshJWKSetCacheEvaluator;
    }
}
