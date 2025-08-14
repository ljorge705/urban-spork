package com.nimbusds.jose.crypto.factories;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.KeyTypeException;
import com.nimbusds.jose.crypto.AESDecrypter;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.ECDHDecrypter;
import com.nimbusds.jose.crypto.PasswordBasedDecrypter;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.jca.JWEJCAContext;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.proc.JWEDecrypterFactory;
import java.security.Key;
import java.security.PrivateKey;
import java.security.interfaces.ECKey;
import java.security.interfaces.RSAKey;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.crypto.SecretKey;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class DefaultJWEDecrypterFactory implements JWEDecrypterFactory {
    public static final Set<JWEAlgorithm> SUPPORTED_ALGORITHMS;
    public static final Set<EncryptionMethod> SUPPORTED_ENCRYPTION_METHODS;
    private final JWEJCAContext jcaContext = new JWEJCAContext();

    @Override // com.nimbusds.jose.jca.JCAAware
    public JWEJCAContext getJCAContext() {
        return this.jcaContext;
    }

    @Override // com.nimbusds.jose.JWEProvider
    public Set<EncryptionMethod> supportedEncryptionMethods() {
        return SUPPORTED_ENCRYPTION_METHODS;
    }

    @Override // com.nimbusds.jose.JWEProvider
    public Set<JWEAlgorithm> supportedJWEAlgorithms() {
        return SUPPORTED_ALGORITHMS;
    }

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(RSADecrypter.SUPPORTED_ALGORITHMS);
        linkedHashSet.addAll(ECDHDecrypter.SUPPORTED_ALGORITHMS);
        linkedHashSet.addAll(DirectDecrypter.SUPPORTED_ALGORITHMS);
        linkedHashSet.addAll(AESDecrypter.SUPPORTED_ALGORITHMS);
        linkedHashSet.addAll(PasswordBasedDecrypter.SUPPORTED_ALGORITHMS);
        SUPPORTED_ALGORITHMS = Collections.unmodifiableSet(linkedHashSet);
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        linkedHashSet2.addAll(RSADecrypter.SUPPORTED_ENCRYPTION_METHODS);
        linkedHashSet2.addAll(ECDHDecrypter.SUPPORTED_ENCRYPTION_METHODS);
        linkedHashSet2.addAll(DirectDecrypter.SUPPORTED_ENCRYPTION_METHODS);
        linkedHashSet2.addAll(AESDecrypter.SUPPORTED_ENCRYPTION_METHODS);
        linkedHashSet2.addAll(PasswordBasedDecrypter.SUPPORTED_ENCRYPTION_METHODS);
        SUPPORTED_ENCRYPTION_METHODS = Collections.unmodifiableSet(linkedHashSet2);
    }

    @Override // com.nimbusds.jose.proc.JWEDecrypterFactory
    public JWEDecrypter createJWEDecrypter(JWEHeader jWEHeader, Key key) throws JOSEException {
        JWEDecrypter passwordBasedDecrypter;
        JWEDecrypter aESDecrypter;
        if (RSADecrypter.SUPPORTED_ALGORITHMS.contains(jWEHeader.getAlgorithm()) && RSADecrypter.SUPPORTED_ENCRYPTION_METHODS.contains(jWEHeader.getEncryptionMethod())) {
            if (!(key instanceof PrivateKey) || !(key instanceof RSAKey)) {
                throw new KeyTypeException(PrivateKey.class, RSAKey.class);
            }
            passwordBasedDecrypter = new RSADecrypter((PrivateKey) key);
        } else {
            if (ECDHDecrypter.SUPPORTED_ALGORITHMS.contains(jWEHeader.getAlgorithm()) && ECDHDecrypter.SUPPORTED_ENCRYPTION_METHODS.contains(jWEHeader.getEncryptionMethod())) {
                if (!(key instanceof PrivateKey) || !(key instanceof ECKey)) {
                    throw new KeyTypeException(PrivateKey.class, ECKey.class);
                }
                aESDecrypter = new ECDHDecrypter((PrivateKey) key, null, Curve.forECParameterSpec(((ECKey) key).getParams()));
            } else if (DirectDecrypter.SUPPORTED_ALGORITHMS.contains(jWEHeader.getAlgorithm()) && DirectDecrypter.SUPPORTED_ENCRYPTION_METHODS.contains(jWEHeader.getEncryptionMethod())) {
                if (!(key instanceof SecretKey)) {
                    throw new KeyTypeException(SecretKey.class);
                }
                aESDecrypter = new DirectDecrypter((SecretKey) key);
                if (!aESDecrypter.supportedEncryptionMethods().contains(jWEHeader.getEncryptionMethod())) {
                    throw new KeyLengthException(jWEHeader.getEncryptionMethod().cekBitLength(), jWEHeader.getEncryptionMethod());
                }
            } else if (AESDecrypter.SUPPORTED_ALGORITHMS.contains(jWEHeader.getAlgorithm()) && AESDecrypter.SUPPORTED_ENCRYPTION_METHODS.contains(jWEHeader.getEncryptionMethod())) {
                if (!(key instanceof SecretKey)) {
                    throw new KeyTypeException(SecretKey.class);
                }
                aESDecrypter = new AESDecrypter((SecretKey) key);
                if (!aESDecrypter.supportedJWEAlgorithms().contains(jWEHeader.getAlgorithm())) {
                    throw new KeyLengthException(jWEHeader.getAlgorithm());
                }
            } else if (PasswordBasedDecrypter.SUPPORTED_ALGORITHMS.contains(jWEHeader.getAlgorithm()) && PasswordBasedDecrypter.SUPPORTED_ENCRYPTION_METHODS.contains(jWEHeader.getEncryptionMethod())) {
                if (!(key instanceof SecretKey)) {
                    throw new KeyTypeException(SecretKey.class);
                }
                passwordBasedDecrypter = new PasswordBasedDecrypter(key.getEncoded());
            } else {
                throw new JOSEException("Unsupported JWE algorithm or encryption method");
            }
            passwordBasedDecrypter = aESDecrypter;
        }
        passwordBasedDecrypter.getJCAContext().setSecureRandom(this.jcaContext.getSecureRandom());
        passwordBasedDecrypter.getJCAContext().setProvider(this.jcaContext.getProvider());
        passwordBasedDecrypter.getJCAContext().setKeyEncryptionProvider(this.jcaContext.getKeyEncryptionProvider());
        passwordBasedDecrypter.getJCAContext().setMACProvider(this.jcaContext.getMACProvider());
        passwordBasedDecrypter.getJCAContext().setContentEncryptionProvider(this.jcaContext.getContentEncryptionProvider());
        return passwordBasedDecrypter;
    }
}
