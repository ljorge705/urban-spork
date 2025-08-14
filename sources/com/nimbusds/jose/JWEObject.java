package com.nimbusds.jose;

import com.nimbusds.jose.crypto.impl.AAD;
import com.nimbusds.jose.util.Base64URL;
import java.text.ParseException;
import net.jcip.annotations.ThreadSafe;
import org.apache.commons.lang3.ClassUtils;

@ThreadSafe
/* loaded from: classes2.dex */
public class JWEObject extends JOSEObject {
    private static final long serialVersionUID = 1;
    private Base64URL authTag;
    private Base64URL cipherText;
    private Base64URL encryptedKey;
    private JWEHeader header;
    private Base64URL iv;
    private State state;

    public enum State {
        UNENCRYPTED,
        ENCRYPTED,
        DECRYPTED
    }

    public Base64URL getAuthTag() {
        return this.authTag;
    }

    public Base64URL getCipherText() {
        return this.cipherText;
    }

    public Base64URL getEncryptedKey() {
        return this.encryptedKey;
    }

    @Override // com.nimbusds.jose.JOSEObject
    public JWEHeader getHeader() {
        return this.header;
    }

    public Base64URL getIV() {
        return this.iv;
    }

    public State getState() {
        return this.state;
    }

    public JWEObject(JWEHeader jWEHeader, Payload payload) {
        if (jWEHeader == null) {
            throw new IllegalArgumentException("The JWE header must not be null");
        }
        this.header = jWEHeader;
        if (payload == null) {
            throw new IllegalArgumentException("The payload must not be null");
        }
        setPayload(payload);
        this.encryptedKey = null;
        this.cipherText = null;
        this.state = State.UNENCRYPTED;
    }

    public JWEObject(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, Base64URL base64URL5) throws ParseException {
        if (base64URL == null) {
            throw new IllegalArgumentException("The first part must not be null");
        }
        try {
            this.header = JWEHeader.parse(base64URL);
            if (base64URL2 == null || base64URL2.toString().isEmpty()) {
                this.encryptedKey = null;
            } else {
                this.encryptedKey = base64URL2;
            }
            if (base64URL3 == null || base64URL3.toString().isEmpty()) {
                this.iv = null;
            } else {
                this.iv = base64URL3;
            }
            if (base64URL4 == null) {
                throw new IllegalArgumentException("The fourth part must not be null");
            }
            this.cipherText = base64URL4;
            if (base64URL5 == null || base64URL5.toString().isEmpty()) {
                this.authTag = null;
            } else {
                this.authTag = base64URL5;
            }
            this.state = State.ENCRYPTED;
            setParsedParts(base64URL, base64URL2, base64URL3, base64URL4, base64URL5);
        } catch (ParseException e) {
            throw new ParseException("Invalid JWE header: " + e.getMessage(), 0);
        }
    }

    private void ensureUnencryptedState() {
        if (this.state != State.UNENCRYPTED) {
            throw new IllegalStateException("The JWE object must be in an unencrypted state");
        }
    }

    private void ensureEncryptedState() {
        if (this.state != State.ENCRYPTED) {
            throw new IllegalStateException("The JWE object must be in an encrypted state");
        }
    }

    private void ensureEncryptedOrDecryptedState() {
        if (this.state != State.ENCRYPTED && this.state != State.DECRYPTED) {
            throw new IllegalStateException("The JWE object must be in an encrypted or decrypted state");
        }
    }

    private void ensureJWEEncrypterSupport(JWEEncrypter jWEEncrypter) throws JOSEException {
        if (!jWEEncrypter.supportedJWEAlgorithms().contains(getHeader().getAlgorithm())) {
            throw new JOSEException("The " + getHeader().getAlgorithm() + " algorithm is not supported by the JWE encrypter: Supported algorithms: " + jWEEncrypter.supportedJWEAlgorithms());
        }
        if (!jWEEncrypter.supportedEncryptionMethods().contains(getHeader().getEncryptionMethod())) {
            throw new JOSEException("The " + getHeader().getEncryptionMethod() + " encryption method or key size is not supported by the JWE encrypter: Supported methods: " + jWEEncrypter.supportedEncryptionMethods());
        }
    }

    public synchronized void encrypt(JWEEncrypter jWEEncrypter) throws JOSEException {
        ensureUnencryptedState();
        ensureJWEEncrypterSupport(jWEEncrypter);
        try {
            try {
                JWECryptoParts jWECryptoPartsEncrypt = jWEEncrypter.encrypt(getHeader(), getPayload().toBytes(), AAD.compute(getHeader()));
                if (jWECryptoPartsEncrypt.getHeader() != null) {
                    this.header = jWECryptoPartsEncrypt.getHeader();
                }
                this.encryptedKey = jWECryptoPartsEncrypt.getEncryptedKey();
                this.iv = jWECryptoPartsEncrypt.getInitializationVector();
                this.cipherText = jWECryptoPartsEncrypt.getCipherText();
                this.authTag = jWECryptoPartsEncrypt.getAuthenticationTag();
                this.state = State.ENCRYPTED;
            } catch (Exception e) {
                throw new JOSEException(e.getMessage(), e);
            }
        } catch (JOSEException e2) {
            throw e2;
        }
    }

    public synchronized void decrypt(JWEDecrypter jWEDecrypter) throws JOSEException {
        ensureEncryptedState();
        try {
            try {
                setPayload(new Payload(jWEDecrypter.decrypt(getHeader(), getEncryptedKey(), getIV(), getCipherText(), getAuthTag(), AAD.compute(getHeader()))));
                this.state = State.DECRYPTED;
            } catch (JOSEException e) {
                throw e;
            }
        } catch (Exception e2) {
            throw new JOSEException(e2.getMessage(), e2);
        }
    }

    @Override // com.nimbusds.jose.JOSEObject
    public String serialize() {
        ensureEncryptedOrDecryptedState();
        StringBuilder sb = new StringBuilder(this.header.toBase64URL().toString());
        sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
        Base64URL base64URL = this.encryptedKey;
        if (base64URL != null) {
            sb.append(base64URL);
        }
        sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
        Base64URL base64URL2 = this.iv;
        if (base64URL2 != null) {
            sb.append(base64URL2);
        }
        sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
        sb.append(this.cipherText);
        sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
        Base64URL base64URL3 = this.authTag;
        if (base64URL3 != null) {
            sb.append(base64URL3);
        }
        return sb.toString();
    }

    public static JWEObject parse(String str) throws ParseException {
        Base64URL[] base64URLArrSplit = JOSEObject.split(str);
        if (base64URLArrSplit.length != 5) {
            throw new ParseException("Unexpected number of Base64URL parts, must be five", 0);
        }
        return new JWEObject(base64URLArrSplit[0], base64URLArrSplit[1], base64URLArrSplit[2], base64URLArrSplit[3], base64URLArrSplit[4]);
    }
}
