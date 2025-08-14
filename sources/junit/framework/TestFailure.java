package junit.framework;

import java.io.PrintWriter;
import java.io.StringWriter;

/* loaded from: classes6.dex */
public class TestFailure {
    protected Test fFailedTest;
    protected Throwable fThrownException;

    public Test failedTest() {
        return this.fFailedTest;
    }

    public Throwable thrownException() {
        return this.fThrownException;
    }

    public TestFailure(Test test, Throwable th) {
        this.fFailedTest = test;
        this.fThrownException = th;
    }

    public String toString() {
        return this.fFailedTest + ": " + this.fThrownException.getMessage();
    }

    public String trace() {
        StringWriter stringWriter = new StringWriter();
        thrownException().printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public String exceptionMessage() {
        return thrownException().getMessage();
    }

    public boolean isFailure() {
        return thrownException() instanceof AssertionFailedError;
    }
}
