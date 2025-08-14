package com.drew.metadata.mov.media;

import com.drew.metadata.mov.QuickTimeDirectory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class QuickTimeSubtitleDirectory extends QuickTimeDirectory {
    public static final int TAG_ALL_SAMPLES_FORCED = 3;
    public static final int TAG_DEFAULT_TEXT_BOX = 4;
    public static final int TAG_FONT_FACE = 6;
    public static final int TAG_FONT_IDENTIFIER = 5;
    public static final int TAG_FONT_SIZE = 7;
    public static final int TAG_FOREGROUND_COLOR = 8;
    public static final int TAG_SOME_SAMPLES_FORCED = 2;
    public static final int TAG_VERTICAL_PLACEMENT = 1;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.mov.QuickTimeDirectory, com.drew.metadata.Directory
    public String getName() {
        return "QuickTime Subtitle";
    }

    @Override // com.drew.metadata.mov.QuickTimeDirectory, com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    public QuickTimeSubtitleDirectory() {
        setDescriptor(new QuickTimeSubtitleDescriptor(this));
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        QuickTimeMediaDirectory.addQuickTimeMediaTags(map);
        map.put(1, "Vertical Placement");
        map.put(2, "Some Samples Forced");
        map.put(3, "All Samples Forced");
        map.put(4, "Default Text Box");
        map.put(5, "Font Identifier");
        map.put(6, "Font Face");
        map.put(7, "Font Size");
        map.put(8, "Foreground Color");
    }
}
