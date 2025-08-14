package io.sentry;

/* loaded from: classes6.dex */
public final class SentryInstantDateProvider implements SentryDateProvider {
    @Override // io.sentry.SentryDateProvider
    public SentryDate now() {
        return new SentryInstantDate();
    }
}
