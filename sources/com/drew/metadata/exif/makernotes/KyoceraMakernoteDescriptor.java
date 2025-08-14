package com.drew.metadata.exif.makernotes;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class KyoceraMakernoteDescriptor extends TagDescriptor<KyoceraMakernoteDirectory> {
    public KyoceraMakernoteDescriptor(KyoceraMakernoteDirectory kyoceraMakernoteDirectory) {
        super(kyoceraMakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 1) {
            return getProprietaryThumbnailDataDescription();
        }
        return super.getDescription(i);
    }

    public String getProprietaryThumbnailDataDescription() {
        return getByteLengthDescription(1);
    }
}
