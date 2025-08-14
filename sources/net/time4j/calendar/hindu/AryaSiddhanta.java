package net.time4j.calendar.hindu;

import java.util.Collections;
import java.util.List;
import net.time4j.engine.CalendarEra;
import net.time4j.engine.EpochDays;

/* loaded from: classes4.dex */
public enum AryaSiddhanta {
    SOLAR { // from class: net.time4j.calendar.hindu.AryaSiddhanta.1
        @Override // net.time4j.calendar.hindu.AryaSiddhanta
        public HinduCS getCalendarSystem() {
            return new OldCS(true);
        }
    },
    LUNAR { // from class: net.time4j.calendar.hindu.AryaSiddhanta.2
        @Override // net.time4j.calendar.hindu.AryaSiddhanta
        public HinduCS getCalendarSystem() {
            return new OldCS(false);
        }
    };

    static final String PREFIX = "AryaSiddhanta@";

    abstract HinduCS getCalendarSystem();

    public HinduVariant variant() {
        return this == SOLAR ? HinduVariant.VAR_OLD_SOLAR : HinduVariant.VAR_OLD_LUNAR;
    }

    private static class OldCS extends HinduCS {
        private static final double ARYA_LUNAR_MONTH = 29.530581807581694d;
        private static final double ARYA_SOLAR_MONTH = 30.43822337962963d;
        private static final double ARYA_SOLAR_YEAR = 365.25868055555554d;

        OldCS(boolean z) {
            super(z ? HinduVariant.VAR_OLD_SOLAR : HinduVariant.VAR_OLD_LUNAR);
        }

        @Override // net.time4j.calendar.hindu.HinduCS, net.time4j.engine.CalendarSystem
        public List<CalendarEra> getEras() {
            return Collections.singletonList(HinduEra.KALI_YUGA);
        }

        @Override // net.time4j.calendar.hindu.HinduCS
        HinduCalendar create(long j) {
            double dTransform = (EpochDays.RATA_DIE.transform(j, EpochDays.UTC) - (-1132959)) + 0.25d;
            if (isSolar()) {
                return new HinduCalendar(this.variant, (int) Math.floor(dTransform / ARYA_SOLAR_YEAR), HinduMonth.ofSolar(((int) modulo(Math.floor(dTransform / ARYA_SOLAR_MONTH), 12.0d)) + 1), HinduDay.valueOf(((int) Math.floor(modulo(dTransform, ARYA_SOLAR_MONTH))) + 1), j);
            }
            double dModulo = dTransform - modulo(dTransform, ARYA_LUNAR_MONTH);
            double dModulo2 = modulo(dModulo, ARYA_SOLAR_MONTH);
            boolean z = 0.907641572047936d >= dModulo2 && dModulo2 > 0.0d;
            int iCeil = (int) (Math.ceil((dModulo + ARYA_SOLAR_MONTH) / ARYA_SOLAR_YEAR) - 1.0d);
            int iModulo = (int) (modulo(Math.ceil(dModulo / ARYA_SOLAR_MONTH), 12.0d) + 1.0d);
            int iModulo2 = (int) (modulo(Math.floor((dTransform * 30.0d) / ARYA_LUNAR_MONTH), 30.0d) + 1.0d);
            HinduMonth hinduMonthOfLunisolar = HinduMonth.ofLunisolar(iModulo);
            HinduVariant hinduVariant = this.variant;
            if (z) {
                hinduMonthOfLunisolar = hinduMonthOfLunisolar.withLeap();
            }
            return new HinduCalendar(hinduVariant, iCeil, hinduMonthOfLunisolar, HinduDay.valueOf(iModulo2), j);
        }

        @Override // net.time4j.calendar.hindu.HinduCS
        HinduCalendar create(int i, HinduMonth hinduMonth, HinduDay hinduDay) {
            double value;
            if (isSolar()) {
                value = (((i * ARYA_SOLAR_YEAR) + ((hinduMonth.getRasi() - 1) * ARYA_SOLAR_MONTH)) + hinduDay.getValue()) - 1.25d;
            } else {
                double d = ((i * 12) - 1) * ARYA_SOLAR_MONTH;
                double dFloor = (Math.floor(d / ARYA_LUNAR_MONTH) + 1.0d) * ARYA_LUNAR_MONTH;
                int value2 = hinduMonth.getValue().getValue();
                if (hinduMonth.isLeap() || Math.ceil((dFloor - d) / 0.907641572047936d) > value2) {
                    value2--;
                }
                value = ((dFloor + (value2 * ARYA_LUNAR_MONTH)) + ((hinduDay.getValue() - 1) * 0.9843527269193898d)) - 0.25d;
            }
            return new HinduCalendar(this.variant, i, hinduMonth, hinduDay, EpochDays.UTC.transform((long) Math.ceil(value - 1132959.0d), EpochDays.RATA_DIE));
        }

        @Override // net.time4j.calendar.hindu.HinduCS
        boolean isValid(int i, HinduMonth hinduMonth, HinduDay hinduDay) {
            if (i < 0 || i > 5999 || hinduMonth == null || hinduDay == null) {
                return false;
            }
            if (isSolar() && (hinduMonth.isLeap() || hinduDay.isLeap())) {
                return false;
            }
            if (hinduDay.getValue() > (isSolar() ? 31 : 30)) {
                return false;
            }
            return !isExpunged(i, hinduMonth, hinduDay);
        }

        @Override // net.time4j.engine.CalendarSystem
        public long getMinimumSinceUTC() {
            return EpochDays.UTC.transform(isSolar() ? -1132959L : -1132988L, EpochDays.RATA_DIE);
        }

        @Override // net.time4j.engine.CalendarSystem
        public long getMaximumSinceUTC() {
            return isSolar() ? 338699L : 338671L;
        }

        private boolean isSolar() {
            return this.variant == HinduVariant.VAR_OLD_SOLAR;
        }
    }
}
