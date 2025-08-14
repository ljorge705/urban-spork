package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import java.io.Closeable;

/* loaded from: classes2.dex */
public interface JWKSetSource<C extends SecurityContext> extends Closeable {
    JWKSet getJWKSet(JWKSetCacheRefreshEvaluator jWKSetCacheRefreshEvaluator, long j, C c) throws KeySourceException;
}
