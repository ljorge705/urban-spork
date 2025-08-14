package com.drew.metadata.pcx;

import com.clevertap.android.sdk.Constants;
import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class PcxDirectory extends Directory {
    public static final int TAG_BITS_PER_PIXEL = 2;
    public static final int TAG_BYTES_PER_LINE = 11;
    public static final int TAG_COLOR_PLANES = 10;
    public static final int TAG_HORIZONTAL_DPI = 7;
    public static final int TAG_HSCR_SIZE = 13;
    public static final int TAG_PALETTE = 9;
    public static final int TAG_PALETTE_TYPE = 12;
    public static final int TAG_VERSION = 1;
    public static final int TAG_VERTICAL_DPI = 8;
    public static final int TAG_VSCR_SIZE = 14;
    public static final int TAG_XMAX = 5;
    public static final int TAG_XMIN = 3;
    public static final int TAG_YMAX = 6;
    public static final int TAG_YMIN = 4;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "PCX";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, Constants.CLTAP_APP_VERSION);
        map.put(2, "Bits Per Pixel");
        map.put(3, "X Min");
        map.put(4, "Y Min");
        map.put(5, "X Max");
        map.put(6, "Y Max");
        map.put(7, "Horizontal DPI");
        map.put(8, "Vertical DPI");
        map.put(9, "Palette");
        map.put(10, "Color Planes");
        map.put(11, "Bytes Per Line");
        map.put(12, "Palette Type");
        map.put(13, "H Scr Size");
        map.put(14, "V Scr Size");
    }

    public PcxDirectory() {
        setDescriptor(new PcxDescriptor(this));
    }
}
