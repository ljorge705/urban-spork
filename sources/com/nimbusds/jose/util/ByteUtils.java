package com.nimbusds.jose.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes2.dex */
public class ByteUtils {
    public static int bitLength(int i) {
        return i * 8;
    }

    public static byte[] concat(byte[]... bArr) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (byte[] bArr2 : bArr) {
                if (bArr2 != null) {
                    byteArrayOutputStream.write(bArr2);
                }
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static byte[] subArray(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    public static int safeBitLength(int i) throws IntegerOverflowException {
        long j = i * 8;
        int i2 = (int) j;
        if (i2 == j) {
            return i2;
        }
        throw new IntegerOverflowException();
    }

    public static int bitLength(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        return bitLength(bArr.length);
    }

    public static int safeBitLength(byte[] bArr) throws IntegerOverflowException {
        if (bArr == null) {
            return 0;
        }
        return safeBitLength(bArr.length);
    }

    public static int byteLength(int i) {
        return i / 8;
    }

    public static boolean isZeroFilled(byte[] bArr) {
        for (byte b : bArr) {
            if (b != 0) {
                return false;
            }
        }
        return true;
    }
}
