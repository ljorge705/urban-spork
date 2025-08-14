package io.sentry;

import io.sentry.protocol.SentryTransaction;

/* loaded from: classes6.dex */
public interface EventProcessor {
    default SentryEvent process(SentryEvent sentryEvent, Hint hint) {
        return sentryEvent;
    }

    default SentryReplayEvent process(SentryReplayEvent sentryReplayEvent, Hint hint) {
        return sentryReplayEvent;
    }

    default SentryTransaction process(SentryTransaction sentryTransaction, Hint hint) {
        return sentryTransaction;
    }
}
