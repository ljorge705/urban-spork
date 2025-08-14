package com.drew.metadata.photoshop;

import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class DuckyDirectory extends Directory {
    public static final int TAG_COMMENT = 2;
    public static final int TAG_COPYRIGHT = 3;
    public static final int TAG_QUALITY = 1;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Ducky";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "Quality");
        map.put(2, "Comment");
        map.put(3, ExifInterface.TAG_COPYRIGHT);
    }

    public DuckyDirectory() {
        setDescriptor(new TagDescriptor(this));
    }
}
