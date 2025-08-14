package org.spongycastle.cert.crmf;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERUTF8String;
import org.spongycastle.asn1.crmf.CRMFObjectIdentifiers;

/* loaded from: classes4.dex */
public class AuthenticatorControl implements Control {
    private static final ASN1ObjectIdentifier type = CRMFObjectIdentifiers.id_regCtrl_authenticator;
    private final DERUTF8String token;

    @Override // org.spongycastle.cert.crmf.Control
    public ASN1ObjectIdentifier getType() {
        return type;
    }

    @Override // org.spongycastle.cert.crmf.Control
    public ASN1Encodable getValue() {
        return this.token;
    }

    public AuthenticatorControl(DERUTF8String dERUTF8String) {
        this.token = dERUTF8String;
    }

    public AuthenticatorControl(String str) {
        this.token = new DERUTF8String(str);
    }
}
