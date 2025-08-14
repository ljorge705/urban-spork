package com.drew.metadata.exif;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class PanasonicRawIFD0Descriptor extends TagDescriptor<PanasonicRawIFD0Directory> {
    public PanasonicRawIFD0Descriptor(PanasonicRawIFD0Directory panasonicRawIFD0Directory) {
        super(panasonicRawIFD0Directory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 1) {
            return getVersionBytesDescription(1, 2);
        }
        if (i == 274) {
            return getOrientationDescription(274);
        }
        return super.getDescription(i);
    }
}
