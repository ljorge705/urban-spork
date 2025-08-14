package com.drew.metadata.exif;

import java.util.HashMap;

/* loaded from: classes5.dex */
public class ExifImageDirectory extends ExifDirectoryBase {
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Exif Image";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        addExifTagNames(map);
    }

    public ExifImageDirectory() {
        setDescriptor(new ExifImageDescriptor(this));
    }
}
