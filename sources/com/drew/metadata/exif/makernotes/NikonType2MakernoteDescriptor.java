package com.drew.metadata.exif.makernotes;

import com.drew.lang.Rational;
import com.drew.metadata.TagDescriptor;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;

/* loaded from: classes5.dex */
public class NikonType2MakernoteDescriptor extends TagDescriptor<NikonType2MakernoteDirectory> {
    public NikonType2MakernoteDescriptor(NikonType2MakernoteDirectory nikonType2MakernoteDirectory) {
        super(nikonType2MakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 1) {
            return getFirmwareVersionDescription();
        }
        if (i == 2) {
            return getIsoSettingDescription();
        }
        if (i == 13) {
            return getProgramShiftDescription();
        }
        if (i == 14) {
            return getExposureDifferenceDescription();
        }
        if (i == 18) {
            return getAutoFlashCompensationDescription();
        }
        if (i == 28) {
            return getExposureTuningDescription();
        }
        if (i == 30) {
            return getColorSpaceDescription();
        }
        if (i == 34) {
            return getActiveDLightingDescription();
        }
        if (i == 42) {
            return getVignetteControlDescription();
        }
        if (i == 139) {
            return getLensStopsDescription();
        }
        if (i == 141) {
            return getColorModeDescription();
        }
        if (i == 177) {
            return getHighISONoiseReductionDescription();
        }
        if (i == 182) {
            return getPowerUpTimeDescription();
        }
        if (i == 23) {
            return getFlashExposureCompensationDescription();
        }
        if (i == 24) {
            return getFlashBracketCompensationDescription();
        }
        if (i == 131) {
            return getLensTypeDescription();
        }
        if (i == 132) {
            return getLensDescription();
        }
        if (i == 146) {
            return getHueAdjustmentDescription();
        }
        if (i != 147) {
            switch (i) {
                case 134:
                    return getDigitalZoomDescription();
                case 135:
                    return getFlashUsedDescription();
                case 136:
                    return getAutoFocusPositionDescription();
                case 137:
                    return getShootingModeDescription();
                default:
                    return super.getDescription(i);
            }
        }
        return getNEFCompressionDescription();
    }

    public String getPowerUpTimeDescription() {
        byte[] byteArray = ((NikonType2MakernoteDirectory) this._directory).getByteArray(182);
        return String.format("%04d:%02d:%02d %02d:%02d:%02d", Short.valueOf(ByteBuffer.wrap(new byte[]{byteArray[0], byteArray[1]}).getShort()), Byte.valueOf(byteArray[2]), Byte.valueOf(byteArray[3]), Byte.valueOf(byteArray[4]), Byte.valueOf(byteArray[5]), Byte.valueOf(byteArray[6]));
    }

    public String getHighISONoiseReductionDescription() {
        return getIndexedDescription(177, "Off", "Minimal", "Low", null, "Normal", null, "High");
    }

    public String getFlashUsedDescription() {
        return getIndexedDescription(135, "Flash Not Used", "Manual Flash", null, "Flash Not Ready", null, null, null, "External Flash", "Fired, Commander Mode", "Fired, TTL Mode");
    }

    public String getNEFCompressionDescription() {
        return getIndexedDescription(147, 1, "Lossy (Type 1)", null, "Uncompressed", null, null, null, "Lossless", "Lossy (Type 2)");
    }

    public String getShootingModeDescription() {
        return getBitFlagDescription(137, new String[]{"Single Frame", "Continuous"}, "Delay", null, "PC Control", "Exposure Bracketing", "Auto ISO", "White-Balance Bracketing", "IR Control");
    }

    public String getLensTypeDescription() {
        return getBitFlagDescription(131, new String[]{"AF", "MF"}, "D", "G", "VR");
    }

    public String getColorSpaceDescription() {
        return getIndexedDescription(30, 1, "sRGB", "Adobe RGB");
    }

    public String getActiveDLightingDescription() {
        Integer integer = ((NikonType2MakernoteDirectory) this._directory).getInteger(34);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 1 ? iIntValue != 3 ? iIntValue != 5 ? iIntValue != 7 ? iIntValue != 65535 ? "Unknown (" + integer + ")" : "Auto" : "Extra High" : "High" : "Normal" : "Light" : "Off";
    }

    public String getVignetteControlDescription() {
        Integer integer = ((NikonType2MakernoteDirectory) this._directory).getInteger(42);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 1 ? iIntValue != 3 ? iIntValue != 5 ? "Unknown (" + integer + ")" : "High" : "Normal" : "Low" : "Off";
    }

    public String getAutoFocusPositionDescription() {
        int[] intArray = ((NikonType2MakernoteDirectory) this._directory).getIntArray(136);
        if (intArray == null) {
            return null;
        }
        if (intArray.length != 4 || intArray[0] != 0 || intArray[2] != 0 || intArray[3] != 0) {
            return "Unknown (" + ((NikonType2MakernoteDirectory) this._directory).getString(136) + ")";
        }
        int i = intArray[1];
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "Unknown (" + intArray[1] + ")" : "Right" : "Left" : "Bottom" : "Top" : "Centre";
    }

    public String getDigitalZoomDescription() {
        Rational rational = ((NikonType2MakernoteDirectory) this._directory).getRational(134);
        if (rational == null) {
            return null;
        }
        return rational.intValue() == 1 ? "No digital zoom" : rational.toSimpleString(true) + "x digital zoom";
    }

    public String getProgramShiftDescription() {
        return getEVDescription(13);
    }

    public String getExposureDifferenceDescription() {
        return getEVDescription(14);
    }

    public String getAutoFlashCompensationDescription() {
        return getEVDescription(18);
    }

    public String getFlashExposureCompensationDescription() {
        return getEVDescription(23);
    }

    public String getFlashBracketCompensationDescription() {
        return getEVDescription(24);
    }

    public String getExposureTuningDescription() {
        return getEVDescription(28);
    }

    public String getLensStopsDescription() {
        return getEVDescription(139);
    }

    private String getEVDescription(int i) {
        int[] intArray = ((NikonType2MakernoteDirectory) this._directory).getIntArray(i);
        if (intArray == null || intArray.length < 2 || intArray.length < 3 || intArray[2] == 0) {
            return null;
        }
        return new DecimalFormat("0.##").format((intArray[0] * intArray[1]) / intArray[2]) + " EV";
    }

    public String getIsoSettingDescription() {
        int[] intArray = ((NikonType2MakernoteDirectory) this._directory).getIntArray(2);
        if (intArray == null) {
            return null;
        }
        if (intArray[0] != 0 || intArray[1] == 0) {
            return "Unknown (" + ((NikonType2MakernoteDirectory) this._directory).getString(2) + ")";
        }
        return "ISO " + intArray[1];
    }

    public String getLensDescription() {
        return getLensSpecificationDescription(132);
    }

    public String getLensFocusDistance() {
        int[] decryptedIntArray = ((NikonType2MakernoteDirectory) this._directory).getDecryptedIntArray(152);
        if (decryptedIntArray == null || decryptedIntArray.length < 11) {
            return null;
        }
        return String.format("%.2fm", Double.valueOf(getDistanceInMeters(decryptedIntArray[10])));
    }

    public String getHueAdjustmentDescription() {
        return getFormattedString(146, "%s degrees");
    }

    public String getColorModeDescription() {
        String string = ((NikonType2MakernoteDirectory) this._directory).getString(141);
        if (string == null) {
            return null;
        }
        return string.startsWith("MODE1") ? "Mode I (sRGB)" : string;
    }

    public String getFirmwareVersionDescription() {
        return getVersionBytesDescription(1, 2);
    }

    private double getDistanceInMeters(int i) {
        if (i < 0) {
            i += 256;
        }
        return Math.pow(10.0d, i / 40.0f) * 0.01d;
    }
}
