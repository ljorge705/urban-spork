package org.spongycastle.cert.crmf;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;

/* loaded from: classes4.dex */
public interface Control {
    ASN1ObjectIdentifier getType();

    ASN1Encodable getValue();
}
