package com.drew.lang;

import java.text.DecimalFormat;

/* loaded from: classes5.dex */
public final class GeoLocation {
    private final double _latitude;
    private final double _longitude;

    public double getLatitude() {
        return this._latitude;
    }

    public double getLongitude() {
        return this._longitude;
    }

    public boolean isZero() {
        return this._latitude == 0.0d && this._longitude == 0.0d;
    }

    public GeoLocation(double d, double d2) {
        this._latitude = d;
        this._longitude = d2;
    }

    public static String decimalToDegreesMinutesSecondsString(double d) {
        double[] dArrDecimalToDegreesMinutesSeconds = decimalToDegreesMinutesSeconds(d);
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        return String.format("%s° %s' %s\"", decimalFormat.format(dArrDecimalToDegreesMinutesSeconds[0]), decimalFormat.format(dArrDecimalToDegreesMinutesSeconds[1]), decimalFormat.format(dArrDecimalToDegreesMinutesSeconds[2]));
    }

    public static double[] decimalToDegreesMinutesSeconds(double d) {
        return new double[]{(int) d, (int) r7, (Math.abs((d % 1.0d) * 60.0d) % 1.0d) * 60.0d};
    }

    public static Double degreesMinutesSecondsToDecimal(Rational rational, Rational rational2, Rational rational3, boolean z) {
        double dAbs = Math.abs(rational.doubleValue()) + (rational2.doubleValue() / 60.0d) + (rational3.doubleValue() / 3600.0d);
        if (Double.isNaN(dAbs)) {
            return null;
        }
        if (z) {
            dAbs *= -1.0d;
        }
        return Double.valueOf(dAbs);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GeoLocation geoLocation = (GeoLocation) obj;
        return Double.compare(geoLocation._latitude, this._latitude) == 0 && Double.compare(geoLocation._longitude, this._longitude) == 0;
    }

    public int hashCode() {
        double d = this._latitude;
        long jDoubleToLongBits = d != 0.0d ? Double.doubleToLongBits(d) : 0L;
        int i = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
        double d2 = this._longitude;
        long jDoubleToLongBits2 = d2 != 0.0d ? Double.doubleToLongBits(d2) : 0L;
        return (i * 31) + ((int) ((jDoubleToLongBits2 >>> 32) ^ jDoubleToLongBits2));
    }

    public String toString() {
        return this._latitude + ", " + this._longitude;
    }

    public String toDMSString() {
        return decimalToDegreesMinutesSecondsString(this._latitude) + ", " + decimalToDegreesMinutesSecondsString(this._longitude);
    }
}
