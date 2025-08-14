package com.uxcam.internals;

import com.uxcam.video.screen.codec.codecs.h264.io.model.SeqParameterSet;

/* loaded from: classes6.dex */
public final class aq {

    /* renamed from: a, reason: collision with root package name */
    public final bd f86a;
    public final ia b = a();
    public final int[] c;
    public final int[] d;
    public final int e;

    public aq(SeqParameterSet seqParameterSet, int i, int i2) {
        this.f86a = seqParameterSet.chroma_format_idc;
        int i3 = seqParameterSet.pic_width_in_mbs_minus1 + 1;
        this.e = (1 << i2) - 1;
        this.c = new int[4];
        this.d = new int[i3 << i];
    }

    public static final int a(int i, int i2) {
        return (i << 4) | i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int a(com.uxcam.internals.am r18, int[] r19, com.uxcam.internals.ia[] r20, int r21, int r22, int[] r23, com.uxcam.internals.ia r24) {
        /*
            Method dump skipped, instructions count: 261
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.internals.aq.a(com.uxcam.internals.am, int[], com.uxcam.internals.ia[], int, int, int[], com.uxcam.internals.ia):int");
    }

    public final ia a() {
        bd bdVar = this.f86a;
        if (bdVar == bd.YUV420) {
            return cu.b;
        }
        if (bdVar == bd.YUV422) {
            return cu.c;
        }
        if (bdVar == bd.YUV444) {
            return cu.f113a[0];
        }
        return null;
    }
}
