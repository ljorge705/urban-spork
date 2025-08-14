package com.drew.metadata.exif.makernotes;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class AppleMakernoteDescriptor extends TagDescriptor<AppleMakernoteDirectory> {
    public AppleMakernoteDescriptor(AppleMakernoteDirectory appleMakernoteDirectory) {
        super(appleMakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 10) {
            return getHdrImageTypeDescription();
        }
        return super.getDescription(i);
    }

    public String getHdrImageTypeDescription() {
        return getIndexedDescription(10, 3, "HDR Image", "Original Image");
    }
}
