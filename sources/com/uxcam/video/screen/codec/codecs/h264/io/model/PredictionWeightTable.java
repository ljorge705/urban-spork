package com.uxcam.video.screen.codec.codecs.h264.io.model;

/* loaded from: classes6.dex */
public class PredictionWeightTable {
    public int chroma_log2_weight_denom;
    public int luma_log2_weight_denom;
    public int[][] luma_weight = new int[2][];
    public int[][][] chroma_weight = new int[2][][];
    public int[][] luma_offset = new int[2][];
    public int[][][] chroma_offset = new int[2][][];
}
