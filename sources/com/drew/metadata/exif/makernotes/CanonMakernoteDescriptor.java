package com.drew.metadata.exif.makernotes;

import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.TagDescriptor;
import com.drew.metadata.exif.makernotes.CanonMakernoteDirectory;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import com.facebook.imageutils.JfifUtil;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit;
import java.text.DecimalFormat;
import java.util.HashMap;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.internal.ws.WebSocketProtocol;
import org.apache.commons.lang3.StringUtils;
import org.jmrtd.cbeff.ISO781611;
import org.spongycastle.crypto.tls.CipherSuite;

/* loaded from: classes5.dex */
public class CanonMakernoteDescriptor extends TagDescriptor<CanonMakernoteDirectory> {
    private static final HashMap<Integer, String> _lensTypeById;

    private double decodeCanonEv(int i) {
        int i2;
        if (i < 0) {
            i = -i;
            i2 = -1;
        } else {
            i2 = 1;
        }
        int i3 = i & 31;
        int i4 = i - i3;
        if (i3 == 12) {
            i3 = 10;
        } else if (i3 == 20) {
            i3 = 21;
        }
        return (i2 * (i4 + i3)) / 32.0d;
    }

    public CanonMakernoteDescriptor(CanonMakernoteDirectory canonMakernoteDirectory) {
        super(canonMakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        switch (i) {
            case 12:
                return getSerialNumberDescription();
            case CanonMakernoteDirectory.CameraSettings.TAG_FOCUS_MODE_1 /* 49415 */:
                return getFocusMode1Description();
            case CanonMakernoteDirectory.CameraSettings.TAG_COLOR_TONE /* 49449 */:
                return getColorToneDescription();
            case CanonMakernoteDirectory.CameraSettings.TAG_SRAW_QUALITY /* 49453 */:
                return getSRawQualityDescription();
            case CanonMakernoteDirectory.FocalLength.TAG_WHITE_BALANCE /* 49671 */:
                return getWhiteBalanceDescription();
            case CanonMakernoteDirectory.AFInfo.TAG_AF_POINTS_IN_FOCUS /* 53770 */:
                return getTagAfPointsInFocus();
            default:
                switch (i) {
                    case CanonMakernoteDirectory.CameraSettings.TAG_MACRO_MODE /* 49409 */:
                        return getMacroModeDescription();
                    case CanonMakernoteDirectory.CameraSettings.TAG_SELF_TIMER_DELAY /* 49410 */:
                        return getSelfTimerDelayDescription();
                    case CanonMakernoteDirectory.CameraSettings.TAG_QUALITY /* 49411 */:
                        return getQualityDescription();
                    case CanonMakernoteDirectory.CameraSettings.TAG_FLASH_MODE /* 49412 */:
                        return getFlashModeDescription();
                    case CanonMakernoteDirectory.CameraSettings.TAG_CONTINUOUS_DRIVE_MODE /* 49413 */:
                        return getContinuousDriveModeDescription();
                    default:
                        switch (i) {
                            case CanonMakernoteDirectory.CameraSettings.TAG_RECORD_MODE /* 49417 */:
                                return getRecordModeDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_IMAGE_SIZE /* 49418 */:
                                return getImageSizeDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_EASY_SHOOTING_MODE /* 49419 */:
                                return getEasyShootingModeDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_DIGITAL_ZOOM /* 49420 */:
                                return getDigitalZoomDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_CONTRAST /* 49421 */:
                                return getContrastDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_SATURATION /* 49422 */:
                                return getSaturationDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_SHARPNESS /* 49423 */:
                                return getSharpnessDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_ISO /* 49424 */:
                                return getIsoDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_METERING_MODE /* 49425 */:
                                return getMeteringModeDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_FOCUS_TYPE /* 49426 */:
                                return getFocusTypeDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_AF_POINT_SELECTED /* 49427 */:
                                return getAfPointSelectedDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_EXPOSURE_MODE /* 49428 */:
                                return getExposureModeDescription();
                            default:
                                switch (i) {
                                    case CanonMakernoteDirectory.CameraSettings.TAG_LENS_TYPE /* 49430 */:
                                        return getLensTypeDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_LONG_FOCAL_LENGTH /* 49431 */:
                                        return getLongFocalLengthDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_SHORT_FOCAL_LENGTH /* 49432 */:
                                        return getShortFocalLengthDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_FOCAL_UNITS_PER_MM /* 49433 */:
                                        return getFocalUnitsPerMillimetreDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_MAX_APERTURE /* 49434 */:
                                        return getMaxApertureDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_MIN_APERTURE /* 49435 */:
                                        return getMinApertureDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_FLASH_ACTIVITY /* 49436 */:
                                        return getFlashActivityDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_FLASH_DETAILS /* 49437 */:
                                        return getFlashDetailsDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_FOCUS_CONTINUOUS /* 49438 */:
                                        return getFocusContinuousDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_AE_SETTING /* 49439 */:
                                        return getAESettingDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_FOCUS_MODE_2 /* 49440 */:
                                        return getFocusMode2Description();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_DISPLAY_APERTURE /* 49441 */:
                                        return getDisplayApertureDescription();
                                    default:
                                        switch (i) {
                                            case CanonMakernoteDirectory.CameraSettings.TAG_SPOT_METERING_MODE /* 49445 */:
                                                return getSpotMeteringModeDescription();
                                            case CanonMakernoteDirectory.CameraSettings.TAG_PHOTO_EFFECT /* 49446 */:
                                                return getPhotoEffectDescription();
                                            case CanonMakernoteDirectory.CameraSettings.TAG_MANUAL_FLASH_OUTPUT /* 49447 */:
                                                return getManualFlashOutputDescription();
                                            default:
                                                switch (i) {
                                                    case CanonMakernoteDirectory.FocalLength.TAG_AF_POINT_USED /* 49678 */:
                                                        return getAfPointUsedDescription();
                                                    case CanonMakernoteDirectory.FocalLength.TAG_FLASH_BIAS /* 49679 */:
                                                        return getFlashBiasDescription();
                                                    default:
                                                        return super.getDescription(i);
                                                }
                                        }
                                }
                        }
                }
        }
    }

    public String getSerialNumberDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(12);
        if (integer == null) {
            return null;
        }
        return String.format("%04X%05d", Integer.valueOf((integer.intValue() >> 8) & 255), Integer.valueOf(integer.intValue() & 255));
    }

    public String getFlashBiasDescription() {
        boolean z;
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.FocalLength.TAG_FLASH_BIAS);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() > 61440) {
            z = true;
            integer = Integer.valueOf(Integer.valueOf(65535 - integer.intValue()).intValue() + 1);
        } else {
            z = false;
        }
        return (z ? "-" : "") + Float.toString(integer.intValue() / 32.0f) + " EV";
    }

    public String getAfPointUsedDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.FocalLength.TAG_AF_POINT_USED);
        if (integer == null) {
            return null;
        }
        return (integer.intValue() & 7) == 0 ? "Right" : (integer.intValue() & 7) == 1 ? "Centre" : (integer.intValue() & 7) == 2 ? "Left" : "Unknown (" + integer + ")";
    }

    public String getTagAfPointsInFocus() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.AFInfo.TAG_AF_POINTS_IN_FOCUS);
        if (integer == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            if ((integer.intValue() & (1 << i)) != 0) {
                if (sb.length() != 0) {
                    sb.append(AbstractJsonLexerKt.COMMA);
                }
                sb.append(i);
            }
        }
        return sb.length() == 0 ? KeychainModule.AccessControl.NONE : sb.toString();
    }

    public String getWhiteBalanceDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.FocalLength.TAG_WHITE_BALANCE, "Auto", "Sunny", "Cloudy", "Tungsten", "Florescent", ExifInterface.TAG_FLASH, "Custom");
    }

    public String getFocusMode2Description() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_FOCUS_MODE_2, "Single", "Continuous");
    }

    public String getFlashDetailsDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_FLASH_DETAILS);
        if (integer == null) {
            return null;
        }
        return ((integer.intValue() >> 14) & 1) != 0 ? "External E-TTL" : ((integer.intValue() >> 13) & 1) != 0 ? "Internal flash" : ((integer.intValue() >> 11) & 1) != 0 ? "FP sync used" : ((integer.intValue() >> 4) & 1) != 0 ? "FP sync enabled" : "Unknown (" + integer + ")";
    }

    public String getFocalUnitsPerMillimetreDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_FOCAL_UNITS_PER_MM);
        if (integer == null) {
            return null;
        }
        return integer.intValue() != 0 ? Integer.toString(integer.intValue()) : "";
    }

    public String getShortFocalLengthDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_SHORT_FOCAL_LENGTH);
        if (integer == null) {
            return null;
        }
        return Integer.toString(integer.intValue()) + StringUtils.SPACE + getFocalUnitsPerMillimetreDescription();
    }

    public String getLongFocalLengthDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_LONG_FOCAL_LENGTH);
        if (integer == null) {
            return null;
        }
        return Integer.toString(integer.intValue()) + StringUtils.SPACE + getFocalUnitsPerMillimetreDescription();
    }

    public String getExposureModeDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_EXPOSURE_MODE, "Easy shooting", "Program", "Tv-priority", "Av-priority", "Manual", "A-DEP");
    }

    public String getLensTypeDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_LENS_TYPE);
        if (integer == null) {
            return null;
        }
        HashMap<Integer, String> map = _lensTypeById;
        if (map.containsKey(integer)) {
            return map.get(integer);
        }
        return String.format("Unknown (%d)", integer);
    }

    public String getMaxApertureDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_MAX_APERTURE);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() > 512) {
            return String.format("Unknown (%d)", integer);
        }
        return getFStopDescription(Math.exp((decodeCanonEv(integer.intValue()) * Math.log(2.0d)) / 2.0d));
    }

    public String getMinApertureDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_MIN_APERTURE);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() > 512) {
            return String.format("Unknown (%d)", integer);
        }
        return getFStopDescription(Math.exp((decodeCanonEv(integer.intValue()) * Math.log(2.0d)) / 2.0d));
    }

    public String getAfPointSelectedDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_AF_POINT_SELECTED, 12288, "None (MF)", "Auto selected", "Right", "Centre", "Left");
    }

    public String getMeteringModeDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_METERING_MODE, 3, "Evaluative", "Partial", "Centre weighted");
    }

    public String getIsoDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_ISO);
        if (integer == null) {
            return null;
        }
        if ((integer.intValue() & 16384) != 0) {
            return "" + (integer.intValue() & (-16385));
        }
        int iIntValue = integer.intValue();
        if (iIntValue == 0) {
            return "Not specified (see ISOSpeedRatings tag)";
        }
        switch (iIntValue) {
            case 15:
                return "Auto";
            case 16:
                return "50";
            case 17:
                return "100";
            case 18:
                return "200";
            case 19:
                return "400";
            default:
                return "Unknown (" + integer + ")";
        }
    }

    public String getSharpnessDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_SHARPNESS);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 1 ? iIntValue != 65535 ? "Unknown (" + integer + ")" : "Low" : "High" : "Normal";
    }

    public String getSaturationDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_SATURATION);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 1 ? iIntValue != 65535 ? "Unknown (" + integer + ")" : "Low" : "High" : "Normal";
    }

    public String getContrastDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_CONTRAST);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 1 ? iIntValue != 65535 ? "Unknown (" + integer + ")" : "Low" : "High" : "Normal";
    }

    public String getEasyShootingModeDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_EASY_SHOOTING_MODE, "Full auto", "Manual", "Landscape", "Fast shutter", "Slow shutter", "Night", "B&W", "Sepia", "Portrait", "Sports", "Macro / Closeup", "Pan focus");
    }

    public String getImageSizeDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_IMAGE_SIZE, "Large", "Medium", "Small");
    }

    public String getFocusMode1Description() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_FOCUS_MODE_1, "One-shot", "AI Servo", "AI Focus", "Manual Focus", "Single", "Continuous", "Manual Focus");
    }

    public String getContinuousDriveModeDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_CONTINUOUS_DRIVE_MODE);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        if (iIntValue != 0) {
            return iIntValue != 1 ? "Unknown (" + integer + ")" : "Continuous";
        }
        Integer integer2 = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_SELF_TIMER_DELAY);
        return integer2 != null ? integer2.intValue() == 0 ? "Single shot" : "Single shot with self-timer" : "Continuous";
    }

    public String getFlashModeDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_FLASH_MODE);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        if (iIntValue == 16) {
            return "External flash";
        }
        switch (iIntValue) {
            case 0:
                return "No flash fired";
            case 1:
                return "Auto";
            case 2:
                return "On";
            case 3:
                return "Red-eye reduction";
            case 4:
                return "Slow-synchro";
            case 5:
                return "Auto and red-eye reduction";
            case 6:
                return "On and red-eye reduction";
            default:
                return "Unknown (" + integer + ")";
        }
    }

    public String getSelfTimerDelayDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_SELF_TIMER_DELAY);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() == 0) {
            return "Self timer not used";
        }
        return new DecimalFormat("0.##").format(integer.intValue() * 0.1d) + " sec";
    }

    public String getMacroModeDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_MACRO_MODE, 1, "Macro", "Normal");
    }

    public String getQualityDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_QUALITY, 2, "Normal", "Fine", null, "Superfine");
    }

    public String getDigitalZoomDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_DIGITAL_ZOOM, "No digital zoom", "2x", "4x");
    }

    public String getRecordModeDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_RECORD_MODE, 1, "JPEG", "CRW+THM", "AVI+THM", "TIF", "TIF+JPEG", "CR2", "CR2+JPEG", null, "MOV", "MP4");
    }

    public String getFocusTypeDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_FOCUS_TYPE);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 1 ? iIntValue != 3 ? iIntValue != 8 ? "Unknown (" + integer + ")" : "Locked (Pan Mode)" : "Close-up (Macro)" : "Auto" : "Manual";
    }

    public String getFlashActivityDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_FLASH_ACTIVITY, "Flash did not fire", "Flash fired");
    }

    public String getFocusContinuousDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_FOCUS_CONTINUOUS, 0, "Single", "Continuous", null, null, null, null, null, null, "Manual");
    }

    public String getAESettingDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_AE_SETTING, 0, "Normal AE", "Exposure Compensation", "AE Lock", "AE Lock + Exposure Comp.", "No AE");
    }

    public String getDisplayApertureDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_DISPLAY_APERTURE);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() == 65535) {
            return integer.toString();
        }
        return getFStopDescription(integer.intValue() / 10.0f);
    }

    public String getSpotMeteringModeDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_SPOT_METERING_MODE, 0, "Center", "AF Point");
    }

    public String getPhotoEffectDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_PHOTO_EFFECT);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        if (iIntValue == 100) {
            return "My Color Data";
        }
        switch (iIntValue) {
            case 0:
                return "Off";
            case 1:
                return "Vivid";
            case 2:
                return "Neutral";
            case 3:
                return "Smooth";
            case 4:
                return "Sepia";
            case 5:
                return "B&W";
            case 6:
                return "Custom";
            default:
                return "Unknown (" + integer + ")";
        }
    }

    public String getManualFlashOutputDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_MANUAL_FLASH_OUTPUT);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 0 ? iIntValue != 1280 ? iIntValue != 1282 ? iIntValue != 1284 ? iIntValue != 32767 ? "Unknown (" + integer + ")" : "n/a" : "Low" : "Medium" : "Full" : "n/a";
    }

    public String getColorToneDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_COLOR_TONE);
        if (integer == null) {
            return null;
        }
        return integer.intValue() == 32767 ? "n/a" : integer.toString();
    }

    public String getSRawQualityDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_SRAW_QUALITY, 0, "n/a", "sRAW1 (mRAW)", "sRAW2 (sRAW)");
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _lensTypeById = map;
        map.put(1, "Canon EF 50mm f/1.8");
        map.put(2, "Canon EF 28mm f/2.8");
        map.put(3, "Canon EF 135mm f/2.8 Soft");
        map.put(4, "Canon EF 35-105mm f/3.5-4.5 or Sigma Lens");
        map.put(5, "Canon EF 35-70mm f/3.5-4.5");
        map.put(6, "Canon EF 28-70mm f/3.5-4.5 or Sigma or Tokina Lens");
        map.put(7, "Canon EF 100-300mm f/5.6L");
        map.put(8, "Canon EF 100-300mm f/5.6 or Sigma or Tokina Lens");
        map.put(9, "Canon EF 70-210mm f/4");
        map.put(10, "Canon EF 50mm f/2.5 Macro or Sigma Lens");
        map.put(11, "Canon EF 35mm f/2");
        map.put(13, "Canon EF 15mm f/2.8 Fisheye");
        map.put(14, "Canon EF 50-200mm f/3.5-4.5L");
        map.put(15, "Canon EF 50-200mm f/3.5-4.5");
        map.put(16, "Canon EF 35-135mm f/3.5-4.5");
        map.put(17, "Canon EF 35-70mm f/3.5-4.5A");
        map.put(18, "Canon EF 28-70mm f/3.5-4.5");
        map.put(20, "Canon EF 100-200mm f/4.5A");
        map.put(21, "Canon EF 80-200mm f/2.8L");
        map.put(22, "Canon EF 20-35mm f/2.8L or Tokina Lens");
        map.put(23, "Canon EF 35-105mm f/3.5-4.5");
        map.put(24, "Canon EF 35-80mm f/4-5.6 Power Zoom");
        map.put(25, "Canon EF 35-80mm f/4-5.6 Power Zoom");
        map.put(26, "Canon EF 100mm f/2.8 Macro or Other Lens");
        map.put(27, "Canon EF 35-80mm f/4-5.6");
        map.put(28, "Canon EF 80-200mm f/4.5-5.6 or Tamron Lens");
        map.put(29, "Canon EF 50mm f/1.8 II");
        map.put(30, "Canon EF 35-105mm f/4.5-5.6");
        map.put(31, "Canon EF 75-300mm f/4-5.6 or Tamron Lens");
        map.put(32, "Canon EF 24mm f/2.8 or Sigma Lens");
        map.put(33, "Voigtlander or Carl Zeiss Lens");
        map.put(35, "Canon EF 35-80mm f/4-5.6");
        map.put(36, "Canon EF 38-76mm f/4.5-5.6");
        map.put(37, "Canon EF 35-80mm f/4-5.6 or Tamron Lens");
        map.put(38, "Canon EF 80-200mm f/4.5-5.6");
        map.put(39, "Canon EF 75-300mm f/4-5.6");
        map.put(40, "Canon EF 28-80mm f/3.5-5.6");
        map.put(41, "Canon EF 28-90mm f/4-5.6");
        map.put(42, "Canon EF 28-200mm f/3.5-5.6 or Tamron Lens");
        map.put(43, "Canon EF 28-105mm f/4-5.6");
        map.put(44, "Canon EF 90-300mm f/4.5-5.6");
        map.put(45, "Canon EF-S 18-55mm f/3.5-5.6 [II]");
        map.put(46, "Canon EF 28-90mm f/4-5.6");
        map.put(47, "Zeiss Milvus 35mm f/2 or 50mm f/2");
        map.put(48, "Canon EF-S 18-55mm f/3.5-5.6 IS");
        map.put(49, "Canon EF-S 55-250mm f/4-5.6 IS");
        map.put(50, "Canon EF-S 18-200mm f/3.5-5.6 IS");
        map.put(51, "Canon EF-S 18-135mm f/3.5-5.6 IS");
        map.put(52, "Canon EF-S 18-55mm f/3.5-5.6 IS II");
        map.put(53, "Canon EF-S 18-55mm f/3.5-5.6 III");
        map.put(54, "Canon EF-S 55-250mm f/4-5.6 IS II");
        map.put(94, "Canon TS-E 17mm f/4L");
        map.put(95, "Canon TS-E 24.0mm f/3.5 L II");
        map.put(Integer.valueOf(PanasonicMakernoteDirectory.TAG_CLEAR_RETOUCH), "Canon MP-E 65mm f/2.8 1-5x Macro Photo");
        map.put(Integer.valueOf(ISO781611.SMT_TAG), "Canon TS-E 24mm f/3.5L");
        map.put(Integer.valueOf(WebSocketProtocol.PAYLOAD_SHORT), "Canon TS-E 45mm f/2.8");
        map.put(127, "Canon TS-E 90mm f/2.8");
        map.put(129, "Canon EF 300mm f/2.8L");
        map.put(130, "Canon EF 50mm f/1.0L");
        map.put(131, "Canon EF 28-80mm f/2.8-4L or Sigma Lens");
        map.put(132, "Canon EF 1200mm f/5.6L");
        map.put(134, "Canon EF 600mm f/4L IS");
        map.put(135, "Canon EF 200mm f/1.8L");
        map.put(136, "Canon EF 300mm f/2.8L");
        map.put(137, "Canon EF 85mm f/1.2L or Sigma or Tamron Lens");
        map.put(138, "Canon EF 28-80mm f/2.8-4L");
        map.put(139, "Canon EF 400mm f/2.8L");
        map.put(140, "Canon EF 500mm f/4.5L");
        map.put(141, "Canon EF 500mm f/4.5L");
        map.put(142, "Canon EF 300mm f/2.8L IS");
        map.put(143, "Canon EF 500mm f/4L IS or Sigma Lens");
        map.put(144, "Canon EF 35-135mm f/4-5.6 USM");
        map.put(145, "Canon EF 100-300mm f/4.5-5.6 USM");
        map.put(146, "Canon EF 70-210mm f/3.5-4.5 USM");
        map.put(147, "Canon EF 35-135mm f/4-5.6 USM");
        map.put(148, "Canon EF 28-80mm f/3.5-5.6 USM");
        map.put(149, "Canon EF 100mm f/2 USM");
        map.put(150, "Canon EF 14mm f/2.8L or Sigma Lens");
        map.put(151, "Canon EF 200mm f/2.8L");
        map.put(152, "Canon EF 300mm f/4L IS or Sigma Lens");
        map.put(153, "Canon EF 35-350mm f/3.5-5.6L or Sigma or Tamron Lens");
        map.put(154, "Canon EF 20mm f/2.8 USM or Zeiss Lens");
        map.put(155, "Canon EF 85mm f/1.8 USM");
        map.put(156, "Canon EF 28-105mm f/3.5-4.5 USM or Tamron Lens");
        map.put(160, "Canon EF 20-35mm f/3.5-4.5 USM or Tamron or Tokina Lens");
        map.put(161, "Canon EF 28-70mm f/2.8L or Sigma or Tamron Lens");
        map.put(162, "Canon EF 200mm f/2.8L");
        map.put(163, "Canon EF 300mm f/4L");
        map.put(164, "Canon EF 400mm f/5.6L");
        map.put(165, "Canon EF 70-200mm f/2.8 L");
        map.put(166, "Canon EF 70-200mm f/2.8 L + 1.4x");
        map.put(167, "Canon EF 70-200mm f/2.8 L + 2x");
        map.put(168, "Canon EF 28mm f/1.8 USM or Sigma Lens");
        map.put(169, "Canon EF 17-35mm f/2.8L or Sigma Lens");
        map.put(170, "Canon EF 200mm f/2.8L II");
        map.put(171, "Canon EF 300mm f/4L");
        map.put(172, "Canon EF 400mm f/5.6L or Sigma Lens");
        map.put(173, "Canon EF 180mm Macro f/3.5L or Sigma Lens");
        map.put(174, "Canon EF 135mm f/2L or Other Lens");
        map.put(175, "Canon EF 400mm f/2.8L");
        map.put(176, "Canon EF 24-85mm f/3.5-4.5 USM");
        map.put(177, "Canon EF 300mm f/4L IS");
        map.put(178, "Canon EF 28-135mm f/3.5-5.6 IS");
        map.put(179, "Canon EF 24mm f/1.4L");
        map.put(180, "Canon EF 35mm f/1.4L or Other Lens");
        map.put(181, "Canon EF 100-400mm f/4.5-5.6L IS + 1.4x or Sigma Lens");
        map.put(182, "Canon EF 100-400mm f/4.5-5.6L IS + 2x or Sigma Lens");
        map.put(183, "Canon EF 100-400mm f/4.5-5.6L IS or Sigma Lens");
        map.put(184, "Canon EF 400mm f/2.8L + 2x");
        map.put(185, "Canon EF 600mm f/4L IS");
        map.put(Integer.valueOf(CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256), "Canon EF 70-200mm f/4L");
        map.put(187, "Canon EF 70-200mm f/4L + 1.4x");
        map.put(188, "Canon EF 70-200mm f/4L + 2x");
        map.put(189, "Canon EF 70-200mm f/4L + 2.8x");
        map.put(Integer.valueOf(CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256), "Canon EF 100mm f/2.8 Macro USM");
        map.put(Integer.valueOf(CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256), "Canon EF 400mm f/4 DO IS");
        map.put(Integer.valueOf(CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256), "Canon EF 35-80mm f/4-5.6 USM");
        map.put(Integer.valueOf(CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256), "Canon EF 80-200mm f/4.5-5.6 USM");
        map.put(Integer.valueOf(CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256), "Canon EF 35-105mm f/4.5-5.6 USM");
        map.put(Integer.valueOf(CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256), "Canon EF 75-300mm f/4-5.6 USM");
        map.put(Integer.valueOf(CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256), "Canon EF 75-300mm f/4-5.6 IS USM");
        map.put(198, "Canon EF 50mm f/1.4 USM or Zeiss Lens");
        map.put(199, "Canon EF 28-80mm f/3.5-5.6 USM");
        map.put(200, "Canon EF 75-300mm f/4-5.6 USM");
        map.put(201, "Canon EF 28-80mm f/3.5-5.6 USM");
        map.put(202, "Canon EF 28-80mm f/3.5-5.6 USM IV");
        map.put(208, "Canon EF 22-55mm f/4-5.6 USM");
        map.put(Integer.valueOf(Mp4VideoDirectory.TAG_DEPTH), "Canon EF 55-200mm f/4.5-5.6");
        map.put(Integer.valueOf(Mp4VideoDirectory.TAG_COMPRESSION_TYPE), "Canon EF 28-90mm f/4-5.6 USM");
        map.put(211, "Canon EF 28-200mm f/3.5-5.6 USM");
        map.put(Integer.valueOf(Mp4VideoDirectory.TAG_OPCOLOR), "Canon EF 28-105mm f/4-5.6 USM");
        map.put(Integer.valueOf(Mp4VideoDirectory.TAG_COLOR_TABLE), "Canon EF 90-300mm f/4.5-5.6 USM or Tamron Lens");
        map.put(Integer.valueOf(Mp4VideoDirectory.TAG_FRAME_RATE), "Canon EF-S 18-55mm f/3.5-5.6 USM");
        map.put(Integer.valueOf(JfifUtil.MARKER_RST7), "Canon EF 55-200mm f/4.5-5.6 II USM");
        map.put(Integer.valueOf(JfifUtil.MARKER_EOI), "Tamron AF 18-270mm f/3.5-6.3 Di II VC PZD");
        map.put(224, "Canon EF 70-200mm f/2.8L IS");
        map.put(Integer.valueOf(JfifUtil.MARKER_APP1), "Canon EF 70-200mm f/2.8L IS + 1.4x");
        map.put(226, "Canon EF 70-200mm f/2.8L IS + 2x");
        map.put(227, "Canon EF 70-200mm f/2.8L IS + 2.8x");
        map.put(228, "Canon EF 28-105mm f/3.5-4.5 USM");
        map.put(229, "Canon EF 16-35mm f/2.8L");
        map.put(230, "Canon EF 24-70mm f/2.8L");
        map.put(231, "Canon EF 17-40mm f/4L");
        map.put(232, "Canon EF 70-300mm f/4.5-5.6 DO IS USM");
        map.put(233, "Canon EF 28-300mm f/3.5-5.6L IS");
        map.put(234, "Canon EF-S 17-85mm f/4-5.6 IS USM or Tokina Lens");
        map.put(235, "Canon EF-S 10-22mm f/3.5-4.5 USM");
        map.put(236, "Canon EF-S 60mm f/2.8 Macro USM");
        map.put(237, "Canon EF 24-105mm f/4L IS");
        map.put(238, "Canon EF 70-300mm f/4-5.6 IS USM");
        map.put(239, "Canon EF 85mm f/1.2L II");
        map.put(Integer.valueOf(FaceDetectorAvcMLKit.FACE_DETECTION_FRAME_WIDTH), "Canon EF-S 17-55mm f/2.8 IS USM");
        map.put(241, "Canon EF 50mm f/1.2L");
        map.put(242, "Canon EF 70-200mm f/4L IS");
        map.put(243, "Canon EF 70-200mm f/4L IS + 1.4x");
        map.put(244, "Canon EF 70-200mm f/4L IS + 2x");
        map.put(245, "Canon EF 70-200mm f/4L IS + 2.8x");
        map.put(246, "Canon EF 16-35mm f/2.8L II");
        map.put(247, "Canon EF 14mm f/2.8L II USM");
        map.put(248, "Canon EF 200mm f/2L IS or Sigma Lens");
        map.put(249, "Canon EF 800mm f/5.6L IS");
        map.put(250, "Canon EF 24mm f/1.4L II or Sigma Lens");
        map.put(251, "Canon EF 70-200mm f/2.8L IS II USM");
        map.put(252, "Canon EF 70-200mm f/2.8L IS II USM + 1.4x");
        map.put(253, "Canon EF 70-200mm f/2.8L IS II USM + 2x");
        map.put(254, "Canon EF 100mm f/2.8L Macro IS USM");
        map.put(255, "Sigma 24-105mm f/4 DG OS HSM | A or Other Sigma Lens");
        map.put(488, "Canon EF-S 15-85mm f/3.5-5.6 IS USM");
        map.put(489, "Canon EF 70-300mm f/4-5.6L IS USM");
        map.put(490, "Canon EF 8-15mm f/4L Fisheye USM");
        map.put(491, "Canon EF 300mm f/2.8L IS II USM");
        map.put(492, "Canon EF 400mm f/2.8L IS II USM");
        map.put(493, "Canon EF 500mm f/4L IS II USM or EF 24-105mm f4L IS USM");
        map.put(494, "Canon EF 600mm f/4.0L IS II USM");
        map.put(495, "Canon EF 24-70mm f/2.8L II USM");
        map.put(496, "Canon EF 200-400mm f/4L IS USM");
        map.put(499, "Canon EF 200-400mm f/4L IS USM + 1.4x");
        map.put(502, "Canon EF 28mm f/2.8 IS USM");
        map.put(503, "Canon EF 24mm f/2.8 IS USM");
        map.put(504, "Canon EF 24-70mm f/4L IS USM");
        map.put(505, "Canon EF 35mm f/2 IS USM");
        map.put(506, "Canon EF 400mm f/4 DO IS II USM");
        map.put(507, "Canon EF 16-35mm f/4L IS USM");
        map.put(508, "Canon EF 11-24mm f/4L USM");
        map.put(747, "Canon EF 100-400mm f/4.5-5.6L IS II USM");
        map.put(750, "Canon EF 35mm f/1.4L II USM");
        map.put(Integer.valueOf(OlympusMakernoteDirectory.TAG_OLYMPUS_IMAGE_WIDTH), "Canon EF-S 18-135mm f/3.5-5.6 IS STM");
        map.put(Integer.valueOf(OlympusMakernoteDirectory.TAG_OLYMPUS_IMAGE_HEIGHT), "Canon EF-M 18-55mm f/3.5-5.6 IS STM or Tamron Lens");
        map.put(4144, "Canon EF 40mm f/2.8 STM");
        map.put(4145, "Canon EF-M 22mm f/2 STM");
        map.put(4146, "Canon EF-S 18-55mm f/3.5-5.6 IS STM");
        map.put(4147, "Canon EF-M 11-22mm f/4-5.6 IS STM");
        map.put(4148, "Canon EF-S 55-250mm f/4-5.6 IS STM");
        map.put(Integer.valueOf(OlympusMakernoteDirectory.TAG_PREVIEW_IMAGE_VALID), "Canon EF-M 55-200mm f/4.5-6.3 IS STM");
        map.put(Integer.valueOf(OlympusMakernoteDirectory.TAG_PREVIEW_IMAGE_START), "Canon EF-S 10-18mm f/4.5-5.6 IS STM");
        map.put(Integer.valueOf(OlympusMakernoteDirectory.TAG_AF_RESULT), "Canon EF 24-105mm f/3.5-5.6 IS STM");
        map.put(Integer.valueOf(OlympusMakernoteDirectory.TAG_CCD_SCAN_MODE), "Canon EF-M 15-45mm f/3.5-6.3 IS STM");
        map.put(Integer.valueOf(OlympusMakernoteDirectory.TAG_NOISE_REDUCTION), "Canon EF-S 24mm f/2.8 STM");
        map.put(Integer.valueOf(OlympusMakernoteDirectory.TAG_NEAR_LENS_STEP), "Canon EF 50mm f/1.8 STM");
        map.put(36912, "Canon EF-S 18-135mm f/3.5-5.6 IS USM");
        map.put(65535, "N/A");
    }
}
