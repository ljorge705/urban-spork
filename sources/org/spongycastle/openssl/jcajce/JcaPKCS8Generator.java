package org.spongycastle.openssl.jcajce;

import java.security.PrivateKey;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.openssl.PKCS8Generator;
import org.spongycastle.operator.OutputEncryptor;
import org.spongycastle.util.io.pem.PemGenerationException;

/* loaded from: classes7.dex */
public class JcaPKCS8Generator extends PKCS8Generator {
    public JcaPKCS8Generator(PrivateKey privateKey, OutputEncryptor outputEncryptor) throws PemGenerationException {
        super(PrivateKeyInfo.getInstance(privateKey.getEncoded()), outputEncryptor);
    }
}
