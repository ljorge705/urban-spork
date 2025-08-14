package com.drew.metadata.png;

import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class PngChromaticitiesDirectory extends Directory {
    public static final int TAG_BLUE_X = 7;
    public static final int TAG_BLUE_Y = 8;
    public static final int TAG_GREEN_X = 5;
    public static final int TAG_GREEN_Y = 6;
    public static final int TAG_RED_X = 3;
    public static final int TAG_RED_Y = 4;
    public static final int TAG_WHITE_POINT_X = 1;
    public static final int TAG_WHITE_POINT_Y = 2;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "PNG Chromaticities";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "White Point X");
        map.put(2, "White Point Y");
        map.put(3, "Red X");
        map.put(4, "Red Y");
        map.put(5, "Green X");
        map.put(6, "Green Y");
        map.put(7, "Blue X");
        map.put(8, "Blue Y");
    }

    public PngChromaticitiesDirectory() {
        setDescriptor(new TagDescriptor(this));
    }
}
