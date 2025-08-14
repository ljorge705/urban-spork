package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.ResourceRetriever;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class URLBasedJWKSetSource<C extends SecurityContext> implements JWKSetSource<C> {
    private final ResourceRetriever resourceRetriever;
    private final URL url;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    public URLBasedJWKSetSource(URL url, ResourceRetriever resourceRetriever) {
        Objects.requireNonNull(url, "The URL must not be null");
        this.url = url;
        Objects.requireNonNull(resourceRetriever, "The resource retriever must not be null");
        this.resourceRetriever = resourceRetriever;
    }

    @Override // com.nimbusds.jose.jwk.source.JWKSetSource
    public JWKSet getJWKSet(JWKSetCacheRefreshEvaluator jWKSetCacheRefreshEvaluator, long j, C c) throws KeySourceException {
        try {
            try {
                return JWKSet.parse(this.resourceRetriever.retrieveResource(this.url).getContent());
            } catch (Exception e) {
                throw new JWKSetParseException("Unable to parse JWK set", e);
            }
        } catch (IOException e2) {
            throw new JWKSetRetrievalException("Couldn't retrieve JWK set from URL: " + e2.getMessage(), e2);
        }
    }
}
