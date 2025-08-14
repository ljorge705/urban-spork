package org.bouncycastle.pqc.crypto.crystals.kyber;

/* loaded from: classes4.dex */
final class CBD {
    CBD() {
    }

    private static long convertByteTo24BitUnsignedInt(byte[] bArr, int i) {
        return ((bArr[i + 2] & 255) << 16) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8);
    }

    private static long convertByteTo32BitUnsignedInt(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static void kyberCBD(Poly poly, byte[] bArr, int i) {
        if (i != 3) {
            for (int i2 = 0; i2 < 32; i2++) {
                long jConvertByteTo32BitUnsignedInt = convertByteTo32BitUnsignedInt(bArr, i2 * 4);
                long j = (jConvertByteTo32BitUnsignedInt & 1431655765) + ((jConvertByteTo32BitUnsignedInt >> 1) & 1431655765);
                for (int i3 = 0; i3 < 8; i3++) {
                    int i4 = i3 * 4;
                    poly.setCoeffIndex((i2 * 8) + i3, (short) (((short) ((j >> i4) & 3)) - ((short) (3 & (j >> (i4 + i))))));
                }
            }
            return;
        }
        for (int i5 = 0; i5 < 64; i5++) {
            long jConvertByteTo24BitUnsignedInt = convertByteTo24BitUnsignedInt(bArr, i5 * 3);
            long j2 = (jConvertByteTo24BitUnsignedInt & 2396745) + ((jConvertByteTo24BitUnsignedInt >> 1) & 2396745) + ((jConvertByteTo24BitUnsignedInt >> 2) & 2396745);
            for (int i6 = 0; i6 < 4; i6++) {
                int i7 = i6 * 6;
                poly.setCoeffIndex((i5 * 4) + i6, (short) (((short) ((j2 >> i7) & 7)) - ((short) (7 & (j2 >> (i7 + 3))))));
            }
        }
    }
}
