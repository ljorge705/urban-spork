package com.drew.metadata.gif;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class GifHeaderDirectory extends Directory {
    public static final int TAG_BACKGROUND_COLOR_INDEX = 8;
    public static final int TAG_BITS_PER_PIXEL = 6;
    public static final int TAG_COLOR_TABLE_SIZE = 4;
    public static final int TAG_GIF_FORMAT_VERSION = 1;
    public static final int TAG_HAS_GLOBAL_COLOR_TABLE = 7;
    public static final int TAG_IMAGE_HEIGHT = 3;
    public static final int TAG_IMAGE_WIDTH = 2;
    public static final int TAG_IS_COLOR_TABLE_SORTED = 5;
    public static final int TAG_PIXEL_ASPECT_RATIO = 9;

    @Deprecated
    public static final int TAG_TRANSPARENT_COLOR_INDEX = 8;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "GIF Header";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "GIF Format Version");
        map.put(3, "Image Height");
        map.put(2, "Image Width");
        map.put(4, "Color Table Size");
        map.put(5, "Is Color Table Sorted");
        map.put(6, "Bits per Pixel");
        map.put(7, "Has Global Color Table");
        map.put(8, "Background Color Index");
        map.put(9, "Pixel Aspect Ratio");
    }

    public GifHeaderDirectory() {
        setDescriptor(new GifHeaderDescriptor(this));
    }
}
