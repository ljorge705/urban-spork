package org.spongycastle.pkcs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.spongycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.operator.OutputEncryptor;

/* loaded from: classes7.dex */
public class PKCS8EncryptedPrivateKeyInfoBuilder {
    private PrivateKeyInfo privateKeyInfo;

    public PKCS8EncryptedPrivateKeyInfoBuilder(PrivateKeyInfo privateKeyInfo) {
        this.privateKeyInfo = privateKeyInfo;
    }

    public PKCS8EncryptedPrivateKeyInfo build(OutputEncryptor outputEncryptor) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            OutputStream outputStream = outputEncryptor.getOutputStream(byteArrayOutputStream);
            outputStream.write(this.privateKeyInfo.getEncoded());
            outputStream.close();
            return new PKCS8EncryptedPrivateKeyInfo(new EncryptedPrivateKeyInfo(outputEncryptor.getAlgorithmIdentifier(), byteArrayOutputStream.toByteArray()));
        } catch (IOException unused) {
            throw new IllegalStateException("cannot encode privateKeyInfo");
        }
    }
}
