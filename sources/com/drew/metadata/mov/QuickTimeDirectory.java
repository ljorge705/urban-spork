package com.drew.metadata.mov;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class QuickTimeDirectory extends Directory {
    public static final int TAG_CANON_THUMBNAIL_DT = 8192;
    public static final int TAG_COMPATIBLE_BRANDS = 4098;
    public static final int TAG_CREATION_TIME = 256;
    public static final int TAG_CURRENT_TIME = 268;
    public static final int TAG_DURATION = 259;
    public static final int TAG_DURATION_SECONDS = 260;
    public static final int TAG_MAJOR_BRAND = 4096;
    public static final int TAG_MEDIA_TIME_SCALE = 774;
    public static final int TAG_MINOR_VERSION = 4097;
    public static final int TAG_MODIFICATION_TIME = 257;
    public static final int TAG_NEXT_TRACK_ID = 269;
    public static final int TAG_POSTER_TIME = 265;
    public static final int TAG_PREFERRED_RATE = 261;
    public static final int TAG_PREFERRED_VOLUME = 262;
    public static final int TAG_PREVIEW_DURATION = 264;
    public static final int TAG_PREVIEW_TIME = 263;
    public static final int TAG_SELECTION_DURATION = 267;
    public static final int TAG_SELECTION_TIME = 266;
    public static final int TAG_TIME_SCALE = 258;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "QuickTime";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(4096, "Major Brand");
        map.put(4097, "Minor Version");
        map.put(4098, "Compatible Brands");
        map.put(256, "Creation Time");
        map.put(257, "Modification Time");
        map.put(258, "Media Time Scale");
        map.put(259, "Duration");
        map.put(260, "Duration in Seconds");
        map.put(261, "Preferred Rate");
        map.put(262, "Preferred Volume");
        map.put(263, "Preview Time");
        map.put(264, "Preview Duration");
        map.put(265, "Poster Time");
        map.put(266, "Selection Time");
        map.put(267, "Selection Duration");
        map.put(268, "Current Time");
        map.put(269, "Next Track ID");
        map.put(774, "Media Time Scale");
        map.put(8192, "Canon Thumbnail DateTime");
    }

    public QuickTimeDirectory() {
        setDescriptor(new QuickTimeDescriptor(this));
    }
}
