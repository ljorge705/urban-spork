package org.junit.internal.runners;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Deprecated
/* loaded from: classes4.dex */
public class TestMethod {
    private final Method method;
    private TestClass testClass;

    public TestMethod(Method method, TestClass testClass) {
        this.method = method;
        this.testClass = testClass;
    }

    public boolean isIgnored() {
        return this.method.getAnnotation(Ignore.class) != null;
    }

    public long getTimeout() {
        Test test = (Test) this.method.getAnnotation(Test.class);
        if (test == null) {
            return 0L;
        }
        return test.timeout();
    }

    protected Class<? extends Throwable> getExpectedException() {
        Test test = (Test) this.method.getAnnotation(Test.class);
        if (test == null || test.expected() == Test.None.class) {
            return null;
        }
        return test.expected();
    }

    boolean isUnexpected(Throwable th) {
        return !getExpectedException().isAssignableFrom(th.getClass());
    }

    boolean expectsException() {
        return getExpectedException() != null;
    }

    List<Method> getBefores() {
        return this.testClass.getAnnotatedMethods(Before.class);
    }

    List<Method> getAfters() {
        return this.testClass.getAnnotatedMethods(After.class);
    }

    public void invoke(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.method.invoke(obj, new Object[0]);
    }
}
