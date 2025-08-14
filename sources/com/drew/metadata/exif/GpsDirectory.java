package com.drew.metadata.exif;

import androidx.exifinterface.media.ExifInterface;
import com.drew.lang.GeoLocation;
import com.drew.lang.Rational;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes5.dex */
public class GpsDirectory extends ExifDirectoryBase {
    public static final int TAG_ALTITUDE = 6;
    public static final int TAG_ALTITUDE_REF = 5;
    public static final int TAG_AREA_INFORMATION = 28;
    public static final int TAG_DATE_STAMP = 29;
    public static final int TAG_DEST_BEARING = 24;
    public static final int TAG_DEST_BEARING_REF = 23;
    public static final int TAG_DEST_DISTANCE = 26;
    public static final int TAG_DEST_DISTANCE_REF = 25;
    public static final int TAG_DEST_LATITUDE = 20;
    public static final int TAG_DEST_LATITUDE_REF = 19;
    public static final int TAG_DEST_LONGITUDE = 22;
    public static final int TAG_DEST_LONGITUDE_REF = 21;
    public static final int TAG_DIFFERENTIAL = 30;
    public static final int TAG_DOP = 11;
    public static final int TAG_H_POSITIONING_ERROR = 31;
    public static final int TAG_IMG_DIRECTION = 17;
    public static final int TAG_IMG_DIRECTION_REF = 16;
    public static final int TAG_LATITUDE = 2;
    public static final int TAG_LATITUDE_REF = 1;
    public static final int TAG_LONGITUDE = 4;
    public static final int TAG_LONGITUDE_REF = 3;
    public static final int TAG_MAP_DATUM = 18;
    public static final int TAG_MEASURE_MODE = 10;
    public static final int TAG_PROCESSING_METHOD = 27;
    public static final int TAG_SATELLITES = 8;
    public static final int TAG_SPEED = 13;
    public static final int TAG_SPEED_REF = 12;
    public static final int TAG_STATUS = 9;
    public static final int TAG_TIME_STAMP = 7;
    public static final int TAG_TRACK = 15;
    public static final int TAG_TRACK_REF = 14;
    public static final int TAG_VERSION_ID = 0;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "GPS";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        addExifTagNames(map);
        map.put(0, "GPS Version ID");
        map.put(1, "GPS Latitude Ref");
        map.put(2, "GPS Latitude");
        map.put(3, "GPS Longitude Ref");
        map.put(4, "GPS Longitude");
        map.put(5, "GPS Altitude Ref");
        map.put(6, "GPS Altitude");
        map.put(7, "GPS Time-Stamp");
        map.put(8, "GPS Satellites");
        map.put(9, "GPS Status");
        map.put(10, "GPS Measure Mode");
        map.put(11, "GPS DOP");
        map.put(12, "GPS Speed Ref");
        map.put(13, "GPS Speed");
        map.put(14, "GPS Track Ref");
        map.put(15, "GPS Track");
        map.put(16, "GPS Img Direction Ref");
        map.put(17, "GPS Img Direction");
        map.put(18, "GPS Map Datum");
        map.put(19, "GPS Dest Latitude Ref");
        map.put(20, "GPS Dest Latitude");
        map.put(21, "GPS Dest Longitude Ref");
        map.put(22, "GPS Dest Longitude");
        map.put(23, "GPS Dest Bearing Ref");
        map.put(24, "GPS Dest Bearing");
        map.put(25, "GPS Dest Distance Ref");
        map.put(26, "GPS Dest Distance");
        map.put(27, "GPS Processing Method");
        map.put(28, "GPS Area Information");
        map.put(29, "GPS Date Stamp");
        map.put(30, "GPS Differential");
        map.put(31, "GPS H Positioning Error");
    }

    public GpsDirectory() {
        setDescriptor(new GpsDescriptor(this));
    }

    public GeoLocation getGeoLocation() {
        Rational[] rationalArray = getRationalArray(2);
        Rational[] rationalArray2 = getRationalArray(4);
        String string = getString(1);
        String string2 = getString(3);
        if (rationalArray != null && rationalArray.length == 3 && rationalArray2 != null && rationalArray2.length == 3 && string != null && string2 != null) {
            Double dDegreesMinutesSecondsToDecimal = GeoLocation.degreesMinutesSecondsToDecimal(rationalArray[0], rationalArray[1], rationalArray[2], string.equalsIgnoreCase(ExifInterface.LATITUDE_SOUTH));
            Double dDegreesMinutesSecondsToDecimal2 = GeoLocation.degreesMinutesSecondsToDecimal(rationalArray2[0], rationalArray2[1], rationalArray2[2], string2.equalsIgnoreCase(ExifInterface.LONGITUDE_WEST));
            if (dDegreesMinutesSecondsToDecimal != null && dDegreesMinutesSecondsToDecimal2 != null) {
                return new GeoLocation(dDegreesMinutesSecondsToDecimal.doubleValue(), dDegreesMinutesSecondsToDecimal2.doubleValue());
            }
        }
        return null;
    }

    public Date getGpsDate() {
        String string = getString(29);
        Rational[] rationalArray = getRationalArray(7);
        if (string != null && rationalArray != null && rationalArray.length == 3) {
            try {
                return new SimpleDateFormat("yyyy:MM:dd HH:mm:ss.S z").parse(String.format(Locale.US, "%s %02d:%02d:%02.3f UTC", string, Integer.valueOf(rationalArray[0].intValue()), Integer.valueOf(rationalArray[1].intValue()), Double.valueOf(rationalArray[2].doubleValue())));
            } catch (ParseException unused) {
            }
        }
        return null;
    }
}
