package com.drew.metadata.mov.media;

import com.drew.metadata.mov.QuickTimeDirectory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class QuickTimeTimecodeDirectory extends QuickTimeDirectory {
    public static final int TAG_24_HOUR_MAX = 2;
    public static final int TAG_BACKGROUND_COLOR = 9;
    public static final int TAG_COUNTER = 4;
    public static final int TAG_DROP_FRAME = 1;
    public static final int TAG_FONT_NAME = 10;
    public static final int TAG_NEGATIVE_TIMES_OK = 3;
    public static final int TAG_TEXT_COLOR = 8;
    public static final int TAG_TEXT_FACE = 6;
    public static final int TAG_TEXT_FONT = 5;
    public static final int TAG_TEXT_SIZE = 7;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.mov.QuickTimeDirectory, com.drew.metadata.Directory
    public String getName() {
        return "QuickTime Timecode";
    }

    @Override // com.drew.metadata.mov.QuickTimeDirectory, com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    public QuickTimeTimecodeDirectory() {
        setDescriptor(new QuickTimeTimecodeDescriptor(this));
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        QuickTimeMediaDirectory.addQuickTimeMediaTags(map);
        map.put(1, "Drop Frame");
        map.put(2, "24 Hour Max");
        map.put(3, "Negative Times OK");
        map.put(4, "Counter");
        map.put(5, "Text Font");
        map.put(6, "Text Face");
        map.put(7, "Text Size");
        map.put(8, "Text Color");
        map.put(9, "Background Color");
        map.put(10, "Font Name");
    }
}
