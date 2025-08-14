package org.spongycastle.asn1.smime;

import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERSet;
import org.spongycastle.asn1.cms.Attribute;

/* loaded from: classes4.dex */
public class SMIMECapabilitiesAttribute extends Attribute {
    public SMIMECapabilitiesAttribute(SMIMECapabilityVector sMIMECapabilityVector) {
        super(SMIMEAttributes.smimeCapabilities, new DERSet(new DERSequence(sMIMECapabilityVector.toASN1EncodableVector())));
    }
}
