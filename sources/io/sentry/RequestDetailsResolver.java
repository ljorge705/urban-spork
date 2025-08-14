package io.sentry;

import io.sentry.util.Objects;
import java.net.URI;
import java.util.HashMap;

/* loaded from: classes6.dex */
final class RequestDetailsResolver {
    private static final String SENTRY_AUTH = "X-Sentry-Auth";
    private static final String USER_AGENT = "User-Agent";
    private final SentryOptions options;

    public RequestDetailsResolver(SentryOptions sentryOptions) {
        this.options = (SentryOptions) Objects.requireNonNull(sentryOptions, "options is required");
    }

    RequestDetails resolve() throws IllegalArgumentException {
        Dsn dsnRetrieveParsedDsn = this.options.retrieveParsedDsn();
        URI sentryUri = dsnRetrieveParsedDsn.getSentryUri();
        String string = sentryUri.resolve(sentryUri.getPath() + "/envelope/").toString();
        String publicKey = dsnRetrieveParsedDsn.getPublicKey();
        String secretKey = dsnRetrieveParsedDsn.getSecretKey();
        String str = "Sentry sentry_version=7,sentry_client=" + this.options.getSentryClientName() + ",sentry_key=" + publicKey + ((secretKey == null || secretKey.length() <= 0) ? "" : ",sentry_secret=" + secretKey);
        String sentryClientName = this.options.getSentryClientName();
        HashMap map = new HashMap();
        map.put("User-Agent", sentryClientName);
        map.put(SENTRY_AUTH, str);
        return new RequestDetails(string, map);
    }
}
