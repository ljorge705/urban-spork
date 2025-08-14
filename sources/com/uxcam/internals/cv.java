package com.uxcam.internals;

import com.uxcam.video.screen.codec.codecs.h264.io.model.MBType;

/* loaded from: classes6.dex */
public final class cv {

    /* renamed from: a, reason: collision with root package name */
    public final bq f114a;
    public aq[] b;
    public int[][] c;
    public int[][] d;

    public cv(bq bqVar) {
        this.f114a = bqVar;
    }

    public static void a(int[] iArr, int i, int[] iArr2, int i2, int i3, int i4) {
        int i5 = 1 << i2;
        int i6 = (i4 << i2) + i3;
        int i7 = 0;
        for (int i8 = 0; i8 < 4; i8++) {
            iArr[i6] = dp.a(iArr2[i7] + i);
            iArr[i6 + 1] = dp.a(iArr2[i7 + 1] + i);
            iArr[i6 + 2] = dp.a(iArr2[i7 + 2] + i);
            iArr[i6 + 3] = dp.a(iArr2[i7 + 3] + i);
            i7 += 4;
            i6 += i5;
        }
    }

    public static void a(int[] iArr, int[][] iArr2) {
        if (iArr.length == 4) {
            int[][] iArr3 = bb.f92a;
            int i = iArr[0];
            int i2 = iArr[1];
            int i3 = i + i2;
            int i4 = i - i2;
            int i5 = iArr[2];
            int i6 = iArr[3];
            int i7 = i5 + i6;
            int i8 = i5 - i6;
            iArr[0] = i3 + i7;
            iArr[1] = i4 + i8;
            iArr[2] = i3 - i7;
            iArr[3] = i4 - i8;
            for (int i9 = 0; i9 < 4; i9++) {
                iArr[i9] = ((iArr[i9] * bb.c[2][0]) << 3) >> 1;
            }
        } else if (iArr.length == 8) {
            int[][] iArr4 = bb.f92a;
        } else {
            int[][] iArr5 = bb.f92a;
            for (int i10 = 0; i10 < 16; i10 += 4) {
                int i11 = iArr[i10];
                int i12 = i10 + 2;
                int i13 = iArr[i12];
                int i14 = i11 + i13;
                int i15 = i11 - i13;
                int i16 = i10 + 1;
                int i17 = iArr[i16];
                int i18 = i10 + 3;
                int i19 = iArr[i18];
                int i20 = i17 - i19;
                int i21 = i17 + i19;
                iArr[i10] = i14 + i21;
                iArr[i16] = i15 + i20;
                iArr[i12] = i15 - i20;
                iArr[i18] = i14 - i21;
            }
            for (int i22 = 0; i22 < 4; i22++) {
                int i23 = iArr[i22];
                int i24 = i22 + 8;
                int i25 = iArr[i24];
                int i26 = i23 + i25;
                int i27 = i23 - i25;
                int i28 = i22 + 4;
                int i29 = iArr[i28];
                int i30 = i22 + 12;
                int i31 = iArr[i30];
                int i32 = i29 - i31;
                int i33 = i29 + i31;
                iArr[i22] = i26 + i33;
                iArr[i28] = i27 + i32;
                iArr[i24] = i27 - i32;
                iArr[i30] = i26 - i33;
            }
            for (int i34 = 0; i34 < 16; i34++) {
                iArr[i34] = ((iArr[i34] * (bb.c[2][0] << 4)) + 4) >> 3;
            }
            int i35 = iArr[2];
            iArr[2] = iArr[4];
            iArr[4] = i35;
            int i36 = iArr[3];
            iArr[3] = iArr[5];
            iArr[5] = i36;
            int i37 = iArr[10];
            iArr[10] = iArr[12];
            iArr[12] = i37;
            int i38 = iArr[11];
            iArr[11] = iArr[13];
            iArr[13] = i38;
        }
        for (int i39 = 0; i39 < iArr2.length; i39++) {
            int[] iArr6 = iArr2[i39];
            int[][] iArr7 = bb.f92a;
            for (int i40 = 0; i40 < 16; i40++) {
                iArr6[i40] = ((iArr6[i40] * (bb.c[2][i40] << 4)) + 1) >> 1;
            }
            int[] iArr8 = iArr2[i39];
            iArr8[0] = iArr[i39];
            for (int i41 = 0; i41 < 16; i41 += 4) {
                int i42 = iArr8[i41];
                int i43 = i41 + 2;
                int i44 = iArr8[i43];
                int i45 = i42 + i44;
                int i46 = i42 - i44;
                int i47 = i41 + 1;
                int i48 = iArr8[i47];
                int i49 = i41 + 3;
                int i50 = iArr8[i49];
                int i51 = (i48 >> 1) - i50;
                int i52 = i48 + (i50 >> 1);
                iArr8[i41] = i45 + i52;
                iArr8[i47] = i46 + i51;
                iArr8[i43] = i46 - i51;
                iArr8[i49] = i45 - i52;
            }
            for (int i53 = 0; i53 < 4; i53++) {
                int i54 = iArr8[i53];
                int i55 = i53 + 8;
                int i56 = iArr8[i55];
                int i57 = i54 + i56;
                int i58 = i54 - i56;
                int i59 = i53 + 4;
                int i60 = iArr8[i59];
                int i61 = i53 + 12;
                int i62 = iArr8[i61];
                int i63 = (i60 >> 1) - i62;
                int i64 = i60 + (i62 >> 1);
                iArr8[i53] = i57 + i64;
                iArr8[i59] = i58 + i63;
                iArr8[i55] = i58 - i63;
                iArr8[i61] = i57 - i64;
            }
            for (int i65 = 0; i65 < 16; i65++) {
                iArr8[i65] = (iArr8[i65] + 32) >> 6;
            }
        }
    }

    public final void a(int i, am amVar, int i2, int i3, int[][] iArr) {
        for (int i4 = 0; i4 < iArr.length; i4++) {
            int[] iArr2 = iArr[i4];
            int[][] iArr3 = bb.f92a;
            for (int i5 = 1; i5 < 16; i5++) {
                int i6 = iArr2[i5];
                int i7 = i6 >> 31;
                iArr2[i5] = ((((((i6 ^ i7) - i7) * bb.f92a[2][i5]) + 87296) >> 18) ^ i7) - i7;
            }
            aq aqVar = this.b[i];
            int i8 = i2 + cu.j[i4];
            int i9 = i3 + cu.k[i4];
            MBType mBType = MBType.I_16x16;
            int[] iArr4 = iArr[i4];
            ia[] iaVarArr = cu.e;
            int[] iArr5 = bb.b;
            aqVar.getClass();
            boolean z = i8 != 0;
            int i10 = aqVar.c[aqVar.e & i9];
            boolean z2 = i9 != 0;
            int i11 = aqVar.d[i8];
            int i12 = mBType == null ? 0 : i10 >> 4;
            int i13 = mBType == null ? 0 : i11 >> 4;
            if (z && z2) {
                i12 = ((i12 + i13) + 1) >> 1;
            } else if (!z) {
                i12 = z2 ? i13 : 0;
            }
            int iA = aq.a(amVar, iArr4, iaVarArr, 1, 15, iArr5, cu.f113a[Math.min(i12, 8)]);
            aqVar.c[i9 & aqVar.e] = iA;
            aqVar.d[i8] = iA;
        }
    }

    public final void a(int i, am amVar, int i2, int i3, int[] iArr) {
        int i4 = 0;
        if (iArr.length == 4) {
            int[][] iArr2 = bb.f92a;
            for (int i5 = 0; i5 < 4; i5++) {
                int i6 = iArr[i5];
                int i7 = i6 >> 31;
                iArr[i5] = ((((((i6 ^ i7) - i7) * bb.f92a[2][0]) + 174592) >> 19) ^ i7) - i7;
            }
            int i8 = iArr[0];
            int i9 = iArr[1];
            int i10 = i8 + i9;
            int i11 = i8 - i9;
            int i12 = iArr[2];
            int i13 = iArr[3];
            int i14 = i12 + i13;
            int i15 = i12 - i13;
            iArr[0] = i10 + i14;
            iArr[1] = i11 + i15;
            iArr[2] = i10 - i14;
            iArr[3] = i11 - i15;
            aq.a(amVar, iArr, cu.f, 0, iArr.length, new int[]{0, 1, 2, 3}, this.b[i].b);
            return;
        }
        if (iArr.length == 8) {
            int[][] iArr3 = bb.f92a;
            aq.a(amVar, iArr, cu.g, 0, iArr.length, new int[]{0, 1, 2, 3, 4, 5, 6, 7}, this.b[i].b);
            return;
        }
        int[][] iArr4 = bb.f92a;
        int i16 = iArr[2];
        iArr[2] = iArr[4];
        iArr[4] = i16;
        int i17 = iArr[3];
        iArr[3] = iArr[5];
        iArr[5] = i17;
        int i18 = iArr[10];
        iArr[10] = iArr[12];
        iArr[12] = i18;
        int i19 = iArr[11];
        iArr[11] = iArr[13];
        iArr[13] = i19;
        for (int i20 = 0; i20 < 16; i20++) {
            int i21 = iArr[i20];
            int i22 = i21 >> 31;
            iArr[i20] = ((((((i21 ^ i22) - i22) * bb.f92a[2][0]) + 174592) >> 19) ^ i22) - i22;
        }
        for (int i23 = 0; i23 < 16; i23 += 4) {
            int i24 = iArr[i23];
            int i25 = i23 + 3;
            int i26 = iArr[i25];
            int i27 = i24 + i26;
            int i28 = i23 + 1;
            int i29 = iArr[i28];
            int i30 = i23 + 2;
            int i31 = iArr[i30];
            int i32 = i29 + i31;
            int i33 = i29 - i31;
            int i34 = i24 - i26;
            iArr[i23] = i27 + i32;
            iArr[i28] = i34 + i33;
            iArr[i30] = i27 - i32;
            iArr[i25] = i34 - i33;
        }
        for (int i35 = 0; i35 < 4; i35++) {
            int i36 = iArr[i35];
            int i37 = i35 + 12;
            int i38 = iArr[i37];
            int i39 = i36 + i38;
            int i40 = i35 + 4;
            int i41 = iArr[i40];
            int i42 = i35 + 8;
            int i43 = iArr[i42];
            int i44 = i41 + i43;
            int i45 = i41 - i43;
            int i46 = i36 - i38;
            iArr[i35] = (i39 + i44) >> 1;
            iArr[i40] = (i45 + i46) >> 1;
            iArr[i42] = (i39 - i44) >> 1;
            iArr[i37] = (i46 - i45) >> 1;
        }
        aq aqVar = this.b[i];
        MBType mBType = MBType.I_16x16;
        ia[] iaVarArr = cu.e;
        int[] iArr5 = bb.b;
        aqVar.getClass();
        boolean z = i2 != 0;
        int i47 = aqVar.c[i3 & aqVar.e];
        boolean z2 = i3 != 0;
        int i48 = aqVar.d[i2];
        int i49 = mBType == null ? 0 : i47 >> 4;
        int i50 = mBType == null ? 0 : i48 >> 4;
        if (z && z2) {
            i4 = ((i49 + i50) + 1) >> 1;
        } else if (z) {
            i4 = i49;
        } else if (z2) {
            i4 = i50;
        }
        aq.a(amVar, iArr, iaVarArr, 0, 16, iArr5, cu.f113a[Math.min(i4, 8)]);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00ba  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int[][] a(com.uxcam.internals.ed r24, int r25, int r26, int r27, int r28, int r29) {
        /*
            Method dump skipped, instructions count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.internals.cv.a(com.uxcam.internals.ed, int, int, int, int, int):int[][]");
    }

    public static int a(int[] iArr, int i) {
        return ((((iArr[i] + iArr[i + 1]) + iArr[i + 2]) + iArr[i + 3]) + 2) >> 2;
    }

    public static int a(int i, int i2, int[] iArr, int[] iArr2) {
        return ((((((((iArr[i] + iArr[i + 1]) + iArr[i + 2]) + iArr[i + 3]) + iArr2[i2]) + iArr2[i2 + 1]) + iArr2[i2 + 2]) + iArr2[i2 + 3]) + 4) >> 3;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(int[] r14, int r15, int r16, int r17, int[][] r18) {
        /*
            r13 = this;
            r0 = r13
            r1 = r15
            r2 = r16
            r3 = r17
            r4 = r3 & 7
            r5 = 128(0x80, float:1.8E-43)
            if (r2 == 0) goto L1b
            if (r3 == 0) goto L1b
            int[][] r6 = r0.c
            r6 = r6[r1]
            int[][] r7 = r0.d
            r7 = r7[r1]
            int r6 = a(r4, r2, r6, r7)
            goto L25
        L1b:
            if (r2 == 0) goto L27
            int[][] r6 = r0.c
            r6 = r6[r1]
            int r6 = a(r6, r4)
        L25:
            r8 = r6
            goto L33
        L27:
            if (r3 == 0) goto L32
            int[][] r6 = r0.d
            r6 = r6[r1]
            int r6 = a(r6, r2)
            goto L25
        L32:
            r8 = r5
        L33:
            r6 = 0
            r9 = r18[r6]
            r10 = 3
            r11 = 0
            r12 = 0
            r7 = r14
            a(r7, r8, r9, r10, r11, r12)
            if (r3 == 0) goto L4b
            int[][] r6 = r0.d
            r6 = r6[r1]
            int r7 = r2 + 4
            int r6 = a(r6, r7)
        L49:
            r8 = r6
            goto L57
        L4b:
            if (r2 == 0) goto L56
            int[][] r6 = r0.c
            r6 = r6[r1]
            int r6 = a(r6, r4)
            goto L49
        L56:
            r8 = r5
        L57:
            r6 = 1
            r9 = r18[r6]
            r10 = 3
            r11 = 4
            r12 = 0
            r7 = r14
            a(r7, r8, r9, r10, r11, r12)
            if (r2 == 0) goto L6f
            int[][] r5 = r0.c
            r5 = r5[r1]
            int r4 = r4 + 4
            int r5 = a(r5, r4)
        L6d:
            r7 = r5
            goto L7a
        L6f:
            if (r3 == 0) goto L6d
            int[][] r4 = r0.d
            r4 = r4[r1]
            int r5 = a(r4, r2)
            goto L6d
        L7a:
            r4 = 2
            r8 = r18[r4]
            r9 = 3
            r10 = 0
            r11 = 4
            r6 = r14
            a(r6, r7, r8, r9, r10, r11)
            int r2 = r13.a(r15, r2, r3)
            r1 = 3
            r3 = r18[r1]
            r4 = 3
            r5 = 4
            r6 = 4
            r1 = r14
            a(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.internals.cv.a(int[], int, int, int, int[][]):void");
    }

    public final int a(int i, int i2, int i3) {
        int i4 = i3 & 7;
        if (i2 != 0 && i3 != 0) {
            return a(i4 + 4, i2 + 4, this.c[i], this.d[i]);
        }
        if (i2 != 0) {
            return a(this.c[i], i4 + 4);
        }
        if (i3 != 0) {
            return a(this.d[i], i2 + 4);
        }
        return 128;
    }

    public final int a(int i, int i2) {
        if (i == 0 && i2 == 0) {
            return 128;
        }
        int i3 = 0;
        if (i2 == 0) {
            int[] iArr = this.c[0];
            int i4 = 0;
            while (i3 < iArr.length) {
                i4 += iArr[i3];
                i3++;
            }
            return (i4 + 8) >> 4;
        }
        if (i == 0) {
            int[] iArr2 = this.d[0];
            for (int i5 = i; i5 < i + 16; i5++) {
                i3 += iArr2[i5];
            }
            return (i3 + 8) >> 4;
        }
        int i6 = 0;
        for (int i7 : this.c[0]) {
            i6 += i7;
        }
        int[] iArr3 = this.d[0];
        for (int i8 = i; i8 < i + 16; i8++) {
            i3 += iArr3[i8];
        }
        return ((i3 + i6) + 16) >> 5;
    }

    public static void a(int i, int i2, int i3, int i4, int i5, int[] iArr, int[] iArr2) {
        int i6;
        int i7 = i3 + 4;
        int i8 = 0;
        if (i7 < i && i4 + 4 < i2) {
            int i9 = (i4 * i) + i3;
            int i10 = 0;
            while (i8 < 4) {
                iArr2[i10] = iArr[i9] - i5;
                iArr2[i10 + 1] = iArr[i9 + 1] - i5;
                iArr2[i10 + 2] = iArr[i9 + 2] - i5;
                iArr2[i10 + 3] = iArr[i9 + 3] - i5;
                i8++;
                i9 += i;
                i10 += 4;
            }
            return;
        }
        int i11 = i4;
        while (true) {
            i6 = i4 + 4;
            if (i11 >= Math.min(i6, i2)) {
                break;
            }
            int iMin = Math.min(i3, i) + (i11 * i);
            int i12 = i3;
            while (i12 < Math.min(i7, i)) {
                iArr2[i8] = iArr[iMin] - i5;
                i12++;
                i8++;
                iMin++;
            }
            int i13 = iMin - 1;
            while (i12 < i7) {
                iArr2[i8] = iArr[i13] - i5;
                i12++;
                i8++;
            }
            i11++;
        }
        while (i11 < i6) {
            int iMin2 = Math.min(i3, i) + ((i2 * i) - i);
            int i14 = i3;
            while (i14 < Math.min(i7, i)) {
                iArr2[i8] = iArr[iMin2] - i5;
                i14++;
                i8++;
                iMin2++;
            }
            int i15 = iMin2 - 1;
            while (i14 < i7) {
                iArr2[i8] = iArr[i15] - i5;
                i14++;
                i8++;
            }
            i11++;
        }
    }

    public static bd[] a() {
        return new bd[]{bd.YUV420J};
    }
}
