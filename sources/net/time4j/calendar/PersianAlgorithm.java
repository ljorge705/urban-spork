package net.time4j.calendar;

import com.drew.metadata.exif.makernotes.LeicaMakernoteDirectory;
import net.time4j.CalendarUnit;
import net.time4j.PlainDate;
import net.time4j.PlainTimestamp;
import net.time4j.base.MathUtils;
import net.time4j.calendar.astro.AstronomicalSeason;
import net.time4j.calendar.astro.SolarTime;
import net.time4j.engine.AttributeKey;
import net.time4j.engine.CalendarSystem;
import net.time4j.engine.EpochDays;
import net.time4j.format.Attributes;
import net.time4j.tz.OffsetSign;
import net.time4j.tz.ZonalOffset;

/* loaded from: classes4.dex */
public enum PersianAlgorithm {
    BORKOWSKI { // from class: net.time4j.calendar.PersianAlgorithm.1
        @Override // net.time4j.calendar.PersianAlgorithm
        boolean isLeapYear(int i, ZonalOffset zonalOffset) {
            PersianAlgorithm.checkYear(i);
            return transform(new PersianCalendar(i + 1, 1, 1), zonalOffset) - transform(new PersianCalendar(i, 1, 1), zonalOffset) == 366;
        }

        @Override // net.time4j.calendar.PersianAlgorithm
        PersianCalendar transform(long j, ZonalOffset zonalOffset) {
            PlainDate plainDateOf = PlainDate.of(j, EpochDays.UTC);
            int year = plainDateOf.getYear();
            int i = year - 621;
            if (plainDateOf.getMonth() < 3) {
                i = year - 622;
            }
            long jBetween = CalendarUnit.DAYS.between(vernalEquinox(i), plainDateOf);
            while (jBetween < 0) {
                i--;
                jBetween = CalendarUnit.DAYS.between(vernalEquinox(i), plainDateOf);
            }
            int i2 = 1;
            while (i2 < 12) {
                long j2 = i2 <= 6 ? 31 : 30;
                if (jBetween < j2) {
                    break;
                }
                jBetween -= j2;
                i2++;
            }
            return PersianCalendar.of(i, i2, (int) (jBetween + 1));
        }

        @Override // net.time4j.calendar.PersianAlgorithm
        long transform(PersianCalendar persianCalendar, ZonalOffset zonalOffset) {
            int year = persianCalendar.getYear();
            int value = persianCalendar.getMonth().getValue();
            return vernalEquinox(year).getDaysSinceEpochUTC() + (((((value - 1) * 31) - ((value / 7) * (value - 7))) + persianCalendar.getDayOfMonth()) - 1);
        }

        private PlainDate vernalEquinox(int i) {
            int[] iArr = {-61, 9, 38, 199, 426, 686, 756, LeicaMakernoteDirectory.TAG_CONTROLLER_BOARD_VERSION, 1111, 1181, 1210, 1635, 2060, 2097, 2192, 2262, 2324, 2394, 2456, 3178};
            int i2 = iArr[19];
            if (i < 1 || i >= i2) {
                throw new IllegalArgumentException("Persian year out of range 1-" + i2 + ": " + i);
            }
            int i3 = i + 621;
            int i4 = 0;
            int i5 = iArr[0];
            int i6 = -14;
            int i7 = 1;
            while (true) {
                if (i7 >= 20) {
                    break;
                }
                int i8 = iArr[i7];
                int i9 = i8 - i5;
                if (i < i8) {
                    i4 = i9;
                    break;
                }
                i6 += ((i9 / 33) * 8) + ((i9 % 33) / 4);
                i7++;
                i5 = i8;
                i4 = i9;
            }
            int i10 = i - i5;
            int i11 = i6 + ((i10 / 33) * 8) + (((i10 % 33) + 3) / 4);
            if (i4 % 33 == 4 && i4 - i10 == 4) {
                i11++;
            }
            return PlainDate.of(i3, 3, (i11 + 20) - (((i3 / 4) - ((((i3 / 100) + 1) * 3) / 4)) - 150));
        }
    },
    KHAYYAM { // from class: net.time4j.calendar.PersianAlgorithm.2
        @Override // net.time4j.calendar.PersianAlgorithm
        boolean isLeapYear(int i, ZonalOffset zonalOffset) {
            PersianAlgorithm.checkYear(i);
            int i2 = i % 33;
            return i2 == 1 || i2 == 5 || i2 == 9 || i2 == 13 || i2 == 17 || i2 == 22 || i2 == 26 || i2 == 30;
        }

        @Override // net.time4j.calendar.PersianAlgorithm
        PersianCalendar transform(long j, ZonalOffset zonalOffset) {
            PersianAlgorithm.checkRange(j);
            long j2 = j + PersianAlgorithm.REFERENCE_ZERO_KHAYYAM;
            int i = (int) (j2 / 12053);
            int i2 = (int) (j2 % 12053);
            int i3 = i * 33;
            int i4 = 0;
            while (i4 < 33) {
                int i5 = (i4 == 1 || i4 == 5 || i4 == 9 || i4 == 13 || i4 == 17 || i4 == 22 || i4 == 26 || i4 == 30) ? 366 : 365;
                if (i2 < i5) {
                    break;
                }
                i2 -= i5;
                i3++;
                i4++;
            }
            int i6 = 1;
            int i7 = 1;
            while (i6 < 12) {
                int i8 = i6 <= 6 ? 31 : 30;
                if (i2 < i8) {
                    break;
                }
                i2 -= i8;
                i7++;
                i6++;
            }
            return new PersianCalendar(i3, i7, 1 + i2);
        }

        @Override // net.time4j.calendar.PersianAlgorithm
        long transform(PersianCalendar persianCalendar, ZonalOffset zonalOffset) {
            int year = persianCalendar.getYear();
            long j = ((year / 33) * PersianAlgorithm.LENGTH_OF_KHAYYAM_CYCLE) - PersianAlgorithm.REFERENCE_ZERO_KHAYYAM;
            int i = 0;
            while (i < year % 33) {
                j += (i == 1 || i == 5 || i == 9 || i == 13 || i == 17 || i == 22 || i == 26 || i == 30) ? 366 : 365;
                i++;
            }
            return ((j + (persianCalendar.getMonth().getValue() <= 7 ? (r7 - 1) * 31 : ((r7 - 1) * 30) + 6)) + persianCalendar.getDayOfMonth()) - 1;
        }
    },
    BIRASHK { // from class: net.time4j.calendar.PersianAlgorithm.3
        @Override // net.time4j.calendar.PersianAlgorithm
        boolean isLeapYear(int i, ZonalOffset zonalOffset) {
            PersianAlgorithm.checkYear(i);
            return MathUtils.floorModulo((MathUtils.floorModulo(i + (-474), 2820) + 512) * 31, 128) < 31;
        }

        @Override // net.time4j.calendar.PersianAlgorithm
        PersianCalendar transform(long j, ZonalOffset zonalOffset) {
            PersianAlgorithm.checkRange(j);
            int i = (int) (j - PersianAlgorithm.START_OF_BIRASHK_CYCLE);
            int iFloorDivide = MathUtils.floorDivide(i, 1029983);
            int iFloorModulo = MathUtils.floorModulo(i, 1029983);
            int iFloorDivide2 = (iFloorDivide * 2820) + 474 + (iFloorModulo == 1029982 ? 2820 : MathUtils.floorDivide((iFloorModulo * 128) + 46878, 46751));
            int iTransform = (int) (j - transform(new PersianCalendar(iFloorDivide2, 1, 1), zonalOffset));
            int i2 = 1;
            int i3 = 1;
            while (i2 < 12) {
                int i4 = i2 <= 6 ? 31 : 30;
                if (iTransform < i4) {
                    break;
                }
                iTransform -= i4;
                i3++;
                i2++;
            }
            return new PersianCalendar(iFloorDivide2, i3, 1 + iTransform);
        }

        @Override // net.time4j.calendar.PersianAlgorithm
        long transform(PersianCalendar persianCalendar, ZonalOffset zonalOffset) {
            int iFloorModulo = MathUtils.floorModulo(persianCalendar.getYear() - 474, 2820);
            return ((MathUtils.floorDivide(r9, 2820) * 1029983) - 492998) + ((iFloorModulo + 473) * 365) + MathUtils.floorDivide(((iFloorModulo + 474) * 31) - 5, 128) + (persianCalendar.getMonth().getValue() <= 7 ? (r9 - 1) * 31 : ((r9 - 1) * 30) + 6) + persianCalendar.getDayOfMonth();
        }
    },
    ASTRONOMICAL { // from class: net.time4j.calendar.PersianAlgorithm.4
        @Override // net.time4j.calendar.PersianAlgorithm
        int getMaxPersianYear() {
            return 2378;
        }

        @Override // net.time4j.calendar.PersianAlgorithm
        boolean isLeapYear(int i, ZonalOffset zonalOffset) {
            if (i < 1 || i > getMaxPersianYear()) {
                throw new IllegalArgumentException("Out of range: " + i);
            }
            return transform(new PersianCalendar(i + 1, 1, 1), zonalOffset) - transform(new PersianCalendar(i, 1, 1), zonalOffset) == 366;
        }

        @Override // net.time4j.calendar.PersianAlgorithm
        PersianCalendar transform(long j, ZonalOffset zonalOffset) {
            if (j < -492997 || j > 375548) {
                throw new IllegalArgumentException("Out of range: " + j);
            }
            PlainDate plainDateOf = PlainDate.of(j, EpochDays.UTC);
            int year = plainDateOf.getYear();
            int i = year - 621;
            if (plainDateOf.getMonth() < 3) {
                i = year - 622;
            }
            long jBetween = CalendarUnit.DAYS.between(vernalEquinox(i, zonalOffset), plainDateOf);
            while (jBetween < 0) {
                i--;
                jBetween = CalendarUnit.DAYS.between(vernalEquinox(i, zonalOffset), plainDateOf);
            }
            int i2 = 1;
            while (i2 < 12) {
                long j2 = i2 <= 6 ? 31 : 30;
                if (jBetween < j2) {
                    break;
                }
                jBetween -= j2;
                i2++;
            }
            return new PersianCalendar(i, i2, (int) (jBetween + 1));
        }

        @Override // net.time4j.calendar.PersianAlgorithm
        long transform(PersianCalendar persianCalendar, ZonalOffset zonalOffset) {
            int year = persianCalendar.getYear();
            int value = persianCalendar.getMonth().getValue();
            return vernalEquinox(year, zonalOffset).getDaysSinceEpochUTC() + (((((value - 1) * 31) - ((value / 7) * (value - 7))) + persianCalendar.getDayOfMonth()) - 1);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private PlainDate vernalEquinox(int i, ZonalOffset zonalOffset) {
            PlainTimestamp plainTimestamp = (PlainTimestamp) AstronomicalSeason.VERNAL_EQUINOX.inYear(i + 621).get(SolarTime.apparentAt(zonalOffset));
            if (plainTimestamp.getHour() >= 12) {
                return (PlainDate) plainTimestamp.getCalendarDate().plus(1L, CalendarUnit.DAYS);
            }
            return plainTimestamp.getCalendarDate();
        }
    };

    private static final int LENGTH_OF_KHAYYAM_CYCLE = 12053;
    private static final long REFERENCE_ZERO_KHAYYAM = 493363;
    private static final long START_OF_BIRASHK_CYCLE = -319872;
    static final ZonalOffset STD_OFFSET = ZonalOffset.ofHoursMinutes(OffsetSign.AHEAD_OF_UTC, 3, 30);
    private static final AttributeKey<PersianAlgorithm> ATTRIBUTE = Attributes.createKey("PERSIAN_ALGORITHM", PersianAlgorithm.class);

    public static AttributeKey<PersianAlgorithm> attribute() {
        return ATTRIBUTE;
    }

    int getMaxPersianYear() {
        return 3000;
    }

    abstract boolean isLeapYear(int i, ZonalOffset zonalOffset);

    abstract long transform(PersianCalendar persianCalendar, ZonalOffset zonalOffset);

    abstract PersianCalendar transform(long j, ZonalOffset zonalOffset);

    public boolean isLeapYear(int i) {
        return isLeapYear(i, STD_OFFSET);
    }

    boolean isValid(int i, int i2, int i3, ZonalOffset zonalOffset) {
        if (i < 1 || i > getMaxPersianYear() || i2 < 1 || i2 > 12 || i3 < 1) {
            return false;
        }
        if (i2 <= 6) {
            return i3 <= 31;
        }
        if (i2 <= 11) {
            return i3 <= 30;
        }
        return i3 <= (isLeapYear(i, zonalOffset) ? 30 : 29);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkRange(long j) {
        CalendarSystem<T> calendarSystem = PersianCalendar.axis().getCalendarSystem();
        if (j < calendarSystem.getMinimumSinceUTC() || j > calendarSystem.getMaximumSinceUTC()) {
            throw new IllegalArgumentException("Out of range: " + j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkYear(int i) {
        if (i < 1 || i > 3000) {
            throw new IllegalArgumentException("Out of range: " + i);
        }
    }
}
