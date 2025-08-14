package net.time4j;

import androidx.media3.common.C;
import java.util.ArrayList;
import java.util.Comparator;
import net.time4j.IsoUnit;
import net.time4j.base.MathUtils;
import net.time4j.engine.ChronoUnit;
import net.time4j.engine.Normalizer;
import net.time4j.engine.TimeSpan;

/* loaded from: classes4.dex */
class StdNormalizer<U extends IsoUnit> implements Normalizer<U>, Comparator<TimeSpan.Item<? extends ChronoUnit>> {
    private static final int MIO = 1000000;
    private static final int MRD = 1000000000;
    private final boolean mixed;

    private StdNormalizer(boolean z) {
        this.mixed = z;
    }

    static StdNormalizer<IsoUnit> ofMixedUnits() {
        return new StdNormalizer<>(true);
    }

    static StdNormalizer<CalendarUnit> ofCalendarUnits() {
        return new StdNormalizer<>(false);
    }

    static StdNormalizer<ClockUnit> ofClockUnits() {
        return new StdNormalizer<>(false);
    }

    static Comparator<TimeSpan.Item<? extends ChronoUnit>> comparator() {
        return new StdNormalizer(false);
    }

    @Override // java.util.Comparator
    public int compare(TimeSpan.Item<? extends ChronoUnit> item, TimeSpan.Item<? extends ChronoUnit> item2) {
        return compare(item.getUnit(), item2.getUnit());
    }

    @Override // net.time4j.engine.Normalizer
    /* renamed from: normalize */
    public Duration<U> normalize2(TimeSpan<? extends U> timeSpan) {
        long jSafeAdd;
        long jSafeAdd2;
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        long jSafeAdd3;
        int size = timeSpan.getTotalLength().size();
        ArrayList arrayList = new ArrayList(size);
        int i = 0;
        long jSafeAdd4 = 0;
        long j6 = 0;
        long j7 = 0;
        long amount = 0;
        long jSafeAdd5 = 0;
        long j8 = 0;
        long j9 = 0;
        long j10 = 0;
        while (i < size) {
            TimeSpan.Item<? extends U> item = timeSpan.getTotalLength().get(i);
            long j11 = amount;
            amount = item.getAmount();
            U unit = item.getUnit();
            int i2 = size;
            long j12 = j6;
            if (unit instanceof CalendarUnit) {
                switch (AnonymousClass1.$SwitchMap$net$time4j$CalendarUnit[((CalendarUnit) CalendarUnit.class.cast(unit)).ordinal()]) {
                    case 1:
                        jSafeAdd4 = MathUtils.safeAdd(MathUtils.safeMultiply(amount, 1000L), jSafeAdd4);
                        break;
                    case 2:
                        jSafeAdd4 = MathUtils.safeAdd(MathUtils.safeMultiply(amount, 100L), jSafeAdd4);
                        break;
                    case 3:
                        jSafeAdd4 = MathUtils.safeAdd(MathUtils.safeMultiply(amount, 10L), jSafeAdd4);
                        break;
                    case 4:
                        jSafeAdd4 = MathUtils.safeAdd(amount, jSafeAdd4);
                        break;
                    case 5:
                        jSafeAdd3 = MathUtils.safeAdd(MathUtils.safeMultiply(amount, 3L), j8);
                        j8 = jSafeAdd3;
                        break;
                    case 6:
                        jSafeAdd3 = MathUtils.safeAdd(amount, j8);
                        j8 = jSafeAdd3;
                        break;
                    case 7:
                        j10 = amount;
                        break;
                    case 8:
                        j9 = amount;
                        break;
                    default:
                        throw new UnsupportedOperationException(unit.toString());
                }
            } else if (unit instanceof ClockUnit) {
                switch (AnonymousClass1.$SwitchMap$net$time4j$ClockUnit[((ClockUnit) ClockUnit.class.cast(unit)).ordinal()]) {
                    case 1:
                        j6 = amount;
                        amount = j11;
                        continue;
                        i++;
                        size = i2;
                    case 2:
                        j7 = amount;
                        break;
                    case 3:
                        j6 = j12;
                        continue;
                        i++;
                        size = i2;
                    case 4:
                        jSafeAdd5 = MathUtils.safeAdd(MathUtils.safeMultiply(amount, 1000000L), jSafeAdd5);
                        break;
                    case 5:
                        jSafeAdd5 = MathUtils.safeAdd(MathUtils.safeMultiply(amount, 1000L), jSafeAdd5);
                        break;
                    case 6:
                        jSafeAdd5 = MathUtils.safeAdd(amount, jSafeAdd5);
                        break;
                    default:
                        throw new UnsupportedOperationException(unit.toString());
                }
            } else {
                arrayList.add(TimeSpan.Item.of(amount, unit));
            }
            amount = j11;
            j6 = j12;
            continue;
            i++;
            size = i2;
        }
        long j13 = j6;
        long j14 = amount;
        if ((j13 | j7 | j14 | jSafeAdd5) != 0) {
            long j15 = jSafeAdd5 % C.NANOS_PER_SECOND;
            long jSafeAdd6 = MathUtils.safeAdd(j14, jSafeAdd5 / C.NANOS_PER_SECOND);
            j2 = jSafeAdd6 % 60;
            long jSafeAdd7 = MathUtils.safeAdd(j7, jSafeAdd6 / 60);
            j = jSafeAdd7 % 60;
            jSafeAdd2 = MathUtils.safeAdd(j13, jSafeAdd7 / 60);
            if (this.mixed) {
                jSafeAdd = MathUtils.safeAdd(j9, jSafeAdd2 / 24);
                j3 = j15;
                jSafeAdd2 %= 24;
            } else {
                jSafeAdd = j9;
                j3 = j15;
            }
        } else {
            jSafeAdd = j9;
            jSafeAdd2 = 0;
            j = 0;
            j2 = 0;
            j3 = 0;
        }
        if ((jSafeAdd4 | j8 | jSafeAdd) != 0) {
            j5 = j2;
            long jSafeAdd8 = MathUtils.safeAdd(jSafeAdd4, j8 / 12);
            long j16 = j8 % 12;
            j4 = j;
            long jSafeAdd9 = MathUtils.safeAdd(MathUtils.safeMultiply(j10, 7L), jSafeAdd);
            if (jSafeAdd8 != 0) {
                arrayList.add(TimeSpan.Item.of(jSafeAdd8, CalendarUnit.YEARS));
            }
            if (j16 != 0) {
                arrayList.add(TimeSpan.Item.of(j16, CalendarUnit.MONTHS));
            }
            if (jSafeAdd9 != 0) {
                arrayList.add(TimeSpan.Item.of(jSafeAdd9, CalendarUnit.DAYS));
            }
        } else {
            j4 = j;
            j5 = j2;
            long j17 = j10;
            if (j17 != 0) {
                arrayList.add(TimeSpan.Item.of(j17, CalendarUnit.WEEKS));
            }
        }
        if (jSafeAdd2 != 0) {
            arrayList.add(TimeSpan.Item.of(jSafeAdd2, ClockUnit.HOURS));
        }
        if (j4 != 0) {
            arrayList.add(TimeSpan.Item.of(j4, ClockUnit.MINUTES));
        }
        if (j5 != 0) {
            arrayList.add(TimeSpan.Item.of(j5, ClockUnit.SECONDS));
        }
        long j18 = j3;
        if (j18 != 0) {
            arrayList.add(TimeSpan.Item.of(j18, ClockUnit.NANOS));
        }
        return new Duration<>(arrayList, timeSpan.isNegative());
    }

    /* renamed from: net.time4j.StdNormalizer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
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

    static int compare(ChronoUnit chronoUnit, ChronoUnit chronoUnit2) {
        int iCompare = Double.compare(chronoUnit2.getLength(), chronoUnit.getLength());
        if (iCompare != 0 || chronoUnit.equals(chronoUnit2)) {
            return iCompare;
        }
        throw new IllegalArgumentException("Mixing different units of same length not allowed.");
    }
}
