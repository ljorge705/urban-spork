package com.drew.metadata.exif.makernotes;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class LeicaType5MakernoteDirectory extends Directory {
    public static final int TagExposureMode = 1037;
    public static final int TagFilmMode = 1042;
    public static final int TagLensModel = 771;
    public static final int TagOriginalDirectory = 1032;
    public static final int TagOriginalFileName = 1031;
    public static final int TagShotInfo = 1040;
    public static final int TagWbRgbLevels = 1043;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Leica Makernote";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(771, "Lens Model");
        map.put(Integer.valueOf(TagOriginalFileName), "Original File Name");
        map.put(1032, "Original Directory");
        map.put(1037, "Exposure Mode");
        map.put(1040, "Shot Info");
        map.put(1042, "Film Mode");
        map.put(1043, "WB RGB Levels");
    }

    public LeicaType5MakernoteDirectory() {
        setDescriptor(new LeicaType5MakernoteDescriptor(this));
    }
}
