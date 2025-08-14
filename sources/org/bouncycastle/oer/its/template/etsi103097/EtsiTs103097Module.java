package org.bouncycastle.oer.its.template.etsi103097;

import org.bouncycastle.oer.OERDefinition;
import org.bouncycastle.oer.its.template.ieee1609dot2.IEEE1609dot2;

/* loaded from: classes4.dex */
public class EtsiTs103097Module {
    public static final OERDefinition.Builder EtsiTs103097Certificate = IEEE1609dot2.ExplicitCertificate.typeName("EtsiTs103097Certificate");
    public static final OERDefinition.Builder EtsiTs103097Data;
    public static final OERDefinition.Builder EtsiTs103097Data_Encrypted;
    public static final OERDefinition.Builder EtsiTs103097Data_Encrypted_Unicast;
    public static final OERDefinition.Builder EtsiTs103097Data_Signed;
    public static final OERDefinition.Builder EtsiTs103097Data_SignedAndEncrypted;
    public static final OERDefinition.Builder EtsiTs103097Data_SignedAndEncrypted_Unicast;
    public static final OERDefinition.Builder EtsiTs103097Data_SignedExternalPayload;
    public static final OERDefinition.Builder EtsiTs103097Data_Unsecured;

    static {
        OERDefinition.Builder builderTypeName = IEEE1609dot2.Ieee1609Dot2Data.typeName("EtsiTs103097Data");
        EtsiTs103097Data = builderTypeName;
        EtsiTs103097Data_Unsecured = builderTypeName.typeName("EtsiTs103097DataUnsecured");
        EtsiTs103097Data_Signed = builderTypeName.typeName("EtsiTs103097DataSigned");
        EtsiTs103097Data_SignedExternalPayload = builderTypeName.typeName("EtsiTs103097DataSignedExternalPayload");
        EtsiTs103097Data_Encrypted = builderTypeName.typeName("EtsiTs103097DataEncrypted");
        EtsiTs103097Data_SignedAndEncrypted = builderTypeName.typeName("EtsiTs103097DataSignedAndEncrypted");
        EtsiTs103097Data_Encrypted_Unicast = builderTypeName.typeName("EtsiTs103097DataEncryptedUnicast");
        EtsiTs103097Data_SignedAndEncrypted_Unicast = builderTypeName.typeName("EtsiTs103097DataSignedAndEncryptedUnicast");
    }
}
