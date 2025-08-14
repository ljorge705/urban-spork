package org.apache.commons.lang3;

/* loaded from: classes4.dex */
public class ClassPathUtils {
    public static String toFullyQualifiedName(Class<?> cls, String str) {
        Validate.notNull(cls, "Parameter '%s' must not be null!", "context");
        Validate.notNull(str, "Parameter '%s' must not be null!", "resourceName");
        return toFullyQualifiedName(cls.getPackage(), str);
    }

    public static String toFullyQualifiedName(Package r2, String str) {
        Validate.notNull(r2, "Parameter '%s' must not be null!", "context");
        Validate.notNull(str, "Parameter '%s' must not be null!", "resourceName");
        return r2.getName() + "." + str;
    }

    public static String toFullyQualifiedPath(Class<?> cls, String str) {
        Validate.notNull(cls, "Parameter '%s' must not be null!", "context");
        Validate.notNull(str, "Parameter '%s' must not be null!", "resourceName");
        return toFullyQualifiedPath(cls.getPackage(), str);
    }

    public static String toFullyQualifiedPath(Package r3, String str) {
        Validate.notNull(r3, "Parameter '%s' must not be null!", "context");
        Validate.notNull(str, "Parameter '%s' must not be null!", "resourceName");
        return r3.getName().replace(ClassUtils.PACKAGE_SEPARATOR_CHAR, '/') + "/" + str;
    }
}
