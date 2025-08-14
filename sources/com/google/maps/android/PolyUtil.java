package com.google.maps.android;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* loaded from: classes2.dex */
public class PolyUtil {
    public static final double DEFAULT_TOLERANCE = 0.1d;

    private PolyUtil() {
    }

    private static double tanLatGC(double d, double d2, double d3, double d4) {
        return ((Math.tan(d) * Math.sin(d3 - d4)) + (Math.tan(d2) * Math.sin(d4))) / Math.sin(d3);
    }

    private static double mercatorLatRhumb(double d, double d2, double d3, double d4) {
        return ((MathUtil.mercator(d) * (d3 - d4)) + (MathUtil.mercator(d2) * d4)) / d3;
    }

    private static boolean intersects(double d, double d2, double d3, double d4, double d5, boolean z) {
        if ((d5 >= 0.0d && d5 >= d3) || ((d5 < 0.0d && d5 < d3) || d4 <= -1.5707963267948966d || d <= -1.5707963267948966d || d2 <= -1.5707963267948966d || d >= 1.5707963267948966d || d2 >= 1.5707963267948966d || d3 <= -3.141592653589793d)) {
            return false;
        }
        double d6 = (((d3 - d5) * d) + (d2 * d5)) / d3;
        if (d >= 0.0d && d2 >= 0.0d && d4 < d6) {
            return false;
        }
        if ((d <= 0.0d && d2 <= 0.0d && d4 >= d6) || d4 >= 1.5707963267948966d) {
            return true;
        }
        if (z) {
            if (Math.tan(d4) < tanLatGC(d, d2, d3, d5)) {
                return false;
            }
        } else if (MathUtil.mercator(d4) < mercatorLatRhumb(d, d2, d3, d5)) {
            return false;
        }
        return true;
    }

    public static boolean containsLocation(LatLng latLng, List<LatLng> list, boolean z) {
        return containsLocation(latLng.latitude, latLng.longitude, list, z);
    }

    public static boolean containsLocation(double d, double d2, List<LatLng> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return false;
        }
        double radians = Math.toRadians(d);
        double radians2 = Math.toRadians(d2);
        LatLng latLng = list.get(size - 1);
        double radians3 = Math.toRadians(latLng.latitude);
        double radians4 = Math.toRadians(latLng.longitude);
        int i = 0;
        double d3 = radians3;
        for (LatLng latLng2 : list) {
            double dWrap = MathUtil.wrap(radians2 - radians4, -3.141592653589793d, 3.141592653589793d);
            if (radians == d3 && dWrap == 0.0d) {
                return true;
            }
            double radians5 = Math.toRadians(latLng2.latitude);
            double radians6 = Math.toRadians(latLng2.longitude);
            if (intersects(d3, radians5, MathUtil.wrap(radians6 - radians4, -3.141592653589793d, 3.141592653589793d), radians, dWrap, z)) {
                i++;
            }
            d3 = radians5;
            radians4 = radians6;
        }
        return (i & 1) != 0;
    }

    public static boolean isLocationOnEdge(LatLng latLng, List<LatLng> list, boolean z, double d) {
        return isLocationOnEdgeOrPath(latLng, list, true, z, d);
    }

    public static boolean isLocationOnEdge(LatLng latLng, List<LatLng> list, boolean z) {
        return isLocationOnEdge(latLng, list, z, 0.1d);
    }

    public static boolean isLocationOnPath(LatLng latLng, List<LatLng> list, boolean z, double d) {
        return isLocationOnEdgeOrPath(latLng, list, false, z, d);
    }

    public static boolean isLocationOnPath(LatLng latLng, List<LatLng> list, boolean z) {
        return isLocationOnPath(latLng, list, z, 0.1d);
    }

    private static boolean isLocationOnEdgeOrPath(LatLng latLng, List<LatLng> list, boolean z, boolean z2, double d) {
        return locationIndexOnEdgeOrPath(latLng, list, z, z2, d) >= 0;
    }

    public static int locationIndexOnPath(LatLng latLng, List<LatLng> list, boolean z, double d) {
        return locationIndexOnEdgeOrPath(latLng, list, false, z, d);
    }

    public static int locationIndexOnPath(LatLng latLng, List<LatLng> list, boolean z) {
        return locationIndexOnPath(latLng, list, z, 0.1d);
    }

    public static int locationIndexOnEdgeOrPath(LatLng latLng, List<LatLng> list, boolean z, boolean z2, double d) {
        List<LatLng> list2;
        int i;
        char c;
        int size = list.size();
        if (size == 0) {
            return -1;
        }
        double d2 = d / 6371009.0d;
        double dHav = MathUtil.hav(d2);
        double radians = Math.toRadians(latLng.latitude);
        double radians2 = Math.toRadians(latLng.longitude);
        if (z) {
            i = size - 1;
            list2 = list;
        } else {
            list2 = list;
            i = 0;
        }
        LatLng latLng2 = list2.get(i);
        double radians3 = Math.toRadians(latLng2.latitude);
        double radians4 = Math.toRadians(latLng2.longitude);
        if (z2) {
            int i2 = 0;
            double d3 = radians3;
            double d4 = radians4;
            for (LatLng latLng3 : list) {
                double radians5 = Math.toRadians(latLng3.latitude);
                double radians6 = Math.toRadians(latLng3.longitude);
                if (isOnSegmentGC(d3, d4, radians5, radians6, radians, radians2, dHav)) {
                    return Math.max(0, i2 - 1);
                }
                i2++;
                d3 = radians5;
                d4 = radians6;
            }
            return -1;
        }
        double d5 = radians - d2;
        double d6 = radians + d2;
        double dMercator = MathUtil.mercator(radians3);
        double dMercator2 = MathUtil.mercator(radians);
        double[] dArr = new double[3];
        int i3 = 0;
        for (LatLng latLng4 : list) {
            double d7 = dMercator2;
            double radians7 = Math.toRadians(latLng4.latitude);
            double dMercator3 = MathUtil.mercator(radians7);
            double radians8 = Math.toRadians(latLng4.longitude);
            if (Math.max(radians3, radians7) < d5 || Math.min(radians3, radians7) > d6) {
                c = 3;
            } else {
                double dWrap = MathUtil.wrap(radians8 - radians4, -3.141592653589793d, 3.141592653589793d);
                double dWrap2 = MathUtil.wrap(radians2 - radians4, -3.141592653589793d, 3.141592653589793d);
                dArr[0] = dWrap2;
                dArr[1] = dWrap2 + 6.283185307179586d;
                dArr[2] = dWrap2 - 6.283185307179586d;
                c = 3;
                for (int i4 = 0; i4 < 3; i4++) {
                    double d8 = dArr[i4];
                    double d9 = dMercator3 - dMercator;
                    double d10 = (dWrap * dWrap) + (d9 * d9);
                    double dClamp = d10 > 0.0d ? MathUtil.clamp(((d8 * dWrap) + ((d7 - dMercator) * d9)) / d10, 0.0d, 1.0d) : 0.0d;
                    if (MathUtil.havDistance(radians, MathUtil.inverseMercator(dMercator + (dClamp * d9)), d8 - (dClamp * dWrap)) < dHav) {
                        return Math.max(0, i3 - 1);
                    }
                }
            }
            i3++;
            radians3 = radians7;
            dMercator2 = d7;
            dMercator = dMercator3;
            radians4 = radians8;
        }
        return -1;
    }

    private static double sinDeltaBearing(double d, double d2, double d3, double d4, double d5, double d6) {
        double dSin = Math.sin(d);
        double dCos = Math.cos(d3);
        double dCos2 = Math.cos(d5);
        double d7 = d6 - d2;
        double d8 = d4 - d2;
        double dSin2 = Math.sin(d7) * dCos2;
        double dSin3 = Math.sin(d8) * dCos;
        double d9 = dSin * 2.0d;
        double dSin4 = Math.sin(d5 - d) + (dCos2 * d9 * MathUtil.hav(d7));
        double dSin5 = Math.sin(d3 - d) + (d9 * dCos * MathUtil.hav(d8));
        double d10 = ((dSin2 * dSin2) + (dSin4 * dSin4)) * ((dSin3 * dSin3) + (dSin5 * dSin5));
        if (d10 <= 0.0d) {
            return 1.0d;
        }
        return ((dSin2 * dSin5) - (dSin4 * dSin3)) / Math.sqrt(d10);
    }

    private static boolean isOnSegmentGC(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
        double dHavDistance = MathUtil.havDistance(d, d5, d2 - d6);
        if (dHavDistance <= d7) {
            return true;
        }
        double dHavDistance2 = MathUtil.havDistance(d3, d5, d4 - d6);
        if (dHavDistance2 <= d7) {
            return true;
        }
        double dHavFromSin = MathUtil.havFromSin(MathUtil.sinFromHav(dHavDistance) * sinDeltaBearing(d, d2, d3, d4, d5, d6));
        if (dHavFromSin > d7) {
            return false;
        }
        double dHavDistance3 = MathUtil.havDistance(d, d3, d2 - d4);
        double d8 = ((1.0d - (dHavDistance3 * 2.0d)) * dHavFromSin) + dHavDistance3;
        if (dHavDistance > d8 || dHavDistance2 > d8) {
            return false;
        }
        if (dHavDistance3 < 0.74d) {
            return true;
        }
        double d9 = 1.0d - (2.0d * dHavFromSin);
        return MathUtil.sinSumFromHav((dHavDistance - dHavFromSin) / d9, (dHavDistance2 - dHavFromSin) / d9) > 0.0d;
    }

    public static List<LatLng> simplify(List<LatLng> list, double d) {
        LatLng latLng;
        int size = list.size();
        if (size < 1) {
            throw new IllegalArgumentException("Polyline must have at least 1 point");
        }
        double d2 = 0.0d;
        if (d <= 0.0d) {
            throw new IllegalArgumentException("Tolerance must be greater than zero");
        }
        boolean zIsClosedPolygon = isClosedPolygon(list);
        if (zIsClosedPolygon) {
            latLng = list.get(list.size() - 1);
            list.remove(list.size() - 1);
            list.add(new LatLng(latLng.latitude + 1.0E-11d, latLng.longitude + 1.0E-11d));
        } else {
            latLng = null;
        }
        Stack stack = new Stack();
        double[] dArr = new double[size];
        int i = 0;
        dArr[0] = 1.0d;
        int i2 = size - 1;
        dArr[i2] = 1.0d;
        if (size > 2) {
            stack.push(new int[]{0, i2});
            int i3 = 0;
            while (stack.size() > 0) {
                int[] iArr = (int[]) stack.pop();
                double d3 = d2;
                for (int i4 = iArr[0] + 1; i4 < iArr[1]; i4++) {
                    double dDistanceToLine = distanceToLine(list.get(i4), list.get(iArr[0]), list.get(iArr[1]));
                    if (dDistanceToLine > d3) {
                        d3 = dDistanceToLine;
                        i3 = i4;
                    }
                }
                if (d3 > d) {
                    dArr[i3] = d3;
                    stack.push(new int[]{iArr[0], i3});
                    stack.push(new int[]{i3, iArr[1]});
                }
                d2 = 0.0d;
            }
        }
        if (zIsClosedPolygon) {
            list.remove(list.size() - 1);
            list.add(latLng);
        }
        ArrayList arrayList = new ArrayList();
        for (LatLng latLng2 : list) {
            if (dArr[i] != 0.0d) {
                arrayList.add(latLng2);
            }
            i++;
        }
        return arrayList;
    }

    public static boolean isClosedPolygon(List<LatLng> list) {
        return list.get(0).equals(list.get(list.size() - 1));
    }

    public static double distanceToLine(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        if (latLng2.equals(latLng3)) {
            return SphericalUtil.computeDistanceBetween(latLng3, latLng);
        }
        double radians = Math.toRadians(latLng.latitude);
        double radians2 = Math.toRadians(latLng.longitude);
        double radians3 = Math.toRadians(latLng2.latitude);
        double radians4 = Math.toRadians(latLng2.longitude);
        double radians5 = Math.toRadians(latLng3.latitude);
        double radians6 = Math.toRadians(latLng3.longitude);
        double dCos = Math.cos(radians3);
        double d = radians5 - radians3;
        double d2 = (radians6 - radians4) * dCos;
        double d3 = (((radians - radians3) * d) + (((radians2 - radians4) * dCos) * d2)) / ((d * d) + (d2 * d2));
        if (d3 <= 0.0d) {
            return SphericalUtil.computeDistanceBetween(latLng, latLng2);
        }
        if (d3 >= 1.0d) {
            return SphericalUtil.computeDistanceBetween(latLng, latLng3);
        }
        return SphericalUtil.computeDistanceBetween(latLng, new LatLng(latLng2.latitude + ((latLng3.latitude - latLng2.latitude) * d3), latLng2.longitude + (d3 * (latLng3.longitude - latLng2.longitude))));
    }

    public static List<LatLng> decode(String str) {
        int i;
        int i2;
        int length = str.length();
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < length) {
            int i6 = 1;
            int i7 = 0;
            int i8 = 1;
            while (true) {
                i = i3 + 1;
                int iCharAt = str.charAt(i3) - '@';
                i8 += iCharAt << i7;
                i7 += 5;
                if (iCharAt < 31) {
                    break;
                }
                i3 = i;
            }
            int i9 = ((i8 & 1) != 0 ? ~(i8 >> 1) : i8 >> 1) + i4;
            int i10 = 0;
            while (true) {
                i2 = i + 1;
                int iCharAt2 = str.charAt(i) - '@';
                i6 += iCharAt2 << i10;
                i10 += 5;
                if (iCharAt2 < 31) {
                    break;
                }
                i = i2;
            }
            i5 += (i6 & 1) != 0 ? ~(i6 >> 1) : i6 >> 1;
            arrayList.add(new LatLng(i9 * 1.0E-5d, i5 * 1.0E-5d));
            i4 = i9;
            i3 = i2;
        }
        return arrayList;
    }

    public static String encode(List<LatLng> list) {
        StringBuffer stringBuffer = new StringBuffer();
        long j = 0;
        long j2 = 0;
        for (LatLng latLng : list) {
            long jRound = Math.round(latLng.latitude * 100000.0d);
            long jRound2 = Math.round(latLng.longitude * 100000.0d);
            encode(jRound - j, stringBuffer);
            encode(jRound2 - j2, stringBuffer);
            j = jRound;
            j2 = jRound2;
        }
        return stringBuffer.toString();
    }

    private static void encode(long j, StringBuffer stringBuffer) {
        long j2 = j << 1;
        if (j < 0) {
            j2 = ~j2;
        }
        while (j2 >= 32) {
            stringBuffer.append(Character.toChars((int) ((32 | (31 & j2)) + 63)));
            j2 >>= 5;
        }
        stringBuffer.append(Character.toChars((int) (j2 + 63)));
    }
}
