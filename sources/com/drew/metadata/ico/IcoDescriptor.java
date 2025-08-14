package com.drew.metadata.ico;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class IcoDescriptor extends TagDescriptor<IcoDirectory> {
    public IcoDescriptor(IcoDirectory icoDirectory) {
        super(icoDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 1) {
            return getImageTypeDescription();
        }
        if (i == 2) {
            return getImageWidthDescription();
        }
        if (i == 3) {
            return getImageHeightDescription();
        }
        if (i == 4) {
            return getColourPaletteSizeDescription();
        }
        return super.getDescription(i);
    }

    public String getImageTypeDescription() {
        return getIndexedDescription(1, 1, "Icon", "Cursor");
    }

    public String getImageWidthDescription() {
        Integer integer = ((IcoDirectory) this._directory).getInteger(2);
        if (integer == null) {
            return null;
        }
        return (integer.intValue() == 0 ? 256 : integer.intValue()) + " pixels";
    }

    public String getImageHeightDescription() {
        Integer integer = ((IcoDirectory) this._directory).getInteger(3);
        if (integer == null) {
            return null;
        }
        return (integer.intValue() == 0 ? 256 : integer.intValue()) + " pixels";
    }

    public String getColourPaletteSizeDescription() {
        Integer integer = ((IcoDirectory) this._directory).getInteger(4);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() == 0) {
            return "No palette";
        }
        return integer + " colour" + (integer.intValue() == 1 ? "" : "s");
    }
}
