package com.facebook.react.bridge;

import com.facebook.jni.HybridData;

/* loaded from: classes5.dex */
public abstract class NativeMap {
    private HybridData mHybridData;

    public native String toString();

    static {
        ReactBridge.staticInit();
    }

    public NativeMap(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
