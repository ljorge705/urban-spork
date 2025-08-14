package com.drew.metadata.exif.makernotes;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class OlympusCameraSettingsMakernoteDirectory extends Directory {
    public static final int TagAeLock = 513;
    public static final int TagAfAreas = 772;
    public static final int TagAfFineTune = 774;
    public static final int TagAfFineTuneAdj = 775;
    public static final int TagAfPointSelected = 773;
    public static final int TagAfSearch = 771;
    public static final int TagArtFilter = 1321;
    public static final int TagArtFilterEffect = 1327;
    public static final int TagCameraSettingsVersion = 0;
    public static final int TagColorCreatorEffect = 1330;
    public static final int TagColorSpace = 1287;
    public static final int TagCompressionFactor = 1293;
    public static final int TagContrastSetting = 1285;
    public static final int TagCustomSaturation = 1283;
    public static final int TagDateTimeUtc = 2312;
    public static final int TagDistortionCorrection = 1291;
    public static final int TagDriveMode = 1536;
    public static final int TagExposureMode = 512;
    public static final int TagExposureShift = 515;
    public static final int TagExtendedWBDetect = 2306;
    public static final int TagFlashControlMode = 1028;
    public static final int TagFlashExposureComp = 1025;
    public static final int TagFlashIntensity = 1029;
    public static final int TagFlashMode = 1024;
    public static final int TagFlashRemoteControl = 1027;
    public static final int TagFocusMode = 769;
    public static final int TagFocusProcess = 770;
    public static final int TagGradation = 1295;
    public static final int TagImageQuality2 = 1539;
    public static final int TagImageStabilization = 1540;
    public static final int TagMacroMode = 768;
    public static final int TagMagicFilter = 1324;
    public static final int TagManometerPressure = 2304;
    public static final int TagManometerReading = 2305;
    public static final int TagManualFlashStrength = 1030;
    public static final int TagMeteringMode = 514;
    public static final int TagModifiedSaturation = 1284;
    public static final int TagNdFilter = 516;
    public static final int TagNoiseFilter = 1319;
    public static final int TagNoiseReduction = 1290;
    public static final int TagPanoramaMode = 1537;
    public static final int TagPictureMode = 1312;
    public static final int TagPictureModeBWFilter = 1317;
    public static final int TagPictureModeContrast = 1315;
    public static final int TagPictureModeEffect = 1325;
    public static final int TagPictureModeHue = 1314;
    public static final int TagPictureModeSaturation = 1313;
    public static final int TagPictureModeSharpness = 1316;
    public static final int TagPictureModeTone = 1318;
    public static final int TagPitchAngle = 2308;
    public static final int TagPreviewImageLength = 258;
    public static final int TagPreviewImageStart = 257;
    public static final int TagPreviewImageValid = 256;
    public static final int TagRollAngle = 2307;
    public static final int TagSceneMode = 1289;
    public static final int TagShadingCompensation = 1292;
    public static final int TagSharpnessSetting = 1286;
    public static final int TagStackedImage = 2052;
    public static final int TagToneLevel = 1326;
    public static final int TagWhiteBalance2 = 1280;
    public static final int TagWhiteBalanceBracket = 1282;
    public static final int TagWhiteBalanceTemperature = 1281;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Olympus Camera Settings";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(0, "Camera Settings Version");
        map.put(256, "Preview Image Valid");
        map.put(257, "Preview Image Start");
        map.put(258, "Preview Image Length");
        map.put(512, "Exposure Mode");
        map.put(513, "AE Lock");
        map.put(514, "Metering Mode");
        map.put(515, "Exposure Shift");
        map.put(516, "ND Filter");
        map.put(768, "Macro Mode");
        map.put(769, "Focus Mode");
        map.put(770, "Focus Process");
        map.put(771, "AF Search");
        map.put(772, "AF Areas");
        map.put(773, "AF Point Selected");
        map.put(774, "AF Fine Tune");
        map.put(Integer.valueOf(TagAfFineTuneAdj), "AF Fine Tune Adj");
        map.put(1024, "Flash Mode");
        map.put(1025, "Flash Exposure Comp");
        map.put(1027, "Flash Remote Control");
        map.put(1028, "Flash Control Mode");
        map.put(1029, "Flash Intensity");
        map.put(1030, "Manual Flash Strength");
        map.put(1280, "White Balance 2");
        map.put(1281, "White Balance Temperature");
        map.put(1282, "White Balance Bracket");
        map.put(1283, "Custom Saturation");
        map.put(1284, "Modified Saturation");
        map.put(1285, "Contrast Setting");
        map.put(1286, "Sharpness Setting");
        map.put(1287, "Color Space");
        map.put(1289, "Scene Mode");
        map.put(1290, "Noise Reduction");
        map.put(1291, "Distortion Correction");
        map.put(1292, "Shading Compensation");
        map.put(1293, "Compression Factor");
        map.put(1295, "Gradation");
        map.put(1312, "Picture Mode");
        map.put(Integer.valueOf(TagPictureModeSaturation), "Picture Mode Saturation");
        map.put(Integer.valueOf(TagPictureModeHue), "Picture Mode Hue");
        map.put(Integer.valueOf(TagPictureModeContrast), "Picture Mode Contrast");
        map.put(Integer.valueOf(TagPictureModeSharpness), "Picture Mode Sharpness");
        map.put(Integer.valueOf(TagPictureModeBWFilter), "Picture Mode BW Filter");
        map.put(Integer.valueOf(TagPictureModeTone), "Picture Mode Tone");
        map.put(Integer.valueOf(TagNoiseFilter), "Noise Filter");
        map.put(Integer.valueOf(TagArtFilter), "Art Filter");
        map.put(Integer.valueOf(TagMagicFilter), "Magic Filter");
        map.put(Integer.valueOf(TagPictureModeEffect), "Picture Mode Effect");
        map.put(Integer.valueOf(TagToneLevel), "Tone Level");
        map.put(Integer.valueOf(TagArtFilterEffect), "Art Filter Effect");
        map.put(Integer.valueOf(TagColorCreatorEffect), "Color Creator Effect");
        map.put(1536, "Drive Mode");
        map.put(1537, "Panorama Mode");
        map.put(Integer.valueOf(TagImageQuality2), "Image Quality 2");
        map.put(Integer.valueOf(TagImageStabilization), "Image Stabilization");
        map.put(Integer.valueOf(TagStackedImage), "Stacked Image");
        map.put(Integer.valueOf(TagManometerPressure), "Manometer Pressure");
        map.put(Integer.valueOf(TagManometerReading), "Manometer Reading");
        map.put(Integer.valueOf(TagExtendedWBDetect), "Extended WB Detect");
        map.put(Integer.valueOf(TagRollAngle), "Roll Angle");
        map.put(Integer.valueOf(TagPitchAngle), "Pitch Angle");
        map.put(Integer.valueOf(TagDateTimeUtc), "Date Time UTC");
    }

    public OlympusCameraSettingsMakernoteDirectory() {
        setDescriptor(new OlympusCameraSettingsMakernoteDescriptor(this));
    }
}
