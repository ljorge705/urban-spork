package org.spongycastle.math.ec;

/* loaded from: classes7.dex */
public class ScaleXPointMap implements ECPointMap {
    protected final ECFieldElement scale;

    public ScaleXPointMap(ECFieldElement eCFieldElement) {
        this.scale = eCFieldElement;
    }

    @Override // org.spongycastle.math.ec.ECPointMap
    public ECPoint map(ECPoint eCPoint) {
        return eCPoint.scaleX(this.scale);
    }
}
