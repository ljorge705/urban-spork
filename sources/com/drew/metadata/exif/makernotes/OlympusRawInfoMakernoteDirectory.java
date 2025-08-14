package com.drew.metadata.exif.makernotes;

import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class OlympusRawInfoMakernoteDirectory extends Directory {
    public static final int TagBlackLevel2 = 1536;
    public static final int TagCmContrast = 8226;
    public static final int TagCmExposureCompensation = 8192;
    public static final int TagCmHue = 8225;
    public static final int TagCmSaturation = 8224;
    public static final int TagCmSharpness = 8227;
    public static final int TagCmWhiteBalance = 8193;
    public static final int TagCmWhiteBalanceComp = 8194;
    public static final int TagCmWhiteBalanceGrayPoint = 8208;
    public static final int TagColorMatrix2 = 512;
    public static final int TagContrastSetting = 4114;
    public static final int TagCoringFilter = 784;
    public static final int TagCoringValues = 785;
    public static final int TagCropHeight = 1557;
    public static final int TagCropLeft = 1554;
    public static final int TagCropTop = 1555;
    public static final int TagCropWidth = 1556;
    public static final int TagHueSetting = 4113;
    public static final int TagLightSource = 4096;
    public static final int TagRawInfoVersion = 0;
    public static final int TagSaturationSetting = 4112;
    public static final int TagSharpnessSetting = 4115;
    public static final int TagValidPixelDepth = 1553;
    public static final int TagWbRbLevelsAuto = 272;
    public static final int TagWbRbLevelsCloudy = 289;
    public static final int TagWbRbLevelsCoolWhiteFluor = 306;
    public static final int TagWbRbLevelsDayWhiteFluor = 305;
    public static final int TagWbRbLevelsDaylightFluor = 304;
    public static final int TagWbRbLevelsEveningSunlight = 292;
    public static final int TagWbRbLevelsFineWeather = 290;
    public static final int TagWbRbLevelsShade = 288;
    public static final int TagWbRbLevelsTungsten = 291;
    public static final int TagWbRbLevelsUsed = 256;
    public static final int TagWbRbLevelsWhiteFluorescent = 307;
    public static final int TagWhiteBalanceComp = 4097;
    public static final int TagYCbCrCoefficients = 1537;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Olympus Raw Info";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(0, "Raw Info Version");
        map.put(256, "WB RB Levels Used");
        map.put(272, "WB RB Levels Auto");
        map.put(288, "WB RB Levels Shade");
        map.put(289, "WB RB Levels Cloudy");
        map.put(Integer.valueOf(TagWbRbLevelsFineWeather), "WB RB Levels Fine Weather");
        map.put(291, "WB RB Levels Tungsten");
        map.put(Integer.valueOf(TagWbRbLevelsEveningSunlight), "WB RB Levels Evening Sunlight");
        map.put(304, "WB RB Levels Daylight Fluor");
        map.put(305, "WB RB Levels Day White Fluor");
        map.put(306, "WB RB Levels Cool White Fluor");
        map.put(307, "WB RB Levels White Fluorescent");
        map.put(512, "Color Matrix 2");
        map.put(784, "Coring Filter");
        map.put(785, "Coring Values");
        map.put(1536, "Black Level 2");
        map.put(1537, ExifInterface.TAG_Y_CB_CR_COEFFICIENTS);
        map.put(1553, "Valid Pixel Depth");
        map.put(1554, "Crop Left");
        map.put(1555, "Crop Top");
        map.put(1556, "Crop Width");
        map.put(1557, "Crop Height");
        map.put(4096, "Light Source");
        map.put(4097, "White Balance Comp");
        map.put(4112, "Saturation Setting");
        map.put(4113, "Hue Setting");
        map.put(4114, "Contrast Setting");
        map.put(4115, "Sharpness Setting");
        map.put(8192, "CM Exposure Compensation");
        map.put(8193, "CM White Balance");
        map.put(8194, "CM White Balance Comp");
        map.put(8208, "CM White Balance Gray Point");
        map.put(8224, "CM Saturation");
        map.put(Integer.valueOf(TagCmHue), "CM Hue");
        map.put(8226, "CM Contrast");
        map.put(Integer.valueOf(TagCmSharpness), "CM Sharpness");
    }

    public OlympusRawInfoMakernoteDirectory() {
        setDescriptor(new OlympusRawInfoMakernoteDescriptor(this));
    }
}
