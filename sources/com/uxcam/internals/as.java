package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class as {
    public static void a(am amVar, int i) {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= 15) {
                i2 = 0;
                break;
            }
            int i4 = (1 << i2) + i3;
            if (i < i4) {
                break;
            }
            i2++;
            i3 = i4;
        }
        amVar.a(0, i2);
        amVar.b(1);
        amVar.a(i - i3, i2);
    }

    public static void a(am amVar, long j, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            amVar.b(((int) (j >> ((i - i2) - 1))) & 1);
        }
    }
}
