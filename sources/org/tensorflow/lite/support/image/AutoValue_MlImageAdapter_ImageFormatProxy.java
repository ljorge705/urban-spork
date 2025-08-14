package org.tensorflow.lite.support.image;

import org.tensorflow.lite.support.image.MlImageAdapter;

/* loaded from: classes7.dex */
final class AutoValue_MlImageAdapter_ImageFormatProxy extends MlImageAdapter.ImageFormatProxy {
    private final ColorSpaceType colorSpaceType;
    private final int imageFormat;

    @Override // org.tensorflow.lite.support.image.MlImageAdapter.ImageFormatProxy
    ColorSpaceType getColorSpaceType() {
        return this.colorSpaceType;
    }

    @Override // org.tensorflow.lite.support.image.MlImageAdapter.ImageFormatProxy
    int getImageFormat() {
        return this.imageFormat;
    }

    AutoValue_MlImageAdapter_ImageFormatProxy(ColorSpaceType colorSpaceType, int imageFormat) {
        if (colorSpaceType == null) {
            throw new NullPointerException("Null colorSpaceType");
        }
        this.colorSpaceType = colorSpaceType;
        this.imageFormat = imageFormat;
    }

    public String toString() {
        return "ImageFormatProxy{colorSpaceType=" + this.colorSpaceType + ", imageFormat=" + this.imageFormat + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MlImageAdapter.ImageFormatProxy)) {
            return false;
        }
        MlImageAdapter.ImageFormatProxy imageFormatProxy = (MlImageAdapter.ImageFormatProxy) o;
        return this.colorSpaceType.equals(imageFormatProxy.getColorSpaceType()) && this.imageFormat == imageFormatProxy.getImageFormat();
    }

    public int hashCode() {
        return ((this.colorSpaceType.hashCode() ^ 1000003) * 1000003) ^ this.imageFormat;
    }
}
