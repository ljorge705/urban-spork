package com.nimbusds.jose.jwk;

import java.util.LinkedList;
import java.util.List;
import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes2.dex */
public final class JWKSelector {
    private final JWKMatcher matcher;

    public JWKMatcher getMatcher() {
        return this.matcher;
    }

    public JWKSelector(JWKMatcher jWKMatcher) {
        if (jWKMatcher == null) {
            throw new IllegalArgumentException("The JWK matcher must not be null");
        }
        this.matcher = jWKMatcher;
    }

    public List<JWK> select(JWKSet jWKSet) {
        LinkedList linkedList = new LinkedList();
        if (jWKSet == null) {
            return linkedList;
        }
        for (JWK jwk : jWKSet.getKeys()) {
            if (this.matcher.matches(jwk)) {
                linkedList.add(jwk);
            }
        }
        return linkedList;
    }
}
