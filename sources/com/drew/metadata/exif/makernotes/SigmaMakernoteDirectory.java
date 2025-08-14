package com.drew.metadata.exif.makernotes;

import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class SigmaMakernoteDirectory extends Directory {
    public static final int TAG_ADJUSTMENT_MODE = 21;
    public static final int TAG_AUTO_BRACKET = 25;
    public static final int TAG_AUTO_FOCUS_MODE = 5;
    public static final int TAG_COLOR_ADJUSTMENT = 20;
    public static final int TAG_COLOR_SPACE = 11;
    public static final int TAG_CONTRAST = 13;
    public static final int TAG_DRIVE_MODE = 3;
    public static final int TAG_EXPOSURE = 12;
    public static final int TAG_EXPOSURE_MODE = 8;
    public static final int TAG_FILL_LIGHT = 18;
    public static final int TAG_FIRMWARE = 23;
    public static final int TAG_FOCUS_SETTING = 6;
    public static final int TAG_HIGHLIGHT = 15;
    public static final int TAG_LENS_RANGE = 10;
    public static final int TAG_METERING_MODE = 9;
    public static final int TAG_QUALITY = 22;
    public static final int TAG_RESOLUTION_MODE = 4;
    public static final int TAG_SATURATION = 16;
    public static final int TAG_SERIAL_NUMBER = 2;
    public static final int TAG_SHADOW = 14;
    public static final int TAG_SHARPNESS = 17;
    public static final int TAG_SOFTWARE = 24;
    public static final int TAG_WHITE_BALANCE = 7;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Sigma Makernote";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(2, "Serial Number");
        map.put(3, "Drive Mode");
        map.put(4, "Resolution Mode");
        map.put(5, "Auto Focus Mode");
        map.put(6, "Focus Setting");
        map.put(7, "White Balance");
        map.put(8, "Exposure Mode");
        map.put(9, "Metering Mode");
        map.put(10, "Lens Range");
        map.put(11, "Color Space");
        map.put(12, "Exposure");
        map.put(13, ExifInterface.TAG_CONTRAST);
        map.put(14, "Shadow");
        map.put(15, "Highlight");
        map.put(16, ExifInterface.TAG_SATURATION);
        map.put(17, ExifInterface.TAG_SHARPNESS);
        map.put(18, "Fill Light");
        map.put(20, "Color Adjustment");
        map.put(21, "Adjustment Mode");
        map.put(22, "Quality");
        map.put(23, "Firmware");
        map.put(24, ExifInterface.TAG_SOFTWARE);
        map.put(25, "Auto Bracket");
    }

    public SigmaMakernoteDirectory() {
        setDescriptor(new SigmaMakernoteDescriptor(this));
    }
}
