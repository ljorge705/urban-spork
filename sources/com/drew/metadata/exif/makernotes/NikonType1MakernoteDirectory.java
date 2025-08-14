package com.drew.metadata.exif.makernotes;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class NikonType1MakernoteDirectory extends Directory {
    public static final int TAG_CCD_SENSITIVITY = 6;
    public static final int TAG_COLOR_MODE = 4;
    public static final int TAG_CONVERTER = 11;
    public static final int TAG_DIGITAL_ZOOM = 10;
    public static final int TAG_FOCUS = 8;
    public static final int TAG_IMAGE_ADJUSTMENT = 5;
    public static final int TAG_QUALITY = 3;
    public static final int TAG_UNKNOWN_1 = 2;
    public static final int TAG_UNKNOWN_2 = 9;
    public static final int TAG_UNKNOWN_3 = 3840;
    public static final int TAG_WHITE_BALANCE = 7;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Nikon Makernote";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(6, "CCD Sensitivity");
        map.put(4, "Color Mode");
        map.put(10, "Digital Zoom");
        map.put(11, "Fisheye Converter");
        map.put(8, "Focus");
        map.put(5, "Image Adjustment");
        map.put(3, "Quality");
        map.put(2, "Makernote Unknown 1");
        map.put(9, "Makernote Unknown 2");
        map.put(3840, "Makernote Unknown 3");
        map.put(7, "White Balance");
    }

    public NikonType1MakernoteDirectory() {
        setDescriptor(new NikonType1MakernoteDescriptor(this));
    }
}
