package com.google.firebase.firestore;

import com.google.firebase.firestore.util.Util;

/* loaded from: classes2.dex */
public class GeoPoint implements Comparable<GeoPoint> {
    private final double latitude;
    private final double longitude;

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public GeoPoint(double d, double d2) {
        if (Double.isNaN(d) || d < -90.0d || d > 90.0d) {
            throw new IllegalArgumentException("Latitude must be in the range of [-90, 90]");
        }
        if (Double.isNaN(d2) || d2 < -180.0d || d2 > 180.0d) {
            throw new IllegalArgumentException("Longitude must be in the range of [-180, 180]");
        }
        this.latitude = d;
        this.longitude = d2;
    }

    @Override // java.lang.Comparable
    public int compareTo(GeoPoint geoPoint) {
        int iCompareDoubles = Util.compareDoubles(this.latitude, geoPoint.latitude);
        return iCompareDoubles == 0 ? Util.compareDoubles(this.longitude, geoPoint.longitude) : iCompareDoubles;
    }

    public String toString() {
        return "GeoPoint { latitude=" + this.latitude + ", longitude=" + this.longitude + " }";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GeoPoint)) {
            return false;
        }
        GeoPoint geoPoint = (GeoPoint) obj;
        return this.latitude == geoPoint.latitude && this.longitude == geoPoint.longitude;
    }

    public int hashCode() {
        long jDoubleToLongBits = Double.doubleToLongBits(this.latitude);
        int i = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
        long jDoubleToLongBits2 = Double.doubleToLongBits(this.longitude);
        return (i * 31) + ((int) ((jDoubleToLongBits2 >>> 32) ^ jDoubleToLongBits2));
    }
}
