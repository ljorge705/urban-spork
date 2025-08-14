package com.drew.metadata.exif.makernotes;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class OlympusRawDevelopmentMakernoteDescriptor extends TagDescriptor<OlympusRawDevelopmentMakernoteDirectory> {
    public OlympusRawDevelopmentMakernoteDescriptor(OlympusRawDevelopmentMakernoteDirectory olympusRawDevelopmentMakernoteDirectory) {
        super(olympusRawDevelopmentMakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 0) {
            return getRawDevVersionDescription();
        }
        switch (i) {
            case 264:
                return getRawDevColorSpaceDescription();
            case 265:
                return getRawDevEngineDescription();
            case 266:
                return getRawDevNoiseReductionDescription();
            case 267:
                return getRawDevEditStatusDescription();
            case 268:
                return getRawDevSettingsDescription();
            default:
                return super.getDescription(i);
        }
    }

    public String getRawDevVersionDescription() {
        return getVersionBytesDescription(0, 4);
    }

    public String getRawDevColorSpaceDescription() {
        return getIndexedDescription(264, "sRGB", "Adobe RGB", "Pro Photo RGB");
    }

    public String getRawDevEngineDescription() {
        return getIndexedDescription(265, "High Speed", "High Function", "Advanced High Speed", "Advanced High Function");
    }

    public String getRawDevNoiseReductionDescription() {
        Integer integer = ((OlympusRawDevelopmentMakernoteDirectory) this._directory).getInteger(266);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() == 0) {
            return "(none)";
        }
        StringBuilder sb = new StringBuilder();
        int iIntValue = integer.intValue();
        if ((iIntValue & 1) != 0) {
            sb.append("Noise Reduction, ");
        }
        if (((iIntValue >> 1) & 1) != 0) {
            sb.append("Noise Filter, ");
        }
        if (((iIntValue >> 2) & 1) != 0) {
            sb.append("Noise Filter (ISO Boost), ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    public String getRawDevEditStatusDescription() {
        Integer integer = ((OlympusRawDevelopmentMakernoteDirectory) this._directory).getInteger(267);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 1 ? (iIntValue == 6 || iIntValue == 8) ? "Edited (Portrait)" : "Unknown (" + integer + ")" : "Edited (Landscape)" : "Original";
    }

    public String getRawDevSettingsDescription() {
        Integer integer = ((OlympusRawDevelopmentMakernoteDirectory) this._directory).getInteger(268);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() == 0) {
            return "(none)";
        }
        StringBuilder sb = new StringBuilder();
        int iIntValue = integer.intValue();
        if ((iIntValue & 1) != 0) {
            sb.append("WB Color Temp, ");
        }
        if (((iIntValue >> 1) & 1) != 0) {
            sb.append("WB Gray Point, ");
        }
        if (((iIntValue >> 2) & 1) != 0) {
            sb.append("Saturation, ");
        }
        if (((iIntValue >> 3) & 1) != 0) {
            sb.append("Contrast, ");
        }
        if (((iIntValue >> 4) & 1) != 0) {
            sb.append("Sharpness, ");
        }
        if (((iIntValue >> 5) & 1) != 0) {
            sb.append("Color Space, ");
        }
        if (((iIntValue >> 6) & 1) != 0) {
            sb.append("High Function, ");
        }
        if (((iIntValue >> 7) & 1) != 0) {
            sb.append("Noise Reduction, ");
        }
        return sb.substring(0, sb.length() - 2);
    }
}
