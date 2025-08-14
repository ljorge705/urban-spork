package androidx.camera.core;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.impl.utils.Exif;
import androidx.camera.core.internal.compat.workaround.ExifRotationAvailability;
import androidx.camera.core.internal.utils.ImageUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes.dex */
final class ImageSaver implements Runnable {
    private static final int COPY_BUFFER_SIZE = 1024;
    private static final int NOT_PENDING = 0;
    private static final int PENDING = 1;
    private static final String TAG = "ImageSaver";
    private static final String TEMP_FILE_PREFIX = "CameraX";
    private static final String TEMP_FILE_SUFFIX = ".tmp";
    private final OnImageSavedCallback mCallback;
    private final ImageProxy mImage;
    private final int mJpegQuality;
    private final int mOrientation;
    private final ImageCapture.OutputFileOptions mOutputFileOptions;
    private final Executor mSequentialIoExecutor;
    private final Executor mUserCallbackExecutor;

    public interface OnImageSavedCallback {
        void onError(SaveError saveError, String str, Throwable th);

        void onImageSaved(ImageCapture.OutputFileResults outputFileResults);
    }

    public enum SaveError {
        FILE_IO_FAILED,
        ENCODE_FAILED,
        CROP_FAILED,
        UNKNOWN
    }

    ImageSaver(ImageProxy imageProxy, ImageCapture.OutputFileOptions outputFileOptions, int i, int i2, Executor executor, Executor executor2, OnImageSavedCallback onImageSavedCallback) {
        this.mImage = imageProxy;
        this.mOutputFileOptions = outputFileOptions;
        this.mOrientation = i;
        this.mJpegQuality = i2;
        this.mCallback = onImageSavedCallback;
        this.mUserCallbackExecutor = executor;
        this.mSequentialIoExecutor = executor2;
    }

    @Override // java.lang.Runnable
    public void run() throws IOException {
        final File fileSaveImageToTempFile = saveImageToTempFile();
        if (fileSaveImageToTempFile != null) {
            this.mSequentialIoExecutor.execute(new Runnable() { // from class: androidx.camera.core.ImageSaver$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m129lambda$run$0$androidxcameracoreImageSaver(fileSaveImageToTempFile);
                }
            });
        }
    }

    private File saveImageToTempFile() throws IOException {
        File fileCreateTempFile;
        SaveError saveError;
        String str;
        Throwable th;
        try {
            if (isSaveToFile()) {
                fileCreateTempFile = new File(this.mOutputFileOptions.getFile().getParent(), "CameraX" + UUID.randomUUID().toString() + getFileExtensionWithDot(this.mOutputFileOptions.getFile()));
            } else {
                fileCreateTempFile = File.createTempFile("CameraX", ".tmp");
            }
            try {
                ImageProxy imageProxy = this.mImage;
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
                    try {
                        fileOutputStream.write(imageToJpegByteArray(this.mImage, this.mJpegQuality));
                        Exif exifCreateFromFile = Exif.createFromFile(fileCreateTempFile);
                        Exif.createFromImageProxy(this.mImage).copyToCroppedImage(exifCreateFromFile);
                        if (!new ExifRotationAvailability().shouldUseExifOrientation(this.mImage)) {
                            exifCreateFromFile.rotate(this.mOrientation);
                        }
                        ImageCapture.Metadata metadata = this.mOutputFileOptions.getMetadata();
                        if (metadata.isReversedHorizontal()) {
                            exifCreateFromFile.flipHorizontally();
                        }
                        if (metadata.isReversedVertical()) {
                            exifCreateFromFile.flipVertically();
                        }
                        if (metadata.getLocation() != null) {
                            exifCreateFromFile.attachLocation(this.mOutputFileOptions.getMetadata().getLocation());
                        }
                        exifCreateFromFile.save();
                        fileOutputStream.close();
                        if (imageProxy != null) {
                            imageProxy.close();
                        }
                        th = null;
                        saveError = null;
                        str = null;
                    } finally {
                    }
                } catch (Throwable th2) {
                    if (imageProxy != null) {
                        try {
                            imageProxy.close();
                        } catch (Throwable th3) {
                            th2.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            } catch (ImageUtil.CodecFailedException e) {
                int i = AnonymousClass1.$SwitchMap$androidx$camera$core$internal$utils$ImageUtil$CodecFailedException$FailureType[e.getFailureType().ordinal()];
                if (i == 1) {
                    saveError = SaveError.ENCODE_FAILED;
                    str = "Failed to encode mImage";
                    th = e;
                } else if (i == 2) {
                    saveError = SaveError.CROP_FAILED;
                    str = "Failed to crop mImage";
                    th = e;
                } else {
                    saveError = SaveError.UNKNOWN;
                    str = "Failed to transcode mImage";
                    th = e;
                }
            } catch (IOException e2) {
                e = e2;
                saveError = SaveError.FILE_IO_FAILED;
                str = "Failed to write temp file";
                th = e;
            } catch (IllegalArgumentException e3) {
                e = e3;
                saveError = SaveError.FILE_IO_FAILED;
                str = "Failed to write temp file";
                th = e;
            } catch (OutOfMemoryError e4) {
                saveError = SaveError.UNKNOWN;
                str = "Processing failed due to low memory.";
                th = e4;
            }
            if (saveError == null) {
                return fileCreateTempFile;
            }
            postError(saveError, str, th);
            fileCreateTempFile.delete();
            return null;
        } catch (IOException e5) {
            postError(SaveError.FILE_IO_FAILED, "Failed to create temp file", e5);
            return null;
        }
    }

    /* renamed from: androidx.camera.core.ImageSaver$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$core$internal$utils$ImageUtil$CodecFailedException$FailureType;

        static {
            int[] iArr = new int[ImageUtil.CodecFailedException.FailureType.values().length];
            $SwitchMap$androidx$camera$core$internal$utils$ImageUtil$CodecFailedException$FailureType = iArr;
            try {
                iArr[ImageUtil.CodecFailedException.FailureType.ENCODE_FAILED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$camera$core$internal$utils$ImageUtil$CodecFailedException$FailureType[ImageUtil.CodecFailedException.FailureType.DECODE_FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$camera$core$internal$utils$ImageUtil$CodecFailedException$FailureType[ImageUtil.CodecFailedException.FailureType.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static String getFileExtensionWithDot(File file) {
        String name = file.getName();
        int iLastIndexOf = name.lastIndexOf(46);
        return iLastIndexOf >= 0 ? name.substring(iLastIndexOf) : "";
    }

    private byte[] imageToJpegByteArray(ImageProxy imageProxy, int i) throws ImageUtil.CodecFailedException {
        boolean zShouldCropImage = ImageUtil.shouldCropImage(imageProxy);
        int format = imageProxy.getFormat();
        if (format == 256) {
            if (!zShouldCropImage) {
                return ImageUtil.jpegImageToJpegByteArray(imageProxy);
            }
            return ImageUtil.jpegImageToJpegByteArray(imageProxy, imageProxy.getCropRect(), i);
        }
        if (format == 35) {
            return ImageUtil.yuvImageToJpegByteArray(imageProxy, zShouldCropImage ? imageProxy.getCropRect() : null, i, 0);
        }
        Logger.w(TAG, "Unrecognized image format: " + format);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a8  */
    /* renamed from: copyTempFileToDestination, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void m129lambda$run$0$androidxcameracoreImageSaver(java.io.File r6) {
        /*
            r5 = this;
            androidx.core.util.Preconditions.checkNotNull(r6)
            r0 = 0
            boolean r1 = r5.isSaveToMediaStore()     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            if (r1 == 0) goto L56
            androidx.camera.core.ImageCapture$OutputFileOptions r1 = r5.mOutputFileOptions     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            android.content.ContentValues r1 = r1.getContentValues()     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            if (r1 == 0) goto L1e
            android.content.ContentValues r1 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            androidx.camera.core.ImageCapture$OutputFileOptions r2 = r5.mOutputFileOptions     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            android.content.ContentValues r2 = r2.getContentValues()     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            goto L23
        L1e:
            android.content.ContentValues r1 = new android.content.ContentValues     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            r1.<init>()     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
        L23:
            r2 = 1
            r5.setContentValuePending(r1, r2)     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            androidx.camera.core.ImageCapture$OutputFileOptions r2 = r5.mOutputFileOptions     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            androidx.camera.core.ImageCapture$OutputFileOptions r3 = r5.mOutputFileOptions     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            android.net.Uri r3 = r3.getSaveCollection()     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            android.net.Uri r1 = r2.insert(r3, r1)     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            if (r1 != 0) goto L3f
            androidx.camera.core.ImageSaver$SaveError r2 = androidx.camera.core.ImageSaver.SaveError.FILE_IO_FAILED     // Catch: java.lang.SecurityException -> L50 java.lang.IllegalArgumentException -> L52 java.io.IOException -> L54 java.lang.Throwable -> L91
            java.lang.String r3 = "Failed to insert URI."
            goto L9f
        L3f:
            boolean r2 = r5.copyTempFileToUri(r6, r1)     // Catch: java.lang.SecurityException -> L50 java.lang.IllegalArgumentException -> L52 java.io.IOException -> L54 java.lang.Throwable -> L91
            if (r2 != 0) goto L4a
            androidx.camera.core.ImageSaver$SaveError r2 = androidx.camera.core.ImageSaver.SaveError.FILE_IO_FAILED     // Catch: java.lang.SecurityException -> L50 java.lang.IllegalArgumentException -> L52 java.io.IOException -> L54 java.lang.Throwable -> L91
            java.lang.String r3 = "Failed to save to URI."
            goto L4c
        L4a:
            r2 = r0
            r3 = r2
        L4c:
            r5.setUriNotPending(r1)     // Catch: java.lang.SecurityException -> L50 java.lang.IllegalArgumentException -> L52 java.io.IOException -> L54 java.lang.Throwable -> L91
            goto L9f
        L50:
            r0 = move-exception
            goto L9b
        L52:
            r0 = move-exception
            goto L9b
        L54:
            r0 = move-exception
            goto L9b
        L56:
            boolean r1 = r5.isSaveToOutputStream()     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            if (r1 == 0) goto L66
            androidx.camera.core.ImageCapture$OutputFileOptions r1 = r5.mOutputFileOptions     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            java.io.OutputStream r1 = r1.getOutputStream()     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            r5.copyTempFileToOutputStream(r6, r1)     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            goto L8d
        L66:
            boolean r1 = r5.isSaveToFile()     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            if (r1 == 0) goto L8d
            androidx.camera.core.ImageCapture$OutputFileOptions r1 = r5.mOutputFileOptions     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            java.io.File r1 = r1.getFile()     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            boolean r2 = r1.exists()     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            if (r2 == 0) goto L7b
            r1.delete()     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
        L7b:
            boolean r2 = r6.renameTo(r1)     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            if (r2 != 0) goto L86
            androidx.camera.core.ImageSaver$SaveError r2 = androidx.camera.core.ImageSaver.SaveError.FILE_IO_FAILED     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            java.lang.String r3 = "Failed to rename file."
            goto L88
        L86:
            r2 = r0
            r3 = r2
        L88:
            android.net.Uri r1 = android.net.Uri.fromFile(r1)     // Catch: java.lang.Throwable -> L91 java.lang.SecurityException -> L93 java.lang.IllegalArgumentException -> L95 java.io.IOException -> L97
            goto L9f
        L8d:
            r1 = r0
            r2 = r1
            r3 = r2
            goto L9f
        L91:
            r0 = move-exception
            goto Lac
        L93:
            r1 = move-exception
            goto L98
        L95:
            r1 = move-exception
            goto L98
        L97:
            r1 = move-exception
        L98:
            r4 = r1
            r1 = r0
            r0 = r4
        L9b:
            androidx.camera.core.ImageSaver$SaveError r2 = androidx.camera.core.ImageSaver.SaveError.FILE_IO_FAILED     // Catch: java.lang.Throwable -> L91
            java.lang.String r3 = "Failed to write destination file."
        L9f:
            r6.delete()
            if (r2 == 0) goto La8
            r5.postError(r2, r3, r0)
            goto Lab
        La8:
            r5.postSuccess(r1)
        Lab:
            return
        Lac:
            r6.delete()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.ImageSaver.m129lambda$run$0$androidxcameracoreImageSaver(java.io.File):void");
    }

    private boolean isSaveToMediaStore() {
        return (this.mOutputFileOptions.getSaveCollection() == null || this.mOutputFileOptions.getContentResolver() == null || this.mOutputFileOptions.getContentValues() == null) ? false : true;
    }

    private boolean isSaveToFile() {
        return this.mOutputFileOptions.getFile() != null;
    }

    private boolean isSaveToOutputStream() {
        return this.mOutputFileOptions.getOutputStream() != null;
    }

    private void setUriNotPending(Uri uri) {
        if (Build.VERSION.SDK_INT >= 29) {
            ContentValues contentValues = new ContentValues();
            setContentValuePending(contentValues, 0);
            this.mOutputFileOptions.getContentResolver().update(uri, contentValues, null, null);
        }
    }

    private void setContentValuePending(ContentValues contentValues, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            contentValues.put("is_pending", Integer.valueOf(i));
        }
    }

    private boolean copyTempFileToUri(File file, Uri uri) throws IOException {
        OutputStream outputStreamOpenOutputStream = this.mOutputFileOptions.getContentResolver().openOutputStream(uri);
        if (outputStreamOpenOutputStream == null) {
            if (outputStreamOpenOutputStream == null) {
                return false;
            }
            outputStreamOpenOutputStream.close();
            return false;
        }
        try {
            copyTempFileToOutputStream(file, outputStreamOpenOutputStream);
            if (outputStreamOpenOutputStream == null) {
                return true;
            }
            outputStreamOpenOutputStream.close();
            return true;
        } catch (Throwable th) {
            if (outputStreamOpenOutputStream != null) {
                try {
                    outputStreamOpenOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void copyTempFileToOutputStream(File file, OutputStream outputStream) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i > 0) {
                    outputStream.write(bArr, 0, i);
                } else {
                    fileInputStream.close();
                    return;
                }
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private void postSuccess(final Uri uri) {
        try {
            this.mUserCallbackExecutor.execute(new Runnable() { // from class: androidx.camera.core.ImageSaver$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m128lambda$postSuccess$1$androidxcameracoreImageSaver(uri);
                }
            });
        } catch (RejectedExecutionException unused) {
            Logger.e(TAG, "Application executor rejected executing OnImageSavedCallback.onImageSaved callback. Skipping.");
        }
    }

    /* renamed from: lambda$postSuccess$1$androidx-camera-core-ImageSaver, reason: not valid java name */
    /* synthetic */ void m128lambda$postSuccess$1$androidxcameracoreImageSaver(Uri uri) {
        this.mCallback.onImageSaved(new ImageCapture.OutputFileResults(uri));
    }

    private void postError(final SaveError saveError, final String str, final Throwable th) {
        try {
            this.mUserCallbackExecutor.execute(new Runnable() { // from class: androidx.camera.core.ImageSaver$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m127lambda$postError$2$androidxcameracoreImageSaver(saveError, str, th);
                }
            });
        } catch (RejectedExecutionException unused) {
            Logger.e(TAG, "Application executor rejected executing OnImageSavedCallback.onError callback. Skipping.");
        }
    }

    /* renamed from: lambda$postError$2$androidx-camera-core-ImageSaver, reason: not valid java name */
    /* synthetic */ void m127lambda$postError$2$androidxcameracoreImageSaver(SaveError saveError, String str, Throwable th) {
        this.mCallback.onError(saveError, str, th);
    }
}
