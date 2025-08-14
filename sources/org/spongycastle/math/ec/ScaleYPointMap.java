package org.spongycastle.math.ec;

/* loaded from: classes7.dex */
public class ScaleYPointMap implements ECPointMap {
    protected final ECFieldElement scale;

    public ScaleYPointMap(ECFieldElement eCFieldElement) {
        this.scale = eCFieldElement;
    }

    @Override // org.spongycastle.math.ec.ECPointMap
    public ECPoint map(ECPoint eCPoint) {
        return eCPoint.scaleY(this.scale);
    }
}
