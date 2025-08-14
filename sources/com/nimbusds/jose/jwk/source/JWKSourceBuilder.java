package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jose.util.ResourceRetriever;
import com.nimbusds.jose.util.events.EventListener;
import com.nimbusds.jose.util.health.HealthReportListener;
import java.net.URL;
import java.util.Objects;

/* loaded from: classes2.dex */
public class JWKSourceBuilder<C extends SecurityContext> {
    public static final long DEFAULT_CACHE_REFRESH_TIMEOUT = 15000;
    public static final long DEFAULT_CACHE_TIME_TO_LIVE = 300000;
    public static final int DEFAULT_HTTP_CONNECT_TIMEOUT = 500;
    public static final int DEFAULT_HTTP_READ_TIMEOUT = 500;
    public static final int DEFAULT_HTTP_SIZE_LIMIT = 51200;
    public static final long DEFAULT_RATE_LIMIT_MIN_INTERVAL = 30000;
    public static final long DEFAULT_REFRESH_AHEAD_TIME = 30000;
    private EventListener<CachingJWKSetSource<C>, C> cachingEventListener;
    protected JWKSource<C> failover;
    private HealthReportListener<JWKSetSourceWithHealthStatusReporting<C>, C> healthReportListener;
    private final JWKSetSource<C> jwkSetSource;
    private EventListener<OutageTolerantJWKSetSource<C>, C> outageEventListener;
    private EventListener<RateLimitedJWKSetSource<C>, C> rateLimitedEventListener;
    private EventListener<RetryingJWKSetSource<C>, C> retryingEventListener;
    private boolean caching = true;
    private long cacheTimeToLive = 300000;
    private long cacheRefreshTimeout = 15000;
    private boolean refreshAhead = true;
    private long refreshAheadTime = 30000;
    private boolean refreshAheadScheduled = false;
    private boolean rateLimited = true;
    private long minTimeInterval = 30000;
    private boolean retrying = false;
    private boolean outageTolerant = false;
    private long outageCacheTimeToLive = -1;

    public JWKSourceBuilder<C> cache(long j, long j2) {
        this.caching = true;
        this.cacheTimeToLive = j;
        this.cacheRefreshTimeout = j2;
        return this;
    }

    public JWKSourceBuilder<C> cache(long j, long j2, EventListener<CachingJWKSetSource<C>, C> eventListener) {
        this.caching = true;
        this.cacheTimeToLive = j;
        this.cacheRefreshTimeout = j2;
        this.cachingEventListener = eventListener;
        return this;
    }

    public JWKSourceBuilder<C> cache(boolean z) {
        this.caching = z;
        return this;
    }

    public JWKSourceBuilder<C> cacheForever() {
        this.caching = true;
        this.cacheTimeToLive = Long.MAX_VALUE;
        this.refreshAhead = false;
        return this;
    }

    public JWKSourceBuilder<C> failover(JWKSource<C> jWKSource) {
        this.failover = jWKSource;
        return this;
    }

    public JWKSourceBuilder<C> healthReporting(HealthReportListener<JWKSetSourceWithHealthStatusReporting<C>, C> healthReportListener) {
        this.healthReportListener = healthReportListener;
        return this;
    }

    public JWKSourceBuilder<C> outageTolerant(long j) {
        this.outageTolerant = true;
        this.outageCacheTimeToLive = j;
        return this;
    }

    public JWKSourceBuilder<C> outageTolerant(long j, EventListener<OutageTolerantJWKSetSource<C>, C> eventListener) {
        this.outageTolerant = true;
        this.outageCacheTimeToLive = j;
        this.outageEventListener = eventListener;
        return this;
    }

    public JWKSourceBuilder<C> outageTolerant(boolean z) {
        this.outageTolerant = z;
        return this;
    }

    public JWKSourceBuilder<C> outageTolerantForever() {
        this.outageTolerant = true;
        this.outageCacheTimeToLive = Long.MAX_VALUE;
        return this;
    }

    public JWKSourceBuilder<C> rateLimited(long j) {
        this.rateLimited = true;
        this.minTimeInterval = j;
        return this;
    }

    public JWKSourceBuilder<C> rateLimited(long j, EventListener<RateLimitedJWKSetSource<C>, C> eventListener) {
        this.rateLimited = true;
        this.minTimeInterval = j;
        this.rateLimitedEventListener = eventListener;
        return this;
    }

    public JWKSourceBuilder<C> rateLimited(boolean z) {
        this.rateLimited = z;
        return this;
    }

    public JWKSourceBuilder<C> refreshAheadCache(long j, boolean z) {
        this.caching = true;
        this.refreshAhead = true;
        this.refreshAheadTime = j;
        this.refreshAheadScheduled = z;
        return this;
    }

    public JWKSourceBuilder<C> refreshAheadCache(long j, boolean z, EventListener<CachingJWKSetSource<C>, C> eventListener) {
        this.caching = true;
        this.refreshAhead = true;
        this.refreshAheadTime = j;
        this.refreshAheadScheduled = z;
        this.cachingEventListener = eventListener;
        return this;
    }

    public JWKSourceBuilder<C> refreshAheadCache(boolean z) {
        if (z) {
            this.caching = true;
        }
        this.refreshAhead = z;
        return this;
    }

    public JWKSourceBuilder<C> retrying(EventListener<RetryingJWKSetSource<C>, C> eventListener) {
        this.retrying = true;
        this.retryingEventListener = eventListener;
        return this;
    }

    public JWKSourceBuilder<C> retrying(boolean z) {
        this.retrying = z;
        return this;
    }

    public static <C extends SecurityContext> JWKSourceBuilder<C> create(URL url) {
        return new JWKSourceBuilder<>(new URLBasedJWKSetSource(url, new DefaultResourceRetriever(500, 500, 51200)));
    }

    public static <C extends SecurityContext> JWKSourceBuilder<C> create(URL url, ResourceRetriever resourceRetriever) {
        return new JWKSourceBuilder<>(new URLBasedJWKSetSource(url, resourceRetriever));
    }

    public static <C extends SecurityContext> JWKSourceBuilder<C> create(JWKSetSource<C> jWKSetSource) {
        return new JWKSourceBuilder<>(jWKSetSource);
    }

    private JWKSourceBuilder(JWKSetSource<C> jWKSetSource) {
        Objects.requireNonNull(jWKSetSource);
        this.jwkSetSource = jWKSetSource;
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00f5 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.nimbusds.jose.jwk.source.JWKSource<C> build() {
        /*
            Method dump skipped, instructions count: 246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nimbusds.jose.jwk.source.JWKSourceBuilder.build():com.nimbusds.jose.jwk.source.JWKSource");
    }
}
