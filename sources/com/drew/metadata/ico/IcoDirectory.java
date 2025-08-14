package com.drew.metadata.ico;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class IcoDirectory extends Directory {
    public static final int TAG_BITS_PER_PIXEL = 7;
    public static final int TAG_COLOUR_PALETTE_SIZE = 4;
    public static final int TAG_COLOUR_PLANES = 5;
    public static final int TAG_CURSOR_HOTSPOT_X = 6;
    public static final int TAG_CURSOR_HOTSPOT_Y = 8;
    public static final int TAG_IMAGE_HEIGHT = 3;
    public static final int TAG_IMAGE_OFFSET_BYTES = 10;
    public static final int TAG_IMAGE_SIZE_BYTES = 9;
    public static final int TAG_IMAGE_TYPE = 1;
    public static final int TAG_IMAGE_WIDTH = 2;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "ICO";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "Image Type");
        map.put(2, "Image Width");
        map.put(3, "Image Height");
        map.put(4, "Colour Palette Size");
        map.put(5, "Colour Planes");
        map.put(6, "Hotspot X");
        map.put(7, "Bits Per Pixel");
        map.put(8, "Hotspot Y");
        map.put(9, "Image Size Bytes");
        map.put(10, "Image Offset Bytes");
    }

    public IcoDirectory() {
        setDescriptor(new IcoDescriptor(this));
    }
}
