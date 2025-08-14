package androidx.camera.core;

/* loaded from: classes.dex */
public class ImageCaptureException extends Exception {
    private final int mImageCaptureError;

    public int getImageCaptureError() {
        return this.mImageCaptureError;
    }

    public ImageCaptureException(int i, String str, Throwable th) {
        super(str, th);
        this.mImageCaptureError = i;
    }
}
