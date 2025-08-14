package com.nimbusds.jose.crypto;

import com.nimbusds.jose.CriticalHeaderParamsAware;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.impl.AAD;
import com.nimbusds.jose.crypto.impl.AESKW;
import com.nimbusds.jose.crypto.impl.ContentCryptoProvider;
import com.nimbusds.jose.crypto.impl.CriticalHeaderParamsDeferral;
import com.nimbusds.jose.crypto.impl.JWEHeaderValidation;
import com.nimbusds.jose.crypto.impl.PBKDF2;
import com.nimbusds.jose.crypto.impl.PRFParams;
import com.nimbusds.jose.crypto.impl.PasswordBasedCryptoProvider;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.StandardCharset;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class PasswordBasedDecrypter extends PasswordBasedCryptoProvider implements JWEDecrypter, CriticalHeaderParamsAware {
    public static final int MAX_ALLOWED_ITERATION_COUNT = 1000000;
    private final CriticalHeaderParamsDeferral critPolicy;

    public PasswordBasedDecrypter(byte[] bArr) {
        super(bArr);
        this.critPolicy = new CriticalHeaderParamsDeferral();
    }

    public PasswordBasedDecrypter(String str) {
        super(str.getBytes(StandardCharset.UTF_8));
        this.critPolicy = new CriticalHeaderParamsDeferral();
    }

    @Override // com.nimbusds.jose.CriticalHeaderParamsAware
    public Set<String> getProcessedCriticalHeaderParams() {
        return this.critPolicy.getProcessedCriticalHeaderParams();
    }

    @Override // com.nimbusds.jose.CriticalHeaderParamsAware
    public Set<String> getDeferredCriticalHeaderParams() {
        return this.critPolicy.getProcessedCriticalHeaderParams();
    }

    @Deprecated
    public byte[] decrypt(JWEHeader jWEHeader, Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4) throws JOSEException {
        return decrypt(jWEHeader, base64URL, base64URL2, base64URL3, base64URL4, AAD.compute(jWEHeader));
    }

    @Override // com.nimbusds.jose.JWEDecrypter
    public byte[] decrypt(JWEHeader jWEHeader, Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, byte[] bArr) throws JOSEException {
        if (base64URL == null) {
            throw new JOSEException("Missing JWE encrypted key");
        }
        if (base64URL2 == null) {
            throw new JOSEException("Missing JWE initialization vector (IV)");
        }
        if (base64URL4 == null) {
            throw new JOSEException("Missing JWE authentication tag");
        }
        if (jWEHeader.getPBES2Salt() == null) {
            throw new JOSEException("Missing JWE p2s header parameter");
        }
        byte[] bArrDecode = jWEHeader.getPBES2Salt().decode();
        if (jWEHeader.getPBES2Count() < 1) {
            throw new JOSEException("Missing JWE p2c header parameter");
        }
        int pBES2Count = jWEHeader.getPBES2Count();
        if (pBES2Count > 1000000) {
            throw new JOSEException("The JWE p2c header exceeds the maximum allowed 1000000 count");
        }
        this.critPolicy.ensureHeaderPasses(jWEHeader);
        JWEAlgorithm algorithmAndEnsureNotNull = JWEHeaderValidation.getAlgorithmAndEnsureNotNull(jWEHeader);
        return ContentCryptoProvider.decrypt(jWEHeader, bArr, base64URL, base64URL2, base64URL3, base64URL4, AESKW.unwrapCEK(PBKDF2.deriveKey(getPassword(), PBKDF2.formatSalt(algorithmAndEnsureNotNull, bArrDecode), pBES2Count, PRFParams.resolve(algorithmAndEnsureNotNull, getJCAContext().getMACProvider())), base64URL.decode(), getJCAContext().getKeyEncryptionProvider()), getJCAContext());
    }
}
