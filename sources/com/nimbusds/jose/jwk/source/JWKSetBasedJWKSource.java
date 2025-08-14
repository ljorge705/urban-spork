package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class JWKSetBasedJWKSource<C extends SecurityContext> implements JWKSource<C>, Closeable {
    private final JWKSetSource<C> source;

    public JWKSetSource<C> getJWKSetSource() {
        return this.source;
    }

    public JWKSetBasedJWKSource(JWKSetSource<C> jWKSetSource) {
        Objects.requireNonNull(jWKSetSource);
        this.source = jWKSetSource;
    }

    @Override // com.nimbusds.jose.jwk.source.JWKSource
    public List<JWK> get(JWKSelector jWKSelector, C c) throws KeySourceException {
        long jCurrentTimeMillis = System.currentTimeMillis();
        JWKSet jWKSet = this.source.getJWKSet(JWKSetCacheRefreshEvaluator.noRefresh(), jCurrentTimeMillis, c);
        List<JWK> listSelect = jWKSelector.select(jWKSet);
        return listSelect.isEmpty() ? jWKSelector.select(this.source.getJWKSet(JWKSetCacheRefreshEvaluator.referenceComparison(jWKSet), jCurrentTimeMillis, c)) : listSelect;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.source.close();
    }
}
