package com.airbnb.lottie.parser;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.collection.SparseArrayCompat;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import com.clevertap.android.sdk.Constants;
import java.io.IOException;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
class KeyframeParser {
    private static final float MAX_CP_VALUE = 100.0f;
    private static SparseArrayCompat<WeakReference<Interpolator>> pathInterpolatorCache;
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    static JsonReader.Options NAMES = JsonReader.Options.of("t", "s", "e", "o", "i", "h", "to", Constants.INAPP_ID_IN_PAYLOAD);
    static JsonReader.Options INTERPOLATOR_NAMES = JsonReader.Options.of("x", "y");

    KeyframeParser() {
    }

    private static SparseArrayCompat<WeakReference<Interpolator>> pathInterpolatorCache() {
        if (pathInterpolatorCache == null) {
            pathInterpolatorCache = new SparseArrayCompat<>();
        }
        return pathInterpolatorCache;
    }

    private static WeakReference<Interpolator> getInterpolator(int i) {
        WeakReference<Interpolator> weakReference;
        synchronized (KeyframeParser.class) {
            weakReference = pathInterpolatorCache().get(i);
        }
        return weakReference;
    }

    private static void putInterpolator(int i, WeakReference<Interpolator> weakReference) {
        synchronized (KeyframeParser.class) {
            pathInterpolatorCache.put(i, weakReference);
        }
    }

    static <T> Keyframe<T> parse(JsonReader jsonReader, LottieComposition lottieComposition, float f, ValueParser<T> valueParser, boolean z, boolean z2) throws IOException {
        if (z && z2) {
            return parseMultiDimensionalKeyframe(lottieComposition, jsonReader, f, valueParser);
        }
        if (z) {
            return parseKeyframe(lottieComposition, jsonReader, f, valueParser);
        }
        return parseStaticValue(jsonReader, f, valueParser);
    }

    private static <T> Keyframe<T> parseKeyframe(LottieComposition lottieComposition, JsonReader jsonReader, float f, ValueParser<T> valueParser) throws IOException {
        Interpolator interpolatorInterpolatorFor;
        T t;
        jsonReader.beginObject();
        PointF pointFJsonToPoint = null;
        T t2 = null;
        T t3 = null;
        PointF pointFJsonToPoint2 = null;
        PointF pointFJsonToPoint3 = null;
        float fNextDouble = 0.0f;
        boolean z = false;
        PointF pointFJsonToPoint4 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.selectName(NAMES)) {
                case 0:
                    fNextDouble = (float) jsonReader.nextDouble();
                    break;
                case 1:
                    t3 = valueParser.parse(jsonReader, f);
                    break;
                case 2:
                    t2 = valueParser.parse(jsonReader, f);
                    break;
                case 3:
                    pointFJsonToPoint = JsonUtils.jsonToPoint(jsonReader, 1.0f);
                    break;
                case 4:
                    pointFJsonToPoint4 = JsonUtils.jsonToPoint(jsonReader, 1.0f);
                    break;
                case 5:
                    if (jsonReader.nextInt() != 1) {
                        z = false;
                        break;
                    } else {
                        z = true;
                        break;
                    }
                case 6:
                    pointFJsonToPoint2 = JsonUtils.jsonToPoint(jsonReader, f);
                    break;
                case 7:
                    pointFJsonToPoint3 = JsonUtils.jsonToPoint(jsonReader, f);
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        if (z) {
            interpolatorInterpolatorFor = LINEAR_INTERPOLATOR;
            t = t3;
        } else {
            interpolatorInterpolatorFor = (pointFJsonToPoint == null || pointFJsonToPoint4 == null) ? LINEAR_INTERPOLATOR : interpolatorFor(pointFJsonToPoint, pointFJsonToPoint4);
            t = t2;
        }
        Keyframe<T> keyframe = new Keyframe<>(lottieComposition, t3, t, interpolatorInterpolatorFor, fNextDouble, null);
        keyframe.pathCp1 = pointFJsonToPoint2;
        keyframe.pathCp2 = pointFJsonToPoint3;
        return keyframe;
    }

    /* JADX WARN: Removed duplicated region for block: B:94:0x01eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static <T> com.airbnb.lottie.value.Keyframe<T> parseMultiDimensionalKeyframe(com.airbnb.lottie.LottieComposition r20, com.airbnb.lottie.parser.moshi.JsonReader r21, float r22, com.airbnb.lottie.parser.ValueParser<T> r23) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 532
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.KeyframeParser.parseMultiDimensionalKeyframe(com.airbnb.lottie.LottieComposition, com.airbnb.lottie.parser.moshi.JsonReader, float, com.airbnb.lottie.parser.ValueParser):com.airbnb.lottie.value.Keyframe");
    }

    private static Interpolator interpolatorFor(PointF pointF, PointF pointF2) {
        Interpolator linearInterpolator;
        pointF.x = MiscUtils.clamp(pointF.x, -1.0f, 1.0f);
        pointF.y = MiscUtils.clamp(pointF.y, -100.0f, 100.0f);
        pointF2.x = MiscUtils.clamp(pointF2.x, -1.0f, 1.0f);
        pointF2.y = MiscUtils.clamp(pointF2.y, -100.0f, 100.0f);
        int iHashFor = Utils.hashFor(pointF.x, pointF.y, pointF2.x, pointF2.y);
        WeakReference<Interpolator> interpolator = getInterpolator(iHashFor);
        Interpolator interpolator2 = interpolator != null ? interpolator.get() : null;
        if (interpolator == null || interpolator2 == null) {
            try {
                linearInterpolator = PathInterpolatorCompat.create(pointF.x, pointF.y, pointF2.x, pointF2.y);
            } catch (IllegalArgumentException e) {
                if ("The Path cannot loop back on itself.".equals(e.getMessage())) {
                    linearInterpolator = PathInterpolatorCompat.create(Math.min(pointF.x, 1.0f), pointF.y, Math.max(pointF2.x, 0.0f), pointF2.y);
                } else {
                    linearInterpolator = new LinearInterpolator();
                }
            }
            interpolator2 = linearInterpolator;
            try {
                putInterpolator(iHashFor, new WeakReference(interpolator2));
            } catch (ArrayIndexOutOfBoundsException unused) {
            }
        }
        return interpolator2;
    }

    private static <T> Keyframe<T> parseStaticValue(JsonReader jsonReader, float f, ValueParser<T> valueParser) throws IOException {
        return new Keyframe<>(valueParser.parse(jsonReader, f));
    }
}
