package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class de {
    public static byte[] a(String str) {
        char[] charArray = str.toCharArray();
        byte[] bArr = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            bArr[i] = (byte) charArray[i];
        }
        return bArr;
    }
}
