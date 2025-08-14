package org.spongycastle.cms;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.cms.KeyAgreeRecipientInfo;
import org.spongycastle.asn1.cms.OriginatorIdentifierOrKey;
import org.spongycastle.asn1.cms.OriginatorPublicKey;
import org.spongycastle.asn1.cms.RecipientInfo;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.operator.GenericKey;

/* loaded from: classes4.dex */
public abstract class KeyAgreeRecipientInfoGenerator implements RecipientInfoGenerator {
    private ASN1ObjectIdentifier keyAgreementOID;
    private ASN1ObjectIdentifier keyEncryptionOID;
    private SubjectPublicKeyInfo originatorKeyInfo;

    protected abstract ASN1Sequence generateRecipientEncryptedKeys(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, GenericKey genericKey) throws CMSException;

    protected abstract byte[] getUserKeyingMaterial(AlgorithmIdentifier algorithmIdentifier) throws CMSException;

    protected KeyAgreeRecipientInfoGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier, SubjectPublicKeyInfo subjectPublicKeyInfo, ASN1ObjectIdentifier aSN1ObjectIdentifier2) {
        this.originatorKeyInfo = subjectPublicKeyInfo;
        this.keyAgreementOID = aSN1ObjectIdentifier;
        this.keyEncryptionOID = aSN1ObjectIdentifier2;
    }

    @Override // org.spongycastle.cms.RecipientInfoGenerator
    public RecipientInfo generate(GenericKey genericKey) throws CMSException {
        AlgorithmIdentifier algorithmIdentifier;
        OriginatorIdentifierOrKey originatorIdentifierOrKey = new OriginatorIdentifierOrKey(createOriginatorPublicKey(this.originatorKeyInfo));
        if (CMSUtils.isDES(this.keyEncryptionOID.getId()) || this.keyEncryptionOID.equals(PKCSObjectIdentifiers.id_alg_CMSRC2wrap)) {
            algorithmIdentifier = new AlgorithmIdentifier(this.keyEncryptionOID, DERNull.INSTANCE);
        } else {
            algorithmIdentifier = new AlgorithmIdentifier(this.keyEncryptionOID);
        }
        AlgorithmIdentifier algorithmIdentifier2 = new AlgorithmIdentifier(this.keyAgreementOID, algorithmIdentifier);
        ASN1Sequence aSN1SequenceGenerateRecipientEncryptedKeys = generateRecipientEncryptedKeys(algorithmIdentifier2, algorithmIdentifier, genericKey);
        byte[] userKeyingMaterial = getUserKeyingMaterial(algorithmIdentifier2);
        if (userKeyingMaterial != null) {
            return new RecipientInfo(new KeyAgreeRecipientInfo(originatorIdentifierOrKey, new DEROctetString(userKeyingMaterial), algorithmIdentifier2, aSN1SequenceGenerateRecipientEncryptedKeys));
        }
        return new RecipientInfo(new KeyAgreeRecipientInfo(originatorIdentifierOrKey, null, algorithmIdentifier2, aSN1SequenceGenerateRecipientEncryptedKeys));
    }

    protected OriginatorPublicKey createOriginatorPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        return new OriginatorPublicKey(new AlgorithmIdentifier(subjectPublicKeyInfo.getAlgorithm().getAlgorithm(), DERNull.INSTANCE), subjectPublicKeyInfo.getPublicKeyData().getBytes());
    }
}
