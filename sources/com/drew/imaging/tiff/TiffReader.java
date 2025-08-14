package com.drew.imaging.tiff;

import com.drew.lang.RandomAccessReader;
import com.drew.lang.Rational;
import java.io.IOException;
import java.util.HashSet;

/* loaded from: classes5.dex */
public class TiffReader {
    private static int calculateTagOffset(int i, int i2) {
        return i + 2 + (i2 * 12);
    }

    public void processTiff(RandomAccessReader randomAccessReader, TiffHandler tiffHandler, int i) throws Throwable {
        short int16 = randomAccessReader.getInt16(i);
        if (int16 == 19789) {
            randomAccessReader.setMotorolaByteOrder(true);
        } else if (int16 == 18761) {
            randomAccessReader.setMotorolaByteOrder(false);
        } else {
            throw new TiffProcessingException("Unclear distinction between Motorola/Intel byte ordering: " + ((int) int16));
        }
        tiffHandler.setTiffMarker(randomAccessReader.getUInt16(i + 2));
        int int32 = randomAccessReader.getInt32(i + 4) + i;
        if (int32 >= randomAccessReader.getLength() - 1) {
            tiffHandler.warn("First IFD offset is beyond the end of the TIFF data segment -- trying default offset");
            int32 = i + 8;
        }
        processIfd(tiffHandler, randomAccessReader, new HashSet(), int32, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x019d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0170 A[Catch: all -> 0x01f0, TryCatch #0 {all -> 0x01f0, blocks: (B:83:0x019d, B:58:0x0110, B:63:0x011e, B:66:0x0129, B:69:0x0133, B:71:0x0138, B:73:0x013e, B:74:0x014c, B:78:0x0155, B:80:0x0170, B:81:0x017e, B:82:0x0198, B:84:0x01a7, B:86:0x01b4, B:98:0x01da, B:100:0x01e0), top: B:118:0x0110 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void processIfd(com.drew.imaging.tiff.TiffHandler r26, com.drew.lang.RandomAccessReader r27, java.util.Set<java.lang.Integer> r28, int r29, int r30) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 527
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.imaging.tiff.TiffReader.processIfd(com.drew.imaging.tiff.TiffHandler, com.drew.lang.RandomAccessReader, java.util.Set, int, int):void");
    }

    private static void processTag(TiffHandler tiffHandler, int i, int i2, int i3, int i4, RandomAccessReader randomAccessReader) throws IOException {
        int i5 = 0;
        switch (i4) {
            case 1:
                if (i3 == 1) {
                    tiffHandler.setInt8u(i, randomAccessReader.getUInt8(i2));
                    break;
                } else {
                    short[] sArr = new short[i3];
                    while (i5 < i3) {
                        sArr[i5] = randomAccessReader.getUInt8(i2 + i5);
                        i5++;
                    }
                    tiffHandler.setInt8uArray(i, sArr);
                    break;
                }
            case 2:
                tiffHandler.setString(i, randomAccessReader.getNullTerminatedStringValue(i2, i3, null));
                break;
            case 3:
                if (i3 == 1) {
                    tiffHandler.setInt16u(i, randomAccessReader.getUInt16(i2));
                    break;
                } else {
                    int[] iArr = new int[i3];
                    while (i5 < i3) {
                        iArr[i5] = randomAccessReader.getUInt16((i5 * 2) + i2);
                        i5++;
                    }
                    tiffHandler.setInt16uArray(i, iArr);
                    break;
                }
            case 4:
                if (i3 == 1) {
                    tiffHandler.setInt32u(i, randomAccessReader.getUInt32(i2));
                    break;
                } else {
                    long[] jArr = new long[i3];
                    while (i5 < i3) {
                        jArr[i5] = randomAccessReader.getUInt32((i5 * 4) + i2);
                        i5++;
                    }
                    tiffHandler.setInt32uArray(i, jArr);
                    break;
                }
            case 5:
                if (i3 != 1) {
                    if (i3 > 1) {
                        Rational[] rationalArr = new Rational[i3];
                        while (i5 < i3) {
                            int i6 = i5 * 8;
                            rationalArr[i5] = new Rational(randomAccessReader.getUInt32(i2 + i6), randomAccessReader.getUInt32(i2 + 4 + i6));
                            i5++;
                        }
                        tiffHandler.setRationalArray(i, rationalArr);
                        break;
                    }
                } else {
                    tiffHandler.setRational(i, new Rational(randomAccessReader.getUInt32(i2), randomAccessReader.getUInt32(i2 + 4)));
                    break;
                }
                break;
            case 6:
                if (i3 == 1) {
                    tiffHandler.setInt8s(i, randomAccessReader.getInt8(i2));
                    break;
                } else {
                    byte[] bArr = new byte[i3];
                    while (i5 < i3) {
                        bArr[i5] = randomAccessReader.getInt8(i2 + i5);
                        i5++;
                    }
                    tiffHandler.setInt8sArray(i, bArr);
                    break;
                }
            case 7:
                tiffHandler.setByteArray(i, randomAccessReader.getBytes(i2, i3));
                break;
            case 8:
                if (i3 == 1) {
                    tiffHandler.setInt16s(i, randomAccessReader.getInt16(i2));
                    break;
                } else {
                    short[] sArr2 = new short[i3];
                    while (i5 < i3) {
                        sArr2[i5] = randomAccessReader.getInt16((i5 * 2) + i2);
                        i5++;
                    }
                    tiffHandler.setInt16sArray(i, sArr2);
                    break;
                }
            case 9:
                if (i3 == 1) {
                    tiffHandler.setInt32s(i, randomAccessReader.getInt32(i2));
                    break;
                } else {
                    int[] iArr2 = new int[i3];
                    while (i5 < i3) {
                        iArr2[i5] = randomAccessReader.getInt32((i5 * 4) + i2);
                        i5++;
                    }
                    tiffHandler.setInt32sArray(i, iArr2);
                    break;
                }
            case 10:
                if (i3 != 1) {
                    if (i3 > 1) {
                        Rational[] rationalArr2 = new Rational[i3];
                        while (i5 < i3) {
                            int i7 = i5 * 8;
                            rationalArr2[i5] = new Rational(randomAccessReader.getInt32(i2 + i7), randomAccessReader.getInt32(i2 + 4 + i7));
                            i5++;
                        }
                        tiffHandler.setRationalArray(i, rationalArr2);
                        break;
                    }
                } else {
                    tiffHandler.setRational(i, new Rational(randomAccessReader.getInt32(i2), randomAccessReader.getInt32(i2 + 4)));
                    break;
                }
                break;
            case 11:
                if (i3 == 1) {
                    tiffHandler.setFloat(i, randomAccessReader.getFloat32(i2));
                    break;
                } else {
                    float[] fArr = new float[i3];
                    while (i5 < i3) {
                        fArr[i5] = randomAccessReader.getFloat32((i5 * 4) + i2);
                        i5++;
                    }
                    tiffHandler.setFloatArray(i, fArr);
                    break;
                }
            case 12:
                if (i3 == 1) {
                    tiffHandler.setDouble(i, randomAccessReader.getDouble64(i2));
                    break;
                } else {
                    double[] dArr = new double[i3];
                    while (i5 < i3) {
                        dArr[i5] = randomAccessReader.getDouble64((i5 * 4) + i2);
                        i5++;
                    }
                    tiffHandler.setDoubleArray(i, dArr);
                    break;
                }
            default:
                tiffHandler.error(String.format("Invalid TIFF tag format code %d for tag 0x%04X", Integer.valueOf(i4), Integer.valueOf(i)));
                break;
        }
    }
}
