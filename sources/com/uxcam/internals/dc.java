package com.uxcam.internals;

import java.util.Arrays;

/* loaded from: classes6.dex */
public final class dc {

    /* renamed from: a, reason: collision with root package name */
    public int[] f118a;

    public dc() {
        int[] iArrA = a();
        this.f118a = iArrA;
        Arrays.fill(iArrA, Integer.MIN_VALUE);
    }

    public static int[] a() {
        return new int[128];
    }

    public final void a(int i, int i2) {
        if (i2 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("This implementation can not store -2147483648");
        }
        int[] iArr = this.f118a;
        if (iArr.length <= i) {
            int i3 = i + 128;
            int[] iArr2 = new int[i3];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            Arrays.fill(iArr2, this.f118a.length, i3, Integer.MIN_VALUE);
            this.f118a = iArr2;
        }
        int[] iArr3 = this.f118a;
        int i4 = iArr3[i];
        iArr3[i] = i2;
    }
}
