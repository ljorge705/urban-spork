package org.spongycastle.pkcs.jcajce;

import java.security.PrivateKey;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.pkcs.PKCS8EncryptedPrivateKeyInfoBuilder;

/* loaded from: classes7.dex */
public class JcaPKCS8EncryptedPrivateKeyInfoBuilder extends PKCS8EncryptedPrivateKeyInfoBuilder {
    public JcaPKCS8EncryptedPrivateKeyInfoBuilder(PrivateKey privateKey) {
        super(PrivateKeyInfo.getInstance(privateKey.getEncoded()));
    }
}
