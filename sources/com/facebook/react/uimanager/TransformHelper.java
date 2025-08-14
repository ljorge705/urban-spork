package com.facebook.react.uimanager;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;

/* loaded from: classes5.dex */
public class TransformHelper {
    private static ThreadLocal<double[]> sHelperMatrix = new ThreadLocal<double[]>() { // from class: com.facebook.react.uimanager.TransformHelper.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public double[] initialValue() {
            return new double[16];
        }
    };

    private static double convertToRadians(ReadableMap readableMap, String str) {
        double d;
        boolean z = true;
        if (readableMap.getType(str) == ReadableType.String) {
            String string = readableMap.getString(str);
            if (string.endsWith("rad")) {
                string = string.substring(0, string.length() - 3);
            } else if (string.endsWith("deg")) {
                string = string.substring(0, string.length() - 3);
                z = false;
            }
            d = Float.parseFloat(string);
        } else {
            d = readableMap.getDouble(str);
        }
        return z ? d : MatrixMathHelper.degreesToRadians(d);
    }

    public static void processTransform(ReadableArray readableArray, double[] dArr) {
        processTransform(readableArray, dArr, 0.0f, 0.0f, null);
    }

    public static void processTransform(ReadableArray readableArray, double[] dArr, float f, float f2, ReadableArray readableArray2) {
        int i;
        double[] dArr2 = sHelperMatrix.get();
        MatrixMathHelper.resetIdentityMatrix(dArr);
        float[] translateForTransformOrigin = getTranslateForTransformOrigin(f, f2, readableArray2);
        if (translateForTransformOrigin != null) {
            MatrixMathHelper.resetIdentityMatrix(dArr2);
            MatrixMathHelper.applyTranslate3D(dArr2, translateForTransformOrigin[0], translateForTransformOrigin[1], translateForTransformOrigin[2]);
            MatrixMathHelper.multiplyInto(dArr, dArr, dArr2);
        }
        if (readableArray.size() == 16 && readableArray.getType(0) == ReadableType.Number) {
            MatrixMathHelper.resetIdentityMatrix(dArr2);
            for (int i2 = 0; i2 < readableArray.size(); i2++) {
                dArr2[i2] = readableArray.getDouble(i2);
            }
            MatrixMathHelper.multiplyInto(dArr, dArr, dArr2);
        } else {
            int size = readableArray.size();
            int i3 = 0;
            while (i3 < size) {
                ReadableMap map = readableArray.getMap(i3);
                String strNextKey = map.keySetIterator().nextKey();
                MatrixMathHelper.resetIdentityMatrix(dArr2);
                if ("matrix".equals(strNextKey)) {
                    ReadableArray array = map.getArray(strNextKey);
                    for (int i4 = 0; i4 < 16; i4++) {
                        dArr2[i4] = array.getDouble(i4);
                    }
                } else if ("perspective".equals(strNextKey)) {
                    MatrixMathHelper.applyPerspective(dArr2, map.getDouble(strNextKey));
                } else if ("rotateX".equals(strNextKey)) {
                    MatrixMathHelper.applyRotateX(dArr2, convertToRadians(map, strNextKey));
                } else if ("rotateY".equals(strNextKey)) {
                    MatrixMathHelper.applyRotateY(dArr2, convertToRadians(map, strNextKey));
                } else {
                    if ("rotate".equals(strNextKey) || "rotateZ".equals(strNextKey)) {
                        i = i3;
                        MatrixMathHelper.applyRotateZ(dArr2, convertToRadians(map, strNextKey));
                    } else if ("scale".equals(strNextKey)) {
                        double d = map.getDouble(strNextKey);
                        MatrixMathHelper.applyScaleX(dArr2, d);
                        MatrixMathHelper.applyScaleY(dArr2, d);
                    } else if (ViewProps.SCALE_X.equals(strNextKey)) {
                        MatrixMathHelper.applyScaleX(dArr2, map.getDouble(strNextKey));
                    } else if (ViewProps.SCALE_Y.equals(strNextKey)) {
                        MatrixMathHelper.applyScaleY(dArr2, map.getDouble(strNextKey));
                    } else if ("translate".equals(strNextKey)) {
                        ReadableArray array2 = map.getArray(strNextKey);
                        i = i3;
                        MatrixMathHelper.applyTranslate3D(dArr2, array2.getDouble(0), array2.getDouble(1), array2.size() > 2 ? array2.getDouble(2) : 0.0d);
                    } else {
                        i = i3;
                        if (ViewProps.TRANSLATE_X.equals(strNextKey)) {
                            MatrixMathHelper.applyTranslate2D(dArr2, map.getDouble(strNextKey), 0.0d);
                        } else if (ViewProps.TRANSLATE_Y.equals(strNextKey)) {
                            MatrixMathHelper.applyTranslate2D(dArr2, 0.0d, map.getDouble(strNextKey));
                        } else if ("skewX".equals(strNextKey)) {
                            MatrixMathHelper.applySkewX(dArr2, convertToRadians(map, strNextKey));
                        } else if ("skewY".equals(strNextKey)) {
                            MatrixMathHelper.applySkewY(dArr2, convertToRadians(map, strNextKey));
                        } else {
                            FLog.w("ReactNative", "Unsupported transform type: " + strNextKey);
                        }
                    }
                    MatrixMathHelper.multiplyInto(dArr, dArr, dArr2);
                    i3 = i + 1;
                }
                i = i3;
                MatrixMathHelper.multiplyInto(dArr, dArr, dArr2);
                i3 = i + 1;
            }
        }
        if (translateForTransformOrigin != null) {
            MatrixMathHelper.resetIdentityMatrix(dArr2);
            MatrixMathHelper.applyTranslate3D(dArr2, -translateForTransformOrigin[0], -translateForTransformOrigin[1], -translateForTransformOrigin[2]);
            MatrixMathHelper.multiplyInto(dArr, dArr, dArr2);
        }
    }

    /* renamed from: com.facebook.react.uimanager.TransformHelper$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        static {
            int[] iArr = new int[ReadableType.values().length];
            $SwitchMap$com$facebook$react$bridge$ReadableType = iArr;
            try {
                iArr[ReadableType.Number.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static float[] getTranslateForTransformOrigin(float f, float f2, ReadableArray readableArray) throws NumberFormatException {
        if (readableArray == null) {
            return null;
        }
        if (f2 == 0.0f && f == 0.0f) {
            return null;
        }
        float f3 = f / 2.0f;
        float f4 = f2 / 2.0f;
        float[] fArr = new float[3];
        fArr[0] = f3;
        fArr[1] = f4;
        fArr[2] = 0.0f;
        int i = 0;
        while (i < readableArray.size() && i < 3) {
            int i2 = AnonymousClass2.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(i).ordinal()];
            if (i2 == 1) {
                fArr[i] = (float) readableArray.getDouble(i);
            } else if (i2 == 2) {
                String string = readableArray.getString(i);
                if (string.endsWith("%")) {
                    fArr[i] = ((i == 0 ? f : f2) * Float.parseFloat(string.substring(0, string.length() - 1))) / 100.0f;
                }
            }
            i++;
        }
        return new float[]{(-f3) + fArr[0], (-f4) + fArr[1], fArr[2]};
    }
}
