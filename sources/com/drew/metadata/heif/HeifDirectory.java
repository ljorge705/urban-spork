package com.drew.metadata.heif;

import com.drew.metadata.Directory;
import com.google.common.net.HttpHeaders;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class HeifDirectory extends Directory {
    public static final int TAG_BITS_PER_CHANNEL = 7;
    public static final int TAG_COMPATIBLE_BRANDS = 3;
    public static final int TAG_IMAGE_HEIGHT = 5;
    public static final int TAG_IMAGE_ROTATION = 6;
    public static final int TAG_IMAGE_WIDTH = 4;
    public static final int TAG_MAJOR_BRAND = 1;
    public static final int TAG_MINOR_VERSION = 2;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "HEIF";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "Major Brand");
        map.put(2, "Minor Version");
        map.put(3, "Compatible Brands");
        map.put(4, HttpHeaders.WIDTH);
        map.put(5, "Height");
        map.put(6, "Rotation");
        map.put(7, "Bits Per Channel");
    }

    public HeifDirectory() {
        setDescriptor(new HeifDescriptor(this));
    }
}
