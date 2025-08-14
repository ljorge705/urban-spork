package org.spongycastle.cms;

import java.io.IOException;
import java.util.List;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.cms.IssuerAndSerialNumber;
import org.spongycastle.asn1.cms.KeyAgreeRecipientIdentifier;
import org.spongycastle.asn1.cms.KeyAgreeRecipientInfo;
import org.spongycastle.asn1.cms.OriginatorIdentifierOrKey;
import org.spongycastle.asn1.cms.OriginatorPublicKey;
import org.spongycastle.asn1.cms.RecipientEncryptedKey;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;

/* loaded from: classes4.dex */
public class KeyAgreeRecipientInformation extends RecipientInformation {
    private ASN1OctetString encryptedKey;
    private KeyAgreeRecipientInfo info;

    static void readRecipientInfo(List list, KeyAgreeRecipientInfo keyAgreeRecipientInfo, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        KeyAgreeRecipientId keyAgreeRecipientId;
        ASN1Sequence recipientEncryptedKeys = keyAgreeRecipientInfo.getRecipientEncryptedKeys();
        for (int i = 0; i < recipientEncryptedKeys.size(); i++) {
            RecipientEncryptedKey recipientEncryptedKey = RecipientEncryptedKey.getInstance(recipientEncryptedKeys.getObjectAt(i));
            KeyAgreeRecipientIdentifier identifier = recipientEncryptedKey.getIdentifier();
            IssuerAndSerialNumber issuerAndSerialNumber = identifier.getIssuerAndSerialNumber();
            if (issuerAndSerialNumber != null) {
                keyAgreeRecipientId = new KeyAgreeRecipientId(issuerAndSerialNumber.getName(), issuerAndSerialNumber.getSerialNumber().getValue());
            } else {
                keyAgreeRecipientId = new KeyAgreeRecipientId(identifier.getRKeyID().getSubjectKeyIdentifier().getOctets());
            }
            list.add(new KeyAgreeRecipientInformation(keyAgreeRecipientInfo, keyAgreeRecipientId, recipientEncryptedKey.getEncryptedKey(), algorithmIdentifier, cMSSecureReadable, authAttributesProvider));
        }
    }

    KeyAgreeRecipientInformation(KeyAgreeRecipientInfo keyAgreeRecipientInfo, RecipientId recipientId, ASN1OctetString aSN1OctetString, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        super(keyAgreeRecipientInfo.getKeyEncryptionAlgorithm(), algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        this.info = keyAgreeRecipientInfo;
        this.rid = recipientId;
        this.encryptedKey = aSN1OctetString;
    }

    private SubjectPublicKeyInfo getSenderPublicKeyInfo(AlgorithmIdentifier algorithmIdentifier, OriginatorIdentifierOrKey originatorIdentifierOrKey) throws IOException, CMSException {
        OriginatorId originatorId;
        OriginatorPublicKey originatorKey = originatorIdentifierOrKey.getOriginatorKey();
        if (originatorKey != null) {
            return getPublicKeyInfoFromOriginatorPublicKey(algorithmIdentifier, originatorKey);
        }
        IssuerAndSerialNumber issuerAndSerialNumber = originatorIdentifierOrKey.getIssuerAndSerialNumber();
        if (issuerAndSerialNumber != null) {
            originatorId = new OriginatorId(issuerAndSerialNumber.getName(), issuerAndSerialNumber.getSerialNumber().getValue());
        } else {
            originatorId = new OriginatorId(originatorIdentifierOrKey.getSubjectKeyIdentifier().getKeyIdentifier());
        }
        return getPublicKeyInfoFromOriginatorId(originatorId);
    }

    private SubjectPublicKeyInfo getPublicKeyInfoFromOriginatorPublicKey(AlgorithmIdentifier algorithmIdentifier, OriginatorPublicKey originatorPublicKey) {
        return new SubjectPublicKeyInfo(algorithmIdentifier, originatorPublicKey.getPublicKey().getBytes());
    }

    private SubjectPublicKeyInfo getPublicKeyInfoFromOriginatorId(OriginatorId originatorId) throws CMSException {
        throw new CMSException("No support for 'originator' as IssuerAndSerialNumber or SubjectKeyIdentifier");
    }

    @Override // org.spongycastle.cms.RecipientInformation
    protected RecipientOperator getRecipientOperator(Recipient recipient) throws IOException, CMSException {
        KeyAgreeRecipient keyAgreeRecipient = (KeyAgreeRecipient) recipient;
        return keyAgreeRecipient.getRecipientOperator(this.keyEncAlg, this.messageAlgorithm, getSenderPublicKeyInfo(keyAgreeRecipient.getPrivateKeyAlgorithmIdentifier(), this.info.getOriginator()), this.info.getUserKeyingMaterial(), this.encryptedKey.getOctets());
    }
}
