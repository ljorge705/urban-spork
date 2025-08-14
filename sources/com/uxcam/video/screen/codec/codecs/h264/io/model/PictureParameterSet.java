package com.uxcam.video.screen.codec.codecs.h264.io.model;

import com.uxcam.internals.al;
import com.uxcam.internals.am;
import com.uxcam.internals.ar;
import com.uxcam.internals.as;
import com.uxcam.internals.dp;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* loaded from: classes6.dex */
public class PictureParameterSet {
    public int[] bottom_right;
    public int chroma_qp_index_offset;
    public boolean constrained_intra_pred_flag;
    public boolean deblocking_filter_control_present_flag;
    public boolean entropy_coding_mode_flag;
    public PPSExt extended;
    public int[] num_ref_idx_active_minus1 = new int[2];
    public int num_slice_groups_minus1;
    public int pic_init_qp_minus26;
    public int pic_init_qs_minus26;
    public boolean pic_order_present_flag;
    public int pic_parameter_set_id;
    public boolean redundant_pic_cnt_present_flag;
    public int[] run_length_minus1;
    public int seq_parameter_set_id;
    public boolean slice_group_change_direction_flag;
    public int slice_group_change_rate_minus1;
    public int[] slice_group_id;
    public int slice_group_map_type;
    public int[] top_left;
    public int weighted_bipred_idc;
    public boolean weighted_pred_flag;

    public static class PPSExt {
        public boolean[] pic_scaling_list_present_flag;
        public ScalingMatrix scalindMatrix;
        public int second_chroma_qp_index_offset;
        public boolean transform_8x8_mode_flag;
    }

    public static PictureParameterSet read(ByteBuffer byteBuffer) {
        al alVar = new al(byteBuffer);
        PictureParameterSet pictureParameterSet = new PictureParameterSet();
        pictureParameterSet.pic_parameter_set_id = ar.b(alVar);
        pictureParameterSet.seq_parameter_set_id = ar.b(alVar);
        pictureParameterSet.entropy_coding_mode_flag = alVar.a() != 0;
        pictureParameterSet.pic_order_present_flag = alVar.a() != 0;
        int iB = ar.b(alVar);
        pictureParameterSet.num_slice_groups_minus1 = iB;
        if (iB > 0) {
            int iB2 = ar.b(alVar);
            pictureParameterSet.slice_group_map_type = iB2;
            int i = pictureParameterSet.num_slice_groups_minus1 + 1;
            pictureParameterSet.top_left = new int[i];
            pictureParameterSet.bottom_right = new int[i];
            pictureParameterSet.run_length_minus1 = new int[i];
            if (iB2 == 0) {
                for (int i2 = 0; i2 <= pictureParameterSet.num_slice_groups_minus1; i2++) {
                    pictureParameterSet.run_length_minus1[i2] = ar.b(alVar);
                }
            } else if (iB2 == 2) {
                for (int i3 = 0; i3 < pictureParameterSet.num_slice_groups_minus1; i3++) {
                    pictureParameterSet.top_left[i3] = ar.b(alVar);
                    pictureParameterSet.bottom_right[i3] = ar.b(alVar);
                }
            } else if (iB2 == 3 || iB2 == 4 || iB2 == 5) {
                pictureParameterSet.slice_group_change_direction_flag = alVar.a() != 0;
                pictureParameterSet.slice_group_change_rate_minus1 = ar.b(alVar);
            } else if (iB2 == 6) {
                int i4 = i > 4 ? 3 : i > 2 ? 2 : 1;
                int iB3 = ar.b(alVar);
                pictureParameterSet.slice_group_id = new int[iB3 + 1];
                for (int i5 = 0; i5 <= iB3; i5++) {
                    pictureParameterSet.slice_group_id[i5] = alVar.b(i4);
                }
            }
        }
        pictureParameterSet.num_ref_idx_active_minus1 = new int[]{ar.b(alVar), ar.b(alVar)};
        pictureParameterSet.weighted_pred_flag = alVar.a() != 0;
        pictureParameterSet.weighted_bipred_idc = alVar.b(2);
        pictureParameterSet.pic_init_qp_minus26 = ar.a(alVar);
        pictureParameterSet.pic_init_qs_minus26 = ar.a(alVar);
        pictureParameterSet.chroma_qp_index_offset = ar.a(alVar);
        pictureParameterSet.deblocking_filter_control_present_flag = alVar.a() != 0;
        pictureParameterSet.constrained_intra_pred_flag = alVar.a() != 0;
        pictureParameterSet.redundant_pic_cnt_present_flag = alVar.a() != 0;
        if (((alVar.f81a.remaining() << 3) + 32) - alVar.b >= 32 || alVar.a(1) != 1 || (alVar.a(24) << 9) != 0) {
            PPSExt pPSExt = new PPSExt();
            pictureParameterSet.extended = pPSExt;
            pPSExt.transform_8x8_mode_flag = alVar.a() != 0;
            if (alVar.a() != 0) {
                for (int i6 = 0; i6 < ((pictureParameterSet.extended.transform_8x8_mode_flag ? 1 : 0) * 2) + 6; i6++) {
                    if (alVar.a() != 0) {
                        ScalingMatrix scalingMatrix = pictureParameterSet.extended.scalindMatrix;
                        ScalingList[] scalingListArr = new ScalingList[8];
                        scalingMatrix.ScalingList4x4 = scalingListArr;
                        ScalingList[] scalingListArr2 = new ScalingList[8];
                        scalingMatrix.ScalingList8x8 = scalingListArr2;
                        if (i6 < 6) {
                            scalingListArr[i6] = ScalingList.read(alVar, 16);
                        } else {
                            scalingListArr2[i6 - 6] = ScalingList.read(alVar, 64);
                        }
                    }
                }
            }
            pictureParameterSet.extended.second_chroma_qp_index_offset = ar.a(alVar);
        }
        return pictureParameterSet;
    }

    public PictureParameterSet copy() {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(2048);
        write(byteBufferAllocate);
        byteBufferAllocate.flip();
        return read(byteBufferAllocate);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PictureParameterSet pictureParameterSet = (PictureParameterSet) obj;
        if (!Arrays.equals(this.bottom_right, pictureParameterSet.bottom_right) || this.chroma_qp_index_offset != pictureParameterSet.chroma_qp_index_offset || this.constrained_intra_pred_flag != pictureParameterSet.constrained_intra_pred_flag || this.deblocking_filter_control_present_flag != pictureParameterSet.deblocking_filter_control_present_flag || this.entropy_coding_mode_flag != pictureParameterSet.entropy_coding_mode_flag) {
            return false;
        }
        PPSExt pPSExt = this.extended;
        if (pPSExt == null) {
            if (pictureParameterSet.extended != null) {
                return false;
            }
        } else if (!pPSExt.equals(pictureParameterSet.extended)) {
            return false;
        }
        int[] iArr = this.num_ref_idx_active_minus1;
        int i = iArr[0];
        int[] iArr2 = pictureParameterSet.num_ref_idx_active_minus1;
        return i == iArr2[0] && iArr[1] == iArr2[1] && this.num_slice_groups_minus1 == pictureParameterSet.num_slice_groups_minus1 && this.pic_init_qp_minus26 == pictureParameterSet.pic_init_qp_minus26 && this.pic_init_qs_minus26 == pictureParameterSet.pic_init_qs_minus26 && this.pic_order_present_flag == pictureParameterSet.pic_order_present_flag && this.pic_parameter_set_id == pictureParameterSet.pic_parameter_set_id && this.redundant_pic_cnt_present_flag == pictureParameterSet.redundant_pic_cnt_present_flag && Arrays.equals(this.run_length_minus1, pictureParameterSet.run_length_minus1) && this.seq_parameter_set_id == pictureParameterSet.seq_parameter_set_id && this.slice_group_change_direction_flag == pictureParameterSet.slice_group_change_direction_flag && this.slice_group_change_rate_minus1 == pictureParameterSet.slice_group_change_rate_minus1 && Arrays.equals(this.slice_group_id, pictureParameterSet.slice_group_id) && this.slice_group_map_type == pictureParameterSet.slice_group_map_type && Arrays.equals(this.top_left, pictureParameterSet.top_left) && this.weighted_bipred_idc == pictureParameterSet.weighted_bipred_idc && this.weighted_pred_flag == pictureParameterSet.weighted_pred_flag;
    }

    public int hashCode() {
        int iHashCode = (((((((((Arrays.hashCode(this.bottom_right) + 31) * 31) + this.chroma_qp_index_offset) * 31) + (this.constrained_intra_pred_flag ? 1231 : 1237)) * 31) + (this.deblocking_filter_control_present_flag ? 1231 : 1237)) * 31) + (this.entropy_coding_mode_flag ? 1231 : 1237)) * 31;
        PPSExt pPSExt = this.extended;
        int iHashCode2 = (iHashCode + (pPSExt == null ? 0 : pPSExt.hashCode())) * 31;
        int[] iArr = this.num_ref_idx_active_minus1;
        return ((((Arrays.hashCode(this.top_left) + ((((Arrays.hashCode(this.slice_group_id) + ((((((((Arrays.hashCode(this.run_length_minus1) + ((((((((((((((((iHashCode2 + iArr[0]) * 31) + iArr[1]) * 31) + this.num_slice_groups_minus1) * 31) + this.pic_init_qp_minus26) * 31) + this.pic_init_qs_minus26) * 31) + (this.pic_order_present_flag ? 1231 : 1237)) * 31) + this.pic_parameter_set_id) * 31) + (this.redundant_pic_cnt_present_flag ? 1231 : 1237)) * 31)) * 31) + this.seq_parameter_set_id) * 31) + (this.slice_group_change_direction_flag ? 1231 : 1237)) * 31) + this.slice_group_change_rate_minus1) * 31)) * 31) + this.slice_group_map_type) * 31)) * 31) + this.weighted_bipred_idc) * 31) + (this.weighted_pred_flag ? 1231 : 1237);
    }

    public void write(ByteBuffer byteBuffer) {
        am amVar = new am(byteBuffer);
        as.a(amVar, this.pic_parameter_set_id);
        as.a(amVar, this.seq_parameter_set_id);
        amVar.b(this.entropy_coding_mode_flag ? 1 : 0);
        amVar.b(this.pic_order_present_flag ? 1 : 0);
        as.a(amVar, this.num_slice_groups_minus1);
        if (this.num_slice_groups_minus1 > 0) {
            as.a(amVar, this.slice_group_map_type);
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            int[] iArr3 = new int[1];
            int i = this.slice_group_map_type;
            if (i == 0) {
                for (int i2 = 0; i2 <= this.num_slice_groups_minus1; i2++) {
                    as.a(amVar, iArr3[i2]);
                }
            } else if (i == 2) {
                for (int i3 = 0; i3 < this.num_slice_groups_minus1; i3++) {
                    as.a(amVar, iArr[i3]);
                    as.a(amVar, iArr2[i3]);
                }
            } else if (i == 3 || i == 4 || i == 5) {
                amVar.b(this.slice_group_change_direction_flag ? 1 : 0);
                as.a(amVar, this.slice_group_change_rate_minus1);
            } else if (i == 6) {
                int i4 = this.num_slice_groups_minus1 + 1;
                int i5 = i4 <= 4 ? i4 > 2 ? 2 : 1 : 3;
                as.a(amVar, this.slice_group_id.length);
                int i6 = 0;
                while (true) {
                    int[] iArr4 = this.slice_group_id;
                    if (i6 > iArr4.length) {
                        break;
                    }
                    amVar.a(iArr4[i6], i5);
                    i6++;
                }
            }
        }
        as.a(amVar, this.num_ref_idx_active_minus1[0]);
        as.a(amVar, this.num_ref_idx_active_minus1[1]);
        amVar.b(this.weighted_pred_flag ? 1 : 0);
        as.a(amVar, this.weighted_bipred_idc, 2);
        as.a(amVar, dp.b(this.pic_init_qp_minus26));
        as.a(amVar, dp.b(this.pic_init_qs_minus26));
        as.a(amVar, dp.b(this.chroma_qp_index_offset));
        amVar.b(this.deblocking_filter_control_present_flag ? 1 : 0);
        amVar.b(this.constrained_intra_pred_flag ? 1 : 0);
        amVar.b(this.redundant_pic_cnt_present_flag ? 1 : 0);
        PPSExt pPSExt = this.extended;
        if (pPSExt != null) {
            amVar.b(pPSExt.transform_8x8_mode_flag ? 1 : 0);
            amVar.b(this.extended.scalindMatrix != null ? 1 : 0);
            if (this.extended.scalindMatrix != null) {
                int i7 = 0;
                while (true) {
                    PPSExt pPSExt2 = this.extended;
                    if (i7 >= ((pPSExt2.transform_8x8_mode_flag ? 1 : 0) * 2) + 6) {
                        break;
                    }
                    if (i7 < 6) {
                        amVar.b(pPSExt2.scalindMatrix.ScalingList4x4[i7] != null ? 1 : 0);
                        ScalingList scalingList = this.extended.scalindMatrix.ScalingList4x4[i7];
                        if (scalingList != null) {
                            scalingList.write(amVar);
                        }
                    } else {
                        int i8 = i7 - 6;
                        amVar.b(pPSExt2.scalindMatrix.ScalingList8x8[i8] != null ? 1 : 0);
                        ScalingList scalingList2 = this.extended.scalindMatrix.ScalingList8x8[i8];
                        if (scalingList2 != null) {
                            scalingList2.write(amVar);
                        }
                    }
                    i7++;
                }
            }
            as.a(amVar, dp.b(this.extended.second_chroma_qp_index_offset));
        }
        amVar.b(1);
        amVar.a();
    }
}
