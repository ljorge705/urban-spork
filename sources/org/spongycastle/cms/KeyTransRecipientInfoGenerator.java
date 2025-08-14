package org.spongycastle.cms;

import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.cms.IssuerAndSerialNumber;
import org.spongycastle.asn1.cms.KeyTransRecipientInfo;
import org.spongycastle.asn1.cms.RecipientIdentifier;
import org.spongycastle.asn1.cms.RecipientInfo;
import org.spongycastle.operator.AsymmetricKeyWrapper;
import org.spongycastle.operator.GenericKey;
import org.spongycastle.operator.OperatorException;

/* loaded from: classes4.dex */
public abstract class KeyTransRecipientInfoGenerator implements RecipientInfoGenerator {
    private IssuerAndSerialNumber issuerAndSerial;
    private byte[] subjectKeyIdentifier;
    protected final AsymmetricKeyWrapper wrapper;

    protected KeyTransRecipientInfoGenerator(IssuerAndSerialNumber issuerAndSerialNumber, AsymmetricKeyWrapper asymmetricKeyWrapper) {
        this.issuerAndSerial = issuerAndSerialNumber;
        this.wrapper = asymmetricKeyWrapper;
    }

    protected KeyTransRecipientInfoGenerator(byte[] bArr, AsymmetricKeyWrapper asymmetricKeyWrapper) {
        this.subjectKeyIdentifier = bArr;
        this.wrapper = asymmetricKeyWrapper;
    }

    @Override // org.spongycastle.cms.RecipientInfoGenerator
    public final RecipientInfo generate(GenericKey genericKey) throws CMSException {
        RecipientIdentifier recipientIdentifier;
        try {
            byte[] bArrGenerateWrappedKey = this.wrapper.generateWrappedKey(genericKey);
            if (this.issuerAndSerial != null) {
                recipientIdentifier = new RecipientIdentifier(this.issuerAndSerial);
            } else {
                recipientIdentifier = new RecipientIdentifier((ASN1OctetString) new DEROctetString(this.subjectKeyIdentifier));
            }
            return new RecipientInfo(new KeyTransRecipientInfo(recipientIdentifier, this.wrapper.getAlgorithmIdentifier(), new DEROctetString(bArrGenerateWrappedKey)));
        } catch (OperatorException e) {
            throw new CMSException("exception wrapping content key: " + e.getMessage(), e);
        }
    }
}
