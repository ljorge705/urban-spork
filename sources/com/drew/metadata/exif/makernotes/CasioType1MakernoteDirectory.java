package com.drew.metadata.exif.makernotes;

import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CasioType1MakernoteDirectory extends Directory {
    public static final int TAG_CCD_SENSITIVITY = 20;
    public static final int TAG_CONTRAST = 12;
    public static final int TAG_DIGITAL_ZOOM = 10;
    public static final int TAG_FLASH_INTENSITY = 5;
    public static final int TAG_FLASH_MODE = 4;
    public static final int TAG_FOCUSING_MODE = 3;
    public static final int TAG_OBJECT_DISTANCE = 6;
    public static final int TAG_QUALITY = 2;
    public static final int TAG_RECORDING_MODE = 1;
    public static final int TAG_SATURATION = 13;
    public static final int TAG_SHARPNESS = 11;
    public static final int TAG_UNKNOWN_1 = 8;
    public static final int TAG_UNKNOWN_2 = 9;
    public static final int TAG_UNKNOWN_3 = 14;
    public static final int TAG_UNKNOWN_4 = 15;
    public static final int TAG_UNKNOWN_5 = 16;
    public static final int TAG_UNKNOWN_6 = 17;
    public static final int TAG_UNKNOWN_7 = 18;
    public static final int TAG_UNKNOWN_8 = 19;
    public static final int TAG_WHITE_BALANCE = 7;
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
        map.put(20, "CCD Sensitivity");
        map.put(12, ExifInterface.TAG_CONTRAST);
        map.put(10, "Digital Zoom");
        map.put(5, "Flash Intensity");
        map.put(4, "Flash Mode");
        map.put(3, "Focusing Mode");
        map.put(6, "Object Distance");
        map.put(2, "Quality");
        map.put(1, "Recording Mode");
        map.put(13, ExifInterface.TAG_SATURATION);
        map.put(11, ExifInterface.TAG_SHARPNESS);
        map.put(8, "Makernote Unknown 1");
        map.put(9, "Makernote Unknown 2");
        map.put(14, "Makernote Unknown 3");
        map.put(15, "Makernote Unknown 4");
        map.put(16, "Makernote Unknown 5");
        map.put(17, "Makernote Unknown 6");
        map.put(18, "Makernote Unknown 7");
        map.put(19, "Makernote Unknown 8");
        map.put(7, "White Balance");
    }

    public CasioType1MakernoteDirectory() {
        setDescriptor(new CasioType1MakernoteDescriptor(this));
    }
}
