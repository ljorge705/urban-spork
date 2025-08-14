package com.drew.metadata.exif.makernotes;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class SonyType6MakernoteDirectory extends Directory {
    public static final int TAG_MAKERNOTE_THUMB_LENGTH = 1300;
    public static final int TAG_MAKERNOTE_THUMB_OFFSET = 1299;
    public static final int TAG_MAKERNOTE_THUMB_VERSION = 8192;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Sony Makernote";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1299, "Makernote Thumb Offset");
        map.put(1300, "Makernote Thumb Length");
        map.put(8192, "Makernote Thumb Version");
    }

    public SonyType6MakernoteDirectory() {
        setDescriptor(new SonyType6MakernoteDescriptor(this));
    }
}
