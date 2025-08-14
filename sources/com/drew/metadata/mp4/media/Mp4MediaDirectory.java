package com.drew.metadata.mp4.media;

import com.drew.metadata.mp4.Mp4Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public abstract class Mp4MediaDirectory extends Mp4Directory {
    public static final int TAG_CREATION_TIME = 101;
    public static final int TAG_DURATION = 103;
    public static final int TAG_LANGUAGE_CODE = 104;
    public static final int TAG_MODIFICATION_TIME = 102;

    protected static void addMp4MediaTags(HashMap<Integer, String> map) {
        map.put(101, "Creation Time");
        map.put(102, "Modification Time");
        map.put(103, "Duration");
        map.put(104, "ISO 639-2 Language Code");
    }
}
