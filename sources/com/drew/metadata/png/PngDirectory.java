package com.drew.metadata.png;

import com.drew.imaging.png.PngChunkType;
import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class PngDirectory extends Directory {
    public static final int TAG_BACKGROUND_COLOR = 15;
    public static final int TAG_BITS_PER_SAMPLE = 3;
    public static final int TAG_COLOR_TYPE = 4;
    public static final int TAG_COMPRESSION_TYPE = 5;
    public static final int TAG_FILTER_METHOD = 6;
    public static final int TAG_GAMMA = 11;
    public static final int TAG_ICC_PROFILE_NAME = 12;
    public static final int TAG_IMAGE_HEIGHT = 2;
    public static final int TAG_IMAGE_WIDTH = 1;
    public static final int TAG_INTERLACE_METHOD = 7;
    public static final int TAG_LAST_MODIFICATION_TIME = 14;
    public static final int TAG_PALETTE_HAS_TRANSPARENCY = 9;
    public static final int TAG_PALETTE_SIZE = 8;
    public static final int TAG_PIXELS_PER_UNIT_X = 16;
    public static final int TAG_PIXELS_PER_UNIT_Y = 17;
    public static final int TAG_SIGNIFICANT_BITS = 19;
    public static final int TAG_SRGB_RENDERING_INTENT = 10;
    public static final int TAG_TEXTUAL_DATA = 13;
    public static final int TAG_UNIT_SPECIFIER = 18;
    protected static final HashMap<Integer, String> _tagNameMap;
    private final PngChunkType _pngChunkType;

    public PngChunkType getPngChunkType() {
        return this._pngChunkType;
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(2, "Image Height");
        map.put(1, "Image Width");
        map.put(3, "Bits Per Sample");
        map.put(4, "Color Type");
        map.put(5, "Compression Type");
        map.put(6, "Filter Method");
        map.put(7, "Interlace Method");
        map.put(8, "Palette Size");
        map.put(9, "Palette Has Transparency");
        map.put(10, "sRGB Rendering Intent");
        map.put(11, "Image Gamma");
        map.put(12, "ICC Profile Name");
        map.put(13, "Textual Data");
        map.put(14, "Last Modification Time");
        map.put(15, "Background Color");
        map.put(16, "Pixels Per Unit X");
        map.put(17, "Pixels Per Unit Y");
        map.put(18, "Unit Specifier");
        map.put(19, "Significant Bits");
    }

    public PngDirectory(PngChunkType pngChunkType) {
        this._pngChunkType = pngChunkType;
        setDescriptor(new PngDescriptor(this));
    }

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "PNG-" + this._pngChunkType.getIdentifier();
    }
}
