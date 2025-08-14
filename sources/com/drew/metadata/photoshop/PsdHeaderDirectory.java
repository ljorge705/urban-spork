package com.drew.metadata.photoshop;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class PsdHeaderDirectory extends Directory {
    public static final int TAG_BITS_PER_CHANNEL = 4;
    public static final int TAG_CHANNEL_COUNT = 1;
    public static final int TAG_COLOR_MODE = 5;
    public static final int TAG_IMAGE_HEIGHT = 2;
    public static final int TAG_IMAGE_WIDTH = 3;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "PSD Header";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "Channel Count");
        map.put(2, "Image Height");
        map.put(3, "Image Width");
        map.put(4, "Bits Per Channel");
        map.put(5, "Color Mode");
    }

    public PsdHeaderDirectory() {
        setDescriptor(new PsdHeaderDescriptor(this));
    }
}
