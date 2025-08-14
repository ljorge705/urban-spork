package com.github.barteksc.pdfviewer.util;

import java.util.ArrayList;

/* loaded from: classes5.dex */
public class ArrayUtils {
    private ArrayUtils() {
    }

    public static int[] deleteDuplicatedPages(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        int iIntValue = -1;
        for (int i : iArr) {
            Integer numValueOf = Integer.valueOf(i);
            if (iIntValue != numValueOf.intValue()) {
                arrayList.add(numValueOf);
            }
            iIntValue = numValueOf.intValue();
        }
        int[] iArr2 = new int[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            iArr2[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr2;
    }

    public static int[] calculateIndexesInDuplicateArray(int[] iArr) {
        int[] iArr2 = new int[iArr.length];
        if (iArr.length == 0) {
            return iArr2;
        }
        int i = 0;
        iArr2[0] = 0;
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (iArr[i2] != iArr[i2 - 1]) {
                i++;
            }
            iArr2[i2] = i;
        }
        return iArr2;
    }

    public static String arrayToString(int[] iArr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < iArr.length; i++) {
            sb.append(iArr[i]);
            if (i != iArr.length - 1) {
                sb.append(com.clevertap.android.sdk.Constants.SEPARATOR_COMMA);
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
