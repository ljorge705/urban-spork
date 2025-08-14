package com.drew.metadata.adobe;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class AdobeJpegDirectory extends Directory {
    public static final int TAG_APP14_FLAGS0 = 1;
    public static final int TAG_APP14_FLAGS1 = 2;
    public static final int TAG_COLOR_TRANSFORM = 3;
    public static final int TAG_DCT_ENCODE_VERSION = 0;
    private static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Adobe JPEG";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(0, "DCT Encode Version");
        map.put(1, "Flags 0");
        map.put(2, "Flags 1");
        map.put(3, "Color Transform");
    }

    public AdobeJpegDirectory() {
        setDescriptor(new AdobeJpegDescriptor(this));
    }
}
