package net.time4j;

import androidx.exifinterface.media.ExifInterface;
import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import com.google.common.base.Ascii;
import com.nimbusds.jose.jwk.JWKParameterNames;
import com.onfido.android.sdk.capture.ui.camera.CapturePresenter;
import com.singular.sdk.internal.SingularParamsBase;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.io.encoding.Base64;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.CalendarUnit;
import net.time4j.base.GregorianDate;
import net.time4j.base.GregorianMath;
import net.time4j.base.MathUtils;
import net.time4j.base.ResourceLoader;
import net.time4j.base.TimeSource;
import net.time4j.base.UnixTime;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.BridgeChronology;
import net.time4j.engine.CalendarDate;
import net.time4j.engine.CalendarEra;
import net.time4j.engine.CalendarSystem;
import net.time4j.engine.Calendrical;
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
import net.time4j.engine.EpochDays;
import net.time4j.engine.FormattableElement;
import net.time4j.engine.IntElementRule;
import net.time4j.engine.Normalizer;
import net.time4j.engine.StartOfDay;
import net.time4j.engine.TimeAxis;
import net.time4j.engine.TimeMetric;
import net.time4j.engine.TimeSpan;
import net.time4j.engine.ValidationElement;
import net.time4j.format.Attributes;
import net.time4j.format.CalendarText;
import net.time4j.format.CalendarType;
import net.time4j.format.DisplayMode;
import net.time4j.format.Leniency;
import net.time4j.format.LocalizedPatternSupport;
import net.time4j.format.TemporalFormatter;
import net.time4j.scale.TimeScale;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;
import net.time4j.tz.TransitionHistory;
import net.time4j.tz.ZonalOffset;
import net.time4j.tz.ZonalTransition;

@CalendarType(CalendarText.ISO_CALENDAR_TYPE)
/* loaded from: classes4.dex */
public final class PlainDate extends Calendrical<IsoDateUnit, PlainDate> implements GregorianDate, Normalizer<CalendarUnit>, LocalizedPatternSupport {
    static final ChronoElement<PlainDate> CALENDAR_DATE;
    public static final CalendarDateElement COMPONENT;

    @FormattableElement(format = "d")
    public static final ProportionalElement<Integer, PlainDate> DAY_OF_MONTH;
    public static final ProportionalElement<Integer, PlainDate> DAY_OF_QUARTER;

    @FormattableElement(format = ExifInterface.LONGITUDE_EAST)
    public static final NavigableElement<Weekday> DAY_OF_WEEK;

    @FormattableElement(format = "D")
    public static final ProportionalElement<Integer, PlainDate> DAY_OF_YEAR;
    private static final Map<String, Object> ELEMENTS;
    private static final TimeAxis<IsoDateUnit, PlainDate> ENGINE;

    @FormattableElement(format = "M")
    public static final ProportionalElement<Integer, PlainDate> MONTH_AS_NUMBER;

    @FormattableElement(alt = "L", format = "M")
    public static final NavigableElement<Month> MONTH_OF_YEAR;

    @FormattableElement(alt = JWKParameterNames.RSA_SECOND_PRIME_FACTOR, format = "Q")
    public static final NavigableElement<Quarter> QUARTER_OF_YEAR;
    private static final CalendarSystem<PlainDate> TRANSFORMER;

    @FormattableElement(format = "F")
    public static final OrdinalWeekdayElement WEEKDAY_IN_MONTH;
    private static final int WIM_INDEX = 19;

    @FormattableElement(format = SingularParamsBase.Constants.IDENTIFIER_UNIQUE_ID_KEY)
    public static final AdjustableElement<Integer, PlainDate> YEAR;

    @FormattableElement(format = "Y")
    public static final AdjustableElement<Integer, PlainDate> YEAR_OF_WEEKDATE;
    private static final long serialVersionUID = -6698431452072325688L;
    private final transient byte dayOfMonth;
    private final transient byte month;
    private final transient int year;
    static final PlainDate MIN = new PlainDate(-999999999, 1, 1);
    static final PlainDate MAX = new PlainDate(999999999, 12, 31);
    static final Integer MIN_YEAR = -999999999;
    static final Integer MAX_YEAR = 999999999;
    private static final Integer VALUE_1 = 1;
    private static final Integer VALUE_12 = 12;
    private static final Integer STD_YEAR_LEN = 365;
    private static final Integer LEAP_YEAR_LEN = 366;
    private static final int[] DAY_OF_YEAR_PER_MONTH = {31, 59, 90, 120, 151, 181, Mp4VideoDirectory.TAG_OPCOLOR, 243, 273, 304, 334, 365};
    private static final int[] DAY_OF_LEAP_YEAR_PER_MONTH = {31, 60, 91, PanasonicMakernoteDirectory.TAG_INTELLIGENT_D_RANGE, 152, 182, Mp4VideoDirectory.TAG_COLOR_TABLE, 244, 274, 305, 335, 366};

    public static TimeAxis<IsoDateUnit, PlainDate> axis() {
        return ENGINE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.time4j.engine.TimePoint, net.time4j.engine.ChronoEntity
    public TimeAxis<IsoDateUnit, PlainDate> getChronology() {
        return ENGINE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.time4j.engine.ChronoEntity
    public PlainDate getContext() {
        return this;
    }

    @Override // net.time4j.base.GregorianDate
    public int getDayOfMonth() {
        return this.dayOfMonth;
    }

    long getEpochMonths() {
        return (((this.year - 1970) * 12) + this.month) - 1;
    }

    @Override // net.time4j.base.GregorianDate
    public int getMonth() {
        return this.month;
    }

    @Override // net.time4j.base.GregorianDate
    public int getYear() {
        return this.year;
    }

    @Override // net.time4j.engine.Calendrical, net.time4j.engine.TimePoint
    public int hashCode() {
        int i = this.year;
        return (((i << 11) + (this.month << 6)) + this.dayOfMonth) ^ (i & (-2048));
    }

    static {
        DateElement dateElement = DateElement.INSTANCE;
        CALENDAR_DATE = dateElement;
        COMPONENT = DateElement.INSTANCE;
        IntegerDateElement integerDateElementCreate = IntegerDateElement.create("YEAR", 14, -999999999, 999999999, AbstractJsonLexerKt.UNICODE_ESC);
        YEAR = integerDateElementCreate;
        YOWElement yOWElement = YOWElement.INSTANCE;
        YEAR_OF_WEEKDATE = yOWElement;
        EnumElement enumElement = new EnumElement("QUARTER_OF_YEAR", Quarter.class, Quarter.Q1, Quarter.Q4, 103, 'Q');
        QUARTER_OF_YEAR = enumElement;
        EnumElement enumElement2 = new EnumElement("MONTH_OF_YEAR", Month.class, Month.JANUARY, Month.DECEMBER, 101, 'M');
        MONTH_OF_YEAR = enumElement2;
        IntegerDateElement integerDateElementCreate2 = IntegerDateElement.create("MONTH_AS_NUMBER", 15, 1, 12, 'M');
        MONTH_AS_NUMBER = integerDateElementCreate2;
        IntegerDateElement integerDateElementCreate3 = IntegerDateElement.create("DAY_OF_MONTH", 16, 1, 31, 'd');
        DAY_OF_MONTH = integerDateElementCreate3;
        EnumElement enumElement3 = new EnumElement("DAY_OF_WEEK", Weekday.class, Weekday.MONDAY, Weekday.SUNDAY, 102, 'E');
        DAY_OF_WEEK = enumElement3;
        IntegerDateElement integerDateElementCreate4 = IntegerDateElement.create("DAY_OF_YEAR", 17, 1, 365, 'D');
        DAY_OF_YEAR = integerDateElementCreate4;
        IntegerDateElement integerDateElementCreate5 = IntegerDateElement.create("DAY_OF_QUARTER", 18, 1, 92, (char) 0);
        DAY_OF_QUARTER = integerDateElementCreate5;
        WeekdayInMonthElement weekdayInMonthElement = WeekdayInMonthElement.INSTANCE;
        WEEKDAY_IN_MONTH = weekdayInMonthElement;
        HashMap map = new HashMap();
        fill(map, dateElement);
        fill(map, integerDateElementCreate);
        fill(map, yOWElement);
        fill(map, enumElement);
        fill(map, enumElement2);
        fill(map, integerDateElementCreate2);
        fill(map, integerDateElementCreate3);
        fill(map, enumElement3);
        fill(map, integerDateElementCreate4);
        fill(map, integerDateElementCreate5);
        fill(map, weekdayInMonthElement);
        ELEMENTS = Collections.unmodifiableMap(map);
        AnonymousClass1 anonymousClass1 = null;
        Transformer transformer = new Transformer(anonymousClass1);
        TRANSFORMER = transformer;
        TimeAxis.Builder builderAppendElement = TimeAxis.Builder.setUp(IsoDateUnit.class, PlainDate.class, new Merger(anonymousClass1), transformer).appendElement(dateElement, new DateElementRule(anonymousClass1), CalendarUnit.DAYS).appendElement(integerDateElementCreate, new IntegerElementRule(integerDateElementCreate), CalendarUnit.YEARS).appendElement(yOWElement, YOWElement.elementRule(PlainDate.class), Weekcycle.YEARS).appendElement(enumElement, EnumElementRule.of(enumElement), CalendarUnit.QUARTERS).appendElement(enumElement2, EnumElementRule.of(enumElement2), CalendarUnit.MONTHS).appendElement(integerDateElementCreate2, new IntegerElementRule(integerDateElementCreate2), CalendarUnit.MONTHS).appendElement(integerDateElementCreate3, new IntegerElementRule(integerDateElementCreate3), CalendarUnit.DAYS).appendElement(enumElement3, EnumElementRule.of(enumElement3), CalendarUnit.DAYS).appendElement(integerDateElementCreate4, new IntegerElementRule(integerDateElementCreate4), CalendarUnit.DAYS).appendElement(integerDateElementCreate5, new IntegerElementRule(integerDateElementCreate5), CalendarUnit.DAYS).appendElement(weekdayInMonthElement, new IntegerElementRule(19, weekdayInMonthElement), CalendarUnit.WEEKS);
        registerUnits(builderAppendElement);
        registerExtensions(builderAppendElement);
        ENGINE = builderAppendElement.build();
    }

    private PlainDate(int i, int i2, int i3) {
        this.year = i;
        this.month = (byte) i2;
        this.dayOfMonth = (byte) i3;
    }

    public static PlainDate of(int i, int i2, int i3) {
        return of(i, i2, i3, true);
    }

    public static PlainDate of(int i, Month month, int i2) {
        return of(i, month.getValue(), i2, true);
    }

    public static PlainDate of(int i, int i2) {
        if (i2 < 1) {
            throw new IllegalArgumentException("Day of year out of range: " + i2);
        }
        if (i2 <= 31) {
            return of(i, 1, i2);
        }
        int[] iArr = GregorianMath.isLeapYear(i) ? DAY_OF_LEAP_YEAR_PER_MONTH : DAY_OF_YEAR_PER_MONTH;
        for (int i3 = i2 > iArr[6] ? 7 : 1; i3 < 12; i3++) {
            if (i2 <= iArr[i3]) {
                return of(i, i3 + 1, i2 - iArr[i3 - 1], false);
            }
        }
        throw new IllegalArgumentException("Day of year out of range: " + i2);
    }

    public static PlainDate of(int i, int i2, Weekday weekday) {
        return of(i, i2, weekday, true);
    }

    public static PlainDate of(long j, EpochDays epochDays) {
        return TRANSFORMER.transform(EpochDays.UTC.transform(j, epochDays));
    }

    public static PlainDate nowInSystemTime() {
        return ZonalClock.ofSystem().today();
    }

    public static PlainDate from(GregorianDate gregorianDate) {
        if (gregorianDate instanceof PlainDate) {
            return (PlainDate) gregorianDate;
        }
        return of(gregorianDate.getYear(), gregorianDate.getMonth(), gregorianDate.getDayOfMonth());
    }

    public PlainTimestamp atStartOfDay() {
        return at(PlainTime.MIN);
    }

    public PlainTimestamp atStartOfDay(TZID tzid) {
        return atStartOfDay(Timezone.of(tzid).getHistory());
    }

    public PlainTimestamp atStartOfDay(String str) {
        return atStartOfDay(Timezone.of(str).getHistory());
    }

    public Moment atFirstMoment(TZID tzid) {
        return atFirstMoment(Timezone.of(tzid));
    }

    public Moment atFirstMoment(String str) {
        return atFirstMoment(Timezone.of(str));
    }

    public PlainTimestamp at(PlainTime plainTime) {
        return PlainTimestamp.of(this, plainTime);
    }

    public PlainTimestamp atTime(int i, int i2) {
        return at(PlainTime.of(i, i2));
    }

    public PlainTimestamp atTime(int i, int i2, int i3) {
        return at(PlainTime.of(i, i2, i3));
    }

    public Weekday getDayOfWeek() {
        return Weekday.valueOf(GregorianMath.getDayOfWeek(this.year, this.month, this.dayOfMonth));
    }

    public int getDayOfYear() {
        byte b = this.month;
        if (b == 1) {
            return this.dayOfMonth;
        }
        if (b != 2) {
            return DAY_OF_YEAR_PER_MONTH[b - 2] + this.dayOfMonth + (GregorianMath.isLeapYear(this.year) ? 1 : 0);
        }
        return this.dayOfMonth + Ascii.US;
    }

    public int lengthOfMonth() {
        return GregorianMath.getLengthOfMonth(this.year, this.month);
    }

    public int lengthOfYear() {
        return isLeapYear() ? 366 : 365;
    }

    public boolean isLeapYear() {
        return GregorianMath.isLeapYear(this.year);
    }

    public boolean isWeekend(Locale locale) {
        return matches(Weekmodel.of(locale).weekend());
    }

    public static boolean isValid(int i, int i2, int i3) {
        return GregorianMath.isValid(i, i2, i3);
    }

    public String print(TemporalFormatter<PlainDate> temporalFormatter) {
        return temporalFormatter.print(this);
    }

    public static PlainDate parse(String str, TemporalFormatter<PlainDate> temporalFormatter) {
        try {
            return temporalFormatter.parse(str);
        } catch (ParseException e) {
            throw new ChronoException(e.getMessage(), e);
        }
    }

    @Override // net.time4j.engine.Calendrical, net.time4j.engine.TimePoint
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlainDate)) {
            return false;
        }
        PlainDate plainDate = (PlainDate) obj;
        return this.dayOfMonth == plainDate.dayOfMonth && this.month == plainDate.month && this.year == plainDate.year;
    }

    @Override // net.time4j.engine.TimePoint
    public String toString() {
        StringBuilder sb = new StringBuilder(32);
        formatYear(sb, this.year);
        format2Digits(sb, this.month);
        format2Digits(sb, this.dayOfMonth);
        return sb.toString();
    }

    @Override // net.time4j.engine.Normalizer
    /* renamed from: normalize */
    public TimeSpan<CalendarUnit> normalize2(TimeSpan<? extends CalendarUnit> timeSpan) {
        return (Duration) until((PlainDate) plus(timeSpan), (TimeMetric) Duration.inYearsMonthsDays());
    }

    public static <S> Chronology<S> axis(Converter<S, PlainDate> converter) {
        return new BridgeChronology(converter, ENGINE);
    }

    @Override // net.time4j.engine.Calendrical
    protected int compareByTime(CalendarDate calendarDate) {
        if (calendarDate instanceof PlainDate) {
            PlainDate plainDate = (PlainDate) calendarDate;
            int i = this.year - plainDate.year;
            if (i != 0) {
                return i;
            }
            int i2 = this.month - plainDate.month;
            return i2 == 0 ? this.dayOfMonth - plainDate.dayOfMonth : i2;
        }
        return super.compareByTime(calendarDate);
    }

    long getDaysSinceUTC() {
        return TRANSFORMER.transform((CalendarSystem<PlainDate>) this);
    }

    PlainDate withDaysSinceUTC(long j) {
        return TRANSFORMER.transform(j);
    }

    static PlainDate from(UnixTime unixTime, ZonalOffset zonalOffset) {
        long posixTime = unixTime.getPosixTime() + zonalOffset.getIntegralAmount();
        int nanosecond = unixTime.getNanosecond() + zonalOffset.getFractionalAmount();
        if (nanosecond < 0) {
            posixTime--;
        } else if (nanosecond >= 1000000000) {
            posixTime++;
        }
        long packedDate = GregorianMath.toPackedDate(EpochDays.MODIFIED_JULIAN_DATE.transform(MathUtils.floorDivide(posixTime, 86400), EpochDays.UNIX));
        return of(GregorianMath.readYear(packedDate), GregorianMath.readMonth(packedDate), GregorianMath.readDayOfMonth(packedDate));
    }

    static Object lookupElement(String str) {
        return ELEMENTS.get(str);
    }

    static PlainDate doAdd(CalendarUnit calendarUnit, PlainDate plainDate, long j, int i) {
        switch (AnonymousClass1.$SwitchMap$net$time4j$CalendarUnit[calendarUnit.ordinal()]) {
            case 1:
                return doAdd(CalendarUnit.MONTHS, plainDate, MathUtils.safeMultiply(j, 12000L), i);
            case 2:
                return doAdd(CalendarUnit.MONTHS, plainDate, MathUtils.safeMultiply(j, CapturePresenter.CONFIRMATION_VIEW_ANIM_DELAY), i);
            case 3:
                return doAdd(CalendarUnit.MONTHS, plainDate, MathUtils.safeMultiply(j, 120L), i);
            case 4:
                return doAdd(CalendarUnit.MONTHS, plainDate, MathUtils.safeMultiply(j, 12L), i);
            case 5:
                return doAdd(CalendarUnit.MONTHS, plainDate, MathUtils.safeMultiply(j, 3L), i);
            case 6:
                return fromEpochMonths(plainDate, MathUtils.safeAdd(plainDate.getEpochMonths(), j), plainDate.dayOfMonth, i);
            case 7:
                return doAdd(CalendarUnit.DAYS, plainDate, MathUtils.safeMultiply(j, 7L), i);
            case 8:
                return addDays(plainDate, j);
            default:
                throw new UnsupportedOperationException(calendarUnit.name());
        }
    }

    int getWeekOfYear() {
        return ((Integer) get(Weekmodel.ISO.weekOfYear())).intValue();
    }

    private PlainTimestamp atStartOfDay(TransitionHistory transitionHistory) {
        if (transitionHistory == null) {
            throw new UnsupportedOperationException("Timezone repository does not expose its transition history: " + Timezone.getProviderInfo());
        }
        ZonalTransition conflictTransition = transitionHistory.getConflictTransition(this, PlainTime.MIN);
        if (conflictTransition != null && conflictTransition.isGap()) {
            long posixTime = conflictTransition.getPosixTime() + conflictTransition.getTotalOffset();
            PlainDate plainDateOf = of(MathUtils.floorDivide(posixTime, 86400), EpochDays.UNIX);
            int iFloorModulo = MathUtils.floorModulo(posixTime, 86400);
            int i = iFloorModulo % 60;
            int i2 = iFloorModulo / 60;
            return PlainTimestamp.of(plainDateOf, PlainTime.of(i2 / 60, i2 % 60, i));
        }
        return at(PlainTime.MIN);
    }

    private Moment atFirstMoment(Timezone timezone) {
        TransitionHistory history = timezone.getHistory();
        if (history == null) {
            throw new UnsupportedOperationException("Timezone repository does not expose its transition history: " + Timezone.getProviderInfo());
        }
        ZonalTransition conflictTransition = history.getConflictTransition(this, PlainTime.MIN);
        if (conflictTransition != null && conflictTransition.isGap()) {
            return Moment.of(conflictTransition.getPosixTime(), TimeScale.POSIX);
        }
        return at(PlainTime.MIN).in(timezone);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getDayOfQuarter() {
        switch (this.month) {
            case 1:
            case 4:
            case 7:
            case 10:
                return this.dayOfMonth;
            case 2:
            case 8:
            case 11:
                return this.dayOfMonth + Ascii.US;
            case 3:
                return (GregorianMath.isLeapYear(this.year) ? (byte) 60 : (byte) 59) + this.dayOfMonth;
            case 5:
                return this.dayOfMonth + 30;
            case 6:
            case 12:
                return this.dayOfMonth + Base64.padSymbol;
            case 9:
                return this.dayOfMonth + 62;
            default:
                throw new AssertionError("Unknown month: " + ((int) this.month));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PlainDate withYear(int i) {
        if (this.year == i) {
            return this;
        }
        return of(i, this.month, Math.min(GregorianMath.getLengthOfMonth(i, this.month), (int) this.dayOfMonth));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PlainDate withMonth(int i) {
        if (this.month == i) {
            return this;
        }
        return of(this.year, i, Math.min(GregorianMath.getLengthOfMonth(this.year, i), (int) this.dayOfMonth));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PlainDate withDayOfMonth(int i) {
        return this.dayOfMonth == i ? this : of(this.year, this.month, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PlainDate withDayOfWeek(Weekday weekday) {
        return getDayOfWeek() == weekday ? this : TRANSFORMER.transform(MathUtils.safeAdd(getDaysSinceUTC(), weekday.getValue() - r0.getValue()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PlainDate withDayOfYear(int i) {
        return getDayOfYear() == i ? this : of(this.year, i);
    }

    private static PlainDate fromEpochMonths(PlainDate plainDate, long j, int i, int i2) {
        if (i2 == 5 && plainDate.dayOfMonth == plainDate.lengthOfMonth()) {
            i2 = 2;
        }
        int iSafeCast = MathUtils.safeCast(MathUtils.safeAdd(MathUtils.floorDivide(j, 12), 1970L));
        int iFloorModulo = MathUtils.floorModulo(j, 12) + 1;
        int lengthOfMonth = GregorianMath.getLengthOfMonth(iSafeCast, iFloorModulo);
        if (i > lengthOfMonth) {
            switch (i2) {
                case 0:
                case 2:
                case 5:
                case 6:
                    break;
                case 1:
                    return fromEpochMonths(plainDate, MathUtils.safeAdd(j, 1L), 1, i2);
                case 3:
                    return fromEpochMonths(plainDate, MathUtils.safeAdd(j, 1L), i - lengthOfMonth, i2);
                case 4:
                    StringBuilder sb = new StringBuilder(32);
                    sb.append("Day of month out of range: ");
                    formatYear(sb, iSafeCast);
                    format2Digits(sb, iFloorModulo);
                    format2Digits(sb, i);
                    throw new ChronoException(sb.toString());
                default:
                    throw new UnsupportedOperationException("Overflow policy not implemented: " + i2);
            }
            return of(iSafeCast, iFloorModulo, i);
        }
        if (i < lengthOfMonth && i2 == 2) {
        }
        return of(iSafeCast, iFloorModulo, i);
        i = lengthOfMonth;
        return of(iSafeCast, iFloorModulo, i);
    }

    private static PlainDate addDays(PlainDate plainDate, long j) {
        long jSafeAdd = MathUtils.safeAdd(plainDate.dayOfMonth, j);
        if (jSafeAdd >= 1 && jSafeAdd <= 28) {
            return of(plainDate.year, plainDate.month, (int) jSafeAdd);
        }
        long jSafeAdd2 = MathUtils.safeAdd(plainDate.getDayOfYear(), j);
        if (jSafeAdd2 >= 1 && jSafeAdd2 <= 365) {
            return of(plainDate.year, (int) jSafeAdd2);
        }
        return TRANSFORMER.transform(MathUtils.safeAdd(plainDate.getDaysSinceUTC(), j));
    }

    private static void fill(Map<String, Object> map, ChronoElement<?> chronoElement) {
        map.put(chronoElement.name(), chronoElement);
    }

    private static void formatYear(StringBuilder sb, int i) {
        int iSafeNegate;
        if (i < 0) {
            sb.append('-');
            iSafeNegate = MathUtils.safeNegate(i);
        } else {
            iSafeNegate = i;
        }
        if (iSafeNegate >= 10000) {
            if (i > 0) {
                sb.append('+');
            }
        } else if (iSafeNegate < 1000) {
            sb.append('0');
            if (iSafeNegate < 100) {
                sb.append('0');
                if (iSafeNegate < 10) {
                    sb.append('0');
                }
            }
        }
        sb.append(iSafeNegate);
    }

    private static void format2Digits(StringBuilder sb, int i) {
        sb.append('-');
        if (i < 10) {
            sb.append('0');
        }
        sb.append(i);
    }

    private static void registerExtensions(TimeAxis.Builder<IsoDateUnit, PlainDate> builder) {
        for (ChronoExtension chronoExtension : ResourceLoader.getInstance().services(ChronoExtension.class)) {
            if (chronoExtension.accept(PlainDate.class)) {
                builder.appendExtension(chronoExtension);
            }
        }
        builder.appendExtension((ChronoExtension) new WeekExtension());
    }

    private static void registerUnits(TimeAxis.Builder<IsoDateUnit, PlainDate> builder) {
        Set<? extends IsoDateUnit> setRange = EnumSet.range(CalendarUnit.MILLENNIA, CalendarUnit.MONTHS);
        Set<? extends IsoDateUnit> setRange2 = EnumSet.range(CalendarUnit.WEEKS, CalendarUnit.DAYS);
        for (CalendarUnit calendarUnit : CalendarUnit.values()) {
            builder.appendUnit(calendarUnit, new CalendarUnit.Rule(calendarUnit), calendarUnit.getLength(), calendarUnit.compareTo(CalendarUnit.WEEKS) < 0 ? setRange : setRange2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static PlainDate of(int i, int i2, Weekday weekday, boolean z) {
        if (i2 < 1 || i2 > 53) {
            if (z) {
                throw new IllegalArgumentException(woyFailed(i2));
            }
            return null;
        }
        if (z && (i < MIN_YEAR.intValue() || i > MAX_YEAR.intValue())) {
            throw new IllegalArgumentException(yowFailed(i));
        }
        int value = Weekday.valueOf(GregorianMath.getDayOfWeek(i, 1, 1)).getValue();
        int value2 = (((value <= 4 ? 2 - value : 9 - value) + ((i2 - 1) * 7)) + weekday.getValue()) - 1;
        if (value2 <= 0) {
            i--;
            value2 += GregorianMath.isLeapYear(i) ? 366 : 365;
        } else {
            int i3 = GregorianMath.isLeapYear(i) ? 366 : 365;
            if (value2 > i3) {
                value2 -= i3;
                i++;
            }
        }
        PlainDate plainDateOf = of(i, value2);
        if (i2 != 53 || plainDateOf.getWeekOfYear() == 53) {
            return plainDateOf;
        }
        if (z) {
            throw new IllegalArgumentException(woyFailed(i2));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String yowFailed(int i) {
        return "YEAR_OF_WEEKDATE (ISO) out of range: " + i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String woyFailed(int i) {
        return "WEEK_OF_YEAR (ISO) out of range: " + i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static PlainDate of(int i, int i2, int i3, boolean z) {
        if (z) {
            GregorianMath.checkDate(i, i2, i3);
        }
        return new PlainDate(i, i2, i3);
    }

    private Object writeReplace() {
        return new SPX(this, 1);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        throw new InvalidObjectException("Serialization proxy required.");
    }

    private static class Merger implements ChronoMerger<PlainDate> {
        private static final int DEFAULT_PIVOT_YEAR = GregorianMath.readYear(GregorianMath.toPackedDate(EpochDays.MODIFIED_JULIAN_DATE.transform(MathUtils.floorDivide(System.currentTimeMillis(), 86400000), EpochDays.UNIX))) + 20;

        @Override // net.time4j.engine.ChronoMerger
        public int getDefaultPivotYear() {
            return DEFAULT_PIVOT_YEAR;
        }

        @Override // net.time4j.engine.ChronoMerger
        public ChronoDisplay preformat(PlainDate plainDate, AttributeQuery attributeQuery) {
            return plainDate;
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
        public /* bridge */ /* synthetic */ PlainDate createFrom(TimeSource timeSource, AttributeQuery attributeQuery) {
            return createFrom2((TimeSource<?>) timeSource, attributeQuery);
        }

        @Override // net.time4j.engine.ChronoMerger
        public /* bridge */ /* synthetic */ PlainDate createFrom(ChronoEntity chronoEntity, AttributeQuery attributeQuery, boolean z, boolean z2) {
            return createFrom2((ChronoEntity<?>) chronoEntity, attributeQuery, z, z2);
        }

        @Override // net.time4j.engine.ChronoMerger
        public String getFormatPattern(DisplayStyle displayStyle, Locale locale) {
            return CalendarText.patternForDate(DisplayMode.ofStyle(displayStyle.getStyleValue()), locale);
        }

        @Override // net.time4j.engine.ChronoMerger
        /* renamed from: createFrom, reason: avoid collision after fix types in other method */
        public PlainDate createFrom2(TimeSource<?> timeSource, AttributeQuery attributeQuery) {
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
            return PlainDate.from(unixTimeCurrentTime, timezoneOfSystem.getOffset(unixTimeCurrentTime));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // net.time4j.engine.ChronoMerger
        /* renamed from: createFrom, reason: avoid collision after fix types in other method */
        public PlainDate createFrom2(ChronoEntity<?> chronoEntity, AttributeQuery attributeQuery, boolean z, boolean z2) {
            Weekday weekday;
            int i;
            if (chronoEntity.contains(PlainDate.CALENDAR_DATE)) {
                return (PlainDate) chronoEntity.get(PlainDate.CALENDAR_DATE);
            }
            int i2 = chronoEntity.getInt(PlainDate.YEAR);
            if (i2 != Integer.MIN_VALUE) {
                int value = chronoEntity.getInt(PlainDate.MONTH_AS_NUMBER);
                if (value == Integer.MIN_VALUE && chronoEntity.contains(PlainDate.MONTH_OF_YEAR)) {
                    value = ((Month) chronoEntity.get(PlainDate.MONTH_OF_YEAR)).getValue();
                }
                if (value != Integer.MIN_VALUE && (i = chronoEntity.getInt(PlainDate.DAY_OF_MONTH)) != Integer.MIN_VALUE) {
                    if (z) {
                        return (PlainDate) ((PlainDate) PlainDate.of(i2, 1, 1).with(PlainDate.MONTH_AS_NUMBER.setLenient(Integer.valueOf(value)))).with(PlainDate.DAY_OF_MONTH.setLenient(Integer.valueOf(i)));
                    }
                    if (validateYear(chronoEntity, i2) && validateMonth(chronoEntity, value) && validateDayOfMonth(chronoEntity, i2, value, i)) {
                        return PlainDate.of(i2, value, i, false);
                    }
                    return null;
                }
                int i3 = chronoEntity.getInt(PlainDate.DAY_OF_YEAR);
                if (i3 != Integer.MIN_VALUE) {
                    if (z) {
                        return (PlainDate) PlainDate.of(i2, 1).with(PlainDate.DAY_OF_YEAR.setLenient(Integer.valueOf(i3)));
                    }
                    if (validateYear(chronoEntity, i2) && validateDayOfYear(chronoEntity, i2, i3)) {
                        return PlainDate.of(i2, i3);
                    }
                    return null;
                }
                int i4 = chronoEntity.getInt(PlainDate.DAY_OF_QUARTER);
                if (i4 != Integer.MIN_VALUE && chronoEntity.contains(PlainDate.QUARTER_OF_YEAR)) {
                    Quarter quarter = (Quarter) chronoEntity.get(PlainDate.QUARTER_OF_YEAR);
                    boolean zIsLeapYear = GregorianMath.isLeapYear(i2);
                    int i5 = (zIsLeapYear ? 91 : 90) + i4;
                    if (quarter == Quarter.Q1) {
                        i5 = i4;
                    } else if (quarter == Quarter.Q3) {
                        i5 += 91;
                    } else if (quarter == Quarter.Q4) {
                        i5 += 183;
                    }
                    if (z) {
                        return (PlainDate) PlainDate.of(i2, 1).with(PlainDate.DAY_OF_YEAR.setLenient(Integer.valueOf(i5)));
                    }
                    if (validateYear(chronoEntity, i2) && validateDayOfQuarter(chronoEntity, zIsLeapYear, quarter, i4)) {
                        return PlainDate.of(i2, i5);
                    }
                    return null;
                }
            }
            int i6 = chronoEntity.getInt(PlainDate.YEAR_OF_WEEKDATE);
            if (i6 != Integer.MIN_VALUE && chronoEntity.contains(Weekmodel.ISO.weekOfYear())) {
                int iIntValue = ((Integer) chronoEntity.get(Weekmodel.ISO.weekOfYear())).intValue();
                if (chronoEntity.contains(PlainDate.DAY_OF_WEEK)) {
                    weekday = (Weekday) chronoEntity.get(PlainDate.DAY_OF_WEEK);
                } else {
                    if (chronoEntity.contains(Weekmodel.ISO.localDayOfWeek())) {
                        weekday = (Weekday) chronoEntity.get(Weekmodel.ISO.localDayOfWeek());
                    }
                    return null;
                }
                if (i6 >= -999999999 && i6 <= 999999999) {
                    PlainDate plainDateOf = PlainDate.of(i6, iIntValue, weekday, false);
                    if (plainDateOf == null) {
                        flagValidationError(chronoEntity, PlainDate.woyFailed(iIntValue));
                    }
                    return plainDateOf;
                }
                flagValidationError(chronoEntity, PlainDate.yowFailed(i6));
                return null;
            }
            if (chronoEntity.contains(EpochDays.MODIFIED_JULIAN_DATE)) {
                return (PlainDate) PlainDate.TRANSFORMER.transform(EpochDays.UTC.transform(((Long) chronoEntity.get(EpochDays.MODIFIED_JULIAN_DATE)).longValue(), EpochDays.MODIFIED_JULIAN_DATE));
            }
            if (chronoEntity instanceof UnixTime) {
                return ((PlainTimestamp) PlainTimestamp.axis().createFrom(chronoEntity, attributeQuery, z, z2)).getCalendarDate();
            }
            return null;
        }

        @Override // net.time4j.engine.ChronoMerger
        public StartOfDay getDefaultStartOfDay() {
            return StartOfDay.MIDNIGHT;
        }

        private static boolean validateYear(ChronoEntity<?> chronoEntity, int i) {
            if (i >= -999999999 && i <= 999999999) {
                return true;
            }
            flagValidationError(chronoEntity, "YEAR out of range: " + i);
            return false;
        }

        private static boolean validateMonth(ChronoEntity<?> chronoEntity, int i) {
            if (i >= 1 && i <= 12) {
                return true;
            }
            flagValidationError(chronoEntity, "MONTH_OF_YEAR out of range: " + i);
            return false;
        }

        private static boolean validateDayOfMonth(ChronoEntity<?> chronoEntity, int i, int i2, int i3) {
            if (i3 >= 1 && (i3 <= 28 || i3 <= GregorianMath.getLengthOfMonth(i, i2))) {
                return true;
            }
            flagValidationError(chronoEntity, "DAY_OF_MONTH out of range: " + i3);
            return false;
        }

        /* JADX WARN: Code restructure failed: missing block: B:9:0x000f, code lost:
        
            if (r4 > (net.time4j.base.GregorianMath.isLeapYear(r3) ? 366 : 365)) goto L12;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static boolean validateDayOfYear(net.time4j.engine.ChronoEntity<?> r2, int r3, int r4) {
            /*
                r0 = 1
                if (r4 < r0) goto L13
                r1 = 365(0x16d, float:5.11E-43)
                if (r4 <= r1) goto L12
                boolean r3 = net.time4j.base.GregorianMath.isLeapYear(r3)
                if (r3 == 0) goto Lf
                r1 = 366(0x16e, float:5.13E-43)
            Lf:
                if (r4 <= r1) goto L12
                goto L13
            L12:
                return r0
            L13:
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r0 = "DAY_OF_YEAR out of range: "
                r3.<init>(r0)
                java.lang.StringBuilder r3 = r3.append(r4)
                java.lang.String r3 = r3.toString()
                flagValidationError(r2, r3)
                r2 = 0
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: net.time4j.PlainDate.Merger.validateDayOfYear(net.time4j.engine.ChronoEntity, int, int):boolean");
        }

        private static boolean validateDayOfQuarter(ChronoEntity<?> chronoEntity, boolean z, Quarter quarter, int i) {
            int i2 = AnonymousClass1.$SwitchMap$net$time4j$Quarter[quarter.ordinal()];
            int i3 = 91;
            if (i2 != 1) {
                if (i2 != 2) {
                    i3 = 92;
                }
            } else if (!z) {
                i3 = 90;
            }
            if (i >= 1 && i <= i3) {
                return true;
            }
            flagValidationError(chronoEntity, "DAY_OF_QUARTER out of range: " + i);
            return false;
        }

        private static void flagValidationError(ChronoEntity<?> chronoEntity, String str) {
            if (chronoEntity.isValid((ChronoElement<ValidationElement>) ValidationElement.ERROR_MESSAGE, (ValidationElement) str)) {
                chronoEntity.with((ChronoElement<ValidationElement>) ValidationElement.ERROR_MESSAGE, (ValidationElement) str);
            }
        }
    }

    /* renamed from: net.time4j.PlainDate$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$time4j$CalendarUnit;
        static final /* synthetic */ int[] $SwitchMap$net$time4j$Quarter;

        static {
            int[] iArr = new int[Quarter.values().length];
            $SwitchMap$net$time4j$Quarter = iArr;
            try {
                iArr[Quarter.Q1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$net$time4j$Quarter[Quarter.Q2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[CalendarUnit.values().length];
            $SwitchMap$net$time4j$CalendarUnit = iArr2;
            try {
                iArr2[CalendarUnit.MILLENNIA.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$net$time4j$CalendarUnit[CalendarUnit.CENTURIES.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$net$time4j$CalendarUnit[CalendarUnit.DECADES.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$net$time4j$CalendarUnit[CalendarUnit.YEARS.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$net$time4j$CalendarUnit[CalendarUnit.QUARTERS.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$net$time4j$CalendarUnit[CalendarUnit.MONTHS.ordinal()] = 6;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$net$time4j$CalendarUnit[CalendarUnit.WEEKS.ordinal()] = 7;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$net$time4j$CalendarUnit[CalendarUnit.DAYS.ordinal()] = 8;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    private static class Transformer implements CalendarSystem<PlainDate> {
        private static final long MAX_LONG = 365241779741L;
        private static final long MIN_LONG = -365243219892L;

        @Override // net.time4j.engine.CalendarSystem
        public long getMaximumSinceUTC() {
            return MAX_LONG;
        }

        @Override // net.time4j.engine.CalendarSystem
        public long getMinimumSinceUTC() {
            return MIN_LONG;
        }

        private Transformer() {
        }

        /* synthetic */ Transformer(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // net.time4j.engine.CalendarSystem
        public PlainDate transform(long j) {
            if (j == MIN_LONG) {
                return PlainDate.MIN;
            }
            if (j == MAX_LONG) {
                return PlainDate.MAX;
            }
            long packedDate = GregorianMath.toPackedDate(EpochDays.MODIFIED_JULIAN_DATE.transform(j, EpochDays.UTC));
            return PlainDate.of(GregorianMath.readYear(packedDate), GregorianMath.readMonth(packedDate), GregorianMath.readDayOfMonth(packedDate));
        }

        @Override // net.time4j.engine.CalendarSystem
        public long transform(PlainDate plainDate) {
            return EpochDays.UTC.transform(GregorianMath.toMJD(plainDate), EpochDays.MODIFIED_JULIAN_DATE);
        }

        @Override // net.time4j.engine.CalendarSystem
        public List<CalendarEra> getEras() {
            return Collections.emptyList();
        }
    }

    private static class DateElementRule implements ElementRule<PlainDate, PlainDate> {
        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(PlainDate plainDate) {
            return null;
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(PlainDate plainDate) {
            return null;
        }

        @Override // net.time4j.engine.ElementRule
        public PlainDate getValue(PlainDate plainDate) {
            return plainDate;
        }

        @Override // net.time4j.engine.ElementRule
        public boolean isValid2(PlainDate plainDate, PlainDate plainDate2) {
            return plainDate2 != null;
        }

        private DateElementRule() {
        }

        /* synthetic */ DateElementRule(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // net.time4j.engine.ElementRule
        public PlainDate withValue2(PlainDate plainDate, PlainDate plainDate2, boolean z) {
            if (plainDate2 != null) {
                return plainDate2;
            }
            throw new IllegalArgumentException("Missing date value.");
        }

        @Override // net.time4j.engine.ElementRule
        public PlainDate getMinimum(PlainDate plainDate) {
            return PlainDate.MIN;
        }

        @Override // net.time4j.engine.ElementRule
        public PlainDate getMaximum(PlainDate plainDate) {
            return PlainDate.MAX;
        }
    }

    private static class IntegerElementRule implements IntElementRule<PlainDate> {
        private final int index;
        private final String name;
        private final ChronoElement<?> ref;

        IntegerElementRule(ChronoElement<Integer> chronoElement) {
            this(((IntegerDateElement) chronoElement).getIndex(), chronoElement);
        }

        IntegerElementRule(int i, ChronoElement<?> chronoElement) {
            this.ref = chronoElement;
            this.name = chronoElement.name();
            this.index = i;
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getValue(PlainDate plainDate) {
            return Integer.valueOf(getInt(plainDate));
        }

        @Override // net.time4j.engine.IntElementRule
        public int getInt(PlainDate plainDate) {
            switch (this.index) {
                case 14:
                    return plainDate.year;
                case 15:
                    return plainDate.month;
                case 16:
                    return plainDate.dayOfMonth;
                case 17:
                    return plainDate.getDayOfYear();
                case 18:
                    return plainDate.getDayOfQuarter();
                case 19:
                    return ((plainDate.dayOfMonth - 1) / 7) + 1;
                default:
                    throw new UnsupportedOperationException(this.name);
            }
        }

        @Override // net.time4j.engine.ElementRule
        public PlainDate withValue2(PlainDate plainDate, Integer num, boolean z) {
            if (num == null) {
                throw new IllegalArgumentException("Missing element value.");
            }
            return withValue(plainDate, num.intValue(), z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // net.time4j.engine.IntElementRule
        public PlainDate withValue(PlainDate plainDate, int i, boolean z) {
            if (!z) {
                switch (this.index) {
                    case 14:
                        return plainDate.withYear(i);
                    case 15:
                        return plainDate.withMonth(i);
                    case 16:
                        return plainDate.withDayOfMonth(i);
                    case 17:
                        return plainDate.withDayOfYear(i);
                    case 18:
                        if (i >= 1 && i <= getMaximumOfQuarterDay(plainDate)) {
                            return (PlainDate) plainDate.plus(i - plainDate.getDayOfQuarter(), CalendarUnit.DAYS);
                        }
                        throw new IllegalArgumentException("Out of range: " + i);
                    case 19:
                        if (z || (i >= 1 && i <= getMaximumOfWIM(plainDate))) {
                            return (PlainDate) plainDate.plus(i - (((plainDate.dayOfMonth - 1) / 7) + 1), CalendarUnit.WEEKS);
                        }
                        throw new IllegalArgumentException("Out of range: " + i);
                    default:
                        throw new UnsupportedOperationException(this.name);
                }
            }
            return (PlainDate) plainDate.plus(MathUtils.safeSubtract(i, getInt(plainDate)), (IsoDateUnit) PlainDate.ENGINE.getBaseUnit(this.ref));
        }

        @Override // net.time4j.engine.ElementRule
        public boolean isValid2(PlainDate plainDate, Integer num) {
            return num != null && isValid(plainDate, num.intValue());
        }

        @Override // net.time4j.engine.IntElementRule
        public boolean isValid(PlainDate plainDate, int i) {
            switch (this.index) {
                case 14:
                    return i >= -999999999 && i <= 999999999;
                case 15:
                    return i >= 1 && i <= 12;
                case 16:
                    return i >= 1 && i <= GregorianMath.getLengthOfMonth(plainDate.year, plainDate.month);
                case 17:
                    if (i >= 1) {
                        return i <= (GregorianMath.isLeapYear(plainDate.year) ? 366 : 365);
                    }
                    return false;
                case 18:
                    return i >= 1 && i <= getMaximumOfQuarterDay(plainDate);
                case 19:
                    return i >= 1 && i <= getMaximumOfWIM(plainDate);
                default:
                    throw new UnsupportedOperationException(this.name);
            }
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getMinimum(PlainDate plainDate) {
            switch (this.index) {
                case 14:
                    return PlainDate.MIN_YEAR;
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                    return PlainDate.VALUE_1;
                default:
                    throw new UnsupportedOperationException(this.name);
            }
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getMaximum(PlainDate plainDate) {
            switch (this.index) {
                case 14:
                    return PlainDate.MAX_YEAR;
                case 15:
                    return PlainDate.VALUE_12;
                case 16:
                    return Integer.valueOf(GregorianMath.getLengthOfMonth(plainDate.year, plainDate.month));
                case 17:
                    return GregorianMath.isLeapYear(plainDate.year) ? PlainDate.LEAP_YEAR_LEN : PlainDate.STD_YEAR_LEN;
                case 18:
                    return Integer.valueOf(getMaximumOfQuarterDay(plainDate));
                case 19:
                    return Integer.valueOf(getMaximumOfWIM(plainDate));
                default:
                    throw new UnsupportedOperationException(this.name);
            }
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(PlainDate plainDate) {
            return getChild();
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(PlainDate plainDate) {
            return getChild();
        }

        private ChronoElement<?> getChild() {
            switch (this.index) {
                case 14:
                    return PlainDate.MONTH_AS_NUMBER;
                case 15:
                    return PlainDate.DAY_OF_MONTH;
                case 16:
                case 17:
                case 18:
                case 19:
                    return null;
                default:
                    throw new UnsupportedOperationException(this.name);
            }
        }

        private static int getMaximumOfQuarterDay(PlainDate plainDate) {
            int i = ((plainDate.month - 1) / 3) + 1;
            return i == 1 ? GregorianMath.isLeapYear(plainDate.year) ? 91 : 90 : i == 2 ? 91 : 92;
        }

        private int getMaximumOfWIM(PlainDate plainDate) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                if ((i2 * 7) + plainDate.dayOfMonth > GregorianMath.getLengthOfMonth(plainDate.year, plainDate.month)) {
                    return (((r5 + (i * 7)) - 1) / 7) + 1;
                }
                i = i2;
            }
        }
    }

    private static class EnumElementRule<V extends Enum<V>> implements ElementRule<PlainDate, V> {
        private final int index;
        private final V max;
        private final V min;
        private final String name;
        private final Class<V> type;

        @Override // net.time4j.engine.ElementRule
        public V getMinimum(PlainDate plainDate) {
            return this.min;
        }

        EnumElementRule(String str, Class<V> cls, V v, V v2, int i) {
            this.name = str;
            this.type = cls;
            this.min = v;
            this.max = v2;
            this.index = i;
        }

        static <V extends Enum<V>> EnumElementRule<V> of(ChronoElement<V> chronoElement) {
            return new EnumElementRule<>(chronoElement.name(), chronoElement.getType(), chronoElement.getDefaultMinimum(), chronoElement.getDefaultMaximum(), ((EnumElement) chronoElement).getIndex());
        }

        @Override // net.time4j.engine.ElementRule
        public V getValue(PlainDate plainDate) {
            Object objValueOf;
            switch (this.index) {
                case 101:
                    objValueOf = Month.valueOf(plainDate.month);
                    break;
                case 102:
                    objValueOf = plainDate.getDayOfWeek();
                    break;
                case 103:
                    objValueOf = Quarter.valueOf(((plainDate.month - 1) / 3) + 1);
                    break;
                default:
                    throw new UnsupportedOperationException(this.name);
            }
            return this.type.cast(objValueOf);
        }

        @Override // net.time4j.engine.ElementRule
        public V getMaximum(PlainDate plainDate) {
            return (this.index == 102 && plainDate.year == 999999999 && plainDate.month == 12 && plainDate.dayOfMonth >= 27) ? this.type.cast(Weekday.FRIDAY) : this.max;
        }

        @Override // net.time4j.engine.ElementRule
        public boolean isValid2(PlainDate plainDate, V v) {
            if (v == null) {
                return false;
            }
            if (this.index != 102 || plainDate.year != 999999999) {
                return true;
            }
            try {
                withValue(plainDate, (PlainDate) v, false);
                return true;
            } catch (IllegalArgumentException unused) {
                return false;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // net.time4j.engine.ElementRule
        public PlainDate withValue2(PlainDate plainDate, V v, boolean z) {
            if (v != null) {
                switch (this.index) {
                    case 101:
                        return plainDate.withMonth(((Month) Month.class.cast(v)).getValue());
                    case 102:
                        return plainDate.withDayOfWeek((Weekday) Weekday.class.cast(v));
                    case 103:
                        return (PlainDate) plainDate.plus(((Quarter) Quarter.class.cast(v)).getValue() - (((plainDate.month - 1) / 3) + 1), CalendarUnit.QUARTERS);
                    default:
                        throw new UnsupportedOperationException(this.name);
                }
            }
            throw new IllegalArgumentException("Missing element value.");
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(PlainDate plainDate) {
            return getChild();
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(PlainDate plainDate) {
            return getChild();
        }

        private ChronoElement<?> getChild() {
            switch (this.index) {
                case 101:
                    return PlainDate.DAY_OF_MONTH;
                case 102:
                    return null;
                case 103:
                    return PlainDate.DAY_OF_QUARTER;
                default:
                    throw new UnsupportedOperationException(this.name);
            }
        }
    }
}
