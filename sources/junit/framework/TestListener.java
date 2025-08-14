package junit.framework;

/* loaded from: classes6.dex */
public interface TestListener {
    void addError(Test test, Throwable th);

    void addFailure(Test test, AssertionFailedError assertionFailedError);

    void endTest(Test test);

    void startTest(Test test);
}
