package com.drew.metadata.exif.makernotes;

import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class ReconyxHyperFireMakernoteDirectory extends Directory {
    public static final int MAKERNOTE_VERSION = 61697;
    public static final int TAG_AMBIENT_TEMPERATURE = 40;
    public static final int TAG_AMBIENT_TEMPERATURE_FAHRENHEIT = 38;
    public static final int TAG_BATTERY_VOLTAGE = 84;
    public static final int TAG_BRIGHTNESS = 74;
    public static final int TAG_CONTRAST = 72;
    public static final int TAG_DATE_TIME_ORIGINAL = 22;
    public static final int TAG_EVENT_NUMBER = 18;
    public static final int TAG_FIRMWARE_VERSION = 2;
    public static final int TAG_INFRARED_ILLUMINATOR = 80;
    public static final int TAG_MAKERNOTE_VERSION = 0;
    public static final int TAG_MOON_PHASE = 36;
    public static final int TAG_MOTION_SENSITIVITY = 82;
    public static final int TAG_SATURATION = 78;
    public static final int TAG_SEQUENCE = 14;
    public static final int TAG_SERIAL_NUMBER = 42;
    public static final int TAG_SHARPNESS = 76;
    public static final int TAG_TRIGGER_MODE = 12;
    public static final int TAG_USER_LABEL = 86;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Reconyx HyperFire Makernote";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(0, "Makernote Version");
        map.put(2, "Firmware Version");
        map.put(12, "Trigger Mode");
        map.put(14, "Sequence");
        map.put(18, "Event Number");
        map.put(22, "Date/Time Original");
        map.put(36, "Moon Phase");
        map.put(38, "Ambient Temperature Fahrenheit");
        map.put(40, "Ambient Temperature");
        map.put(42, "Serial Number");
        map.put(72, ExifInterface.TAG_CONTRAST);
        map.put(74, "Brightness");
        map.put(76, ExifInterface.TAG_SHARPNESS);
        map.put(78, ExifInterface.TAG_SATURATION);
        map.put(80, "Infrared Illuminator");
        map.put(82, "Motion Sensitivity");
        map.put(84, "Battery Voltage");
        map.put(86, "User Label");
    }

    public ReconyxHyperFireMakernoteDirectory() {
        setDescriptor(new ReconyxHyperFireMakernoteDescriptor(this));
    }
}
