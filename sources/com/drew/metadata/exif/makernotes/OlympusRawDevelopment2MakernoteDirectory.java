package com.drew.metadata.exif.makernotes;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class OlympusRawDevelopment2MakernoteDirectory extends Directory {
    public static final int TagRawDevArtFilter = 289;
    public static final int TagRawDevAutoGradation = 281;
    public static final int TagRawDevColorSpace = 265;
    public static final int TagRawDevContrastValue = 261;
    public static final int TagRawDevEngine = 267;
    public static final int TagRawDevExposureBiasValue = 256;
    public static final int TagRawDevGradation = 274;
    public static final int TagRawDevGrayPoint = 260;
    public static final int TagRawDevMemoryColorEmphasis = 264;
    public static final int TagRawDevNoiseReduction = 266;
    public static final int TagRawDevPictureMode = 268;
    public static final int TagRawDevPmBwFilter = 272;
    public static final int TagRawDevPmContrast = 270;
    public static final int TagRawDevPmNoiseFilter = 288;
    public static final int TagRawDevPmPictureTone = 273;
    public static final int TagRawDevPmSaturation = 269;
    public static final int TagRawDevPmSharpness = 271;
    public static final int TagRawDevSaturation3 = 275;
    public static final int TagRawDevSaturationEmphasis = 263;
    public static final int TagRawDevSharpnessValue = 262;
    public static final int TagRawDevVersion = 0;
    public static final int TagRawDevWbFineAdjustment = 259;
    public static final int TagRawDevWhiteBalance = 257;
    public static final int TagRawDevWhiteBalanceValue = 258;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Olympus Raw Development 2";
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
        map.put(257, "Raw Dev White Balance");
        map.put(258, "Raw Dev White Balance Value");
        map.put(259, "Raw Dev WB Fine Adjustment");
        map.put(260, "Raw Dev Gray Point");
        map.put(261, "Raw Dev Contrast Value");
        map.put(262, "Raw Dev Sharpness Value");
        map.put(263, "Raw Dev Saturation Emphasis");
        map.put(264, "Raw Dev Memory Color Emphasis");
        map.put(265, "Raw Dev Color Space");
        map.put(266, "Raw Dev Noise Reduction");
        map.put(267, "Raw Dev Engine");
        map.put(268, "Raw Dev Picture Mode");
        map.put(269, "Raw Dev PM Saturation");
        map.put(270, "Raw Dev PM Contrast");
        map.put(271, "Raw Dev PM Sharpness");
        map.put(272, "Raw Dev PM BW Filter");
        map.put(273, "Raw Dev PM Picture Tone");
        map.put(274, "Raw Dev Gradation");
        map.put(275, "Raw Dev Saturation 3");
        map.put(281, "Raw Dev Auto Gradation");
        map.put(288, "Raw Dev PM Noise Filter");
        map.put(289, "Raw Dev Art Filter");
    }

    public OlympusRawDevelopment2MakernoteDirectory() {
        setDescriptor(new OlympusRawDevelopment2MakernoteDescriptor(this));
    }
}
