package org.spongycastle.cms;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.BEROctetString;
import org.spongycastle.asn1.BERSet;
import org.spongycastle.asn1.DERSet;
import org.spongycastle.asn1.cms.CMSObjectIdentifiers;
import org.spongycastle.asn1.cms.ContentInfo;
import org.spongycastle.asn1.cms.EncryptedContentInfo;
import org.spongycastle.asn1.cms.EnvelopedData;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.operator.GenericKey;
import org.spongycastle.operator.OutputEncryptor;

/* loaded from: classes4.dex */
public class CMSEnvelopedDataGenerator extends CMSEnvelopedGenerator {
    private CMSEnvelopedData doGenerate(CMSTypedData cMSTypedData, OutputEncryptor outputEncryptor) throws IOException, CMSException {
        if (!this.oldRecipientInfoGenerators.isEmpty()) {
            throw new IllegalStateException("can only use addRecipientGenerator() with this method");
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            OutputStream outputStream = outputEncryptor.getOutputStream(byteArrayOutputStream);
            cMSTypedData.write(outputStream);
            outputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            AlgorithmIdentifier algorithmIdentifier = outputEncryptor.getAlgorithmIdentifier();
            BEROctetString bEROctetString = new BEROctetString(byteArray);
            GenericKey key = outputEncryptor.getKey();
            Iterator it = this.recipientInfoGenerators.iterator();
            while (it.hasNext()) {
                aSN1EncodableVector.add(((RecipientInfoGenerator) it.next()).generate(key));
            }
            return new CMSEnvelopedData(new ContentInfo(CMSObjectIdentifiers.envelopedData, new EnvelopedData(this.originatorInfo, new DERSet(aSN1EncodableVector), new EncryptedContentInfo(cMSTypedData.getContentType(), algorithmIdentifier, bEROctetString), this.unprotectedAttributeGenerator != null ? new BERSet(this.unprotectedAttributeGenerator.getAttributes(new HashMap()).toASN1EncodableVector()) : null)));
        } catch (IOException unused) {
            throw new CMSException("");
        }
    }

    public CMSEnvelopedData generate(CMSTypedData cMSTypedData, OutputEncryptor outputEncryptor) throws CMSException {
        return doGenerate(cMSTypedData, outputEncryptor);
    }
}
