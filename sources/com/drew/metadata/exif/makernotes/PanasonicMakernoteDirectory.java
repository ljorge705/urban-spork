package com.drew.metadata.exif.makernotes;

import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.drew.lang.ByteArrayReader;
import com.drew.metadata.Age;
import com.drew.metadata.Directory;
import com.drew.metadata.Face;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class PanasonicMakernoteDirectory extends Directory {
    public static final int TAG_ACCELEROMETER_X = 141;
    public static final int TAG_ACCELEROMETER_Y = 142;
    public static final int TAG_ACCELEROMETER_Z = 140;
    public static final int TAG_ACCESSORY_SERIAL_NUMBER = 84;
    public static final int TAG_ACCESSORY_TYPE = 83;
    public static final int TAG_ADVANCED_SCENE_MODE = 61;
    public static final int TAG_AF_AREA_MODE = 15;
    public static final int TAG_AF_ASSIST_LAMP = 49;
    public static final int TAG_AF_POINT_POSITION = 77;
    public static final int TAG_AUDIO = 32;
    public static final int TAG_BABY_AGE = 51;
    public static final int TAG_BABY_AGE_1 = 32784;
    public static final int TAG_BABY_NAME = 102;
    public static final int TAG_BRACKET_SETTINGS = 69;
    public static final int TAG_BURST_MODE = 42;
    public static final int TAG_BURST_SPEED = 119;
    public static final int TAG_CAMERA_ORIENTATION = 143;
    public static final int TAG_CITY = 109;
    public static final int TAG_CITY2 = 128;
    public static final int TAG_CLEAR_RETOUCH = 124;
    public static final int TAG_CLEAR_RETOUCH_VALUE = 163;
    public static final int TAG_COLOR_EFFECT = 40;
    public static final int TAG_COLOR_MODE = 50;
    public static final int TAG_COLOR_TEMP_KELVIN = 68;
    public static final int TAG_CONTRAST = 57;
    public static final int TAG_CONTRAST_MODE = 44;
    public static final int TAG_CONVERSION_LENS = 53;
    public static final int TAG_COUNTRY = 105;
    public static final int TAG_EASY_MODE = 34;
    public static final int TAG_EXIF_VERSION = 38;
    public static final int TAG_FACES_DETECTED = 63;
    public static final int TAG_FACE_DETECTION_INFO = 78;
    public static final int TAG_FACE_RECOGNITION_INFO = 97;
    public static final int TAG_FILM_MODE = 66;
    public static final int TAG_FIRMWARE_VERSION = 2;
    public static final int TAG_FLASH_BIAS = 36;
    public static final int TAG_FLASH_CURTAIN = 72;
    public static final int TAG_FLASH_FIRED = 32775;
    public static final int TAG_FLASH_WARNING = 98;
    public static final int TAG_FOCUS_MODE = 7;
    public static final int TAG_HDR = 158;
    public static final int TAG_IMAGE_STABILIZATION = 26;
    public static final int TAG_INTELLIGENT_D_RANGE = 121;
    public static final int TAG_INTELLIGENT_EXPOSURE = 93;
    public static final int TAG_INTELLIGENT_RESOLUTION = 112;
    public static final int TAG_INTERNAL_ND_FILTER = 157;
    public static final int TAG_INTERNAL_SERIAL_NUMBER = 37;
    public static final int TAG_LANDMARK = 111;
    public static final int TAG_LENS_FIRMWARE_VERSION = 96;
    public static final int TAG_LENS_SERIAL_NUMBER = 82;
    public static final int TAG_LENS_TYPE = 81;
    public static final int TAG_LOCATION = 103;
    public static final int TAG_LONG_EXPOSURE_NOISE_REDUCTION = 73;
    public static final int TAG_MACRO_MODE = 28;
    public static final int TAG_MAKERNOTE_VERSION = 32768;
    public static final int TAG_NOISE_REDUCTION = 45;
    public static final int TAG_OPTICAL_ZOOM_MODE = 52;
    public static final int TAG_PANASONIC_IMAGE_HEIGHT = 76;
    public static final int TAG_PANASONIC_IMAGE_WIDTH = 75;
    public static final int TAG_PHOTO_STYLE = 137;
    public static final int TAG_PITCH_ANGLE = 145;
    public static final int TAG_PRINT_IMAGE_MATCHING_INFO = 3584;
    public static final int TAG_PROGRAM_ISO = 60;
    public static final int TAG_QUALITY_MODE = 1;
    public static final int TAG_RECOGNIZED_FACE_FLAGS = 99;
    public static final int TAG_RECORD_MODE = 31;
    public static final int TAG_ROLL_ANGLE = 144;
    public static final int TAG_ROTATION = 48;
    public static final int TAG_SATURATION = 64;
    public static final int TAG_SCENE_MODE = 32769;
    public static final int TAG_SELF_TIMER = 46;
    public static final int TAG_SEQUENCE_NUMBER = 43;
    public static final int TAG_SHADING_COMPENSATION = 138;
    public static final int TAG_SHARPNESS = 65;
    public static final int TAG_SHUTTER_TYPE = 159;
    public static final int TAG_STATE = 107;
    public static final int TAG_SWEEP_PANORAMA_DIRECTION = 147;
    public static final int TAG_SWEEP_PANORAMA_FIELD_OF_VIEW = 148;
    public static final int TAG_TEXT_STAMP = 59;
    public static final int TAG_TEXT_STAMP_1 = 62;
    public static final int TAG_TEXT_STAMP_2 = 32776;
    public static final int TAG_TEXT_STAMP_3 = 32777;
    public static final int TAG_TIMER_RECORDING = 150;
    public static final int TAG_TITLE = 101;
    public static final int TAG_TOUCH_AE = 171;
    public static final int TAG_TRANSFORM = 89;
    public static final int TAG_TRANSFORM_1 = 32786;
    public static final int TAG_TRAVEL_DAY = 54;
    public static final int TAG_UNKNOWN_DATA_DUMP = 33;
    public static final int TAG_UPTIME = 41;
    public static final int TAG_WB_ADJUST_AB = 70;
    public static final int TAG_WB_ADJUST_GM = 71;
    public static final int TAG_WB_BLUE_LEVEL = 32774;
    public static final int TAG_WB_GREEN_LEVEL = 32773;
    public static final int TAG_WB_RED_LEVEL = 32772;
    public static final int TAG_WHITE_BALANCE = 3;
    public static final int TAG_WHITE_BALANCE_BIAS = 35;
    public static final int TAG_WORLD_TIME_LOCATION = 58;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Panasonic Makernote";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "Quality Mode");
        map.put(2, Constants.CLTAP_APP_VERSION);
        map.put(3, "White Balance");
        map.put(7, "Focus Mode");
        map.put(15, "AF Area Mode");
        map.put(26, "Image Stabilization");
        map.put(28, "Macro Mode");
        map.put(31, "Record Mode");
        map.put(32, "Audio");
        map.put(37, "Internal Serial Number");
        map.put(33, "Unknown Data Dump");
        map.put(34, "Easy Mode");
        map.put(35, "White Balance Bias");
        map.put(36, "Flash Bias");
        map.put(38, "Exif Version");
        map.put(40, "Color Effect");
        map.put(41, "Camera Uptime");
        map.put(42, "Burst Mode");
        map.put(43, "Sequence Number");
        map.put(44, "Contrast Mode");
        map.put(45, "Noise Reduction");
        map.put(46, "Self Timer");
        map.put(48, "Rotation");
        map.put(49, "AF Assist Lamp");
        map.put(50, "Color Mode");
        map.put(51, "Baby Age");
        map.put(52, "Optical Zoom Mode");
        map.put(53, "Conversion Lens");
        map.put(54, "Travel Day");
        map.put(57, ExifInterface.TAG_CONTRAST);
        map.put(58, "World Time Location");
        map.put(59, "Text Stamp");
        map.put(60, "Program ISO");
        map.put(61, "Advanced Scene Mode");
        map.put(3584, "Print Image Matching (PIM) Info");
        map.put(63, "Number of Detected Faces");
        map.put(64, ExifInterface.TAG_SATURATION);
        map.put(65, ExifInterface.TAG_SHARPNESS);
        map.put(66, "Film Mode");
        map.put(68, "Color Temp Kelvin");
        map.put(69, "Bracket Settings");
        map.put(70, "White Balance Adjust (AB)");
        map.put(71, "White Balance Adjust (GM)");
        map.put(72, "Flash Curtain");
        map.put(73, "Long Exposure Noise Reduction");
        map.put(75, "Panasonic Image Width");
        map.put(76, "Panasonic Image Height");
        map.put(77, "Af Point Position");
        map.put(78, "Face Detection Info");
        map.put(81, "Lens Type");
        map.put(82, "Lens Serial Number");
        map.put(83, "Accessory Type");
        map.put(84, "Accessory Serial Number");
        map.put(89, "Transform");
        map.put(93, "Intelligent Exposure");
        map.put(96, "Lens Firmware Version");
        map.put(97, "Face Recognition Info");
        map.put(98, "Flash Warning");
        map.put(99, "Recognized Face Flags");
        map.put(101, "Title");
        map.put(102, "Baby Name");
        map.put(103, HttpHeaders.LOCATION);
        map.put(105, "Country");
        map.put(107, "State");
        map.put(109, "City");
        map.put(111, "Landmark");
        map.put(112, "Intelligent Resolution");
        map.put(119, "Burst Speed");
        map.put(Integer.valueOf(TAG_INTELLIGENT_D_RANGE), "Intelligent D-Range");
        map.put(Integer.valueOf(TAG_CLEAR_RETOUCH), "Clear Retouch");
        map.put(128, "City 2");
        map.put(137, "Photo Style");
        map.put(138, "Shading Compensation");
        map.put(140, "Accelerometer Z");
        map.put(141, "Accelerometer X");
        map.put(142, "Accelerometer Y");
        map.put(143, "Camera Orientation");
        map.put(144, "Roll Angle");
        map.put(145, "Pitch Angle");
        map.put(147, "Sweep Panorama Direction");
        map.put(148, "Sweep Panorama Field Of View");
        map.put(150, "Timer Recording");
        map.put(157, "Internal ND Filter");
        map.put(158, "HDR");
        map.put(159, "Shutter Type");
        map.put(163, "Clear Retouch Value");
        map.put(171, "Touch AE");
        map.put(32768, "Makernote Version");
        map.put(Integer.valueOf(TAG_SCENE_MODE), "Scene Mode");
        map.put(Integer.valueOf(TAG_WB_RED_LEVEL), "White Balance (Red)");
        map.put(32773, "White Balance (Green)");
        map.put(Integer.valueOf(TAG_WB_BLUE_LEVEL), "White Balance (Blue)");
        map.put(Integer.valueOf(TAG_FLASH_FIRED), "Flash Fired");
        map.put(62, "Text Stamp 1");
        map.put(Integer.valueOf(TAG_TEXT_STAMP_2), "Text Stamp 2");
        map.put(Integer.valueOf(TAG_TEXT_STAMP_3), "Text Stamp 3");
        map.put(Integer.valueOf(TAG_BABY_AGE_1), "Baby Age 1");
        map.put(Integer.valueOf(TAG_TRANSFORM_1), "Transform 1");
    }

    public PanasonicMakernoteDirectory() {
        setDescriptor(new PanasonicMakernoteDescriptor(this));
    }

    public Face[] getDetectedFaces() {
        byte[] byteArray = getByteArray(78);
        if (byteArray == null) {
            return null;
        }
        ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
        byteArrayReader.setMotorolaByteOrder(false);
        try {
            int uInt16 = byteArrayReader.getUInt16(0);
            if (uInt16 == 0) {
                return null;
            }
            Face[] faceArr = new Face[uInt16];
            for (int i = 0; i < uInt16; i++) {
                int i2 = i * 8;
                faceArr[i] = new Face(byteArrayReader.getUInt16(i2 + 2), byteArrayReader.getUInt16(i2 + 4), byteArrayReader.getUInt16(i2 + 6), byteArrayReader.getUInt16(i2 + 8), null, null);
            }
            return faceArr;
        } catch (IOException unused) {
            return null;
        }
    }

    public Face[] getRecognizedFaces() {
        byte[] byteArray = getByteArray(97);
        if (byteArray == null) {
            return null;
        }
        ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
        byteArrayReader.setMotorolaByteOrder(false);
        try {
            int uInt16 = byteArrayReader.getUInt16(0);
            if (uInt16 == 0) {
                return null;
            }
            Face[] faceArr = new Face[uInt16];
            for (int i = 0; i < uInt16; i++) {
                int i2 = i * 44;
                faceArr[i] = new Face(byteArrayReader.getUInt16(i2 + 24), byteArrayReader.getUInt16(i2 + 26), byteArrayReader.getUInt16(i2 + 28), byteArrayReader.getUInt16(i2 + 30), byteArrayReader.getString(i2 + 4, 20, "ASCII").trim(), Age.fromPanasonicString(byteArrayReader.getString(i2 + 32, 20, "ASCII").trim()));
            }
            return faceArr;
        } catch (IOException unused) {
            return null;
        }
    }

    public Age getAge(int i) {
        String string = getString(i);
        if (string == null) {
            return null;
        }
        return Age.fromPanasonicString(string);
    }
}
