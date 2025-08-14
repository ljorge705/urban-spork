package com.uxcam.internals;

/* loaded from: classes6.dex */
public enum bd {
    RGB(3, new int[]{0, 0, 0}, new int[]{0, 0, 0}, new int[]{0, 0, 0}),
    YUV420(3, new int[]{0, 1, 2}, new int[]{0, 1, 1}, new int[]{0, 1, 1}),
    YUV420J(3, new int[]{0, 1, 2}, new int[]{0, 1, 1}, new int[]{0, 1, 1}),
    YUV422(3, new int[]{0, 1, 2}, new int[]{0, 1, 1}, new int[]{0, 0, 0}),
    YUV422J(3, new int[]{0, 1, 2}, new int[]{0, 1, 1}, new int[]{0, 0, 0}),
    YUV444(3, new int[]{0, 1, 2}, new int[]{0, 0, 0}, new int[]{0, 0, 0}),
    YUV444J(3, new int[]{0, 1, 2}, new int[]{0, 0, 0}, new int[]{0, 0, 0}),
    YUV422_10(3, new int[]{0, 1, 2}, new int[]{0, 1, 1}, new int[]{0, 0, 0}),
    /* JADX INFO: Fake field, exist only in values array */
    GREY(1, new int[]{0}, new int[]{0}, new int[]{0}),
    MONO(1, new int[]{0, 0, 0}, new int[]{0, 0, 0}, new int[]{0, 0, 0}),
    YUV444_10(3, new int[]{0, 1, 2}, new int[]{0, 0, 0}, new int[]{0, 0, 0});


    /* renamed from: a, reason: collision with root package name */
    public final int f94a;
    public final int[] b;
    public final int[] c;
    public final int[] d;

    bd(int i, int[] iArr, int[] iArr2, int[] iArr3) {
        this.f94a = i;
        this.b = iArr;
        this.c = iArr2;
        this.d = iArr3;
    }
}
