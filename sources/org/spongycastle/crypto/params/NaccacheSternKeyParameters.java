package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes7.dex */
public class NaccacheSternKeyParameters extends AsymmetricKeyParameter {
    private BigInteger g;
    int lowerSigmaBound;

    /* renamed from: n, reason: collision with root package name */
    private BigInteger f393n;

    public BigInteger getG() {
        return this.g;
    }

    public int getLowerSigmaBound() {
        return this.lowerSigmaBound;
    }

    public BigInteger getModulus() {
        return this.f393n;
    }

    public NaccacheSternKeyParameters(boolean z, BigInteger bigInteger, BigInteger bigInteger2, int i) {
        super(z);
        this.g = bigInteger;
        this.f393n = bigInteger2;
        this.lowerSigmaBound = i;
    }
}
