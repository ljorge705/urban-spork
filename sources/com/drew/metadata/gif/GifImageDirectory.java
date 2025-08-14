package com.drew.metadata.gif;

import com.drew.metadata.Directory;
import com.google.common.net.HttpHeaders;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class GifImageDirectory extends Directory {
    public static final int TAG_HAS_LOCAL_COLOUR_TABLE = 5;
    public static final int TAG_HEIGHT = 4;
    public static final int TAG_IS_COLOR_TABLE_SORTED = 7;
    public static final int TAG_IS_INTERLACED = 6;
    public static final int TAG_LEFT = 1;
    public static final int TAG_LOCAL_COLOUR_TABLE_BITS_PER_PIXEL = 8;
    public static final int TAG_TOP = 2;
    public static final int TAG_WIDTH = 3;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "GIF Image";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "Left");
        map.put(2, "Top");
        map.put(3, HttpHeaders.WIDTH);
        map.put(4, "Height");
        map.put(5, "Has Local Colour Table");
        map.put(6, "Is Interlaced");
        map.put(7, "Is Local Colour Table Sorted");
        map.put(8, "Local Colour Table Bits Per Pixel");
    }

    public GifImageDirectory() {
        setDescriptor(new GifImageDescriptor(this));
    }
}
