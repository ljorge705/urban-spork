package com.nimbusds.jose;

import com.nimbusds.jose.jca.JCAAware;
import com.nimbusds.jose.jca.JCAContext;
import java.util.Set;

/* loaded from: classes2.dex */
public interface JWSProvider extends JOSEProvider, JCAAware<JCAContext> {
    Set<JWSAlgorithm> supportedJWSAlgorithms();
}
