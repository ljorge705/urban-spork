package com.scottyab.rootbeer;

import com.scottyab.rootbeer.util.QLog;

/* loaded from: classes6.dex */
public class RootBeerNative {
    private static boolean libraryLoaded = false;

    public native int checkForRoot(Object[] objArr);

    public native int setLogDebugMessages(boolean z);

    public boolean wasNativeLibraryLoaded() {
        return libraryLoaded;
    }

    static {
        try {
            System.loadLibrary("toolChecker");
            libraryLoaded = true;
        } catch (UnsatisfiedLinkError e) {
            QLog.e(e);
        }
    }
}
