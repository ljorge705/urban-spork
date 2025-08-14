package com.drew.metadata.adobe;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class AdobeJpegDescriptor extends TagDescriptor<AdobeJpegDirectory> {
    public AdobeJpegDescriptor(AdobeJpegDirectory adobeJpegDirectory) {
        super(adobeJpegDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 0) {
            return getDctEncodeVersionDescription();
        }
        if (i == 3) {
            return getColorTransformDescription();
        }
        return super.getDescription(i);
    }

    private String getDctEncodeVersionDescription() {
        Integer integer = ((AdobeJpegDirectory) this._directory).getInteger(0);
        if (integer == null) {
            return null;
        }
        return integer.intValue() == 100 ? "100" : Integer.toString(integer.intValue());
    }

    private String getColorTransformDescription() {
        return getIndexedDescription(3, "Unknown (RGB or CMYK)", "YCbCr", "YCCK");
    }
}
