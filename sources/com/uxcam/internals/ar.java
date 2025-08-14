package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class ar {
    public static int a(al alVar) {
        int iB = b(alVar);
        return ((iB >> 1) + (iB & 1)) * ((r0 << 1) - 1);
    }

    public static int b(al alVar) {
        int i = 0;
        while (alVar.a() == 0 && i < 31) {
            i++;
        }
        if (i <= 0) {
            return 0;
        }
        return (int) (((1 << i) - 1) + alVar.b(i));
    }
}
