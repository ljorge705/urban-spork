package com.drew.metadata.mp4.media;

import java.util.HashMap;

/* loaded from: classes5.dex */
public class Mp4HintDirectory extends Mp4MediaDirectory {
    public static final int TAG_AVERAGE_BITRATE = 104;
    public static final int TAG_AVERAGE_PDU_SIZE = 102;
    public static final int TAG_MAX_BITRATE = 103;
    public static final int TAG_MAX_PDU_SIZE = 101;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.mp4.Mp4Directory, com.drew.metadata.Directory
    public String getName() {
        return "MP4 Hint";
    }

    @Override // com.drew.metadata.mp4.Mp4Directory, com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    public Mp4HintDirectory() {
        setDescriptor(new Mp4HintDescriptor(this));
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        Mp4MediaDirectory.addMp4MediaTags(map);
        map.put(101, "Max PDU Size");
        map.put(102, "Average PDU Size");
        map.put(103, "Max Bitrate");
        map.put(104, "Average Bitrate");
    }
}
