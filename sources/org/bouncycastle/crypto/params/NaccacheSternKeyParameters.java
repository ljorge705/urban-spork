package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes4.dex */
public class NaccacheSternKeyParameters extends AsymmetricKeyParameter {
    private BigInteger g;
    int lowerSigmaBound;

    /* renamed from: n, reason: collision with root package name */
    private BigInteger f331n;

    public NaccacheSternKeyParameters(boolean z, BigInteger bigInteger, BigInteger bigInteger2, int i) {
        super(z);
        this.g = bigInteger;
        this.f331n = bigInteger2;
        this.lowerSigmaBound = i;
    }

    public BigInteger getG() {
        return this.g;
    }

    public int getLowerSigmaBound() {
        return this.lowerSigmaBound;
    }

    public BigInteger getModulus() {
        return this.f331n;
    }
}
