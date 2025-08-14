package com.drew.metadata.exif.makernotes;

import com.drew.metadata.TagDescriptor;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public class OlympusImageProcessingMakernoteDescriptor extends TagDescriptor<OlympusImageProcessingMakernoteDirectory> {
    public OlympusImageProcessingMakernoteDescriptor(OlympusImageProcessingMakernoteDirectory olympusImageProcessingMakernoteDirectory) {
        super(olympusImageProcessingMakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 0) {
            return getImageProcessingVersionDescription();
        }
        if (i == 512) {
            return getColorMatrixDescription();
        }
        if (i == 4124) {
            return getMultipleExposureModeDescription();
        }
        if (i == 4370) {
            return getAspectRatioDescription();
        }
        if (i == 6400) {
            return getKeystoneCompensationDescription();
        }
        if (i != 6401) {
            switch (i) {
                case 4112:
                    return getNoiseReduction2Description();
                case 4113:
                    return getDistortionCorrection2Description();
                case 4114:
                    return getShadingCompensation2Description();
                default:
                    return super.getDescription(i);
            }
        }
        return getKeystoneDirectionDescription();
    }

    public String getImageProcessingVersionDescription() {
        return getVersionBytesDescription(0, 4);
    }

    public String getColorMatrixDescription() {
        int[] intArray = ((OlympusImageProcessingMakernoteDirectory) this._directory).getIntArray(512);
        if (intArray == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intArray.length; i++) {
            if (i != 0) {
                sb.append(StringUtils.SPACE);
            }
            sb.append((int) ((short) intArray[i]));
        }
        return sb.toString();
    }

    public String getNoiseReduction2Description() {
        Integer integer = ((OlympusImageProcessingMakernoteDirectory) this._directory).getInteger(4112);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() == 0) {
            return "(none)";
        }
        StringBuilder sb = new StringBuilder();
        short sShortValue = integer.shortValue();
        if ((sShortValue & 1) != 0) {
            sb.append("Noise Reduction, ");
        }
        if (((sShortValue >> 1) & 1) != 0) {
            sb.append("Noise Filter, ");
        }
        if (((sShortValue >> 2) & 1) != 0) {
            sb.append("Noise Filter (ISO Boost), ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    public String getDistortionCorrection2Description() {
        return getIndexedDescription(4113, "Off", "On");
    }

    public String getShadingCompensation2Description() {
        return getIndexedDescription(4114, "Off", "On");
    }

    public String getMultipleExposureModeDescription() {
        int[] intArray = ((OlympusImageProcessingMakernoteDirectory) this._directory).getIntArray(4124);
        if (intArray == null) {
            Integer integer = ((OlympusImageProcessingMakernoteDirectory) this._directory).getInteger(4124);
            if (integer == null) {
                return null;
            }
            intArray = new int[]{integer.intValue()};
        }
        if (intArray.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        short s = (short) intArray[0];
        if (s == 0) {
            sb.append("Off");
        } else if (s == 2) {
            sb.append("On (2 frames)");
        } else if (s == 3) {
            sb.append("On (3 frames)");
        } else {
            sb.append("Unknown (").append((int) ((short) intArray[0])).append(")");
        }
        if (intArray.length > 1) {
            sb.append("; ").append((int) ((short) intArray[1]));
        }
        return sb.toString();
    }

    public String getAspectRatioDescription() {
        byte[] byteArray = ((OlympusImageProcessingMakernoteDirectory) this._directory).getByteArray(OlympusImageProcessingMakernoteDirectory.TagAspectRatio);
        if (byteArray == null || byteArray.length < 2) {
            return null;
        }
        String str = String.format("%d %d", Byte.valueOf(byteArray[0]), Byte.valueOf(byteArray[1]));
        return str.equals("1 1") ? "4:3" : str.equals("1 4") ? "1:1" : str.equals("2 1") ? "3:2 (RAW)" : str.equals("2 2") ? "3:2" : str.equals("3 1") ? "16:9 (RAW)" : str.equals("3 3") ? "16:9" : str.equals("4 1") ? "1:1 (RAW)" : str.equals("4 4") ? "6:6" : str.equals("5 5") ? "5:4" : str.equals("6 6") ? "7:6" : str.equals("7 7") ? "6:5" : str.equals("8 8") ? "7:5" : str.equals("9 1") ? "3:4 (RAW)" : str.equals("9 9") ? "3:4" : "Unknown (" + str + ")";
    }

    public String getKeystoneCompensationDescription() {
        byte[] byteArray = ((OlympusImageProcessingMakernoteDirectory) this._directory).getByteArray(OlympusImageProcessingMakernoteDirectory.TagKeystoneCompensation);
        if (byteArray == null || byteArray.length < 2) {
            return null;
        }
        String str = String.format("%d %d", Byte.valueOf(byteArray[0]), Byte.valueOf(byteArray[1]));
        return str.equals("0 0") ? "Off" : str.equals("0 1") ? "On" : "Unknown (" + str + ")";
    }

    public String getKeystoneDirectionDescription() {
        return getIndexedDescription(OlympusImageProcessingMakernoteDirectory.TagKeystoneDirection, "Vertical", "Horizontal");
    }
}
