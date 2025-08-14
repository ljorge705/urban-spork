package com.drew.metadata.exif.makernotes;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class SonyType6MakernoteDescriptor extends TagDescriptor<SonyType6MakernoteDirectory> {
    public SonyType6MakernoteDescriptor(SonyType6MakernoteDirectory sonyType6MakernoteDirectory) {
        super(sonyType6MakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 8192) {
            return getMakernoteThumbVersionDescription();
        }
        return super.getDescription(i);
    }

    public String getMakernoteThumbVersionDescription() {
        return getVersionBytesDescription(8192, 2);
    }
}
