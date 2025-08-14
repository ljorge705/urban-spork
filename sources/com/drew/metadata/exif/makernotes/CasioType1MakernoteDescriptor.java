package com.drew.metadata.exif.makernotes;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class CasioType1MakernoteDescriptor extends TagDescriptor<CasioType1MakernoteDirectory> {
    public CasioType1MakernoteDescriptor(CasioType1MakernoteDirectory casioType1MakernoteDirectory) {
        super(casioType1MakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i != 20) {
            switch (i) {
                case 1:
                    return getRecordingModeDescription();
                case 2:
                    return getQualityDescription();
                case 3:
                    return getFocusingModeDescription();
                case 4:
                    return getFlashModeDescription();
                case 5:
                    return getFlashIntensityDescription();
                case 6:
                    return getObjectDistanceDescription();
                case 7:
                    return getWhiteBalanceDescription();
                default:
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
        }
        return getCcdSensitivityDescription();
    }

    public String getCcdSensitivityDescription() {
        Integer integer = ((CasioType1MakernoteDirectory) this._directory).getInteger(20);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 64 ? iIntValue != 80 ? iIntValue != 100 ? iIntValue != 125 ? iIntValue != 244 ? iIntValue != 250 ? "Unknown (" + integer + ")" : "+2.0" : "+3.0" : "+1.0" : "High" : "Normal (ISO 80 equivalent)" : "Normal";
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
        Integer integer = ((CasioType1MakernoteDirectory) this._directory).getInteger(10);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 65536 ? (iIntValue == 65537 || iIntValue == 131072) ? "2x digital zoom" : iIntValue != 262144 ? "Unknown (" + integer + ")" : "4x digital zoom" : "No digital zoom";
    }

    public String getWhiteBalanceDescription() {
        Integer integer = ((CasioType1MakernoteDirectory) this._directory).getInteger(7);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 1 ? iIntValue != 2 ? iIntValue != 3 ? iIntValue != 4 ? iIntValue != 5 ? iIntValue != 129 ? "Unknown (" + integer + ")" : "Manual" : "Shade" : "Florescent" : "Daylight" : "Tungsten" : "Auto";
    }

    public String getObjectDistanceDescription() {
        if (((CasioType1MakernoteDirectory) this._directory).getInteger(6) == null) {
            return null;
        }
        return getFocalLengthDescription(r0.intValue());
    }

    public String getFlashIntensityDescription() {
        Integer integer = ((CasioType1MakernoteDirectory) this._directory).getInteger(5);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 11 ? iIntValue != 13 ? iIntValue != 15 ? "Unknown (" + integer + ")" : "Strong" : "Normal" : "Weak";
    }

    public String getFlashModeDescription() {
        return getIndexedDescription(4, 1, "Auto", "On", "Off", "Red eye reduction");
    }

    public String getFocusingModeDescription() {
        return getIndexedDescription(3, 2, "Macro", "Auto focus", "Manual focus", "Infinity");
    }

    public String getQualityDescription() {
        return getIndexedDescription(2, 1, "Economy", "Normal", "Fine");
    }

    public String getRecordingModeDescription() {
        return getIndexedDescription(1, 1, "Single shutter", "Panorama", "Night scene", "Portrait", "Landscape");
    }
}
