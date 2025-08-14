package com.drew.metadata.exif.makernotes;

import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class OlympusRawDevelopment2MakernoteDescriptor extends TagDescriptor<OlympusRawDevelopment2MakernoteDirectory> {
    private static final HashMap<Integer, String> _filters;

    public OlympusRawDevelopment2MakernoteDescriptor(OlympusRawDevelopment2MakernoteDirectory olympusRawDevelopment2MakernoteDirectory) {
        super(olympusRawDevelopment2MakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 0) {
            return getRawDevVersionDescription();
        }
        if (i == 256) {
            return getRawDevExposureBiasValueDescription();
        }
        if (i == 289) {
            return getRawDevArtFilterDescription();
        }
        if (i == 272) {
            return getRawDevPmBwFilterDescription();
        }
        if (i != 273) {
            switch (i) {
                case 265:
                    return getRawDevColorSpaceDescription();
                case 266:
                    return getRawDevNoiseReductionDescription();
                case 267:
                    return getRawDevEngineDescription();
                case 268:
                    return getRawDevPictureModeDescription();
                default:
                    return super.getDescription(i);
            }
        }
        return getRawDevPmPictureToneDescription();
    }

    public String getRawDevVersionDescription() {
        return getVersionBytesDescription(0, 4);
    }

    public String getRawDevExposureBiasValueDescription() {
        return getIndexedDescription(256, 1, "Color Temperature", "Gray Point");
    }

    public String getRawDevColorSpaceDescription() {
        return getIndexedDescription(265, "sRGB", "Adobe RGB", "Pro Photo RGB");
    }

    public String getRawDevNoiseReductionDescription() {
        Integer integer = ((OlympusRawDevelopment2MakernoteDirectory) this._directory).getInteger(266);
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
        if (((iIntValue >> 3) & 1) != 0) {
            sb.append("Noise Filter (Auto), ");
        }
        if (sb.length() > 2) {
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.toString();
    }

    public String getRawDevEngineDescription() {
        return getIndexedDescription(267, "High Speed", "High Function", "Advanced High Speed", "Advanced High Function");
    }

    public String getRawDevPictureModeDescription() {
        Integer integer = ((OlympusRawDevelopment2MakernoteDirectory) this._directory).getInteger(268);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 1 ? iIntValue != 2 ? iIntValue != 3 ? iIntValue != 256 ? iIntValue != 512 ? "Unknown (" + integer + ")" : "Sepia" : "Monotone" : "Muted" : "Natural" : "Vivid";
    }

    public String getRawDevPmBwFilterDescription() {
        return getIndexedDescription(272, "Neutral", "Yellow", "Orange", "Red", "Green");
    }

    public String getRawDevPmPictureToneDescription() {
        return getIndexedDescription(273, "Neutral", "Sepia", "Blue", "Purple", "Green");
    }

    public String getRawDevArtFilterDescription() {
        return getFilterDescription(289);
    }

    public String getFilterDescription(int i) {
        int[] intArray = ((OlympusRawDevelopment2MakernoteDirectory) this._directory).getIntArray(i);
        if (intArray == null || intArray.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < intArray.length; i2++) {
            if (i2 == 0) {
                HashMap<Integer, String> map = _filters;
                sb.append(map.containsKey(Integer.valueOf(intArray[i2])) ? map.get(Integer.valueOf(intArray[i2])) : "[unknown]");
            } else {
                sb.append(intArray[i2]).append("; ");
            }
            sb.append("; ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _filters = map;
        map.put(0, "Off");
        map.put(1, "Soft Focus");
        map.put(2, "Pop Art");
        map.put(3, "Pale & Light Color");
        map.put(4, "Light Tone");
        map.put(5, "Pin Hole");
        map.put(6, "Grainy Film");
        map.put(9, "Diorama");
        map.put(10, "Cross Process");
        map.put(12, "Fish Eye");
        map.put(13, "Drawing");
        map.put(14, "Gentle Sepia");
        map.put(15, "Pale & Light Color II");
        map.put(16, "Pop Art II");
        map.put(17, "Pin Hole II");
        map.put(18, "Pin Hole III");
        map.put(19, "Grainy Film II");
        map.put(20, "Dramatic Tone");
        map.put(21, "Punk");
        map.put(22, "Soft Focus 2");
        map.put(23, "Sparkle");
        map.put(24, "Watercolor");
        map.put(25, "Key Line");
        map.put(26, "Key Line II");
        map.put(27, "Miniature");
        map.put(28, "Reflection");
        map.put(29, "Fragmented");
        map.put(31, "Cross Process II");
        map.put(32, "Dramatic Tone II");
        map.put(33, "Watercolor I");
        map.put(34, "Watercolor II");
        map.put(35, "Diorama II");
        map.put(36, "Vintage");
        map.put(37, "Vintage II");
        map.put(38, "Vintage III");
        map.put(39, "Partial Color");
        map.put(40, "Partial Color II");
        map.put(41, "Partial Color III");
    }
}
