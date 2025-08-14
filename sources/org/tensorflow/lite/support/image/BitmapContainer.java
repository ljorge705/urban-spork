package org.tensorflow.lite.support.image;

import android.graphics.Bitmap;
import android.media.Image;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/* loaded from: classes7.dex */
final class BitmapContainer implements ImageContainer {
    private final Bitmap bitmap;

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public Bitmap getBitmap() {
        return this.bitmap;
    }

    static BitmapContainer create(Bitmap bitmap) {
        return new BitmapContainer(bitmap);
    }

    private BitmapContainer(Bitmap bitmap) {
        SupportPreconditions.checkNotNull(bitmap, "Cannot load null bitmap.");
        SupportPreconditions.checkArgument(bitmap.getConfig().equals(Bitmap.Config.ARGB_8888), "Only supports loading ARGB_8888 bitmaps.");
        this.bitmap = bitmap;
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public BitmapContainer clone() {
        Bitmap bitmap = this.bitmap;
        return create(bitmap.copy(bitmap.getConfig(), this.bitmap.isMutable()));
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public TensorBuffer getTensorBuffer(DataType dataType) {
        TensorBuffer tensorBufferCreateDynamic = TensorBuffer.createDynamic(dataType);
        ImageConversions.convertBitmapToTensorBuffer(this.bitmap, tensorBufferCreateDynamic);
        return tensorBufferCreateDynamic;
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public Image getMediaImage() {
        throw new UnsupportedOperationException("Converting from Bitmap to android.media.Image is unsupported.");
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public int getWidth() {
        return this.bitmap.getWidth();
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public int getHeight() {
        return this.bitmap.getHeight();
    }

    @Override // org.tensorflow.lite.support.image.ImageContainer
    public ColorSpaceType getColorSpaceType() {
        return ColorSpaceType.fromBitmapConfig(this.bitmap.getConfig());
    }
}
