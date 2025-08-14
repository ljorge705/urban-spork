package org.spongycastle.tsp;

/* loaded from: classes7.dex */
public class TSPValidationException extends TSPException {
    private int failureCode;

    public int getFailureCode() {
        return this.failureCode;
    }

    public TSPValidationException(String str) {
        super(str);
        this.failureCode = -1;
    }

    public TSPValidationException(String str, int i) {
        super(str);
        this.failureCode = i;
    }
}
