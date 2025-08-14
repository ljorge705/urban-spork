package org.bouncycastle.pqc.crypto.falcon;

/* loaded from: classes4.dex */
class FalconCodec {
    final byte[] max_fg_bits = {0, 8, 8, 8, 8, 8, 7, 7, 6, 6, 5};
    final byte[] max_FG_bits = {0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
    final byte[] max_sig_bits = {0, 10, 11, 11, 12, 12, 12, 12, 12, 12, 12};

    FalconCodec() {
    }

    int comp_decode(short[] sArr, int i, int i2, byte[] bArr, int i3, int i4) {
        int i5 = 1 << i2;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < i5; i9++) {
            if (i8 >= i4) {
                return 0;
            }
            i6 = (i6 << 8) | (bArr[i3 + i8] & 255);
            i8++;
            int i10 = i6 >>> i7;
            int i11 = i10 & 128;
            int i12 = i10 & 127;
            do {
                if (i7 == 0) {
                    if (i8 >= i4) {
                        return 0;
                    }
                    i6 = (i6 << 8) | (bArr[i3 + i8] & 255);
                    i8++;
                    i7 = 8;
                }
                i7--;
                if (((i6 >>> i7) & 1) == 0) {
                    i12 += 128;
                } else {
                    if (i11 != 0 && i12 == 0) {
                        return 0;
                    }
                    int i13 = i + i9;
                    if (i11 != 0) {
                        i12 = -i12;
                    }
                    sArr[i13] = (short) i12;
                }
            } while (i12 <= 2047);
            return 0;
        }
        if ((((1 << i7) - 1) & i6) != 0) {
            return 0;
        }
        return i8;
    }

    int comp_encode(byte[] bArr, int i, int i2, short[] sArr, int i3, int i4) {
        int i5 = 1 << i4;
        for (int i6 = 0; i6 < i5; i6++) {
            short s = sArr[i3 + i6];
            if (s < -2047 || s > 2047) {
                return 0;
            }
        }
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < i5; i10++) {
            int i11 = i8 << 1;
            short s2 = sArr[i3 + i10];
            int i12 = s2;
            if (s2 < 0) {
                i11 |= 1;
                i12 = -s2;
            }
            int i13 = (i11 << 7) | (i12 & 127);
            int i14 = (i12 >>> 7) + 1;
            i8 = (i13 << i14) | 1;
            i7 = i7 + 8 + i14;
            while (i7 >= 8) {
                i7 -= 8;
                if (bArr != null) {
                    if (i9 >= i2) {
                        return 0;
                    }
                    bArr[i + i9] = (byte) (i8 >>> i7);
                }
                i9++;
            }
        }
        if (i7 <= 0) {
            return i9;
        }
        if (bArr != null) {
            if (i9 >= i2) {
                return 0;
            }
            bArr[i + i9] = (byte) (i8 << (8 - i7));
        }
        return i9 + 1;
    }

    int modq_decode(short[] sArr, int i, int i2, byte[] bArr, int i3, int i4) {
        int i5 = 1 << i2;
        int i6 = ((i5 * 14) + 7) >> 3;
        if (i6 > i4) {
            return 0;
        }
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 < i5) {
            int i10 = i3 + 1;
            i8 = (i8 << 8) | (bArr[i3] & 255);
            int i11 = i9 + 8;
            if (i11 >= 14) {
                i9 -= 6;
                int i12 = (i8 >>> i9) & 16383;
                if (i12 >= 12289) {
                    return 0;
                }
                sArr[i + i7] = (short) i12;
                i7++;
            } else {
                i9 = i11;
            }
            i3 = i10;
        }
        if ((((1 << i9) - 1) & i8) != 0) {
            return 0;
        }
        return i6;
    }

    int modq_encode(byte[] bArr, int i, int i2, short[] sArr, int i3, int i4) {
        int i5 = 1 << i4;
        for (int i6 = 0; i6 < i5; i6++) {
            if ((65535 & sArr[i3 + i6]) >= 12289) {
                return 0;
            }
        }
        int i7 = ((i5 * 14) + 7) >> 3;
        if (bArr == null) {
            return i7;
        }
        if (i7 > i2) {
            return 0;
        }
        int i8 = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < i5; i10++) {
            i9 = (i9 << 14) | (sArr[i3 + i10] & 65535);
            i8 += 14;
            while (i8 >= 8) {
                i8 -= 8;
                bArr[i] = (byte) (i9 >> i8);
                i++;
            }
        }
        if (i8 > 0) {
            bArr[i] = (byte) (i9 << (8 - i8));
        }
        return i7;
    }

    int trim_i16_decode(short[] sArr, int i, int i2, int i3, byte[] bArr, int i4, int i5) {
        int i6 = 1 << i2;
        int i7 = ((i6 * i3) + 7) >> 3;
        if (i7 > i5) {
            return 0;
        }
        int i8 = (1 << i3) - 1;
        int i9 = 1 << (i3 - 1);
        int i10 = i4;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        while (i11 < i6) {
            int i14 = i10 + 1;
            i12 = (i12 << 8) | (bArr[i10] & 255);
            i13 += 8;
            while (i13 >= i3 && i11 < i6) {
                i13 -= i3;
                int i15 = (i12 >>> i13) & i8;
                int i16 = i15 | (-(i15 & i9));
                if (i16 == (-i9)) {
                    return 0;
                }
                sArr[i + i11] = (short) (i16 | (-(i16 & i9)));
                i11++;
            }
            i10 = i14;
        }
        if ((((1 << i13) - 1) & i12) != 0) {
            return 0;
        }
        return i7;
    }

    int trim_i16_encode(byte[] bArr, int i, int i2, short[] sArr, int i3, int i4, int i5) {
        int i6 = 1 << i4;
        int i7 = (1 << (i5 - 1)) - 1;
        int i8 = -i7;
        for (int i9 = 0; i9 < i6; i9++) {
            short s = sArr[i3 + i9];
            if (s < i8 || s > i7) {
                return 0;
            }
        }
        int i10 = ((i6 * i5) + 7) >> 3;
        if (bArr == null) {
            return i10;
        }
        if (i10 > i2) {
            return 0;
        }
        int i11 = (1 << i5) - 1;
        int i12 = 0;
        int i13 = 0;
        for (int i14 = 0; i14 < i6; i14++) {
            i13 = (i13 << i5) | (sArr[i3 + i14] & 4095 & i11);
            i12 += i5;
            while (i12 >= 8) {
                i12 -= 8;
                bArr[i] = (byte) (i13 >> i12);
                i++;
            }
        }
        if (i12 > 0) {
            bArr[i] = (byte) (i13 << (8 - i12));
        }
        return i10;
    }

    int trim_i8_decode(byte[] bArr, int i, int i2, int i3, byte[] bArr2, int i4, int i5) {
        int i6 = 1 << i2;
        int i7 = ((i6 * i3) + 7) >> 3;
        if (i7 > i5) {
            return 0;
        }
        int i8 = (1 << i3) - 1;
        int i9 = 1 << (i3 - 1);
        int i10 = i4;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        while (i11 < i6) {
            int i14 = i10 + 1;
            i12 = (i12 << 8) | (bArr2[i10] & 255);
            i13 += 8;
            while (i13 >= i3 && i11 < i6) {
                i13 -= i3;
                int i15 = (i12 >>> i13) & i8;
                int i16 = i15 | (-(i15 & i9));
                if (i16 == (-i9)) {
                    return 0;
                }
                bArr[i + i11] = (byte) i16;
                i11++;
            }
            i10 = i14;
        }
        if ((((1 << i13) - 1) & i12) != 0) {
            return 0;
        }
        return i7;
    }

    int trim_i8_encode(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4, int i5) {
        int i6 = 1 << i4;
        int i7 = (1 << (i5 - 1)) - 1;
        int i8 = -i7;
        for (int i9 = 0; i9 < i6; i9++) {
            int i10 = bArr2[i3 + i9];
            if (i10 < i8 || i10 > i7) {
                return 0;
            }
        }
        int i11 = ((i6 * i5) + 7) >> 3;
        if (bArr == null) {
            return i11;
        }
        if (i11 > i2) {
            return 0;
        }
        int i12 = (1 << i5) - 1;
        int i13 = 0;
        int i14 = 0;
        for (int i15 = 0; i15 < i6; i15++) {
            i14 = (i14 << i5) | (bArr2[i3 + i15] & 65535 & i12);
            i13 += i5;
            while (i13 >= 8) {
                i13 -= 8;
                bArr[i] = (byte) (i14 >>> i13);
                i++;
            }
        }
        if (i13 > 0) {
            bArr[i] = (byte) (i14 << (8 - i13));
        }
        return i11;
    }
}
