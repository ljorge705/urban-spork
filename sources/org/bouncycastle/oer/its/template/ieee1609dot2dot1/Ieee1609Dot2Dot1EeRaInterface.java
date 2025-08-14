package org.bouncycastle.oer.its.template.ieee1609dot2dot1;

import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.oer.OERDefinition;
import org.bouncycastle.oer.its.template.ieee1609dot2.IEEE1609dot2;
import org.bouncycastle.oer.its.template.ieee1609dot2.basetypes.Ieee1609Dot2BaseTypes;

/* loaded from: classes4.dex */
public class Ieee1609Dot2Dot1EeRaInterface {
    public static final OERDefinition.Builder AdditionalParams;
    public static final OERDefinition.Builder ButterflyExpansion;
    public static final OERDefinition.Builder ButterflyParamsOriginal;
    public static final OERDefinition.Builder EeRaCertRequest;

    static {
        OERDefinition.Builder builderTypeName = OERDefinition.choice(OERDefinition.octets(16).label("aes128"), OERDefinition.extension(new Object[0])).typeName("ButterflyExpansion");
        ButterflyExpansion = builderTypeName;
        OERDefinition.Builder builderTypeName2 = OERDefinition.seq(builderTypeName.label("signingExpansion"), Ieee1609Dot2BaseTypes.PublicEncryptionKey.label("encryptionKey"), builderTypeName.label("encryptionExpansion")).typeName("ButterflyParamsOriginal");
        ButterflyParamsOriginal = builderTypeName2;
        OERDefinition.Builder builderTypeName3 = OERDefinition.choice(builderTypeName2.label("original"), builderTypeName.label("unified"), builderTypeName.label("compactUnified"), Ieee1609Dot2BaseTypes.PublicEncryptionKey.label("encryptionKey"), OERDefinition.extension(new Object[0])).typeName("AdditionalParams");
        AdditionalParams = builderTypeName3;
        EeRaCertRequest = OERDefinition.seq(Ieee1609Dot2BaseTypes.UINT8.label("version").validSwitchValue(new ASN1Integer(2L)), Ieee1609Dot2BaseTypes.Time32.label("generationTime"), IEEE1609dot2.CertificateType.label("type"), IEEE1609dot2.ToBeSignedCertificate.label("tbsCert"), OERDefinition.optional(builderTypeName3.label("additionalParams")), OERDefinition.extension(new Object[0])).typeName("EeRaCertRequest");
    }
}
