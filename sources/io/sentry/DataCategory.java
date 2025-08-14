package io.sentry;

import com.facebook.hermes.intl.Constants;
import io.sentry.cache.EnvelopeCache;

/* loaded from: classes6.dex */
public enum DataCategory {
    All("__all__"),
    Default(Constants.COLLATION_DEFAULT),
    Error("error"),
    Session(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE),
    Attachment("attachment"),
    Monitor("monitor"),
    Profile("profile"),
    MetricBucket("metric_bucket"),
    Transaction("transaction"),
    Replay("replay"),
    Span("span"),
    Security("security"),
    UserReport("user_report"),
    Unknown("unknown");

    private final String category;

    public String getCategory() {
        return this.category;
    }

    DataCategory(String str) {
        this.category = str;
    }
}
