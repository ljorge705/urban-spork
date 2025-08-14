package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.jwk.JWKSet;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import net.jcip.annotations.ThreadSafe;

@Deprecated
@ThreadSafe
/* loaded from: classes2.dex */
public class DefaultJWKSetCache implements JWKSetCache {
    public static final long DEFAULT_LIFESPAN_MINUTES = 15;
    public static final long DEFAULT_REFRESH_TIME_MINUTES = 5;
    private volatile JWKSetWithTimestamp jwkSetWithTimestamp;
    private final long lifespan;
    private final long refreshTime;
    private final TimeUnit timeUnit;

    public DefaultJWKSetCache() {
        this(15L, 5L, TimeUnit.MINUTES);
    }

    public DefaultJWKSetCache(long j, long j2, TimeUnit timeUnit) {
        this.lifespan = j;
        this.refreshTime = j2;
        if ((j > -1 || j2 > -1) && timeUnit == null) {
            throw new IllegalArgumentException("A time unit must be specified for non-negative lifespans or refresh times");
        }
        this.timeUnit = timeUnit;
    }

    @Override // com.nimbusds.jose.jwk.source.JWKSetCache
    public void put(JWKSet jWKSet) {
        this.jwkSetWithTimestamp = jWKSet != null ? new JWKSetWithTimestamp(jWKSet) : null;
    }

    @Override // com.nimbusds.jose.jwk.source.JWKSetCache
    public JWKSet get() {
        if (this.jwkSetWithTimestamp == null || isExpired()) {
            return null;
        }
        return this.jwkSetWithTimestamp.getJWKSet();
    }

    @Override // com.nimbusds.jose.jwk.source.JWKSetCache
    public boolean requiresRefresh() {
        return this.jwkSetWithTimestamp != null && this.refreshTime > -1 && new Date().getTime() > this.jwkSetWithTimestamp.getDate().getTime() + TimeUnit.MILLISECONDS.convert(this.refreshTime, this.timeUnit);
    }

    public long getPutTimestamp() {
        if (this.jwkSetWithTimestamp != null) {
            return this.jwkSetWithTimestamp.getDate().getTime();
        }
        return -1L;
    }

    public boolean isExpired() {
        return this.jwkSetWithTimestamp != null && this.lifespan > -1 && new Date().getTime() > this.jwkSetWithTimestamp.getDate().getTime() + TimeUnit.MILLISECONDS.convert(this.lifespan, this.timeUnit);
    }

    public long getLifespan(TimeUnit timeUnit) {
        long j = this.lifespan;
        return j < 0 ? j : timeUnit.convert(j, this.timeUnit);
    }

    public long getRefreshTime(TimeUnit timeUnit) {
        long j = this.refreshTime;
        return j < 0 ? j : timeUnit.convert(j, this.timeUnit);
    }
}
