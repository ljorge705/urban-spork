package com.drew.metadata.photoshop;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class PsdHeaderDescriptor extends TagDescriptor<PsdHeaderDirectory> {
    public PsdHeaderDescriptor(PsdHeaderDirectory psdHeaderDirectory) {
        super(psdHeaderDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 1) {
            return getChannelCountDescription();
        }
        if (i == 2) {
            return getImageHeightDescription();
        }
        if (i == 3) {
            return getImageWidthDescription();
        }
        if (i == 4) {
            return getBitsPerChannelDescription();
        }
        if (i == 5) {
            return getColorModeDescription();
        }
        return super.getDescription(i);
    }

    public String getChannelCountDescription() {
        Integer integer = ((PsdHeaderDirectory) this._directory).getInteger(1);
        if (integer == null) {
            return null;
        }
        return integer + " channel" + (integer.intValue() == 1 ? "" : "s");
    }

    public String getBitsPerChannelDescription() {
        Integer integer = ((PsdHeaderDirectory) this._directory).getInteger(4);
        if (integer == null) {
            return null;
        }
        return integer + " bit" + (integer.intValue() == 1 ? "" : "s") + " per channel";
    }

    public String getColorModeDescription() {
        return getIndexedDescription(5, "Bitmap", "Grayscale", "Indexed", "RGB", "CMYK", null, null, "Multichannel", "Duotone", "Lab");
    }

    public String getImageHeightDescription() {
        Integer integer = ((PsdHeaderDirectory) this._directory).getInteger(2);
        if (integer == null) {
            return null;
        }
        return integer + " pixel" + (integer.intValue() == 1 ? "" : "s");
    }

    public String getImageWidthDescription() {
        try {
            Integer integer = ((PsdHeaderDirectory) this._directory).getInteger(3);
            if (integer == null) {
                return null;
            }
            return integer + " pixel" + (integer.intValue() == 1 ? "" : "s");
        } catch (Exception unused) {
            return null;
        }
    }
}
