package com.drew.metadata.exif;

import java.util.HashMap;

/* loaded from: classes5.dex */
public class ExifIFD0Directory extends ExifDirectoryBase {
    public static final int TAG_EXIF_SUB_IFD_OFFSET = 34665;
    public static final int TAG_GPS_INFO_OFFSET = 34853;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Exif IFD0";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    public ExifIFD0Directory() {
        setDescriptor(new ExifIFD0Descriptor(this));
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        addExifTagNames(map);
    }
}
