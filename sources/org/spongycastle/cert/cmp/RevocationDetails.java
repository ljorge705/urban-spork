package org.spongycastle.cert.cmp;

import java.math.BigInteger;
import org.spongycastle.asn1.cmp.RevDetails;
import org.spongycastle.asn1.x500.X500Name;

/* loaded from: classes4.dex */
public class RevocationDetails {
    private RevDetails revDetails;

    public RevDetails toASN1Structure() {
        return this.revDetails;
    }

    public RevocationDetails(RevDetails revDetails) {
        this.revDetails = revDetails;
    }

    public X500Name getSubject() {
        return this.revDetails.getCertDetails().getSubject();
    }

    public X500Name getIssuer() {
        return this.revDetails.getCertDetails().getIssuer();
    }

    public BigInteger getSerialNumber() {
        return this.revDetails.getCertDetails().getSerialNumber().getValue();
    }
}
