package junit.framework;

import org.junit.runner.Describable;
import org.junit.runner.Description;

/* loaded from: classes6.dex */
public class JUnit4TestCaseFacade implements Test, Describable {
    private final Description fDescription;

    @Override // junit.framework.Test
    public int countTestCases() {
        return 1;
    }

    @Override // org.junit.runner.Describable
    public Description getDescription() {
        return this.fDescription;
    }

    JUnit4TestCaseFacade(Description description) {
        this.fDescription = description;
    }

    public String toString() {
        return getDescription().toString();
    }

    @Override // junit.framework.Test
    public void run(TestResult testResult) {
        throw new RuntimeException("This test stub created only for informational purposes.");
    }
}
