package com.drew.metadata.exif.makernotes;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class KyoceraMakernoteDirectory extends Directory {
    public static final int TAG_PRINT_IMAGE_MATCHING_INFO = 3584;
    public static final int TAG_PROPRIETARY_THUMBNAIL = 1;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Kyocera/Contax Makernote";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "Proprietary Thumbnail Format Data");
        map.put(3584, "Print Image Matching (PIM) Info");
    }

    public KyoceraMakernoteDirectory() {
        setDescriptor(new KyoceraMakernoteDescriptor(this));
    }
}
