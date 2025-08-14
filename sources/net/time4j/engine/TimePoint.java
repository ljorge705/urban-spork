package net.time4j.engine;

import java.io.Serializable;
import net.time4j.base.MathUtils;
import net.time4j.engine.TimePoint;

/* loaded from: classes4.dex */
public abstract class TimePoint<U, T extends TimePoint<U, T>> extends ChronoEntity<T> implements Comparable<T>, Serializable {
    @Override // java.lang.Comparable
    public abstract int compareTo(T t);

    public abstract boolean equals(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.time4j.engine.ChronoEntity
    public abstract TimeAxis<U, T> getChronology();

    public abstract int hashCode();

    public abstract String toString();

    public T plus(TimeSpan<? extends U> timeSpan) {
        try {
            return (T) timeSpan.addTo(getContext());
        } catch (IllegalArgumentException e) {
            ArithmeticException arithmeticException = new ArithmeticException("Result beyond boundaries of time axis.");
            arithmeticException.initCause(e);
            throw arithmeticException;
        }
    }

    public T minus(TimeSpan<? extends U> timeSpan) {
        try {
            return (T) timeSpan.subtractFrom(getContext());
        } catch (IllegalArgumentException e) {
            ArithmeticException arithmeticException = new ArithmeticException("Result beyond boundaries of time axis.");
            arithmeticException.initCause(e);
            throw arithmeticException;
        }
    }

    public T plus(long j, U u) {
        if (j == 0) {
            return getContext();
        }
        try {
            return getRule((TimePoint<U, T>) u).addTo(getContext(), j);
        } catch (IllegalArgumentException e) {
            ArithmeticException arithmeticException = new ArithmeticException("Result beyond boundaries of time axis.");
            arithmeticException.initCause(e);
            throw arithmeticException;
        }
    }

    public T minus(long j, U u) {
        return (T) plus(MathUtils.safeNegate(j), u);
    }

    public <P> P until(T t, TimeMetric<? extends U, P> timeMetric) {
        return timeMetric.between(getContext(), t);
    }

    public long until(T t, U u) {
        return getRule((TimePoint<U, T>) u).between(getContext(), t);
    }

    public static <U, T extends TimePoint<U, T>> T min(T t, T t2) {
        return t.compareTo(t2) > 0 ? t2 : t;
    }

    public static <U, T extends TimePoint<U, T>> T max(T t, T t2) {
        return t.compareTo(t2) > 0 ? t : t2;
    }

    private UnitRule<T> getRule(U u) {
        return getChronology().getRule((TimeAxis<U, T>) u);
    }
}
