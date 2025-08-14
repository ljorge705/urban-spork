package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

/* loaded from: classes7.dex */
public class McElieceCCA2PublicKeyParameters extends McElieceCCA2KeyParameters {
    private GF2Matrix matrixG;

    /* renamed from: n, reason: collision with root package name */
    private int f407n;
    private int t;

    public GF2Matrix getG() {
        return this.matrixG;
    }

    public int getN() {
        return this.f407n;
    }

    public int getT() {
        return this.t;
    }

    public McElieceCCA2PublicKeyParameters(int i, int i2, GF2Matrix gF2Matrix, String str) {
        super(false, str);
        this.f407n = i;
        this.t = i2;
        this.matrixG = new GF2Matrix(gF2Matrix);
    }

    public int getK() {
        return this.matrixG.getNumRows();
    }
}
