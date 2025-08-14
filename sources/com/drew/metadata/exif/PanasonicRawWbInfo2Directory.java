package com.drew.metadata.exif;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class PanasonicRawWbInfo2Directory extends Directory {
    public static final int TagNumWbEntries = 0;
    public static final int TagWbRgbLevels1 = 2;
    public static final int TagWbRgbLevels2 = 6;
    public static final int TagWbRgbLevels3 = 10;
    public static final int TagWbRgbLevels4 = 14;
    public static final int TagWbRgbLevels5 = 18;
    public static final int TagWbRgbLevels6 = 22;
    public static final int TagWbRgbLevels7 = 26;
    public static final int TagWbType1 = 1;
    public static final int TagWbType2 = 5;
    public static final int TagWbType3 = 9;
    public static final int TagWbType4 = 13;
    public static final int TagWbType5 = 17;
    public static final int TagWbType6 = 21;
    public static final int TagWbType7 = 25;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "PanasonicRaw WbInfo2";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(0, "Num WB Entries");
        map.put(0, "Num WB Entries");
        map.put(1, "WB Type 1");
        map.put(2, "WB RGB Levels 1");
        map.put(5, "WB Type 2");
        map.put(6, "WB RGB Levels 2");
        map.put(9, "WB Type 3");
        map.put(10, "WB RGB Levels 3");
        map.put(13, "WB Type 4");
        map.put(14, "WB RGB Levels 4");
        map.put(17, "WB Type 5");
        map.put(18, "WB RGB Levels 5");
        map.put(21, "WB Type 6");
        map.put(22, "WB RGB Levels 6");
        map.put(25, "WB Type 7");
        map.put(26, "WB RGB Levels 7");
    }

    public PanasonicRawWbInfo2Directory() {
        setDescriptor(new PanasonicRawWbInfo2Descriptor(this));
    }
}
