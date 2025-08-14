package org.tensorflow.lite.support.label;

import java.util.ArrayList;
import java.util.List;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes7.dex */
public class LabelUtil {
    public static List<String> mapValueToLabels(TensorBuffer tensorBuffer, List<String> labels, int offset) {
        SupportPreconditions.checkNotNull(tensorBuffer, "Given tensor should not be null");
        SupportPreconditions.checkNotNull(labels, "Given labels should not be null");
        int[] intArray = tensorBuffer.getIntArray();
        ArrayList arrayList = new ArrayList();
        for (int i : intArray) {
            int i2 = i + offset;
            if (i2 < 0 || i2 >= labels.size()) {
                arrayList.add("");
            } else {
                arrayList.add(labels.get(i2));
            }
        }
        return arrayList;
    }

    private LabelUtil() {
    }
}
