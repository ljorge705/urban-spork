package org.spongycastle.cms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1OctetStringParser;
import org.spongycastle.asn1.ASN1SequenceParser;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1SetParser;
import org.spongycastle.asn1.DERSet;
import org.spongycastle.asn1.cms.AttributeTable;
import org.spongycastle.asn1.cms.AuthenticatedDataParser;
import org.spongycastle.asn1.cms.CMSAttributes;
import org.spongycastle.asn1.cms.OriginatorInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.cms.CMSEnvelopedHelper;
import org.spongycastle.operator.DigestCalculatorProvider;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.util.Arrays;

/* loaded from: classes4.dex */
public class CMSAuthenticatedDataParser extends CMSContentInfoParser {
    private boolean authAttrNotRead;
    private ASN1Set authAttrSet;
    private AttributeTable authAttrs;
    AuthenticatedDataParser authData;
    private byte[] mac;
    private AlgorithmIdentifier macAlg;
    private OriginatorInformation originatorInfo;
    RecipientInformationStore recipientInfoStore;
    private boolean unauthAttrNotRead;
    private AttributeTable unauthAttrs;

    public AlgorithmIdentifier getMacAlgorithm() {
        return this.macAlg;
    }

    public OriginatorInformation getOriginatorInfo() {
        return this.originatorInfo;
    }

    public RecipientInformationStore getRecipientInfos() {
        return this.recipientInfoStore;
    }

    public CMSAuthenticatedDataParser(byte[] bArr) throws IOException, CMSException {
        this(new ByteArrayInputStream(bArr));
    }

    public CMSAuthenticatedDataParser(byte[] bArr, DigestCalculatorProvider digestCalculatorProvider) throws IOException, CMSException {
        this(new ByteArrayInputStream(bArr), digestCalculatorProvider);
    }

    public CMSAuthenticatedDataParser(InputStream inputStream) throws IOException, CMSException {
        this(inputStream, (DigestCalculatorProvider) null);
    }

    public CMSAuthenticatedDataParser(InputStream inputStream, DigestCalculatorProvider digestCalculatorProvider) throws IOException, CMSException {
        super(inputStream);
        this.authAttrNotRead = true;
        AuthenticatedDataParser authenticatedDataParser = new AuthenticatedDataParser((ASN1SequenceParser) this._contentInfo.getContent(16));
        this.authData = authenticatedDataParser;
        OriginatorInfo originatorInfo = authenticatedDataParser.getOriginatorInfo();
        if (originatorInfo != null) {
            this.originatorInfo = new OriginatorInformation(originatorInfo);
        }
        ASN1Set aSN1Set = ASN1Set.getInstance(this.authData.getRecipientInfos().toASN1Primitive());
        this.macAlg = this.authData.getMacAlgorithm();
        AlgorithmIdentifier digestAlgorithm = this.authData.getDigestAlgorithm();
        if (digestAlgorithm == null) {
            this.recipientInfoStore = CMSEnvelopedHelper.buildRecipientInformationStore(aSN1Set, this.macAlg, new CMSEnvelopedHelper.CMSAuthenticatedSecureReadable(this.macAlg, new CMSProcessableInputStream(((ASN1OctetStringParser) this.authData.getEncapsulatedContentInfo().getContent(4)).getOctetStream())));
        } else {
            if (digestCalculatorProvider == null) {
                throw new CMSException("a digest calculator provider is required if authenticated attributes are present");
            }
            try {
                this.recipientInfoStore = CMSEnvelopedHelper.buildRecipientInformationStore(aSN1Set, this.macAlg, new CMSEnvelopedHelper.CMSDigestAuthenticatedSecureReadable(digestCalculatorProvider.get(digestAlgorithm), new CMSProcessableInputStream(((ASN1OctetStringParser) this.authData.getEncapsulatedContentInfo().getContent(4)).getOctetStream())), new AuthAttributesProvider() { // from class: org.spongycastle.cms.CMSAuthenticatedDataParser.1
                    @Override // org.spongycastle.cms.AuthAttributesProvider
                    public ASN1Set getAuthAttributes() {
                        try {
                            return CMSAuthenticatedDataParser.this.getAuthAttrSet();
                        } catch (IOException unused) {
                            throw new IllegalStateException("can't parse authenticated attributes!");
                        }
                    }
                });
            } catch (OperatorCreationException e) {
                throw new CMSException("unable to create digest calculator: " + e.getMessage(), e);
            }
        }
    }

    public String getMacAlgOID() {
        return this.macAlg.getAlgorithm().toString();
    }

    public byte[] getMacAlgParams() {
        try {
            return encodeObj(this.macAlg.getParameters());
        } catch (Exception e) {
            throw new RuntimeException("exception getting encryption parameters " + e);
        }
    }

    public byte[] getMac() throws IOException {
        if (this.mac == null) {
            getAuthAttrs();
            this.mac = this.authData.getMac().getOctets();
        }
        return Arrays.clone(this.mac);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ASN1Set getAuthAttrSet() throws IOException {
        if (this.authAttrs == null && this.authAttrNotRead) {
            ASN1SetParser authAttrs = this.authData.getAuthAttrs();
            if (authAttrs != null) {
                this.authAttrSet = (ASN1Set) authAttrs.toASN1Primitive();
            }
            this.authAttrNotRead = false;
        }
        return this.authAttrSet;
    }

    public AttributeTable getAuthAttrs() throws IOException {
        ASN1Set authAttrSet;
        if (this.authAttrs == null && this.authAttrNotRead && (authAttrSet = getAuthAttrSet()) != null) {
            this.authAttrs = new AttributeTable(authAttrSet);
        }
        return this.authAttrs;
    }

    public AttributeTable getUnauthAttrs() throws IOException {
        if (this.unauthAttrs == null && this.unauthAttrNotRead) {
            ASN1SetParser unauthAttrs = this.authData.getUnauthAttrs();
            this.unauthAttrNotRead = false;
            if (unauthAttrs != null) {
                ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                while (true) {
                    ASN1Encodable object = unauthAttrs.readObject();
                    if (object == null) {
                        break;
                    }
                    aSN1EncodableVector.add(((ASN1SequenceParser) object).toASN1Primitive());
                }
                this.unauthAttrs = new AttributeTable(new DERSet(aSN1EncodableVector));
            }
        }
        return this.unauthAttrs;
    }

    private byte[] encodeObj(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable != null) {
            return aSN1Encodable.toASN1Primitive().getEncoded();
        }
        return null;
    }

    public byte[] getContentDigest() {
        AttributeTable attributeTable = this.authAttrs;
        if (attributeTable != null) {
            return ASN1OctetString.getInstance(attributeTable.get(CMSAttributes.messageDigest).getAttrValues().getObjectAt(0)).getOctets();
        }
        return null;
    }
}
