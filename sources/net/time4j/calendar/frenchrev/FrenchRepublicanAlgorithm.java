package net.time4j.calendar.frenchrev;

import net.time4j.CalendarUnit;
import net.time4j.PlainDate;
import net.time4j.PlainTimestamp;
import net.time4j.base.MathUtils;
import net.time4j.calendar.astro.AstronomicalSeason;
import net.time4j.calendar.astro.SolarTime;
import net.time4j.engine.AttributeKey;
import net.time4j.engine.EpochDays;
import net.time4j.format.Attributes;
import net.time4j.tz.OffsetSign;
import net.time4j.tz.ZonalOffset;

/* loaded from: classes4.dex */
public enum FrenchRepublicanAlgorithm {
    EQUINOX { // from class: net.time4j.calendar.frenchrev.FrenchRepublicanAlgorithm.1
        @Override // net.time4j.calendar.frenchrev.FrenchRepublicanAlgorithm
        public boolean isLeapYear(int i) {
            if (i < 1 || i > 1202) {
                throw new IllegalArgumentException("Out of range: " + i);
            }
            return autumnalEquinox(i + 1).getDaysSinceEpochUTC() - autumnalEquinox(i).getDaysSinceEpochUTC() == 366;
        }

        @Override // net.time4j.calendar.frenchrev.FrenchRepublicanAlgorithm
        FrenchRepublicanCalendar transform(long j) {
            FrenchRepublicanAlgorithm.check(j);
            PlainDate plainDateOf = PlainDate.of(j, EpochDays.UTC);
            int year = plainDateOf.getYear();
            int i = year - 1791;
            if (plainDateOf.getMonth() < 9) {
                i = year - 1792;
            }
            long jBetween = CalendarUnit.DAYS.between(autumnalEquinox(i), plainDateOf);
            while (jBetween < 0) {
                i--;
                jBetween = CalendarUnit.DAYS.between(autumnalEquinox(i), plainDateOf);
            }
            return new FrenchRepublicanCalendar(i, (int) (jBetween + 1));
        }

        @Override // net.time4j.calendar.frenchrev.FrenchRepublicanAlgorithm
        long transform(FrenchRepublicanCalendar frenchRepublicanCalendar) {
            return (autumnalEquinox(frenchRepublicanCalendar.getYear()).getDaysSinceEpochUTC() + frenchRepublicanCalendar.getDayOfYear()) - 1;
        }

        private PlainDate autumnalEquinox(int i) {
            return ((PlainTimestamp) AstronomicalSeason.AUTUMNAL_EQUINOX.inYear(i + 1791).get(SolarTime.apparentAt(FrenchRepublicanAlgorithm.PARIS_OBSERVATORY))).getCalendarDate();
        }
    },
    ROMME { // from class: net.time4j.calendar.frenchrev.FrenchRepublicanAlgorithm.2
        @Override // net.time4j.calendar.frenchrev.FrenchRepublicanAlgorithm
        public boolean isLeapYear(int i) {
            if (i < 1 || i > 1202) {
                throw new IllegalArgumentException("Out of range: " + i);
            }
            if (i == 3 || i == 7 || i == 11) {
                return true;
            }
            if (i >= 15) {
                return (i & 3) == 0 && (i % 100 != 0 || i % 400 == 0);
            }
            return false;
        }

        @Override // net.time4j.calendar.frenchrev.FrenchRepublicanAlgorithm
        FrenchRepublicanCalendar transform(long j) {
            if (j >= FrenchRepublicanAlgorithm.ABOLITION) {
                FrenchRepublicanAlgorithm.check(j);
                int iFloorDivide = (int) (MathUtils.floorDivide(((j - FrenchRepublicanAlgorithm.EPOCH) + 2) * 4000, 1460969) + 1);
                long jTransform = transform(new FrenchRepublicanCalendar(iFloorDivide, 1));
                if (jTransform > j) {
                    jTransform = transform(new FrenchRepublicanCalendar(iFloorDivide - 1, 1));
                    iFloorDivide--;
                }
                return new FrenchRepublicanCalendar(iFloorDivide, (int) ((j - jTransform) + 1));
            }
            return EQUINOX.transform(j);
        }

        @Override // net.time4j.calendar.frenchrev.FrenchRepublicanAlgorithm
        long transform(FrenchRepublicanCalendar frenchRepublicanCalendar) {
            if (frenchRepublicanCalendar.getYear() < 15) {
                return EQUINOX.transform(frenchRepublicanCalendar);
            }
            int year = frenchRepublicanCalendar.getYear() - 1;
            return ((((FrenchRepublicanAlgorithm.EPOCH - 1) + (year * 365)) + MathUtils.floorDivide(year, 4)) - MathUtils.floorDivide(year, 100)) + MathUtils.floorDivide(year, 400) + frenchRepublicanCalendar.getDayOfYear();
        }
    };

    private static final ZonalOffset PARIS_OBSERVATORY = ZonalOffset.atLongitude(OffsetSign.AHEAD_OF_UTC, 2, 20, 14.025d);
    private static final long ABOLITION = ((Long) PlainDate.of(1806, 1, 1).get(EpochDays.UTC)).longValue();
    private static final long EPOCH = ((Long) PlainDate.of(1792, 9, 22).get(EpochDays.UTC)).longValue();
    private static final AttributeKey<FrenchRepublicanAlgorithm> ATTRIBUTE = Attributes.createKey("FRENCH_REPUBLICAN_ALGORITHM", FrenchRepublicanAlgorithm.class);

    public static AttributeKey<FrenchRepublicanAlgorithm> attribute() {
        return ATTRIBUTE;
    }

    abstract long transform(FrenchRepublicanCalendar frenchRepublicanCalendar);

    abstract FrenchRepublicanCalendar transform(long j);

    public boolean isLeapYear(int i) {
        throw new AbstractMethodError();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void check(long j) {
        if (j < -65478 || j > 373542) {
            throw new IllegalArgumentException("Out of range: " + j);
        }
    }
}
