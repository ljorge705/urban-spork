package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.cache.CachedObject;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
abstract class AbstractCachingJWKSetSource<C extends SecurityContext> extends JWKSetSourceWrapper<C> {
    private volatile CachedObject<JWKSet> cachedJWKSet;
    private final long timeToLive;

    CachedObject<JWKSet> getCachedJWKSet() {
        return this.cachedJWKSet;
    }

    public long getTimeToLive() {
        return this.timeToLive;
    }

    void setCachedJWKSet(CachedObject<JWKSet> cachedObject) {
        this.cachedJWKSet = cachedObject;
    }

    AbstractCachingJWKSetSource(JWKSetSource<C> jWKSetSource, long j) {
        super(jWKSetSource);
        this.timeToLive = j;
    }

    CachedObject<JWKSet> getCachedJWKSetIfValid(long j) {
        CachedObject<JWKSet> cachedJWKSet = getCachedJWKSet();
        if (cachedJWKSet == null || !cachedJWKSet.isValid(j)) {
            return null;
        }
        return cachedJWKSet;
    }

    CachedObject<JWKSet> cacheJWKSet(JWKSet jWKSet, long j) {
        CachedObject<JWKSet> cachedObject = new CachedObject<>(jWKSet, currentTimeMillis(), CachedObject.computeExpirationTime(j, getTimeToLive()));
        setCachedJWKSet(cachedObject);
        return cachedObject;
    }

    long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
