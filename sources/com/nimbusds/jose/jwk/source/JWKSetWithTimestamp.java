package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.jwk.JWKSet;
import java.util.Date;
import net.jcip.annotations.Immutable;

@Deprecated
@Immutable
/* loaded from: classes2.dex */
public final class JWKSetWithTimestamp {
    private final JWKSet jwkSet;
    private final Date timestamp;

    public Date getDate() {
        return this.timestamp;
    }

    public JWKSet getJWKSet() {
        return this.jwkSet;
    }

    public JWKSetWithTimestamp(JWKSet jWKSet) {
        this(jWKSet, new Date());
    }

    public JWKSetWithTimestamp(JWKSet jWKSet, Date date) {
        if (jWKSet == null) {
            throw new IllegalArgumentException("The JWK set must not be null");
        }
        this.jwkSet = jWKSet;
        if (date == null) {
            throw new IllegalArgumentException("The timestamp must not null");
        }
        this.timestamp = date;
    }
}
