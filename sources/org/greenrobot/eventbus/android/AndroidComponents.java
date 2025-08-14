package org.greenrobot.eventbus.android;

import org.greenrobot.eventbus.Logger;
import org.greenrobot.eventbus.MainThreadSupport;

/* loaded from: classes4.dex */
public abstract class AndroidComponents {
    private static final AndroidComponents implementation;
    public final MainThreadSupport defaultMainThreadSupport;
    public final Logger logger;

    public static boolean areAvailable() {
        return implementation != null;
    }

    public static AndroidComponents get() {
        return implementation;
    }

    static {
        implementation = AndroidDependenciesDetector.isAndroidSDKAvailable() ? AndroidDependenciesDetector.instantiateAndroidComponents() : null;
    }

    public AndroidComponents(Logger logger, MainThreadSupport mainThreadSupport) {
        this.logger = logger;
        this.defaultMainThreadSupport = mainThreadSupport;
    }
}
