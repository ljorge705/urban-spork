package net.time4j;

import net.time4j.base.MathUtils;
import net.time4j.engine.CalendarDays;
import net.time4j.engine.CalendarVariant;
import net.time4j.engine.Calendrical;
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoException;
import net.time4j.engine.StartOfDay;
import net.time4j.engine.VariantSource;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;
import net.time4j.tz.ZonalOffset;

/* loaded from: classes4.dex */
public final class GeneralTimestamp<C> implements ChronoDisplay, VariantSource {

    /* renamed from: ca, reason: collision with root package name */
    private final Calendrical<?, ?> f314ca;
    private final CalendarVariant<?> cv;
    private final PlainTime time;

    @Override // net.time4j.engine.ChronoDisplay
    public boolean hasTimezone() {
        return false;
    }

    public C toDate() {
        C c = (C) this.cv;
        return c == null ? (C) this.f314ca : c;
    }

    public PlainTime toTime() {
        return this.time;
    }

    private GeneralTimestamp(CalendarVariant<?> calendarVariant, Calendrical<?, ?> calendrical, PlainTime plainTime) {
        if (plainTime.getHour() != 24) {
            this.cv = calendarVariant;
            this.f314ca = calendrical;
            this.time = plainTime;
        } else {
            if (calendarVariant == null) {
                this.cv = null;
                this.f314ca = calendrical.plus(CalendarDays.of(1L));
            } else {
                this.cv = calendarVariant.plus(CalendarDays.of(1L));
                this.f314ca = null;
            }
            this.time = PlainTime.midnightAtStartOfDay();
        }
    }

    /* JADX WARN: Incorrect types in method signature: <C:Lnet/time4j/engine/CalendarVariant<TC;>;>(TC;Lnet/time4j/PlainTime;)Lnet/time4j/GeneralTimestamp<TC;>; */
    public static GeneralTimestamp of(CalendarVariant calendarVariant, PlainTime plainTime) {
        if (calendarVariant == null) {
            throw new NullPointerException("Missing date component.");
        }
        return new GeneralTimestamp(calendarVariant, null, plainTime);
    }

    /* JADX WARN: Incorrect types in method signature: <C:Lnet/time4j/engine/Calendrical<*TC;>;>(TC;Lnet/time4j/PlainTime;)Lnet/time4j/GeneralTimestamp<TC;>; */
    public static GeneralTimestamp of(Calendrical calendrical, PlainTime plainTime) {
        if (calendrical == null) {
            throw new NullPointerException("Missing date component.");
        }
        return new GeneralTimestamp(null, calendrical, plainTime);
    }

    public GeneralTimestamp<C> minus(CalendarDays calendarDays) {
        return plus(calendarDays.inverse());
    }

    public GeneralTimestamp<C> minus(long j, ClockUnit clockUnit) {
        return plus(MathUtils.safeNegate(j), clockUnit);
    }

    public GeneralTimestamp<C> plus(CalendarDays calendarDays) {
        CalendarVariant<?> calendarVariant = this.cv;
        CalendarVariant calendarVariantPlus = calendarVariant == null ? null : calendarVariant.plus(calendarDays);
        Calendrical<?, ?> calendrical = this.f314ca;
        return new GeneralTimestamp<>(calendarVariantPlus, calendrical != null ? calendrical.plus(calendarDays) : null, this.time);
    }

    public GeneralTimestamp<C> plus(long j, ClockUnit clockUnit) {
        DayCycles dayCyclesRoll = this.time.roll(j, clockUnit);
        CalendarDays calendarDaysOf = CalendarDays.of(dayCyclesRoll.getDayOverflow());
        CalendarVariant<?> calendarVariant = this.cv;
        CalendarVariant calendarVariantPlus = calendarVariant == null ? null : calendarVariant.plus(calendarDaysOf);
        Calendrical<?, ?> calendrical = this.f314ca;
        return new GeneralTimestamp<>(calendarVariantPlus, calendrical != null ? calendrical.plus(calendarDaysOf) : null, dayCyclesRoll.getWallTime());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GeneralTimestamp)) {
            return false;
        }
        GeneralTimestamp generalTimestamp = (GeneralTimestamp) GeneralTimestamp.class.cast(obj);
        if (!this.time.equals(generalTimestamp.time)) {
            return false;
        }
        CalendarVariant<?> calendarVariant = this.cv;
        return calendarVariant == null ? generalTimestamp.cv == null && this.f314ca.equals(generalTimestamp.f314ca) : generalTimestamp.f314ca == null && calendarVariant.equals(generalTimestamp.cv);
    }

    public int hashCode() {
        int iHashCode;
        CalendarVariant<?> calendarVariant = this.cv;
        if (calendarVariant == null) {
            iHashCode = this.f314ca.hashCode();
        } else {
            iHashCode = calendarVariant.hashCode();
        }
        return iHashCode + this.time.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        CalendarVariant<?> calendarVariant = this.cv;
        if (calendarVariant == null) {
            sb.append(this.f314ca);
        } else {
            sb.append(calendarVariant);
        }
        sb.append(this.time);
        return sb.toString();
    }

    public Moment at(ZonalOffset zonalOffset, StartOfDay startOfDay) {
        PlainTimestamp plainTimestampAt;
        CalendarVariant<?> calendarVariant = this.cv;
        if (calendarVariant == null) {
            plainTimestampAt = ((PlainDate) this.f314ca.transform(PlainDate.class)).at(this.time);
        } else {
            plainTimestampAt = ((PlainDate) calendarVariant.transform(PlainDate.class)).at(this.time);
        }
        int iIntValue = ((Integer) this.time.get(PlainTime.SECOND_OF_DAY)).intValue() - startOfDay.getDeviation(plainTimestampAt.getCalendarDate(), zonalOffset);
        if (iIntValue >= 86400) {
            plainTimestampAt = plainTimestampAt.minus(1L, CalendarUnit.DAYS);
        } else if (iIntValue < 0) {
            plainTimestampAt = plainTimestampAt.plus(1L, CalendarUnit.DAYS);
        }
        return plainTimestampAt.at(zonalOffset);
    }

    public Moment in(Timezone timezone, StartOfDay startOfDay) {
        PlainTimestamp plainTimestampAt;
        CalendarVariant<?> calendarVariant = this.cv;
        if (calendarVariant == null) {
            plainTimestampAt = ((PlainDate) this.f314ca.transform(PlainDate.class)).at(this.time);
        } else {
            plainTimestampAt = ((PlainDate) calendarVariant.transform(PlainDate.class)).at(this.time);
        }
        int iIntValue = ((Integer) this.time.get(PlainTime.SECOND_OF_DAY)).intValue() - startOfDay.getDeviation(plainTimestampAt.getCalendarDate(), timezone.getID());
        if (iIntValue >= 86400) {
            plainTimestampAt = plainTimestampAt.minus(1L, CalendarUnit.DAYS);
        } else if (iIntValue < 0) {
            plainTimestampAt = plainTimestampAt.plus(1L, CalendarUnit.DAYS);
        }
        return plainTimestampAt.in(timezone);
    }

    @Override // net.time4j.engine.ChronoDisplay
    public boolean contains(ChronoElement<?> chronoElement) {
        return chronoElement.isDateElement() ? toDate0().contains(chronoElement) : this.time.contains(chronoElement);
    }

    @Override // net.time4j.engine.ChronoDisplay
    public <V> V get(ChronoElement<V> chronoElement) {
        return chronoElement.isDateElement() ? (V) toDate0().get(chronoElement) : (V) this.time.get(chronoElement);
    }

    @Override // net.time4j.engine.ChronoDisplay
    public int getInt(ChronoElement<Integer> chronoElement) {
        return chronoElement.isDateElement() ? toDate0().getInt(chronoElement) : this.time.getInt(chronoElement);
    }

    @Override // net.time4j.engine.ChronoDisplay
    public <V> V getMinimum(ChronoElement<V> chronoElement) {
        return chronoElement.isDateElement() ? (V) toDate0().getMinimum(chronoElement) : (V) this.time.getMinimum(chronoElement);
    }

    @Override // net.time4j.engine.ChronoDisplay
    public <V> V getMaximum(ChronoElement<V> chronoElement) {
        return chronoElement.isDateElement() ? (V) toDate0().getMaximum(chronoElement) : (V) this.time.getMaximum(chronoElement);
    }

    @Override // net.time4j.engine.ChronoDisplay
    public TZID getTimezone() {
        throw new ChronoException("Timezone not available: " + this);
    }

    private ChronoDisplay toDate0() {
        ChronoDisplay chronoDisplay = this.cv;
        if (chronoDisplay == null) {
            chronoDisplay = this.f314ca;
        }
        return chronoDisplay;
    }

    @Override // net.time4j.engine.VariantSource
    public String getVariant() {
        CalendarVariant<?> calendarVariant = this.cv;
        return calendarVariant == null ? "" : calendarVariant.getVariant();
    }
}
