package org.bouncycastle.oer.its.template.ieee1609dot2;

import io.sentry.protocol.Geo;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.oer.Element;
import org.bouncycastle.oer.ElementSupplier;
import org.bouncycastle.oer.OERDefinition;
import org.bouncycastle.oer.Switch;
import org.bouncycastle.oer.SwitchIndexer;
import org.bouncycastle.oer.its.template.etsi103097.extension.EtsiTs103097ExtensionModule;
import org.bouncycastle.oer.its.template.ieee1609dot2.basetypes.Ieee1609Dot2BaseTypes;
import org.bouncycastle.util.BigIntegers;
import org.spongycastle.cms.CMSAttributeTableGenerator;

/* loaded from: classes4.dex */
public class IEEE1609dot2 {
    public static final OERDefinition.Builder AesCcmCiphertext;
    public static final OERDefinition.Builder Certificate;
    public static final OERDefinition.Builder CertificateBase;
    public static final OERDefinition.Builder CertificateId;
    public static final OERDefinition.Builder CertificateType;
    public static final OERDefinition.Builder ContributedExtensionBlock;
    public static final Switch ContributedExtensionBlockSwitch;
    public static final OERDefinition.Builder ContributedExtensionBlocks;
    public static final OERDefinition.Builder CounterSignature;
    public static final OERDefinition.Builder EncryptedData;
    public static final OERDefinition.Builder EncryptedDataEncryptionKey;
    public static final OERDefinition.Builder EndEntityType;
    public static final OERDefinition.Builder ExplicitCertificate;
    public static final OERDefinition.Builder HashedData;
    public static final OERDefinition.Builder HeaderInfo;
    public static final OERDefinition.Builder HeaderInfoContributorId;
    public static final OERDefinition.Builder Ieee1609Dot2Content;
    public static final OERDefinition.Builder Ieee1609Dot2Data;
    public static final OERDefinition.Builder ImplicitCertificate;
    public static final OERDefinition.Builder IssuerIdentifier;
    public static final OERDefinition.Builder LinkageData;
    public static final OERDefinition.Builder MissingCrlIdentifier;
    public static final OERDefinition.Builder Opaque;
    public static final OERDefinition.Builder PKRecipientInfo;
    public static final OERDefinition.Builder PduFunctionalType;
    public static final OERDefinition.Builder PreSharedKeyRecipientInfo;
    public static final OERDefinition.Builder PsidGroupPermissions;
    public static final OERDefinition.Builder RecipientInfo;
    public static final OERDefinition.Builder SequenceOfCertificate;
    public static final OERDefinition.Builder SequenceOfPsidGroupPermissions;
    public static final OERDefinition.Builder SequenceOfRecipientInfo;
    public static final OERDefinition.Builder SignedData;
    public static final OERDefinition.Builder SignedDataPayload;
    public static final OERDefinition.Builder SignerIdentifier;
    public static final OERDefinition.Builder SubjectPermissions;
    public static final OERDefinition.Builder SymmRecipientInfo;
    public static final OERDefinition.Builder SymmetricCiphertext;
    public static final OERDefinition.Builder ToBeSignedCertificate;
    public static final OERDefinition.Builder ToBeSignedData;
    public static final OERDefinition.Builder VerificationKeyIndicator;
    private static final ASN1Integer etsiHeaderInfoContributorId;
    private static ASN1Integer explicitOrdinal;
    private static final ASN1Encodable[] extensionBlockSwitchKeys;
    private static ASN1Integer implicitOrdinal;

    static {
        OERDefinition.Builder builderTypeName = OERDefinition.octets().typeName("Opaque");
        Opaque = builderTypeName;
        OERDefinition.Builder builderTypeName2 = OERDefinition.integer(0L, 255L).typeName("PduFunctionalType");
        PduFunctionalType = builderTypeName2;
        OERDefinition.Builder builderTypeName3 = OERDefinition.choice(OERDefinition.octets(32).label("sha256HashedData"), OERDefinition.extension(OERDefinition.octets(48).label("sha384HashedData"), OERDefinition.octets(32).label("reserved"))).typeName("HashedData");
        HashedData = builderTypeName3;
        OERDefinition.Builder builderTypeName4 = OERDefinition.seq(Ieee1609Dot2BaseTypes.HashedId3.label("cracaId"), Ieee1609Dot2BaseTypes.CrlSeries.label("crlSeries"), OERDefinition.extension(new Object[0])).typeName("MissingCrlIdentifier");
        MissingCrlIdentifier = builderTypeName4;
        ASN1Integer aSN1Integer = new ASN1Integer(BigIntegers.TWO);
        etsiHeaderInfoContributorId = aSN1Integer;
        extensionBlockSwitchKeys = new ASN1Encodable[]{aSN1Integer};
        OERDefinition.Builder builderValidSwitchValue = OERDefinition.integer(0L, 255L).typeName("HeaderInfoContributorId").validSwitchValue(aSN1Integer);
        HeaderInfoContributorId = builderValidSwitchValue;
        Switch r4 = new Switch() { // from class: org.bouncycastle.oer.its.template.ieee1609dot2.IEEE1609dot2.1
            @Override // org.bouncycastle.oer.Switch
            public ASN1Encodable[] keys() {
                return IEEE1609dot2.extensionBlockSwitchKeys;
            }

            @Override // org.bouncycastle.oer.Switch
            public Element result(SwitchIndexer switchIndexer) {
                ASN1Integer aSN1Integer2 = ASN1Integer.getInstance(switchIndexer.get(0).toASN1Primitive());
                if (aSN1Integer2.equals((ASN1Primitive) IEEE1609dot2.etsiHeaderInfoContributorId)) {
                    return OERDefinition.seqof(EtsiTs103097ExtensionModule.EtsiOriginatingHeaderInfoExtension).rangeToMAXFrom(1L).label("extns").build();
                }
                throw new IllegalArgumentException("No forward definition for type id " + aSN1Integer2);
            }
        };
        ContributedExtensionBlockSwitch = r4;
        OERDefinition.Builder builderTypeName5 = OERDefinition.seq(builderValidSwitchValue.label("contributorId"), OERDefinition.aSwitch(r4).label("Extn")).typeName("ContributedExtensionBlock");
        ContributedExtensionBlock = builderTypeName5;
        OERDefinition.Builder builderTypeName6 = OERDefinition.seqof(builderTypeName5).rangeToMAXFrom(1L).typeName("ContributedExtensionBlocks");
        ContributedExtensionBlocks = builderTypeName6;
        OERDefinition.Builder builderTypeName7 = Ieee1609Dot2BaseTypes.HashedId8.typeName("PreSharedKeyRecipientInfo");
        PreSharedKeyRecipientInfo = builderTypeName7;
        OERDefinition.Builder builderTypeName8 = OERDefinition.choice(Ieee1609Dot2BaseTypes.EciesP256EncryptedKey.label("eciesNistP256"), Ieee1609Dot2BaseTypes.EciesP256EncryptedKey.label("eciesBrainpoolP256r1"), OERDefinition.extension(new Object[0])).typeName("EncryptedDataEncryptionKey");
        EncryptedDataEncryptionKey = builderTypeName8;
        OERDefinition.Builder builderTypeName9 = OERDefinition.seq(Ieee1609Dot2BaseTypes.HashedId8.label("recipientId"), builderTypeName8.label("encKey")).typeName("PKRecipientInfo");
        PKRecipientInfo = builderTypeName9;
        OERDefinition.Builder builderTypeName10 = OERDefinition.seq(OERDefinition.octets(12).label("nonce"), builderTypeName.label("ccmCiphertext")).typeName("AesCcmCiphertext");
        AesCcmCiphertext = builderTypeName10;
        OERDefinition.Builder builderTypeName11 = OERDefinition.choice(builderTypeName10.label("aes128ccm"), OERDefinition.extension(new Object[0])).typeName("SymmetricCiphertext");
        SymmetricCiphertext = builderTypeName11;
        OERDefinition.Builder builderTypeName12 = OERDefinition.seq(Ieee1609Dot2BaseTypes.HashedId8.label("recipientId"), builderTypeName11.label("encKey")).typeName("SymmRecipientInfo");
        SymmRecipientInfo = builderTypeName12;
        OERDefinition.Builder builderTypeName13 = OERDefinition.choice(builderTypeName7.label("pskRecipInfo"), builderTypeName12.label("symmRecipInfo"), builderTypeName9.label("certRecipInfo"), builderTypeName9.label("signedDataRecipInfo"), builderTypeName9.label("rekRecipInfo")).typeName("RecipientInfo");
        RecipientInfo = builderTypeName13;
        OERDefinition.Builder builderTypeName14 = OERDefinition.seqof(builderTypeName13).typeName("SequenceOfRecipientInfo");
        SequenceOfRecipientInfo = builderTypeName14;
        OERDefinition.Builder builderTypeName15 = OERDefinition.seq(builderTypeName14.label("recipients"), builderTypeName11.label("ciphertext")).typeName("EncryptedData");
        EncryptedData = builderTypeName15;
        OERDefinition.Builder builderTypeName16 = OERDefinition.bitString(8L).defaultValue(new DERBitString(new byte[]{0}, 0)).typeName("EndEntityType");
        EndEntityType = builderTypeName16;
        OERDefinition.Builder builderTypeName17 = OERDefinition.choice(Ieee1609Dot2BaseTypes.SequenceOfPsidSspRange.label("explicit"), OERDefinition.nullValue().label("all"), OERDefinition.extension(new Object[0])).typeName("SubjectPermissions");
        SubjectPermissions = builderTypeName17;
        OERDefinition.Builder builderTypeName18 = OERDefinition.choice(Ieee1609Dot2BaseTypes.PublicVerificationKey.label("verificationKey"), Ieee1609Dot2BaseTypes.EccP256CurvePoint.label("reconstructionValue"), OERDefinition.extension(new Object[0])).typeName("VerificationKeyIndicator");
        VerificationKeyIndicator = builderTypeName18;
        OERDefinition.Builder builderTypeName19 = OERDefinition.seq(builderTypeName17.label("subjectPermissions"), OERDefinition.integer(1L).label("minChainLength"), OERDefinition.integer(0L).label("chainLengthRange"), builderTypeName16.label("eeType")).typeName("PsidGroupPermissions");
        PsidGroupPermissions = builderTypeName19;
        OERDefinition.Builder builderTypeName20 = OERDefinition.seqof(builderTypeName19).typeName("SequenceOfPsidGroupPermissions");
        SequenceOfPsidGroupPermissions = builderTypeName20;
        OERDefinition.Builder builderTypeName21 = OERDefinition.seq(Ieee1609Dot2BaseTypes.IValue.label("iCert"), Ieee1609Dot2BaseTypes.LinkageValue.label("linkageValue"), OERDefinition.optional(Ieee1609Dot2BaseTypes.GroupLinkageValue.label("groupLinkageValue")), OERDefinition.extension(new Object[0])).typeName("LinkageData");
        LinkageData = builderTypeName21;
        OERDefinition.Builder builderTypeName22 = OERDefinition.choice(builderTypeName21.label("linkageData"), Ieee1609Dot2BaseTypes.Hostname.label("name"), OERDefinition.octets(1, 64).label("binaryId"), OERDefinition.nullValue().label("none"), OERDefinition.extension(new Object[0])).typeName("CertificateId");
        CertificateId = builderTypeName22;
        OERDefinition.Builder builderTypeName23 = OERDefinition.seq(builderTypeName22.label("id"), Ieee1609Dot2BaseTypes.HashedId3.label("cracaId"), Ieee1609Dot2BaseTypes.CrlSeries.label("crlSeries"), Ieee1609Dot2BaseTypes.ValidityPeriod.label("validityPeriod"), OERDefinition.optional(Ieee1609Dot2BaseTypes.GeographicRegion.label(Geo.JsonKeys.REGION), Ieee1609Dot2BaseTypes.SubjectAssurance.label("assuranceLevel"), Ieee1609Dot2BaseTypes.SequenceOfPsidSsp.label("appPermissions"), builderTypeName20.label("certIssuePermissions"), builderTypeName20.label("certRequestPermissions"), OERDefinition.nullValue().label("canRequestRollover"), Ieee1609Dot2BaseTypes.PublicEncryptionKey.label("encryptionKey")), builderTypeName18.label("verifyKeyIndicator"), OERDefinition.extension(new Object[0])).typeName("ToBeSignedCertificate");
        ToBeSignedCertificate = builderTypeName23;
        OERDefinition.Builder builderTypeName24 = OERDefinition.choice(Ieee1609Dot2BaseTypes.HashedId8.label("sha256AndDigest"), Ieee1609Dot2BaseTypes.HashAlgorithm.label("self"), OERDefinition.extension(Ieee1609Dot2BaseTypes.HashedId8.label("sha384AndDigest"))).typeName("IssuerIdentifier");
        IssuerIdentifier = builderTypeName24;
        OERDefinition.Builder builderTypeName25 = OERDefinition.enumeration(OERDefinition.enumItem("explicit"), OERDefinition.enumItem("implicit"), OERDefinition.extension(new Object[0])).typeName("CertificateType");
        CertificateType = builderTypeName25;
        explicitOrdinal = new ASN1Integer(BigInteger.ZERO);
        implicitOrdinal = new ASN1Integer(BigInteger.ONE);
        OERDefinition.Builder builderTypeName26 = OERDefinition.seq(Ieee1609Dot2BaseTypes.UINT8.label("version"), builderTypeName25.label("type"), builderTypeName24.label("issuer"), builderTypeName23.label("toBeSigned"), OERDefinition.optional(Ieee1609Dot2BaseTypes.Signature.label("signature"))).label("signature").typeName("CertificateBase");
        CertificateBase = builderTypeName26;
        OERDefinition.Builder builderTypeName27 = builderTypeName26.copy().typeName("Certificate");
        Certificate = builderTypeName27;
        ExplicitCertificate = builderTypeName26.typeName("ExplicitCertificate").replaceChild(1, builderTypeName25.validSwitchValue(explicitOrdinal).label("type"));
        ImplicitCertificate = builderTypeName26.typeName("ImplicitCertificate").replaceChild(1, builderTypeName25.validSwitchValue(implicitOrdinal).label("type"));
        OERDefinition.Builder builderTypeName28 = OERDefinition.seqof(builderTypeName27).typeName("SequenceOfCertificate");
        SequenceOfCertificate = builderTypeName28;
        OERDefinition.Builder builderTypeName29 = OERDefinition.choice(Ieee1609Dot2BaseTypes.HashedId8.label(CMSAttributeTableGenerator.DIGEST), builderTypeName28.label("certificate"), OERDefinition.nullValue().label("self"), OERDefinition.extension(new Object[0])).typeName("SignerIdentifier");
        SignerIdentifier = builderTypeName29;
        OERDefinition.Builder builderTypeName30 = OERDefinition.seq(Ieee1609Dot2BaseTypes.Psid.label("psid"), OERDefinition.optional(Ieee1609Dot2BaseTypes.Time64.label("generationTime"), Ieee1609Dot2BaseTypes.Time64.label("expiryTime"), Ieee1609Dot2BaseTypes.ThreeDLocation.label("generationLocation"), Ieee1609Dot2BaseTypes.HashedId3.label("p2pcdLearningRequest"), builderTypeName4.label("missingCrlIdentifier"), Ieee1609Dot2BaseTypes.EncryptionKey.label("encryptionKey")), OERDefinition.extension(OERDefinition.optional(Ieee1609Dot2BaseTypes.SequenceOfHashedId3.label("inlineP2pcdRequest"), builderTypeName27.label("requestedCertificate"), builderTypeName2.label("pduFunctionalType"), builderTypeName6.label("contributedExtensions")))).typeName("HeaderInfo");
        HeaderInfo = builderTypeName30;
        SignedData = OERDefinition.seq(Ieee1609Dot2BaseTypes.HashAlgorithm.label("hashId"), OERDefinition.deferred(new ElementSupplier() { // from class: org.bouncycastle.oer.its.template.ieee1609dot2.IEEE1609dot2.2
            private Element built;

            @Override // org.bouncycastle.oer.ElementSupplier
            public Element build() {
                Element element;
                synchronized (this) {
                    if (this.built == null) {
                        this.built = IEEE1609dot2.ToBeSignedData.label("tbsData").build();
                    }
                    element = this.built;
                }
                return element;
            }
        }).label("tbsData"), builderTypeName29.label("signer"), Ieee1609Dot2BaseTypes.Signature.label("signature")).typeName("SignedData");
        OERDefinition.Builder builderTypeName31 = OERDefinition.choice(builderTypeName.label("unsecuredData"), OERDefinition.deferred(new ElementSupplier() { // from class: org.bouncycastle.oer.its.template.ieee1609dot2.IEEE1609dot2.3
            private Element built;

            @Override // org.bouncycastle.oer.ElementSupplier
            public Element build() {
                Element element;
                synchronized (this) {
                    if (this.built == null) {
                        this.built = IEEE1609dot2.SignedData.label("signedData").mayRecurse(true).build();
                    }
                    element = this.built;
                }
                return element;
            }
        }).label("signedData").mayRecurse(true), builderTypeName15.label("encryptedData"), builderTypeName.label("signedCertificateRequest"), OERDefinition.extension(new Object[0])).typeName("Ieee1609Dot2Content");
        Ieee1609Dot2Content = builderTypeName31;
        CounterSignature = OERDefinition.seq(Ieee1609Dot2BaseTypes.UINT8.label("protocolVersion"), builderTypeName31.label("content")).typeName("CounterSignature");
        OERDefinition.Builder builderTypeName32 = OERDefinition.seq(Ieee1609Dot2BaseTypes.UINT8.validSwitchValue(new ASN1Integer(3L)).label("protocolVersion"), builderTypeName31.label("content")).typeName("Ieee1609Dot2Data");
        Ieee1609Dot2Data = builderTypeName32;
        OERDefinition.Builder builderTypeName33 = OERDefinition.seq(OERDefinition.optional(builderTypeName32.label("data"), builderTypeName3.label("extDataHash")), OERDefinition.extension(new Object[0])).typeName("SignedDataPayload");
        SignedDataPayload = builderTypeName33;
        ToBeSignedData = OERDefinition.seq(builderTypeName33.label("payload"), builderTypeName30.label("headerInfo")).typeName("ToBeSignedData");
    }
}
