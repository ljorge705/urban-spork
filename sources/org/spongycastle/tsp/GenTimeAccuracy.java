package org.spongycastle.tsp;

import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.tsp.Accuracy;

/* loaded from: classes7.dex */
public class GenTimeAccuracy {
    private Accuracy accuracy;

    public GenTimeAccuracy(Accuracy accuracy) {
        this.accuracy = accuracy;
    }

    public int getSeconds() {
        return getTimeComponent(this.accuracy.getSeconds());
    }

    public int getMillis() {
        return getTimeComponent(this.accuracy.getMillis());
    }

    public int getMicros() {
        return getTimeComponent(this.accuracy.getMicros());
    }

    private int getTimeComponent(ASN1Integer aSN1Integer) {
        if (aSN1Integer != null) {
            return aSN1Integer.getValue().intValue();
        }
        return 0;
    }

    public String toString() {
        return getSeconds() + "." + format(getMillis()) + format(getMicros());
    }

    private String format(int i) {
        if (i < 10) {
            return "00" + i;
        }
        if (i < 100) {
            return "0" + i;
        }
        return Integer.toString(i);
    }
}
