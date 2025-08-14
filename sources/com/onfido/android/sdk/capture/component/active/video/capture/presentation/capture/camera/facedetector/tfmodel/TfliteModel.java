package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.tfmodel;

import android.graphics.Bitmap;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0004H&J\u0015\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\bH&¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/tfmodel/TfliteModel;", ExifInterface.GPS_DIRECTION_TRUE, "", Constants.KEY_HIDE_CLOSE, "", "initialize", "run", "bitmap", "Landroid/graphics/Bitmap;", "(Landroid/graphics/Bitmap;)Ljava/lang/Object;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface TfliteModel<T> {
    void close();

    void initialize();

    T run(Bitmap bitmap);
}
