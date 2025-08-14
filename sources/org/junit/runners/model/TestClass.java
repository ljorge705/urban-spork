package org.junit.runners.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.internal.MethodSorter;

/* loaded from: classes4.dex */
public class TestClass implements Annotatable {
    private static final FieldComparator FIELD_COMPARATOR;
    private static final MethodComparator METHOD_COMPARATOR;
    private final Class<?> clazz;
    private final Map<Class<? extends Annotation>, List<FrameworkField>> fieldsForAnnotations;
    private final Map<Class<? extends Annotation>, List<FrameworkMethod>> methodsForAnnotations;

    public Class<?> getJavaClass() {
        return this.clazz;
    }

    static {
        FIELD_COMPARATOR = new FieldComparator();
        METHOD_COMPARATOR = new MethodComparator();
    }

    public TestClass(Class<?> cls) throws SecurityException {
        this.clazz = cls;
        if (cls != null && cls.getConstructors().length > 1) {
            throw new IllegalArgumentException("Test class can only have one constructor");
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        scanAnnotatedMembers(linkedHashMap, linkedHashMap2);
        this.methodsForAnnotations = makeDeeplyUnmodifiable(linkedHashMap);
        this.fieldsForAnnotations = makeDeeplyUnmodifiable(linkedHashMap2);
    }

    protected void scanAnnotatedMembers(Map<Class<? extends Annotation>, List<FrameworkMethod>> map, Map<Class<? extends Annotation>, List<FrameworkField>> map2) throws SecurityException {
        for (Class<?> cls : getSuperClasses(this.clazz)) {
            for (Method method : MethodSorter.getDeclaredMethods(cls)) {
                addToAnnotationLists(new FrameworkMethod(method), map);
            }
            for (Field field : getSortedDeclaredFields(cls)) {
                addToAnnotationLists(new FrameworkField(field), map2);
            }
        }
    }

    private static Field[] getSortedDeclaredFields(Class<?> cls) {
        Field[] declaredFields = cls.getDeclaredFields();
        Arrays.sort(declaredFields, FIELD_COMPARATOR);
        return declaredFields;
    }

    protected static <T extends FrameworkMember<T>> void addToAnnotationLists(T t, Map<Class<? extends Annotation>, List<T>> map) {
        for (Annotation annotation : t.getAnnotations()) {
            Class<? extends Annotation> clsAnnotationType = annotation.annotationType();
            List<T> annotatedMembers = getAnnotatedMembers(map, clsAnnotationType, true);
            if (t.isShadowedBy(annotatedMembers)) {
                return;
            }
            if (runsTopToBottom(clsAnnotationType)) {
                annotatedMembers.add(0, t);
            } else {
                annotatedMembers.add(t);
            }
        }
    }

    private static <T extends FrameworkMember<T>> Map<Class<? extends Annotation>, List<T>> makeDeeplyUnmodifiable(Map<Class<? extends Annotation>, List<T>> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<Class<? extends Annotation>, List<T>> entry : map.entrySet()) {
            linkedHashMap.put(entry.getKey(), Collections.unmodifiableList(entry.getValue()));
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    public List<FrameworkMethod> getAnnotatedMethods() {
        List<FrameworkMethod> listCollectValues = collectValues(this.methodsForAnnotations);
        Collections.sort(listCollectValues, METHOD_COMPARATOR);
        return listCollectValues;
    }

    public List<FrameworkMethod> getAnnotatedMethods(Class<? extends Annotation> cls) {
        return Collections.unmodifiableList(getAnnotatedMembers(this.methodsForAnnotations, cls, false));
    }

    public List<FrameworkField> getAnnotatedFields() {
        return collectValues(this.fieldsForAnnotations);
    }

    public List<FrameworkField> getAnnotatedFields(Class<? extends Annotation> cls) {
        return Collections.unmodifiableList(getAnnotatedMembers(this.fieldsForAnnotations, cls, false));
    }

    private <T> List<T> collectValues(Map<?, List<T>> map) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<List<T>> it = map.values().iterator();
        while (it.hasNext()) {
            linkedHashSet.addAll(it.next());
        }
        return new ArrayList(linkedHashSet);
    }

    private static <T> List<T> getAnnotatedMembers(Map<Class<? extends Annotation>, List<T>> map, Class<? extends Annotation> cls, boolean z) {
        if (!map.containsKey(cls) && z) {
            map.put(cls, new ArrayList());
        }
        List<T> list = map.get(cls);
        return list == null ? Collections.emptyList() : list;
    }

    private static boolean runsTopToBottom(Class<? extends Annotation> cls) {
        return cls.equals(Before.class) || cls.equals(BeforeClass.class);
    }

    private static List<Class<?>> getSuperClasses(Class<?> cls) {
        ArrayList arrayList = new ArrayList();
        while (cls != null) {
            arrayList.add(cls);
            cls = cls.getSuperclass();
        }
        return arrayList;
    }

    public String getName() {
        Class<?> cls = this.clazz;
        return cls == null ? "null" : cls.getName();
    }

    public Constructor<?> getOnlyConstructor() throws SecurityException {
        Constructor<?>[] constructors = this.clazz.getConstructors();
        Assert.assertEquals(1L, constructors.length);
        return constructors[0];
    }

    @Override // org.junit.runners.model.Annotatable
    public Annotation[] getAnnotations() {
        Class<?> cls = this.clazz;
        return cls == null ? new Annotation[0] : cls.getAnnotations();
    }

    @Override // org.junit.runners.model.Annotatable
    public <T extends Annotation> T getAnnotation(Class<T> cls) {
        Class<?> cls2 = this.clazz;
        if (cls2 == null) {
            return null;
        }
        return (T) cls2.getAnnotation(cls);
    }

    public <T> List<T> getAnnotatedFieldValues(Object obj, Class<? extends Annotation> cls, Class<T> cls2) throws IllegalArgumentException {
        ArrayList arrayList = new ArrayList();
        Iterator<FrameworkField> it = getAnnotatedFields(cls).iterator();
        while (it.hasNext()) {
            try {
                Object obj2 = it.next().get(obj);
                if (cls2.isInstance(obj2)) {
                    arrayList.add(cls2.cast(obj2));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("How did getFields return a field we couldn't access?", e);
            }
        }
        return arrayList;
    }

    public <T> List<T> getAnnotatedMethodValues(Object obj, Class<? extends Annotation> cls, Class<T> cls2) {
        ArrayList arrayList = new ArrayList();
        for (FrameworkMethod frameworkMethod : getAnnotatedMethods(cls)) {
            try {
                if (cls2.isAssignableFrom(frameworkMethod.getReturnType())) {
                    arrayList.add(cls2.cast(frameworkMethod.invokeExplosively(obj, new Object[0])));
                }
            } catch (Throwable th) {
                throw new RuntimeException("Exception in " + frameworkMethod.getName(), th);
            }
        }
        return arrayList;
    }

    public boolean isPublic() {
        return Modifier.isPublic(this.clazz.getModifiers());
    }

    public boolean isANonStaticInnerClass() {
        return this.clazz.isMemberClass() && !Modifier.isStatic(this.clazz.getModifiers());
    }

    public int hashCode() {
        Class<?> cls = this.clazz;
        if (cls == null) {
            return 0;
        }
        return cls.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.clazz == ((TestClass) obj).clazz;
    }

    private static class FieldComparator implements Comparator<Field> {
        private FieldComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Field field, Field field2) {
            return field.getName().compareTo(field2.getName());
        }
    }

    private static class MethodComparator implements Comparator<FrameworkMethod> {
        private MethodComparator() {
        }

        @Override // java.util.Comparator
        public int compare(FrameworkMethod frameworkMethod, FrameworkMethod frameworkMethod2) {
            return MethodSorter.NAME_ASCENDING.compare(frameworkMethod.getMethod(), frameworkMethod2.getMethod());
        }
    }
}
