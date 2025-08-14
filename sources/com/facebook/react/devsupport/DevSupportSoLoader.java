package com.facebook.react.devsupport;

import com.facebook.soloader.SoLoader;

/* loaded from: classes5.dex */
class DevSupportSoLoader {
    private static volatile boolean sDidInit = false;

    DevSupportSoLoader() {
    }

    public static synchronized void staticInit() {
        if (sDidInit) {
            return;
        }
        SoLoader.loadLibrary("react_devsupportjni");
        sDidInit = true;
    }
}
