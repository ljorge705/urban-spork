package com.drew.metadata.exif.makernotes;

import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class KodakMakernoteDescriptor extends TagDescriptor<KodakMakernoteDirectory> {
    public KodakMakernoteDescriptor(KodakMakernoteDirectory kodakMakernoteDirectory) {
        super(kodakMakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 9) {
            return getQualityDescription();
        }
        if (i == 10) {
            return getBurstModeDescription();
        }
        if (i == 27) {
            return getShutterModeDescription();
        }
        if (i == 56) {
            return getFocusModeDescription();
        }
        if (i == 64) {
            return getWhiteBalanceDescription();
        }
        if (i == 102) {
            return getColorModeDescription();
        }
        if (i == 107) {
            return getSharpnessDescription();
        }
        if (i == 92) {
            return getFlashModeDescription();
        }
        if (i == 93) {
            return getFlashFiredDescription();
        }
        return super.getDescription(i);
    }

    public String getSharpnessDescription() {
        return getIndexedDescription(107, "Normal");
    }

    public String getColorModeDescription() {
        Integer integer = ((KodakMakernoteDirectory) this._directory).getInteger(102);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 1 ? iIntValue != 2 ? iIntValue != 3 ? iIntValue != 4 ? iIntValue != 32 ? iIntValue != 64 ? iIntValue != 256 ? iIntValue != 512 ? iIntValue != 8192 ? iIntValue != 16384 ? "Unknown (" + integer + ")" : "Sepia" : "B&W" : "Neutral Color" : "Saturated Color" : "Neutral Color" : "Saturated Color" : "B&W Red Filter" : "B&W Yellow Filter" : "Sepia" : "B&W";
    }

    public String getFlashFiredDescription() {
        return getIndexedDescription(93, "No", "Yes");
    }

    public String getFlashModeDescription() {
        Integer integer = ((KodakMakernoteDirectory) this._directory).getInteger(92);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 1 ? iIntValue != 2 ? iIntValue != 3 ? iIntValue != 16 ? iIntValue != 32 ? iIntValue != 64 ? "Unknown (" + integer + ")" : "Red Eye" : "Off" : "Fill Flash" : "Red Eye" : "Off" : "Fill Flash" : "Auto";
    }

    public String getWhiteBalanceDescription() {
        return getIndexedDescription(64, "Auto", ExifInterface.TAG_FLASH, "Tungsten", "Daylight");
    }

    public String getFocusModeDescription() {
        return getIndexedDescription(56, "Normal", null, "Macro");
    }

    public String getShutterModeDescription() {
        Integer integer = ((KodakMakernoteDirectory) this._directory).getInteger(27);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 8 ? iIntValue != 32 ? "Unknown (" + integer + ")" : "Manual" : "Aperture Priority" : "Auto";
    }

    public String getBurstModeDescription() {
        return getIndexedDescription(10, "Off", "On");
    }

    public String getQualityDescription() {
        return getIndexedDescription(9, 1, "Fine", "Normal");
    }
}
