package com.onfido.api.client.util;

/* loaded from: classes6.dex */
public class StringUtil {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean hasCharacter(String str) {
        return !isNullOrEmpty(str);
    }
}
