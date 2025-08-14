package com.uxcam.screenaction.utils;

import io.flutter.embedding.engine.renderer.FlutterRenderer;
import java.lang.reflect.Field;

/* loaded from: classes6.dex */
public class ReflectionUtil {
    public static Field findField(String str, Class cls) throws NoSuchFieldException {
        for (Class superclass = cls; superclass != Object.class; superclass = superclass.getSuperclass()) {
            for (Field field : superclass.getDeclaredFields()) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
        }
        throw new NoSuchFieldException("Field " + str + " not found for class " + cls);
    }

    public static Field findFieldFlutter(String str, Class cls) throws NoSuchFieldException {
        for (Class superclass = cls; superclass != Object.class; superclass = superclass.getSuperclass()) {
            for (Field field : superclass.getDeclaredFields()) {
                if (str.equals(field.getName())) {
                    return field;
                }
                if (str.equals("flutterRenderer") && field.getType().getName().equalsIgnoreCase(FlutterRenderer.class.getName())) {
                    return field;
                }
            }
        }
        throw new NoSuchFieldException("Field " + str + " not found for class " + cls);
    }

    public static Object getFieldValue(String str, Object obj) {
        try {
            return getFieldValueUnchecked(str, obj);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Object getFieldValueFlutter(String str, Object obj) {
        try {
            return getFieldValueUncheckedFlutter(str, obj);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Object getFieldValueUnchecked(String str, Object obj) throws NoSuchFieldException, SecurityException {
        Field fieldFindField = findField(str, obj.getClass());
        fieldFindField.setAccessible(true);
        return fieldFindField.get(obj);
    }

    public static Object getFieldValueUncheckedFlutter(String str, Object obj) throws NoSuchFieldException, SecurityException {
        Field fieldFindFieldFlutter = findFieldFlutter(str, obj.getClass());
        fieldFindFieldFlutter.setAccessible(true);
        return fieldFindFieldFlutter.get(obj);
    }
}
