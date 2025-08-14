package net.time4j;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.C;
import com.drew.metadata.exif.makernotes.NikonType2MakernoteDirectory;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.DayPeriod;
import net.time4j.base.MathUtils;
import net.time4j.base.ResourceLoader;
import net.time4j.base.TimeSource;
import net.time4j.base.UnixTime;
import net.time4j.base.WallTime;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.BridgeChronology;
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoException;
import net.time4j.engine.ChronoExtension;
import net.time4j.engine.ChronoMerger;
import net.time4j.engine.Chronology;
import net.time4j.engine.Converter;
import net.time4j.engine.DisplayStyle;
import net.time4j.engine.ElementRule;
import net.time4j.engine.FormattableElement;
import net.time4j.engine.StartOfDay;
import net.time4j.engine.Temporal;
import net.time4j.engine.TimeAxis;
import net.time4j.engine.TimePoint;
import net.time4j.engine.UnitRule;
import net.time4j.engine.ValidationElement;
import net.time4j.format.Attributes;
import net.time4j.format.CalendarText;
import net.time4j.format.CalendarType;
import net.time4j.format.DisplayMode;
import net.time4j.format.Leniency;
import net.time4j.format.LocalizedPatternSupport;
import net.time4j.format.TemporalFormatter;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;
import net.time4j.tz.ZonalOffset;
import org.apache.commons.lang3.ClassUtils;

@CalendarType(CalendarText.ISO_CALENDAR_TYPE)
/* loaded from: classes4.dex */
public final class PlainTime extends TimePoint<IsoTimeUnit, PlainTime> implements WallTime, Temporal<PlainTime>, LocalizedPatternSupport {

    @FormattableElement(format = "a")
    public static final ZonalElement<Meridiem> AM_PM_OF_DAY;

    @FormattableElement(format = "h")
    public static final AdjustableElement<Integer, PlainTime> CLOCK_HOUR_OF_AMPM;

    @FormattableElement(format = "k")
    public static final AdjustableElement<Integer, PlainTime> CLOCK_HOUR_OF_DAY;
    public static final WallTimeElement COMPONENT;
    private static final BigDecimal DECIMAL_23_9;
    private static final BigDecimal DECIMAL_24_0;
    private static final BigDecimal DECIMAL_3600;
    private static final BigDecimal DECIMAL_59_9;
    private static final BigDecimal DECIMAL_60;
    public static final ZonalElement<BigDecimal> DECIMAL_HOUR;
    public static final ZonalElement<BigDecimal> DECIMAL_MINUTE;
    private static final BigDecimal DECIMAL_MRD;
    public static final ZonalElement<BigDecimal> DECIMAL_SECOND;

    @FormattableElement(format = "K")
    public static final ProportionalElement<Integer, PlainTime> DIGITAL_HOUR_OF_AMPM;

    @FormattableElement(format = "H")
    public static final ProportionalElement<Integer, PlainTime> DIGITAL_HOUR_OF_DAY;
    private static final Map<String, Object> ELEMENTS;
    private static final TimeAxis<IsoTimeUnit, PlainTime> ENGINE;
    private static final PlainTime[] HOURS;
    public static final ProportionalElement<Integer, PlainTime> HOUR_FROM_0_TO_24;
    private static final ElementRule<PlainTime, BigDecimal> H_DECIMAL_RULE;
    static final char ISO_DECIMAL_SEPARATOR;
    private static final int KILO = 1000;
    static final PlainTime MAX;
    public static final ProportionalElement<Long, PlainTime> MICRO_OF_DAY;
    public static final ProportionalElement<Integer, PlainTime> MICRO_OF_SECOND;

    @FormattableElement(format = ExifInterface.GPS_MEASUREMENT_IN_PROGRESS)
    public static final ProportionalElement<Integer, PlainTime> MILLI_OF_DAY;
    public static final ProportionalElement<Integer, PlainTime> MILLI_OF_SECOND;
    static final PlainTime MIN;
    public static final ProportionalElement<Integer, PlainTime> MINUTE_OF_DAY;

    @FormattableElement(format = "m")
    public static final ProportionalElement<Integer, PlainTime> MINUTE_OF_HOUR;
    private static final int MIO = 1000000;
    private static final int MRD = 1000000000;
    private static final ElementRule<PlainTime, BigDecimal> M_DECIMAL_RULE;
    public static final ProportionalElement<Long, PlainTime> NANO_OF_DAY;

    @FormattableElement(format = ExifInterface.LATITUDE_SOUTH)
    public static final ProportionalElement<Integer, PlainTime> NANO_OF_SECOND;
    public static final ChronoElement<ClockUnit> PRECISION;
    public static final ProportionalElement<Integer, PlainTime> SECOND_OF_DAY;

    @FormattableElement(format = "s")
    public static final ProportionalElement<Integer, PlainTime> SECOND_OF_MINUTE;
    private static final ElementRule<PlainTime, BigDecimal> S_DECIMAL_RULE;
    static final ChronoElement<PlainTime> WALL_TIME;
    private static final long serialVersionUID = 2780881537313863339L;
    private final transient byte hour;
    private final transient byte minute;
    private final transient int nano;
    private final transient byte second;

    public static TimeAxis<IsoTimeUnit, PlainTime> axis() {
        return ENGINE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getNanoOfDay() {
        return this.nano + (this.second * C.NANOS_PER_SECOND) + (this.minute * 60000000000L) + (this.hour * 3600000000000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFullHour() {
        return ((this.minute | this.second) | this.nano) == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFullMinute() {
        return (this.second | this.nano) == 0;
    }

    public static PlainTime midnightAtEndOfDay() {
        return MAX;
    }

    public static PlainTime midnightAtStartOfDay() {
        return MIN;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.time4j.engine.TimePoint, net.time4j.engine.ChronoEntity
    public TimeAxis<IsoTimeUnit, PlainTime> getChronology() {
        return ENGINE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.time4j.engine.ChronoEntity
    public PlainTime getContext() {
        return this;
    }

    @Override // net.time4j.base.WallTime
    public int getHour() {
        return this.hour;
    }

    @Override // net.time4j.base.WallTime
    public int getMinute() {
        return this.minute;
    }

    @Override // net.time4j.base.WallTime
    public int getNanosecond() {
        return this.nano;
    }

    @Override // net.time4j.base.WallTime
    public int getSecond() {
        return this.second;
    }

    @Override // net.time4j.engine.TimePoint
    public int hashCode() {
        return this.hour + (this.minute * 60) + (this.second * 3600) + (this.nano * 37);
    }

    static {
        ISO_DECIMAL_SEPARATOR = Boolean.getBoolean("net.time4j.format.iso.decimal.dot") ? ClassUtils.PACKAGE_SEPARATOR_CHAR : AbstractJsonLexerKt.COMMA;
        DECIMAL_60 = new BigDecimal(60);
        DECIMAL_3600 = new BigDecimal(NikonType2MakernoteDirectory.TAG_NIKON_SCAN);
        DECIMAL_MRD = new BigDecimal(1000000000);
        DECIMAL_24_0 = new BigDecimal("24");
        DECIMAL_23_9 = new BigDecimal("23.999999999999999");
        DECIMAL_59_9 = new BigDecimal("59.999999999999999");
        HOURS = new PlainTime[25];
        for (int i = 0; i <= 24; i++) {
            HOURS[i] = new PlainTime(i, 0, 0, 0, false);
        }
        PlainTime[] plainTimeArr = HOURS;
        PlainTime plainTime = plainTimeArr[0];
        MIN = plainTime;
        PlainTime plainTime2 = plainTimeArr[24];
        MAX = plainTime2;
        TimeElement timeElement = TimeElement.INSTANCE;
        WALL_TIME = timeElement;
        COMPONENT = TimeElement.INSTANCE;
        AmPmElement amPmElement = AmPmElement.AM_PM_OF_DAY;
        AM_PM_OF_DAY = amPmElement;
        IntegerTimeElement integerTimeElementCreateClockElement = IntegerTimeElement.createClockElement("CLOCK_HOUR_OF_AMPM", false);
        CLOCK_HOUR_OF_AMPM = integerTimeElementCreateClockElement;
        IntegerTimeElement integerTimeElementCreateClockElement2 = IntegerTimeElement.createClockElement("CLOCK_HOUR_OF_DAY", true);
        CLOCK_HOUR_OF_DAY = integerTimeElementCreateClockElement2;
        IntegerTimeElement integerTimeElementCreateTimeElement = IntegerTimeElement.createTimeElement("DIGITAL_HOUR_OF_AMPM", 3, 0, 11, 'K');
        DIGITAL_HOUR_OF_AMPM = integerTimeElementCreateTimeElement;
        IntegerTimeElement integerTimeElementCreateTimeElement2 = IntegerTimeElement.createTimeElement("DIGITAL_HOUR_OF_DAY", 4, 0, 23, 'H');
        DIGITAL_HOUR_OF_DAY = integerTimeElementCreateTimeElement2;
        IntegerTimeElement integerTimeElementCreateTimeElement3 = IntegerTimeElement.createTimeElement("HOUR_FROM_0_TO_24", 5, 0, 23, 'H');
        HOUR_FROM_0_TO_24 = integerTimeElementCreateTimeElement3;
        IntegerTimeElement integerTimeElementCreateTimeElement4 = IntegerTimeElement.createTimeElement("MINUTE_OF_HOUR", 6, 0, 59, 'm');
        MINUTE_OF_HOUR = integerTimeElementCreateTimeElement4;
        IntegerTimeElement integerTimeElementCreateTimeElement5 = IntegerTimeElement.createTimeElement("MINUTE_OF_DAY", 7, 0, 1439, (char) 0);
        MINUTE_OF_DAY = integerTimeElementCreateTimeElement5;
        IntegerTimeElement integerTimeElementCreateTimeElement6 = IntegerTimeElement.createTimeElement("SECOND_OF_MINUTE", 8, 0, 59, 's');
        SECOND_OF_MINUTE = integerTimeElementCreateTimeElement6;
        IntegerTimeElement integerTimeElementCreateTimeElement7 = IntegerTimeElement.createTimeElement("SECOND_OF_DAY", 9, 0, 86399, (char) 0);
        SECOND_OF_DAY = integerTimeElementCreateTimeElement7;
        IntegerTimeElement integerTimeElementCreateTimeElement8 = IntegerTimeElement.createTimeElement("MILLI_OF_SECOND", 10, 0, 999, (char) 0);
        MILLI_OF_SECOND = integerTimeElementCreateTimeElement8;
        IntegerTimeElement integerTimeElementCreateTimeElement9 = IntegerTimeElement.createTimeElement("MICRO_OF_SECOND", 11, 0, 999999, (char) 0);
        MICRO_OF_SECOND = integerTimeElementCreateTimeElement9;
        IntegerTimeElement integerTimeElementCreateTimeElement10 = IntegerTimeElement.createTimeElement("NANO_OF_SECOND", 12, 0, 999999999, 'S');
        NANO_OF_SECOND = integerTimeElementCreateTimeElement10;
        IntegerTimeElement integerTimeElementCreateTimeElement11 = IntegerTimeElement.createTimeElement("MILLI_OF_DAY", 13, 0, 86399999, 'A');
        MILLI_OF_DAY = integerTimeElementCreateTimeElement11;
        LongElement longElementCreate = LongElement.create("MICRO_OF_DAY", 0L, 86399999999L);
        MICRO_OF_DAY = longElementCreate;
        LongElement longElementCreate2 = LongElement.create("NANO_OF_DAY", 0L, 86399999999999L);
        NANO_OF_DAY = longElementCreate2;
        DecimalTimeElement decimalTimeElement = new DecimalTimeElement("DECIMAL_HOUR", DECIMAL_23_9);
        DECIMAL_HOUR = decimalTimeElement;
        BigDecimal bigDecimal = DECIMAL_59_9;
        DecimalTimeElement decimalTimeElement2 = new DecimalTimeElement("DECIMAL_MINUTE", bigDecimal);
        DECIMAL_MINUTE = decimalTimeElement2;
        DecimalTimeElement decimalTimeElement3 = new DecimalTimeElement("DECIMAL_SECOND", bigDecimal);
        DECIMAL_SECOND = decimalTimeElement3;
        ChronoElement<ClockUnit> chronoElement = PrecisionElement.CLOCK_PRECISION;
        PRECISION = chronoElement;
        HashMap map = new HashMap();
        fill(map, timeElement);
        fill(map, amPmElement);
        fill(map, integerTimeElementCreateClockElement);
        fill(map, integerTimeElementCreateClockElement2);
        fill(map, integerTimeElementCreateTimeElement);
        fill(map, integerTimeElementCreateTimeElement2);
        fill(map, integerTimeElementCreateTimeElement3);
        fill(map, integerTimeElementCreateTimeElement4);
        fill(map, integerTimeElementCreateTimeElement5);
        fill(map, integerTimeElementCreateTimeElement6);
        fill(map, integerTimeElementCreateTimeElement7);
        fill(map, integerTimeElementCreateTimeElement8);
        fill(map, integerTimeElementCreateTimeElement9);
        fill(map, integerTimeElementCreateTimeElement10);
        fill(map, integerTimeElementCreateTimeElement11);
        fill(map, longElementCreate);
        fill(map, longElementCreate2);
        fill(map, decimalTimeElement);
        fill(map, decimalTimeElement2);
        fill(map, decimalTimeElement3);
        ELEMENTS = Collections.unmodifiableMap(map);
        BigDecimalElementRule bigDecimalElementRule = new BigDecimalElementRule(decimalTimeElement, DECIMAL_24_0);
        H_DECIMAL_RULE = bigDecimalElementRule;
        BigDecimalElementRule bigDecimalElementRule2 = new BigDecimalElementRule(decimalTimeElement2, bigDecimal);
        M_DECIMAL_RULE = bigDecimalElementRule2;
        BigDecimalElementRule bigDecimalElementRule3 = new BigDecimalElementRule(decimalTimeElement3, bigDecimal);
        S_DECIMAL_RULE = bigDecimalElementRule3;
        TimeAxis.Builder up = TimeAxis.Builder.setUp(IsoTimeUnit.class, PlainTime.class, new Merger(null), plainTime, plainTime2);
        AnonymousClass1 anonymousClass1 = null;
        TimeAxis.Builder builderAppendElement = up.appendElement((ChronoElement) timeElement, (ElementRule) new TimeRule(anonymousClass1)).appendElement((ChronoElement) amPmElement, (ElementRule) new MeridiemRule(anonymousClass1)).appendElement(integerTimeElementCreateClockElement, new IntegerElementRule(integerTimeElementCreateClockElement, 1, 12), ClockUnit.HOURS).appendElement(integerTimeElementCreateClockElement2, new IntegerElementRule(integerTimeElementCreateClockElement2, 1, 24), ClockUnit.HOURS).appendElement(integerTimeElementCreateTimeElement, new IntegerElementRule(integerTimeElementCreateTimeElement, 0, 11), ClockUnit.HOURS).appendElement(integerTimeElementCreateTimeElement2, new IntegerElementRule(integerTimeElementCreateTimeElement2, 0, 23), ClockUnit.HOURS).appendElement(integerTimeElementCreateTimeElement3, new IntegerElementRule(integerTimeElementCreateTimeElement3, 0, 24), ClockUnit.HOURS).appendElement(integerTimeElementCreateTimeElement4, new IntegerElementRule(integerTimeElementCreateTimeElement4, 0, 59), ClockUnit.MINUTES).appendElement(integerTimeElementCreateTimeElement5, new IntegerElementRule(integerTimeElementCreateTimeElement5, 0, 1440), ClockUnit.MINUTES).appendElement(integerTimeElementCreateTimeElement6, new IntegerElementRule(integerTimeElementCreateTimeElement6, 0, 59), ClockUnit.SECONDS).appendElement(integerTimeElementCreateTimeElement7, new IntegerElementRule(integerTimeElementCreateTimeElement7, 0, 86400), ClockUnit.SECONDS).appendElement(integerTimeElementCreateTimeElement8, new IntegerElementRule(integerTimeElementCreateTimeElement8, 0, 999), ClockUnit.MILLIS).appendElement(integerTimeElementCreateTimeElement9, new IntegerElementRule(integerTimeElementCreateTimeElement9, 0, 999999), ClockUnit.MICROS).appendElement(integerTimeElementCreateTimeElement10, new IntegerElementRule(integerTimeElementCreateTimeElement10, 0, 999999999), ClockUnit.NANOS).appendElement(integerTimeElementCreateTimeElement11, new IntegerElementRule(integerTimeElementCreateTimeElement11, 0, 86400000), ClockUnit.MILLIS).appendElement(longElementCreate, new LongElementRule(longElementCreate, 0L, 86400000000L), ClockUnit.MICROS).appendElement(longElementCreate2, new LongElementRule(longElementCreate2, 0L, 86400000000000L), ClockUnit.NANOS).appendElement((ChronoElement) decimalTimeElement, (ElementRule) bigDecimalElementRule).appendElement((ChronoElement) decimalTimeElement2, (ElementRule) bigDecimalElementRule2).appendElement((ChronoElement) decimalTimeElement3, (ElementRule) bigDecimalElementRule3).appendElement((ChronoElement) chronoElement, (ElementRule) new PrecisionRule(null));
        registerExtensions(builderAppendElement);
        registerUnits(builderAppendElement);
        ENGINE = builderAppendElement.build();
    }

    private PlainTime(int i, int i2, int i3, int i4, boolean z) {
        if (z) {
            checkHour(i);
            checkMinute(i2);
            checkSecond(i3);
            checkNano(i4);
            if (i == 24 && (i2 | i3 | i4) != 0) {
                throw new IllegalArgumentException("T24:00:00 exceeded.");
            }
        }
        this.hour = (byte) i;
        this.minute = (byte) i2;
        this.second = (byte) i3;
        this.nano = i4;
    }

    public static PlainTime of(int i) {
        checkHour(i);
        return HOURS[i];
    }

    public static PlainTime of(int i, int i2) {
        if (i2 == 0) {
            return of(i);
        }
        return new PlainTime(i, i2, 0, 0, true);
    }

    public static PlainTime of(int i, int i2, int i3) {
        if ((i2 | i3) == 0) {
            return of(i);
        }
        return new PlainTime(i, i2, i3, 0, true);
    }

    public static PlainTime of(int i, int i2, int i3, int i4) {
        return of(i, i2, i3, i4, true);
    }

    public static PlainTime of(BigDecimal bigDecimal) {
        return H_DECIMAL_RULE.withValue2(null, bigDecimal, false);
    }

    public static PlainTime nowInSystemTime() {
        return ZonalClock.ofSystem().now().toTime();
    }

    public static PlainTime from(WallTime wallTime) {
        if (wallTime instanceof PlainTime) {
            return (PlainTime) wallTime;
        }
        if (wallTime instanceof PlainTimestamp) {
            return ((PlainTimestamp) wallTime).getWallTime();
        }
        return of(wallTime.getHour(), wallTime.getMinute(), wallTime.getSecond(), wallTime.getNanosecond());
    }

    public DayCycles roll(long j, ClockUnit clockUnit) {
        return ClockUnitRule.addToWithOverflow(this, j, clockUnit);
    }

    public String print(TemporalFormatter<PlainTime> temporalFormatter) {
        return temporalFormatter.print(this);
    }

    public static PlainTime parse(String str, TemporalFormatter<PlainTime> temporalFormatter) {
        try {
            return temporalFormatter.parse(str);
        } catch (ParseException e) {
            throw new ChronoException(e.getMessage(), e);
        }
    }

    @Override // net.time4j.engine.TimePoint
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlainTime)) {
            return false;
        }
        PlainTime plainTime = (PlainTime) obj;
        return this.hour == plainTime.hour && this.minute == plainTime.minute && this.second == plainTime.second && this.nano == plainTime.nano;
    }

    @Override // net.time4j.engine.Temporal
    public boolean isBefore(PlainTime plainTime) {
        return compareTo(plainTime) < 0;
    }

    @Override // net.time4j.engine.Temporal
    public boolean isAfter(PlainTime plainTime) {
        return compareTo(plainTime) > 0;
    }

    @Override // net.time4j.engine.Temporal
    public boolean isSimultaneous(PlainTime plainTime) {
        return compareTo(plainTime) == 0;
    }

    public boolean isMidnight() {
        return isFullHour() && this.hour % Ascii.CAN == 0;
    }

    @Override // net.time4j.engine.TimePoint
    public int compareTo(PlainTime plainTime) {
        int i = this.hour - plainTime.hour;
        if (i == 0 && (i = this.minute - plainTime.minute) == 0 && (i = this.second - plainTime.second) == 0) {
            i = this.nano - plainTime.nano;
        }
        if (i < 0) {
            return -1;
        }
        return i == 0 ? 0 : 1;
    }

    @Override // net.time4j.engine.TimePoint
    public String toString() {
        StringBuilder sb = new StringBuilder(19);
        sb.append('T');
        append2Digits(this.hour, sb);
        if ((this.minute | this.second | this.nano) != 0) {
            sb.append(AbstractJsonLexerKt.COLON);
            append2Digits(this.minute, sb);
            if ((this.second | this.nano) != 0) {
                sb.append(AbstractJsonLexerKt.COLON);
                append2Digits(this.second, sb);
                int i = this.nano;
                if (i != 0) {
                    printNanos(sb, i);
                }
            }
        }
        return sb.toString();
    }

    public static <S> Chronology<S> axis(Converter<S, PlainTime> converter) {
        return new BridgeChronology(converter, ENGINE);
    }

    static void printNanos(StringBuilder sb, int i) {
        int i2;
        sb.append(ISO_DECIMAL_SEPARATOR);
        String string = Integer.toString(i);
        if (i % 1000000 == 0) {
            i2 = 3;
        } else {
            i2 = i % 1000 == 0 ? 6 : 9;
        }
        for (int length = string.length(); length < 9; length++) {
            sb.append('0');
        }
        int length2 = (i2 + string.length()) - 9;
        for (int i3 = 0; i3 < length2; i3++) {
            sb.append(string.charAt(i3));
        }
    }

    static PlainTime from(UnixTime unixTime, ZonalOffset zonalOffset) {
        long posixTime = unixTime.getPosixTime() + zonalOffset.getIntegralAmount();
        int nanosecond = unixTime.getNanosecond() + zonalOffset.getFractionalAmount();
        if (nanosecond < 0) {
            nanosecond += 1000000000;
            posixTime--;
        } else if (nanosecond >= 1000000000) {
            nanosecond -= 1000000000;
            posixTime++;
        }
        int iFloorModulo = MathUtils.floorModulo(posixTime, 86400);
        int i = iFloorModulo % 60;
        int i2 = iFloorModulo / 60;
        return of(i2 / 60, i2 % 60, i, nanosecond);
    }

    static Object lookupElement(String str) {
        return ELEMENTS.get(str);
    }

    boolean hasReducedRange(ChronoElement<?> chronoElement) {
        return (chronoElement == MILLI_OF_DAY && this.nano % 1000000 != 0) || (chronoElement == HOUR_FROM_0_TO_24 && !isFullHour()) || ((chronoElement == MINUTE_OF_DAY && !isFullMinute()) || ((chronoElement == SECOND_OF_DAY && this.nano != 0) || (chronoElement == MICRO_OF_DAY && this.nano % 1000 != 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static PlainTime of(int i, int i2, int i3, int i4, boolean z) {
        if ((i2 | i3 | i4) != 0) {
            return new PlainTime(i, i2, i3, i4, z);
        }
        if (z) {
            return of(i);
        }
        return HOURS[i];
    }

    private static void fill(Map<String, Object> map, ChronoElement<?> chronoElement) {
        map.put(chronoElement.name(), chronoElement);
    }

    private static void append2Digits(int i, StringBuilder sb) {
        if (i < 10) {
            sb.append('0');
        }
        sb.append(i);
    }

    private static void checkHour(long j) {
        if (j < 0 || j > 24) {
            throw new IllegalArgumentException("HOUR_OF_DAY out of range: " + j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkMinute(long j) {
        if (j < 0 || j > 59) {
            throw new IllegalArgumentException("MINUTE_OF_HOUR out of range: " + j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkSecond(long j) {
        if (j < 0 || j > 59) {
            throw new IllegalArgumentException("SECOND_OF_MINUTE out of range: " + j);
        }
    }

    private static void checkNano(int i) {
        if (i < 0 || i >= 1000000000) {
            throw new IllegalArgumentException("NANO_OF_SECOND out of range: " + i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static PlainTime createFromMillis(int i, int i2) {
        int i3 = ((i % 1000) * 1000000) + i2;
        int i4 = i / 1000;
        int i5 = i4 % 60;
        int i6 = i4 / 60;
        return of(i6 / 60, i6 % 60, i5, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static PlainTime createFromMicros(long j, int i) {
        int i2 = (((int) (j % 1000000)) * 1000) + i;
        int i3 = (int) (j / 1000000);
        int i4 = i3 % 60;
        int i5 = i3 / 60;
        return of(i5 / 60, i5 % 60, i4, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static PlainTime createFromNanos(long j) {
        int i = (int) (j % C.NANOS_PER_SECOND);
        int i2 = (int) (j / C.NANOS_PER_SECOND);
        int i3 = i2 % 60;
        int i4 = i2 / 60;
        return of(i4 / 60, i4 % 60, i3, i);
    }

    private static void registerExtensions(TimeAxis.Builder<IsoTimeUnit, PlainTime> builder) {
        for (ChronoExtension chronoExtension : ResourceLoader.getInstance().services(ChronoExtension.class)) {
            if (chronoExtension.accept(PlainTime.class)) {
                builder.appendExtension(chronoExtension);
            }
        }
        builder.appendExtension((ChronoExtension) new DayPeriod.Extension());
    }

    private static void registerUnits(TimeAxis.Builder<IsoTimeUnit, PlainTime> builder) {
        Set<? extends IsoTimeUnit> setAllOf = EnumSet.allOf(ClockUnit.class);
        for (ClockUnit clockUnit : ClockUnit.values()) {
            builder.appendUnit(clockUnit, new ClockUnitRule(clockUnit, null), clockUnit.getLength(), setAllOf);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long floorMod(long j, long j2) {
        return j - (j2 * (j >= 0 ? j / j2 : ((j + 1) / j2) - 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long floorDiv(long j, long j2) {
        if (j >= 0) {
            return j / j2;
        }
        return ((j + 1) / j2) - 1;
    }

    private Object writeReplace() {
        return new SPX(this, 2);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        throw new InvalidObjectException("Serialization proxy required.");
    }

    private static class ClockUnitRule implements UnitRule<PlainTime> {
        private final ClockUnit unit;

        /* synthetic */ ClockUnitRule(ClockUnit clockUnit, AnonymousClass1 anonymousClass1) {
            this(clockUnit);
        }

        private ClockUnitRule(ClockUnit clockUnit) {
            this.unit = clockUnit;
        }

        @Override // net.time4j.engine.UnitRule
        public PlainTime addTo(PlainTime plainTime, long j) {
            return j == 0 ? plainTime : (PlainTime) doAdd(PlainTime.class, this.unit, plainTime, j);
        }

        @Override // net.time4j.engine.UnitRule
        public long between(PlainTime plainTime, PlainTime plainTime2) {
            long j;
            long nanoOfDay = plainTime2.getNanoOfDay() - plainTime.getNanoOfDay();
            switch (AnonymousClass1.$SwitchMap$net$time4j$ClockUnit[this.unit.ordinal()]) {
                case 1:
                    j = 3600000000000L;
                    break;
                case 2:
                    j = 60000000000L;
                    break;
                case 3:
                    j = C.NANOS_PER_SECOND;
                    break;
                case 4:
                    j = 1000000;
                    break;
                case 5:
                    j = 1000;
                    break;
                case 6:
                    j = 1;
                    break;
                default:
                    throw new UnsupportedOperationException(this.unit.name());
            }
            return nanoOfDay / j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static DayCycles addToWithOverflow(PlainTime plainTime, long j, ClockUnit clockUnit) {
            if (j == 0 && plainTime.hour < 24) {
                return new DayCycles(0L, plainTime);
            }
            return (DayCycles) doAdd(DayCycles.class, clockUnit, plainTime, j);
        }

        private static <R> R doAdd(Class<R> cls, ClockUnit clockUnit, PlainTime plainTime, long j) {
            long jSafeAdd;
            PlainTime plainTimeOf;
            int iFloorModulo = plainTime.minute;
            int iFloorModulo2 = plainTime.second;
            int i = plainTime.nano;
            switch (AnonymousClass1.$SwitchMap$net$time4j$ClockUnit[clockUnit.ordinal()]) {
                case 1:
                    jSafeAdd = MathUtils.safeAdd(plainTime.hour, j);
                    break;
                case 2:
                    long jSafeAdd2 = MathUtils.safeAdd(plainTime.minute, j);
                    jSafeAdd = MathUtils.safeAdd(plainTime.hour, MathUtils.floorDivide(jSafeAdd2, 60));
                    iFloorModulo = MathUtils.floorModulo(jSafeAdd2, 60);
                    break;
                case 3:
                    long jSafeAdd3 = MathUtils.safeAdd(plainTime.second, j);
                    long jSafeAdd4 = MathUtils.safeAdd(plainTime.minute, MathUtils.floorDivide(jSafeAdd3, 60));
                    jSafeAdd = MathUtils.safeAdd(plainTime.hour, MathUtils.floorDivide(jSafeAdd4, 60));
                    int iFloorModulo3 = MathUtils.floorModulo(jSafeAdd4, 60);
                    iFloorModulo2 = MathUtils.floorModulo(jSafeAdd3, 60);
                    iFloorModulo = iFloorModulo3;
                    break;
                case 4:
                    return (R) doAdd(cls, ClockUnit.NANOS, plainTime, MathUtils.safeMultiply(j, 1000000L));
                case 5:
                    return (R) doAdd(cls, ClockUnit.NANOS, plainTime, MathUtils.safeMultiply(j, 1000L));
                case 6:
                    long jSafeAdd5 = MathUtils.safeAdd(plainTime.nano, j);
                    long jSafeAdd6 = MathUtils.safeAdd(plainTime.second, MathUtils.floorDivide(jSafeAdd5, 1000000000));
                    long jSafeAdd7 = MathUtils.safeAdd(plainTime.minute, MathUtils.floorDivide(jSafeAdd6, 60));
                    jSafeAdd = MathUtils.safeAdd(plainTime.hour, MathUtils.floorDivide(jSafeAdd7, 60));
                    int iFloorModulo4 = MathUtils.floorModulo(jSafeAdd7, 60);
                    int iFloorModulo5 = MathUtils.floorModulo(jSafeAdd6, 60);
                    int iFloorModulo6 = MathUtils.floorModulo(jSafeAdd5, 1000000000);
                    iFloorModulo = iFloorModulo4;
                    iFloorModulo2 = iFloorModulo5;
                    i = iFloorModulo6;
                    break;
                default:
                    throw new UnsupportedOperationException(clockUnit.name());
            }
            int iFloorModulo7 = MathUtils.floorModulo(jSafeAdd, 24);
            if ((iFloorModulo7 | iFloorModulo | iFloorModulo2 | i) == 0) {
                plainTimeOf = (j <= 0 || cls != PlainTime.class) ? PlainTime.MIN : PlainTime.MAX;
            } else {
                plainTimeOf = PlainTime.of(iFloorModulo7, iFloorModulo, iFloorModulo2, i);
            }
            if (cls == PlainTime.class) {
                return cls.cast(plainTimeOf);
            }
            return cls.cast(new DayCycles(MathUtils.floorDivide(jSafeAdd, 24), plainTimeOf));
        }
    }

    /* renamed from: net.time4j.PlainTime$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$time4j$ClockUnit;

        static {
            int[] iArr = new int[ClockUnit.values().length];
            $SwitchMap$net$time4j$ClockUnit = iArr;
            try {
                iArr[ClockUnit.HOURS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$net$time4j$ClockUnit[ClockUnit.MINUTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$net$time4j$ClockUnit[ClockUnit.SECONDS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$net$time4j$ClockUnit[ClockUnit.MILLIS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$net$time4j$ClockUnit[ClockUnit.MICROS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$net$time4j$ClockUnit[ClockUnit.NANOS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private static class TimeRule implements ElementRule<PlainTime, PlainTime> {
        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(PlainTime plainTime) {
            return null;
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(PlainTime plainTime) {
            return null;
        }

        @Override // net.time4j.engine.ElementRule
        public PlainTime getValue(PlainTime plainTime) {
            return plainTime;
        }

        @Override // net.time4j.engine.ElementRule
        public boolean isValid2(PlainTime plainTime, PlainTime plainTime2) {
            return plainTime2 != null;
        }

        private TimeRule() {
        }

        /* synthetic */ TimeRule(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // net.time4j.engine.ElementRule
        public PlainTime withValue2(PlainTime plainTime, PlainTime plainTime2, boolean z) {
            if (plainTime2 != null) {
                return plainTime2;
            }
            throw new IllegalArgumentException("Missing time value.");
        }

        @Override // net.time4j.engine.ElementRule
        public PlainTime getMinimum(PlainTime plainTime) {
            return PlainTime.MIN;
        }

        @Override // net.time4j.engine.ElementRule
        public PlainTime getMaximum(PlainTime plainTime) {
            return PlainTime.MAX;
        }
    }

    private static class PrecisionRule implements ElementRule<PlainTime, ClockUnit> {
        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(PlainTime plainTime) {
            return null;
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(PlainTime plainTime) {
            return null;
        }

        @Override // net.time4j.engine.ElementRule
        public boolean isValid2(PlainTime plainTime, ClockUnit clockUnit) {
            return clockUnit != null;
        }

        private PrecisionRule() {
        }

        /* synthetic */ PrecisionRule(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // net.time4j.engine.ElementRule
        public ClockUnit getValue(PlainTime plainTime) {
            if (plainTime.nano != 0) {
                if (plainTime.nano % 1000000 != 0) {
                    if (plainTime.nano % 1000 == 0) {
                        return ClockUnit.MICROS;
                    }
                    return ClockUnit.NANOS;
                }
                return ClockUnit.MILLIS;
            }
            if (plainTime.second == 0) {
                if (plainTime.minute != 0) {
                    return ClockUnit.MINUTES;
                }
                return ClockUnit.HOURS;
            }
            return ClockUnit.SECONDS;
        }

        @Override // net.time4j.engine.ElementRule
        public PlainTime withValue2(PlainTime plainTime, ClockUnit clockUnit, boolean z) {
            if (clockUnit == null) {
                throw new IllegalArgumentException("Missing precision value.");
            }
            if (clockUnit.ordinal() >= getValue(plainTime).ordinal()) {
                return plainTime;
            }
            switch (AnonymousClass1.$SwitchMap$net$time4j$ClockUnit[clockUnit.ordinal()]) {
                case 1:
                    return PlainTime.of(plainTime.hour);
                case 2:
                    return PlainTime.of(plainTime.hour, plainTime.minute);
                case 3:
                    return PlainTime.of(plainTime.hour, plainTime.minute, plainTime.second);
                case 4:
                    return PlainTime.of(plainTime.hour, plainTime.minute, plainTime.second, (plainTime.nano / 1000000) * 1000000);
                case 5:
                    return PlainTime.of(plainTime.hour, plainTime.minute, plainTime.second, (plainTime.nano / 1000) * 1000);
                case 6:
                    return plainTime;
                default:
                    throw new UnsupportedOperationException(clockUnit.name());
            }
        }

        @Override // net.time4j.engine.ElementRule
        public ClockUnit getMinimum(PlainTime plainTime) {
            return ClockUnit.HOURS;
        }

        @Override // net.time4j.engine.ElementRule
        public ClockUnit getMaximum(PlainTime plainTime) {
            return ClockUnit.NANOS;
        }
    }

    private static class MeridiemRule implements ElementRule<PlainTime, Meridiem> {
        @Override // net.time4j.engine.ElementRule
        public boolean isValid2(PlainTime plainTime, Meridiem meridiem) {
            return meridiem != null;
        }

        private MeridiemRule() {
        }

        /* synthetic */ MeridiemRule(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // net.time4j.engine.ElementRule
        public Meridiem getValue(PlainTime plainTime) {
            return Meridiem.ofHour(plainTime.hour);
        }

        @Override // net.time4j.engine.ElementRule
        public PlainTime withValue2(PlainTime plainTime, Meridiem meridiem, boolean z) {
            int i = plainTime.hour == 24 ? 0 : plainTime.hour;
            if (meridiem == null) {
                throw new IllegalArgumentException("Missing am/pm-value.");
            }
            if (meridiem == Meridiem.AM) {
                if (i >= 12) {
                    i -= 12;
                }
            } else if (meridiem == Meridiem.PM && i < 12) {
                i += 12;
            }
            return PlainTime.of(i, plainTime.minute, plainTime.second, plainTime.nano);
        }

        @Override // net.time4j.engine.ElementRule
        public Meridiem getMinimum(PlainTime plainTime) {
            return Meridiem.AM;
        }

        @Override // net.time4j.engine.ElementRule
        public Meridiem getMaximum(PlainTime plainTime) {
            return Meridiem.PM;
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(PlainTime plainTime) {
            return PlainTime.DIGITAL_HOUR_OF_AMPM;
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(PlainTime plainTime) {
            return PlainTime.DIGITAL_HOUR_OF_AMPM;
        }
    }

    private static class IntegerElementRule implements ElementRule<PlainTime, Integer> {
        private final ChronoElement<Integer> element;
        private final int index;
        private final int max;
        private final int min;

        IntegerElementRule(ChronoElement<Integer> chronoElement, int i, int i2) {
            this.element = chronoElement;
            if (chronoElement instanceof IntegerTimeElement) {
                this.index = ((IntegerTimeElement) chronoElement).getIndex();
            } else {
                this.index = -1;
            }
            this.min = i;
            this.max = i2;
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getValue(PlainTime plainTime) {
            int i;
            byte b;
            int nanoOfDay = 24;
            switch (this.index) {
                case 1:
                    nanoOfDay = plainTime.hour % 12;
                    if (nanoOfDay == 0) {
                        nanoOfDay = 12;
                    }
                    return Integer.valueOf(nanoOfDay);
                case 2:
                    int i2 = plainTime.hour % Ascii.CAN;
                    if (i2 != 0) {
                        nanoOfDay = i2;
                    }
                    return Integer.valueOf(nanoOfDay);
                case 3:
                    nanoOfDay = plainTime.hour % 12;
                    return Integer.valueOf(nanoOfDay);
                case 4:
                    nanoOfDay = plainTime.hour % Ascii.CAN;
                    return Integer.valueOf(nanoOfDay);
                case 5:
                    nanoOfDay = plainTime.hour;
                    return Integer.valueOf(nanoOfDay);
                case 6:
                    nanoOfDay = plainTime.minute;
                    return Integer.valueOf(nanoOfDay);
                case 7:
                    i = plainTime.hour * 60;
                    b = plainTime.minute;
                    nanoOfDay = i + b;
                    return Integer.valueOf(nanoOfDay);
                case 8:
                    nanoOfDay = plainTime.second;
                    return Integer.valueOf(nanoOfDay);
                case 9:
                    i = (plainTime.hour * 3600) + (plainTime.minute * 60);
                    b = plainTime.second;
                    nanoOfDay = i + b;
                    return Integer.valueOf(nanoOfDay);
                case 10:
                    nanoOfDay = plainTime.nano / 1000000;
                    return Integer.valueOf(nanoOfDay);
                case 11:
                    nanoOfDay = plainTime.nano / 1000;
                    return Integer.valueOf(nanoOfDay);
                case 12:
                    nanoOfDay = plainTime.nano;
                    return Integer.valueOf(nanoOfDay);
                case 13:
                    nanoOfDay = (int) (plainTime.getNanoOfDay() / 1000000);
                    return Integer.valueOf(nanoOfDay);
                default:
                    throw new UnsupportedOperationException(this.element.name());
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Removed duplicated region for block: B:22:0x006c A[PHI: r8
          0x006c: PHI (r8v9 int) = (r8v4 int), (r8v5 int), (r8v4 int), (r8v8 int), (r8v4 int) binds: [B:9:0x002d, B:34:0x0087, B:28:0x007a, B:26:0x0075, B:24:0x0072] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0075 A[PHI: r8
          0x0075: PHI (r8v7 int) = (r8v5 int), (r8v4 int) binds: [B:34:0x0087, B:24:0x0072] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // net.time4j.engine.ElementRule
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public net.time4j.PlainTime withValue2(net.time4j.PlainTime r7, java.lang.Integer r8, boolean r9) {
            /*
                r6 = this;
                if (r8 == 0) goto La4
                if (r9 == 0) goto Ld
                int r8 = r8.intValue()
                net.time4j.PlainTime r7 = r6.withValueInLenientMode(r7, r8)
                return r7
            Ld:
                boolean r9 = r6.isValid(r7, r8)
                if (r9 == 0) goto L8f
                byte r9 = net.time4j.PlainTime.access$700(r7)
                byte r0 = net.time4j.PlainTime.access$800(r7)
                byte r1 = net.time4j.PlainTime.access$900(r7)
                int r2 = net.time4j.PlainTime.access$1000(r7)
                int r8 = r8.intValue()
                int r3 = r6.index
                r4 = 0
                r5 = 1000000(0xf4240, float:1.401298E-39)
                switch(r3) {
                    case 1: goto L7e;
                    case 2: goto L78;
                    case 3: goto L6e;
                    case 4: goto L6c;
                    case 5: goto L6c;
                    case 6: goto L6a;
                    case 7: goto L65;
                    case 8: goto L63;
                    case 9: goto L5a;
                    case 10: goto L51;
                    case 11: goto L48;
                    case 12: goto L46;
                    case 13: goto L3c;
                    default: goto L30;
                }
            L30:
                java.lang.UnsupportedOperationException r7 = new java.lang.UnsupportedOperationException
                net.time4j.engine.ChronoElement<java.lang.Integer> r8 = r6.element
                java.lang.String r8 = r8.name()
                r7.<init>(r8)
                throw r7
            L3c:
                int r7 = net.time4j.PlainTime.access$1000(r7)
                int r7 = r7 % r5
                net.time4j.PlainTime r7 = net.time4j.PlainTime.access$1100(r8, r7)
                return r7
            L46:
                r2 = r8
                goto L8a
            L48:
                int r8 = r8 * 1000
                int r7 = net.time4j.PlainTime.access$1000(r7)
                int r7 = r7 % 1000
                goto L57
            L51:
                int r8 = r8 * r5
                int r7 = net.time4j.PlainTime.access$1000(r7)
                int r7 = r7 % r5
            L57:
                int r2 = r8 + r7
                goto L8a
            L5a:
                int r9 = r8 / 3600
                int r8 = r8 % 3600
                int r0 = r8 / 60
                int r1 = r8 % 60
                goto L8a
            L63:
                r1 = r8
                goto L8a
            L65:
                int r9 = r8 / 60
                int r0 = r8 % 60
                goto L8a
            L6a:
                r0 = r8
                goto L8a
            L6c:
                r9 = r8
                goto L8a
            L6e:
                boolean r7 = isAM(r7)
                if (r7 == 0) goto L75
                goto L6c
            L75:
                int r8 = r8 + 12
                goto L6c
            L78:
                r7 = 24
                if (r8 != r7) goto L6c
                r9 = r4
                goto L8a
            L7e:
                r9 = 12
                if (r8 != r9) goto L83
                r8 = r4
            L83:
                boolean r7 = isAM(r7)
                if (r7 == 0) goto L75
                goto L6c
            L8a:
                net.time4j.PlainTime r7 = net.time4j.PlainTime.of(r9, r0, r1, r2)
                return r7
            L8f:
                java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r9 = new java.lang.StringBuilder
                java.lang.String r0 = "Value out of range: "
                r9.<init>(r0)
                java.lang.StringBuilder r8 = r9.append(r8)
                java.lang.String r8 = r8.toString()
                r7.<init>(r8)
                throw r7
            La4:
                java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
                java.lang.String r8 = "Missing element value."
                r7.<init>(r8)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: net.time4j.PlainTime.IntegerElementRule.withValue(net.time4j.PlainTime, java.lang.Integer, boolean):net.time4j.PlainTime");
        }

        @Override // net.time4j.engine.ElementRule
        public boolean isValid2(PlainTime plainTime, Integer num) {
            int iIntValue;
            int i;
            if (num == null || (iIntValue = num.intValue()) < this.min || iIntValue > (i = this.max)) {
                return false;
            }
            if (iIntValue == i) {
                int i2 = this.index;
                if (i2 == 5) {
                    return plainTime.isFullHour();
                }
                if (i2 == 7) {
                    return plainTime.isFullMinute();
                }
                if (i2 == 9) {
                    return plainTime.nano == 0;
                }
                if (i2 == 13) {
                    return plainTime.nano % 1000000 == 0;
                }
            }
            if (plainTime.hour == 24) {
                switch (this.index) {
                    case 6:
                    case 8:
                    case 10:
                    case 11:
                    case 12:
                        return iIntValue == 0;
                }
            }
            return true;
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getMinimum(PlainTime plainTime) {
            return Integer.valueOf(this.min);
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getMaximum(PlainTime plainTime) {
            if (plainTime.hour == 24) {
                switch (this.index) {
                    case 6:
                    case 8:
                    case 10:
                    case 11:
                    case 12:
                        return 0;
                }
            }
            if (plainTime.hasReducedRange(this.element)) {
                return Integer.valueOf(this.max - 1);
            }
            return Integer.valueOf(this.max);
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(PlainTime plainTime) {
            return getChild(plainTime);
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(PlainTime plainTime) {
            return getChild(plainTime);
        }

        private ChronoElement<?> getChild(PlainTime plainTime) {
            switch (this.index) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    return PlainTime.MINUTE_OF_HOUR;
                case 6:
                case 7:
                    return PlainTime.SECOND_OF_MINUTE;
                case 8:
                case 9:
                    return PlainTime.NANO_OF_SECOND;
                default:
                    return null;
            }
        }

        private PlainTime withValueInLenientMode(PlainTime plainTime, int i) {
            if (this.element == PlainTime.HOUR_FROM_0_TO_24 || this.element == PlainTime.DIGITAL_HOUR_OF_DAY || this.element == PlainTime.DIGITAL_HOUR_OF_AMPM) {
                return plainTime.plus(MathUtils.safeSubtract(i, ((Integer) plainTime.get(this.element)).intValue()), ClockUnit.HOURS);
            }
            if (this.element == PlainTime.MINUTE_OF_HOUR) {
                return plainTime.plus(MathUtils.safeSubtract(i, (int) plainTime.minute), ClockUnit.MINUTES);
            }
            if (this.element == PlainTime.SECOND_OF_MINUTE) {
                return plainTime.plus(MathUtils.safeSubtract(i, (int) plainTime.second), ClockUnit.SECONDS);
            }
            if (this.element == PlainTime.MILLI_OF_SECOND) {
                return plainTime.plus(MathUtils.safeSubtract(i, ((Integer) plainTime.get(PlainTime.MILLI_OF_SECOND)).intValue()), ClockUnit.MILLIS);
            }
            if (this.element == PlainTime.MICRO_OF_SECOND) {
                return plainTime.plus(MathUtils.safeSubtract(i, ((Integer) plainTime.get(PlainTime.MICRO_OF_SECOND)).intValue()), ClockUnit.MICROS);
            }
            if (this.element == PlainTime.NANO_OF_SECOND) {
                return plainTime.plus(MathUtils.safeSubtract(i, plainTime.nano), ClockUnit.NANOS);
            }
            if (this.element == PlainTime.MILLI_OF_DAY) {
                int iFloorModulo = MathUtils.floorModulo(i, 86400000);
                int i2 = plainTime.nano % 1000000;
                if (iFloorModulo == 0 && i2 == 0) {
                    return i > 0 ? PlainTime.MAX : PlainTime.MIN;
                }
                return PlainTime.createFromMillis(iFloorModulo, i2);
            }
            if (this.element == PlainTime.MINUTE_OF_DAY) {
                int iFloorModulo2 = MathUtils.floorModulo(i, 1440);
                if (iFloorModulo2 == 0 && plainTime.isFullMinute()) {
                    return i > 0 ? PlainTime.MAX : PlainTime.MIN;
                }
                return withValue(plainTime, Integer.valueOf(iFloorModulo2), false);
            }
            if (this.element == PlainTime.SECOND_OF_DAY) {
                int iFloorModulo3 = MathUtils.floorModulo(i, 86400);
                if (iFloorModulo3 == 0 && plainTime.nano == 0) {
                    return i > 0 ? PlainTime.MAX : PlainTime.MIN;
                }
                return withValue(plainTime, Integer.valueOf(iFloorModulo3), false);
            }
            throw new UnsupportedOperationException(this.element.name());
        }

        private static boolean isAM(PlainTime plainTime) {
            return plainTime.hour < 12 || plainTime.hour == 24;
        }
    }

    private static class LongElementRule implements ElementRule<PlainTime, Long> {
        private final ChronoElement<Long> element;
        private final long max;
        private final long min;

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(PlainTime plainTime) {
            return null;
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(PlainTime plainTime) {
            return null;
        }

        LongElementRule(ChronoElement<Long> chronoElement, long j, long j2) {
            this.element = chronoElement;
            this.min = j;
            this.max = j2;
        }

        @Override // net.time4j.engine.ElementRule
        public Long getValue(PlainTime plainTime) {
            return Long.valueOf(this.element == PlainTime.MICRO_OF_DAY ? plainTime.getNanoOfDay() / 1000 : plainTime.getNanoOfDay());
        }

        @Override // net.time4j.engine.ElementRule
        public PlainTime withValue2(PlainTime plainTime, Long l, boolean z) {
            if (l == null) {
                throw new IllegalArgumentException("Missing element value.");
            }
            if (z) {
                return withValueInLenientMode(plainTime, l.longValue());
            }
            if (!isValid(plainTime, l)) {
                throw new IllegalArgumentException("Value out of range: " + l);
            }
            long jLongValue = l.longValue();
            return this.element == PlainTime.MICRO_OF_DAY ? PlainTime.createFromMicros(jLongValue, plainTime.nano % 1000) : PlainTime.createFromNanos(jLongValue);
        }

        @Override // net.time4j.engine.ElementRule
        public boolean isValid2(PlainTime plainTime, Long l) {
            if (l == null) {
                return false;
            }
            return (this.element == PlainTime.MICRO_OF_DAY && l.longValue() == this.max) ? plainTime.nano % 1000 == 0 : this.min <= l.longValue() && l.longValue() <= this.max;
        }

        @Override // net.time4j.engine.ElementRule
        public Long getMinimum(PlainTime plainTime) {
            return Long.valueOf(this.min);
        }

        @Override // net.time4j.engine.ElementRule
        public Long getMaximum(PlainTime plainTime) {
            if (this.element == PlainTime.MICRO_OF_DAY && plainTime.nano % 1000 != 0) {
                return Long.valueOf(this.max - 1);
            }
            return Long.valueOf(this.max);
        }

        private PlainTime withValueInLenientMode(PlainTime plainTime, long j) {
            if (this.element == PlainTime.MICRO_OF_DAY) {
                long jFloorMod = PlainTime.floorMod(j, 86400000000L);
                int i = plainTime.nano % 1000;
                if (jFloorMod != 0 || i != 0 || j <= 0) {
                    return PlainTime.createFromMicros(jFloorMod, i);
                }
                return PlainTime.MAX;
            }
            long jFloorMod2 = PlainTime.floorMod(j, 86400000000000L);
            if (jFloorMod2 != 0 || j <= 0) {
                return PlainTime.createFromNanos(jFloorMod2);
            }
            return PlainTime.MAX;
        }
    }

    private static class BigDecimalElementRule implements ElementRule<PlainTime, BigDecimal> {
        private final ChronoElement<BigDecimal> element;
        private final BigDecimal max;

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(PlainTime plainTime) {
            return null;
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(PlainTime plainTime) {
            return null;
        }

        BigDecimalElementRule(ChronoElement<BigDecimal> chronoElement, BigDecimal bigDecimal) {
            this.element = chronoElement;
            this.max = bigDecimal;
        }

        @Override // net.time4j.engine.ElementRule
        public BigDecimal getValue(PlainTime plainTime) {
            BigDecimal bigDecimalAdd;
            if (this.element == PlainTime.DECIMAL_HOUR) {
                if (!plainTime.equals(PlainTime.MIN)) {
                    if (plainTime.hour == 24) {
                        return PlainTime.DECIMAL_24_0;
                    }
                    bigDecimalAdd = BigDecimal.valueOf(plainTime.hour).add(div(BigDecimal.valueOf(plainTime.minute), PlainTime.DECIMAL_60)).add(div(BigDecimal.valueOf(plainTime.second), PlainTime.DECIMAL_3600)).add(div(BigDecimal.valueOf(plainTime.nano), PlainTime.DECIMAL_3600.multiply(PlainTime.DECIMAL_MRD)));
                } else {
                    return BigDecimal.ZERO;
                }
            } else if (this.element == PlainTime.DECIMAL_MINUTE) {
                if (plainTime.isFullHour()) {
                    return BigDecimal.ZERO;
                }
                bigDecimalAdd = BigDecimal.valueOf(plainTime.minute).add(div(BigDecimal.valueOf(plainTime.second), PlainTime.DECIMAL_60)).add(div(BigDecimal.valueOf(plainTime.nano), PlainTime.DECIMAL_60.multiply(PlainTime.DECIMAL_MRD)));
            } else if (this.element == PlainTime.DECIMAL_SECOND) {
                if (!plainTime.isFullMinute()) {
                    bigDecimalAdd = BigDecimal.valueOf(plainTime.second).add(div(BigDecimal.valueOf(plainTime.nano), PlainTime.DECIMAL_MRD));
                } else {
                    return BigDecimal.ZERO;
                }
            } else {
                throw new UnsupportedOperationException(this.element.name());
            }
            return bigDecimalAdd.setScale(15, RoundingMode.FLOOR).stripTrailingZeros();
        }

        @Override // net.time4j.engine.ElementRule
        public PlainTime withValue2(PlainTime plainTime, BigDecimal bigDecimal, boolean z) {
            int iFloorModulo;
            int iFloorModulo2;
            long jLongValueExact;
            int iIntValue;
            int nano;
            int iFloorModulo3;
            if (bigDecimal == null) {
                throw new IllegalArgumentException("Missing element value.");
            }
            if (this.element == PlainTime.DECIMAL_HOUR) {
                BigDecimal scale = bigDecimal.setScale(0, RoundingMode.FLOOR);
                BigDecimal bigDecimalMultiply = bigDecimal.subtract(scale).multiply(PlainTime.DECIMAL_60);
                BigDecimal scale2 = bigDecimalMultiply.setScale(0, RoundingMode.FLOOR);
                BigDecimal bigDecimalMultiply2 = bigDecimalMultiply.subtract(scale2).multiply(PlainTime.DECIMAL_60);
                BigDecimal scale3 = bigDecimalMultiply2.setScale(0, RoundingMode.FLOOR);
                jLongValueExact = scale.longValueExact();
                iFloorModulo = scale2.intValue();
                iIntValue = scale3.intValue();
                nano = toNano(bigDecimalMultiply2.subtract(scale3));
            } else if (this.element == PlainTime.DECIMAL_MINUTE) {
                BigDecimal scale4 = bigDecimal.setScale(0, RoundingMode.FLOOR);
                BigDecimal bigDecimalMultiply3 = bigDecimal.subtract(scale4).multiply(PlainTime.DECIMAL_60);
                BigDecimal scale5 = bigDecimalMultiply3.setScale(0, RoundingMode.FLOOR);
                int iIntValue2 = scale5.intValue();
                int nano2 = toNano(bigDecimalMultiply3.subtract(scale5));
                long jLongValueExact2 = scale4.longValueExact();
                long jFloorDivide = plainTime.hour;
                if (!z) {
                    PlainTime.checkMinute(jLongValueExact2);
                    iFloorModulo = (int) jLongValueExact2;
                } else {
                    jFloorDivide += MathUtils.floorDivide(jLongValueExact2, 60);
                    iFloorModulo = MathUtils.floorModulo(jLongValueExact2, 60);
                }
                nano = nano2;
                iIntValue = iIntValue2;
                jLongValueExact = jFloorDivide;
            } else if (this.element == PlainTime.DECIMAL_SECOND) {
                BigDecimal scale6 = bigDecimal.setScale(0, RoundingMode.FLOOR);
                int nano3 = toNano(bigDecimal.subtract(scale6));
                long jLongValueExact3 = scale6.longValueExact();
                long jFloorDivide2 = plainTime.hour;
                iFloorModulo = plainTime.minute;
                if (!z) {
                    PlainTime.checkSecond(jLongValueExact3);
                    iFloorModulo2 = (int) jLongValueExact3;
                } else {
                    iFloorModulo2 = MathUtils.floorModulo(jLongValueExact3, 60);
                    long jFloorDivide3 = iFloorModulo + MathUtils.floorDivide(jLongValueExact3, 60);
                    jFloorDivide2 += MathUtils.floorDivide(jFloorDivide3, 60);
                    iFloorModulo = MathUtils.floorModulo(jFloorDivide3, 60);
                }
                jLongValueExact = jFloorDivide2;
                iIntValue = iFloorModulo2;
                nano = nano3;
            } else {
                throw new UnsupportedOperationException(this.element.name());
            }
            if (z) {
                iFloorModulo3 = MathUtils.floorModulo(jLongValueExact, 24);
                if (jLongValueExact > 0 && (iFloorModulo3 | iFloorModulo | iIntValue | nano) == 0) {
                    return PlainTime.MAX;
                }
            } else {
                if (jLongValueExact < 0 || jLongValueExact > 24) {
                    throw new IllegalArgumentException("Value out of range: " + bigDecimal);
                }
                iFloorModulo3 = (int) jLongValueExact;
            }
            return PlainTime.of(iFloorModulo3, iFloorModulo, iIntValue, nano);
        }

        @Override // net.time4j.engine.ElementRule
        public boolean isValid2(PlainTime plainTime, BigDecimal bigDecimal) {
            if (bigDecimal == null) {
                return false;
            }
            return (plainTime.hour == 24 && (this.element == PlainTime.DECIMAL_MINUTE || this.element == PlainTime.DECIMAL_SECOND)) ? BigDecimal.ZERO.compareTo(bigDecimal) == 0 : BigDecimal.ZERO.compareTo(bigDecimal) <= 0 && this.max.compareTo(bigDecimal) >= 0;
        }

        @Override // net.time4j.engine.ElementRule
        public BigDecimal getMinimum(PlainTime plainTime) {
            return BigDecimal.ZERO;
        }

        @Override // net.time4j.engine.ElementRule
        public BigDecimal getMaximum(PlainTime plainTime) {
            return (plainTime.hour == 24 && (this.element == PlainTime.DECIMAL_MINUTE || this.element == PlainTime.DECIMAL_SECOND)) ? BigDecimal.ZERO : this.max;
        }

        private static BigDecimal div(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
            return bigDecimal.divide(bigDecimal2, 16, RoundingMode.FLOOR);
        }

        private static int toNano(BigDecimal bigDecimal) {
            return Math.min(999999999, bigDecimal.movePointRight(9).setScale(0, RoundingMode.HALF_UP).intValue());
        }
    }

    private static class Merger implements ChronoMerger<PlainTime> {
        @Override // net.time4j.engine.ChronoMerger
        public ChronoDisplay preformat(PlainTime plainTime, AttributeQuery attributeQuery) {
            return plainTime;
        }

        @Override // net.time4j.engine.ChronoMerger
        public Chronology<?> preparser() {
            return null;
        }

        private Merger() {
        }

        /* synthetic */ Merger(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // net.time4j.engine.ChronoMerger
        public /* bridge */ /* synthetic */ PlainTime createFrom(TimeSource timeSource, AttributeQuery attributeQuery) {
            return createFrom2((TimeSource<?>) timeSource, attributeQuery);
        }

        @Override // net.time4j.engine.ChronoMerger
        public /* bridge */ /* synthetic */ PlainTime createFrom(ChronoEntity chronoEntity, AttributeQuery attributeQuery, boolean z, boolean z2) {
            return createFrom2((ChronoEntity<?>) chronoEntity, attributeQuery, z, z2);
        }

        @Override // net.time4j.engine.ChronoMerger
        public String getFormatPattern(DisplayStyle displayStyle, Locale locale) {
            return CalendarText.patternForTime(DisplayMode.ofStyle(displayStyle.getStyleValue()), locale);
        }

        @Override // net.time4j.engine.ChronoMerger
        /* renamed from: createFrom, reason: avoid collision after fix types in other method */
        public PlainTime createFrom2(TimeSource<?> timeSource, AttributeQuery attributeQuery) {
            Timezone timezoneOfSystem;
            if (attributeQuery.contains(Attributes.TIMEZONE_ID)) {
                timezoneOfSystem = Timezone.of((TZID) attributeQuery.get(Attributes.TIMEZONE_ID));
            } else {
                if (!((Leniency) attributeQuery.get(Attributes.LENIENCY, Leniency.SMART)).isLax()) {
                    return null;
                }
                timezoneOfSystem = Timezone.ofSystem();
            }
            UnixTime unixTimeCurrentTime = timeSource.currentTime();
            return PlainTime.from(unixTimeCurrentTime, timezoneOfSystem.getOffset(unixTimeCurrentTime));
        }

        @Override // net.time4j.engine.ChronoMerger
        /* renamed from: createFrom, reason: avoid collision after fix types in other method */
        public PlainTime createFrom2(ChronoEntity<?> chronoEntity, AttributeQuery attributeQuery, boolean z, boolean z2) {
            if (chronoEntity instanceof UnixTime) {
                return ((PlainTimestamp) PlainTimestamp.axis().createFrom(chronoEntity, attributeQuery, z, z2)).getWallTime();
            }
            if (chronoEntity.contains(PlainTime.WALL_TIME)) {
                return (PlainTime) chronoEntity.get(PlainTime.WALL_TIME);
            }
            if (chronoEntity.contains(PlainTime.DECIMAL_HOUR)) {
                return PlainTime.of((BigDecimal) chronoEntity.get(PlainTime.DECIMAL_HOUR));
            }
            int hour = chronoEntity.getInt(PlainTime.HOUR_FROM_0_TO_24);
            if (hour == Integer.MIN_VALUE) {
                hour = readHour(chronoEntity);
                if (hour == Integer.MIN_VALUE) {
                    return readSpecialCases(chronoEntity);
                }
                if (hour == -1 || hour == -2) {
                    if (!z) {
                        flagValidationError(chronoEntity, "Clock hour cannot be zero.");
                        return null;
                    }
                    hour = hour == -1 ? 0 : 12;
                } else if (hour == 24 && !z) {
                    flagValidationError(chronoEntity, "Time 24:00 not allowed, use lax mode or element HOUR_FROM_0_TO_24 instead.");
                    return null;
                }
            }
            if (chronoEntity.contains(PlainTime.DECIMAL_MINUTE)) {
                return (PlainTime) PlainTime.M_DECIMAL_RULE.withValue2(PlainTime.of(hour), chronoEntity.get(PlainTime.DECIMAL_MINUTE), false);
            }
            int i = chronoEntity.getInt(PlainTime.MINUTE_OF_HOUR);
            if (i == Integer.MIN_VALUE) {
                i = 0;
            }
            if (chronoEntity.contains(PlainTime.DECIMAL_SECOND)) {
                return (PlainTime) PlainTime.S_DECIMAL_RULE.withValue2(PlainTime.of(hour, i), chronoEntity.get(PlainTime.DECIMAL_SECOND), false);
            }
            int i2 = chronoEntity.getInt(PlainTime.SECOND_OF_MINUTE);
            if (i2 == Integer.MIN_VALUE) {
                i2 = 0;
            }
            int iSafeMultiply = chronoEntity.getInt(PlainTime.NANO_OF_SECOND);
            if (iSafeMultiply == Integer.MIN_VALUE) {
                int i3 = chronoEntity.getInt(PlainTime.MICRO_OF_SECOND);
                if (i3 == Integer.MIN_VALUE) {
                    int i4 = chronoEntity.getInt(PlainTime.MILLI_OF_SECOND);
                    iSafeMultiply = i4 == Integer.MIN_VALUE ? 0 : MathUtils.safeMultiply(i4, 1000000);
                } else {
                    iSafeMultiply = MathUtils.safeMultiply(i3, 1000);
                }
            }
            if (z) {
                long jSafeAdd = MathUtils.safeAdd(MathUtils.safeMultiply(MathUtils.safeAdd(MathUtils.safeAdd(MathUtils.safeMultiply(hour, 3600L), MathUtils.safeMultiply(i, 60L)), i2), C.NANOS_PER_SECOND), iSafeMultiply);
                long jFloorMod = PlainTime.floorMod(jSafeAdd, 86400000000000L);
                long jFloorDiv = PlainTime.floorDiv(jSafeAdd, 86400000000000L);
                if (jFloorDiv != 0 && chronoEntity.isValid(LongElement.DAY_OVERFLOW, jFloorDiv)) {
                    chronoEntity.with(LongElement.DAY_OVERFLOW, jFloorDiv);
                }
                if (jFloorMod != 0 || jFloorDiv <= 0) {
                    return PlainTime.createFromNanos(jFloorMod);
                }
                return PlainTime.MAX;
            }
            if ((hour >= 0 && i >= 0 && i2 >= 0 && iSafeMultiply >= 0 && hour == 24 && (i | i2 | iSafeMultiply) == 0) || (hour < 24 && i <= 59 && i2 <= 59 && iSafeMultiply <= 1000000000)) {
                return PlainTime.of(hour, i, i2, iSafeMultiply, false);
            }
            flagValidationError(chronoEntity, "Time component out of range.");
            return null;
        }

        private static int readHour(ChronoEntity<?> chronoEntity) {
            int i = chronoEntity.getInt(PlainTime.DIGITAL_HOUR_OF_DAY);
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            int i2 = chronoEntity.getInt(PlainTime.CLOCK_HOUR_OF_DAY);
            if (i2 == 0) {
                return -1;
            }
            if (i2 == 24) {
                return 0;
            }
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (chronoEntity.contains(PlainTime.AM_PM_OF_DAY)) {
                Meridiem meridiem = (Meridiem) chronoEntity.get(PlainTime.AM_PM_OF_DAY);
                int i3 = chronoEntity.getInt(PlainTime.CLOCK_HOUR_OF_AMPM);
                if (i3 != Integer.MIN_VALUE) {
                    if (i3 == 0) {
                        return meridiem == Meridiem.AM ? -1 : -2;
                    }
                    int i4 = i3 != 12 ? i3 : 0;
                    return meridiem == Meridiem.AM ? i4 : i4 + 12;
                }
                int i5 = chronoEntity.getInt(PlainTime.DIGITAL_HOUR_OF_AMPM);
                if (i5 != Integer.MIN_VALUE) {
                    return meridiem == Meridiem.AM ? i5 : i5 + 12;
                }
            }
            return Integer.MIN_VALUE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private static PlainTime readSpecialCases(ChronoEntity<?> chronoEntity) {
            int iIntValue;
            int iIntValue2;
            if (chronoEntity.contains(PlainTime.NANO_OF_DAY)) {
                long jLongValue = ((Long) chronoEntity.get(PlainTime.NANO_OF_DAY)).longValue();
                if (jLongValue >= 0 && jLongValue <= 86400000000000L) {
                    return PlainTime.createFromNanos(jLongValue);
                }
                flagValidationError(chronoEntity, "NANO_OF_DAY out of range: " + jLongValue);
                return null;
            }
            if (chronoEntity.contains(PlainTime.MICRO_OF_DAY)) {
                return PlainTime.createFromMicros(((Long) chronoEntity.get(PlainTime.MICRO_OF_DAY)).longValue(), chronoEntity.contains(PlainTime.NANO_OF_SECOND) ? ((Integer) chronoEntity.get(PlainTime.NANO_OF_SECOND)).intValue() % 1000 : 0);
            }
            if (chronoEntity.contains(PlainTime.MILLI_OF_DAY)) {
                if (chronoEntity.contains(PlainTime.NANO_OF_SECOND)) {
                    int iIntValue3 = ((Integer) chronoEntity.get(PlainTime.NANO_OF_SECOND)).intValue();
                    if (iIntValue3 < 0 || iIntValue3 >= 1000000000) {
                        flagValidationError(chronoEntity, "NANO_OF_SECOND out of range: " + iIntValue3);
                        return null;
                    }
                    i = iIntValue3 % 1000000;
                } else if (chronoEntity.contains(PlainTime.MICRO_OF_SECOND)) {
                    int iIntValue4 = ((Integer) chronoEntity.get(PlainTime.MICRO_OF_SECOND)).intValue();
                    if (iIntValue4 < 0 || iIntValue4 >= 1000000) {
                        flagValidationError(chronoEntity, "MICRO_OF_SECOND out of range: " + iIntValue4);
                        return null;
                    }
                    i = iIntValue4 % 1000;
                }
                int iIntValue5 = ((Integer) chronoEntity.get(PlainTime.MILLI_OF_DAY)).intValue();
                if (iIntValue5 >= 0 && iIntValue5 <= 86400000) {
                    return PlainTime.createFromMillis(iIntValue5, i);
                }
                flagValidationError(chronoEntity, "MILLI_OF_DAY out of range: " + iIntValue5);
                return null;
            }
            if (chronoEntity.contains(PlainTime.SECOND_OF_DAY)) {
                if (chronoEntity.contains(PlainTime.NANO_OF_SECOND)) {
                    iIntValue2 = ((Integer) chronoEntity.get(PlainTime.NANO_OF_SECOND)).intValue();
                } else if (chronoEntity.contains(PlainTime.MICRO_OF_SECOND)) {
                    iIntValue2 = ((Integer) chronoEntity.get(PlainTime.MICRO_OF_SECOND)).intValue() * 1000;
                } else {
                    iIntValue2 = chronoEntity.contains(PlainTime.MILLI_OF_SECOND) ? ((Integer) chronoEntity.get(PlainTime.MILLI_OF_SECOND)).intValue() * 1000000 : 0;
                }
                return (PlainTime) PlainTime.of(0, 0, 0, iIntValue2).with(PlainTime.SECOND_OF_DAY, (ProportionalElement<Integer, PlainTime>) chronoEntity.get(PlainTime.SECOND_OF_DAY));
            }
            if (!chronoEntity.contains(PlainTime.MINUTE_OF_DAY)) {
                return null;
            }
            if (chronoEntity.contains(PlainTime.NANO_OF_SECOND)) {
                iIntValue = ((Integer) chronoEntity.get(PlainTime.NANO_OF_SECOND)).intValue();
            } else if (chronoEntity.contains(PlainTime.MICRO_OF_SECOND)) {
                iIntValue = ((Integer) chronoEntity.get(PlainTime.MICRO_OF_SECOND)).intValue() * 1000;
            } else {
                iIntValue = chronoEntity.contains(PlainTime.MILLI_OF_SECOND) ? ((Integer) chronoEntity.get(PlainTime.MILLI_OF_SECOND)).intValue() * 1000000 : 0;
            }
            return (PlainTime) PlainTime.of(0, 0, chronoEntity.contains(PlainTime.SECOND_OF_MINUTE) ? ((Integer) chronoEntity.get(PlainTime.SECOND_OF_MINUTE)).intValue() : 0, iIntValue).with(PlainTime.MINUTE_OF_DAY, (ProportionalElement<Integer, PlainTime>) chronoEntity.get(PlainTime.MINUTE_OF_DAY));
        }

        private static void flagValidationError(ChronoEntity<?> chronoEntity, String str) {
            if (chronoEntity.isValid((ChronoElement<ValidationElement>) ValidationElement.ERROR_MESSAGE, (ValidationElement) str)) {
                chronoEntity.with((ChronoElement<ValidationElement>) ValidationElement.ERROR_MESSAGE, (ValidationElement) str);
            }
        }

        @Override // net.time4j.engine.ChronoMerger
        public StartOfDay getDefaultStartOfDay() {
            return StartOfDay.MIDNIGHT;
        }

        @Override // net.time4j.engine.ChronoMerger
        public int getDefaultPivotYear() {
            return PlainDate.axis().getDefaultPivotYear();
        }
    }
}
