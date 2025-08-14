package com.onfido.android.sdk.capture.detector.helper;

import android.graphics.Bitmap;
import com.google.mlkit.vision.common.InputImage;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.swmansion.reanimated.layoutReanimation.Snapshot;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a \u0010\u0002\u001a\u00020\u0003*\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0000\u001a\f\u0010\u0007\u001a\u00020\u0003*\u00020\u0004H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"ROTATION_MULTIPLIER", "", "toInputImageFromJpeg", "Lcom/google/mlkit/vision/common/InputImage;", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "degree", Snapshot.TARGET_WIDTH, "toInputImageFromYuv", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentDetectionExtensionKt {
    public static final int ROTATION_MULTIPLIER = 90;

    public static final InputImage toInputImageFromJpeg(DocumentDetectionFrame documentDetectionFrame, int i, int i2) {
        Intrinsics.checkNotNullParameter(documentDetectionFrame, "<this>");
        Bitmap bitmapDecodeScaledResource$default = ImageUtils.decodeScaledResource$default(new ImageUtils(), documentDetectionFrame.getYuv(), i2, (documentDetectionFrame.getFrameHeight() * i2) / documentDetectionFrame.getFrameWidth(), Bitmap.Config.ARGB_8888, null, 16, null);
        float frameHeight = ((documentDetectionFrame.getFrameWidth() <= documentDetectionFrame.getFrameHeight() || bitmapDecodeScaledResource$default.getHeight() <= bitmapDecodeScaledResource$default.getWidth()) ? documentDetectionFrame.getFrameHeight() : documentDetectionFrame.getFrameWidth()) / bitmapDecodeScaledResource$default.getHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeScaledResource$default, (int) (documentDetectionFrame.getOuterFrame().getLeft() / frameHeight), (int) (documentDetectionFrame.getOuterFrame().getTop() / frameHeight), (int) (documentDetectionFrame.getOuterFrame().getWidth() / frameHeight), (int) (documentDetectionFrame.getOuterFrame().getHeight() / frameHeight));
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
        bitmapDecodeScaledResource$default.recycle();
        InputImage inputImageFromBitmap = InputImage.fromBitmap(bitmapCreateBitmap, i);
        Intrinsics.checkNotNullExpressionValue(inputImageFromBitmap, "fromBitmap(...)");
        return inputImageFromBitmap;
    }

    public static /* synthetic */ InputImage toInputImageFromJpeg$default(DocumentDetectionFrame documentDetectionFrame, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = 720;
        }
        return toInputImageFromJpeg(documentDetectionFrame, i, i2);
    }

    public static final InputImage toInputImageFromYuv(DocumentDetectionFrame documentDetectionFrame) {
        Intrinsics.checkNotNullParameter(documentDetectionFrame, "<this>");
        InputImage inputImageFromByteArray = InputImage.fromByteArray(documentDetectionFrame.getYuv(), documentDetectionFrame.getPictureWidth(), documentDetectionFrame.getPictureHeight(), documentDetectionFrame.getRotation() * 90, 17);
        Intrinsics.checkNotNullExpressionValue(inputImageFromByteArray, "fromByteArray(...)");
        return inputImageFromByteArray;
    }
}
