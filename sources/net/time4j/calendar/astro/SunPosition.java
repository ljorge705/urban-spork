package net.time4j.calendar.astro;

import java.io.Serializable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.Moment;
import net.time4j.calendar.astro.Zodiac;

/* loaded from: classes4.dex */
public class SunPosition implements EquatorialCoordinates, Serializable {
    private static final long serialVersionUID = -3023032442869934354L;
    private final double azimuth;
    private final double declination;
    private final double elevation;
    private final double rightAscension;

    public double getAzimuth() {
        return this.azimuth;
    }

    @Override // net.time4j.calendar.astro.EquatorialCoordinates
    public double getDeclination() {
        return this.declination;
    }

    public double getElevation() {
        return this.elevation;
    }

    @Override // net.time4j.calendar.astro.EquatorialCoordinates
    public double getRightAscension() {
        return this.rightAscension;
    }

    private SunPosition(double d, double d2, double d3, double d4) {
        this.rightAscension = d;
        this.declination = d2;
        this.azimuth = d3;
        this.elevation = d4;
    }

    public static SunPosition at(Moment moment, GeoLocation geoLocation) {
        JulianDay julianDayOfEphemerisTime = JulianDay.ofEphemerisTime(moment);
        double centuryJ2000 = julianDayOfEphemerisTime.getCenturyJ2000();
        StdSolarCalculator stdSolarCalculator = StdSolarCalculator.TIME4J;
        double[] dArr = new double[2];
        StdSolarCalculator.nutations(centuryJ2000, dArr);
        double d = dArr[0];
        double dMeanObliquity = StdSolarCalculator.meanObliquity(centuryJ2000) + dArr[1];
        double radians = Math.toRadians(stdSolarCalculator.rightAscension(julianDayOfEphemerisTime.getValue()));
        double radians2 = Math.toRadians(stdSolarCalculator.declination(julianDayOfEphemerisTime.getValue()));
        double radians3 = Math.toRadians(geoLocation.getLatitude());
        double radians4 = Math.toRadians(geoLocation.getLongitude());
        double dCos = Math.cos(radians3);
        double dSin = Math.sin(radians3);
        int altitude = geoLocation.getAltitude();
        double dGmst = ((AstroUtils.gmst(JulianDay.ofMeanSolarTime(moment).getMJD()) + Math.toRadians(d * Math.cos(Math.toRadians(dMeanObliquity)))) + radians4) - radians;
        double degrees = Math.toDegrees(Math.asin((Math.sin(radians2) * dSin) + (Math.cos(radians2) * dCos * Math.cos(dGmst))));
        if (degrees >= (-0.5d) - stdSolarCalculator.getGeodeticAngle(geoLocation.getLatitude(), altitude)) {
            degrees += (AstroUtils.refractionFactorOfStdAtmosphere(altitude) * AstroUtils.getRefraction(degrees)) / 60.0d;
        }
        double degrees2 = Math.toDegrees(Math.atan2(Math.sin(dGmst), (Math.cos(dGmst) * dSin) - (Math.tan(radians2) * dCos))) + 180.0d;
        return new SunPosition(Math.toDegrees(radians), Math.toDegrees(radians2), degrees2, degrees);
    }

    public static Zodiac.Event inConstellationOf(Zodiac zodiac) {
        return Zodiac.Event.ofConstellation('S', zodiac);
    }

    public static Zodiac.Event inSignOf(Zodiac zodiac) {
        return Zodiac.Event.ofSign('S', zodiac);
    }

    public double getShadowLength(double d) {
        double elevation = getElevation();
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException("Object height must be finite and positive: " + d);
        }
        if (d <= 0.0d) {
            throw new IllegalArgumentException("Object height must be greater than zero: " + d);
        }
        if (elevation <= 0.0d) {
            return Double.POSITIVE_INFINITY;
        }
        if (elevation == 90.0d) {
            return 0.0d;
        }
        double dTan = d / Math.tan(Math.toRadians(elevation));
        if (dTan < 0.016666666666666666d) {
            return 0.0d;
        }
        return dTan;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SunPosition)) {
            return false;
        }
        SunPosition sunPosition = (SunPosition) obj;
        return this.rightAscension == sunPosition.rightAscension && this.declination == sunPosition.declination && this.azimuth == sunPosition.azimuth && this.elevation == sunPosition.elevation;
    }

    public int hashCode() {
        return AstroUtils.hashCode(this.rightAscension) + (AstroUtils.hashCode(this.declination) * 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append("sun-position[ra=");
        sb.append(this.rightAscension);
        sb.append(",decl=");
        sb.append(this.declination);
        sb.append(",azimuth=");
        sb.append(this.azimuth);
        sb.append(",elevation=");
        sb.append(this.elevation);
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }
}
