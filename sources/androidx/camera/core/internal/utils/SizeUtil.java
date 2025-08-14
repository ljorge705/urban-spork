package androidx.camera.core.internal.utils;

import android.util.Size;
import androidx.camera.core.impl.utils.CompareSizesByArea;
import com.drew.metadata.exif.makernotes.OlympusMakernoteDirectory;
import com.drew.metadata.photoshop.PhotoshopDirectory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit;
import com.onfido.android.sdk.capture.internal.camera.camerax.DefaultFrameSampler;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class SizeUtil {
    public static final Size RESOLUTION_ZERO = new Size(0, 0);
    public static final Size RESOLUTION_QVGA = new Size(320, FaceDetectorAvcMLKit.FACE_DETECTION_FRAME_WIDTH);
    public static final Size RESOLUTION_VGA = new Size(OlympusMakernoteDirectory.TAG_PREVIEW_IMAGE, DefaultFrameSampler.DESIRED_FRAME_WIDTH);
    public static final Size RESOLUTION_480P = new Size(720, DefaultFrameSampler.DESIRED_FRAME_WIDTH);
    public static final Size RESOLUTION_720P = new Size(1280, 720);
    public static final Size RESOLUTION_1080P = new Size(1920, PhotoshopDirectory.TAG_COUNT_INFORMATION);
    public static final Size RESOLUTION_1440P = new Size(1920, 1440);

    private SizeUtil() {
    }

    public static int getArea(Size size) {
        return size.getWidth() * size.getHeight();
    }

    public static boolean isSmallerByArea(Size size, Size size2) {
        return getArea(size) < getArea(size2);
    }

    public static boolean isLongerInAnyEdge(Size size, Size size2) {
        return size.getWidth() > size2.getWidth() || size.getHeight() > size2.getHeight();
    }

    public static Size getMaxSize(List<Size> list) {
        if (list.isEmpty()) {
            return null;
        }
        return (Size) Collections.max(list, new CompareSizesByArea());
    }
}
