package androidx.camera.core.processing;

import androidx.camera.core.ImageProcessor;
import androidx.camera.core.ImageProxy;

/* loaded from: classes.dex */
public class ImageProcessorRequest implements ImageProcessor.Request {
    private final ImageProxy mImageProxy;
    private final int mOutputFormat;

    @Override // androidx.camera.core.ImageProcessor.Request
    public ImageProxy getInputImage() {
        return this.mImageProxy;
    }

    @Override // androidx.camera.core.ImageProcessor.Request
    public int getOutputFormat() {
        return this.mOutputFormat;
    }

    public ImageProcessorRequest(ImageProxy imageProxy, int i) {
        this.mImageProxy = imageProxy;
        this.mOutputFormat = i;
    }
}
