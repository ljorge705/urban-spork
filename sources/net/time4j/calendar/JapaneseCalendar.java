package net.time4j.calendar;

import androidx.exifinterface.media.ExifInterface;
import java.io.DataInputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.text.ParsePosition;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import net.time4j.GeneralTimestamp;
import net.time4j.Moment;
import net.time4j.Month;
import net.time4j.PlainDate;
import net.time4j.PlainTime;
import net.time4j.SystemClock;
import net.time4j.Weekday;
import net.time4j.Weekmodel;
import net.time4j.base.GregorianMath;
import net.time4j.base.MathUtils;
import net.time4j.base.ResourceLoader;
import net.time4j.base.TimeSource;
import net.time4j.calendar.Nengo;
import net.time4j.calendar.service.GenericDatePatterns;
import net.time4j.calendar.service.StdIntegerDateElement;
import net.time4j.calendar.service.StdWeekdayElement;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.CalendarDate;
import net.time4j.engine.CalendarEra;
import net.time4j.engine.CalendarSystem;
import net.time4j.engine.Calendrical;
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoException;
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
import net.time4j.format.CalendarText;
import net.time4j.format.CalendarType;
import net.time4j.format.Leniency;
import net.time4j.format.LocalizedPatternSupport;
import net.time4j.format.NumberSystem;
import net.time4j.format.OutputContext;
import net.time4j.format.TextElement;
import net.time4j.format.TextWidth;
import net.time4j.format.internal.DualFormatElement;
import net.time4j.format.internal.DualFormatHelper;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;

@CalendarType("japanese")
/* loaded from: classes4.dex */
public final class JapaneseCalendar extends Calendrical<Unit, JapaneseCalendar> implements LocalizedPatternSupport {
    private static final Transformer CALSYS;

    @FormattableElement(format = "d")
    public static final StdCalendarElement<Integer, JapaneseCalendar> DAY_OF_MONTH;
    private static final int DAY_OF_MONTH_INDEX = 2;

    @FormattableElement(format = ExifInterface.LONGITUDE_EAST)
    public static final StdCalendarElement<Weekday, JapaneseCalendar> DAY_OF_WEEK;

    @FormattableElement(format = "D")
    public static final StdCalendarElement<Integer, JapaneseCalendar> DAY_OF_YEAR;
    private static final int DAY_OF_YEAR_INDEX = 3;
    private static final TimeAxis<Unit, JapaneseCalendar> ENGINE;
    private static final long EPOCH_1873 = -36158;

    @FormattableElement(format = "G")
    public static final TextElement<Nengo> ERA;
    private static final int KOKI_INDEX = 5;
    public static final ChronoElement<Integer> KOKI_YEAR;
    private static final byte[] LEAP_INDICATORS;
    private static final int[] LUNISOLAR_MONTHS;
    public static final StdCalendarElement<Integer, JapaneseCalendar> MONTH_AS_ORDINAL;
    private static final int MONTH_AS_ORDINAL_INDEX = 1;

    @FormattableElement(alt = "L", format = "M")
    public static final TextElement<EastAsianMonth> MONTH_OF_YEAR;
    private static final int MRD = 1000000000;
    private static final int RELATED_GREGORIAN_YEAR_INDEX = 4;
    private static final long[] START_OF_YEAR;

    @FormattableElement(format = "F")
    public static final OrdinalWeekdayElement<JapaneseCalendar> WEEKDAY_IN_MONTH;
    private static final WeekdayInMonthElement<JapaneseCalendar> WIM_ELEMENT;

    @FormattableElement(format = "y")
    public static final StdCalendarElement<Integer, JapaneseCalendar> YEAR_OF_ERA;
    private static final int YEAR_OF_NENGO_INDEX = 0;
    private static final long serialVersionUID = -153630575450868922L;
    private final transient int dayOfMonth;
    private final transient int dayOfYear;
    private final transient EastAsianMonth month;
    private final transient Nengo nengo;
    private final transient int relgregyear;

    public static TimeAxis<Unit, JapaneseCalendar> axis() {
        return ENGINE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.time4j.engine.TimePoint, net.time4j.engine.ChronoEntity
    public TimeAxis<Unit, JapaneseCalendar> getChronology() {
        return ENGINE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.time4j.engine.ChronoEntity
    public JapaneseCalendar getContext() {
        return this;
    }

    public int getDayOfMonth() {
        return this.dayOfMonth;
    }

    public int getDayOfYear() {
        return this.dayOfYear;
    }

    public Nengo getEra() {
        return this.nengo;
    }

    public EastAsianMonth getMonth() {
        return this.month;
    }

    @Override // net.time4j.engine.Calendrical, net.time4j.engine.TimePoint
    public int hashCode() {
        return (this.relgregyear * 17) + (this.dayOfYear * 31);
    }

    static {
        InputStream inputStreamLoad = ResourceLoader.getInstance().load(ResourceLoader.getInstance().locate("calendar", JapaneseCalendar.class, "data/tsuchihashi.data"), true);
        if (inputStreamLoad == null) {
            try {
                try {
                    inputStreamLoad = ResourceLoader.getInstance().load(JapaneseCalendar.class, "data/tsuchihashi.data", true);
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            } finally {
            }
        }
        DataInputStream dataInputStream = new DataInputStream(inputStreamLoad);
        byte[] bArr = new byte[1172];
        int[] iArr = new int[1172];
        long[] jArr = new long[1172];
        long j = -464176;
        int i = 0;
        for (int i2 = 1172; i < i2; i2 = 1172) {
            byte b = dataInputStream.readByte();
            int i3 = dataInputStream.readShort();
            bArr[i] = b;
            iArr[i] = i3;
            jArr[i] = j;
            int i4 = 1;
            int i5 = 0;
            while (true) {
                if (i4 <= (b == 0 ? 12 : 13)) {
                    i5 += (i3 & 1) == 1 ? 30 : 29;
                    i3 >>>= 1;
                    i4++;
                }
            }
            j += i5;
            i++;
        }
        LEAP_INDICATORS = bArr;
        LUNISOLAR_MONTHS = iArr;
        START_OF_YEAR = jArr;
        if (inputStreamLoad != null) {
            try {
                inputStreamLoad.close();
            } catch (IOException e2) {
                e2.printStackTrace(System.err);
            }
        }
        Nengo.Element element = Nengo.Element.SINGLETON;
        ERA = element;
        YearOfNengoElement yearOfNengoElement = new YearOfNengoElement();
        YEAR_OF_ERA = yearOfNengoElement;
        StdIntegerDateElement stdIntegerDateElement = new StdIntegerDateElement("KOKI_YEAR", JapaneseCalendar.class, 1361, 1000000659, (char) 0, null, null);
        KOKI_YEAR = stdIntegerDateElement;
        MonthPrimitiveElement monthPrimitiveElement = new MonthPrimitiveElement();
        MONTH_OF_YEAR = monthPrimitiveElement;
        StdIntegerDateElement stdIntegerDateElement2 = new StdIntegerDateElement("MONTH_AS_ORDINAL", JapaneseCalendar.class, 1, 12, (char) 0, null, null);
        MONTH_AS_ORDINAL = stdIntegerDateElement2;
        StdIntegerDateElement stdIntegerDateElement3 = new StdIntegerDateElement("DAY_OF_MONTH", JapaneseCalendar.class, 1, 31, 'd');
        DAY_OF_MONTH = stdIntegerDateElement3;
        StdIntegerDateElement stdIntegerDateElement4 = new StdIntegerDateElement("DAY_OF_YEAR", JapaneseCalendar.class, 1, 365, 'D');
        DAY_OF_YEAR = stdIntegerDateElement4;
        StdWeekdayElement stdWeekdayElement = new StdWeekdayElement(JapaneseCalendar.class, getDefaultWeekmodel());
        DAY_OF_WEEK = stdWeekdayElement;
        WeekdayInMonthElement<JapaneseCalendar> weekdayInMonthElement = new WeekdayInMonthElement<>(JapaneseCalendar.class, stdIntegerDateElement3, stdWeekdayElement);
        WIM_ELEMENT = weekdayInMonthElement;
        WEEKDAY_IN_MONTH = weekdayInMonthElement;
        Transformer transformer = new Transformer();
        CALSYS = transformer;
        ENGINE = TimeAxis.Builder.setUp(Unit.class, JapaneseCalendar.class, new Merger(), transformer).appendElement(element, new NengoRule(), Unit.ERAS).appendElement(yearOfNengoElement, new IntegerRule(0), Unit.YEARS).appendElement(monthPrimitiveElement, MonthPrimitiveElement.SINGLETON_JP, Unit.MONTHS).appendElement(stdIntegerDateElement2, new IntegerRule(1), Unit.MONTHS).appendElement(stdIntegerDateElement3, new IntegerRule(2), Unit.DAYS).appendElement(stdIntegerDateElement4, new IntegerRule(3), Unit.DAYS).appendElement(stdWeekdayElement, new WeekdayRule(getDefaultWeekmodel(), new ChronoFunction<JapaneseCalendar, CalendarSystem<JapaneseCalendar>>() { // from class: net.time4j.calendar.JapaneseCalendar.1
            @Override // net.time4j.engine.ChronoFunction
            public CalendarSystem<JapaneseCalendar> apply(JapaneseCalendar japaneseCalendar) {
                return JapaneseCalendar.CALSYS;
            }
        }), Unit.DAYS).appendElement((ChronoElement) weekdayInMonthElement, WeekdayInMonthElement.getRule(weekdayInMonthElement)).appendElement(stdIntegerDateElement, new IntegerRule(5), Unit.YEARS).appendElement((ChronoElement) CommonElements.RELATED_GREGORIAN_YEAR, (ElementRule) new IntegerRule(4)).appendUnit(Unit.ERAS, new JapaneseUnitRule(Unit.ERAS), Unit.ERAS.getLength()).appendUnit(Unit.YEARS, new JapaneseUnitRule(Unit.YEARS), Unit.YEARS.getLength()).appendUnit(Unit.MONTHS, new JapaneseUnitRule(Unit.MONTHS), Unit.MONTHS.getLength()).appendUnit(Unit.WEEKS, new JapaneseUnitRule(Unit.WEEKS), Unit.WEEKS.getLength(), Collections.singleton(Unit.DAYS)).appendUnit(Unit.DAYS, new JapaneseUnitRule(Unit.DAYS), Unit.DAYS.getLength(), Collections.singleton(Unit.WEEKS)).build();
    }

    private JapaneseCalendar(Nengo nengo, int i, int i2) {
        this(nengo, i, i2, getMonth(i, i2), getDayOfMonth(i, i2));
    }

    private JapaneseCalendar(Nengo nengo, int i, int i2, EastAsianMonth eastAsianMonth, int i3) {
        this.nengo = nengo;
        this.relgregyear = i;
        this.dayOfYear = i2;
        this.month = eastAsianMonth;
        this.dayOfMonth = i3;
    }

    public static JapaneseCalendar ofGregorian(Nengo nengo, int i, int i2, int i3) {
        if (!nengo.isModern() || (nengo == Nengo.MEIJI && i < 6)) {
            throw new IllegalArgumentException("Cannot create modern calendar with lunisolar calendar year.");
        }
        return of(nengo, i, EastAsianMonth.valueOf(i2), i3, Leniency.SMART);
    }

    public static JapaneseCalendar of(Nengo nengo, int i, EastAsianMonth eastAsianMonth, int i2) {
        return of(nengo, i, eastAsianMonth, i2, Leniency.SMART);
    }

    public static JapaneseCalendar of(Nengo nengo, int i, EastAsianMonth eastAsianMonth, int i2, Leniency leniency) {
        Nengo nengo2;
        if (i < 1) {
            throw new IllegalArgumentException("Year of nengo smaller than 1: " + i);
        }
        if (i2 < 1) {
            throw new IllegalArgumentException("Day of month smaller than 1: " + i2);
        }
        int firstRelatedGregorianYear = nengo.getFirstRelatedGregorianYear() + i;
        int i3 = firstRelatedGregorianYear - 1;
        Nengo nengoFindNext = nengo.findNext();
        if (nengoFindNext != null && nengoFindNext.getFirstRelatedGregorianYear() < i3) {
            throw new IllegalArgumentException("Year of nengo out of range: " + nengo + "/" + i);
        }
        int lengthOfMonth = 0;
        if (i3 >= 1873) {
            if (eastAsianMonth.isLeap()) {
                throw new IllegalArgumentException("Lunisolar leap month not valid in modern times: " + eastAsianMonth);
            }
            if (i2 > GregorianMath.getLengthOfMonth(i3, eastAsianMonth.getNumber())) {
                throw new IllegalArgumentException("Day of month out of range: " + i2);
            }
            int number = eastAsianMonth.getNumber();
            for (int i4 = 1; i4 < number; i4++) {
                lengthOfMonth += GregorianMath.getLengthOfMonth(i3, i4);
            }
            lengthOfMonth += i2;
        } else {
            int i5 = firstRelatedGregorianYear - 702;
            int i6 = LUNISOLAR_MONTHS[i5];
            int monthIndex = getMonthIndex(i3, eastAsianMonth);
            if (eastAsianMonth.isLeap() && monthIndex != LEAP_INDICATORS[i5]) {
                throw new IllegalArgumentException("Invalid leap month: " + eastAsianMonth);
            }
            for (int i7 = 1; i7 <= monthIndex; i7++) {
                int i8 = (i6 & 1) == 1 ? 30 : 29;
                if (i7 != monthIndex) {
                    lengthOfMonth += i8;
                    i6 >>>= 1;
                } else {
                    if (i2 > i8) {
                        throw new IllegalArgumentException("Day of month out of range: " + i2);
                    }
                    lengthOfMonth += i2;
                }
            }
        }
        int i9 = lengthOfMonth;
        if (i3 == 1872 && eastAsianMonth.getNumber() == 12 && i2 >= 3) {
            if (leniency.isStrict()) {
                throw new IllegalArgumentException("Last month of lunisolar calendar had only 2 days.");
            }
            int i10 = i2 - 2;
            return new JapaneseCalendar(Nengo.MEIJI, 1873, i10, EastAsianMonth.valueOf(1), i10);
        }
        long jTransform = transform(i3, i9);
        CALSYS.check(jTransform);
        Nengo nengoFindBestNengo = findBestNengo(nengo.matches(Nengo.Selector.NORTHERN_COURT), i3, jTransform);
        int i11 = AnonymousClass2.$SwitchMap$net$time4j$format$Leniency[leniency.ordinal()];
        if (i11 != 1) {
            if (i11 == 2) {
                nengo2 = nengoFindBestNengo;
            }
            return new JapaneseCalendar(nengo2, i3, i9, eastAsianMonth, i2);
        }
        if (nengoFindBestNengo != nengo) {
            throw new IllegalArgumentException("Nengo should be: " + nengoFindBestNengo + ", but was: " + nengo);
        }
        nengo2 = nengo;
        return new JapaneseCalendar(nengo2, i3, i9, eastAsianMonth, i2);
    }

    public static JapaneseCalendar nowInSystemTime() {
        return (JapaneseCalendar) SystemClock.inLocalView().now(axis());
    }

    public int getYear() {
        return (this.relgregyear - this.nengo.getFirstRelatedGregorianYear()) + 1;
    }

    public Weekday getDayOfWeek() {
        return Weekday.valueOf(MathUtils.floorModulo(CALSYS.transform(this) + 5, 7) + 1);
    }

    public int lengthOfMonth() {
        return getLengthOfMonth(this.relgregyear, this.month);
    }

    public int lengthOfYear() {
        return getLengthOfYear(this.relgregyear);
    }

    public boolean isLeapYear() {
        int i = this.relgregyear;
        if (i >= 1873) {
            return GregorianMath.isLeapYear(i);
        }
        return LEAP_INDICATORS[i + (-701)] > 0;
    }

    public GeneralTimestamp<JapaneseCalendar> at(PlainTime plainTime) {
        return GeneralTimestamp.of(this, plainTime);
    }

    public GeneralTimestamp<JapaneseCalendar> atTime(int i, int i2) {
        return at(PlainTime.of(i, i2));
    }

    @Override // net.time4j.engine.Calendrical, net.time4j.engine.TimePoint
    public int compareTo(JapaneseCalendar japaneseCalendar) {
        int iCompareTo = super.compareTo(japaneseCalendar);
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        int value = this.nengo.getValue() - japaneseCalendar.nengo.getValue();
        if (value != 0) {
            return value;
        }
        boolean zMatches = this.nengo.matches(Nengo.Selector.NORTHERN_COURT);
        boolean zMatches2 = japaneseCalendar.nengo.matches(Nengo.Selector.NORTHERN_COURT);
        return (zMatches || !zMatches2) ? (!zMatches || zMatches2) ? 0 : 1 : -1;
    }

    @Override // net.time4j.engine.Calendrical, net.time4j.engine.TimePoint
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof JapaneseCalendar)) {
            return false;
        }
        JapaneseCalendar japaneseCalendar = (JapaneseCalendar) obj;
        return this.relgregyear == japaneseCalendar.relgregyear && this.dayOfYear == japaneseCalendar.dayOfYear && this.nengo == japaneseCalendar.nengo && this.dayOfMonth == japaneseCalendar.dayOfMonth && this.month.equals(japaneseCalendar.month);
    }

    @Override // net.time4j.engine.TimePoint
    public String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.nengo.getDisplayName(Locale.ROOT));
        sb.append('-');
        sb.append(getYear());
        sb.append('(');
        sb.append(this.relgregyear);
        sb.append(")-");
        if (this.month.isLeap()) {
            sb.append('*');
        }
        int number = this.month.getNumber();
        if (this.relgregyear >= 1873 && number < 10) {
            sb.append('0');
        }
        sb.append(number);
        sb.append('-');
        int dayOfMonth = getDayOfMonth();
        if (dayOfMonth < 10) {
            sb.append('0');
        }
        sb.append(dayOfMonth);
        return sb.toString();
    }

    public static Weekmodel getDefaultWeekmodel() {
        return Weekmodel.of(Locale.JAPAN);
    }

    @Override // net.time4j.engine.Calendrical
    protected int compareByTime(CalendarDate calendarDate) {
        JapaneseCalendar japaneseCalendarTransform;
        if (calendarDate instanceof JapaneseCalendar) {
            japaneseCalendarTransform = (JapaneseCalendar) JapaneseCalendar.class.cast(calendarDate);
        } else {
            japaneseCalendarTransform = CALSYS.transform(calendarDate.getDaysSinceEpochUTC());
        }
        int i = this.relgregyear;
        int i2 = japaneseCalendarTransform.relgregyear;
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        int i3 = this.dayOfYear;
        int i4 = japaneseCalendarTransform.dayOfYear;
        if (i3 < i4) {
            return -1;
        }
        return i3 > i4 ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JapaneseCalendar tryWithNorthernCourt() {
        int i = this.relgregyear;
        if (i < 1332 || i >= 1394) {
            return this;
        }
        Nengo nengoOfRelatedGregorianYear = Nengo.ofRelatedGregorianYear(i, Nengo.Selector.NORTHERN_COURT);
        while (nengoOfRelatedGregorianYear.getStartAsDaysSinceEpochUTC() > getDaysSinceEpochUTC()) {
            nengoOfRelatedGregorianYear = nengoOfRelatedGregorianYear.findPrevious();
        }
        return new JapaneseCalendar(nengoOfRelatedGregorianYear, this.relgregyear, this.dayOfYear, this.month, this.dayOfMonth);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Nengo findBestNengo(boolean z, int i, long j) {
        Nengo nengoOfRelatedGregorianYear;
        Nengo nengoFindPrevious;
        if (z && i >= 1332 && i < 1394) {
            nengoOfRelatedGregorianYear = Nengo.ofRelatedGregorianYear(i, Nengo.Selector.NORTHERN_COURT);
        } else {
            nengoOfRelatedGregorianYear = Nengo.ofRelatedGregorianYear(i, Nengo.Selector.OFFICIAL);
        }
        while (nengoOfRelatedGregorianYear.getStartAsDaysSinceEpochUTC() > j && (nengoFindPrevious = nengoOfRelatedGregorianYear.findPrevious()) != null) {
            nengoOfRelatedGregorianYear = nengoFindPrevious;
        }
        return nengoOfRelatedGregorianYear;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static EastAsianMonth getMonth(int i, int i2) {
        if (i2 >= 1) {
            int lengthOfMonth = 0;
            if (i >= 1873) {
                for (int i3 = 1; i3 <= 12; i3++) {
                    lengthOfMonth += GregorianMath.getLengthOfMonth(i, i3);
                    if (lengthOfMonth >= i2) {
                        return EastAsianMonth.valueOf(i3);
                    }
                }
            } else {
                int i4 = i - 701;
                byte b = LEAP_INDICATORS[i4];
                int i5 = LUNISOLAR_MONTHS[i4];
                int i6 = b != 0 ? 13 : 12;
                int i7 = 1;
                while (i7 <= i6) {
                    lengthOfMonth += (i5 & 1) == 1 ? 30 : 29;
                    i5 >>>= 1;
                    if (lengthOfMonth >= i2) {
                        EastAsianMonth eastAsianMonthValueOf = EastAsianMonth.valueOf((b <= 0 || b > i7) ? i7 : i7 - 1);
                        return i7 == b ? eastAsianMonthValueOf.withLeap() : eastAsianMonthValueOf;
                    }
                    i7++;
                }
            }
        }
        throw new IllegalArgumentException("Day of year out of range: " + i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getLengthOfMonth(int i, EastAsianMonth eastAsianMonth) {
        if (i >= 1873) {
            return GregorianMath.getLengthOfMonth(i, eastAsianMonth.getNumber());
        }
        if (i == 1872 && eastAsianMonth.getNumber() == 12) {
            return 2;
        }
        int monthIndex = getMonthIndex(i, eastAsianMonth);
        int i2 = LUNISOLAR_MONTHS[i - 701];
        for (int i3 = 1; i3 <= monthIndex; i3++) {
            if (i3 == monthIndex) {
                return (i2 & 1) == 1 ? 30 : 29;
            }
            i2 >>>= 1;
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getLengthOfYear(int i) {
        if (i >= 1873) {
            return GregorianMath.isLeapYear(i) ? 366 : 365;
        }
        if (i == 1872) {
            return (int) (EPOCH_1873 - START_OF_YEAR[1171]);
        }
        int i2 = i - 701;
        int i3 = LUNISOLAR_MONTHS[i2];
        int i4 = LEAP_INDICATORS[i2] == 0 ? 12 : 13;
        int i5 = 0;
        for (int i6 = 1; i6 <= i4; i6++) {
            i5 += (i3 & 1) == 1 ? 30 : 29;
            i3 >>>= 1;
        }
        return i5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getMonthIndex(int i, EastAsianMonth eastAsianMonth) {
        int number = eastAsianMonth.getNumber();
        if (i >= 1873) {
            return number;
        }
        byte b = LEAP_INDICATORS[i - 701];
        return (eastAsianMonth.isLeap() || (b > 0 && number >= b)) ? number + 1 : number;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getDayOfMonth(int i, int i2) {
        EastAsianMonth month = getMonth(i, i2);
        if (i >= 1873) {
            int number = month.getNumber();
            for (int i3 = 1; i3 < number; i3++) {
                i2 -= GregorianMath.getLengthOfMonth(i, i3);
            }
        } else {
            int monthIndex = getMonthIndex(i, month);
            int i4 = LUNISOLAR_MONTHS[i - 701];
            for (int i5 = 1; i5 < monthIndex; i5++) {
                i2 -= (i4 & 1) == 1 ? 30 : 29;
                i4 >>>= 1;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getArrayIndex(long j) {
        int length = START_OF_YEAR.length - 1;
        int i = 0;
        while (i <= length) {
            int i2 = (i + length) >> 1;
            if (START_OF_YEAR[i2] <= j) {
                i = i2 + 1;
            } else {
                length = i2 - 1;
            }
        }
        return i - 1;
    }

    static long transform(int i, int i2) {
        if (i >= 1873) {
            return PlainDate.of(i, i2).getDaysSinceEpochUTC();
        }
        return (START_OF_YEAR[i - 701] + i2) - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JapaneseCalendar create(JapaneseCalendar japaneseCalendar, int i, EastAsianMonth eastAsianMonth, int i2) {
        Nengo nengoOfRelatedGregorianYear = Nengo.ofRelatedGregorianYear(i);
        JapaneseCalendar japaneseCalendarOf = of(nengoOfRelatedGregorianYear, (i - nengoOfRelatedGregorianYear.getFirstRelatedGregorianYear()) + 1, eastAsianMonth, i2, Leniency.SMART);
        return japaneseCalendar.nengo.matches(Nengo.Selector.NORTHERN_COURT) ? japaneseCalendarOf.tryWithNorthernCourt() : japaneseCalendarOf;
    }

    private Object writeReplace() {
        return new SPX(this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        throw new InvalidObjectException("Serialization proxy required.");
    }

    public enum Unit implements ChronoUnit {
        ERAS(2.147483647E9d),
        YEARS(3.1556952E7d),
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

        public long between(JapaneseCalendar japaneseCalendar, JapaneseCalendar japaneseCalendar2) {
            return japaneseCalendar.until(japaneseCalendar2, (JapaneseCalendar) this);
        }
    }

    private static class Transformer implements CalendarSystem<JapaneseCalendar> {
        @Override // net.time4j.engine.CalendarSystem
        public long getMaximumSinceUTC() {
            return 365241779741L;
        }

        private Transformer() {
        }

        @Override // net.time4j.engine.CalendarSystem
        public JapaneseCalendar transform(long j) {
            if (j < JapaneseCalendar.EPOCH_1873) {
                int arrayIndex = JapaneseCalendar.getArrayIndex(j);
                if (arrayIndex < 0) {
                    throw new IllegalArgumentException("Out of bounds: " + j);
                }
                int i = arrayIndex + 701;
                return new JapaneseCalendar(JapaneseCalendar.findBestNengo(false, i, j), i, (int) ((j - JapaneseCalendar.START_OF_YEAR[arrayIndex]) + 1));
            }
            PlainDate plainDateOf = PlainDate.of(j, EpochDays.UTC);
            int year = plainDateOf.getYear();
            return new JapaneseCalendar(JapaneseCalendar.findBestNengo(false, year, j), year, plainDateOf.getDayOfYear(), EastAsianMonth.valueOf(plainDateOf.getMonth()), plainDateOf.getDayOfMonth());
        }

        @Override // net.time4j.engine.CalendarSystem
        public long transform(JapaneseCalendar japaneseCalendar) {
            return JapaneseCalendar.transform(japaneseCalendar.relgregyear, japaneseCalendar.dayOfYear);
        }

        @Override // net.time4j.engine.CalendarSystem
        public long getMinimumSinceUTC() {
            return JapaneseCalendar.START_OF_YEAR[0];
        }

        @Override // net.time4j.engine.CalendarSystem
        public List<CalendarEra> getEras() {
            return Nengo.list();
        }

        void check(long j) {
            if (j < getMinimumSinceUTC() || j > getMaximumSinceUTC()) {
                throw new IllegalArgumentException("Japanese calendar out of supported range.");
            }
        }
    }

    private static class JapaneseUnitRule implements UnitRule<JapaneseCalendar> {
        private final Unit unit;

        JapaneseUnitRule(Unit unit) {
            this.unit = unit;
        }

        /* JADX WARN: Removed duplicated region for block: B:34:0x0087 A[PHI: r2
          0x0087: PHI (r2v17 int) = (r2v12 int), (r2v18 int) binds: [B:40:0x009d, B:32:0x0084] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // net.time4j.engine.UnitRule
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public net.time4j.calendar.JapaneseCalendar addTo(net.time4j.calendar.JapaneseCalendar r12, long r13) {
            /*
                Method dump skipped, instructions count: 364
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: net.time4j.calendar.JapaneseCalendar.JapaneseUnitRule.addTo(net.time4j.calendar.JapaneseCalendar, long):net.time4j.calendar.JapaneseCalendar");
        }

        /* JADX WARN: Removed duplicated region for block: B:41:0x00a6  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x00b9 A[PHI: r4
          0x00b9: PHI (r4v9 int) = (r4v4 int), (r4v10 int) binds: [B:44:0x00b6, B:38:0x00a1] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // net.time4j.engine.UnitRule
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public long between(net.time4j.calendar.JapaneseCalendar r12, net.time4j.calendar.JapaneseCalendar r13) {
            /*
                Method dump skipped, instructions count: 293
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: net.time4j.calendar.JapaneseCalendar.JapaneseUnitRule.between(net.time4j.calendar.JapaneseCalendar, net.time4j.calendar.JapaneseCalendar):long");
        }

        private static JapaneseCalendar erasAdded(JapaneseCalendar japaneseCalendar, long j) {
            int firstRelatedGregorianYear;
            Nengo nengoOfRelatedGregorianYear = japaneseCalendar.nengo;
            int year = japaneseCalendar.getYear();
            EastAsianMonth eastAsianMonthValueOf = japaneseCalendar.month;
            int i = japaneseCalendar.dayOfMonth;
            if (nengoOfRelatedGregorianYear.matches(Nengo.Selector.NORTHERN_COURT)) {
                nengoOfRelatedGregorianYear = Nengo.ofRelatedGregorianYear(japaneseCalendar.relgregyear);
                year = (japaneseCalendar.relgregyear - nengoOfRelatedGregorianYear.getFirstRelatedGregorianYear()) + 1;
            }
            Nengo nengoOfIndexOfficial = Nengo.ofIndexOfficial(MathUtils.safeAdd(nengoOfRelatedGregorianYear.getIndexOfficial(), MathUtils.safeCast(j)));
            Nengo nengoFindNext = nengoOfIndexOfficial.findNext();
            if (nengoFindNext != null && year > (firstRelatedGregorianYear = (nengoFindNext.getFirstRelatedGregorianYear() - nengoOfIndexOfficial.getFirstRelatedGregorianYear()) + 1)) {
                year = firstRelatedGregorianYear;
            }
            int firstRelatedGregorianYear2 = (year - 1) + nengoOfIndexOfficial.getFirstRelatedGregorianYear();
            if (firstRelatedGregorianYear2 >= 1873) {
                if (eastAsianMonthValueOf.isLeap()) {
                    eastAsianMonthValueOf = EastAsianMonth.valueOf(eastAsianMonthValueOf.getNumber());
                }
            } else if (eastAsianMonthValueOf.isLeap() && JapaneseCalendar.LEAP_INDICATORS[firstRelatedGregorianYear2 - 701] == 0) {
                eastAsianMonthValueOf = EastAsianMonth.valueOf(eastAsianMonthValueOf.getNumber());
            }
            int lengthOfMonth = JapaneseCalendar.getLengthOfMonth(firstRelatedGregorianYear2, eastAsianMonthValueOf);
            if (i > lengthOfMonth) {
                i = lengthOfMonth;
            }
            return JapaneseCalendar.of(nengoOfIndexOfficial, year, eastAsianMonthValueOf, i);
        }

        private static int erasBetween(JapaneseCalendar japaneseCalendar, JapaneseCalendar japaneseCalendar2) {
            Nengo nengoOfRelatedGregorianYear = japaneseCalendar.nengo;
            int year = japaneseCalendar.getYear();
            int monthIndex = JapaneseCalendar.getMonthIndex(japaneseCalendar.relgregyear, japaneseCalendar.month);
            int i = japaneseCalendar.dayOfMonth;
            if (nengoOfRelatedGregorianYear.matches(Nengo.Selector.NORTHERN_COURT)) {
                nengoOfRelatedGregorianYear = Nengo.ofRelatedGregorianYear(japaneseCalendar.relgregyear);
                year = (japaneseCalendar.relgregyear - nengoOfRelatedGregorianYear.getFirstRelatedGregorianYear()) + 1;
            }
            Nengo nengoOfRelatedGregorianYear2 = japaneseCalendar2.nengo;
            int year2 = japaneseCalendar2.getYear();
            int monthIndex2 = JapaneseCalendar.getMonthIndex(japaneseCalendar2.relgregyear, japaneseCalendar2.month);
            int i2 = japaneseCalendar2.dayOfMonth;
            if (nengoOfRelatedGregorianYear2.matches(Nengo.Selector.NORTHERN_COURT)) {
                nengoOfRelatedGregorianYear2 = Nengo.ofRelatedGregorianYear(japaneseCalendar2.relgregyear);
                year2 = (japaneseCalendar2.relgregyear - nengoOfRelatedGregorianYear2.getFirstRelatedGregorianYear()) + 1;
            }
            int indexOfficial = nengoOfRelatedGregorianYear2.getIndexOfficial() - nengoOfRelatedGregorianYear.getIndexOfficial();
            if (indexOfficial > 0) {
                if (year <= year2) {
                    if (year != year2) {
                        return indexOfficial;
                    }
                    if (monthIndex <= monthIndex2 && (monthIndex != monthIndex2 || i <= i2)) {
                        return indexOfficial;
                    }
                }
                return indexOfficial - 1;
            }
            if (indexOfficial >= 0) {
                return indexOfficial;
            }
            if (year >= year2) {
                if (year != year2) {
                    return indexOfficial;
                }
                if (monthIndex >= monthIndex2 && (monthIndex != monthIndex2 || i >= i2)) {
                    return indexOfficial;
                }
            }
            return indexOfficial + 1;
        }

        private static void checkAmountOfMonths(long j) {
            if (Math.abs(j) >= 25000) {
                throw new ArithmeticException("Month arithmetic limited to delta smaller than 25000.");
            }
        }
    }

    /* renamed from: net.time4j.calendar.JapaneseCalendar$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$net$time4j$calendar$JapaneseCalendar$Unit;
        static final /* synthetic */ int[] $SwitchMap$net$time4j$format$Leniency;

        static {
            int[] iArr = new int[Unit.values().length];
            $SwitchMap$net$time4j$calendar$JapaneseCalendar$Unit = iArr;
            try {
                iArr[Unit.ERAS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$net$time4j$calendar$JapaneseCalendar$Unit[Unit.YEARS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$net$time4j$calendar$JapaneseCalendar$Unit[Unit.MONTHS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$net$time4j$calendar$JapaneseCalendar$Unit[Unit.WEEKS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$net$time4j$calendar$JapaneseCalendar$Unit[Unit.DAYS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[Leniency.values().length];
            $SwitchMap$net$time4j$format$Leniency = iArr2;
            try {
                iArr2[Leniency.STRICT.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$net$time4j$format$Leniency[Leniency.SMART.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private static class NengoRule implements ElementRule<JapaneseCalendar, Nengo> {
        @Override // net.time4j.engine.ElementRule
        /* renamed from: isValid, reason: avoid collision after fix types in other method */
        public boolean isValid2(JapaneseCalendar japaneseCalendar, Nengo nengo) {
            return nengo != null;
        }

        private NengoRule() {
        }

        @Override // net.time4j.engine.ElementRule
        public Nengo getValue(JapaneseCalendar japaneseCalendar) {
            return japaneseCalendar.nengo;
        }

        @Override // net.time4j.engine.ElementRule
        public Nengo getMinimum(JapaneseCalendar japaneseCalendar) {
            return JapaneseCalendar.ERA.getDefaultMinimum();
        }

        @Override // net.time4j.engine.ElementRule
        public Nengo getMaximum(JapaneseCalendar japaneseCalendar) {
            return JapaneseCalendar.ERA.getDefaultMaximum();
        }

        @Override // net.time4j.engine.ElementRule
        /* renamed from: withValue, reason: avoid collision after fix types in other method */
        public JapaneseCalendar withValue2(JapaneseCalendar japaneseCalendar, Nengo nengo, boolean z) {
            int firstRelatedGregorianYear;
            int year = japaneseCalendar.getYear();
            EastAsianMonth eastAsianMonthValueOf = japaneseCalendar.month;
            int i = japaneseCalendar.dayOfMonth;
            Nengo nengoFindNext = nengo.findNext();
            if (nengoFindNext != null && year > (firstRelatedGregorianYear = (nengoFindNext.getFirstRelatedGregorianYear() - nengo.getFirstRelatedGregorianYear()) + 1)) {
                year = firstRelatedGregorianYear;
            }
            int firstRelatedGregorianYear2 = (year - 1) + nengo.getFirstRelatedGregorianYear();
            if (firstRelatedGregorianYear2 >= 1873) {
                if (eastAsianMonthValueOf.isLeap()) {
                    eastAsianMonthValueOf = EastAsianMonth.valueOf(eastAsianMonthValueOf.getNumber());
                }
            } else if (eastAsianMonthValueOf.isLeap() && JapaneseCalendar.LEAP_INDICATORS[firstRelatedGregorianYear2 - 701] == 0) {
                eastAsianMonthValueOf = EastAsianMonth.valueOf(eastAsianMonthValueOf.getNumber());
            }
            int lengthOfMonth = JapaneseCalendar.getLengthOfMonth(firstRelatedGregorianYear2, eastAsianMonthValueOf);
            if (i > lengthOfMonth) {
                i = lengthOfMonth;
            }
            return JapaneseCalendar.of(nengo, year, eastAsianMonthValueOf, i, z ? Leniency.LAX : Leniency.SMART);
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(JapaneseCalendar japaneseCalendar) {
            return JapaneseCalendar.YEAR_OF_ERA;
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(JapaneseCalendar japaneseCalendar) {
            return JapaneseCalendar.YEAR_OF_ERA;
        }
    }

    private static class IntegerRule implements IntElementRule<JapaneseCalendar> {
        private final int index;

        IntegerRule(int i) {
            this.index = i;
        }

        @Override // net.time4j.engine.IntElementRule
        public int getInt(JapaneseCalendar japaneseCalendar) {
            int i = this.index;
            if (i == 0) {
                return japaneseCalendar.getYear();
            }
            if (i == 1) {
                return JapaneseCalendar.getMonthIndex(japaneseCalendar.relgregyear, japaneseCalendar.month);
            }
            if (i == 2) {
                return japaneseCalendar.dayOfMonth;
            }
            if (i == 3) {
                return japaneseCalendar.dayOfYear;
            }
            if (i == 4) {
                return japaneseCalendar.relgregyear;
            }
            if (i == 5) {
                return japaneseCalendar.relgregyear + 660;
            }
            throw new UnsupportedOperationException("Unknown element index: " + this.index);
        }

        int getMin() {
            int i = this.index;
            if (i == 0 || i == 1 || i == 2 || i == 3) {
                return 1;
            }
            if (i == 4) {
                return 701;
            }
            if (i == 5) {
                return 1361;
            }
            throw new UnsupportedOperationException("Unknown element index: " + this.index);
        }

        int getMax(JapaneseCalendar japaneseCalendar) {
            int i = this.index;
            if (i == 0) {
                Nengo nengo = japaneseCalendar.nengo;
                Nengo nengoFindNext = nengo.findNext();
                if (nengoFindNext != null) {
                    return (nengoFindNext.getFirstRelatedGregorianYear() - nengo.getFirstRelatedGregorianYear()) + 1;
                }
                return 1000000000 - Nengo.Element.SINGLETON.getDefaultMaximum().getFirstRelatedGregorianYear();
            }
            if (i == 1) {
                return (japaneseCalendar.relgregyear >= 1873 || JapaneseCalendar.LEAP_INDICATORS[japaneseCalendar.relgregyear + (-701)] == 0) ? 12 : 13;
            }
            if (i == 2) {
                return JapaneseCalendar.getLengthOfMonth(japaneseCalendar.relgregyear, japaneseCalendar.month);
            }
            if (i == 3) {
                return JapaneseCalendar.getLengthOfYear(japaneseCalendar.relgregyear);
            }
            if (i == 4) {
                return 999999999;
            }
            if (i == 5) {
                return 1000000659;
            }
            throw new UnsupportedOperationException("Unknown element index: " + this.index);
        }

        @Override // net.time4j.engine.IntElementRule
        public boolean isValid(JapaneseCalendar japaneseCalendar, int i) {
            int i2 = this.index;
            if (i2 != 0 && i2 != 1 && i2 != 2 && i2 != 3) {
                if (i2 == 4) {
                    return japaneseCalendar.relgregyear == i;
                }
                if (i2 != 5) {
                    throw new UnsupportedOperationException("Unknown element index: " + this.index);
                }
            }
            return i >= 1 && i <= getMax(japaneseCalendar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // net.time4j.engine.IntElementRule
        public JapaneseCalendar withValue(JapaneseCalendar japaneseCalendar, int i, boolean z) {
            byte b;
            EastAsianMonth eastAsianMonthValueOf;
            if (!isValid(japaneseCalendar, i)) {
                if (this.index == 4) {
                    throw new IllegalArgumentException("The related gregorian year is read-only.");
                }
                throw new IllegalArgumentException("Out of range: " + i);
            }
            int i2 = this.index;
            if (i2 == 0) {
                return yearsAdded(japaneseCalendar, (japaneseCalendar.nengo.getFirstRelatedGregorianYear() + i) - 1);
            }
            if (i2 == 1) {
                if (japaneseCalendar.relgregyear >= 1873 || (b = JapaneseCalendar.LEAP_INDICATORS[japaneseCalendar.relgregyear - 701]) == 0 || b > i) {
                    eastAsianMonthValueOf = EastAsianMonth.valueOf(i);
                } else if (i == b) {
                    eastAsianMonthValueOf = EastAsianMonth.valueOf(i - 1).withLeap();
                } else {
                    eastAsianMonthValueOf = EastAsianMonth.valueOf(i - 1);
                }
                return (JapaneseCalendar) japaneseCalendar.with(JapaneseCalendar.MONTH_OF_YEAR, (TextElement<EastAsianMonth>) eastAsianMonthValueOf);
            }
            if (i2 == 2) {
                return JapaneseCalendar.create(japaneseCalendar, japaneseCalendar.relgregyear, japaneseCalendar.month, i);
            }
            if (i2 == 3) {
                return new JapaneseCalendar(japaneseCalendar.nengo, japaneseCalendar.relgregyear, i);
            }
            if (i2 == 4) {
                return japaneseCalendar;
            }
            if (i2 == 5) {
                return yearsAdded(japaneseCalendar, i - 660);
            }
            throw new UnsupportedOperationException("Unknown element index: " + this.index);
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getValue(JapaneseCalendar japaneseCalendar) {
            return Integer.valueOf(getInt(japaneseCalendar));
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getMinimum(JapaneseCalendar japaneseCalendar) {
            return Integer.valueOf(getMin());
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getMaximum(JapaneseCalendar japaneseCalendar) {
            return Integer.valueOf(getMax(japaneseCalendar));
        }

        @Override // net.time4j.engine.ElementRule
        /* renamed from: isValid, reason: merged with bridge method [inline-methods] */
        public boolean isValid2(JapaneseCalendar japaneseCalendar, Integer num) {
            return num != null && isValid(japaneseCalendar, num.intValue());
        }

        @Override // net.time4j.engine.ElementRule
        /* renamed from: withValue, reason: merged with bridge method [inline-methods] */
        public JapaneseCalendar withValue2(JapaneseCalendar japaneseCalendar, Integer num, boolean z) {
            if (num == null) {
                throw new IllegalArgumentException("Not nullable.");
            }
            return withValue(japaneseCalendar, num.intValue(), z);
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(JapaneseCalendar japaneseCalendar) {
            int i = this.index;
            if (i == 0) {
                return JapaneseCalendar.MONTH_OF_YEAR;
            }
            if (i == 1) {
                return JapaneseCalendar.DAY_OF_MONTH;
            }
            if (i == 2 || i == 3 || i == 4 || i == 5) {
                return null;
            }
            throw new UnsupportedOperationException("Unknown element index: " + this.index);
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(JapaneseCalendar japaneseCalendar) {
            return getChildAtFloor(japaneseCalendar);
        }

        private static JapaneseCalendar yearsAdded(JapaneseCalendar japaneseCalendar, int i) {
            EastAsianMonth eastAsianMonthValueOf = japaneseCalendar.month;
            int number = eastAsianMonthValueOf.getNumber();
            if (i >= 1873) {
                if (eastAsianMonthValueOf.isLeap()) {
                    eastAsianMonthValueOf = EastAsianMonth.valueOf(eastAsianMonthValueOf.getNumber());
                }
            } else if (eastAsianMonthValueOf.isLeap() && JapaneseCalendar.LEAP_INDICATORS[i - 701] != number + 1) {
                eastAsianMonthValueOf = EastAsianMonth.valueOf(eastAsianMonthValueOf.getNumber());
            }
            return JapaneseCalendar.create(japaneseCalendar, i, eastAsianMonthValueOf, Math.min(japaneseCalendar.dayOfMonth, JapaneseCalendar.getLengthOfMonth(i, eastAsianMonthValueOf)));
        }
    }

    private static class YearOfNengoElement extends StdIntegerDateElement<JapaneseCalendar> implements DualFormatElement {
        private static final long serialVersionUID = -8502388572788955989L;

        private YearOfNengoElement() {
            super("YEAR_OF_ERA", JapaneseCalendar.class, 1, 1000000000 - Nengo.Element.SINGLETON.getDefaultMaximum().getFirstRelatedGregorianYear(), 'y', null, null);
        }

        @Override // net.time4j.format.TextElement
        public void print(ChronoDisplay chronoDisplay, Appendable appendable, AttributeQuery attributeQuery) throws IOException, ChronoException {
            char cCharAt;
            NumberSystem numberSystem = (NumberSystem) attributeQuery.get(Attributes.NUMBER_SYSTEM, NumberSystem.ARABIC);
            if (attributeQuery.contains(Attributes.ZERO_DIGIT)) {
                cCharAt = ((Character) attributeQuery.get(Attributes.ZERO_DIGIT)).charValue();
            } else {
                cCharAt = numberSystem.isDecimal() ? numberSystem.getDigits().charAt(0) : '0';
            }
            print(chronoDisplay, appendable, attributeQuery, numberSystem, cCharAt, 1, 9);
        }

        @Override // net.time4j.format.internal.DualFormatElement
        public void print(ChronoDisplay chronoDisplay, Appendable appendable, AttributeQuery attributeQuery, NumberSystem numberSystem, char c, int i, int i2) throws IOException, ChronoException {
            int i3 = chronoDisplay.getInt(this);
            if (i3 == 1 && numberSystem == NumberSystem.ARABIC && ((Locale) attributeQuery.get(Attributes.LANGUAGE, Locale.ROOT)).getLanguage().equals("ja")) {
                appendable.append((char) 20803);
                return;
            }
            String numeral = numberSystem.toNumeral(i3);
            if (numberSystem.isDecimal()) {
                int length = i - numeral.length();
                for (int i4 = 0; i4 < length; i4++) {
                    appendable.append(c);
                }
            }
            appendable.append(numeral);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.time4j.format.TextElement
        public Integer parse(CharSequence charSequence, ParsePosition parsePosition, AttributeQuery attributeQuery) throws NumberFormatException {
            char cCharAt;
            int i;
            NumberSystem numberSystem = (NumberSystem) attributeQuery.get(Attributes.NUMBER_SYSTEM, NumberSystem.ARABIC);
            int index = parsePosition.getIndex();
            if (numberSystem == NumberSystem.ARABIC && charSequence.charAt(index) == 20803 && ((Locale) attributeQuery.get(Attributes.LANGUAGE, Locale.ROOT)).getLanguage().equals("ja")) {
                parsePosition.setIndex(index + 1);
                return 1;
            }
            int integer = 0;
            if (attributeQuery.contains(Attributes.ZERO_DIGIT)) {
                cCharAt = ((Character) attributeQuery.get(Attributes.ZERO_DIGIT)).charValue();
            } else {
                cCharAt = numberSystem.isDecimal() ? numberSystem.getDigits().charAt(0) : '0';
            }
            Leniency leniency = numberSystem.isDecimal() ? Leniency.SMART : (Leniency) attributeQuery.get(Attributes.LENIENCY, Leniency.SMART);
            if (numberSystem.isDecimal()) {
                int iMin = Math.min(index + 9, charSequence.length());
                int i2 = index;
                i = i2;
                while (i2 < iMin) {
                    int iCharAt = charSequence.charAt(i2) - cCharAt;
                    if (iCharAt < 0 || iCharAt > 9) {
                        break;
                    }
                    integer = (integer * 10) + iCharAt;
                    i++;
                    i2++;
                }
            } else {
                int length = charSequence.length();
                int i3 = 0;
                for (int i4 = index; i4 < length && numberSystem.contains(charSequence.charAt(i4)); i4++) {
                    i3++;
                }
                if (i3 > 0) {
                    i = index + i3;
                    integer = numberSystem.toInteger(charSequence.subSequence(index, i).toString(), leniency);
                } else {
                    i = index;
                }
            }
            if (i == index) {
                parsePosition.setErrorIndex(index);
                return null;
            }
            parsePosition.setIndex(i);
            return Integer.valueOf(integer);
        }

        @Override // net.time4j.format.internal.DualFormatElement
        public Integer parse(CharSequence charSequence, ParsePosition parsePosition, AttributeQuery attributeQuery, ChronoEntity<?> chronoEntity) {
            return parse(charSequence, parsePosition, attributeQuery);
        }
    }

    private static class MonthPrimitiveElement extends EastAsianME implements ElementRule<JapaneseCalendar, EastAsianMonth> {
        static final MonthPrimitiveElement SINGLETON_JP = new MonthPrimitiveElement();
        private static final long serialVersionUID = -2978966174642315851L;

        @Override // net.time4j.calendar.EastAsianME
        protected Object readResolve() throws ObjectStreamException {
            return SINGLETON_JP;
        }

        private MonthPrimitiveElement() {
        }

        @Override // net.time4j.engine.ElementRule
        public EastAsianMonth getValue(JapaneseCalendar japaneseCalendar) {
            return japaneseCalendar.month;
        }

        @Override // net.time4j.engine.ElementRule
        public EastAsianMonth getMinimum(JapaneseCalendar japaneseCalendar) {
            return EastAsianMonth.valueOf(1);
        }

        @Override // net.time4j.engine.ElementRule
        public EastAsianMonth getMaximum(JapaneseCalendar japaneseCalendar) {
            EastAsianMonth eastAsianMonthValueOf = EastAsianMonth.valueOf(12);
            return (japaneseCalendar.relgregyear >= 1873 || JapaneseCalendar.LEAP_INDICATORS[japaneseCalendar.relgregyear + (-701)] != 13) ? eastAsianMonthValueOf : eastAsianMonthValueOf.withLeap();
        }

        @Override // net.time4j.engine.ElementRule
        /* renamed from: isValid, reason: avoid collision after fix types in other method */
        public boolean isValid2(JapaneseCalendar japaneseCalendar, EastAsianMonth eastAsianMonth) {
            if (eastAsianMonth == null) {
                return false;
            }
            if (japaneseCalendar.relgregyear >= 1873) {
                return !eastAsianMonth.isLeap();
            }
            return !eastAsianMonth.isLeap() || JapaneseCalendar.LEAP_INDICATORS[japaneseCalendar.relgregyear + (-701)] == eastAsianMonth.getNumber() + 1;
        }

        @Override // net.time4j.engine.ElementRule
        /* renamed from: withValue, reason: avoid collision after fix types in other method */
        public JapaneseCalendar withValue2(JapaneseCalendar japaneseCalendar, EastAsianMonth eastAsianMonth, boolean z) {
            if (isValid2(japaneseCalendar, eastAsianMonth)) {
                return JapaneseCalendar.create(japaneseCalendar, japaneseCalendar.relgregyear, eastAsianMonth, Math.min(japaneseCalendar.dayOfMonth, JapaneseCalendar.getLengthOfMonth(japaneseCalendar.relgregyear, eastAsianMonth)));
            }
            throw new IllegalArgumentException("Invalid month: " + eastAsianMonth);
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(JapaneseCalendar japaneseCalendar) {
            return JapaneseCalendar.DAY_OF_MONTH;
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(JapaneseCalendar japaneseCalendar) {
            return JapaneseCalendar.DAY_OF_MONTH;
        }

        @Override // net.time4j.calendar.EastAsianME, net.time4j.format.TextElement
        public void print(ChronoDisplay chronoDisplay, Appendable appendable, AttributeQuery attributeQuery) throws IOException, ChronoException {
            if (((Integer) chronoDisplay.get(CommonElements.RELATED_GREGORIAN_YEAR)).intValue() >= 1873) {
                int iIntValue = ((Integer) attributeQuery.get(DualFormatElement.COUNT_OF_PATTERN_SYMBOLS, 0)).intValue();
                int number = ((EastAsianMonth) chronoDisplay.get(JapaneseCalendar.MONTH_OF_YEAR)).getNumber();
                if (iIntValue == 0) {
                    appendable.append(CalendarText.getIsoInstance((Locale) attributeQuery.get(Attributes.LANGUAGE, Locale.ROOT)).getStdMonths((TextWidth) attributeQuery.get(Attributes.TEXT_WIDTH, TextWidth.WIDE), (OutputContext) attributeQuery.get(Attributes.OUTPUT_CONTEXT, OutputContext.FORMAT)).print(Month.valueOf(number)));
                    return;
                }
                NumberSystem numberSystem = (NumberSystem) attributeQuery.get(Attributes.NUMBER_SYSTEM, NumberSystem.ARABIC);
                char cCharValue = ((Character) attributeQuery.get(Attributes.ZERO_DIGIT, Character.valueOf(numberSystem.getDigits().charAt(0)))).charValue();
                String numeral = DualFormatHelper.toNumeral(numberSystem, cCharValue, number);
                if (numberSystem.isDecimal()) {
                    for (int length = iIntValue - numeral.length(); length > 0; length--) {
                        appendable.append(cCharValue);
                    }
                }
                appendable.append(numeral);
                return;
            }
            super.print(chronoDisplay, appendable, attributeQuery);
        }

        @Override // net.time4j.calendar.EastAsianME, net.time4j.format.TextElement
        public EastAsianMonth parse(CharSequence charSequence, ParsePosition parsePosition, AttributeQuery attributeQuery) {
            Locale locale = (Locale) attributeQuery.get(Attributes.LANGUAGE, Locale.ROOT);
            int iIntValue = ((Integer) attributeQuery.get(DualFormatElement.COUNT_OF_PATTERN_SYMBOLS, 0)).intValue();
            int index = parsePosition.getIndex();
            if (iIntValue == 0) {
                Month month = (Month) CalendarText.getIsoInstance(locale).getStdMonths((TextWidth) attributeQuery.get(Attributes.TEXT_WIDTH, TextWidth.WIDE), (OutputContext) attributeQuery.get(Attributes.OUTPUT_CONTEXT, OutputContext.FORMAT)).parse(charSequence, parsePosition, Month.class, attributeQuery);
                if (month != null) {
                    return EastAsianMonth.valueOf(month.getValue());
                }
                parsePosition.setIndex(index);
                parsePosition.setErrorIndex(-1);
            }
            return super.parse(charSequence, parsePosition, attributeQuery);
        }
    }

    private static class Merger implements ChronoMerger<JapaneseCalendar> {
        @Override // net.time4j.engine.ChronoMerger
        public int getDefaultPivotYear() {
            return 100;
        }

        @Override // net.time4j.engine.ChronoMerger
        public ChronoDisplay preformat(JapaneseCalendar japaneseCalendar, AttributeQuery attributeQuery) {
            return japaneseCalendar;
        }

        @Override // net.time4j.engine.ChronoMerger
        public Chronology<?> preparser() {
            return null;
        }

        private Merger() {
        }

        @Override // net.time4j.engine.ChronoMerger
        public /* bridge */ /* synthetic */ JapaneseCalendar createFrom(TimeSource timeSource, AttributeQuery attributeQuery) {
            return createFrom2((TimeSource<?>) timeSource, attributeQuery);
        }

        @Override // net.time4j.engine.ChronoMerger
        public /* bridge */ /* synthetic */ JapaneseCalendar createFrom(ChronoEntity chronoEntity, AttributeQuery attributeQuery, boolean z, boolean z2) {
            return createFrom2((ChronoEntity<?>) chronoEntity, attributeQuery, z, z2);
        }

        @Override // net.time4j.engine.ChronoMerger
        public String getFormatPattern(DisplayStyle displayStyle, Locale locale) {
            return GenericDatePatterns.get("japanese", displayStyle, locale);
        }

        @Override // net.time4j.engine.ChronoMerger
        public StartOfDay getDefaultStartOfDay() {
            return StartOfDay.MIDNIGHT;
        }

        @Override // net.time4j.engine.ChronoMerger
        /* renamed from: createFrom, reason: avoid collision after fix types in other method */
        public JapaneseCalendar createFrom2(TimeSource<?> timeSource, AttributeQuery attributeQuery) {
            TZID id;
            if (attributeQuery.contains(Attributes.TIMEZONE_ID)) {
                id = (TZID) attributeQuery.get(Attributes.TIMEZONE_ID);
            } else {
                if (!((Leniency) attributeQuery.get(Attributes.LENIENCY, Leniency.SMART)).isLax()) {
                    return null;
                }
                id = Timezone.ofSystem().getID();
            }
            return (JapaneseCalendar) Moment.from(timeSource.currentTime()).toGeneralTimestamp(JapaneseCalendar.ENGINE, id, (StartOfDay) attributeQuery.get(Attributes.START_OF_DAY, getDefaultStartOfDay())).toDate();
        }

        @Override // net.time4j.engine.ChronoMerger
        /* renamed from: createFrom, reason: avoid collision after fix types in other method */
        public JapaneseCalendar createFrom2(ChronoEntity<?> chronoEntity, AttributeQuery attributeQuery, boolean z, boolean z2) {
            EastAsianMonth eastAsianMonthValueOf;
            Leniency leniency;
            Leniency leniency2;
            Nengo nengo = (Nengo) chronoEntity.get(JapaneseCalendar.ERA);
            if (nengo == null) {
                chronoEntity.with((ChronoElement<ValidationElement>) ValidationElement.ERROR_MESSAGE, (ValidationElement) "Missing Japanese nengo/era.");
                return null;
            }
            int i = chronoEntity.getInt(JapaneseCalendar.YEAR_OF_ERA);
            if (i == Integer.MIN_VALUE) {
                chronoEntity.with((ChronoElement<ValidationElement>) ValidationElement.ERROR_MESSAGE, (ValidationElement) "Missing Japanese year.");
                return null;
            }
            int firstRelatedGregorianYear = nengo.getFirstRelatedGregorianYear() + i;
            int i2 = firstRelatedGregorianYear - 1;
            if (chronoEntity.contains(JapaneseCalendar.MONTH_OF_YEAR)) {
                eastAsianMonthValueOf = (EastAsianMonth) chronoEntity.get(JapaneseCalendar.MONTH_OF_YEAR);
            } else if (chronoEntity.contains(JapaneseCalendar.MONTH_AS_ORDINAL)) {
                int i3 = chronoEntity.getInt(JapaneseCalendar.MONTH_AS_ORDINAL);
                if (i2 >= 1873) {
                    eastAsianMonthValueOf = EastAsianMonth.valueOf(i3);
                } else {
                    byte b = JapaneseCalendar.LEAP_INDICATORS[firstRelatedGregorianYear - 702];
                    if (i3 == b) {
                        eastAsianMonthValueOf = EastAsianMonth.valueOf(i3 - 1).withLeap();
                    } else if (i3 > b) {
                        eastAsianMonthValueOf = EastAsianMonth.valueOf(i3 - 1);
                    } else {
                        eastAsianMonthValueOf = EastAsianMonth.valueOf(i3);
                    }
                }
            } else {
                eastAsianMonthValueOf = null;
            }
            if (eastAsianMonthValueOf != null) {
                int i4 = chronoEntity.getInt(JapaneseCalendar.DAY_OF_MONTH);
                if (i4 == Integer.MIN_VALUE) {
                    chronoEntity.with((ChronoElement<ValidationElement>) ValidationElement.ERROR_MESSAGE, (ValidationElement) "Missing Japanese day of month.");
                    return null;
                }
                if (z) {
                    leniency2 = Leniency.LAX;
                } else {
                    leniency2 = (Leniency) attributeQuery.get(Attributes.LENIENCY, Leniency.SMART);
                }
                return JapaneseCalendar.of(nengo, i, eastAsianMonthValueOf, i4, leniency2);
            }
            int i5 = chronoEntity.getInt(JapaneseCalendar.DAY_OF_YEAR);
            if (i5 != Integer.MIN_VALUE && i5 <= JapaneseCalendar.getLengthOfYear(i2)) {
                try {
                    EastAsianMonth month = JapaneseCalendar.getMonth(i2, i5);
                    int dayOfMonth = JapaneseCalendar.getDayOfMonth(i2, i5);
                    if (z) {
                        leniency = Leniency.LAX;
                    } else {
                        leniency = (Leniency) attributeQuery.get(Attributes.LENIENCY, Leniency.SMART);
                    }
                    return JapaneseCalendar.of(nengo, i, month, dayOfMonth, leniency);
                } catch (IllegalArgumentException unused) {
                    chronoEntity.with((ChronoElement<ValidationElement>) ValidationElement.ERROR_MESSAGE, (ValidationElement) "Invalid Japanese date.");
                }
            }
            return null;
        }
    }

    private static class SPX implements Externalizable {
        private static final int JAPANESE = 9;
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
            objectOutput.writeByte(9);
            writeJapanese(objectOutput);
        }

        @Override // java.io.Externalizable
        public void readExternal(ObjectInput objectInput) throws IOException {
            if (objectInput.readByte() == 9) {
                this.obj = readJapanese(objectInput);
                return;
            }
            throw new InvalidObjectException("Unknown calendar type.");
        }

        private void writeJapanese(ObjectOutput objectOutput) throws IOException {
            JapaneseCalendar japaneseCalendar = (JapaneseCalendar) this.obj;
            objectOutput.writeInt((japaneseCalendar.getYear() - 1) + japaneseCalendar.getEra().getFirstRelatedGregorianYear());
            objectOutput.writeInt(japaneseCalendar.getDayOfYear());
        }

        private JapaneseCalendar readJapanese(ObjectInput objectInput) throws IOException {
            return (JapaneseCalendar) JapaneseCalendar.axis().getCalendarSystem().transform(JapaneseCalendar.transform(objectInput.readInt(), objectInput.readInt()));
        }
    }
}
