package org.spongycastle.cms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1OctetStringParser;
import org.spongycastle.asn1.ASN1SequenceParser;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1SetParser;
import org.spongycastle.asn1.DERSet;
import org.spongycastle.asn1.cms.AttributeTable;
import org.spongycastle.asn1.cms.EncryptedContentInfoParser;
import org.spongycastle.asn1.cms.EnvelopedDataParser;
import org.spongycastle.asn1.cms.OriginatorInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.cms.CMSEnvelopedHelper;

/* loaded from: classes4.dex */
public class CMSEnvelopedDataParser extends CMSContentInfoParser {
    private boolean attrNotRead;
    private AlgorithmIdentifier encAlg;
    EnvelopedDataParser envelopedData;
    private OriginatorInformation originatorInfo;
    RecipientInformationStore recipientInfoStore;
    private AttributeTable unprotectedAttributes;

    public AlgorithmIdentifier getContentEncryptionAlgorithm() {
        return this.encAlg;
    }

    public OriginatorInformation getOriginatorInfo() {
        return this.originatorInfo;
    }

    public RecipientInformationStore getRecipientInfos() {
        return this.recipientInfoStore;
    }

    public CMSEnvelopedDataParser(byte[] bArr) throws IOException, CMSException {
        this(new ByteArrayInputStream(bArr));
    }

    public CMSEnvelopedDataParser(InputStream inputStream) throws IOException, CMSException {
        super(inputStream);
        this.attrNotRead = true;
        EnvelopedDataParser envelopedDataParser = new EnvelopedDataParser((ASN1SequenceParser) this._contentInfo.getContent(16));
        this.envelopedData = envelopedDataParser;
        OriginatorInfo originatorInfo = envelopedDataParser.getOriginatorInfo();
        if (originatorInfo != null) {
            this.originatorInfo = new OriginatorInformation(originatorInfo);
        }
        ASN1Set aSN1Set = ASN1Set.getInstance(this.envelopedData.getRecipientInfos().toASN1Primitive());
        EncryptedContentInfoParser encryptedContentInfo = this.envelopedData.getEncryptedContentInfo();
        this.encAlg = encryptedContentInfo.getContentEncryptionAlgorithm();
        this.recipientInfoStore = CMSEnvelopedHelper.buildRecipientInformationStore(aSN1Set, this.encAlg, new CMSEnvelopedHelper.CMSEnvelopedSecureReadable(this.encAlg, new CMSProcessableInputStream(((ASN1OctetStringParser) encryptedContentInfo.getEncryptedContent(4)).getOctetStream())));
    }

    public String getEncryptionAlgOID() {
        return this.encAlg.getAlgorithm().toString();
    }

    public byte[] getEncryptionAlgParams() {
        try {
            return encodeObj(this.encAlg.getParameters());
        } catch (Exception e) {
            throw new RuntimeException("exception getting encryption parameters " + e);
        }
    }

    public AttributeTable getUnprotectedAttributes() throws IOException {
        if (this.unprotectedAttributes == null && this.attrNotRead) {
            ASN1SetParser unprotectedAttrs = this.envelopedData.getUnprotectedAttrs();
            this.attrNotRead = false;
            if (unprotectedAttrs != null) {
                ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                while (true) {
                    ASN1Encodable object = unprotectedAttrs.readObject();
                    if (object == null) {
                        break;
                    }
                    aSN1EncodableVector.add(((ASN1SequenceParser) object).toASN1Primitive());
                }
                this.unprotectedAttributes = new AttributeTable(new DERSet(aSN1EncodableVector));
            }
        }
        return this.unprotectedAttributes;
    }

    private byte[] encodeObj(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable != null) {
            return aSN1Encodable.toASN1Primitive().getEncoded();
        }
        return null;
    }
}
