package com.drew.metadata.mov.media;

import com.drew.metadata.mov.QuickTimeDirectory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class QuickTimeTextDirectory extends QuickTimeDirectory {
    public static final int TAG_ANTI_ALIAS = 9;
    public static final int TAG_AUTO_SCALE = 1;
    public static final int TAG_BACKGROUND_COLOR = 12;
    public static final int TAG_CONTINUOUS_SCROLL = 7;
    public static final int TAG_DEFAULT_TEXT_BOX = 13;
    public static final int TAG_DROP_SHADOW = 8;
    public static final int TAG_FONT_FACE = 15;
    public static final int TAG_FONT_NUMBER = 14;
    public static final int TAG_FOREGROUND_COLOR = 16;
    public static final int TAG_HORIZONTAL_SCROLL = 5;
    public static final int TAG_JUSTIFICATION = 11;
    public static final int TAG_KEY_TEXT = 10;
    public static final int TAG_MOVIE_BACKGROUND_COLOR = 2;
    public static final int TAG_NAME = 17;
    public static final int TAG_REVERSE_SCROLL = 6;
    public static final int TAG_SCROLL_IN = 3;
    public static final int TAG_SCROLL_OUT = 4;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.mov.QuickTimeDirectory, com.drew.metadata.Directory
    public String getName() {
        return "QuickTime Text";
    }

    @Override // com.drew.metadata.mov.QuickTimeDirectory, com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    public QuickTimeTextDirectory() {
        setDescriptor(new QuickTimeTextDescriptor(this));
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        QuickTimeMediaDirectory.addQuickTimeMediaTags(map);
        map.put(1, "Auto Scale");
        map.put(2, "Use Background Color");
        map.put(3, "Scroll In");
        map.put(4, "Scroll Out");
        map.put(5, "Scroll Orientation");
        map.put(6, "Scroll Direction");
        map.put(7, "Continuous Scroll");
        map.put(8, "Drop Shadow");
        map.put(9, "Anti-aliasing");
        map.put(10, "Display Text Background Color");
        map.put(11, "Alignment");
        map.put(12, "Background Color");
        map.put(13, "Default Text Box");
        map.put(14, "Font Number");
        map.put(15, "Font Face");
        map.put(16, "Foreground Color");
        map.put(17, "Font Name");
    }
}
