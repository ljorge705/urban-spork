package org.bouncycastle.pqc.crypto.bike;

import org.bouncycastle.pqc.crypto.KEMParameters;

/* loaded from: classes4.dex */
public class BIKEParameters implements KEMParameters {
    public static final BIKEParameters bike128 = new BIKEParameters("bike128", 12323, 142, 134, 256, 5, 3, 128);
    public static final BIKEParameters bike192 = new BIKEParameters("bike192", 24659, 206, 199, 256, 5, 3, 192);
    public static final BIKEParameters bike256 = new BIKEParameters("bike256", 40973, 274, 264, 256, 5, 3, 256);
    private BIKEEngine bikeEngine;
    private final int defaultKeySize;
    private int l;
    private String name;
    private int nbIter;
    private int r;
    private int t;
    private int tau;
    private int w;

    private BIKEParameters(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.name = str;
        this.r = i;
        this.w = i2;
        this.t = i3;
        this.l = i4;
        this.nbIter = i5;
        this.tau = i6;
        this.defaultKeySize = i7;
        this.bikeEngine = new BIKEEngine(i, i2, i3, i4, i5, i6);
    }

    BIKEEngine getEngine() {
        return this.bikeEngine;
    }

    public int getL() {
        return this.l;
    }

    public int getLByte() {
        return this.l / 8;
    }

    public String getName() {
        return this.name;
    }

    public int getNbIter() {
        return this.nbIter;
    }

    public int getR() {
        return this.r;
    }

    public int getRByte() {
        return (this.r + 7) / 8;
    }

    public int getSessionKeySize() {
        return this.defaultKeySize;
    }

    public int getT() {
        return this.t;
    }

    public int getTau() {
        return this.tau;
    }

    public int getW() {
        return this.w;
    }
}
