package com.onfido.android.sdk.capture.internal.camera.camerax;

import android.widget.FrameLayout;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\u0010\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0006H&J\"\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/camerax/FrameSampler;", ExifInterface.GPS_DIRECTION_TRUE, "", Constants.KEY_HIDE_CLOSE, "", "observeFrame", "Lio/reactivex/rxjava3/core/Observable;", "onPreviewAvailable", "previewView", "Landroid/widget/FrameLayout;", "frameSamplingPeriod", "Lkotlin/time/Duration;", "onPreviewAvailable-HG0u8IE", "(Landroid/widget/FrameLayout;J)V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface FrameSampler<T> {
    void close();

    Observable<? extends T> observeFrame();

    /* renamed from: onPreviewAvailable-HG0u8IE */
    void mo5541onPreviewAvailableHG0u8IE(FrameLayout previewView, long frameSamplingPeriod);
}
