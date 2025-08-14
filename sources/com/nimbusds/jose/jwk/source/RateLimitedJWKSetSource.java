package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.events.EventListener;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class RateLimitedJWKSetSource<C extends SecurityContext> extends JWKSetSourceWrapper<C> {
    private int counter;
    private final EventListener<RateLimitedJWKSetSource<C>, C> eventListener;
    private final long minTimeInterval;
    private long nextOpeningTime;

    public long getMinTimeInterval() {
        return this.minTimeInterval;
    }

    public static class RateLimitedEvent<C extends SecurityContext> extends AbstractJWKSetSourceEvent<RateLimitedJWKSetSource<C>, C> {
        @Override // com.nimbusds.jose.jwk.source.AbstractJWKSetSourceEvent, com.nimbusds.jose.util.events.Event
        public /* bridge */ /* synthetic */ SecurityContext getContext() {
            return super.getContext();
        }

        @Override // com.nimbusds.jose.jwk.source.AbstractJWKSetSourceEvent, com.nimbusds.jose.util.events.Event
        public /* bridge */ /* synthetic */ JWKSetSource getSource() {
            return super.getSource();
        }

        private RateLimitedEvent(RateLimitedJWKSetSource<C> rateLimitedJWKSetSource, C c) {
            super(rateLimitedJWKSetSource, c);
        }
    }

    public RateLimitedJWKSetSource(JWKSetSource<C> jWKSetSource, long j, EventListener<RateLimitedJWKSetSource<C>, C> eventListener) {
        super(jWKSetSource);
        this.nextOpeningTime = -1L;
        this.counter = 0;
        this.minTimeInterval = j;
        this.eventListener = eventListener;
    }

    @Override // com.nimbusds.jose.jwk.source.JWKSetSource
    public JWKSet getJWKSet(JWKSetCacheRefreshEvaluator jWKSetCacheRefreshEvaluator, long j, C c) throws KeySourceException {
        boolean z;
        synchronized (this) {
            if (this.nextOpeningTime <= j) {
                this.nextOpeningTime = this.minTimeInterval + j;
                this.counter = 1;
            } else {
                int i = this.counter;
                z = i <= 0;
                if (!z) {
                    this.counter = i - 1;
                }
            }
        }
        if (z) {
            EventListener<RateLimitedJWKSetSource<C>, C> eventListener = this.eventListener;
            if (eventListener != null) {
                eventListener.notify(new RateLimitedEvent(c));
            }
            throw new RateLimitReachedException();
        }
        return getSource().getJWKSet(jWKSetCacheRefreshEvaluator, j, c);
    }
}
