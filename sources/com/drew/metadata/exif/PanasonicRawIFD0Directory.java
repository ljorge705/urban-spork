package com.drew.metadata.exif;

import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class PanasonicRawIFD0Directory extends Directory {
    public static final int TagBlackLevel1 = 8;
    public static final int TagBlackLevel2 = 9;
    public static final int TagBlackLevel3 = 10;
    public static final int TagBlackLevelBlue = 30;
    public static final int TagBlackLevelGreen = 29;
    public static final int TagBlackLevelRed = 28;
    public static final int TagBlueBalance = 18;
    public static final int TagCropBottom = 49;
    public static final int TagCropLeft = 48;
    public static final int TagCropRight = 50;
    public static final int TagCropTop = 47;
    public static final int TagDistortionInfo = 281;
    public static final int TagHighIsoMultiplierBlue = 26;
    public static final int TagHighIsoMultiplierGreen = 25;
    public static final int TagHighIsoMultiplierRed = 24;
    public static final int TagIso = 23;
    public static final int TagJpgFromRaw = 46;
    public static final int TagLinearityLimitBlue = 16;
    public static final int TagLinearityLimitGreen = 15;
    public static final int TagLinearityLimitRed = 14;
    public static final int TagMake = 271;
    public static final int TagModel = 272;
    public static final int TagOrientation = 274;
    public static final int TagPanasonicRawVersion = 1;
    public static final int TagRawDataOffset = 280;
    public static final int TagRedBalance = 17;
    public static final int TagRowsPerStrip = 278;
    public static final int TagSensorBottomBorder = 6;
    public static final int TagSensorHeight = 3;
    public static final int TagSensorLeftBorder = 5;
    public static final int TagSensorRightBorder = 7;
    public static final int TagSensorTopBorder = 4;
    public static final int TagSensorWidth = 2;
    public static final int TagStripByteCounts = 279;
    public static final int TagStripOffsets = 273;
    public static final int TagWbBlueLevel = 38;
    public static final int TagWbGreenLevel = 37;
    public static final int TagWbInfo = 19;
    public static final int TagWbInfo2 = 39;
    public static final int TagWbRedLevel = 36;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "PanasonicRaw Exif IFD0";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    public PanasonicRawIFD0Directory() {
        setDescriptor(new PanasonicRawIFD0Descriptor(this));
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "Panasonic Raw Version");
        map.put(2, "Sensor Width");
        map.put(3, "Sensor Height");
        map.put(4, "Sensor Top Border");
        map.put(5, "Sensor Left Border");
        map.put(6, "Sensor Bottom Border");
        map.put(7, "Sensor Right Border");
        map.put(8, "Black Level 1");
        map.put(9, "Black Level 2");
        map.put(10, "Black Level 3");
        map.put(14, "Linearity Limit Red");
        map.put(15, "Linearity Limit Green");
        map.put(16, "Linearity Limit Blue");
        map.put(17, "Red Balance");
        map.put(18, "Blue Balance");
        map.put(23, ExifInterface.TAG_RW2_ISO);
        map.put(24, "High ISO Multiplier Red");
        map.put(25, "High ISO Multiplier Green");
        map.put(26, "High ISO Multiplier Blue");
        map.put(28, "Black Level Red");
        map.put(29, "Black Level Green");
        map.put(30, "Black Level Blue");
        map.put(36, "WB Red Level");
        map.put(37, "WB Green Level");
        map.put(38, "WB Blue Level");
        map.put(46, "Jpg From Raw");
        map.put(47, "Crop Top");
        map.put(48, "Crop Left");
        map.put(49, "Crop Bottom");
        map.put(50, "Crop Right");
        map.put(271, ExifInterface.TAG_MAKE);
        map.put(272, ExifInterface.TAG_MODEL);
        map.put(273, "Strip Offsets");
        map.put(274, ExifInterface.TAG_ORIENTATION);
        map.put(278, "Rows Per Strip");
        map.put(279, "Strip Byte Counts");
        map.put(280, "Raw Data Offset");
    }
}
