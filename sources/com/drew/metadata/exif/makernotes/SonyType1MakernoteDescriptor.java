package com.drew.metadata.exif.makernotes;

import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.TagDescriptor;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.oblador.keychain.KeychainModule;

/* loaded from: classes5.dex */
public class SonyType1MakernoteDescriptor extends TagDescriptor<SonyType1MakernoteDirectory> {
    public SonyType1MakernoteDescriptor(SonyType1MakernoteDirectory sonyType1MakernoteDirectory) {
        super(sonyType1MakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        switch (i) {
            case 258:
                return getImageQualityDescription();
            case 260:
                return getFlashExposureCompensationDescription();
            case 261:
                return getTeleconverterDescription();
            case 277:
                return getWhiteBalanceDescription();
            case SonyType1MakernoteDirectory.TAG_HIGH_ISO_NOISE_REDUCTION /* 8201 */:
                return getHighIsoNoiseReductionDescription();
            case SonyType1MakernoteDirectory.TAG_PICTURE_EFFECT /* 8206 */:
                return getPictureEffectDescription();
            case SonyType1MakernoteDirectory.TAG_SOFT_SKIN_EFFECT /* 8207 */:
                return getSoftSkinEffectDescription();
            case 8209:
                return getVignettingCorrectionDescription();
            case 8210:
                return getLateralChromaticAberrationDescription();
            case SonyType1MakernoteDirectory.TAG_DISTORTION_CORRECTION /* 8211 */:
                return getDistortionCorrectionDescription();
            case SonyType1MakernoteDirectory.TAG_AUTO_PORTRAIT_FRAMED /* 8214 */:
                return getAutoPortraitFramedDescription();
            case SonyType1MakernoteDirectory.TAG_FOCUS_MODE /* 8219 */:
                return getFocusModeDescription();
            case SonyType1MakernoteDirectory.TAG_AF_POINT_SELECTED /* 8222 */:
                return getAFPointSelectedDescription();
            case SonyType1MakernoteDirectory.TAG_SONY_MODEL_ID /* 45057 */:
                return getSonyModelIdDescription();
            case SonyType1MakernoteDirectory.TAG_COLOR_TEMPERATURE /* 45089 */:
                return getColorTemperatureDescription();
            case SonyType1MakernoteDirectory.TAG_SCENE_MODE /* 45091 */:
                return getSceneModeDescription();
            case SonyType1MakernoteDirectory.TAG_ZONE_MATCHING /* 45092 */:
                return getZoneMatchingDescription();
            case SonyType1MakernoteDirectory.TAG_DYNAMIC_RANGE_OPTIMISER /* 45093 */:
                return getDynamicRangeOptimizerDescription();
            case SonyType1MakernoteDirectory.TAG_IMAGE_STABILISATION /* 45094 */:
                return getImageStabilizationDescription();
            case SonyType1MakernoteDirectory.TAG_COLOR_MODE /* 45097 */:
                return getColorModeDescription();
            case SonyType1MakernoteDirectory.TAG_MACRO /* 45120 */:
                return getMacroDescription();
            case SonyType1MakernoteDirectory.TAG_EXPOSURE_MODE /* 45121 */:
                return getExposureModeDescription();
            case SonyType1MakernoteDirectory.TAG_AF_MODE /* 45123 */:
                return getAFModeDescription();
            case SonyType1MakernoteDirectory.TAG_AF_ILLUMINATOR /* 45124 */:
                return getAFIlluminatorDescription();
            case SonyType1MakernoteDirectory.TAG_JPEG_QUALITY /* 45127 */:
                return getJpegQualityDescription();
            case SonyType1MakernoteDirectory.TAG_FLASH_LEVEL /* 45128 */:
                return getFlashLevelDescription();
            case SonyType1MakernoteDirectory.TAG_RELEASE_MODE /* 45129 */:
                return getReleaseModeDescription();
            case SonyType1MakernoteDirectory.TAG_SEQUENCE_NUMBER /* 45130 */:
                return getSequenceNumberDescription();
            case SonyType1MakernoteDirectory.TAG_ANTI_BLUR /* 45131 */:
                return getAntiBlurDescription();
            case SonyType1MakernoteDirectory.TAG_LONG_EXPOSURE_NOISE_REDUCTION_OR_FOCUS_MODE /* 45134 */:
                return getLongExposureNoiseReductionDescription();
            default:
                return super.getDescription(i);
        }
    }

    public String getImageQualityDescription() {
        return getIndexedDescription(258, "RAW", "Super Fine", "Fine", "Standard", "Economy", "Extra Fine", "RAW + JPEG", "Compressed RAW", "Compressed RAW + JPEG");
    }

    public String getFlashExposureCompensationDescription() {
        return getFormattedInt(260, "%d EV");
    }

    public String getTeleconverterDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(261);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 72 ? iIntValue != 80 ? iIntValue != 96 ? iIntValue != 136 ? iIntValue != 144 ? iIntValue != 160 ? "Unknown (" + integer + ")" : "Minolta AF 1.4x APO" : "Minolta AF 1.4x APO II" : "Minolta/Sony AF 1.4x APO (D)" : "Minolta AF 2x APO" : "Minolta AF 2x APO II" : "Minolta/Sony AF 2x APO (D)" : KeychainModule.AccessControl.NONE;
    }

    public String getWhiteBalanceDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(277);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 1 ? iIntValue != 16 ? iIntValue != 32 ? iIntValue != 48 ? iIntValue != 64 ? iIntValue != 80 ? iIntValue != 96 ? iIntValue != 112 ? "Unknown (" + integer + ")" : "Custom" : "Fluorescent" : ExifInterface.TAG_FLASH : "Tungsten" : "Shade" : "Cloudy" : "Daylight" : "Color Temperature/Color Filter" : "Auto";
    }

    public String getColorTemperatureDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(SonyType1MakernoteDirectory.TAG_COLOR_TEMPERATURE);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() == 0) {
            return "Auto";
        }
        return String.format("%d K", Integer.valueOf(((integer.intValue() & ViewCompat.MEASURED_STATE_MASK) >> 24) | ((integer.intValue() & 16711680) >> 8)));
    }

    public String getZoneMatchingDescription() {
        return getIndexedDescription(SonyType1MakernoteDirectory.TAG_ZONE_MATCHING, "ISO Setting Used", "High Key", "Low Key");
    }

    public String getDynamicRangeOptimizerDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(SonyType1MakernoteDirectory.TAG_DYNAMIC_RANGE_OPTIMISER);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        if (iIntValue == 0) {
            return "Off";
        }
        if (iIntValue == 1) {
            return "Standard";
        }
        if (iIntValue == 2) {
            return "Advanced Auto";
        }
        if (iIntValue == 3) {
            return "Auto";
        }
        switch (iIntValue) {
            case 8:
                return "Advanced LV1";
            case 9:
                return "Advanced LV2";
            case 10:
                return "Advanced LV3";
            case 11:
                return "Advanced LV4";
            case 12:
                return "Advanced LV5";
            default:
                switch (iIntValue) {
                    case 16:
                        return "LV1";
                    case 17:
                        return "LV2";
                    case 18:
                        return "LV3";
                    case 19:
                        return "LV4";
                    case 20:
                        return "LV5";
                    default:
                        return String.format("Unknown (%d)", integer);
                }
        }
    }

    public String getImageStabilizationDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(SonyType1MakernoteDirectory.TAG_IMAGE_STABILISATION);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 1 ? "N/A" : "On" : "Off";
    }

    public String getColorModeDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(SonyType1MakernoteDirectory.TAG_COLOR_MODE);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        switch (iIntValue) {
            case 0:
                return "Standard";
            case 1:
                return "Vivid";
            case 2:
                return "Portrait";
            case 3:
                return "Landscape";
            case 4:
                return "Sunset";
            case 5:
                return "Night Portrait";
            case 6:
                return "Black & White";
            case 7:
                return "Adobe RGB";
            default:
                switch (iIntValue) {
                    case 12:
                        return "Neutral";
                    case 13:
                        return "Clear";
                    case 14:
                        return "Deep";
                    case 15:
                        return "Light";
                    case 16:
                        return "Autumn";
                    case 17:
                        return "Sepia";
                    default:
                        switch (iIntValue) {
                            case 100:
                                return "Neutral";
                            case 101:
                                return "Clear";
                            case 102:
                                return "Deep";
                            case 103:
                                return "Light";
                            case 104:
                                return "Night View";
                            case 105:
                                return "Autumn Leaves";
                            default:
                                return String.format("Unknown (%d)", integer);
                        }
                }
        }
    }

    public String getMacroDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(SonyType1MakernoteDirectory.TAG_MACRO);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 1 ? iIntValue != 2 ? iIntValue != 65535 ? String.format("Unknown (%d)", integer) : "N/A" : "Magnifying Glass/Super Macro" : "On" : "Off";
    }

    public String getExposureModeDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(SonyType1MakernoteDirectory.TAG_EXPOSURE_MODE);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        if (iIntValue == 29) {
            return "Underwater";
        }
        if (iIntValue == 65535) {
            return "N/A";
        }
        switch (iIntValue) {
            case 0:
                return "Program";
            case 1:
                return "Portrait";
            case 2:
                return "Beach";
            case 3:
                return "Sports";
            case 4:
                return "Snow";
            case 5:
                return "Landscape";
            case 6:
                return "Auto";
            case 7:
                return "Aperture Priority";
            case 8:
                return "Shutter Priority";
            case 9:
                return "Night Scene / Twilight";
            case 10:
                return "Hi-Speed Shutter";
            case 11:
                return "Twilight Portrait";
            case 12:
                return "Soft Snap/Portrait";
            case 13:
                return "Fireworks";
            case 14:
                return "Smile Shutter";
            case 15:
                return "Manual";
            default:
                switch (iIntValue) {
                    case 18:
                        return "High Sensitivity";
                    case 19:
                        return "Macro";
                    case 20:
                        return "Advanced Sports Shooting";
                    default:
                        switch (iIntValue) {
                            case 33:
                                return "Food";
                            case 34:
                                return "Panorama";
                            case 35:
                                return "Handheld Night Shot";
                            case 36:
                                return "Anti Motion Blur";
                            case 37:
                                return "Pet";
                            case 38:
                                return "Backlight Correction HDR";
                            case 39:
                                return "Superior Auto";
                            case 40:
                                return "Background Defocus";
                            case 41:
                                return "Soft Skin";
                            case 42:
                                return "3D Image";
                            default:
                                return String.format("Unknown (%d)", integer);
                        }
                }
        }
    }

    public String getJpegQualityDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(SonyType1MakernoteDirectory.TAG_JPEG_QUALITY);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 1 ? iIntValue != 2 ? iIntValue != 65535 ? String.format("Unknown (%d)", integer) : "N/A" : "Extra Fine" : "Fine" : "Normal";
    }

    public String getAntiBlurDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(SonyType1MakernoteDirectory.TAG_ANTI_BLUR);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 1 ? iIntValue != 2 ? iIntValue != 65535 ? String.format("Unknown (%d)", integer) : "N/A" : "On (Shooting)" : "On (Continuous)" : "Off";
    }

    public String getLongExposureNoiseReductionDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(SonyType1MakernoteDirectory.TAG_LONG_EXPOSURE_NOISE_REDUCTION_OR_FOCUS_MODE);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 1 ? iIntValue != 65535 ? String.format("Unknown (%d)", integer) : "N/A" : "On" : "Off";
    }

    public String getHighIsoNoiseReductionDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(SonyType1MakernoteDirectory.TAG_HIGH_ISO_NOISE_REDUCTION);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 1 ? iIntValue != 2 ? iIntValue != 3 ? iIntValue != 256 ? iIntValue != 65535 ? String.format("Unknown (%d)", integer) : "N/A" : "Auto" : "High" : "Normal" : "On" : "Off";
    }

    public String getPictureEffectDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(SonyType1MakernoteDirectory.TAG_PICTURE_EFFECT);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        if (iIntValue == 13) {
            return "High Contrast Monochrome";
        }
        if (iIntValue == 80) {
            return "Rich-tone Monochrome";
        }
        if (iIntValue == 97) {
            return "Water Color";
        }
        if (iIntValue == 98) {
            return "Water Color 2";
        }
        switch (iIntValue) {
            case 0:
                return "Off";
            case 1:
                return "Toy Camera";
            case 2:
                return "Pop Color";
            case 3:
                return "Posterization";
            case 4:
                return "Posterization B/W";
            case 5:
                return "Retro Photo";
            case 6:
                return "Soft High Key";
            case 7:
                return "Partial Color (red)";
            case 8:
                return "Partial Color (green)";
            case 9:
                return "Partial Color (blue)";
            case 10:
                return "Partial Color (yellow)";
            default:
                switch (iIntValue) {
                    case 16:
                        return "Toy Camera (normal)";
                    case 17:
                        return "Toy Camera (cool)";
                    case 18:
                        return "Toy Camera (warm)";
                    case 19:
                        return "Toy Camera (green)";
                    case 20:
                        return "Toy Camera (magenta)";
                    default:
                        switch (iIntValue) {
                            case 32:
                                return "Soft Focus (low)";
                            case 33:
                                return "Soft Focus";
                            case 34:
                                return "Soft Focus (high)";
                            default:
                                switch (iIntValue) {
                                    case 48:
                                        return "Miniature (auto)";
                                    case 49:
                                        return "Miniature (top)";
                                    case 50:
                                        return "Miniature (middle horizontal)";
                                    case 51:
                                        return "Miniature (bottom)";
                                    case 52:
                                        return "Miniature (left)";
                                    case 53:
                                        return "Miniature (middle vertical)";
                                    case 54:
                                        return "Miniature (right)";
                                    default:
                                        switch (iIntValue) {
                                            case 64:
                                                return "HDR Painting (low)";
                                            case 65:
                                                return "HDR Painting";
                                            case 66:
                                                return "HDR Painting (high)";
                                            default:
                                                switch (iIntValue) {
                                                    case 112:
                                                        return "Illustration (low)";
                                                    case 113:
                                                        return "Illustration";
                                                    case 114:
                                                        return "Illustration (high)";
                                                    default:
                                                        return String.format("Unknown (%d)", integer);
                                                }
                                        }
                                }
                        }
                }
        }
    }

    public String getSoftSkinEffectDescription() {
        return getIndexedDescription(SonyType1MakernoteDirectory.TAG_SOFT_SKIN_EFFECT, "Off", "Low", "Mid", "High");
    }

    public String getVignettingCorrectionDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(8209);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != -1 ? iIntValue != 0 ? iIntValue != 2 ? String.format("Unknown (%d)", integer) : "Auto" : "Off" : "N/A";
    }

    public String getLateralChromaticAberrationDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(8210);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != -1 ? iIntValue != 0 ? iIntValue != 2 ? String.format("Unknown (%d)", integer) : "Auto" : "Off" : "N/A";
    }

    public String getDistortionCorrectionDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(SonyType1MakernoteDirectory.TAG_DISTORTION_CORRECTION);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != -1 ? iIntValue != 0 ? iIntValue != 2 ? String.format("Unknown (%d)", integer) : "Auto" : "Off" : "N/A";
    }

    public String getAutoPortraitFramedDescription() {
        return getIndexedDescription(SonyType1MakernoteDirectory.TAG_AUTO_PORTRAIT_FRAMED, "No", "Yes");
    }

    public String getFocusModeDescription() {
        return getIndexedDescription(SonyType1MakernoteDirectory.TAG_FOCUS_MODE, "Manual", null, "AF-A", "AF-C", "AF-S", null, "DMF", "AF-D");
    }

    public String getAFPointSelectedDescription() {
        return getIndexedDescription(SonyType1MakernoteDirectory.TAG_AF_POINT_SELECTED, "Auto", "Center", "Top", "Upper-right", "Right", "Lower-right", "Bottom", "Lower-left", "Left", "Upper-left\t  \t", "Far Right", "Far Left", "Upper-middle", "Near Right", "Lower-middle", "Near Left", "Upper Far Right", "Lower Far Right", "Lower Far Left", "Upper Far Left");
    }

    public String getSonyModelIdDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(SonyType1MakernoteDirectory.TAG_SONY_MODEL_ID);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        if (iIntValue == 2) {
            return "DSC-R1";
        }
        if (iIntValue == 269) {
            return "DSLR-A850";
        }
        if (iIntValue == 270) {
            return "DSLR-A850 (APS-C mode)";
        }
        switch (iIntValue) {
            case 256:
                return "DSLR-A100";
            case 257:
                return "DSLR-A900";
            case 258:
                return "DSLR-A700";
            case 259:
                return "DSLR-A200";
            case 260:
                return "DSLR-A350";
            case 261:
                return "DSLR-A300";
            case 262:
                return "DSLR-A900 (APS-C mode)";
            case 263:
                return "DSLR-A380/A390";
            case 264:
                return "DSLR-A330";
            case 265:
                return "DSLR-A230";
            case 266:
                return "DSLR-A290";
            default:
                switch (iIntValue) {
                    case 273:
                        return "DSLR-A550";
                    case 274:
                        return "DSLR-A500";
                    case 275:
                        return "DSLR-A450";
                    default:
                        switch (iIntValue) {
                            case 278:
                                return "NEX-5";
                            case 279:
                                return "NEX-3";
                            case 280:
                                return "SLT-A33";
                            case 281:
                                return "SLT-A55V";
                            case 282:
                                return "DSLR-A560";
                            case 283:
                                return "DSLR-A580";
                            case 284:
                                return "NEX-C3";
                            case 285:
                                return "SLT-A35";
                            case 286:
                                return "SLT-A65V";
                            case OlympusImageProcessingMakernoteDirectory.TagWbGLevel /* 287 */:
                                return "SLT-A77V";
                            case 288:
                                return "NEX-5N";
                            case 289:
                                return "NEX-7";
                            case OlympusRawInfoMakernoteDirectory.TagWbRbLevelsFineWeather /* 290 */:
                                return "NEX-VG20E";
                            case 291:
                                return "SLT-A37";
                            case OlympusRawInfoMakernoteDirectory.TagWbRbLevelsEveningSunlight /* 292 */:
                                return "SLT-A57";
                            case 293:
                                return "NEX-F3";
                            case 294:
                                return "SLT-A99V";
                            case 295:
                                return "NEX-6";
                            case 296:
                                return "NEX-5R";
                            case ExifDirectoryBase.TAG_PAGE_NUMBER /* 297 */:
                                return "DSC-RX100";
                            case 298:
                                return "DSC-RX1";
                            default:
                                return "Unknown (" + integer + ")";
                        }
                }
        }
    }

    public String getSceneModeDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(SonyType1MakernoteDirectory.TAG_SCENE_MODE);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        switch (iIntValue) {
            case 0:
                return "Standard";
            case 1:
                return "Portrait";
            case 2:
                return "Text";
            case 3:
                return "Night Scene";
            case 4:
                return "Sunset";
            case 5:
                return "Sports";
            case 6:
                return "Landscape";
            case 7:
                return "Night Portrait";
            case 8:
                return "Macro";
            case 9:
                return "Super Macro";
            default:
                switch (iIntValue) {
                    case 16:
                        return "Auto";
                    case 17:
                        return "Night View/Portrait";
                    case 18:
                        return "Sweep Panorama";
                    case 19:
                        return "Handheld Night Shot";
                    case 20:
                        return "Anti Motion Blur";
                    case 21:
                        return "Cont. Priority AE";
                    case 22:
                        return "Auto+";
                    case 23:
                        return "3D Sweep Panorama";
                    case 24:
                        return "Superior Auto";
                    case 25:
                        return "High Sensitivity";
                    case 26:
                        return "Fireworks";
                    case 27:
                        return "Food";
                    case 28:
                        return "Pet";
                    default:
                        return "Unknown (" + integer + ")";
                }
        }
    }

    public String getAFModeDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(SonyType1MakernoteDirectory.TAG_AF_MODE);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 1 ? iIntValue != 2 ? iIntValue != 3 ? iIntValue != 4 ? iIntValue != 6 ? iIntValue != 65535 ? iIntValue != 14 ? iIntValue != 15 ? "Unknown (" + integer + ")" : "Face Detected" : "Manual Focus" : "n/a" : "Touch" : "Flexible Spot" : "Spot" : "Center" : "Multi" : "Default";
    }

    public String getAFIlluminatorDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(SonyType1MakernoteDirectory.TAG_AF_ILLUMINATOR);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 1 ? iIntValue != 65535 ? "Unknown (" + integer + ")" : "n/a" : "Auto" : "Off";
    }

    public String getFlashLevelDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(SonyType1MakernoteDirectory.TAG_FLASH_LEVEL);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        if (iIntValue == -32768) {
            return "Low";
        }
        if (iIntValue == 128) {
            return "n/a";
        }
        if (iIntValue == 32767) {
            return "High";
        }
        switch (iIntValue) {
            case -3:
                return "-3/3";
            case -2:
                return "-2/3";
            case -1:
                return "-1/3";
            case 0:
                return "Normal";
            case 1:
                return "+1/3";
            case 2:
                return "+2/3";
            case 3:
                return "+3/3";
            default:
                return "Unknown (" + integer + ")";
        }
    }

    public String getReleaseModeDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(SonyType1MakernoteDirectory.TAG_RELEASE_MODE);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 2 ? iIntValue != 65535 ? iIntValue != 5 ? iIntValue != 6 ? "Unknown (" + integer + ")" : "White Balance Bracketing" : "Exposure Bracketing" : "n/a" : "Continuous" : "Normal";
    }

    public String getSequenceNumberDescription() {
        Integer integer = ((SonyType1MakernoteDirectory) this._directory).getInteger(SonyType1MakernoteDirectory.TAG_RELEASE_MODE);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 65535 ? integer.toString() : "n/a" : "Single";
    }
}
