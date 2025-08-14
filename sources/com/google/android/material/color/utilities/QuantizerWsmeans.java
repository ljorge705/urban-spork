package com.google.android.material.color.utilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public final class QuantizerWsmeans {
    private static final int MAX_ITERATIONS = 10;
    private static final double MIN_MOVEMENT_DISTANCE = 3.0d;

    private QuantizerWsmeans() {
    }

    private static final class Distance implements Comparable<Distance> {
        int index = -1;
        double distance = -1.0d;

        Distance() {
        }

        @Override // java.lang.Comparable
        public int compareTo(Distance distance) {
            return Double.valueOf(this.distance).compareTo(Double.valueOf(distance.distance));
        }
    }

    public static Map<Integer, Integer> quantize(int[] iArr, int[] iArr2, int i) {
        int[] iArr3;
        int i2;
        int i3;
        HashMap map = new HashMap();
        double[][] dArr = new double[iArr.length][];
        int[] iArr4 = new int[iArr.length];
        PointProviderLab pointProviderLab = new PointProviderLab();
        int i4 = 0;
        for (int i5 : iArr) {
            Integer num = (Integer) map.get(Integer.valueOf(i5));
            if (num == null) {
                dArr[i4] = pointProviderLab.fromInt(i5);
                iArr4[i4] = i5;
                i4++;
                map.put(Integer.valueOf(i5), 1);
            } else {
                map.put(Integer.valueOf(i5), Integer.valueOf(num.intValue() + 1));
            }
        }
        int[] iArr5 = new int[i4];
        for (int i6 = 0; i6 < i4; i6++) {
            iArr5[i6] = ((Integer) map.get(Integer.valueOf(iArr4[i6]))).intValue();
        }
        int iMin = Math.min(i, i4);
        if (iArr2.length != 0) {
            iMin = Math.min(iMin, iArr2.length);
        }
        double[][] dArr2 = new double[iMin][];
        int i7 = 0;
        for (int i8 = 0; i8 < iArr2.length; i8++) {
            dArr2[i8] = pointProviderLab.fromInt(iArr2[i8]);
            i7++;
        }
        int i9 = iMin - i7;
        if (i9 > 0) {
            for (int i10 = 0; i10 < i9; i10++) {
            }
        }
        int[] iArr6 = new int[i4];
        for (int i11 = 0; i11 < i4; i11++) {
            iArr6[i11] = (int) Math.floor(Math.random() * iMin);
        }
        int[][] iArr7 = new int[iMin][];
        for (int i12 = 0; i12 < iMin; i12++) {
            iArr7[i12] = new int[iMin];
        }
        Distance[][] distanceArr = new Distance[iMin][];
        for (int i13 = 0; i13 < iMin; i13++) {
            distanceArr[i13] = new Distance[iMin];
            for (int i14 = 0; i14 < iMin; i14++) {
                distanceArr[i13][i14] = new Distance();
            }
        }
        int[] iArr8 = new int[iMin];
        int i15 = 0;
        while (true) {
            if (i15 >= 10) {
                iArr3 = iArr8;
                i2 = 0;
                break;
            }
            int i16 = 0;
            while (i16 < iMin) {
                int i17 = i16 + 1;
                int i18 = i17;
                while (i18 < iMin) {
                    double dDistance = pointProviderLab.distance(dArr2[i16], dArr2[i18]);
                    distanceArr[i18][i16].distance = dDistance;
                    distanceArr[i18][i16].index = i16;
                    distanceArr[i16][i18].distance = dDistance;
                    distanceArr[i16][i18].index = i18;
                    i18++;
                    iArr8 = iArr8;
                }
                int[] iArr9 = iArr8;
                Arrays.sort(distanceArr[i16]);
                for (int i19 = 0; i19 < iMin; i19++) {
                    iArr7[i16][i19] = distanceArr[i16][i19].index;
                }
                iArr8 = iArr9;
                i16 = i17;
            }
            int[] iArr10 = iArr8;
            int i20 = 0;
            int i21 = 0;
            while (i20 < i4) {
                double[] dArr3 = dArr[i20];
                int i22 = iArr6[i20];
                double dDistance2 = pointProviderLab.distance(dArr3, dArr2[i22]);
                int[][] iArr11 = iArr7;
                int[] iArr12 = iArr5;
                double d = dDistance2;
                int i23 = -1;
                int i24 = 0;
                while (i24 < iMin) {
                    Distance[][] distanceArr2 = distanceArr;
                    int i25 = i4;
                    if (distanceArr[i22][i24].distance < 4.0d * dDistance2) {
                        double dDistance3 = pointProviderLab.distance(dArr3, dArr2[i24]);
                        if (dDistance3 < d) {
                            i23 = i24;
                            d = dDistance3;
                        }
                    }
                    i24++;
                    i4 = i25;
                    distanceArr = distanceArr2;
                }
                int i26 = i4;
                Distance[][] distanceArr3 = distanceArr;
                if (i23 != -1 && Math.abs(Math.sqrt(d) - Math.sqrt(dDistance2)) > MIN_MOVEMENT_DISTANCE) {
                    i21++;
                    iArr6[i20] = i23;
                }
                i20++;
                iArr7 = iArr11;
                iArr5 = iArr12;
                i4 = i26;
                distanceArr = distanceArr3;
            }
            int[] iArr13 = iArr5;
            int[][] iArr14 = iArr7;
            int i27 = i4;
            Distance[][] distanceArr4 = distanceArr;
            if (i21 == 0 && i15 != 0) {
                i2 = 0;
                iArr3 = iArr10;
                break;
            }
            double[] dArr4 = new double[iMin];
            double[] dArr5 = new double[iMin];
            double[] dArr6 = new double[iMin];
            char c = 0;
            Arrays.fill(iArr10, 0);
            int i28 = 0;
            while (true) {
                i3 = i27;
                if (i28 >= i3) {
                    break;
                }
                int i29 = iArr6[i28];
                double[] dArr7 = dArr[i28];
                int i30 = iArr13[i28];
                iArr10[i29] = iArr10[i29] + i30;
                double d2 = i30;
                dArr4[i29] = dArr4[i29] + (dArr7[c] * d2);
                dArr5[i29] = dArr5[i29] + (dArr7[1] * d2);
                dArr6[i29] = dArr6[i29] + (dArr7[2] * d2);
                i28++;
                i15 = i15;
                i27 = i3;
                c = 0;
            }
            int i31 = i15;
            for (int i32 = 0; i32 < iMin; i32++) {
                int i33 = iArr10[i32];
                if (i33 == 0) {
                    dArr2[i32] = new double[]{0.0d, 0.0d, 0.0d};
                } else {
                    double d3 = i33;
                    double d4 = dArr4[i32] / d3;
                    double d5 = dArr5[i32] / d3;
                    double d6 = dArr6[i32] / d3;
                    double[] dArr8 = dArr2[i32];
                    dArr8[0] = d4;
                    dArr8[1] = d5;
                    dArr8[2] = d6;
                }
            }
            iArr7 = iArr14;
            i15 = i31 + 1;
            iArr8 = iArr10;
            i4 = i3;
            iArr5 = iArr13;
            distanceArr = distanceArr4;
        }
        HashMap map2 = new HashMap();
        for (int i34 = i2; i34 < iMin; i34++) {
            int i35 = iArr3[i34];
            if (i35 != 0) {
                int i36 = pointProviderLab.toInt(dArr2[i34]);
                if (!map2.containsKey(Integer.valueOf(i36))) {
                    map2.put(Integer.valueOf(i36), Integer.valueOf(i35));
                }
            }
        }
        return map2;
    }
}
