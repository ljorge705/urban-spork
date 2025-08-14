package net.time4j.calendar.astro;

import com.drew.metadata.exif.makernotes.NikonType2MakernoteDirectory;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.CalendarUnit;
import net.time4j.ClockUnit;
import net.time4j.Moment;
import net.time4j.PlainDate;
import net.time4j.PlainTime;
import net.time4j.PlainTimestamp;
import net.time4j.base.MathUtils;
import net.time4j.base.ResourceLoader;
import net.time4j.engine.CalendarDate;
import net.time4j.engine.ChronoCondition;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoException;
import net.time4j.engine.ChronoFunction;
import net.time4j.engine.EpochDays;
import net.time4j.scale.LeapSeconds;
import net.time4j.scale.TimeScale;
import net.time4j.scale.UniversalTime;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;
import net.time4j.tz.ZonalOffset;

/* loaded from: classes4.dex */
public final class SolarTime implements GeoLocation, Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final double ARC_MIN = 0.016666666666666666d;
    private static final ConcurrentMap<String, Calculator> CALCULATORS;
    static final String DECLINATION = "declination";
    private static final Calculator DEFAULT_CALCULATOR;
    private static final SolarTime JERUSALEM;
    private static final SolarTime MECCA;
    static final String RIGHT_ASCENSION = "right-ascension";
    static final double STD_REFRACTION = 34.0d;
    static final double STD_ZENITH = 90.83333333333333d;
    static final double SUN_RADIUS = 16.0d;
    private static final long serialVersionUID = -4816619838743247977L;
    private final int altitude;
    private final String calculator;
    private final double latitude;
    private final double longitude;
    private final TZID observerZoneID;

    public interface Calculator {
        double equationOfTime(double d);

        double getFeature(double d, String str);

        double getGeodeticAngle(double d, int i);

        double getZenithAngle(double d, int i);

        String name();

        Moment sunrise(CalendarDate calendarDate, double d, double d2, double d3);

        Moment sunset(CalendarDate calendarDate, double d, double d2, double d3);
    }

    public static SolarTime ofJerusalem() {
        return JERUSALEM;
    }

    public static SolarTime ofMecca() {
        return MECCA;
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

    static {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        calculator = null;
        for (Calculator calculator : ResourceLoader.getInstance().services(Calculator.class)) {
            concurrentHashMap.put(calculator.name(), calculator);
        }
        for (StdSolarCalculator stdSolarCalculator : StdSolarCalculator.values()) {
            concurrentHashMap.put(stdSolarCalculator.name(), stdSolarCalculator);
        }
        CALCULATORS = concurrentHashMap;
        if (calculator == null) {
            calculator = StdSolarCalculator.NOAA;
        }
        DEFAULT_CALCULATOR = calculator;
        JERUSALEM = ofLocation().easternLongitude(35, 14, 5.0d).northernLatitude(31, 46, 44.0d).atAltitude(721).usingCalculator(StdSolarCalculator.TIME4J).build();
        MECCA = ofLocation().easternLongitude(39, 49, 34.06d).northernLatitude(21, 25, 21.22d).atAltitude(298).usingCalculator(StdSolarCalculator.TIME4J).build();
    }

    private SolarTime(double d, double d2, int i, String str, TZID tzid) {
        this.latitude = d;
        this.longitude = d2;
        this.altitude = i;
        this.calculator = str;
        this.observerZoneID = tzid;
    }

    public static Builder ofLocation() {
        return new Builder();
    }

    public static SolarTime ofLocation(double d, double d2) {
        return ofLocation(d, d2, 0, DEFAULT_CALCULATOR);
    }

    public static SolarTime ofLocation(double d, double d2, int i, String str) {
        check(d, d2, i, str);
        return new SolarTime(d, d2, i, str, null);
    }

    public static SolarTime ofLocation(double d, double d2, int i, Calculator calculator) {
        String strName = calculator.name();
        CALCULATORS.putIfAbsent(strName, calculator);
        check(d, d2, i, strName);
        return new SolarTime(d, d2, i, strName, null);
    }

    public Calculator getCalculator() {
        return CALCULATORS.get(this.calculator);
    }

    public ChronoFunction<CalendarDate, Moment> sunrise() {
        return new ChronoFunction<CalendarDate, Moment>() { // from class: net.time4j.calendar.astro.SolarTime.1
            @Override // net.time4j.engine.ChronoFunction
            public Moment apply(CalendarDate calendarDate) {
                return SolarTime.this.getCalculator().sunrise(SolarTime.this.toLMT(calendarDate), SolarTime.this.latitude, SolarTime.this.longitude, SolarTime.this.zenithAngle());
            }
        };
    }

    public ChronoFunction<CalendarDate, Moment> sunrise(Twilight twilight) {
        final double dGeodeticAngle = geodeticAngle() + 90.0d + twilight.getAngle();
        return new ChronoFunction<CalendarDate, Moment>() { // from class: net.time4j.calendar.astro.SolarTime.2
            @Override // net.time4j.engine.ChronoFunction
            public Moment apply(CalendarDate calendarDate) {
                return SolarTime.this.getCalculator().sunrise(SolarTime.this.toLMT(calendarDate), SolarTime.this.latitude, SolarTime.this.longitude, dGeodeticAngle);
            }
        };
    }

    public ChronoFunction<CalendarDate, Moment> sunset() {
        return new ChronoFunction<CalendarDate, Moment>() { // from class: net.time4j.calendar.astro.SolarTime.3
            @Override // net.time4j.engine.ChronoFunction
            public Moment apply(CalendarDate calendarDate) {
                return SolarTime.this.getCalculator().sunset(SolarTime.this.toLMT(calendarDate), SolarTime.this.latitude, SolarTime.this.longitude, SolarTime.this.zenithAngle());
            }
        };
    }

    public ChronoFunction<CalendarDate, Moment> sunset(Twilight twilight) {
        final double dGeodeticAngle = geodeticAngle() + 90.0d + twilight.getAngle();
        return new ChronoFunction<CalendarDate, Moment>() { // from class: net.time4j.calendar.astro.SolarTime.4
            @Override // net.time4j.engine.ChronoFunction
            public Moment apply(CalendarDate calendarDate) {
                return SolarTime.this.getCalculator().sunset(SolarTime.this.toLMT(calendarDate), SolarTime.this.latitude, SolarTime.this.longitude, dGeodeticAngle);
            }
        };
    }

    public ChronoFunction<CalendarDate, Sunshine> sunshine(final TZID tzid) {
        return new ChronoFunction<CalendarDate, Sunshine>() { // from class: net.time4j.calendar.astro.SolarTime.5
            @Override // net.time4j.engine.ChronoFunction
            public Sunshine apply(CalendarDate calendarDate) {
                PlainDate gregorian = SolarTime.toGregorian(SolarTime.this.toLMT(calendarDate));
                Calculator calculator = SolarTime.this.getCalculator();
                double dZenithAngle = SolarTime.this.zenithAngle();
                Moment momentSunrise = calculator.sunrise(calendarDate, SolarTime.this.latitude, SolarTime.this.longitude, dZenithAngle);
                Moment momentSunset = calculator.sunset(calendarDate, SolarTime.this.latitude, SolarTime.this.longitude, dZenithAngle);
                return new Sunshine(gregorian, momentSunrise, momentSunset, tzid, momentSunrise == null && momentSunset == null && Double.compare(SolarTime.this.getHighestElevationOfSun(gregorian), 90.0d - dZenithAngle) < 0);
            }
        };
    }

    public ChronoCondition<CalendarDate> polarNight() {
        return new ChronoCondition<CalendarDate>() { // from class: net.time4j.calendar.astro.SolarTime.6
            @Override // net.time4j.engine.ChronoCondition
            public boolean test(CalendarDate calendarDate) {
                if (Double.compare(Math.abs(SolarTime.this.latitude), 66.0d) < 0) {
                    return false;
                }
                PlainDate gregorian = SolarTime.toGregorian(SolarTime.this.toLMT(calendarDate));
                Calculator calculator = SolarTime.this.getCalculator();
                double dZenithAngle = SolarTime.this.zenithAngle();
                return calculator.sunrise(calendarDate, SolarTime.this.latitude, SolarTime.this.longitude, dZenithAngle) == null && calculator.sunset(calendarDate, SolarTime.this.latitude, SolarTime.this.longitude, dZenithAngle) == null && Double.compare(SolarTime.this.getHighestElevationOfSun(gregorian), 90.0d - dZenithAngle) < 0;
            }
        };
    }

    public ChronoCondition<CalendarDate> midnightSun() {
        return new ChronoCondition<CalendarDate>() { // from class: net.time4j.calendar.astro.SolarTime.7
            @Override // net.time4j.engine.ChronoCondition
            public boolean test(CalendarDate calendarDate) {
                if (Double.compare(Math.abs(SolarTime.this.latitude), 66.0d) < 0) {
                    return false;
                }
                PlainDate gregorian = SolarTime.toGregorian(SolarTime.this.toLMT(calendarDate));
                Calculator calculator = SolarTime.this.getCalculator();
                double dZenithAngle = SolarTime.this.zenithAngle();
                return calculator.sunrise(calendarDate, SolarTime.this.latitude, SolarTime.this.longitude, dZenithAngle) == null && calculator.sunset(calendarDate, SolarTime.this.latitude, SolarTime.this.longitude, dZenithAngle) == null && Double.compare(SolarTime.this.getHighestElevationOfSun(gregorian), 90.0d - dZenithAngle) > 0;
            }
        };
    }

    public ChronoFunction<CalendarDate, Moment> transitAtNoon() {
        return new ChronoFunction<CalendarDate, Moment>() { // from class: net.time4j.calendar.astro.SolarTime.8
            @Override // net.time4j.engine.ChronoFunction
            public Moment apply(CalendarDate calendarDate) {
                return SolarTime.transitAtNoon(SolarTime.this.toLMT(calendarDate), SolarTime.this.longitude, SolarTime.this.calculator);
            }
        };
    }

    public ChronoFunction<CalendarDate, Moment> transitAtMidnight() {
        return new ChronoFunction<CalendarDate, Moment>() { // from class: net.time4j.calendar.astro.SolarTime.9
            @Override // net.time4j.engine.ChronoFunction
            public Moment apply(CalendarDate calendarDate) {
                return SolarTime.transitAtMidnight(SolarTime.this.toLMT(calendarDate), SolarTime.this.longitude, SolarTime.this.calculator);
            }
        };
    }

    public ChronoFunction<CalendarDate, Moment> timeOfShadowBeforeNoon(final double d, final double d2) {
        checkShadow(d, d2);
        return new ChronoFunction<CalendarDate, Moment>() { // from class: net.time4j.calendar.astro.SolarTime.10
            @Override // net.time4j.engine.ChronoFunction
            public Moment apply(CalendarDate calendarDate) {
                return SolarTime.this.timeOfShadow(calendarDate, false, d, d2);
            }
        };
    }

    public ChronoFunction<CalendarDate, Moment> timeOfShadowAfterNoon(final double d, final double d2) {
        checkShadow(d, d2);
        return new ChronoFunction<CalendarDate, Moment>() { // from class: net.time4j.calendar.astro.SolarTime.11
            @Override // net.time4j.engine.ChronoFunction
            public Moment apply(CalendarDate calendarDate) {
                return SolarTime.this.timeOfShadow(calendarDate, true, d, d2);
            }
        };
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SolarTime)) {
            return false;
        }
        SolarTime solarTime = (SolarTime) obj;
        return this.calculator.equals(solarTime.calculator) && Double.compare(this.latitude, solarTime.latitude) == 0 && Double.compare(this.longitude, solarTime.longitude) == 0 && this.altitude == solarTime.altitude && equalZones(this.observerZoneID, solarTime.observerZoneID);
    }

    public int hashCode() {
        return this.calculator.hashCode() + (AstroUtils.hashCode(this.latitude) * 7) + (AstroUtils.hashCode(this.longitude) * 31) + (this.altitude * 37);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SolarTime[latitude=");
        sb.append(this.latitude);
        sb.append(",longitude=");
        sb.append(this.longitude);
        if (this.altitude != 0) {
            sb.append(",altitude=");
            sb.append(this.altitude);
        }
        if (!this.calculator.equals(DEFAULT_CALCULATOR.name())) {
            sb.append(",calculator=");
            sb.append(this.calculator);
        }
        if (this.observerZoneID != null) {
            sb.append(",observerZoneID=");
            sb.append(this.observerZoneID.canonical());
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    public static ChronoFunction<Moment, PlainTimestamp> apparentAt(final ZonalOffset zonalOffset) {
        return new ChronoFunction<Moment, PlainTimestamp>() { // from class: net.time4j.calendar.astro.SolarTime.12
            @Override // net.time4j.engine.ChronoFunction
            public PlainTimestamp apply(Moment moment) {
                return SolarTime.onAverage(moment, zonalOffset).plus((long) Math.floor(SolarTime.equationOfTime(moment)), ClockUnit.SECONDS).plus((int) ((r1 - r3) * 1.0E9d), ClockUnit.NANOS);
            }
        };
    }

    public static ChronoFunction<Moment, PlainTimestamp> apparentAt(final ZonalOffset zonalOffset, final String str) {
        return new ChronoFunction<Moment, PlainTimestamp>() { // from class: net.time4j.calendar.astro.SolarTime.13
            @Override // net.time4j.engine.ChronoFunction
            public PlainTimestamp apply(Moment moment) {
                return SolarTime.onAverage(moment, zonalOffset).plus((long) Math.floor(SolarTime.equationOfTime(moment, str)), ClockUnit.SECONDS).plus((int) ((r1 - r3) * 1.0E9d), ClockUnit.NANOS);
            }
        };
    }

    public static ChronoFunction<Moment, PlainTimestamp> onAverage(final ZonalOffset zonalOffset) {
        return new ChronoFunction<Moment, PlainTimestamp>() { // from class: net.time4j.calendar.astro.SolarTime.14
            @Override // net.time4j.engine.ChronoFunction
            public PlainTimestamp apply(Moment moment) {
                return SolarTime.onAverage(moment, zonalOffset);
            }
        };
    }

    public static double equationOfTime(Moment moment) {
        return DEFAULT_CALCULATOR.equationOfTime(JulianDay.getValue(moment, TimeScale.TT));
    }

    public static double equationOfTime(Moment moment, String str) {
        if (str == null) {
            throw new NullPointerException("Missing calculator parameter.");
        }
        ConcurrentMap<String, Calculator> concurrentMap = CALCULATORS;
        if (concurrentMap.containsKey(str)) {
            return concurrentMap.get(str).equationOfTime(JulianDay.getValue(moment, TimeScale.TT));
        }
        throw new IllegalArgumentException("Unknown calculator: " + str);
    }

    double getHighestElevationOfSun(PlainDate plainDate) {
        double radians = Math.toRadians(getCalculator().getFeature(JulianDay.getValue((Moment) plainDate.get(transitAtNoon()), TimeScale.TT), DECLINATION));
        double radians2 = Math.toRadians(this.latitude);
        double degrees = Math.toDegrees(Math.asin((Math.sin(radians2) * Math.sin(radians)) + (Math.cos(radians2) * Math.cos(radians))));
        if (Double.isNaN(degrees)) {
            throw new UnsupportedOperationException("Solar declination not supported by: " + getCalculator().name());
        }
        return degrees;
    }

    static PlainDate toGregorian(CalendarDate calendarDate) {
        if (calendarDate instanceof PlainDate) {
            return (PlainDate) calendarDate;
        }
        return PlainDate.of(calendarDate.getDaysSinceEpochUTC(), EpochDays.UTC);
    }

    static Moment fromLocalEvent(CalendarDate calendarDate, int i, double d, String str) {
        Calculator calculator = CALCULATORS.get(str);
        double daysSinceEpochUTC = ((calendarDate.getDaysSinceEpochUTC() * 86400) + (i * NikonType2MakernoteDirectory.TAG_NIKON_SCAN)) - (d * 240.0d);
        long jFloor = (long) Math.floor(daysSinceEpochUTC);
        int i2 = (int) ((daysSinceEpochUTC - jFloor) * 1.0E9d);
        TimeScale timeScale = TimeScale.UT;
        if (!LeapSeconds.getInstance().isEnabled()) {
            jFloor += 63072000;
            timeScale = TimeScale.POSIX;
        }
        Moment momentOf = Moment.of(jFloor, i2, timeScale);
        return momentOf.minus((long) Math.floor(calculator.equationOfTime(JulianDay.getValue(momentOf.minus((long) Math.floor(calculator.equationOfTime(JulianDay.getValue(momentOf, TimeScale.TT))), (long) TimeUnit.SECONDS).minus((int) ((r7 - r2) * 1.0E9d), (long) TimeUnit.NANOSECONDS), TimeScale.TT))), (long) TimeUnit.SECONDS).minus((int) ((r7 - r9) * 1.0E9d), (long) TimeUnit.NANOSECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static PlainTimestamp onAverage(Moment moment, ZonalOffset zonalOffset) {
        return Moment.of(moment.getElapsedTime(TimeScale.UT) + 63072000, moment.getNanosecond(TimeScale.UT), TimeScale.POSIX).toZonalTimestamp(zonalOffset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static Moment transitAtNoon(CalendarDate calendarDate, double d, String str) {
        return (Moment) fromLocalEvent(calendarDate, 12, d, str).with((ChronoElement<ChronoElement<TimeUnit>>) Moment.PRECISION, (ChronoElement<TimeUnit>) precision(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static Moment transitAtMidnight(CalendarDate calendarDate, double d, String str) {
        return (Moment) fromLocalEvent(calendarDate, 0, d, str).with((ChronoElement<ChronoElement<TimeUnit>>) Moment.PRECISION, (ChronoElement<TimeUnit>) precision(str));
    }

    private void checkShadow(double d, double d2) {
        if (Double.isInfinite(d) || Double.isNaN(d) || d <= 0.0d) {
            throw new IllegalArgumentException("Object height must be finite and positive.");
        }
        if (Double.isInfinite(d2) || Double.isNaN(d2) || d2 < 0.0d) {
            throw new IllegalArgumentException("Length of shadow must be finite and not negative.");
        }
        if (Math.abs(this.latitude) > 66.0d) {
            throw new UnsupportedOperationException("Cannot calculate time of shadow for polar regions.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Moment timeOfShadow(CalendarDate calendarDate, boolean z, double d, double d2) {
        PlainDate gregorian = toGregorian(toLMT(calendarDate));
        Moment moment = (Moment) gregorian.get(z ? sunset() : sunrise());
        Moment moment2 = (Moment) gregorian.get(transitAtNoon());
        double elevation = SunPosition.at(moment2, this).getElevation();
        if (elevation <= ARC_MIN) {
            return moment;
        }
        double degrees = d2 == 0.0d ? 90.0d : Math.toDegrees(Math.atan(d / d2));
        if (degrees > elevation + ARC_MIN) {
            return null;
        }
        return timeOfShadow(moment.getPosixTime(), moment2.getPosixTime(), degrees);
    }

    private Moment timeOfShadow(long j, long j2, double d) {
        Moment momentOf = Moment.of(MathUtils.safeAdd(j, j2) / 2, TimeScale.POSIX);
        if (Math.abs(SunPosition.at(momentOf, this).getElevation() - d) < ARC_MIN) {
            return momentOf;
        }
        if (Double.compare(d, r1) > 0.0d) {
            return timeOfShadow(momentOf.getPosixTime(), j2, d);
        }
        return timeOfShadow(j, momentOf.getPosixTime(), d);
    }

    private static TimeUnit precision(String str) {
        return str.equals(StdSolarCalculator.SIMPLE.name()) ? TimeUnit.MINUTES : TimeUnit.SECONDS;
    }

    private double geodeticAngle() {
        return getCalculator().getGeodeticAngle(this.latitude, this.altitude);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double zenithAngle() {
        return getCalculator().getZenithAngle(this.latitude, this.altitude);
    }

    private static void check(double d, double d2, int i, String str) {
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
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Missing calculator.");
        }
        if (!CALCULATORS.containsKey(str)) {
            throw new IllegalArgumentException("Unknown calculator: " + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CalendarDate toLMT(CalendarDate calendarDate) {
        if (this.observerZoneID == null || Math.abs(this.longitude) < 150.0d) {
            return calendarDate;
        }
        PlainTimestamp plainTimestampAt = toGregorian(calendarDate).at(PlainTime.of(12));
        if (!plainTimestampAt.isValid(this.observerZoneID)) {
            throw new ChronoException("Calendar date does not exist in zone: " + calendarDate + " (" + this.observerZoneID.canonical() + ")");
        }
        return plainTimestampAt.inTimezone(this.observerZoneID).toZonalTimestamp(ZonalOffset.atLongitude(new BigDecimal(this.longitude))).getCalendarDate();
    }

    private static boolean equalZones(TZID tzid, TZID tzid2) {
        if (tzid == null) {
            return tzid2 == null;
        }
        if (tzid2 == null) {
            return false;
        }
        return tzid.canonical().equals(tzid2.canonical());
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        check(this.latitude, this.longitude, this.altitude, this.calculator);
    }

    public static class Builder {
        private int altitude;
        private String calculator;
        private double latitude;
        private double longitude;
        private TZID observerZoneID;

        private Builder() {
            this.latitude = Double.NaN;
            this.longitude = Double.NaN;
            this.altitude = 0;
            this.calculator = SolarTime.DEFAULT_CALCULATOR.name();
            this.observerZoneID = null;
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

        public Builder usingCalculator(String str) {
            if (str.isEmpty()) {
                throw new IllegalArgumentException("Missing calculator.");
            }
            if (!SolarTime.CALCULATORS.containsKey(str)) {
                throw new IllegalArgumentException("Unknown calculator: " + str);
            }
            this.calculator = str;
            return this;
        }

        public Builder usingCalculator(Calculator calculator) {
            SolarTime.CALCULATORS.putIfAbsent(calculator.name(), calculator);
            this.calculator = calculator.name();
            return this;
        }

        public Builder inTimezone(TZID tzid) {
            if (tzid == null) {
                throw new NullPointerException("Missing timezone identifier.");
            }
            this.observerZoneID = tzid;
            return this;
        }

        public SolarTime build() {
            if (Double.isNaN(this.latitude)) {
                throw new IllegalStateException("Latitude was not yet set.");
            }
            if (Double.isNaN(this.longitude)) {
                throw new IllegalStateException("Longitude was not yet set.");
            }
            return new SolarTime(this.latitude, this.longitude, this.altitude, this.calculator, this.observerZoneID);
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

    public static class Sunshine {
        private final PlainTimestamp endLocal;
        private final Moment endUTC;
        private final PlainTimestamp startLocal;
        private final Moment startUTC;

        public boolean isAbsent() {
            return this.startUTC == null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Sunshine(PlainDate plainDate, Moment moment, Moment moment2, TZID tzid, boolean z) {
            Timezone timezoneOf = Timezone.of(tzid);
            boolean z2 = timezoneOf.getHistory() != null;
            if (z) {
                this.startUTC = null;
                this.endUTC = null;
                this.startLocal = null;
                this.endLocal = null;
                return;
            }
            if (moment != null) {
                this.startUTC = moment;
                this.startLocal = moment.toZonalTimestamp(tzid);
                if (moment2 != null) {
                    this.endUTC = moment2;
                    this.endLocal = moment2.toZonalTimestamp(tzid);
                    return;
                } else if (z2) {
                    PlainDate plainDate2 = (PlainDate) plainDate.plus(1L, CalendarUnit.DAYS);
                    this.endUTC = plainDate2.atFirstMoment(tzid);
                    this.endLocal = plainDate2.atStartOfDay(tzid);
                    return;
                } else {
                    Moment momentIn = ((PlainDate) plainDate.plus(1L, CalendarUnit.DAYS)).atStartOfDay().in(timezoneOf);
                    this.endUTC = momentIn;
                    this.endLocal = momentIn.toZonalTimestamp(tzid);
                    return;
                }
            }
            if (moment2 != null) {
                if (z2) {
                    this.startUTC = plainDate.atFirstMoment(tzid);
                    this.startLocal = plainDate.atStartOfDay(tzid);
                    this.endUTC = moment2;
                    this.endLocal = moment2.toZonalTimestamp(tzid);
                    return;
                }
                Moment momentIn2 = plainDate.atStartOfDay().in(timezoneOf);
                this.startUTC = momentIn2;
                this.startLocal = momentIn2.toZonalTimestamp(tzid);
                this.endUTC = moment2;
                this.endLocal = moment2.toZonalTimestamp(tzid);
                return;
            }
            if (z2) {
                this.startUTC = plainDate.atFirstMoment(tzid);
                this.startLocal = plainDate.atStartOfDay(tzid);
                PlainDate plainDate3 = (PlainDate) plainDate.plus(1L, CalendarUnit.DAYS);
                this.endUTC = plainDate3.atFirstMoment(tzid);
                this.endLocal = plainDate3.atStartOfDay(tzid);
                return;
            }
            Moment momentIn3 = plainDate.atStartOfDay().in(timezoneOf);
            this.startUTC = momentIn3;
            this.startLocal = momentIn3.toZonalTimestamp(tzid);
            Moment momentIn4 = ((PlainDate) plainDate.plus(1L, CalendarUnit.DAYS)).atStartOfDay().in(timezoneOf);
            this.endUTC = momentIn4;
            this.endLocal = momentIn4.toZonalTimestamp(tzid);
        }

        public Moment startUTC() {
            return (Moment) checkAndGet(this.startUTC);
        }

        public Moment endUTC() {
            return (Moment) checkAndGet(this.endUTC);
        }

        public PlainTimestamp startLocal() {
            return (PlainTimestamp) checkAndGet(this.startLocal);
        }

        public PlainTimestamp endLocal() {
            return (PlainTimestamp) checkAndGet(this.endLocal);
        }

        public boolean isPresent(Moment moment) {
            return (isAbsent() || this.startUTC.isAfter((UniversalTime) moment) || !moment.isBefore((UniversalTime) this.endUTC)) ? false : true;
        }

        public boolean isPresent(PlainTimestamp plainTimestamp) {
            return (isAbsent() || this.startLocal.isAfter(plainTimestamp) || !plainTimestamp.isBefore(this.endLocal)) ? false : true;
        }

        public int length() {
            if (isAbsent()) {
                return 0;
            }
            return (int) this.startUTC.until(this.endUTC, (Moment) TimeUnit.SECONDS);
        }

        public String toString() {
            if (isAbsent()) {
                return "Polar night";
            }
            StringBuilder sb = new StringBuilder(128);
            sb.append("Sunshine[utc=");
            sb.append(this.startUTC);
            sb.append('/');
            sb.append(this.endUTC);
            sb.append(",local=");
            sb.append(this.startLocal);
            sb.append('/');
            sb.append(this.endLocal);
            sb.append(",length=");
            sb.append(length());
            sb.append(AbstractJsonLexerKt.END_LIST);
            return sb.toString();
        }

        private static <T> T checkAndGet(T t) {
            if (t != null) {
                return t;
            }
            throw new IllegalStateException("Sunshine is absent (polar night).");
        }
    }
}
