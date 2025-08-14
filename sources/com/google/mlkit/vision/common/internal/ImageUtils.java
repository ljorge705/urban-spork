package com.google.mlkit.vision.common.internal;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.vision.common.InputImage;
import java.nio.ByteBuffer;

/* compiled from: com.google.mlkit:vision-common@@17.2.0 */
/* loaded from: classes2.dex */
public class ImageUtils {
    private static final GmsLogger zza = new GmsLogger("MLKitImageUtils", "");
    private static ImageUtils zzb = new ImageUtils();

    private ImageUtils() {
    }

    public static ImageUtils getInstance() {
        return zzb;
    }

    public IObjectWrapper getImageDataWrapper(InputImage inputImage) throws MlKitException {
        int format = inputImage.getFormat();
        if (format == -1) {
            return ObjectWrapper.wrap((Bitmap) Preconditions.checkNotNull(inputImage.getBitmapInternal()));
        }
        if (format != 17) {
            if (format == 35) {
                return ObjectWrapper.wrap(inputImage.getMediaImage());
            }
            if (format != 842094169) {
                throw new MlKitException("Unsupported image format: " + inputImage.getFormat(), 3);
            }
        }
        return ObjectWrapper.wrap((ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer()));
    }

    public int getMobileVisionImageFormat(InputImage inputImage) {
        return inputImage.getFormat();
    }

    public int getMobileVisionImageSize(InputImage inputImage) {
        if (inputImage.getFormat() == -1) {
            return ((Bitmap) Preconditions.checkNotNull(inputImage.getBitmapInternal())).getAllocationByteCount();
        }
        if (inputImage.getFormat() == 17 || inputImage.getFormat() == 842094169) {
            return ((ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer())).limit();
        }
        if (inputImage.getFormat() != 35) {
            return 0;
        }
        return (((Image.Plane[]) Preconditions.checkNotNull(inputImage.getPlanes()))[0].getBuffer().limit() * 3) / 2;
    }

    public Matrix getUprightRotationMatrix(int i, int i2, int i3) {
        if (i3 == 0) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postTranslate((-i) / 2.0f, (-i2) / 2.0f);
        matrix.postRotate(i3 * 90);
        int i4 = i3 % 2;
        int i5 = i4 != 0 ? i2 : i;
        if (i4 == 0) {
            i = i2;
        }
        matrix.postTranslate(i5 / 2.0f, i / 2.0f);
        return matrix;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006d A[Catch: FileNotFoundException -> 0x00d0, TryCatch #1 {FileNotFoundException -> 0x00d0, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:33:0x0073, B:34:0x0088, B:45:0x00b9, B:47:0x00c3, B:36:0x008d, B:37:0x0091, B:38:0x0098, B:39:0x009c, B:40:0x00a3, B:41:0x00a7, B:43:0x00ae, B:32:0x006d, B:29:0x0057, B:49:0x00c8, B:50:0x00cf), top: B:57:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008d A[Catch: FileNotFoundException -> 0x00d0, TryCatch #1 {FileNotFoundException -> 0x00d0, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:33:0x0073, B:34:0x0088, B:45:0x00b9, B:47:0x00c3, B:36:0x008d, B:37:0x0091, B:38:0x0098, B:39:0x009c, B:40:0x00a3, B:41:0x00a7, B:43:0x00ae, B:32:0x006d, B:29:0x0057, B:49:0x00c8, B:50:0x00cf), top: B:57:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0091 A[Catch: FileNotFoundException -> 0x00d0, TryCatch #1 {FileNotFoundException -> 0x00d0, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:33:0x0073, B:34:0x0088, B:45:0x00b9, B:47:0x00c3, B:36:0x008d, B:37:0x0091, B:38:0x0098, B:39:0x009c, B:40:0x00a3, B:41:0x00a7, B:43:0x00ae, B:32:0x006d, B:29:0x0057, B:49:0x00c8, B:50:0x00cf), top: B:57:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0098 A[Catch: FileNotFoundException -> 0x00d0, TryCatch #1 {FileNotFoundException -> 0x00d0, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:33:0x0073, B:34:0x0088, B:45:0x00b9, B:47:0x00c3, B:36:0x008d, B:37:0x0091, B:38:0x0098, B:39:0x009c, B:40:0x00a3, B:41:0x00a7, B:43:0x00ae, B:32:0x006d, B:29:0x0057, B:49:0x00c8, B:50:0x00cf), top: B:57:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x009c A[Catch: FileNotFoundException -> 0x00d0, TryCatch #1 {FileNotFoundException -> 0x00d0, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:33:0x0073, B:34:0x0088, B:45:0x00b9, B:47:0x00c3, B:36:0x008d, B:37:0x0091, B:38:0x0098, B:39:0x009c, B:40:0x00a3, B:41:0x00a7, B:43:0x00ae, B:32:0x006d, B:29:0x0057, B:49:0x00c8, B:50:0x00cf), top: B:57:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00a3 A[Catch: FileNotFoundException -> 0x00d0, TryCatch #1 {FileNotFoundException -> 0x00d0, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:33:0x0073, B:34:0x0088, B:45:0x00b9, B:47:0x00c3, B:36:0x008d, B:37:0x0091, B:38:0x0098, B:39:0x009c, B:40:0x00a3, B:41:0x00a7, B:43:0x00ae, B:32:0x006d, B:29:0x0057, B:49:0x00c8, B:50:0x00cf), top: B:57:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a7 A[Catch: FileNotFoundException -> 0x00d0, TryCatch #1 {FileNotFoundException -> 0x00d0, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:33:0x0073, B:34:0x0088, B:45:0x00b9, B:47:0x00c3, B:36:0x008d, B:37:0x0091, B:38:0x0098, B:39:0x009c, B:40:0x00a3, B:41:0x00a7, B:43:0x00ae, B:32:0x006d, B:29:0x0057, B:49:0x00c8, B:50:0x00cf), top: B:57:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ae A[Catch: FileNotFoundException -> 0x00d0, TryCatch #1 {FileNotFoundException -> 0x00d0, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:33:0x0073, B:34:0x0088, B:45:0x00b9, B:47:0x00c3, B:36:0x008d, B:37:0x0091, B:38:0x0098, B:39:0x009c, B:40:0x00a3, B:41:0x00a7, B:43:0x00ae, B:32:0x006d, B:29:0x0057, B:49:0x00c8, B:50:0x00cf), top: B:57:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b9 A[Catch: FileNotFoundException -> 0x00d0, TryCatch #1 {FileNotFoundException -> 0x00d0, blocks: (B:3:0x0002, B:5:0x0008, B:7:0x0016, B:33:0x0073, B:34:0x0088, B:45:0x00b9, B:47:0x00c3, B:36:0x008d, B:37:0x0091, B:38:0x0098, B:39:0x009c, B:40:0x00a3, B:41:0x00a7, B:43:0x00ae, B:32:0x006d, B:29:0x0057, B:49:0x00c8, B:50:0x00cf), top: B:57:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.graphics.Bitmap zza(android.content.ContentResolver r11, android.net.Uri r12) throws java.lang.IllegalAccessException, java.io.IOException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instructions count: 248
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.vision.common.internal.ImageUtils.zza(android.content.ContentResolver, android.net.Uri):android.graphics.Bitmap");
    }
}
