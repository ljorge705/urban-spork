package com.drew.metadata.exif.makernotes;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class OlympusImageProcessingMakernoteDirectory extends Directory {
    public static final int TagAspectFrame = 4371;
    public static final int TagAspectRatio = 4370;
    public static final int TagBlackLevel2 = 1536;
    public static final int TagCameraTemperature = 4870;
    public static final int TagColorMatrix = 512;
    public static final int TagCoringFilter = 784;
    public static final int TagCoringValues = 785;
    public static final int TagCropHeight = 1557;
    public static final int TagCropLeft = 1554;
    public static final int TagCropTop = 1555;
    public static final int TagCropWidth = 1556;
    public static final int TagDistortionCorrection2 = 4113;
    public static final int TagEnhancer = 768;
    public static final int TagEnhancerValues = 769;
    public static final int TagFaceDetectArea = 4609;
    public static final int TagFaceDetectFrameCrop = 4615;
    public static final int TagFaceDetectFrameSize = 4611;
    public static final int TagFacesDetected = 4608;
    public static final int TagGainBase = 1552;
    public static final int TagImageProcessingVersion = 0;
    public static final int TagKeystoneCompensation = 6400;
    public static final int TagKeystoneDirection = 6401;
    public static final int TagKeystoneValue = 6406;
    public static final int TagMaxFaces = 4610;
    public static final int TagMultipleExposureMode = 4124;
    public static final int TagNoiseReduction2 = 4112;
    public static final int TagSensorCalibration = 2053;
    public static final int TagShadingCompensation2 = 4114;
    public static final int TagUnknownBlock1 = 1589;
    public static final int TagUnknownBlock2 = 1590;
    public static final int TagUnknownBlock3 = 4355;
    public static final int TagUnknownBlock4 = 4356;
    public static final int TagValidBits = 1553;
    public static final int TagWbGLevel = 287;
    public static final int TagWbGLevel3000K = 275;
    public static final int TagWbGLevel3300K = 276;
    public static final int TagWbGLevel3600K = 277;
    public static final int TagWbGLevel3900K = 278;
    public static final int TagWbGLevel4000K = 279;
    public static final int TagWbGLevel4300K = 280;
    public static final int TagWbGLevel4500K = 281;
    public static final int TagWbGLevel4800K = 282;
    public static final int TagWbGLevel5300K = 283;
    public static final int TagWbGLevel6000K = 284;
    public static final int TagWbGLevel6600K = 285;
    public static final int TagWbGLevel7500K = 286;
    public static final int TagWbRbLevels = 256;
    public static final int TagWbRbLevels3000K = 258;
    public static final int TagWbRbLevels3300K = 259;
    public static final int TagWbRbLevels3600K = 260;
    public static final int TagWbRbLevels3900K = 261;
    public static final int TagWbRbLevels4000K = 262;
    public static final int TagWbRbLevels4300K = 263;
    public static final int TagWbRbLevels4500K = 264;
    public static final int TagWbRbLevels4800K = 265;
    public static final int TagWbRbLevels5300K = 266;
    public static final int TagWbRbLevels6000K = 267;
    public static final int TagWbRbLevels6600K = 268;
    public static final int TagWbRbLevels7500K = 269;
    public static final int TagWbRbLevelsCwB1 = 270;
    public static final int TagWbRbLevelsCwB2 = 271;
    public static final int TagWbRbLevelsCwB3 = 272;
    public static final int TagWbRbLevelsCwB4 = 273;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Olympus Image Processing";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(0, "Image Processing Version");
        map.put(256, "WB RB Levels");
        map.put(258, "WB RB Levels 3000K");
        map.put(259, "WB RB Levels 3300K");
        map.put(260, "WB RB Levels 3600K");
        map.put(261, "WB RB Levels 3900K");
        map.put(262, "WB RB Levels 4000K");
        map.put(263, "WB RB Levels 4300K");
        map.put(264, "WB RB Levels 4500K");
        map.put(265, "WB RB Levels 4800K");
        map.put(266, "WB RB Levels 5300K");
        map.put(267, "WB RB Levels 6000K");
        map.put(268, "WB RB Levels 6600K");
        map.put(269, "WB RB Levels 7500K");
        map.put(270, "WB RB Levels CWB1");
        map.put(271, "WB RB Levels CWB2");
        map.put(272, "WB RB Levels CWB3");
        map.put(273, "WB RB Levels CWB4");
        map.put(275, "WB G Level 3000K");
        map.put(276, "WB G Level 3300K");
        map.put(277, "WB G Level 3600K");
        map.put(278, "WB G Level 3900K");
        map.put(279, "WB G Level 4000K");
        map.put(280, "WB G Level 4300K");
        map.put(281, "WB G Level 4500K");
        map.put(282, "WB G Level 4800K");
        map.put(283, "WB G Level 5300K");
        map.put(284, "WB G Level 6000K");
        map.put(285, "WB G Level 6600K");
        map.put(286, "WB G Level 7500K");
        map.put(Integer.valueOf(TagWbGLevel), "WB G Level");
        map.put(512, "Color Matrix");
        map.put(768, "Enhancer");
        map.put(769, "Enhancer Values");
        map.put(784, "Coring Filter");
        map.put(785, "Coring Values");
        map.put(1536, "Black Level 2");
        map.put(Integer.valueOf(TagGainBase), "Gain Base");
        map.put(1553, "Valid Bits");
        map.put(1554, "Crop Left");
        map.put(1555, "Crop Top");
        map.put(1556, "Crop Width");
        map.put(1557, "Crop Height");
        map.put(Integer.valueOf(TagUnknownBlock1), "Unknown Block 1");
        map.put(Integer.valueOf(TagUnknownBlock2), "Unknown Block 2");
        map.put(Integer.valueOf(TagSensorCalibration), "Sensor Calibration");
        map.put(4112, "Noise Reduction 2");
        map.put(4113, "Distortion Correction 2");
        map.put(4114, "Shading Compensation 2");
        map.put(4124, "Multiple Exposure Mode");
        map.put(Integer.valueOf(TagUnknownBlock3), "Unknown Block 3");
        map.put(Integer.valueOf(TagUnknownBlock4), "Unknown Block 4");
        map.put(Integer.valueOf(TagAspectRatio), "Aspect Ratio");
        map.put(Integer.valueOf(TagAspectFrame), "Aspect Frame");
        map.put(Integer.valueOf(TagFacesDetected), "Faces Detected");
        map.put(4609, "Face Detect Area");
        map.put(Integer.valueOf(TagMaxFaces), "Max Faces");
        map.put(4611, "Face Detect Frame Size");
        map.put(Integer.valueOf(TagFaceDetectFrameCrop), "Face Detect Frame Crop");
        map.put(Integer.valueOf(TagCameraTemperature), "Camera Temperature");
        map.put(Integer.valueOf(TagKeystoneCompensation), "Keystone Compensation");
        map.put(Integer.valueOf(TagKeystoneDirection), "Keystone Direction");
        map.put(Integer.valueOf(TagKeystoneValue), "Keystone Value");
    }

    public OlympusImageProcessingMakernoteDirectory() {
        setDescriptor(new OlympusImageProcessingMakernoteDescriptor(this));
    }
}
