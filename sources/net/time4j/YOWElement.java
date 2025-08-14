package net.time4j;

import java.io.ObjectStreamException;
import net.time4j.base.GregorianMath;
import net.time4j.base.MathUtils;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoOperator;
import net.time4j.engine.ElementRule;
import net.time4j.engine.EpochDays;
import net.time4j.engine.UnitRule;

/* loaded from: classes4.dex */
final class YOWElement extends AbstractDateElement<Integer> {
    private static final long serialVersionUID = -6907291758376370420L;
    private final transient ElementOperator<PlainDate> nextAdjuster;
    private final transient ElementOperator<PlainDate> previousAdjuster;
    private static final UnitRule U_RULE = new URule();
    static final YOWElement INSTANCE = new YOWElement("YEAR_OF_WEEKDATE");

    private Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }

    static <T extends ChronoEntity<T>> UnitRule<T> unitRule() {
        return U_RULE;
    }

    @Override // net.time4j.AbstractDateElement, net.time4j.AdjustableElement
    public ElementOperator<PlainDate> decremented() {
        return this.previousAdjuster;
    }

    @Override // net.time4j.engine.BasicElement, net.time4j.engine.ChronoElement
    public char getSymbol() {
        return 'Y';
    }

    @Override // net.time4j.AbstractDateElement, net.time4j.AdjustableElement
    public ElementOperator<PlainDate> incremented() {
        return this.nextAdjuster;
    }

    @Override // net.time4j.engine.ChronoElement
    public boolean isDateElement() {
        return true;
    }

    @Override // net.time4j.engine.BasicElement
    protected boolean isSingleton() {
        return true;
    }

    @Override // net.time4j.engine.ChronoElement
    public boolean isTimeElement() {
        return false;
    }

    private YOWElement(String str) {
        super(str);
        this.previousAdjuster = new YOWRollingAdjuster(-1L);
        this.nextAdjuster = new YOWRollingAdjuster(1L);
    }

    @Override // net.time4j.engine.ChronoElement
    public Class<Integer> getType() {
        return Integer.class;
    }

    @Override // net.time4j.engine.ChronoElement
    public Integer getDefaultMinimum() {
        return PlainDate.MIN_YEAR;
    }

    @Override // net.time4j.engine.ChronoElement
    public Integer getDefaultMaximum() {
        return PlainDate.MAX_YEAR;
    }

    static <T extends ChronoEntity<T>> ElementRule<T, Integer> elementRule(Class<T> cls) {
        return new ERule();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getFirstCalendarWeekAsDayOfYear(PlainDate plainDate, int i) {
        return getFirstCalendarWeekAsDayOfYear(plainDate.getYear() + i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getFirstCalendarWeekAsDayOfYear(int i) {
        int value = Weekday.valueOf(GregorianMath.getDayOfWeek(i, 1, 1)).getValue(Weekmodel.ISO);
        return value <= 8 - Weekmodel.ISO.getMinimalDaysInFirstWeek() ? 2 - value : 9 - value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getLengthOfYear(PlainDate plainDate, int i) {
        return GregorianMath.isLeapYear(plainDate.getYear() + i) ? 366 : 365;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getWeekOfYear(PlainDate plainDate) {
        int dayOfYear = plainDate.getDayOfYear();
        int firstCalendarWeekAsDayOfYear = getFirstCalendarWeekAsDayOfYear(plainDate, 0);
        if (firstCalendarWeekAsDayOfYear <= dayOfYear) {
            int i = ((dayOfYear - firstCalendarWeekAsDayOfYear) / 7) + 1;
            if (i < 53 || getFirstCalendarWeekAsDayOfYear(plainDate, 1) + getLengthOfYear(plainDate, 0) > dayOfYear) {
                return i;
            }
            return 1;
        }
        return (((dayOfYear + getLengthOfYear(plainDate, -1)) - getFirstCalendarWeekAsDayOfYear(plainDate, -1)) / 7) + 1;
    }

    private static class YOWRollingAdjuster extends ElementOperator<PlainDate> {
        private final long amount;
        private final ChronoOperator<PlainTimestamp> yowTS;

        @Override // net.time4j.ElementOperator
        ChronoOperator<PlainTimestamp> onTimestamp() {
            return this.yowTS;
        }

        private YOWRollingAdjuster(long j) {
            super(YOWElement.INSTANCE, 8);
            this.amount = j;
            this.yowTS = new ChronoOperator<PlainTimestamp>() { // from class: net.time4j.YOWElement.YOWRollingAdjuster.1
                @Override // net.time4j.engine.ChronoOperator
                public PlainTimestamp apply(PlainTimestamp plainTimestamp) {
                    return (PlainTimestamp) YOWElement.unitRule().addTo(plainTimestamp, YOWRollingAdjuster.this.amount);
                }
            };
        }

        @Override // net.time4j.engine.ChronoOperator
        public PlainDate apply(PlainDate plainDate) {
            return (PlainDate) YOWElement.unitRule().addTo(plainDate, this.amount);
        }
    }

    private static class URule<T extends ChronoEntity<T>> implements UnitRule<T> {
        private URule() {
        }

        @Override // net.time4j.engine.UnitRule
        public T addTo(T t, long j) {
            if (j == 0) {
                return t;
            }
            int iSafeCast = MathUtils.safeCast(MathUtils.safeAdd(((Integer) t.get(YOWElement.INSTANCE)).intValue(), j));
            PlainDate plainDate = (PlainDate) t.get(PlainDate.CALENDAR_DATE);
            int weekOfYear = plainDate.getWeekOfYear();
            Weekday dayOfWeek = plainDate.getDayOfWeek();
            if (weekOfYear == 53) {
                weekOfYear = ((Integer) PlainDate.of(iSafeCast, 26, dayOfWeek).getMaximum(Weekmodel.ISO.weekOfYear())).intValue();
            }
            return (T) t.with(PlainDate.CALENDAR_DATE, PlainDate.of(iSafeCast, weekOfYear, dayOfWeek));
        }

        @Override // net.time4j.engine.UnitRule
        public long between(T t, T t2) {
            PlainDate plainDate = (PlainDate) t.get(PlainDate.CALENDAR_DATE);
            PlainDate plainDate2 = (PlainDate) t2.get(PlainDate.CALENDAR_DATE);
            long jIntValue = ((Integer) plainDate2.get(YOWElement.INSTANCE)).intValue() - ((Integer) plainDate.get(YOWElement.INSTANCE)).intValue();
            if (jIntValue == 0) {
                return jIntValue;
            }
            int weekOfYear = YOWElement.getWeekOfYear(plainDate);
            int weekOfYear2 = YOWElement.getWeekOfYear(plainDate2);
            if (jIntValue > 0 && weekOfYear > weekOfYear2) {
                jIntValue--;
            } else if (jIntValue < 0 && weekOfYear < weekOfYear2) {
                jIntValue++;
            }
            if (jIntValue == 0 || weekOfYear != weekOfYear2) {
                return jIntValue;
            }
            int value = plainDate.getDayOfWeek().getValue();
            int value2 = plainDate2.getDayOfWeek().getValue();
            if (jIntValue > 0 && value > value2) {
                jIntValue--;
            } else if (jIntValue < 0 && value < value2) {
                jIntValue++;
            }
            if (jIntValue == 0 || value != value2 || !t.contains(PlainTime.WALL_TIME) || !t2.contains(PlainTime.WALL_TIME)) {
                return jIntValue;
            }
            PlainTime plainTime = (PlainTime) t.get(PlainTime.WALL_TIME);
            PlainTime plainTime2 = (PlainTime) t2.get(PlainTime.WALL_TIME);
            return (jIntValue <= 0 || !plainTime.isAfter(plainTime2)) ? (jIntValue >= 0 || !plainTime.isBefore(plainTime2)) ? jIntValue : jIntValue + 1 : jIntValue - 1;
        }
    }

    private static class ERule<T extends ChronoEntity<T>> implements ElementRule<T, Integer> {
        private ERule() {
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getValue(T t) {
            PlainDate plainDate = (PlainDate) t.get(PlainDate.CALENDAR_DATE);
            int year = plainDate.getYear();
            int dayOfYear = plainDate.getDayOfYear();
            int firstCalendarWeekAsDayOfYear = YOWElement.getFirstCalendarWeekAsDayOfYear(plainDate, 0);
            if (firstCalendarWeekAsDayOfYear > dayOfYear) {
                year--;
            } else if (((dayOfYear - firstCalendarWeekAsDayOfYear) / 7) + 1 >= 53 && YOWElement.getFirstCalendarWeekAsDayOfYear(plainDate, 1) + YOWElement.getLengthOfYear(plainDate, 0) <= dayOfYear) {
                year++;
            }
            return Integer.valueOf(year);
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getMinimum(T t) {
            return YOWElement.INSTANCE.getDefaultMinimum();
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getMaximum(T t) {
            return YOWElement.INSTANCE.getDefaultMaximum();
        }

        @Override // net.time4j.engine.ElementRule
        /* renamed from: isValid, reason: merged with bridge method [inline-methods] */
        public boolean isValid2(T t, Integer num) {
            int iIntValue;
            return num != null && (iIntValue = num.intValue()) >= -999999999 && iIntValue <= 999999999;
        }

        @Override // net.time4j.engine.ElementRule
        /* renamed from: withValue, reason: merged with bridge method [inline-methods] */
        public T withValue2(T t, Integer num, boolean z) {
            if (num == null) {
                throw new IllegalArgumentException("Missing element value.");
            }
            return (T) t.with(PlainDate.CALENDAR_DATE, setYearOfWeekdate((PlainDate) t.get(PlainDate.CALENDAR_DATE), num.intValue()));
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(T t) {
            return getChild();
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(T t) {
            return getChild();
        }

        private ChronoElement<?> getChild() {
            return Weekmodel.ISO.weekOfYear();
        }

        private static PlainDate setYearOfWeekdate(PlainDate plainDate, int i) {
            int firstCalendarWeekAsDayOfYear = YOWElement.getFirstCalendarWeekAsDayOfYear(i);
            int weekOfYear = YOWElement.getWeekOfYear(plainDate);
            long jTransform = EpochDays.UNIX.transform(GregorianMath.toMJD(i, 1, 1), EpochDays.MODIFIED_JULIAN_DATE) + (firstCalendarWeekAsDayOfYear - 1) + ((weekOfYear - 1) * 7) + (plainDate.getDayOfWeek().getValue(Weekmodel.ISO) - 1);
            if (weekOfYear == 53) {
                if (((YOWElement.getFirstCalendarWeekAsDayOfYear(i + 1) + (GregorianMath.isLeapYear(i) ? 366 : 365)) - firstCalendarWeekAsDayOfYear) / 7 < 53) {
                    jTransform -= 7;
                }
            }
            return plainDate.withDaysSinceUTC(jTransform - 730);
        }
    }
}
