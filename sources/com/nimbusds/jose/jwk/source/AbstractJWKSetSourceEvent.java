package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.jwk.source.JWKSetSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.events.Event;
import java.util.Objects;

/* loaded from: classes2.dex */
class AbstractJWKSetSourceEvent<S extends JWKSetSource<C>, C extends SecurityContext> implements Event<S, C> {
    private final C context;
    private final S source;

    @Override // com.nimbusds.jose.util.events.Event
    public C getContext() {
        return this.context;
    }

    @Override // com.nimbusds.jose.util.events.Event
    public S getSource() {
        return this.source;
    }

    AbstractJWKSetSourceEvent(S s, C c) {
        Objects.requireNonNull(s);
        this.source = s;
        this.context = c;
    }
}
