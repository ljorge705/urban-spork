package com.google.android.material.color.utilities;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public final class QuantizerMap implements Quantizer {
    Map<Integer, Integer> colorToCount;

    public Map<Integer, Integer> getColorToCount() {
        return this.colorToCount;
    }

    @Override // com.google.android.material.color.utilities.Quantizer
    public QuantizerResult quantize(int[] iArr, int i) {
        HashMap map = new HashMap();
        for (int i2 : iArr) {
            Integer num = (Integer) map.get(Integer.valueOf(i2));
            int iIntValue = 1;
            if (num != null) {
                iIntValue = 1 + num.intValue();
            }
            map.put(Integer.valueOf(i2), Integer.valueOf(iIntValue));
        }
        this.colorToCount = map;
        return new QuantizerResult(map);
    }
}
