package com.uxcam.video.screen.codec.codecs.h264.io.model;

import com.uxcam.internals.al;
import com.uxcam.internals.am;
import com.uxcam.internals.ar;
import com.uxcam.internals.as;
import com.uxcam.internals.dp;

/* loaded from: classes6.dex */
public class ScalingList {
    public int[] scalingList;
    public boolean useDefaultScalingMatrixFlag;

    public static ScalingList read(al alVar, int i) {
        ScalingList scalingList = new ScalingList();
        scalingList.scalingList = new int[i];
        int iA = 8;
        int i2 = 8;
        int i3 = 0;
        while (i3 < i) {
            if (iA != 0) {
                iA = ((ar.a(alVar) + i2) + 256) % 256;
                scalingList.useDefaultScalingMatrixFlag = i3 == 0 && iA == 0;
            }
            int[] iArr = scalingList.scalingList;
            if (iA != 0) {
                i2 = iA;
            }
            iArr[i3] = i2;
            i3++;
        }
        return scalingList;
    }

    public void write(am amVar) {
        int i = 0;
        if (this.useDefaultScalingMatrixFlag) {
            as.a(amVar, dp.b(0));
            return;
        }
        int i2 = 8;
        while (true) {
            if (i >= this.scalingList.length) {
                return;
            }
            as.a(amVar, dp.b((r2[i] - i2) - 256));
            i2 = this.scalingList[i];
            i++;
        }
    }
}
