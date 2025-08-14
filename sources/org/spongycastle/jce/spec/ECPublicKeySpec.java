package org.spongycastle.jce.spec;

import org.spongycastle.math.ec.ECPoint;

/* loaded from: classes7.dex */
public class ECPublicKeySpec extends ECKeySpec {
    private ECPoint q;

    public ECPoint getQ() {
        return this.q;
    }

    public ECPublicKeySpec(ECPoint eCPoint, ECParameterSpec eCParameterSpec) {
        super(eCParameterSpec);
        if (eCPoint.getCurve() != null) {
            this.q = eCPoint.normalize();
        } else {
            this.q = eCPoint;
        }
    }
}
