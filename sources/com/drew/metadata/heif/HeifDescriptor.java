package com.drew.metadata.heif;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class HeifDescriptor extends TagDescriptor<HeifDirectory> {
    public HeifDescriptor(HeifDirectory heifDirectory) {
        super(heifDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 4 || i == 5) {
            return getPixelDescription(i);
        }
        if (i == 6) {
            return getRotationDescription(i);
        }
        return super.getDescription(i);
    }

    public String getPixelDescription(int i) {
        return ((HeifDirectory) this._directory).getString(i) + " pixels";
    }

    public String getRotationDescription(int i) {
        return (((HeifDirectory) this._directory).getInteger(i).intValue() * 90) + " degrees";
    }
}
