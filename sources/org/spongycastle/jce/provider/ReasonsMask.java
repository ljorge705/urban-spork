package org.spongycastle.jce.provider;

import org.spongycastle.asn1.x509.ReasonFlags;

/* loaded from: classes7.dex */
class ReasonsMask {
    static final ReasonsMask allReasons = new ReasonsMask(33023);
    private int _reasons;

    int getReasons() {
        return this._reasons;
    }

    ReasonsMask(ReasonFlags reasonFlags) {
        this._reasons = reasonFlags.intValue();
    }

    private ReasonsMask(int i) {
        this._reasons = i;
    }

    ReasonsMask() {
        this(0);
    }

    void addReasons(ReasonsMask reasonsMask) {
        this._reasons = reasonsMask.getReasons() | this._reasons;
    }

    boolean isAllReasons() {
        return this._reasons == allReasons._reasons;
    }

    ReasonsMask intersect(ReasonsMask reasonsMask) {
        ReasonsMask reasonsMask2 = new ReasonsMask();
        reasonsMask2.addReasons(new ReasonsMask(reasonsMask.getReasons() & this._reasons));
        return reasonsMask2;
    }

    boolean hasNewReasons(ReasonsMask reasonsMask) {
        return ((reasonsMask.getReasons() ^ this._reasons) | this._reasons) != 0;
    }
}
