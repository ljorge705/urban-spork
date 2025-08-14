package com.drew.metadata.exif.makernotes;

import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class ReconyxUltraFireMakernoteDirectory extends Directory {
    public static final int MAKERNOTE_ID = 65536;
    public static final int MAKERNOTE_PUBLIC_ID = 133234689;
    public static final int TAG_AMBIENT_TEMPERATURE = 70;
    public static final int TAG_AMBIENT_TEMPERATURE_FAHRENHEIT = 68;
    public static final int TAG_BATTERY_VOLTAGE = 73;
    public static final int TAG_BTL_VERSION = 38;
    public static final int TAG_CAMERA_VERSION = 24;
    public static final int TAG_DATE_TIME_ORIGINAL = 59;
    public static final int TAG_DAY_OF_WEEK = 66;
    public static final int TAG_EVENT_NUMBER = 55;
    public static final int TAG_EVENT_TYPE = 52;
    public static final int TAG_FLASH = 72;
    public static final int TAG_LABEL = 0;
    public static final int TAG_MAKERNOTE_ID = 10;
    public static final int TAG_MAKERNOTE_PUBLIC_ID = 18;
    public static final int TAG_MAKERNOTE_PUBLIC_SIZE = 22;
    public static final int TAG_MAKERNOTE_SIZE = 14;
    public static final int TAG_MOON_PHASE = 67;
    public static final int TAG_PEX_VERSION = 45;
    public static final int TAG_SEQUENCE = 53;
    public static final int TAG_SERIAL_NUMBER = 75;
    public static final int TAG_UIB_VERSION = 31;
    public static final int TAG_USER_LABEL = 80;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Reconyx UltraFire Makernote";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(0, "Makernote Label");
        map.put(10, "Makernote ID");
        map.put(14, "Makernote Size");
        map.put(18, "Makernote Public ID");
        map.put(22, "Makernote Public Size");
        map.put(24, "Camera Version");
        map.put(31, "Uib Version");
        map.put(38, "Btl Version");
        map.put(45, "Pex Version");
        map.put(52, "Event Type");
        map.put(53, "Sequence");
        map.put(55, "Event Number");
        map.put(59, "Date/Time Original");
        map.put(66, "Day of Week");
        map.put(67, "Moon Phase");
        map.put(68, "Ambient Temperature Fahrenheit");
        map.put(70, "Ambient Temperature");
        map.put(72, ExifInterface.TAG_FLASH);
        map.put(73, "Battery Voltage");
        map.put(75, "Serial Number");
        map.put(80, "User Label");
    }

    public ReconyxUltraFireMakernoteDirectory() {
        setDescriptor(new ReconyxUltraFireMakernoteDescriptor(this));
    }
}
