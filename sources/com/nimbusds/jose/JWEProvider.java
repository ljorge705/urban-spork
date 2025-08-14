package com.nimbusds.jose;

import com.nimbusds.jose.jca.JCAAware;
import com.nimbusds.jose.jca.JWEJCAContext;
import java.util.Set;

/* loaded from: classes2.dex */
public interface JWEProvider extends JOSEProvider, JCAAware<JWEJCAContext> {
    Set<EncryptionMethod> supportedEncryptionMethods();

    Set<JWEAlgorithm> supportedJWEAlgorithms();
}
