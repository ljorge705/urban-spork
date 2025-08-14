package org.spongycastle.util.test;

/* loaded from: classes7.dex */
public class TestFailedException extends RuntimeException {
    private TestResult _result;

    public TestResult getResult() {
        return this._result;
    }

    public TestFailedException(TestResult testResult) {
        this._result = testResult;
    }
}
