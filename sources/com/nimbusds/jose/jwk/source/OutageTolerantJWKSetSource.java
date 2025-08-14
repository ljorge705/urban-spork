package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.cache.CachedObject;
import com.nimbusds.jose.util.events.EventListener;
import java.util.Objects;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class OutageTolerantJWKSetSource<C extends SecurityContext> extends AbstractCachingJWKSetSource<C> {
    private final EventListener<OutageTolerantJWKSetSource<C>, C> eventListener;

    @Override // com.nimbusds.jose.jwk.source.AbstractCachingJWKSetSource
    public /* bridge */ /* synthetic */ long getTimeToLive() {
        return super.getTimeToLive();
    }

    public static class OutageEvent<C extends SecurityContext> extends AbstractJWKSetSourceEvent<OutageTolerantJWKSetSource<C>, C> {
        private final Exception exception;
        private final long remainingTime;

        public Exception getException() {
            return this.exception;
        }

        public long getRemainingTime() {
            return this.remainingTime;
        }

        @Override // com.nimbusds.jose.jwk.source.AbstractJWKSetSourceEvent, com.nimbusds.jose.util.events.Event
        public /* bridge */ /* synthetic */ SecurityContext getContext() {
            return super.getContext();
        }

        @Override // com.nimbusds.jose.jwk.source.AbstractJWKSetSourceEvent, com.nimbusds.jose.util.events.Event
        public /* bridge */ /* synthetic */ JWKSetSource getSource() {
            return super.getSource();
        }

        private OutageEvent(OutageTolerantJWKSetSource<C> outageTolerantJWKSetSource, Exception exc, long j, C c) {
            super(outageTolerantJWKSetSource, c);
            Objects.requireNonNull(exc);
            this.exception = exc;
            this.remainingTime = j;
        }
    }

    public OutageTolerantJWKSetSource(JWKSetSource<C> jWKSetSource, long j, EventListener<OutageTolerantJWKSetSource<C>, C> eventListener) {
        super(jWKSetSource, j);
        this.eventListener = eventListener;
    }

    @Override // com.nimbusds.jose.jwk.source.JWKSetSource
    public JWKSet getJWKSet(JWKSetCacheRefreshEvaluator jWKSetCacheRefreshEvaluator, long j, C c) throws KeySourceException {
        try {
            JWKSet jWKSet = getSource().getJWKSet(jWKSetCacheRefreshEvaluator, j, c);
            cacheJWKSet(jWKSet, j);
            return jWKSet;
        } catch (JWKSetUnavailableException e) {
            CachedObject<JWKSet> cachedJWKSet = getCachedJWKSet();
            if (cachedJWKSet != null && cachedJWKSet.isValid(j)) {
                long expirationTime = cachedJWKSet.getExpirationTime() - j;
                EventListener<OutageTolerantJWKSetSource<C>, C> eventListener = this.eventListener;
                if (eventListener != null) {
                    eventListener.notify(new OutageEvent(e, expirationTime, c));
                }
                JWKSet jWKSet2 = new JWKSet(cachedJWKSet.get().getKeys());
                if (!jWKSetCacheRefreshEvaluator.requiresRefresh(jWKSet2)) {
                    return jWKSet2;
                }
            }
            throw e;
        }
    }
}
