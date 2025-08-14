package com.onfido.android.sdk.capture.utils;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.provider.MediaStore;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.component.active.video.capture.analytics.AvcAnalyticsEvent;
import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.internal.util.Dimension;
import com.onfido.android.sdk.capture.internal.util.ImageResult;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.ui.camera.Exif;
import com.onfido.android.sdk.capture.ui.camera.Frame;
import com.onfido.javax.inject.Inject;
import io.sentry.protocol.SentryStackFrame;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0010\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0012JE\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u0011H\u0010¢\u0006\u0002\b\u0014J\u001d\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0010¢\u0006\u0002\b\u0016JF\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\u001a\b\u0002\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020!0\u001fH\u0016J(\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0004H\u0012J\u001d\u0010(\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0010¢\u0006\u0002\b,J%\u0010(\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020)2\u0006\u0010-\u001a\u00020+2\u0006\u0010.\u001a\u00020+H\u0010¢\u0006\u0002\b,J\u0010\u0010/\u001a\u00020\u00042\u0006\u00100\u001a\u00020\u0004H\u0016J\u0010\u00101\u001a\u00020 2\u0006\u00100\u001a\u00020\u0004H\u0012J \u00102\u001a\u00020\f2\u0006\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0018H\u0016J2\u00106\u001a\u0004\u0018\u0001072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u00108\u001a\u0002072\u0006\u00109\u001a\u00020:H\u0090@¢\u0006\u0004\b;\u0010<J\u0015\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u000207H\u0010¢\u0006\u0002\b@J\u001d\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020+2\u0006\u0010D\u001a\u00020EH\u0010¢\u0006\u0002\bFJ(\u0010G\u001a\u00020\f2\u0006\u0010H\u001a\u00020\f2\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00042\u0006\u0010I\u001a\u00020\u0004H\u0016J \u0010J\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010K\u001a\u00020\u00042\u0006\u0010L\u001a\u00020\u0004H\u0012J \u0010M\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010K\u001a\u00020\u00042\u0006\u0010L\u001a\u00020\u0004H\u0012J \u0010N\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010K\u001a\u00020\u00042\u0006\u0010L\u001a\u00020\u0004H\u0012J \u0010O\u001a\u00020\u00182\u0006\u0010P\u001a\u00020\u00182\u0006\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020TH\u0016J\u0016\u0010U\u001a\u00020\f*\u00020\u00182\b\b\u0002\u0010\u000f\u001a\u00020\u0004H\u0012¨\u0006V²\u0006\n\u0010W\u001a\u00020\u0018X\u008a\u0084\u0002"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/ImageUtils;", "", "()V", "calculateInSampleSize", "", RRWebOptionsEvent.EVENT_TAG, "Landroid/graphics/BitmapFactory$Options;", "reqWidth", "reqHeight", "cropImage", "Lcom/onfido/android/sdk/capture/internal/util/ImageResult;", "jpegData", "", "frame", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "jpegQuality", "isCameraXEnabled", "", "isFourByThreeEnabled", "isInVirtualEnvironment", "cropImage$onfido_capture_sdk_core_release", "cropImageForScreenShowOnly", "cropImageForScreenShowOnly$onfido_capture_sdk_core_release", "decodeScaledResource", "Landroid/graphics/Bitmap;", "data", "dstWidth", "dstHeight", "inPreferredConfig", "Landroid/graphics/Bitmap$Config;", "updateMatrixBlock", "Lkotlin/Function2;", "Landroid/graphics/Matrix;", "", "encodeYUV420SP", "yuv420sp", "argb", "", "width", "height", "getDocumentDetectionFrame", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage;", "outerLimits", "Landroid/graphics/RectF;", "getDocumentDetectionFrame$onfido_capture_sdk_core_release", "outerRect", "innerRect", "getExifOrientationDegrees", "exifOrientation", "getMatrixForRotation", "getNV21", "inputWidth", "inputHeight", "scaled", "getPoaFileNameAfterCropping", "", "poaImageFileName", "capturedFilesDir", "Ljava/io/File;", "getPoaFileNameAfterCropping$onfido_capture_sdk_core_release", "([BLcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;Ljava/lang/String;Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUploadMediaPickerIntent", "Landroid/content/Intent;", "textToShow", "getUploadMediaPickerIntent$onfido_capture_sdk_core_release", "limitRect", "Lcom/onfido/android/sdk/capture/ui/camera/Frame;", "rect", "cropRect", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage$CropRect;", "limitRect$onfido_capture_sdk_core_release", "rotateNV21", "yuv", ViewProps.ROTATION, "rotateYUV420Degree180", "imageWidth", "imageHeight", "rotateYUV420Degree270", "rotateYUV420Degree90", "roundBitmap", "bitmap", "resources", "Landroid/content/res/Resources;", "cornerRadiusInPx", "", "convertToByteArray", "onfido-capture-sdk-core_release", "emptyBitmap"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class ImageUtils {
    @Inject
    public ImageUtils() {
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int i = options.outHeight;
        int i2 = options.outWidth;
        int i3 = 1;
        if (i > reqHeight || i2 > reqWidth) {
            int i4 = i / 2;
            int i5 = i2 / 2;
            while (i4 / i3 >= reqHeight && i5 / i3 >= reqWidth) {
                i3 *= 2;
            }
        }
        return i3;
    }

    private byte[] convertToByteArray(Bitmap bitmap, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
        return byteArray;
    }

    static /* synthetic */ byte[] convertToByteArray$default(ImageUtils imageUtils, Bitmap bitmap, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: convertToByteArray");
        }
        if ((i2 & 1) != 0) {
            i = 100;
        }
        return imageUtils.convertToByteArray(bitmap, i);
    }

    public static /* synthetic */ ImageResult cropImage$onfido_capture_sdk_core_release$default(ImageUtils imageUtils, byte[] bArr, DocumentDetectionFrame documentDetectionFrame, int i, boolean z, boolean z2, boolean z3, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cropImage");
        }
        if ((i2 & 4) != 0) {
            i = 100;
        }
        return imageUtils.cropImage$onfido_capture_sdk_core_release(bArr, documentDetectionFrame, i, (i2 & 8) != 0 ? false : z, (i2 & 16) != 0 ? false : z2, (i2 & 32) != 0 ? false : z3);
    }

    public static /* synthetic */ Bitmap decodeScaledResource$default(ImageUtils imageUtils, byte[] bArr, int i, int i2, Bitmap.Config config, Function2 function2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decodeScaledResource");
        }
        if ((i3 & 8) != 0) {
            config = Bitmap.Config.RGB_565;
        }
        Bitmap.Config config2 = config;
        if ((i3 & 16) != 0) {
            function2 = new Function2<Matrix, Bitmap, Unit>() { // from class: com.onfido.android.sdk.capture.utils.ImageUtils.decodeScaledResource.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Matrix matrix, Bitmap bitmap) {
                    invoke2(matrix, bitmap);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Matrix matrix, Bitmap bitmap) {
                    Intrinsics.checkNotNullParameter(matrix, "<anonymous parameter 0>");
                    Intrinsics.checkNotNullParameter(bitmap, "<anonymous parameter 1>");
                }
            };
        }
        return imageUtils.decodeScaledResource(bArr, i, i2, config2, function2);
    }

    private static final Bitmap decodeScaledResource$lambda$1(Lazy<Bitmap> lazy) {
        return lazy.getValue();
    }

    private void encodeYUV420SP(byte[] yuv420sp, int[] argb, int width, int height) {
        int i = width * height;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < height; i4++) {
            int i5 = 0;
            while (i5 < width) {
                int i6 = argb[i3];
                int i7 = (16711680 & i6) >> 16;
                int i8 = (65280 & i6) >> 8;
                int i9 = 255;
                int i10 = i6 & 255;
                int i11 = (((((i7 * 66) + (i8 * 129)) + (i10 * 25)) + 128) >> 8) + 16;
                int i12 = (((((i7 * (-38)) - (i8 * 74)) + (i10 * 112)) + 128) >> 8) + 128;
                int i13 = (((((i7 * 112) - (i8 * 94)) - (i10 * 18)) + 128) >> 8) + 128;
                int i14 = i2 + 1;
                if (i11 < 0) {
                    i11 = 0;
                } else if (i11 > 255) {
                    i11 = 255;
                }
                yuv420sp[i2] = (byte) i11;
                if (i4 % 2 == 0 && i3 % 2 == 0) {
                    int i15 = i + 1;
                    if (i13 < 0) {
                        i13 = 0;
                    } else if (i13 > 255) {
                        i13 = 255;
                    }
                    yuv420sp[i] = (byte) i13;
                    i += 2;
                    if (i12 < 0) {
                        i9 = 0;
                    } else if (i12 <= 255) {
                        i9 = i12;
                    }
                    yuv420sp[i15] = (byte) i9;
                }
                i3++;
                i5++;
                i2 = i14;
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private Matrix getMatrixForRotation(int exifOrientation) {
        Matrix matrix = new Matrix();
        switch (exifOrientation) {
            case 2:
                matrix.setScale(-1.0f, 1.0f);
                return matrix;
            case 3:
                matrix.setRotate(180.0f);
                return matrix;
            case 4:
                matrix.setRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                return matrix;
            case 5:
                matrix.setRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                return matrix;
            case 6:
                matrix.setRotate(90.0f);
                return matrix;
            case 7:
                matrix.setRotate(-90.0f);
                matrix.postScale(-1.0f, 1.0f);
                return matrix;
            case 8:
                matrix.setRotate(-90.0f);
                return matrix;
            default:
                return matrix;
        }
    }

    static /* synthetic */ Object getPoaFileNameAfterCropping$suspendImpl(ImageUtils imageUtils, byte[] bArr, DocumentDetectionFrame documentDetectionFrame, String str, File file, Continuation<? super String> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new ImageUtils$getPoaFileNameAfterCropping$2(imageUtils, bArr, documentDetectionFrame, file, str, null), continuation);
    }

    private byte[] rotateYUV420Degree180(byte[] data, int imageWidth, int imageHeight) {
        int i = imageWidth * imageHeight;
        int i2 = (i * 3) / 2;
        byte[] bArr = new byte[i2];
        int i3 = 0;
        for (int i4 = i - 1; i4 >= 0; i4--) {
            bArr[i3] = data[i4];
            i3++;
        }
        for (int i5 = i2 - 1; i5 >= i; i5 -= 2) {
            int i6 = i3 + 1;
            bArr[i3] = data[i5 - 1];
            i3 += 2;
            bArr[i6] = data[i5];
        }
        return bArr;
    }

    private byte[] rotateYUV420Degree270(byte[] data, int imageWidth, int imageHeight) {
        return rotateYUV420Degree180(rotateYUV420Degree90(data, imageWidth, imageHeight), imageWidth, imageHeight);
    }

    private byte[] rotateYUV420Degree90(byte[] data, int imageWidth, int imageHeight) {
        int i = imageWidth * imageHeight;
        int i2 = (i * 3) / 2;
        byte[] bArr = new byte[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < imageWidth; i4++) {
            for (int i5 = imageHeight - 1; -1 < i5; i5--) {
                bArr[i3] = data[(i5 * imageWidth) + i4];
                i3++;
            }
        }
        int i6 = i2 - 1;
        for (int i7 = imageWidth - 1; i7 > 0; i7 -= 2) {
            int i8 = imageHeight / 2;
            for (int i9 = 0; i9 < i8; i9++) {
                int i10 = (i9 * imageWidth) + i;
                bArr[i6] = data[i10 + i7];
                bArr[i6 - 1] = data[i10 + (i7 - 1)];
                i6 -= 2;
            }
        }
        return bArr;
    }

    public ImageResult cropImage$onfido_capture_sdk_core_release(byte[] jpegData, DocumentDetectionFrame frame, int jpegQuality, boolean isCameraXEnabled, boolean isFourByThreeEnabled, boolean isInVirtualEnvironment) throws IllegalAccessException, SecurityException, IOException, IllegalArgumentException {
        Bitmap bitmapCreateBitmap;
        Intrinsics.checkNotNullParameter(jpegData, "jpegData");
        Intrinsics.checkNotNullParameter(frame, "frame");
        Bitmap bitmapDecodeScaledResource$default = decodeScaledResource$default(this, jpegData, frame.getFrameWidth(), frame.getFrameHeight(), Bitmap.Config.ARGB_8888, null, 16, null);
        int height = (int) (frame.getOuterFrame().getHeight() * 1.15d);
        int top = (frame.getOuterFrame().getTop() + (frame.getOuterFrame().getHeight() / 2)) - (height / 2);
        boolean z = frame.getFrameHeight() < frame.getFrameWidth();
        int frameHeight = z ? frame.getFrameHeight() : frame.getFrameWidth();
        try {
            if (isFourByThreeEnabled) {
                bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeScaledResource$default, 0, ((z ? frame.getFrameWidth() : frame.getFrameHeight()) - height) / 2, frameHeight, height);
            } else {
                bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeScaledResource$default, 0, top, frameHeight, height);
            }
            bitmapDecodeScaledResource$default = bitmapCreateBitmap;
        } catch (IllegalArgumentException e) {
            Timber.Companion companion = Timber.INSTANCE;
            companion.tag("CropImage");
            companion.e(e, "Cropping failed: \nIsFourByThreeEnabled = " + isFourByThreeEnabled + "\nFrame Width = " + frame.getFrameWidth() + "\nFrame Height = " + frame.getFrameHeight() + "\nOuter Frame Top = " + frame.getOuterFrame().getTop() + "\nOuter Frame Left = " + frame.getOuterFrame().getLeft() + "\nOuter Frame Height = " + frame.getOuterFrame().getHeight() + "\nOuter Frame Width = " + frame.getOuterFrame().getWidth() + "\nDecoded Bitmap Height = " + bitmapDecodeScaledResource$default.getHeight() + "\nDecoded Bitmap Width = " + bitmapDecodeScaledResource$default.getWidth(), new Object[0]);
        }
        Intrinsics.checkNotNull(bitmapDecodeScaledResource$default);
        int width = bitmapDecodeScaledResource$default.getWidth();
        int height2 = bitmapDecodeScaledResource$default.getHeight();
        byte[] bArrConvertToByteArray = convertToByteArray(bitmapDecodeScaledResource$default, jpegQuality);
        bitmapDecodeScaledResource$default.recycle();
        FileUtils fileUtils = FileUtils.INSTANCE;
        File fileFileFromByteArray$default = FileUtils.fileFromByteArray$default(fileUtils, jpegData, null, 2, null);
        ExifInterface exifInterface = new ExifInterface(fileFileFromByteArray$default.getAbsolutePath());
        File fileFileFromByteArray$default2 = FileUtils.fileFromByteArray$default(fileUtils, bArrConvertToByteArray, null, 2, null);
        ExifInterface exifInterface2 = new ExifInterface(fileFileFromByteArray$default2.getAbsolutePath());
        try {
            Exif.copy(exifInterface, exifInterface2, androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION);
            exifInterface2.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_MAKER_NOTE, (isCameraXEnabled ? AvcAnalyticsEvent.MotionCamera.CAMERA_X : "Camera1 API") + ": " + (isInVirtualEnvironment ? "virtual" : SentryStackFrame.JsonKeys.NATIVE));
            exifInterface2.saveAttributes();
        } catch (IOException e2) {
            Timber.INSTANCE.e(e2);
        }
        try {
            return new ImageResult(FileUtils.INSTANCE.fileToByteArray(fileFileFromByteArray$default2), new Dimension(width, height2));
        } finally {
            fileFileFromByteArray$default2.delete();
            fileFileFromByteArray$default.delete();
        }
    }

    public ImageResult cropImageForScreenShowOnly$onfido_capture_sdk_core_release(byte[] jpegData, DocumentDetectionFrame frame) {
        Intrinsics.checkNotNullParameter(jpegData, "jpegData");
        Intrinsics.checkNotNullParameter(frame, "frame");
        Bitmap bitmapDecodeScaledResource$default = decodeScaledResource$default(this, jpegData, frame.getFrameWidth(), frame.getFrameHeight(), Bitmap.Config.ARGB_8888, null, 16, null);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeScaledResource$default, frame.getInnerFrame().getLeft(), frame.getInnerFrame().getTop(), frame.getInnerFrame().getWidth(), frame.getInnerFrame().getHeight());
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
        bitmapDecodeScaledResource$default.recycle();
        byte[] bArrConvertToByteArray$default = convertToByteArray$default(this, bitmapCreateBitmap, 0, 1, null);
        bitmapCreateBitmap.recycle();
        return new ImageResult(bArrConvertToByteArray$default, new Dimension(bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight()));
    }

    public Bitmap decodeScaledResource(byte[] data, final int dstWidth, final int dstHeight, Bitmap.Config inPreferredConfig, Function2<? super Matrix, ? super Bitmap, Unit> updateMatrixBlock) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(inPreferredConfig, "inPreferredConfig");
        Intrinsics.checkNotNullParameter(updateMatrixBlock, "updateMatrixBlock");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        int iExifOrientationIdentifier = Exif.exifOrientationIdentifier(data);
        int exifOrientationDegrees = getExifOrientationDegrees(iExifOrientationIdentifier);
        options.inSampleSize = calculateInSampleSize(options, (exifOrientationDegrees == 90 || exifOrientationDegrees == 270) ? dstHeight : dstWidth, (exifOrientationDegrees == 90 || exifOrientationDegrees == 270) ? dstWidth : dstHeight);
        options.inJustDecodeBounds = false;
        Lazy lazy = LazyKt.lazy(new Function0<Bitmap>() { // from class: com.onfido.android.sdk.capture.utils.ImageUtils$decodeScaledResource$emptyBitmap$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Bitmap invoke() {
                Timber.INSTANCE.e("Can't decode image: Height: " + dstHeight + ", Width: " + dstWidth, new Object[0]);
                return Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888);
            }
        });
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        if (bitmapDecodeByteArray == null) {
            bitmapDecodeByteArray = decodeScaledResource$lambda$1(lazy);
            Intrinsics.checkNotNullExpressionValue(bitmapDecodeByteArray, "decodeScaledResource$lambda$1(...)");
        }
        Matrix matrixForRotation = getMatrixForRotation(iExifOrientationIdentifier);
        updateMatrixBlock.invoke(matrixForRotation, bitmapDecodeByteArray);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeByteArray, 0, 0, bitmapDecodeByteArray.getWidth(), bitmapDecodeByteArray.getHeight(), matrixForRotation, true);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
        if (bitmapDecodeByteArray != bitmapCreateBitmap) {
            bitmapDecodeByteArray.recycle();
        }
        return bitmapCreateBitmap;
    }

    public DocumentDetectionFrame getDocumentDetectionFrame$onfido_capture_sdk_core_release(OnfidoImage frame, RectF outerLimits) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        Intrinsics.checkNotNullParameter(outerLimits, "outerLimits");
        return new DocumentDetectionFrame(frame.getData(), frame.getWidth(), frame.getHeight(), frame.getWidth(), frame.getHeight(), limitRect$onfido_capture_sdk_core_release(outerLimits, frame.getCropRect()), null, getExifOrientationDegrees(Exif.exifOrientationIdentifier(frame.getData())), frame.getCropRect(), frame.getBitmap(), 64, null);
    }

    public int getExifOrientationDegrees(int exifOrientation) {
        switch (exifOrientation) {
            case 3:
            case 4:
                return 180;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    public byte[] getNV21(int inputWidth, int inputHeight, Bitmap scaled) {
        Intrinsics.checkNotNullParameter(scaled, "scaled");
        int i = inputWidth * inputHeight;
        int[] iArr = new int[i];
        scaled.getPixels(iArr, 0, inputWidth, 0, 0, inputWidth, inputHeight);
        byte[] bArr = new byte[(i * 3) / 2];
        encodeYUV420SP(bArr, iArr, inputWidth, inputHeight);
        return bArr;
    }

    public Object getPoaFileNameAfterCropping$onfido_capture_sdk_core_release(byte[] bArr, DocumentDetectionFrame documentDetectionFrame, String str, File file, Continuation<? super String> continuation) {
        return getPoaFileNameAfterCropping$suspendImpl(this, bArr, documentDetectionFrame, str, file, continuation);
    }

    public Intent getUploadMediaPickerIntent$onfido_capture_sdk_core_release(String textToShow) {
        Intrinsics.checkNotNullParameter(textToShow, "textToShow");
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        Intent intent2 = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent2.setType("*/*");
        Intent intentCreateChooser = Intent.createChooser(intent, textToShow);
        intentCreateChooser.putExtra("android.intent.extra.INITIAL_INTENTS", new Intent[]{intent2});
        Intrinsics.checkNotNullExpressionValue(intentCreateChooser, "also(...)");
        return intentCreateChooser;
    }

    public Frame limitRect$onfido_capture_sdk_core_release(RectF rect, OnfidoImage.CropRect cropRect) {
        Intrinsics.checkNotNullParameter(rect, "rect");
        Intrinsics.checkNotNullParameter(cropRect, "cropRect");
        float zoomFactor = cropRect.getZoomFactor();
        return new Frame((int) (rect.width() / zoomFactor), (int) (rect.height() / zoomFactor), (int) (cropRect.getHorizontalOffset() + (rect.left / zoomFactor)), (int) (cropRect.getVerticalOffset() + (rect.top / zoomFactor)));
    }

    public byte[] rotateNV21(byte[] yuv, int width, int height, int rotation) {
        Intrinsics.checkNotNullParameter(yuv, "yuv");
        if (rotation == 0) {
            return yuv;
        }
        if (rotation == 90) {
            return rotateYUV420Degree90(yuv, width, height);
        }
        if (rotation == 180) {
            return rotateYUV420Degree180(yuv, width, height);
        }
        if (rotation == 270) {
            return rotateYUV420Degree270(yuv, width, height);
        }
        throw new IllegalStateException("Rotation not 0, 90, 180 or 270. Rotation is " + rotation);
    }

    public Bitmap roundBitmap(Bitmap bitmap, Resources resources, float cornerRadiusInPx) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(resources, "resources");
        RoundedBitmapDrawable roundedBitmapDrawableCreate = RoundedBitmapDrawableFactory.create(resources, bitmap);
        roundedBitmapDrawableCreate.setCornerRadius(cornerRadiusInPx);
        Intrinsics.checkNotNullExpressionValue(roundedBitmapDrawableCreate, "apply(...)");
        Bitmap bitmap2 = ViewExtensionsKt.toBitmap(roundedBitmapDrawableCreate);
        if (!Intrinsics.areEqual(bitmap, bitmap2)) {
            bitmap.recycle();
        }
        return bitmap2;
    }

    public DocumentDetectionFrame getDocumentDetectionFrame$onfido_capture_sdk_core_release(OnfidoImage frame, RectF outerRect, RectF innerRect) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        Intrinsics.checkNotNullParameter(outerRect, "outerRect");
        Intrinsics.checkNotNullParameter(innerRect, "innerRect");
        return new DocumentDetectionFrame(frame.getData(), frame.getWidth(), frame.getHeight(), frame.getWidth(), frame.getHeight(), limitRect$onfido_capture_sdk_core_release(outerRect, frame.getCropRect()), limitRect$onfido_capture_sdk_core_release(innerRect, frame.getCropRect()), frame.getRotation(), frame.getCropRect(), frame.getBitmap());
    }
}
