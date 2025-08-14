package com.drew.metadata.exif.makernotes;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class PentaxMakernoteDescriptor extends TagDescriptor<PentaxMakernoteDirectory> {
    public PentaxMakernoteDescriptor(PentaxMakernoteDirectory pentaxMakernoteDirectory) {
        super(pentaxMakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 1) {
            return getCaptureModeDescription();
        }
        if (i == 2) {
            return getQualityLevelDescription();
        }
        if (i == 3) {
            return getFocusModeDescription();
        }
        if (i == 4) {
            return getFlashModeDescription();
        }
        if (i == 7) {
            return getWhiteBalanceDescription();
        }
        if (i == 20) {
            return getIsoSpeedDescription();
        }
        if (i != 23) {
            switch (i) {
                case 10:
                    return getDigitalZoomDescription();
                case 11:
                    return getSharpnessDescription();
                case 12:
                    return getContrastDescription();
                case 13:
                    return getSaturationDescription();
                default:
                    return super.getDescription(i);
            }
        }
        return getColourDescription();
    }

    public String getColourDescription() {
        return getIndexedDescription(23, 1, "Normal", "Black & White", "Sepia");
    }

    public String getIsoSpeedDescription() {
        Integer integer = ((PentaxMakernoteDirectory) this._directory).getInteger(20);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 10 ? iIntValue != 16 ? iIntValue != 100 ? iIntValue != 200 ? "Unknown (" + integer + ")" : "ISO 200" : "ISO 100" : "ISO 200" : "ISO 100";
    }

    public String getSaturationDescription() {
        return getIndexedDescription(13, "Normal", "Low", "High");
    }

    public String getContrastDescription() {
        return getIndexedDescription(12, "Normal", "Low", "High");
    }

    public String getSharpnessDescription() {
        return getIndexedDescription(11, "Normal", "Soft", "Hard");
    }

    public String getDigitalZoomDescription() {
        Float floatObject = ((PentaxMakernoteDirectory) this._directory).getFloatObject(10);
        if (floatObject == null) {
            return null;
        }
        return floatObject.floatValue() == 0.0f ? "Off" : Float.toString(floatObject.floatValue());
    }

    public String getWhiteBalanceDescription() {
        return getIndexedDescription(7, "Auto", "Daylight", "Shade", "Tungsten", "Fluorescent", "Manual");
    }

    public String getFlashModeDescription() {
        return getIndexedDescription(4, 1, "Auto", "Flash On", null, "Flash Off", null, "Red-eye Reduction");
    }

    public String getFocusModeDescription() {
        return getIndexedDescription(3, 2, "Custom", "Auto");
    }

    public String getQualityLevelDescription() {
        return getIndexedDescription(2, "Good", "Better", "Best");
    }

    public String getCaptureModeDescription() {
        return getIndexedDescription(1, "Auto", "Night-scene", "Manual", null, "Multiple");
    }
}
