package com.drew.metadata.exif.makernotes;

import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class KodakMakernoteDirectory extends Directory {
    public static final int TAG_BURST_MODE = 10;
    public static final int TAG_BURST_MODE_2 = 24;
    public static final int TAG_COLOR_MODE = 102;
    public static final int TAG_DATE_TIME_STAMP = 100;
    public static final int TAG_DIGITAL_ZOOM = 104;
    public static final int TAG_EXPOSURE_COMPENSATION = 36;
    public static final int TAG_EXPOSURE_TIME = 32;
    public static final int TAG_FLASH_FIRED = 93;
    public static final int TAG_FLASH_MODE = 92;
    public static final int TAG_FOCUS_MODE = 56;
    public static final int TAG_F_NUMBER = 30;
    public static final int TAG_IMAGE_HEIGHT = 14;
    public static final int TAG_IMAGE_WIDTH = 12;
    public static final int TAG_ISO = 96;
    public static final int TAG_ISO_SETTING = 94;
    public static final int TAG_KODAK_MODEL = 0;
    public static final int TAG_METERING_MODE = 28;
    public static final int TAG_MONTH_DAY_CREATED = 18;
    public static final int TAG_QUALITY = 9;
    public static final int TAG_SEQUENCE_NUMBER = 29;
    public static final int TAG_SHARPNESS = 107;
    public static final int TAG_SHUTTER_MODE = 27;
    public static final int TAG_TIME_CREATED = 20;
    public static final int TAG_TOTAL_ZOOM = 98;
    public static final int TAG_WHITE_BALANCE = 64;
    public static final int TAG_YEAR_CREATED = 16;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Kodak Makernote";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(0, "Kodak Model");
        map.put(9, "Quality");
        map.put(10, "Burst Mode");
        map.put(12, "Image Width");
        map.put(14, "Image Height");
        map.put(16, "Year Created");
        map.put(18, "Month/Day Created");
        map.put(20, "Time Created");
        map.put(24, "Burst Mode 2");
        map.put(27, "Shutter Speed");
        map.put(28, "Metering Mode");
        map.put(29, "Sequence Number");
        map.put(30, "F Number");
        map.put(32, "Exposure Time");
        map.put(36, "Exposure Compensation");
        map.put(56, "Focus Mode");
        map.put(64, "White Balance");
        map.put(92, "Flash Mode");
        map.put(93, "Flash Fired");
        map.put(94, "ISO Setting");
        map.put(96, ExifInterface.TAG_RW2_ISO);
        map.put(98, "Total Zoom");
        map.put(100, "Date/Time Stamp");
        map.put(102, "Color Mode");
        map.put(104, "Digital Zoom");
        map.put(107, ExifInterface.TAG_SHARPNESS);
    }

    public KodakMakernoteDirectory() {
        setDescriptor(new KodakMakernoteDescriptor(this));
    }
}
