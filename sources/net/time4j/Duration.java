package net.time4j;

import com.onfido.android.sdk.capture.ui.camera.CapturePresenter;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.IsoUnit;
import net.time4j.base.GregorianDate;
import net.time4j.base.MathUtils;
import net.time4j.base.WallTime;
import net.time4j.engine.AbstractDuration;
import net.time4j.engine.AbstractMetric;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoUnit;
import net.time4j.engine.Normalizer;
import net.time4j.engine.TimeMetric;
import net.time4j.engine.TimePoint;
import net.time4j.engine.TimeSpan;
import net.time4j.format.TimeSpanFormatter;
import net.time4j.tz.Timezone;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes4.dex */
public final class Duration<U extends IsoUnit> extends AbstractDuration<U> implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int CALENDAR_TYPE = 0;
    private static final Formatter<CalendarUnit> CF_BAS_CAL;
    private static final Formatter<CalendarUnit> CF_BAS_ORD;
    private static final Formatter<CalendarUnit> CF_EXT_CAL;
    private static final Formatter<CalendarUnit> CF_EXT_ORD;
    private static final TimeMetric<ClockUnit, Duration<ClockUnit>> CLOCK_METRIC;
    private static final int CLOCK_TYPE = 1;
    private static final char ISO_DECIMAL_SEPARATOR;
    private static final Comparator<TimeSpan.Item<? extends ChronoUnit>> ITEM_COMPARATOR;
    private static final long MIO = 1000000;
    private static final long MRD = 1000000000;
    private static final int PRINT_STYLE_ISO = 1;
    private static final int PRINT_STYLE_NORMAL = 0;
    private static final int PRINT_STYLE_XML = 2;
    public static Normalizer<CalendarUnit> STD_CALENDAR_PERIOD = null;
    public static Normalizer<ClockUnit> STD_CLOCK_PERIOD = null;
    public static Normalizer<IsoUnit> STD_PERIOD = null;
    private static final int SUPER_TYPE = -1;
    private static final Formatter<ClockUnit> TF_BAS;
    private static final Formatter<ClockUnit> TF_EXT;
    private static final TimeMetric<IsoDateUnit, Duration<IsoDateUnit>> WEEK_BASED_METRIC;
    private static final int WEEK_BASED_TYPE = 2;
    private static final TimeMetric<CalendarUnit, Duration<CalendarUnit>> YMD_METRIC;
    private static final Duration ZERO;
    private static final long serialVersionUID = -6321211763598951499L;
    private final transient List<TimeSpan.Item<U>> items;
    private final transient boolean negative;

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> T cast(Object obj) {
        return obj;
    }

    private static Formatter<CalendarUnit> getAlternativeDateFormat(boolean z, boolean z2) {
        return z ? z2 ? CF_EXT_ORD : CF_EXT_CAL : z2 ? CF_BAS_ORD : CF_BAS_CAL;
    }

    private static Formatter<ClockUnit> getAlternativeTimeFormat(boolean z) {
        return z ? TF_EXT : TF_BAS;
    }

    private static boolean hasMixedSigns(long j, long j2) {
        return (j < 0 && j2 > 0) || (j > 0 && j2 < 0);
    }

    public static TimeMetric<ClockUnit, Duration<ClockUnit>> inClockUnits() {
        return CLOCK_METRIC;
    }

    public static TimeMetric<IsoDateUnit, Duration<IsoDateUnit>> inWeekBasedUnits() {
        return WEEK_BASED_METRIC;
    }

    public static TimeMetric<CalendarUnit, Duration<CalendarUnit>> inYearsMonthsDays() {
        return YMD_METRIC;
    }

    public static <U extends IsoUnit> Duration<U> ofZero() {
        return ZERO;
    }

    @Override // net.time4j.engine.TimeSpan
    public List<TimeSpan.Item<U>> getTotalLength() {
        return this.items;
    }

    @Override // net.time4j.engine.TimeSpan
    public boolean isNegative() {
        return this.negative;
    }

    static {
        ISO_DECIMAL_SEPARATOR = Boolean.getBoolean("net.time4j.format.iso.decimal.dot") ? ClassUtils.PACKAGE_SEPARATOR_CHAR : AbstractJsonLexerKt.COMMA;
        ZERO = new Duration();
        CF_EXT_CAL = createAlternativeDateFormat(true, false);
        CF_EXT_ORD = createAlternativeDateFormat(true, true);
        CF_BAS_CAL = createAlternativeDateFormat(false, false);
        CF_BAS_ORD = createAlternativeDateFormat(false, true);
        TF_EXT = createAlternativeTimeFormat(true);
        TF_BAS = createAlternativeTimeFormat(false);
        ITEM_COMPARATOR = StdNormalizer.comparator();
        STD_PERIOD = StdNormalizer.ofMixedUnits();
        STD_CALENDAR_PERIOD = StdNormalizer.ofCalendarUnits();
        STD_CLOCK_PERIOD = StdNormalizer.ofClockUnits();
        YMD_METRIC = in(CalendarUnit.YEARS, CalendarUnit.MONTHS, CalendarUnit.DAYS);
        CLOCK_METRIC = in(ClockUnit.HOURS, ClockUnit.MINUTES, ClockUnit.SECONDS, ClockUnit.NANOS);
        WEEK_BASED_METRIC = in(CalendarUnit.weekBasedYears(), CalendarUnit.WEEKS, CalendarUnit.DAYS);
    }

    Duration(List<TimeSpan.Item<U>> list, boolean z) {
        boolean zIsEmpty = list.isEmpty();
        if (zIsEmpty) {
            this.items = Collections.emptyList();
        } else {
            Collections.sort(list, ITEM_COMPARATOR);
            this.items = Collections.unmodifiableList(list);
        }
        this.negative = !zIsEmpty && z;
    }

    private Duration(Duration<U> duration, boolean z) {
        this.items = duration.items;
        boolean z2 = duration.negative;
        this.negative = z ? !z2 : z2;
    }

    private Duration() {
        this.items = Collections.emptyList();
        this.negative = false;
    }

    public static <U extends IsoUnit> Duration<U> of(long j, U u) {
        if (j == 0) {
            return ofZero();
        }
        if (j < 0) {
            j = MathUtils.safeNegate(j);
        }
        if (u instanceof ClockUnit) {
            char symbol = u.getSymbol();
            if (symbol == '3') {
                u = (U) cast(ClockUnit.NANOS);
                j = MathUtils.safeMultiply(j, 1000000L);
            } else if (symbol == '6') {
                u = (U) cast(ClockUnit.NANOS);
                j = MathUtils.safeMultiply(j, 1000L);
            }
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(TimeSpan.Item.of(j, u));
        return new Duration<>(arrayList, j < 0);
    }

    public static Builder ofPositive() {
        return new Builder(false);
    }

    public static Builder ofNegative() {
        return new Builder(true);
    }

    public static Duration<CalendarUnit> ofCalendarUnits(int i, int i2, int i3) {
        return ofCalendarUnits(i, i2, i3, false);
    }

    public static Duration<ClockUnit> ofClockUnits(int i, int i2, int i3) {
        return ofClockUnits(i, i2, i3, 0L, false);
    }

    public static <U extends IsoUnit> TimeMetric<U, Duration<U>> in(U... uArr) {
        return new Metric(uArr);
    }

    public static <U extends IsoUnit> TimeMetric<U, Duration<U>> in(Collection<? extends U> collection) {
        return new Metric(collection);
    }

    public static TimeMetric<IsoUnit, Duration<IsoUnit>> in(Timezone timezone, IsoUnit... isoUnitArr) {
        return new ZonalMetric(timezone, isoUnitArr);
    }

    @Override // net.time4j.engine.AbstractDuration
    public boolean contains(IsoUnit isoUnit) {
        if (isoUnit == null) {
            return false;
        }
        boolean zIsFractionUnit = isFractionUnit(isoUnit);
        int size = this.items.size();
        for (int i = 0; i < size; i++) {
            TimeSpan.Item<U> item = this.items.get(i);
            U unit = item.getUnit();
            if (unit.equals(isoUnit) || (zIsFractionUnit && isFractionUnit(unit))) {
                return item.getAmount() > 0;
            }
        }
        return false;
    }

    @Override // net.time4j.engine.AbstractDuration
    public long getPartialAmount(IsoUnit isoUnit) {
        if (isoUnit == null) {
            return 0L;
        }
        boolean zIsFractionUnit = isFractionUnit(isoUnit);
        int size = this.items.size();
        for (int i = 0; i < size; i++) {
            TimeSpan.Item<U> item = this.items.get(i);
            U unit = item.getUnit();
            if (unit.equals(isoUnit)) {
                return item.getAmount();
            }
            if (zIsFractionUnit && isFractionUnit(unit)) {
                int symbol = unit.getSymbol() - '0';
                int symbol2 = isoUnit.getSymbol() - '0';
                int i2 = 1;
                for (int i3 = 0; i3 < Math.abs(symbol - symbol2); i3++) {
                    i2 *= 10;
                }
                if (symbol >= symbol2) {
                    return item.getAmount() / i2;
                }
                return item.getAmount() * i2;
            }
        }
        return 0L;
    }

    public static <U extends IsoUnit, T extends TimePoint<U, T>> Comparator<Duration<? extends U>> comparator(T t) {
        return new LengthComparator(t);
    }

    public static Comparator<Duration<ClockUnit>> comparatorOnClock() {
        return new Comparator<Duration<ClockUnit>>() { // from class: net.time4j.Duration.1
            @Override // java.util.Comparator
            public int compare(Duration<ClockUnit> duration, Duration<ClockUnit> duration2) {
                long jLengthInSeconds = Duration.lengthInSeconds(duration);
                long jLengthInSeconds2 = Duration.lengthInSeconds(duration2);
                if (jLengthInSeconds < jLengthInSeconds2) {
                    return -1;
                }
                if (jLengthInSeconds > jLengthInSeconds2) {
                    return 1;
                }
                long partialAmount = duration.getPartialAmount((ChronoUnit) ClockUnit.NANOS) % 1000000000;
                long partialAmount2 = duration2.getPartialAmount((ChronoUnit) ClockUnit.NANOS) % 1000000000;
                if (duration.isNegative()) {
                    partialAmount = MathUtils.safeNegate(partialAmount);
                }
                if (duration2.isNegative()) {
                    partialAmount2 = MathUtils.safeNegate(partialAmount2);
                }
                if (partialAmount < partialAmount2) {
                    return -1;
                }
                return partialAmount > partialAmount2 ? 1 : 0;
            }
        };
    }

    public Duration<U> plus(long j, U u) {
        long amount;
        boolean z;
        IsoUnit isoUnit;
        if (u == null) {
            throw new NullPointerException("Missing chronological unit.");
        }
        if (j == 0) {
            return this;
        }
        if (j < 0) {
            amount = MathUtils.safeNegate(j);
            z = true;
        } else {
            amount = j;
            z = false;
        }
        ArrayList arrayList = new ArrayList(getTotalLength());
        TimeSpan.Item itemReplaceFraction = replaceFraction(amount, u);
        if (itemReplaceFraction != null) {
            amount = itemReplaceFraction.getAmount();
            isoUnit = (IsoUnit) itemReplaceFraction.getUnit();
        } else {
            isoUnit = u;
        }
        if (isEmpty()) {
            if (itemReplaceFraction == null) {
                itemReplaceFraction = TimeSpan.Item.of(amount, isoUnit);
            }
            arrayList.add(itemReplaceFraction);
            return new Duration<>(arrayList, z);
        }
        int index = getIndex(isoUnit);
        boolean zIsNegative = isNegative();
        if (index < 0) {
            if (isNegative() == z) {
                arrayList.add(TimeSpan.Item.of(amount, isoUnit));
            } else {
                return plus(of(j, u));
            }
        } else {
            long jSafeAdd = MathUtils.safeAdd(MathUtils.safeMultiply(((TimeSpan.Item) arrayList.get(index)).getAmount(), isNegative() ? -1 : 1), MathUtils.safeMultiply(amount, z ? -1 : 1));
            if (jSafeAdd == 0) {
                arrayList.remove(index);
            } else {
                if (count() != 1) {
                    if (isNegative() != (jSafeAdd < 0)) {
                        return plus(of(j, u));
                    }
                }
                if (jSafeAdd < 0) {
                    jSafeAdd = MathUtils.safeNegate(jSafeAdd);
                }
                arrayList.set(index, TimeSpan.Item.of(jSafeAdd, isoUnit));
                zIsNegative = jSafeAdd < 0;
            }
        }
        return new Duration<>(arrayList, zIsNegative);
    }

    public Duration<U> plus(TimeSpan<? extends U> timeSpan) {
        long j;
        long j2;
        long jSafeNegate;
        Duration<U> durationMerge = merge(this, timeSpan);
        if (durationMerge != null) {
            return durationMerge;
        }
        long[] jArr = {0, 0, 0, 0};
        if (summarize(this, jArr) && summarize(timeSpan, jArr)) {
            long jSafeNegate2 = jArr[0];
            long j3 = jArr[1];
            long jSafeNegate3 = jArr[2];
            long jSafeNegate4 = jArr[3];
            long j4 = 0;
            if (jSafeNegate4 != 0) {
                j = j3;
                j2 = jSafeNegate4;
            } else if (jSafeNegate3 != 0) {
                j = j3;
                j2 = jSafeNegate3;
            } else {
                j = j3;
                j2 = j;
            }
            if (!hasMixedSigns(jSafeNegate2, j2)) {
                boolean z = jSafeNegate2 < 0 || j2 < 0;
                if (z) {
                    jSafeNegate2 = MathUtils.safeNegate(jSafeNegate2);
                    jSafeNegate = MathUtils.safeNegate(j);
                    jSafeNegate3 = MathUtils.safeNegate(jSafeNegate3);
                    jSafeNegate4 = MathUtils.safeNegate(jSafeNegate4);
                } else {
                    jSafeNegate = j;
                }
                long j5 = jSafeNegate2 / 12;
                long j6 = jSafeNegate2 % 12;
                if (jSafeNegate4 != 0) {
                    j4 = jSafeNegate4 % 1000000000;
                    jSafeNegate3 = jSafeNegate4 / 1000000000;
                }
                long j7 = jSafeNegate3 / 3600;
                long j8 = jSafeNegate3 % 3600;
                HashMap map = new HashMap();
                map.put(CalendarUnit.YEARS, Long.valueOf(j5));
                map.put(CalendarUnit.MONTHS, Long.valueOf(j6));
                map.put(CalendarUnit.DAYS, Long.valueOf(jSafeNegate));
                map.put(ClockUnit.HOURS, Long.valueOf(j7));
                map.put(ClockUnit.MINUTES, Long.valueOf(j8 / 60));
                map.put(ClockUnit.SECONDS, Long.valueOf(j8 % 60));
                map.put(ClockUnit.NANOS, Long.valueOf(j4));
                return create(map, z);
            }
        }
        throw new IllegalStateException("Mixed signs in result time span not allowed: " + this + " PLUS " + timeSpan);
    }

    public Duration<U> with(long j, U u) {
        if (j < 0) {
            j = MathUtils.safeNegate(j);
        }
        TimeSpan.Item itemReplaceFraction = replaceFraction(j, u);
        if (itemReplaceFraction != null) {
            j = itemReplaceFraction.getAmount();
            u = (U) itemReplaceFraction.getUnit();
        }
        return plus(MathUtils.safeSubtract(MathUtils.safeMultiply(j, j < 0 ? -1L : 1L), MathUtils.safeMultiply(getPartialAmount((IsoUnit) u), isNegative() ? -1L : 1L)), u);
    }

    public Duration<U> abs() {
        return isNegative() ? inverse() : this;
    }

    @Override // net.time4j.engine.AbstractDuration
    public Duration<U> inverse() {
        return multipliedBy(-1);
    }

    public Duration<U> multipliedBy(int i) {
        if (!isEmpty()) {
            boolean zIsNegative = true;
            if (i != 1) {
                if (i == 0) {
                    return ofZero();
                }
                if (i == -1) {
                    return new Duration<>((Duration) this, true);
                }
                ArrayList arrayList = new ArrayList(count());
                int iAbs = Math.abs(i);
                int iCount = count();
                for (int i2 = 0; i2 < iCount; i2++) {
                    TimeSpan.Item<U> item = getTotalLength().get(i2);
                    arrayList.add(TimeSpan.Item.of(MathUtils.safeMultiply(item.getAmount(), iAbs), item.getUnit()));
                }
                if (i >= 0) {
                    zIsNegative = isNegative();
                } else if (isNegative()) {
                    zIsNegative = false;
                }
                return new Duration<>(arrayList, zIsNegative);
            }
        }
        return this;
    }

    public List<Duration<U>> union(TimeSpan<? extends U> timeSpan) {
        Duration durationMerge = merge(this, timeSpan);
        if (durationMerge == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this);
            arrayList.add(ofZero().plus(timeSpan));
            return Collections.unmodifiableList(arrayList);
        }
        return Collections.singletonList(durationMerge);
    }

    public static Duration<IsoUnit> compose(Duration<CalendarUnit> duration, Duration<ClockUnit> duration2) {
        return ofZero().plus(duration).plus(duration2);
    }

    public Duration<CalendarUnit> toCalendarPeriod() {
        if (isEmpty()) {
            return ofZero();
        }
        ArrayList arrayList = new ArrayList();
        for (TimeSpan.Item<U> item : this.items) {
            if (item.getUnit() instanceof CalendarUnit) {
                arrayList.add(TimeSpan.Item.of(item.getAmount(), CalendarUnit.class.cast(item.getUnit())));
            }
        }
        if (arrayList.isEmpty()) {
            return ofZero();
        }
        return new Duration<>(arrayList, isNegative());
    }

    public Duration<ClockUnit> toClockPeriod() {
        if (isEmpty()) {
            return ofZero();
        }
        ArrayList arrayList = new ArrayList();
        for (TimeSpan.Item<U> item : this.items) {
            if (item.getUnit() instanceof ClockUnit) {
                arrayList.add(TimeSpan.Item.of(item.getAmount(), ClockUnit.class.cast(item.getUnit())));
            }
        }
        if (arrayList.isEmpty()) {
            return ofZero();
        }
        return new Duration<>(arrayList, isNegative());
    }

    public Duration<ClockUnit> toClockPeriodWithDaysAs24Hours() {
        if (isEmpty()) {
            return ofZero();
        }
        ArrayList arrayList = new ArrayList();
        long jSafeMultiply = 0;
        for (TimeSpan.Item<U> item : this.items) {
            if (item.getUnit() instanceof ClockUnit) {
                arrayList.add(TimeSpan.Item.of(item.getAmount(), ClockUnit.class.cast(item.getUnit())));
            } else if (item.getUnit().equals(CalendarUnit.DAYS)) {
                jSafeMultiply = MathUtils.safeMultiply(item.getAmount(), 24L);
            }
        }
        if (jSafeMultiply != 0) {
            int size = arrayList.size();
            int i = 0;
            while (true) {
                if (i < size) {
                    TimeSpan.Item item2 = (TimeSpan.Item) arrayList.get(i);
                    if (item2.getUnit() == ClockUnit.HOURS) {
                        arrayList.set(i, TimeSpan.Item.of(MathUtils.safeAdd(item2.getAmount(), jSafeMultiply), ClockUnit.HOURS));
                        break;
                    }
                    i++;
                } else {
                    arrayList.add(TimeSpan.Item.of(jSafeMultiply, ClockUnit.HOURS));
                    break;
                }
            }
        } else if (arrayList.isEmpty()) {
            return ofZero();
        }
        return new Duration<>(arrayList, isNegative());
    }

    public Duration<U> with(Normalizer<U> normalizer) {
        return convert(normalizer.normalize2(this));
    }

    public Duration<U> truncatedTo(U u) {
        if (isEmpty()) {
            return ofZero();
        }
        double length = u.getLength();
        ArrayList arrayList = new ArrayList();
        for (TimeSpan.Item<U> item : this.items) {
            if (Double.compare(item.getUnit().getLength(), length) < 0) {
                break;
            }
            arrayList.add(item);
        }
        if (arrayList.isEmpty()) {
            return ofZero();
        }
        return new Duration<>(arrayList, isNegative());
    }

    public static Normalizer<IsoUnit> approximateHours(int i) {
        return new ApproximateNormalizer(i, ClockUnit.HOURS);
    }

    public static Normalizer<IsoUnit> approximateMinutes(int i) {
        return new ApproximateNormalizer(i, ClockUnit.MINUTES);
    }

    public static Normalizer<IsoUnit> approximateSeconds(int i) {
        return new ApproximateNormalizer(i, ClockUnit.SECONDS);
    }

    public static Normalizer<IsoUnit> approximateMaxUnitOnly() {
        return new ApproximateNormalizer(false);
    }

    public static Normalizer<IsoUnit> approximateMaxUnitOrWeeks() {
        return new ApproximateNormalizer(true);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Duration)) {
            return false;
        }
        Duration duration = (Duration) Duration.class.cast(obj);
        return this.negative == duration.negative && getTotalLength().equals(duration.getTotalLength());
    }

    public int hashCode() {
        int iHashCode = getTotalLength().hashCode();
        return this.negative ? iHashCode ^ iHashCode : iHashCode;
    }

    @Override // net.time4j.engine.AbstractDuration
    public String toString() {
        return toString(0);
    }

    public String toStringISO() {
        return toString(1);
    }

    public String toStringXML() {
        return toString(2);
    }

    public static Duration<IsoUnit> parsePeriod(String str) throws ParseException {
        return parsePeriod(str, IsoUnit.class);
    }

    public static Duration<CalendarUnit> parseCalendarPeriod(String str) throws ParseException {
        return parsePeriod(str, CalendarUnit.class);
    }

    public static Duration<ClockUnit> parseClockPeriod(String str) throws ParseException {
        return parsePeriod(str, ClockUnit.class);
    }

    public static Duration<IsoDateUnit> parseWeekBasedPeriod(String str) throws ParseException {
        return parsePeriod(str, IsoDateUnit.class);
    }

    public static Formatter<IsoUnit> formatter(String str) {
        return Formatter.ofPattern(str);
    }

    public static <U extends IsoUnit> Formatter<U> formatter(Class<U> cls, String str) {
        return Formatter.ofPattern(cls, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0159  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String toString(int r22) {
        /*
            Method dump skipped, instructions count: 538
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: net.time4j.Duration.toString(int):java.lang.String");
    }

    private int count() {
        return getTotalLength().size();
    }

    private static <U> boolean isEmpty(TimeSpan<U> timeSpan) {
        List<TimeSpan.Item<U>> totalLength = timeSpan.getTotalLength();
        int size = totalLength.size();
        for (int i = 0; i < size; i++) {
            if (totalLength.get(i).getAmount() > 0) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long lengthInSeconds(Duration<ClockUnit> duration) {
        long jSafeAdd = MathUtils.safeAdd(MathUtils.safeAdd(MathUtils.safeAdd(MathUtils.safeMultiply(duration.getPartialAmount((ChronoUnit) ClockUnit.HOURS), 3600L), MathUtils.safeMultiply(duration.getPartialAmount((ChronoUnit) ClockUnit.MINUTES), 60L)), duration.getPartialAmount((ChronoUnit) ClockUnit.SECONDS)), duration.getPartialAmount((ChronoUnit) ClockUnit.NANOS) / 1000000000);
        return duration.isNegative() ? MathUtils.safeNegate(jSafeAdd) : jSafeAdd;
    }

    private static Duration<CalendarUnit> ofCalendarUnits(long j, long j2, long j3, boolean z) {
        ArrayList arrayList = new ArrayList(3);
        if (j != 0) {
            arrayList.add(TimeSpan.Item.of(j, CalendarUnit.YEARS));
        }
        if (j2 != 0) {
            arrayList.add(TimeSpan.Item.of(j2, CalendarUnit.MONTHS));
        }
        if (j3 != 0) {
            arrayList.add(TimeSpan.Item.of(j3, CalendarUnit.DAYS));
        }
        return new Duration<>(arrayList, z);
    }

    private static Duration<ClockUnit> ofClockUnits(long j, long j2, long j3, long j4, boolean z) {
        ArrayList arrayList = new ArrayList(4);
        if (j != 0) {
            arrayList.add(TimeSpan.Item.of(j, ClockUnit.HOURS));
        }
        if (j2 != 0) {
            arrayList.add(TimeSpan.Item.of(j2, ClockUnit.MINUTES));
        }
        if (j3 != 0) {
            arrayList.add(TimeSpan.Item.of(j3, ClockUnit.SECONDS));
        }
        if (j4 != 0) {
            arrayList.add(TimeSpan.Item.of(j4, ClockUnit.NANOS));
        }
        return new Duration<>(arrayList, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <U extends IsoUnit> Duration<U> create(Map<U, Long> map, boolean z) {
        if (map.isEmpty()) {
            return ofZero();
        }
        ArrayList arrayList = new ArrayList(map.size());
        long jSafeAdd = 0;
        for (Map.Entry<U, Long> entry : map.entrySet()) {
            long jLongValue = entry.getValue().longValue();
            if (jLongValue != 0) {
                U key = entry.getKey();
                if (key == ClockUnit.MILLIS) {
                    jSafeAdd = MathUtils.safeAdd(jSafeAdd, MathUtils.safeMultiply(jLongValue, 1000000L));
                } else if (key == ClockUnit.MICROS) {
                    jSafeAdd = MathUtils.safeAdd(jSafeAdd, MathUtils.safeMultiply(jLongValue, 1000L));
                } else if (key == ClockUnit.NANOS) {
                    jSafeAdd = MathUtils.safeAdd(jSafeAdd, jLongValue);
                } else {
                    arrayList.add(TimeSpan.Item.of(jLongValue, key));
                }
            }
        }
        if (jSafeAdd != 0) {
            arrayList.add(TimeSpan.Item.of(jSafeAdd, (IsoUnit) cast(ClockUnit.NANOS)));
        } else if (arrayList.isEmpty()) {
            return ofZero();
        }
        return new Duration<>(arrayList, z);
    }

    private int getIndex(ChronoUnit chronoUnit) {
        return getIndex(chronoUnit, getTotalLength());
    }

    private static <U extends ChronoUnit> int getIndex(ChronoUnit chronoUnit, List<TimeSpan.Item<U>> list) {
        int size = list.size() - 1;
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) >>> 1;
            int iCompare = StdNormalizer.compare((ChronoUnit) list.get(i2).getUnit(), chronoUnit);
            if (iCompare < 0) {
                i = i2 + 1;
            } else {
                if (iCompare <= 0) {
                    return i2;
                }
                size = i2 - 1;
            }
        }
        return -1;
    }

    private static <U extends IsoUnit> TimeSpan.Item<U> replaceFraction(long j, U u) {
        long jSafeMultiply;
        IsoUnit isoUnit;
        if (u.equals(ClockUnit.MILLIS)) {
            jSafeMultiply = MathUtils.safeMultiply(j, 1000000L);
            isoUnit = (IsoUnit) cast(ClockUnit.NANOS);
        } else {
            if (!u.equals(ClockUnit.MICROS)) {
                return null;
            }
            jSafeMultiply = MathUtils.safeMultiply(j, 1000L);
            isoUnit = (IsoUnit) cast(ClockUnit.NANOS);
        }
        return TimeSpan.Item.of(jSafeMultiply, isoUnit);
    }

    private static <U extends IsoUnit> Duration<U> merge(Duration<U> duration, TimeSpan<? extends U> timeSpan) {
        if (duration.isEmpty()) {
            if (isEmpty(timeSpan)) {
                return duration;
            }
            if (timeSpan instanceof Duration) {
                return (Duration) cast(timeSpan);
            }
        }
        HashMap map = new HashMap();
        int iCount = duration.count();
        int i = 0;
        while (true) {
            int i2 = -1;
            if (i >= iCount) {
                break;
            }
            TimeSpan.Item<U> item = duration.getTotalLength().get(i);
            U unit = item.getUnit();
            long amount = item.getAmount();
            if (!duration.isNegative()) {
                i2 = 1;
            }
            map.put(unit, Long.valueOf(MathUtils.safeMultiply(amount, i2)));
            i++;
        }
        boolean zIsNegative = timeSpan.isNegative();
        int size = timeSpan.getTotalLength().size();
        for (int i3 = 0; i3 < size; i3++) {
            TimeSpan.Item<? extends U> item2 = timeSpan.getTotalLength().get(i3);
            U unit2 = item2.getUnit();
            long amount2 = item2.getAmount();
            TimeSpan.Item itemReplaceFraction = replaceFraction(amount2, unit2);
            if (itemReplaceFraction != null) {
                amount2 = itemReplaceFraction.getAmount();
                unit2 = (U) itemReplaceFraction.getUnit();
            }
            if (map.containsKey(unit2)) {
                map.put(unit2, Long.valueOf(MathUtils.safeAdd(((Long) map.get(unit2)).longValue(), MathUtils.safeMultiply(amount2, zIsNegative ? -1 : 1))));
            } else {
                map.put(unit2, Long.valueOf(MathUtils.safeMultiply(amount2, zIsNegative ? -1 : 1)));
            }
        }
        if (duration.isNegative() != zIsNegative) {
            Iterator it = map.entrySet().iterator();
            zIsNegative = false;
            boolean z = true;
            while (it.hasNext()) {
                boolean z2 = ((Long) ((Map.Entry) it.next()).getValue()).longValue() < 0;
                if (z) {
                    z = false;
                    zIsNegative = z2;
                } else if (zIsNegative != z2) {
                    return null;
                }
            }
        }
        if (zIsNegative) {
            for (Map.Entry entry : map.entrySet()) {
                long jLongValue = ((Long) entry.getValue()).longValue();
                Object key = entry.getKey();
                if (jLongValue < 0) {
                    jLongValue = MathUtils.safeNegate(jLongValue);
                }
                map.put(key, Long.valueOf(jLongValue));
            }
        }
        return create(map, zIsNegative);
    }

    private static <U extends IsoUnit> boolean summarize(TimeSpan<? extends U> timeSpan, long[] jArr) {
        long j;
        long jSafeAdd;
        long j2;
        long jSafeAdd2 = jArr[0];
        long jSafeAdd3 = jArr[1];
        long jSafeAdd4 = jArr[2];
        long jSafeAdd5 = jArr[3];
        for (TimeSpan.Item<? extends U> item : timeSpan.getTotalLength()) {
            U unit = item.getUnit();
            long amount = item.getAmount();
            if (timeSpan.isNegative()) {
                amount = MathUtils.safeNegate(amount);
            }
            long j3 = jSafeAdd4;
            long j4 = amount;
            if (unit instanceof CalendarUnit) {
                CalendarUnit calendarUnit = (CalendarUnit) CalendarUnit.class.cast(unit);
                switch (AnonymousClass2.$SwitchMap$net$time4j$CalendarUnit[calendarUnit.ordinal()]) {
                    case 1:
                        jSafeAdd2 = MathUtils.safeAdd(jSafeAdd2, MathUtils.safeMultiply(j4, 12000L));
                        break;
                    case 2:
                        jSafeAdd2 = MathUtils.safeAdd(jSafeAdd2, MathUtils.safeMultiply(j4, CapturePresenter.CONFIRMATION_VIEW_ANIM_DELAY));
                        break;
                    case 3:
                        jSafeAdd2 = MathUtils.safeAdd(jSafeAdd2, MathUtils.safeMultiply(j4, 120L));
                        break;
                    case 4:
                        jSafeAdd2 = MathUtils.safeAdd(jSafeAdd2, MathUtils.safeMultiply(j4, 12L));
                        break;
                    case 5:
                        jSafeAdd2 = MathUtils.safeAdd(jSafeAdd2, MathUtils.safeMultiply(j4, 3L));
                        break;
                    case 6:
                        jSafeAdd2 = MathUtils.safeAdd(jSafeAdd2, j4);
                        break;
                    case 7:
                        jSafeAdd3 = MathUtils.safeAdd(jSafeAdd3, MathUtils.safeMultiply(j4, 7L));
                        break;
                    case 8:
                        jSafeAdd3 = MathUtils.safeAdd(jSafeAdd3, j4);
                        break;
                    default:
                        throw new UnsupportedOperationException(calendarUnit.name());
                }
            } else {
                if (!(unit instanceof ClockUnit)) {
                    return false;
                }
                ClockUnit clockUnit = (ClockUnit) ClockUnit.class.cast(unit);
                switch (AnonymousClass2.$SwitchMap$net$time4j$ClockUnit[clockUnit.ordinal()]) {
                    case 1:
                        j2 = jSafeAdd3;
                        jSafeAdd4 = MathUtils.safeAdd(j3, MathUtils.safeMultiply(j4, 3600L));
                        jSafeAdd3 = j2;
                    case 2:
                        j2 = jSafeAdd3;
                        jSafeAdd4 = MathUtils.safeAdd(j3, MathUtils.safeMultiply(j4, 60L));
                        jSafeAdd3 = j2;
                    case 3:
                        jSafeAdd4 = MathUtils.safeAdd(j3, j4);
                        j2 = jSafeAdd3;
                        jSafeAdd3 = j2;
                    case 4:
                        jSafeAdd5 = MathUtils.safeAdd(jSafeAdd5, MathUtils.safeMultiply(j4, 1000000L));
                        break;
                    case 5:
                        jSafeAdd5 = MathUtils.safeAdd(jSafeAdd5, MathUtils.safeMultiply(j4, 1000L));
                        break;
                    case 6:
                        jSafeAdd5 = MathUtils.safeAdd(jSafeAdd5, j4);
                        break;
                    default:
                        throw new UnsupportedOperationException(clockUnit.name());
                }
            }
            jSafeAdd4 = j3;
            j2 = jSafeAdd3;
            jSafeAdd3 = j2;
        }
        long j5 = jSafeAdd3;
        long j6 = jSafeAdd4;
        long j7 = 0;
        if (jSafeAdd5 != 0) {
            j = jSafeAdd2;
            jSafeAdd5 = MathUtils.safeAdd(MathUtils.safeAdd(jSafeAdd5, MathUtils.safeMultiply(j5, 86400000000000L)), MathUtils.safeMultiply(j6, 1000000000L));
            jSafeAdd = 0;
        } else {
            j = jSafeAdd2;
            if (j6 != 0) {
                jSafeAdd = MathUtils.safeAdd(j6, MathUtils.safeMultiply(j5, 86400L));
            } else {
                j7 = j5;
                jSafeAdd = j6;
            }
        }
        jArr[0] = j;
        jArr[1] = j7;
        jArr[2] = jSafeAdd;
        jArr[3] = jSafeAdd5;
        return true;
    }

    /* renamed from: net.time4j.Duration$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$net$time4j$CalendarUnit;
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
            int[] iArr2 = new int[CalendarUnit.values().length];
            $SwitchMap$net$time4j$CalendarUnit = iArr2;
            try {
                iArr2[CalendarUnit.MILLENNIA.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$net$time4j$CalendarUnit[CalendarUnit.CENTURIES.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$net$time4j$CalendarUnit[CalendarUnit.DECADES.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$net$time4j$CalendarUnit[CalendarUnit.YEARS.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$net$time4j$CalendarUnit[CalendarUnit.QUARTERS.ordinal()] = 5;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$net$time4j$CalendarUnit[CalendarUnit.MONTHS.ordinal()] = 6;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$net$time4j$CalendarUnit[CalendarUnit.WEEKS.ordinal()] = 7;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$net$time4j$CalendarUnit[CalendarUnit.DAYS.ordinal()] = 8;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    private static <U extends IsoUnit> Duration<U> convert(TimeSpan<U> timeSpan) {
        if (timeSpan instanceof Duration) {
            return (Duration) cast(timeSpan);
        }
        return ofZero().plus(timeSpan);
    }

    private boolean isFractionUnit(IsoUnit isoUnit) {
        char symbol = isoUnit.getSymbol();
        return symbol >= '1' && symbol <= '9';
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <U extends IsoUnit> Duration<U> parsePeriod(String str, Class<U> cls) throws ParseException {
        int i;
        boolean z;
        int i2 = 0;
        if (str.length() == 0) {
            throw new ParseException("Empty period string.", 0);
        }
        int i3 = str.charAt(0) == '-' ? 1 : 0;
        boolean z2 = i3;
        try {
            if (str.charAt(i3) != 'P') {
                throw new ParseException("Format symbol 'P' expected: " + str, i3);
            }
            int i4 = i3 + 1;
            ArrayList arrayList = new ArrayList();
            int iIndexOf = str.indexOf(84, i4);
            boolean z3 = iIndexOf == -1;
            if (cls == CalendarUnit.class) {
                i = 0;
            } else if (cls == ClockUnit.class) {
                i = 1;
            } else {
                i = cls == IsoDateUnit.class ? 2 : -1;
            }
            if (!z3) {
                if (iIndexOf <= i4) {
                    z = false;
                } else {
                    if (i == 1) {
                        throw new ParseException("Unexpected date component found: " + str, i4);
                    }
                    z = parse(str.substring(0, iIndexOf), i4, iIndexOf, 0, arrayList);
                }
                if (cls == CalendarUnit.class) {
                    throw new ParseException("Unexpected time component found: " + str, iIndexOf);
                }
                if (z) {
                    parseAlt(str, iIndexOf + 1, str.length(), false, arrayList);
                } else {
                    parse(str, iIndexOf + 1, str.length(), 1, arrayList);
                }
            } else {
                if (i == 1) {
                    throw new ParseException("Format symbol 'T' expected: " + str, i4);
                }
                int length = str.length();
                if (i != -1) {
                    i2 = i;
                }
                parse(str, i4, length, i2, arrayList);
            }
            return new Duration<>(arrayList, z2);
        } catch (IndexOutOfBoundsException e) {
            ParseException parseException = new ParseException("Unexpected termination of period string: " + str, i3);
            parseException.initCause(e);
            throw parseException;
        }
    }

    private static <U extends ChronoUnit> boolean parse(String str, int i, int i2, int i3, List<TimeSpan.Item<U>> list) throws ParseException {
        int i4;
        int i5;
        ChronoUnit dateSymbol;
        char cCharAt = str.charAt(i2 - 1);
        char c = '9';
        char c2 = '0';
        if (cCharAt >= '0' && cCharAt <= '9' && i3 != 2) {
            parseAlt(str, i, i2, i3 == 0, list);
            return true;
        }
        if (i == i2) {
            throw new ParseException(str, i);
        }
        int i6 = i;
        int i7 = i6;
        boolean z = false;
        boolean z2 = false;
        ChronoUnit chronoUnitAddParsedItem = null;
        StringBuilder sb = null;
        while (i7 < i2) {
            char cCharAt2 = str.charAt(i7);
            if (cCharAt2 < c2 || cCharAt2 > c) {
                if (cCharAt2 == ',' || cCharAt2 == '.') {
                    i4 = i7;
                    int i8 = i6;
                    if (sb == null || i3 != 1) {
                        throw new ParseException("Decimal separator misplaced: " + str, i4);
                    }
                    chronoUnitAddParsedItem = addParsedItem(ClockUnit.SECONDS, chronoUnitAddParsedItem, parseAmount(str, sb.toString(), i8), str, i4, list);
                    i6 = i8;
                    z = true;
                    z2 = true;
                } else {
                    if (z) {
                        throw new ParseException("Unexpected char '" + cCharAt2 + "' found: " + str, i7);
                    }
                    if (z2) {
                        if (cCharAt2 != 'S') {
                            throw new ParseException("Second symbol expected: " + str, i7);
                        }
                        if (sb == null) {
                            throw new ParseException("Decimal separator misplaced: " + str, i7 - 1);
                        }
                        if (sb.length() > 9) {
                            sb.delete(9, sb.length());
                        }
                        for (int length = sb.length(); length < 9; length++) {
                            sb.append(c2);
                        }
                        i5 = i6;
                        i4 = i7;
                        chronoUnitAddParsedItem = addParsedItem(ClockUnit.NANOS, chronoUnitAddParsedItem, parseAmount(str, sb.toString(), i6), str, i7, list);
                    } else {
                        i5 = i6;
                        i4 = i7;
                        long amount = parseAmount(str, sb == null ? String.valueOf(cCharAt2) : sb.toString(), i5);
                        if (i3 == 1) {
                            dateSymbol = parseTimeSymbol(cCharAt2, str, i4);
                        } else if (i3 == 2) {
                            dateSymbol = parseWeekBasedSymbol(cCharAt2, str, i4);
                        } else {
                            dateSymbol = parseDateSymbol(cCharAt2, str, i4);
                        }
                        chronoUnitAddParsedItem = addParsedItem(dateSymbol, chronoUnitAddParsedItem, amount, str, i4, list);
                    }
                    i6 = i5;
                    z = true;
                }
                sb = null;
            } else {
                if (sb == null) {
                    sb = new StringBuilder();
                    i6 = i7;
                    z = false;
                }
                sb.append(cCharAt2);
                i4 = i7;
            }
            i7 = i4 + 1;
            c = '9';
            c2 = '0';
        }
        if (z) {
            return false;
        }
        throw new ParseException("Unit symbol expected: " + str, i2);
    }

    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [boolean, int] */
    private static <U extends ChronoUnit> void parseAlt(String str, int i, int i2, boolean z, List<TimeSpan.Item<U>> list) throws ParseException {
        long partialAmount;
        long partialAmount2;
        z = true;
        boolean z2 = true;
        if (z) {
            int i3 = i + 4;
            ?? r10 = (i3 >= i2 || str.charAt(i3) != '-') ? 0 : 1;
            if (r10 == 0 ? i + 7 != i2 : i + 8 != i2) {
                z2 = false;
            }
            Duration duration = getAlternativeDateFormat(r10, z2).parse(str, i);
            long partialAmount3 = duration.getPartialAmount((IsoUnit) CalendarUnit.YEARS);
            if (z2) {
                partialAmount2 = duration.getPartialAmount((IsoUnit) CalendarUnit.DAYS);
                partialAmount = 0;
            } else {
                partialAmount = duration.getPartialAmount((IsoUnit) CalendarUnit.MONTHS);
                long partialAmount4 = duration.getPartialAmount((IsoUnit) CalendarUnit.DAYS);
                if (partialAmount > 12) {
                    throw new ParseException("ISO-8601 prohibits months-part > 12: " + str, i3 + r10);
                }
                if (partialAmount4 > 30) {
                    throw new ParseException("ISO-8601 prohibits days-part > 30: " + str, i + 6 + (r10 == 0 ? 0 : 2));
                }
                partialAmount2 = partialAmount4;
            }
            if (partialAmount3 > 0) {
                list.add(TimeSpan.Item.of(partialAmount3, (ChronoUnit) cast(CalendarUnit.YEARS)));
            }
            if (partialAmount > 0) {
                list.add(TimeSpan.Item.of(partialAmount, (ChronoUnit) cast(CalendarUnit.MONTHS)));
            }
            if (partialAmount2 > 0) {
                list.add(TimeSpan.Item.of(partialAmount2, (ChronoUnit) cast(CalendarUnit.DAYS)));
                return;
            }
            return;
        }
        int i4 = i + 2;
        ?? r5 = (i4 >= i2 || str.charAt(i4) != ':') ? 0 : 1;
        Duration duration2 = getAlternativeTimeFormat(r5).parse(str, i);
        long partialAmount5 = duration2.getPartialAmount((IsoUnit) ClockUnit.HOURS);
        if (partialAmount5 > 0) {
            if (partialAmount5 > 24) {
                throw new ParseException("ISO-8601 prohibits hours-part > 24: " + str, i);
            }
            list.add(TimeSpan.Item.of(partialAmount5, (ChronoUnit) cast(ClockUnit.HOURS)));
        }
        long partialAmount6 = duration2.getPartialAmount((IsoUnit) ClockUnit.MINUTES);
        if (partialAmount6 > 0) {
            if (partialAmount6 > 60) {
                throw new ParseException("ISO-8601 prohibits minutes-part > 60: " + str, i4 + r5);
            }
            list.add(TimeSpan.Item.of(partialAmount6, (ChronoUnit) cast(ClockUnit.MINUTES)));
        }
        long partialAmount7 = duration2.getPartialAmount((IsoUnit) ClockUnit.SECONDS);
        if (partialAmount7 > 0) {
            if (partialAmount7 > 60) {
                throw new ParseException("ISO-8601 prohibits seconds-part > 60: " + str, i + 4 + (r5 == 0 ? 0 : 2));
            }
            list.add(TimeSpan.Item.of(partialAmount7, (ChronoUnit) cast(ClockUnit.SECONDS)));
        }
        long partialAmount8 = duration2.getPartialAmount((IsoUnit) ClockUnit.NANOS);
        if (partialAmount8 > 0) {
            list.add(TimeSpan.Item.of(partialAmount8, (ChronoUnit) cast(ClockUnit.NANOS)));
        }
    }

    private static Formatter<CalendarUnit> createAlternativeDateFormat(boolean z, boolean z2) {
        return Formatter.ofPattern(CalendarUnit.class, z ? z2 ? "YYYY-DDD" : "YYYY-MM-DD" : z2 ? "YYYYDDD" : "YYYYMMDD");
    }

    private static Formatter<ClockUnit> createAlternativeTimeFormat(boolean z) {
        return Formatter.ofPattern(ClockUnit.class, z ? "hh[:mm[:ss[,fffffffff]]]" : "hh[mm[ss[,fffffffff]]]");
    }

    private static CalendarUnit parseDateSymbol(char c, String str, int i) throws ParseException {
        if (c == 'I') {
            return CalendarUnit.MILLENNIA;
        }
        if (c == 'M') {
            return CalendarUnit.MONTHS;
        }
        if (c == 'Q') {
            return CalendarUnit.QUARTERS;
        }
        if (c == 'W') {
            return CalendarUnit.WEEKS;
        }
        if (c != 'Y') {
            switch (c) {
                case 'C':
                    return CalendarUnit.CENTURIES;
                case 'D':
                    return CalendarUnit.DAYS;
                case 'E':
                    return CalendarUnit.DECADES;
                default:
                    throw new ParseException("Symbol '" + c + "' not supported: " + str, i);
            }
        }
        return CalendarUnit.YEARS;
    }

    private static ClockUnit parseTimeSymbol(char c, String str, int i) throws ParseException {
        if (c == 'H') {
            return ClockUnit.HOURS;
        }
        if (c == 'M') {
            return ClockUnit.MINUTES;
        }
        if (c == 'S') {
            return ClockUnit.SECONDS;
        }
        throw new ParseException("Symbol '" + c + "' not supported: " + str, i);
    }

    private static IsoDateUnit parseWeekBasedSymbol(char c, String str, int i) throws ParseException {
        if (c == 'D') {
            return CalendarUnit.DAYS;
        }
        if (c == 'W') {
            return CalendarUnit.WEEKS;
        }
        if (c == 'Y') {
            return CalendarUnit.weekBasedYears();
        }
        throw new ParseException("Symbol '" + c + "' not supported: " + str, i);
    }

    private static <U extends ChronoUnit> ChronoUnit addParsedItem(ChronoUnit chronoUnit, ChronoUnit chronoUnit2, long j, String str, int i, List<TimeSpan.Item<U>> list) throws ParseException {
        if (chronoUnit2 == null || Double.compare(chronoUnit.getLength(), chronoUnit2.getLength()) < 0) {
            if (j != 0) {
                list.add(TimeSpan.Item.of(j, (ChronoUnit) cast(chronoUnit)));
            }
            return chronoUnit;
        }
        if (Double.compare(chronoUnit.getLength(), chronoUnit2.getLength()) == 0) {
            throw new ParseException("Duplicate unit items: " + str, i);
        }
        throw new ParseException("Wrong order of unit items: " + str, i);
    }

    private static long parseAmount(String str, String str2, int i) throws ParseException {
        try {
            return Long.parseLong(str2);
        } catch (NumberFormatException e) {
            ParseException parseException = new ParseException(str, i);
            parseException.initCause(e);
            throw parseException;
        }
    }

    private Object writeReplace() {
        return new SPX(this, 6);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        throw new InvalidObjectException("Serialization proxy required.");
    }

    public static class Builder {
        private final boolean negative;
        private boolean millisSet = false;
        private boolean microsSet = false;
        private boolean nanosSet = false;
        private final List<TimeSpan.Item<IsoUnit>> items = new ArrayList(10);

        Builder(boolean z) {
            this.negative = z;
        }

        public Builder years(int i) {
            set(i, CalendarUnit.YEARS);
            return this;
        }

        public Builder months(int i) {
            set(i, CalendarUnit.MONTHS);
            return this;
        }

        public Builder days(int i) {
            set(i, CalendarUnit.DAYS);
            return this;
        }

        public Builder hours(int i) {
            set(i, ClockUnit.HOURS);
            return this;
        }

        public Builder minutes(int i) {
            set(i, ClockUnit.MINUTES);
            return this;
        }

        public Builder seconds(int i) {
            set(i, ClockUnit.SECONDS);
            return this;
        }

        public Builder millis(int i) {
            millisCalled();
            update(i, 1000000L);
            return this;
        }

        public Builder micros(int i) {
            microsCalled();
            update(i, 1000L);
            return this;
        }

        public Builder nanos(int i) {
            nanosCalled();
            update(i, 1L);
            return this;
        }

        public Duration<IsoUnit> build() {
            if (this.items.isEmpty()) {
                throw new IllegalStateException("Not set any amount and unit.");
            }
            return new Duration<>(this.items, this.negative);
        }

        private Builder set(long j, IsoUnit isoUnit) {
            int size = this.items.size();
            for (int i = 0; i < size; i++) {
                if (this.items.get(i).getUnit() == isoUnit) {
                    throw new IllegalStateException("Already registered: " + isoUnit);
                }
            }
            if (j != 0) {
                this.items.add(TimeSpan.Item.of(j, isoUnit));
            }
            return this;
        }

        private void update(long j, long j2) {
            if (j >= 0) {
                ClockUnit clockUnit = ClockUnit.NANOS;
                for (int size = this.items.size() - 1; size >= 0; size--) {
                    TimeSpan.Item<IsoUnit> item = this.items.get(size);
                    if (item.getUnit().equals(ClockUnit.NANOS)) {
                        this.items.set(size, TimeSpan.Item.of(MathUtils.safeAdd(MathUtils.safeMultiply(j, j2), item.getAmount()), clockUnit));
                        return;
                    }
                }
                if (j != 0) {
                    this.items.add(TimeSpan.Item.of(MathUtils.safeMultiply(j, j2), clockUnit));
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("Illegal negative amount: " + j);
        }

        private void millisCalled() {
            if (this.millisSet) {
                throw new IllegalStateException("Called twice for: " + ClockUnit.MILLIS.name());
            }
            this.millisSet = true;
        }

        private void microsCalled() {
            if (this.microsSet) {
                throw new IllegalStateException("Called twice for: " + ClockUnit.MICROS.name());
            }
            this.microsSet = true;
        }

        private void nanosCalled() {
            if (this.nanosSet) {
                throw new IllegalStateException("Called twice for: " + ClockUnit.NANOS.name());
            }
            this.nanosSet = true;
        }
    }

    private static class ZonalMetric implements TimeMetric<IsoUnit, Duration<IsoUnit>> {
        private final TimeMetric<IsoUnit, Duration<IsoUnit>> metric;
        private final Timezone tz;

        private ZonalMetric(Timezone timezone, IsoUnit... isoUnitArr) {
            if (timezone == null) {
                throw new NullPointerException("Missing timezone.");
            }
            this.tz = timezone;
            this.metric = new Metric(isoUnitArr);
        }

        @Override // net.time4j.engine.TimeMetric
        public <T extends TimePoint<? super IsoUnit, T>> Duration<IsoUnit> between(T t, T t2) {
            boolean z;
            if (t.compareTo(t2) > 0) {
                z = true;
                t2 = t;
                t = t2;
            } else {
                z = false;
            }
            Duration<IsoUnit> durationBetween = this.metric.between(t, t2.plus(getOffset(t) - getOffset(t2), ClockUnit.SECONDS));
            return z ? durationBetween.inverse() : durationBetween;
        }

        @Override // net.time4j.engine.TimeMetric
        public TimeMetric<IsoUnit, Duration<IsoUnit>> reversible() {
            throw new UnsupportedOperationException("Not reversible.");
        }

        private int getOffset(ChronoEntity<?> chronoEntity) {
            return this.tz.getStrategy().getOffset((GregorianDate) chronoEntity.get(PlainDate.COMPONENT), (WallTime) chronoEntity.get(PlainTime.COMPONENT), this.tz).getIntegralAmount();
        }
    }

    public static final class Formatter<U extends IsoUnit> extends TimeSpanFormatter<U, Duration<U>> {
        private static final String JODA_PATTERN = "'P'[-#################Y'Y'][-#################M'M'][-#################W'W'][-#################D'D']['T'[-#################h'H'][-#################m'M'][-#################s'S'[.fffffffff]]]";

        private Formatter(Class<U> cls, String str) {
            super(cls, str);
        }

        public static Formatter<IsoUnit> ofJodaStyle() {
            return ofPattern(IsoUnit.class, JODA_PATTERN);
        }

        public static Formatter<IsoUnit> ofPattern(String str) {
            return ofPattern(IsoUnit.class, str);
        }

        public static <U extends IsoUnit> Formatter<U> ofPattern(Class<U> cls, String str) {
            return new Formatter<>(cls, str);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // net.time4j.format.TimeSpanFormatter
        public Duration<U> convert(Map<U, Long> map, boolean z) {
            return Duration.create(map, z);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // net.time4j.format.TimeSpanFormatter
        public U getUnit(char c) {
            if (c == 'I') {
                return CalendarUnit.MILLENNIA;
            }
            if (c == 'M') {
                return CalendarUnit.MONTHS;
            }
            if (c == 'Q') {
                return CalendarUnit.QUARTERS;
            }
            if (c == 'W') {
                return CalendarUnit.WEEKS;
            }
            if (c == 'Y') {
                return CalendarUnit.YEARS;
            }
            if (c == 'f') {
                return ClockUnit.NANOS;
            }
            if (c == 'h') {
                return ClockUnit.HOURS;
            }
            if (c == 'm') {
                return ClockUnit.MINUTES;
            }
            if (c != 's') {
                switch (c) {
                    case 'C':
                        return CalendarUnit.CENTURIES;
                    case 'D':
                        return CalendarUnit.DAYS;
                    case 'E':
                        return CalendarUnit.DECADES;
                    default:
                        throw new IllegalArgumentException("Unsupported pattern symbol: " + c);
                }
            }
            return ClockUnit.SECONDS;
        }
    }

    private static class Metric<U extends IsoUnit> extends AbstractMetric<U, Duration<U>> {
        private Metric(U... uArr) {
            super(uArr.length > 1, uArr);
        }

        private Metric(Collection<? extends U> collection) {
            super(collection.size() > 1, collection);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // net.time4j.engine.AbstractMetric
        public Duration<U> createEmptyTimeSpan() {
            return Duration.ofZero();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // net.time4j.engine.AbstractMetric
        public Duration<U> createTimeSpan(List<TimeSpan.Item<U>> list, boolean z) {
            return new Duration<>(list, z);
        }

        @Override // net.time4j.engine.AbstractMetric
        protected TimeSpan.Item<U> resolve(TimeSpan.Item<U> item) {
            U unit = item.getUnit();
            if (unit.equals(ClockUnit.MILLIS)) {
                return TimeSpan.Item.of(MathUtils.safeMultiply(item.getAmount(), 1000000L), ClockUnit.NANOS);
            }
            return unit.equals(ClockUnit.MICROS) ? TimeSpan.Item.of(MathUtils.safeMultiply(item.getAmount(), 1000L), ClockUnit.NANOS) : item;
        }
    }

    private static class LengthComparator<U extends IsoUnit, T extends TimePoint<U, T>> implements Comparator<Duration<? extends U>> {
        private final T base;

        private LengthComparator(T t) {
            if (t == null) {
                throw new NullPointerException("Missing base time point.");
            }
            this.base = t;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Comparator
        public int compare(Duration<? extends U> duration, Duration<? extends U> duration2) {
            boolean zIsNegative = duration.isNegative();
            boolean zIsNegative2 = duration2.isNegative();
            if (zIsNegative && !zIsNegative2) {
                return -1;
            }
            if (!zIsNegative && zIsNegative2) {
                return 1;
            }
            if (duration.isEmpty() && duration2.isEmpty()) {
                return 0;
            }
            return this.base.plus(duration).compareTo(this.base.plus(duration2));
        }
    }

    private static class ApproximateNormalizer implements Normalizer<IsoUnit> {
        private final boolean daysToWeeks;
        private final int steps;
        private final ClockUnit unit;

        ApproximateNormalizer(boolean z) {
            this.daysToWeeks = z;
            this.steps = 1;
            this.unit = null;
        }

        ApproximateNormalizer(int i, ClockUnit clockUnit) {
            if (i < 1) {
                throw new IllegalArgumentException("Step width is not positive: " + i);
            }
            if (clockUnit.compareTo(ClockUnit.SECONDS) > 0) {
                throw new IllegalArgumentException("Unsupported unit.");
            }
            this.daysToWeeks = false;
            this.steps = i;
            this.unit = clockUnit;
        }

        /* JADX WARN: Removed duplicated region for block: B:40:0x0151  */
        @Override // net.time4j.engine.Normalizer
        /* renamed from: normalize */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public net.time4j.engine.TimeSpan<net.time4j.IsoUnit> normalize2(net.time4j.engine.TimeSpan<? extends net.time4j.IsoUnit> r14) {
            /*
                Method dump skipped, instructions count: 397
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: net.time4j.Duration.ApproximateNormalizer.normalize2(net.time4j.engine.TimeSpan):net.time4j.Duration");
        }

        private static int safeCast(double d) {
            if (d < -2.147483648E9d || d > 2.147483647E9d) {
                throw new ArithmeticException("Out of range: " + d);
            }
            return (int) d;
        }

        private static IsoUnit maxUnit(double d) {
            for (IsoUnit isoUnit : Arrays.asList(CalendarUnit.YEARS, CalendarUnit.MONTHS, CalendarUnit.DAYS, ClockUnit.HOURS, ClockUnit.MINUTES)) {
                if (d >= isoUnit.getLength()) {
                    return isoUnit;
                }
            }
            return ClockUnit.SECONDS;
        }
    }
}
