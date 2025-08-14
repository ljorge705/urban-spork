package com.drew.metadata.exif.makernotes;

import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CanonMakernoteDirectory extends Directory {
    private static final int TAG_AF_INFO_ARRAY = 18;
    public static final int TAG_AF_INFO_ARRAY_2 = 38;
    public static final int TAG_AF_POINTS_IN_FOCUS_1D = 148;
    public static final int TAG_AMBIANCE_INFO_ARRAY = 16416;
    public static final int TAG_ASPECT_INFO_ARRAY = 154;
    public static final int TAG_BLACK_LEVEL = 16392;
    public static final int TAG_CAMERA_INFO_ARRAY = 13;
    private static final int TAG_CAMERA_SETTINGS_ARRAY = 1;
    public static final int TAG_CANON_CUSTOM_FUNCTIONS_ARRAY = 15;
    public static final int TAG_CANON_FILE_LENGTH = 14;
    public static final int TAG_CANON_FIRMWARE_VERSION = 7;
    public static final int TAG_CANON_FLAGS_ARRAY = 176;
    public static final int TAG_CANON_IMAGE_NUMBER = 8;
    public static final int TAG_CANON_IMAGE_TYPE = 6;
    public static final int TAG_CANON_OWNER_NAME = 9;
    public static final int TAG_CANON_SERIAL_NUMBER = 12;
    public static final int TAG_CATEGORIES = 35;
    public static final int TAG_COLOR_BALANCE_ARRAY = 169;
    public static final int TAG_COLOR_DATA_ARRAY_2 = 16385;
    public static final int TAG_COLOR_INFO_ARRAY = 16403;
    public static final int TAG_COLOR_INFO_ARRAY_2 = 16387;
    public static final int TAG_COLOR_SPACE = 180;
    public static final int TAG_COLOR_TEMPERATURE = 174;
    public static final int TAG_CROP_INFO = 152;
    public static final int TAG_CRW_PARAM = 16386;
    public static final int TAG_CUSTOM_FUNCTIONS_1D_ARRAY = 144;
    public static final int TAG_CUSTOM_FUNCTIONS_ARRAY_2 = 153;
    public static final int TAG_CUSTOM_PICTURE_STYLE_FILE_NAME = 16400;
    public static final int TAG_DATE_STAMP_MODE = 28;
    public static final int TAG_DUST_REMOVAL_DATA = 151;
    public static final int TAG_FACE_DETECT_ARRAY_1 = 36;
    public static final int TAG_FACE_DETECT_ARRAY_2 = 37;
    public static final int TAG_FILE_INFO_ARRAY = 147;
    public static final int TAG_FILTER_INFO_ARRAY = 16420;
    public static final int TAG_FIRMWARE_REVISION = 30;
    private static final int TAG_FOCAL_LENGTH_ARRAY = 2;
    public static final int TAG_IMAGE_UNIQUE_ID = 40;
    public static final int TAG_LENS_INFO_ARRAY = 16409;
    public static final int TAG_LENS_MODEL = 149;
    public static final int TAG_LIGHTING_OPTIMIZER_ARRAY = 16408;
    public static final int TAG_MEASURED_COLOR_ARRAY = 170;
    public static final int TAG_MODEL_ID = 16;
    public static final int TAG_MODIFIED_INFO_ARRAY = 177;
    public static final int TAG_MOVIE_INFO_ARRAY = 17;
    public static final int TAG_MY_COLORS = 29;
    public static final int TAG_ORIGINAL_DECISION_DATA_OFFSET = 131;
    private static final int TAG_PANORAMA_ARRAY = 5;
    public static final int TAG_PERSONAL_FUNCTIONS_ARRAY = 145;
    public static final int TAG_PERSONAL_FUNCTION_VALUES_ARRAY = 146;
    public static final int TAG_PREVIEW_IMAGE_INFO_ARRAY = 182;
    public static final int TAG_PROCESSING_INFO_ARRAY = 160;
    public static final int TAG_RAW_DATA_OFFSET = 129;
    public static final int TAG_SENSOR_INFO_ARRAY = 224;
    public static final int TAG_SERIAL_INFO_ARRAY = 150;
    public static final int TAG_SERIAL_NUMBER_FORMAT = 21;
    public static final int TAG_SHARPNESS_FREQ_TABLE = 163;
    public static final int TAG_SHARPNESS_TABLE = 162;
    private static final int TAG_SHOT_INFO_ARRAY = 4;
    public static final int TAG_SUPER_MACRO = 26;
    public static final int TAG_THUMBNAIL_IMAGE_VALID_AREA = 19;
    public static final int TAG_TONE_CURVE_MATCHING = 178;
    public static final int TAG_TONE_CURVE_TABLE = 161;
    public static final int TAG_VIGNETTING_CORRECTION_ARRAY_1 = 16405;
    public static final int TAG_VIGNETTING_CORRECTION_ARRAY_2 = 16406;
    public static final int TAG_VRD_OFFSET = 208;
    public static final int TAG_WHITE_BALANCE_MATCHING = 179;
    public static final int TAG_WHITE_BALANCE_TABLE = 164;
    protected static final HashMap<Integer, String> _tagNameMap;

    public static final class AFInfo {
        private static final int OFFSET = 53760;
        public static final int TAG_AF_AREA_HEIGHT = 53767;
        public static final int TAG_AF_AREA_WIDTH = 53766;
        public static final int TAG_AF_AREA_X_POSITIONS = 53768;
        public static final int TAG_AF_AREA_Y_POSITIONS = 53769;
        public static final int TAG_AF_IMAGE_HEIGHT = 53765;
        public static final int TAG_AF_IMAGE_WIDTH = 53764;
        public static final int TAG_AF_POINTS_IN_FOCUS = 53770;
        public static final int TAG_IMAGE_HEIGHT = 53763;
        public static final int TAG_IMAGE_WIDTH = 53762;
        public static final int TAG_NUM_AF_POINTS = 53760;
        public static final int TAG_PRIMARY_AF_POINT_1 = 53771;
        public static final int TAG_PRIMARY_AF_POINT_2 = 53772;
        public static final int TAG_VALID_AF_POINTS = 53761;
    }

    public static final class CameraSettings {
        private static final int OFFSET = 49408;
        public static final int TAG_AE_SETTING = 49439;
        public static final int TAG_AF_POINT_SELECTED = 49427;
        public static final int TAG_COLOR_TONE = 49449;
        public static final int TAG_CONTINUOUS_DRIVE_MODE = 49413;
        public static final int TAG_CONTRAST = 49421;
        public static final int TAG_DIGITAL_ZOOM = 49420;
        public static final int TAG_DISPLAY_APERTURE = 49441;
        public static final int TAG_EASY_SHOOTING_MODE = 49419;
        public static final int TAG_EXPOSURE_MODE = 49428;
        public static final int TAG_FLASH_ACTIVITY = 49436;
        public static final int TAG_FLASH_DETAILS = 49437;
        public static final int TAG_FLASH_MODE = 49412;
        public static final int TAG_FOCAL_UNITS_PER_MM = 49433;
        public static final int TAG_FOCUS_CONTINUOUS = 49438;
        public static final int TAG_FOCUS_MODE_1 = 49415;
        public static final int TAG_FOCUS_MODE_2 = 49440;
        public static final int TAG_FOCUS_TYPE = 49426;
        public static final int TAG_IMAGE_SIZE = 49418;
        public static final int TAG_ISO = 49424;
        public static final int TAG_LENS_TYPE = 49430;
        public static final int TAG_LONG_FOCAL_LENGTH = 49431;
        public static final int TAG_MACRO_MODE = 49409;
        public static final int TAG_MANUAL_FLASH_OUTPUT = 49447;
        public static final int TAG_MAX_APERTURE = 49434;
        public static final int TAG_METERING_MODE = 49425;
        public static final int TAG_MIN_APERTURE = 49435;
        public static final int TAG_PHOTO_EFFECT = 49446;
        public static final int TAG_QUALITY = 49411;
        public static final int TAG_RECORD_MODE = 49417;
        public static final int TAG_SATURATION = 49422;
        public static final int TAG_SELF_TIMER_DELAY = 49410;
        public static final int TAG_SHARPNESS = 49423;
        public static final int TAG_SHORT_FOCAL_LENGTH = 49432;
        public static final int TAG_SPOT_METERING_MODE = 49445;
        public static final int TAG_SRAW_QUALITY = 49453;
        public static final int TAG_UNKNOWN_2 = 49414;
        public static final int TAG_UNKNOWN_3 = 49416;
        public static final int TAG_UNKNOWN_7 = 49429;
        public static final int TAG_ZOOM_SOURCE_WIDTH = 49442;
        public static final int TAG_ZOOM_TARGET_WIDTH = 49443;
    }

    public static final class FocalLength {
        private static final int OFFSET = 49664;
        public static final int TAG_AEB_BRACKET_VALUE = 49681;
        public static final int TAG_AF_POINT_USED = 49678;
        public static final int TAG_AUTO_EXPOSURE_BRACKETING = 49680;
        public static final int TAG_FLASH_BIAS = 49679;
        public static final int TAG_SEQUENCE_NUMBER = 49673;
        public static final int TAG_SUBJECT_DISTANCE = 49683;
        public static final int TAG_WHITE_BALANCE = 49671;
    }

    public static final class Panorama {
        private static final int OFFSET = 50432;
        public static final int TAG_PANORAMA_DIRECTION = 50437;
        public static final int TAG_PANORAMA_FRAME_NUMBER = 50434;
    }

    public static final class ShotInfo {
        private static final int OFFSET = 50176;
        public static final int TAG_AEB_BRACKET_VALUE = 50193;
        public static final int TAG_AF_POINTS_IN_FOCUS = 50190;
        public static final int TAG_AUTO_EXPOSURE_BRACKETING = 50192;
        public static final int TAG_AUTO_ISO = 50177;
        public static final int TAG_AUTO_ROTATE = 50203;
        public static final int TAG_BASE_ISO = 50178;
        public static final int TAG_BULB_DURATION = 50200;
        public static final int TAG_CAMERA_TEMPERATURE = 50188;
        public static final int TAG_CAMERA_TYPE = 50202;
        public static final int TAG_CONTROL_MODE = 50194;
        public static final int TAG_EXPOSURE_COMPENSATION = 50182;
        public static final int TAG_EXPOSURE_TIME = 50198;
        public static final int TAG_FLASH_EXPOSURE_BRACKETING = 50191;
        public static final int TAG_FLASH_GUIDE_NUMBER = 50189;
        public static final int TAG_FLASH_OUTPUT = 50209;
        public static final int TAG_FOCUS_DISTANCE_LOWER = 50196;
        public static final int TAG_FOCUS_DISTANCE_UPPER = 50195;
        public static final int TAG_F_NUMBER = 50197;
        public static final int TAG_MEASURED_EV = 50179;
        public static final int TAG_MEASURED_EV_2 = 50199;
        public static final int TAG_ND_FILTER = 50204;
        public static final int TAG_OPTICAL_ZOOM_CODE = 50186;
        public static final int TAG_SELF_TIMER_2 = 50205;
        public static final int TAG_SEQUENCE_NUMBER = 50185;
        public static final int TAG_SLOW_SHUTTER = 50184;
        public static final int TAG_TARGET_APERTURE = 50180;
        public static final int TAG_TARGET_EXPOSURE_TIME = 50181;
        public static final int TAG_WHITE_BALANCE = 50183;
    }

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Canon Makernote";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(7, "Firmware Version");
        map.put(8, "Image Number");
        map.put(6, "Image Type");
        map.put(9, "Owner Name");
        map.put(12, "Camera Serial Number");
        map.put(13, "Camera Info Array");
        map.put(14, "File Length");
        map.put(15, "Custom Functions");
        map.put(16, "Canon Model ID");
        map.put(17, "Movie Info Array");
        map.put(Integer.valueOf(CameraSettings.TAG_AF_POINT_SELECTED), "AF Point Selected");
        map.put(Integer.valueOf(CameraSettings.TAG_CONTINUOUS_DRIVE_MODE), "Continuous Drive Mode");
        map.put(Integer.valueOf(CameraSettings.TAG_CONTRAST), ExifInterface.TAG_CONTRAST);
        map.put(Integer.valueOf(CameraSettings.TAG_EASY_SHOOTING_MODE), "Easy Shooting Mode");
        map.put(Integer.valueOf(CameraSettings.TAG_EXPOSURE_MODE), "Exposure Mode");
        map.put(Integer.valueOf(CameraSettings.TAG_FLASH_DETAILS), "Flash Details");
        map.put(Integer.valueOf(CameraSettings.TAG_FLASH_MODE), "Flash Mode");
        map.put(Integer.valueOf(CameraSettings.TAG_FOCAL_UNITS_PER_MM), "Focal Units per mm");
        map.put(Integer.valueOf(CameraSettings.TAG_FOCUS_MODE_1), "Focus Mode");
        map.put(Integer.valueOf(CameraSettings.TAG_FOCUS_MODE_2), "Focus Mode");
        map.put(Integer.valueOf(CameraSettings.TAG_IMAGE_SIZE), "Image Size");
        map.put(Integer.valueOf(CameraSettings.TAG_ISO), "Iso");
        map.put(Integer.valueOf(CameraSettings.TAG_LONG_FOCAL_LENGTH), "Long Focal Length");
        map.put(Integer.valueOf(CameraSettings.TAG_MACRO_MODE), "Macro Mode");
        map.put(Integer.valueOf(CameraSettings.TAG_METERING_MODE), "Metering Mode");
        map.put(Integer.valueOf(CameraSettings.TAG_SATURATION), ExifInterface.TAG_SATURATION);
        map.put(Integer.valueOf(CameraSettings.TAG_SELF_TIMER_DELAY), "Self Timer Delay");
        map.put(Integer.valueOf(CameraSettings.TAG_SHARPNESS), ExifInterface.TAG_SHARPNESS);
        map.put(Integer.valueOf(CameraSettings.TAG_SHORT_FOCAL_LENGTH), "Short Focal Length");
        map.put(Integer.valueOf(CameraSettings.TAG_QUALITY), "Quality");
        map.put(Integer.valueOf(CameraSettings.TAG_UNKNOWN_2), "Unknown Camera Setting 2");
        map.put(Integer.valueOf(CameraSettings.TAG_UNKNOWN_3), "Unknown Camera Setting 3");
        map.put(Integer.valueOf(CameraSettings.TAG_RECORD_MODE), "Record Mode");
        map.put(Integer.valueOf(CameraSettings.TAG_DIGITAL_ZOOM), "Digital Zoom");
        map.put(Integer.valueOf(CameraSettings.TAG_FOCUS_TYPE), "Focus Type");
        map.put(Integer.valueOf(CameraSettings.TAG_UNKNOWN_7), "Unknown Camera Setting 7");
        map.put(Integer.valueOf(CameraSettings.TAG_LENS_TYPE), "Lens Type");
        map.put(Integer.valueOf(CameraSettings.TAG_MAX_APERTURE), "Max Aperture");
        map.put(Integer.valueOf(CameraSettings.TAG_MIN_APERTURE), "Min Aperture");
        map.put(Integer.valueOf(CameraSettings.TAG_FLASH_ACTIVITY), "Flash Activity");
        map.put(Integer.valueOf(CameraSettings.TAG_FOCUS_CONTINUOUS), "Focus Continuous");
        map.put(Integer.valueOf(CameraSettings.TAG_AE_SETTING), "AE Setting");
        map.put(Integer.valueOf(CameraSettings.TAG_DISPLAY_APERTURE), "Display Aperture");
        map.put(Integer.valueOf(CameraSettings.TAG_ZOOM_SOURCE_WIDTH), "Zoom Source Width");
        map.put(Integer.valueOf(CameraSettings.TAG_ZOOM_TARGET_WIDTH), "Zoom Target Width");
        map.put(Integer.valueOf(CameraSettings.TAG_SPOT_METERING_MODE), "Spot Metering Mode");
        map.put(Integer.valueOf(CameraSettings.TAG_PHOTO_EFFECT), "Photo Effect");
        map.put(Integer.valueOf(CameraSettings.TAG_MANUAL_FLASH_OUTPUT), "Manual Flash Output");
        map.put(Integer.valueOf(CameraSettings.TAG_COLOR_TONE), "Color Tone");
        map.put(Integer.valueOf(CameraSettings.TAG_SRAW_QUALITY), "SRAW Quality");
        map.put(Integer.valueOf(FocalLength.TAG_WHITE_BALANCE), "White Balance");
        map.put(Integer.valueOf(FocalLength.TAG_SEQUENCE_NUMBER), "Sequence Number");
        map.put(Integer.valueOf(FocalLength.TAG_AF_POINT_USED), "AF Point Used");
        map.put(Integer.valueOf(FocalLength.TAG_FLASH_BIAS), "Flash Bias");
        map.put(Integer.valueOf(FocalLength.TAG_AUTO_EXPOSURE_BRACKETING), "Auto Exposure Bracketing");
        map.put(Integer.valueOf(FocalLength.TAG_AEB_BRACKET_VALUE), "AEB Bracket Value");
        map.put(Integer.valueOf(FocalLength.TAG_SUBJECT_DISTANCE), "Subject Distance");
        map.put(Integer.valueOf(ShotInfo.TAG_AUTO_ISO), "Auto ISO");
        map.put(Integer.valueOf(ShotInfo.TAG_BASE_ISO), "Base ISO");
        map.put(Integer.valueOf(ShotInfo.TAG_MEASURED_EV), "Measured EV");
        map.put(Integer.valueOf(ShotInfo.TAG_TARGET_APERTURE), "Target Aperture");
        map.put(Integer.valueOf(ShotInfo.TAG_TARGET_EXPOSURE_TIME), "Target Exposure Time");
        map.put(Integer.valueOf(ShotInfo.TAG_EXPOSURE_COMPENSATION), "Exposure Compensation");
        map.put(Integer.valueOf(ShotInfo.TAG_WHITE_BALANCE), "White Balance");
        map.put(Integer.valueOf(ShotInfo.TAG_SLOW_SHUTTER), "Slow Shutter");
        map.put(Integer.valueOf(ShotInfo.TAG_SEQUENCE_NUMBER), "Sequence Number");
        map.put(Integer.valueOf(ShotInfo.TAG_OPTICAL_ZOOM_CODE), "Optical Zoom Code");
        map.put(Integer.valueOf(ShotInfo.TAG_CAMERA_TEMPERATURE), "Camera Temperature");
        map.put(Integer.valueOf(ShotInfo.TAG_FLASH_GUIDE_NUMBER), "Flash Guide Number");
        map.put(Integer.valueOf(ShotInfo.TAG_AF_POINTS_IN_FOCUS), "AF Points in Focus");
        map.put(Integer.valueOf(ShotInfo.TAG_FLASH_EXPOSURE_BRACKETING), "Flash Exposure Compensation");
        map.put(Integer.valueOf(ShotInfo.TAG_AUTO_EXPOSURE_BRACKETING), "Auto Exposure Bracketing");
        map.put(Integer.valueOf(ShotInfo.TAG_AEB_BRACKET_VALUE), "AEB Bracket Value");
        map.put(Integer.valueOf(ShotInfo.TAG_CONTROL_MODE), "Control Mode");
        map.put(Integer.valueOf(ShotInfo.TAG_FOCUS_DISTANCE_UPPER), "Focus Distance Upper");
        map.put(Integer.valueOf(ShotInfo.TAG_FOCUS_DISTANCE_LOWER), "Focus Distance Lower");
        map.put(Integer.valueOf(ShotInfo.TAG_F_NUMBER), "F Number");
        map.put(Integer.valueOf(ShotInfo.TAG_EXPOSURE_TIME), "Exposure Time");
        map.put(Integer.valueOf(ShotInfo.TAG_MEASURED_EV_2), "Measured EV 2");
        map.put(Integer.valueOf(ShotInfo.TAG_BULB_DURATION), "Bulb Duration");
        map.put(Integer.valueOf(ShotInfo.TAG_CAMERA_TYPE), "Camera Type");
        map.put(Integer.valueOf(ShotInfo.TAG_AUTO_ROTATE), "Auto Rotate");
        map.put(Integer.valueOf(ShotInfo.TAG_ND_FILTER), "ND Filter");
        map.put(Integer.valueOf(ShotInfo.TAG_SELF_TIMER_2), "Self Timer 2");
        map.put(Integer.valueOf(ShotInfo.TAG_FLASH_OUTPUT), "Flash Output");
        map.put(Integer.valueOf(Panorama.TAG_PANORAMA_FRAME_NUMBER), "Panorama Frame Number");
        map.put(Integer.valueOf(Panorama.TAG_PANORAMA_DIRECTION), "Panorama Direction");
        map.put(Integer.valueOf(AFInfo.TAG_NUM_AF_POINTS), "AF Point Count");
        map.put(Integer.valueOf(AFInfo.TAG_VALID_AF_POINTS), "Valid AF Point Count");
        map.put(Integer.valueOf(AFInfo.TAG_IMAGE_WIDTH), "Image Width");
        map.put(Integer.valueOf(AFInfo.TAG_IMAGE_HEIGHT), "Image Height");
        map.put(Integer.valueOf(AFInfo.TAG_AF_IMAGE_WIDTH), "AF Image Width");
        map.put(Integer.valueOf(AFInfo.TAG_AF_IMAGE_HEIGHT), "AF Image Height");
        map.put(Integer.valueOf(AFInfo.TAG_AF_AREA_WIDTH), "AF Area Width");
        map.put(Integer.valueOf(AFInfo.TAG_AF_AREA_HEIGHT), "AF Area Height");
        map.put(Integer.valueOf(AFInfo.TAG_AF_AREA_X_POSITIONS), "AF Area X Positions");
        map.put(Integer.valueOf(AFInfo.TAG_AF_AREA_Y_POSITIONS), "AF Area Y Positions");
        map.put(Integer.valueOf(AFInfo.TAG_AF_POINTS_IN_FOCUS), "AF Points in Focus");
        map.put(Integer.valueOf(AFInfo.TAG_PRIMARY_AF_POINT_1), "Primary AF Point 1");
        map.put(Integer.valueOf(AFInfo.TAG_PRIMARY_AF_POINT_2), "Primary AF Point 2");
        map.put(19, "Thumbnail Image Valid Area");
        map.put(21, "Serial Number Format");
        map.put(26, "Super Macro");
        map.put(28, "Date Stamp Mode");
        map.put(29, "My Colors");
        map.put(30, "Firmware Revision");
        map.put(35, "Categories");
        map.put(36, "Face Detect Array 1");
        map.put(37, "Face Detect Array 2");
        map.put(38, "AF Info Array 2");
        map.put(40, "Image Unique ID");
        map.put(129, "Raw Data Offset");
        map.put(131, "Original Decision Data Offset");
        map.put(144, "Custom Functions (1D) Array");
        map.put(145, "Personal Functions Array");
        map.put(146, "Personal Function Values Array");
        map.put(147, "File Info Array");
        map.put(148, "AF Points in Focus (1D)");
        map.put(149, "Lens Model");
        map.put(150, "Serial Info Array");
        map.put(151, "Dust Removal Data");
        map.put(152, "Crop Info");
        map.put(153, "Custom Functions Array 2");
        map.put(154, "Aspect Information Array");
        map.put(160, "Processing Information Array");
        map.put(161, "Tone Curve Table");
        map.put(162, "Sharpness Table");
        map.put(163, "Sharpness Frequency Table");
        map.put(164, "White Balance Table");
        map.put(169, "Color Balance Array");
        map.put(170, "Measured Color Array");
        map.put(174, "Color Temperature");
        map.put(176, "Canon Flags Array");
        map.put(177, "Modified Information Array");
        map.put(178, "Tone Curve Matching");
        map.put(179, "White Balance Matching");
        map.put(180, "Color Space");
        map.put(182, "Preview Image Info Array");
        map.put(208, "VRD Offset");
        map.put(224, "Sensor Information Array");
        map.put(Integer.valueOf(TAG_COLOR_DATA_ARRAY_2), "Color Data Array 1");
        map.put(16386, "CRW Parameters");
        map.put(Integer.valueOf(TAG_COLOR_INFO_ARRAY_2), "Color Data Array 2");
        map.put(Integer.valueOf(TAG_BLACK_LEVEL), "Black Level");
        map.put(Integer.valueOf(TAG_CUSTOM_PICTURE_STYLE_FILE_NAME), "Custom Picture Style File Name");
        map.put(Integer.valueOf(TAG_COLOR_INFO_ARRAY), "Color Info Array");
        map.put(Integer.valueOf(TAG_VIGNETTING_CORRECTION_ARRAY_1), "Vignetting Correction Array 1");
        map.put(Integer.valueOf(TAG_VIGNETTING_CORRECTION_ARRAY_2), "Vignetting Correction Array 2");
        map.put(Integer.valueOf(TAG_LIGHTING_OPTIMIZER_ARRAY), "Lighting Optimizer Array");
        map.put(Integer.valueOf(TAG_LENS_INFO_ARRAY), "Lens Info Array");
        map.put(Integer.valueOf(TAG_AMBIANCE_INFO_ARRAY), "Ambiance Info Array");
        map.put(Integer.valueOf(TAG_FILTER_INFO_ARRAY), "Filter Info Array");
    }

    public CanonMakernoteDirectory() {
        setDescriptor(new CanonMakernoteDescriptor(this));
    }

    @Override // com.drew.metadata.Directory
    public void setObjectArray(int i, Object obj) {
        if (!(obj instanceof int[])) {
            super.setObjectArray(i, obj);
            return;
        }
        int i2 = 0;
        if (i == 1) {
            int[] iArr = (int[]) obj;
            while (i2 < iArr.length) {
                setInt(49408 + i2, iArr[i2]);
                i2++;
            }
            return;
        }
        if (i == 2) {
            int[] iArr2 = (int[]) obj;
            while (i2 < iArr2.length) {
                setInt(49664 + i2, iArr2[i2]);
                i2++;
            }
            return;
        }
        if (i == 4) {
            int[] iArr3 = (int[]) obj;
            while (i2 < iArr3.length) {
                setInt(50176 + i2, iArr3[i2]);
                i2++;
            }
            return;
        }
        if (i == 5) {
            int[] iArr4 = (int[]) obj;
            while (i2 < iArr4.length) {
                setInt(50432 + i2, iArr4[i2]);
                i2++;
            }
            return;
        }
        if (i == 18) {
            int[] iArr5 = (int[]) obj;
            int i3 = iArr5[0];
            int i4 = 0;
            int i5 = 0;
            while (i4 < iArr5.length) {
                int i6 = AFInfo.TAG_NUM_AF_POINTS + i5;
                if (i6 == 53768 || i6 == 53769) {
                    if (iArr5.length - 1 >= i4 + i3) {
                        short[] sArr = new short[i3];
                        for (int i7 = 0; i7 < i3; i7++) {
                            sArr[i7] = (short) iArr5[i4 + i7];
                        }
                        super.setObjectArray(i6, sArr);
                    }
                    i4 += i3 - 1;
                } else if (i6 == 53770) {
                    int i8 = (i3 + 15) / 16;
                    short[] sArr2 = new short[i8];
                    if (iArr5.length - 1 >= i4 + i8) {
                        for (int i9 = 0; i9 < i8; i9++) {
                            sArr2[i9] = (short) iArr5[i4 + i9];
                        }
                        super.setObjectArray(i6, sArr2);
                    }
                    i4 += i8 - 1;
                } else {
                    super.setObjectArray(i6, Integer.valueOf(iArr5[i4]));
                }
                i5++;
                i4++;
            }
            return;
        }
        super.setObjectArray(i, obj);
    }
}
