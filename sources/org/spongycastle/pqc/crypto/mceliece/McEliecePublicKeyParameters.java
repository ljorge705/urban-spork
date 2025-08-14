package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

/* loaded from: classes7.dex */
public class McEliecePublicKeyParameters extends McElieceKeyParameters {
    private GF2Matrix g;

    /* renamed from: n, reason: collision with root package name */
    private int f415n;
    private int t;

    public GF2Matrix getG() {
        return this.g;
    }

    public int getN() {
        return this.f415n;
    }

    public int getT() {
        return this.t;
    }

    public McEliecePublicKeyParameters(int i, int i2, GF2Matrix gF2Matrix) {
        super(false, null);
        this.f415n = i;
        this.t = i2;
        this.g = new GF2Matrix(gF2Matrix);
    }

    public int getK() {
        return this.g.getNumRows();
    }
}
