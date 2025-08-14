package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;

/* loaded from: classes4.dex */
public class GOST3410PrivateKeySpec implements KeySpec {

    /* renamed from: a, reason: collision with root package name */
    private BigInteger f335a;
    private BigInteger p;
    private BigInteger q;
    private BigInteger x;

    public GOST3410PrivateKeySpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.x = bigInteger;
        this.p = bigInteger2;
        this.q = bigInteger3;
        this.f335a = bigInteger4;
    }

    public BigInteger getA() {
        return this.f335a;
    }

    public BigInteger getP() {
        return this.p;
    }

    public BigInteger getQ() {
        return this.q;
    }

    public BigInteger getX() {
        return this.x;
    }
}
