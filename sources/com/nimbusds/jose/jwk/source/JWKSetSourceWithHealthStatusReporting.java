package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.health.HealthReport;
import com.nimbusds.jose.util.health.HealthReportListener;
import com.nimbusds.jose.util.health.HealthStatus;
import java.util.Objects;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class JWKSetSourceWithHealthStatusReporting<C extends SecurityContext> extends JWKSetSourceWrapper<C> {
    private final HealthReportListener<JWKSetSourceWithHealthStatusReporting<C>, C> healthReportListener;

    public JWKSetSourceWithHealthStatusReporting(JWKSetSource<C> jWKSetSource, HealthReportListener<JWKSetSourceWithHealthStatusReporting<C>, C> healthReportListener) {
        super(jWKSetSource);
        Objects.requireNonNull(healthReportListener);
        this.healthReportListener = healthReportListener;
    }

    @Override // com.nimbusds.jose.jwk.source.JWKSetSource
    public JWKSet getJWKSet(JWKSetCacheRefreshEvaluator jWKSetCacheRefreshEvaluator, long j, C c) throws Exception {
        try {
            JWKSet jWKSet = getSource().getJWKSet(jWKSetCacheRefreshEvaluator, j, c);
            this.healthReportListener.notify(new HealthReport<>(this, HealthStatus.HEALTHY, j, c));
            return jWKSet;
        } catch (Exception e) {
            this.healthReportListener.notify(new HealthReport<>(this, HealthStatus.NOT_HEALTHY, e, j, c));
            throw e;
        }
    }
}
