package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECConstants;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.util.Arrays;

/* loaded from: classes7.dex */
public class ECDomainParameters implements ECConstants {
    private ECPoint G;
    private ECCurve curve;
    private BigInteger h;

    /* renamed from: n, reason: collision with root package name */
    private BigInteger f391n;
    private byte[] seed;

    public ECCurve getCurve() {
        return this.curve;
    }

    public ECPoint getG() {
        return this.G;
    }

    public BigInteger getH() {
        return this.h;
    }

    public BigInteger getN() {
        return this.f391n;
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger) {
        this(eCCurve, eCPoint, bigInteger, ONE, null);
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        this(eCCurve, eCPoint, bigInteger, bigInteger2, null);
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.curve = eCCurve;
        this.G = eCPoint.normalize();
        this.f391n = bigInteger;
        this.h = bigInteger2;
        this.seed = bArr;
    }

    public byte[] getSeed() {
        return Arrays.clone(this.seed);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ECDomainParameters)) {
            return false;
        }
        ECDomainParameters eCDomainParameters = (ECDomainParameters) obj;
        return this.curve.equals(eCDomainParameters.curve) && this.G.equals(eCDomainParameters.G) && this.f391n.equals(eCDomainParameters.f391n) && this.h.equals(eCDomainParameters.h);
    }

    public int hashCode() {
        return (((((this.curve.hashCode() * 37) ^ this.G.hashCode()) * 37) ^ this.f391n.hashCode()) * 37) ^ this.h.hashCode();
    }
}
