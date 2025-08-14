package com.drew.metadata.mp4;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class Mp4Directory extends Directory {
    public static final int TAG_COMPATIBLE_BRANDS = 3;
    public static final int TAG_CREATION_TIME = 256;
    public static final int TAG_CURRENT_TIME = 269;
    public static final int TAG_DURATION = 259;
    public static final int TAG_DURATION_SECONDS = 260;
    public static final int TAG_MAJOR_BRAND = 1;
    public static final int TAG_MEDIA_TIME_SCALE = 774;
    public static final int TAG_MINOR_VERSION = 2;
    public static final int TAG_MODIFICATION_TIME = 257;
    public static final int TAG_NEXT_TRACK_ID = 270;
    public static final int TAG_POSTER_TIME = 266;
    public static final int TAG_PREFERRED_RATE = 261;
    public static final int TAG_PREFERRED_VOLUME = 262;
    public static final int TAG_PREVIEW_DURATION = 265;
    public static final int TAG_PREVIEW_TIME = 264;
    public static final int TAG_ROTATION = 512;
    public static final int TAG_SELECTION_DURATION = 268;
    public static final int TAG_SELECTION_TIME = 267;
    public static final int TAG_TIME_SCALE = 258;
    public static final int TAG_TRANSFORMATION_MATRIX = 271;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "MP4";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "Major Brand");
        map.put(2, "Minor Version");
        map.put(3, "Compatible Brands");
        map.put(256, "Creation Time");
        map.put(257, "Modification Time");
        map.put(258, "Media Time Scale");
        map.put(259, "Duration");
        map.put(260, "Duration in Seconds");
        map.put(261, "Preferred Rate");
        map.put(262, "Preferred Volume");
        map.put(264, "Preview Time");
        map.put(265, "Preview Duration");
        map.put(266, "Poster Time");
        map.put(267, "Selection Time");
        map.put(268, "Selection Duration");
        map.put(269, "Current Time");
        map.put(270, "Next Track ID");
        map.put(271, "Transformation Matrix");
        map.put(512, "Rotation");
        map.put(774, "Media Time Scale");
    }

    public Mp4Directory() {
        setDescriptor(new Mp4Descriptor(this));
    }
}
