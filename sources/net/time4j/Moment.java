package net.time4j;

import androidx.media3.common.C;
import com.drew.metadata.exif.makernotes.NikonType2MakernoteDirectory;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.base.GregorianMath;
import net.time4j.base.MathUtils;
import net.time4j.base.TimeSource;
import net.time4j.base.UnixTime;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.BridgeChronology;
import net.time4j.engine.CalendarFamily;
import net.time4j.engine.CalendarVariant;
import net.time4j.engine.Calendrical;
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoException;
import net.time4j.engine.ChronoMerger;
import net.time4j.engine.ChronoOperator;
import net.time4j.engine.Chronology;
import net.time4j.engine.Converter;
import net.time4j.engine.DisplayStyle;
import net.time4j.engine.ElementRule;
import net.time4j.engine.EpochDays;
import net.time4j.engine.FlagElement;
import net.time4j.engine.StartOfDay;
import net.time4j.engine.Temporal;
import net.time4j.engine.TimeAxis;
import net.time4j.engine.TimeLine;
import net.time4j.engine.TimePoint;
import net.time4j.engine.UnitRule;
import net.time4j.format.Attributes;
import net.time4j.format.CalendarText;
import net.time4j.format.CalendarType;
import net.time4j.format.DisplayMode;
import net.time4j.format.TemporalFormatter;
import net.time4j.scale.LeapSecondEvent;
import net.time4j.scale.LeapSeconds;
import net.time4j.scale.TimeScale;
import net.time4j.scale.UniversalTime;
import net.time4j.tz.OverlapResolver;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;
import net.time4j.tz.TransitionStrategy;
import net.time4j.tz.ZonalOffset;

@CalendarType(CalendarText.ISO_CALENDAR_TYPE)
/* loaded from: classes4.dex */
public final class Moment extends TimePoint<TimeUnit, Moment> implements UniversalTime, Temporal<UniversalTime> {
    private static final TimeAxis<TimeUnit, Moment> ENGINE;
    public static final ChronoElement<Integer> FRACTION;
    private static final Set<ChronoElement<?>> HIGH_TIME_ELEMENTS;
    private static final Map<ChronoElement<?>, Integer> LOW_TIME_ELEMENTS;
    private static final Moment MAX;
    private static final long MAX_LIMIT;
    private static final Moment MIN;
    private static final long MIN_LIMIT;
    private static final int MIO = 1000000;
    private static final int MRD = 1000000000;
    private static final ChronoOperator<Moment> NEXT_LS;
    private static final int POSITIVE_LEAP_MASK = 1073741824;
    private static final long POSIX_GPS_DELTA = 315964800;
    public static final ChronoElement<Long> POSIX_TIME;
    private static final long POSIX_UTC_DELTA = 63072000;
    public static final ChronoElement<TimeUnit> PRECISION;
    private static final Moment START_LS_CHECK;
    private static final Map<TimeUnit, Double> UNIT_LENGTHS;
    public static final Moment UNIX_EPOCH;
    private static final long UTC_GPS_DELTA = 252892809;
    private static final long UTC_TAI_DELTA = 441763200;
    private static final long serialVersionUID = -3192884724477742274L;
    private final transient int fraction;
    private final transient long posixTime;

    public static TimeAxis<TimeUnit, Moment> axis() {
        return ENGINE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPositiveLS() {
        return (this.fraction >>> 30) != 0;
    }

    public static ChronoOperator<Moment> nextLeapSecond() {
        return NEXT_LS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.time4j.engine.TimePoint, net.time4j.engine.ChronoEntity
    public TimeAxis<TimeUnit, Moment> getChronology() {
        return ENGINE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.time4j.engine.ChronoEntity
    public Moment getContext() {
        return this;
    }

    @Override // net.time4j.base.UnixTime
    public int getNanosecond() {
        return this.fraction & (-1073741825);
    }

    @Override // net.time4j.base.UnixTime
    public long getPosixTime() {
        return this.posixTime;
    }

    /* synthetic */ Moment(int i, long j, AnonymousClass1 anonymousClass1) {
        this(i, j);
    }

    static {
        long mjd = GregorianMath.toMJD(-999999999, 1, 1);
        long mjd2 = GregorianMath.toMJD(999999999, 12, 31);
        long jTransform = EpochDays.UNIX.transform(mjd, EpochDays.MODIFIED_JULIAN_DATE) * 86400;
        MIN_LIMIT = jTransform;
        long jTransform2 = (EpochDays.UNIX.transform(mjd2, EpochDays.MODIFIED_JULIAN_DATE) * 86400) + 86399;
        MAX_LIMIT = jTransform2;
        Moment moment = new Moment(jTransform, 0, TimeScale.POSIX);
        MIN = moment;
        Moment moment2 = new Moment(jTransform2, 999999999, TimeScale.POSIX);
        MAX = moment2;
        START_LS_CHECK = new Moment(63158400L, 0, TimeScale.POSIX);
        HashSet hashSet = new HashSet();
        hashSet.add(PlainTime.HOUR_FROM_0_TO_24);
        hashSet.add(PlainTime.DIGITAL_HOUR_OF_DAY);
        hashSet.add(PlainTime.DIGITAL_HOUR_OF_AMPM);
        hashSet.add(PlainTime.CLOCK_HOUR_OF_DAY);
        hashSet.add(PlainTime.CLOCK_HOUR_OF_AMPM);
        hashSet.add(PlainTime.AM_PM_OF_DAY);
        hashSet.add(PlainTime.MINUTE_OF_HOUR);
        hashSet.add(PlainTime.MINUTE_OF_DAY);
        HIGH_TIME_ELEMENTS = Collections.unmodifiableSet(hashSet);
        HashMap map = new HashMap();
        map.put(PlainTime.SECOND_OF_MINUTE, 1);
        map.put(PlainTime.SECOND_OF_DAY, 1);
        map.put(PlainTime.MILLI_OF_SECOND, 1000);
        map.put(PlainTime.MILLI_OF_DAY, 1000);
        map.put(PlainTime.MICRO_OF_SECOND, 1000000);
        map.put(PlainTime.MICRO_OF_DAY, 1000000);
        map.put(PlainTime.NANO_OF_SECOND, 1000000000);
        map.put(PlainTime.NANO_OF_DAY, 1000000000);
        LOW_TIME_ELEMENTS = Collections.unmodifiableMap(map);
        EnumMap enumMap = new EnumMap(TimeUnit.class);
        enumMap.put((EnumMap) TimeUnit.DAYS, (TimeUnit) Double.valueOf(86400.0d));
        enumMap.put((EnumMap) TimeUnit.HOURS, (TimeUnit) Double.valueOf(3600.0d));
        enumMap.put((EnumMap) TimeUnit.MINUTES, (TimeUnit) Double.valueOf(60.0d));
        enumMap.put((EnumMap) TimeUnit.SECONDS, (TimeUnit) Double.valueOf(1.0d));
        enumMap.put((EnumMap) TimeUnit.MILLISECONDS, (TimeUnit) Double.valueOf(0.001d));
        enumMap.put((EnumMap) TimeUnit.MICROSECONDS, (TimeUnit) Double.valueOf(1.0E-6d));
        enumMap.put((EnumMap) TimeUnit.NANOSECONDS, (TimeUnit) Double.valueOf(1.0E-9d));
        UNIT_LENGTHS = Collections.unmodifiableMap(enumMap);
        AnonymousClass1 anonymousClass1 = null;
        TimeAxis.Builder up = TimeAxis.Builder.setUp(TimeUnit.class, Moment.class, new Merger(anonymousClass1), moment, moment2);
        for (TimeUnit timeUnit : TimeUnit.values()) {
            TimeUnitRule timeUnitRule = new TimeUnitRule(timeUnit);
            Map<TimeUnit, Double> map2 = UNIT_LENGTHS;
            up.appendUnit(timeUnit, timeUnitRule, map2.get(timeUnit).doubleValue(), map2.keySet());
        }
        up.appendElement(LongElement.POSIX_TIME, LongElement.POSIX_TIME, TimeUnit.SECONDS);
        up.appendElement(IntElement.FRACTION, IntElement.FRACTION, TimeUnit.NANOSECONDS);
        up.appendElement((ChronoElement) PrecisionElement.TIME_PRECISION, (ElementRule) new PrecisionRule(anonymousClass1));
        ENGINE = up.withTimeLine(new GlobalTimeLine(anonymousClass1)).build();
        UNIX_EPOCH = new Moment(0L, 0, TimeScale.POSIX);
        POSIX_TIME = LongElement.POSIX_TIME;
        FRACTION = IntElement.FRACTION;
        PRECISION = PrecisionElement.TIME_PRECISION;
        NEXT_LS = new NextLS(anonymousClass1);
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x011d A[PHI: r1 r3
      0x011d: PHI (r1v2 long) = (r1v0 long), (r1v0 long), (r1v10 long), (r1v11 long) binds: [B:8:0x0025, B:44:0x011b, B:38:0x00e0, B:39:0x00e2] A[DONT_GENERATE, DONT_INLINE]
      0x011d: PHI (r3v1 int) = (r3v0 int), (r3v0 int), (r3v24 int), (r3v25 int) binds: [B:8:0x0025, B:44:0x011b, B:38:0x00e0, B:39:0x00e2] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private Moment(long r18, int r20, net.time4j.scale.TimeScale r21) {
        /*
            Method dump skipped, instructions count: 431
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: net.time4j.Moment.<init>(long, int, net.time4j.scale.TimeScale):void");
    }

    private Moment(int i, long j) {
        checkUnixTime(j);
        this.posixTime = j;
        this.fraction = i;
    }

    public static Moment of(long j, TimeScale timeScale) {
        return of(j, 0, timeScale);
    }

    public static Moment of(long j, int i, TimeScale timeScale) {
        return (j == 0 && i == 0 && timeScale == TimeScale.POSIX) ? UNIX_EPOCH : new Moment(j, i, timeScale);
    }

    public static Moment nowInSystemTime() {
        return SystemClock.INSTANCE.currentTime();
    }

    public static Moment from(UnixTime unixTime) {
        if (unixTime instanceof Moment) {
            return (Moment) Moment.class.cast(unixTime);
        }
        if ((unixTime instanceof UniversalTime) && LeapSeconds.getInstance().isEnabled()) {
            UniversalTime universalTime = (UniversalTime) UniversalTime.class.cast(unixTime);
            return of(universalTime.getElapsedTime(TimeScale.UTC), universalTime.getNanosecond(TimeScale.UTC), TimeScale.UTC);
        }
        return of(unixTime.getPosixTime(), unixTime.getNanosecond(), TimeScale.POSIX);
    }

    @Override // net.time4j.scale.UniversalTime
    public long getElapsedTime(TimeScale timeScale) {
        long elapsedTimeUTC;
        int nanos;
        switch (AnonymousClass1.$SwitchMap$net$time4j$scale$TimeScale[timeScale.ordinal()]) {
            case 1:
                return this.posixTime;
            case 2:
                return getElapsedTimeUTC();
            case 3:
                if (getElapsedTimeUTC() < 0) {
                    double dDeltaT = TimeScale.deltaT(getDateUTC()) + (this.posixTime - POSIX_UTC_DELTA) + (getNanosecond() / 1.0E9d);
                    long jFloor = (long) Math.floor(dDeltaT);
                    if (Double.compare(1.0E9d - ((dDeltaT - jFloor) * 1.0E9d), 1.0d) < 0) {
                        jFloor++;
                        nanos = 0;
                    } else {
                        nanos = toNanos(dDeltaT, jFloor);
                    }
                    elapsedTimeUTC = jFloor - (-441763168);
                    if (nanos - 184000000 < 0) {
                        elapsedTimeUTC = jFloor - (-441763167);
                    }
                } else {
                    elapsedTimeUTC = getElapsedTimeUTC() + 441763210;
                }
                if (elapsedTimeUTC >= 0) {
                    return elapsedTimeUTC;
                }
                throw new IllegalArgumentException("TAI not supported before 1958-01-01: " + this);
            case 4:
                long elapsedTimeUTC2 = getElapsedTimeUTC();
                if (LeapSeconds.getInstance().strip(elapsedTimeUTC2) < POSIX_GPS_DELTA) {
                    throw new IllegalArgumentException("GPS not supported before 1980-01-06: " + this);
                }
                if (!LeapSeconds.getInstance().isEnabled()) {
                    elapsedTimeUTC2 += 9;
                }
                return elapsedTimeUTC2 - UTC_GPS_DELTA;
            case 5:
                if (this.posixTime < POSIX_UTC_DELTA) {
                    double dDeltaT2 = TimeScale.deltaT(getDateUTC()) + (this.posixTime - POSIX_UTC_DELTA) + (getNanosecond() / 1.0E9d);
                    long jFloor2 = (long) Math.floor(dDeltaT2);
                    return Double.compare(1.0E9d - ((dDeltaT2 - ((double) jFloor2)) * 1.0E9d), 1.0d) < 0 ? jFloor2 + 1 : jFloor2;
                }
                long elapsedTimeUTC3 = getElapsedTimeUTC();
                return getNanosecond() + 184000000 >= 1000000000 ? elapsedTimeUTC3 + 43 : 42 + elapsedTimeUTC3;
            case 6:
                long j = this.posixTime;
                return j < POSIX_UTC_DELTA ? j - POSIX_UTC_DELTA : (long) Math.floor(getModernUT());
            default:
                throw new UnsupportedOperationException("Not yet implemented: " + timeScale);
        }
    }

    @Override // net.time4j.scale.UniversalTime
    public int getNanosecond(TimeScale timeScale) {
        long elapsedTimeUTC;
        int nanosecond;
        int nanos = 0;
        switch (AnonymousClass1.$SwitchMap$net$time4j$scale$TimeScale[timeScale.ordinal()]) {
            case 1:
            case 2:
                return getNanosecond();
            case 3:
                if (getElapsedTimeUTC() < 0) {
                    double dDeltaT = TimeScale.deltaT(getDateUTC()) + (this.posixTime - POSIX_UTC_DELTA) + (getNanosecond() / 1.0E9d);
                    long jFloor = (long) Math.floor(dDeltaT);
                    if (Double.compare(1.0E9d - ((dDeltaT - jFloor) * 1.0E9d), 1.0d) < 0) {
                        jFloor++;
                    } else {
                        nanos = toNanos(dDeltaT, jFloor);
                    }
                    elapsedTimeUTC = jFloor - (-441763168);
                    nanosecond = nanos - 184000000;
                    if (nanosecond < 0) {
                        elapsedTimeUTC = jFloor - (-441763167);
                        nanosecond = nanos - (-816000000);
                    }
                } else {
                    elapsedTimeUTC = getElapsedTimeUTC() + UTC_TAI_DELTA;
                    nanosecond = getNanosecond();
                }
                if (elapsedTimeUTC >= 0) {
                    return nanosecond;
                }
                throw new IllegalArgumentException("TAI not supported before 1958-01-01: " + this);
            case 4:
                if (LeapSeconds.getInstance().strip(getElapsedTimeUTC()) < POSIX_GPS_DELTA) {
                    throw new IllegalArgumentException("GPS not supported before 1980-01-06: " + this);
                }
                return getNanosecond();
            case 5:
                if (this.posixTime < POSIX_UTC_DELTA) {
                    double dDeltaT2 = TimeScale.deltaT(getDateUTC()) + (this.posixTime - POSIX_UTC_DELTA) + (getNanosecond() / 1.0E9d);
                    long jFloor2 = (long) Math.floor(dDeltaT2);
                    if (Double.compare(1.0E9d - ((dDeltaT2 - jFloor2) * 1.0E9d), 1.0d) < 0) {
                        return 0;
                    }
                    return toNanos(dDeltaT2, jFloor2);
                }
                int nanosecond2 = getNanosecond();
                int i = nanosecond2 + 184000000;
                return i >= 1000000000 ? nanosecond2 - 816000000 : i;
            case 6:
                if (this.posixTime < POSIX_UTC_DELTA) {
                    return getNanosecond();
                }
                double modernUT = getModernUT();
                return toNanos(modernUT, (long) Math.floor(modernUT));
            default:
                throw new UnsupportedOperationException("Not yet implemented: " + timeScale);
        }
    }

    @Override // net.time4j.scale.UniversalTime
    public boolean isLeapSecond() {
        return isPositiveLS() && LeapSeconds.getInstance().isEnabled();
    }

    public BigDecimal transform(TimeScale timeScale) {
        return new BigDecimal(getElapsedTime(timeScale)).setScale(9, RoundingMode.UNNECESSARY).add(new BigDecimal(getNanosecond(timeScale)).movePointLeft(9));
    }

    @Override // net.time4j.engine.Temporal
    public boolean isAfter(UniversalTime universalTime) {
        return compareTo(from(universalTime)) > 0;
    }

    @Override // net.time4j.engine.Temporal
    public boolean isBefore(UniversalTime universalTime) {
        return compareTo(from(universalTime)) < 0;
    }

    @Override // net.time4j.engine.Temporal
    public boolean isSimultaneous(UniversalTime universalTime) {
        return compareTo(from(universalTime)) == 0;
    }

    public PlainTimestamp toLocalTimestamp() {
        return in(Timezone.ofSystem());
    }

    public PlainTimestamp toZonalTimestamp(TZID tzid) {
        return in(Timezone.of(tzid));
    }

    public PlainTimestamp toZonalTimestamp(String str) {
        return in(Timezone.of(str));
    }

    public <C extends Calendrical<?, C>> GeneralTimestamp<C> toGeneralTimestamp(Chronology<C> chronology, TZID tzid, StartOfDay startOfDay) {
        PlainTimestamp zonalTimestamp = toZonalTimestamp(tzid);
        return GeneralTimestamp.of(zonalTimestamp.minus(startOfDay.getDeviation(zonalTimestamp.getCalendarDate(), tzid), ClockUnit.SECONDS).getCalendarDate().transform(chronology.getChronoType()), zonalTimestamp.getWallTime());
    }

    public <C extends CalendarVariant<C>> GeneralTimestamp<C> toGeneralTimestamp(CalendarFamily<C> calendarFamily, String str, TZID tzid, StartOfDay startOfDay) {
        PlainTimestamp zonalTimestamp = toZonalTimestamp(tzid);
        return GeneralTimestamp.of(zonalTimestamp.minus(startOfDay.getDeviation(zonalTimestamp.getCalendarDate(), tzid), ClockUnit.SECONDS).getCalendarDate().transform(calendarFamily.getChronoType(), str), zonalTimestamp.getWallTime());
    }

    public ZonalDateTime inLocalView() {
        return ZonalDateTime.of(this, Timezone.ofSystem());
    }

    public ZonalDateTime inZonalView(TZID tzid) {
        return ZonalDateTime.of(this, Timezone.of(tzid));
    }

    public ZonalDateTime inZonalView(String str) {
        return ZonalDateTime.of(this, Timezone.of(str));
    }

    public Moment plus(long j, SI si) {
        Moment momentOf;
        check1972(this);
        if (j == 0) {
            return this;
        }
        try {
            int i = AnonymousClass1.$SwitchMap$net$time4j$SI[si.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    long jSafeAdd = MathUtils.safeAdd(getNanosecond(), j);
                    int iFloorModulo = MathUtils.floorModulo(jSafeAdd, 1000000000);
                    long jFloorDivide = MathUtils.floorDivide(jSafeAdd, 1000000000);
                    if (LeapSeconds.getInstance().isEnabled()) {
                        momentOf = new Moment(MathUtils.safeAdd(getElapsedTimeUTC(), jFloorDivide), iFloorModulo, TimeScale.UTC);
                    } else {
                        momentOf = of(MathUtils.safeAdd(this.posixTime, jFloorDivide), iFloorModulo, TimeScale.POSIX);
                    }
                } else {
                    throw new UnsupportedOperationException();
                }
            } else if (LeapSeconds.getInstance().isEnabled()) {
                momentOf = new Moment(MathUtils.safeAdd(getElapsedTimeUTC(), j), getNanosecond(), TimeScale.UTC);
            } else {
                momentOf = of(MathUtils.safeAdd(this.posixTime, j), getNanosecond(), TimeScale.POSIX);
            }
            if (j < 0) {
                check1972(momentOf);
            }
            return momentOf;
        } catch (IllegalArgumentException e) {
            ArithmeticException arithmeticException = new ArithmeticException("Result beyond boundaries of time axis.");
            arithmeticException.initCause(e);
            throw arithmeticException;
        }
    }

    public Moment plus(MachineTime<SI> machineTime) {
        return plus(machineTime.getSeconds(), SI.SECONDS).plus(machineTime.getFraction(), SI.NANOSECONDS);
    }

    public Moment minus(long j, SI si) {
        return plus(MathUtils.safeNegate(j), si);
    }

    public Moment minus(MachineTime<SI> machineTime) {
        return minus(machineTime.getSeconds(), SI.SECONDS).minus(machineTime.getFraction(), SI.NANOSECONDS);
    }

    public long until(Moment moment, SI si) {
        return si.between(this, moment);
    }

    public String print(TemporalFormatter<Moment> temporalFormatter) {
        return temporalFormatter.print(this);
    }

    public static Moment parse(String str, TemporalFormatter<Moment> temporalFormatter) {
        try {
            return temporalFormatter.parse(str);
        } catch (ParseException e) {
            throw new ChronoException(e.getMessage(), e);
        }
    }

    @Override // net.time4j.engine.TimePoint
    public int compareTo(Moment moment) {
        int nanosecond;
        long elapsedTimeUTC = getElapsedTimeUTC();
        long elapsedTimeUTC2 = moment.getElapsedTimeUTC();
        if (elapsedTimeUTC < elapsedTimeUTC2) {
            return -1;
        }
        if (elapsedTimeUTC <= elapsedTimeUTC2 && (nanosecond = getNanosecond() - moment.getNanosecond()) <= 0) {
            return nanosecond < 0 ? -1 : 0;
        }
        return 1;
    }

    @Override // net.time4j.engine.TimePoint
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Moment)) {
            return false;
        }
        Moment moment = (Moment) obj;
        if (this.posixTime != moment.posixTime) {
            return false;
        }
        return LeapSeconds.getInstance().isEnabled() ? this.fraction == moment.fraction : getNanosecond() == moment.getNanosecond();
    }

    @Override // net.time4j.engine.TimePoint
    public int hashCode() {
        long j = this.posixTime;
        return (((int) (j ^ (j >>> 32))) * 19) + (getNanosecond() * 37);
    }

    @Override // net.time4j.engine.TimePoint
    public String toString() {
        return toStringUTC(true);
    }

    public String toString(TimeScale timeScale) {
        StringBuilder sb = new StringBuilder(50);
        sb.append(timeScale.name());
        sb.append('-');
        switch (AnonymousClass1.$SwitchMap$net$time4j$scale$TimeScale[timeScale.ordinal()]) {
            case 1:
                sb.append(PlainTimestamp.from(this, ZonalOffset.UTC));
                sb.append('Z');
                break;
            case 2:
                sb.append(toStringUTC(false));
                break;
            case 3:
            case 4:
            case 5:
            case 6:
                sb.append(PlainTimestamp.from(transformForPrint(timeScale), ZonalOffset.UTC));
                sb.append('Z');
                break;
            default:
                throw new UnsupportedOperationException(timeScale.name());
        }
        return sb.toString();
    }

    public static <S> Chronology<S> axis(Converter<S, Moment> converter) {
        return new BridgeChronology(converter, ENGINE);
    }

    static void checkNegativeLS(long j, PlainTimestamp plainTimestamp) {
        LeapSeconds leapSeconds = LeapSeconds.getInstance();
        if (leapSeconds.supportsNegativeLS() && leapSeconds.strip(leapSeconds.enhance(j)) > j) {
            throw new ChronoException("Illegal local timestamp due to negative leap second: " + plainTimestamp);
        }
    }

    static void check1972(Moment moment) {
        if (moment.posixTime < POSIX_UTC_DELTA) {
            throw new UnsupportedOperationException("Cannot calculate SI-duration before 1972-01-01.");
        }
    }

    private String toStringUTC(boolean z) {
        PlainDate dateUTC = getDateUTC();
        int timeOfDay = getTimeOfDay(this);
        int i = timeOfDay / 60;
        int i2 = i / 60;
        int i3 = i % 60;
        int shift = (timeOfDay % 60) + LeapSeconds.getInstance().getShift(getElapsedTimeUTC());
        int nanosecond = getNanosecond();
        StringBuilder sb = new StringBuilder(50);
        sb.append(dateUTC);
        sb.append('T');
        format(i2, 2, sb);
        if (z || (i3 | shift | nanosecond) != 0) {
            sb.append(AbstractJsonLexerKt.COLON);
            format(i3, 2, sb);
            if (z || (shift | nanosecond) != 0) {
                sb.append(AbstractJsonLexerKt.COLON);
                format(shift, 2, sb);
                if (nanosecond > 0) {
                    sb.append(AbstractJsonLexerKt.COMMA);
                    format(nanosecond, 9, sb);
                }
            }
        }
        sb.append('Z');
        return sb.toString();
    }

    private long getElapsedTimeUTC() {
        if (!LeapSeconds.getInstance().isEnabled()) {
            return this.posixTime - POSIX_UTC_DELTA;
        }
        long jEnhance = LeapSeconds.getInstance().enhance(this.posixTime);
        return isPositiveLS() ? jEnhance + 1 : jEnhance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PlainDate getDateUTC() {
        return PlainDate.of(MathUtils.floorDivide(this.posixTime, 86400), EpochDays.UNIX);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PlainTime getTimeUTC() {
        int timeOfDay = getTimeOfDay(this);
        int i = timeOfDay / 60;
        return PlainTime.of(i / 60, i % 60, timeOfDay % 60, getNanosecond());
    }

    private double getModernUT() {
        double elapsedTimeUTC = ((getElapsedTimeUTC() + 42.184d) + (getNanosecond() / 1.0E9d)) - TimeScale.deltaT(getDateUTC());
        return Double.compare(1.0E9d - ((elapsedTimeUTC - ((double) ((long) Math.floor(elapsedTimeUTC)))) * 1.0E9d), 1.0d) < 0 ? r3 + 1 : elapsedTimeUTC;
    }

    private static int toNanos(double d, long j) {
        try {
            return (int) ((d * 1.0E9d) - MathUtils.safeMultiply(j, C.NANOS_PER_SECOND));
        } catch (ArithmeticException unused) {
            return (int) ((d - j) * 1.0E9d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isNegativeLS() {
        LeapSeconds leapSeconds = LeapSeconds.getInstance();
        if (!leapSeconds.supportsNegativeLS()) {
            return false;
        }
        long j = this.posixTime;
        return leapSeconds.strip(leapSeconds.enhance(j)) > j;
    }

    private static void checkUnixTime(long j) {
        if (j > MAX_LIMIT || j < MIN_LIMIT) {
            throw new IllegalArgumentException("UNIX time (UT) out of supported range: " + j);
        }
    }

    private static void checkFraction(int i) {
        if (i >= 1000000000 || i < 0) {
            throw new IllegalArgumentException("Nanosecond out of range: " + i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Moment transformForPrint(TimeScale timeScale) {
        switch (AnonymousClass1.$SwitchMap$net$time4j$scale$TimeScale[timeScale.ordinal()]) {
            case 1:
                return isLeapSecond() ? new Moment(getNanosecond(), this.posixTime) : this;
            case 2:
                return this;
            case 3:
                return new Moment(getNanosecond(timeScale), MathUtils.safeAdd(getElapsedTime(timeScale), -378691200L));
            case 4:
                return new Moment(getNanosecond(), MathUtils.safeAdd(getElapsedTime(TimeScale.GPS), POSIX_GPS_DELTA));
            case 5:
            case 6:
                return new Moment(getNanosecond(timeScale), MathUtils.safeAdd(getElapsedTime(timeScale), POSIX_UTC_DELTA));
            default:
                throw new UnsupportedOperationException(timeScale.name());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Moment transformForParse(TimeScale timeScale) {
        if (timeScale == TimeScale.UTC) {
            return this;
        }
        if (isLeapSecond()) {
            throw new IllegalArgumentException("Leap seconds do not exist on continuous time scale: " + timeScale);
        }
        int i = AnonymousClass1.$SwitchMap$net$time4j$scale$TimeScale[timeScale.ordinal()];
        if (i == 1) {
            return this;
        }
        if (i == 3) {
            return new Moment(MathUtils.safeSubtract(this.posixTime, -378691200L), getNanosecond(), timeScale);
        }
        if (i == 4) {
            return new Moment(MathUtils.safeSubtract(this.posixTime, POSIX_GPS_DELTA), getNanosecond(), timeScale);
        }
        if (i == 5 || i == 6) {
            return new Moment(MathUtils.safeSubtract(this.posixTime, POSIX_UTC_DELTA), getNanosecond(), timeScale);
        }
        throw new UnsupportedOperationException(timeScale.name());
    }

    private static void format(int i, int i2, StringBuilder sb) {
        int i3 = 1;
        for (int i4 = 0; i4 < i2 - 1; i4++) {
            i3 *= 10;
        }
        while (i < i3 && i3 >= 10) {
            sb.append('0');
            i3 /= 10;
        }
        sb.append(String.valueOf(i));
    }

    private static int getTimeOfDay(Moment moment) {
        return MathUtils.floorModulo(moment.posixTime, 86400);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Moment moveEventuallyToLS(Moment moment) {
        PlainDate dateUTC = moment.getDateUTC();
        PlainTime timeUTC = moment.getTimeUTC();
        return (LeapSeconds.getInstance().getShift(dateUTC) == 1 && timeUTC.getHour() == 23 && timeUTC.getMinute() == 59 && timeUTC.getSecond() == 59) ? moment.plus(1L, SI.SECONDS) : moment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PlainTimestamp in(Timezone timezone) {
        return PlainTimestamp.from(this, timezone.getOffset(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getMaxSecondOfMinute(Moment moment) {
        int timeOfDay = getTimeOfDay(moment) / 60;
        if (timeOfDay / 60 != 23 || timeOfDay % 60 != 59) {
            return 59;
        }
        return 59 + LeapSeconds.getInstance().getShift(moment.getDateUTC());
    }

    private Object writeReplace() {
        return new SPX(this, 4);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        throw new InvalidObjectException("Serialization proxy required.");
    }

    void writeTimestamp(DataOutput dataOutput) throws IOException {
        int i = isPositiveLS() ? 65 : 64;
        int nanosecond = getNanosecond();
        if (nanosecond > 0) {
            i |= 2;
        }
        dataOutput.writeByte(i);
        dataOutput.writeLong(this.posixTime);
        if (nanosecond > 0) {
            dataOutput.writeInt(nanosecond);
        }
    }

    static Moment readTimestamp(DataInput dataInput, boolean z, boolean z2) throws IOException {
        long j = dataInput.readLong();
        int i = z2 ? dataInput.readInt() : 0;
        if (j == 0) {
            if (z) {
                throw new InvalidObjectException("UTC epoch is no leap second.");
            }
            if (i == 0) {
                return UNIX_EPOCH;
            }
        }
        if (j == MIN_LIMIT && i == 0) {
            if (z) {
                throw new InvalidObjectException("Minimum is no leap second.");
            }
            return MIN;
        }
        if (j == MAX_LIMIT && i == 999999999) {
            if (z) {
                throw new InvalidObjectException("Maximum is no leap second.");
            }
            return MAX;
        }
        checkFraction(i);
        if (z) {
            LeapSeconds leapSeconds = LeapSeconds.getInstance();
            if (leapSeconds.isEnabled() && !leapSeconds.isPositiveLS(leapSeconds.enhance(j) + 1)) {
                long packedDate = GregorianMath.toPackedDate(j);
                int month = GregorianMath.readMonth(packedDate);
                int dayOfMonth = GregorianMath.readDayOfMonth(packedDate);
                throw new InvalidObjectException("Not registered as leap second event: " + GregorianMath.readYear(packedDate) + "-" + (month < 10 ? "0" : "") + month + (dayOfMonth >= 10 ? "" : "0") + dayOfMonth + " [Please check leap second configurations either of emitter vm or this target vm]");
            }
            i |= 1073741824;
        }
        return new Moment(i, j);
    }

    static final class Operator implements ChronoOperator<Moment> {
        private final ChronoOperator<PlainTimestamp> delegate;
        private final ChronoElement<?> element;
        private final int type;
        private final Timezone tz;

        Operator(ChronoOperator<PlainTimestamp> chronoOperator, ChronoElement<?> chronoElement, int i) {
            this.delegate = chronoOperator;
            this.element = chronoElement;
            this.type = i;
            this.tz = null;
        }

        Operator(ChronoOperator<PlainTimestamp> chronoOperator, ChronoElement<?> chronoElement, int i, Timezone timezone) {
            this.delegate = chronoOperator;
            this.element = chronoElement;
            this.type = i;
            this.tz = timezone;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // net.time4j.engine.ChronoOperator
        public Moment apply(Moment moment) {
            int i;
            Timezone timezoneOfSystem = this.tz;
            if (timezoneOfSystem == null) {
                timezoneOfSystem = Timezone.ofSystem();
            }
            if (moment.isLeapSecond() && isNonIsoOffset(timezoneOfSystem, moment)) {
                throw new IllegalArgumentException("Leap second can only be adjusted  with timezone-offset in full minutes: " + timezoneOfSystem.getOffset(moment));
            }
            long jSafeSubtract = 1;
            if (moment.isAfter((UniversalTime) Moment.START_LS_CHECK)) {
                if (this.element == PlainTime.SECOND_OF_MINUTE && this.type == -1 && extractValue() == 60) {
                    if (moment.isLeapSecond()) {
                        return moment;
                    }
                    if (!isNonIsoOffset(timezoneOfSystem, moment)) {
                        if (Moment.getMaxSecondOfMinute(moment) == 60) {
                            return moment.plus(MathUtils.safeSubtract(60L, extractOld(moment)), SI.SECONDS);
                        }
                        throw new IllegalArgumentException("Leap second invalid in context: " + moment);
                    }
                    throw new IllegalArgumentException("Leap second can only be set  with timezone-offset in full minutes: " + timezoneOfSystem.getOffset(moment));
                }
                if (Moment.LOW_TIME_ELEMENTS.containsKey(this.element) && ((i = this.type) == 2 || i == 3 || i == 6)) {
                    int iIntValue = ((Integer) Moment.LOW_TIME_ELEMENTS.get(this.element)).intValue();
                    int i2 = this.type;
                    if (i2 == 2) {
                        jSafeSubtract = -1;
                    } else if (i2 == 6) {
                        jSafeSubtract = MathUtils.safeSubtract(extractValue(), extractOld(moment));
                    }
                    if (iIntValue == 1) {
                        return moment.plus(jSafeSubtract, SI.SECONDS);
                    }
                    if (iIntValue == 1000) {
                        return moment.plus(MathUtils.safeMultiply(1000000L, jSafeSubtract), SI.NANOSECONDS);
                    }
                    if (iIntValue == 1000000) {
                        return moment.plus(MathUtils.safeMultiply(1000L, jSafeSubtract), SI.NANOSECONDS);
                    }
                    if (iIntValue == 1000000000) {
                        return moment.plus(jSafeSubtract, SI.NANOSECONDS);
                    }
                    throw new AssertionError();
                }
            }
            PlainTimestamp plainTimestamp = (PlainTimestamp) moment.in(timezoneOfSystem).with(this.delegate);
            Moment momentIn = plainTimestamp.in(timezoneOfSystem);
            if (this.type == 4) {
                return momentIn;
            }
            if (momentIn.isNegativeLS()) {
                if (this.tz.getStrategy() != Timezone.STRICT_MODE) {
                    return momentIn;
                }
                throw new ChronoException("Illegal local timestamp due to negative leap second: " + plainTimestamp);
            }
            if (this.element.isDateElement() || Moment.HIGH_TIME_ELEMENTS.contains(this.element)) {
                return (moment.isLeapSecond() || this.type == 5) ? Moment.moveEventuallyToLS(momentIn) : momentIn;
            }
            if (this.element == PlainTime.SECOND_OF_MINUTE) {
                int i3 = this.type;
                return (i3 == 1 || i3 == 5) ? Moment.moveEventuallyToLS(momentIn) : momentIn;
            }
            if (this.element != PlainTime.MILLI_OF_SECOND && this.element != PlainTime.MICRO_OF_SECOND && this.element != PlainTime.NANO_OF_SECOND) {
                return momentIn;
            }
            int i4 = this.type;
            return ((i4 == -1 || i4 == 0 || i4 == 1 || i4 == 5) && moment.isLeapSecond()) ? momentIn.plus(1L, SI.SECONDS) : momentIn;
        }

        private long extractOld(Moment moment) {
            return ((Number) Number.class.cast(moment.getTimeUTC().get(this.element))).longValue();
        }

        private long extractValue() {
            Object value = ((ValueOperator) ValueOperator.class.cast(this.delegate)).getValue();
            if (value == null) {
                throw new IllegalArgumentException("Missing new element value.");
            }
            return ((Number) Number.class.cast(value)).longValue();
        }

        private static boolean isNonIsoOffset(Timezone timezone, Moment moment) {
            ZonalOffset offset = timezone.getOffset(moment);
            return (offset.getFractionalAmount() == 0 && offset.getAbsoluteSeconds() % 60 == 0) ? false : true;
        }
    }

    private static class TimeUnitRule implements UnitRule<Moment> {
        private final TimeUnit unit;

        TimeUnitRule(TimeUnit timeUnit) {
            this.unit = timeUnit;
        }

        @Override // net.time4j.engine.UnitRule
        public Moment addTo(Moment moment, long j) {
            if (this.unit.compareTo(TimeUnit.SECONDS) >= 0) {
                return Moment.of(MathUtils.safeAdd(moment.getPosixTime(), MathUtils.safeMultiply(j, this.unit.toSeconds(1L))), moment.getNanosecond(), TimeScale.POSIX);
            }
            long jSafeAdd = MathUtils.safeAdd(moment.getNanosecond(), MathUtils.safeMultiply(j, this.unit.toNanos(1L)));
            return Moment.of(MathUtils.safeAdd(moment.getPosixTime(), MathUtils.floorDivide(jSafeAdd, 1000000000)), MathUtils.floorModulo(jSafeAdd, 1000000000), TimeScale.POSIX);
        }

        @Override // net.time4j.engine.UnitRule
        public long between(Moment moment, Moment moment2) {
            long jSafeAdd;
            if (this.unit.compareTo(TimeUnit.SECONDS) >= 0) {
                jSafeAdd = moment2.getPosixTime() - moment.getPosixTime();
                if (jSafeAdd < 0) {
                    if (moment2.getNanosecond() > moment.getNanosecond()) {
                        jSafeAdd++;
                    }
                } else if (jSafeAdd > 0 && moment2.getNanosecond() < moment.getNanosecond()) {
                    jSafeAdd--;
                }
            } else {
                jSafeAdd = MathUtils.safeAdd(MathUtils.safeMultiply(MathUtils.safeSubtract(moment2.getPosixTime(), moment.getPosixTime()), C.NANOS_PER_SECOND), moment2.getNanosecond() - moment.getNanosecond());
            }
            switch (AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit[this.unit.ordinal()]) {
                case 1:
                    return jSafeAdd / 86400;
                case 2:
                    return jSafeAdd / 3600;
                case 3:
                    return jSafeAdd / 60;
                case 4:
                case 7:
                    return jSafeAdd;
                case 5:
                    return jSafeAdd / 1000000;
                case 6:
                    return jSafeAdd / 1000;
                default:
                    throw new UnsupportedOperationException(this.unit.name());
            }
        }
    }

    /* renamed from: net.time4j.Moment$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$util$concurrent$TimeUnit;
        static final /* synthetic */ int[] $SwitchMap$net$time4j$SI;
        static final /* synthetic */ int[] $SwitchMap$net$time4j$scale$TimeScale;

        static {
            int[] iArr = new int[TimeUnit.values().length];
            $SwitchMap$java$util$concurrent$TimeUnit = iArr;
            try {
                iArr[TimeUnit.DAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.HOURS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MINUTES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MILLISECONDS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MICROSECONDS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.NANOSECONDS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr2 = new int[SI.values().length];
            $SwitchMap$net$time4j$SI = iArr2;
            try {
                iArr2[SI.SECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$net$time4j$SI[SI.NANOSECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr3 = new int[TimeScale.values().length];
            $SwitchMap$net$time4j$scale$TimeScale = iArr3;
            try {
                iArr3[TimeScale.POSIX.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$net$time4j$scale$TimeScale[TimeScale.UTC.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$net$time4j$scale$TimeScale[TimeScale.TAI.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$net$time4j$scale$TimeScale[TimeScale.GPS.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$net$time4j$scale$TimeScale[TimeScale.TT.ordinal()] = 5;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$net$time4j$scale$TimeScale[TimeScale.UT.ordinal()] = 6;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    private enum LongElement implements ChronoElement<Long>, ElementRule<Moment, Long> {
        POSIX_TIME;

        @Override // net.time4j.engine.ChronoElement
        public char getSymbol() {
            return (char) 0;
        }

        @Override // net.time4j.engine.ChronoElement
        public boolean isDateElement() {
            return false;
        }

        @Override // net.time4j.engine.ChronoElement
        public boolean isLenient() {
            return false;
        }

        @Override // net.time4j.engine.ChronoElement
        public boolean isTimeElement() {
            return false;
        }

        @Override // net.time4j.engine.ChronoElement
        public Class<Long> getType() {
            return Long.class;
        }

        @Override // java.util.Comparator
        public int compare(ChronoDisplay chronoDisplay, ChronoDisplay chronoDisplay2) {
            return ((Long) chronoDisplay.get(this)).compareTo((Long) chronoDisplay2.get(this));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.time4j.engine.ChronoElement
        public Long getDefaultMinimum() {
            return Long.valueOf(Moment.MIN_LIMIT);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.time4j.engine.ChronoElement
        public Long getDefaultMaximum() {
            return Long.valueOf(Moment.MAX_LIMIT);
        }

        @Override // net.time4j.engine.ChronoElement
        public String getDisplayName(Locale locale) {
            return name();
        }

        @Override // net.time4j.engine.ElementRule
        public Long getValue(Moment moment) {
            return Long.valueOf(moment.getPosixTime());
        }

        @Override // net.time4j.engine.ElementRule
        public Long getMinimum(Moment moment) {
            return Long.valueOf(Moment.MIN_LIMIT);
        }

        @Override // net.time4j.engine.ElementRule
        public Long getMaximum(Moment moment) {
            return Long.valueOf(Moment.MAX_LIMIT);
        }

        @Override // net.time4j.engine.ElementRule
        public boolean isValid2(Moment moment, Long l) {
            if (l == null) {
                return false;
            }
            long jLongValue = l.longValue();
            return jLongValue >= Moment.MIN_LIMIT && jLongValue <= Moment.MAX_LIMIT;
        }

        @Override // net.time4j.engine.ElementRule
        public Moment withValue2(Moment moment, Long l, boolean z) {
            if (l == null) {
                throw new IllegalArgumentException("Missing elapsed seconds.");
            }
            return Moment.of(l.longValue(), moment.getNanosecond(), TimeScale.POSIX);
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(Moment moment) {
            return IntElement.FRACTION;
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(Moment moment) {
            return IntElement.FRACTION;
        }
    }

    private enum IntElement implements ChronoElement<Integer>, ElementRule<Moment, Integer> {
        FRACTION;

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(Moment moment) {
            return null;
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(Moment moment) {
            return null;
        }

        @Override // net.time4j.engine.ChronoElement
        public char getSymbol() {
            return (char) 0;
        }

        @Override // net.time4j.engine.ChronoElement
        public boolean isDateElement() {
            return false;
        }

        @Override // net.time4j.engine.ChronoElement
        public boolean isLenient() {
            return false;
        }

        @Override // net.time4j.engine.ChronoElement
        public boolean isTimeElement() {
            return false;
        }

        @Override // net.time4j.engine.ChronoElement
        public Class<Integer> getType() {
            return Integer.class;
        }

        @Override // java.util.Comparator
        public int compare(ChronoDisplay chronoDisplay, ChronoDisplay chronoDisplay2) {
            return ((Integer) chronoDisplay.get(this)).compareTo((Integer) chronoDisplay2.get(this));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.time4j.engine.ChronoElement
        public Integer getDefaultMinimum() {
            return 0;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.time4j.engine.ChronoElement
        public Integer getDefaultMaximum() {
            return 999999999;
        }

        @Override // net.time4j.engine.ChronoElement
        public String getDisplayName(Locale locale) {
            return name();
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getValue(Moment moment) {
            return Integer.valueOf(moment.getNanosecond());
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getMinimum(Moment moment) {
            return getDefaultMinimum();
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getMaximum(Moment moment) {
            return getDefaultMaximum();
        }

        @Override // net.time4j.engine.ElementRule
        public boolean isValid2(Moment moment, Integer num) {
            int iIntValue;
            return num != null && (iIntValue = num.intValue()) >= 0 && iIntValue < 1000000000;
        }

        @Override // net.time4j.engine.ElementRule
        public Moment withValue2(Moment moment, Integer num, boolean z) {
            if (num == null) {
                throw new IllegalArgumentException("Missing fraction value.");
            }
            if (LeapSeconds.getInstance().isEnabled()) {
                return Moment.of(moment.getElapsedTime(TimeScale.UTC), num.intValue(), TimeScale.UTC);
            }
            return Moment.of(moment.getPosixTime(), num.intValue(), TimeScale.POSIX);
        }
    }

    private static class Merger implements ChronoMerger<Moment> {
        private Merger() {
        }

        /* synthetic */ Merger(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // net.time4j.engine.ChronoMerger
        public /* bridge */ /* synthetic */ Moment createFrom(TimeSource timeSource, AttributeQuery attributeQuery) {
            return createFrom2((TimeSource<?>) timeSource, attributeQuery);
        }

        @Override // net.time4j.engine.ChronoMerger
        public /* bridge */ /* synthetic */ Moment createFrom(ChronoEntity chronoEntity, AttributeQuery attributeQuery, boolean z, boolean z2) {
            return createFrom2((ChronoEntity<?>) chronoEntity, attributeQuery, z, z2);
        }

        @Override // net.time4j.engine.ChronoMerger
        public String getFormatPattern(DisplayStyle displayStyle, Locale locale) {
            DisplayMode displayModeOfStyle = DisplayMode.ofStyle(displayStyle.getStyleValue());
            return CalendarText.patternForMoment(displayModeOfStyle, displayModeOfStyle, locale);
        }

        @Override // net.time4j.engine.ChronoMerger
        public StartOfDay getDefaultStartOfDay() {
            return StartOfDay.MIDNIGHT;
        }

        @Override // net.time4j.engine.ChronoMerger
        public int getDefaultPivotYear() {
            return PlainDate.axis().getDefaultPivotYear();
        }

        @Override // net.time4j.engine.ChronoMerger
        /* renamed from: createFrom, reason: avoid collision after fix types in other method */
        public Moment createFrom2(TimeSource<?> timeSource, AttributeQuery attributeQuery) {
            return Moment.from(timeSource.currentTime());
        }

        @Override // net.time4j.engine.ChronoMerger
        /* renamed from: createFrom, reason: avoid collision after fix types in other method */
        public Moment createFrom2(ChronoEntity<?> chronoEntity, AttributeQuery attributeQuery, boolean z, boolean z2) {
            PlainTimestamp plainTimestamp;
            TZID timezone;
            Moment momentInTimezone;
            ZonalOffset offset;
            Moment moment;
            TimeScale timeScale = (TimeScale) attributeQuery.get(Attributes.TIME_SCALE, TimeScale.UTC);
            if (chronoEntity instanceof UnixTime) {
                return Moment.from((UnixTime) UnixTime.class.cast(chronoEntity)).transformForParse(timeScale);
            }
            if (chronoEntity.contains(LongElement.POSIX_TIME)) {
                return Moment.of(((Long) chronoEntity.get(LongElement.POSIX_TIME)).longValue(), chronoEntity.contains(IntElement.FRACTION) ? ((Integer) chronoEntity.get(IntElement.FRACTION)).intValue() : 0, TimeScale.POSIX).transformForParse(timeScale);
            }
            if (chronoEntity.contains(FlagElement.LEAP_SECOND)) {
                chronoEntity.with((ChronoElement<Integer>) PlainTime.SECOND_OF_MINUTE, 60);
                i = 1;
            }
            ChronoElement<?> chronoElementElement = PlainTimestamp.axis().element();
            if (chronoEntity.contains(chronoElementElement)) {
                plainTimestamp = (PlainTimestamp) chronoEntity.get(chronoElementElement);
            } else {
                plainTimestamp = (PlainTimestamp) PlainTimestamp.axis().createFrom(chronoEntity, attributeQuery, z, z2);
            }
            AnonymousClass1 anonymousClass1 = null;
            if (plainTimestamp == null) {
                return null;
            }
            if (chronoEntity.hasTimezone()) {
                timezone = chronoEntity.getTimezone();
            } else {
                timezone = attributeQuery.contains(Attributes.TIMEZONE_ID) ? (TZID) attributeQuery.get(Attributes.TIMEZONE_ID) : null;
            }
            if (timezone == null) {
                momentInTimezone = null;
            } else if (chronoEntity.contains(FlagElement.DAYLIGHT_SAVING)) {
                momentInTimezone = plainTimestamp.in(Timezone.of(timezone).with(((TransitionStrategy) attributeQuery.get(Attributes.TRANSITION_STRATEGY, Timezone.DEFAULT_CONFLICT_STRATEGY)).using(((Boolean) chronoEntity.get(FlagElement.DAYLIGHT_SAVING)).booleanValue() ? OverlapResolver.EARLIER_OFFSET : OverlapResolver.LATER_OFFSET)));
            } else if (attributeQuery.contains(Attributes.TRANSITION_STRATEGY)) {
                momentInTimezone = plainTimestamp.in(Timezone.of(timezone).with((TransitionStrategy) attributeQuery.get(Attributes.TRANSITION_STRATEGY)));
            } else {
                momentInTimezone = plainTimestamp.inTimezone(timezone);
            }
            if (momentInTimezone == null) {
                return null;
            }
            if (i != 0) {
                if (timezone instanceof ZonalOffset) {
                    offset = (ZonalOffset) timezone;
                } else {
                    offset = Timezone.of(timezone).getOffset(momentInTimezone);
                }
                if (offset.getFractionalAmount() == 0 && offset.getAbsoluteSeconds() % 60 == 0) {
                    if (momentInTimezone.getDateUTC().getYear() >= 1972) {
                        moment = momentInTimezone.plus(1L, SI.SECONDS);
                    } else {
                        moment = new Moment(momentInTimezone.getNanosecond(), momentInTimezone.getPosixTime() + 1, anonymousClass1);
                    }
                    if (z) {
                        momentInTimezone = moment;
                    } else if (LeapSeconds.getInstance().isEnabled()) {
                        if (!moment.isPositiveLS()) {
                            throw new IllegalArgumentException("SECOND_OF_MINUTE parsed as invalid leapsecond before " + moment);
                        }
                        momentInTimezone = moment;
                    }
                } else {
                    throw new IllegalArgumentException("Leap second is only allowed  with timezone-offset in full minutes: " + offset);
                }
            }
            return momentInTimezone.transformForParse(timeScale);
        }

        @Override // net.time4j.engine.ChronoMerger
        public ChronoDisplay preformat(Moment moment, AttributeQuery attributeQuery) {
            if (attributeQuery.contains(Attributes.TIMEZONE_ID)) {
                return moment.transformForPrint((TimeScale) attributeQuery.get(Attributes.TIME_SCALE, TimeScale.UTC)).inZonalView((TZID) attributeQuery.get(Attributes.TIMEZONE_ID));
            }
            throw new IllegalArgumentException("Cannot print moment without timezone.");
        }

        @Override // net.time4j.engine.ChronoMerger
        public Chronology<?> preparser() {
            return PlainTimestamp.axis();
        }
    }

    private static class GlobalTimeLine implements TimeLine<Moment> {
        @Override // net.time4j.engine.TimeLine
        public boolean isCalendrical() {
            return false;
        }

        private GlobalTimeLine() {
        }

        /* synthetic */ GlobalTimeLine(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // net.time4j.engine.TimeLine
        public Moment stepForward(Moment moment) {
            try {
                if (useSI(moment)) {
                    return moment.plus(1L, SI.NANOSECONDS);
                }
                return moment.plus(1L, (long) TimeUnit.NANOSECONDS);
            } catch (ArithmeticException unused) {
                return null;
            }
        }

        @Override // net.time4j.engine.TimeLine
        public Moment stepBackwards(Moment moment) {
            try {
                if (useSI(moment)) {
                    return moment.minus(1L, SI.NANOSECONDS);
                }
                return moment.minus(1L, (long) TimeUnit.NANOSECONDS);
            } catch (ArithmeticException unused) {
                return null;
            }
        }

        @Override // java.util.Comparator
        public int compare(Moment moment, Moment moment2) {
            return moment.compareTo(moment2);
        }

        private static boolean useSI(Moment moment) {
            return moment.posixTime > Moment.POSIX_UTC_DELTA && LeapSeconds.getInstance().isEnabled();
        }
    }

    private static class NextLS implements ChronoOperator<Moment> {
        private NextLS() {
        }

        /* synthetic */ NextLS(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // net.time4j.engine.ChronoOperator
        public Moment apply(Moment moment) {
            LeapSecondEvent nextEvent;
            LeapSeconds leapSeconds = LeapSeconds.getInstance();
            if (!leapSeconds.isEnabled() || (nextEvent = leapSeconds.getNextEvent(moment.getElapsedTime(TimeScale.UTC))) == null) {
                return null;
            }
            return PlainDate.from(nextEvent.getDate()).atTime(23, 59, 59).atUTC().plus(nextEvent.getShift(), SI.SECONDS);
        }
    }

    private static class PrecisionRule implements ElementRule<Moment, TimeUnit> {
        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(Moment moment) {
            return null;
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(Moment moment) {
            return null;
        }

        @Override // net.time4j.engine.ElementRule
        public boolean isValid2(Moment moment, TimeUnit timeUnit) {
            return timeUnit != null;
        }

        private PrecisionRule() {
        }

        /* synthetic */ PrecisionRule(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // net.time4j.engine.ElementRule
        public TimeUnit getValue(Moment moment) {
            int nanosecond = moment.getNanosecond();
            if (nanosecond == 0) {
                long j = moment.posixTime;
                if (MathUtils.floorModulo(j, 86400) == 0) {
                    return TimeUnit.DAYS;
                }
                if (MathUtils.floorModulo(j, NikonType2MakernoteDirectory.TAG_NIKON_SCAN) == 0) {
                    return TimeUnit.HOURS;
                }
                if (MathUtils.floorModulo(j, 60) == 0) {
                    return TimeUnit.MINUTES;
                }
                return TimeUnit.SECONDS;
            }
            if (nanosecond % 1000000 == 0) {
                return TimeUnit.MILLISECONDS;
            }
            if (nanosecond % 1000 == 0) {
                return TimeUnit.MICROSECONDS;
            }
            return TimeUnit.NANOSECONDS;
        }

        @Override // net.time4j.engine.ElementRule
        public Moment withValue2(Moment moment, TimeUnit timeUnit, boolean z) {
            Moment momentOf;
            if (timeUnit == null) {
                throw new IllegalArgumentException("Missing precision.");
            }
            switch (AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit[timeUnit.ordinal()]) {
                case 1:
                    return Moment.of(MathUtils.floorDivide(moment.posixTime, 86400) * 86400, TimeScale.POSIX);
                case 2:
                    return Moment.of(MathUtils.floorDivide(moment.posixTime, NikonType2MakernoteDirectory.TAG_NIKON_SCAN) * 3600, TimeScale.POSIX);
                case 3:
                    return Moment.of(MathUtils.floorDivide(moment.posixTime, 60) * 60, TimeScale.POSIX);
                case 4:
                    momentOf = Moment.of(moment.posixTime, 0, TimeScale.POSIX);
                    break;
                case 5:
                    momentOf = Moment.of(moment.posixTime, (moment.getNanosecond() / 1000000) * 1000000, TimeScale.POSIX);
                    break;
                case 6:
                    momentOf = Moment.of(moment.posixTime, (moment.getNanosecond() / 1000) * 1000, TimeScale.POSIX);
                    break;
                case 7:
                    return moment;
                default:
                    throw new UnsupportedOperationException(timeUnit.name());
            }
            return (moment.isLeapSecond() && LeapSeconds.getInstance().isEnabled()) ? momentOf.plus(1L, SI.SECONDS) : momentOf;
        }

        @Override // net.time4j.engine.ElementRule
        public TimeUnit getMinimum(Moment moment) {
            return TimeUnit.DAYS;
        }

        @Override // net.time4j.engine.ElementRule
        public TimeUnit getMaximum(Moment moment) {
            return TimeUnit.NANOSECONDS;
        }
    }
}
