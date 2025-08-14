package com.drew.metadata.mp4.media;

import java.util.HashMap;

/* loaded from: classes5.dex */
public class Mp4MetaDirectory extends Mp4MediaDirectory {
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.mp4.Mp4Directory, com.drew.metadata.Directory
    public String getName() {
        return "MP4 Metadata";
    }

    @Override // com.drew.metadata.mp4.Mp4Directory, com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    public Mp4MetaDirectory() {
        setDescriptor(new Mp4MetaDescriptor(this));
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        Mp4MediaDirectory.addMp4MediaTags(map);
    }
}
