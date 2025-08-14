package com.drew.metadata.gif;

import com.drew.metadata.Directory;
import com.drew.metadata.StringValue;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class GifCommentDirectory extends Directory {
    public static final int TAG_COMMENT = 1;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "GIF Comment";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "Comment");
    }

    public GifCommentDirectory(StringValue stringValue) {
        setDescriptor(new GifCommentDescriptor(this));
        setStringValue(1, stringValue);
    }
}
