package com.drew.metadata.exif.makernotes;

import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CasioType2MakernoteDirectory extends Directory {
    public static final int TAG_BESTSHOT_MODE = 12295;
    public static final int TAG_CCD_ISO_SENSITIVITY = 12308;
    public static final int TAG_COLOUR_MODE = 12309;
    public static final int TAG_CONTRAST = 32;
    public static final int TAG_ENHANCEMENT = 12310;
    public static final int TAG_FILTER = 12311;
    public static final int TAG_FLASH_DISTANCE = 8244;
    public static final int TAG_FOCAL_LENGTH = 29;
    public static final int TAG_FOCUS_MODE_1 = 13;
    public static final int TAG_FOCUS_MODE_2 = 12291;
    public static final int TAG_IMAGE_SIZE = 9;
    public static final int TAG_ISO_SENSITIVITY = 20;
    public static final int TAG_OBJECT_DISTANCE = 8226;
    public static final int TAG_PREVIEW_THUMBNAIL = 8192;
    public static final int TAG_PRINT_IMAGE_MATCHING_INFO = 3584;
    public static final int TAG_QUALITY = 12290;
    public static final int TAG_QUALITY_MODE = 8;
    public static final int TAG_RECORD_MODE = 12288;
    public static final int TAG_SATURATION = 31;
    public static final int TAG_SELF_TIMER = 12289;
    public static final int TAG_SHARPNESS = 33;
    public static final int TAG_THUMBNAIL_DIMENSIONS = 2;
    public static final int TAG_THUMBNAIL_OFFSET = 4;
    public static final int TAG_THUMBNAIL_SIZE = 3;
    public static final int TAG_TIME_ZONE = 12294;
    public static final int TAG_WHITE_BALANCE_1 = 25;
    public static final int TAG_WHITE_BALANCE_2 = 8210;
    public static final int TAG_WHITE_BALANCE_BIAS = 8209;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Casio Makernote";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(2, "Thumbnail Dimensions");
        map.put(3, "Thumbnail Size");
        map.put(4, "Thumbnail Offset");
        map.put(8, "Quality Mode");
        map.put(9, "Image Size");
        map.put(13, "Focus Mode");
        map.put(20, "ISO Sensitivity");
        map.put(25, "White Balance");
        map.put(29, "Focal Length");
        map.put(31, ExifInterface.TAG_SATURATION);
        map.put(32, ExifInterface.TAG_CONTRAST);
        map.put(33, ExifInterface.TAG_SHARPNESS);
        map.put(3584, "Print Image Matching (PIM) Info");
        map.put(8192, "Casio Preview Thumbnail");
        map.put(8209, "White Balance Bias");
        map.put(8210, "White Balance");
        map.put(8226, "Object Distance");
        map.put(Integer.valueOf(TAG_FLASH_DISTANCE), "Flash Distance");
        map.put(12288, "Record Mode");
        map.put(Integer.valueOf(TAG_SELF_TIMER), "Self Timer");
        map.put(Integer.valueOf(TAG_QUALITY), "Quality");
        map.put(Integer.valueOf(TAG_FOCUS_MODE_2), "Focus Mode");
        map.put(Integer.valueOf(TAG_TIME_ZONE), "Time Zone");
        map.put(Integer.valueOf(TAG_BESTSHOT_MODE), "BestShot Mode");
        map.put(Integer.valueOf(TAG_CCD_ISO_SENSITIVITY), "CCD ISO Sensitivity");
        map.put(Integer.valueOf(TAG_COLOUR_MODE), "Colour Mode");
        map.put(Integer.valueOf(TAG_ENHANCEMENT), "Enhancement");
        map.put(Integer.valueOf(TAG_FILTER), "Filter");
    }

    public CasioType2MakernoteDirectory() {
        setDescriptor(new CasioType2MakernoteDescriptor(this));
    }
}
