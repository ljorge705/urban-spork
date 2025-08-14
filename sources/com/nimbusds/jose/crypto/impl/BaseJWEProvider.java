package com.nimbusds.jose.crypto.impl;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEProvider;
import com.nimbusds.jose.jca.JWEJCAContext;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.crypto.SecretKey;

/* loaded from: classes2.dex */
public abstract class BaseJWEProvider implements JWEProvider {
    private static final Set<String> ACCEPTABLE_CEK_ALGS = Collections.unmodifiableSet(new HashSet(Arrays.asList(CipherStorageKeystoreAesCbc.ALGORITHM_AES, "ChaCha20")));
    private final Set<JWEAlgorithm> algs;
    private final SecretKey cek;
    private final Set<EncryptionMethod> encs;
    private final JWEJCAContext jcaContext;

    @Override // com.nimbusds.jose.jca.JCAAware
    public JWEJCAContext getJCAContext() {
        return this.jcaContext;
    }

    protected boolean isCEKProvided() {
        return this.cek != null;
    }

    @Override // com.nimbusds.jose.JWEProvider
    public Set<EncryptionMethod> supportedEncryptionMethods() {
        return this.encs;
    }

    @Override // com.nimbusds.jose.JWEProvider
    public Set<JWEAlgorithm> supportedJWEAlgorithms() {
        return this.algs;
    }

    public BaseJWEProvider(Set<JWEAlgorithm> set, Set<EncryptionMethod> set2) {
        this(set, set2, null);
    }

    public BaseJWEProvider(Set<JWEAlgorithm> set, Set<EncryptionMethod> set2, SecretKey secretKey) {
        this.jcaContext = new JWEJCAContext();
        if (set == null) {
            throw new IllegalArgumentException("The supported JWE algorithm set must not be null");
        }
        this.algs = Collections.unmodifiableSet(set);
        if (set2 == null) {
            throw new IllegalArgumentException("The supported encryption methods must not be null");
        }
        this.encs = set2;
        if (secretKey != null && set.size() > 1 && (secretKey.getAlgorithm() == null || !ACCEPTABLE_CEK_ALGS.contains(secretKey.getAlgorithm()))) {
            throw new IllegalArgumentException("The algorithm of the content encryption key (CEK) must be AES or ChaCha20");
        }
        this.cek = secretKey;
    }

    protected SecretKey getCEK(EncryptionMethod encryptionMethod) throws JOSEException {
        return (isCEKProvided() || encryptionMethod == null) ? this.cek : ContentCryptoProvider.generateCEK(encryptionMethod, this.jcaContext.getSecureRandom());
    }
}
