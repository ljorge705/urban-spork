package org.bouncycastle.oer.its.template.etsi102941;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import org.bouncycastle.oer.OERDefinition;
import org.bouncycastle.oer.its.template.etsi102941.basetypes.EtsiTs102941BaseTypes;
import org.bouncycastle.oer.its.template.etsi103097.EtsiTs103097Module;
import org.bouncycastle.oer.its.template.ieee1609dot2.basetypes.Ieee1609Dot2BaseTypes;

/* loaded from: classes4.dex */
public class EtsiTs102941TrustLists {
    public static final OERDefinition.Builder AaEntry;
    public static final OERDefinition.Builder CrlEntry;
    public static final OERDefinition.Builder CtlCommand;
    public static final OERDefinition.Builder CtlDelete;
    public static final OERDefinition.Builder CtlEntry;
    public static final OERDefinition.Builder CtlFormat;
    public static final OERDefinition.Builder DcDelete;
    public static final OERDefinition.Builder DcEntry;
    public static final OERDefinition.Builder DeltaCtl;
    public static final OERDefinition.Builder EaEntry;
    public static final OERDefinition.Builder FullCtl;
    public static final OERDefinition.Builder RootCaEntry;
    public static final OERDefinition.Builder SequenceOfCrlEntry;
    public static final OERDefinition.Builder SequenceOfCtlCommand;
    public static final OERDefinition.Builder TlmEntry;
    public static final OERDefinition.Builder ToBeSignedCrl;
    public static final OERDefinition.Builder ToBeSignedRcaCtl;
    public static final OERDefinition.Builder ToBeSignedTlmCtl;
    public static final OERDefinition.Builder Url;

    static {
        OERDefinition.Builder builderTypeName = Ieee1609Dot2BaseTypes.HashedId8.typeName("CrlEntry");
        CrlEntry = builderTypeName;
        OERDefinition.Builder builderTypeName2 = OERDefinition.seqof(builderTypeName).typeName("SequenceOfCrlEntry");
        SequenceOfCrlEntry = builderTypeName2;
        ToBeSignedCrl = OERDefinition.seq(EtsiTs102941BaseTypes.Version.label("version"), Ieee1609Dot2BaseTypes.Time32.label("thisUpdate"), Ieee1609Dot2BaseTypes.Time32.label("nextUpdate"), builderTypeName2.label(RemoteConfigConstants.ResponseFieldKey.ENTRIES), OERDefinition.extension(new Object[0])).typeName("ToBeSignedCrl");
        OERDefinition.Builder builderTypeName3 = OERDefinition.ia5String().typeName("Url");
        Url = builderTypeName3;
        OERDefinition.Builder builderTypeName4 = builderTypeName3.typeName("DcDelete");
        DcDelete = builderTypeName4;
        OERDefinition.Builder builderTypeName5 = OERDefinition.seq(builderTypeName3.label("url"), Ieee1609Dot2BaseTypes.SequenceOfHashedId8.label("cert")).typeName("DcEntry");
        DcEntry = builderTypeName5;
        OERDefinition.Builder builderTypeName6 = OERDefinition.seq(EtsiTs103097Module.EtsiTs103097Certificate.label("aaCertificate"), builderTypeName3.label("accessPoint")).typeName("AaEntry");
        AaEntry = builderTypeName6;
        OERDefinition.Builder builderTypeName7 = OERDefinition.seq(EtsiTs103097Module.EtsiTs103097Certificate.label("eaCertificate"), builderTypeName3.label("aaAccessPoint"), OERDefinition.optional(builderTypeName3.label("itsAccessPoint"))).typeName("EaEntry");
        EaEntry = builderTypeName7;
        OERDefinition.Builder builderTypeName8 = OERDefinition.seq(EtsiTs103097Module.EtsiTs103097Certificate.label("selfsignedRootCa"), OERDefinition.optional(EtsiTs103097Module.EtsiTs103097Certificate.label("successorTo"))).typeName("RootCaEntry");
        RootCaEntry = builderTypeName8;
        OERDefinition.Builder builderTypeName9 = OERDefinition.seq(EtsiTs103097Module.EtsiTs103097Certificate.label("selfSignedTLMCertificate"), OERDefinition.optional(EtsiTs103097Module.EtsiTs103097Certificate.label("successorTo")), builderTypeName3.label("accessPoint")).typeName("TlmEntry");
        TlmEntry = builderTypeName9;
        OERDefinition.Builder builderTypeName10 = OERDefinition.choice(Ieee1609Dot2BaseTypes.HashedId8.label("cert"), builderTypeName4.label("dc"), OERDefinition.extension(new Object[0])).typeName("CtlDelete");
        CtlDelete = builderTypeName10;
        OERDefinition.Builder builderTypeName11 = OERDefinition.choice(builderTypeName8.label("rca"), builderTypeName7.label("ea"), builderTypeName6.label("aa"), builderTypeName5.label("dc"), builderTypeName9.label("tlm"), OERDefinition.extension(new Object[0])).typeName("CtlEntry");
        CtlEntry = builderTypeName11;
        OERDefinition.Builder builderTypeName12 = OERDefinition.choice(builderTypeName11.label("add"), builderTypeName10.label("delete"), OERDefinition.extension(new Object[0])).typeName("CtlCommand");
        CtlCommand = builderTypeName12;
        OERDefinition.Builder builderTypeName13 = OERDefinition.seqof(builderTypeName12).typeName("SequenceOfCtlCommand");
        SequenceOfCtlCommand = builderTypeName13;
        OERDefinition.Builder builderTypeName14 = OERDefinition.seq(EtsiTs102941BaseTypes.Version.label("version"), Ieee1609Dot2BaseTypes.Time32.label("nextUpdate"), OERDefinition.bool().label("isFullCtl"), OERDefinition.integer(0L, 255L).label("ctlSequence"), builderTypeName13.label("ctlCommands"), OERDefinition.extension(new Object[0])).typeName("CtlFormat");
        CtlFormat = builderTypeName14;
        DeltaCtl = builderTypeName14.typeName("DeltaCtl");
        FullCtl = builderTypeName14.typeName("FullCtl");
        ToBeSignedTlmCtl = builderTypeName14.typeName("ToBeSignedRcaCtl");
        ToBeSignedRcaCtl = builderTypeName14.typeName("ToBeSignedRcaCtl");
    }
}
