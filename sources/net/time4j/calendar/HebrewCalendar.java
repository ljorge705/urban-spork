package net.time4j.calendar;

import androidx.exifinterface.media.ExifInterface;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import net.time4j.GeneralTimestamp;
import net.time4j.Moment;
import net.time4j.PlainDate;
import net.time4j.PlainTime;
import net.time4j.SystemClock;
import net.time4j.Weekday;
import net.time4j.Weekmodel;
import net.time4j.base.MathUtils;
import net.time4j.base.TimeSource;
import net.time4j.calendar.CommonElements;
import net.time4j.calendar.HebrewMonth;
import net.time4j.calendar.service.GenericDatePatterns;
import net.time4j.calendar.service.StdEnumDateElement;
import net.time4j.calendar.service.StdIntegerDateElement;
import net.time4j.calendar.service.StdWeekdayElement;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.CalendarDate;
import net.time4j.engine.CalendarDays;
import net.time4j.engine.CalendarEra;
import net.time4j.engine.CalendarSystem;
import net.time4j.engine.Calendrical;
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoExtension;
import net.time4j.engine.ChronoFunction;
import net.time4j.engine.ChronoMerger;
import net.time4j.engine.ChronoUnit;
import net.time4j.engine.Chronology;
import net.time4j.engine.DisplayStyle;
import net.time4j.engine.ElementRule;
import net.time4j.engine.EpochDays;
import net.time4j.engine.FormattableElement;
import net.time4j.engine.IntElementRule;
import net.time4j.engine.StartOfDay;
import net.time4j.engine.TimeAxis;
import net.time4j.engine.UnitRule;
import net.time4j.engine.ValidationElement;
import net.time4j.format.Attributes;
import net.time4j.format.CalendarType;
import net.time4j.format.Leniency;
import net.time4j.format.LocalizedPatternSupport;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;

@CalendarType("hebrew")
/* loaded from: classes4.dex */
public final class HebrewCalendar extends Calendrical<Unit, HebrewCalendar> implements LocalizedPatternSupport {
    public static final StdCalendarElement<Integer, HebrewCalendar> BOUNDED_WEEK_OF_MONTH;
    public static final StdCalendarElement<Integer, HebrewCalendar> BOUNDED_WEEK_OF_YEAR;
    private static final EraYearMonthDaySystem<HebrewCalendar> CALSYS;

    @FormattableElement(format = "d")
    public static final StdCalendarElement<Integer, HebrewCalendar> DAY_OF_MONTH;
    private static final int DAY_OF_MONTH_INDEX = 2;

    @FormattableElement(format = ExifInterface.LONGITUDE_EAST)
    public static final StdCalendarElement<Weekday, HebrewCalendar> DAY_OF_WEEK;

    @FormattableElement(format = "D")
    public static final StdCalendarElement<Integer, HebrewCalendar> DAY_OF_YEAR;
    private static final int DAY_OF_YEAR_INDEX = 3;
    private static final TimeAxis<Unit, HebrewCalendar> ENGINE;

    @FormattableElement(format = "G")
    public static final ChronoElement<HebrewEra> ERA;
    private static final long FIXED_EPOCH = ((Long) PlainDate.of(-3760, 9, 7).get(EpochDays.RATA_DIE)).longValue();
    public static final StdCalendarElement<Weekday, HebrewCalendar> LOCAL_DAY_OF_WEEK;

    @FormattableElement(alt = "L", format = "M")
    public static final StdCalendarElement<HebrewMonth, HebrewCalendar> MONTH_OF_YEAR;

    @FormattableElement(format = "F")
    public static final OrdinalWeekdayElement<HebrewCalendar> WEEKDAY_IN_MONTH;
    public static final StdCalendarElement<Integer, HebrewCalendar> WEEK_OF_MONTH;
    public static final StdCalendarElement<Integer, HebrewCalendar> WEEK_OF_YEAR;
    private static final WeekdayInMonthElement<HebrewCalendar> WIM_ELEMENT;
    private static final int YEAR_INDEX = 0;

    @FormattableElement(format = "y")
    public static final StdCalendarElement<Integer, HebrewCalendar> YEAR_OF_ERA;
    private static final long serialVersionUID = -4183006723190472312L;
    private final transient int dom;
    private final transient HebrewMonth month;
    private final transient int year;

    public static TimeAxis<Unit, HebrewCalendar> axis() {
        return ENGINE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.time4j.engine.TimePoint, net.time4j.engine.ChronoEntity
    public TimeAxis<Unit, HebrewCalendar> getChronology() {
        return ENGINE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.time4j.engine.ChronoEntity
    public HebrewCalendar getContext() {
        return this;
    }

    public int getDayOfMonth() {
        return this.dom;
    }

    public HebrewMonth getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    static {
        StdEnumDateElement stdEnumDateElement = new StdEnumDateElement("ERA", HebrewCalendar.class, HebrewEra.class, 'G');
        ERA = stdEnumDateElement;
        StdIntegerDateElement stdIntegerDateElement = new StdIntegerDateElement("YEAR_OF_ERA", HebrewCalendar.class, 1, 9999, 'y', null, null);
        YEAR_OF_ERA = stdIntegerDateElement;
        StdEnumDateElement<HebrewMonth, HebrewCalendar> stdEnumDateElement2 = new StdEnumDateElement<HebrewMonth, HebrewCalendar>("MONTH_OF_YEAR", HebrewCalendar.class, HebrewMonth.class, 'M') { // from class: net.time4j.calendar.HebrewCalendar.1
            @Override // net.time4j.calendar.service.StdEnumDateElement
            protected boolean hasLeapMonth(ChronoDisplay chronoDisplay) {
                return HebrewCalendar.isLeapYear(chronoDisplay.getInt(HebrewCalendar.YEAR_OF_ERA));
            }

            @Override // net.time4j.calendar.service.StdEnumDateElement, net.time4j.format.NumericalElement
            public int printToInt(HebrewMonth hebrewMonth, ChronoDisplay chronoDisplay, AttributeQuery attributeQuery) {
                int i = AnonymousClass3.$SwitchMap$net$time4j$calendar$HebrewMonth$Order[((HebrewMonth.Order) attributeQuery.get(HebrewMonth.order(), HebrewMonth.Order.CIVIL)).ordinal()];
                if (i == 1) {
                    return hebrewMonth.getCivilValue(hasLeapMonth(chronoDisplay));
                }
                if (i == 2) {
                    return hebrewMonth.getBiblicalValue(hasLeapMonth(chronoDisplay));
                }
                return numerical((AnonymousClass1) hebrewMonth);
            }

            @Override // net.time4j.calendar.service.StdEnumDateElement, net.time4j.format.NumericalElement
            public boolean parseFromInt(ChronoEntity<?> chronoEntity, int i) {
                if (i < 1 || i > 13) {
                    return false;
                }
                chronoEntity.with((ChronoElement<Integer>) ParsedMonthElement.INSTANCE, i);
                return true;
            }
        };
        MONTH_OF_YEAR = stdEnumDateElement2;
        StdIntegerDateElement stdIntegerDateElement2 = new StdIntegerDateElement("DAY_OF_MONTH", HebrewCalendar.class, 1, 30, 'd');
        DAY_OF_MONTH = stdIntegerDateElement2;
        StdIntegerDateElement stdIntegerDateElement3 = new StdIntegerDateElement("DAY_OF_YEAR", HebrewCalendar.class, 1, 355, 'D');
        DAY_OF_YEAR = stdIntegerDateElement3;
        StdWeekdayElement stdWeekdayElement = new StdWeekdayElement(HebrewCalendar.class, getDefaultWeekmodel());
        DAY_OF_WEEK = stdWeekdayElement;
        WeekdayInMonthElement<HebrewCalendar> weekdayInMonthElement = new WeekdayInMonthElement<>(HebrewCalendar.class, stdIntegerDateElement2, stdWeekdayElement);
        WIM_ELEMENT = weekdayInMonthElement;
        WEEKDAY_IN_MONTH = weekdayInMonthElement;
        Transformer transformer = new Transformer();
        CALSYS = transformer;
        ENGINE = TimeAxis.Builder.setUp(Unit.class, HebrewCalendar.class, new Merger(), transformer).appendElement((ChronoElement) stdEnumDateElement, (ElementRule) new EraRule()).appendElement(stdIntegerDateElement, new IntegerRule(0), Unit.YEARS).appendElement(stdEnumDateElement2, new MonthRule(), Unit.MONTHS).appendElement(stdIntegerDateElement2, new IntegerRule(2), Unit.DAYS).appendElement(stdIntegerDateElement3, new IntegerRule(3), Unit.DAYS).appendElement(stdWeekdayElement, new WeekdayRule(getDefaultWeekmodel(), new ChronoFunction<HebrewCalendar, CalendarSystem<HebrewCalendar>>() { // from class: net.time4j.calendar.HebrewCalendar.2
            @Override // net.time4j.engine.ChronoFunction
            public CalendarSystem<HebrewCalendar> apply(HebrewCalendar hebrewCalendar) {
                return HebrewCalendar.CALSYS;
            }
        }), Unit.DAYS).appendElement((ChronoElement) weekdayInMonthElement, WeekdayInMonthElement.getRule(weekdayInMonthElement)).appendElement((ChronoElement) CommonElements.RELATED_GREGORIAN_YEAR, (ElementRule) new RelatedGregorianYearRule(transformer, stdIntegerDateElement3)).appendUnit(Unit.YEARS, new HebrewUnitRule(Unit.YEARS), Unit.YEARS.getLength(), Collections.singleton(Unit.MONTHS)).appendUnit(Unit.MONTHS, new HebrewUnitRule(Unit.MONTHS), Unit.MONTHS.getLength(), Collections.singleton(Unit.YEARS)).appendUnit(Unit.WEEKS, new HebrewUnitRule(Unit.WEEKS), Unit.WEEKS.getLength(), Collections.singleton(Unit.DAYS)).appendUnit(Unit.DAYS, new HebrewUnitRule(Unit.DAYS), Unit.DAYS.getLength(), Collections.singleton(Unit.WEEKS)).appendExtension((ChronoExtension) new CommonElements.Weekengine(HebrewCalendar.class, stdIntegerDateElement2, stdIntegerDateElement3, getDefaultWeekmodel())).build();
        LOCAL_DAY_OF_WEEK = CommonElements.localDayOfWeek(axis(), getDefaultWeekmodel());
        WEEK_OF_YEAR = CommonElements.weekOfYear(axis(), getDefaultWeekmodel());
        WEEK_OF_MONTH = CommonElements.weekOfMonth(axis(), getDefaultWeekmodel());
        BOUNDED_WEEK_OF_YEAR = CommonElements.boundedWeekOfYear(axis(), getDefaultWeekmodel());
        BOUNDED_WEEK_OF_MONTH = CommonElements.boundedWeekOfMonth(axis(), getDefaultWeekmodel());
    }

    private HebrewCalendar(int i, HebrewMonth hebrewMonth, int i2) {
        this.year = i;
        this.month = hebrewMonth;
        this.dom = i2;
    }

    public static HebrewCalendar of(int i, HebrewMonth hebrewMonth, int i2) {
        if (!CALSYS.isValid(HebrewEra.ANNO_MUNDI, i, hebrewMonth.getValue(), i2)) {
            throw new IllegalArgumentException("Invalid Hebrew date: year=" + i + ", month=" + hebrewMonth + ", day=" + i2);
        }
        return new HebrewCalendar(i, hebrewMonth, i2);
    }

    public static HebrewCalendar ofCivil(int i, int i2, int i3) {
        return of(i, HebrewMonth.valueOfCivil(i2, isLeapYear(i)), i3);
    }

    public static HebrewCalendar ofBiblical(int i, int i2, int i3) {
        return of(i, HebrewMonth.valueOfBiblical(i2, isLeapYear(i)), i3);
    }

    public static HebrewCalendar nowInSystemTime() {
        return (HebrewCalendar) SystemClock.inLocalView().now(axis());
    }

    public static HebrewCalendar nowInSystemTime(StartOfDay startOfDay) {
        return (HebrewCalendar) SystemClock.currentMoment().toGeneralTimestamp(axis(), Timezone.ofSystem().getID(), startOfDay).toDate();
    }

    public HebrewEra getEra() {
        return HebrewEra.ANNO_MUNDI;
    }

    public Weekday getDayOfWeek() {
        return Weekday.valueOf(MathUtils.floorModulo(CALSYS.transform((EraYearMonthDaySystem<HebrewCalendar>) this) + 5, 7) + 1);
    }

    public int getDayOfYear() {
        return getInt(DAY_OF_YEAR);
    }

    public int lengthOfMonth() {
        return lengthOfMonth(this.year, this.month);
    }

    public int lengthOfYear() {
        return lengthOfYear(this.year);
    }

    public static boolean isLeapYear(int i) {
        if (i >= 0) {
            return ((i * 7) + 1) % 19 < 7;
        }
        throw new IllegalArgumentException("Hebrew year is not positive: " + i);
    }

    public boolean isLeapYear() {
        return isLeapYear(this.year);
    }

    public boolean isSabbaticalYear() {
        return this.year % 7 == 0;
    }

    public boolean isRoshChodesh() {
        return (this.dom == 1 && this.month != HebrewMonth.TISHRI) || this.dom == 30;
    }

    public static boolean isValid(int i, HebrewMonth hebrewMonth, int i2) {
        return CALSYS.isValid(HebrewEra.ANNO_MUNDI, i, hebrewMonth.getValue(), i2);
    }

    public HebrewCalendar barMitzvah() {
        return (HebrewCalendar) get(HebrewAnniversary.BIRTHDAY.inHebrewYear(this.year + 13));
    }

    public HebrewCalendar batMitzvah() {
        return (HebrewCalendar) get(HebrewAnniversary.BIRTHDAY.inHebrewYear(this.year + 12));
    }

    public GeneralTimestamp<HebrewCalendar> at(PlainTime plainTime) {
        return GeneralTimestamp.of(this, plainTime);
    }

    public GeneralTimestamp<HebrewCalendar> atTime(int i, int i2) {
        return at(PlainTime.of(i, i2));
    }

    @Override // net.time4j.engine.Calendrical, net.time4j.engine.TimePoint
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HebrewCalendar)) {
            return false;
        }
        HebrewCalendar hebrewCalendar = (HebrewCalendar) obj;
        return this.dom == hebrewCalendar.dom && this.month == hebrewCalendar.month && this.year == hebrewCalendar.year;
    }

    @Override // net.time4j.engine.Calendrical, net.time4j.engine.TimePoint
    public int hashCode() {
        return (this.dom * 17) + (this.month.getValue() * 31) + (this.year * 37);
    }

    @Override // net.time4j.engine.TimePoint
    public String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append("AM-");
        String strValueOf = String.valueOf(this.year);
        for (int length = strValueOf.length(); length < 4; length++) {
            sb.append('0');
        }
        sb.append(strValueOf);
        sb.append('-');
        sb.append(this.month.name());
        sb.append('-');
        if (this.dom < 10) {
            sb.append('0');
        }
        sb.append(this.dom);
        return sb.toString();
    }

    public static Weekmodel getDefaultWeekmodel() {
        return Weekmodel.of(Weekday.SUNDAY, 1, Weekday.FRIDAY, Weekday.SATURDAY);
    }

    static int lengthOfMonth(int i, HebrewMonth hebrewMonth) {
        switch (AnonymousClass3.$SwitchMap$net$time4j$calendar$HebrewMonth[hebrewMonth.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return 29;
            case 6:
                int iLengthOfYear = lengthOfYear(i);
                return (iLengthOfYear == 355 || iLengthOfYear == 385) ? 30 : 29;
            case 7:
                int iLengthOfYear2 = lengthOfYear(i);
                return (iLengthOfYear2 == 353 || iLengthOfYear2 == 383) ? 29 : 30;
            default:
                return 30;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lengthOfYear(int i) {
        return (int) (hcNewYear(i + 1) - hcNewYear(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long hcNewYear(int i) {
        return FIXED_EPOCH + hcDelay1(i) + hcDelay2(i);
    }

    private static int hcDelay2(int i) {
        int iHcDelay1 = hcDelay1(i);
        if (hcDelay1(i + 1) - iHcDelay1 == 356) {
            return 2;
        }
        return iHcDelay1 - hcDelay1(i - 1) == 382 ? 1 : 0;
    }

    private static int hcDelay1(int i) {
        int iFloor = (int) Math.floor((hcMolad(i, HebrewMonth.TISHRI) - FIXED_EPOCH) + 0.5d);
        int i2 = iFloor + 1;
        return (i2 * 3) % 7 < 3 ? i2 : iFloor;
    }

    private static double hcMolad(int i, HebrewMonth hebrewMonth) {
        int value = hebrewMonth.getValue();
        int i2 = value + 6;
        if (i2 > 13) {
            i2 = value - 7;
        }
        if (i2 < 7) {
            i++;
        }
        return (FIXED_EPOCH - 0.033796296296296297d) + (((i2 - 7) + MathUtils.floorDivide((i * 235) - 234, 19)) * 29.53059413580247d);
    }

    private Object writeReplace() {
        return new SPX(this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        throw new InvalidObjectException("Serialization proxy required.");
    }

    public enum Unit implements ChronoUnit {
        YEARS(3.155732352E7d),
        MONTHS(2592000.0d),
        WEEKS(604800.0d),
        DAYS(86400.0d);

        private final transient double length;

        @Override // net.time4j.engine.ChronoUnit
        public double getLength() {
            return this.length;
        }

        @Override // net.time4j.engine.ChronoUnit
        public boolean isCalendrical() {
            return true;
        }

        Unit(double d) {
            this.length = d;
        }

        public int between(HebrewCalendar hebrewCalendar, HebrewCalendar hebrewCalendar2) {
            return (int) hebrewCalendar.until(hebrewCalendar2, (HebrewCalendar) this);
        }
    }

    private static class Transformer implements EraYearMonthDaySystem<HebrewCalendar> {
        private Transformer() {
        }

        @Override // net.time4j.calendar.EraYearMonthDaySystem
        public boolean isValid(CalendarEra calendarEra, int i, int i2, int i3) {
            return calendarEra == HebrewEra.ANNO_MUNDI && i >= 1 && i <= 9999 && i2 >= 1 && i2 <= 13 && i3 >= 1 && i3 <= getLengthOfMonth(calendarEra, i, i2);
        }

        @Override // net.time4j.calendar.EraYearMonthDaySystem
        public int getLengthOfMonth(CalendarEra calendarEra, int i, int i2) {
            if (calendarEra != HebrewEra.ANNO_MUNDI) {
                throw new IllegalArgumentException("Invalid era: " + calendarEra);
            }
            if (i >= 1 && i <= 9999 && i2 >= 1 && i2 <= 13) {
                return HebrewCalendar.lengthOfMonth(i, HebrewMonth.valueOf(i2));
            }
            throw new IllegalArgumentException("Out of bounds: year=" + i + ", month=" + HebrewMonth.valueOf(i2));
        }

        @Override // net.time4j.calendar.EraYearMonthDaySystem
        public int getLengthOfYear(CalendarEra calendarEra, int i) {
            if (calendarEra != HebrewEra.ANNO_MUNDI) {
                throw new IllegalArgumentException("Invalid era: " + calendarEra);
            }
            if (i >= 1 && i <= 9999) {
                return HebrewCalendar.lengthOfYear(i);
            }
            throw new IllegalArgumentException("Out of bounds: year=" + i);
        }

        @Override // net.time4j.engine.CalendarSystem
        public HebrewCalendar transform(long j) {
            long jTransform = EpochDays.RATA_DIE.transform(j, EpochDays.UTC);
            int iFloorDivide = (int) MathUtils.floorDivide((jTransform - HebrewCalendar.FIXED_EPOCH) * 98496, 35975351);
            int i = iFloorDivide - 1;
            while (HebrewCalendar.hcNewYear(iFloorDivide) <= jTransform) {
                i = iFloorDivide;
                iFloorDivide++;
            }
            long jHcNewYear = jTransform - (HebrewCalendar.hcNewYear(i) - 1);
            boolean zIsLeapYear = HebrewCalendar.isLeapYear(i);
            int i2 = 1;
            for (int i3 = 1; i3 < 13; i3++) {
                if (i3 != 6 || zIsLeapYear) {
                    long jLengthOfMonth = jHcNewYear - HebrewCalendar.lengthOfMonth(i, HebrewMonth.valueOf(i3));
                    if (jLengthOfMonth <= 0) {
                        break;
                    }
                    i2 = i3 + 1;
                    jHcNewYear = jLengthOfMonth;
                } else {
                    i2 = i3 + 1;
                }
            }
            return HebrewCalendar.of(i, HebrewMonth.valueOf(i2), (int) jHcNewYear);
        }

        @Override // net.time4j.engine.CalendarSystem
        public long transform(HebrewCalendar hebrewCalendar) {
            long jTransform = (EpochDays.UTC.transform(HebrewCalendar.hcNewYear(hebrewCalendar.year), EpochDays.RATA_DIE) + hebrewCalendar.dom) - 1;
            boolean zIsLeapYear = HebrewCalendar.isLeapYear(hebrewCalendar.year);
            int value = hebrewCalendar.month.getValue();
            for (int i = 1; i < value; i++) {
                if (zIsLeapYear || i != 6) {
                    jTransform += HebrewCalendar.lengthOfMonth(hebrewCalendar.year, HebrewMonth.valueOf(i));
                }
            }
            return jTransform;
        }

        @Override // net.time4j.engine.CalendarSystem
        public long getMinimumSinceUTC() {
            int i = 1;
            return transform(new HebrewCalendar(i, HebrewMonth.TISHRI, i));
        }

        @Override // net.time4j.engine.CalendarSystem
        public long getMaximumSinceUTC() {
            return transform(new HebrewCalendar(9999, HebrewMonth.ELUL, 29));
        }

        @Override // net.time4j.engine.CalendarSystem
        public List<CalendarEra> getEras() {
            return Collections.singletonList(HebrewEra.ANNO_MUNDI);
        }
    }

    private static class IntegerRule implements IntElementRule<HebrewCalendar> {
        private final int index;

        IntegerRule(int i) {
            this.index = i;
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getValue(HebrewCalendar hebrewCalendar) {
            return Integer.valueOf(getInt(hebrewCalendar));
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getMinimum(HebrewCalendar hebrewCalendar) {
            int i = this.index;
            if (i == 0 || i == 2 || i == 3) {
                return 1;
            }
            throw new UnsupportedOperationException("Unknown element index: " + this.index);
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getMaximum(HebrewCalendar hebrewCalendar) {
            return Integer.valueOf(getMax(hebrewCalendar));
        }

        @Override // net.time4j.engine.ElementRule
        /* renamed from: isValid, reason: merged with bridge method [inline-methods] */
        public boolean isValid2(HebrewCalendar hebrewCalendar, Integer num) {
            if (num == null) {
                return false;
            }
            return getMinimum(hebrewCalendar).compareTo(num) <= 0 && getMaximum(hebrewCalendar).compareTo(num) >= 0;
        }

        @Override // net.time4j.engine.ElementRule
        /* renamed from: withValue, reason: merged with bridge method [inline-methods] */
        public HebrewCalendar withValue2(HebrewCalendar hebrewCalendar, Integer num, boolean z) {
            if (num == null) {
                throw new IllegalArgumentException("Missing new value.");
            }
            return withValue(hebrewCalendar, num.intValue(), z);
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(HebrewCalendar hebrewCalendar) {
            if (this.index == 0) {
                return HebrewCalendar.MONTH_OF_YEAR;
            }
            return null;
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(HebrewCalendar hebrewCalendar) {
            if (this.index == 0) {
                return HebrewCalendar.MONTH_OF_YEAR;
            }
            return null;
        }

        @Override // net.time4j.engine.IntElementRule
        public int getInt(HebrewCalendar hebrewCalendar) {
            int i = this.index;
            if (i == 0) {
                return hebrewCalendar.year;
            }
            if (i == 2) {
                return hebrewCalendar.dom;
            }
            if (i == 3) {
                boolean zIsLeapYear = HebrewCalendar.isLeapYear(hebrewCalendar.year);
                int lengthOfMonth = 0;
                for (int i2 = 1; i2 < hebrewCalendar.month.getValue(); i2++) {
                    if (zIsLeapYear || i2 != 6) {
                        lengthOfMonth += HebrewCalendar.CALSYS.getLengthOfMonth(HebrewEra.ANNO_MUNDI, hebrewCalendar.year, i2);
                    }
                }
                return lengthOfMonth + hebrewCalendar.dom;
            }
            throw new UnsupportedOperationException("Unknown element index: " + this.index);
        }

        @Override // net.time4j.engine.IntElementRule
        public boolean isValid(HebrewCalendar hebrewCalendar, int i) {
            return i <= getMax(hebrewCalendar) && 1 <= i;
        }

        @Override // net.time4j.engine.IntElementRule
        public HebrewCalendar withValue(HebrewCalendar hebrewCalendar, int i, boolean z) {
            if (!isValid(hebrewCalendar, i)) {
                throw new IllegalArgumentException("Out of range: " + i);
            }
            int i2 = this.index;
            if (i2 == 0) {
                return HebrewCalendar.of(i, hebrewCalendar.getMonth(), Math.min(hebrewCalendar.dom, HebrewCalendar.CALSYS.getLengthOfMonth(HebrewEra.ANNO_MUNDI, i, hebrewCalendar.month.getValue())));
            }
            if (i2 == 2) {
                return new HebrewCalendar(hebrewCalendar.year, hebrewCalendar.month, i);
            }
            if (i2 == 3) {
                return hebrewCalendar.plus(CalendarDays.of(i - getInt(hebrewCalendar)));
            }
            throw new UnsupportedOperationException("Unknown element index: " + this.index);
        }

        private int getMax(HebrewCalendar hebrewCalendar) {
            int i = this.index;
            if (i == 0) {
                return 9999;
            }
            if (i == 2) {
                return HebrewCalendar.lengthOfMonth(hebrewCalendar.year, hebrewCalendar.month);
            }
            if (i == 3) {
                return HebrewCalendar.lengthOfYear(hebrewCalendar.year);
            }
            throw new UnsupportedOperationException("Unknown element index: " + this.index);
        }
    }

    private static class MonthRule implements ElementRule<HebrewCalendar, HebrewMonth> {
        private MonthRule() {
        }

        @Override // net.time4j.engine.ElementRule
        public HebrewMonth getValue(HebrewCalendar hebrewCalendar) {
            return hebrewCalendar.month;
        }

        @Override // net.time4j.engine.ElementRule
        public HebrewMonth getMinimum(HebrewCalendar hebrewCalendar) {
            return HebrewMonth.TISHRI;
        }

        @Override // net.time4j.engine.ElementRule
        public HebrewMonth getMaximum(HebrewCalendar hebrewCalendar) {
            return HebrewMonth.ELUL;
        }

        @Override // net.time4j.engine.ElementRule
        /* renamed from: isValid, reason: avoid collision after fix types in other method */
        public boolean isValid2(HebrewCalendar hebrewCalendar, HebrewMonth hebrewMonth) {
            return hebrewMonth != null && (hebrewMonth != HebrewMonth.ADAR_I || hebrewCalendar.isLeapYear());
        }

        @Override // net.time4j.engine.ElementRule
        /* renamed from: withValue, reason: avoid collision after fix types in other method */
        public HebrewCalendar withValue2(HebrewCalendar hebrewCalendar, HebrewMonth hebrewMonth, boolean z) {
            if (hebrewMonth == null) {
                throw new IllegalArgumentException("Missing month.");
            }
            if (hebrewMonth != HebrewMonth.ADAR_I || hebrewCalendar.isLeapYear()) {
                return new HebrewCalendar(hebrewCalendar.year, hebrewMonth, Math.min(hebrewCalendar.dom, HebrewCalendar.lengthOfMonth(hebrewCalendar.year, hebrewMonth)));
            }
            throw new IllegalArgumentException("ADAR-I cannot be set in a standard year: " + hebrewCalendar);
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(HebrewCalendar hebrewCalendar) {
            return HebrewCalendar.DAY_OF_MONTH;
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(HebrewCalendar hebrewCalendar) {
            return HebrewCalendar.DAY_OF_MONTH;
        }
    }

    private static class EraRule implements ElementRule<HebrewCalendar, HebrewEra> {
        @Override // net.time4j.engine.ElementRule
        /* renamed from: isValid, reason: avoid collision after fix types in other method */
        public boolean isValid2(HebrewCalendar hebrewCalendar, HebrewEra hebrewEra) {
            return hebrewEra != null;
        }

        private EraRule() {
        }

        @Override // net.time4j.engine.ElementRule
        public HebrewEra getValue(HebrewCalendar hebrewCalendar) {
            return HebrewEra.ANNO_MUNDI;
        }

        @Override // net.time4j.engine.ElementRule
        public HebrewEra getMinimum(HebrewCalendar hebrewCalendar) {
            return HebrewEra.ANNO_MUNDI;
        }

        @Override // net.time4j.engine.ElementRule
        public HebrewEra getMaximum(HebrewCalendar hebrewCalendar) {
            return HebrewEra.ANNO_MUNDI;
        }

        @Override // net.time4j.engine.ElementRule
        /* renamed from: withValue, reason: avoid collision after fix types in other method */
        public HebrewCalendar withValue2(HebrewCalendar hebrewCalendar, HebrewEra hebrewEra, boolean z) {
            if (hebrewEra != null) {
                return hebrewCalendar;
            }
            throw new IllegalArgumentException("Missing era value.");
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(HebrewCalendar hebrewCalendar) {
            return HebrewCalendar.YEAR_OF_ERA;
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(HebrewCalendar hebrewCalendar) {
            return HebrewCalendar.YEAR_OF_ERA;
        }
    }

    private enum ParsedMonthElement implements ChronoElement<Integer> {
        INSTANCE;

        @Override // net.time4j.engine.ChronoElement
        public char getSymbol() {
            return (char) 0;
        }

        @Override // net.time4j.engine.ChronoElement
        public boolean isDateElement() {
            return true;
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
            return 1;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.time4j.engine.ChronoElement
        public Integer getDefaultMaximum() {
            return 13;
        }

        @Override // net.time4j.engine.ChronoElement
        public String getDisplayName(Locale locale) {
            return name();
        }
    }

    private static class Merger implements ChronoMerger<HebrewCalendar> {
        @Override // net.time4j.engine.ChronoMerger
        public ChronoDisplay preformat(HebrewCalendar hebrewCalendar, AttributeQuery attributeQuery) {
            return hebrewCalendar;
        }

        @Override // net.time4j.engine.ChronoMerger
        public Chronology<?> preparser() {
            return null;
        }

        private Merger() {
        }

        @Override // net.time4j.engine.ChronoMerger
        public /* bridge */ /* synthetic */ HebrewCalendar createFrom(TimeSource timeSource, AttributeQuery attributeQuery) {
            return createFrom2((TimeSource<?>) timeSource, attributeQuery);
        }

        @Override // net.time4j.engine.ChronoMerger
        public /* bridge */ /* synthetic */ HebrewCalendar createFrom(ChronoEntity chronoEntity, AttributeQuery attributeQuery, boolean z, boolean z2) {
            return createFrom2((ChronoEntity<?>) chronoEntity, attributeQuery, z, z2);
        }

        @Override // net.time4j.engine.ChronoMerger
        public String getFormatPattern(DisplayStyle displayStyle, Locale locale) {
            return GenericDatePatterns.get("hebrew", displayStyle, locale);
        }

        @Override // net.time4j.engine.ChronoMerger
        /* renamed from: createFrom, reason: avoid collision after fix types in other method */
        public HebrewCalendar createFrom2(TimeSource<?> timeSource, AttributeQuery attributeQuery) {
            TZID id;
            if (attributeQuery.contains(Attributes.TIMEZONE_ID)) {
                id = (TZID) attributeQuery.get(Attributes.TIMEZONE_ID);
            } else {
                if (!((Leniency) attributeQuery.get(Attributes.LENIENCY, Leniency.SMART)).isLax()) {
                    return null;
                }
                id = Timezone.ofSystem().getID();
            }
            return (HebrewCalendar) Moment.from(timeSource.currentTime()).toGeneralTimestamp(HebrewCalendar.ENGINE, id, (StartOfDay) attributeQuery.get(Attributes.START_OF_DAY, getDefaultStartOfDay())).toDate();
        }

        @Override // net.time4j.engine.ChronoMerger
        /* renamed from: createFrom, reason: avoid collision after fix types in other method */
        public HebrewCalendar createFrom2(ChronoEntity<?> chronoEntity, AttributeQuery attributeQuery, boolean z, boolean z2) {
            HebrewMonth hebrewMonthValueOfCivil;
            int i = chronoEntity.getInt(HebrewCalendar.YEAR_OF_ERA);
            if (i == Integer.MIN_VALUE) {
                chronoEntity.with((ChronoElement<ValidationElement>) ValidationElement.ERROR_MESSAGE, (ValidationElement) "Missing Hebrew year.");
                return null;
            }
            int i2 = 1;
            if (chronoEntity.contains(ParsedMonthElement.INSTANCE)) {
                int i3 = chronoEntity.getInt(ParsedMonthElement.INSTANCE);
                int i4 = AnonymousClass3.$SwitchMap$net$time4j$calendar$HebrewMonth$Order[((HebrewMonth.Order) attributeQuery.get(HebrewMonth.order(), HebrewMonth.Order.CIVIL)).ordinal()];
                if (i4 == 1) {
                    hebrewMonthValueOfCivil = HebrewMonth.valueOfCivil(i3, HebrewCalendar.isLeapYear(i));
                } else if (i4 == 2) {
                    hebrewMonthValueOfCivil = HebrewMonth.valueOfBiblical(i3, HebrewCalendar.isLeapYear(i));
                } else {
                    hebrewMonthValueOfCivil = HebrewMonth.valueOf(i3);
                }
            } else {
                hebrewMonthValueOfCivil = chronoEntity.contains(HebrewCalendar.MONTH_OF_YEAR) ? (HebrewMonth) chronoEntity.get(HebrewCalendar.MONTH_OF_YEAR) : null;
            }
            if (hebrewMonthValueOfCivil != null) {
                int i5 = chronoEntity.getInt(HebrewCalendar.DAY_OF_MONTH);
                if (i5 != Integer.MIN_VALUE) {
                    if (HebrewCalendar.CALSYS.isValid(HebrewEra.ANNO_MUNDI, i, hebrewMonthValueOfCivil.getValue(), i5)) {
                        return HebrewCalendar.of(i, hebrewMonthValueOfCivil, i5);
                    }
                    chronoEntity.with((ChronoElement<ValidationElement>) ValidationElement.ERROR_MESSAGE, (ValidationElement) "Invalid Hebrew date.");
                }
            } else {
                int i6 = chronoEntity.getInt(HebrewCalendar.DAY_OF_YEAR);
                if (i6 != Integer.MIN_VALUE) {
                    if (i6 > 0) {
                        boolean zIsLeapYear = HebrewCalendar.isLeapYear(i);
                        int i7 = 0;
                        while (i2 <= 13) {
                            if (i2 != 6 || zIsLeapYear) {
                                int iLengthOfMonth = HebrewCalendar.lengthOfMonth(i, HebrewMonth.valueOf(i2)) + i7;
                                if (i6 <= iLengthOfMonth) {
                                    return HebrewCalendar.of(i, HebrewMonth.valueOf(i2), i6 - i7);
                                }
                                i2++;
                                i7 = iLengthOfMonth;
                            }
                        }
                    }
                    chronoEntity.with((ChronoElement<ValidationElement>) ValidationElement.ERROR_MESSAGE, (ValidationElement) "Invalid Hebrew date.");
                }
            }
            return null;
        }

        @Override // net.time4j.engine.ChronoMerger
        public StartOfDay getDefaultStartOfDay() {
            return StartOfDay.EVENING;
        }

        @Override // net.time4j.engine.ChronoMerger
        public int getDefaultPivotYear() {
            return HebrewCalendar.nowInSystemTime().getYear() + 20;
        }
    }

    private static class HebrewUnitRule implements UnitRule<HebrewCalendar> {
        private final Unit unit;

        HebrewUnitRule(Unit unit) {
            this.unit = unit;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0084 A[PHI: r5
          0x0084: PHI (r5v3 int) = (r5v2 int), (r5v5 int) binds: [B:34:0x007d, B:27:0x006a] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // net.time4j.engine.UnitRule
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public net.time4j.calendar.HebrewCalendar addTo(net.time4j.calendar.HebrewCalendar r9, long r10) {
            /*
                r8 = this;
                int[] r0 = net.time4j.calendar.HebrewCalendar.AnonymousClass3.$SwitchMap$net$time4j$calendar$HebrewCalendar$Unit
                net.time4j.calendar.HebrewCalendar$Unit r1 = r8.unit
                int r1 = r1.ordinal()
                r0 = r0[r1]
                r1 = 1
                if (r0 == r1) goto L9e
                r2 = 2
                if (r0 == r2) goto L40
                r1 = 3
                if (r0 == r1) goto L23
                r1 = 4
                if (r0 != r1) goto L17
                goto L29
            L17:
                java.lang.UnsupportedOperationException r9 = new java.lang.UnsupportedOperationException
                net.time4j.calendar.HebrewCalendar$Unit r10 = r8.unit
                java.lang.String r10 = r10.name()
                r9.<init>(r10)
                throw r9
            L23:
                r0 = 7
                long r10 = net.time4j.base.MathUtils.safeMultiply(r10, r0)
            L29:
                net.time4j.calendar.EraYearMonthDaySystem r0 = net.time4j.calendar.HebrewCalendar.access$100()
                long r0 = r0.transform(r9)
                long r9 = net.time4j.base.MathUtils.safeAdd(r0, r10)
                net.time4j.calendar.EraYearMonthDaySystem r11 = net.time4j.calendar.HebrewCalendar.access$100()
                java.lang.Object r9 = r11.transform(r9)
                net.time4j.calendar.HebrewCalendar r9 = (net.time4j.calendar.HebrewCalendar) r9
                return r9
            L40:
                int r0 = net.time4j.calendar.HebrewCalendar.access$800(r9)
                net.time4j.calendar.HebrewMonth r2 = net.time4j.calendar.HebrewCalendar.access$1000(r9)
                int r2 = r2.getValue()
                long r3 = java.lang.Math.abs(r10)
            L50:
                r5 = 0
                int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r7 <= 0) goto L89
                int r5 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
                r6 = 6
                if (r5 <= 0) goto L70
                int r5 = r2 + 1
                if (r5 != r6) goto L68
                boolean r6 = net.time4j.calendar.HebrewCalendar.isLeapYear(r0)
                if (r6 != 0) goto L68
                int r2 = r2 + 2
                goto L85
            L68:
                r2 = 14
                if (r5 != r2) goto L84
                int r0 = r0 + 1
                r2 = r1
                goto L85
            L70:
                int r5 = r2 + (-1)
                if (r5 != r6) goto L7d
                boolean r6 = net.time4j.calendar.HebrewCalendar.isLeapYear(r0)
                if (r6 != 0) goto L7d
                int r2 = r2 + (-2)
                goto L85
            L7d:
                if (r5 != 0) goto L84
                int r0 = r0 + (-1)
                r2 = 13
                goto L85
            L84:
                r2 = r5
            L85:
                r5 = 1
                long r3 = r3 - r5
                goto L50
            L89:
                net.time4j.calendar.HebrewMonth r10 = net.time4j.calendar.HebrewMonth.valueOf(r2)
                int r9 = net.time4j.calendar.HebrewCalendar.access$900(r9)
                int r11 = net.time4j.calendar.HebrewCalendar.lengthOfMonth(r0, r10)
                int r9 = java.lang.Math.min(r9, r11)
                net.time4j.calendar.HebrewCalendar r9 = net.time4j.calendar.HebrewCalendar.of(r0, r10, r9)
                return r9
            L9e:
                int r0 = net.time4j.calendar.HebrewCalendar.access$800(r9)
                long r2 = (long) r0
                long r10 = net.time4j.base.MathUtils.safeAdd(r2, r10)
                int r10 = net.time4j.base.MathUtils.safeCast(r10)
                if (r10 < r1) goto Ld4
                r11 = 9999(0x270f, float:1.4012E-41)
                if (r10 > r11) goto Ld4
                net.time4j.calendar.HebrewMonth r11 = net.time4j.calendar.HebrewCalendar.access$1000(r9)
                net.time4j.calendar.HebrewMonth r0 = net.time4j.calendar.HebrewMonth.ADAR_I
                if (r11 != r0) goto Lc1
                boolean r0 = net.time4j.calendar.HebrewCalendar.isLeapYear(r10)
                if (r0 != 0) goto Lc1
                net.time4j.calendar.HebrewMonth r11 = net.time4j.calendar.HebrewMonth.SHEVAT
            Lc1:
                int r9 = net.time4j.calendar.HebrewCalendar.access$900(r9)
                int r0 = net.time4j.calendar.HebrewCalendar.lengthOfMonth(r10, r11)
                int r9 = java.lang.Math.min(r9, r0)
                net.time4j.calendar.HebrewCalendar r0 = new net.time4j.calendar.HebrewCalendar
                r1 = 0
                r0.<init>(r10, r11, r9)
                return r0
            Ld4:
                java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r11 = new java.lang.StringBuilder
                java.lang.String r0 = "Resulting year out of bounds: "
                r11.<init>(r0)
                java.lang.StringBuilder r10 = r11.append(r10)
                java.lang.String r10 = r10.toString()
                r9.<init>(r10)
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: net.time4j.calendar.HebrewCalendar.HebrewUnitRule.addTo(net.time4j.calendar.HebrewCalendar, long):net.time4j.calendar.HebrewCalendar");
        }

        @Override // net.time4j.engine.UnitRule
        public long between(HebrewCalendar hebrewCalendar, HebrewCalendar hebrewCalendar2) {
            HebrewCalendar hebrewCalendar3;
            HebrewCalendar hebrewCalendar4;
            int i = AnonymousClass3.$SwitchMap$net$time4j$calendar$HebrewCalendar$Unit[this.unit.ordinal()];
            if (i == 1) {
                int i2 = hebrewCalendar2.year - hebrewCalendar.year;
                if (i2 > 0) {
                    if (hebrewCalendar2.month.getValue() < hebrewCalendar.month.getValue() || (hebrewCalendar2.month.getValue() == hebrewCalendar.month.getValue() && hebrewCalendar2.dom < hebrewCalendar.dom)) {
                        i2--;
                    }
                } else if (i2 < 0 && (hebrewCalendar2.month.getValue() > hebrewCalendar.month.getValue() || (hebrewCalendar2.month.getValue() == hebrewCalendar.month.getValue() && hebrewCalendar2.dom > hebrewCalendar.dom))) {
                    i2++;
                }
                return i2;
            }
            if (i != 2) {
                if (i == 3) {
                    return Unit.DAYS.between(hebrewCalendar, hebrewCalendar2) / 7;
                }
                if (i == 4) {
                    return HebrewCalendar.CALSYS.transform((EraYearMonthDaySystem) hebrewCalendar2) - HebrewCalendar.CALSYS.transform((EraYearMonthDaySystem) hebrewCalendar);
                }
                throw new UnsupportedOperationException(this.unit.name());
            }
            boolean zIsAfter = hebrewCalendar.isAfter((CalendarDate) hebrewCalendar2);
            if (zIsAfter) {
                hebrewCalendar4 = hebrewCalendar;
                hebrewCalendar3 = hebrewCalendar2;
            } else {
                hebrewCalendar3 = hebrewCalendar;
                hebrewCalendar4 = hebrewCalendar2;
            }
            int i3 = hebrewCalendar3.year;
            int value = hebrewCalendar3.month.getValue();
            int i4 = 0;
            while (true) {
                if (i3 >= hebrewCalendar4.year && (i3 != hebrewCalendar4.year || value >= hebrewCalendar4.month.getValue())) {
                    break;
                }
                int i5 = value + 1;
                i4++;
                if (i5 == 6 && !HebrewCalendar.isLeapYear(i3)) {
                    value += 2;
                } else if (i5 == 14) {
                    i3++;
                    value = 1;
                } else {
                    value = i5;
                }
            }
            if (i4 > 0 && hebrewCalendar3.dom > hebrewCalendar4.dom) {
                i4--;
            }
            if (zIsAfter) {
                i4 = -i4;
            }
            return i4;
        }
    }

    /* renamed from: net.time4j.calendar.HebrewCalendar$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$net$time4j$calendar$HebrewCalendar$Unit;
        static final /* synthetic */ int[] $SwitchMap$net$time4j$calendar$HebrewMonth;
        static final /* synthetic */ int[] $SwitchMap$net$time4j$calendar$HebrewMonth$Order;

        static {
            int[] iArr = new int[Unit.values().length];
            $SwitchMap$net$time4j$calendar$HebrewCalendar$Unit = iArr;
            try {
                iArr[Unit.YEARS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$net$time4j$calendar$HebrewCalendar$Unit[Unit.MONTHS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$net$time4j$calendar$HebrewCalendar$Unit[Unit.WEEKS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$net$time4j$calendar$HebrewCalendar$Unit[Unit.DAYS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[HebrewMonth.values().length];
            $SwitchMap$net$time4j$calendar$HebrewMonth = iArr2;
            try {
                iArr2[HebrewMonth.IYAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$net$time4j$calendar$HebrewMonth[HebrewMonth.TAMUZ.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$net$time4j$calendar$HebrewMonth[HebrewMonth.ELUL.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$net$time4j$calendar$HebrewMonth[HebrewMonth.TEVET.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$net$time4j$calendar$HebrewMonth[HebrewMonth.ADAR_II.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$net$time4j$calendar$HebrewMonth[HebrewMonth.HESHVAN.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$net$time4j$calendar$HebrewMonth[HebrewMonth.KISLEV.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            int[] iArr3 = new int[HebrewMonth.Order.values().length];
            $SwitchMap$net$time4j$calendar$HebrewMonth$Order = iArr3;
            try {
                iArr3[HebrewMonth.Order.CIVIL.ordinal()] = 1;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$net$time4j$calendar$HebrewMonth$Order[HebrewMonth.Order.BIBLICAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    private static class SPX implements Externalizable {
        private static final int HEBREW_DATE = 12;
        private static final long serialVersionUID = 1;
        private transient Object obj;

        private Object readResolve() throws ObjectStreamException {
            return this.obj;
        }

        public SPX() {
        }

        SPX(Object obj) {
            this.obj = obj;
        }

        @Override // java.io.Externalizable
        public void writeExternal(ObjectOutput objectOutput) throws IOException {
            objectOutput.writeByte(12);
            writeHebrewDate(objectOutput);
        }

        @Override // java.io.Externalizable
        public void readExternal(ObjectInput objectInput) throws IOException {
            if (objectInput.readByte() == 12) {
                this.obj = readHebrewDate(objectInput);
                return;
            }
            throw new InvalidObjectException("Unknown calendar type.");
        }

        private void writeHebrewDate(ObjectOutput objectOutput) throws IOException {
            HebrewCalendar hebrewCalendar = (HebrewCalendar) this.obj;
            objectOutput.writeInt(hebrewCalendar.getYear());
            objectOutput.writeByte(hebrewCalendar.getMonth().getValue());
            objectOutput.writeByte(hebrewCalendar.getDayOfMonth());
        }

        private HebrewCalendar readHebrewDate(ObjectInput objectInput) throws IOException {
            return HebrewCalendar.of(objectInput.readInt(), HebrewMonth.valueOf(objectInput.readByte()), objectInput.readByte());
        }
    }
}
