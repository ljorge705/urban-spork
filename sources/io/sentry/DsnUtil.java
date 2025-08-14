package io.sentry;

import java.util.Locale;

/* loaded from: classes6.dex */
public final class DsnUtil {
    public static boolean urlContainsDsnHost(SentryOptions sentryOptions, String str) {
        String host;
        if (sentryOptions == null || str == null || sentryOptions.getDsn() == null || (host = sentryOptions.retrieveParsedDsn().getSentryUri().getHost()) == null) {
            return false;
        }
        return str.toLowerCase(Locale.ROOT).contains(host.toLowerCase(Locale.ROOT));
    }
}
