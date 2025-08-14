package com.nimbusds.jose.util.health;

import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.events.Event;
import java.util.Objects;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes2.dex */
public class HealthReport<S, C extends SecurityContext> implements Event<S, C> {
    private final C context;
    private final Exception exception;
    private final S source;
    private final HealthStatus status;
    private final long timestamp;

    @Override // com.nimbusds.jose.util.events.Event
    public C getContext() {
        return this.context;
    }

    public Exception getException() {
        return this.exception;
    }

    public HealthStatus getHealthStatus() {
        return this.status;
    }

    @Override // com.nimbusds.jose.util.events.Event
    public S getSource() {
        return this.source;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public HealthReport(S s, HealthStatus healthStatus, long j, C c) {
        this(s, healthStatus, null, j, c);
    }

    public HealthReport(S s, HealthStatus healthStatus, Exception exc, long j, C c) {
        Objects.requireNonNull(s);
        this.source = s;
        Objects.requireNonNull(healthStatus);
        this.status = healthStatus;
        if (exc != null && HealthStatus.HEALTHY.equals(healthStatus)) {
            throw new IllegalArgumentException("Exception not accepted for a healthy status");
        }
        this.exception = exc;
        this.timestamp = j;
        this.context = c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("HealthReport{source=");
        sb.append(this.source);
        sb.append(", status=").append(this.status);
        sb.append(", exception=").append(this.exception);
        sb.append(", timestamp=").append(this.timestamp);
        sb.append(", context=").append(this.context);
        sb.append(AbstractJsonLexerKt.END_OBJ);
        return sb.toString();
    }
}
