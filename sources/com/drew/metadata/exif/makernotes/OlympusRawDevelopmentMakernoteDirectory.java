package com.drew.metadata.exif.makernotes;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class OlympusRawDevelopmentMakernoteDirectory extends Directory {
    public static final int TagRawDevColorSpace = 264;
    public static final int TagRawDevContrastValue = 262;
    public static final int TagRawDevEditStatus = 267;
    public static final int TagRawDevEngine = 265;
    public static final int TagRawDevExposureBiasValue = 256;
    public static final int TagRawDevGrayPoint = 259;
    public static final int TagRawDevMemoryColorEmphasis = 261;
    public static final int TagRawDevNoiseReduction = 266;
    public static final int TagRawDevSaturationEmphasis = 260;
    public static final int TagRawDevSettings = 268;
    public static final int TagRawDevSharpnessValue = 263;
    public static final int TagRawDevVersion = 0;
    public static final int TagRawDevWbFineAdjustment = 258;
    public static final int TagRawDevWhiteBalanceValue = 257;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Olympus Raw Development";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(0, "Raw Dev Version");
        map.put(256, "Raw Dev Exposure Bias Value");
        map.put(257, "Raw Dev White Balance Value");
        map.put(258, "Raw Dev WB Fine Adjustment");
        map.put(259, "Raw Dev Gray Point");
        map.put(260, "Raw Dev Saturation Emphasis");
        map.put(261, "Raw Dev Memory Color Emphasis");
        map.put(262, "Raw Dev Contrast Value");
        map.put(263, "Raw Dev Sharpness Value");
        map.put(264, "Raw Dev Color Space");
        map.put(265, "Raw Dev Engine");
        map.put(266, "Raw Dev Noise Reduction");
        map.put(267, "Raw Dev Edit Status");
        map.put(268, "Raw Dev Settings");
    }

    public OlympusRawDevelopmentMakernoteDirectory() {
        setDescriptor(new OlympusRawDevelopmentMakernoteDescriptor(this));
    }
}
