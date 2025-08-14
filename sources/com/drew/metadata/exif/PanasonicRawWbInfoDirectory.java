package com.drew.metadata.exif;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class PanasonicRawWbInfoDirectory extends Directory {
    public static final int TagNumWbEntries = 0;
    public static final int TagWbRbLevels1 = 2;
    public static final int TagWbRbLevels2 = 5;
    public static final int TagWbRbLevels3 = 8;
    public static final int TagWbRbLevels4 = 11;
    public static final int TagWbRbLevels5 = 14;
    public static final int TagWbRbLevels6 = 17;
    public static final int TagWbRbLevels7 = 20;
    public static final int TagWbType1 = 1;
    public static final int TagWbType2 = 4;
    public static final int TagWbType3 = 7;
    public static final int TagWbType4 = 10;
    public static final int TagWbType5 = 13;
    public static final int TagWbType6 = 16;
    public static final int TagWbType7 = 19;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "PanasonicRaw WbInfo";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(0, "Num WB Entries");
        map.put(1, "WB Type 1");
        map.put(2, "WB RGB Levels 1");
        map.put(4, "WB Type 2");
        map.put(5, "WB RGB Levels 2");
        map.put(7, "WB Type 3");
        map.put(8, "WB RGB Levels 3");
        map.put(10, "WB Type 4");
        map.put(11, "WB RGB Levels 4");
        map.put(13, "WB Type 5");
        map.put(14, "WB RGB Levels 5");
        map.put(16, "WB Type 6");
        map.put(17, "WB RGB Levels 6");
        map.put(19, "WB Type 7");
        map.put(20, "WB RGB Levels 7");
    }

    public PanasonicRawWbInfoDirectory() {
        setDescriptor(new PanasonicRawWbInfoDescriptor(this));
    }
}
