package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.preview;

import android.graphics.Bitmap;
import android.widget.FrameLayout;
import androidx.camera.view.PreviewView;
import androidx.camera.viewfinder.CameraViewfinder;
import com.onfido.android.sdk.capture.internal.camera.camerax.CameraXExtKt;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.javax.inject.Inject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0007H\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH\u0002J\u000e\u0010\n\u001a\u0004\u0018\u00010\u0004*\u00020\tH\u0002¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/preview/PreviewBitmapHelper;", "", "()V", "getBitmap", "Landroid/graphics/Bitmap;", "previewView", "Landroid/widget/FrameLayout;", "Landroidx/camera/view/PreviewView;", "viewfinder", "Landroidx/camera/viewfinder/CameraViewfinder;", "getBitmapDirectly", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PreviewBitmapHelper {
    @Inject
    public PreviewBitmapHelper() {
    }

    private final Bitmap getBitmapDirectly(CameraViewfinder cameraViewfinder) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            Field declaredField = cameraViewfinder.getClass().getDeclaredField("mImplementation");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(cameraViewfinder);
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

    public final Bitmap getBitmap(FrameLayout previewView) {
        Intrinsics.checkNotNullParameter(previewView, "previewView");
        if (previewView instanceof PreviewView) {
            return getBitmap((PreviewView) previewView);
        }
        if (previewView instanceof CameraViewfinder) {
            return getBitmap((CameraViewfinder) previewView);
        }
        return null;
    }

    private final Bitmap getBitmap(PreviewView previewView) {
        return CameraXExtKt.getFrame(previewView);
    }

    private final Bitmap getBitmap(CameraViewfinder viewfinder) {
        return getBitmapDirectly(viewfinder);
    }
}
