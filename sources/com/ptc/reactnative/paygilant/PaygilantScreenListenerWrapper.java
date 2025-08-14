package com.ptc.reactnative.paygilant;

import com.paygilant.PG_FraudDetection_SDK.Biometric.PaygilantScreenListener;

/* loaded from: classes6.dex */
public class PaygilantScreenListenerWrapper {
    public int _id;
    public PaygilantScreenListener listener;

    public PaygilantScreenListenerWrapper(PaygilantScreenListener paygilantScreenListener, int i) {
        this.listener = paygilantScreenListener;
        this._id = i;
    }
}
