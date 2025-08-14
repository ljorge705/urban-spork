package org.tensorflow.lite.support.image;

import org.tensorflow.lite.support.common.internal.SupportPreconditions;
import org.tensorflow.lite.support.image.AutoValue_ImageProperties;

/* loaded from: classes7.dex */
public abstract class ImageProperties {
    private static final int DEFAULT_HEIGHT = -1;
    private static final int DEFAULT_WIDTH = -1;

    public abstract ColorSpaceType getColorSpaceType();

    public abstract int getHeight();

    public abstract int getWidth();

    public static Builder builder() {
        return new AutoValue_ImageProperties.Builder().setHeight(-1).setWidth(-1);
    }

    public static abstract class Builder {
        abstract ImageProperties autoBuild();

        public abstract Builder setColorSpaceType(ColorSpaceType colorSpaceType);

        public abstract Builder setHeight(int height);

        public abstract Builder setWidth(int width);

        public ImageProperties build() {
            ImageProperties imagePropertiesAutoBuild = autoBuild();
            SupportPreconditions.checkState(imagePropertiesAutoBuild.getHeight() >= 0, "Negative image height is not allowed.");
            SupportPreconditions.checkState(imagePropertiesAutoBuild.getWidth() >= 0, "Negative image width is not allowed.");
            return imagePropertiesAutoBuild;
        }
    }
}
