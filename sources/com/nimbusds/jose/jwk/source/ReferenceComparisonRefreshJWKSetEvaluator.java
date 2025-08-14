package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.jwk.JWKSet;
import java.util.Objects;

/* loaded from: classes2.dex */
class ReferenceComparisonRefreshJWKSetEvaluator extends JWKSetCacheRefreshEvaluator {
    private final JWKSet jwkSet;

    @Override // com.nimbusds.jose.jwk.source.JWKSetCacheRefreshEvaluator
    public boolean requiresRefresh(JWKSet jWKSet) {
        return jWKSet == this.jwkSet;
    }

    public ReferenceComparisonRefreshJWKSetEvaluator(JWKSet jWKSet) {
        this.jwkSet = jWKSet;
    }

    public int hashCode() {
        return Objects.hash(this.jwkSet);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return Objects.equals(this.jwkSet, ((ReferenceComparisonRefreshJWKSetEvaluator) obj).jwkSet);
        }
        return false;
    }
}
