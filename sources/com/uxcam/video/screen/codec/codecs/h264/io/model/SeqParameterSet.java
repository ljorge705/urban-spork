package com.uxcam.video.screen.codec.codecs.h264.io.model;

import com.uxcam.internals.al;
import com.uxcam.internals.am;
import com.uxcam.internals.ar;
import com.uxcam.internals.as;
import com.uxcam.internals.bd;
import com.uxcam.internals.dp;
import com.uxcam.video.screen.codec.codecs.h264.io.model.VUIParameters;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public class SeqParameterSet {
    public int bit_depth_chroma_minus8;
    public int bit_depth_luma_minus8;
    public bd chroma_format_idc;
    public boolean constraint_set_0_flag;
    public boolean constraint_set_1_flag;
    public boolean constraint_set_2_flag;
    public boolean constraint_set_3_flag;
    public boolean delta_pic_order_always_zero_flag;
    public boolean direct_8x8_inference_flag;
    public boolean field_pic_flag;
    public int frame_crop_bottom_offset;
    public int frame_crop_left_offset;
    public int frame_crop_right_offset;
    public int frame_crop_top_offset;
    public boolean frame_cropping_flag;
    public boolean frame_mbs_only_flag;
    public boolean gaps_in_frame_num_value_allowed_flag;
    public int level_idc;
    public int log2_max_frame_num_minus4;
    public int log2_max_pic_order_cnt_lsb_minus4;
    public boolean mb_adaptive_frame_field_flag;
    public int num_ref_frames;
    public int num_ref_frames_in_pic_order_cnt_cycle;
    public int[] offsetForRefFrame;
    public int offset_for_non_ref_pic;
    public int offset_for_top_to_bottom_field;
    public int pic_height_in_map_units_minus1;
    public int pic_order_cnt_type;
    public int pic_width_in_mbs_minus1;
    public int profile_idc;
    public boolean qpprime_y_zero_transform_bypass_flag;
    public boolean residual_color_transform_flag;
    public ScalingMatrix scalingMatrix;
    public int seq_parameter_set_id;
    public VUIParameters vuiParams;

    /* renamed from: com.uxcam.video.screen.codec.codecs.h264.io.model.SeqParameterSet$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$uxcam$video$screen$codec$common$model$ColorSpace;

        static {
            int[] iArr = new int[bd.values().length];
            $SwitchMap$com$uxcam$video$screen$codec$common$model$ColorSpace = iArr;
            try {
                iArr[9] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$uxcam$video$screen$codec$common$model$ColorSpace[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$uxcam$video$screen$codec$common$model$ColorSpace[3] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$uxcam$video$screen$codec$common$model$ColorSpace[5] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static int fromColor(bd bdVar) {
        int i = AnonymousClass1.$SwitchMap$com$uxcam$video$screen$codec$common$model$ColorSpace[bdVar.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        if (i == 3) {
            return 2;
        }
        if (i == 4) {
            return 3;
        }
        throw new RuntimeException("Colorspace not supported");
    }

    public static SeqParameterSet read(ByteBuffer byteBuffer) {
        al alVar = new al(byteBuffer);
        SeqParameterSet seqParameterSet = new SeqParameterSet();
        seqParameterSet.profile_idc = alVar.b(8);
        seqParameterSet.constraint_set_0_flag = alVar.a() != 0;
        seqParameterSet.constraint_set_1_flag = alVar.a() != 0;
        seqParameterSet.constraint_set_2_flag = alVar.a() != 0;
        seqParameterSet.constraint_set_3_flag = alVar.a() != 0;
        alVar.b(4);
        seqParameterSet.level_idc = alVar.b(8);
        seqParameterSet.seq_parameter_set_id = ar.b(alVar);
        int i = seqParameterSet.profile_idc;
        if (i == 100 || i == 110 || i == 122 || i == 144) {
            bd color = getColor(ar.b(alVar));
            seqParameterSet.chroma_format_idc = color;
            if (color == bd.YUV444) {
                seqParameterSet.residual_color_transform_flag = alVar.a() != 0;
            }
            seqParameterSet.bit_depth_luma_minus8 = ar.b(alVar);
            seqParameterSet.bit_depth_chroma_minus8 = ar.b(alVar);
            seqParameterSet.qpprime_y_zero_transform_bypass_flag = alVar.a() != 0;
            if (alVar.a() != 0) {
                readScalingListMatrix(alVar, seqParameterSet);
            }
        } else {
            seqParameterSet.chroma_format_idc = bd.YUV420;
        }
        seqParameterSet.log2_max_frame_num_minus4 = ar.b(alVar);
        int iB = ar.b(alVar);
        seqParameterSet.pic_order_cnt_type = iB;
        if (iB == 0) {
            seqParameterSet.log2_max_pic_order_cnt_lsb_minus4 = ar.b(alVar);
        } else if (iB == 1) {
            seqParameterSet.delta_pic_order_always_zero_flag = alVar.a() != 0;
            seqParameterSet.offset_for_non_ref_pic = ar.a(alVar);
            seqParameterSet.offset_for_top_to_bottom_field = ar.a(alVar);
            int iB2 = ar.b(alVar);
            seqParameterSet.num_ref_frames_in_pic_order_cnt_cycle = iB2;
            seqParameterSet.offsetForRefFrame = new int[iB2];
            for (int i2 = 0; i2 < seqParameterSet.num_ref_frames_in_pic_order_cnt_cycle; i2++) {
                seqParameterSet.offsetForRefFrame[i2] = ar.a(alVar);
            }
        }
        seqParameterSet.num_ref_frames = ar.b(alVar);
        seqParameterSet.gaps_in_frame_num_value_allowed_flag = alVar.a() != 0;
        seqParameterSet.pic_width_in_mbs_minus1 = ar.b(alVar);
        seqParameterSet.pic_height_in_map_units_minus1 = ar.b(alVar);
        boolean z = alVar.a() != 0;
        seqParameterSet.frame_mbs_only_flag = z;
        if (!z) {
            seqParameterSet.mb_adaptive_frame_field_flag = alVar.a() != 0;
        }
        seqParameterSet.direct_8x8_inference_flag = alVar.a() != 0;
        boolean z2 = alVar.a() != 0;
        seqParameterSet.frame_cropping_flag = z2;
        if (z2) {
            seqParameterSet.frame_crop_left_offset = ar.b(alVar);
            seqParameterSet.frame_crop_right_offset = ar.b(alVar);
            seqParameterSet.frame_crop_top_offset = ar.b(alVar);
            seqParameterSet.frame_crop_bottom_offset = ar.b(alVar);
        }
        if (alVar.a() != 0) {
            seqParameterSet.vuiParams = readVUIParameters(alVar);
        }
        return seqParameterSet;
    }

    private static HRDParameters readHRDParameters(al alVar) {
        HRDParameters hRDParameters = new HRDParameters();
        hRDParameters.cpb_cnt_minus1 = ar.b(alVar);
        hRDParameters.bit_rate_scale = alVar.b(4);
        hRDParameters.cpb_size_scale = alVar.b(4);
        int i = hRDParameters.cpb_cnt_minus1 + 1;
        hRDParameters.bit_rate_value_minus1 = new int[i];
        hRDParameters.cpb_size_value_minus1 = new int[i];
        hRDParameters.cbr_flag = new boolean[i];
        for (int i2 = 0; i2 <= hRDParameters.cpb_cnt_minus1; i2++) {
            hRDParameters.bit_rate_value_minus1[i2] = ar.b(alVar);
            hRDParameters.cpb_size_value_minus1[i2] = ar.b(alVar);
            hRDParameters.cbr_flag[i2] = alVar.a() != 0;
        }
        hRDParameters.initial_cpb_removal_delay_length_minus1 = alVar.b(5);
        hRDParameters.cpb_removal_delay_length_minus1 = alVar.b(5);
        hRDParameters.dpb_output_delay_length_minus1 = alVar.b(5);
        hRDParameters.time_offset_length = alVar.b(5);
        return hRDParameters;
    }

    private static void readScalingListMatrix(al alVar, SeqParameterSet seqParameterSet) {
        seqParameterSet.scalingMatrix = new ScalingMatrix();
        for (int i = 0; i < 8; i++) {
            if (alVar.a() != 0) {
                ScalingMatrix scalingMatrix = seqParameterSet.scalingMatrix;
                ScalingList[] scalingListArr = new ScalingList[8];
                scalingMatrix.ScalingList4x4 = scalingListArr;
                ScalingList[] scalingListArr2 = new ScalingList[8];
                scalingMatrix.ScalingList8x8 = scalingListArr2;
                if (i < 6) {
                    scalingListArr[i] = ScalingList.read(alVar, 16);
                } else {
                    scalingListArr2[i - 6] = ScalingList.read(alVar, 64);
                }
            }
        }
    }

    private static VUIParameters readVUIParameters(al alVar) {
        VUIParameters vUIParameters = new VUIParameters();
        boolean z = alVar.a() != 0;
        vUIParameters.aspect_ratio_info_present_flag = z;
        if (z) {
            AspectRatio aspectRatioFromValue = AspectRatio.fromValue(alVar.b(8));
            vUIParameters.aspect_ratio = aspectRatioFromValue;
            if (aspectRatioFromValue == AspectRatio.Extended_SAR) {
                vUIParameters.sar_width = alVar.b(16);
                vUIParameters.sar_height = alVar.b(16);
            }
        }
        boolean z2 = alVar.a() != 0;
        vUIParameters.overscan_info_present_flag = z2;
        if (z2) {
            vUIParameters.overscan_appropriate_flag = alVar.a() != 0;
        }
        boolean z3 = alVar.a() != 0;
        vUIParameters.video_signal_type_present_flag = z3;
        if (z3) {
            vUIParameters.video_format = alVar.b(3);
            vUIParameters.video_full_range_flag = alVar.a() != 0;
            boolean z4 = alVar.a() != 0;
            vUIParameters.colour_description_present_flag = z4;
            if (z4) {
                vUIParameters.colour_primaries = alVar.b(8);
                vUIParameters.transfer_characteristics = alVar.b(8);
                vUIParameters.matrix_coefficients = alVar.b(8);
            }
        }
        boolean z5 = alVar.a() != 0;
        vUIParameters.chroma_loc_info_present_flag = z5;
        if (z5) {
            vUIParameters.chroma_sample_loc_type_top_field = ar.b(alVar);
            vUIParameters.chroma_sample_loc_type_bottom_field = ar.b(alVar);
        }
        boolean z6 = alVar.a() != 0;
        vUIParameters.timing_info_present_flag = z6;
        if (z6) {
            vUIParameters.num_units_in_tick = alVar.b(32);
            vUIParameters.time_scale = alVar.b(32);
            vUIParameters.fixed_frame_rate_flag = alVar.a() != 0;
        }
        boolean z7 = alVar.a() != 0;
        if (z7) {
            vUIParameters.nalHRDParams = readHRDParameters(alVar);
        }
        boolean z8 = alVar.a() != 0;
        if (z8) {
            vUIParameters.vclHRDParams = readHRDParameters(alVar);
        }
        if (z7 || z8) {
            vUIParameters.low_delay_hrd_flag = alVar.a() != 0;
        }
        vUIParameters.pic_struct_present_flag = alVar.a() != 0;
        if (alVar.a() != 0) {
            VUIParameters.BitstreamRestriction bitstreamRestriction = new VUIParameters.BitstreamRestriction();
            vUIParameters.bitstreamRestriction = bitstreamRestriction;
            bitstreamRestriction.motion_vectors_over_pic_boundaries_flag = alVar.a() != 0;
            vUIParameters.bitstreamRestriction.max_bytes_per_pic_denom = ar.b(alVar);
            vUIParameters.bitstreamRestriction.max_bits_per_mb_denom = ar.b(alVar);
            vUIParameters.bitstreamRestriction.log2_max_mv_length_horizontal = ar.b(alVar);
            vUIParameters.bitstreamRestriction.log2_max_mv_length_vertical = ar.b(alVar);
            vUIParameters.bitstreamRestriction.num_reorder_frames = ar.b(alVar);
            vUIParameters.bitstreamRestriction.max_dec_frame_buffering = ar.b(alVar);
        }
        return vUIParameters;
    }

    private void writeHRDParameters(HRDParameters hRDParameters, am amVar) {
        as.a(amVar, hRDParameters.cpb_cnt_minus1);
        as.a(amVar, hRDParameters.bit_rate_scale, 4);
        as.a(amVar, hRDParameters.cpb_size_scale, 4);
        for (int i = 0; i <= hRDParameters.cpb_cnt_minus1; i++) {
            as.a(amVar, hRDParameters.bit_rate_value_minus1[i]);
            as.a(amVar, hRDParameters.cpb_size_value_minus1[i]);
            amVar.b(hRDParameters.cbr_flag[i] ? 1 : 0);
        }
        as.a(amVar, hRDParameters.initial_cpb_removal_delay_length_minus1, 5);
        as.a(amVar, hRDParameters.cpb_removal_delay_length_minus1, 5);
        as.a(amVar, hRDParameters.dpb_output_delay_length_minus1, 5);
        as.a(amVar, hRDParameters.time_offset_length, 5);
    }

    private void writeVUIParameters(VUIParameters vUIParameters, am amVar) {
        amVar.b(vUIParameters.aspect_ratio_info_present_flag ? 1 : 0);
        if (vUIParameters.aspect_ratio_info_present_flag) {
            as.a(amVar, vUIParameters.aspect_ratio.getValue(), 8);
            if (vUIParameters.aspect_ratio == AspectRatio.Extended_SAR) {
                as.a(amVar, vUIParameters.sar_width, 16);
                as.a(amVar, vUIParameters.sar_height, 16);
            }
        }
        amVar.b(vUIParameters.overscan_info_present_flag ? 1 : 0);
        if (vUIParameters.overscan_info_present_flag) {
            amVar.b(vUIParameters.overscan_appropriate_flag ? 1 : 0);
        }
        amVar.b(vUIParameters.video_signal_type_present_flag ? 1 : 0);
        if (vUIParameters.video_signal_type_present_flag) {
            as.a(amVar, vUIParameters.video_format, 3);
            amVar.b(vUIParameters.video_full_range_flag ? 1 : 0);
            amVar.b(vUIParameters.colour_description_present_flag ? 1 : 0);
            if (vUIParameters.colour_description_present_flag) {
                as.a(amVar, vUIParameters.colour_primaries, 8);
                as.a(amVar, vUIParameters.transfer_characteristics, 8);
                as.a(amVar, vUIParameters.matrix_coefficients, 8);
            }
        }
        amVar.b(vUIParameters.chroma_loc_info_present_flag ? 1 : 0);
        if (vUIParameters.chroma_loc_info_present_flag) {
            as.a(amVar, vUIParameters.chroma_sample_loc_type_top_field);
            as.a(amVar, vUIParameters.chroma_sample_loc_type_bottom_field);
        }
        amVar.b(vUIParameters.timing_info_present_flag ? 1 : 0);
        if (vUIParameters.timing_info_present_flag) {
            as.a(amVar, vUIParameters.num_units_in_tick, 32);
            as.a(amVar, vUIParameters.time_scale, 32);
            amVar.b(vUIParameters.fixed_frame_rate_flag ? 1 : 0);
        }
        amVar.b(vUIParameters.nalHRDParams != null ? 1 : 0);
        HRDParameters hRDParameters = vUIParameters.nalHRDParams;
        if (hRDParameters != null) {
            writeHRDParameters(hRDParameters, amVar);
        }
        amVar.b(vUIParameters.vclHRDParams != null ? 1 : 0);
        HRDParameters hRDParameters2 = vUIParameters.vclHRDParams;
        if (hRDParameters2 != null) {
            writeHRDParameters(hRDParameters2, amVar);
        }
        if (vUIParameters.nalHRDParams != null || vUIParameters.vclHRDParams != null) {
            amVar.b(vUIParameters.low_delay_hrd_flag ? 1 : 0);
        }
        amVar.b(vUIParameters.pic_struct_present_flag ? 1 : 0);
        amVar.b(vUIParameters.bitstreamRestriction == null ? 0 : 1);
        VUIParameters.BitstreamRestriction bitstreamRestriction = vUIParameters.bitstreamRestriction;
        if (bitstreamRestriction != null) {
            amVar.b(bitstreamRestriction.motion_vectors_over_pic_boundaries_flag ? 1 : 0);
            as.a(amVar, vUIParameters.bitstreamRestriction.max_bytes_per_pic_denom);
            as.a(amVar, vUIParameters.bitstreamRestriction.max_bits_per_mb_denom);
            as.a(amVar, vUIParameters.bitstreamRestriction.log2_max_mv_length_horizontal);
            as.a(amVar, vUIParameters.bitstreamRestriction.log2_max_mv_length_vertical);
            as.a(amVar, vUIParameters.bitstreamRestriction.num_reorder_frames);
            as.a(amVar, vUIParameters.bitstreamRestriction.max_dec_frame_buffering);
        }
    }

    public SeqParameterSet copy() {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(2048);
        write(byteBufferAllocate);
        byteBufferAllocate.flip();
        return read(byteBufferAllocate);
    }

    public void write(ByteBuffer byteBuffer) {
        am amVar = new am(byteBuffer);
        as.a(amVar, this.profile_idc, 8);
        amVar.b(this.constraint_set_0_flag ? 1 : 0);
        amVar.b(this.constraint_set_1_flag ? 1 : 0);
        amVar.b(this.constraint_set_2_flag ? 1 : 0);
        amVar.b(this.constraint_set_3_flag ? 1 : 0);
        as.a(amVar, 0L, 4);
        as.a(amVar, this.level_idc, 8);
        as.a(amVar, this.seq_parameter_set_id);
        int i = this.profile_idc;
        if (i == 100 || i == 110 || i == 122 || i == 144) {
            as.a(amVar, fromColor(this.chroma_format_idc));
            if (this.chroma_format_idc == bd.YUV444) {
                amVar.b(this.residual_color_transform_flag ? 1 : 0);
            }
            as.a(amVar, this.bit_depth_luma_minus8);
            as.a(amVar, this.bit_depth_chroma_minus8);
            amVar.b(this.qpprime_y_zero_transform_bypass_flag ? 1 : 0);
            amVar.b(this.scalingMatrix != null ? 1 : 0);
            if (this.scalingMatrix != null) {
                for (int i2 = 0; i2 < 8; i2++) {
                    if (i2 < 6) {
                        amVar.b(this.scalingMatrix.ScalingList4x4[i2] != null ? 1 : 0);
                        ScalingList scalingList = this.scalingMatrix.ScalingList4x4[i2];
                        if (scalingList != null) {
                            scalingList.write(amVar);
                        }
                    } else {
                        int i3 = i2 - 6;
                        amVar.b(this.scalingMatrix.ScalingList8x8[i3] != null ? 1 : 0);
                        ScalingList scalingList2 = this.scalingMatrix.ScalingList8x8[i3];
                        if (scalingList2 != null) {
                            scalingList2.write(amVar);
                        }
                    }
                }
            }
        }
        as.a(amVar, this.log2_max_frame_num_minus4);
        as.a(amVar, this.pic_order_cnt_type);
        int i4 = this.pic_order_cnt_type;
        if (i4 == 0) {
            as.a(amVar, this.log2_max_pic_order_cnt_lsb_minus4);
        } else if (i4 == 1) {
            amVar.b(this.delta_pic_order_always_zero_flag ? 1 : 0);
            as.a(amVar, dp.b(this.offset_for_non_ref_pic));
            as.a(amVar, dp.b(this.offset_for_top_to_bottom_field));
            as.a(amVar, this.offsetForRefFrame.length);
            int i5 = 0;
            while (true) {
                int[] iArr = this.offsetForRefFrame;
                if (i5 >= iArr.length) {
                    break;
                }
                as.a(amVar, dp.b(iArr[i5]));
                i5++;
            }
        }
        as.a(amVar, this.num_ref_frames);
        amVar.b(this.gaps_in_frame_num_value_allowed_flag ? 1 : 0);
        as.a(amVar, this.pic_width_in_mbs_minus1);
        as.a(amVar, this.pic_height_in_map_units_minus1);
        amVar.b(this.frame_mbs_only_flag ? 1 : 0);
        if (!this.frame_mbs_only_flag) {
            amVar.b(this.mb_adaptive_frame_field_flag ? 1 : 0);
        }
        amVar.b(this.direct_8x8_inference_flag ? 1 : 0);
        amVar.b(this.frame_cropping_flag ? 1 : 0);
        if (this.frame_cropping_flag) {
            as.a(amVar, this.frame_crop_left_offset);
            as.a(amVar, this.frame_crop_right_offset);
            as.a(amVar, this.frame_crop_top_offset);
            as.a(amVar, this.frame_crop_bottom_offset);
        }
        amVar.b(this.vuiParams != null ? 1 : 0);
        VUIParameters vUIParameters = this.vuiParams;
        if (vUIParameters != null) {
            writeVUIParameters(vUIParameters, amVar);
        }
        amVar.b(1);
        amVar.a();
    }

    public static bd getColor(int i) {
        if (i == 0) {
            return bd.MONO;
        }
        if (i == 1) {
            return bd.YUV420;
        }
        if (i == 2) {
            return bd.YUV422;
        }
        if (i == 3) {
            return bd.YUV444;
        }
        throw new RuntimeException("Colorspace not supported");
    }
}
