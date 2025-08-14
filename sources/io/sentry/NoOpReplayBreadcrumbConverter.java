package io.sentry;

import io.sentry.rrweb.RRWebEvent;

/* loaded from: classes6.dex */
public final class NoOpReplayBreadcrumbConverter implements ReplayBreadcrumbConverter {
    private static final NoOpReplayBreadcrumbConverter instance = new NoOpReplayBreadcrumbConverter();

    public static NoOpReplayBreadcrumbConverter getInstance() {
        return instance;
    }

    @Override // io.sentry.ReplayBreadcrumbConverter
    public RRWebEvent convert(Breadcrumb breadcrumb) {
        return null;
    }

    private NoOpReplayBreadcrumbConverter() {
    }
}
