package com.drew.metadata.exif;

import java.util.HashMap;

/* loaded from: classes5.dex */
public class ExifThumbnailDirectory extends ExifDirectoryBase {

    @Deprecated
    public static final int TAG_THUMBNAIL_COMPRESSION = 259;
    public static final int TAG_THUMBNAIL_LENGTH = 514;
    public static final int TAG_THUMBNAIL_OFFSET = 513;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Exif Thumbnail";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        addExifTagNames(map);
        map.put(513, "Thumbnail Offset");
        map.put(514, "Thumbnail Length");
    }

    public ExifThumbnailDirectory() {
        setDescriptor(new ExifThumbnailDescriptor(this));
    }
}
