package com.nimbusds.jose.proc;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObject;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.PlainObject;
import com.nimbusds.jose.crypto.factories.DefaultJWEDecrypterFactory;
import com.nimbusds.jose.crypto.factories.DefaultJWSVerifierFactory;
import com.nimbusds.jose.proc.SecurityContext;
import java.security.Key;
import java.text.ParseException;
import java.util.List;
import java.util.ListIterator;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class DefaultJOSEProcessor<C extends SecurityContext> implements ConfigurableJOSEProcessor<C> {
    private JWEKeySelector<C> jweKeySelector;
    private JWSKeySelector<C> jwsKeySelector;
    private JOSEObjectTypeVerifier<C> jwsTypeVerifier = DefaultJOSEObjectTypeVerifier.JOSE;
    private JOSEObjectTypeVerifier<C> jweTypeVerifier = DefaultJOSEObjectTypeVerifier.JOSE;
    private JWSVerifierFactory jwsVerifierFactory = new DefaultJWSVerifierFactory();
    private JWEDecrypterFactory jweDecrypterFactory = new DefaultJWEDecrypterFactory();

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public JWEDecrypterFactory getJWEDecrypterFactory() {
        return this.jweDecrypterFactory;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public JWEKeySelector<C> getJWEKeySelector() {
        return this.jweKeySelector;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public JOSEObjectTypeVerifier<C> getJWETypeVerifier() {
        return this.jweTypeVerifier;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public JWSKeySelector<C> getJWSKeySelector() {
        return this.jwsKeySelector;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public JOSEObjectTypeVerifier<C> getJWSTypeVerifier() {
        return this.jwsTypeVerifier;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public JWSVerifierFactory getJWSVerifierFactory() {
        return this.jwsVerifierFactory;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public void setJWEDecrypterFactory(JWEDecrypterFactory jWEDecrypterFactory) {
        this.jweDecrypterFactory = jWEDecrypterFactory;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public void setJWEKeySelector(JWEKeySelector<C> jWEKeySelector) {
        this.jweKeySelector = jWEKeySelector;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public void setJWETypeVerifier(JOSEObjectTypeVerifier<C> jOSEObjectTypeVerifier) {
        this.jweTypeVerifier = jOSEObjectTypeVerifier;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public void setJWSKeySelector(JWSKeySelector<C> jWSKeySelector) {
        this.jwsKeySelector = jWSKeySelector;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public void setJWSTypeVerifier(JOSEObjectTypeVerifier<C> jOSEObjectTypeVerifier) {
        this.jwsTypeVerifier = jOSEObjectTypeVerifier;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessorConfiguration
    public void setJWSVerifierFactory(JWSVerifierFactory jWSVerifierFactory) {
        this.jwsVerifierFactory = jWSVerifierFactory;
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessor
    public Payload process(String str, C c) throws BadJOSEException, JOSEException, ParseException {
        return process(JOSEObject.parse(str), (JOSEObject) c);
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessor
    public Payload process(JOSEObject jOSEObject, C c) throws BadJOSEException, JOSEException {
        if (jOSEObject instanceof JWSObject) {
            return process((JWSObject) jOSEObject, (JWSObject) c);
        }
        if (jOSEObject instanceof JWEObject) {
            return process((JWEObject) jOSEObject, (JWEObject) c);
        }
        if (jOSEObject instanceof PlainObject) {
            return process((PlainObject) jOSEObject, (PlainObject) c);
        }
        throw new JOSEException("Unexpected JOSE object type: " + jOSEObject.getClass());
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessor
    public Payload process(PlainObject plainObject, C c) throws BadJOSEException {
        JOSEObjectTypeVerifier<C> jOSEObjectTypeVerifier = this.jwsTypeVerifier;
        if (jOSEObjectTypeVerifier == null) {
            throw new BadJOSEException("Unsecured (plain) JOSE object rejected: No JWS header typ (type) verifier is configured");
        }
        jOSEObjectTypeVerifier.verify(plainObject.getHeader().getType(), c);
        throw new BadJOSEException("Unsecured (plain) JOSE objects are rejected, extend class to handle");
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessor
    public Payload process(JWSObject jWSObject, C c) throws BadJOSEException, JOSEException {
        JOSEObjectTypeVerifier<C> jOSEObjectTypeVerifier = this.jwsTypeVerifier;
        if (jOSEObjectTypeVerifier == null) {
            throw new BadJOSEException("JWS object rejected: No JWS header typ (type) verifier is configured");
        }
        jOSEObjectTypeVerifier.verify(jWSObject.getHeader().getType(), c);
        if (getJWSKeySelector() == null) {
            throw new BadJOSEException("JWS object rejected: No JWS key selector is configured");
        }
        if (getJWSVerifierFactory() == null) {
            throw new JOSEException("No JWS verifier is configured");
        }
        List<? extends Key> listSelectJWSKeys = getJWSKeySelector().selectJWSKeys(jWSObject.getHeader(), c);
        if (listSelectJWSKeys == null || listSelectJWSKeys.isEmpty()) {
            throw new BadJOSEException("JWS object rejected: Another algorithm expected, or no matching key(s) found");
        }
        ListIterator<? extends Key> listIterator = listSelectJWSKeys.listIterator();
        while (listIterator.hasNext()) {
            JWSVerifier jWSVerifierCreateJWSVerifier = getJWSVerifierFactory().createJWSVerifier(jWSObject.getHeader(), listIterator.next());
            if (jWSVerifierCreateJWSVerifier != null) {
                if (jWSObject.verify(jWSVerifierCreateJWSVerifier)) {
                    return jWSObject.getPayload();
                }
                if (!listIterator.hasNext()) {
                    throw new BadJWSException("JWS object rejected: Invalid signature");
                }
            }
        }
        throw new BadJOSEException("JWS object rejected: No matching verifier(s) found");
    }

    @Override // com.nimbusds.jose.proc.JOSEProcessor
    public Payload process(JWEObject jWEObject, C c) throws BadJOSEException, JOSEException {
        JOSEObjectTypeVerifier<C> jOSEObjectTypeVerifier = this.jweTypeVerifier;
        if (jOSEObjectTypeVerifier == null) {
            throw new BadJOSEException("JWE object rejected: No JWE header typ (type) verifier is configured");
        }
        jOSEObjectTypeVerifier.verify(jWEObject.getHeader().getType(), c);
        if (getJWEKeySelector() == null) {
            throw new BadJOSEException("JWE object rejected: No JWE key selector is configured");
        }
        if (getJWEDecrypterFactory() == null) {
            throw new JOSEException("No JWE decrypter is configured");
        }
        List<? extends Key> listSelectJWEKeys = getJWEKeySelector().selectJWEKeys(jWEObject.getHeader(), c);
        if (listSelectJWEKeys == null || listSelectJWEKeys.isEmpty()) {
            throw new BadJOSEException("JWE object rejected: Another algorithm expected, or no matching key(s) found");
        }
        ListIterator<? extends Key> listIterator = listSelectJWEKeys.listIterator();
        while (listIterator.hasNext()) {
            JWEDecrypter jWEDecrypterCreateJWEDecrypter = getJWEDecrypterFactory().createJWEDecrypter(jWEObject.getHeader(), listIterator.next());
            if (jWEDecrypterCreateJWEDecrypter != null) {
                try {
                    jWEObject.decrypt(jWEDecrypterCreateJWEDecrypter);
                    if ("JWT".equalsIgnoreCase(jWEObject.getHeader().getContentType())) {
                        JWSObject jWSObject = jWEObject.getPayload().toJWSObject();
                        if (jWSObject == null) {
                            return jWEObject.getPayload();
                        }
                        return process(jWSObject, (JWSObject) c);
                    }
                    return jWEObject.getPayload();
                } catch (JOSEException e) {
                    if (!listIterator.hasNext()) {
                        throw new BadJWEException("JWE object rejected: " + e.getMessage(), e);
                    }
                }
            }
        }
        throw new BadJOSEException("JWE object rejected: No matching decrypter(s) found");
    }
}
