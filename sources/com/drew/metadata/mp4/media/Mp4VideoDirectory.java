package com.drew.metadata.mp4.media;

import com.google.common.net.HttpHeaders;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class Mp4VideoDirectory extends Mp4MediaDirectory {
    public static final int TAG_COLOR_TABLE = 213;
    public static final int TAG_COMPRESSION_TYPE = 210;
    public static final int TAG_COMPRESSOR_NAME = 208;
    public static final int TAG_DEPTH = 209;
    public static final int TAG_FRAME_RATE = 214;
    public static final int TAG_GRAPHICS_MODE = 211;
    public static final int TAG_HEIGHT = 205;
    public static final int TAG_HORIZONTAL_RESOLUTION = 206;
    public static final int TAG_OPCOLOR = 212;
    public static final int TAG_SPATIAL_QUALITY = 203;
    public static final int TAG_TEMPORAL_QUALITY = 202;
    public static final int TAG_VENDOR = 201;
    public static final int TAG_VERTICAL_RESOLUTION = 207;
    public static final int TAG_WIDTH = 204;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.mp4.Mp4Directory, com.drew.metadata.Directory
    public String getName() {
        return "MP4 Video";
    }

    @Override // com.drew.metadata.mp4.Mp4Directory, com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    public Mp4VideoDirectory() {
        setDescriptor(new Mp4VideoDescriptor(this));
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        Mp4MediaDirectory.addMp4MediaTags(map);
        map.put(201, "Vendor");
        map.put(202, "Temporal Quality");
        map.put(203, "Spatial Quality");
        map.put(204, HttpHeaders.WIDTH);
        map.put(205, "Height");
        map.put(206, "Horizontal Resolution");
        map.put(207, "Vertical Resolution");
        map.put(208, "Compressor Name");
        map.put(Integer.valueOf(TAG_DEPTH), "Depth");
        map.put(Integer.valueOf(TAG_COMPRESSION_TYPE), "Compression Type");
        map.put(211, "Graphics Mode");
        map.put(Integer.valueOf(TAG_OPCOLOR), "Opcolor");
        map.put(Integer.valueOf(TAG_COLOR_TABLE), "Color Table");
        map.put(Integer.valueOf(TAG_FRAME_RATE), "Frame Rate");
    }
}
