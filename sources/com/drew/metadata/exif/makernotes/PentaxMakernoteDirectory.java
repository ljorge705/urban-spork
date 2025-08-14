package com.drew.metadata.exif.makernotes;

import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class PentaxMakernoteDirectory extends Directory {
    public static final int TAG_CAPTURE_MODE = 1;
    public static final int TAG_COLOUR = 23;
    public static final int TAG_CONTRAST = 12;
    public static final int TAG_DAYLIGHT_SAVINGS = 4097;
    public static final int TAG_DIGITAL_ZOOM = 10;
    public static final int TAG_FLASH_MODE = 4;
    public static final int TAG_FOCUS_MODE = 3;
    public static final int TAG_ISO_SPEED = 20;
    public static final int TAG_PRINT_IMAGE_MATCHING_INFO = 3584;
    public static final int TAG_QUALITY_LEVEL = 2;
    public static final int TAG_SATURATION = 13;
    public static final int TAG_SHARPNESS = 11;
    public static final int TAG_TIME_ZONE = 4096;
    public static final int TAG_WHITE_BALANCE = 7;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Pentax Makernote";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "Capture Mode");
        map.put(2, "Quality Level");
        map.put(3, "Focus Mode");
        map.put(4, "Flash Mode");
        map.put(7, "White Balance");
        map.put(10, "Digital Zoom");
        map.put(11, ExifInterface.TAG_SHARPNESS);
        map.put(12, ExifInterface.TAG_CONTRAST);
        map.put(13, ExifInterface.TAG_SATURATION);
        map.put(20, "ISO Speed");
        map.put(23, "Colour");
        map.put(3584, "Print Image Matching (PIM) Info");
        map.put(4096, "Time Zone");
        map.put(4097, "Daylight Savings");
    }

    public PentaxMakernoteDirectory() {
        setDescriptor(new PentaxMakernoteDescriptor(this));
    }
}
