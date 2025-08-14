package org.spongycastle.cms;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import org.spongycastle.asn1.BEROctetString;
import org.spongycastle.asn1.BERSet;
import org.spongycastle.asn1.cms.CMSObjectIdentifiers;
import org.spongycastle.asn1.cms.ContentInfo;
import org.spongycastle.asn1.cms.EncryptedContentInfo;
import org.spongycastle.asn1.cms.EncryptedData;
import org.spongycastle.operator.OutputEncryptor;

/* loaded from: classes4.dex */
public class CMSEncryptedDataGenerator extends CMSEncryptedGenerator {
    private CMSEncryptedData doGenerate(CMSTypedData cMSTypedData, OutputEncryptor outputEncryptor) throws IOException, CMSException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            OutputStream outputStream = outputEncryptor.getOutputStream(byteArrayOutputStream);
            cMSTypedData.write(outputStream);
            outputStream.close();
            return new CMSEncryptedData(new ContentInfo(CMSObjectIdentifiers.encryptedData, new EncryptedData(new EncryptedContentInfo(cMSTypedData.getContentType(), outputEncryptor.getAlgorithmIdentifier(), new BEROctetString(byteArrayOutputStream.toByteArray())), this.unprotectedAttributeGenerator != null ? new BERSet(this.unprotectedAttributeGenerator.getAttributes(new HashMap()).toASN1EncodableVector()) : null)));
        } catch (IOException unused) {
            throw new CMSException("");
        }
    }

    public CMSEncryptedData generate(CMSTypedData cMSTypedData, OutputEncryptor outputEncryptor) throws CMSException {
        return doGenerate(cMSTypedData, outputEncryptor);
    }
}
