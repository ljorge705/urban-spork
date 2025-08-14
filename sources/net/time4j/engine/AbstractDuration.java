package net.time4j.engine;

import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.base.MathUtils;
import net.time4j.engine.ChronoUnit;
import net.time4j.engine.TimeSpan;

/* loaded from: classes4.dex */
public abstract class AbstractDuration<U extends ChronoUnit> implements TimeSpan<U> {
    private static final int MIO = 1000000;

    public abstract AbstractDuration<U> inverse();

    @Override // net.time4j.engine.TimeSpan
    public boolean contains(U u) {
        for (TimeSpan.Item<U> item : getTotalLength()) {
            if (item.getUnit().equals(u)) {
                return item.getAmount() > 0;
            }
        }
        return false;
    }

    @Override // net.time4j.engine.TimeSpan
    public long getPartialAmount(U u) {
        for (TimeSpan.Item<U> item : getTotalLength()) {
            if (item.getUnit().equals(u)) {
                return item.getAmount();
            }
        }
        return 0L;
    }

    @Override // net.time4j.engine.TimeSpan
    public boolean isPositive() {
        return (isNegative() || isEmpty()) ? false : true;
    }

    @Override // net.time4j.engine.TimeSpan
    public boolean isEmpty() {
        List<TimeSpan.Item<U>> totalLength = getTotalLength();
        int size = totalLength.size();
        for (int i = 0; i < size; i++) {
            if (totalLength.get(i).getAmount() > 0) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        if (isEmpty()) {
            return "PT0S";
        }
        StringBuilder sb = new StringBuilder();
        if (isNegative()) {
            sb.append('-');
        }
        sb.append('P');
        int size = getTotalLength().size();
        for (int i = 0; i < size; i++) {
            TimeSpan.Item<U> item = getTotalLength().get(i);
            if (i > 0) {
                sb.append(AbstractJsonLexerKt.COMMA);
            }
            sb.append(item.getAmount());
            sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
            sb.append(item.getUnit());
            sb.append(AbstractJsonLexerKt.END_OBJ);
        }
        return sb.toString();
    }

    @Override // net.time4j.engine.TimeSpan
    public final <T extends TimePoint<? super U, T>> T addTo(T t) {
        return (T) add(t, this, false);
    }

    @Override // net.time4j.engine.TimeSpan
    public final <T extends TimePoint<? super U, T>> T subtractFrom(T t) {
        return (T) add(t, this, true);
    }

    private <T extends TimePoint<? super U, T>> T add(T t, TimeSpan<U> timeSpan, boolean z) {
        TimeAxis chronology = t.getChronology();
        List<TimeSpan.Item<U>> totalLength = timeSpan.getTotalLength();
        boolean zIsNegative = timeSpan.isNegative();
        if (z) {
            zIsNegative = !timeSpan.isNegative();
        }
        if (zIsNegative) {
            int size = totalLength.size() - 1;
            T t2 = t;
            while (size >= 0) {
                TimeSpan.Item<U> item = totalLength.get(size);
                U unit = item.getUnit();
                long amount = item.getAmount();
                size--;
                while (size >= 0) {
                    TimeSpan.Item<U> item2 = totalLength.get(size);
                    U unit2 = item2.getUnit();
                    long amount2 = item2.getAmount();
                    long factor = getFactor(chronology, unit2, unit);
                    if (Double.isNaN(factor) || amount2 >= 2147483647L || factor <= 1 || factor >= 1000000 || !chronology.isConvertible(unit2, unit)) {
                        break;
                    }
                    amount = MathUtils.safeAdd(amount, MathUtils.safeMultiply(amount2, factor));
                    size--;
                }
                t2 = (T) t2.plus(MathUtils.safeNegate(amount), unit);
            }
            return t2;
        }
        int size2 = totalLength.size();
        int i = 0;
        TimePoint timePointPlus = t;
        while (i < size2) {
            TimeSpan.Item<U> item3 = totalLength.get(i);
            U unit3 = item3.getUnit();
            long amount3 = item3.getAmount();
            i++;
            while (i < size2) {
                TimeSpan.Item<U> item4 = totalLength.get(i);
                U unit4 = item4.getUnit();
                long factor2 = getFactor(chronology, unit3, unit4);
                if (!Double.isNaN(factor2)) {
                    if (amount3 >= 2147483647L) {
                        break;
                    }
                    if (factor2 <= 1) {
                        break;
                    }
                    if (factor2 >= 1000000 || !chronology.isConvertible(unit3, unit4)) {
                        break;
                    }
                    amount3 = MathUtils.safeAdd(item4.getAmount(), MathUtils.safeMultiply(amount3, factor2));
                    i++;
                    unit3 = unit4;
                } else {
                    break;
                }
            }
            timePointPlus = timePointPlus.plus(amount3, unit3);
        }
        return (T) timePointPlus;
    }

    private static <U> long getFactor(TimeAxis<U, ?> timeAxis, U u, U u2) {
        return Math.round(timeAxis.getLength(u) / timeAxis.getLength(u2));
    }
}
