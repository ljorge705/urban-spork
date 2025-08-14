package org.spongycastle.jcajce.provider.symmetric.util;

import java.security.AccessController;
import java.security.PrivilegedAction;

/* loaded from: classes7.dex */
public class ClassUtil {
    public static Class loadClass(Class cls, final String str) {
        try {
            ClassLoader classLoader = cls.getClassLoader();
            if (classLoader != null) {
                return classLoader.loadClass(str);
            }
            return (Class) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.spongycastle.jcajce.provider.symmetric.util.ClassUtil.1
                @Override // java.security.PrivilegedAction
                public Object run() {
                    try {
                        return Class.forName(str);
                    } catch (Exception unused) {
                        return null;
                    }
                }
            });
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
