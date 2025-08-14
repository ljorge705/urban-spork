package com.drew.metadata.exif.makernotes;

import com.drew.lang.Rational;
import com.drew.metadata.TagDescriptor;
import com.google.android.material.timepicker.TimeModel;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public class OlympusFocusInfoMakernoteDescriptor extends TagDescriptor<OlympusFocusInfoMakernoteDirectory> {
    public OlympusFocusInfoMakernoteDescriptor(OlympusFocusInfoMakernoteDirectory olympusFocusInfoMakernoteDirectory) {
        super(olympusFocusInfoMakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 0) {
            return getFocusInfoVersionDescription();
        }
        if (i == 521) {
            return getAutoFocusDescription();
        }
        if (i == 773) {
            return getFocusDistanceDescription();
        }
        if (i == 776) {
            return getAfPointDescription();
        }
        if (i == 4609) {
            return getExternalFlashDescription();
        }
        if (i == 5376) {
            return getSensorTemperatureDescription();
        }
        if (i == 5632) {
            return getImageStabilizationDescription();
        }
        if (i == 4612) {
            return getExternalFlashBounceDescription();
        }
        if (i == 4613) {
            return getExternalFlashZoomDescription();
        }
        if (i == 4617) {
            return getManualFlashDescription();
        }
        if (i == 4618) {
            return getMacroLedDescription();
        }
        return super.getDescription(i);
    }

    public String getFocusInfoVersionDescription() {
        return getVersionBytesDescription(0, 4);
    }

    public String getAutoFocusDescription() {
        return getIndexedDescription(521, "Off", "On");
    }

    public String getFocusDistanceDescription() {
        Rational rational = ((OlympusFocusInfoMakernoteDirectory) this._directory).getRational(773);
        return (rational == null || rational.getNumerator() == 4294967295L || rational.getNumerator() == 0) ? "inf" : (rational.getNumerator() / 1000.0d) + " m";
    }

    public String getAfPointDescription() {
        Integer integer = ((OlympusFocusInfoMakernoteDirectory) this._directory).getInteger(OlympusFocusInfoMakernoteDirectory.TagAfPoint);
        if (integer == null) {
            return null;
        }
        return integer.toString();
    }

    public String getExternalFlashDescription() {
        int[] intArray = ((OlympusFocusInfoMakernoteDirectory) this._directory).getIntArray(4609);
        if (intArray == null || intArray.length < 2) {
            return null;
        }
        String str = String.format("%d %d", Short.valueOf((short) intArray[0]), Short.valueOf((short) intArray[1]));
        return str.equals("0 0") ? "Off" : str.equals("1 0") ? "On" : "Unknown (" + str + ")";
    }

    public String getExternalFlashBounceDescription() {
        return getIndexedDescription(OlympusFocusInfoMakernoteDirectory.TagExternalFlashBounce, "Bounce or Off", "Direct");
    }

    public String getExternalFlashZoomDescription() {
        int[] intArray = ((OlympusFocusInfoMakernoteDirectory) this._directory).getIntArray(OlympusFocusInfoMakernoteDirectory.TagExternalFlashZoom);
        if (intArray == null) {
            Integer integer = ((OlympusFocusInfoMakernoteDirectory) this._directory).getInteger(OlympusFocusInfoMakernoteDirectory.TagExternalFlashZoom);
            if (integer == null) {
                return null;
            }
            intArray = new int[]{integer.intValue()};
        }
        if (intArray.length == 0) {
            return null;
        }
        String str = String.format(TimeModel.NUMBER_FORMAT, Short.valueOf((short) intArray[0]));
        if (intArray.length > 1) {
            str = str + StringUtils.SPACE + String.format(TimeModel.NUMBER_FORMAT, Short.valueOf((short) intArray[1]));
        }
        return str.equals("0") ? "Off" : str.equals("1") ? "On" : str.equals("0 0") ? "Off" : str.equals("1 0") ? "On" : "Unknown (" + str + ")";
    }

    public String getManualFlashDescription() {
        int[] intArray = ((OlympusFocusInfoMakernoteDirectory) this._directory).getIntArray(OlympusFocusInfoMakernoteDirectory.TagManualFlash);
        if (intArray == null) {
            return null;
        }
        return ((short) intArray[0]) == 0 ? "Off" : ((short) intArray[1]) == 1 ? "Full" : "On (1/" + ((int) ((short) intArray[1])) + " strength)";
    }

    public String getMacroLedDescription() {
        return getIndexedDescription(OlympusFocusInfoMakernoteDirectory.TagMacroLed, "Off", "On");
    }

    public String getSensorTemperatureDescription() {
        return ((OlympusFocusInfoMakernoteDirectory) this._directory).getString(OlympusFocusInfoMakernoteDirectory.TagSensorTemperature);
    }

    public String getImageStabilizationDescription() {
        byte[] byteArray = ((OlympusFocusInfoMakernoteDirectory) this._directory).getByteArray(OlympusFocusInfoMakernoteDirectory.TagImageStabilization);
        if (byteArray == null) {
            return null;
        }
        if ((byteArray[0] | byteArray[1] | byteArray[2] | byteArray[3]) == 0) {
            return "Off";
        }
        return "On, ".concat((byteArray[43] & 1) > 0 ? "Mode 1" : "Mode 2");
    }
}
