package net.time4j.calendar;

import net.time4j.Moment;
import net.time4j.PlainDate;
import net.time4j.base.MathUtils;
import net.time4j.calendar.EastAsianCalendar;
import net.time4j.calendar.astro.AstronomicalSeason;
import net.time4j.calendar.astro.JulianDay;
import net.time4j.calendar.astro.MoonPhase;
import net.time4j.engine.CalendarDate;
import net.time4j.engine.CalendarSystem;
import net.time4j.engine.EpochDays;
import net.time4j.tz.ZonalOffset;

/* loaded from: classes4.dex */
abstract class EastAsianCS<D extends EastAsianCalendar<?, D>> implements CalendarSystem<D> {
    static final double MEAN_SYNODIC_MONTH = 29.530588861d;
    static final double MEAN_TROPICAL_YEAR = 365.242189d;
    private static final long CALENDAR_REFORM_1645 = PlainDate.of(1645, 1, 28).getDaysSinceEpochUTC();
    private static final long MAX_LIMIT = PlainDate.of(3000, 1, 27).getDaysSinceEpochUTC();
    private static final long EPOCH_CHINESE = PlainDate.of(-2636, 2, 15).getDaysSinceEpochUTC();

    abstract D create(int i, int i2, EastAsianMonth eastAsianMonth, int i3, long j);

    abstract int[] getLeapMonths();

    @Override // net.time4j.engine.CalendarSystem
    public final long getMaximumSinceUTC() {
        return MAX_LIMIT;
    }

    @Override // net.time4j.engine.CalendarSystem
    public long getMinimumSinceUTC() {
        return CALENDAR_REFORM_1645;
    }

    abstract ZonalOffset getOffset(long j);

    EastAsianCS() {
    }

    @Override // net.time4j.engine.CalendarSystem
    public final D transform(long j) {
        long jWinterOnOrBefore = winterOnOrBefore(j);
        long jWinterOnOrBefore2 = winterOnOrBefore(370 + jWinterOnOrBefore);
        long jNewMoonOnOrAfter = newMoonOnOrAfter(jWinterOnOrBefore + 1);
        long jNewMoonBefore = newMoonBefore(jWinterOnOrBefore2 + 1);
        long jNewMoonBefore2 = newMoonBefore(j + 1);
        boolean z = lunations(jNewMoonOnOrAfter, jNewMoonBefore) == 12;
        long jLunations = lunations(jNewMoonOnOrAfter, jNewMoonBefore2);
        if (z && hasLeapMonth(jNewMoonOnOrAfter, jNewMoonBefore2)) {
            jLunations--;
        }
        int iFloorModulo = MathUtils.floorModulo(jLunations, 12);
        int i = iFloorModulo != 0 ? iFloorModulo : 12;
        long jFloor = (long) Math.floor((1.5d - (i / 12.0d)) + ((j - EPOCH_CHINESE) / MEAN_TROPICAL_YEAR));
        int iFloorDivide = 1 + ((int) MathUtils.floorDivide(jFloor - 1, 60));
        int iFloorModulo2 = MathUtils.floorModulo(jFloor, 60);
        int i2 = iFloorModulo2 != 0 ? iFloorModulo2 : 60;
        int i3 = (int) ((j - jNewMoonBefore2) + 1);
        EastAsianMonth eastAsianMonthValueOf = EastAsianMonth.valueOf(i);
        if (z && hasNoMajorSolarTerm(jNewMoonBefore2) && !hasLeapMonth(jNewMoonOnOrAfter, newMoonBefore(jNewMoonBefore2))) {
            eastAsianMonthValueOf = eastAsianMonthValueOf.withLeap();
        }
        return (D) create(iFloorDivide, i2, eastAsianMonthValueOf, i3, j);
    }

    @Override // net.time4j.engine.CalendarSystem
    public final long transform(D d) {
        return transform(d.getCycle(), d.getYear().getNumber(), d.getMonth(), d.getDayOfMonth());
    }

    final int getLeapMonth(int i, int i2) {
        int[] leapMonths = getLeapMonths();
        int i3 = (((i - 1) * 60) + i2) - 1;
        int iMax = ((i3 - leapMonths[0]) / 3) * 2;
        while (iMax < leapMonths.length) {
            int i4 = leapMonths[iMax];
            if (i4 >= i3) {
                if (i4 > i3) {
                    return 0;
                }
                return leapMonths[iMax + 1];
            }
            iMax += Math.max(((i3 - i4) / 3) * 2, 2);
        }
        return 0;
    }

    final long transform(int i, int i2, EastAsianMonth eastAsianMonth, int i3) {
        if (!isValid(i, i2, eastAsianMonth, i3)) {
            throw new IllegalArgumentException("Invalid date.");
        }
        return (firstDayOfMonth(i, i2, eastAsianMonth) + i3) - 1;
    }

    boolean isValid(int i, int i2, EastAsianMonth eastAsianMonth, int i3) {
        if (i < 72 || i > 94 || i2 < 1 || i2 > 60 || ((i == 72 && i2 < 22) || ((i == 94 && i2 > 56) || i3 < 1 || i3 > 30 || eastAsianMonth == null || (eastAsianMonth.isLeap() && eastAsianMonth.getNumber() != getLeapMonth(i, i2))))) {
            return false;
        }
        if (i3 != 30) {
            return true;
        }
        long jFirstDayOfMonth = firstDayOfMonth(i, i2, eastAsianMonth);
        return newMoonOnOrAfter(1 + jFirstDayOfMonth) - jFirstDayOfMonth == 30;
    }

    final long newYear(int i, int i2) {
        return newYearOnOrBefore((long) Math.floor(EPOCH_CHINESE + (((((i - 1) * 60) + i2) - 0.5d) * MEAN_TROPICAL_YEAR)));
    }

    final int getMajorSolarTerm(long j) {
        int iFloor = (((int) Math.floor(SolarTerm.solarLongitude(JulianDay.ofEphemerisTime(midnight(j)).getValue()) / 30.0d)) + 2) % 12;
        if (iFloor == 0) {
            return 12;
        }
        return iFloor;
    }

    final boolean hasNoMajorSolarTerm(long j) {
        return (((int) Math.floor(SolarTerm.solarLongitude(JulianDay.ofEphemerisTime(midnight(j)).getValue()) / 30.0d)) + 2) % 12 == (((int) Math.floor(SolarTerm.solarLongitude(JulianDay.ofEphemerisTime(midnight(newMoonOnOrAfter(j + 1))).getValue()) / 30.0d)) + 2) % 12;
    }

    final long newMoonOnOrAfter(long j) {
        return MoonPhase.NEW_MOON.atOrAfter(midnight(j)).toZonalTimestamp(getOffset(j)).toDate().getDaysSinceEpochUTC();
    }

    Moment midnight(long j) {
        return PlainDate.of(j, EpochDays.UTC).atStartOfDay().at(getOffset(j));
    }

    private long newMoonBefore(long j) {
        return MoonPhase.NEW_MOON.before(midnight(j)).toZonalTimestamp(getOffset(j)).toDate().getDaysSinceEpochUTC();
    }

    private static long lunations(long j, long j2) {
        return Math.round((j2 - j) / MEAN_SYNODIC_MONTH);
    }

    private long newYearInSui(long j) {
        long jWinterOnOrBefore = winterOnOrBefore(j);
        long jWinterOnOrBefore2 = winterOnOrBefore(370 + jWinterOnOrBefore);
        long jNewMoonOnOrAfter = newMoonOnOrAfter(jWinterOnOrBefore + 1);
        long jNewMoonOnOrAfter2 = newMoonOnOrAfter(jNewMoonOnOrAfter + 1);
        return (lunations(jNewMoonOnOrAfter, newMoonBefore(jWinterOnOrBefore2 + 1)) == 12 && (hasNoMajorSolarTerm(jNewMoonOnOrAfter) || hasNoMajorSolarTerm(jNewMoonOnOrAfter2))) ? newMoonOnOrAfter(jNewMoonOnOrAfter2 + 1) : jNewMoonOnOrAfter2;
    }

    private long newYearOnOrBefore(long j) {
        long jNewYearInSui = newYearInSui(j);
        return j >= jNewYearInSui ? jNewYearInSui : newYearInSui(j - 180);
    }

    private boolean hasLeapMonth(long j, long j2) {
        return j2 >= j && (hasNoMajorSolarTerm(j2) || hasLeapMonth(j, newMoonBefore(j2)));
    }

    private long firstDayOfMonth(int i, int i2, EastAsianMonth eastAsianMonth) {
        long jNewMoonOnOrAfter = newMoonOnOrAfter(newYear(i, i2) + ((eastAsianMonth.getNumber() - 1) * 29));
        return eastAsianMonth.equals(transform(jNewMoonOnOrAfter).getMonth()) ? jNewMoonOnOrAfter : newMoonOnOrAfter(jNewMoonOnOrAfter + 1);
    }

    private long winterOnOrBefore(long j) {
        ZonalOffset offset = getOffset(j);
        PlainDate plainDateOf = PlainDate.of(j, EpochDays.UTC);
        int year = (plainDateOf.getMonth() <= 11 || plainDateOf.getDayOfMonth() <= 15) ? plainDateOf.getYear() - 1 : plainDateOf.getYear();
        PlainDate calendarDate = AstronomicalSeason.WINTER_SOLSTICE.inYear(year).toZonalTimestamp(offset).getCalendarDate();
        if (calendarDate.isAfter((CalendarDate) plainDateOf)) {
            calendarDate = AstronomicalSeason.WINTER_SOLSTICE.inYear(year - 1).toZonalTimestamp(offset).getCalendarDate();
        }
        return calendarDate.getDaysSinceEpochUTC();
    }
}
