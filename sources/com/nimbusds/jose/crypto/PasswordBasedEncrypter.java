package com.nimbusds.jose.crypto;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWECryptoParts;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.impl.AAD;
import com.nimbusds.jose.crypto.impl.AESKW;
import com.nimbusds.jose.crypto.impl.ContentCryptoProvider;
import com.nimbusds.jose.crypto.impl.JWEHeaderValidation;
import com.nimbusds.jose.crypto.impl.PBKDF2;
import com.nimbusds.jose.crypto.impl.PRFParams;
import com.nimbusds.jose.crypto.impl.PasswordBasedCryptoProvider;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.StandardCharset;
import java.util.Arrays;
import javax.crypto.SecretKey;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class PasswordBasedEncrypter extends PasswordBasedCryptoProvider implements JWEEncrypter {
    public static final int MIN_RECOMMENDED_ITERATION_COUNT = 1000;
    public static final int MIN_SALT_LENGTH = 8;
    private final int iterationCount;
    private final int saltLength;

    public int getIterationCount() {
        return this.iterationCount;
    }

    public int getSaltLength() {
        return this.saltLength;
    }

    public PasswordBasedEncrypter(byte[] bArr, int i, int i2) {
        super(bArr);
        if (i < 8) {
            throw new IllegalArgumentException("The minimum salt length (p2s) is 8 bytes");
        }
        this.saltLength = i;
        if (i2 < 1000) {
            throw new IllegalArgumentException("The minimum recommended iteration count (p2c) is 1000");
        }
        this.iterationCount = i2;
    }

    public PasswordBasedEncrypter(String str, int i, int i2) {
        this(str.getBytes(StandardCharset.UTF_8), i, i2);
    }

    @Deprecated
    public JWECryptoParts encrypt(JWEHeader jWEHeader, byte[] bArr) throws JOSEException {
        return encrypt(jWEHeader, bArr, AAD.compute(jWEHeader));
    }

    @Override // com.nimbusds.jose.JWEEncrypter
    public JWECryptoParts encrypt(JWEHeader jWEHeader, byte[] bArr, byte[] bArr2) throws IllegalStateException, JOSEException {
        JWEAlgorithm algorithmAndEnsureNotNull = JWEHeaderValidation.getAlgorithmAndEnsureNotNull(jWEHeader);
        EncryptionMethod encryptionMethod = jWEHeader.getEncryptionMethod();
        byte[] bArr3 = new byte[this.saltLength];
        getJCAContext().getSecureRandom().nextBytes(bArr3);
        SecretKey secretKeyDeriveKey = PBKDF2.deriveKey(getPassword(), PBKDF2.formatSalt(algorithmAndEnsureNotNull, bArr3), this.iterationCount, PRFParams.resolve(algorithmAndEnsureNotNull, getJCAContext().getMACProvider()));
        JWEHeader jWEHeaderBuild = new JWEHeader.Builder(jWEHeader).pbes2Salt(Base64URL.encode(bArr3)).pbes2Count(this.iterationCount).build();
        SecretKey secretKeyGenerateCEK = ContentCryptoProvider.generateCEK(encryptionMethod, getJCAContext().getSecureRandom());
        Base64URL base64URLEncode = Base64URL.encode(AESKW.wrapCEK(secretKeyGenerateCEK, secretKeyDeriveKey, getJCAContext().getKeyEncryptionProvider()));
        if (Arrays.equals(AAD.compute(jWEHeader), bArr2)) {
            bArr2 = AAD.compute(jWEHeaderBuild);
        }
        return ContentCryptoProvider.encrypt(jWEHeaderBuild, bArr, bArr2, secretKeyGenerateCEK, base64URLEncode, getJCAContext());
    }
}
