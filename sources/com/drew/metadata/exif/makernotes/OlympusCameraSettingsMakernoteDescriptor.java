package com.drew.metadata.exif.makernotes;

import com.drew.lang.Rational;
import com.drew.metadata.TagDescriptor;
import java.text.DecimalFormat;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class OlympusCameraSettingsMakernoteDescriptor extends TagDescriptor<OlympusCameraSettingsMakernoteDirectory> {
    private static final HashMap<Integer, String> _filters;
    private static final HashMap<Integer, String> _toneLevelType;

    public OlympusCameraSettingsMakernoteDescriptor(OlympusCameraSettingsMakernoteDirectory olympusCameraSettingsMakernoteDirectory) {
        super(olympusCameraSettingsMakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i != 0) {
            if (i == 1280) {
                return getWhiteBalance2Description();
            }
            if (i == 1281) {
                return getWhiteBalanceTemperatureDescription();
            }
            if (i == 1312) {
                return getPictureModeDescription();
            }
            if (i == 1313) {
                return getPictureModeSaturationDescription();
            }
            if (i == 1536) {
                return getDriveModeDescription();
            }
            if (i != 1537) {
                switch (i) {
                    case 0:
                        break;
                    case 256:
                        return getPreviewImageValidDescription();
                    case 768:
                        return getMacroModeDescription();
                    case 769:
                        return getFocusModeDescription();
                    case 770:
                        return getFocusProcessDescription();
                    case 771:
                        return getAfSearchDescription();
                    case 772:
                        return getAfAreasDescription();
                    case 773:
                        return getAfPointSelectedDescription();
                    case 774:
                        return getAfFineTuneDescription();
                    case 1024:
                        return getFlashModeDescription();
                    case 1295:
                        return getGradationDescription();
                    case OlympusCameraSettingsMakernoteDirectory.TagArtFilter /* 1321 */:
                        return getArtFilterDescription();
                    case OlympusCameraSettingsMakernoteDirectory.TagColorCreatorEffect /* 1330 */:
                        return getColorCreatorEffectDescription();
                    case OlympusCameraSettingsMakernoteDirectory.TagImageQuality2 /* 1539 */:
                        return getImageQuality2Description();
                    case OlympusCameraSettingsMakernoteDirectory.TagImageStabilization /* 1540 */:
                        return getImageStabilizationDescription();
                    case OlympusCameraSettingsMakernoteDirectory.TagStackedImage /* 2052 */:
                        return getStackedImageDescription();
                    case OlympusCameraSettingsMakernoteDirectory.TagManometerPressure /* 2304 */:
                        return getManometerPressureDescription();
                    case OlympusCameraSettingsMakernoteDirectory.TagManometerReading /* 2305 */:
                        return getManometerReadingDescription();
                    case OlympusCameraSettingsMakernoteDirectory.TagExtendedWBDetect /* 2306 */:
                        return getExtendedWBDetectDescription();
                    case OlympusCameraSettingsMakernoteDirectory.TagRollAngle /* 2307 */:
                        return getRollAngleDescription();
                    case OlympusCameraSettingsMakernoteDirectory.TagPitchAngle /* 2308 */:
                        return getPitchAngleDescription();
                    case OlympusCameraSettingsMakernoteDirectory.TagDateTimeUtc /* 2312 */:
                        return getDateTimeUTCDescription();
                    default:
                        switch (i) {
                            case 512:
                                return getExposureModeDescription();
                            case 513:
                                return getAeLockDescription();
                            case 514:
                                return getMeteringModeDescription();
                            case 515:
                                return getExposureShiftDescription();
                            case 516:
                                return getNdFilterDescription();
                            default:
                                switch (i) {
                                    case 1027:
                                        return getFlashRemoteControlDescription();
                                    case 1028:
                                        return getFlashControlModeDescription();
                                    case 1029:
                                        return getFlashIntensityDescription();
                                    case 1030:
                                        return getManualFlashStrengthDescription();
                                    default:
                                        switch (i) {
                                            case 1283:
                                                return getCustomSaturationDescription();
                                            case 1284:
                                                return getModifiedSaturationDescription();
                                            case 1285:
                                                return getContrastSettingDescription();
                                            case 1286:
                                                return getSharpnessSettingDescription();
                                            case 1287:
                                                return getColorSpaceDescription();
                                            default:
                                                switch (i) {
                                                    case 1289:
                                                        return getSceneModeDescription();
                                                    case 1290:
                                                        return getNoiseReductionDescription();
                                                    case 1291:
                                                        return getDistortionCorrectionDescription();
                                                    case 1292:
                                                        return getShadingCompensationDescription();
                                                    default:
                                                        switch (i) {
                                                            case OlympusCameraSettingsMakernoteDirectory.TagPictureModeContrast /* 1315 */:
                                                                return getPictureModeContrastDescription();
                                                            case OlympusCameraSettingsMakernoteDirectory.TagPictureModeSharpness /* 1316 */:
                                                                return getPictureModeSharpnessDescription();
                                                            case OlympusCameraSettingsMakernoteDirectory.TagPictureModeBWFilter /* 1317 */:
                                                                return getPictureModeBWFilterDescription();
                                                            case OlympusCameraSettingsMakernoteDirectory.TagPictureModeTone /* 1318 */:
                                                                return getPictureModeToneDescription();
                                                            case OlympusCameraSettingsMakernoteDirectory.TagNoiseFilter /* 1319 */:
                                                                return getNoiseFilterDescription();
                                                            default:
                                                                switch (i) {
                                                                    case OlympusCameraSettingsMakernoteDirectory.TagMagicFilter /* 1324 */:
                                                                        return getMagicFilterDescription();
                                                                    case OlympusCameraSettingsMakernoteDirectory.TagPictureModeEffect /* 1325 */:
                                                                        return getPictureModeEffectDescription();
                                                                    case OlympusCameraSettingsMakernoteDirectory.TagToneLevel /* 1326 */:
                                                                        return getToneLevelDescription();
                                                                    case OlympusCameraSettingsMakernoteDirectory.TagArtFilterEffect /* 1327 */:
                                                                        return getArtFilterEffectDescription();
                                                                    default:
                                                                        return super.getDescription(i);
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
            } else {
                return getPanoramaModeDescription();
            }
        }
        return getCameraSettingsVersionDescription();
    }

    public String getCameraSettingsVersionDescription() {
        return getVersionBytesDescription(0, 4);
    }

    public String getPreviewImageValidDescription() {
        return getIndexedDescription(256, "No", "Yes");
    }

    public String getExposureModeDescription() {
        return getIndexedDescription(512, 1, "Manual", "Program", "Aperture-priority AE", "Shutter speed priority", "Program-shift");
    }

    public String getAeLockDescription() {
        return getIndexedDescription(513, "Off", "On");
    }

    public String getMeteringModeDescription() {
        Integer integer = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getInteger(514);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 2 ? iIntValue != 3 ? iIntValue != 5 ? iIntValue != 261 ? iIntValue != 515 ? iIntValue != 1027 ? "Unknown (" + integer + ")" : "Spot+Shadow control" : "Spot+Highlight control" : "Pattern+AF" : "ESP" : "Spot" : "Center-weighted average";
    }

    public String getExposureShiftDescription() {
        return getRationalOrDoubleString(515);
    }

    public String getNdFilterDescription() {
        return getIndexedDescription(516, "Off", "On");
    }

    public String getMacroModeDescription() {
        return getIndexedDescription(768, "Off", "On", "Super Macro");
    }

    public String getFocusModeDescription() {
        int[] intArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getIntArray(769);
        if (intArray == null) {
            Integer integer = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getInteger(769);
            if (integer == null) {
                return null;
            }
            intArray = new int[]{integer.intValue()};
        }
        if (intArray.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = intArray[0];
        if (i == 0) {
            sb.append("Single AF");
        } else if (i == 1) {
            sb.append("Sequential shooting AF");
        } else if (i == 2) {
            sb.append("Continuous AF");
        } else if (i == 3) {
            sb.append("Multi AF");
        } else if (i == 4) {
            sb.append("Face detect");
        } else if (i == 10) {
            sb.append("MF");
        } else {
            sb.append("Unknown (" + intArray[0] + ")");
        }
        if (intArray.length > 1) {
            sb.append("; ");
            int i2 = intArray[1];
            if (i2 == 0) {
                sb.append("(none)");
            } else {
                if ((i2 & 1) > 0) {
                    sb.append("S-AF, ");
                }
                if (((i2 >> 2) & 1) > 0) {
                    sb.append("C-AF, ");
                }
                if (((i2 >> 4) & 1) > 0) {
                    sb.append("MF, ");
                }
                if (((i2 >> 5) & 1) > 0) {
                    sb.append("Face detect, ");
                }
                if (((i2 >> 6) & 1) > 0) {
                    sb.append("Imager AF, ");
                }
                if (((i2 >> 7) & 1) > 0) {
                    sb.append("Live View Magnification Frame, ");
                }
                if (((i2 >> 8) & 1) > 0) {
                    sb.append("AF sensor, ");
                }
                sb.setLength(sb.length() - 2);
            }
        }
        return sb.toString();
    }

    public String getFocusProcessDescription() {
        int[] intArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getIntArray(770);
        if (intArray == null) {
            Integer integer = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getInteger(770);
            if (integer == null) {
                return null;
            }
            intArray = new int[]{integer.intValue()};
        }
        if (intArray.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = intArray[0];
        if (i == 0) {
            sb.append("AF not used");
        } else if (i == 1) {
            sb.append("AF used");
        } else {
            sb.append("Unknown (" + intArray[0] + ")");
        }
        if (intArray.length > 1) {
            sb.append("; " + intArray[1]);
        }
        return sb.toString();
    }

    public String getAfSearchDescription() {
        return getIndexedDescription(771, "Not Ready", "Ready");
    }

    public String getAfAreasDescription() {
        Object object = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getObject(772);
        if (object == null || !(object instanceof long[])) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (long j : (long[]) object) {
            if (j != 0) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                if (j == 913916549) {
                    sb.append("Left ");
                } else if (j == 2038007173) {
                    sb.append("Center ");
                } else if (j == 3178875269L) {
                    sb.append("Right ");
                }
                sb.append(String.format("(%d/255,%d/255)-(%d/255,%d/255)", Long.valueOf((j >> 24) & 255), Long.valueOf((j >> 16) & 255), Long.valueOf((j >> 8) & 255), Long.valueOf(j & 255)));
            }
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    public String getAfPointSelectedDescription() {
        Rational[] rationalArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getRationalArray(773);
        if (rationalArray == null) {
            return "n/a";
        }
        if (rationalArray.length < 4) {
            return null;
        }
        int i = 0;
        if (rationalArray.length == 5 && rationalArray[0].longValue() == 0) {
            i = 1;
        }
        int iDoubleValue = (int) (rationalArray[i].doubleValue() * 100.0d);
        int iDoubleValue2 = (int) (rationalArray[i + 1].doubleValue() * 100.0d);
        int iDoubleValue3 = (int) (rationalArray[i + 2].doubleValue() * 100.0d);
        int iDoubleValue4 = (int) (rationalArray[i + 3].doubleValue() * 100.0d);
        return ((iDoubleValue + iDoubleValue2) + iDoubleValue3) + iDoubleValue4 == 0 ? "n/a" : String.format("(%d%%,%d%%) (%d%%,%d%%)", Integer.valueOf(iDoubleValue), Integer.valueOf(iDoubleValue2), Integer.valueOf(iDoubleValue3), Integer.valueOf(iDoubleValue4));
    }

    public String getAfFineTuneDescription() {
        return getIndexedDescription(774, "Off", "On");
    }

    public String getFlashModeDescription() {
        Integer integer = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getInteger(1024);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() == 0) {
            return "Off";
        }
        StringBuilder sb = new StringBuilder();
        int iIntValue = integer.intValue();
        if ((iIntValue & 1) != 0) {
            sb.append("On, ");
        }
        if (((iIntValue >> 1) & 1) != 0) {
            sb.append("Fill-in, ");
        }
        if (((iIntValue >> 2) & 1) != 0) {
            sb.append("Red-eye, ");
        }
        if (((iIntValue >> 3) & 1) != 0) {
            sb.append("Slow-sync, ");
        }
        if (((iIntValue >> 4) & 1) != 0) {
            sb.append("Forced On, ");
        }
        if (((iIntValue >> 5) & 1) != 0) {
            sb.append("2nd Curtain, ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    public String getFlashRemoteControlDescription() {
        Integer integer = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getInteger(1027);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        if (iIntValue == 0) {
            return "Off";
        }
        if (iIntValue == 1) {
            return "Channel 1, Low";
        }
        if (iIntValue == 2) {
            return "Channel 2, Low";
        }
        if (iIntValue == 3) {
            return "Channel 3, Low";
        }
        if (iIntValue == 4) {
            return "Channel 4, Low";
        }
        switch (iIntValue) {
            case 9:
                return "Channel 1, Mid";
            case 10:
                return "Channel 2, Mid";
            case 11:
                return "Channel 3, Mid";
            case 12:
                return "Channel 4, Mid";
            default:
                switch (iIntValue) {
                    case 17:
                        return "Channel 1, High";
                    case 18:
                        return "Channel 2, High";
                    case 19:
                        return "Channel 3, High";
                    case 20:
                        return "Channel 4, High";
                    default:
                        return "Unknown (" + integer + ")";
                }
        }
    }

    public String getFlashControlModeDescription() {
        int[] intArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getIntArray(1028);
        if (intArray == null || intArray.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = intArray[0];
        if (i == 0) {
            sb.append("Off");
        } else if (i == 3) {
            sb.append("TTL");
        } else if (i == 4) {
            sb.append("Auto");
        } else if (i == 5) {
            sb.append("Manual");
        } else {
            sb.append("Unknown (").append(intArray[0]).append(")");
        }
        for (int i2 = 1; i2 < intArray.length; i2++) {
            sb.append("; ").append(intArray[i2]);
        }
        return sb.toString();
    }

    public String getFlashIntensityDescription() {
        Rational[] rationalArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getRationalArray(1029);
        if (rationalArray == null || rationalArray.length == 0) {
            return null;
        }
        if (rationalArray.length == 3) {
            if (rationalArray[0].getDenominator() == 0 && rationalArray[1].getDenominator() == 0 && rationalArray[2].getDenominator() == 0) {
                return "n/a";
            }
        } else if (rationalArray.length == 4 && rationalArray[0].getDenominator() == 0 && rationalArray[1].getDenominator() == 0 && rationalArray[2].getDenominator() == 0 && rationalArray[3].getDenominator() == 0) {
            return "n/a (x4)";
        }
        StringBuilder sb = new StringBuilder();
        for (Rational rational : rationalArray) {
            sb.append(rational).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    public String getManualFlashStrengthDescription() {
        Rational[] rationalArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getRationalArray(1030);
        if (rationalArray == null || rationalArray.length == 0) {
            return "n/a";
        }
        if (rationalArray.length == 3) {
            if (rationalArray[0].getDenominator() == 0 && rationalArray[1].getDenominator() == 0 && rationalArray[2].getDenominator() == 0) {
                return "n/a";
            }
        } else if (rationalArray.length == 4 && rationalArray[0].getDenominator() == 0 && rationalArray[1].getDenominator() == 0 && rationalArray[2].getDenominator() == 0 && rationalArray[3].getDenominator() == 0) {
            return "n/a (x4)";
        }
        StringBuilder sb = new StringBuilder();
        for (Rational rational : rationalArray) {
            sb.append(rational).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    public String getWhiteBalance2Description() {
        Integer integer = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getInteger(1280);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        if (iIntValue == 0) {
            return "Auto";
        }
        if (iIntValue == 1) {
            return "Auto (Keep Warm Color Off)";
        }
        if (iIntValue == 48) {
            return "3600K (Tungsten light-like)";
        }
        if (iIntValue == 67) {
            return "Underwater";
        }
        switch (iIntValue) {
            case 16:
                return "7500K (Fine Weather with Shade)";
            case 17:
                return "6000K (Cloudy)";
            case 18:
                return "5300K (Fine Weather)";
            default:
                switch (iIntValue) {
                    case 20:
                        return "3000K (Tungsten light)";
                    case 21:
                        return "3600K (Tungsten light-like)";
                    case 22:
                        return "Auto Setup";
                    case 23:
                        return "5500K (Flash)";
                    default:
                        switch (iIntValue) {
                            case 33:
                                return "6600K (Daylight fluorescent)";
                            case 34:
                                return "4500K (Neutral white fluorescent)";
                            case 35:
                                return "4000K (Cool white fluorescent)";
                            case 36:
                                return "White Fluorescent";
                            default:
                                switch (iIntValue) {
                                    case 256:
                                        return "One Touch WB 1";
                                    case 257:
                                        return "One Touch WB 2";
                                    case 258:
                                        return "One Touch WB 3";
                                    case 259:
                                        return "One Touch WB 4";
                                    default:
                                        switch (iIntValue) {
                                            case 512:
                                                return "Custom WB 1";
                                            case 513:
                                                return "Custom WB 2";
                                            case 514:
                                                return "Custom WB 3";
                                            case 515:
                                                return "Custom WB 4";
                                            default:
                                                return "Unknown (" + integer + ")";
                                        }
                                }
                        }
                }
        }
    }

    public String getWhiteBalanceTemperatureDescription() {
        Integer integer = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getInteger(1281);
        if (integer == null) {
            return null;
        }
        return integer.intValue() == 0 ? "Auto" : integer.toString();
    }

    public String getCustomSaturationDescription() {
        return getValueMinMaxDescription(1283);
    }

    public String getModifiedSaturationDescription() {
        return getIndexedDescription(1284, "Off", "CM1 (Red Enhance)", "CM2 (Green Enhance)", "CM3 (Blue Enhance)", "CM4 (Skin Tones)");
    }

    public String getContrastSettingDescription() {
        return getValueMinMaxDescription(1285);
    }

    public String getSharpnessSettingDescription() {
        return getValueMinMaxDescription(1286);
    }

    public String getColorSpaceDescription() {
        return getIndexedDescription(1287, "sRGB", "Adobe RGB", "Pro Photo RGB");
    }

    public String getSceneModeDescription() {
        Integer integer = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getInteger(1289);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        if (iIntValue == 0) {
            return "Standard";
        }
        if (iIntValue == 54) {
            return "Face Portrait";
        }
        if (iIntValue == 57) {
            return "Bulb";
        }
        if (iIntValue == 142) {
            return "Hand-held Starlight";
        }
        if (iIntValue == 154) {
            return "HDR";
        }
        if (iIntValue == 59) {
            return "Smile Shot";
        }
        if (iIntValue == 60) {
            return "Quick Shutter";
        }
        switch (iIntValue) {
            case 6:
                return "Auto";
            case 7:
                return "Sport";
            case 8:
                return "Portrait";
            case 9:
                return "Landscape+Portrait";
            case 10:
                return "Landscape";
            case 11:
                return "Night Scene";
            case 12:
                return "Self Portrait";
            case 13:
                return "Panorama";
            case 14:
                return "2 in 1";
            case 15:
                return "Movie";
            case 16:
                return "Landscape+Portrait";
            case 17:
                return "Night+Portrait";
            case 18:
                return "Indoor";
            case 19:
                return "Fireworks";
            case 20:
                return "Sunset";
            case 21:
                return "Beauty Skin";
            case 22:
                return "Macro";
            case 23:
                return "Super Macro";
            case 24:
                return "Food";
            case 25:
                return "Documents";
            case 26:
                return "Museum";
            case 27:
                return "Shoot & Select";
            case 28:
                return "Beach & Snow";
            case 29:
                return "Self Portrait+Timer";
            case 30:
                return "Candle";
            case 31:
                return "Available Light";
            case 32:
                return "Behind Glass";
            case 33:
                return "My Mode";
            case 34:
                return "Pet";
            case 35:
                return "Underwater Wide1";
            case 36:
                return "Underwater Macro";
            case 37:
                return "Shoot & Select1";
            case 38:
                return "Shoot & Select2";
            case 39:
                return "High Key";
            case 40:
                return "Digital Image Stabilization";
            case 41:
                return "Auction";
            case 42:
                return "Beach";
            case 43:
                return "Snow";
            case 44:
                return "Underwater Wide2";
            case 45:
                return "Low Key";
            case 46:
                return "Children";
            case 47:
                return "Vivid";
            case 48:
                return "Nature Macro";
            case 49:
                return "Underwater Snapshot";
            case 50:
                return "Shooting Guide";
            default:
                switch (iIntValue) {
                    case 63:
                        return "Slow Shutter";
                    case 64:
                        return "Bird Watching";
                    case 65:
                        return "Multiple Exposure";
                    case 66:
                        return "e-Portrait";
                    case 67:
                        return "Soft Background Shot";
                    default:
                        return "Unknown (" + integer + ")";
                }
        }
    }

    public String getNoiseReductionDescription() {
        Integer integer = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getInteger(1290);
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
            sb.append("Auto, ");
        }
        return sb.length() != 0 ? sb.substring(0, sb.length() - 2) : "(none)";
    }

    public String getDistortionCorrectionDescription() {
        return getIndexedDescription(1291, "Off", "On");
    }

    public String getShadingCompensationDescription() {
        return getIndexedDescription(1292, "Off", "On");
    }

    public String getGradationDescription() {
        String str;
        int[] intArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getIntArray(1295);
        if (intArray == null || intArray.length < 3) {
            return null;
        }
        String str2 = String.format("%d %d %d", Integer.valueOf(intArray[0]), Integer.valueOf(intArray[1]), Integer.valueOf(intArray[2]));
        if (str2.equals("0 0 0")) {
            str = "n/a";
        } else if (str2.equals("-1 -1 1")) {
            str = "Low Key";
        } else if (str2.equals("0 -1 1")) {
            str = "Normal";
        } else {
            str = str2.equals("1 -1 1") ? "High Key" : "Unknown (" + str2 + ")";
        }
        if (intArray.length <= 3) {
            return str;
        }
        int i = intArray[3];
        if (i == 0) {
            return str + "; User-Selected";
        }
        return i == 1 ? str + "; Auto-Override" : str;
    }

    public String getPictureModeDescription() {
        int[] intArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getIntArray(1312);
        if (intArray == null) {
            Integer integer = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getInteger(1290);
            if (integer == null) {
                return null;
            }
            intArray = new int[]{integer.intValue()};
        }
        if (intArray.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = intArray[0];
        if (i == 1) {
            sb.append("Vivid");
        } else if (i == 2) {
            sb.append("Natural");
        } else if (i == 3) {
            sb.append("Muted");
        } else if (i == 4) {
            sb.append("Portrait");
        } else if (i == 5) {
            sb.append("i-Enhance");
        } else if (i == 256) {
            sb.append("Monotone");
        } else if (i == 512) {
            sb.append("Sepia");
        } else {
            sb.append("Unknown (").append(intArray[0]).append(")");
        }
        if (intArray.length > 1) {
            sb.append("; ").append(intArray[1]);
        }
        return sb.toString();
    }

    public String getPictureModeSaturationDescription() {
        return getValueMinMaxDescription(OlympusCameraSettingsMakernoteDirectory.TagPictureModeSaturation);
    }

    public String getPictureModeContrastDescription() {
        return getValueMinMaxDescription(OlympusCameraSettingsMakernoteDirectory.TagPictureModeContrast);
    }

    public String getPictureModeSharpnessDescription() {
        return getValueMinMaxDescription(OlympusCameraSettingsMakernoteDirectory.TagPictureModeSharpness);
    }

    public String getPictureModeBWFilterDescription() {
        return getIndexedDescription(OlympusCameraSettingsMakernoteDirectory.TagPictureModeBWFilter, "n/a", "Neutral", "Yellow", "Orange", "Red", "Green");
    }

    public String getPictureModeToneDescription() {
        return getIndexedDescription(OlympusCameraSettingsMakernoteDirectory.TagPictureModeTone, "n/a", "Neutral", "Sepia", "Blue", "Purple", "Green");
    }

    public String getNoiseFilterDescription() {
        int[] intArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getIntArray(OlympusCameraSettingsMakernoteDirectory.TagNoiseFilter);
        if (intArray == null) {
            return null;
        }
        String str = String.format("%d %d %d", Integer.valueOf(intArray[0]), Integer.valueOf(intArray[1]), Integer.valueOf(intArray[2]));
        return str.equals("0 0 0") ? "n/a" : str.equals("-2 -2 1") ? "Off" : str.equals("-1 -2 1") ? "Low" : str.equals("0 -2 1") ? "Standard" : str.equals("1 -2 1") ? "High" : "Unknown (" + str + ")";
    }

    public String getArtFilterDescription() {
        return getFiltersDescription(OlympusCameraSettingsMakernoteDirectory.TagArtFilter);
    }

    public String getMagicFilterDescription() {
        return getFiltersDescription(OlympusCameraSettingsMakernoteDirectory.TagMagicFilter);
    }

    public String getPictureModeEffectDescription() {
        int[] intArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getIntArray(OlympusCameraSettingsMakernoteDirectory.TagPictureModeEffect);
        if (intArray == null) {
            return null;
        }
        String str = String.format("%d %d %d", Integer.valueOf(intArray[0]), Integer.valueOf(intArray[1]), Integer.valueOf(intArray[2]));
        return str.equals("0 0 0") ? "n/a" : str.equals("-1 -1 1") ? "Low" : str.equals("0 -1 1") ? "Standard" : str.equals("1 -1 1") ? "High" : "Unknown (" + str + ")";
    }

    public String getToneLevelDescription() {
        int[] intArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getIntArray(OlympusCameraSettingsMakernoteDirectory.TagToneLevel);
        if (intArray == null || intArray.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intArray.length; i++) {
            if (i == 0 || i == 4 || i == 8 || i == 12 || i == 16 || i == 20 || i == 24) {
                sb.append(_toneLevelType.get(Integer.valueOf(intArray[i]))).append("; ");
            } else {
                sb.append(intArray[i]).append("; ");
            }
        }
        return sb.substring(0, sb.length() - 2);
    }

    public String getArtFilterEffectDescription() {
        int[] intArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getIntArray(OlympusCameraSettingsMakernoteDirectory.TagArtFilterEffect);
        if (intArray == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intArray.length; i++) {
            if (i == 0) {
                HashMap<Integer, String> map = _filters;
                sb.append(map.containsKey(Integer.valueOf(intArray[i])) ? map.get(Integer.valueOf(intArray[i])) : "[unknown]").append("; ");
            } else if (i == 3) {
                sb.append("Partial Color ").append(intArray[i]).append("; ");
            } else if (i == 4) {
                switch (intArray[i]) {
                    case 0:
                        sb.append("No Effect");
                        break;
                    case PanasonicMakernoteDirectory.TAG_BABY_AGE_1 /* 32784 */:
                        sb.append("Star Light");
                        break;
                    case 32800:
                        sb.append("Pin Hole");
                        break;
                    case 32816:
                        sb.append("Frame");
                        break;
                    case 32832:
                        sb.append("Soft Focus");
                        break;
                    case 32848:
                        sb.append("White Edge");
                        break;
                    case 32864:
                        sb.append("B&W");
                        break;
                    default:
                        sb.append("Unknown (").append(intArray[i]).append(")");
                        break;
                }
                sb.append("; ");
            } else if (i == 6) {
                int i2 = intArray[i];
                if (i2 == 0) {
                    sb.append("No Color Filter");
                } else if (i2 == 1) {
                    sb.append("Yellow Color Filter");
                } else if (i2 == 2) {
                    sb.append("Orange Color Filter");
                } else if (i2 == 3) {
                    sb.append("Red Color Filter");
                } else if (i2 == 4) {
                    sb.append("Green Color Filter");
                } else {
                    sb.append("Unknown (").append(intArray[i]).append(")");
                }
                sb.append("; ");
            } else {
                sb.append(intArray[i]).append("; ");
            }
        }
        return sb.substring(0, sb.length() - 2);
    }

    public String getColorCreatorEffectDescription() {
        int[] intArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getIntArray(OlympusCameraSettingsMakernoteDirectory.TagColorCreatorEffect);
        if (intArray == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intArray.length; i++) {
            if (i == 0) {
                sb.append("Color ").append(intArray[i]).append("; ");
            } else if (i == 3) {
                sb.append("Strength ").append(intArray[i]).append("; ");
            } else {
                sb.append(intArray[i]).append("; ");
            }
        }
        return sb.substring(0, sb.length() - 2);
    }

    public String getDriveModeDescription() {
        int[] intArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getIntArray(1536);
        if (intArray == null) {
            return null;
        }
        if (intArray.length == 0 || intArray[0] == 0) {
            return "Single Shot";
        }
        StringBuilder sb = new StringBuilder();
        int i = intArray[0];
        if (i == 5 && intArray.length >= 3) {
            int i2 = intArray[2];
            if ((i2 & 1) > 0) {
                sb.append("AE");
            }
            if (((i2 >> 1) & 1) > 0) {
                sb.append("WB");
            }
            if (((i2 >> 2) & 1) > 0) {
                sb.append("FL");
            }
            if (((i2 >> 3) & 1) > 0) {
                sb.append("MF");
            }
            if (((i2 >> 6) & 1) > 0) {
                sb.append("Focus");
            }
            sb.append(" Bracketing");
        } else if (i == 1) {
            sb.append("Continuous Shooting");
        } else if (i == 2) {
            sb.append("Exposure Bracketing");
        } else if (i == 3) {
            sb.append("White Balance Bracketing");
        } else if (i == 4) {
            sb.append("Exposure+WB Bracketing");
        } else {
            sb.append("Unknown (").append(intArray[0]).append(")");
        }
        sb.append(", Shot ").append(intArray[1]);
        return sb.toString();
    }

    public String getPanoramaModeDescription() {
        int i;
        String str;
        int[] intArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getIntArray(1537);
        if (intArray == null) {
            return null;
        }
        if (intArray.length == 0 || (i = intArray[0]) == 0) {
            return "Off";
        }
        if (i == 1) {
            str = "Left to Right";
        } else if (i == 2) {
            str = "Right to Left";
        } else if (i != 3) {
            str = i != 4 ? "Unknown (" + intArray[0] + ")" : "Top to Bottom";
        } else {
            str = "Bottom to Top";
        }
        return String.format("%s, Shot %d", str, Integer.valueOf(intArray[1]));
    }

    public String getImageQuality2Description() {
        return getIndexedDescription(OlympusCameraSettingsMakernoteDirectory.TagImageQuality2, 1, "SQ", "HQ", "SHQ", "RAW", "SQ (5)");
    }

    public String getImageStabilizationDescription() {
        return getIndexedDescription(OlympusCameraSettingsMakernoteDirectory.TagImageStabilization, "Off", "On, Mode 1", "On, Mode 2", "On, Mode 3", "On, Mode 4");
    }

    public String getStackedImageDescription() {
        int[] intArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getIntArray(OlympusCameraSettingsMakernoteDirectory.TagStackedImage);
        if (intArray == null || intArray.length < 2) {
            return null;
        }
        int i = intArray[0];
        int i2 = intArray[1];
        return (i == 0 && i2 == 0) ? "No" : (i == 9 && i2 == 8) ? "Focus-stacked (8 images)" : String.format("Unknown (%d %d)", Integer.valueOf(i), Integer.valueOf(i2));
    }

    public String getManometerPressureDescription() {
        if (((OlympusCameraSettingsMakernoteDirectory) this._directory).getInteger(OlympusCameraSettingsMakernoteDirectory.TagManometerPressure) == null) {
            return null;
        }
        return String.format("%s kPa", new DecimalFormat("#.##").format(r0.intValue() / 10.0d));
    }

    public String getManometerReadingDescription() {
        int[] intArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getIntArray(OlympusCameraSettingsMakernoteDirectory.TagManometerReading);
        if (intArray == null || intArray.length < 2) {
            return null;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return String.format("%s m, %s ft", decimalFormat.format(intArray[0] / 10.0d), decimalFormat.format(intArray[1] / 10.0d));
    }

    public String getExtendedWBDetectDescription() {
        return getIndexedDescription(OlympusCameraSettingsMakernoteDirectory.TagExtendedWBDetect, "Off", "On");
    }

    public String getRollAngleDescription() {
        int[] intArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getIntArray(OlympusCameraSettingsMakernoteDirectory.TagRollAngle);
        if (intArray == null || intArray.length < 2) {
            return null;
        }
        return String.format("%s %d", intArray[0] != 0 ? Double.toString((-r1) / 10.0d) : "n/a", Integer.valueOf(intArray[1]));
    }

    public String getPitchAngleDescription() {
        int[] intArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getIntArray(OlympusCameraSettingsMakernoteDirectory.TagPitchAngle);
        if (intArray == null || intArray.length < 2) {
            return null;
        }
        int i = intArray[0];
        return String.format("%s %d", i != 0 ? Double.toString(i / 10.0d) : "n/a", Integer.valueOf(intArray[1]));
    }

    public String getDateTimeUTCDescription() {
        Object object = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getObject(OlympusCameraSettingsMakernoteDirectory.TagDateTimeUtc);
        if (object == null) {
            return null;
        }
        return object.toString();
    }

    private String getValueMinMaxDescription(int i) {
        int[] intArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getIntArray(i);
        if (intArray == null || intArray.length < 3) {
            return null;
        }
        return String.format("%d (min %d, max %d)", Integer.valueOf(intArray[0]), Integer.valueOf(intArray[1]), Integer.valueOf(intArray[2]));
    }

    private String getFiltersDescription(int i) {
        int[] intArray = ((OlympusCameraSettingsMakernoteDirectory) this._directory).getIntArray(i);
        if (intArray == null || intArray.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < intArray.length; i2++) {
            if (i2 == 0) {
                HashMap<Integer, String> map = _filters;
                sb.append(map.containsKey(Integer.valueOf(intArray[i2])) ? map.get(Integer.valueOf(intArray[i2])) : "[unknown]");
            } else {
                sb.append(intArray[i2]);
            }
            sb.append("; ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _toneLevelType = map;
        HashMap<Integer, String> map2 = new HashMap<>();
        _filters = map2;
        map2.put(0, "Off");
        map2.put(1, "Soft Focus");
        map2.put(2, "Pop Art");
        map2.put(3, "Pale & Light Color");
        map2.put(4, "Light Tone");
        map2.put(5, "Pin Hole");
        map2.put(6, "Grainy Film");
        map2.put(9, "Diorama");
        map2.put(10, "Cross Process");
        map2.put(12, "Fish Eye");
        map2.put(13, "Drawing");
        map2.put(14, "Gentle Sepia");
        map2.put(15, "Pale & Light Color II");
        map2.put(16, "Pop Art II");
        map2.put(17, "Pin Hole II");
        map2.put(18, "Pin Hole III");
        map2.put(19, "Grainy Film II");
        map2.put(20, "Dramatic Tone");
        map2.put(21, "Punk");
        map2.put(22, "Soft Focus 2");
        map2.put(23, "Sparkle");
        map2.put(24, "Watercolor");
        map2.put(25, "Key Line");
        map2.put(26, "Key Line II");
        map2.put(27, "Miniature");
        map2.put(28, "Reflection");
        map2.put(29, "Fragmented");
        map2.put(31, "Cross Process II");
        map2.put(32, "Dramatic Tone II");
        map2.put(33, "Watercolor I");
        map2.put(34, "Watercolor II");
        map2.put(35, "Diorama II");
        map2.put(36, "Vintage");
        map2.put(37, "Vintage II");
        map2.put(38, "Vintage III");
        map2.put(39, "Partial Color");
        map2.put(40, "Partial Color II");
        map2.put(41, "Partial Color III");
        map.put(0, "0");
        map.put(-31999, "Highlights ");
        map.put(-31998, "Shadows ");
        map.put(-31997, "Midtones ");
    }
}
