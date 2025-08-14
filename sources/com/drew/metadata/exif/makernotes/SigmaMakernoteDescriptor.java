package com.drew.metadata.exif.makernotes;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class SigmaMakernoteDescriptor extends TagDescriptor<SigmaMakernoteDirectory> {
    public SigmaMakernoteDescriptor(SigmaMakernoteDirectory sigmaMakernoteDirectory) {
        super(sigmaMakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 8) {
            return getExposureModeDescription();
        }
        if (i == 9) {
            return getMeteringModeDescription();
        }
        return super.getDescription(i);
    }

    private String getMeteringModeDescription() {
        String string = ((SigmaMakernoteDirectory) this._directory).getString(9);
        if (string == null || string.length() == 0) {
            return null;
        }
        char cCharAt = string.charAt(0);
        return cCharAt != '8' ? cCharAt != 'A' ? cCharAt != 'C' ? string : "Center Weighted Average" : "Average" : "Multi Segment";
    }

    private String getExposureModeDescription() {
        String string = ((SigmaMakernoteDirectory) this._directory).getString(8);
        if (string == null || string.length() == 0) {
            return null;
        }
        char cCharAt = string.charAt(0);
        return cCharAt != 'A' ? cCharAt != 'M' ? cCharAt != 'P' ? cCharAt != 'S' ? string : "Shutter Speed Priority AE" : "Program AE" : "Manual" : "Aperture Priority AE";
    }
}
