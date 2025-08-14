package com.nimbusds.jose.crypto.utils;

/* loaded from: classes2.dex */
public class ConstantTimeUtils {
    public static boolean areEqual(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            i |= bArr[i2] ^ bArr2[i2];
        }
        return i == 0;
    }

    private ConstantTimeUtils() {
    }
}
