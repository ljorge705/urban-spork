package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class gg {
    /* JADX WARN: Removed duplicated region for block: B:27:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(com.uxcam.video.screen.codec.codecs.h264.io.model.SliceHeader r9, com.uxcam.internals.am r10, int r11) {
        /*
            com.uxcam.video.screen.codec.codecs.h264.io.model.SeqParameterSet r0 = r9.sps
            com.uxcam.video.screen.codec.codecs.h264.io.model.PredictionWeightTable r1 = r9.pred_weight_table
            int r2 = r1.luma_log2_weight_denom
            r3 = 1
            int r2 = r3 << r2
            int r1 = r1.chroma_log2_weight_denom
            int r1 = r3 << r1
            r4 = 0
            r5 = r4
        Lf:
            com.uxcam.video.screen.codec.codecs.h264.io.model.PredictionWeightTable r6 = r9.pred_weight_table
            int[][] r7 = r6.luma_weight
            r7 = r7[r11]
            int r8 = r7.length
            if (r5 >= r8) goto La9
            r7 = r7[r5]
            if (r7 != r2) goto L27
            int[][] r6 = r6.luma_offset
            r6 = r6[r11]
            r6 = r6[r5]
            if (r6 == 0) goto L25
            goto L27
        L25:
            r6 = r4
            goto L28
        L27:
            r6 = r3
        L28:
            r10.b(r6)
            if (r6 == 0) goto L4b
            com.uxcam.video.screen.codec.codecs.h264.io.model.PredictionWeightTable r6 = r9.pred_weight_table
            int[][] r6 = r6.luma_weight
            r6 = r6[r11]
            r6 = r6[r5]
            int r6 = com.uxcam.internals.dp.b(r6)
            com.uxcam.internals.as.a(r10, r6)
            com.uxcam.video.screen.codec.codecs.h264.io.model.PredictionWeightTable r6 = r9.pred_weight_table
            int[][] r6 = r6.luma_offset
            r6 = r6[r11]
            r6 = r6[r5]
            int r6 = com.uxcam.internals.dp.b(r6)
            com.uxcam.internals.as.a(r10, r6)
        L4b:
            com.uxcam.internals.bd r6 = r0.chroma_format_idc
            com.uxcam.internals.bd r7 = com.uxcam.internals.bd.MONO
            if (r6 == r7) goto La5
            com.uxcam.video.screen.codec.codecs.h264.io.model.PredictionWeightTable r6 = r9.pred_weight_table
            int[][][] r7 = r6.chroma_weight
            r7 = r7[r11]
            r8 = r7[r4]
            r8 = r8[r5]
            if (r8 != r1) goto L76
            int[][][] r6 = r6.chroma_offset
            r6 = r6[r11]
            r8 = r6[r4]
            r8 = r8[r5]
            if (r8 != 0) goto L76
            r7 = r7[r3]
            r7 = r7[r5]
            if (r7 != r1) goto L76
            r6 = r6[r3]
            r6 = r6[r5]
            if (r6 == 0) goto L74
            goto L76
        L74:
            r6 = r4
            goto L77
        L76:
            r6 = r3
        L77:
            r10.b(r6)
            if (r6 == 0) goto La5
            r6 = r4
        L7d:
            r7 = 2
            if (r6 >= r7) goto La5
            com.uxcam.video.screen.codec.codecs.h264.io.model.PredictionWeightTable r7 = r9.pred_weight_table
            int[][][] r7 = r7.chroma_weight
            r7 = r7[r11]
            r7 = r7[r6]
            r7 = r7[r5]
            int r7 = com.uxcam.internals.dp.b(r7)
            com.uxcam.internals.as.a(r10, r7)
            com.uxcam.video.screen.codec.codecs.h264.io.model.PredictionWeightTable r7 = r9.pred_weight_table
            int[][][] r7 = r7.chroma_offset
            r7 = r7[r11]
            r7 = r7[r6]
            r7 = r7[r5]
            int r7 = com.uxcam.internals.dp.b(r7)
            com.uxcam.internals.as.a(r10, r7)
            int r6 = r6 + 1
            goto L7d
        La5:
            int r5 = r5 + 1
            goto Lf
        La9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.internals.gg.a(com.uxcam.video.screen.codec.codecs.h264.io.model.SliceHeader, com.uxcam.internals.am, int):void");
    }
}
