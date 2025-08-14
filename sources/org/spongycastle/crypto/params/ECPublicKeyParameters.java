package org.spongycastle.crypto.params;

import org.spongycastle.math.ec.ECPoint;

/* loaded from: classes7.dex */
public class ECPublicKeyParameters extends ECKeyParameters {
    private final ECPoint Q;

    public ECPoint getQ() {
        return this.Q;
    }

    public ECPublicKeyParameters(ECPoint eCPoint, ECDomainParameters eCDomainParameters) {
        super(false, eCDomainParameters);
        this.Q = validate(eCPoint);
    }

    private ECPoint validate(ECPoint eCPoint) {
        if (eCPoint == null) {
            throw new IllegalArgumentException("point has null value");
        }
        if (eCPoint.isInfinity()) {
            throw new IllegalArgumentException("point at infinity");
        }
        ECPoint eCPointNormalize = eCPoint.normalize();
        if (eCPointNormalize.isValid()) {
            return eCPointNormalize;
        }
        throw new IllegalArgumentException("point not on curve");
    }
}
