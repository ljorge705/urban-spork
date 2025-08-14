package org.bouncycastle.pqc.legacy.crypto.mceliece;

import org.bouncycastle.pqc.legacy.math.linearalgebra.GF2Matrix;

/* loaded from: classes4.dex */
public class McEliecePublicKeyParameters extends McElieceKeyParameters {
    private GF2Matrix g;

    /* renamed from: n, reason: collision with root package name */
    private int f373n;
    private int t;

    public McEliecePublicKeyParameters(int i, int i2, GF2Matrix gF2Matrix) {
        super(false, null);
        this.f373n = i;
        this.t = i2;
        this.g = new GF2Matrix(gF2Matrix);
    }

    public GF2Matrix getG() {
        return this.g;
    }

    public int getK() {
        return this.g.getNumRows();
    }

    public int getN() {
        return this.f373n;
    }

    public int getT() {
        return this.t;
    }
}
