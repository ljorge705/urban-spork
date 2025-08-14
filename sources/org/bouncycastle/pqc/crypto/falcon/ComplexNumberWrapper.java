package org.bouncycastle.pqc.crypto.falcon;

/* loaded from: classes4.dex */
class ComplexNumberWrapper {
    FalconFPR im;
    FalconFPR re;

    ComplexNumberWrapper(FalconFPR falconFPR, FalconFPR falconFPR2) {
        this.re = falconFPR;
        this.im = falconFPR2;
    }
}
