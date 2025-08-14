package junit.framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class TestResult {
    protected List<TestFailure> fFailures = new ArrayList();
    protected List<TestFailure> fErrors = new ArrayList();
    protected List<TestListener> fListeners = new ArrayList();
    protected int fRunTests = 0;
    private boolean fStop = false;

    public synchronized void addError(Test test, Throwable th) {
        this.fErrors.add(new TestFailure(test, th));
        Iterator<TestListener> it = cloneListeners().iterator();
        while (it.hasNext()) {
            it.next().addError(test, th);
        }
    }

    public synchronized void addFailure(Test test, AssertionFailedError assertionFailedError) {
        this.fFailures.add(new TestFailure(test, assertionFailedError));
        Iterator<TestListener> it = cloneListeners().iterator();
        while (it.hasNext()) {
            it.next().addFailure(test, assertionFailedError);
        }
    }

    public synchronized void addListener(TestListener testListener) {
        this.fListeners.add(testListener);
    }

    public synchronized void removeListener(TestListener testListener) {
        this.fListeners.remove(testListener);
    }

    private synchronized List<TestListener> cloneListeners() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        arrayList.addAll(this.fListeners);
        return arrayList;
    }

    public void endTest(Test test) {
        Iterator<TestListener> it = cloneListeners().iterator();
        while (it.hasNext()) {
            it.next().endTest(test);
        }
    }

    public synchronized int errorCount() {
        return this.fErrors.size();
    }

    public synchronized Enumeration<TestFailure> errors() {
        return Collections.enumeration(this.fErrors);
    }

    public synchronized int failureCount() {
        return this.fFailures.size();
    }

    public synchronized Enumeration<TestFailure> failures() {
        return Collections.enumeration(this.fFailures);
    }

    protected void run(final TestCase testCase) {
        startTest(testCase);
        runProtected(testCase, new Protectable() { // from class: junit.framework.TestResult.1
            @Override // junit.framework.Protectable
            public void protect() throws Throwable {
                testCase.runBare();
            }
        });
        endTest(testCase);
    }

    public synchronized int runCount() {
        return this.fRunTests;
    }

    public void runProtected(Test test, Protectable protectable) {
        try {
            protectable.protect();
        } catch (ThreadDeath e) {
            throw e;
        } catch (AssertionFailedError e2) {
            addFailure(test, e2);
        } catch (Throwable th) {
            addError(test, th);
        }
    }

    public synchronized boolean shouldStop() {
        return this.fStop;
    }

    public void startTest(Test test) {
        int iCountTestCases = test.countTestCases();
        synchronized (this) {
            this.fRunTests += iCountTestCases;
        }
        Iterator<TestListener> it = cloneListeners().iterator();
        while (it.hasNext()) {
            it.next().startTest(test);
        }
    }

    public synchronized void stop() {
        this.fStop = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean wasSuccessful() {
        /*
            r1 = this;
            monitor-enter(r1)
            int r0 = r1.failureCount()     // Catch: java.lang.Throwable -> L12
            if (r0 != 0) goto Lf
            int r0 = r1.errorCount()     // Catch: java.lang.Throwable -> L12
            if (r0 != 0) goto Lf
            r0 = 1
            goto L10
        Lf:
            r0 = 0
        L10:
            monitor-exit(r1)
            return r0
        L12:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: junit.framework.TestResult.wasSuccessful():boolean");
    }
}
