package net.time4j.calendar.astro;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.CalendarUnit;
import net.time4j.Moment;
import net.time4j.PlainDate;
import net.time4j.PlainTimestamp;
import net.time4j.engine.ChronoElement;
import net.time4j.scale.UniversalTime;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;

/* loaded from: classes4.dex */
public final class LunarTime implements GeoLocation, Serializable {
    private static final int MRD = 1000000000;
    private static final long serialVersionUID = -8029871830105935048L;
    private final int altitude;
    private final double latitude;
    private final double longitude;
    private final TZID observerZoneID;

    private static double toJulianCenturies(double d) {
        return (d - 51544.5d) / 36525.0d;
    }

    @Override // net.time4j.calendar.astro.GeoLocation
    public int getAltitude() {
        return this.altitude;
    }

    @Override // net.time4j.calendar.astro.GeoLocation
    public double getLatitude() {
        return this.latitude;
    }

    @Override // net.time4j.calendar.astro.GeoLocation
    public double getLongitude() {
        return this.longitude;
    }

    public TZID getObserverZoneID() {
        return this.observerZoneID;
    }

    private LunarTime(double d, double d2, int i, TZID tzid) {
        check(d, d2, i, tzid);
        this.latitude = d;
        this.longitude = d2;
        this.altitude = i;
        this.observerZoneID = tzid;
    }

    public static Builder ofLocation(TZID tzid) {
        if (tzid == null) {
            throw new NullPointerException("Missing observer timezone.");
        }
        return new Builder(tzid);
    }

    public static LunarTime ofLocation(TZID tzid, double d, double d2) {
        return ofLocation(tzid, d, d2, 0);
    }

    public static LunarTime ofLocation(TZID tzid, double d, double d2, int i) {
        return new LunarTime(d, d2, i, tzid);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LunarTime)) {
            return false;
        }
        LunarTime lunarTime = (LunarTime) obj;
        return this.altitude == lunarTime.altitude && Double.compare(this.latitude, lunarTime.latitude) == 0 && Double.compare(this.longitude, lunarTime.longitude) == 0 && this.observerZoneID.canonical().equals(lunarTime.observerZoneID.canonical());
    }

    public int hashCode() {
        return (AstroUtils.hashCode(this.latitude) * 7) + (AstroUtils.hashCode(this.longitude) * 31) + (this.altitude * 37);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LunarTime[,observer-tz=");
        sb.append(this.observerZoneID.canonical());
        sb.append(",latitude=");
        sb.append(this.latitude);
        sb.append(",longitude=");
        sb.append(this.longitude);
        if (this.altitude != 0) {
            sb.append(",altitude=");
            sb.append(this.altitude);
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0153  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public net.time4j.calendar.astro.LunarTime.Moonlight on(net.time4j.engine.CalendarDate r54) {
        /*
            Method dump skipped, instructions count: 352
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: net.time4j.calendar.astro.LunarTime.on(net.time4j.engine.CalendarDate):net.time4j.calendar.astro.LunarTime$Moonlight");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Moment add(Moment moment, double d) {
        double d2 = d * 3600.0d;
        long jFloor = (long) Math.floor(d2);
        return (Moment) moment.plus(jFloor, (long) TimeUnit.SECONDS).plus((long) ((d2 - jFloor) * 1.0E9d), (long) TimeUnit.NANOSECONDS).with((ChronoElement<ChronoElement<TimeUnit>>) Moment.PRECISION, (ChronoElement<TimeUnit>) TimeUnit.SECONDS);
    }

    private static double sinAlt(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        double d9 = (d2 / 24.0d) + d;
        double[] dArrCalculateMeeus47 = MoonPosition.calculateMeeus47(toJulianCenturies((d8 / 86400.0d) + d9));
        double dGmst = ((AstroUtils.gmst(d9) + Math.toRadians(dArrCalculateMeeus47[0] * Math.cos(Math.toRadians(dArrCalculateMeeus47[1])))) + d3) - Math.toRadians(dArrCalculateMeeus47[2]);
        double radians = Math.toRadians(dArrCalculateMeeus47[3]);
        return ((Math.sin(radians) * d5) + ((Math.cos(radians) * d4) * Math.cos(dGmst))) - Math.sin(Math.toRadians(((getHorizontalParallax(dArrCalculateMeeus47[4]) * 0.7275d) - d7) - d6));
    }

    private static int interpolate(double d, double d2, double d3, double[] dArr) {
        double d4;
        int i;
        double d5 = ((d3 + d) * 0.5d) - d2;
        double d6 = (d3 - d) * 0.5d;
        double d7 = (-d6) / (2.0d * d5);
        double d8 = (((d5 * d7) + d6) * d7) + d2;
        double d9 = (d6 * d6) - ((4.0d * d5) * d2);
        double d10 = Double.NaN;
        if (d9 >= 0.0d) {
            double dSqrt = (Math.sqrt(d9) * 0.5d) / Math.abs(d5);
            double d11 = d7 - dSqrt;
            if (Math.abs(d11) <= 1.0d) {
                i = 1;
            } else {
                d11 = Double.NaN;
                i = 0;
            }
            d4 = dSqrt + d7;
            if (Math.abs(d4) <= 1.0d) {
                i++;
            } else {
                d4 = Double.NaN;
            }
            d10 = d11;
        } else {
            d4 = Double.NaN;
            i = 0;
        }
        dArr[0] = d7;
        dArr[1] = d8;
        dArr[2] = d10;
        dArr[3] = d4;
        return i;
    }

    private static double getHorizontalParallax(double d) {
        return Math.toDegrees(Math.asin(6378.14d / d));
    }

    private static void check(double d, double d2, int i, TZID tzid) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException("Latitude must be a finite value: " + d);
        }
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            throw new IllegalArgumentException("Longitude must be a finite value: " + d2);
        }
        if (Double.compare(d, 90.0d) > 0 || Double.compare(d, -90.0d) < 0) {
            throw new IllegalArgumentException("Degrees out of range -90.0 <= latitude <= +90.0: " + d);
        }
        if (Double.compare(d2, 180.0d) >= 0 || Double.compare(d2, -180.0d) < 0) {
            throw new IllegalArgumentException("Degrees out of range -180.0 <= longitude < +180.0: " + d2);
        }
        double d3 = i;
        if (Double.isNaN(d3) || Double.isInfinite(d3)) {
            throw new IllegalArgumentException("Altitude must be finite: " + i);
        }
        if (i < 0 || i >= 11000) {
            throw new IllegalArgumentException("Meters out of range 0 <= altitude < +11,000: " + i);
        }
        Timezone.of(tzid);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        check(this.latitude, this.longitude, this.altitude, this.observerZoneID);
    }

    public static class Builder {
        private int altitude;
        private double latitude;
        private double longitude;
        private final TZID observerZoneID;

        private Builder(TZID tzid) {
            this.latitude = Double.NaN;
            this.longitude = Double.NaN;
            this.altitude = 0;
            this.observerZoneID = tzid;
        }

        public Builder northernLatitude(int i, int i2, double d) {
            check(i, i2, d, 90);
            if (!Double.isNaN(this.latitude)) {
                throw new IllegalStateException("Latitude has already been set.");
            }
            this.latitude = i + (i2 / 60.0d) + (d / 3600.0d);
            return this;
        }

        public Builder southernLatitude(int i, int i2, double d) {
            check(i, i2, d, 90);
            if (!Double.isNaN(this.latitude)) {
                throw new IllegalStateException("Latitude has already been set.");
            }
            this.latitude = (i + (i2 / 60.0d) + (d / 3600.0d)) * (-1.0d);
            return this;
        }

        public Builder easternLongitude(int i, int i2, double d) {
            check(i, i2, d, 179);
            if (!Double.isNaN(this.longitude)) {
                throw new IllegalStateException("Longitude has already been set.");
            }
            this.longitude = i + (i2 / 60.0d) + (d / 3600.0d);
            return this;
        }

        public Builder westernLongitude(int i, int i2, double d) {
            check(i, i2, d, 180);
            if (!Double.isNaN(this.longitude)) {
                throw new IllegalStateException("Longitude has already been set.");
            }
            this.longitude = (i + (i2 / 60.0d) + (d / 3600.0d)) * (-1.0d);
            return this;
        }

        public Builder atAltitude(int i) {
            double d = i;
            if (Double.isInfinite(d) || Double.isNaN(d)) {
                throw new IllegalArgumentException("Altitude must be finite: " + i);
            }
            if (i < 0 || i >= 11000) {
                throw new IllegalArgumentException("Meters out of range 0 <= altitude < +11,000: " + i);
            }
            this.altitude = i;
            return this;
        }

        public LunarTime build() {
            if (Double.isNaN(this.latitude)) {
                throw new IllegalStateException("Latitude was not yet set.");
            }
            if (Double.isNaN(this.longitude)) {
                throw new IllegalStateException("Longitude was not yet set.");
            }
            return new LunarTime(this.latitude, this.longitude, this.altitude, this.observerZoneID);
        }

        private static void check(int i, int i2, double d, int i3) {
            if (i < 0 || i > i3 || (i == i3 && i3 != 179 && (i2 > 0 || Double.compare(d, 0.0d) > 0))) {
                throw new IllegalArgumentException("Degrees out of range: " + i + " (decimal=" + (i + (i2 / 60.0d) + (d / 3600.0d)) + ")");
            }
            if (i2 < 0 || i2 >= 60) {
                throw new IllegalArgumentException("Arc minutes out of range: " + i2);
            }
            if (Double.isNaN(d) || Double.isInfinite(d)) {
                throw new IllegalArgumentException("Arc seconds must be finite.");
            }
            if (Double.compare(d, 0.0d) < 0 || Double.compare(d, 60.0d) >= 0) {
                throw new IllegalArgumentException("Arc seconds out of range: " + d);
            }
        }
    }

    public static class Moonlight {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final boolean above;
        private final Moment endOfDay;
        private final Moment moonrise;
        private final Moment moonset;
        private final TZID observerZoneID;
        private final Moment startOfDay;

        public boolean isPresentAllDay() {
            return this.above && this.moonrise == null && this.moonset == null;
        }

        public Moment moonrise() {
            return this.moonrise;
        }

        public Moment moonset() {
            return this.moonset;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Moonlight(PlainDate plainDate, TZID tzid, Moment moment, Moment moment2, boolean z) {
            this.observerZoneID = tzid;
            Timezone timezoneOf = Timezone.of(tzid);
            PlainDate plainDate2 = (PlainDate) plainDate.plus(1L, CalendarUnit.DAYS);
            if (timezoneOf.getHistory() == null) {
                this.startOfDay = plainDate.atStartOfDay().in(timezoneOf);
                this.endOfDay = plainDate2.atStartOfDay().in(timezoneOf);
            } else {
                this.startOfDay = plainDate.atFirstMoment(tzid);
                this.endOfDay = plainDate2.atFirstMoment(tzid);
            }
            this.moonrise = moment;
            this.moonset = moment2;
            this.above = z;
        }

        public PlainTimestamp moonriseLocal() {
            Moment moment = this.moonrise;
            if (moment == null) {
                return null;
            }
            return moment.toZonalTimestamp(this.observerZoneID);
        }

        public PlainTimestamp moonrise(TZID tzid) {
            Moment moment = this.moonrise;
            if (moment == null) {
                return null;
            }
            return moment.toZonalTimestamp(tzid);
        }

        public PlainTimestamp moonsetLocal() {
            Moment moment = this.moonset;
            if (moment == null) {
                return null;
            }
            return moment.toZonalTimestamp(this.observerZoneID);
        }

        public PlainTimestamp moonset(TZID tzid) {
            Moment moment = this.moonset;
            if (moment == null) {
                return null;
            }
            return moment.toZonalTimestamp(tzid);
        }

        public boolean isPresent(Moment moment) {
            if (moment.isBefore(this.startOfDay) || !moment.isBefore(this.endOfDay)) {
                return false;
            }
            Moment moment2 = this.moonrise;
            if (moment2 == null) {
                UniversalTime universalTime = this.moonset;
                return universalTime == null ? this.above : moment.isBefore(universalTime);
            }
            Moment moment3 = this.moonset;
            if (moment3 == null) {
                return !moment.isBefore((UniversalTime) moment2);
            }
            return moment2.isBefore((UniversalTime) moment3) ? !moment.isBefore(this.moonrise) && moment.isBefore(this.moonset) : moment.isBefore(this.moonset) || !moment.isBefore(this.moonrise);
        }

        public boolean isAbsent() {
            return length() == 0;
        }

        public int length() {
            long jUntil;
            Moment moment = this.moonrise;
            if (moment == null) {
                Moment moment2 = this.moonset;
                if (moment2 != null) {
                    jUntil = this.startOfDay.until(moment2, (Moment) TimeUnit.SECONDS);
                } else {
                    if (!this.above) {
                        return 0;
                    }
                    jUntil = this.startOfDay.until(this.endOfDay, (Moment) TimeUnit.SECONDS);
                }
            } else {
                Moment moment3 = this.moonset;
                if (moment3 == null) {
                    jUntil = moment.until(this.endOfDay, (Moment) TimeUnit.SECONDS);
                } else if (moment.isBefore((UniversalTime) moment3)) {
                    jUntil = this.moonrise.until(this.moonset, (Moment) TimeUnit.SECONDS);
                } else {
                    jUntil = this.startOfDay.until(this.moonset, (Moment) TimeUnit.SECONDS) + this.moonrise.until(this.endOfDay, (Moment) TimeUnit.SECONDS);
                }
            }
            return (int) jUntil;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Moonlight[tz=");
            sb.append(this.observerZoneID.canonical());
            sb.append(" | ");
            Moment moment = this.moonrise;
            if (moment != null) {
                Moment moment2 = this.moonset;
                if (moment2 == null) {
                    sb.append("moonrise=");
                    sb.append(this.moonrise.toZonalTimestamp(this.observerZoneID));
                } else if (moment.isBefore((UniversalTime) moment2)) {
                    sb.append("moonrise=");
                    sb.append(this.moonrise.toZonalTimestamp(this.observerZoneID));
                    sb.append(" | moonset=");
                    sb.append(this.moonset.toZonalTimestamp(this.observerZoneID));
                } else {
                    sb.append("moonset=");
                    sb.append(this.moonset.toZonalTimestamp(this.observerZoneID));
                    sb.append(" | moonrise=");
                    sb.append(this.moonrise.toZonalTimestamp(this.observerZoneID));
                }
            } else if (this.moonset == null) {
                sb.append("always ");
                sb.append(this.above ? "up" : "down");
            } else {
                sb.append("moonset=");
                sb.append(this.moonset.toZonalTimestamp(this.observerZoneID));
            }
            sb.append(" | length=");
            sb.append(length());
            sb.append(AbstractJsonLexerKt.END_LIST);
            return sb.toString();
        }
    }
}
