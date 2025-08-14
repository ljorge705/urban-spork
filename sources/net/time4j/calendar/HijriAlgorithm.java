package net.time4j.calendar;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.time4j.base.MathUtils;
import net.time4j.engine.CalendarEra;
import net.time4j.engine.VariantSource;

/* loaded from: classes4.dex */
public enum HijriAlgorithm implements VariantSource {
    EAST_ISLAMIC_CIVIL("islamic-eastc", new int[]{2, 5, 7, 10, 13, 15, 18, 21, 24, 26, 29}, true),
    EAST_ISLAMIC_ASTRO("islamic-easta", new int[]{2, 5, 7, 10, 13, 15, 18, 21, 24, 26, 29}, false),
    WEST_ISLAMIC_CIVIL("islamic-civil", new int[]{2, 5, 7, 10, 13, 16, 18, 21, 24, 26, 29}, true),
    WEST_ISLAMIC_ASTRO("islamic-tbla", new int[]{2, 5, 7, 10, 13, 16, 18, 21, 24, 26, 29}, false),
    FATIMID_CIVIL("islamic-fatimidc", new int[]{2, 5, 8, 10, 13, 16, 19, 21, 24, 27, 29}, true),
    FATIMID_ASTRO("islamic-fatimida", new int[]{2, 5, 8, 10, 13, 16, 19, 21, 24, 27, 29}, false),
    HABASH_AL_HASIB_CIVIL("islamic-habashalhasibc", new int[]{2, 5, 8, 11, 13, 16, 19, 21, 24, 27, 30}, true),
    HABASH_AL_HASIB_ASTRO("islamic-habashalhasiba", new int[]{2, 5, 8, 11, 13, 16, 19, 21, 24, 27, 30}, false);

    private final transient Transformer calsys;
    private static final long LENGTH_OF_30_YEAR_CYCLE = 10631;
    private static final long START_622_07_15 = -492879;
    private static final long START_622_07_16 = (-492879) + 1;
    private static final int MAX_YEAR = 1600;
    private static final long ASTRO_1600_12_29 = 74106;
    private static final long CIVIL_1600_12_29 = 74106 + 1;

    HijriAlgorithm(String str, int[] iArr, boolean z) {
        this.calsys = new Transformer(str, iArr, z, 0);
    }

    @Override // net.time4j.engine.VariantSource
    public String getVariant() {
        return this.calsys.variant;
    }

    EraYearMonthDaySystem<HijriCalendar> getCalendarSystem(int i) {
        return i == 0 ? this.calsys : new Transformer(HijriAdjustment.of(getVariant(), i).getVariant(), this.calsys.intercalaries, this.calsys.civil, i);
    }

    private static class Transformer implements EraYearMonthDaySystem<HijriCalendar> {
        private final int adjustment;
        private final boolean civil;
        private final int[] intercalaries;
        private final String variant;

        Transformer(String str, int[] iArr, boolean z, int i) {
            this.variant = str;
            this.intercalaries = iArr;
            this.civil = z;
            this.adjustment = i;
        }

        @Override // net.time4j.calendar.EraYearMonthDaySystem
        public boolean isValid(CalendarEra calendarEra, int i, int i2, int i3) {
            return calendarEra == HijriEra.ANNO_HEGIRAE && i >= 1 && i <= HijriAlgorithm.MAX_YEAR && i2 >= 1 && i2 <= 12 && i3 >= 1 && i3 <= getLengthOfMonth(calendarEra, i, i2);
        }

        @Override // net.time4j.calendar.EraYearMonthDaySystem
        public int getLengthOfMonth(CalendarEra calendarEra, int i, int i2) {
            if (calendarEra != HijriEra.ANNO_HEGIRAE) {
                throw new IllegalArgumentException("Wrong era: " + calendarEra);
            }
            if (i < 1 || i > HijriAlgorithm.MAX_YEAR || i2 < 1 || i2 > 12) {
                throw new IllegalArgumentException("Out of bounds: " + i + "/" + i2);
            }
            if (i2 == 12) {
                return Arrays.binarySearch(this.intercalaries, ((i - 1) % 30) + 1) >= 0 ? 30 : 29;
            }
            return i2 % 2 == 1 ? 30 : 29;
        }

        @Override // net.time4j.calendar.EraYearMonthDaySystem
        public int getLengthOfYear(CalendarEra calendarEra, int i) {
            if (calendarEra != HijriEra.ANNO_HEGIRAE) {
                throw new IllegalArgumentException("Wrong era: " + calendarEra);
            }
            if (i < 1 || i > HijriAlgorithm.MAX_YEAR) {
                throw new IllegalArgumentException("Out of bounds: yearOfEra=" + i);
            }
            return Arrays.binarySearch(this.intercalaries, ((i - 1) % 30) + 1) >= 0 ? 355 : 354;
        }

        /* JADX WARN: Removed duplicated region for block: B:37:0x0083  */
        @Override // net.time4j.engine.CalendarSystem
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public net.time4j.calendar.HijriCalendar transform(long r8) {
            /*
                r7 = this;
                int r0 = r7.adjustment
                long r0 = (long) r0
                long r0 = net.time4j.base.MathUtils.safeAdd(r8, r0)
                boolean r2 = r7.civil
                if (r2 == 0) goto L10
                long r2 = net.time4j.calendar.HijriAlgorithm.access$400()
                goto L14
            L10:
                long r2 = net.time4j.calendar.HijriAlgorithm.access$500()
            L14:
                int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r4 < 0) goto L9c
                boolean r4 = r7.civil
                if (r4 == 0) goto L21
                long r4 = net.time4j.calendar.HijriAlgorithm.access$600()
                goto L25
            L21:
                long r4 = net.time4j.calendar.HijriAlgorithm.access$700()
            L25:
                int r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
                if (r4 > 0) goto L9c
                long r8 = net.time4j.base.MathUtils.safeSubtract(r0, r2)
                long r0 = net.time4j.calendar.HijriAlgorithm.access$800()
                long r0 = r8 / r0
                r2 = 30
                long r0 = r0 * r2
                int r0 = net.time4j.base.MathUtils.safeCast(r0)
                r1 = 1
                int r0 = r0 + r1
                long r2 = net.time4j.calendar.HijriAlgorithm.access$800()
                long r8 = r8 % r2
                int r8 = (int) r8
                r9 = r1
            L43:
                r2 = 30
                if (r9 >= r2) goto L5c
                int[] r3 = r7.intercalaries
                int r3 = java.util.Arrays.binarySearch(r3, r9)
                if (r3 < 0) goto L52
                r3 = 355(0x163, float:4.97E-43)
                goto L54
            L52:
                r3 = 354(0x162, float:4.96E-43)
            L54:
                if (r8 <= r3) goto L5c
                int r8 = r8 - r3
                int r0 = r0 + 1
                int r9 = r9 + 1
                goto L43
            L5c:
                r9 = r1
                r3 = r9
            L5e:
                r4 = 29
                r5 = 12
                if (r9 >= r5) goto L73
                int r6 = r9 % 2
                if (r6 != 0) goto L6a
                r6 = r4
                goto L6b
            L6a:
                r6 = r2
            L6b:
                if (r8 <= r6) goto L73
                int r8 = r8 - r6
                int r3 = r3 + 1
                int r9 = r9 + 1
                goto L5e
            L73:
                int r8 = r8 + r1
                if (r3 != r5) goto L85
                int r9 = r0 + (-1)
                int r9 = r9 % r2
                int r9 = r9 + r1
                int[] r6 = r7.intercalaries
                int r9 = java.util.Arrays.binarySearch(r6, r9)
                if (r9 < 0) goto L83
                goto L89
            L83:
                r2 = r4
                goto L89
            L85:
                int r9 = r3 % 2
                if (r9 != r1) goto L83
            L89:
                if (r8 <= r2) goto L94
                int r3 = r3 + 1
                if (r3 <= r5) goto L93
                int r0 = r0 + 1
                r8 = r1
                goto L95
            L93:
                r8 = r1
            L94:
                r1 = r3
            L95:
                java.lang.String r9 = r7.variant
                net.time4j.calendar.HijriCalendar r8 = net.time4j.calendar.HijriCalendar.of(r9, r0, r1, r8)
                return r8
            L9c:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "Out of supported range: "
                r1.<init>(r2)
                java.lang.StringBuilder r8 = r1.append(r8)
                java.lang.String r8 = r8.toString()
                r0.<init>(r8)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: net.time4j.calendar.HijriAlgorithm.Transformer.transform(long):net.time4j.calendar.HijriCalendar");
        }

        @Override // net.time4j.engine.CalendarSystem
        public long transform(HijriCalendar hijriCalendar) {
            int year = hijriCalendar.getYear();
            int value = hijriCalendar.getMonth().getValue();
            int dayOfMonth = hijriCalendar.getDayOfMonth();
            if (year < 1 || year > HijriAlgorithm.MAX_YEAR || value < 1 || value > 12 || dayOfMonth < 1 || dayOfMonth > 30) {
                throw new IllegalArgumentException("Out of supported range: " + hijriCalendar);
            }
            long j = (r0 / 30) * HijriAlgorithm.LENGTH_OF_30_YEAR_CYCLE;
            int i = ((year - 1) % 30) + 1;
            for (int i2 = 1; i2 < i; i2++) {
                j += Arrays.binarySearch(this.intercalaries, i2) >= 0 ? 355L : 354L;
            }
            for (int i3 = 1; i3 < value; i3++) {
                j += i3 % 2 == 0 ? 29L : 30L;
            }
            if (dayOfMonth == 30 && ((value == 12 && Arrays.binarySearch(this.intercalaries, i) < 0) || (value != 12 && value % 2 == 0))) {
                throw new IllegalArgumentException("Invalid day-of-month: " + hijriCalendar);
            }
            return MathUtils.safeSubtract(((this.civil ? HijriAlgorithm.START_622_07_16 : HijriAlgorithm.START_622_07_15) + (j + dayOfMonth)) - 1, this.adjustment);
        }

        @Override // net.time4j.engine.CalendarSystem
        public long getMinimumSinceUTC() {
            return MathUtils.safeSubtract(this.civil ? HijriAlgorithm.START_622_07_16 : HijriAlgorithm.START_622_07_15, this.adjustment);
        }

        @Override // net.time4j.engine.CalendarSystem
        public long getMaximumSinceUTC() {
            return MathUtils.safeSubtract(this.civil ? HijriAlgorithm.CIVIL_1600_12_29 : HijriAlgorithm.ASTRO_1600_12_29, this.adjustment);
        }

        @Override // net.time4j.engine.CalendarSystem
        public List<CalendarEra> getEras() {
            return Collections.singletonList(HijriEra.ANNO_HEGIRAE);
        }
    }
}
