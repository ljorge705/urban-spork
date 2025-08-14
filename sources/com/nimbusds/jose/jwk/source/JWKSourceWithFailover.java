package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.IOUtils;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class JWKSourceWithFailover<C extends SecurityContext> implements JWKSource<C>, Closeable {
    private final JWKSource<C> failoverJWKSource;
    private final JWKSource<C> jwkSource;

    public JWKSourceWithFailover(JWKSource<C> jWKSource, JWKSource<C> jWKSource2) {
        Objects.requireNonNull(jWKSource, "The primary JWK source must not be null");
        this.jwkSource = jWKSource;
        this.failoverJWKSource = jWKSource2;
    }

    private List<JWK> failover(Exception exc, JWKSelector jWKSelector, C c) throws KeySourceException {
        try {
            return this.failoverJWKSource.get(jWKSelector, c);
        } catch (KeySourceException e) {
            throw new KeySourceException(exc.getMessage() + "; Failover JWK source retrieval failed with: " + e.getMessage(), e);
        }
    }

    @Override // com.nimbusds.jose.jwk.source.JWKSource
    public List<JWK> get(JWKSelector jWKSelector, C c) throws KeySourceException {
        try {
            return this.jwkSource.get(jWKSelector, c);
        } catch (Exception e) {
            return failover(e, jWKSelector, c);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        JWKSource<C> jWKSource = this.jwkSource;
        if (jWKSource instanceof Closeable) {
            IOUtils.closeSilently((Closeable) jWKSource);
        }
        JWKSource<C> jWKSource2 = this.failoverJWKSource;
        if (jWKSource2 instanceof Closeable) {
            IOUtils.closeSilently((Closeable) jWKSource2);
        }
    }
}
