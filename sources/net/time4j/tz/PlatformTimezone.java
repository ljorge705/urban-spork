package net.time4j.tz;

import com.drew.metadata.exif.makernotes.NikonType2MakernoteDirectory;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.time4j.base.GregorianDate;
import net.time4j.base.GregorianMath;
import net.time4j.base.MathUtils;
import net.time4j.base.UnixTime;
import net.time4j.base.WallTime;
import org.apache.commons.lang3.time.TimeZones;

/* loaded from: classes4.dex */
final class PlatformTimezone extends Timezone {
    private static final long serialVersionUID = -8432968264242113551L;
    private final transient ZonalOffset fixedOffset;
    private final TZID id;
    private final boolean strict;
    private final TimeZone tz;

    @Override // net.time4j.tz.Timezone
    public boolean isFixed() {
        return this.fixedOffset != null;
    }

    PlatformTimezone() {
        this.id = null;
        this.tz = null;
        this.strict = false;
        this.fixedOffset = null;
    }

    PlatformTimezone(TZID tzid) {
        this(tzid, TimeZone.getDefault(), false);
    }

    PlatformTimezone(TZID tzid, String str) {
        this(tzid, findZone(str), false);
    }

    private PlatformTimezone(TZID tzid, TimeZone timeZone, boolean z) {
        this.id = tzid;
        TimeZone timeZone2 = (TimeZone) timeZone.clone();
        this.tz = timeZone2;
        this.strict = z;
        if (timeZone2.useDaylightTime()) {
            this.fixedOffset = null;
            return;
        }
        String id = timeZone2.getID();
        if (id.startsWith(TimeZones.GMT_ID) || id.startsWith("Etc/") || id.equals("Greenwich") || id.equals("UCT") || id.equals("UTC") || id.equals("Universal") || id.equals("Zulu")) {
            this.fixedOffset = fromOffsetMillis(timeZone2.getOffset(System.currentTimeMillis()));
        } else {
            this.fixedOffset = null;
        }
    }

    @Override // net.time4j.tz.Timezone
    public TZID getID() {
        TZID tzid = this.id;
        return tzid == null ? new NamedID(TimeZone.getDefault().getID()) : tzid;
    }

    @Override // net.time4j.tz.Timezone
    public ZonalOffset getOffset(UnixTime unixTime) {
        TimeZone timeZone;
        if (this.id == null) {
            timeZone = TimeZone.getDefault();
        } else {
            ZonalOffset zonalOffset = this.fixedOffset;
            if (zonalOffset != null) {
                return zonalOffset;
            }
            timeZone = this.tz;
        }
        return fromOffsetMillis(timeZone.getOffset(unixTime.getPosixTime() * 1000));
    }

    @Override // net.time4j.tz.Timezone
    public ZonalOffset getStandardOffset(UnixTime unixTime) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(this.id == null ? TimeZone.getDefault() : this.tz);
        gregorianCalendar.setTimeInMillis(unixTime.getPosixTime() * 1000);
        return fromOffsetMillis(gregorianCalendar.get(15));
    }

    @Override // net.time4j.tz.Timezone
    public ZonalOffset getDaylightSavingOffset(UnixTime unixTime) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(this.id == null ? TimeZone.getDefault() : this.tz);
        gregorianCalendar.setTimeInMillis(unixTime.getPosixTime() * 1000);
        return fromOffsetMillis(gregorianCalendar.get(16));
    }

    @Override // net.time4j.tz.Timezone
    public ZonalOffset getOffset(GregorianDate gregorianDate, WallTime wallTime) {
        int dayOfMonth;
        int i;
        int i2;
        ZonalOffset zonalOffset = this.fixedOffset;
        if (zonalOffset != null) {
            return zonalOffset;
        }
        int year = gregorianDate.getYear();
        int month = gregorianDate.getMonth();
        int dayOfMonth2 = gregorianDate.getDayOfMonth();
        if (wallTime.getHour() == 24) {
            long packedDate = GregorianMath.toPackedDate(MathUtils.safeAdd(GregorianMath.toMJD(gregorianDate), 1L));
            int year2 = GregorianMath.readYear(packedDate);
            int month2 = GregorianMath.readMonth(packedDate);
            dayOfMonth = GregorianMath.readDayOfMonth(packedDate);
            month = month2;
            year = year2;
        } else {
            dayOfMonth = dayOfMonth2;
        }
        if (year > 0) {
            i2 = year;
            i = 1;
        } else {
            i = 0;
            i2 = 1 - year;
        }
        int dayOfWeek = GregorianMath.getDayOfWeek(year, month, dayOfMonth) + 1;
        return fromOffsetMillis((this.id == null ? TimeZone.getDefault() : this.tz).getOffset(i, i2, month - 1, dayOfMonth, dayOfWeek == 8 ? 1 : dayOfWeek, wallTime.getHour() != 24 ? (((wallTime.getHour() * NikonType2MakernoteDirectory.TAG_NIKON_SCAN) + (wallTime.getMinute() * 60) + wallTime.getSecond()) * 1000) + (wallTime.getNanosecond() / 1000000) : 0));
    }

    @Override // net.time4j.tz.Timezone
    public boolean isInvalid(GregorianDate gregorianDate, WallTime wallTime) {
        if (this.fixedOffset != null) {
            return false;
        }
        int year = gregorianDate.getYear();
        int month = gregorianDate.getMonth();
        int dayOfMonth = gregorianDate.getDayOfMonth();
        int hour = wallTime.getHour();
        int minute = wallTime.getMinute();
        int second = wallTime.getSecond();
        int nanosecond = wallTime.getNanosecond() / 1000000;
        GregorianCalendar gregorianCalendar = new GregorianCalendar(this.id == null ? TimeZone.getDefault() : this.tz);
        gregorianCalendar.set(14, nanosecond);
        gregorianCalendar.set(year, month - 1, dayOfMonth, hour, minute, second);
        return (gregorianCalendar.get(1) == year && gregorianCalendar.get(2) + 1 == month && gregorianCalendar.get(5) == dayOfMonth && gregorianCalendar.get(11) == hour && gregorianCalendar.get(12) == minute && gregorianCalendar.get(13) == second && gregorianCalendar.get(14) == nanosecond) ? false : true;
    }

    @Override // net.time4j.tz.Timezone
    public boolean isDaylightSaving(UnixTime unixTime) {
        if (this.fixedOffset != null) {
            return false;
        }
        return (this.id == null ? TimeZone.getDefault() : this.tz).inDaylightTime(new Date(unixTime.getPosixTime() * 1000));
    }

    @Override // net.time4j.tz.Timezone
    public TransitionHistory getHistory() {
        ZonalOffset zonalOffset = this.fixedOffset;
        if (zonalOffset == null) {
            return null;
        }
        return zonalOffset.getModel();
    }

    public boolean equals(Object obj) {
        if (obj instanceof PlatformTimezone) {
            PlatformTimezone platformTimezone = (PlatformTimezone) obj;
            if (this.id == null) {
                return platformTimezone.id == null;
            }
            if (this.tz.equals(platformTimezone.tz) && this.strict == platformTimezone.strict) {
                ZonalOffset zonalOffset = this.fixedOffset;
                if (zonalOffset == null) {
                    return platformTimezone.fixedOffset == null;
                }
                return zonalOffset.equals(platformTimezone.fixedOffset);
            }
        }
        return false;
    }

    public int hashCode() {
        if (this.id == null) {
            return 0;
        }
        return this.tz.hashCode();
    }

    public String toString() {
        TimeZone timeZone = this.id == null ? TimeZone.getDefault() : this.tz;
        StringBuilder sb = new StringBuilder(256);
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        sb.append(getClass().getName());
        sb.append(AbstractJsonLexerKt.COLON);
        sb.append(timeZone);
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    @Override // net.time4j.tz.Timezone
    public String getDisplayName(NameStyle nameStyle, Locale locale) {
        return (this.id == null ? TimeZone.getDefault() : this.tz).getDisplayName(nameStyle.isDaylightSaving(), !nameStyle.isAbbreviation() ? 1 : 0, locale);
    }

    @Override // net.time4j.tz.Timezone
    public TransitionStrategy getStrategy() {
        return this.strict ? STRICT_MODE : DEFAULT_CONFLICT_STRATEGY;
    }

    @Override // net.time4j.tz.Timezone
    public Timezone with(TransitionStrategy transitionStrategy) {
        if (this.id == null || getStrategy() == transitionStrategy) {
            return this;
        }
        if (transitionStrategy == DEFAULT_CONFLICT_STRATEGY) {
            return new PlatformTimezone(this.id, this.tz, false);
        }
        if (transitionStrategy == STRICT_MODE) {
            return new PlatformTimezone(this.id, this.tz, true);
        }
        throw new UnsupportedOperationException(transitionStrategy.toString());
    }

    static TimeZone findZone(String str) {
        if (str.equals("Z")) {
            return TimeZone.getTimeZone("GMT+00:00");
        }
        if (str.startsWith("UTC")) {
            return TimeZone.getTimeZone(TimeZones.GMT_ID + str.substring(3));
        }
        if (str.startsWith("UT")) {
            return TimeZone.getTimeZone(TimeZones.GMT_ID + str.substring(2));
        }
        return TimeZone.getTimeZone(str);
    }

    boolean isGMT() {
        return (this.id == null ? TimeZone.getDefault() : this.tz).getID().equals(TimeZones.GMT_ID);
    }

    private static ZonalOffset fromOffsetMillis(int i) {
        return ZonalOffset.ofTotalSeconds(MathUtils.floorDivide(i, 1000));
    }

    private Object readResolve() {
        TZID tzid = this.id;
        if (tzid == null) {
            return new PlatformTimezone();
        }
        return new PlatformTimezone(tzid, this.tz, this.strict);
    }
}
