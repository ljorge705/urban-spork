package org.tensorflow.lite.support.image;

import org.tensorflow.lite.support.image.ImageProperties;

/* loaded from: classes7.dex */
final class AutoValue_ImageProperties extends ImageProperties {
    private final ColorSpaceType colorSpaceType;
    private final int height;
    private final int width;

    @Override // org.tensorflow.lite.support.image.ImageProperties
    public ColorSpaceType getColorSpaceType() {
        return this.colorSpaceType;
    }

    @Override // org.tensorflow.lite.support.image.ImageProperties
    public int getHeight() {
        return this.height;
    }

    @Override // org.tensorflow.lite.support.image.ImageProperties
    public int getWidth() {
        return this.width;
    }

    private AutoValue_ImageProperties(int height, int width, ColorSpaceType colorSpaceType) {
        this.height = height;
        this.width = width;
        this.colorSpaceType = colorSpaceType;
    }

    public String toString() {
        return "ImageProperties{height=" + this.height + ", width=" + this.width + ", colorSpaceType=" + this.colorSpaceType + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ImageProperties)) {
            return false;
        }
        ImageProperties imageProperties = (ImageProperties) o;
        return this.height == imageProperties.getHeight() && this.width == imageProperties.getWidth() && this.colorSpaceType.equals(imageProperties.getColorSpaceType());
    }

    public int hashCode() {
        return ((((this.height ^ 1000003) * 1000003) ^ this.width) * 1000003) ^ this.colorSpaceType.hashCode();
    }

    static final class Builder extends ImageProperties.Builder {
        private ColorSpaceType colorSpaceType;
        private Integer height;
        private Integer width;

        Builder() {
        }

        @Override // org.tensorflow.lite.support.image.ImageProperties.Builder
        public ImageProperties.Builder setHeight(int height) {
            this.height = Integer.valueOf(height);
            return this;
        }

        @Override // org.tensorflow.lite.support.image.ImageProperties.Builder
        public ImageProperties.Builder setWidth(int width) {
            this.width = Integer.valueOf(width);
            return this;
        }

        @Override // org.tensorflow.lite.support.image.ImageProperties.Builder
        public ImageProperties.Builder setColorSpaceType(ColorSpaceType colorSpaceType) {
            if (colorSpaceType == null) {
                throw new NullPointerException("Null colorSpaceType");
            }
            this.colorSpaceType = colorSpaceType;
            return this;
        }

        @Override // org.tensorflow.lite.support.image.ImageProperties.Builder
        ImageProperties autoBuild() {
            String str = this.height == null ? " height" : "";
            if (this.width == null) {
                str = str + " width";
            }
            if (this.colorSpaceType == null) {
                str = str + " colorSpaceType";
            }
            if (!str.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + str);
            }
            return new AutoValue_ImageProperties(this.height.intValue(), this.width.intValue(), this.colorSpaceType);
        }
    }
}
