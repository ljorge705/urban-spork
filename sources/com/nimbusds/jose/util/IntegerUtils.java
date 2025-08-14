package com.nimbusds.jose.util;

/* loaded from: classes2.dex */
public class IntegerUtils {
    public static byte[] toBytes(int i) {
        return new byte[]{(byte) (i >>> 24), (byte) ((i >>> 16) & 255), (byte) ((i >>> 8) & 255), (byte) (i & 255)};
    }

    private IntegerUtils() {
    }
}
