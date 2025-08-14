package com.scottyab.rootbeer.util;

/* loaded from: classes6.dex */
public final class Utils {
    private Utils() throws InstantiationException {
        throw new InstantiationException("This class is not for instantiation");
    }

    public static boolean isSelinuxFlagInEnabled() throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return "1".equals((String) cls.getMethod("get", String.class).invoke(cls, "ro.build.selinux"));
        } catch (Exception unused) {
            return false;
        }
    }
}
