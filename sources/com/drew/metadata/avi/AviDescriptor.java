package com.drew.metadata.avi;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class AviDescriptor extends TagDescriptor<AviDirectory> {
    public AviDescriptor(AviDirectory aviDirectory) {
        super(aviDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 6 || i == 7) {
            return getSizeDescription(i);
        }
        return super.getDescription(i);
    }

    public String getSizeDescription(int i) {
        return ((AviDirectory) this._directory).getString(i) + " pixels";
    }
}
