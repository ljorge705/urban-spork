package com.drew.metadata.mov.media;

import com.google.common.net.HttpHeaders;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class QuickTimeVideoDirectory extends QuickTimeMediaDirectory {
    public static final int TAG_COLOR_TABLE = 13;
    public static final int TAG_COMPRESSION_TYPE = 10;
    public static final int TAG_COMPRESSOR_NAME = 8;
    public static final int TAG_DEPTH = 9;
    public static final int TAG_FRAME_RATE = 14;
    public static final int TAG_GRAPHICS_MODE = 11;
    public static final int TAG_HEIGHT = 5;
    public static final int TAG_HORIZONTAL_RESOLUTION = 6;
    public static final int TAG_OPCOLOR = 12;
    public static final int TAG_SPATIAL_QUALITY = 3;
    public static final int TAG_TEMPORAL_QUALITY = 2;
    public static final int TAG_VENDOR = 1;
    public static final int TAG_VERTICAL_RESOLUTION = 7;
    public static final int TAG_WIDTH = 4;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.mov.QuickTimeDirectory, com.drew.metadata.Directory
    public String getName() {
        return "QuickTime Video";
    }

    @Override // com.drew.metadata.mov.QuickTimeDirectory, com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    public QuickTimeVideoDirectory() {
        setDescriptor(new QuickTimeVideoDescriptor(this));
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        QuickTimeMediaDirectory.addQuickTimeMediaTags(map);
        map.put(1, "Vendor");
        map.put(2, "Temporal Quality");
        map.put(3, "Spatial Quality");
        map.put(4, HttpHeaders.WIDTH);
        map.put(5, "Height");
        map.put(6, "Horizontal Resolution");
        map.put(7, "Vertical Resolution");
        map.put(8, "Compressor Name");
        map.put(9, "Depth");
        map.put(10, "Compression Type");
        map.put(11, "Graphics Mode");
        map.put(12, "Opcolor");
        map.put(13, "Color Table");
        map.put(14, "Frame Rate");
    }
}
