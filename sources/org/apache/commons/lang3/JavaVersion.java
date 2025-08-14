package org.apache.commons.lang3;

import org.apache.commons.lang3.math.NumberUtils;

/* loaded from: classes4.dex */
public enum JavaVersion {
    JAVA_0_9(1.5f, "0.9"),
    JAVA_1_1(1.1f, "1.1"),
    JAVA_1_2(1.2f, "1.2"),
    JAVA_1_3(1.3f, "1.3"),
    JAVA_1_4(1.4f, "1.4"),
    JAVA_1_5(1.5f, "1.5"),
    JAVA_1_6(1.6f, "1.6"),
    JAVA_1_7(1.7f, "1.7"),
    JAVA_1_8(1.8f, "1.8"),
    JAVA_1_9(9.0f, "9"),
    JAVA_9(9.0f, "9"),
    JAVA_10(10.0f, "10"),
    JAVA_11(11.0f, "11"),
    JAVA_RECENT(maxVersion(), Float.toString(maxVersion()));

    private final String name;
    private final float value;

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }

    JavaVersion(float f, String str) {
        this.value = f;
        this.name = str;
    }

    public boolean atLeast(JavaVersion javaVersion) {
        return this.value >= javaVersion.value;
    }

    static JavaVersion getJavaVersion(String str) {
        return get(str);
    }

    static JavaVersion get(String str) {
        if ("0.9".equals(str)) {
            return JAVA_0_9;
        }
        if ("1.1".equals(str)) {
            return JAVA_1_1;
        }
        if ("1.2".equals(str)) {
            return JAVA_1_2;
        }
        if ("1.3".equals(str)) {
            return JAVA_1_3;
        }
        if ("1.4".equals(str)) {
            return JAVA_1_4;
        }
        if ("1.5".equals(str)) {
            return JAVA_1_5;
        }
        if ("1.6".equals(str)) {
            return JAVA_1_6;
        }
        if ("1.7".equals(str)) {
            return JAVA_1_7;
        }
        if ("1.8".equals(str)) {
            return JAVA_1_8;
        }
        if ("9".equals(str)) {
            return JAVA_9;
        }
        if ("10".equals(str)) {
            return JAVA_10;
        }
        if ("11".equals(str)) {
            return JAVA_11;
        }
        if (str == null) {
            return null;
        }
        float floatVersion = toFloatVersion(str);
        if (floatVersion - 1.0d < 1.0d) {
            int iMax = Math.max(str.indexOf(46), str.indexOf(44));
            if (Float.parseFloat(str.substring(iMax + 1, Math.max(str.length(), str.indexOf(44, iMax)))) > 0.9f) {
                return JAVA_RECENT;
            }
        } else if (floatVersion > 10.0f) {
            return JAVA_RECENT;
        }
        return null;
    }

    private static float maxVersion() {
        float floatVersion = toFloatVersion(System.getProperty("java.specification.version", "99.0"));
        if (floatVersion > 0.0f) {
            return floatVersion;
        }
        return 99.0f;
    }

    private static float toFloatVersion(String str) {
        if (str.contains(".")) {
            String[] strArrSplit = str.split("\\.");
            if (strArrSplit.length >= 2) {
                return NumberUtils.toFloat(strArrSplit[0] + ClassUtils.PACKAGE_SEPARATOR_CHAR + strArrSplit[1], -1.0f);
            }
            return -1.0f;
        }
        return NumberUtils.toFloat(str, -1.0f);
    }
}
