package com.facebook.react.uimanager;

/* loaded from: classes5.dex */
public class ReactRootViewTagGenerator {
    private static final int ROOT_VIEW_TAG_INCREMENT = 10;
    private static int sNextRootViewTag = 1;

    public static synchronized int getNextRootViewTag() {
        int i;
        i = sNextRootViewTag;
        sNextRootViewTag = i + 10;
        return i;
    }
}
