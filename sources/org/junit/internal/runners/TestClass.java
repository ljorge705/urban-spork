package org.junit.internal.runners;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.MethodSorter;

@Deprecated
/* loaded from: classes4.dex */
public class TestClass {
    private final Class<?> klass;

    public Class<?> getJavaClass() {
        return this.klass;
    }

    public TestClass(Class<?> cls) {
        this.klass = cls;
    }

    public List<Method> getTestMethods() {
        return getAnnotatedMethods(Test.class);
    }

    List<Method> getBefores() {
        return getAnnotatedMethods(BeforeClass.class);
    }

    List<Method> getAfters() {
        return getAnnotatedMethods(AfterClass.class);
    }

    public List<Method> getAnnotatedMethods(Class<? extends Annotation> cls) throws SecurityException {
        ArrayList arrayList = new ArrayList();
        Iterator<Class<?>> it = getSuperClasses(this.klass).iterator();
        while (it.hasNext()) {
            for (Method method : MethodSorter.getDeclaredMethods(it.next())) {
                if (method.getAnnotation(cls) != null && !isShadowed(method, arrayList)) {
                    arrayList.add(method);
                }
            }
        }
        if (runsTopToBottom(cls)) {
            Collections.reverse(arrayList);
        }
        return arrayList;
    }

    private boolean runsTopToBottom(Class<? extends Annotation> cls) {
        return cls.equals(Before.class) || cls.equals(BeforeClass.class);
    }

    private boolean isShadowed(Method method, List<Method> list) {
        Iterator<Method> it = list.iterator();
        while (it.hasNext()) {
            if (isShadowed(method, it.next())) {
                return true;
            }
        }
        return false;
    }

    private boolean isShadowed(Method method, Method method2) {
        if (!method2.getName().equals(method.getName()) || method2.getParameterTypes().length != method.getParameterTypes().length) {
            return false;
        }
        for (int i = 0; i < method2.getParameterTypes().length; i++) {
            if (!method2.getParameterTypes()[i].equals(method.getParameterTypes()[i])) {
                return false;
            }
        }
        return true;
    }

    private List<Class<?>> getSuperClasses(Class<?> cls) {
        ArrayList arrayList = new ArrayList();
        while (cls != null) {
            arrayList.add(cls);
            cls = cls.getSuperclass();
        }
        return arrayList;
    }

    public Constructor<?> getConstructor() throws NoSuchMethodException, SecurityException {
        return this.klass.getConstructor(new Class[0]);
    }

    public String getName() {
        return this.klass.getName();
    }
}
