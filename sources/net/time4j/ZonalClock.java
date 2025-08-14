package net.time4j;

import net.time4j.base.TimeSource;
import net.time4j.base.UnixTime;
import net.time4j.engine.CalendarFamily;
import net.time4j.engine.CalendarVariant;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.Chronology;
import net.time4j.engine.StartOfDay;
import net.time4j.engine.VariantSource;
import net.time4j.format.Attributes;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;

/* loaded from: classes4.dex */
public final class ZonalClock {
    private static final ZonalClock SYSTEM = new ZonalClock();
    private final TimeSource<?> timeSource;
    private final Timezone timezone;

    static ZonalClock ofSystem() {
        return SYSTEM;
    }

    public TimeSource<?> getSource() {
        return this.timeSource;
    }

    public ZonalClock(TimeSource<?> timeSource, TZID tzid) {
        this(timeSource, Timezone.of(tzid));
    }

    public ZonalClock(TimeSource<?> timeSource, String str) {
        this(timeSource, Timezone.of(str));
    }

    public ZonalClock(TimeSource<?> timeSource, Timezone timezone) {
        if (timeSource == null) {
            throw new NullPointerException("Missing time source.");
        }
        if (timezone == null) {
            throw new NullPointerException("Missing timezone.");
        }
        this.timeSource = timeSource;
        this.timezone = timezone;
    }

    private ZonalClock() {
        this.timeSource = SystemClock.INSTANCE;
        this.timezone = null;
    }

    public PlainDate today() {
        UnixTime unixTimeCurrentTime = this.timeSource.currentTime();
        Timezone timezoneOfSystem = this.timezone;
        if (timezoneOfSystem == null) {
            timezoneOfSystem = Timezone.ofSystem();
        }
        return PlainDate.from(unixTimeCurrentTime, timezoneOfSystem.getOffset(unixTimeCurrentTime));
    }

    public PlainTimestamp now() {
        UnixTime unixTimeCurrentTime = this.timeSource.currentTime();
        Timezone timezoneOfSystem = this.timezone;
        if (timezoneOfSystem == null) {
            timezoneOfSystem = Timezone.ofSystem();
        }
        return PlainTimestamp.from(unixTimeCurrentTime, timezoneOfSystem.getOffset(unixTimeCurrentTime));
    }

    public <T extends ChronoEntity<T>> T now(Chronology<T> chronology) {
        Timezone timezoneOfSystem = this.timezone;
        if (timezoneOfSystem == null) {
            timezoneOfSystem = Timezone.ofSystem();
        }
        T tCreateFrom = chronology.createFrom(this.timeSource, new Attributes.Builder().setTimezone(timezoneOfSystem.getID()).build());
        if (tCreateFrom != null) {
            return tCreateFrom;
        }
        Class<T> chronoType = chronology.getChronoType();
        if (CalendarVariant.class.isAssignableFrom(chronoType)) {
            throw new IllegalArgumentException("Calendar variant required: " + chronoType.getName());
        }
        throw new IllegalArgumentException("Insufficient data: " + chronoType.getName());
    }

    public <C extends CalendarVariant<C>> GeneralTimestamp<C> now(CalendarFamily<C> calendarFamily, String str, StartOfDay startOfDay) {
        Timezone timezoneOfSystem = this.timezone;
        if (timezoneOfSystem == null) {
            timezoneOfSystem = Timezone.ofSystem();
        }
        return Moment.from(this.timeSource.currentTime()).toGeneralTimestamp(calendarFamily, str, timezoneOfSystem.getID(), startOfDay);
    }

    public <C extends CalendarVariant<C>> GeneralTimestamp<C> now(CalendarFamily<C> calendarFamily, VariantSource variantSource, StartOfDay startOfDay) {
        return now(calendarFamily, variantSource.getVariant(), startOfDay);
    }

    public TZID getTimezone() {
        Timezone timezoneOfSystem = this.timezone;
        if (timezoneOfSystem == null) {
            timezoneOfSystem = Timezone.ofSystem();
        }
        return timezoneOfSystem.getID();
    }
}
