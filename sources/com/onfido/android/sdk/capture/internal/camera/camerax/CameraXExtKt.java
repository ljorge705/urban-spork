package com.onfido.android.sdk.capture.internal.camera.camerax;

import android.graphics.Bitmap;
import androidx.camera.video.Quality;
import androidx.camera.view.PreviewView;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\tH\u0000\"(\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\f\u0012\n \u0004*\u0004\u0018\u00010\u00030\u00030\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"QUALITY_MAPPER", "", "", "Landroidx/camera/video/Quality;", "kotlin.jvm.PlatformType", "getQUALITY_MAPPER", "()Ljava/util/Map;", "getFrame", "Landroid/graphics/Bitmap;", "Landroidx/camera/view/PreviewView;", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CameraXExtKt {
    private static final Map<Integer, Quality> QUALITY_MAPPER;

    static {
        Pair pair = TuplesKt.to(1, Quality.HIGHEST);
        Quality quality = Quality.LOWEST;
        QUALITY_MAPPER = MapsKt.mapOf(pair, TuplesKt.to(2, quality), TuplesKt.to(3, quality), TuplesKt.to(4, Quality.SD), TuplesKt.to(5, Quality.HD), TuplesKt.to(6, Quality.FHD), TuplesKt.to(7, quality), TuplesKt.to(8, Quality.UHD));
    }

    public static final Bitmap getFrame(PreviewView previewView) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(previewView, "<this>");
        try {
            Field declaredField = previewView.getClass().getDeclaredField("mImplementation");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(previewView);
            Method declaredMethod = obj.getClass().getSuperclass().getDeclaredMethod("getBitmap", null);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(obj, null);
            if (objInvoke instanceof Bitmap) {
                return (Bitmap) objInvoke;
            }
            return null;
        } catch (ReflectiveOperationException e) {
            Timber.INSTANCE.e(e);
            return null;
        }
    }

    public static final Map<Integer, Quality> getQUALITY_MAPPER() {
        return QUALITY_MAPPER;
    }
}
