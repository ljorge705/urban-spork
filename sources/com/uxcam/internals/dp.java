package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class dp {
    public static final int a(int i) {
        if (i < 0) {
            return 0;
        }
        if (i > 255) {
            return 255;
        }
        return i;
    }

    public static final int b(int i) {
        if (i == 0) {
            return 0;
        }
        int i2 = i >> 31;
        return (((i ^ i2) - i2) << 1) - ((~i) >>> 31);
    }
}
