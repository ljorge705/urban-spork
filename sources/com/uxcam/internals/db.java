package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class db {

    /* renamed from: a, reason: collision with root package name */
    public int[] f117a;
    public int b;

    public db() {
        this(0);
    }

    public final int[] a() {
        int i = this.b;
        int[] iArr = new int[i];
        System.arraycopy(this.f117a, 0, iArr, 0, i);
        return iArr;
    }

    public db(int i) {
        this.f117a = new int[128];
    }

    public final void a(int i) {
        int i2 = this.b;
        int[] iArr = this.f117a;
        if (i2 >= iArr.length) {
            int[] iArr2 = new int[iArr.length + 128];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.f117a = iArr2;
        }
        int[] iArr3 = this.f117a;
        int i3 = this.b;
        this.b = i3 + 1;
        iArr3[i3] = i;
    }
}
