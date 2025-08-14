package com.drew.metadata.exif;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class PanasonicRawDistortionDirectory extends Directory {
    public static final int TagDistortionCorrection = 7;
    public static final int TagDistortionN = 12;
    public static final int TagDistortionParam02 = 2;
    public static final int TagDistortionParam04 = 4;
    public static final int TagDistortionParam08 = 8;
    public static final int TagDistortionParam09 = 9;
    public static final int TagDistortionParam11 = 11;
    public static final int TagDistortionScale = 5;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "PanasonicRaw DistortionInfo";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(2, "Distortion Param 2");
        map.put(4, "Distortion Param 4");
        map.put(5, "Distortion Scale");
        map.put(7, "Distortion Correction");
        map.put(8, "Distortion Param 8");
        map.put(9, "Distortion Param 9");
        map.put(11, "Distortion Param 11");
        map.put(12, "Distortion N");
    }

    public PanasonicRawDistortionDirectory() {
        setDescriptor(new PanasonicRawDistortionDescriptor(this));
    }
}
