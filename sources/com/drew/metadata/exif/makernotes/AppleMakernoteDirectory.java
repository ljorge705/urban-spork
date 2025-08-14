package com.drew.metadata.exif.makernotes;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class AppleMakernoteDirectory extends Directory {
    public static final int TAG_BURST_UUID = 11;
    public static final int TAG_HDR_IMAGE_TYPE = 10;
    public static final int TAG_RUN_TIME = 3;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Apple Makernote";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(3, "Run Time");
        map.put(10, "HDR Image Type");
        map.put(11, "Burst UUID");
    }

    public AppleMakernoteDirectory() {
        setDescriptor(new AppleMakernoteDescriptor(this));
    }
}
