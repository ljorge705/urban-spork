package junit.framework;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.junit.internal.MethodSorter;

/* loaded from: classes6.dex */
public class TestSuite implements Test {
    private String fName;
    private Vector<Test> fTests;

    public String getName() {
        return this.fName;
    }

    public void setName(String str) {
        this.fName = str;
    }

    public static Test createTest(Class<?> cls, String str) throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
        Object objNewInstance;
        try {
            Constructor<?> testConstructor = getTestConstructor(cls);
            try {
                if (testConstructor.getParameterTypes().length == 0) {
                    objNewInstance = testConstructor.newInstance(new Object[0]);
                    if (objNewInstance instanceof TestCase) {
                        ((TestCase) objNewInstance).setName(str);
                    }
                } else {
                    objNewInstance = testConstructor.newInstance(str);
                }
                return (Test) objNewInstance;
            } catch (IllegalAccessException e) {
                return warning("Cannot access test case: " + str + " (" + exceptionToString(e) + ")");
            } catch (InstantiationException e2) {
                return warning("Cannot instantiate test case: " + str + " (" + exceptionToString(e2) + ")");
            } catch (InvocationTargetException e3) {
                return warning("Exception in constructor: " + str + " (" + exceptionToString(e3.getTargetException()) + ")");
            }
        } catch (NoSuchMethodException unused) {
            return warning("Class " + cls.getName() + " has no public constructor TestCase(String name) or TestCase()");
        }
    }

    public static Constructor<?> getTestConstructor(Class<?> cls) throws NoSuchMethodException {
        try {
            return cls.getConstructor(String.class);
        } catch (NoSuchMethodException unused) {
            return cls.getConstructor(new Class[0]);
        }
    }

    public static Test warning(final String str) {
        return new TestCase("warning") { // from class: junit.framework.TestSuite.1
            @Override // junit.framework.TestCase
            protected void runTest() {
                fail(str);
            }
        };
    }

    private static String exceptionToString(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public TestSuite() {
        this.fTests = new Vector<>(10);
    }

    public TestSuite(Class<?> cls) throws SecurityException {
        this.fTests = new Vector<>(10);
        addTestsFromTestCase(cls);
    }

    private void addTestsFromTestCase(Class<?> cls) throws SecurityException {
        this.fName = cls.getName();
        try {
            getTestConstructor(cls);
            if (!Modifier.isPublic(cls.getModifiers())) {
                addTest(warning("Class " + cls.getName() + " is not public"));
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (Class<?> superclass = cls; Test.class.isAssignableFrom(superclass); superclass = superclass.getSuperclass()) {
                for (Method method : MethodSorter.getDeclaredMethods(superclass)) {
                    addTestMethod(method, arrayList, cls);
                }
            }
            if (this.fTests.size() == 0) {
                addTest(warning("No tests found in " + cls.getName()));
            }
        } catch (NoSuchMethodException unused) {
            addTest(warning("Class " + cls.getName() + " has no public constructor TestCase(String name) or TestCase()"));
        }
    }

    public TestSuite(Class<? extends TestCase> cls, String str) {
        this(cls);
        setName(str);
    }

    public TestSuite(String str) {
        this.fTests = new Vector<>(10);
        setName(str);
    }

    public TestSuite(Class<?>... clsArr) {
        this.fTests = new Vector<>(10);
        for (Class<?> cls : clsArr) {
            addTest(testCaseForClass(cls));
        }
    }

    private Test testCaseForClass(Class<?> cls) {
        if (TestCase.class.isAssignableFrom(cls)) {
            return new TestSuite(cls.asSubclass(TestCase.class));
        }
        return warning(cls.getCanonicalName() + " does not extend TestCase");
    }

    public TestSuite(Class<? extends TestCase>[] clsArr, String str) {
        this(clsArr);
        setName(str);
    }

    public void addTest(Test test) {
        this.fTests.add(test);
    }

    public void addTestSuite(Class<? extends TestCase> cls) {
        addTest(new TestSuite(cls));
    }

    @Override // junit.framework.Test
    public int countTestCases() {
        Iterator<Test> it = this.fTests.iterator();
        int iCountTestCases = 0;
        while (it.hasNext()) {
            iCountTestCases += it.next().countTestCases();
        }
        return iCountTestCases;
    }

    @Override // junit.framework.Test
    public void run(TestResult testResult) {
        Iterator<Test> it = this.fTests.iterator();
        while (it.hasNext()) {
            Test next = it.next();
            if (testResult.shouldStop()) {
                return;
            } else {
                runTest(next, testResult);
            }
        }
    }

    public void runTest(Test test, TestResult testResult) {
        test.run(testResult);
    }

    public Test testAt(int i) {
        return this.fTests.get(i);
    }

    public int testCount() {
        return this.fTests.size();
    }

    public Enumeration<Test> tests() {
        return this.fTests.elements();
    }

    public String toString() {
        if (getName() != null) {
            return getName();
        }
        return super.toString();
    }

    private void addTestMethod(Method method, List<String> list, Class<?> cls) {
        String name = method.getName();
        if (list.contains(name)) {
            return;
        }
        if (!isPublicTestMethod(method)) {
            if (isTestMethod(method)) {
                addTest(warning("Test method isn't public: " + method.getName() + "(" + cls.getCanonicalName() + ")"));
            }
        } else {
            list.add(name);
            addTest(createTest(cls, name));
        }
    }

    private boolean isPublicTestMethod(Method method) {
        return isTestMethod(method) && Modifier.isPublic(method.getModifiers());
    }

    private boolean isTestMethod(Method method) {
        return method.getParameterTypes().length == 0 && method.getName().startsWith("test") && method.getReturnType().equals(Void.TYPE);
    }
}
