package com.drew.metadata.exif.makernotes;

import com.drew.lang.Rational;
import com.drew.metadata.TagDescriptor;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public class OlympusRawInfoMakernoteDescriptor extends TagDescriptor<OlympusRawInfoMakernoteDirectory> {
    public OlympusRawInfoMakernoteDescriptor(OlympusRawInfoMakernoteDirectory olympusRawInfoMakernoteDirectory) {
        super(olympusRawInfoMakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 0) {
            return getVersionBytesDescription(0, 4);
        }
        if (i == 512) {
            return getColorMatrix2Description();
        }
        if (i == 1537) {
            return getYCbCrCoefficientsDescription();
        }
        if (i == 4096) {
            return getOlympusLightSourceDescription();
        }
        return super.getDescription(i);
    }

    public String getColorMatrix2Description() {
        int[] intArray = ((OlympusRawInfoMakernoteDirectory) this._directory).getIntArray(512);
        if (intArray == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intArray.length; i++) {
            sb.append((int) ((short) intArray[i]));
            if (i < intArray.length - 1) {
                sb.append(StringUtils.SPACE);
            }
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    public String getYCbCrCoefficientsDescription() {
        int[] intArray = ((OlympusRawInfoMakernoteDirectory) this._directory).getIntArray(1537);
        if (intArray == null) {
            return null;
        }
        int length = intArray.length / 2;
        Rational[] rationalArr = new Rational[length];
        for (int i = 0; i < intArray.length / 2; i++) {
            int i2 = i * 2;
            rationalArr[i] = new Rational((short) intArray[i2], (short) intArray[i2 + 1]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < length; i3++) {
            sb.append(rationalArr[i3].doubleValue());
            if (i3 < length - 1) {
                sb.append(StringUtils.SPACE);
            }
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    public String getOlympusLightSourceDescription() {
        Integer integer = ((OlympusRawInfoMakernoteDirectory) this._directory).getInteger(4096);
        if (integer == null) {
            return null;
        }
        short sShortValue = integer.shortValue();
        if (sShortValue == 0) {
            return "Unknown";
        }
        if (sShortValue == 20) {
            return "Tungsten (Incandescent)";
        }
        if (sShortValue == 22) {
            return "Evening Sunlight";
        }
        if (sShortValue == 256) {
            return "One Touch White Balance";
        }
        if (sShortValue == 512) {
            return "Custom 1-4";
        }
        switch (sShortValue) {
            case 16:
                return "Shade";
            case 17:
                return "Cloudy";
            case 18:
                return "Fine Weather";
            default:
                switch (sShortValue) {
                    case 33:
                        return "Daylight Fluorescent";
                    case 34:
                        return "Day White Fluorescent";
                    case 35:
                        return "Cool White Fluorescent";
                    case 36:
                        return "White Fluorescent";
                    default:
                        return "Unknown (" + integer + ")";
                }
        }
    }
}
