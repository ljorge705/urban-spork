package com.drew.metadata.exif;

import androidx.exifinterface.media.ExifInterface;
import com.drew.lang.GeoLocation;
import com.drew.lang.Rational;
import com.drew.metadata.TagDescriptor;
import java.text.DecimalFormat;

/* loaded from: classes5.dex */
public class GpsDescriptor extends TagDescriptor<GpsDirectory> {
    public GpsDescriptor(GpsDirectory gpsDirectory) {
        super(gpsDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        switch (i) {
            case 0:
                return getGpsVersionIdDescription();
            case 1:
            case 3:
            case 8:
            case 18:
            case 19:
            case 21:
            case 29:
            default:
                return super.getDescription(i);
            case 2:
                return getGpsLatitudeDescription();
            case 4:
                return getGpsLongitudeDescription();
            case 5:
                return getGpsAltitudeRefDescription();
            case 6:
                return getGpsAltitudeDescription();
            case 7:
                return getGpsTimeStampDescription();
            case 9:
                return getGpsStatusDescription();
            case 10:
                return getGpsMeasureModeDescription();
            case 11:
                return getGpsDopDescription();
            case 12:
                return getGpsSpeedRefDescription();
            case 13:
                return getGpsSpeedDescription();
            case 14:
            case 16:
            case 23:
                return getGpsDirectionReferenceDescription(i);
            case 15:
            case 17:
            case 24:
                return getGpsDirectionDescription(i);
            case 20:
                return getGpsDestLatitudeDescription();
            case 22:
                return getGpsDestLongitudeDescription();
            case 25:
                return getGpsDestinationReferenceDescription();
            case 26:
                return getGpsDestDistanceDescription();
            case 27:
                return getGpsProcessingMethodDescription();
            case 28:
                return getGpsAreaInformationDescription();
            case 30:
                return getGpsDifferentialDescription();
            case 31:
                return getGpsHPositioningErrorDescription();
        }
    }

    private String getGpsVersionIdDescription() {
        return getVersionBytesDescription(0, 1);
    }

    public String getGpsLatitudeDescription() {
        GeoLocation geoLocation = ((GpsDirectory) this._directory).getGeoLocation();
        if (geoLocation == null) {
            return null;
        }
        return GeoLocation.decimalToDegreesMinutesSecondsString(geoLocation.getLatitude());
    }

    public String getGpsLongitudeDescription() {
        GeoLocation geoLocation = ((GpsDirectory) this._directory).getGeoLocation();
        if (geoLocation == null) {
            return null;
        }
        return GeoLocation.decimalToDegreesMinutesSecondsString(geoLocation.getLongitude());
    }

    public String getGpsTimeStampDescription() {
        Rational[] rationalArray = ((GpsDirectory) this._directory).getRationalArray(7);
        DecimalFormat decimalFormat = new DecimalFormat("00.000");
        if (rationalArray == null) {
            return null;
        }
        return String.format("%02d:%02d:%s UTC", Integer.valueOf(rationalArray[0].intValue()), Integer.valueOf(rationalArray[1].intValue()), decimalFormat.format(rationalArray[2].doubleValue()));
    }

    public String getGpsDestLatitudeDescription() {
        Double dDegreesMinutesSecondsToDecimal;
        Rational[] rationalArray = ((GpsDirectory) this._directory).getRationalArray(20);
        String string = ((GpsDirectory) this._directory).getString(19);
        if (rationalArray == null || rationalArray.length != 3 || string == null || (dDegreesMinutesSecondsToDecimal = GeoLocation.degreesMinutesSecondsToDecimal(rationalArray[0], rationalArray[1], rationalArray[2], string.equalsIgnoreCase(ExifInterface.LATITUDE_SOUTH))) == null) {
            return null;
        }
        return GeoLocation.decimalToDegreesMinutesSecondsString(dDegreesMinutesSecondsToDecimal.doubleValue());
    }

    public String getGpsDestLongitudeDescription() {
        Double dDegreesMinutesSecondsToDecimal;
        Rational[] rationalArray = ((GpsDirectory) this._directory).getRationalArray(4);
        String string = ((GpsDirectory) this._directory).getString(3);
        if (rationalArray == null || rationalArray.length != 3 || string == null || (dDegreesMinutesSecondsToDecimal = GeoLocation.degreesMinutesSecondsToDecimal(rationalArray[0], rationalArray[1], rationalArray[2], string.equalsIgnoreCase(ExifInterface.LONGITUDE_WEST))) == null) {
            return null;
        }
        return GeoLocation.decimalToDegreesMinutesSecondsString(dDegreesMinutesSecondsToDecimal.doubleValue());
    }

    public String getGpsDestinationReferenceDescription() {
        String string = ((GpsDirectory) this._directory).getString(25);
        if (string == null) {
            return null;
        }
        String strTrim = string.trim();
        return "K".equalsIgnoreCase(strTrim) ? "kilometers" : "M".equalsIgnoreCase(strTrim) ? "miles" : "N".equalsIgnoreCase(strTrim) ? "knots" : "Unknown (" + strTrim + ")";
    }

    public String getGpsDestDistanceDescription() {
        Rational rational = ((GpsDirectory) this._directory).getRational(26);
        if (rational == null) {
            return null;
        }
        String gpsDestinationReferenceDescription = getGpsDestinationReferenceDescription();
        Object[] objArr = new Object[2];
        objArr[0] = new DecimalFormat("0.##").format(rational.doubleValue());
        objArr[1] = gpsDestinationReferenceDescription == null ? "unit" : gpsDestinationReferenceDescription.toLowerCase();
        return String.format("%s %s", objArr);
    }

    public String getGpsDirectionDescription(int i) {
        String string;
        Rational rational = ((GpsDirectory) this._directory).getRational(i);
        if (rational != null) {
            string = new DecimalFormat("0.##").format(rational.doubleValue());
        } else {
            string = ((GpsDirectory) this._directory).getString(i);
        }
        if (string == null || string.trim().length() == 0) {
            return null;
        }
        return string.trim() + " degrees";
    }

    public String getGpsDirectionReferenceDescription(int i) {
        String string = ((GpsDirectory) this._directory).getString(i);
        if (string == null) {
            return null;
        }
        String strTrim = string.trim();
        return ExifInterface.GPS_DIRECTION_TRUE.equalsIgnoreCase(strTrim) ? "True direction" : "M".equalsIgnoreCase(strTrim) ? "Magnetic direction" : "Unknown (" + strTrim + ")";
    }

    public String getGpsDopDescription() {
        Rational rational = ((GpsDirectory) this._directory).getRational(11);
        if (rational == null) {
            return null;
        }
        return new DecimalFormat("0.##").format(rational.doubleValue());
    }

    public String getGpsSpeedRefDescription() {
        String string = ((GpsDirectory) this._directory).getString(12);
        if (string == null) {
            return null;
        }
        String strTrim = string.trim();
        return "K".equalsIgnoreCase(strTrim) ? "km/h" : "M".equalsIgnoreCase(strTrim) ? "mph" : "N".equalsIgnoreCase(strTrim) ? "knots" : "Unknown (" + strTrim + ")";
    }

    public String getGpsSpeedDescription() {
        Rational rational = ((GpsDirectory) this._directory).getRational(13);
        if (rational == null) {
            return null;
        }
        String gpsSpeedRefDescription = getGpsSpeedRefDescription();
        Object[] objArr = new Object[2];
        objArr[0] = new DecimalFormat("0.##").format(rational.doubleValue());
        objArr[1] = gpsSpeedRefDescription == null ? "unit" : gpsSpeedRefDescription.toLowerCase();
        return String.format("%s %s", objArr);
    }

    public String getGpsMeasureModeDescription() {
        String string = ((GpsDirectory) this._directory).getString(10);
        if (string == null) {
            return null;
        }
        String strTrim = string.trim();
        return ExifInterface.GPS_MEASUREMENT_2D.equalsIgnoreCase(strTrim) ? "2-dimensional measurement" : ExifInterface.GPS_MEASUREMENT_3D.equalsIgnoreCase(strTrim) ? "3-dimensional measurement" : "Unknown (" + strTrim + ")";
    }

    public String getGpsStatusDescription() {
        String string = ((GpsDirectory) this._directory).getString(9);
        if (string == null) {
            return null;
        }
        String strTrim = string.trim();
        return ExifInterface.GPS_MEASUREMENT_IN_PROGRESS.equalsIgnoreCase(strTrim) ? "Active (Measurement in progress)" : ExifInterface.GPS_MEASUREMENT_INTERRUPTED.equalsIgnoreCase(strTrim) ? "Void (Measurement Interoperability)" : "Unknown (" + strTrim + ")";
    }

    public String getGpsAltitudeRefDescription() {
        return getIndexedDescription(5, "Sea level", "Below sea level");
    }

    public String getGpsAltitudeDescription() {
        Rational rational = ((GpsDirectory) this._directory).getRational(6);
        if (rational == null) {
            return null;
        }
        return new DecimalFormat("0.##").format(rational.doubleValue()) + " metres";
    }

    public String getGpsProcessingMethodDescription() {
        return getEncodedTextDescription(27);
    }

    public String getGpsAreaInformationDescription() {
        return getEncodedTextDescription(28);
    }

    public String getGpsDifferentialDescription() {
        return getIndexedDescription(30, "No Correction", "Differential Corrected");
    }

    public String getGpsHPositioningErrorDescription() {
        Rational rational = ((GpsDirectory) this._directory).getRational(31);
        if (rational == null) {
            return null;
        }
        return new DecimalFormat("0.##").format(rational.doubleValue()) + " metres";
    }

    public String getDegreesMinutesSecondsDescription() {
        GeoLocation geoLocation = ((GpsDirectory) this._directory).getGeoLocation();
        if (geoLocation == null) {
            return null;
        }
        return geoLocation.toDMSString();
    }
}
