package com.onfido.android.sdk.capture.internal.camera.camerax;

import android.graphics.Bitmap;
import androidx.camera.view.PreviewView;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.MimeTypes;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\fH&¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalyzer;", ExifInterface.GPS_DIRECTION_TRUE, "", "analyze", "", MimeTypes.BASE_TYPE_IMAGE, "Landroid/graphics/Bitmap;", "previewView", "Landroidx/camera/view/PreviewView;", "scaleType", "Landroidx/camera/view/PreviewView$ScaleType;", "observeFrame", "Lio/reactivex/rxjava3/core/Observable;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ImageAnalyzer<T> {
    void analyze(Bitmap image, PreviewView previewView, PreviewView.ScaleType scaleType);

    Observable<? extends T> observeFrame();
}
