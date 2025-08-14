package com.nimbusds.jose.proc;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.proc.SecurityContext;
import java.security.Key;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public class SingleKeyJWSKeySelector<C extends SecurityContext> implements JWSKeySelector<C> {
    private final JWSAlgorithm expectedJWSAlg;
    private final List<Key> singletonKeyList;

    public SingleKeyJWSKeySelector(JWSAlgorithm jWSAlgorithm, Key key) {
        if (jWSAlgorithm == null) {
            throw new IllegalArgumentException("The expected JWS algorithm cannot be null");
        }
        if (key == null) {
            throw new IllegalArgumentException("The key cannot be null");
        }
        this.singletonKeyList = Collections.singletonList(key);
        this.expectedJWSAlg = jWSAlgorithm;
    }

    @Override // com.nimbusds.jose.proc.JWSKeySelector
    public List<? extends Key> selectJWSKeys(JWSHeader jWSHeader, C c) {
        return !this.expectedJWSAlg.equals(jWSHeader.getAlgorithm()) ? Collections.emptyList() : this.singletonKeyList;
    }
}
