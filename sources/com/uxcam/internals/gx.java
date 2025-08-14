package com.uxcam.internals;

import com.clevertap.android.sdk.Constants;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes6.dex */
public final class gx {

    /* renamed from: a, reason: collision with root package name */
    public static final HashSet f187a;

    static {
        HashSet hashSet = new HashSet();
        f187a = hashSet;
        hashSet.add(String.class);
        hashSet.add(Byte.class);
        hashSet.add(Short.class);
        hashSet.add(Integer.class);
        hashSet.add(Long.class);
        hashSet.add(Float.class);
        hashSet.add(Double.class);
        hashSet.add(Character.class);
    }

    public static void a(Object obj, StringBuilder sb, String... strArr) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        sb.append("{\n");
        HashSet hashSet = new HashSet(Arrays.asList(strArr));
        for (Method method : obj.getClass().getMethods()) {
            if (method.getName().startsWith("get") && method.getParameterTypes().length == 0 && (!Void.TYPE.equals(method.getReturnType()))) {
                try {
                    String strSubstring = method.getName().substring(3);
                    String str = strSubstring.substring(0, 1).toLowerCase() + strSubstring.substring(1);
                    if (strArr.length <= 0 || hashSet.contains(str)) {
                        Object objInvoke = method.invoke(obj, new Object[0]);
                        if (objInvoke.getClass().isPrimitive() || f187a.contains(objInvoke.getClass()) || (objInvoke instanceof Iterable)) {
                            sb.append(str + ": ");
                            a(sb, objInvoke);
                            sb.append(",\n");
                        }
                    }
                } catch (Exception unused) {
                }
            }
        }
        sb.append("}");
    }

    public static void a(StringBuilder sb, Object obj) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (obj == String.class) {
            sb.append("'");
            sb.append((String) obj);
            sb.append("'");
        } else {
            if (obj instanceof Iterable) {
                Iterator it = ((Iterable) obj).iterator();
                sb.append("[");
                while (it.hasNext()) {
                    a(it.next(), sb, new String[0]);
                    if (it.hasNext()) {
                        sb.append(Constants.SEPARATOR_COMMA);
                    }
                }
                sb.append("]");
                return;
            }
            sb.append(obj);
        }
    }
}
