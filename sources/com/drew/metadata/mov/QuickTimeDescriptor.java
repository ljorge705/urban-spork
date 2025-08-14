package com.drew.metadata.mov;

import com.drew.lang.Rational;
import com.drew.metadata.TagDescriptor;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class QuickTimeDescriptor extends TagDescriptor<QuickTimeDirectory> {
    public QuickTimeDescriptor(QuickTimeDirectory quickTimeDirectory) {
        super(quickTimeDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 260) {
            return getDurationDescription();
        }
        if (i == 4096) {
            return getMajorBrandDescription();
        }
        if (i == 4098) {
            return getCompatibleBrandsDescription();
        }
        return super.getDescription(i);
    }

    private String getMajorBrandDescription() {
        byte[] byteArray = ((QuickTimeDirectory) this._directory).getByteArray(4096);
        if (byteArray == null) {
            return null;
        }
        return QuickTimeDictionary.lookup(4096, new String(byteArray));
    }

    private String getCompatibleBrandsDescription() {
        String[] stringArray = ((QuickTimeDirectory) this._directory).getStringArray(4098);
        if (stringArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : stringArray) {
            String strLookup = QuickTimeDictionary.lookup(4096, str);
            if (strLookup != null) {
                str = strLookup;
            }
            arrayList.add(str);
        }
        return Arrays.toString(arrayList.toArray());
    }

    private String getDurationDescription() {
        Rational rational = ((QuickTimeDirectory) this._directory).getRational(260);
        if (rational == null) {
            return null;
        }
        double dDoubleValue = rational.doubleValue();
        return String.format("%1$02d:%2$02d:%3$02d", Integer.valueOf((int) (dDoubleValue / Math.pow(60.0d, 2.0d))), Integer.valueOf((int) ((dDoubleValue / Math.pow(60.0d, 1.0d)) - (r2.intValue() * 60))), Integer.valueOf((int) Math.ceil((dDoubleValue / Math.pow(60.0d, 0.0d)) - (r3.intValue() * 60))));
    }
}
