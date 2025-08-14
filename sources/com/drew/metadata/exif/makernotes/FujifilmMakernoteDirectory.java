package com.drew.metadata.exif.makernotes;

import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class FujifilmMakernoteDirectory extends Directory {
    public static final int TAG_AUTO_BRACKETING = 4352;
    public static final int TAG_AUTO_DYNAMIC_RANGE = 5131;
    public static final int TAG_AUTO_EXPOSURE_WARNING = 4866;
    public static final int TAG_BLUR_WARNING = 4864;
    public static final int TAG_COLOR_SATURATION = 4099;
    public static final int TAG_COLOR_TEMPERATURE = 4101;
    public static final int TAG_CONTRAST = 4102;
    public static final int TAG_DEVELOPMENT_DYNAMIC_RANGE = 5123;
    public static final int TAG_DYNAMIC_RANGE = 5120;
    public static final int TAG_DYNAMIC_RANGE_SETTING = 5122;
    public static final int TAG_EXR_AUTO = 4147;
    public static final int TAG_EXR_MODE = 4148;
    public static final int TAG_FACES_DETECTED = 16640;
    public static final int TAG_FACE_POSITIONS = 16643;
    public static final int TAG_FACE_REC_INFO = 17026;
    public static final int TAG_FILE_SOURCE = 32768;
    public static final int TAG_FILM_MODE = 5121;
    public static final int TAG_FINE_PIX_COLOR = 4624;
    public static final int TAG_FLASH_EV = 4113;
    public static final int TAG_FLASH_MODE = 4112;
    public static final int TAG_FOCUS_MODE = 4129;
    public static final int TAG_FOCUS_PIXEL = 4131;
    public static final int TAG_FOCUS_WARNING = 4865;
    public static final int TAG_FRAME_NUMBER = 32771;
    public static final int TAG_GE_IMAGE_SIZE = 4868;
    public static final int TAG_HIGH_ISO_NOISE_REDUCTION = 4110;
    public static final int TAG_MACRO = 4128;
    public static final int TAG_MAKERNOTE_VERSION = 0;
    public static final int TAG_MAX_APERTURE_AT_MAX_FOCAL = 5127;
    public static final int TAG_MAX_APERTURE_AT_MIN_FOCAL = 5126;
    public static final int TAG_MAX_FOCAL_LENGTH = 5125;
    public static final int TAG_MIN_FOCAL_LENGTH = 5124;
    public static final int TAG_NOISE_REDUCTION = 4107;
    public static final int TAG_ORDER_NUMBER = 32770;
    public static final int TAG_PARALLAX = 45585;
    public static final int TAG_PICTURE_MODE = 4145;
    public static final int TAG_QUALITY = 4096;
    public static final int TAG_SEQUENCE_NUMBER = 4353;
    public static final int TAG_SERIAL_NUMBER = 16;
    public static final int TAG_SHARPNESS = 4097;
    public static final int TAG_SLOW_SYNC = 4144;
    public static final int TAG_TONE = 4100;
    public static final int TAG_WHITE_BALANCE = 4098;
    public static final int TAG_WHITE_BALANCE_FINE_TUNE = 4106;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Fujifilm Makernote";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(0, "Makernote Version");
        map.put(16, "Serial Number");
        map.put(4096, "Quality");
        map.put(4097, ExifInterface.TAG_SHARPNESS);
        map.put(4098, "White Balance");
        map.put(4099, "Color Saturation");
        map.put(4100, "Tone (Contrast)");
        map.put(4101, "Color Temperature");
        map.put(4102, ExifInterface.TAG_CONTRAST);
        map.put(4106, "White Balance Fine Tune");
        map.put(4107, "Noise Reduction");
        map.put(4110, "High ISO Noise Reduction");
        map.put(4112, "Flash Mode");
        map.put(4113, "Flash Strength");
        map.put(4128, "Macro");
        map.put(4129, "Focus Mode");
        map.put(4131, "Focus Pixel");
        map.put(4144, "Slow Sync");
        map.put(4145, "Picture Mode");
        map.put(4147, "EXR Auto");
        map.put(4148, "EXR Mode");
        map.put(Integer.valueOf(TAG_AUTO_BRACKETING), "Auto Bracketing");
        map.put(Integer.valueOf(TAG_SEQUENCE_NUMBER), "Sequence Number");
        map.put(Integer.valueOf(TAG_FINE_PIX_COLOR), "FinePix Color Setting");
        map.put(Integer.valueOf(TAG_BLUR_WARNING), "Blur Warning");
        map.put(Integer.valueOf(TAG_FOCUS_WARNING), "Focus Warning");
        map.put(Integer.valueOf(TAG_AUTO_EXPOSURE_WARNING), "AE Warning");
        map.put(Integer.valueOf(TAG_GE_IMAGE_SIZE), "GE Image Size");
        map.put(Integer.valueOf(TAG_DYNAMIC_RANGE), "Dynamic Range");
        map.put(Integer.valueOf(TAG_FILM_MODE), "Film Mode");
        map.put(Integer.valueOf(TAG_DYNAMIC_RANGE_SETTING), "Dynamic Range Setting");
        map.put(Integer.valueOf(TAG_DEVELOPMENT_DYNAMIC_RANGE), "Development Dynamic Range");
        map.put(Integer.valueOf(TAG_MIN_FOCAL_LENGTH), "Minimum Focal Length");
        map.put(Integer.valueOf(TAG_MAX_FOCAL_LENGTH), "Maximum Focal Length");
        map.put(Integer.valueOf(TAG_MAX_APERTURE_AT_MIN_FOCAL), "Maximum Aperture at Minimum Focal Length");
        map.put(Integer.valueOf(TAG_MAX_APERTURE_AT_MAX_FOCAL), "Maximum Aperture at Maximum Focal Length");
        map.put(Integer.valueOf(TAG_AUTO_DYNAMIC_RANGE), "Auto Dynamic Range");
        map.put(Integer.valueOf(TAG_FACES_DETECTED), "Faces Detected");
        map.put(Integer.valueOf(TAG_FACE_POSITIONS), "Face Positions");
        map.put(17026, "Face Detection Data");
        map.put(32768, "File Source");
        map.put(Integer.valueOf(TAG_ORDER_NUMBER), "Order Number");
        map.put(Integer.valueOf(TAG_FRAME_NUMBER), "Frame Number");
        map.put(Integer.valueOf(TAG_PARALLAX), "Parallax");
    }

    public FujifilmMakernoteDirectory() {
        setDescriptor(new FujifilmMakernoteDescriptor(this));
    }
}
