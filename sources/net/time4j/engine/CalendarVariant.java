package net.time4j.engine;

import java.io.Serializable;
import net.time4j.base.MathUtils;
import net.time4j.engine.CalendarVariant;

/* loaded from: classes4.dex */
public abstract class CalendarVariant<D extends CalendarVariant<D>> extends ChronoEntity<D> implements CalendarDate, VariantSource, Comparable<D>, Serializable {
    public abstract boolean equals(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.time4j.engine.ChronoEntity
    public abstract CalendarFamily<D> getChronology();

    public abstract int hashCode();

    public abstract String toString();

    public D withVariant(String str) {
        if (str.equals(getVariant())) {
            return getContext();
        }
        return (D) transform(getChronology().getChronoType(), str);
    }

    public D withVariant(VariantSource variantSource) {
        return (D) withVariant(variantSource.getVariant());
    }

    public <T extends Calendrical<?, T>> T transform(Class<T> cls) throws ClassNotFoundException {
        String name = cls.getName();
        Chronology chronologyLookup = Chronology.lookup(cls);
        if (chronologyLookup == null) {
            throw new IllegalArgumentException("Cannot find any chronology for given target type: " + name);
        }
        return (T) transform(chronologyLookup.getCalendarSystem(), name);
    }

    public <T extends CalendarVariant<T>> T transform(Class<T> cls, String str) throws ClassNotFoundException {
        String name = cls.getName();
        Chronology chronologyLookup = Chronology.lookup(cls);
        if (chronologyLookup == null) {
            throw new IllegalArgumentException("Cannot find any chronology for given target type: " + name);
        }
        return (T) transform(chronologyLookup.getCalendarSystem(str), name);
    }

    public <T extends CalendarVariant<T>> T transform(Class<T> cls, VariantSource variantSource) {
        return (T) transform(cls, variantSource.getVariant());
    }

    @Override // java.lang.Comparable
    public int compareTo(D d) {
        long daysSinceEpochUTC = getDaysSinceEpochUTC();
        long daysSinceEpochUTC2 = d.getDaysSinceEpochUTC();
        if (daysSinceEpochUTC < daysSinceEpochUTC2) {
            return -1;
        }
        if (daysSinceEpochUTC > daysSinceEpochUTC2) {
            return 1;
        }
        return getVariant().compareTo(d.getVariant());
    }

    @Override // net.time4j.engine.Temporal
    public boolean isAfter(CalendarDate calendarDate) {
        return getDaysSinceEpochUTC() > calendarDate.getDaysSinceEpochUTC();
    }

    @Override // net.time4j.engine.Temporal
    public boolean isBefore(CalendarDate calendarDate) {
        return getDaysSinceEpochUTC() < calendarDate.getDaysSinceEpochUTC();
    }

    @Override // net.time4j.engine.Temporal
    public boolean isSimultaneous(CalendarDate calendarDate) {
        return getDaysSinceEpochUTC() == calendarDate.getDaysSinceEpochUTC();
    }

    public D plus(CalendarDays calendarDays) {
        long jSafeAdd = MathUtils.safeAdd(getDaysSinceEpochUTC(), calendarDays.getAmount());
        try {
            return getCalendarSystem().transform(jSafeAdd);
        } catch (IllegalArgumentException e) {
            ArithmeticException arithmeticException = new ArithmeticException("Out of range: " + jSafeAdd);
            arithmeticException.initCause(e);
            throw arithmeticException;
        }
    }

    public D minus(CalendarDays calendarDays) {
        return (D) plus(CalendarDays.of(MathUtils.safeNegate(calendarDays.getAmount())));
    }

    public long getDaysSinceEpochUTC() {
        return getCalendarSystem().transform((CalendarSystem<D>) getContext());
    }

    protected CalendarSystem<D> getCalendarSystem() {
        return (CalendarSystem<D>) getChronology().getCalendarSystem(getVariant());
    }

    @Override // net.time4j.engine.ChronoEntity
    <V> ElementRule<D, V> getRule(ChronoElement<V> chronoElement) {
        if (chronoElement instanceof EpochDays) {
            return ((EpochDays) EpochDays.class.cast(chronoElement)).derive(getCalendarSystem());
        }
        return super.getRule(chronoElement);
    }

    private <T> T transform(CalendarSystem<T> calendarSystem, String str) {
        long daysSinceEpochUTC = getDaysSinceEpochUTC();
        if (calendarSystem.getMinimumSinceUTC() > daysSinceEpochUTC || calendarSystem.getMaximumSinceUTC() < daysSinceEpochUTC) {
            throw new ArithmeticException("Cannot transform <" + daysSinceEpochUTC + "> to: " + str);
        }
        return calendarSystem.transform(daysSinceEpochUTC);
    }
}
